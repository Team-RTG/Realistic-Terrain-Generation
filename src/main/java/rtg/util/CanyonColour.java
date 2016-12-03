package rtg.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

import rtg.RTG;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.config.RTGConfig;

/**
 *
 * @author topisani
 *
 */
public enum CanyonColour {

    MESA(RTGConfig.getPlateauGradientBlockMetasFromConfigString(RTG.instance.getConfig().mesaGradientString.get())),
    MESA_BRYCE(RTGConfig.getPlateauGradientBlockMetasFromConfigString(RTG.instance.getConfig().mesaBryceGradientString.get())),
    SAVANNA(RTGConfig.getPlateauGradientBlockMetasFromConfigString(RTG.instance.getConfig().savannaGradientString.get()));

    private static Map<CanyonColour, IBlockState[]> colourBlocks = new HashMap<CanyonColour, IBlockState[]>();
    private static OpenSimplexNoise simplex;
    private byte[] bytes;

    private static IBlockState plateauBlock = Block.getBlockFromName(RTG.instance.getConfig().plateauBlockId.get()).getStateFromMeta(RTG.instance.getConfig().plateauBlockByte.get());
    private static Block plateauGradientBlock = Block.getBlockFromName(RTG.instance.getConfig().plateauGradientBlockId.get());

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

