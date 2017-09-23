package rtg.world.biome.realistic.rockhoundingsurface;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeRHSBase extends RealisticBiomeBase {

    public static RealisticBiomeBase rhsWhiteSands;

    public RealisticBiomeRHSBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
    }

    @Override
    public String modSlug() {
        return "rockhoundingsurface";
    }

    @Override
    public Biome baseBiome() {
        return this.baseBiome;
    }

    @Override
    public Biome riverBiome() {
        return this.riverBiome;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("rockhounding_surface")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("White Sands") && biomeClass.equals("com.globbypotato.rockhounding_surface.world.BiomeWhiteSands")) {
                    rhsWhiteSands = new RealisticBiomeRHSWhiteSands(biome);
                }
            }
        }
    }
}
