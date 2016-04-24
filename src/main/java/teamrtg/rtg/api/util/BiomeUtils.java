package teamrtg.rtg.api.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import static net.minecraft.world.biome.BiomeGenBase.REGISTRY;

public class BiomeUtils {

    private static BiomeGenBase[] registeredBiomes = new BiomeGenBase[256];

    static {
        for (BiomeGenBase b : BiomeGenBase.REGISTRY) {
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
        return REGISTRY.getNameForObject(biome);
    }

    public static BiomeGenBase getBiomeForLoc(ResourceLocation location) {
        return REGISTRY.getObject(location);
    }

    public static int getIdForBiome(BiomeGenBase biome) {
        if (biome instanceof RealisticBiomeBase)
            return BiomeGenBase.getIdForBiome(((RealisticBiomeBase) biome).baseBiome);
        return BiomeGenBase.getIdForBiome(biome);
    }
}
