package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPGlacier extends TerrainBase {

    private float width;
    private float strength;

    public TerrainBOPGlacier(float mountainWidth, float mountainStrength, float height) {

        width = mountainWidth;
        strength = mountainStrength;
        base = height;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, base);
    }
}
