package rtg.api.world.terrain.heighteffect;

import rtg.api.util.noise.VoronoiResult;
import rtg.api.world.RTGWorld;


/**
 * This returns a value generally between just below 0 and 1 depending on the distance from a voronoi cell border
 * 0 is on the border
 *
 * @author Zeno410
 */
public class VoronoiBorderEffect extends HeightEffect {

    public float pointWavelength = 0;
    public float floor = Float.MAX_VALUE;
    public float minimumDivisor = 0;//low divisors can produce excessive rates of change

    @Override
    public float added(RTGWorld rtgWorld, float x, float y) {
        VoronoiResult points = rtgWorld.cellularInstance(1).eval2D(x / pointWavelength, y / pointWavelength);
        float raise = (float) (points.interiorValue());
        raise = 1.0f - raise;
        //raise = TerrainBase.blendedHillHeight(raise, floor);
        return raise;
    }

}