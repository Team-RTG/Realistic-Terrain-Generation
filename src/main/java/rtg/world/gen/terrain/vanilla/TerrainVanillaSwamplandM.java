package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaSwamplandM extends TerrainBase
{

    private float width;
    private float strength;
    private float terrainHeight;

    public TerrainVanillaSwamplandM(float mountainWidth, float mountainStrength,float height)
    {

        width = mountainWidth;
        strength = mountainStrength;
        terrainHeight = height;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
    }
}
