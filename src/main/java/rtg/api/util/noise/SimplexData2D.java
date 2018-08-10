package rtg.api.util.noise;

import rtg.api.util.MathUtils;


public abstract class SimplexData2D implements ISimplexData2D {

    private double deltaX;
    private double deltaY;

    private SimplexData2D() {
        this.clear();
    }

    /**
     * Gets a new {@link SimplexData2D.Disk} multi-evaluation data object for use in generating jitter effects.
     *
     * @return a new instance of SimplexData2D.Disk
     * @since 1.0.0
     */
    public static ISimplexData2D newDisk() {
        return new Disk();
    }

    /**
     * Gets a new {@link SimplexData2D.Derivative} multi-evaluation data object for use in generating jitter effects.
     *
     * @return a new instance of SimplexData2D.Derivative
     * @since 1.0.0
     */
    public static ISimplexData2D newDerivative() {
        return new Derivative();
    }

    @Override
    public final double getDeltaX() {
        return this.deltaX;
    }

    @Override
    public final void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    @Override
    public final double getDeltaY() {
        return this.deltaY;
    }

    @Override
    public final void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    @Override
    public final void addToDeltaX(double val) {
        this.deltaX += val;
    }

    @Override
    public final void addToDeltaY(double val) {
        this.deltaY += val;
    }

    @Override
    public final void clear() {
        this.setDeltaX(0.0d);
        this.setDeltaY(0.0d);
    }

    public static final class Disk extends SimplexData2D implements ISimplexData2D {

        private Disk() {
            super();
        }

        @Override
        public DataRequest request() {
            return (double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy) -> {
                double attnSq = MathUtils.pow2(attn);
                double extrap = MathUtils.pow2(attnSq) * extrapolation;
                this.addToDeltaX(extrap * OpenSimplexNoise.GRADIENTS_SPH2[gi_sph2]);
                this.addToDeltaY(extrap * OpenSimplexNoise.GRADIENTS_SPH2[gi_sph2 + 1]);
            };
        }
    }

    public static final class Derivative extends SimplexData2D implements ISimplexData2D {

        private Derivative() {
            super();
        }

        @Override
        public DataRequest request() {
            return (double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy) -> {
                double attnSq = MathUtils.pow2(attn);
                this.addToDeltaX((gx * attn - 8 * dx * extrapolation) * attnSq * attn);
                this.addToDeltaY((gy * attn - 8 * dy * extrapolation) * attnSq * attn);
            };
        }
    }
}
