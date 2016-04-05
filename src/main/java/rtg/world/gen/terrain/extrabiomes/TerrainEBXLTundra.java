package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.PlateauEffect;

public class TerrainEBXLTundra extends FunctionalTerrainBase
{

    public TerrainEBXLTundra()
    {
        base = 66;
        HeightVariation tops = new HeightVariation();
        tops.height = 4;
        tops.wavelength = 20;
        tops.octave = 0;

        PlateauEffect flatHills = new PlateauEffect();
        flatHills.height = 6f;
        flatHills.bottomSimplexValue = 0.3f;
        flatHills.topSimplexValue = 0.5f;
        flatHills.octave = 1;
        flatHills.wavelength = 80;
        flatHills.subordinate = tops;

        height = flatHills.plus(new GroundEffect(2));

    }
}
