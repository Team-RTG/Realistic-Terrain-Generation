package rtg.api.util.noise;

/**
 * @author <a href="https://github.com/srs-bsns">srs-bsns</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@FunctionalInterface
public interface CellularNoise {

    /**
     * Evaluates 2D Cellular noise for the given coordinates and returns the results in a {@link VoronoiResult} object.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the VoronoiResult
     * @since 1.0.0
     */
    VoronoiResult eval2D(double x, double y);
}
