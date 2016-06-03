package teamrtg.rtg.api.world.biome;

import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.module.RTGModule;

/**
 * Collective wrapper for Biomes and TerrainFeatures
 * @author topisani
 */
public interface IWorldFeature extends IHasDecos, IHasSurface {

    void initConfig();

    BiomeConfig getConfig();

    RTGModule getMod();

    String getName();
}
