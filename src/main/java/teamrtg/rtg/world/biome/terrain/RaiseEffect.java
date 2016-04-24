package teamrtg.rtg.world.biome.terrain;

import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;

/**
 * // just adds a constant increase
 * @author Zeno410
 */
public class RaiseEffect extends HeightEffect {
    // just adds a number
    public final float height;

    public RaiseEffect(float height) {
        this.height = height;
    }

    public final float added(OpenSimplexNoise simplex, CellNoise cell, int x, int y) {
        return height;
    }
}
