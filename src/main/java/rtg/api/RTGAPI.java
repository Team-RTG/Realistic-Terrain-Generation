package rtg.api;

import java.nio.file.Path;

import rtg.api.config.RTGConfig;

public class RTGAPI {

    public static final String NAME = "rtgapi";
    public static final String OWNER = "rtg";
    public static final String VERSION = "1.0.0";
    public static RTGConfig rtgConfig;
    public static Path configPath;

    public static RTGConfig config() {
        return rtgConfig;
    }
}
