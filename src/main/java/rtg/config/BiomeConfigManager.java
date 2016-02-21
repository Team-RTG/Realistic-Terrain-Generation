package rtg.config;

import java.util.ArrayList;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACCoraliumInfestedSwamp;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklands;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsForest;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsHighland;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsMountains;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsPlains;
import rtg.api.biome.arsmagica.config.BiomeConfigAM;
import rtg.api.biome.arsmagica.config.BiomeConfigAMWitchwoodForest;
import rtg.api.biome.atg.config.BiomeConfigATG;
import rtg.api.biome.atg.config.BiomeConfigATGGravelBeach;
import rtg.api.biome.atg.config.BiomeConfigATGRockySteppe;
import rtg.api.biome.atg.config.BiomeConfigATGShrubland;
import rtg.api.biome.atg.config.BiomeConfigATGSnowyGravelBeach;
import rtg.api.biome.atg.config.BiomeConfigATGTropicalShrubland;
import rtg.api.biome.atg.config.BiomeConfigATGTundra;
import rtg.api.biome.atg.config.BiomeConfigATGVolcano;
import rtg.api.biome.atg.config.BiomeConfigATGWoodland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPAlps;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPAlpsForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPArctic;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBambooForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBayou;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBog;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBorealForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBrushland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCanyon;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCanyonRavine;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPChaparral;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCherryBlossomGrove;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPConiferousForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCoralReef;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPCrag;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeadForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeadSwamp;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeciduousForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDenseForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDryRiver;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPEucalyptusForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFen;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFlowerField;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFrostForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPFungiForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGarden;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGlacier;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGrassland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGrove;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPHeathland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPHighland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPJadeCliffs;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPKelpForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLandOfLakes;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLandOfLakesMarsh;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLavenderFields;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushDesert;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushRiver;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushSwamp;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMangrove;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMapleWoods;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMarsh;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMeadow;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMeadowForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMoor;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMountain;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMysticGrove;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOasis;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOminousWoods;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOrchard;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOriginValley;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPOutback;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPPrairie;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPQuagmire;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPRainforest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPRedwoodForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSacredSprings;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPScrubland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSeasonalForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSeasonalForestClearing;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShield;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPShrubland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSilkglades;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSludgepit;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSnowyConiferousForest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSpruceWoods;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPSteppe;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTemperateRainforest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPThicket;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTropicalRainforest;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTropics;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPTundra;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPVolcano;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPWasteland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPWetland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPWoodland;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPXericShrubland;
import rtg.api.biome.buildcraft.config.BiomeConfigBC;
import rtg.api.biome.buildcraft.config.BiomeConfigBCDesertOilField;
import rtg.api.biome.buildcraft.config.BiomeConfigBCOceanOilField;
import rtg.api.biome.chromaticraft.config.BiomeConfigCC;
import rtg.api.biome.chromaticraft.config.BiomeConfigCCEnderForest;
import rtg.api.biome.chromaticraft.config.BiomeConfigCCRainbowForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEB;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBAlpineMountains;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBAlpineMountainsEdge;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBAlpineMountainsM;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBAlpineTundra;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBAspenForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBAspenHills;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBadlands;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBasin;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBlossomHills;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBlossomWoods;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBorealArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBorealForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBorealPlateau;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBorealPlateauM;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBCarr;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBClayHills;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBClearing;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBColdBorealForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBColdCypressForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBColdFirForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBColdPineForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBCreekBed;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBCypressForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBDesertArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBEphemeralLake;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBEphemeralLakeEdge;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBFens;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBFirForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBFloweryArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBForestedArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBForestedMountains;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBForestedValley;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBFrozenArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBGlacier;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBGrassyArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBIceSheet;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBKakadu;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBLake;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBLowHills;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBMangroves;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBMarsh;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBMeadow;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBMeadowM;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBMountainousArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBMountains;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBMountainsEdge;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBOakForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBOasis;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBPineForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBPineForestArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBPlateau;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBPolarDesert;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBPrairie;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBRainforest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBRainforestValley;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBRedDesert;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBRiparianZone;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBRockyDesert;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBRockyHills;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBRoofedShrublands;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSahara;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSandstoneCanyon;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSandstoneCanyon2;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSandstoneRanges;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSandstoneRangesM;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBScree;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBScrub;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBShield;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBShrublands;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSilverPineForest;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSilverPineHills;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSnowyDesert;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSnowyPlateau;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSnowyRanges;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSnowyWastelands;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSteppe;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBStoneCanyon;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBStoneCanyon2;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBTropicalArchipelago;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBTundra;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBVolcano;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBVolcanoM;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBWastelands;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBWoodlandField;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBWoodlandHills;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBWoodlandLake;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBWoodlandLakeEdge;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBWoodlands;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBXericSavanna;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBXericShrubland;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXL;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLAlpine;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLAutumnWoods;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLBirchForest;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLExtremeJungle;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLForestedHills;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLForestedIsland;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLGlacier;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLGreenHills;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLGreenSwamp;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLIceWasteland;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLMarsh;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLMeadow;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLMiniJungle;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLMountainDesert;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLMountainRidge;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLMountainTaiga;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLPineForest;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLRainforest;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLRedwoodForest;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLRedwoodLush;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLSavanna;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLShrubland;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLSnowForest;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLSnowyRainforest;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLTemperateRainforest;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLTundra;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLWasteland;
import rtg.api.biome.extrabiomes.config.BiomeConfigEBXLWoodlands;
import rtg.api.biome.highlands.config.BiomeConfigHL;
import rtg.api.biome.highlands.config.BiomeConfigHLAlps;
import rtg.api.biome.highlands.config.BiomeConfigHLAutumnForest;
import rtg.api.biome.highlands.config.BiomeConfigHLBadlands;
import rtg.api.biome.highlands.config.BiomeConfigHLBaldHill;
import rtg.api.biome.highlands.config.BiomeConfigHLBirchHills;
import rtg.api.biome.highlands.config.BiomeConfigHLBog;
import rtg.api.biome.highlands.config.BiomeConfigHLCanyon;
import rtg.api.biome.highlands.config.BiomeConfigHLCliffs;
import rtg.api.biome.highlands.config.BiomeConfigHLDesertIsland;
import rtg.api.biome.highlands.config.BiomeConfigHLDesertMountains;
import rtg.api.biome.highlands.config.BiomeConfigHLDunes;
import rtg.api.biome.highlands.config.BiomeConfigHLEstuary;
import rtg.api.biome.highlands.config.BiomeConfigHLFlyingMountains;
import rtg.api.biome.highlands.config.BiomeConfigHLForestIsland;
import rtg.api.biome.highlands.config.BiomeConfigHLGlacier;
import rtg.api.biome.highlands.config.BiomeConfigHLHighlandsB;
import rtg.api.biome.highlands.config.BiomeConfigHLJungleIsland;
import rtg.api.biome.highlands.config.BiomeConfigHLLake;
import rtg.api.biome.highlands.config.BiomeConfigHLLowlands;
import rtg.api.biome.highlands.config.BiomeConfigHLMeadow;
import rtg.api.biome.highlands.config.BiomeConfigHLMesa;
import rtg.api.biome.highlands.config.BiomeConfigHLOasis;
import rtg.api.biome.highlands.config.BiomeConfigHLOutback;
import rtg.api.biome.highlands.config.BiomeConfigHLPinelands;
import rtg.api.biome.highlands.config.BiomeConfigHLRainforest;
import rtg.api.biome.highlands.config.BiomeConfigHLRedwoodForest;
import rtg.api.biome.highlands.config.BiomeConfigHLRockIsland;
import rtg.api.biome.highlands.config.BiomeConfigHLRockMountains;
import rtg.api.biome.highlands.config.BiomeConfigHLSahel;
import rtg.api.biome.highlands.config.BiomeConfigHLSavannah;
import rtg.api.biome.highlands.config.BiomeConfigHLShrubland;
import rtg.api.biome.highlands.config.BiomeConfigHLSnowIsland;
import rtg.api.biome.highlands.config.BiomeConfigHLSnowMountains;
import rtg.api.biome.highlands.config.BiomeConfigHLSteppe;
import rtg.api.biome.highlands.config.BiomeConfigHLTallPineForest;
import rtg.api.biome.highlands.config.BiomeConfigHLTropicalIslands;
import rtg.api.biome.highlands.config.BiomeConfigHLTropics;
import rtg.api.biome.highlands.config.BiomeConfigHLTundra;
import rtg.api.biome.highlands.config.BiomeConfigHLValley;
import rtg.api.biome.highlands.config.BiomeConfigHLVolcanoIsland;
import rtg.api.biome.highlands.config.BiomeConfigHLWindyIsland;
import rtg.api.biome.highlands.config.BiomeConfigHLWoodlands;
import rtg.api.biome.highlands.config.BiomeConfigHLWoodsMountains;
import rtg.api.biome.lotsomobs.config.BiomeConfigLOM;
import rtg.api.biome.lotsomobs.config.BiomeConfigLOMAntartica;
import rtg.api.biome.lotsomobs.config.BiomeConfigLOMTropicalBeach;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRW;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRWBotanicalGarden;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRWMountainOfMadness;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRWMurica;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRWOssuary;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRWRockCandyMountain;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRWShadowFen;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRWSpookyForest;
import rtg.api.biome.thaumcraft.config.BiomeConfigTC;
import rtg.api.biome.thaumcraft.config.BiomeConfigTCEerie;
import rtg.api.biome.thaumcraft.config.BiomeConfigTCMagicalForest;
import rtg.api.biome.thaumcraft.config.BiomeConfigTCTaintedLand;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFU;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFULeekPlains;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuBuildings;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuExtremeHills;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuExtremeHillsEdge;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuForest;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuForestHills;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuPlainHills;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuPlains;
import rtg.api.biome.tofucraft.config.BiomeConfigTOFUTofuRiver;
import rtg.api.biome.vampirism.config.BiomeConfigVAMP;
import rtg.api.biome.vampirism.config.BiomeConfigVAMPVampireForest;
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

