package rtg.util;

import rtg.api.biome.ISupportedMod;
import rtg.config.ModConfig;

/**
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
        @Override
        public boolean isPresent() {
            return true;
        }
    },
    BOP("BiomesOPlenty"),
    ABYSSALCRAFT("abyssalcraft");

    private final boolean present;
    private final String modId;
    private ModConfig config;

    SupportedMod(String modId) {
        this.modId = modId;
        present = new ModPresenceTester(modId).present();
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
