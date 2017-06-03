package rtg.api.world.terrain;

import rtg.api.world.RTGWorld;

public class TerrainOrganic extends TerrainBase {

    public TerrainOrganic() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int z, float border, float river) {

        return riverized(rtgWorld.organicBiomeGenerator.getHeightAt(x, z), river);
    }
}
