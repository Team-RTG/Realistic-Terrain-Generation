package teamrtg.rtg.api.biome;

import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.mods.RTGSupport;

/**
 * Collective wrapper for Biomes and TerrainFeatures
 * @author topisani
 */
interface IGenArea extends IDecoratable, IHasSurface, IHasTerrain {

    void initConfig();

    BiomeConfig getConfig();

    RTGSupport getMod();

    String getName();
}
