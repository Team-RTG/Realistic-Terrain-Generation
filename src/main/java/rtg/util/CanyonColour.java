package rtg.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * 
 * @author topisani
 *
 */
public enum CanyonColour {
	MESA(new byte[]{-1, -1, -1, 1, 1, 1, 0, -1, -1, 6, 1, 1, 8, 0, -1, -1, 14, -1, -1, 6, 1, 1, 4}),
	MESA_BRYCE(new byte[]{-1, -1, 0, 1, 0, 0, 0, 14, 0, 8, 0, 1, 8, 0, -1, 0, 14, 0, 0, 14, 0, 0, 8}),
	SAVANNA(new byte[]{0, 0, 0, 0, 8, 8, 12, 12, 8, 0, 8, 12, 12, 8, 12, 8, 0, 0, 8, 12, 12,});

	private static Map<CanyonColour, Block[]> colourBlocks = new HashMap<CanyonColour, Block[]>();
	private static Map<CanyonColour, byte[]> colourMetas = new HashMap<CanyonColour, byte[]>();
	private static OpenSimplexNoise simplex;
	private byte[] bytes;

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
				c[i] = (b == -1) ? Blocks.hardened_clay : Blocks.stained_hardened_clay;
				cm[i] = (b == -1) ? (byte)0 : b;
			}
			colourBlocks.put(colour, c);
			colourMetas.put(colour, cm);
		}
	}

	public Block getBlockForHeight(int x, int y, int z) {
		float y1 = y + (simplex.noise3((float)x / 80f, (float)y / 6, (float) z / 80f) * 3f);
		y1 += simplex.noise2((float) x / 70f, (float) z / 70f) * 3f;
		y1 = (y1 < 0)? 0 : (y1 > 255)? 255 : y1;
		return colourBlocks.get(this)[Math.round(y1)];
	}
	
	public byte getMetaForHeight(int x, int y, int z) {
		float y1 = y + (simplex.noise3((float)x / 80f, (float)y / 6, (float) z / 80f) * 3f);
		y1 += simplex.noise2((float) x / 70f, (float) z / 70f) * 3f;
		y1 = (y1 < 0)? 0 : (y1 > 255)? 255 : y1;
		return colourMetas.get(this)[Math.round(y1)];
	}
}
