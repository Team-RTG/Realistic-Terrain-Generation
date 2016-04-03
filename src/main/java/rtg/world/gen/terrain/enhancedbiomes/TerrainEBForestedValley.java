package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBForestedValley extends FunctionalTerrainBase
{

    public TerrainEBForestedValley()
    {
        height = new GroundEffect(groundNoiseAmplitudeHills);
    }


}
