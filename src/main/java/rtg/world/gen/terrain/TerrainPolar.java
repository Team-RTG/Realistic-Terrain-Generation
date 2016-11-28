package rtg.world.gen.terrain;

import rtg.api.world.RTGWorld;
import rtg.config.ConfigRTG;

public class TerrainPolar extends TerrainBase {

    public TerrainPolar() {

    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        float st = (rtgWorld.simplex.noise2(x / 160f, y / 160f) + 0.38f) * (minDuneHeight + (float) ConfigRTG.duneHeight) * river;
        st = st < 0.2f ? 0.2f : st;

        float h = rtgWorld.simplex.noise2(x / 60f, y / 60f) * st * 2f;
        h = h > 0f ? -h : h;
        h += st;
        h *= h / 50f;
        h += st;

        return 70f + h;
    }
}
