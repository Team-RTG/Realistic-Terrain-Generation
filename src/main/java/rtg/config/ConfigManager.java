package rtg.config;

import java.io.File;

import rtg.config.abyssalcraft.ConfigAC;
import rtg.config.agriculturalrevolution.ConfigAR;
import rtg.config.betteragriculture.ConfigBA;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.config.biomesyougo.ConfigBYG;
import rtg.config.flowercraft.ConfigFC;
import rtg.config.mineworld.ConfigMW;
import rtg.config.mithwoodforest.ConfigMF;
import rtg.config.morechinesemc.ConfigMCM;
import rtg.config.rtg.ConfigRTG;
import rtg.config.sugiforest.ConfigSF;
import rtg.config.vanilla.ConfigVanilla;

public class ConfigManager {

    public static File rtgConfigFile;
    public static File vanillaConfigFile;
    public static File acConfigFile;
    public static File arConfigFile;
    public static File baConfigFile;
    public static File bopConfigFile;
    public static File bygConfigFile;
    public static File fcConfigFile;
    public static File mcmConfigFile;
    public static File mfConfigFile;
    public static File mwConfigFile;
    public static File sfConfigFile;

    private ConfigRTG configRTG = new ConfigRTG();

    public static void init(String configpath) {

        rtgConfigFile = new File(configpath + "rtg.cfg");
        vanillaConfigFile = new File(configpath + "biomes/vanilla.cfg");
        acConfigFile = new File(configpath + "biomes/abyssalcraft.cfg");
        arConfigFile = new File(configpath + "biomes/agriculturalrevolution.cfg");
        baConfigFile = new File(configpath + "biomes/betteragriculture.cfg");
        bopConfigFile = new File(configpath + "biomes/biomesoplenty.cfg");
        bygConfigFile = new File(configpath + "biomes/biomesyougo.cfg");
        fcConfigFile = new File(configpath + "biomes/flowercraft.cfg");
        mcmConfigFile = new File(configpath + "biomes/morechinesemc.cfg");
        mfConfigFile = new File(configpath + "biomes/mithwoodforest.cfg");
        mwConfigFile = new File(configpath + "biomes/mineworld.cfg");
        sfConfigFile = new File(configpath + "biomes/sugiforest.cfg");

        ConfigRTG.init(rtgConfigFile);

        ConfigVanilla.init(vanillaConfigFile);

        ConfigAC.init(acConfigFile);
        ConfigAR.init(arConfigFile);
        ConfigBA.init(baConfigFile);
        ConfigBOP.init(bopConfigFile);
        ConfigBYG.init(bygConfigFile);
        ConfigFC.init(fcConfigFile);
        ConfigMCM.init(mcmConfigFile);
        ConfigMF.init(mfConfigFile);
        ConfigMW.init(mwConfigFile);
        ConfigSF.init(sfConfigFile);
    }

    public ConfigRTG rtg() {

        return configRTG;
    }
}
