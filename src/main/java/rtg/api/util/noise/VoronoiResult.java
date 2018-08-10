package rtg.api.util.noise;

import java.awt.geom.Point2D;


/**
 * @author Zeno410
 */
public class VoronoiResult {

    private double shortestDistance = 32000000.0;
    private double nextDistance = 32000000.0;
    private double closestX = 32000000.0;
    private double closestZ = 32000000.0;

    public final double getShortestDistance() {
        return this.shortestDistance;
    }

    public final double getNextDistance() {
        return this.nextDistance;
    }

    // returns 0 in the middle of a cell and 1 on the border;
    public final double borderValue() {
        return shortestDistance / nextDistance;
    }

    // returns 1 in the middle of a cell and 0 on the border;
    public final double interiorValue() {
        return (nextDistance - shortestDistance) / nextDistance;
    }

    // returns a point on the vector from closestX, closestZ to the passed point but at the specified length
    public final Point2D.Float toLength(Point2D.Float toMap, float radius) {
        double distance = toMap.distance(this.closestX, this.closestZ);
        double xDist = toMap.getX() - this.closestX;
        double zDist = toMap.getY() - this.closestZ;
        xDist *= radius / distance;
        zDist *= radius / distance;
        return new Point2D.Float((float) (this.closestX + xDist), (float) (this.closestZ + zDist));
    }

    void evaluate(Point2D.Double[] points, double x, double z) {
        for (Point2D.Double point : points) {
            double distance = point.distanceSq(x, z);
            if (distance < this.shortestDistance) {
                this.nextDistance = this.shortestDistance;
                this.shortestDistance = distance;
                this.closestX = point.getX();
                this.closestZ = point.getY();
            }
            else if (distance < this.nextDistance) {
                this.nextDistance = distance;
            }
        }
    }
}
