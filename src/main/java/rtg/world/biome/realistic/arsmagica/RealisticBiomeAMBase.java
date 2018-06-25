package rtg.world.biome.realistic.arsmagica;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeAMBase extends RealisticBiomeBase {

    public static RealisticBiomeBase amWitchwoodForest;

    public RealisticBiomeAMBase(Biome b, Biome river) {

        super(b, river);
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (Mods.arsmagica2.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("WitchwoodForest") && biomeClass.equals("am2.world.BiomeWitchwoodForest")) {
                    amWitchwoodForest = new RealisticBiomeAMWitchwoodForest(biome);
                }
            }
        }
    }
}
