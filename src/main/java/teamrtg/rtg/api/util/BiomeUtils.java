package teamrtg.rtg.api.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import static net.minecraft.world.biome.Biome.REGISTRY;

public class BiomeUtils {

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
}
