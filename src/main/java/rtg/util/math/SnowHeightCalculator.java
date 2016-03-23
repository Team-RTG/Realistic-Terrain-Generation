package rtg.util.math;

import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

public class SnowHeightCalculator {
    public static void calc(int x, int y, int k, ChunkPrimer primer, float[] noise) {
        if (k < 254) {
            byte h = (byte) ((noise[y * 16 + x] - ((int) noise[y * 16 + x])) * 8);

            if (h > 7) {
                primer.setBlockState(x, k, y + 2, Blocks.snow_layer.getDefaultState());
                primer.setBlockState(x, k, y + 1, Blocks.snow_layer.getStateFromMeta(7));
            } else {
                primer.setBlockState(x, k, y + 1, Blocks.snow_layer.getStateFromMeta(h));
            }
        }
    }
}
