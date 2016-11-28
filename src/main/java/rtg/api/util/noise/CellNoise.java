package rtg.api.util.noise;

import rtg.util.CellOctave;

public interface CellNoise {
    public float noise(double x, double z,double depth) ;
    public CellOctave octave(int index);
    public CellOctave river();
}
