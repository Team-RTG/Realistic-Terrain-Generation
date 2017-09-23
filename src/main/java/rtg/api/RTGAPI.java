package rtg.api;

import rtg.api.config.RTGConfig;

public class RTGAPI {

    public static final String NAME = "rtgapi";
    public static final String OWNER = "rtg";
    public static final String VERSION = "1.0.0";
    public static RTGConfig rtgConfig;
    public static String configPath;

    /*
     * This method is currently unused, but we're leaving it here for when we start
     * supporting multiple dimensions.
     */
    public static RTGConfig config(int dimension) {
        return rtgConfig;
    }

    public static RTGConfig config() {
        return config(0);
    }
}
