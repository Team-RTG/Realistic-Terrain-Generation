
package rtg.api.util;

/**
 *
 * @author Zeno410
 */
public class PlateauStep {
    public float height = 32;
    public float start = 0.3f;
    public float finish = 0.5f;
    
    public float increase(float simplexVal) {
        if (simplexVal <= start) return 0;
        if (simplexVal >= finish) return height;
        return (simplexVal - start)/(finish - start) * height;
    }

}
