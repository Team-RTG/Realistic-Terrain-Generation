package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBBadlands extends TerrainBase
{
    private float baseHeight = 70f;
    private HeightEffect height;

    public TerrainEBBadlands()
    {
        // big hills
        HillockEffect hillock = new HillockEffect();
        hillock.height = 20;
        hillock.minimumSimplex = 0.1f;
        hillock.octave = 2;
        hillock.wavelength = 30;
        
        JitterEffect jitteredHillock = new JitterEffect();
        jitteredHillock.amplitude =5;
        jitteredHillock.wavelength = 7;
        jitteredHillock.jittered = hillock;
        
        // little rockpiles
        HillockEffect rockPile = new HillockEffect();
        rockPile.height = 5;
        rockPile.minimumSimplex = 0.1f;
        rockPile.octave = 3;
        rockPile.wavelength = 10;
        
        JitterEffect jitteredRockPile = new JitterEffect();
        jitteredRockPile.amplitude =3;
        jitteredRockPile.wavelength = 5;
        jitteredRockPile.jittered = rockPile;
        
        // and a little variation
        height = jitteredHillock.plus(jitteredRockPile).plus(new GroundEffect(6f));
        //height = new GroundEffect(6f);

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(height.added(simplex, cell, x, y)+baseHeight,river);
    }
}
