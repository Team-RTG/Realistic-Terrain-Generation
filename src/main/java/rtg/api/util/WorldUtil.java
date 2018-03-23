package rtg.api.util;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

@UtilityClass
public final class WorldUtil
{
    private WorldUtil() {}

    public static final class Biomes
    {
        private Biomes() {}

        @Nullable
        public static Biome getBiomeFromCfgString(final String cfgString) {
            ResourceLocation rl = new ResourceLocation(cfgString);
            if (Biome.REGISTRY.containsKey(rl)) { return Biome.REGISTRY.getObject(rl); }
            return null;
        }

        public static Biome getBiomeFromCfgString(final String cfgString, final Biome fallback) {
            Biome biome = getBiomeFromCfgString(cfgString);
            return biome != null ? biome : fallback;
        }
    }
}
