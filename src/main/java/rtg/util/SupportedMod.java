package rtg.util;

import rtg.api.config.ISupportedMod;
import rtg.config.ModConfig;
import rtg.config.rtg.ConfigRTG;

/**
 * Holds all the mods that RTG implements explicit support for
 * Provides access to modId, presence and configuration
 * Allways pass and store as ISupportedMod
 * @author topisani
 */
public enum SupportedMod implements ISupportedMod {
    VANILLA("Vanilla") {
        @Override
        public boolean isPresent() {
            return true;
        }
    },
    RTG("RTG") {
        private final ConfigRTG config = new ConfigRTG();

        @Override
        public boolean isPresent() {
            return true;
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

    private final boolean present;
    private final String modId;
    private final ModConfig config;

    SupportedMod(String modId) {
        this(modId, true);
    }

    SupportedMod(String modId, boolean hasConfig) {
        this.modId = modId;
        present = new ModPresenceTester(modId).present();
        this.config = hasConfig ? new ModConfig(this) : null;
    }

    @Override
    public boolean isPresent() {
        return present;
    }

    @Override
    public ModConfig getConfig() {
        return config;
    }

    @Override
    public String getModId() {
        return modId;
    }
}
