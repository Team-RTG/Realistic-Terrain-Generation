package rtg.world.biome.realistic.arsmagica;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeAMBase extends RealisticBiomeBase {

    public static RealisticBiomeBase amWitchwoodForest;

    public RealisticBiomeAMBase(Biome b, Biome river) {

        super(b, river);
    }

    @Override
    public Biome baseBiome() {
        return this.baseBiome;
    }

    @Override
    public Biome riverBiome() {
        return this.riverBiome;
    }

    @Override
    public String modSlug() {
        return ModCompat.arsmagica2.getPrettyName();
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (ModCompat.arsmagica2.isLoaded()) {

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
