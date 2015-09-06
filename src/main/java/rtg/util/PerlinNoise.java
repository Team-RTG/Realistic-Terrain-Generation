/**
 * Replacement PerlinNoise.java that generates
 * OpenSimplex Noise instead of Perlin Noise.
 *
 * 2D function by Kurt Spencer (With new Dodecagonal gradient set)
 * 3D function heavily optimized variant by DigitalShadow
 */
 
package rtg.util;

/**
 * @author Kurt Spencer
 * @version $Revision: 1.1$
 */
public class PerlinNoise {

	private static final double STRETCH_2D = -0.211324865405187;    //(1/Math.sqrt(2+1)-1)/2;
	private static final double STRETCH_3D = -1.0 / 6.0;            //(1/Math.sqrt(3+1)-1)/3;
	private static final double SQUISH_2D = 0.366025403784439;      //(Math.sqrt(2+1)-1)/2;
	private static final double SQUISH_3D = 1.0 / 3.0;              //(Math.sqrt(3+1)-1)/3;
	
	private static final long DEFAULT_SEED = 0;
	
	private int[] perm;
	private int[] perm2D;
	private int[] perm3D;
	
	public PerlinNoise() {
		this(DEFAULT_SEED);
	}
	
	public PerlinNoise(long seed) {
		perm = new int[256];
		perm2D = new int[256];
		perm3D = new int[256];
		int[] source = new int[256];
		for (int i = 0; i < 256; i++) {
			source[i] = i;
		}
		for (int i = 255; i >= 0; i--) {
			seed = seed * 6364136223846793005L + 1442695040888963407L;
			int r = (int)((seed + 31) % (i + 1));
			if (r < 0) {
				r += (i + 1);
			}
			perm[i] = source[r];
			perm2D[i] = ((perm[i] % 12) * 2);
			perm3D[i] = ((perm[i] % 24) * 3);
			source[r] = source[i];
		}
	}
	
	//Alias for 1D
	public float noise1(float x) {
		return (float)noise(x, 0.5);
	}
	
	//Alias for 2D
	public float noise2(float x, float y) {
		return (float)noise(x, y);
	}
	
	//Alias for 3D
	public float noise3(float x, float y, float z) {
		return (float)noise(x, y, z);
	}
	
	//Alias for 3D (again)
	public double improvedNoise(double x, double y, double z)
	{
		return noise(x, y, z);
	}
	
	//2D OpenSimplex Noise
	public double noise(double x, double y) {
	
		//Place input coordinates onto grid.
		double stretchOffset = (x + y) * STRETCH_2D;
		double xs = x + stretchOffset;
		double ys = y + stretchOffset;
		
		//Floor to get grid coordinates of rhombus (stretched square) super-cell origin.
		int xsb = fastFloor(xs);
		int ysb = fastFloor(ys);
		
		//Skew out to get actual coordinates of rhombus origin. We'll need these later.
		double squishOffset = (xsb + ysb) * SQUISH_2D;
		double xb = xsb + squishOffset;
		double yb = ysb + squishOffset;
		
		//Compute grid coordinates relative to rhombus origin.
		double xins = xs - xsb;
		double yins = ys - ysb;
		
		//Sum those together to get a value that determines which region we're in.
		double inSum = xins + yins;

		//Positions relative to origin point.
		double dx0 = x - xb;
		double dy0 = y - yb;
		
		//We'll be defining these inside the next block and using them afterwards.
		double dx_ext, dy_ext;
		int xsv_ext, ysv_ext;
		
		double value = 0;

		//Contribution (1,0)
		double dx1 = dx0 - 1 - SQUISH_2D;
		double dy1 = dy0 - 0 - SQUISH_2D;
		double attn1 = 2 - dx1 * dx1 - dy1 * dy1;
		if (attn1 > 0) {
			attn1 *= attn1;
			value += attn1 * attn1 * extrapolate2D(xsb + 1, ysb + 0, dx1, dy1);
		}

		//Contribution (0,1)
		double dx2 = dx0 - 0 - SQUISH_2D;
		double dy2 = dy0 - 1 - SQUISH_2D;
		double attn2 = 2 - dx2 * dx2 - dy2 * dy2;
		if (attn2 > 0) {
			attn2 *= attn2;
			value += attn2 * attn2 * extrapolate2D(xsb + 0, ysb + 1, dx2, dy2);
		}
		
		if (inSum <= 1) { //We're inside the triangle (2-Simplex) at (0,0)
			double zins = 1 - inSum;
			if (zins > xins || zins > yins) { //(0,0) is one of the closest two triangular vertices
				if (xins > yins) {
					xsv_ext = xsb + 1;
					ysv_ext = ysb - 1;
					dx_ext = dx0 - 1;
					dy_ext = dy0 + 1;
				} else {
					xsv_ext = xsb - 1;
					ysv_ext = ysb + 1;
					dx_ext = dx0 + 1;
					dy_ext = dy0 - 1;
				}
			} else { //(1,0) and (0,1) are the closest two vertices.
				xsv_ext = xsb + 1;
				ysv_ext = ysb + 1;
				dx_ext = dx0 - 1 - 2 * SQUISH_2D;
				dy_ext = dy0 - 1 - 2 * SQUISH_2D;
			}
		} else { //We're inside the triangle (2-Simplex) at (1,1)
			double zins = 2 - inSum;
			if (zins < xins || zins < yins) { //(0,0) is one of the closest two triangular vertices
				if (xins > yins) {
					xsv_ext = xsb + 2;
					ysv_ext = ysb + 0;
					dx_ext = dx0 - 2 - 2 * SQUISH_2D;
					dy_ext = dy0 + 0 - 2 * SQUISH_2D;
				} else {
					xsv_ext = xsb + 0;
					ysv_ext = ysb + 2;
					dx_ext = dx0 + 0 - 2 * SQUISH_2D;
					dy_ext = dy0 - 2 - 2 * SQUISH_2D;
				}
			} else { //(1,0) and (0,1) are the closest two vertices.
				dx_ext = dx0;
				dy_ext = dy0;
				xsv_ext = xsb;
				ysv_ext = ysb;
			}
			xsb += 1;
			ysb += 1;
			dx0 = dx0 - 1 - 2 * SQUISH_2D;
			dy0 = dy0 - 1 - 2 * SQUISH_2D;
		}
		
		//Contribution (0,0) or (1,1)
		double attn0 = 2 - dx0 * dx0 - dy0 * dy0;
		if (attn0 > 0) {
			attn0 *= attn0;
			value += attn0 * attn0 * extrapolate2D(xsb, ysb, dx0, dy0);
		}
		
		//Extra Vertex
		double attn_ext = 2 - dx_ext * dx_ext - dy_ext * dy_ext;
		if (attn_ext > 0) {
			attn_ext *= attn_ext;
			value += attn_ext * attn_ext * extrapolate2D(xsv_ext, ysv_ext, dx_ext, dy_ext);
		}
		
		return value;
	}
	
