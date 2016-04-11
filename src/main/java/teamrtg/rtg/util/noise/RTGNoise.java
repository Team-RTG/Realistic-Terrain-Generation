package teamrtg.rtg.util.noise;

import java.util.ArrayList;

/**
 * An object that holds multiple octaves of OpenSimplex noise, and can get the result at any point.
 * @author topisani
 */
public class RTGNoise implements IFloatAt {

    private final OpenSimplexNoise simplex;
    public static final RTGNoise EMPTY = new RTGNoise(0);
    public static final RTGNoise EMPTY_TRUE = new RTGNoise(0).setRange(0f, 0f, RangeType.BOOL);

    private ArrayList<Octave> octaves = new ArrayList<>();
    private float min;
    private float max;
    private RangeType rangeType = RangeType.NONE;

    /**
     * BOOL returns 1 if the noise is inside the range, and 0 if not.
     * CROP cuts off any excess values above or below the range.
     */
    public enum RangeType {
        BOOL,
        CROP,
        NONE;
    }

    public RTGNoise(long seed) {
        this.simplex = new OpenSimplexNoise(seed);
    }

    public RTGNoise(OpenSimplexNoise simplex) {
        this.simplex = simplex;
    }

    /**
     * Returns the resulting noise of all the octaves at a point.
     */
    public float getAt(float x, float y, float z) {
        float result = 0f;
        for (Octave octave : octaves) {
            result += octave.getAt(x, y, z);
        }
        switch (rangeType) {
            case BOOL:
                result = (result >= min && result <= max) ? 1f : 0f;
                break;
            case CROP:
                result = (result < min) ? min : (result > max) ? max : result;
                break;
            default:
                break;
        }
        return result;
    }

    public boolean getBoolAt(float x, float y, float z) {
        return getAt(x, y, z) == 1f;
    }

    public RTGNoise setRange(float min, float max, RangeType rangeType) {
        this.min = min;
        this.max = max;
        this.rangeType = rangeType;
        return this;
    }

    public RTGNoise addOctave(Octave octave) {
        this.octaves.add(octave);
        return this;
    }

    public RTGNoise addOctave1D(float divisor, float factor, float addend) {
        this.octaves.add(new Octave1D(divisor, factor, addend));
        return this;
    }

    public RTGNoise addOctave2D(float divisor, float factor, float addend) {
        this.octaves.add(new Octave2D(divisor, factor, addend));
        return this;
    }

    public RTGNoise addOctave3D(float divisorXZ, float divisorY, float factor, float addend) {
        this.octaves.add(new Octave3D(divisorXZ, divisorY, factor, addend));
        return this;
    }

    public abstract static class Octave {
        protected float divisorXZ;
        protected float divisorY;
        protected float factor;
        protected float addend;

        public abstract float getAt(float x, float y, float z);
    }

    public class Octave1D extends Octave {
        public Octave1D(float divisor, float factor, float addend) {
            this.divisorXZ = divisor;
            this.factor = factor;
            this.addend = addend;
        }

        @Override
        public float getAt(float x, float y, float z) {
            return RTGNoise.this.simplex.noise1(x / divisorXZ) * factor + addend;
        }
    }

    public class Octave2D extends Octave {

        public Octave2D(float divisor, float factor, float addend) {
            this.divisorXZ = divisor;
            this.factor = factor;
            this.addend = addend;
        }

        @Override
        public float getAt(float x, float y, float z) {
            return RTGNoise.this.simplex.noise2(x / divisorXZ, z / divisorXZ) * factor + addend;
        }
    }

    public class Octave3D extends Octave {

        public Octave3D(float divisor, float factor, float addend) {
            this.divisorXZ = divisor;
            this.divisorY = divisor;
            this.factor = factor;
            this.addend = addend;
        }

        public Octave3D(float divisorXZ, float divisorY, float factor, float addend) {
            this.divisorXZ = divisorXZ;
            this.divisorY = divisorY;
            this.factor = factor;
            this.addend = addend;
        }

        @Override
        public float getAt(float x, float y, float z) {
            return RTGNoise.this.simplex.noise3(x / divisorXZ, y / divisorY, z / divisorXZ) * factor + addend;
        }
    }
}
