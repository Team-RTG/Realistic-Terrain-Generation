package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVMountainousDesert extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVMountainousDesert(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainHLGreyMountains(230f, 100f, 68f);
    }

    public class TerrainHLGreyMountains extends TerrainBase {

        private float width;
        private float strength;
        private float terrainHeight;


        private int wavelength = 39;
        private ISimplexData2D jitter = SimplexData2D.newDisk();
        private double amplitude = 12;

        public TerrainHLGreyMountains(float mountainWidth, float mountainStrength, float height) {
            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;

            // experimentation
            terrainHeight = 30;
            width = 120;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            SimplexNoise simplex = rtgWorld.simplexInstance(0);

            simplex.multiEval2D((float) x / wavelength, (float) y / wavelength, jitter);
            float pX = (float) ((float) x + jitter.getDeltaX() * amplitude);
            float pY = (float) ((float) y + jitter.getDeltaY() * amplitude);

            float h = simplex.noise2f(pX / 19f, pY / 19f);
            h = h * h * 2f;
            float h2 = simplex.noise2f(pX / 13f, pY / 13f);
            h2 = h2 * h2 * 1.3f;
            h = Math.max(h, h2);
            h += h2;
            float h3 = simplex.noise2f(pX / 53f, pY / 53f);
            h3 = h3 * h3 * 5f;
            h += h3;

            float m = unsignedPower(simplex.noise2f(pX / width, pY / width), 1.4f) * strength * river;
            // invert y and x for complexity
            float m2 = unsignedPower(simplex.noise2f(pY / (width * 1.5f), pX / (width * 1.5f)), 1.4f) * strength * river;

            m = Math.max(m, m2);

            // intensify ruggedness at height
            h = m > 10 ? h * m / 10 : h;

            m = above(m, -50f);


            return terrainHeight + h + m;


        }
    }
}
