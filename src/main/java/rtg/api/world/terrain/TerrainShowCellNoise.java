
package rtg.api.world.terrain;

import rtg.api.util.noise.SpacedCellOctave;
import rtg.api.world.IRTGWorld;

/**
 *
 * @author Zeno410
 */
public class TerrainShowCellNoise extends TerrainBase {

    double riverSeparation = 40;
    double riverValleyLevel = 1f;

    @Override
    public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {
        double riverFactor = ((SpacedCellOctave)(rtgWorld.cell().octave(0)))
                .eval(((double)x)/riverSeparation, ((double)y)/riverSeparation).interiorValue();
        if (riverFactor>riverValleyLevel) return 0; // no river effect
        double start = (float)(riverFactor/riverValleyLevel) -1f;
        //double start = ((SpacedCellOctave)(rtgWorld.cell.octave(0))).border2(((double)x)/riverSeparation, ((double)y)/riverSeparation, riverValleyLevel, 1f);
        return (float)(20f*(1f-start)+63f);
    }

}
