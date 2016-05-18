package teamrtg.rtg.api.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;

import static net.minecraft.world.biome.BiomeGenBase.REGISTRY;

public class BiomeUtils {

    private static BiomeGenBase[] registeredBiomes = new BiomeGenBase[256];

    static {
        for (BiomeGenBase b : BiomeGenBase.REGISTRY) {
            registeredBiomes[getId(b)] = b;
        }
    }

    public static BiomeGenBase[] getRegisteredBiomes() {
        return registeredBiomes;
    }

    public static int biomeIds() {
        return 256;
    }

    public static ResourceLocation getLocForBiome(BiomeGenBase biome) {
        return REGISTRY.getNameForObject(biome);
    }

    public static BiomeGenBase getBiomeForLoc(ResourceLocation location) {
        return REGISTRY.getObject(location);
    }

    public static int getId(BiomeGenBase biome) {
        return BiomeGenBase.getIdForBiome(biome);
    }
}
