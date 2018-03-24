package rtg.api.util;

import javax.annotation.Nullable;

import net.minecraft.block.BlockSnow;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import rtg.world.gen.genlayer.GenLayerNoRivers;

@UtilityClass
public final class WorldUtil
{
    private WorldUtil() {}

    @UtilityClass
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

        public static GenLayer[] removeRivers(GenLayer[] original) {
            try {
                GenLayer biomePattern = ReflectionHelper.getPrivateValue(GenLayerRiverMix.class, (GenLayerRiverMix)original[0], "field_75910_b", "biomePatternGeneratorChain");
                GenLayer noRivers = new GenLayerNoRivers(100L, biomePattern);
                GenLayerVoronoiZoom vzoon = new GenLayerVoronoiZoom(10L, noRivers);
                return new GenLayer[]{noRivers, vzoon, noRivers};
            }
            catch (Exception ex) {
                Logger.error("RiverRemover failed, returning original GenLayers. -> {}", ex.getMessage());
                return original;
            }
        }
    }

    @UtilityClass
    public static final class Terrain
    {
        private Terrain() {}

        public static double dis2Elliptic(double x1, double y1, double x2, double y2, double fX, double fY) { return Math.sqrt((x1-x2)*(x1-x2)/fX*fX + (y1-y2)*(y1-y2)/fY*fY); }

        public static float calcCliff(int x, int z, float[] noise) {
            float cliff = 0f;
            if (x >  0) { cliff = Math.max(cliff, Math.abs(noise[x * 16 + z] - noise[(x - 1) * 16 + z])); }
            if (z >  0) { cliff = Math.max(cliff, Math.abs(noise[x * 16 + z] - noise[x * 16 + z - 1])); }
            if (x < 15) { cliff = Math.max(cliff, Math.abs(noise[x * 16 + z] - noise[(x + 1) * 16 + z])); }
            if (z < 15) { cliff = Math.max(cliff, Math.abs(noise[x * 16 + z] - noise[x * 16 + z + 1])); }
            return cliff;
        }

        public static void calcSnowHeight(int x, int y, int z, ChunkPrimer primer, float[] noise) {
            if (y < 254) {
                byte h = (byte) ((noise[x * 16 + z] - ((int) noise[x * 16 + z])) * 8);
                if (h > 7) {
                    primer.setBlockState(x, y + 2, z, Blocks.SNOW_LAYER.getDefaultState());
                    primer.setBlockState(x, y + 1, z, Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, 7));
                }
                else if (h > 0) { primer.setBlockState(x, y + 1, z, Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, (int)h)); }
            }
        }

        public static float bayesianAdjustment(float probability, float multiplier) {
            // returns the original probability adjusted for the multiplier to the confidence ratio
            // useful for computationally cheap remappings within [0,1]
            if (probability >= 1) return probability;
            if (probability <= 0) return probability;
            float newConfidence = probability*multiplier/(1f-probability);
            return newConfidence/(1f+newConfidence);
        }
    }
}
