package rtg.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.noise.OpenSimplexNoise;

/**
 *
 * @author topisani
 *
 */
public enum CanyonColour {

    MESA(RTGConfig.getPlateauGradientBlockMetasFromConfigString(RTGAPI.config().MESA_GRADIENT_STRING.get())),
    MESA_BRYCE(RTGConfig.getPlateauGradientBlockMetasFromConfigString(RTGAPI.config().MESA_BRYCE_GRADIENT_STRING.get())),
    SAVANNA(RTGConfig.getPlateauGradientBlockMetasFromConfigString(RTGAPI.config().SAVANNA_GRADIENT_STRING.get()));

    private static Map<CanyonColour, IBlockState[]> colourBlocks = new HashMap<CanyonColour, IBlockState[]>();
    private static OpenSimplexNoise simplex;
    private byte[] bytes;

    private static IBlockState plateauBlock = Block.getBlockFromName(RTGAPI.config().PLATEAU_BLOCK_ID.get()).getStateFromMeta(RTGAPI.config().PLATEAU_BLOCK_META.get());
    private static Block plateauGradientBlock = Block.getBlockFromName(RTGAPI.config().PLATEAU_GRADIENT_BLOCK_ID.get());

    CanyonColour(byte[] bytes) {
        this.bytes = bytes;
    }

    public static void init(long l) {

        simplex = new OpenSimplexNoise(l);

        for (CanyonColour colour : CanyonColour.values()) {

            IBlockState[] c = new IBlockState[256];
            int j;

            for (int i = 0; i < 256; i++) {

                byte b = colour.bytes[i % colour.bytes.length];
                c[i] = (b == -1) ? plateauBlock : plateauGradientBlock.getStateFromMeta(b);
            }

            colourBlocks.put(colour, c);
        }
    }

    public IBlockState getBlockForHeight(int x, int y, int z) {

        return getBlockForHeight(x, (float)y, z);
    }

    public IBlockState getBlockForHeight(int x, float y, int z) {

        y = (y < 0) ? 0 : (y > 255) ? 255 : y;

        return colourBlocks.get(this)[(int)y];
    }
}

