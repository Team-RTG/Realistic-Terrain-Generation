package rtg.world.biome.realistic.arsmagica;

import rtg.config.arsmagica.ConfigAM;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeAMBase extends RealisticBiomeBase
{
    
    public RealisticBiomeAMBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(b, riverbiome, t, s);
        
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("arsmagica2") && ConfigAM.generateAMBiomes)
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    BiomeGenBase amBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "WitchwoodForest" && biomeClass == "am2.worldgen.BiomeWitchwoodForest")
                    {
                        if (ConfigAM.generateAMWitchwoodForest) {
                            BiomeBase.addBiome(
                                new RealisticBiomeAMWitchwoodForest(amBiome)
                                );
                        }
                    }
                }
            }
        }
    }
}
