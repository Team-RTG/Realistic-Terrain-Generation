package teamrtg.rtg.api.util.noise;

import teamrtg.rtg.api.world.RTGWorld;

/**
 * @author topisani
 */
public interface IFloatAt {

    IFloatAt ZERO = (x, y, z, rtgWorld) -> 0f;

    float getAt(float bx, float by, float bz, RTGWorld rtgWorld);
}
