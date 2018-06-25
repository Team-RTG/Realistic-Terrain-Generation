package rtg.world.biome.realistic.mithwoodforest;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeMFBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mfMithwoodForest;

    public RealisticBiomeMFBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
    }

    public static void addBiomes() {

        if (Mods.mithwoodforest.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Mithwood Forest") && biomeClass.equals("rainbeau.mithwoodforest.RMFWorldGen.BiomeMithwoodForest")) {
                    mfMithwoodForest = new RealisticBiomeMFMithwoodForest(biome);
                }
            }
        }
    }
}
