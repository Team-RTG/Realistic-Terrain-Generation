package rtg.world.biome.realistic.growthcraft;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.growthcraft.config.BiomeConfigGC;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeGCBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase gcBambooForest;
    
    public RealisticBiomeGCBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("Growthcraft|Bamboo"))
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    BiomeGenBase gcBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if ((biomeName == "BambooForest" || biomeName == "Bamboo Forest") && biomeClass == "growthcraft.bamboo.world.BiomeGenBamboo")
                    {
                        gcBambooForest = new RealisticBiomeGCBambooForest(gcBiome, BiomeConfigGC.biomeConfigGCBambooForest);
                    }
                }
            }
        }
    }
}
