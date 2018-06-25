package rtg.world.biome.realistic.betteragriculture;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeBABase extends RealisticBiomeBase {

    public static RealisticBiomeBase baFarmlandBiome;

    public RealisticBiomeBABase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
    }

    public static void addBiomes() {

        if (Mods.betteragriculture.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("FarmlandBiome") && biomeClass.equals("betteragriculture.world.biome.FarmlandBiome")) {
                    baFarmlandBiome = new RealisticBiomeBAFarmlandBiome(biome);
                }
            }
        }
    }
}