import net.minecraftforge.common.config.Configuration;

public class BiomeConfigManager
{
    
    public static void initBiomeConfigs()
    {
        initBiomeConfigsAC();
        initBiomeConfigsAM();
        initBiomeConfigsATG();
        initBiomeConfigsBOP();
        initBiomeConfigsBC();
        initBiomeConfigsCC();
        initBiomeConfigsEB();
        initBiomeConfigsEBXL();
        initBiomeConfigsHL();
        initBiomeConfigsRW();
        initBiomeConfigsTC();
        initBiomeConfigsVAMP();
        initBiomeConfigsLOM();
        initBiomeConfigsTOFU();
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
    
    public static void initBiomeConfigsAM()
    {
        BiomeConfigAM.biomeConfigAMWitchwoodForest = new BiomeConfigAMWitchwoodForest();
    }
    
    public static void initBiomeConfigsATG()
    {
        BiomeConfigATG.biomeConfigATGGravelBeach = new BiomeConfigATGGravelBeach();
        BiomeConfigATG.biomeConfigATGRockySteppe = new BiomeConfigATGRockySteppe();
        BiomeConfigATG.biomeConfigATGShrubland = new BiomeConfigATGShrubland();
        BiomeConfigATG.biomeConfigATGSnowyGravelBeach = new BiomeConfigATGSnowyGravelBeach();
        BiomeConfigATG.biomeConfigATGTropicalShrubland = new BiomeConfigATGTropicalShrubland();
        BiomeConfigATG.biomeConfigATGTundra = new BiomeConfigATGTundra();
        BiomeConfigATG.biomeConfigATGVolcano = new BiomeConfigATGVolcano();
        BiomeConfigATG.biomeConfigATGWoodland = new BiomeConfigATGWoodland();
    }
    
    public static void initBiomeConfigsBOP()
    {
        BiomeConfigBOP.biomeConfigBOPAlps = new BiomeConfigBOPAlps();
        BiomeConfigBOP.biomeConfigBOPArctic = new BiomeConfigBOPArctic();
        BiomeConfigBOP.biomeConfigBOPBambooForest = new BiomeConfigBOPBambooForest();
        BiomeConfigBOP.biomeConfigBOPBayou = new BiomeConfigBOPBayou();
        BiomeConfigBOP.biomeConfigBOPBog = new BiomeConfigBOPBog();
        BiomeConfigBOP.biomeConfigBOPBorealForest = new BiomeConfigBOPBorealForest();
        BiomeConfigBOP.biomeConfigBOPBrushland = new BiomeConfigBOPBrushland();
        BiomeConfigBOP.biomeConfigBOPCanyon = new BiomeConfigBOPCanyon();
        BiomeConfigBOP.biomeConfigBOPChaparral = new BiomeConfigBOPChaparral();
        BiomeConfigBOP.biomeConfigBOPCherryBlossomGrove = new BiomeConfigBOPCherryBlossomGrove();
        BiomeConfigBOP.biomeConfigBOPConiferousForest = new BiomeConfigBOPConiferousForest();
        BiomeConfigBOP.biomeConfigBOPCrag = new BiomeConfigBOPCrag();
        BiomeConfigBOP.biomeConfigBOPDeadForest = new BiomeConfigBOPDeadForest();
        BiomeConfigBOP.biomeConfigBOPDeadSwamp = new BiomeConfigBOPDeadSwamp();
        BiomeConfigBOP.biomeConfigBOPDeciduousForest = new BiomeConfigBOPDeciduousForest();
        BiomeConfigBOP.biomeConfigBOPDenseForest = new BiomeConfigBOPDenseForest();
        BiomeConfigBOP.biomeConfigBOPDryRiver = new BiomeConfigBOPDryRiver();
        BiomeConfigBOP.biomeConfigBOPEucalyptusForest = new BiomeConfigBOPEucalyptusForest();
        BiomeConfigBOP.biomeConfigBOPFen = new BiomeConfigBOPFen();
        BiomeConfigBOP.biomeConfigBOPFlowerField = new BiomeConfigBOPFlowerField();
        BiomeConfigBOP.biomeConfigBOPFrostForest = new BiomeConfigBOPFrostForest();
        BiomeConfigBOP.biomeConfigBOPFungiForest = new BiomeConfigBOPFungiForest();
        BiomeConfigBOP.biomeConfigBOPGarden = new BiomeConfigBOPGarden();
        BiomeConfigBOP.biomeConfigBOPGrassland = new BiomeConfigBOPGrassland();
        BiomeConfigBOP.biomeConfigBOPGrove = new BiomeConfigBOPGrove();
        BiomeConfigBOP.biomeConfigBOPHeathland = new BiomeConfigBOPHeathland();
        BiomeConfigBOP.biomeConfigBOPHighland = new BiomeConfigBOPHighland();
        BiomeConfigBOP.biomeConfigBOPJadeCliffs = new BiomeConfigBOPJadeCliffs();
        BiomeConfigBOP.biomeConfigBOPLandOfLakes = new BiomeConfigBOPLandOfLakes();
        BiomeConfigBOP.biomeConfigBOPLavenderFields = new BiomeConfigBOPLavenderFields();
        BiomeConfigBOP.biomeConfigBOPLushDesert = new BiomeConfigBOPLushDesert();
        BiomeConfigBOP.biomeConfigBOPLushRiver = new BiomeConfigBOPLushRiver();
        BiomeConfigBOP.biomeConfigBOPLushSwamp = new BiomeConfigBOPLushSwamp();
        BiomeConfigBOP.biomeConfigBOPMapleWoods = new BiomeConfigBOPMapleWoods();
        BiomeConfigBOP.biomeConfigBOPMarsh = new BiomeConfigBOPMarsh();
        BiomeConfigBOP.biomeConfigBOPMeadow = new BiomeConfigBOPMeadow();
        BiomeConfigBOP.biomeConfigBOPMoor = new BiomeConfigBOPMoor();
        BiomeConfigBOP.biomeConfigBOPMountain = new BiomeConfigBOPMountain();
        BiomeConfigBOP.biomeConfigBOPMysticGrove = new BiomeConfigBOPMysticGrove();
        BiomeConfigBOP.biomeConfigBOPOminousWoods = new BiomeConfigBOPOminousWoods();
        BiomeConfigBOP.biomeConfigBOPOriginValley = new BiomeConfigBOPOriginValley();
        BiomeConfigBOP.biomeConfigBOPOutback = new BiomeConfigBOPOutback();
        BiomeConfigBOP.biomeConfigBOPPrairie = new BiomeConfigBOPPrairie();
        BiomeConfigBOP.biomeConfigBOPRainforest = new BiomeConfigBOPRainforest();
        BiomeConfigBOP.biomeConfigBOPRedwoodForest = new BiomeConfigBOPRedwoodForest();
        BiomeConfigBOP.biomeConfigBOPSacredSprings = new BiomeConfigBOPSacredSprings();
        BiomeConfigBOP.biomeConfigBOPSeasonalForest = new BiomeConfigBOPSeasonalForest();
        BiomeConfigBOP.biomeConfigBOPShield = new BiomeConfigBOPShield();
        BiomeConfigBOP.biomeConfigBOPShrubland = new BiomeConfigBOPShrubland();
        BiomeConfigBOP.biomeConfigBOPSludgepit = new BiomeConfigBOPSludgepit();
        BiomeConfigBOP.biomeConfigBOPSnowyConiferousForest = new BiomeConfigBOPSnowyConiferousForest();
        BiomeConfigBOP.biomeConfigBOPSteppe = new BiomeConfigBOPSteppe();
        BiomeConfigBOP.biomeConfigBOPTemperateRainforest = new BiomeConfigBOPTemperateRainforest();
        BiomeConfigBOP.biomeConfigBOPThicket = new BiomeConfigBOPThicket();
        BiomeConfigBOP.biomeConfigBOPTropicalRainforest = new BiomeConfigBOPTropicalRainforest();
        BiomeConfigBOP.biomeConfigBOPTundra = new BiomeConfigBOPTundra();
        BiomeConfigBOP.biomeConfigBOPWasteland = new BiomeConfigBOPWasteland();
        BiomeConfigBOP.biomeConfigBOPWetland = new BiomeConfigBOPWetland();
        BiomeConfigBOP.biomeConfigBOPWoodland = new BiomeConfigBOPWoodland();
        BiomeConfigBOP.biomeConfigBOPXericShrubland = new BiomeConfigBOPXericShrubland();
        BiomeConfigBOP.biomeConfigBOPAlpsForest = new BiomeConfigBOPAlpsForest();
        BiomeConfigBOP.biomeConfigBOPCanyonRavine = new BiomeConfigBOPCanyonRavine();
        BiomeConfigBOP.biomeConfigBOPGlacier = new BiomeConfigBOPGlacier();
        BiomeConfigBOP.biomeConfigBOPLandOfLakesMarsh = new BiomeConfigBOPLandOfLakesMarsh();
        BiomeConfigBOP.biomeConfigBOPMangrove = new BiomeConfigBOPMangrove();
        BiomeConfigBOP.biomeConfigBOPMeadowForest = new BiomeConfigBOPMeadowForest();
        BiomeConfigBOP.biomeConfigBOPOasis = new BiomeConfigBOPOasis();
        BiomeConfigBOP.biomeConfigBOPOrchard = new BiomeConfigBOPOrchard();
        BiomeConfigBOP.biomeConfigBOPQuagmire = new BiomeConfigBOPQuagmire();
        BiomeConfigBOP.biomeConfigBOPScrubland = new BiomeConfigBOPScrubland();
        BiomeConfigBOP.biomeConfigBOPSeasonalForestClearing = new BiomeConfigBOPSeasonalForestClearing();
        BiomeConfigBOP.biomeConfigBOPSilkglades = new BiomeConfigBOPSilkglades();
        BiomeConfigBOP.biomeConfigBOPSpruceWoods = new BiomeConfigBOPSpruceWoods();
        BiomeConfigBOP.biomeConfigBOPTropics = new BiomeConfigBOPTropics();
        BiomeConfigBOP.biomeConfigBOPVolcano = new BiomeConfigBOPVolcano();
        BiomeConfigBOP.biomeConfigBOPCoralReef = new BiomeConfigBOPCoralReef();
        BiomeConfigBOP.biomeConfigBOPKelpForest = new BiomeConfigBOPKelpForest();
    }
    
    public static void initBiomeConfigsBC()
    {
        BiomeConfigBC.biomeConfigBCDesertOilField = new BiomeConfigBCDesertOilField();
        BiomeConfigBC.biomeConfigBCOceanOilField = new BiomeConfigBCOceanOilField();
    }
    
    public static void initBiomeConfigsCC()
    {
        BiomeConfigCC.biomeConfigCCEnderForest = new BiomeConfigCCEnderForest();
        BiomeConfigCC.biomeConfigCCRainbowForest = new BiomeConfigCCRainbowForest();
    }
    
    public static void initBiomeConfigsEB()
    {
        BiomeConfigEB.biomeConfigEBAlpineMountains = new BiomeConfigEBAlpineMountains();
        BiomeConfigEB.biomeConfigEBAlpineMountainsEdge = new BiomeConfigEBAlpineMountainsEdge();
        BiomeConfigEB.biomeConfigEBAlpineMountainsM = new BiomeConfigEBAlpineMountainsM();
        BiomeConfigEB.biomeConfigEBAlpineTundra = new BiomeConfigEBAlpineTundra();
        BiomeConfigEB.biomeConfigEBAspenForest = new BiomeConfigEBAspenForest();
        BiomeConfigEB.biomeConfigEBAspenHills = new BiomeConfigEBAspenHills();
        BiomeConfigEB.biomeConfigEBBadlands = new BiomeConfigEBBadlands();
        BiomeConfigEB.biomeConfigEBBasin = new BiomeConfigEBBasin();
        BiomeConfigEB.biomeConfigEBBlossomHills = new BiomeConfigEBBlossomHills();
        BiomeConfigEB.biomeConfigEBBlossomWoods = new BiomeConfigEBBlossomWoods();
        BiomeConfigEB.biomeConfigEBBorealArchipelago = new BiomeConfigEBBorealArchipelago();
        BiomeConfigEB.biomeConfigEBBorealForest = new BiomeConfigEBBorealForest();
        BiomeConfigEB.biomeConfigEBBorealPlateau = new BiomeConfigEBBorealPlateau();
        BiomeConfigEB.biomeConfigEBBorealPlateauM = new BiomeConfigEBBorealPlateauM();
        BiomeConfigEB.biomeConfigEBCarr = new BiomeConfigEBCarr();
        BiomeConfigEB.biomeConfigEBClayHills = new BiomeConfigEBClayHills();
        BiomeConfigEB.biomeConfigEBClearing = new BiomeConfigEBClearing();
        BiomeConfigEB.biomeConfigEBColdBorealForest = new BiomeConfigEBColdBorealForest();
        BiomeConfigEB.biomeConfigEBColdCypressForest = new BiomeConfigEBColdCypressForest();
        BiomeConfigEB.biomeConfigEBColdFirForest = new BiomeConfigEBColdFirForest();
        BiomeConfigEB.biomeConfigEBColdPineForest = new BiomeConfigEBColdPineForest();
        BiomeConfigEB.biomeConfigEBCreekBed = new BiomeConfigEBCreekBed();
        BiomeConfigEB.biomeConfigEBCypressForest = new BiomeConfigEBCypressForest();
        BiomeConfigEB.biomeConfigEBDesertArchipelago = new BiomeConfigEBDesertArchipelago();
        BiomeConfigEB.biomeConfigEBEphemeralLake = new BiomeConfigEBEphemeralLake();
        BiomeConfigEB.biomeConfigEBEphemeralLakeEdge = new BiomeConfigEBEphemeralLakeEdge();
        BiomeConfigEB.biomeConfigEBFens = new BiomeConfigEBFens();
        BiomeConfigEB.biomeConfigEBFirForest = new BiomeConfigEBFirForest();
        BiomeConfigEB.biomeConfigEBFloweryArchipelago = new BiomeConfigEBFloweryArchipelago();
        BiomeConfigEB.biomeConfigEBForestedArchipelago = new BiomeConfigEBForestedArchipelago();
        BiomeConfigEB.biomeConfigEBForestedMountains = new BiomeConfigEBForestedMountains();
        BiomeConfigEB.biomeConfigEBForestedValley = new BiomeConfigEBForestedValley();
        BiomeConfigEB.biomeConfigEBFrozenArchipelago = new BiomeConfigEBFrozenArchipelago();
        BiomeConfigEB.biomeConfigEBGlacier = new BiomeConfigEBGlacier();
        BiomeConfigEB.biomeConfigEBGrassyArchipelago = new BiomeConfigEBGrassyArchipelago();
        BiomeConfigEB.biomeConfigEBIceSheet = new BiomeConfigEBIceSheet();
        BiomeConfigEB.biomeConfigEBKakadu = new BiomeConfigEBKakadu();
        BiomeConfigEB.biomeConfigEBLake = new BiomeConfigEBLake();
        BiomeConfigEB.biomeConfigEBLowHills = new BiomeConfigEBLowHills();
        BiomeConfigEB.biomeConfigEBMangroves = new BiomeConfigEBMangroves();
        BiomeConfigEB.biomeConfigEBMarsh = new BiomeConfigEBMarsh();
        BiomeConfigEB.biomeConfigEBMeadow = new BiomeConfigEBMeadow();
        BiomeConfigEB.biomeConfigEBMeadowM = new BiomeConfigEBMeadowM();
        BiomeConfigEB.biomeConfigEBMountainousArchipelago = new BiomeConfigEBMountainousArchipelago();
        BiomeConfigEB.biomeConfigEBMountains = new BiomeConfigEBMountains();
        BiomeConfigEB.biomeConfigEBMountainsEdge = new BiomeConfigEBMountainsEdge();
        BiomeConfigEB.biomeConfigEBOakForest = new BiomeConfigEBOakForest();
        BiomeConfigEB.biomeConfigEBOasis = new BiomeConfigEBOasis();
        BiomeConfigEB.biomeConfigEBPineForest = new BiomeConfigEBPineForest();
        BiomeConfigEB.biomeConfigEBPineForestArchipelago = new BiomeConfigEBPineForestArchipelago();
        BiomeConfigEB.biomeConfigEBPlateau = new BiomeConfigEBPlateau();
        BiomeConfigEB.biomeConfigEBPolarDesert = new BiomeConfigEBPolarDesert();
        BiomeConfigEB.biomeConfigEBPrairie = new BiomeConfigEBPrairie();
        BiomeConfigEB.biomeConfigEBRainforest = new BiomeConfigEBRainforest();
        BiomeConfigEB.biomeConfigEBRainforestValley = new BiomeConfigEBRainforestValley();
        BiomeConfigEB.biomeConfigEBRedDesert = new BiomeConfigEBRedDesert();
        BiomeConfigEB.biomeConfigEBRiparianZone = new BiomeConfigEBRiparianZone();
        BiomeConfigEB.biomeConfigEBRockyDesert = new BiomeConfigEBRockyDesert();
        BiomeConfigEB.biomeConfigEBRockyHills = new BiomeConfigEBRockyHills();
        BiomeConfigEB.biomeConfigEBRoofedShrublands = new BiomeConfigEBRoofedShrublands();
        BiomeConfigEB.biomeConfigEBSahara = new BiomeConfigEBSahara();
        BiomeConfigEB.biomeConfigEBSandstoneCanyon = new BiomeConfigEBSandstoneCanyon();
        BiomeConfigEB.biomeConfigEBSandstoneCanyon2 = new BiomeConfigEBSandstoneCanyon2();
        BiomeConfigEB.biomeConfigEBSandstoneRanges = new BiomeConfigEBSandstoneRanges();
        BiomeConfigEB.biomeConfigEBSandstoneRangesM = new BiomeConfigEBSandstoneRangesM();
        BiomeConfigEB.biomeConfigEBScree = new BiomeConfigEBScree();
        BiomeConfigEB.biomeConfigEBScrub = new BiomeConfigEBScrub();
        BiomeConfigEB.biomeConfigEBShield = new BiomeConfigEBShield();
        BiomeConfigEB.biomeConfigEBShrublands = new BiomeConfigEBShrublands();
        BiomeConfigEB.biomeConfigEBSilverPineForest = new BiomeConfigEBSilverPineForest();
        BiomeConfigEB.biomeConfigEBSilverPineHills = new BiomeConfigEBSilverPineHills();
        BiomeConfigEB.biomeConfigEBSnowyDesert = new BiomeConfigEBSnowyDesert();
        BiomeConfigEB.biomeConfigEBSnowyPlateau = new BiomeConfigEBSnowyPlateau();
        BiomeConfigEB.biomeConfigEBSnowyRanges = new BiomeConfigEBSnowyRanges();
        BiomeConfigEB.biomeConfigEBSnowyWastelands = new BiomeConfigEBSnowyWastelands();
        BiomeConfigEB.biomeConfigEBSteppe = new BiomeConfigEBSteppe();
        BiomeConfigEB.biomeConfigEBStoneCanyon = new BiomeConfigEBStoneCanyon();
        BiomeConfigEB.biomeConfigEBStoneCanyon2 = new BiomeConfigEBStoneCanyon2();
        BiomeConfigEB.biomeConfigEBTropicalArchipelago = new BiomeConfigEBTropicalArchipelago();
        BiomeConfigEB.biomeConfigEBTundra = new BiomeConfigEBTundra();
        BiomeConfigEB.biomeConfigEBVolcano = new BiomeConfigEBVolcano();
        BiomeConfigEB.biomeConfigEBVolcanoM = new BiomeConfigEBVolcanoM();
        BiomeConfigEB.biomeConfigEBWastelands = new BiomeConfigEBWastelands();
        BiomeConfigEB.biomeConfigEBWoodlandField = new BiomeConfigEBWoodlandField();
        BiomeConfigEB.biomeConfigEBWoodlandHills = new BiomeConfigEBWoodlandHills();
        BiomeConfigEB.biomeConfigEBWoodlandLake = new BiomeConfigEBWoodlandLake();
        BiomeConfigEB.biomeConfigEBWoodlandLakeEdge = new BiomeConfigEBWoodlandLakeEdge();
        BiomeConfigEB.biomeConfigEBWoodlands = new BiomeConfigEBWoodlands();
        BiomeConfigEB.biomeConfigEBXericSavanna = new BiomeConfigEBXericSavanna();
        BiomeConfigEB.biomeConfigEBXericShrubland = new BiomeConfigEBXericShrubland();
    }
    
    public static void initBiomeConfigsEBXL()
    {
        BiomeConfigEBXL.biomeConfigEBXLAlpine = new BiomeConfigEBXLAlpine();
        BiomeConfigEBXL.biomeConfigEBXLAutumnWoods = new BiomeConfigEBXLAutumnWoods();
        BiomeConfigEBXL.biomeConfigEBXLBirchForest = new BiomeConfigEBXLBirchForest();
        BiomeConfigEBXL.biomeConfigEBXLExtremeJungle = new BiomeConfigEBXLExtremeJungle();
        BiomeConfigEBXL.biomeConfigEBXLForestedHills = new BiomeConfigEBXLForestedHills();
        BiomeConfigEBXL.biomeConfigEBXLForestedIsland = new BiomeConfigEBXLForestedIsland();
        BiomeConfigEBXL.biomeConfigEBXLGlacier = new BiomeConfigEBXLGlacier();
        BiomeConfigEBXL.biomeConfigEBXLGreenHills = new BiomeConfigEBXLGreenHills();
        BiomeConfigEBXL.biomeConfigEBXLGreenSwamp = new BiomeConfigEBXLGreenSwamp();
        BiomeConfigEBXL.biomeConfigEBXLIceWasteland = new BiomeConfigEBXLIceWasteland();
        BiomeConfigEBXL.biomeConfigEBXLMarsh = new BiomeConfigEBXLMarsh();
        BiomeConfigEBXL.biomeConfigEBXLMeadow = new BiomeConfigEBXLMeadow();
        BiomeConfigEBXL.biomeConfigEBXLMiniJungle = new BiomeConfigEBXLMiniJungle();
        BiomeConfigEBXL.biomeConfigEBXLMountainDesert = new BiomeConfigEBXLMountainDesert();
        BiomeConfigEBXL.biomeConfigEBXLMountainRidge = new BiomeConfigEBXLMountainRidge();
        BiomeConfigEBXL.biomeConfigEBXLMountainTaiga = new BiomeConfigEBXLMountainTaiga();
        BiomeConfigEBXL.biomeConfigEBXLPineForest = new BiomeConfigEBXLPineForest();
        BiomeConfigEBXL.biomeConfigEBXLRainforest = new BiomeConfigEBXLRainforest();
        BiomeConfigEBXL.biomeConfigEBXLRedwoodForest = new BiomeConfigEBXLRedwoodForest();
        BiomeConfigEBXL.biomeConfigEBXLRedwoodLush = new BiomeConfigEBXLRedwoodLush();
        BiomeConfigEBXL.biomeConfigEBXLSavanna = new BiomeConfigEBXLSavanna();
        BiomeConfigEBXL.biomeConfigEBXLShrubland = new BiomeConfigEBXLShrubland();
        BiomeConfigEBXL.biomeConfigEBXLSnowForest = new BiomeConfigEBXLSnowForest();
        BiomeConfigEBXL.biomeConfigEBXLSnowyRainforest = new BiomeConfigEBXLSnowyRainforest();
        BiomeConfigEBXL.biomeConfigEBXLTemperateRainforest = new BiomeConfigEBXLTemperateRainforest();
        BiomeConfigEBXL.biomeConfigEBXLTundra = new BiomeConfigEBXLTundra();
        BiomeConfigEBXL.biomeConfigEBXLWasteland = new BiomeConfigEBXLWasteland();
        BiomeConfigEBXL.biomeConfigEBXLWoodlands = new BiomeConfigEBXLWoodlands();
    }
    
    public static void initBiomeConfigsHL()
    {
        BiomeConfigHL.biomeConfigHLAlps = new BiomeConfigHLAlps();
        BiomeConfigHL.biomeConfigHLAutumnForest = new BiomeConfigHLAutumnForest();
        BiomeConfigHL.biomeConfigHLBadlands = new BiomeConfigHLBadlands();
        BiomeConfigHL.biomeConfigHLBaldHill = new BiomeConfigHLBaldHill();
        BiomeConfigHL.biomeConfigHLBirchHills = new BiomeConfigHLBirchHills();
        BiomeConfigHL.biomeConfigHLBog = new BiomeConfigHLBog();
        BiomeConfigHL.biomeConfigHLCanyon = new BiomeConfigHLCanyon();
        BiomeConfigHL.biomeConfigHLCliffs = new BiomeConfigHLCliffs();
        BiomeConfigHL.biomeConfigHLDesertIsland = new BiomeConfigHLDesertIsland();
        BiomeConfigHL.biomeConfigHLDesertMountains = new BiomeConfigHLDesertMountains();
        BiomeConfigHL.biomeConfigHLDunes = new BiomeConfigHLDunes();
        BiomeConfigHL.biomeConfigHLEstuary = new BiomeConfigHLEstuary();
        BiomeConfigHL.biomeConfigHLFlyingMountains = new BiomeConfigHLFlyingMountains();
        BiomeConfigHL.biomeConfigHLForestIsland = new BiomeConfigHLForestIsland();
        BiomeConfigHL.biomeConfigHLGlacier = new BiomeConfigHLGlacier();
        BiomeConfigHL.biomeConfigHLHighlandsB = new BiomeConfigHLHighlandsB();
        BiomeConfigHL.biomeConfigHLJungleIsland = new BiomeConfigHLJungleIsland();
        BiomeConfigHL.biomeConfigHLLake = new BiomeConfigHLLake();
        BiomeConfigHL.biomeConfigHLLowlands = new BiomeConfigHLLowlands();
        BiomeConfigHL.biomeConfigHLMeadow = new BiomeConfigHLMeadow();
        BiomeConfigHL.biomeConfigHLMesa = new BiomeConfigHLMesa();
        BiomeConfigHL.biomeConfigHLOasis = new BiomeConfigHLOasis();
        BiomeConfigHL.biomeConfigHLOutback = new BiomeConfigHLOutback();
        BiomeConfigHL.biomeConfigHLPinelands = new BiomeConfigHLPinelands();
        BiomeConfigHL.biomeConfigHLRainforest = new BiomeConfigHLRainforest();
        BiomeConfigHL.biomeConfigHLRedwoodForest = new BiomeConfigHLRedwoodForest();
        BiomeConfigHL.biomeConfigHLRockIsland = new BiomeConfigHLRockIsland();
        BiomeConfigHL.biomeConfigHLRockMountains = new BiomeConfigHLRockMountains();
        BiomeConfigHL.biomeConfigHLSahel = new BiomeConfigHLSahel();
        BiomeConfigHL.biomeConfigHLSavannah = new BiomeConfigHLSavannah();
        BiomeConfigHL.biomeConfigHLShrubland = new BiomeConfigHLShrubland();
        BiomeConfigHL.biomeConfigHLSnowIsland = new BiomeConfigHLSnowIsland();
        BiomeConfigHL.biomeConfigHLSnowMountains = new BiomeConfigHLSnowMountains();
        BiomeConfigHL.biomeConfigHLSteppe = new BiomeConfigHLSteppe();
        BiomeConfigHL.biomeConfigHLTallPineForest = new BiomeConfigHLTallPineForest();
        BiomeConfigHL.biomeConfigHLTropicalIslands = new BiomeConfigHLTropicalIslands();
        BiomeConfigHL.biomeConfigHLTropics = new BiomeConfigHLTropics();
        BiomeConfigHL.biomeConfigHLTundra = new BiomeConfigHLTundra();
        BiomeConfigHL.biomeConfigHLValley = new BiomeConfigHLValley();
        BiomeConfigHL.biomeConfigHLVolcanoIsland = new BiomeConfigHLVolcanoIsland();
        BiomeConfigHL.biomeConfigHLWindyIsland = new BiomeConfigHLWindyIsland();
        BiomeConfigHL.biomeConfigHLWoodlands = new BiomeConfigHLWoodlands();
        BiomeConfigHL.biomeConfigHLWoodsMountains = new BiomeConfigHLWoodsMountains();
    }
    
    public static void initBiomeConfigsRW()
    {  
        BiomeConfigRW.biomeConfigRWBotanicalGarden = new BiomeConfigRWBotanicalGarden();
        BiomeConfigRW.biomeConfigRWMurica = new BiomeConfigRWMurica();
        BiomeConfigRW.biomeConfigRWMountainOfMadness = new BiomeConfigRWMountainOfMadness();
        BiomeConfigRW.biomeConfigRWOssuary = new BiomeConfigRWOssuary();
        BiomeConfigRW.biomeConfigRWRockCandyMountain = new BiomeConfigRWRockCandyMountain();
        BiomeConfigRW.biomeConfigRWShadowFen = new BiomeConfigRWShadowFen();
        BiomeConfigRW.biomeConfigRWSpookyForest = new BiomeConfigRWSpookyForest();
    }
    
    public static void initBiomeConfigsTC()
    {
        BiomeConfigTC.biomeConfigTCEerie = new BiomeConfigTCEerie();
        BiomeConfigTC.biomeConfigTCMagicalForest = new BiomeConfigTCMagicalForest();
        BiomeConfigTC.biomeConfigTCTaintedLand = new BiomeConfigTCTaintedLand();
    }
    
    public static void initBiomeConfigsLOM()
    {
        BiomeConfigLOM.biomeConfigLOMAntartica = new BiomeConfigLOMAntartica();
        BiomeConfigLOM.biomeConfigLOMTropicalBeach = new BiomeConfigLOMTropicalBeach();
    }

    public static void initBiomeConfigsVAMP()
    {
        BiomeConfigVAMP.biomeConfigVAMPVampireForest = new BiomeConfigVAMPVampireForest();
    }
    
    public static void initBiomeConfigsTOFU()
    {

        BiomeConfigTOFU.biomeConfigTOFULeekPlains = new BiomeConfigTOFULeekPlains();
        BiomeConfigTOFU.biomeConfigTOFUTofuBuildings = new BiomeConfigTOFUTofuBuildings();
        BiomeConfigTOFU.biomeConfigTOFUTofuExtremeHills = new BiomeConfigTOFUTofuExtremeHills();
        BiomeConfigTOFU.biomeConfigTOFUTofuExtremeHillsEdge = new BiomeConfigTOFUTofuExtremeHillsEdge();
        BiomeConfigTOFU.biomeConfigTOFUTofuForest = new BiomeConfigTOFUTofuForest();
        BiomeConfigTOFU.biomeConfigTOFUTofuForestHills = new BiomeConfigTOFUTofuForestHills();
        BiomeConfigTOFU.biomeConfigTOFUTofuPlainHills = new BiomeConfigTOFUTofuPlainHills();
        BiomeConfigTOFU.biomeConfigTOFUTofuPlains = new BiomeConfigTOFUTofuPlains();
        BiomeConfigTOFU.biomeConfigTOFUTofuRiver = new BiomeConfigTOFUTofuRiver();
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
