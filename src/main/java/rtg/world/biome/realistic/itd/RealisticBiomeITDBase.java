package rtg.world.biome.realistic.itd;


import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.itd.config.BiomeConfigITD;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;


public class RealisticBiomeITDBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase itdDarkForest;

    public RealisticBiomeITDBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes()
    {
        if (Loader.isModLoaded("InTheDarkness"))
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();

            for(int i = 0; i < 256; i++)
            {
                if(b[i] != null)
                {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }

                    BiomeGenBase itdBiome = b[i];
                    String biomeName = itdBiome.biomeName;
                    String biomeClass = itdBiome.getBiomeClass().getName();

                    if (biomeName.equals("darkForest") && biomeClass.equals("mod.mcreator.mcreator_darkForest$BiomeGendarkForest"))
                    {
                        itdDarkForest = new RealisticBiomeITDDarkForest(itdBiome, BiomeConfigITD.biomeConfigITDDarkForest);
                    }
                }
            }
        }
    }
}