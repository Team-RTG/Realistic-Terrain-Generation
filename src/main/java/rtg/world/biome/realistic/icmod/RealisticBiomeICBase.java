package rtg.world.biome.realistic.icmod;

import cpw.mods.fml.common.Loader;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.icmod.config.BiomeConfigIC;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

/**
 * Created by VelocityRa on 16/4/2016.
 */
public class RealisticBiomeICBase extends RealisticBiomeBase {

    public static RealisticBiomeBase icIceCream;

    public RealisticBiomeICBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes()
    {
        if (Loader.isModLoaded("ICMod"))
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

                    BiomeGenBase icBiome = b[i];
                    String biomeName = icBiome.biomeName;
                    String biomeClass = icBiome.getBiomeClass().getName();

                    if (biomeName == "Ice Cream" && biomeClass == "com.pcr3w.iceCream.Biomes.BiomeGenIceCream")
                    {
                        icIceCream = new RealisticBiomeICIceCream(icBiome, BiomeConfigIC.biomeConfigICIceCream);
                    }
                }
            }
        }
    }
}
