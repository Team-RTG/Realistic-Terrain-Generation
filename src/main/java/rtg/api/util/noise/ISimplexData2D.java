package rtg.api.util.noise;

public interface ISimplexData2D {

    void addToDeltaX(double val);

    void addToDeltaY(double val);

    double getDeltaX();

    void setDeltaX(double deltaX);

    double getDeltaY();

    void setDeltaY(double deltaY);

    void clear();

    DataRequest request();

    @FunctionalInterface
    interface DataRequest {

        void apply(double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy);
    }
}
