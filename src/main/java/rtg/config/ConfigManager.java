package rtg.config;

import java.io.File;

import rtg.config.abyssalcraft.ConfigAC;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.config.biomesyougo.ConfigBYG;
import rtg.config.flowercraft.ConfigFC;
import rtg.config.rtg.ConfigRTG;
import rtg.config.sugiforest.ConfigSF;
import rtg.config.vanilla.ConfigVanilla;

public class ConfigManager {

    public static File rtgConfigFile;
    public static File vanillaConfigFile;
    public static File acConfigFile;
    public static File bopConfigFile;
    public static File bygConfigFile;
    public static File fcConfigFile;
    public static File sfConfigFile;

    private ConfigRTG configRTG = new ConfigRTG();

    public static void init(String configpath) {

        rtgConfigFile = new File(configpath + "rtg.cfg");
        vanillaConfigFile = new File(configpath + "biomes/vanilla.cfg");
        acConfigFile = new File(configpath + "biomes/abyssalcraft.cfg");
        bopConfigFile = new File(configpath + "biomes/biomesoplenty.cfg");
        bygConfigFile = new File(configpath + "biomes/biomesyougo.cfg");
        fcConfigFile = new File(configpath + "biomes/flowercraft.cfg");
        sfConfigFile = new File(configpath + "biomes/sugiforest.cfg");

        ConfigRTG.init(rtgConfigFile);

        ConfigVanilla.init(vanillaConfigFile);

        ConfigAC.init(acConfigFile);
        ConfigBOP.init(bopConfigFile);
        ConfigBYG.init(bygConfigFile);
        ConfigFC.init(fcConfigFile);
        ConfigSF.init(sfConfigFile);
    }

    public ConfigRTG rtg() {

        return configRTG;
    }
}
