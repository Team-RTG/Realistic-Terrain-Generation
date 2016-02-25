package rtg.config;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.VillageConfig;
import rtg.api.biome.vanilla.config.VillageConfigVanilla;

import java.util.ArrayList;

/**
 * Created by topisani on 2/23/16.
 */
public class VillageConfigManager {

    public static void initVillageConfigs()
    {
        initVillageConfigsVanilla();
    }

    public static void initVillageConfigsVanilla()
    {
        VillageConfigVanilla.villageConfigVanillaBeach = new VillageConfig( "vanilla", "vanillaBeach");
        VillageConfigVanilla.villageConfigVanillaBirchForest = new VillageConfig( "vanilla", "vanillaBirchForest");
        VillageConfigVanilla.villageConfigVanillaBirchForestHills = new VillageConfig( "vanilla", "vanillaBirchForestHills");
        VillageConfigVanilla.villageConfigVanillaColdBeach = new VillageConfig( "vanilla", "vanillaColdBeach");
        VillageConfigVanilla.villageConfigVanillaColdTaiga = new VillageConfig( "vanilla", "vanillaColdTaiga");
        VillageConfigVanilla.villageConfigVanillaColdTaigaHills = new VillageConfig( "vanilla", "vanillaColdTaigaHills");
        VillageConfigVanilla.villageConfigVanillaDeepOcean = new VillageConfig( "vanilla", "vanillaDeepOcean");
        VillageConfigVanilla.villageConfigVanillaDesert = new VillageConfig( "vanilla", "vanillaDesert");
        VillageConfigVanilla.villageConfigVanillaDesertHills = new VillageConfig( "vanilla", "vanillaDesertHills");
        VillageConfigVanilla.villageConfigVanillaExtremeHills = new VillageConfig( "vanilla", "vanillaExtremeHills");
        VillageConfigVanilla.villageConfigVanillaExtremeHillsEdge = new VillageConfig( "vanilla", "vanillaExtremeHillsEdge");
        VillageConfigVanilla.villageConfigVanillaExtremeHillsPlus = new VillageConfig( "vanilla", "vanillaExtremeHillsPlus");
        VillageConfigVanilla.villageConfigVanillaForest = new VillageConfig( "vanilla", "vanillaForest");
        VillageConfigVanilla.villageConfigVanillaForestHills = new VillageConfig( "vanilla", "vanillaForestHills");
        VillageConfigVanilla.villageConfigVanillaFrozenOcean = new VillageConfig( "vanilla", "vanillaFrozenOcean");
        VillageConfigVanilla.villageConfigVanillaFrozenRiver = new VillageConfig( "vanilla", "vanillaFrozenRiver");
        VillageConfigVanilla.villageConfigVanillaIcePlains = new VillageConfig( "vanilla", "vanillaIcePlains");
        VillageConfigVanilla.villageConfigVanillaIceMountains = new VillageConfig( "vanilla", "vanillaIceMountains");
        VillageConfigVanilla.villageConfigVanillaJungle = new VillageConfig( "vanilla", "vanillaJungle");
        VillageConfigVanilla.villageConfigVanillaJungleEdge = new VillageConfig( "vanilla", "vanillaJungleEdge");
        VillageConfigVanilla.villageConfigVanillaJungleHills = new VillageConfig( "vanilla", "vanillaJungleHills");
        VillageConfigVanilla.villageConfigVanillaMegaTaiga = new VillageConfig( "vanilla", "vanillaMegaTaiga");
        VillageConfigVanilla.villageConfigVanillaMegaTaigaHills = new VillageConfig( "vanilla", "vanillaMegaTaigaHills");
        VillageConfigVanilla.villageConfigVanillaMesa = new VillageConfig( "vanilla", "vanillaMesa");
        VillageConfigVanilla.villageConfigVanillaMesaPlateau = new VillageConfig( "vanilla", "vanillaMesaPlateau");
        VillageConfigVanilla.villageConfigVanillaMesaPlateauF = new VillageConfig( "vanilla", "vanillaMesaPlateauF");
        VillageConfigVanilla.villageConfigVanillaMushroomIsland = new VillageConfig( "vanilla", "vanillaMushroomIsland");
        VillageConfigVanilla.villageConfigVanillaMushroomIslandShore = new VillageConfig( "vanilla", "vanillaMushroomIslandShore");
        VillageConfigVanilla.villageConfigVanillaOcean = new VillageConfig( "vanilla", "vanillaOcean");
        VillageConfigVanilla.villageConfigVanillaPlains = new VillageConfig( "vanilla", "vanillaPlains");
        VillageConfigVanilla.villageConfigVanillaRiver = new VillageConfig( "vanilla", "vanillaRiver");
        VillageConfigVanilla.villageConfigVanillaRoofedForest = new VillageConfig( "vanilla", "vanillaRoofedForest");
        VillageConfigVanilla.villageConfigVanillaSavanna = new VillageConfig( "vanilla", "vanillaSavanna");
        VillageConfigVanilla.villageConfigVanillaSavannaPlateau = new VillageConfig( "vanilla", "vanillaSavannaPlateau");
        VillageConfigVanilla.villageConfigVanillaStoneBeach = new VillageConfig( "vanilla", "vanillaStoneBeach");
        VillageConfigVanilla.villageConfigVanillaSwampland = new VillageConfig( "vanilla", "vanillaSwampland");
        VillageConfigVanilla.villageConfigVanillaTaiga = new VillageConfig( "vanilla", "vanillaTaiga");
        VillageConfigVanilla.villageConfigVanillaTaigaHills = new VillageConfig( "vanilla", "vanillaTaigaHills");
        VillageConfigVanilla.villageConfigVanillaSunflowerPlains = new VillageConfig( "vanilla", "vanillaSunflowerPlains");
        VillageConfigVanilla.villageConfigVanillaDesertM = new VillageConfig( "vanilla", "vanillaDesertM");
        VillageConfigVanilla.villageConfigVanillaExtremeHillsM = new VillageConfig( "vanilla", "vanillaExtremeHillsM");
        VillageConfigVanilla.villageConfigVanillaFlowerForest = new VillageConfig( "vanilla", "vanillaFlowerForest");
        VillageConfigVanilla.villageConfigVanillaTaigaM = new VillageConfig( "vanilla", "vanillaTaigaM");
        VillageConfigVanilla.villageConfigVanillaSwamplandM = new VillageConfig( "vanilla", "vanillaSwamplandM");
        VillageConfigVanilla.villageConfigVanillaIcePlainsSpikes = new VillageConfig( "vanilla", "vanillaIcePlainsSpikes");
        VillageConfigVanilla.villageConfigVanillaJungleM = new VillageConfig( "vanilla", "vanillaJungleM");
        VillageConfigVanilla.villageConfigVanillaJungleEdgeM = new VillageConfig( "vanilla", "vanillaJungleEdgeM");
        VillageConfigVanilla.villageConfigVanillaBirchForestM = new VillageConfig( "vanilla", "vanillaBirchForestM");
        VillageConfigVanilla.villageConfigVanillaBirchForestHillsM = new VillageConfig( "vanilla", "vanillaBirchForestHillsM");
        VillageConfigVanilla.villageConfigVanillaRoofedForestM = new VillageConfig( "vanilla", "vanillaRoofedForestM");
        VillageConfigVanilla.villageConfigVanillaColdTaigaM = new VillageConfig( "vanilla", "vanillaColdTaigaM");
        VillageConfigVanilla.villageConfigVanillaMegaSpruceTaiga = new VillageConfig( "vanilla", "vanillaMegaSpruceTaiga");
        VillageConfigVanilla.villageConfigVanillaRedwoodTaigaHills = new VillageConfig( "vanilla", "vanillaRedwoodTaigaHills");
        VillageConfigVanilla.villageConfigVanillaExtremeHillsPlusM = new VillageConfig( "vanilla", "vanillaExtremeHillsPlusM");
        VillageConfigVanilla.villageConfigVanillaSavannaM = new VillageConfig( "vanilla", "vanillaSavannaM");
        VillageConfigVanilla.villageConfigVanillaSavannaPlateauM = new VillageConfig( "vanilla", "vanillaSavannaPlateauM");
        VillageConfigVanilla.villageConfigVanillaMesaBryce = new VillageConfig( "vanilla", "vanillaMesaBryce");
        VillageConfigVanilla.villageConfigVanillaMesaPlateauFM = new VillageConfig( "vanilla", "vanillaMesaPlateauFM");
        VillageConfigVanilla.villageConfigVanillaMesaPlateauM = new VillageConfig( "vanilla", "vanillaMesaPlateauM");
    }

    public static void setVillageConfigsFromUserConfigs(VillageConfig[] villageConfigs, Configuration config)
    {

        for (int i = 0; i < villageConfigs.length; i++) {

            String categoryName = "village." + villageConfigs[i].modSlug + "." + villageConfigs[i].biomeSlug;
            ArrayList<BiomeConfigProperty> properties = villageConfigs[i].getProperties();

            for (int j = 0; j < properties.size(); j++) {

                BiomeConfigProperty prop = properties.get(j);

                switch (prop.type) {

                    case INTEGER:

                        prop.valueInt = config.getInt(
                                prop.name,
                                categoryName,
                                prop.valueInt,
                                prop.minValue,
                                prop.maxValue,
                                prop.description
                        );

                        break;

                    case BOOLEAN:

                        prop.valueBoolean = config.getBoolean(
                                prop.name,
                                categoryName,
                                prop.valueBoolean,
                                prop.description
                        );

                        break;

                    case STRING:

                        prop.valueString = config.getString(
                                prop.name,
                                categoryName,
                                prop.valueString,
                                prop.description
                        );

                        break;
                    default:
                        throw new RuntimeException("BiomeConfigProperty type not supported.");
                }
            }
        }
    }
}
