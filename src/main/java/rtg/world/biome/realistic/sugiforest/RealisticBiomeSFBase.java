package rtg.world.biome.realistic.sugiforest;

import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.sugiforest.config.BiomeConfigSF;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeSFBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase sfSugiForest;
    
    public RealisticBiomeSFBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("kegare.sugiforest"))
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
                    
                    BiomeGenBase sfBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();

                    if (biomeName.equals("Sugi Forest") && biomeClass.equals("com.kegare.sugiforest.world.BiomeGenSugiForest")) {
                        sfSugiForest = new RealisticBiomeSFSugiForest(sfBiome, BiomeConfigSF.biomeConfigSFSugiForest);
                    }
                }
            }
        }
    }
}
