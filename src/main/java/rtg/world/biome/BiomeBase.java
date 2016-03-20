package rtg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase {

    public BiomeBase(int biomeID) {

        super((new BiomePropertiesRTG(BiomeGenBase.getBiomeForId(biomeID).getBiomeName()))
                .setRainfall(BiomeGenBase.getBiomeForId(biomeID).getRainfall())
                .setTemperature(BiomeGenBase.getBiomeForId(biomeID).getTemperature()));

    }

    public static class BiomePropertiesRTG extends BiomeGenBase.BiomeProperties {
        private final String biomeName;
        private float baseHeight = 0.1F;
        private float heightVariation = 0.2F;
        private float temperature = 0.5F;
        private float rainfall = 0.5F;
        private int waterColor = 16777215;
        private boolean enableSnow;
        private boolean enableRain = true;
        private String baseBiomeRegName;

        public BiomePropertiesRTG(String nameIn) {
            super(nameIn);
            this.biomeName = nameIn;
        }

        protected BiomePropertiesRTG setTemperature(float temperatureIn) {
            if (temperatureIn > 0.1F && temperatureIn < 0.2F) {
                throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
            } else {
                this.temperature = temperatureIn;
                return this;
            }
        }

        protected BiomePropertiesRTG setRainfall(float rainfallIn) {
            this.rainfall = rainfallIn;
            return this;
        }

        protected BiomePropertiesRTG setBaseHeight(float baseHeightIn) {
            this.baseHeight = baseHeightIn;
            return this;
        }

        protected BiomePropertiesRTG setHeightVariation(float heightVariationIn) {
            this.heightVariation = heightVariationIn;
            return this;
        }

        protected BiomePropertiesRTG setRainDisabled() {
            this.enableRain = false;
            return this;
        }

        protected BiomePropertiesRTG setSnowEnabled() {
            this.enableSnow = true;
            return this;
        }

        protected BiomePropertiesRTG setWaterColor(int waterColorIn) {
            this.waterColor = waterColorIn;
            return this;
        }

        protected BiomePropertiesRTG setBaseBiome(String nameIn) {
            this.baseBiomeRegName = nameIn;
            return this;
        }
    }
}
