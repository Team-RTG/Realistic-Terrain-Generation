package teamrtg.rtg.util.noise;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

/**
 * @author topisani
 */
public interface IBoolAt {
    boolean getAt(float x, float y, float z, ChunkProviderRTG provider);
}
