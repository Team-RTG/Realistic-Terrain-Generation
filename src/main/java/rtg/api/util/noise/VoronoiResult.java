
package rtg.api.util.noise;

import java.awt.geom.Point2D;

/**
 *
 * @author Zeno410
 */
public class VoronoiResult {
    public double shortestDistance = 32000000.0;
    public double nextDistance = 32000000.0;
    public double closestX = 32000000.0;
    public double closestZ =32000000.0;

    public final double borderValue() {
        // returns 0 in the middle of a cell and 1 on the border;
        return (shortestDistance)/nextDistance;
    }
    
    public final double interiorValue() {
        // returns 1 in the middle of a cell and 0 on the border;
        return (nextDistance-shortestDistance)/nextDistance;
    }
   
    public final double interiorValue(double minimumDivisor) {
        // like interiorvalue but the value is capped to avert problems from too small cells
        if (minimumDivisor>nextDistance) minimumDivisor = nextDistance;
        return (nextDistance-shortestDistance)/minimumDivisor;
    }

    public final double distanceDifference() {
        return nextDistance-shortestDistance;
    }
    
    public final Point2D.Float toLength(Point2D.Float toMap, float radius) {
        // returns a point on the vector from closestX, closestZ to the passed point but at the specified 
        // length
        float xDist = toMap.x - (float)closestX;
        float zDist = toMap.y - (float)closestZ;
        float distance = (float)Math.sqrt(xDist*xDist + zDist*zDist);
        xDist *= radius/distance;
        zDist *= radius/distance;
        return new Point2D.Float((float)closestX+xDist,(float)closestZ+zDist);
    }
    
    void evaluate(Point2D.Double [] points, double x, double z) {
        for (int i = 0 ; i < points.length; i++) {
            double xPos = points[i].x;
            double zPos = points[i].y;
            double xDist = xPos - x;
            double zDist = zPos - z;
            double dist = xDist * xDist + zDist * zDist;

            if(dist < shortestDistance)
            {
                    nextDistance = shortestDistance;
                    shortestDistance = dist;

                    closestX = xPos;
                    closestZ = zPos;
            } else if (dist < nextDistance) {
                nextDistance = dist;
            }  
        }
    }
}
