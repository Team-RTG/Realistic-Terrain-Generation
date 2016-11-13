package rtg.config;

import java.io.File;

import rtg.config.rtg.ConfigRTG;

public class ConfigManager {

    public static File rtgConfigFile;
    private ConfigRTG configRTG = new ConfigRTG();

    public static void init(String configpath) {

        rtgConfigFile = new File(configpath + "rtg.cfg");
        ConfigRTG.init(rtgConfigFile);
    }

    public ConfigRTG rtg() {

        return configRTG;
    }
}
