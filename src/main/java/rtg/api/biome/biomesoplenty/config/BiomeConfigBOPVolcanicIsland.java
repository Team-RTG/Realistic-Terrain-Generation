package rtg.api.biome.biomesoplenty.config;


public class BiomeConfigBOPVolcanicIsland extends BiomeConfigBOPBase {

    public BiomeConfigBOPVolcanicIsland() {

        super("volcanicisland");

        this.setPropertyValueById(allowVolcanoesId, true);
        this.setPropertyValueById(volcanoChanceId, -1);
    }
}
