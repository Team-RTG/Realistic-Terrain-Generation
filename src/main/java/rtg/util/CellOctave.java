package rtg.util;

/**
 * @author Zeno410
 */
public interface CellOctave {
    float noise(double x, double z, double depth);

    double[] eval(double x, double y);
}
