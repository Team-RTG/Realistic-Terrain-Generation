package rtg.util;

import rtg.api.config.Config;
import rtg.api.util.ISupportedMod;
import rtg.api.util.ModPresenceTester;
import rtg.config.ConfigRTG;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;

/**
 * Holds all the mods that RTG implements explicit support for
 * Provides access to modId, presence and configuration
 * Allways pass and store as ISupportedMod
 *
 * @author topisani
 */
public enum SupportedMod implements ISupportedMod {
    VANILLA("Vanilla") {
        @Override
        public boolean isPresent() {
            return true;
        }

        @Override
        public void addBiomes() {
            RealisticBiomeVanillaBase.addBiomes();
        }
    },
    RTG("RTG") {
        private ConfigRTG config;

        @Override
        public void init() {
            this.present = true;
            config = new ConfigRTG();
        }

        @Override
        public ConfigRTG getConfig() {
            return config;
        }
    },
    BOP("BiomesOPlenty"),
    THAUMCRAFT("Thaumcraft"),
    BUILDCRAFT("Buildcraft"),
    ABYSSALCRAFT("abyssalcraft");

    protected boolean present;
    protected final String modId;
    protected boolean hasConfig;
    protected Config.ModConfig config;

    SupportedMod(String modId) {
        this(modId, true);
    }

    SupportedMod(String modId, boolean hasConfig) {
        this.modId = modId;
        this.hasConfig = hasConfig;
    }

    @Override
    public void init() {
        present = new ModPresenceTester(modId).present();
        this.config = this.hasConfig ? new Config.ModConfig(this) : null;
    }

    @Override
    public boolean isPresent() {
        return present;
    }

    @Override
    public Config.ModConfig getConfig() {
        return config;
    }

    @Override
    public String getModId() {
        return modId;
    }

    @Override
    public void addBiomes() {}

    public static void initAll(ISupportedMod[] mods) {
        for (ISupportedMod mod : mods) {
            mod.init();
        }
    }

    public static void addAllBiomes(ISupportedMod[] mods) {
        for (ISupportedMod mod : mods) {
            mod.addBiomes();
        }
    }
}
