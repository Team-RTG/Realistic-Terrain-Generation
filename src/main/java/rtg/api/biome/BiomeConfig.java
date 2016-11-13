package rtg.api.biome;

import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.BiomeConfigProperty.Type;
import rtg.world.biome.realistic.RealisticBiomeBase;


public class BiomeConfig {

    public RealisticBiomeBase realisticBiome;
    public ArrayList<BiomeConfigProperty> properties;

    /*
     * GLOBAL CONFIGS
     */
    public static final String allowVillagesId = "allowVillages";
    public static final String allowVillagesName = "Allow Villages";

    public static final String allowVolcanoesId = "allowVolcanoes";
    public static final String allowVolcanoesName = "Allow Volcanoes";

    public static final String volcanoChanceId = "volcanoChance";
    public static final String volcanoChanceName = "Volcano Chance";

    public static final String useRTGDecorationsId = "useRTGDecorations";
    public static final String useRTGDecorationsName = "Use RTG Decorations";

    public static final String useRTGSurfacesId = "useRTGSurfaces";
    public static final String useRTGSurfacesName = "Use RTG Surfaces";

    public static final String surfaceTopBlockId = "surfaceTopBlock";
    public static final String surfaceTopBlockName = "RTG Surface: Top Block";

    public static final String surfaceTopBlockMetaId = "surfaceTopBlockMeta";
    public static final String surfaceTopBlockMetaName = "RTG Surface: Top Block Meta";

    public static final String surfaceFillerBlockId = "surfaceFillerBlock";
    public static final String surfaceFillerBlockName = "RTG Surface: Filler Block";

    public static final String surfaceFillerBlockMetaId = "surfaceFillerBlockMeta";
    public static final String surfaceFillerBlockMetaName = "RTG Surface: Filler Block Meta";

    public static final String caveDensityId = "caveDensity";
    public static final String caveDensityName = "Cave Density";

    public static final String caveFrequencyId = "caveFrequency";
    public static final String caveFrequencyName = "Cave Frequency";

    public static final String ravineFrequencyId = "ravineFrequency";
    public static final String ravineFrequencyName = "Ravine Frequency";

    public static final String beachBiomeId = "beachBiome";
    public static final String beachBiomeName = "Beach Biome";

    public static final String treeDensityMultiplierId = "treeDensityMultiplier";
    public static final String treeDensityMultiplierName = "RTG Tree Density Multiplier";

    /*
     * OPTIONAL CONFIGS
     */

    public static final String decorationLogsId = "decorationLogs";
    public static final String decorationLogsName = "RTG Decoration: Logs";

    public static final String surfaceMixBlockId = "surfaceMixBlock";
    public static final String surfaceMixBlockName = "RTG Surface: Mix Block";

    public static final String surfaceMixBlockMetaId = "surfaceMixBlockMeta";
    public static final String surfaceMixBlockMetaName = "RTG Surface: Mix Block Meta";

    public static final String surfaceMixFillerBlockId = "surfaceMixFillerBlock";
    public static final String surfaceMixFillerBlockName = "RTG Surface: Mix Filler Block";

    public static final String surfaceMixFillerBlockMetaId = "surfaceMixFillerBlockMeta";
    public static final String surfaceMixFillerBlockMetaName = "RTG Surface: Mix Filler Block Meta";

    public static final String decorationPalmTreesId = "decorationPalmTrees";
    public static final String decorationPalmTreesName = "RTG Decoration: Palm Trees";

    public static final String decorationCactusId = "decorationCactus";
    public static final String decorationCactusName = "RTG Decoration: Cactus";

    public static final String decorationCobwebsId = "decorationCobwebs";
    public static final String decorationCobwebsName = "RTG Decoration: Cobwebs";

    public static final String decorationWheatId = "decorationWheat";
    public static final String decorationWheatName = "RTG Decoration: Wheat";

    public static final String decorationWheatChanceId = "decorationWheatChance";
    public static final String decorationWheatChanceName = "RTG Decoration: Wheat (Chance)";

    public static final String decorationWheatMinYId = "decorationWheatMinY";
    public static final String decorationWheatMinYName = "RTG Decoration: Wheat (Min Y)";

    public static final String decorationWheatMaxYId = "decorationWheatMaxY";
    public static final String decorationWheatMaxYName = "RTG Decoration: Wheat (Max Y)";

