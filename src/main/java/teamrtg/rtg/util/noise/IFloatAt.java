package teamrtg.rtg.util.noise;

/**
 * @author topisani
 */
public interface IFloatAt {

    IFloatAt ZERO = (x, y, z) -> 0f;

    float getAt(float bx, float by, float bz);
}
