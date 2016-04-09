package teamrtg.rtg.util.math;

import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

public class SnowHeightCalculator {
    public static void calc(int x, int y, int z, ChunkPrimer primer, float[] noise) {
        if (y < 254) {
            byte h = (byte) ((noise[x * 16 + z] - ((int) noise[x * 16 + z])) * 8);

            if (h > 7) {
                primer.setBlockState(x, y + 2, z, Blocks.snow_layer.getDefaultState());
                primer.setBlockState(x, y + 1, z, Blocks.snow_layer.getStateFromMeta(7));
            } else {
                primer.setBlockState(x, y + 1, z, Blocks.snow_layer.getStateFromMeta(h));
            }
        }
    }
}
