/*
 * Copyright (C) 2003, 2004 Jason Bevins (original libnoise code)
 * Copyright � 2010 Thomas J. Hodge (java port of libnoise)
 *
 * This file was part of libnoiseforjava.
 *
 * libnoiseforjava is a Java port of the C++ library libnoise, which may be found at
 * http://libnoise.sourceforge.net/.  libnoise was developed by Jason Bevins, who may be
 * contacted at jlbezigvins@gmzigail.com (for great email, take off every 'zig').
 * Porting to Java was done by Thomas Hodge, who may be contacted at
 * libnoisezagforjava@gzagmail.com (remove every 'zag').
 *
 * libnoiseforjava is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * libnoiseforjava is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * libnoiseforjava.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package rtg.api.util.noise;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Random;

import rtg.api.util.LimitedArrayCacheMap;
import rtg.api.util.MathUtils;


/**
 * This is a Voronoi noise generator, originally from https://github.com/TJHJava/libnoiseforjava
 * It was modified to work in a similar way to the bukkit noise generators, and to support
 * octaves and 2d noise,
 * <p>
 * Further modified to use saved points to avert basin centers being too close. zeno410
 * <p>
 * <p>
 * by mncat77 and jtjj222. <----------
 */
public class SpacedCellularNoise implements CellularNoise {

    private static final int totalPoints = 100;
    private static final int pointsPerTorus = 25;
    private static final double minDistanceSq = 0.005d;

    private final Map<Point, Point2D.Double[]> cache = new LimitedArrayCacheMap<>(256);
    private final Point2D.Double[] allPoints;
    private final long xSeed;
    private final long ySeed;

    public SpacedCellularNoise(long xSeed) {
        this.xSeed = xSeed;
        this.ySeed = new Random(xSeed).nextLong();
        this.allPoints = new Point2D.Double[totalPoints];
        this.setPoints();
    }

    private static double minimalToroidalDistanceSquared(Point2D.Double point, Point2D.Double[] existing, int count) {
        double result = 1.0;
        for (int i = 0; i < count; i++) {
            double distance = toroidalDistanceSquared(point, existing[i]);
            if (distance < result) {
                result = distance;
            }
        }
        return result;
    }

    private static double toroidalDistanceSquared(Point2D.Double first, Point2D.Double second) {
        double xDist = Math.abs(first.x - second.x);
        if (xDist > 0.5) {
            xDist = 1.0 - xDist;
        }
        double yDist = Math.abs(first.y - second.y);
        if (yDist > 0.5) {
            yDist = 1.0 - yDist;
        }
        return MathUtils.pow2(xDist) + MathUtils.pow2(yDist);
    }

    @Override
    public VoronoiResult eval2D(double x, double y) {

        // this algorithm places the points about five times more frequently
        // so I'm adjusting the passed values rather than recalibrating all the routings
        x = x / 5.0;
        y = y / 5.0;

        int xInt = (x > 0.0 ? (int) x : (int) x - 1);
        int yInt = (y > 0.0 ? (int) y : (int) y - 1);

        // note the algorithm requires a fresh VoronoiResult.
        VoronoiResult result = new VoronoiResult();

        Point square = new Point(xInt, yInt);
        // evaluate the points for that square;
        result.evaluate(this.areaPoints(square), x, y);

        // now horizontally adjacent squares as appropriate
        double distance = y - yInt;
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt, yInt - 1)), x, y);
        }

        distance = x - xInt;
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt - 1, yInt)), x, y);
        }

        distance = yInt - y + 1.0;
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt, yInt + 1)), x, y);
        }

        distance = xInt - x + 1.0;
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt + 1, yInt)), x, y);
        }

        // now diagonally adjacent squares
        distance = Math.min(y - yInt, x - xInt);
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt - 1, yInt - 1)), x, y);
        }

        distance = Math.min(yInt - y + 1.0, x - xInt);
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt - 1, yInt + 1)), x, y);
        }

        distance = Math.min(yInt - y + 1.0, xInt - x + 1.0);
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt + 1, yInt + 1)), x, y);
        }

        distance = Math.min(y - yInt, xInt - x + 1.0);
        if (distance != result.getNextDistance()) {
            result.evaluate(this.areaPoints(new Point(xInt + 1, yInt - 1)), x, y);
        }

        return result;
    }

    /**
     * Working on a algorithm
     * The algorithm is to create a set of points on a unit torus, none of which are too close.
     * Each unit square gets a random subset of these points
     * On hold since I found the problem was that valueNoise was using too small a divisor and
     * letting points overlap
     */
    private Point2D.Double[] areaPoints(Point area) {
        Point2D.Double[] ret;
        return ((ret = this.cache.get(area)) != null) ? ret : this.generatedAreaPoints(area);
    }

    private Point2D.Double[] generatedAreaPoints(Point area) {
        Random random = new Random(area.hashCode());
        boolean[] used = new boolean[totalPoints];
        Point2D.Double[] result = new Point2D.Double[pointsPerTorus];
        int index = 0;
        for (int i = 0; i < pointsPerTorus; i++) {
            int advance = random.nextInt(totalPoints);
            for (int j = 0; j < advance; j++) {
                while (used[index]) {
                    index++;
                    if (index >= totalPoints) {
                        index = 0;
                    }
                }
            }
            // add the point, offset to the area
            result[i] = new Point2D.Double(this.allPoints[index].getX() + area.getX(), this.allPoints[index].getY() + area.getY());
            used[i] = true;
        }
        this.cache.put(area, result);
        return result;
    }

    private void setPoints() {
        // this sets all the points in the master torus.
        Random xRandom = new Random(this.xSeed);
        Random yRandom = new Random(this.ySeed);

        // only bump the iterator if we are storing a value
        for (int i = 0; i < totalPoints; ) {
            Point2D.Double newPoint = new Point2D.Double(xRandom.nextDouble(), yRandom.nextDouble());
            if (minimalToroidalDistanceSquared(newPoint, allPoints, i) >= minDistanceSq) {
                allPoints[i++] = newPoint;
            }
        }
    }
}