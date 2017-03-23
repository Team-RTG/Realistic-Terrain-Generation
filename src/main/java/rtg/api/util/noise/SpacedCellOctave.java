
package rtg.api.util.noise;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;
import rtg.api.util.TimedHashMap;
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

/**
 * This is a Voronoi noise generator, originally from https://github.com/TJHJava/libnoiseforjava
 * It was modified to work in a similar way to the bukkit noise generators, and to support
 * octaves and 2d noise,
 * 
 * Further modified to use saved points to avert basin centers being too close. zeno410
 * 
 *
 * by mncat77 and jtjj222. <----------
 */
public class SpacedCellOctave implements CellOctave
{
	private static final double SQRT_2 = 1.4142135623730950488;
	private static final double SQRT_3 = 1.7320508075688772935;
        
        static final int totalPoints = 100;
        static final int pointsPerTorus = 25;
        static final double minimumDistance = 0.07;
        
                

	private boolean useDistance = false;

	private long seed;
        private long ySeed;
        private long zSeed;
	private short distanceMethod;
        private Point2D.Double[] allPoints;

	public SpacedCellOctave(long seed, short distanceMethod, boolean useDistance){
		this.seed = seed;
                ySeed = new Random(seed).nextLong();
                zSeed = new Random(ySeed).nextLong();
		this.distanceMethod = distanceMethod;
                this.useDistance = useDistance;
                setPoints();
	}

	private double distance(double xDist, double zDist)
	{
		return Math.sqrt(xDist * xDist + zDist * zDist);
	}

	private double getDistance2D(double xDist, double zDist)
	{
		switch(distanceMethod)
		{
		case 0:
			return Math.sqrt(xDist * xDist + zDist * zDist) / SQRT_2;
		case 1:
			return xDist + zDist;
		default:
			return Double.NaN;
		}
	}

	public boolean isUseDistance()
	{
		return useDistance;
	}

	public void setUseDistance(boolean useDistance)
	{
		this.useDistance = useDistance;
	}

	public short getDistanceMethod()
	{
		return distanceMethod;
	}

	public long getSeed()
	{
		return seed;
	}

	public void setSeed(long seed)
	{
		this.seed = seed;
        ySeed = new Random(seed).nextLong();
        zSeed = new Random(ySeed).nextLong();
	}
        
        /*
         * Working on a algorithm
         * The algorithm is to create a set of points on a unit torus, none of which are too close.
         * Each unit square gets a random subset of these points
         * On hold since I found the problem was that valueNoise was using too small a divisor and
         * letting points overlap
        */

        private void setPoints() {
            // this sets all the points in the master torus.
            Random xRandom = new Random(seed);
            Random yRandom = new Random(ySeed);
            allPoints = new Point2D.Double[totalPoints];
            
            int nextIndex = 0;
            int tries = 0;
            while(nextIndex < totalPoints) {
                // crash if stuck
                if (tries++> 100000 ) throw new RuntimeException(""+nextIndex);
                double xPos = xRandom.nextDouble();
                double yPos = yRandom.nextDouble();
                Point2D.Double newPoint = new Point2D.Double(xPos,yPos);
                if (minimalToroidalDistanceSquared(newPoint,allPoints,nextIndex)>=minimumDistance*minimumDistance ) {
                    allPoints[nextIndex++] = newPoint;
                }
            }
        }
        
        public static double minimalToroidalDistanceSquared(Point2D.Double point, Point2D.Double [] existing, int count) {
            double result = 1.0;
            for (int i = 0; i < count; i ++) {
                double distance = toroidalDistanceSquared(point, existing [i]);
                if (distance < result) result = distance;
            }
            return result;
        }
        public static double toroidalDistanceSquared (Point2D.Double one, Point2D.Double two) {
            double xDist = Math.abs(one.x - two.x);
            if (xDist > 0.5) xDist = 1.0 - xDist;
            double yDist = Math.abs(one.y - two.y);
            if (yDist > 0.5) yDist = 1.0 - yDist;
            return xDist*xDist + yDist*yDist;
        }
        
