package rtg.world.biome.realistic;

import java.util.ArrayList;
import java.util.Random;

import rtg.util.CellNoise;
import rtg.world.biome.BiomeBase;


public class RealisticBiomePool 
{
    private CellNoise biomecell;
    private Random rand;
    private ArrayList<RealisticBiomeBase> biomes_snow;
    private ArrayList<RealisticBiomeBase> biomes_cold;
    private ArrayList<RealisticBiomeBase> biomes_hot;
    private ArrayList<RealisticBiomeBase> biomes_wet;
    private ArrayList<RealisticBiomeBase> biomes_all;
    private int biomes_snowLength;
    private int biomes_coldLength;
    private int biomes_hotLength;
    private int biomes_wetLength;
    private int biomes_allLength;
    
    public RealisticBiomePool(CellNoise bc, Random r)
    {
        biomecell = bc;
        rand = r;
        
        biomes_snow = new ArrayList<RealisticBiomeBase>();
        biomes_cold = new ArrayList<RealisticBiomeBase>();
        biomes_hot = new ArrayList<RealisticBiomeBase>();
        biomes_wet = new ArrayList<RealisticBiomeBase>();
        biomes_all = new ArrayList<RealisticBiomeBase>();
        
        biomes_snow.addAll(BiomeBase.biomes_snow);
        biomes_cold.addAll(BiomeBase.biomes_cold);
        biomes_hot.addAll(BiomeBase.biomes_hot);
        biomes_wet.addAll(BiomeBase.biomes_wet);
        
        biomes_all.addAll(BiomeBase.biomes_snow);
        biomes_all.addAll(BiomeBase.biomes_cold);
        biomes_all.addAll(BiomeBase.biomes_wet);
        biomes_all.addAll(BiomeBase.biomes_hot);

        biomes_snowLength = biomes_snow.size();
        biomes_coldLength = biomes_cold.size();
        biomes_hotLength = biomes_hot.size();
        biomes_wetLength = biomes_wet.size();
        biomes_allLength = biomes_all.size();
    }
    
    public RealisticBiomeBase chooseBiome(double par1, double par2)
    {
        RealisticBiomeBase output = null;
        float b = (biomecell.noise((par1 + 4000f) / 1200D, par2 / 1200D, 1D) * 0.5f) + 0.5f;        
        b = b < 0f ? 0f : b >= 0.9999999f ? 0.9999999f : b;
                
        if (b < 0.25f) {
            
            if (biomes_snowLength < 1) {
                output = chooseColdBiome(par1, par2);
            }
            else {
                output = chooseSnowBiome(par1, par2);
            }
        }
        else if (b < 0.50f) {
            
            if (biomes_coldLength < 1) {
                output = chooseWetBiome(par1, par2);
            }
            else {
                output = chooseColdBiome(par1, par2);
            }
        }
        else if (b < 0.75f) {
            
            if (biomes_wetLength < 1) {
                output = chooseHotBiome(par1, par2);
            }
            else {
                output = chooseWetBiome(par1, par2);
            }
        }
        else if (b < 1) {

            if (biomes_hotLength < 1) {
                output = chooseRandomBiome();
            }
            else {
                output = chooseHotBiome(par1, par2);
            }            
        }
        else {
            
            //It should never make it into here, but just in case.
            output = chooseRandomBiome();
        }
        
        return output;
    }
    
    public RealisticBiomeBase chooseSnowBiome(double par1, double par2)
    {
        
        RealisticBiomeBase output;
        
        if (biomes_snowLength < 1) {
            output = chooseRandomBiome();
        }
        else {
            
            float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
            h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
            h *= biomes_snowLength;
            
            output = biomes_snow.get((int) (h));
            
            // FMLLog.log(Level.INFO, "chooseSnowBiome: %s", output.getRealisticBiomeName());
            
        }
        
        return output;
    }
    
    public RealisticBiomeBase chooseColdBiome(double par1, double par2)
    {
        
        RealisticBiomeBase output;
        
        if (biomes_coldLength < 1) {
            output = chooseRandomBiome();
        }
        else {
            
            float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
            h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
            h *= biomes_coldLength;
            
            output = biomes_cold.get((int) (h));
            
            // FMLLog.log(Level.INFO, "chooseColdBiome: %s", output.getRealisticBiomeName());
        }
        return output;
    }
    
    public RealisticBiomeBase chooseWetBiome(double par1, double par2)
    {
        
        RealisticBiomeBase output;
        
        if (biomes_wetLength < 1) {
            output = chooseRandomBiome();
        }
        else {
            
            float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
            h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
            h *= biomes_wetLength;
            
            output = biomes_wet.get((int) (h));
        }
        
        // FMLLog.log(Level.INFO, "chooseWetBiome: %s", output.getRealisticBiomeName());
        
        return output;
    }
    
    public RealisticBiomeBase chooseHotBiome(double par1, double par2)
    {
        
        RealisticBiomeBase output;
        
        if (biomes_hotLength < 1) {
            output = chooseRandomBiome();
        }
        else {
            
            float h = (biomecell.noise(par1 / 450D, par2 / 450D, 1D) * 0.5f) + 0.5f;
            h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
            h *= biomes_hotLength;
            
            output = biomes_hot.get((int) (h));
            
            // FMLLog.log(Level.INFO, "chooseHotBiome: %s", output.getRealisticBiomeName());
        }
        return output;
    }
    
    public RealisticBiomeBase chooseRandomBiome()
    {
        
        RealisticBiomeBase output = biomes_all.get(rand.nextInt(biomes_allLength));
        
        // FMLLog.log(Level.INFO, "chooseRandomBiome: %s", output.getRealisticBiomeName());
        
        return output;
    }
}
