package rtg.api.util.noise;

public interface ISimplexData2D
{
    void setDeltaX(double deltaX);
    void setDeltaY(double deltaY);
    void addToDeltaX(double val);
    void addToDeltaY(double val);
    double getDeltaX();
    double getDeltaY();
    void clear();

    DataRequest request();

    @FunctionalInterface
    interface DataRequest {
        void apply(double attn, double extrapolation, double gx, double gy, int gi_sph2, double dx, double dy);
    }
}
