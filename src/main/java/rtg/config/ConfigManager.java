package rtg.config;

import rtg.config.rtg.ConfigRTG;

public class ConfigManager {

    public static ModConfig rtgConfig;
    public static ModConfig vanillaConfig;
    public static ModConfig bopConfig;
    public static ModConfig thaumConfig;
    public static ModConfig buildConfig;
    public static ModConfig abyssalConfig;

    private ConfigRTG configRTG = new ConfigRTG();

    public static void init(String configpath) {

        rtgConfig = new ConfigRTG();
        vanillaConfig = new ModConfig("Vanilla");
        bopConfig = new ModConfig("BiomesOPlenty");
        thaumConfig = new ModConfig("Thaumcraft");
        buildConfig = new ModConfig("BuildCraft");
        abyssalConfig = new ModConfig("Abyssalcraft");
    }

    public ConfigRTG rtg() {
        return configRTG;
    }
}
