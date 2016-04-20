package rtg.world.gen.terrain.vanilla;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaDesert extends TerrainBase
{
	public TerrainVanillaDesert()
	{
		super(64);
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        //return terrainPolar(x, y, simplex, river);
		float duneHeight = (minDuneHeight + (float) ConfigRTG.duneHeight);

		duneHeight *= (1f + simplex.octave(2).noise2((float)x / 330f, (float)y / 330f)) / 2f;

		float stPitch = 200f;	// The higher this is, the more smoothly dunes blend with the terrain
		float stFactor = duneHeight;
		float hPitch = 70;	// Dune scale
		float hDivisor = 40;

		return terrainPolar(x, y, simplex, river, stPitch, stFactor, hPitch, hDivisor, base) +
				groundNoise(x,y, 1f, simplex);
	}
}
