package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBSandstoneRangesM extends TerrainBase
{

    private TerrainBase parent = new TerrainEBSandstoneRanges(80f,30f);
    public TerrainEBSandstoneRangesM(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength,
        float baseHeight)
    {


    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return parent.generateNoise(simplex, cell, x, y, border, river);
    }
}
