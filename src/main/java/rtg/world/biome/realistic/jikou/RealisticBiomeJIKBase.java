package rtg.world.biome.realistic.jikou;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeJIKBase extends RealisticBiomeBase {

    public static RealisticBiomeBase jikJikou;
    public static RealisticBiomeBase jikCherry;

    public RealisticBiomeJIKBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
    }

    public static void addBiomes() {

        if (Mods.jikou.isLoaded()) {

// TODO: 1.12 Clean up how these rBiomes are initialised. Migrate to looking up ResourceLocations in the registry.
            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("jikou") && biomeClass.equals("mod.mcreator.mcreator_jikou$BiomeGenjikou")) {
                    jikJikou = new RealisticBiomeJIKJikou(biome);
                }
                else if (biomeName.equals("cherry") && biomeClass.equals("mod.mcreator.mcreator_cherry$BiomeGencherry")) {
                    jikCherry = new RealisticBiomeJIKCherry(biome);
                }
            }
        }
    }
}
