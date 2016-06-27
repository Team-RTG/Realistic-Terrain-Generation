package rtg.config;

import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACCoraliumInfestedSwamp;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklands;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsForest;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsHighland;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsMountains;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsPlains;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPAlps;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBambooForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBayou;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBog;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBorealForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBrushland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPChaparral;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCherryBlossomGrove;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPColdDesert;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPConiferousForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCoralReef;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCrag;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeadForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeadSwamp;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPEucalyptusForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFen;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFlowerField;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFlowerIsland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGlacier;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGrassland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGravelBeach;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGrove;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPHeathland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPHighland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPKelpForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLandOfLakes;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLavenderFields;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushDesert;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushSwamp;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMapleWoods;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMarsh;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMeadow;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMoor;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMountain;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMountainFoothills;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMysticGrove;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOasis;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOminousWoods;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOrchard;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOriginIsland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOutback;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOvergrownCliffs;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPPrairie;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPQuagmire;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPRainforest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPRedwoodForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSacredSprings;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSeasonalForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShield;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShrubland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSnowyConiferousForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSnowyForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSteppe;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTemperateRainforest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTropicalIsland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTropicalRainforest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTundra;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPVolcanicIsland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPWasteland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPWetland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPWoodland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPXericShrubland;
import rtg.api.biome.buildcraft.config.BiomeConfigBC;
import rtg.api.biome.buildcraft.config.BiomeConfigBCDesertOilField;
import rtg.api.biome.buildcraft.config.BiomeConfigBCOceanOilField;
import rtg.api.biome.minestrappolation.config.BiomeConfigMS;
import rtg.api.biome.minestrappolation.config.BiomeConfigMSRedwoodForest;
import rtg.api.biome.minestrappolation.config.BiomeConfigMSTheFrost;
import rtg.api.biome.thaumcraft.config.BiomeConfigTC;
import rtg.api.biome.thaumcraft.config.BiomeConfigTCEerie;
import rtg.api.biome.thaumcraft.config.BiomeConfigTCMagicalForest;
import rtg.api.biome.thaumcraft.config.BiomeConfigTCTaintedLand;
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
        initBiomeConfigsAC();
        initBiomeConfigsBOP();
        initBiomeConfigsBC();
        initBiomeConfigsMS();
        initBiomeConfigsTC();
        initBiomeConfigsVanilla();
    }
    
    public static void initBiomeConfigsAC()
    {
        BiomeConfigAC.biomeConfigACCoraliumInfestedSwamp = new BiomeConfigACCoraliumInfestedSwamp();
        BiomeConfigAC.biomeConfigACDarklands = new BiomeConfigACDarklands();
        BiomeConfigAC.biomeConfigACDarklandsForest = new BiomeConfigACDarklandsForest();
        BiomeConfigAC.biomeConfigACDarklandsHighland = new BiomeConfigACDarklandsHighland();
        BiomeConfigAC.biomeConfigACDarklandsMountains = new BiomeConfigACDarklandsMountains();
        BiomeConfigAC.biomeConfigACDarklandsPlains = new BiomeConfigACDarklandsPlains();
    }
    
    public static void initBiomeConfigsBOP()
    {
    	BiomeConfigBOP.biomeConfigBOPAlps = new BiomeConfigBOPAlps();
    	BiomeConfigBOP.biomeConfigBOPBambooForest = new BiomeConfigBOPBambooForest();
    	BiomeConfigBOP.biomeConfigBOPBayou = new BiomeConfigBOPBayou();
    	BiomeConfigBOP.biomeConfigBOPBog = new BiomeConfigBOPBog();
    	BiomeConfigBOP.biomeConfigBOPBorealForest = new BiomeConfigBOPBorealForest();
    	BiomeConfigBOP.biomeConfigBOPBrushland = new BiomeConfigBOPBrushland();
    	BiomeConfigBOP.biomeConfigBOPChaparral = new BiomeConfigBOPChaparral();
    	BiomeConfigBOP.biomeConfigBOPCherryBlossomGrove = new BiomeConfigBOPCherryBlossomGrove();
    	BiomeConfigBOP.biomeConfigBOPColdDesert = new BiomeConfigBOPColdDesert();
    	BiomeConfigBOP.biomeConfigBOPConiferousForest = new BiomeConfigBOPConiferousForest();
    	BiomeConfigBOP.biomeConfigBOPCoralReef = new BiomeConfigBOPCoralReef();
    	BiomeConfigBOP.biomeConfigBOPCrag = new BiomeConfigBOPCrag();
    	BiomeConfigBOP.biomeConfigBOPDeadForest = new BiomeConfigBOPDeadForest();
    	BiomeConfigBOP.biomeConfigBOPDeadSwamp = new BiomeConfigBOPDeadSwamp();
    	BiomeConfigBOP.biomeConfigBOPEucalyptusForest = new BiomeConfigBOPEucalyptusForest();
    	BiomeConfigBOP.biomeConfigBOPFen = new BiomeConfigBOPFen();
    	BiomeConfigBOP.biomeConfigBOPFlowerField = new BiomeConfigBOPFlowerField();
    	BiomeConfigBOP.biomeConfigBOPFlowerIsland = new BiomeConfigBOPFlowerIsland();
    	BiomeConfigBOP.biomeConfigBOPGlacier = new BiomeConfigBOPGlacier();
    	BiomeConfigBOP.biomeConfigBOPGrassland = new BiomeConfigBOPGrassland();
    	BiomeConfigBOP.biomeConfigBOPGravelBeach = new BiomeConfigBOPGravelBeach();
    	BiomeConfigBOP.biomeConfigBOPGrove = new BiomeConfigBOPGrove();
    	BiomeConfigBOP.biomeConfigBOPHeathland = new BiomeConfigBOPHeathland();
    	BiomeConfigBOP.biomeConfigBOPHighland = new BiomeConfigBOPHighland();
    	BiomeConfigBOP.biomeConfigBOPKelpForest = new BiomeConfigBOPKelpForest();
    	BiomeConfigBOP.biomeConfigBOPLandOfLakes = new BiomeConfigBOPLandOfLakes();
    	BiomeConfigBOP.biomeConfigBOPLavenderFields = new BiomeConfigBOPLavenderFields();
    	BiomeConfigBOP.biomeConfigBOPLushDesert = new BiomeConfigBOPLushDesert();
    	BiomeConfigBOP.biomeConfigBOPLushSwamp = new BiomeConfigBOPLushSwamp();
    	BiomeConfigBOP.biomeConfigBOPMapleWoods = new BiomeConfigBOPMapleWoods();
    	BiomeConfigBOP.biomeConfigBOPMarsh = new BiomeConfigBOPMarsh();
    	BiomeConfigBOP.biomeConfigBOPMeadow = new BiomeConfigBOPMeadow();
    	BiomeConfigBOP.biomeConfigBOPMoor = new BiomeConfigBOPMoor();
    	BiomeConfigBOP.biomeConfigBOPMountain = new BiomeConfigBOPMountain();
    	BiomeConfigBOP.biomeConfigBOPMountainFoothills = new BiomeConfigBOPMountainFoothills();
    	BiomeConfigBOP.biomeConfigBOPMysticGrove = new BiomeConfigBOPMysticGrove();
    	BiomeConfigBOP.biomeConfigBOPOasis = new BiomeConfigBOPOasis();
    	BiomeConfigBOP.biomeConfigBOPOminousWoods = new BiomeConfigBOPOminousWoods();
    	BiomeConfigBOP.biomeConfigBOPOrchard = new BiomeConfigBOPOrchard();
    	BiomeConfigBOP.biomeConfigBOPOriginIsland = new BiomeConfigBOPOriginIsland();
    	BiomeConfigBOP.biomeConfigBOPOutback = new BiomeConfigBOPOutback();
    	BiomeConfigBOP.biomeConfigBOPOvergrownCliffs = new BiomeConfigBOPOvergrownCliffs();
    	BiomeConfigBOP.biomeConfigBOPPrairie = new BiomeConfigBOPPrairie();
    	BiomeConfigBOP.biomeConfigBOPQuagmire = new BiomeConfigBOPQuagmire();
    	BiomeConfigBOP.biomeConfigBOPRainforest = new BiomeConfigBOPRainforest();
    	BiomeConfigBOP.biomeConfigBOPRedwoodForest = new BiomeConfigBOPRedwoodForest();
    	BiomeConfigBOP.biomeConfigBOPSacredSprings = new BiomeConfigBOPSacredSprings();
    	BiomeConfigBOP.biomeConfigBOPSeasonalForest = new BiomeConfigBOPSeasonalForest();
    	BiomeConfigBOP.biomeConfigBOPShield = new BiomeConfigBOPShield();
    	BiomeConfigBOP.biomeConfigBOPShrubland = new BiomeConfigBOPShrubland();
    	BiomeConfigBOP.biomeConfigBOPSnowyConiferousForest = new BiomeConfigBOPSnowyConiferousForest();
    	BiomeConfigBOP.biomeConfigBOPSnowyForest = new BiomeConfigBOPSnowyForest();
    	BiomeConfigBOP.biomeConfigBOPSteppe = new BiomeConfigBOPSteppe();
    	BiomeConfigBOP.biomeConfigBOPTemperateRainforest = new BiomeConfigBOPTemperateRainforest();
    	BiomeConfigBOP.biomeConfigBOPTropicalIsland = new BiomeConfigBOPTropicalIsland();
    	BiomeConfigBOP.biomeConfigBOPTropicalRainforest = new BiomeConfigBOPTropicalRainforest();
    	BiomeConfigBOP.biomeConfigBOPTundra = new BiomeConfigBOPTundra();
    	BiomeConfigBOP.biomeConfigBOPVolcanicIsland = new BiomeConfigBOPVolcanicIsland();
    	BiomeConfigBOP.biomeConfigBOPWasteland = new BiomeConfigBOPWasteland();
    	BiomeConfigBOP.biomeConfigBOPWetland = new BiomeConfigBOPWetland();
    	BiomeConfigBOP.biomeConfigBOPWoodland = new BiomeConfigBOPWoodland();
    	BiomeConfigBOP.biomeConfigBOPXericShrubland = new BiomeConfigBOPXericShrubland();
    }
    
    public static void initBiomeConfigsBC()
    {
        BiomeConfigBC.biomeConfigBCDesertOilField = new BiomeConfigBCDesertOilField();
        BiomeConfigBC.biomeConfigBCOceanOilField = new BiomeConfigBCOceanOilField();
    }
    
    public static void initBiomeConfigsMS()
    {
        BiomeConfigMS.biomeConfigMSRedwoodForest = new BiomeConfigMSRedwoodForest();
        BiomeConfigMS.biomeConfigMSTheFrost = new BiomeConfigMSTheFrost();
    }

    public static void initBiomeConfigsTC()
    {
        BiomeConfigTC.biomeConfigTCEerie = new BiomeConfigTCEerie();
        BiomeConfigTC.biomeConfigTCMagicalForest = new BiomeConfigTCMagicalForest();
        BiomeConfigTC.biomeConfigTCTaintedLand = new BiomeConfigTCTaintedLand();
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
    public static void setBiomeConfigsFromUserConfigs(BiomeConfig[] biomeConfigs, Configuration config)
    {
        
        for (int i = 0; i < biomeConfigs.length; i++) {
            
            String categoryName = "biome." + biomeConfigs[i].modSlug + "." + biomeConfigs[i].biomeSlug;
            ArrayList<BiomeConfigProperty> properties = biomeConfigs[i].getProperties();
            
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
