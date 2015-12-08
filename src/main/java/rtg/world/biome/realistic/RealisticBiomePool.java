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
        
        //float b = (biomecell.noise((par1 + (float)(biomes_allLength * 20)) / (float)(biomes_allLength * 6), par2 / (float)(biomes_allLength * 6), 1D) * 0.5f) + 0.5f;
        float b = (biomecell.noise((par1 + 4000f) / 1200D, par2 / 1200D, 1D) * 0.5f) + 0.5f;
        b = b < 0f ? 0f : b >= 0.9999999f ? 0.9999999f : b;
        
        //FMLLog.log(Level.INFO, "bcn=%f|b=%f|%d", bcn, b, biomes_allLength);
        
        float h = calculateNoiseForTempBiome(par1, par2);
        h *= biomePool.size();
        
        output = RealisticBiomeBase.getBiome(biomePool.get((int) (h)));
        
        // FMLLog.log(Level.INFO, "chooseSnowBiome: %s", output.getRealisticBiomeName());
        
        return output;
    }
    
    public RealisticBiomeBase chooseRandomBiome()
    {
        
        RealisticBiomeBase output = RealisticBiomeBase.getBiome(biomePool.get(rand.nextInt(biomePool.size())));
        
        // FMLLog.log(Level.INFO, "chooseRandomBiome: %s", output.getRealisticBiomeName());
        
        return output;
    }
    
    private float calculateNoiseForTempBiome(double par1, double par2) {
        
        //float h = (biomecell.noise(par1 / (float)(biomes_allLength * 2), par2 / (float)(biomes_allLength * 2), 1D) * 0.5f) + 0.5f;
        float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
        
        return h;
    }
}
