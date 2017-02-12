package rtg.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;

import rtg.config.rtg.ConfigRTG;

/**
 * 
 * @author topisani
 *
 */
public enum CanyonColour {

    MESA(ConfigRTG.mesaPlateauBlockMetas),
    MESA_BRYCE(ConfigRTG.mesaBrycePlateauBlockMetas),
    SAVANNA(ConfigRTG.savannaPlateauBlockMetas);

	private static Map<CanyonColour, Block[]> colourBlocks = new HashMap<CanyonColour, Block[]>();
	private static Map<CanyonColour, byte[]> colourMetas = new HashMap<CanyonColour, byte[]>();
	private static OpenSimplexNoise simplex;
	private byte[] bytes;

    private static Block plateauBlock = Block.getBlockFromName(ConfigRTG.plateauBlockId);
    private static byte plateauBlockMeta = (byte)ConfigRTG.plateauBlockByte;
    private static Block plateauGradientBlock = Block.getBlockFromName(ConfigRTG.plateauGradientBlockId);

	CanyonColour(byte[] bytes) {
		this.bytes = bytes;
	}

	public static void init(long l) {
		simplex = new OpenSimplexNoise(l);

		for (CanyonColour colour : CanyonColour.values()) {
			Block[] c = new Block[256];
			byte[] cm = new byte[256];
			int j;
			for (int i = 0; i < 256; i++) {
				byte b = colour.bytes[i % colour.bytes.length];
                c[i] = (b == -1) ? plateauBlock : plateauGradientBlock;
				cm[i] = (b == -1) ? plateauBlockMeta : b;
			}
			colourBlocks.put(colour, c);
			colourMetas.put(colour, cm);
		}
	}

	public Block getBlockForHeight(int x, int y, int z) {
        return getBlockForHeight(x,(float)y,z);
	}

    public Block getBlockForHeight(int x, float y, int z) {
		float y1 = y + (simplex.noise3((float)x / 80f, (float)y / 6, z / 80f) * 3f);
		y1 += simplex.noise2((float) x / 70f, (float) z / 70f) * 3f;
		y1 = (y1 < 0)? 0 : (y1 > 255)? 255 : y1;
		return colourBlocks.get(this)[Math.round(y1)];
	}
	
	public byte getMetaForHeight(int x, int y, int z) {
		return getMetaForHeight(x,(float)y,z);
	}

    public byte getMetaForHeight(int x, float y, int z) {
		float y1 = y + (simplex.noise3((float)x / 80f, (float)y / 6, (float) z / 80f) * 3f);
		y1 += simplex.noise2((float) x / 70f, (float) z / 70f) * 3f;
		y1 = (y1 < 0)? 0 : (y1 > 255)? 255 : y1;
		return colourMetas.get(this)[Math.round(y1)];
	}
}
