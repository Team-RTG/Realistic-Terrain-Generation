package rtg.world.biome.realistic;

import java.util.ArrayList;
import java.util.Random;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.world.biome.BiomeBase;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;


public class RealisticBiomePool extends GenLayer
{
    private ArrayList<Integer> biomePool;
    private CellNoise biomecell;
    private Random rand;
    private ArrayList<Integer> biomes_all;
    private int biomes_allLength;

    public RealisticBiomePool(long seed) {
        super(seed);
        biomecell = new CellNoise(seed, (short) 0);
        rand = new Random(seed);

        biomePool = new ArrayList<Integer>();

        biomes_all = new ArrayList<Integer>();

        biomes_all.addAll(BiomeBase.biomes_snow);
        biomes_all.addAll(BiomeBase.biomes_cold);
        biomes_all.addAll(BiomeBase.biomes_wet);
        biomes_all.addAll(BiomeBase.biomes_hot);

        biomes_allLength = biomes_all.size();

        int minTemp = 1000000; //Not a typo. This will get reset to a lower value below.
        int maxTemp = -1000000; //Not a typo. This will get reset to a higher value below.

        for (int intBiomeIndex = 0; intBiomeIndex < biomes_allLength; intBiomeIndex++) {

            RealisticBiomeBase rb = RealisticBiomeBase.getBiome(biomes_all.get(intBiomeIndex));
            BiomeGenBase rbb = rb.baseBiome;
            float rbbtemp = rbb.temperature;
            int btemp = (int) (rbbtemp * 100);

            minTemp = (btemp < minTemp) ? btemp : minTemp;
            maxTemp = (btemp > maxTemp) ? btemp : maxTemp;
        }

        for (int intTempIndex = minTemp; intTempIndex <= maxTemp; intTempIndex++) {
            for (int intBiomeIndex = 0; intBiomeIndex < biomes_all.size(); intBiomeIndex++) {

                RealisticBiomeBase rb = RealisticBiomeBase.getBiome(biomes_all.get(intBiomeIndex));
                BiomeGenBase rbb = rb.baseBiome;
                float rbbtemp = rbb.temperature;
                int btemp = (int) (rbbtemp * 100);

                if (btemp == intTempIndex) {

                    biomePool.add(rb.baseBiome.biomeID);
                }
            }
        }
    }
    public RealisticBiomePool(CellNoise bc, Random r)
    {
        super(0L);
        biomecell = bc;
        rand = r;
        
        biomePool = new ArrayList<Integer>();

        biomes_all = new ArrayList<Integer>();
        
        biomes_all.addAll(BiomeBase.biomes_snow);
        biomes_all.addAll(BiomeBase.biomes_cold);
        biomes_all.addAll(BiomeBase.biomes_wet);
        biomes_all.addAll(BiomeBase.biomes_hot);

        biomes_allLength = biomes_all.size();
        
        int minTemp = 1000000; //Not a typo. This will get reset to a lower value below.
        int maxTemp = -1000000; //Not a typo. This will get reset to a higher value below.
        
        for (int intBiomeIndex = 0; intBiomeIndex < biomes_allLength; intBiomeIndex++) {
            
            RealisticBiomeBase rb = RealisticBiomeBase.getBiome(biomes_all.get(intBiomeIndex));
            BiomeGenBase rbb = rb.baseBiome;
            float rbbtemp = rbb.temperature;
            int btemp = (int) (rbbtemp * 100);
            
            minTemp = (btemp < minTemp) ? btemp : minTemp;
            maxTemp = (btemp > maxTemp) ? btemp : maxTemp;
        }
        
        for (int intTempIndex = minTemp; intTempIndex <= maxTemp; intTempIndex++) {
            for (int intBiomeIndex = 0; intBiomeIndex < biomes_all.size(); intBiomeIndex++) {

                RealisticBiomeBase rb = RealisticBiomeBase.getBiome(biomes_all.get(intBiomeIndex));
                BiomeGenBase rbb = rb.baseBiome;
                float rbbtemp = rbb.temperature;
                int btemp = (int) (rbbtemp * 100);
                
                if (btemp == intTempIndex) {
                    
                    biomePool.add(rb.baseBiome.biomeID);
                }
            }
        }
    }
    
    public RealisticBiomeBase chooseBiome(double par1, double par2)
    {
        int generateOnlyThisBiomeId = (int) ConfigRTG.generateOnlyThisBiomeId;

        if (generateOnlyThisBiomeId > -1)
        {
            return RealisticBiomeBase.getBiome(generateOnlyThisBiomeId);
        }
        RealisticBiomeBase output = null;
        int biomeSize = ConfigRTG.biomeSize;
        
        /**
         * This is where we determine the size of the biomes.
         */
        int normalSize = 3;
        int normalDivisor = 450;
        biomeSize = (int)((normalDivisor * biomeSize) / normalSize);
        
        float bcn = (biomecell.noise(par1 / (double)biomeSize, par2 / (double)biomeSize, 1D));
        
        //FMLLog.log(Level.INFO, "%f|%f", par1, par2);
        
        bcn = normalize(bcn, -1f, 1f, 0f, 1f);
        bcn = bcn < 0f ? 0f : bcn >= 0.9999999f ? 0.9999999f : bcn;
        bcn *= biomePool.size();
                
        output = RealisticBiomeBase.getBiome(biomePool.get((int) (bcn)));
                
        return output;
    }
    
    private float normalize(float value, float minOld, float maxOld, float minNew, float maxNew) {
                 
        return (maxNew - minNew) / (maxOld - minOld) * (value - maxOld) + maxNew;
    }

    @Override
    public int[] getInts(int x0, int z0, int xSize, int zSize) {// not actually sure which is X and which is Z
        int[] aint = IntCache.getIntCache(xSize * zSize);
        for (int zDelta = 0;zDelta<zSize;zDelta++) {
            for  (int xDelta = 0;xDelta<xSize;xDelta++){
                aint[xDelta + zDelta * xSize] = this.chooseBiome(x0+xDelta, z0+zDelta).biomeID;
            }
        }
        return aint;
    }
}
