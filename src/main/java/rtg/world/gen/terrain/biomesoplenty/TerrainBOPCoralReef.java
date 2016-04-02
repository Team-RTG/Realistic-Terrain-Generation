package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPCoralReef extends TerrainBase {
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
    public TerrainBOPCoralReef(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
        booRiver = riverGen;
        height = new float[] {5.0f, 0.5f, 12.5f, 0.5f};
        strength = heightStrength;
        heightLength = height.length;
        cWidth = canyonWidth;
        cHeigth = canyonHeight;
        cStrength = canyonStrength;
        base = baseHeight;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        return terrainOceanCanyon(x, y, simplex, river, height, border, strength, heightLength, booRiver);
    }
}
