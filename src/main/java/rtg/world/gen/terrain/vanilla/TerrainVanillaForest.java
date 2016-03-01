package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaForest extends TerrainBase
{
    private float hillStrength = 10f;// this needs to be linked to the
    private float groundVariation = 2f;
    public TerrainVanillaForest()
    {
    
    }
    
    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        float h = groundNoise(x, y, groundVariation, simplex);

        float m = hills(x, y, hillStrength, simplex, river);
        
        float floNoise = 68f + h + m;
        
        return floNoise;
    }
}
