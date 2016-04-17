package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLDesertMountains extends TerrainBase
{
	private float width;
	private float strength;
	private float terrainHeight;

    private int wavelength = 39;
    private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
    private double amplitude = 8;


	public TerrainHLDesertMountains(float mountainWidth, float mountainStrength,float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		terrainHeight = height;
                // experimentation
        terrainHeight = 45;
        width = 120;
	}

    // based on rock mountains but there's one noise abs'd instead of two, to stop lakes
    // and everything is a little lower and rounder
	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{

        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        float pX = (float)((float)x + jitter.deltax() * amplitude);
        float pY = (float)((float)y + jitter.deltay() * amplitude);

        float h = simplex.noise2(pX / 20f, pY / 20f);
        h = h*h*1.5f;
        float h2 = simplex.noise2(pX / 14f,pY / 14f);
        h2 = h2 * h2 * 1f;
        h = Math.max(h, h2);
		h += h2;
        float h3 = simplex.noise2(pX/50f,pY/50f);
        h3= h3*h3*4f;
        h+= h3;

        float m = unsignedPower(Math.abs(simplex.noise2(pX / width, pY / width)),1.3f) * strength * river;
        // invert y and x for complexity
        //float m2 = unsignedPower(simplex.noise2(y / (width*1.5f), x / (width*1.5f)),1.4f) * strength * river;

        //m = Math.max(m, m2);

        // intensify ruggedness at height
        h = m>10? h * m/10: h;

        m = above(m,-50f);

        float longNoise = simplex.noise2(pX /100f, pY / 100f)*10f;


        return terrainHeight + h + m + longNoise;
	}
}
