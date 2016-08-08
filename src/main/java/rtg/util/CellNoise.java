package rtg.util;
public interface CellNoise {
    public float noise(double x, double z,double depth) ;
    public CellOctave octave(int index);
    public CellOctave river();
}
