package rtg.world.biome.realistic.buildcraft;

import rtg.config.buildcraft.ConfigBC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBCBase extends RealisticBiomeBase
{
    
    public RealisticBiomeBCBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(b, riverbiome, t, s);
        
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("BuildCraft|Core") && ConfigBC.generateBCBiomes)
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    BiomeGenBase bcBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "Desert Oil Field" && biomeClass == "buildcraft.energy.worldgen.BiomeGenOilDesert")
                    {
                        if (ConfigBC.generateBCDesertOilField) {
                            BiomeBase.addBiome(
                                new RealisticBiomeBCDesertOilField(bcBiome)
                                );
                        }
                    }
                    else if (biomeName == "Ocean Oil Field" && biomeClass == "buildcraft.energy.worldgen.BiomeGenOilOcean")
                    {
                        if (ConfigBC.generateBCOceanOilField) {
                            BiomeBase.addBiome(
                                new RealisticBiomeBCOceanOilField(bcBiome)
                                );
                        }
                    }
                }
            }
        }
    }
}
