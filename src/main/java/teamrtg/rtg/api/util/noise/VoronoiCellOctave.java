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
package teamrtg.rtg.api.util.noise;

import java.util.Random;

/**
 * This is a Voronoi noise generator, originally from https://github.com/TJHJava/libnoiseforjava
 * It was modified to work in a similar way to the bukkit noise generators, and to support
 * octaves and 2d noise,
 * <p>
 * by mncat77 and jtjj222. <----------
 */
public class VoronoiCellOctave implements CellOctave {
    private static final double SQRT_2 = 1.4142135623730950488;
    private static final double SQRT_3 = 1.7320508075688772935;

    private boolean useDistance = false;

    private long seed;
    private short distanceMethod;


    public VoronoiCellOctave(long seed, short distanceMethod, boolean useDistance) {
        this.seed = seed;
        this.distanceMethod = distanceMethod;
        this.useDistance = useDistance;
    }

    public boolean isUseDistance() {
        return useDistance;
    }

    public void setUseDistance(boolean useDistance) {
        this.useDistance = useDistance;
    }

    public short getDistanceMethod() {
        return distanceMethod;
    }

    public void setDistanceMethod(short distanceMethod) {
        this.distanceMethod = distanceMethod;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public float noise(double x, double z, double frequency) {
        x *= frequency;
        z *= frequency;

        int xInt = (x > .0 ? (int) x : (int) x - 1);
        int zInt = (z > .0 ? (int) z : (int) z - 1);

        double minDist = 32000000.0;

        double xCandidate = 0;
        double zCandidate = 0;

        for (int zCur = zInt - 2; zCur <= zInt + 2; zCur++) {
            for (int xCur = xInt - 2; xCur <= xInt + 2; xCur++) {

                double xPos = xCur + valueNoise2D(xCur, zCur, seed);
                double zPos = zCur + valueNoise2D(xCur, zCur, new Random(seed).nextLong());
                double xDist = xPos - x;
                double zDist = zPos - z;
                double dist = xDist * xDist + zDist * zDist;

                if (dist < minDist) {
                    minDist = dist;
                    xCandidate = xPos;
                    zCandidate = zPos;
                }
            }
        }

        if (useDistance) {
            double xDist = xCandidate - x;
            double zDist = zCandidate - z;
            return (float) getDistance2D(xDist, zDist);
        } else return ((float) valueNoise2D(
            (int) (Math.floor(xCandidate)),
            (int) (Math.floor(zCandidate)), seed));
    }

    public double[] eval(double x, double z) {

        int xInt = (x > .0 ? (int) x : (int) x - 1);
        int zInt = (z > .0 ? (int) z : (int) z - 1);

        double dCandidate = 32000000.0;
        double xCandidate = 0;
        double zCandidate = 0;

        double dNeighbour = 32000000.0;
        double xNeighbour = 0;
        double zNeighbour = 0;

        for (int zCur = zInt - 2; zCur <= zInt + 2; zCur++) {
            for (int xCur = xInt - 2; xCur <= xInt + 2; xCur++) {

                double xPos = xCur + valueNoise2D(xCur, zCur, seed);
                double zPos = zCur + valueNoise2D(xCur, zCur, new Random(seed).nextLong());
                double xDist = xPos - x;
                double zDist = zPos - z;
                double dist = xDist * xDist + zDist * zDist;
                //double dist = getDistance2D(xPos - x, zPos - z);

                if (dist < dCandidate) {
                    dNeighbour = dCandidate;
                    dCandidate = dist;

					/*dNeighbour = dCandidate;
                    xNeighbour = xCandidate;
					zNeighbour = zCandidate;

					dCandidate = dist;
					xCandidate = xPos;
					zCandidate = zPos;*/
                } else if (dist < dNeighbour) {
                    dNeighbour = dist;
                }
            }
        }

        //double c = getDistance2D(xNeighbour - x, zNeighbour - z) - getDistance2D(xCandidate - x, zCandidate - z);
        double[] result = new double[2];
        result[0] = dCandidate;
        result[1] = dNeighbour;
        return result;
    }

    /**
     * To avoid having to store the feature points, we use a hash function
     * of the coordinates and the seed instead. Those big scary numbers are
     * arbitrary primes.
     */
    public static double valueNoise2D(int x, int z, long seed) {
        long n = (1619 * x + 6971 * z + 1013 * seed) & 0x7fffffff;
        n = (n >> 13) ^ n;
        return 1.0 - ((double) ((n * (n * n * 60493 + 19990303) + 1376312589) & 0x7fffffff) / 1073741824.0);
    }

    private double getDistance2D(double xDist, double zDist) {
        switch (distanceMethod) {
            case 0:
                return Math.sqrt(xDist * xDist + zDist * zDist) / SQRT_2;
            case 1:
                return xDist + zDist;
            default:
                return Double.NaN;
        }
    }

    public float border2(double x, double z, double width, float depth) {
        x *= 1D;
        z *= 1D;

        int xInt = (x > .0 ? (int) x : (int) x - 1);
        int zInt = (z > .0 ? (int) z : (int) z - 1);

        double dCandidate = 32000000.0;
        double xCandidate = 0;
        double zCandidate = 0;

        double dNeighbour = 32000000.0;
        double xNeighbour = 0;
        double zNeighbour = 0;

        double xPos, zPos, xDist, zDist, dist;
        for (int zCur = zInt - 2; zCur <= zInt + 2; zCur++) {
            for (int xCur = xInt - 2; xCur <= xInt + 2; xCur++) {
                xPos = xCur + valueNoise2D(xCur, zCur, seed);
                zPos = zCur + valueNoise2D(xCur, zCur, new Random(seed).nextLong());
                xDist = xPos - x;
                zDist = zPos - z;
                dist = distance(xPos - x, zPos - z);

                if (dist < dCandidate) {
                    dNeighbour = dCandidate;
                    xNeighbour = xCandidate;
                    zNeighbour = zCandidate;

                    dCandidate = dist;
                    xCandidate = xPos;
                    zCandidate = zPos;
                } else if (dist > dCandidate && dist < dNeighbour) {
                    dNeighbour = dist;
                    xNeighbour = xPos;
                    zNeighbour = zPos;
                }
            }
        }

        double diff = distance(xCandidate - xNeighbour, zCandidate - zNeighbour);
        double total = (dCandidate + dNeighbour) / diff;

        dCandidate = dCandidate / total;
        dNeighbour = dNeighbour / total;

        double c = (diff / 2D) - dCandidate;
        if (c < width) {
            return (((float) (c / width)) - 1f) * depth;
        } else {
            return 0f;
        }
    }

    private double distance(double xDist, double zDist) {
        return Math.sqrt(xDist * xDist + zDist * zDist);
    }

    public double noise(double x, double y, double z, double frequency) {
        // Inside each unit cube, there is a seed point at a random position.  Go
        // through each of the nearby cubes until we find a cube with a seed point
        // that is closest to the specified position.
        x *= frequency;
        y *= frequency;
        z *= frequency;

        int xInt = (x > .0 ? (int) x : (int) x - 1);
        int yInt = (y > .0 ? (int) y : (int) y - 1);
        int zInt = (z > .0 ? (int) z : (int) z - 1);

        double minDist = 32000000.0;

        double xCandidate = 0;
        double yCandidate = 0;
        double zCandidate = 0;

        Random rand = new Random(seed);

        for (int zCur = zInt - 2; zCur <= zInt + 2; zCur++) {
            for (int yCur = yInt - 2; yCur <= yInt + 2; yCur++) {
                for (int xCur = xInt - 2; xCur <= xInt + 2; xCur++) {
                    // Calculate the position and distance to the seed point inside of
                    // this unit cube.

                    double xPos = xCur + valueNoise3D(xCur, yCur, zCur, seed);
                    double yPos = yCur + valueNoise3D(xCur, yCur, zCur, rand.nextLong());
                    double zPos = zCur + valueNoise3D(xCur, yCur, zCur, rand.nextLong());
                    double xDist = xPos - x;
                    double yDist = yPos - y;
                    double zDist = zPos - z;
                    double dist = xDist * xDist + yDist * yDist + zDist * zDist;

                    if (dist < minDist) {
                        // This seed point is closer to any others found so far, so record
                        // this seed point.
                        minDist = dist;
                        xCandidate = xPos;
                        yCandidate = yPos;
                        zCandidate = zPos;
                    }
                }
            }
        }

        if (useDistance) {
            double xDist = xCandidate - x;
            double yDist = yCandidate - y;
            double zDist = zCandidate - z;

            return getDistance(xDist, yDist, zDist);
        } else return ((double) valueNoise3D(
            (int) (Math.floor(xCandidate)),
            (int) (Math.floor(yCandidate)),
            (int) (Math.floor(zCandidate)), seed));

    }

    public static double valueNoise3D(int x, int y, int z, long seed) {
        long n = (1619 * x + 31337 * y + 6971 * z + 1013 * seed) & 0x7fffffff;
        n = (n >> 13) ^ n;
        return 1.0 - ((double) ((n * (n * n * 60493 + 19990303) + 1376312589) & 0x7fffffff) / 1073741824.0);
    }

    private double getDistance(double xDist, double yDist, double zDist) {
        switch (distanceMethod) {
            case 0:
                return Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist) / SQRT_3; //Approximation (for speed) of elucidean (regular) distance
            case 1:
                return xDist + yDist + zDist;
            default:
                return Double.NaN;
        }
    }

}