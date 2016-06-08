package teamrtg.rtg.api.util.noise;

/**
 * @author KdotJPG
 *         <p>
 *         Generates 2D Simplex-cellular noise.
 *         <p>
 *         Simplex-cellular noise is cellular noise implemented using the lattice
 *         of Simplex noise.
 *         <p>
 *         In this case the point contribution determination is implemented using
 *         a lookup scheme inspired by DigitalShadow's optimized implementation of
 *         OpenSimplex noise, and a permutation table of size 1024 is used instead
 *         of the traditional 256.
 *         <p>
 *         Each point set is defined as the directions from the center to the
 *         vertices of the normalized expanded vertex figure of the lattice for
 *         the given dimensionality. These point sets are symmetric with the
 *         lattice, but don't include directions that follow edges or facets.
 *         <p>
 *         Supports multi-evaluation with F1 and F2 values.
 *         <p>
 *         Version 02/05/2015
 */

public class SimplexCellularNoise implements CellNoise {

    public final int OCTAVE_COUNT = 5;
    private SimplexCellularOctave[] octaves = new SimplexCellularOctave[5];

    public SimplexCellularNoise(long seed) {
        for (int i = 0; i < 5; i++) {
            octaves[i] = new SimplexCellularOctave(seed + i);
        }
    }

    public float noise(double x, double z, double depth) {
        return river().noise(x, z, depth);
    }

    public SimplexCellularOctave octave(int index) {
        return octaves[index];
    }

    public SimplexCellularOctave river() {return octave(0);}

}
