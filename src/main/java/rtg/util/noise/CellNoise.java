package rtg.util.noise;

public interface CellNoise {
    float noise(double x, double z, double depth);

    CellOctave octave(int index);

    CellOctave river();
}
