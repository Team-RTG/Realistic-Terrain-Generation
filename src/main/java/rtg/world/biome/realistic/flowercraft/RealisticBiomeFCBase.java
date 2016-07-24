package rtg.world.biome.realistic.flowercraft;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.flowercraft.config.BiomeConfigFC;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeFCBase extends RealisticBiomeBase {

    public static RealisticBiomeBase fcPhantasia;

    public RealisticBiomeFCBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("flowercraftmod")) {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();

            for (int i = 0; i < 256; i++) {
                if (b[i] != null) {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }

                    BiomeGenBase fcBiome = b[i];
                    String biomeName = fcBiome.biomeName;
                    String biomeClass = fcBiome.getBiomeClass().getName();

                    if (biomeName == "Phantasia" && biomeClass == "flowercraftmod.world.biome.BiomeGenFCPhantasia") {
                        fcPhantasia = new RealisticBiomeFCPhantasia(fcBiome, BiomeConfigFC.biomeConfigFCPhantasia);
                    }
                }
            }
        }
    }
}