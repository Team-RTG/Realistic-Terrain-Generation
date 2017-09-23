package rtg.api.world.terrain;

import rtg.api.world.IRTGWorld;

public class TerrainOrganic extends TerrainBase {

    public TerrainOrganic() {

    }

    @Override
    public float generateNoise(IRTGWorld rtgWorld, int x, int z, float border, float river) {

        return riverized(rtgWorld.organicBiomeGenerator().getHeightAt(x, z), river);
    }
}
