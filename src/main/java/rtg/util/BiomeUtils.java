package rtg.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;

import static net.minecraft.world.biome.BiomeGenBase.biomeRegistry;
import static net.minecraft.world.biome.BiomeGenBase.getIdForBiome;

public class BiomeUtils {

    private static BiomeGenBase[] registeredBiomes = new BiomeGenBase[256];

    static {
        for (BiomeGenBase b : BiomeGenBase.biomeRegistry) {
            registeredBiomes[getIdForBiome(b)] = b;
        }
    }

    public static BiomeGenBase[] getRegisteredBiomes() {
        return registeredBiomes;
    }

    public static int biomeIds() {
        return 256;
    }

    public static ResourceLocation getLocForBiome(BiomeGenBase biome) {
        return biomeRegistry.getNameForObject(biome);
    }

    public static BiomeGenBase getBiomeForLoc(ResourceLocation location) {
        return biomeRegistry.getObject(location);
    }
}
