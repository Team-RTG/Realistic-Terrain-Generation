package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.util.PlateauUtil;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.VoronoiPlateauEffect;


public class RealisticBiomeTRAVRockyPlateau extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVRockyPlateau(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainRTGMesaPlateau(67);
    }

    public static class TerrainRTGMesaPlateau extends TerrainBase {

        private static final float stepStart = 0.25f;
        private static final float stepFinish = 0.4f;
        private static final float stepHeight = 32;
        final VoronoiPlateauEffect plateau;
        final int groundNoise;
        private float jitterWavelength = 30;
        private float jitterAmplitude = 10;
        private float bumpinessMultiplier = 0.05f;
        private float bumpinessWavelength = 10f;

        public TerrainRTGMesaPlateau(float base) {
            plateau = new VoronoiPlateauEffect();
            plateau.pointWavelength = 200;
            this.base = base;
            groundNoise = 4;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int passedX, int passedY, float border, float river) {
            ISimplexData2D jitterData = SimplexData2D.newDisk();
            rtgWorld.simplexInstance(1).multiEval2D(passedX / jitterWavelength, passedY / jitterWavelength, jitterData);
            float x = (float) (passedX + jitterData.getDeltaX() * jitterAmplitude);
            float y = (float) (passedY + jitterData.getDeltaY() * jitterAmplitude);
            float bordercap = (bordercap = border * 3.5f - 2.5f) > 1 ? 1.0f : bordercap;
            float rivercap = (rivercap = 3f * river) > 1 ? 1.0f : rivercap;
            float bumpiness = rtgWorld.simplexInstance(2).noise2f(x / bumpinessWavelength, y / bumpinessWavelength) * bumpinessMultiplier;
            float simplex = plateau.added(rtgWorld, x, y) * bordercap * rivercap + bumpiness;
            float added = PlateauUtil.stepIncrease(simplex, stepStart, stepFinish, stepHeight) / border;
            return riverized(base + TerrainBase.groundNoise(x, y, groundNoise, rtgWorld), river) + added;
        }

    }
}
