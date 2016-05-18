/**
 * Generates OpenSimplex Noise
 * <p/>
 * 2D function lookup-table version by KdotJPG (With new Dodecagonal gradient set)
 * 3D function lookup-table version by DigitalShadow (With new normalized expanded cuboctahedral gradient set)
 * <p/>
 * Both implemented using permutation tables of size 1024 instead of the traditional 256.
 * <p/>
 * Includes additional 2D function that supports
 * - Simultaneous evaluation of the same point with different seeds
 * - First derivatives
 * - SPH2-noise (output is a 2D coordinate within a unit disc, rather than a 1D value)
 */

package teamrtg.rtg.api.util.noise;

/**
 * @author KdotJPG
 * @version $Revision: 1.3$
 * @see https://gist.github.com/KdotJPG/b1270127455a94ac5d19
 */
public class SimplexOctave {

    private static final double STRETCH_2D = -0.211324865405187;    //(1/Math.sqrt(2+1)-1)/2;
    private static final double SQUISH_2D = 0.366025403784439;      //(Math.sqrt(2+1)-1)/2;
    private static final double STRETCH_3D = -1.0 / 6.0;            //(1/Math.sqrt(3+1)-1)/3;
    private static final double SQUISH_3D = 1.0 / 3.0;              //(Math.sqrt(3+1)-1)/3;

    private static final long DEFAULT_SEED = 0;
    private static final LatticePoint2D[] LOOKUP_2D;
    private static final double[] GRADIENTS_SPH2 = new double[] {
        0, 1.000000000000000,
        0.500000000000000, 0.866025403784439,
        0.866025403784439, 0.500000000000000,
        1.000000000000000, 0,
        0.866025403784439, -0.500000000000000,
        0.500000000000000, -0.866025403784439,
        0, -1.000000000000000,
        -0.500000000000000, -0.866025403784439,
        -0.866025403784439, -0.500000000000000,
        -1.000000000000000, 0,
        -0.866025403784439, 0.500000000000000,
        -0.500000000000000, 0.866025403784439
    };
    private static Contribution3[] LOOKUP_3D;
    //2D Gradients -- new scheme (Dodecagon)
    private static double[] GRADIENTS_2D = new double[] {
        0.114251372530929, 0.065963060686016,
        0.131926121372032, 0.000000000000000,
        0.114251372530929, -0.065963060686016,
        0.065963060686016, -0.114251372530929,
        0.000000000000000, -0.131926121372032,
        -0.065963060686016, -0.114251372530929,
        -0.114251372530929, -0.065963060686016,
        -0.131926121372032, -0.000000000000000,
        -0.114251372530929, 0.065963060686016,
        -0.065963060686016, 0.114251372530929,
        -0.000000000000000, 0.131926121372032,
        0.065963060686016, 0.114251372530929,
    };
    //3D Gradients -- new scheme (Normalized expanded cuboctahedron)
    private static double[] GRADIENTS_3D = new double[] {
        -0.009192019279820, 0.061948581592974, 0.105513124626310,
        0.061948581592974, -0.009192019279820, 0.105513124626310,
        0.052339395980958, 0.052339395980958, 0.097858646551677,
        0.002784312704445, 0.002784312704445, 0.122636188189934,
        -0.009192019279820, 0.105513124626310, 0.061948581592974,
        0.061948581592974, 0.105513124626310, -0.009192019279820,
        0.052339395980958, 0.097858646551677, 0.052339395980958,
        0.002784312704445, 0.122636188189934, 0.002784312704445,
        0.105513124626310, -0.009192019279820, 0.061948581592974,
        0.105513124626310, 0.061948581592974, -0.009192019279820,
        0.097858646551677, 0.052339395980958, 0.052339395980958,
        0.122636188189934, 0.002784312704445, 0.002784312704445,
        -0.067278076657600, 0.090991610281865, 0.047427067248529,
        -0.090991610281865, 0.067278076657600, -0.047427067248529,
        -0.057908021389848, 0.107463104666361, -0.012388770819128,
        -0.107463104666361, 0.057908021389848, 0.012388770819128,
        -0.067278076657600, 0.047427067248529, 0.090991610281865,
        -0.090991610281865, -0.047427067248529, 0.067278076657600,
        -0.057908021389848, -0.012388770819128, 0.107463104666361,
        -0.107463104666361, 0.012388770819128, 0.057908021389848,
        0.047427067248529, -0.067278076657600, 0.090991610281865,
        -0.047427067248529, -0.090991610281865, 0.067278076657600,
        -0.012388770819128, -0.057908021389848, 0.107463104666361,
        0.012388770819128, -0.107463104666361, 0.057908021389848,
        0.067278076657600, -0.090991610281865, -0.047427067248529,
        0.090991610281865, -0.067278076657600, 0.047427067248529,
        0.107463104666361, -0.057908021389848, -0.012388770819128,
        0.057908021389848, -0.107463104666361, 0.012388770819128,
        0.067278076657600, -0.047427067248529, -0.090991610281865,
        0.090991610281865, 0.047427067248529, -0.067278076657600,
        0.107463104666361, -0.012388770819128, -0.057908021389848,
        0.057908021389848, 0.012388770819128, -0.107463104666361,
        -0.047427067248529, 0.067278076657600, -0.090991610281865,
        0.047427067248529, 0.090991610281865, -0.067278076657600,
        -0.012388770819128, 0.107463104666361, -0.057908021389848,
        0.012388770819128, 0.057908021389848, -0.107463104666361,
        0.009192019279820, -0.061948581592974, -0.105513124626310,
        -0.061948581592974, 0.009192019279820, -0.105513124626310,
        -0.002784312704445, -0.002784312704445, -0.122636188189934,
        -0.052339395980958, -0.052339395980958, -0.097858646551677,
        0.009192019279820, -0.105513124626310, -0.061948581592974,
        -0.061948581592974, -0.105513124626310, 0.009192019279820,
        -0.002784312704445, -0.122636188189934, -0.002784312704445,
        -0.052339395980958, -0.097858646551677, -0.052339395980958,
        -0.105513124626310, 0.009192019279820, -0.061948581592974,
        -0.105513124626310, -0.061948581592974, 0.009192019279820,
        -0.122636188189934, -0.002784312704445, -0.002784312704445,
        -0.097858646551677, -0.052339395980958, -0.052339395980958
    };

