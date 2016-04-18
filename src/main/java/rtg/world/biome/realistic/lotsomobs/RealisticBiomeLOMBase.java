package rtg.world.biome.realistic.lotsomobs;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.lotsomobs.config.BiomeConfigLOM;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

public class RealisticBiomeLOMBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase lomAntartica;
    public static RealisticBiomeBase lomTropicalBeach;
    
    public RealisticBiomeLOMBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("lom"))
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }
                    
                    BiomeGenBase lomBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "Antartica" && biomeClass == "com.lom.lotsomobsbiomes.BiomeGenAntartica")
                    {
                        lomAntartica = new RealisticBiomeLOMAntartica(lomBiome, BiomeConfigLOM.biomeConfigLOMAntartica);
                    }
                    else if (biomeName == "Tropical Beach" && biomeClass == "com.lom.lotsomobsbiomes.BiomeGenTropical")
                    {
                        lomTropicalBeach = new RealisticBiomeLOMTropicalBeach(lomBiome, BiomeConfigLOM.biomeConfigLOMTropicalBeach);
                    }
                }
            }
        }
    }
}
