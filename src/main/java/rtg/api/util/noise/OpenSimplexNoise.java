package rtg.api.util.noise;

import rtg.api.util.MathUtils;


/**
 * Generates OpenSimplex Noise
 * <p>
 * 2D function lookup-table version by KdotJPG (With new Dodecagonal gradient set)
 * 3D function lookup-table version by DigitalShadow (With new normalized expanded cuboctahedral gradient set)
 * <p>
 * Both implemented using permutation tables of size 1024 instead of the traditional 256.
 * <p>
 * Includes additional 2D function that supports
 * - Simultaneous evaluation of the same point with different seeds
 * - First derivatives
 * - SPH2-noise (output is a 2D coordinate within a unit disc, rather than a 1D value)
 *
 * @author KdotJPG
 * @author <a href="https://github.com/srs-bsns">srs-bsns</a>
 * @version 1.0.0
 * @see <a href="https://gist.github.com/KdotJPG/b1270127455a94ac5d19">KdotJPG/b1270127455a94ac5d19</a>
 * @since 1.0.0
 */
public class OpenSimplexNoise implements SimplexNoise {

    //2D Gradients -- new scheme (Dodecagon)
    static final double[] GRADIENTS_2D = new double[]{
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
    static final double[] GRADIENTS_SPH2 = new double[]{
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
    //3D Gradients -- new scheme (Normalized expanded cuboctahedron)
    static final double[] GRADIENTS_3D = new double[]{
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
    private static final double STRETCH_2D = -0.211324865405187; //(1/Math.sqrt(2+1)-1)/2;
    private static final double SQUISH_2D = 0.366025403784439; //(Math.sqrt(2+1)-1)/2;
    private static final double STRETCH_3D = -1.0 / 6.0;         //(1/Math.sqrt(3+1)-1)/3;
    private static final double SQUISH_3D = 1.0 / 3.0;         //(Math.sqrt(3+1)-1)/3;
    private static final LatticePoint2D[] LOOKUP_2D;
    private static final Contribution3D[] LOOKUP_3D;


    /* float Aliases */

    static {

        //2D (KdotJPG)
        LOOKUP_2D = new LatticePoint2D[8 * 4];

        for (int i = 0; i < 8; i++) {

            int i1, j1, i2, j2;
            if ((i & 1) == 0) {

                if ((i & 2) == 0) {
                    i1 = 0;
                    j1 = 0;
                }
                else {
                    i1 = 2;
                    j1 = 0;
                }

                if ((i & 4) == 0) {
                    i2 = 1;
                    j2 = -1;
                }
                else {
                    i2 = 1;
                    j2 = 1;
                }
            }
            else {

                if ((i & 2) == 0) {
                    i1 = -1;
                    j1 = 1;
                }
                else {
                    i1 = 1;
                    j1 = 1;
                }

                if ((i & 4) == 0) {
                    i2 = 0;
                    j2 = 0;
                }
                else {
                    i2 = 0;
                    j2 = 2;
                }
            }

            LOOKUP_2D[i * 4] = new LatticePoint2D(1, 0);
            LOOKUP_2D[i * 4 + 1] = new LatticePoint2D(0, 1);
            LOOKUP_2D[i * 4 + 2] = new LatticePoint2D(i1, j1);
            LOOKUP_2D[i * 4 + 3] = new LatticePoint2D(i2, j2);
        }

        //3D (DigitalShadow)
        int[][] base3D = new int[][]{
            new int[]{0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            new int[]{2, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1, 3, 1, 1, 1},
            new int[]{1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 2, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1}
        };

        int[] p3D = new int[]{
            0, 0, 1, -1, 0, 0, 1, 0, -1, 0, 0, -1,
            1, 0, 0, 0, 1, -1, 0, 0, -1, 0, 1, 0,
            0, -1, 1, 0, 2, 1, 1, 0, 1, 1, 1, -1,
            0, 2, 1, 0, 1, 1, 1, -1, 1, 0, 2, 0,
            1, 1, 1, -1, 1, 1, 1, 3, 2, 1, 0, 3,
            1, 2, 0, 1, 3, 2, 0, 1, 3, 1, 0, 2,
            1, 3, 0, 2, 1, 3, 0, 1, 2, 1, 1, 1,
            0, 0, 2, 2, 0, 0, 1, 1, 0, 1, 0, 2,
            0, 2, 0, 1, 1, 0, 0, 1, 2, 0, 0, 2,
            2, 0, 0, 0, 0, 1, 1, -1, 1, 2, 0, 0,
            0, 0, 1, -1, 1, 1, 2, 0, 0, 0, 0, 1,
            1, 1, -1, 2, 3, 1, 1, 1, 2, 0, 0, 2,
            2, 3, 1, 1, 1, 2, 2, 0, 0, 2, 3, 1,
            1, 1, 2, 0, 2, 0, 2, 1, 1, -1, 1, 2,
            0, 0, 2, 2, 1, 1, -1, 1, 2, 2, 0, 0,
            2, 1, -1, 1, 1, 2, 0, 0, 2, 2, 1, -1,
            1, 1, 2, 0, 2, 0, 2, 1, 1, 1, -1, 2,
            2, 0, 0, 2, 1, 1, 1, -1, 2, 0, 2, 0
        };

        int[] lookupPairs3D = new int[]{
            0, 2, 1, 1, 2, 2, 5, 1,
            6, 0, 7, 0, 32, 2, 34, 2,
            129, 1, 133, 1, 160, 5, 161, 5,
            518, 0, 519, 0, 546, 4, 550, 4,
            645, 3, 647, 3, 672, 5, 673, 5,
            674, 4, 677, 3, 678, 4, 679, 3,
            680, 13, 681, 13, 682, 12, 685, 14,
            686, 12, 687, 14, 712, 20, 714, 18,
            809, 21, 813, 23, 840, 20, 841, 21,
            1198, 19, 1199, 22, 1226, 18, 1230, 19,
            1325, 23, 1327, 22, 1352, 15, 1353, 17,
            1354, 15, 1357, 17, 1358, 16, 1359, 16,
            1360, 11, 1361, 10, 1362, 11, 1365, 10,
            1366, 9, 1367, 9, 1392, 11, 1394, 11,
            1489, 10, 1493, 10, 1520, 8, 1521, 8,
            1878, 9, 1879, 9, 1906, 7, 1910, 7,
            2005, 6, 2007, 6, 2032, 8, 2033, 8,
            2034, 7, 2037, 6, 2038, 7, 2039, 6
        };

        Contribution3D[] contributions3D = new Contribution3D[p3D.length / 9];

        for (int i = 0; i < p3D.length; i += 9) {

            int[] baseSet = base3D[p3D[i]];
            Contribution3D previous = null;
            Contribution3D current = null;
            for (int j = 0; j < baseSet.length; j += 4) {

                current = new Contribution3D(baseSet[j], baseSet[j + 1], baseSet[j + 2], baseSet[j + 3]);
                if (previous == null) {
                    contributions3D[i / 9] = current;
                }
                else {
                    previous.setNext(current);
                }
                previous = current;
            }
            if (current != null) {
                current.setNext(new Contribution3D(p3D[i + 1], p3D[i + 2], p3D[i + 3], p3D[i + 4]));
                current.getNext().setNext(new Contribution3D(p3D[i + 5], p3D[i + 6], p3D[i + 7], p3D[i + 8]));
            }
        }

        LOOKUP_3D = new Contribution3D[2048];
        for (int i = 0; i < lookupPairs3D.length; i += 2) {
            LOOKUP_3D[lookupPairs3D[i]] = contributions3D[lookupPairs3D[i + 1]];
        }
    }

    private int[] perm;


    /* Standard functions */
    private int[] perm2D;
    private int[] perm2D_sph2;


    /* Multi-eval */
    private int[] perm3D;

    public OpenSimplexNoise(long seed) {

        this.perm = new int[1024];
        this.perm2D = new int[1024];
        this.perm2D_sph2 = new int[1024];
        this.perm3D = new int[1024];

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
            perm2D[i] = perm[i] % 12 * 2;
            perm2D_sph2[i] = perm[i] / 12 % 12 * 2;
            perm3D[i] = perm[i] % 48 * 3;
            source[r] = source[i];
        }
    }

    private static int fastFloor(double d) {
        int i;
        return (d < (i = (int) d)) ? i - 1 : i;
    }


    /* Definitions */

    @Override
    public float noise2f(float x, float y) {
        return (float) noise2d(x, y);
    }

    @Override
    public float noise3f(float x, float y, float z) {
        return (float) noise3d(x, y, z);
    }

    //2D OpenSimplex noise (KdotJPG)
    @Override
    public double noise2d(double x, double y) {

        double value = 0;

        //Get points for A2 lattice
        double s = STRETCH_2D * (x + y);
        double xs = x + s;
        double ys = y + s;

        //Get base points and offsets
        int xsb = fastFloor(xs);
        int ysb = fastFloor(ys);

        double xsi = xs - xsb;
        double ysi = ys - ysb;

        //Index to point list
        int a = (int) (ysi - xsi + 1);
        int index = (a << 2)
            | (int) (xsi + ysi / 2.0 + a / 2.0) << 3
            | (int) (ysi + xsi / 2.0 + 1.0 / 2.0 - a / 2.0) << 4;

        //Get unskewed offsets.
        double ssi = (xsi + ysi) * SQUISH_2D;
        double xi = xsi + ssi;
        double yi = ysi + ssi;

        //Point contributions
        for (int i = 0; i < 4; i++) {

            LatticePoint2D lattice = LOOKUP_2D[index + i];

            double dx = xi + lattice.getDx();
            double dy = yi + lattice.getDy();

            double attn = 2.0 - MathUtils.pow2(dx) - MathUtils.pow2(dy);
            if (attn <= 0) {
                continue;
            }

            int pxm = (xsb + lattice.getXsv()) & 1023;
            int pym = (ysb + lattice.getYsv()) & 1023;
            int gi = perm2D[perm[pxm] ^ pym];

            double extrp = GRADIENTS_2D[gi] * dx + GRADIENTS_2D[gi + 1] * dy;

            value += MathUtils.pow2(MathUtils.pow2(attn)) * extrp;
        }
        return value;
    }

    //3D OpenSimplex Noise (DigitalShadow)
    @Override
    public double noise3d(double x, double y, double z) {

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

        int hash = (int) (yins - zins + 1)
            | (int) (xins - yins + 1) << 1
            | (int) (xins - zins + 1) << 2
            | (int) inSum << 3
            | (int) (inSum + zins) << 5
            | (int) (inSum + yins) << 7
            | (int) (inSum + xins) << 9;

        Contribution3D c = LOOKUP_3D[hash];

        double value = 0.0;
        while (c != null) {

            double dx = dx0 + c.getDx();
            double dy = dy0 + c.getDy();
            double dz = dz0 + c.getDz();

            double attn = 2 - dx * dx - dy * dy - dz * dz;
            if (attn > 0) {

                int px = xsb + c.getXsb();
                int py = ysb + c.getYsb();
                int pz = zsb + c.getZsb();

                int i = perm3D[(perm[(perm[px & 0x3FF] ^ py) & 0x3FF] ^ pz) & 0x3FF];

                double valuePart = GRADIENTS_3D[i] * dx + GRADIENTS_3D[i + 1] * dy + GRADIENTS_3D[i + 2] * dz;

                value += MathUtils.pow2(MathUtils.pow2(attn)) * valuePart;
            }
            c = c.getNext();
        }
        return value;
    }

    @Override
    public void multiEval2D(double x, double y, ISimplexData2D data) {

        //Get points for A2 lattice
        double s = STRETCH_2D * (x + y);
        double xs = x + s;
        double ys = y + s;

        //Get base points and offsets
        int xsb = fastFloor(xs);
        int ysb = fastFloor(ys);
        double xsi = xs - xsb;
        double ysi = ys - ysb;

        //Index to point list
        int a = (int) (ysi - xsi + 1);
        int index = (a << 2) | (int) (xsi + ysi / 2.0 + a / 2.0) << 3 | (int) (ysi + xsi / 2.0 + 1.0 / 2.0 - a / 2.0) << 4;

        //Get unskewed offsets.
        double ssi = (xsi + ysi) * SQUISH_2D;
        double xi = xsi + ssi;
        double yi = ysi + ssi;

        // clear data
        data.clear();

        //Point contributions
        for (int i = 0; i < 4; i++) {

            LatticePoint2D lattice = LOOKUP_2D[index + i];

            double dx = xi + lattice.getDx();
            double dy = yi + lattice.getDy();
            double attn = 2.0 - MathUtils.pow2(dx) - MathUtils.pow2(dy);
            if (attn <= 0) {
                continue;
            }

            int pxm = (xsb + lattice.getXsv()) & 1023;
            int pym = (ysb + lattice.getYsv()) & 1023;
            int gi_p = perm[pxm] ^ pym;
            int gi = perm2D[gi_p];
            double gx = GRADIENTS_2D[gi];
            double gy = GRADIENTS_2D[gi + 1];
            double extrp = gx * dx + gy * dy;
            int gi_sph2 = perm2D_sph2[gi_p];
            data.request().apply(attn, extrp, gx, gy, gi_sph2, dx, dy);
        }
    }

    private static final class LatticePoint2D {

        private int xsv;
        private int ysv;
        private double dx;
        private double dy;

        private LatticePoint2D(int xsv, int ysv) {
            this.xsv = xsv;
            this.ysv = ysv;
            this.dx = -xsv - ((xsv + ysv) * SQUISH_2D);
            this.dy = -ysv - ((xsv + ysv) * SQUISH_2D);
        }

        public int getXsv() {
            return xsv;
        }

        public int getYsv() {
            return ysv;
        }

        public double getDx() {
            return dx;
        }

        public double getDy() {
            return dy;
        }
    }

    private static final class Contribution3D {

        private int xsb;
        private int ysb;
        private int zsb;
        private double dx;
        private double dy;
        private double dz;
        private Contribution3D next;

        private Contribution3D(double multiplier, int xsb, int ysb, int zsb) {
            this.xsb = xsb;
            this.ysb = ysb;
            this.zsb = zsb;
            this.dx = -xsb - multiplier * SQUISH_3D;
            this.dy = -ysb - multiplier * SQUISH_3D;
            this.dz = -zsb - multiplier * SQUISH_3D;
        }

        private Contribution3D getNext() {
            return next;
        }

        private void setNext(Contribution3D next) {
            this.next = next;
        }

        private double getDx() {
            return dx;
        }

        private double getDy() {
            return dy;
        }

        private double getDz() {
            return dz;
        }

        private int getXsb() {
            return xsb;
        }

        private int getYsb() {
            return ysb;
        }

        private int getZsb() {
            return zsb;
        }
    }
}