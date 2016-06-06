package teamrtg.rtg.api.util.math;

public class TerrainMath {
    public static double nextX(double x, double d, double s) {
        return x + (s * Math.cos(d * Math.PI / 180.0));
    }

    public static double nextY(double y, double d, double s) {
        return y + (s * Math.sin(d * Math.PI / 180.0));
    }

    public static double dis1(double n1, double n2) {
        return Math.sqrt((n1 - n2) * (n1 - n2));
    }

    public static double dis2(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double dis3(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
    }

    public static double dirToPoint2(double x1, double y1, double x2, double y2) {
        return Math.atan2((y2 - y1), (x2 - x1)) * 180 / Math.PI;
    }
}