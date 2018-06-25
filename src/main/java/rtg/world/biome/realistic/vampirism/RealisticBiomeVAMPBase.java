package rtg.world.biome.realistic.vampirism;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;


@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeVAMPBase extends RealisticBiomeBase {

    public static RealisticBiomeBase vampireForest;

    public RealisticBiomeVAMPBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        if (Mods.vampirism.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("vampireForest") && biomeClass.equals("de.teamlapen.vampirism.biome.BiomeGenVampireForest")) {
                    vampireForest = new RealisticBiomeVAMPVampireForest(biome);
                }
            }
        }
    }
}