	//3D OpenSimplex Noise
	public double noise(double x, double y, double z)
	{
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
		   (int)(yins - zins + 1) |
		   (int)(xins - yins + 1) << 1 |
		   (int)(xins - zins + 1) << 2 |
		   (int)inSum << 3 |
		   (int)(inSum + zins) << 5 |
		   (int)(inSum + yins) << 7 |
		   (int)(inSum + xins) << 9;

		Contribution3 c = lookup3D[hash];

		double value = 0.0;
		while (c != null)
		{
			double dx = dx0 + c.dx;
			double dy = dy0 + c.dy;
			double dz = dz0 + c.dz;
			double attn = 2 - dx * dx - dy * dy - dz * dz;
			if (attn > 0)
			{
				int px = xsb + c.xsb;
				int py = ysb + c.ysb;
				int pz = zsb + c.zsb;

				int i = perm3D[(perm[(perm[px & 0xFF] + py) & 0xFF] + pz) & 0xFF];
				double valuePart = gradients3D[i] * dx + gradients3D[i + 1] * dy + gradients3D[i + 2] * dz;

				attn *= attn;
				value += attn * attn * valuePart;
			}

			c = c.next;
		}
		return value;
	}
	
	private double extrapolate2D(int xsb, int ysb, double dx, double dy)
	{
		int index = perm2D[(perm[xsb & 0xFF] + ysb) & 0xFF];
		return gradients2D[index] * dx + gradients2D[index + 1] * dy;
	}
	
	private static int fastFloor(double x) {
		int xi = (int)x;
		return x < xi ? xi - 1 : xi;
	}

	private static Contribution3[] lookup3D;
	
