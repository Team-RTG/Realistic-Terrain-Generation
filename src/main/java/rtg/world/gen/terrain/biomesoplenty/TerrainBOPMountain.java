package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPMountain extends TerrainBase
{
	public TerrainBOPMountain()
	{
	}
	
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
		float h = simplex.noise2(x / 300f, y / 300f) * 135f * river;
		h *= h / 32f;
		h = h > 150f ? 150f : h;

		/*float bn = 0f;
		if(h < 1f)
		{
			bn = 1f - h;
			for(int i = 0; i < 3; i++)
			{
				bn *= bn * 1.25f;
			}
			
			bn = bn > 3f ? 3f : bn;
		}*/

		if(h < 10f)
		{
			h += simplex.noise2(x / 14f, y / 14f) * (10f - h) * 0.2f;
		}
		
		if(h > 10f)
		{
			float d = (h - 10f) / 2f > 8f ? 8f : (h - 10f) / 2f;
			h += simplex.noise2(x / 35f, y / 35f) * d;
			h += simplex.noise2(x / 60f, y / 60f) * d * 0.5f;

			if(h > 35f)
			{
				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
			}
		}

		if(h > 2f)
		{
			float d = (h - 2f) / 2f > 4f ? 4f : (h - 2f) / 2f;
    		h += simplex.noise2(x / 28f, y / 28f) * d;
    		h += simplex.noise2(x / 18f, y / 18f) * (d / 2f);
    		h += simplex.noise2(x / 8f, y / 8f) * (d / 2f);
		}
		
		return h + 67f;// - bn;
	}
}
