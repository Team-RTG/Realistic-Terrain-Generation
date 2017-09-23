package rtg.api.util;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import static net.minecraft.world.biome.Biome.REGISTRY;

import net.minecraftforge.common.BiomeDictionary;

public class BiomeUtils {

    //TODO: Read this from somewhere
    private static Biome[] registeredBiomes = new Biome[256];

    static {
        for (Biome b : Biome.REGISTRY) {
            registeredBiomes[getId(b)] = b;
        }
    }

    public static Biome[] getRegisteredBiomes() {
        return registeredBiomes;
    }

    public static int biomeIds() {
        return 256;
    }

    public static ResourceLocation getLocForBiome(Biome biome) {
        return REGISTRY.getNameForObject(biome);
    }

    public static Biome getBiomeForLoc(ResourceLocation location) {
        return REGISTRY.getObject(location);
    }

    public static int getId(Biome biome) {
        return Biome.getIdForBiome(biome);
    }

    public static Biome getPreferredBeachForBiome(Biome biome) {

        /*
         * Some of this code is from Climate Control, and it's still a bit crude. - Zeno
         * Some of this code is from Pink's brain, and it's also a bit crude. - Pink
         */

        float height = biome.getBaseHeight() + (biome.getHeightVariation() * 2f);
        float temp = biome.getTemperature();

        // Use a cold beach if the temperature is low enough; otherwise, just use a normal beach.
        Biome beach = (temp <= 0.05f) ? Biomes.COLD_BEACH : Biomes.BEACH;

        // If this is a mountainous biome or a Taiga variant, then let's use a stone beach.
        if ((height > (1.0f + 0.5f) && temp > 0.05f) || isTaigaBiome(biome)) {
            beach = Biomes.STONE_BEACH;
        }

        // Snowy biomes should always use cold beach; otherwise, the transition looks too abrupt.
        if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY)) {
            beach = Biomes.COLD_BEACH;
        }

        return beach;
    }

    private static boolean isTaigaBiome(Biome biome) {
        return BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD)
            && BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.CONIFEROUS)
            && BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST)
            && !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY);
    }
}
