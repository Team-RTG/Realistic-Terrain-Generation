package rtg.api.util;

import net.minecraft.block.BlockSnow;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

public class SnowHeightCalculator {

    public static void calc(int x, int y, int z, ChunkPrimer primer, float[] noise) {

        if (y < 254) {

            byte h = (byte) ((noise[x * 16 + z] - ((int) noise[x * 16 + z])) * 8);

            if (h > 7) {
                primer.setBlockState(x, y + 2, z, Blocks.SNOW_LAYER.getDefaultState());
                primer.setBlockState(x, y + 1, z, Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, 7));
            }
            else if (h > 0) {
                primer.setBlockState(x, y + 1, z, Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, (int)h));
            }
        }
    }
}