	static {
		int[][] base3D = new int[][] {
			new int[] { 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1 },
			new int[] { 2, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1, 3, 1, 1, 1 },
			new int[] { 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 2, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1 }
		};
		int[] p3D = new int[] { 0, 0, 1, -1, 0, 0, 1, 0, -1, 0, 0, -1, 1, 0, 0, 0, 1, -1, 0, 0, -1, 0, 1, 0, 0, -1, 1, 0, 2, 1, 1, 0, 1, 1, 1, -1, 0, 2, 1, 0, 1, 1, 1, -1, 1, 0, 2, 0, 1, 1, 1, -1, 1, 1, 1, 3, 2, 1, 0, 3, 1, 2, 0, 1, 3, 2, 0, 1, 3, 1, 0, 2, 1, 3, 0, 2, 1, 3, 0, 1, 2, 1, 1, 1, 0, 0, 2, 2, 0, 0, 1, 1, 0, 1, 0, 2, 0, 2, 0, 1, 1, 0, 0, 1, 2, 0, 0, 2, 2, 0, 0, 0, 0, 1, 1, -1, 1, 2, 0, 0, 0, 0, 1, -1, 1, 1, 2, 0, 0, 0, 0, 1, 1, 1, -1, 2, 3, 1, 1, 1, 2, 0, 0, 2, 2, 3, 1, 1, 1, 2, 2, 0, 0, 2, 3, 1, 1, 1, 2, 0, 2, 0, 2, 1, 1, -1, 1, 2, 0, 0, 2, 2, 1, 1, -1, 1, 2, 2, 0, 0, 2, 1, -1, 1, 1, 2, 0, 0, 2, 2, 1, -1, 1, 1, 2, 0, 2, 0, 2, 1, 1, 1, -1, 2, 2, 0, 0, 2, 1, 1, 1, -1, 2, 0, 2, 0 };
		int[] lookupPairs3D = new int[] { 0, 2, 1, 1, 2, 2, 5, 1, 6, 0, 7, 0, 32, 2, 34, 2, 129, 1, 133, 1, 160, 5, 161, 5, 518, 0, 519, 0, 546, 4, 550, 4, 645, 3, 647, 3, 672, 5, 673, 5, 674, 4, 677, 3, 678, 4, 679, 3, 680, 13, 681, 13, 682, 12, 685, 14, 686, 12, 687, 14, 712, 20, 714, 18, 809, 21, 813, 23, 840, 20, 841, 21, 1198, 19, 1199, 22, 1226, 18, 1230, 19, 1325, 23, 1327, 22, 1352, 15, 1353, 17, 1354, 15, 1357, 17, 1358, 16, 1359, 16, 1360, 11, 1361, 10, 1362, 11, 1365, 10, 1366, 9, 1367, 9, 1392, 11, 1394, 11, 1489, 10, 1493, 10, 1520, 8, 1521, 8, 1878, 9, 1879, 9, 1906, 7, 1910, 7, 2005, 6, 2007, 6, 2032, 8, 2033, 8, 2034, 7, 2037, 6, 2038, 7, 2039, 6 };

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
		
		lookup3D = new Contribution3[2048];
		for (int i = 0; i < lookupPairs3D.length; i += 2) {
			lookup3D[lookupPairs3D[i]] = contributions3D[lookupPairs3D[i + 1]];
		}
	}
	
	//2D Gradients -- new scheme (Dodecagon)
	private static double[] gradients2D = new double[] {
	   0.114251372530929,   0.065963060686016,
	   0.131926121372032,   0.000000000000000,
	   0.114251372530929,  -0.065963060686016,
	   0.065963060686016,  -0.114251372530929,
	   0.000000000000000,  -0.131926121372032,
	  -0.065963060686016,  -0.114251372530929,
	  -0.114251372530929,  -0.065963060686016,
	  -0.131926121372032,  -0.000000000000000,
	  -0.114251372530929,   0.065963060686016,
	  -0.065963060686016,   0.114251372530929,
	  -0.000000000000000,   0.131926121372032,
	   0.065963060686016,   0.114251372530929,
	};
	
	//3D Gradients (Stretched Rhombicuboctahedron)
	private static double[] gradients3D = new double[] {
		-0.106796116504854,		0.0388349514563107,		0.0388349514563107,
		-0.0388349514563107,	0.106796116504854,		0.0388349514563107,
		-0.0388349514563107,	0.0388349514563107,		0.106796116504854,
		0.106796116504854,		0.0388349514563107,		0.0388349514563107,
		0.0388349514563107,		0.106796116504854,		0.0388349514563107,
		0.0388349514563107,		0.0388349514563107,		0.106796116504854,
		-0.106796116504854,		-0.0388349514563107,	0.0388349514563107,
		-0.0388349514563107,	-0.106796116504854,		0.0388349514563107,
		-0.0388349514563107,	-0.0388349514563107,	0.106796116504854,
		0.106796116504854,		-0.0388349514563107,	0.0388349514563107,
		0.0388349514563107,		-0.106796116504854,		0.0388349514563107,
		0.0388349514563107,		-0.0388349514563107,	0.106796116504854,
		-0.106796116504854,		0.0388349514563107,		-0.0388349514563107,
		-0.0388349514563107,	0.106796116504854,		-0.0388349514563107,
		-0.0388349514563107,	0.0388349514563107,		-0.106796116504854,
		0.106796116504854,		0.0388349514563107,		-0.0388349514563107,
		0.0388349514563107,		0.106796116504854,		-0.0388349514563107,
		0.0388349514563107,		0.0388349514563107,		-0.106796116504854,
		-0.106796116504854,		-0.0388349514563107,	-0.0388349514563107,
		-0.0388349514563107,	-0.106796116504854,		-0.0388349514563107,
		-0.0388349514563107,	-0.0388349514563107,	-0.106796116504854,
		0.106796116504854,		-0.0388349514563107,	-0.0388349514563107,
		0.0388349514563107,		-0.106796116504854,		-0.0388349514563107,
		0.0388349514563107,		-0.0388349514563107,	-0.106796116504854,
	};

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

}