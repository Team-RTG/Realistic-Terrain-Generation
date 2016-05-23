package teamrtg.rtg.api.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.gen.RealisticBiomeGenerator;

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

    public static RealisticBiomeBase getRealistic(BiomeGenBase biome) {
        return RealisticBiomeGenerator.getBiome(BiomeUtils.getId(biome));
    }

    public static BiomeGenBase getBGB(RealisticBiomeBase biome) {
        return biome.baseBiome;
    }
}
