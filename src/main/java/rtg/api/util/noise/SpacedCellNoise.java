
package rtg.api.util.noise;

/**
 *
 * @author Zeno410
 */

public class SpacedCellNoise implements CellNoise
{

    private SpacedCellOctave[] octaves = new SpacedCellOctave [5];
    public final int OCTAVE_COUNT = 5;

    public SpacedCellNoise(long seed) {
        for (int i = 0; i < 5; i++) {
            octaves[i]= new SpacedCellOctave(seed+i,(short)0,true);
        }
    }

    public SpacedCellOctave octave(int index) {
        return octaves[index];
    }

    public SpacedCellOctave river() {return octave(0);}

    public float noise(double x, double z, double depth) {
        return river().noise(x, z, depth);
    }


}