    static {

        //2D (KdotJPG)
        LOOKUP_2D = new LatticePoint2D[8 * 4];
        for (int i = 0; i < 8; i++) {
            int i1, j1, i2, j2;
            if ((i & 1) == 0) {
                if ((i & 2) == 0) {
                    i1 = 0;
                    j1 = 0;
                } else {
                    i1 = 2;
                    j1 = 0;
                }
                if ((i & 4) == 0) {
                    i2 = 1;
                    j2 = -1;
                } else {
                    i2 = 1;
                    j2 = 1;
                }
            } else {
                if ((i & 2) == 0) {
                    i1 = -1;
                    j1 = 1;
                } else {
                    i1 = 1;
                    j1 = 1;
                }
                if ((i & 4) == 0) {
                    i2 = 0;
                    j2 = 0;
                } else {
                    i2 = 0;
                    j2 = 2;
                }
            }
            LOOKUP_2D[i * 4 + 0] = new LatticePoint2D(1, 0);
            LOOKUP_2D[i * 4 + 1] = new LatticePoint2D(0, 1);
            LOOKUP_2D[i * 4 + 2] = new LatticePoint2D(i1, j1);
            LOOKUP_2D[i * 4 + 3] = new LatticePoint2D(i2, j2);
        }

        //3D (DigitalShadow)
        int[][] base3D = new int[][] {
            new int[] {0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            new int[] {2, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1, 3, 1, 1, 1},
            new int[] {1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 2, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1}
        };
        int[] p3D = new int[] {0, 0, 1, -1, 0, 0, 1, 0, -1, 0, 0, -1, 1, 0, 0, 0, 1, -1, 0, 0, -1, 0, 1, 0, 0, -1, 1, 0, 2, 1, 1, 0, 1, 1, 1, -1, 0, 2, 1, 0, 1, 1, 1, -1, 1, 0, 2, 0, 1, 1, 1, -1, 1, 1, 1, 3, 2, 1, 0, 3, 1, 2, 0, 1, 3, 2, 0, 1, 3, 1, 0, 2, 1, 3, 0, 2, 1, 3, 0, 1, 2, 1, 1, 1, 0, 0, 2, 2, 0, 0, 1, 1, 0, 1, 0, 2, 0, 2, 0, 1, 1, 0, 0, 1, 2, 0, 0, 2, 2, 0, 0, 0, 0, 1, 1, -1, 1, 2, 0, 0, 0, 0, 1, -1, 1, 1, 2, 0, 0, 0, 0, 1, 1, 1, -1, 2, 3, 1, 1, 1, 2, 0, 0, 2, 2, 3, 1, 1, 1, 2, 2, 0, 0, 2, 3, 1, 1, 1, 2, 0, 2, 0, 2, 1, 1, -1, 1, 2, 0, 0, 2, 2, 1, 1, -1, 1, 2, 2, 0, 0, 2, 1, -1, 1, 1, 2, 0, 0, 2, 2, 1, -1, 1, 1, 2, 0, 2, 0, 2, 1, 1, 1, -1, 2, 2, 0, 0, 2, 1, 1, 1, -1, 2, 0, 2, 0};
        int[] lookupPairs3D = new int[] {0, 2, 1, 1, 2, 2, 5, 1, 6, 0, 7, 0, 32, 2, 34, 2, 129, 1, 133, 1, 160, 5, 161, 5, 518, 0, 519, 0, 546, 4, 550, 4, 645, 3, 647, 3, 672, 5, 673, 5, 674, 4, 677, 3, 678, 4, 679, 3, 680, 13, 681, 13, 682, 12, 685, 14, 686, 12, 687, 14, 712, 20, 714, 18, 809, 21, 813, 23, 840, 20, 841, 21, 1198, 19, 1199, 22, 1226, 18, 1230, 19, 1325, 23, 1327, 22, 1352, 15, 1353, 17, 1354, 15, 1357, 17, 1358, 16, 1359, 16, 1360, 11, 1361, 10, 1362, 11, 1365, 10, 1366, 9, 1367, 9, 1392, 11, 1394, 11, 1489, 10, 1493, 10, 1520, 8, 1521, 8, 1878, 9, 1879, 9, 1906, 7, 1910, 7, 2005, 6, 2007, 6, 2032, 8, 2033, 8, 2034, 7, 2037, 6, 2038, 7, 2039, 6};

        Contribution3[] contributions3D = new Contribution3[p3D.length / 9];
        for (int i = 0; i < p3D.length; i += 9) {
            int[] baseSet = base3D[p3D[i]];
            Contribution3 previous = null, current = null;
            for (int k = 0; k < baseSet.length; k += 4) {
                current = new Contribution3(baseSet[k], baseSet[k + 1], baseSet[k + 2], baseSet[k + 3]);
                if (previous == null) {
                    contributions3D[i / 9] = current;
                } else {
                    previous.next = current;
                }
                previous = current;
            }
            current.next = new Contribution3(p3D[i + 1], p3D[i + 2], p3D[i + 3], p3D[i + 4]);
            current.next.next = new Contribution3(p3D[i + 5], p3D[i + 6], p3D[i + 7], p3D[i + 8]);
        }

        LOOKUP_3D = new Contribution3[2048];
        for (int i = 0; i < lookupPairs3D.length; i += 2) {
            LOOKUP_3D[lookupPairs3D[i]] = contributions3D[lookupPairs3D[i + 1]];
        }
    }

	/*
     * Aliases
	 */

    private int[] perm;
    private int[] perm2D;
    private int[] perm2D_sph2;
    private int[] perm3D;

	/*
     * Standard functions
	 */

    public SimplexOctave() {
        this(DEFAULT_SEED);
    }

    public SimplexOctave(long seed) {
        perm = new int[1024];
        perm2D = new int[1024];
        perm2D_sph2 = new int[1024];
        perm3D = new int[1024];
        int[] source = new int[1024];
        for (int i = 0; i < 1024; i++) {
            source[i] = i;
        }
        for (int i = 1023; i >= 0; i--) {
            seed = seed * 6364136223846793005L + 1442695040888963407L;
            int r = (int) ((seed + 31) % (i + 1));
            if (r < 0) {
                r += (i + 1);
            }
            perm[i] = source[r];
            perm2D[i] = ((perm[i] % 12) * 2);
            perm2D_sph2[i] = (((perm[i] / 12) % 12) * 2);
            perm3D[i] = ((perm[i] % 48) * 3);
            source[r] = source[i];
        }
    }

	/*
     * Multi-eval
	 */

    //2D OpenSimplex noise Multi-eval (KdotJPG)
    public static void noise(double x, double y, NoiseInstance2[] instances, double[] results) {

        //Get points for A2 lattice
        double s = STRETCH_2D * (x + y);
        double xs = x + s, ys = y + s;

        //Get base points and offsets
        int xsb = fastFloor(xs), ysb = fastFloor(ys);
        double xsi = xs - xsb, ysi = ys - ysb;

        //Index to point list
        int a = (int) (ysi - xsi + 1);
        int index =
            (a << 2) |
                (int) (xsi + ysi / 2.0 + a / 2.0) << 3 |
                (int) (ysi + xsi / 2.0 + 1.0 / 2.0 - a / 2.0) << 4;

        //Get unskewed offsets.
        double ssi = (xsi + ysi) * SQUISH_2D;
        double xi = xsi + ssi, yi = ysi + ssi;

        //Point contributions
        for (int i = 0; i < 4; i++) {
            LatticePoint2D c = LOOKUP_2D[index + i];

            double dx = xi + c.dx, dy = yi + c.dy;
            double attn = 2.0 - dx * dx - dy * dy;
            if (attn <= 0) continue;
            double attnSq = attn * attn;

            int pxm = (xsb + c.xsv) & 1023, pym = (ysb + c.ysv) & 1023;
            for (NoiseInstance2 instance : instances) {
                int gi_p = instance.noise.perm[pxm] ^ pym;
                int gi = instance.noise.perm2D[gi_p];
                double gx = GRADIENTS_2D[gi + 0], gy = GRADIENTS_2D[gi + 1];
                double extrapolation = gx * dx + gy * dy;

                if (instance.valueIndex >= 0) {
                    results[instance.valueIndex] += attnSq * attnSq * extrapolation;
                }
                if ((instance.ddxIndex & instance.ddyIndex) >= 0) {
                    if (instance.ddxIndex >= 0) {
                        results[instance.ddxIndex] += (gx * attn - 8 * dx * extrapolation) * attnSq * attn;
                    }
                    if (instance.ddyIndex >= 0) {
                        results[instance.ddyIndex] += (gy * attn - 8 * dy * extrapolation) * attnSq * attn;
                    }
                }
                if ((instance.sph2xIndex & instance.sph2yIndex) >= 0) {
                    int gi_sph2 = instance.noise.perm2D_sph2[gi_p];
                    if (instance.sph2xIndex >= 0) {
                        results[instance.sph2xIndex] += attnSq * attnSq * extrapolation * GRADIENTS_SPH2[gi_sph2 + 0];
                    }
                    if (instance.sph2yIndex >= 0) {
                        results[instance.sph2yIndex] += attnSq * attnSq * extrapolation * GRADIENTS_SPH2[gi_sph2 + 1];
                    }
                }
            }
        }
    }

    //Alias for 1D
    public float noise1(float x) {
        return (float) noise(x, 0.5);
    }

    /* Data Request scheme
     * Outside methods have the ability to create DataStore objects.
     * They can know the type of data in them
     * SimplexOctave objects can set the data in a DataStore
     *
     */

    //2D OpenSimplex noise (KdotJPG)
    public double noise(double x, double y) {
        double value = 0;

        //Get points for A2 lattice
        double s = STRETCH_2D * (x + y);
        double xs = x + s, ys = y + s;

        //Get base points and offsets
        int xsb = fastFloor(xs), ysb = fastFloor(ys);
        double xsi = xs - xsb, ysi = ys - ysb;

        //Index to point list
        int a = (int) (ysi - xsi + 1);
        int index =
            (a << 2) |
                (int) (xsi + ysi / 2.0 + a / 2.0) << 3 |
                (int) (ysi + xsi / 2.0 + 1.0 / 2.0 - a / 2.0) << 4;

        //Get unskewed offsets.
        double ssi = (xsi + ysi) * SQUISH_2D;
        double xi = xsi + ssi, yi = ysi + ssi;

        //Point contributions
        for (int i = 0; i < 4; i++) {
            LatticePoint2D c = LOOKUP_2D[index + i];

            double dx = xi + c.dx, dy = yi + c.dy;
            double attn = 2.0 - dx * dx - dy * dy;
            if (attn <= 0) continue;

            int pxm = (xsb + c.xsv) & 1023, pym = (ysb + c.ysv) & 1023;
            int gi = perm2D[perm[pxm] ^ pym];
            double extrapolation = GRADIENTS_2D[gi] * dx
                + GRADIENTS_2D[gi + 1] * dy;

            attn *= attn;
            value += attn * attn * extrapolation;
        }

        return value;
    }

    private static int fastFloor(double x) {
        int xi = (int) x;
        return x < xi ? xi - 1 : xi;
    }

    //Alias for 2D
    public float noise2(float x, float y) {
        return (float) noise(x, y);
    }

    //Alias for 3D
    public float noise3(float x, float y, float z) {
        return (float) noise(x, y, z);
    }

    //3D OpenSimplex Noise (DigitalShadow)
    public double noise(double x, double y, double z) {
        double stretchOffset = (x + y + z) * STRETCH_3D;
        double xs = x + stretchOffset;
        double ys = y + stretchOffset;
        double zs = z + stretchOffset;

        int xsb = fastFloor(xs);
        int ysb = fastFloor(ys);
        int zsb = fastFloor(zs);

        double squishOffset = (xsb + ysb + zsb) * SQUISH_3D;
        double dx0 = x - (xsb + squishOffset);
        double dy0 = y - (ysb + squishOffset);
        double dz0 = z - (zsb + squishOffset);

        double xins = xs - xsb;
        double yins = ys - ysb;
        double zins = zs - zsb;

        double inSum = xins + yins + zins;

        int hash =
            (int) (yins - zins + 1) |
                (int) (xins - yins + 1) << 1 |
                (int) (xins - zins + 1) << 2 |
                (int) inSum << 3 |
                (int) (inSum + zins) << 5 |
                (int) (inSum + yins) << 7 |
                (int) (inSum + xins) << 9;

        Contribution3 c = LOOKUP_3D[hash];

        double value = 0.0;
        while (c != null) {
            double dx = dx0 + c.dx;
            double dy = dy0 + c.dy;
            double dz = dz0 + c.dz;
            double attn = 2 - dx * dx - dy * dy - dz * dz;
            if (attn > 0) {
                int px = xsb + c.xsb;
                int py = ysb + c.ysb;
                int pz = zsb + c.zsb;

                int i = perm3D[(perm[(perm[px & 0x3FF] ^ py) & 0x3FF] ^ pz) & 0x3FF];
                double valuePart = GRADIENTS_3D[i] * dx + GRADIENTS_3D[i + 1] * dy + GRADIENTS_3D[i + 2] * dz;

                attn *= attn;
                value += attn * attn * valuePart;
            }

            c = c.next;
        }
        return value;
    }


	/*
     * Utility
	 */

    //Alias for 3D (again)
    public double improvedNoise(double x, double y, double z) {
        return noise(x, y, z);
    }

	/*
     * Definitions
	 */

    public void evaluateNoise(double x, double y, DataStore data) {

        //Get points for A2 lattice
        double s = STRETCH_2D * (x + y);
        double xs = x + s, ys = y + s;

        //Get base points and offsets
        int xsb = fastFloor(xs), ysb = fastFloor(ys);
        double xsi = xs - xsb, ysi = ys - ysb;

        //Index to point list
        int a = (int) (ysi - xsi + 1);
        int index =
            (a << 2) |
                (int) (xsi + ysi / 2.0 + a / 2.0) << 3 |
                (int) (ysi + xsi / 2.0 + 1.0 / 2.0 - a / 2.0) << 4;

        //Get unskewed offsets.
        double ssi = (xsi + ysi) * SQUISH_2D;
        double xi = xsi + ssi, yi = ysi + ssi;

        // clear data
        data.clear();
        //Point contributions
        for (int i = 0; i < 4; i++) {
            LatticePoint2D c = LOOKUP_2D[index + i];

            double dx = xi + c.dx, dy = yi + c.dy;
            double attn = 2.0 - dx * dx - dy * dy;
            if (attn <= 0) continue;

            int pxm = (xsb + c.xsv) & 1023, pym = (ysb + c.ysv) & 1023;
            int gi_p = perm[pxm] ^ pym;
            int gi = perm2D[gi_p];
            double gx = GRADIENTS_2D[gi + 0], gy = GRADIENTS_2D[gi + 1];
            double extrapolation = gx * dx + gy * dy;
            int gi_sph2 = perm2D_sph2[gi_p];
            data.request().record(attn, extrapolation, gx, gy, gi_sph2, dx, dy);
        }
    }
    private interface DataRequest {
        abstract void record(double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy);
    }

    abstract public static class DataStore {
        // methods not public to indicate outside classes should not use
        // can't make private, which would be ideal, becuase of java rules
        abstract DataRequest request();

        abstract void clear();
    }

    public static class Disk extends DataStore {
        private final DataRequest request;
        private double deltax;
        private double deltay;

        public Disk() {
            super();
            request = new Request();
        }

        public final double deltax() {return deltax;}

        @Override
        void clear() {
            deltax = 0;
            deltay = 0;
        }

        public final double deltay() {return deltay;}

        DataRequest request() {return request;}

        private class Request implements DataRequest {
            public final void record(double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy) {
                double attnSq = attn * attn;
                deltax += attnSq * attnSq * extrapolation * GRADIENTS_SPH2[gi_sph2 + 0];
                deltay += attnSq * attnSq * extrapolation * GRADIENTS_SPH2[gi_sph2 + 1];

            }
        }


    }

    public static class Derivative extends DataStore {
        private final DataRequest request;
        private double deltax;
        private double deltay;

        public Derivative() {
            super();
            request = new Request();
        }

        public final double deltax() {return deltax;}

        @Override
        void clear() {
            deltax = 0;
            deltay = 0;
        }

        public final double deltay() {return deltay;}

        private class Request implements DataRequest {
            public final void record(double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy) {
                double attnSq = attn * attn;
                deltax += (gx * attn - 8 * dx * extrapolation) * attnSq * attn;
                deltay += (gy * attn - 8 * dy * extrapolation) * attnSq * attn;
            }
        }

        DataRequest request() {return request;}


    }

    public static class Scalar extends DataStore {
        private final DataRequest request;
        private double value;

        public Scalar() {
            super();
            request = new Request();
        }

        public final double value() {return value;}

        @Override
        void clear() {
            value = 0;
        }

        private class Request implements DataRequest {
            public final void record(double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy) {
                double attnSq = attn * attn;
                value += attnSq * attnSq * extrapolation;
            }
        }

        DataRequest request() {return request;}


    }

    private static class LatticePoint2D {
        public int xsv, ysv;
        public double dx, dy;

        public LatticePoint2D(int xsv, int ysv) {
            this.xsv = xsv;
            this.ysv = ysv;
            double ssv = (xsv + ysv) * SQUISH_2D;
            this.dx = -xsv - ssv;
            this.dy = -ysv - ssv;
        }
    }

    private static class Contribution3 {
        public double dx, dy, dz;
        public int xsb, ysb, zsb;
        public Contribution3 next;

        public Contribution3(double multiplier, int xsb, int ysb, int zsb) {
            dx = -xsb - multiplier * SQUISH_3D;
            dy = -ysb - multiplier * SQUISH_3D;
            dz = -zsb - multiplier * SQUISH_3D;
            this.xsb = xsb;
            this.ysb = ysb;
            this.zsb = zsb;
        }
    }

    public static class NoiseInstance2 {
        public SimplexOctave noise;
        public int valueIndex;
        public int ddxIndex, ddyIndex;
        public int sph2xIndex, sph2yIndex;
        public NoiseInstance2(SimplexOctave noise, int valueIndex,
                              int ddxIndex, int ddyIndex) {
            this(noise, valueIndex, ddxIndex, ddyIndex, -1, -1);
        }

        public NoiseInstance2(SimplexOctave noise, int valueIndex,
                              int ddxIndex, int ddyIndex, int sph2xIndex, int sph2yIndex) {
            this.noise = noise;
            this.valueIndex = valueIndex;
            this.ddxIndex = ddxIndex;
            this.ddyIndex = ddyIndex;
            this.sph2xIndex = sph2xIndex;
            this.sph2yIndex = sph2yIndex;
        }
        public NoiseInstance2(SimplexOctave noise, int valueIndex) {
            this(noise, valueIndex, -1, -1, -1, -1);
        }
    }

}