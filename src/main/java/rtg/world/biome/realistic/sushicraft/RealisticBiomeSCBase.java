package rtg.world.biome.realistic.sushicraft;


import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.thaumcraft.config.BiomeConfigTC;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;
/**
 * Created by VelocityRa on 15/4/2016.
 */

public class RealisticBiomeSCBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase scSakuraForest;

    public RealisticBiomeSCBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes()
    {
        if (Loader.isModLoaded("sushicraft"))
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

                    BiomeGenBase scBiome = b[i];
                    String biomeName = scBiome.biomeName;
                    String biomeClass = scBiome.getBiomeClass().getName();

                    if (biomeName == "Sakura Forest" && biomeClass == "fr.kingstone.sushicraft.world.biome.BiomeGenSakura")
                    {
                        scSakuraForest = new RealisticBiomeSCSakuraForest(scBiome, BiomeConfigTC.biomeConfigTCMagicalForest);
                    }
                }
            }
        }
    }
}