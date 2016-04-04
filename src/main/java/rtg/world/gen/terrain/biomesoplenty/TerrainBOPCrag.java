package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.util.noise.SimplexOctave;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPCrag extends TerrainBase {
    private boolean booRiver;
    private float[] height;
    private int heightLength;
    private float strength;
    private float cWidth;
    private float cHeigth;
    private float cStrength;
    private float base;

    private float pointHeightVariation = 20f;
    private float pointHeightWavelength = 400f;// deep variation
    private float pointHeight = 50;
    private float pointWavelength = 20;

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
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        // need a little jitter to the points
        SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
        simplex.riverJitter().evaluateNoise(x / 20.0, y / 20.0, jitter);
        double pX = x + jitter.deltax() * 1f;
        double pY = y + jitter.deltay() * 1f;

        // restrict the points to in the biome.
        double multiplier = (border - 0.5) * 10.0;
        if (multiplier < 0) multiplier = 0;
        if (multiplier > 1) multiplier = 1;
        double[] points = cell.octave(1).eval((float) pX / pointWavelength, (float) pY / pointWavelength);
        float p = (float) ((points[1] - points[0]) * (pointHeight +
                pointHeightVariation * simplex.noise((float) x / pointHeightWavelength, (float) y / pointHeightWavelength)));

        return riverized(base, river) + p;
        //return terrainCanyon(x, y, simplex, river, height, border, strength, heightLength, booRiver);
    }
}
