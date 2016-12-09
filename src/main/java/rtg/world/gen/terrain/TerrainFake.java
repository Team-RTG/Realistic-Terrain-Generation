package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;

public class TerrainFake extends TerrainBase {

    public TerrainFake() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int z, float border, float river) {

        return riverized(rtgWorld.biomeFaker.getHeightAt(x, z), river);
    }
}
