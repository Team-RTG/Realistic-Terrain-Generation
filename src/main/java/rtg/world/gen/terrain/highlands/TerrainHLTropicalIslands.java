package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLTropicalIslands extends TerrainBase
{
    private float hHeight;
    private float hWidth;
    private float vHeight;
    private float vWidth;
    private float lHeight;
    private float lWidth;
    private float bHeight;

    /*
     * hillHeight = 70f
     * hillWidth = 180f
     *
     * varHeight = 7f
     * varWidth = 100f
     *
     * lakeHeigth = 38f
     * lakeWidth = 260f
     *
     * baseHeight = 68f
     *
     * 70f, 180f, 7f, 100f, 38f, 260f, 68f
     */
    public TerrainHLTropicalIslands(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight)
    {
        hHeight = hillHeight;
        hWidth = hillWidth;

        vHeight = varHeight;
        vWidth = varWidth;

        lHeight = lakeHeight;
        lWidth = lakeWidth;

        bHeight = baseHeight;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainGrasslandHills(x, y, simplex, cell, river, vWidth, vHeight, hWidth, hHeight,bHeight);
    }
}
