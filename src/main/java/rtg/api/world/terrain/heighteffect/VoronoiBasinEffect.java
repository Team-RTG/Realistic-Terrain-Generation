
package rtg.api.world.terrain.heighteffect;

import java.awt.geom.Point2D;

import rtg.api.util.Bayesian;
import rtg.api.util.noise.VoronoiResult;
import rtg.api.world.IRTGWorld;


/**
 * This returns a value generally between just below 0 and 1 depending on the distance from a voronoi cell border
 * 0 is in the center
 * @author Zeno410
 */
public class VoronoiBasinEffect extends HeightEffect {

    public float pointWavelength = 200;
    public float minimumDivisor = 0;//low divisors can produce excessive rates of change
    public float adjustmentRadius = 1f;
    
    @Override
    public float added(IRTGWorld rtgWorld, float x, float y) {
        Point2D.Float evaluateAt = new Point2D.Float((float) x / pointWavelength,(float) y / pointWavelength);
         VoronoiResult points = rtgWorld.voronoi().octave(1).eval(evaluateAt.x, evaluateAt.y);
         float raise = (float) (points.borderValue());
         // now we're going to get an adjustment value which will be the same
         // for all points on a given vector from the voronoi basin center
         Point2D.Float adjustAt = points.toLength(evaluateAt, adjustmentRadius);
         float noZeros = 0.25f;
         float adjustment = (float)rtgWorld.voronoi().octave(2).eval(adjustAt.x,adjustAt.y).interiorValue() + noZeros;
         float reAdjustment = (float)rtgWorld.voronoi().octave(3).eval(adjustAt.x,adjustAt.y).interiorValue() + noZeros;;
         // 0 to 1 which is currently undesirable so increase to average closer to 1
         adjustment = Bayesian.adjustment(adjustment, reAdjustment);
         // invert adjustment for Bryce
         raise = Bayesian.adjustment(raise, 1f/(adjustment*adjustment));
         return raise;
    }

}