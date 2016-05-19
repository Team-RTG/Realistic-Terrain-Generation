package teamrtg.rtg.api.world.biome;

import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.mods.RTGSupport;

/**
 * Collective wrapper for Biomes and TerrainFeatures
 * @author topisani
 */
public interface IWorldFeature extends IHasDecos, IHasSurface {

    void initConfig();

    BiomeConfig getConfig();

    RTGSupport getMod();

    String getName();
}