    public BiomeConfig(RealisticBiomeBase realisticBiome) {

        this.realisticBiome = realisticBiome;
        this.properties = new ArrayList<BiomeConfigProperty>();

        this.addProperty(new BiomeConfigProperty(allowVillagesId, Type.BOOLEAN, allowVillagesName, "", true));

        this.addProperty(new BiomeConfigProperty(allowVolcanoesId, Type.BOOLEAN, allowVolcanoesName, "", false));
        this.addProperty(new BiomeConfigProperty(volcanoChanceId, Type.INTEGER, volcanoChanceName, "1/x chance that a volcano will generate if this biome has volcanoes enabled." + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable volcanoes for this biome." + Configuration.NEW_LINE, -1, -1, Integer.MAX_VALUE));

        this.addProperty(new BiomeConfigProperty(useRTGDecorationsId, Type.BOOLEAN, useRTGDecorationsName, "", true));

        this.addProperty(new BiomeConfigProperty(useRTGSurfacesId, Type.BOOLEAN, useRTGSurfacesName, "", true));
        this.addProperty(new BiomeConfigProperty(surfaceTopBlockId, Type.STRING, surfaceTopBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceTopBlockMetaId, Type.STRING, surfaceTopBlockMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceFillerBlockId, Type.STRING, surfaceFillerBlockName, "", ""));
        this.addProperty(new BiomeConfigProperty(surfaceFillerBlockMetaId, Type.STRING, surfaceFillerBlockMetaName, "", ""));
        this.addProperty(new BiomeConfigProperty(caveDensityId, Type.INTEGER, caveDensityName, "This setting controls the size of caves." + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40));
        this.addProperty(new BiomeConfigProperty(caveFrequencyId, Type.INTEGER, caveFrequencyName, "This setting controls the number of caves that generate." + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40));
        this.addProperty(new BiomeConfigProperty(ravineFrequencyId, Type.INTEGER, ravineFrequencyName, "This setting controls the number of ravines that generate." + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable ravines for this biome." + Configuration.NEW_LINE, -1, -1, 100));

        this.addProperty(new BiomeConfigProperty(
            beachBiomeId, Type.INTEGER, beachBiomeName,
            "Biome ID to use for this biome's beach."
                + Configuration.NEW_LINE
                + "The only 'officially supported' values for this setting are:"
                + Configuration.NEW_LINE
                + "-1 = Automatic beach generation (RECOMMENDED)"
                + Configuration.NEW_LINE
                + "16 = Vanilla Beach"
                + Configuration.NEW_LINE
                + "26 = Vanilla Cold Beach"
                + Configuration.NEW_LINE
                + "25 = Vanilla Stone Beach"
                + Configuration.NEW_LINE
                + "The ID of this biome = No beach"
                + Configuration.NEW_LINE
                + "Other biome IDs are allowed, but they have not been tested, may yield undesirable results, and will not be supported."
                + Configuration.NEW_LINE
                + "Note: If this biome has been hardcoded by RTG to use a specific beach, this setting will have no effect."
                + Configuration.NEW_LINE,
            -1, -1, 255
        ));

        this.addProperty(new BiomeConfigProperty(
            treeDensityMultiplierId,
            Type.FLOAT,
            treeDensityMultiplierName,
            "This setting allows you to increase/decrease the number of RTG trees that generate in this biome."
                + Configuration.NEW_LINE +
                "This setting overrides the global setting (see /.minecraft/config/RTG/rtg.cfg) and only affects trees generated by RTG."
                + Configuration.NEW_LINE +
                "Trees generated by this biome's decorator will adhere to their own density rules."
                + Configuration.NEW_LINE +
                "Set to -1.0 to use the global setting."
                + Configuration.NEW_LINE +
                "1.0 = Default tree generation; 2.0 = Twice as many trees; 0.5 = half as many trees; 0 = No trees"
                + Configuration.NEW_LINE,
            -1.0f, -1.0f, 5.0f
        ));
    }

    public void addProperty(BiomeConfigProperty property) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(property.id)) {
                removeProperty(property.id);
                break;
            }
        }

        this.properties.add(property);
    }

    public void removeProperty(String id) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id)) {
                this.properties.remove(i);
                return;
            }
        }
    }

    public ArrayList<BiomeConfigProperty> getProperties() {

        return this.properties;
    }

    public BiomeConfigProperty getPropertyById(String id) {

        for (int i = 0; i < this.properties.size(); i++) {

            if (this.properties.get(i).id.contentEquals(id)) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public void setPropertyValueById(String id, boolean value) {

        getPropertyById(id).valueBoolean = value;
    }

    public void setPropertyValueById(String id, int value) {

        getPropertyById(id).valueInt = value;
    }

    public void setPropertyValueById(String id, float value) {

        getPropertyById(id).valueFloat = value;
    }

    public void setPropertyValueById(String id, String value) {

        getPropertyById(id).valueString = value;
    }

    public boolean _boolean(String id) {

        try {

            return getPropertyById(id).valueBoolean;
        }
        catch (Exception e) {

            throw new RuntimeException("Biome config property (" + realisticBiome.modSlug() + "." + realisticBiome.biomeSlug() + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public int _int(String id) {

        try {

            return getPropertyById(id).valueInt;
        }
        catch (Exception e) {

            throw new RuntimeException("Biome config property (" + realisticBiome.modSlug() + "." + realisticBiome.biomeSlug() + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public float _float(String id) {

        try {

            return getPropertyById(id).valueFloat;
        }
        catch (Exception e) {

            throw new RuntimeException("Biome config property (" + realisticBiome.modSlug() + "." + realisticBiome.biomeSlug() + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public String _string(String id) {

        try {

            return getPropertyById(id).valueString;
        }
        catch (Exception e) {

            throw new RuntimeException("Biome config property (" + realisticBiome.modSlug() + "." + realisticBiome.biomeSlug() + "." + id + ") could not be found. Reason: " + e.getMessage());
        }
    }

    public static String formatSlug(String s) {

        s = s.toLowerCase();
        s = s.replaceAll("\\+", "plus");
        s = s.replaceAll("\\W", "");

        return s;
    }
}
