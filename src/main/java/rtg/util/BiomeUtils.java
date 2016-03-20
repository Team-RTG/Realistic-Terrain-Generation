package rtg.util;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.List;

public class BiomeUtils {

    public static List<BiomeGenBase> getRegisteredBiomes() {
        return Lists.newArrayList(BiomeGenBase.biomeRegistry.iterator());
    }

    public static int biomeIds() {
        return 256;
    }

    public static ResourceLocation getLocForBiome(BiomeGenBase biome) {
        return BiomeGenBase.biomeRegistry.getNameForObject(biome);
    }

    public static BiomeGenBase getBiomeForLoc(ResourceLocation location) {
        return BiomeGenBase.biomeRegistry.getObject(location);
    }
}
