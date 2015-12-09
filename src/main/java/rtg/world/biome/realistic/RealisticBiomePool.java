package rtg.world.biome.realistic;

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;


public class RealisticBiomePool 
{
    private ArrayList<Integer> biomePool;
    private CellNoise biomecell;
    private Random rand;
    private ArrayList<Integer> biomes_all;
    private int biomes_allLength;
    
    public RealisticBiomePool(CellNoise bc, Random r)
    {
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
                    
                    if (ConfigRTG.enableDebugging) {
                        FMLLog.log(Level.INFO, "Added %s at temp %d.", rb.getRealisticBiomeName(), btemp);
                    }
                }
            }
        }
    }
    
    public RealisticBiomeBase chooseBiome(double par1, double par2)
    {
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
}
