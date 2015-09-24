package rtg.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class SnowHeightCalculatorTemp 
{
	public static void calc(int x, int y, int k, Block[] blocks, byte[] metadata, float[] noise)
	{
		if(k < 254)
		{
			byte h = (byte) ((noise[y * 16 + x] - ((int) noise[y * 16 + x])) * 8);
			
			if(h > 7)
			{
				blocks[(y * 16 + x) * 256 + k + 2] = Blocks.snow_layer;
				blocks[(y * 16 + x) * 256 + k + 1] = Blocks.snow_layer;
				metadata[(y * 16 + x) * 256 + k + 1] = 7;
			}
			else
			{
				blocks[(y * 16 + x) * 256 + k + 1] = Blocks.snow_layer;
				metadata[(y * 16 + x) * 256 + k + 1] = h;
			}
		}
	}
}
