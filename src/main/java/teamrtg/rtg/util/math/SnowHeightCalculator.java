package teamrtg.rtg.util.math;

import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

public class SnowHeightCalculator {
    //TODO: This is just wrong, but thats how its done in 1.7, so why change it?
    public static void calc(int x, int z, int y, ChunkPrimer primer, float[] noise) {
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
