package rtg.api.util.noise;

/**
 * @author <a href="https://github.com/srs-bsns">srs-bsns</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SimplexNoise {

    /**
     * Returns a 2D noise float value for the given coordinates.
     * This is an alias for {@link #noise2d}
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the noise value
     * @since 1.0.0
     */
    float noise2f(float x, float y);

    /**
     * Returns a 3D noise float value for the given coordinates.
     * This is an alias for {@link #noise3d}
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return the noise value
     * @since 1.0.0
     */
    float noise3f(float x, float y, float z);

    /**
     * Returns a 2D noise double value for the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the noise value
     * @since 1.0.0
     */
    double noise2d(double x, double y);

    /**
     * Returns a 3D noise double value for the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return the noise value
     * @since 1.0.0
     */
    double noise3d(double x, double y, double z);

    /**
     * Performs a 2D noise multi-evaluation on a {@link ISimplexData2D} object with the given coordinates.
     *
     * @param x    the x coordinate
     * @param y    the y coordinate
     * @param data the ISimplexData2D object
     * @since 1.0.0
     */
    void multiEval2D(double x, double y, ISimplexData2D data);
}
