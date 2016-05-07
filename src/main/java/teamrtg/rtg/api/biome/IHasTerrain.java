package teamrtg.rtg.api.biome;

import teamrtg.rtg.world.biome.terrain.TerrainBase;

/**
 * @author topisani
 */
public interface IHasTerrain {

    TerrainBase initTerrain();

    TerrainBase getTerrain();
}
