package teamrtg.rtg.api.util.noise;

import teamrtg.rtg.api.world.RTGWorld;

/**
 * @author topisani
 */
public interface IBoolAt {
    boolean getAt(float x, float y, float z, RTGWorld rtgWorld);
}
