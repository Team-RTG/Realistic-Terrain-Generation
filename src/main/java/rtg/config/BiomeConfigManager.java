package rtg.config;

import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBeach;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForest;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestHillsM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdBeach;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaiga;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaigaHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaigaM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaDeepOcean;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaDesert;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaDesertHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaDesertM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHillsEdge;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHillsM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHillsPlus;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHillsPlusM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaFlowerForest;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForest;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForestHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaFrozenOcean;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaFrozenRiver;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIceMountains;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIcePlains;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIcePlainsSpikes;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungle;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleEdge;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleEdgeM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMegaSpruceTaiga;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMegaTaiga;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMegaTaigaHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMesa;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMesaBryce;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMesaPlateau;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMesaPlateauF;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMesaPlateauFM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMesaPlateauM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMushroomIsland;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMushroomIslandShore;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaOcean;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaPlains;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRedwoodTaigaHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRiver;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForest;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForestM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavanna;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavannaM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavannaPlateau;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavannaPlateauM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaStoneBeach;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSunflowerPlains;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSwampland;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSwamplandM;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaiga;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaHills;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaM;

public class BiomeConfigManager
{
    
    public static void initBiomeConfigs()
    {
        initBiomeConfigsVanilla();
    }
    
    public static void initBiomeConfigsVanilla()
    {
        BiomeConfigVanilla.biomeConfigVanillaBeach = new BiomeConfigVanillaBeach();
        BiomeConfigVanilla.biomeConfigVanillaBirchForest = new BiomeConfigVanillaBirchForest();
        BiomeConfigVanilla.biomeConfigVanillaBirchForestHills = new BiomeConfigVanillaBirchForestHills();
        BiomeConfigVanilla.biomeConfigVanillaColdBeach = new BiomeConfigVanillaColdBeach();
        BiomeConfigVanilla.biomeConfigVanillaColdTaiga = new BiomeConfigVanillaColdTaiga();
        BiomeConfigVanilla.biomeConfigVanillaColdTaigaHills = new BiomeConfigVanillaColdTaigaHills();
        BiomeConfigVanilla.biomeConfigVanillaDeepOcean = new BiomeConfigVanillaDeepOcean();
        BiomeConfigVanilla.biomeConfigVanillaDesert = new BiomeConfigVanillaDesert();
        BiomeConfigVanilla.biomeConfigVanillaDesertHills = new BiomeConfigVanillaDesertHills();
        BiomeConfigVanilla.biomeConfigVanillaExtremeHills = new BiomeConfigVanillaExtremeHills();
        BiomeConfigVanilla.biomeConfigVanillaExtremeHillsEdge = new BiomeConfigVanillaExtremeHillsEdge();
        BiomeConfigVanilla.biomeConfigVanillaExtremeHillsPlus = new BiomeConfigVanillaExtremeHillsPlus();
        BiomeConfigVanilla.biomeConfigVanillaForest = new BiomeConfigVanillaForest();
        BiomeConfigVanilla.biomeConfigVanillaForestHills = new BiomeConfigVanillaForestHills();
        BiomeConfigVanilla.biomeConfigVanillaFrozenOcean = new BiomeConfigVanillaFrozenOcean();
        BiomeConfigVanilla.biomeConfigVanillaFrozenRiver = new BiomeConfigVanillaFrozenRiver();
        BiomeConfigVanilla.biomeConfigVanillaIcePlains = new BiomeConfigVanillaIcePlains();
        BiomeConfigVanilla.biomeConfigVanillaIceMountains = new BiomeConfigVanillaIceMountains();
        BiomeConfigVanilla.biomeConfigVanillaJungle = new BiomeConfigVanillaJungle();
        BiomeConfigVanilla.biomeConfigVanillaJungleEdge = new BiomeConfigVanillaJungleEdge();
        BiomeConfigVanilla.biomeConfigVanillaJungleHills = new BiomeConfigVanillaJungleHills();
        BiomeConfigVanilla.biomeConfigVanillaMegaTaiga = new BiomeConfigVanillaMegaTaiga();
        BiomeConfigVanilla.biomeConfigVanillaMegaTaigaHills = new BiomeConfigVanillaMegaTaigaHills();
        BiomeConfigVanilla.biomeConfigVanillaMesa = new BiomeConfigVanillaMesa();
        BiomeConfigVanilla.biomeConfigVanillaMesaPlateau = new BiomeConfigVanillaMesaPlateau();
        BiomeConfigVanilla.biomeConfigVanillaMesaPlateauF = new BiomeConfigVanillaMesaPlateauF();
        BiomeConfigVanilla.biomeConfigVanillaMushroomIsland = new BiomeConfigVanillaMushroomIsland();
        BiomeConfigVanilla.biomeConfigVanillaMushroomIslandShore = new BiomeConfigVanillaMushroomIslandShore();
        BiomeConfigVanilla.biomeConfigVanillaOcean = new BiomeConfigVanillaOcean();
        BiomeConfigVanilla.biomeConfigVanillaPlains = new BiomeConfigVanillaPlains();
        BiomeConfigVanilla.biomeConfigVanillaRiver = new BiomeConfigVanillaRiver();
        BiomeConfigVanilla.biomeConfigVanillaRoofedForest = new BiomeConfigVanillaRoofedForest();
        BiomeConfigVanilla.biomeConfigVanillaSavanna = new BiomeConfigVanillaSavanna();
        BiomeConfigVanilla.biomeConfigVanillaSavannaPlateau = new BiomeConfigVanillaSavannaPlateau();
        BiomeConfigVanilla.biomeConfigVanillaStoneBeach = new BiomeConfigVanillaStoneBeach();
        BiomeConfigVanilla.biomeConfigVanillaSwampland = new BiomeConfigVanillaSwampland();
        BiomeConfigVanilla.biomeConfigVanillaTaiga = new BiomeConfigVanillaTaiga();
        BiomeConfigVanilla.biomeConfigVanillaTaigaHills = new BiomeConfigVanillaTaigaHills();
        BiomeConfigVanilla.biomeConfigVanillaSunflowerPlains = new BiomeConfigVanillaSunflowerPlains();
        BiomeConfigVanilla.biomeConfigVanillaDesertM = new BiomeConfigVanillaDesertM();
        BiomeConfigVanilla.biomeConfigVanillaExtremeHillsM = new BiomeConfigVanillaExtremeHillsM();
        BiomeConfigVanilla.biomeConfigVanillaFlowerForest = new BiomeConfigVanillaFlowerForest();
        BiomeConfigVanilla.biomeConfigVanillaTaigaM = new BiomeConfigVanillaTaigaM();
        BiomeConfigVanilla.biomeConfigVanillaSwamplandM = new BiomeConfigVanillaSwamplandM();
        BiomeConfigVanilla.biomeConfigVanillaIcePlainsSpikes = new BiomeConfigVanillaIcePlainsSpikes();
        BiomeConfigVanilla.biomeConfigVanillaJungleM = new BiomeConfigVanillaJungleM();
        BiomeConfigVanilla.biomeConfigVanillaJungleEdgeM = new BiomeConfigVanillaJungleEdgeM();
        BiomeConfigVanilla.biomeConfigVanillaBirchForestM = new BiomeConfigVanillaBirchForestM();
        BiomeConfigVanilla.biomeConfigVanillaBirchForestHillsM = new BiomeConfigVanillaBirchForestHillsM();
        BiomeConfigVanilla.biomeConfigVanillaRoofedForestM = new BiomeConfigVanillaRoofedForestM();
        BiomeConfigVanilla.biomeConfigVanillaColdTaigaM = new BiomeConfigVanillaColdTaigaM();
        BiomeConfigVanilla.biomeConfigVanillaMegaSpruceTaiga = new BiomeConfigVanillaMegaSpruceTaiga();
        BiomeConfigVanilla.biomeConfigVanillaRedwoodTaigaHills = new BiomeConfigVanillaRedwoodTaigaHills();
        BiomeConfigVanilla.biomeConfigVanillaExtremeHillsPlusM = new BiomeConfigVanillaExtremeHillsPlusM();
        BiomeConfigVanilla.biomeConfigVanillaSavannaM = new BiomeConfigVanillaSavannaM();
        BiomeConfigVanilla.biomeConfigVanillaSavannaPlateauM = new BiomeConfigVanillaSavannaPlateauM();
        BiomeConfigVanilla.biomeConfigVanillaMesaBryce = new BiomeConfigVanillaMesaBryce();
        BiomeConfigVanilla.biomeConfigVanillaMesaPlateauFM = new BiomeConfigVanillaMesaPlateauFM();
        BiomeConfigVanilla.biomeConfigVanillaMesaPlateauM = new BiomeConfigVanillaMesaPlateauM();
    }
}
