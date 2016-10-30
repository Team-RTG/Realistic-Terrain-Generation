package rtg.world.gen.terrain.mineworld;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainMWDeadForest extends TerrainBase {

    private float minHeight = 58f;
    private float maxHeight = 120f;
    private float hillStrength = 30f;
    private float deadForestGroundAmplitude = 10f;

    public TerrainMWDeadForest() {

    }

    public TerrainMWDeadForest(float minHeight, float maxHeight, float hillStrength) {

        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
        this.hillStrength = hillStrength;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

        return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, deadForestGroundAmplitude, 0f);
    }
}
