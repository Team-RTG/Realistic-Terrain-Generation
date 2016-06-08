package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;

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
