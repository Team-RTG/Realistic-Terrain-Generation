package rtg.world.biome.realistic.vampirism;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vampirism.config.BiomeConfigVAMP;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVAMPBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase vampVampireForest;
    
    public RealisticBiomeVAMPBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("vampirism"))
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
                    
                    BiomeGenBase vampBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "vampireForest" && biomeClass == "de.teamlapen.vampirism.biome.BiomeVampireForest")
                    {
                        vampVampireForest = new RealisticBiomeVAMPVampireForest(vampBiome, BiomeConfigVAMP.biomeConfigVAMPVampireForest);
                    }
                }
            }
        }
    }
}
