package teamrtg.rtg.core.world;

import teamrtg.rtg.api.world.biome.RTGBiome;

/**
 *
 * @author Zeno410
 */
public class ChunkLandscape {
    public float [] noise = new float [256];
    public RTGBiome [] biomes = new RTGBiome[256];
    public float [] river = new float [256];
}