	public float noise(double x, double z, double frequency)
	{
            // this is essentially obsolete and needs to have its few remaining uses changed
		x *= frequency;
		z *= frequency;
                
                VoronoiResult result = eval(x,z);
                return (float) result.shortestDistance;
	}

	public VoronoiResult eval (double x, double z)
	{
            // this algorithm places the points about five times more frequently
            // so I'm adjusting the passed values rather than recalibrating all the routings
            x = x / 5.0; z = z / 5.0;
                    

		int xInt = (x > .0? (int)x: (int)x - 1);
		int zInt = (z > .0? (int)z: (int)z - 1);

		VoronoiResult result= new VoronoiResult();
                // note the algorithm requires a fresh VoronoiResult.
                                        
                Point square = new Point(xInt,zInt);
                // evaluate the points for that square;
                result.evaluate(this.areaPoints(square, allPoints), x, z);
                
                // double version of the floor for distance arithmetic;
                double zFloor = zInt;
                double xFloor = xInt;
                // now horizontally adjacent squares as appropriate
                double distance = z - zInt;
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt,zInt-1),allPoints), x, z);
                }
                
                distance = x - xInt;
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt - 1,zInt),allPoints), x, z);
                }
                
                distance = zInt - z + 1.0;
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt,zInt + 1),allPoints), x, z);
                }
                
                distance = xInt - x + 1.0;
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt + 1,zInt),allPoints), x, z);
                }
                
                // now diagonally adjacent squares
                distance = Math.min(z - zInt,x - xInt);
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt - 1,zInt - 1),allPoints), x, z);
                }
                
                distance = Math.min(zInt - z + 1.0,x - xInt);
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt - 1,zInt +1 ),allPoints), x, z);
                }
                
                distance = Math.min(zInt - z + 1.0,xInt - x + 1.0);
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt + 1,zInt + 1),allPoints), x, z);
                }
                
                distance = Math.min(z - zInt,xInt - x + 1.0);
                if (distance!=result.nextDistance) {
                    result.evaluate(areaPoints(new Point(xInt + 1,zInt - 1),allPoints), x, z);
                }

        return result;
	}

	/**
	 * To avoid having to store the feature points, we use a hash function
	 * of the coordinates and the seed instead. Those big scary numbers are
	 * arbitrary primes.
	 */
	public static double valueNoise2D (int x, int z, long seed)
	{
		long n = (1619 * x + 6971 * z + 1013 * seed) & 0x7fffffff;
		n = (n >> 13) ^ n;
                int hashed = (int)((n * (n * n * 60493 + 19990303) + 1376312589));
                return 0.90 - ((double)(hashed & 0x7fffffff) / 2684354560.0);
		//return 1.0 - ((double)((n * (n * n * 60493 + 19990303) + 1376312589) & 0x7fffffff) / 1073741824.0);
	}

        TimedHashMap<Point,Point2D.Double []> pointStorage = new TimedHashMap(5*60*1000);
        private Point2D.Double [] areaPoints(Point area,Point2D.Double [] masterList) {
            Point2D.Double [] result = pointStorage.get(area);
            if (result == null) {
                result = generatedAreaPoints(area,masterList);
                pointStorage.put(area, result);
            }
            double test = result[result.length -1].x * 2;
            return result;
        }
        
        
        private Point2D.Double [] generatedAreaPoints(Point area,Point2D.Double [] masterList) {
            Random random = new Random(area.hashCode());
            boolean [] used = new boolean [masterList.length];
            Point2D.Double [] result = new Point2D.Double [pointsPerTorus];
            int index = 0;
            for (int i = 0; i < pointsPerTorus; i ++) {
                int advance = random.nextInt(masterList.length-i);
                for (int j = 0 ; j < advance ; j++) {
                    while (used[index]) {
                        index ++;
                        if (index >= masterList.length) index = 0;
                    }
                }
                // add the point, offset to the area
                result[i] = new Point2D.Double(masterList[index].x+(double)area.x,masterList[index].y+(double)area.y);
                used[i] = true;
            }
            double above = result[5].x*2;
            return result;
        }
}