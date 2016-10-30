package rtg.world.gen.terrain.mineworld;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainMWIceHills extends TerrainBase {

    private float width;
    private float strength;
    private float terrainHeight;

    public TerrainMWIceHills(float mountainWidth, float mountainStrength, float height) {

        width = mountainWidth;
        strength = mountainStrength;
        terrainHeight = height;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
    }
}
