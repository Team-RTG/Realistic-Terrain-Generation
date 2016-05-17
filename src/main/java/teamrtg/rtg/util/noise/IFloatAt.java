package teamrtg.rtg.util.noise;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

/**
 * @author topisani
 */
public interface IFloatAt {

    IFloatAt ZERO = (x, y, z, provider) -> 0f;

    float getAt(float bx, float by, float bz, ChunkProviderRTG provider);
}
