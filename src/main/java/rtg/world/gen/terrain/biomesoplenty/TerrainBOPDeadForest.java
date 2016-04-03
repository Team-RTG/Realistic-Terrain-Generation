package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPDeadForest extends TerrainBase
{
    private float minHeight = 58f;
    private float maxHeight = 120f;
    private float hillStrength = 30f;
    private float deadForestGroundAmplitude = 10f;

    public TerrainBOPDeadForest()
    {

    }

    public TerrainBOPDeadForest(float minHeight, float maxHeight, float hillStrength)
    {
        this.minHeight = minHeight;
        this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
        this.hillStrength = hillStrength;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, deadForestGroundAmplitude, 0f);
    }
}
