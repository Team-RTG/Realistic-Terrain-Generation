package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;

public class TerrainOceanCanyon extends TerrainBase {
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
    public TerrainOceanCanyon(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
        booRiver = riverGen;
        height = new float[] {5.0f, 0.5f, 12.5f, 0.5f, 18.0f, 0.5f};
        strength = heightStrength;
        heightLength = height.length;
        cWidth = canyonWidth;
        cHeigth = canyonHeight;
        cStrength = canyonStrength;
        base = baseHeight;
    }

    @Override
    public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
        //float b = simplex.noise2(x / cWidth, y / cWidth) * cHeigth * river;
        //b *= b / cStrength;
        river *= 1.3f;
        river = river > 1f ? 1f : river;
        float r = rtgWorld.simplex.noise2(x / 100f, y / 100f) * 50f;
        r = r < -7.4f ? -7.4f : r > 7.4f ? 7.4f : r;
        float b = (17f + r) * river;

        float hn = rtgWorld.simplex.noise2(x / 12f, y / 12f) * 0.5f;
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
            if (b > height[i] && biomeWeight > 0.6f + (height[i] * 0.015f) + hn * 0.2f) {
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
        } else if (b < 5f) {
            bn = (rtgWorld.simplex.noise2(x / 7f, y / 7f) * 1.3f + rtgWorld.simplex.noise2(x / 15f, y / 15f) * 2f) * (5f - b) * 0.2f;
        }

        b += cTotal - bn;

        float floNoise = base + b;
        floNoise = floNoise < minimumOceanFloor ? minimumOceanFloor : floNoise;

        return floNoise;
    }
}
