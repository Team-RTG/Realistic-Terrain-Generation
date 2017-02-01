package rtg.config;

import java.io.File;

import rtg.config.abyssalcraft.ConfigAC;
import rtg.config.afraidofthedark.ConfigAOTD;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.config.buildcraft.ConfigBC;
import rtg.config.flowercraft.ConfigFC;
import rtg.config.highlands.ConfigHL;
import rtg.config.minestrappolation.ConfigMS;
import rtg.config.rtg.ConfigRTG;
import rtg.config.thaumcraft.ConfigTC;
import rtg.config.vanilla.ConfigVanilla;

public class ConfigManager {

    public static File rtgConfigFile;
    public static File vanillaConfigFile;
    public static File bopConfigFile;
    public static File tcConfigFile;
    public static File bcConfigFile;
    public static File hlConfigFile;
    public static File acConfigFile;
    public static File aotdConfigFile;
    public static File msConfigFile;
    public static File fcConfigFile;

    private ConfigRTG configRTG = new ConfigRTG();

    public static void init(String configpath) {

        rtgConfigFile = new File(configpath + "rtg.cfg");
        vanillaConfigFile = new File(configpath + "biomes/vanilla.cfg");
        bopConfigFile = new File(configpath + "biomes/biomesoplenty.cfg");
        tcConfigFile = new File(configpath + "biomes/thaumcraft.cfg");
        bcConfigFile = new File(configpath + "biomes/buildcraft.cfg");
        hlConfigFile = new File(configpath + "biomes/highlands.cfg");
        acConfigFile = new File(configpath + "biomes/abyssalcraft.cfg");
        aotdConfigFile = new File(configpath + "biomes/afraidofthedark.cfg");
        msConfigFile = new File(configpath + "biomes/minestrappolation.cfg");
        fcConfigFile = new File(configpath + "biomes/flowercraft.cfg");

        ConfigRTG.init(rtgConfigFile);

        ConfigVanilla.init(vanillaConfigFile);

        ConfigBOP.init(bopConfigFile);
        ConfigTC.init(tcConfigFile);
        ConfigBC.init(bcConfigFile);
        ConfigHL.init(hlConfigFile);
        ConfigAC.init(acConfigFile);
        ConfigAOTD.init(aotdConfigFile);
        ConfigMS.init(msConfigFile);
        ConfigFC.init(fcConfigFile);
    }

    public ConfigRTG rtg() {

        return configRTG;
    }
}
