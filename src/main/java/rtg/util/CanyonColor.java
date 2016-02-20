package rtg.util;

public class CanyonColor
{
	private static byte[] claycolor = new byte[256];
	
	public static void init(long l)
	{
		int[] c = new int[]{0, 1, 8, 14, 1, 8};
		OpenSimplexNoise simplex = new OpenSimplexNoise(l);
		
		float n;
		for(int i = 0; i < 256; i++)
		{
			n = 3f + simplex.noise1(i / 10f) * 8;
			n = n < 0f ? 0f : n > 5.9f ? 5.9f : n;
			claycolor[i] = (byte)c[(int)n];
		}
	}
	
	public static byte getColorForHeight(int height)
	{
		height = height < 0 ? 0 : height > 255 ? 255 : height;
		return claycolor[height];
	}
}
