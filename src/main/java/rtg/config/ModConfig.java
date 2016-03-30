package rtg.config;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFU;
import rtg.util.Logger;

import java.io.File;

public class ModConfig {
    public final Configuration config;
    public final String name;
    public final boolean hasBiomeConfigs;

    public ModConfig(String name) {
        this(name, true);
    }

    public ModConfig(String name, boolean hasBiomeConfigs) {
        this.config = new Configuration(new File("biomes/" + name.toLowerCase() + ".cfg"));
        this.name = name;
        this.hasBiomeConfigs = hasBiomeConfigs;

        try {
            config.load();

            this.setDefaults();

            if (hasBiomeConfigs) BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigTOFU.getBiomeConfigs(), config);
        } catch (Exception e) {
            Logger.error("RTG has had a problem loading " + name + " configuration.");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public void setDefaults() {}
}
