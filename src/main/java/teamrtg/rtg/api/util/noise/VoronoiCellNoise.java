package teamrtg.rtg.api.util.noise;

/**
 * @author Zeno410
 */
public class VoronoiCellNoise implements CellNoise {

    public final int OCTAVE_COUNT = 5;
    private VoronoiCellOctave[] octaves = new VoronoiCellOctave[5];

    public VoronoiCellNoise(long seed) {
        for (int i = 0; i < 5; i++) {
            octaves[i] = new VoronoiCellOctave(seed + i, (short) 0, true);
        }
    }

    public float noise(double x, double z, double depth) {
        return river().noise(x, z, depth);
    }

    public VoronoiCellOctave octave(int index) {
        return octaves[index];
    }

    public VoronoiCellOctave river() {return octave(0);}


}