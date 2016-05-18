package teamrtg.rtg.modules.rtg.terrainfeature;

import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.TerrainFeature;
import teamrtg.rtg.api.world.biome.deco.DecoBase;

import java.util.ArrayList;

/**
 * @author topisani
 */
public class LakeFeature extends TerrainFeature {

    public LakeFeature() {
        super(Mods.RTG, "lake");
    }

    @Override
    public TerrainBase initTerrain() {
        return null;
    }

    @Override
    public ArrayList<DecoBase> getDecos() {
        return null;
    }
}
