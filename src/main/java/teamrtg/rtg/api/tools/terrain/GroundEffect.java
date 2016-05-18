package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.world.biome.TerrainBase;

/**
 * @author Zeno410
 */
public class GroundEffect extends HeightEffect {
    // the standard ground effect
    private final float amplitude;

    public GroundEffect(float amplitude) {
        this.amplitude = amplitude;
    }

    public final float added(OpenSimplexNoise simplex, CellNoise cell, int x, int y) {
        return TerrainBase.groundNoise(x, y, amplitude, simplex);
    }

}
