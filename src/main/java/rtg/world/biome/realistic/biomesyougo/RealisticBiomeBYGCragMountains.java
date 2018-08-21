package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.world.biome.Biome;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeBYGCragMountains extends RealisticBiomeBYGBase {

    public RealisticBiomeBYGCragMountains(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPCrag(false, new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f, 23.0f, 0.5f}, 35f, 80f, 60f, 40f, 69f);
    }

    public class TerrainBOPCrag extends TerrainBase {

        private boolean booRiver;
        private float[] height;
        private int heightLength;
        private float strength;
        private float cWidth;
        private float cHeigth;
        private float cStrength;
        private float base;

        /*
         * Example parameters:
         *
         * allowed to generate rivers?
         * riverGen = true
         *
         * canyon jump heights
         * heightArray = new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}
         *
         * strength of canyon jump heights
         * heightStrength = 35f
         *
         * canyon width (cliff to cliff)
         * canyonWidth = 160f
         *
         * canyon heigth (total heigth)
         * canyonHeight = 60f
         *
         * canyon strength
         * canyonStrength = 40f
         *
         */
        public TerrainBOPCrag(boolean riverGen, float[] heightArryay, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
            booRiver = riverGen;
            height = heightArryay;
            strength = heightStrength;
            heightLength = height.length;
            cWidth = canyonWidth;
            cHeigth = canyonHeight;
            cStrength = canyonStrength;
            base = baseHeight;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            //float b = simplex.noise2(x / cWidth, y / cWidth) * cHeigth * river;
            //b *= b / cStrength;
            river *= 1.3f;
            river = river > 1f ? 1f : river;
            float r = simplex.noise2f(x / 100f, y / 100f) * 50f;
            r = r < -7.4f ? -7.4f : r > 7.4f ? 7.4f : r;
            float b = (17f + r) * river;

            float hn = simplex.noise2f(x / 12f, y / 12f) * 0.5f;
            float sb = 0f;
            if (b > 0f) {
                sb = b;
                sb = sb < 0f ? 0f : sb > 7f ? 7f : sb;
                sb = hn * sb;
            }
            b += sb;

            float cTotal = 0f;
            float cTemp = 0f;

            for (int i = 0; i < heightLength; i += 2) {
                cTemp = 0;
                if (b > height[i] && border > 0.6f + (height[i] * 0.015f) + hn * 0.2f) {
                    cTemp = b > height[i] + height[i + 1] ? height[i + 1] : b - height[i];
                    cTemp *= strength;
                }
                cTotal += cTemp;
            }


            float bn = 0f;
            if (booRiver) {
                if (b < 5f) {
                    bn = 5f - b;
                    for (int i = 0; i < 3; i++) {
                        bn *= bn / 4.5f;
                    }
                }
            }
            else if (b < 5f) {
                bn = (simplex.noise2f(x / 7f, y / 7f) * 1.3f + simplex.noise2f(x / 15f, y / 15f) * 2f) * (5f - b) * 0.2f;
            }

            b += cTotal - bn;

            return base + b;
        }
    }
}
