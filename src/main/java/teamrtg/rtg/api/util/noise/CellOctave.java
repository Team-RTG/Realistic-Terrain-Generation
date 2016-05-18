package teamrtg.rtg.api.util.noise;

/**
 *
 * @author Zeno410
 */
public interface CellOctave {
    public float noise(double x, double z, double depth);

    public double[] eval(double x, double y);
}
