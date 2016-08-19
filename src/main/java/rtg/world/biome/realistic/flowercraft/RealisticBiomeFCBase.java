package rtg.world.biome.realistic.flowercraft;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.flowercraft.config.BiomeConfigFC;
import rtg.util.BiomeUtils;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeFCBase extends RealisticBiomeBase {

    public static RealisticBiomeBase fcPhantasia;

    public RealisticBiomeFCBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("flowercraftmod")) {

            Biome[] b = BiomeUtils.getRegisteredBiomes();

            for (int i = 0; i < 256; i++) {

                if (b[i] != null) {

                    if (BiomeUtils.getName(b[i]) == null) {

                        Logger.warn("Biome ID %d has no name.", BiomeUtils.getId(b[i]));
                        continue;
                    }

                    Biome biome = b[i];
                    String biomeName = BiomeUtils.getName(biome);
                    String biomeClass = biome.getBiomeClass().getName();

                    if (biomeName.equals("Phantasia") && biomeClass.equals("flowercraftmod.world.biome.BiomeGenFCPhantasia")) {
                        fcPhantasia = new RealisticBiomeFCPhantasia(biome, BiomeConfigFC.biomeConfigFCPhantasia);
                    }
                }
            }
        }
    }
}