package rtg.init;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import rtg.RTGConfig;
import rtg.api.RTGAPI;
import rtg.api.util.UtilityClass;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACCoraliumInfestedSwamp;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklands;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsForest;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsHighland;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsMountains;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsPlains;
import rtg.world.biome.realistic.auxiliarybiomes.RealisticBiomeAUXForestedIsland;
import rtg.world.biome.realistic.auxiliarybiomes.RealisticBiomeAUXIceWasteland;
import rtg.world.biome.realistic.auxiliarybiomes.RealisticBiomeAUXMarsh;
import rtg.world.biome.realistic.auxiliarybiomes.RealisticBiomeAUXWasteland;
import rtg.world.biome.realistic.auxiliarybiomes.RealisticBiomeAUXWhiteForest;
import rtg.world.biome.realistic.betteragriculture.RealisticBiomeBAFarmlandBiome;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPAlps;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPAlpsFoothills;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBambooForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBayou;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBog;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBorealForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBrushland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPChaparral;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPCherryBlossomGrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPColdDesert;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPConiferousForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPCoralReef;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPCrag;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPDeadForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPDeadSwamp;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPEucalyptusForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPFen;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPFlowerField;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPFlowerIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGlacier;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGrassland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGravelBeach;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPHighland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPKelpForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLandOfLakes;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLavenderFields;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLushDesert;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLushSwamp;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMangrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMapleWoods;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMarsh;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMeadow;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMoor;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMountainFoothills;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMountainPeaks;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMysticGrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOasis;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOminousWoods;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOrchard;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOriginBeach;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOriginIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOutback;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOvergrownCliffs;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPPasture;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPPrairie;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPQuagmire;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPRainforest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPRedwoodForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSacredSprings;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSeasonalForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPShield;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPShrubland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSnowyConiferousForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSnowyForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSteppe;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTemperateRainforest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTropicalIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTropicalRainforest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTundra;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPVolcanicIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWasteland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWetland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWhiteBeach;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWoodland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPXericShrubland;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGAlliumFields;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGAlps;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGAmaranthFields;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGAncientForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGAspenForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBambooForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBaobabSavanna;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBayou;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBlueTaiga;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBluffMountains;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBog;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBorealForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGCanyons;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGChaparralLowlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGCherryGrove;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGCikaForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGConiferousForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGCyprusSwampland;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGDeadSea;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGDeciduousForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGDoverMountains;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGDunes;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGEbonyWoods;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGEnchantedForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGEucalyptusTropics;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGEvergreenTaiga;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGFrostyForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGFungalJungle;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGGiantBlueSpruceTaiga;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGGiantSeasonalSpruceTaiga;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGGlaciers;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGGlowshroomBayou;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGGrasslandPlateau;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGGreatLakes;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGGreatOakLowlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGJacarandaForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGLushDesert;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGMangroveMarshes;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGMapleForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGMeadow;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGOrchard;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGOutback;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGOutlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGPaperBirchForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGPineLowlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGPineMountains;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGPraire;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGPrairie;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGQuagmire;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGRedDesert;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGRedOakForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGRedOutlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGRedwoodTropics;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSavannaCanopy;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSeasonalBirchForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSeasonalDeciduous;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSeasonalForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSeasonalTaiga;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGShrublands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSkyrisHighlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSnowyConiferousForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSnowyEvergeenTaiga;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSnowyPineMountains;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGSonoranDesert;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGStellataPasture;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGStoneBrushlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGTropicalIslands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGTropicalMountains;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGTropicalRainforest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGTundra;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGWeepingWitchForest;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGWhisperingWoods;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGWoodlands;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGZelkovaForest;
import rtg.world.biome.realistic.bionisation.RealisticBiomeBIOInfectedForest;
import rtg.world.biome.realistic.buildcraft.RealisticBiomeBCDesertOilField;
import rtg.world.biome.realistic.buildcraft.RealisticBiomeBCOceanOilField;
import rtg.world.biome.realistic.candyworld.RealisticBiomeCWChocolateForest;
import rtg.world.biome.realistic.candyworld.RealisticBiomeCWCottonCandyPlains;
import rtg.world.biome.realistic.candyworld.RealisticBiomeCWGummySwamp;
import rtg.world.biome.realistic.defiledlands.RealisticBiomeDLDesertDefiled;
import rtg.world.biome.realistic.defiledlands.RealisticBiomeDLForestTenebra;
import rtg.world.biome.realistic.defiledlands.RealisticBiomeDLForestVilespine;
import rtg.world.biome.realistic.defiledlands.RealisticBiomeDLHillsDefiled;
import rtg.world.biome.realistic.defiledlands.RealisticBiomeDLIcePlainsDefiled;
import rtg.world.biome.realistic.defiledlands.RealisticBiomeDLPlainsDefiled;
import rtg.world.biome.realistic.defiledlands.RealisticBiomeDLSwampDefiled;
import rtg.world.biome.realistic.douglasforest.RealisticBiomeDFDouglasForest;
import rtg.world.biome.realistic.douglasforest.RealisticBiomeDFMapleForest;
import rtg.world.biome.realistic.environs.RealisticBiomeENVDeadOakForest;
import rtg.world.biome.realistic.environs.RealisticBiomeENVDeadOakForestHills;
import rtg.world.biome.realistic.environs.RealisticBiomeENVEmeraldCliffs;
import rtg.world.biome.realistic.environs.RealisticBiomeENVEndForest;
import rtg.world.biome.realistic.environs.RealisticBiomeENVEndShrubland;
import rtg.world.biome.realistic.environs.RealisticBiomeENVExtremeJungle;
import rtg.world.biome.realistic.environs.RealisticBiomeENVIcyHills;
import rtg.world.biome.realistic.environs.RealisticBiomeENVMoor;
import rtg.world.biome.realistic.environs.RealisticBiomeENVPineTaiga;
import rtg.world.biome.realistic.environs.RealisticBiomeENVPineTaigaHills;
import rtg.world.biome.realistic.environs.RealisticBiomeENVSilkglades;
import rtg.world.biome.realistic.environs.RealisticBiomeENVSparseEndForest;
import rtg.world.biome.realistic.environs.RealisticBiomeENVStoneFlats;
import rtg.world.biome.realistic.environs.RealisticBiomeENVTallOakForest;
import rtg.world.biome.realistic.environs.RealisticBiomeENVTallOakForestHills;
import rtg.world.biome.realistic.environs.RealisticBiomeENVTallOakWetland;
import rtg.world.biome.realistic.environs.RealisticBiomeENVWastelandEroded;
import rtg.world.biome.realistic.environs.RealisticBiomeENVWastelandSpikes;
import rtg.world.biome.realistic.explorercraft.RealisticBiomeECBambooForest;
import rtg.world.biome.realistic.explorercraft.RealisticBiomeECForestedMountain;
import rtg.world.biome.realistic.explorercraft.RealisticBiomeECSnowdonia;
import rtg.world.biome.realistic.floricraft.RealisticBiomeFLORIRoseLand;
import rtg.world.biome.realistic.floricraft.RealisticBiomeFLORITulipLand;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYRECrimsonGrove;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYREMegaMountains;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYREMushroomGrove;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYRERedDesert;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYRERedDesertHills;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYRERockyWasteland;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYRETropicalLakes;
import rtg.world.biome.realistic.fyrecraft.RealisticBiomeFYREVolcanicWasteland;
import rtg.world.biome.realistic.gravityfalls.RealisticBiomeGFGravityFalls;
import rtg.world.biome.realistic.gravityfalls.RealisticBiomeGFNightmareRealm;
import rtg.world.biome.realistic.iceandfire.RealisticBiomeIAFGlacier;
import rtg.world.biome.realistic.mistbiomes.RealisticBiomeMBColdMistTaiga;
import rtg.world.biome.realistic.mistbiomes.RealisticBiomeMBMistDesert;
import rtg.world.biome.realistic.mistbiomes.RealisticBiomeMBMistForest;
import rtg.world.biome.realistic.mistbiomes.RealisticBiomeMBMistMushroomIsland;
import rtg.world.biome.realistic.mistbiomes.RealisticBiomeMBMistPlains;
import rtg.world.biome.realistic.mistbiomes.RealisticBiomeMBMistSwamp;
import rtg.world.biome.realistic.mistbiomes.RealisticBiomeMBMistTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTAegeanArchipelago;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTAliumMeadow;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTAlps;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTAutumnForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTAutumnForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTAutumnTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTAutumnTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTBirchHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTBlackBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTBlossomForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTBlossomForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTBlueTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTBlueTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTBrownBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTClayland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTCliffs;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdBirchForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdBirchForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdBlueTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdBlueTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdDesert;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdFlowerForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdFlowerForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdMegaBlueTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdMegaBlueTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdMegaSpruceTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdMegaSpruceTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdMegaTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdMegaTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdRoofedForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTColdRoofedForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTEstuary;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTFen;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTFungalJungle;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTFungalJungleHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTGlacier;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTGrassHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTGreenMixedForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTGreenMixedForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTGreenSwamp;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTHeath;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTHighland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTHotspring;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTIcyTundra;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTIronBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTLake;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTLowland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTLushDesert;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMapleForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMapleForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMeadow;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMegaAutumnTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMegaAutumnTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMegaBlueTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMegaBlueTaigaHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMegaMapleForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMegaMapleForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMixedForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMixedForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMonsoonForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMoorland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTMountains;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTOakForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTOakForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTOlivineBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTOrangeBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTPineland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTPinkBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTPoppyMeadow;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTPurpleBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTRedSandDune;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTRockland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTRockyTaiga;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTRoyalForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTRoyalForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTSahel;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTSandDune;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTSandyMountains;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTScrubland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTShrubland;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTSnowdune;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTStoneFields;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTStoneMountains;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTTallOakForest;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTTallOakForestHills;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTTropicalDesert;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTTundra;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTWhiteBeach;
import rtg.world.biome.realistic.novamterram.RealisticBiomeNTWhiteOrchard;
import rtg.world.biome.realistic.odioita.RealisticBiomeODIOOrangeBlancoaForest;
import rtg.world.biome.realistic.odioita.RealisticBiomeODIOPinkBlancoaForest;
import rtg.world.biome.realistic.odioita.RealisticBiomeODIORedBlancoaForest;
import rtg.world.biome.realistic.odioita.RealisticBiomeODIOWhiteBlancoaForest;
import rtg.world.biome.realistic.plants.RealisticBiomePLANTSCrystalForest;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJAspenGrove;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJBaobabFields;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJBlossomingFields;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJBorealForest;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJOvergrownSpires;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJPraire;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJRedwoodPeaks;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJRedwoods;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJSnowyBorealForest;
import rtg.world.biome.realistic.projectvibrantjourneys.RealisticBiomePVJWillowSwamp;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWBambooMarsh;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWBirchAutumnForest;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWBlueOakForest;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWBombonaBeach;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWBurOakForest;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWEmperorRidge;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWFlatlandThicket;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWSilverBirchHills;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWSpinyForest;
import rtg.world.biome.realistic.realworld.RealisticBiomeRWSpruceMountains;
import rtg.world.biome.realistic.redwoods.RealisticBiomeREDAlpine;
import rtg.world.biome.realistic.redwoods.RealisticBiomeREDLushRedwoodForest;
import rtg.world.biome.realistic.redwoods.RealisticBiomeREDRedwoodForest;
import rtg.world.biome.realistic.redwoods.RealisticBiomeREDSnowyRainforest;
import rtg.world.biome.realistic.redwoods.RealisticBiomeREDTemperateRainforest;
import rtg.world.biome.realistic.rockhounding.RealisticBiomeRHWhiteSands;
import rtg.world.biome.realistic.spookybiomes.RealisticBiomeSBGhostlyForest;
import rtg.world.biome.realistic.spookybiomes.RealisticBiomeSBWitchwoodForest;
import rtg.world.biome.realistic.sugiforest.RealisticBiomeSFSugiForest;
import rtg.world.biome.realistic.terscraft.RealisticBiomeTERSBiomeDemonite;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCEerie;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCMagicalForest;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVAridHighland;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVAutumnalWoodedHills;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVAutumnalWoods;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVBadlands;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVBirchForestedHills;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVCliffs;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVDesertShrubland;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVForestedHills;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVGlacier;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVGlacierSpikes;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVGreenSwamp;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVLushHills;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVMeadow;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVMiniJungle;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVMountainousDesert;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVRedDesert;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVRockyPlains;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVRockyPlateau;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVSnowyConiferousForest;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVTemperateRainforest;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVThicket;
import rtg.world.biome.realistic.traverse.RealisticBiomeTRAVWoodlands;
import rtg.world.biome.realistic.vampirism.RealisticBiomeVAMPVampireForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBeach;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForestHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForestHillsM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForestM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdBeach;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdTaigaM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDeepOcean;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDesert;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDesertHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDesertM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsEdge;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsPlus;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsPlusM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaFlowerForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaForestHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaFrozenOcean;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaFrozenRiver;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaIceMountains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaIcePlains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaIcePlainsSpikes;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungle;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleEdge;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleEdgeM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMegaSpruceTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMegaTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMegaTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesa;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaBryce;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateau;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateauF;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateauFM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateauM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMushroomIsland;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMushroomIslandShore;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaOcean;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaPlains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRedwoodTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRiver;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRoofedForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRoofedForestM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavanna;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavannaM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavannaPlateau;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavannaPlateauM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaStoneBeach;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSunflowerPlains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSwampland;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSwamplandM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaTaigaM;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEArchipelago;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEAustralianOutback;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEBarelands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEBluff;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEBrushlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEBrushlandsHills;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEBush;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEBushlandHills;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEDenseBrushlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEExtremeSouthernAlps;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEForestedCanyonPillars;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEForestedCanyonPlateau;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEForestedCanyonPlateauM;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEGhostForest;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEGrassyFen;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEGrassyMarshland;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEHighInhabitedWoodlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEHighWoodlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEHighlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEHotBrushlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOELowSpruceWoodlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOELowTallWoodlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOELowWoodlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOELowlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOELowlandsChapparal;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEMire;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEMoorlands;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOENorthernAlps;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOENorthernSnowAlps;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOENorthernSnowSubalpineAlps;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOENorthernSubalpineAlps;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEOrchidFields;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOERedSandDunes;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESandDunes;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESandDunesM;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESandDunesOasis;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESnowRocks;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESnowRocksMountains;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESnowRocksPlateau;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESouthernAlps;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOESouthernAlpsSubalpine;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEStonyReef;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOETropicalJungle;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWastelandFlats;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWastelandFlatsOasis;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWastelandFlatsSlopes;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWastelandFlatsTrees;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWhiteOaks;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWhiteOaksHills;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWhiteWoods;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWhiteWoodsHills;
import rtg.world.biome.realistic.zoesteria.RealisticBiomeZOEWoodlandsHills;


@UtilityClass
public final class BiomeInit {

    private BiomeInit() {

    }

    public static void init() {

        init_minecraft();

        if (Mods.abyssalcraft.isLoaded()) {
            init_abyssalcraft();
        }

        if (Mods.auxbiomes.isLoaded()) {
            init_auxiliarybiomes();
        }

        if (Mods.betteragriculture.isLoaded()) {
            init_betteragriculture();
        }

        if (Mods.biomesoplenty.isLoaded()) {
            init_biomesoplenty();
        }

        if (Mods.byg.isLoaded()) {
            init_biomesyougo();
        }

        if (Mods.bionisation3.isLoaded()) {
            init_bionisation();
        }

        if (Mods.buildcraftenergy.isLoaded()) {
            init_buildcraft();
        }

        if (Mods.candymod.isLoaded()) {
            init_candymod();
        }

        if (Mods.defiledlands.isLoaded()) {
            init_defiledlands();
        }

        if (Mods.douglas_forest.isLoaded()) {
            init_douglasforest();
        }

        if (Mods.environs.isLoaded()) {
            init_environs();
        }

        if (Mods.explorercraft.isLoaded()) {
            init_explorercraft();
        }

        if (Mods.floricraft.isLoaded()) {
            init_floricraft();
        }

        if (Mods.fyrecraft.isLoaded()) {
            init_fyrecraft();
        }

        if (Mods.gravityfalls.isLoaded()) {
            init_gravityfalls();
        }

        if (Mods.iceandfire.isLoaded()) {
            init_iceandfire();
        }

        if (Mods.mistbiomes.isLoaded()) {
            init_mistbiomes();
        }

        if (Mods.nt.isLoaded()) {
            //init_novamterram();
        }

        if (Mods.odioitamod.isLoaded()) {
            init_odioitamod();
        }

        if (Mods.plants2.isLoaded()) {
            init_plants();
        }

        if (Mods.pvj.isLoaded()) {
            init_pvj();
        }

        if (Mods.realworld.isLoaded()) {
            init_realworld();
        }

        if (Mods.redwoods.isLoaded()) {
            init_redwoods();
        }

        if (Mods.rockhounding_surface.isLoaded()) {
            init_rockhounding();
        }

        if (Mods.spookybiomes.isLoaded()) {
            init_spookybiomes();
        }

        if (Mods.sugiforest.isLoaded()) {
            init_sugiforest();
        }

        if (Mods.terscraft.isLoaded()) {
            init_terscraft();
        }

        if (Mods.thaumcraft.isLoaded()) {
            init_thaumcraft();
        }

        if (Mods.traverse.isLoaded()) {
            init_traverse();
        }

        if (Mods.valoegheses_be.isLoaded()) {
            init_zoesteria();
        }

        if (Mods.vampirism.isLoaded()) {
            init_vampirism();
        }

        // This must be done after all biomes have been initialised so that they are all available.
        RTGAPI.initPatchBiome(RTGConfig.patchBiome());
    }

    public static void preInit() {
        RTGAPI.RTG_BIOMES.addBiomes(
                RealisticBiomeBase.RiverType.NORMAL.setRTGBiome(new RealisticBiomeVanillaRiver()),
                RealisticBiomeBase.RiverType.FROZEN.setRTGBiome(new RealisticBiomeVanillaFrozenRiver()),
                RealisticBiomeBase.BeachType.NORMAL.setRTGBiome(new RealisticBiomeVanillaBeach()),
                RealisticBiomeBase.BeachType.STONE.setRTGBiome(new RealisticBiomeVanillaStoneBeach()),
                RealisticBiomeBase.BeachType.COLD.setRTGBiome(new RealisticBiomeVanillaColdBeach())
        );
    }

    private static void init_minecraft() {
        // vanilla rivers and beaches are initialised to enum fields during #preInit
        RTGAPI.RTG_BIOMES.addBiomes(
                new RealisticBiomeVanillaBirchForest(),
                new RealisticBiomeVanillaBirchForestHills(),
                new RealisticBiomeVanillaBirchForestHillsM(),
                new RealisticBiomeVanillaBirchForestM(),
                new RealisticBiomeVanillaColdTaiga(),
                new RealisticBiomeVanillaColdTaigaHills(),
                new RealisticBiomeVanillaColdTaigaM(),
                new RealisticBiomeVanillaDeepOcean(),
                new RealisticBiomeVanillaDesert(),
                new RealisticBiomeVanillaDesertHills(),
                new RealisticBiomeVanillaDesertM(),
                new RealisticBiomeVanillaExtremeHills(),
                new RealisticBiomeVanillaExtremeHillsEdge(),
                new RealisticBiomeVanillaExtremeHillsM(),
                new RealisticBiomeVanillaExtremeHillsPlus(),
                new RealisticBiomeVanillaExtremeHillsPlusM(),
                new RealisticBiomeVanillaFlowerForest(),
                new RealisticBiomeVanillaForest(),
                new RealisticBiomeVanillaForestHills(),
                new RealisticBiomeVanillaFrozenOcean(),
                new RealisticBiomeVanillaIceMountains(),
                new RealisticBiomeVanillaIcePlains(),
                new RealisticBiomeVanillaIcePlainsSpikes(),
                new RealisticBiomeVanillaJungle(),
                new RealisticBiomeVanillaJungleEdge(),
                new RealisticBiomeVanillaJungleEdgeM(),
                new RealisticBiomeVanillaJungleHills(),
                new RealisticBiomeVanillaJungleM(),
                new RealisticBiomeVanillaMegaSpruceTaiga(),
                new RealisticBiomeVanillaMegaTaiga(),
                new RealisticBiomeVanillaMegaTaigaHills(),
                new RealisticBiomeVanillaMesa(),
                new RealisticBiomeVanillaMesaBryce(),
                new RealisticBiomeVanillaMesaPlateau(),
                new RealisticBiomeVanillaMesaPlateauF(),
                new RealisticBiomeVanillaMesaPlateauFM(),
                new RealisticBiomeVanillaMesaPlateauM(),
                new RealisticBiomeVanillaMushroomIsland(),
                new RealisticBiomeVanillaMushroomIslandShore(),
                new RealisticBiomeVanillaOcean(),
                new RealisticBiomeVanillaPlains(),
                new RealisticBiomeVanillaRedwoodTaigaHills(),
                new RealisticBiomeVanillaRoofedForest(),
                new RealisticBiomeVanillaRoofedForestM(),
                new RealisticBiomeVanillaSavanna(),
                new RealisticBiomeVanillaSavannaM(),
                new RealisticBiomeVanillaSavannaPlateau(),
                new RealisticBiomeVanillaSavannaPlateauM(),
                new RealisticBiomeVanillaSunflowerPlains(),
                new RealisticBiomeVanillaSwampland(),
                new RealisticBiomeVanillaSwamplandM(),
                new RealisticBiomeVanillaTaiga(),
                new RealisticBiomeVanillaTaigaHills(),
                new RealisticBiomeVanillaTaigaM()
        );
    }

    private static void init_abyssalcraft() {

        String modid = Mods.abyssalcraft.name();

        if (isBiomePresent(modid, "coralium_infested_swamp")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACCoraliumInfestedSwamp());
        }
        if (isBiomePresent(modid, "darklands")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklands());
        }
        if (isBiomePresent(modid, "darklands_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsForest());
        }
        if (isBiomePresent(modid, "darklands_hills")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsHighland());
        }
        if (isBiomePresent(modid, "darklands_mountains")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsMountains());
        }
        if (isBiomePresent(modid, "darklands_plains")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsPlains());
        }
    }

    private static void init_auxiliarybiomes() {

        String modid = Mods.auxbiomes.name();
        Biome biome;
        ResourceLocation marsh = new ResourceLocation(modid, "marsh");
        ResourceLocation wasteland = new ResourceLocation(modid, "wasteland");
        ResourceLocation ice_wasteland = new ResourceLocation(modid, "ice_wasteland");
        ResourceLocation forested_island = new ResourceLocation(modid, "forested_island");
        ResourceLocation white_forest = new ResourceLocation(modid, "white_forest");

        if ((biome = Biome.REGISTRY.getObject(marsh)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeAUXMarsh(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(wasteland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeAUXWasteland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(ice_wasteland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeAUXIceWasteland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forested_island)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeAUXForestedIsland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(white_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeAUXWhiteForest(biome));
        }
    }

    private static void init_betteragriculture() {

        String modid = Mods.betteragriculture.name();
        Biome biome;
        ResourceLocation farmlandbiome = new ResourceLocation(modid, "farmlandbiome");

        if ((biome = Biome.REGISTRY.getObject(farmlandbiome)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBAFarmlandBiome(biome));
        }
    }

    private static void init_biomesoplenty() {

        String modid = Mods.biomesoplenty.name();

        if (isBiomePresent(modid, "alps")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPAlps());
        }
        if (isBiomePresent(modid, "alps_foothills")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPAlpsFoothills());
        }
        if (isBiomePresent(modid, "bamboo_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBambooForest());
        }
        if (isBiomePresent(modid, "bayou")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBayou());
        }
        if (isBiomePresent(modid, "bog")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBog());
        }
        if (isBiomePresent(modid, "boreal_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBorealForest());
        }
        if (isBiomePresent(modid, "brushland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBrushland());
        }
        if (isBiomePresent(modid, "chaparral")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPChaparral());
        }
        if (isBiomePresent(modid, "cherry_blossom_grove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCherryBlossomGrove());
        }
        if (isBiomePresent(modid, "cold_desert")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPColdDesert());
        }
        if (isBiomePresent(modid, "coniferous_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPConiferousForest());
        }
        if (isBiomePresent(modid, "coral_reef")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCoralReef());
        }
        if (isBiomePresent(modid, "crag")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCrag());
        }
        if (isBiomePresent(modid, "dead_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadForest());
        }
        if (isBiomePresent(modid, "dead_swamp")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadSwamp());
        }
        if (isBiomePresent(modid, "eucalyptus_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPEucalyptusForest());
        }
        if (isBiomePresent(modid, "fen")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFen());
        }
        if (isBiomePresent(modid, "flower_field")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerField());
        }
        if (isBiomePresent(modid, "flower_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerIsland());
        }
        if (isBiomePresent(modid, "glacier")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGlacier());
        }
        if (isBiomePresent(modid, "grassland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrassland());
        }
        if (isBiomePresent(modid, "gravel_beach")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGravelBeach());
        }
        if (isBiomePresent(modid, "grove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrove());
        }
        if (isBiomePresent(modid, "highland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPHighland());
        }
        if (isBiomePresent(modid, "kelp_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPKelpForest());
        }
        if (isBiomePresent(modid, "land_of_lakes")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLandOfLakes());
        }
        if (isBiomePresent(modid, "lavender_fields")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLavenderFields());
        }
        if (isBiomePresent(modid, "lush_desert")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushDesert());
        }
        if (isBiomePresent(modid, "lush_swamp")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushSwamp());
        }
        if (isBiomePresent(modid, "mangrove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMangrove());
        }
        if (isBiomePresent(modid, "maple_woods")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMapleWoods());
        }
        if (isBiomePresent(modid, "marsh")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMarsh());
        }
        if (isBiomePresent(modid, "meadow")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMeadow());
        }
        if (isBiomePresent(modid, "moor")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMoor());
        }
        if (isBiomePresent(modid, "mountain")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainPeaks());
        }
        if (isBiomePresent(modid, "mountain_foothills")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainFoothills());
        }
        if (isBiomePresent(modid, "mystic_grove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMysticGrove());
        }
        if (isBiomePresent(modid, "oasis")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOasis());
        }
        if (isBiomePresent(modid, "ominous_woods")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOminousWoods());
        }
        if (isBiomePresent(modid, "orchard")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOrchard());
        }
        if (isBiomePresent(modid, "origin_beach")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOriginBeach());
        }
        if (isBiomePresent(modid, "origin_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOriginIsland());
        }
        if (isBiomePresent(modid, "outback")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOutback());
        }
        if (isBiomePresent(modid, "overgrown_cliffs")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOvergrownCliffs());
        }
        if (isBiomePresent(modid, "pasture")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPPasture());
        }
        if (isBiomePresent(modid, "prairie")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPPrairie());
        }
        if (isBiomePresent(modid, "quagmire")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPQuagmire());
        }
        if (isBiomePresent(modid, "rainforest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRainforest());
        }
        if (isBiomePresent(modid, "redwood_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRedwoodForest());
        }
        if (isBiomePresent(modid, "sacred_springs")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSacredSprings());
        }
        if (isBiomePresent(modid, "seasonal_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSeasonalForest());
        }
        if (isBiomePresent(modid, "shield")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShield());
        }
        if (isBiomePresent(modid, "shrubland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShrubland());
        }
        if (isBiomePresent(modid, "snowy_coniferous_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyConiferousForest());
        }
        if (isBiomePresent(modid, "snowy_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyForest());
        }
        if (isBiomePresent(modid, "steppe")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSteppe());
        }
        if (isBiomePresent(modid, "temperate_rainforest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTemperateRainforest());
        }
        if (isBiomePresent(modid, "tropical_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalIsland());
        }
        if (isBiomePresent(modid, "tropical_rainforest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalRainforest());
        }
        if (isBiomePresent(modid, "tundra")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTundra());
        }
        if (isBiomePresent(modid, "volcanic_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPVolcanicIsland());
        }
        if (isBiomePresent(modid, "wasteland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWasteland());
        }
        if (isBiomePresent(modid, "wetland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWetland());
        }
        if (isBiomePresent(modid, "white_beach")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWhiteBeach());
        }
        if (isBiomePresent(modid, "woodland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWoodland());
        }
        if (isBiomePresent(modid, "xeric_shrubland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPXericShrubland());
        }
    }

    private static void init_biomesyougo() {

        String modid = Mods.byg.name();
        Biome biome;
        //ResourceLocation babyssalbog = new ResourceLocation(modid, "babyssalbog");
        ResourceLocation balliumfields = new ResourceLocation(modid, "balliumfields");
        ResourceLocation balps = new ResourceLocation(modid, "balps");
        ResourceLocation bamaranth_fields = new ResourceLocation(modid, "bamaranth_fields");
        ResourceLocation bancientforest = new ResourceLocation(modid, "bancientforest");
        ResourceLocation baspenforest = new ResourceLocation(modid, "baspenforest");
        //ResourceLocation bastralisle = new ResourceLocation(modid, "bastralisle");
        ResourceLocation bbambooforest = new ResourceLocation(modid, "bbambooforest");
        ResourceLocation bbaobabsavanna = new ResourceLocation(modid, "bbaobabsavanna");
        ResourceLocation bbayou = new ResourceLocation(modid, "bbayou");
        ResourceLocation bbluetaiga = new ResourceLocation(modid, "bbluetaiga");
        ResourceLocation bbluff_mountains = new ResourceLocation(modid, "bbluff_mountains");
        ResourceLocation bbog = new ResourceLocation(modid, "bbog");
        ResourceLocation bborealforest = new ResourceLocation(modid, "bborealforest");
        ResourceLocation bcanyons = new ResourceLocation(modid, "bcanyons");
        ResourceLocation bchaparrallowlands = new ResourceLocation(modid, "bchaparrallowlands");
        ResourceLocation bcherrygrove = new ResourceLocation(modid, "bcherrygrove");
        ResourceLocation bcikaforest = new ResourceLocation(modid, "bcikaforest");
        ResourceLocation bconiferousforest = new ResourceLocation(modid, "bconiferousforest");
        //ResourceLocation bcosmicocean = new ResourceLocation(modid, "bcosmicocean");
        ResourceLocation bcypress_swamplands = new ResourceLocation(modid, "bcypress_swamplands");
        ResourceLocation bdeadsea = new ResourceLocation(modid, "bdeadsea");
        ResourceLocation bdeciduousforest = new ResourceLocation(modid, "bdeciduousforest");
        ResourceLocation bdovermoutains = new ResourceLocation(modid, "bdovermoutains");
        ResourceLocation bdunes = new ResourceLocation(modid, "bdunes");
        ResourceLocation bebonywoods = new ResourceLocation(modid, "bebonywoods");
        ResourceLocation benchantedforest = new ResourceLocation(modid, "benchantedforest");
        ResourceLocation beucalyptustropics = new ResourceLocation(modid, "beucalyptustropics");
        ResourceLocation bevergreentaiga = new ResourceLocation(modid, "bevergreentaiga");
        ResourceLocation bfrostyforest = new ResourceLocation(modid, "bfrostyforest");
        ResourceLocation bfungaljungle = new ResourceLocation(modid, "bfungaljungle");
        ResourceLocation bgiant_blue_spruce_taiga = new ResourceLocation(modid, "bgiant_blue_spruce_taiga");
        ResourceLocation bgiant_seasonal_spruce_taiga = new ResourceLocation(modid, "bgiant_seasonal_spruce_taiga");
        ResourceLocation bglaciers = new ResourceLocation(modid, "bglaciers");
        ResourceLocation bglowshroombayou = new ResourceLocation(modid, "bglowshroombayou");
        ResourceLocation bgrasslandplateau = new ResourceLocation(modid, "bgrasslandplateau");
        ResourceLocation bgreatlakes = new ResourceLocation(modid, "bgreatlakes");
        ResourceLocation bgreatoaklowlands = new ResourceLocation(modid, "bgreatoaklowlands");
        ResourceLocation bjacarandaforest = new ResourceLocation(modid, "bjacarandaforest");
        ResourceLocation blushdesert = new ResourceLocation(modid, "blushdesert");
        ResourceLocation bmangrovemarshes = new ResourceLocation(modid, "bmangrovemarshes");
        ResourceLocation bmapleforest = new ResourceLocation(modid, "bmapleforest");
        ResourceLocation bmeadow = new ResourceLocation(modid, "bmeadow");
        ResourceLocation borchard = new ResourceLocation(modid, "borchard");
        ResourceLocation boutback = new ResourceLocation(modid, "boutback");
        ResourceLocation boutlands = new ResourceLocation(modid, "boutlands");
        ResourceLocation bpaperbirchforest = new ResourceLocation(modid, "bpaperbirchforest");
        ResourceLocation bpine_lowlands = new ResourceLocation(modid, "bpine_lowlands");
        ResourceLocation bpinemountains = new ResourceLocation(modid, "bpinemountains");
        ResourceLocation bpraire = new ResourceLocation(modid, "bpraire");
        ResourceLocation bprairie = new ResourceLocation(modid, "bprairie");
        ResourceLocation bquagmire = new ResourceLocation(modid, "bquagmire");
        ResourceLocation breddesert = new ResourceLocation(modid, "breddesert");
        ResourceLocation bredoakforest = new ResourceLocation(modid, "bredoakforest");
        ResourceLocation bred_outlands = new ResourceLocation(modid, "bred_outlands");
        ResourceLocation bredwoodtropics = new ResourceLocation(modid, "bredwoodtropics");
        ResourceLocation bsavannacanopy = new ResourceLocation(modid, "bsavannacanopy");
        ResourceLocation bseasonalbirchforest = new ResourceLocation(modid, "bseasonalbirchforest");
        ResourceLocation bseasonaldeciduous = new ResourceLocation(modid, "bseasonaldeciduous");
        ResourceLocation bseasonalforest = new ResourceLocation(modid, "bseasonalforest");
        ResourceLocation bseasonaltaiga = new ResourceLocation(modid, "bseasonaltaiga");
        //ResourceLocation bshattereddesert = new ResourceLocation(modid, "bshattereddesert");
        ResourceLocation bshrublands = new ResourceLocation(modid, "bshrublands");
        ResourceLocation bstone_brushlands = new ResourceLocation(modid, "bstone_brushlands");
        ResourceLocation bskyrishighlands = new ResourceLocation(modid, "bskyrishighlands");
        ResourceLocation bsnowyconiferousforest = new ResourceLocation(modid, "bsnowyconiferousforest");
        ResourceLocation bsnowyevergeentaiga = new ResourceLocation(modid, "bsnowyevergeentaiga");
        ResourceLocation bsnowypinemountains = new ResourceLocation(modid, "bsnowypinemountains");
        ResourceLocation bsonorandesert = new ResourceLocation(modid, "bsonorandesert");
        ResourceLocation bstellatapasture = new ResourceLocation(modid, "bstellatapasture");
        ResourceLocation btropical_islands = new ResourceLocation(modid, "btropical_islands");
        ResourceLocation btropicalmountains = new ResourceLocation(modid, "btropicalmountains");
        ResourceLocation btropicalrainforest = new ResourceLocation(modid, "btropicalrainforest");
        ResourceLocation btundra = new ResourceLocation(modid, "btundra");
        ResourceLocation bweepingwitchforest = new ResourceLocation(modid, "bweepingwitchforest");
        ResourceLocation bwhisperingwoods = new ResourceLocation(modid, "bwhisperingwoods");
        ResourceLocation bwoodlands = new ResourceLocation(modid, "bwoodlands");
        ResourceLocation bzelkovaforest = new ResourceLocation(modid, "bzelkovaforest");

//        if ((biome = Biome.REGISTRY.getObject(babyssalbog)) != null) {
//            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGAbyssalBog(biome));
//        }
        if ((biome = Biome.REGISTRY.getObject(balliumfields)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGAlliumFields(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(balps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bamaranth_fields)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGAmaranthFields(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bancientforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGAncientForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(baspenforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGAspenForest(biome));
        }
//        if ((biome = Biome.REGISTRY.getObject(bastralisle)) != null) {
//            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGAstralIsle(biome));
//        }
        if ((biome = Biome.REGISTRY.getObject(bbambooforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGBambooForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bbaobabsavanna)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGBaobabSavanna(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bbayou)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGBayou(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bbluetaiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGBlueTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bbluff_mountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGBluffMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bbog)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGBog(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bborealforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGBorealForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bcanyons)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGCanyons(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bchaparrallowlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGChaparralLowlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bcherrygrove)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGCherryGrove(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bcikaforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGCikaForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bconiferousforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGConiferousForest(biome));
        }
//        if ((biome = Biome.REGISTRY.getObject(bcosmicocean)) != null) {
//            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGCosmicOcean(biome));
//        }
        if ((biome = Biome.REGISTRY.getObject(bcypress_swamplands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGCyprusSwampland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bdeadsea)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGDeadSea(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bdeciduousforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGDeciduousForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bdovermoutains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGDoverMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bdunes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGDunes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bebonywoods)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGEbonyWoods(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(benchantedforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGEnchantedForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(beucalyptustropics)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGEucalyptusTropics(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bevergreentaiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGEvergreenTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bfrostyforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGFrostyForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bfungaljungle)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGFungalJungle(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bgiant_blue_spruce_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGGiantBlueSpruceTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bgiant_seasonal_spruce_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGGiantSeasonalSpruceTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bglaciers)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGGlaciers(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bglowshroombayou)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGGlowshroomBayou(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bgrasslandplateau)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGGrasslandPlateau(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bgreatlakes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGGreatLakes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bgreatoaklowlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGGreatOakLowlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bjacarandaforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGJacarandaForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(blushdesert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGLushDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bmangrovemarshes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGMangroveMarshes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bmapleforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGMapleForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bmeadow)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGMeadow(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(borchard)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGOrchard(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(boutback)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGOutback(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(boutlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGOutlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bpaperbirchforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGPaperBirchForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bpine_lowlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGPineLowlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bpinemountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGPineMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bpraire)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGPraire(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bprairie)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGPrairie(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bquagmire)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGQuagmire(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(breddesert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGRedDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bredoakforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGRedOakForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bred_outlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGRedOutlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bredwoodtropics)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGRedwoodTropics(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bsavannacanopy)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSavannaCanopy(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bseasonalbirchforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSeasonalBirchForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bseasonaldeciduous)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSeasonalDeciduous(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bseasonalforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSeasonalForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bseasonaltaiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSeasonalTaiga(biome));
        }
//        if ((biome = Biome.REGISTRY.getObject(bshattereddesert)) != null) {
//            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGShatteredDesert(biome));
//        }
        if ((biome = Biome.REGISTRY.getObject(bshrublands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGShrublands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bskyrishighlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSkyrisHighlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bsnowyconiferousforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSnowyConiferousForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bsnowyevergeentaiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSnowyEvergeenTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bsnowypinemountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSnowyPineMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bsonorandesert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGSonoranDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bstellatapasture)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGStellataPasture(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bstone_brushlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGStoneBrushlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(btropical_islands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGTropicalIslands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(btropicalmountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGTropicalMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(btropicalrainforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGTropicalRainforest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(btundra)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGTundra(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bweepingwitchforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGWeepingWitchForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bwhisperingwoods)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGWhisperingWoods(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bwoodlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGWoodlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bzelkovaforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBYGZelkovaForest(biome));
        }
    }

    private static void init_bionisation() {

        String modid = Mods.bionisation3.name();
        Biome biome;
        ResourceLocation infected_forest = new ResourceLocation(modid, "infected_forest");

        if ((biome = Biome.REGISTRY.getObject(infected_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBIOInfectedForest(biome));
        }
    }

    private static void init_buildcraft() {

        String modid = Mods.buildcraftenergy.name();
        Biome biome;
        ResourceLocation bc_oil_desert = new ResourceLocation(modid, "oil_desert");
        ResourceLocation bc_oil_ocean = new ResourceLocation(modid, "oil_ocean");

        if ((biome = Biome.REGISTRY.getObject(bc_oil_desert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBCDesertOilField(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bc_oil_ocean)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBCOceanOilField(biome));
        }
    }

    private static void init_candymod() {

        String modid = Mods.candymod.name();
        Biome biome;
        ResourceLocation biome_cotton_candy = new ResourceLocation(modid, "biome_cotton_candy");
        ResourceLocation biome_chocolate_forest = new ResourceLocation(modid, "biome_chocolate_forest");
        ResourceLocation biome_gummy_swamp = new ResourceLocation(modid, "biome_gummy_swamp");

        if ((biome = Biome.REGISTRY.getObject(biome_cotton_candy)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeCWCottonCandyPlains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(biome_chocolate_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeCWChocolateForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(biome_gummy_swamp)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeCWGummySwamp(biome));
        }
    }

    private static void init_defiledlands() {

        String modid = Mods.defiledlands.name();
        Biome biome;
        ResourceLocation desert_defiled = new ResourceLocation(modid, "desert_defiled");
        ResourceLocation plains_defiled = new ResourceLocation(modid, "plains_defiled");
        ResourceLocation forest_tenebra = new ResourceLocation(modid, "forest_tenebra");
        ResourceLocation forest_vilespine = new ResourceLocation(modid, "forest_vilespine");
        ResourceLocation hills_defiled = new ResourceLocation(modid, "hills_defiled");
        ResourceLocation swamp_defiled = new ResourceLocation(modid, "swamp_defiled");
        ResourceLocation ice_plains_defiled = new ResourceLocation(modid, "ice_plains_defiled");

        if ((biome = Biome.REGISTRY.getObject(desert_defiled)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDLDesertDefiled(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(plains_defiled)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDLPlainsDefiled(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forest_tenebra)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDLForestTenebra(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forest_vilespine)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDLForestVilespine(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(hills_defiled)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDLHillsDefiled(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(swamp_defiled)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDLSwampDefiled(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(ice_plains_defiled)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDLIcePlainsDefiled(biome));
        }
    }

    private static void init_douglasforest() {

        String modid = Mods.douglas_forest.name();
        Biome biome;
        ResourceLocation douglas_forest = new ResourceLocation(modid, "douglas_forest");
        ResourceLocation maple_forest = new ResourceLocation(modid, "maple_forest");

        if ((biome = Biome.REGISTRY.getObject(douglas_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDFDouglasForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(maple_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeDFMapleForest(biome));
        }
    }

    private static void init_environs() {

        String modid = Mods.environs.name();
        Biome biome;
        ResourceLocation extreme_jungle = new ResourceLocation(modid, "extreme_jungle");
        ResourceLocation moor = new ResourceLocation(modid, "moor");
        ResourceLocation emerald_cliffs = new ResourceLocation(modid, "emerald_cliffs");
        ResourceLocation pine_forest = new ResourceLocation(modid, "pine_forest");
        ResourceLocation pine_forest_hills = new ResourceLocation(modid, "pine_forest_hills");
        ResourceLocation silkglades = new ResourceLocation(modid, "silkglades");
        ResourceLocation stone_flats = new ResourceLocation(modid, "stone_flats");
        ResourceLocation end_forest = new ResourceLocation(modid, "end_forest");
        ResourceLocation sparse_end_forest = new ResourceLocation(modid, "sparse_end_forest");
        ResourceLocation end_shrubland = new ResourceLocation(modid, "end_shrubland");
        ResourceLocation tall_oak_forest = new ResourceLocation(modid, "tall_oak_forest");
        ResourceLocation tall_oak_forest_hills = new ResourceLocation(modid, "tall_oak_forest_hills");
        ResourceLocation tall_oak_wetland = new ResourceLocation(modid, "tall_oak_wetland");
        ResourceLocation wasteland_spikes = new ResourceLocation(modid, "wasteland_spikes");
        ResourceLocation wasteland_eroded = new ResourceLocation(modid, "wasteland_eroded");
        ResourceLocation dead_oak_forest = new ResourceLocation(modid, "dead_oak_forest");
        ResourceLocation dead_oak_forest_hills = new ResourceLocation(modid, "dead_oak_forest_hills");
        ResourceLocation icy_hills = new ResourceLocation(modid, "icy_hills");

        if ((biome = Biome.REGISTRY.getObject(extreme_jungle)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVExtremeJungle(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(moor)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVMoor(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(emerald_cliffs)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVEmeraldCliffs(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(pine_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVPineTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(pine_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVPineTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(silkglades)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVSilkglades(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(stone_flats)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVStoneFlats(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(end_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVEndForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(sparse_end_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVSparseEndForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(end_shrubland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVEndShrubland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tall_oak_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVTallOakForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tall_oak_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVTallOakForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tall_oak_wetland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVTallOakWetland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(wasteland_spikes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVWastelandSpikes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(wasteland_eroded)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVWastelandEroded(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(dead_oak_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVDeadOakForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(dead_oak_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVDeadOakForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(icy_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeENVIcyHills(biome));
        }
    }

    private static void init_explorercraft() {

        String modid = Mods.explorercraft.name();
        Biome biome;
        ResourceLocation bamboo_forest = new ResourceLocation(modid, "bamboo_forest");
        ResourceLocation forested_mountain = new ResourceLocation(modid, "forested_mountain");
        ResourceLocation snowdonia = new ResourceLocation(modid, "snowdonia");

        if ((biome = Biome.REGISTRY.getObject(bamboo_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeECBambooForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forested_mountain)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeECForestedMountain(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snowdonia)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeECSnowdonia(biome));
        }
    }

    private static void init_floricraft() {

        String modid = Mods.floricraft.name();
        Biome biome;
        ResourceLocation tulip_land = new ResourceLocation(modid, "tulip land");
        ResourceLocation rose_land = new ResourceLocation(modid, "rose land");

        if ((biome = Biome.REGISTRY.getObject(tulip_land)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFLORITulipLand(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rose_land)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFLORIRoseLand(biome));
        }
    }

    private static void init_fyrecraft() {

        String modid = Mods.fyrecraft.name();
        Biome biome;
        ResourceLocation rocky_wasteland = new ResourceLocation(modid, "rocky wasteland");
        ResourceLocation mushroom_grove = new ResourceLocation(modid, "mushroom grove");
        ResourceLocation tropical_lakes = new ResourceLocation(modid, "tropical lakes");
        ResourceLocation mega_mountains = new ResourceLocation(modid, "mega mountains");
        ResourceLocation red_desert = new ResourceLocation(modid, "red desert");
        ResourceLocation red_desert_hills = new ResourceLocation(modid, "red desert hills");
        ResourceLocation volcanic_wasteland = new ResourceLocation(modid, "volcanic wasteland");
        ResourceLocation crimson_grove = new ResourceLocation(modid, "crimson grove");

        if ((biome = Biome.REGISTRY.getObject(rocky_wasteland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYRERockyWasteland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mushroom_grove)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYREMushroomGrove(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tropical_lakes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYRETropicalLakes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mega_mountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYREMegaMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(red_desert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYRERedDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(red_desert_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYRERedDesertHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(volcanic_wasteland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYREVolcanicWasteland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(crimson_grove)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeFYRECrimsonGrove(biome));
        }
    }

    private static void init_gravityfalls() {

        String modid = Mods.gravityfalls.name();
        Biome biome;
        ResourceLocation gravityfalls = new ResourceLocation(modid, "gravityfalls");
        ResourceLocation nightmarerealm = new ResourceLocation(modid, "nightmarerealm");

        if ((biome = Biome.REGISTRY.getObject(gravityfalls)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeGFGravityFalls(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(nightmarerealm)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeGFNightmareRealm(biome));
        }
    }

    public static void init_iceandfire() {

        String modid = Mods.iceandfire.name();
        Biome biome;
        ResourceLocation glacier = new ResourceLocation(modid, "glacier");

        if ((biome = Biome.REGISTRY.getObject(glacier)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeIAFGlacier(biome));
        }
    }

    private static void init_mistbiomes() {

        String modid = Mods.mistbiomes.name();
        Biome biome;
        ResourceLocation mistforest = new ResourceLocation(modid, "mistforest");
        ResourceLocation mistplains = new ResourceLocation(modid, "mistplains");
        ResourceLocation misttaiga = new ResourceLocation(modid, "misttaiga");
        ResourceLocation mistdesert = new ResourceLocation(modid, "mistdesert");
        ResourceLocation coldmisttaiga = new ResourceLocation(modid, "coldmisttaiga");
        ResourceLocation mistswamp = new ResourceLocation(modid, "mistswamp");
        ResourceLocation mistymushroomisland = new ResourceLocation(modid, "mistymushroomisland");

        if ((biome = Biome.REGISTRY.getObject(mistforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeMBMistForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mistplains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeMBMistPlains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(misttaiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeMBMistTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mistdesert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeMBMistDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(coldmisttaiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeMBColdMistTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mistswamp)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeMBMistSwamp(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mistymushroomisland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeMBMistMushroomIsland(biome));
        }
    }

    private static void init_novamterram() {

        String modid = Mods.nt.name();
        Biome biome;

        /*
        NT beaches generate as normal biomes in RTG worlds for some reason, so instead of just letting the biome patcher
        replace them with Plains, let's remove them from the Biome Manager completely.
         */

        final List<Biome> ntbeaches = Stream.of(
            new ResourceLocation(modid, "black_beach"),
            new ResourceLocation(modid, "brown_beach"),
            new ResourceLocation(modid, "iron_beach"),
            new ResourceLocation(modid, "olivine_beach"),
            new ResourceLocation(modid, "orange_beach"),
            new ResourceLocation(modid, "pink_beach"),
            new ResourceLocation(modid, "purple_beach"),
            new ResourceLocation(modid, "white_beach")
        )
            .map(ForgeRegistries.BIOMES::getValue)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        Arrays.stream(BiomeManager.BiomeType.values()).forEach(type ->
            Objects.requireNonNull(BiomeManager.getBiomes(type)).stream()
                .filter(biomeEntry -> ntbeaches.contains(biomeEntry.biome))
                .forEach(biomeEntry -> BiomeManager.removeBiome(type, biomeEntry)));

        ResourceLocation aegean_archipelago = new ResourceLocation(modid, "aegean_archipelago");
        ResourceLocation alium_meadow = new ResourceLocation(modid, "alium_meadow");
        ResourceLocation alps = new ResourceLocation(modid, "alps");
        ResourceLocation autumn_forest = new ResourceLocation(modid, "autumn_forest");
        ResourceLocation autumn_forest_hills = new ResourceLocation(modid, "autumn_forest_hills");
        ResourceLocation autumn_taiga = new ResourceLocation(modid, "autumn_taiga");
        ResourceLocation autumn_taiga_hills = new ResourceLocation(modid, "autumn_taiga_hills");
        ResourceLocation birch_hills = new ResourceLocation(modid, "birch_hills");
        ResourceLocation black_beach = new ResourceLocation(modid, "black_beach");
        ResourceLocation blossom_forest = new ResourceLocation(modid, "blossom_forest");
        ResourceLocation blossom_forest_hills = new ResourceLocation(modid, "blossom_forest_hills");
        ResourceLocation blue_taiga = new ResourceLocation(modid, "blue_taiga");
        ResourceLocation blue_taiga_hills = new ResourceLocation(modid, "blue_taiga_hills");
        ResourceLocation brown_beach = new ResourceLocation(modid, "brown_beach");
        ResourceLocation clayland = new ResourceLocation(modid, "clayland");
        ResourceLocation cliffs = new ResourceLocation(modid, "cliffs");
        ResourceLocation cold_birch_forest = new ResourceLocation(modid, "cold_birch_forest");
        ResourceLocation cold_birch_forest_hills = new ResourceLocation(modid, "cold_birch_forest_hills");
        ResourceLocation cold_blue_taiga = new ResourceLocation(modid, "cold_blue_taiga");
        ResourceLocation cold_blue_taiga_hills = new ResourceLocation(modid, "cold_blue_taiga_hills");
        ResourceLocation cold_desert = new ResourceLocation(modid, "cold_desert");
        ResourceLocation cold_flower_forest = new ResourceLocation(modid, "cold_flower_forest");
        ResourceLocation cold_flower_forest_hills = new ResourceLocation(modid, "cold_flower_forest_hills");
        ResourceLocation cold_forest = new ResourceLocation(modid, "cold_forest");
        ResourceLocation cold_forest_hills = new ResourceLocation(modid, "cold_forest_hills");
        ResourceLocation cold_mega_blue_taiga = new ResourceLocation(modid, "cold_mega_blue_taiga");
        ResourceLocation cold_mega_blue_taiga_hills = new ResourceLocation(modid, "cold_mega_blue_taiga_hills");
        ResourceLocation cold_mega_spruce_taiga = new ResourceLocation(modid, "cold_mega_spruce_taiga");
        ResourceLocation cold_mega_spruce_taiga_hills = new ResourceLocation(modid, "cold_mega_spruce_taiga_hills");
        ResourceLocation cold_mega_taiga = new ResourceLocation(modid, "cold_mega_taiga");
        ResourceLocation cold_mega_taiga_hills = new ResourceLocation(modid, "cold_mega_taiga_hills");
        ResourceLocation cold_roofed_forest = new ResourceLocation(modid, "cold_roofed_forest");
        ResourceLocation cold_roofed_forest_hills = new ResourceLocation(modid, "cold_roofed_forest_hills");
        ResourceLocation estuary = new ResourceLocation(modid, "estuary");
        ResourceLocation fen = new ResourceLocation(modid, "fen");
        ResourceLocation fungal_jungle = new ResourceLocation(modid, "fungal_jungle");
        ResourceLocation fungal_jungle_hills = new ResourceLocation(modid, "fungal_jungle_hills");
        ResourceLocation glacier = new ResourceLocation(modid, "glacier");
        ResourceLocation grass_hills = new ResourceLocation(modid, "grass_hills");
        ResourceLocation green_mixed_forest = new ResourceLocation(modid, "green_mixed_forest");
        ResourceLocation green_mixed_forest_hills = new ResourceLocation(modid, "green_mixed_forest_hills");
        ResourceLocation green_swamp = new ResourceLocation(modid, "green_swamp");
        ResourceLocation heath = new ResourceLocation(modid, "heath");
        ResourceLocation highland = new ResourceLocation(modid, "highland");
        ResourceLocation hills = new ResourceLocation(modid, "hills");
        ResourceLocation hotspring = new ResourceLocation(modid, "hotspring");
        ResourceLocation icy_tundra = new ResourceLocation(modid, "icy_tundra");
        ResourceLocation iron_beach = new ResourceLocation(modid, "iron_beach");
        ResourceLocation lake = new ResourceLocation(modid, "lake");
        ResourceLocation lowland = new ResourceLocation(modid, "lowland");
        ResourceLocation lush_desert = new ResourceLocation(modid, "lush_desert");
        ResourceLocation maple_forest = new ResourceLocation(modid, "maple_forest");
        ResourceLocation maple_forest_hills = new ResourceLocation(modid, "maple_forest_hills");
        ResourceLocation meadow = new ResourceLocation(modid, "meadow");
        ResourceLocation mega_autumn_taiga = new ResourceLocation(modid, "mega_autumn_taiga");
        ResourceLocation mega_autumn_taiga_hills = new ResourceLocation(modid, "mega_autumn_taiga_hills");
        ResourceLocation mega_blue_taiga = new ResourceLocation(modid, "mega_blue_taiga");
        ResourceLocation mega_blue_taiga_hills = new ResourceLocation(modid, "mega_blue_taiga_hills");
        ResourceLocation mega_maple_forest = new ResourceLocation(modid, "mega_maple_forest");
        ResourceLocation mega_maple_forest_hills = new ResourceLocation(modid, "mega_maple_forest_hills");
        ResourceLocation mixed_forest = new ResourceLocation(modid, "mixed_forest");
        ResourceLocation mixed_forest_hills = new ResourceLocation(modid, "mixed_forest_hills");
        ResourceLocation monsoon_forest = new ResourceLocation(modid, "monsoon_forest");
        ResourceLocation moorland = new ResourceLocation(modid, "moorland");
        ResourceLocation mountains = new ResourceLocation(modid, "mountains");
        ResourceLocation oak_forest = new ResourceLocation(modid, "oak_forest");
        ResourceLocation oak_forest_hills = new ResourceLocation(modid, "oak_forest_hills");
        ResourceLocation olivine_beach = new ResourceLocation(modid, "olivine_beach");
        ResourceLocation orange_beach = new ResourceLocation(modid, "orange_beach");
        ResourceLocation pineland = new ResourceLocation(modid, "pineland");
        ResourceLocation pink_beach = new ResourceLocation(modid, "pink_beach");
        ResourceLocation poppy_meadow = new ResourceLocation(modid, "poppy_meadow");
        ResourceLocation purple_beach = new ResourceLocation(modid, "purple_beach");
        ResourceLocation red_sand_dune = new ResourceLocation(modid, "red_sand_dune");
        ResourceLocation rockland = new ResourceLocation(modid, "rockland");
        ResourceLocation rocky_taiga = new ResourceLocation(modid, "rocky_taiga");
        ResourceLocation royal_forest = new ResourceLocation(modid, "royal_forest");
        ResourceLocation royal_forest_hills = new ResourceLocation(modid, "royal_forest_hills");
        ResourceLocation sahel = new ResourceLocation(modid, "sahel");
        ResourceLocation sand_dune = new ResourceLocation(modid, "sand_dune");
        ResourceLocation sandy_mountains = new ResourceLocation(modid, "sandy_mountains");
        ResourceLocation scrubland = new ResourceLocation(modid, "scrubland");
        ResourceLocation shrubland = new ResourceLocation(modid, "shrubland");
        ResourceLocation snowdune = new ResourceLocation(modid, "snowdune");
        ResourceLocation stone_fields = new ResourceLocation(modid, "stone_fields");
        ResourceLocation stone_mountains = new ResourceLocation(modid, "stone_mountains");
        ResourceLocation tall_oak_forest = new ResourceLocation(modid, "tall_oak_forest");
        ResourceLocation tall_oak_forest_hills = new ResourceLocation(modid, "tall_oak_forest_hills");
        ResourceLocation tropical_desert = new ResourceLocation(modid, "tropical_desert");
        ResourceLocation tundra = new ResourceLocation(modid, "tundra");
        ResourceLocation white_beach = new ResourceLocation(modid, "white_beach");
        ResourceLocation white_orchard = new ResourceLocation(modid, "white_orchard");

        if ((biome = Biome.REGISTRY.getObject(aegean_archipelago)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTAegeanArchipelago(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(alium_meadow)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTAliumMeadow(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(alps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(autumn_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTAutumnForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(autumn_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTAutumnForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(autumn_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTAutumnTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(autumn_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTAutumnTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(birch_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTBirchHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(black_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTBlackBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(blossom_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTBlossomForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(blossom_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTBlossomForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(blue_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTBlueTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(blue_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTBlueTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(brown_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTBrownBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(clayland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTClayland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cliffs)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTCliffs(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_birch_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdBirchForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_birch_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdBirchForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_blue_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdBlueTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_blue_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdBlueTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_desert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_flower_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdFlowerForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_flower_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdFlowerForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_mega_blue_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdMegaBlueTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_mega_blue_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdMegaBlueTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_mega_spruce_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdMegaSpruceTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_mega_spruce_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdMegaSpruceTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_mega_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdMegaTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_mega_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdMegaTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_roofed_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdRoofedForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cold_roofed_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTColdRoofedForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(estuary)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTEstuary(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(fen)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTFen(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(fungal_jungle)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTFungalJungle(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(fungal_jungle_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTFungalJungleHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(glacier)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTGlacier(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(grass_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTGrassHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(green_mixed_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTGreenMixedForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(green_mixed_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTGreenMixedForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(green_swamp)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTGreenSwamp(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(heath)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTHeath(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(highland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTHighland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(hotspring)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTHotspring(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(icy_tundra)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTIcyTundra(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(iron_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTIronBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(lake)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTLake(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(lowland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTLowland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(lush_desert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTLushDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(maple_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMapleForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(maple_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMapleForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(meadow)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMeadow(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mega_autumn_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMegaAutumnTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mega_autumn_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMegaAutumnTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mega_blue_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMegaBlueTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mega_blue_taiga_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMegaBlueTaigaHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mega_maple_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMegaMapleForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mega_maple_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMegaMapleForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mixed_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMixedForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mixed_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMixedForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(monsoon_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMonsoonForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(moorland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMoorland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(oak_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTOakForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(oak_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTOakForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(olivine_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTOlivineBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(orange_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTOrangeBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(pineland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTPineland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(pink_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTPinkBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(poppy_meadow)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTPoppyMeadow(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(purple_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTPurpleBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(red_sand_dune)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTRedSandDune(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rockland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTRockland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rocky_taiga)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTRockyTaiga(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(royal_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTRoyalForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(royal_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTRoyalForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(sahel)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTSahel(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(sand_dune)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTSandDune(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(sandy_mountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTSandyMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(scrubland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTScrubland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(shrubland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTShrubland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snowdune)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTSnowdune(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(stone_fields)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTStoneFields(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(stone_mountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTStoneMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tall_oak_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTTallOakForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tall_oak_forest_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTTallOakForestHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tropical_desert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTTropicalDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tundra)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTTundra(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(white_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTWhiteBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(white_orchard)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeNTWhiteOrchard(biome));
        }
    }

    private static void init_odioitamod() {

        String modid = Mods.odioitamod.name();
        Biome biome;
        ResourceLocation whiteblancoaforest = new ResourceLocation(modid, "whiteblancoaforest");
        ResourceLocation orangeblancoaforest = new ResourceLocation(modid, "orangeblancoaforest");
        ResourceLocation pinkblancoaforest = new ResourceLocation(modid, "pinkblancoaforest");
        ResourceLocation redblancoaforest = new ResourceLocation(modid, "redblancoaforest");

        if ((biome = Biome.REGISTRY.getObject(whiteblancoaforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeODIOWhiteBlancoaForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(orangeblancoaforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeODIOOrangeBlancoaForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(pinkblancoaforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeODIOPinkBlancoaForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(redblancoaforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeODIORedBlancoaForest(biome));
        }
    }

    private static void init_plants() {

        String modid = Mods.plants2.name();
        Biome biome;
        ResourceLocation crystal_forest = new ResourceLocation(modid, "crystal_forest");

        if ((biome = Biome.REGISTRY.getObject(crystal_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePLANTSCrystalForest(biome));
        }
    }

    private static void init_pvj() {

        String modid = Mods.pvj.name();
        Biome biome;
        ResourceLocation prairie = new ResourceLocation(modid, "prairie");
        ResourceLocation redwoods = new ResourceLocation(modid, "redwoods");
        ResourceLocation redwood_peaks = new ResourceLocation(modid, "redwood_peaks");
        ResourceLocation willow_swamp = new ResourceLocation(modid, "willow_swamp");
        ResourceLocation boreal_forest = new ResourceLocation(modid, "boreal_forest");
        ResourceLocation snowy_boreal_forest = new ResourceLocation(modid, "snowy_boreal_forest");
        ResourceLocation aspen_grove = new ResourceLocation(modid, "aspen_grove");
        ResourceLocation overgrown_spires = new ResourceLocation(modid, "overgrown_spires");
        ResourceLocation blossoming_fields = new ResourceLocation(modid, "blossoming_fields");
        ResourceLocation baobab_fields = new ResourceLocation(modid, "baobab_fields");

        if ((biome = Biome.REGISTRY.getObject(prairie)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJPraire(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(redwoods)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJRedwoods(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(redwood_peaks)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJRedwoodPeaks(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(willow_swamp)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJWillowSwamp(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(boreal_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJBorealForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snowy_boreal_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJSnowyBorealForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(aspen_grove)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJAspenGrove(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(overgrown_spires)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJOvergrownSpires(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(blossoming_fields)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJBlossomingFields(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(baobab_fields)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomePVJBaobabFields(biome));
        }
    }

    private static void init_realworld() {

        String modid = Mods.realworld.name();
        Biome biome;
        ResourceLocation rw_bamboo_marsh = new ResourceLocation(modid, "rw_bamboo_marsh");
        ResourceLocation rw_birch_autumn_forest = new ResourceLocation(modid, "rw_birch_autumn_forest");
        ResourceLocation rw_blue_oak_forest = new ResourceLocation(modid, "rw_blue_oak_forest");
        ResourceLocation rw_bombona_beach = new ResourceLocation(modid, "rw_bombona_beach");
        ResourceLocation rw_bur_oak_forest = new ResourceLocation(modid, "rw_bur_oak_forest");
        ResourceLocation rw_flatland_thicket = new ResourceLocation(modid, "rw_flatland_thicket");
        ResourceLocation rw_emperor_ridge = new ResourceLocation(modid, "rw_emperor_ridge");
        ResourceLocation rw_silver_birch_hills = new ResourceLocation(modid, "rw_silver_birch_hills");
        ResourceLocation rw_spiny_forest = new ResourceLocation(modid, "rw_spiny_forest");
        ResourceLocation rw_spruce_mountains = new ResourceLocation(modid, "rw_spruce_mountains");

        if ((biome = Biome.REGISTRY.getObject(rw_bamboo_marsh)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWBambooMarsh(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_birch_autumn_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWBirchAutumnForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_blue_oak_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWBlueOakForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_bombona_beach)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWBombonaBeach(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_bur_oak_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWBurOakForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_flatland_thicket)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWFlatlandThicket(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_emperor_ridge)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWEmperorRidge(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_silver_birch_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWSilverBirchHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_spiny_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWSpinyForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rw_spruce_mountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRWSpruceMountains(biome));
        }
    }

    private static void init_redwoods() {

        String modid = Mods.redwoods.name();
        Biome biome;
        ResourceLocation redwood_forest = new ResourceLocation(modid, "redwood_forest");
        ResourceLocation lush_redwood_forest = new ResourceLocation(modid, "lush_redwood_forest");
        ResourceLocation temperate_rainforest = new ResourceLocation(modid, "temperate_rainforest");
        ResourceLocation snowy_rainforest = new ResourceLocation(modid, "snowy_rainforest");
        ResourceLocation alpine = new ResourceLocation(modid, "alpine"); // Doesn't actually generate in-game.

        if ((biome = Biome.REGISTRY.getObject(redwood_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeREDRedwoodForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(lush_redwood_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeREDLushRedwoodForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(temperate_rainforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeREDTemperateRainforest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snowy_rainforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeREDSnowyRainforest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(alpine)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeREDAlpine(biome));
        }
    }

    private static void init_rockhounding() {

        String modid = Mods.rockhounding_surface.name();
        Biome biome;
        ResourceLocation white_sands = new ResourceLocation(modid, "white_sands");

        if ((biome = Biome.REGISTRY.getObject(white_sands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeRHWhiteSands(biome));
        }
    }

    private static void init_spookybiomes() {

        String modid = Mods.spookybiomes.name();
        Biome biome;
        ResourceLocation witchwood_forest = new ResourceLocation(modid, "witchwood_forest");
        ResourceLocation ghostly_forest = new ResourceLocation(modid, "ghostly_forest");

        if ((biome = Biome.REGISTRY.getObject(witchwood_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeSBWitchwoodForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(ghostly_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeSBGhostlyForest(biome));
        }
    }

    private static void init_sugiforest() {

        String modid = Mods.sugiforest.name();
        Biome biome;
        ResourceLocation sugi_forest = new ResourceLocation(modid, "sugi_forest");

        if ((biome = Biome.REGISTRY.getObject(sugi_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeSFSugiForest(biome));
        }
    }

    private static void init_terscraft() {

        String modid = Mods.terscraft.name();
        Biome biome;
        ResourceLocation biomedemonite = new ResourceLocation(modid, "biomedemonite");

        if ((biome = Biome.REGISTRY.getObject(biomedemonite)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTERSBiomeDemonite(biome));
        }
    }

    private static void init_thaumcraft() {

        final ResourceLocation magicalForest = new ResourceLocation(Mods.thaumcraft.name(), "magical_forest");
        final ResourceLocation eerie = new ResourceLocation(Mods.thaumcraft.name(), "eerie");
        Biome biome;

        if ((biome = Biome.REGISTRY.getObject(magicalForest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTCMagicalForest(biome));
        }

        if ((biome = Biome.REGISTRY.getObject(eerie)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTCEerie(biome));
        }
    }

    private static void init_traverse() {

        String modid = Mods.traverse.name();
        Biome biome;
        ResourceLocation autumnal_woods = new ResourceLocation(modid, "autumnal_woods");
        ResourceLocation woodlands = new ResourceLocation(modid, "woodlands");
        ResourceLocation mini_jungle = new ResourceLocation(modid, "mini_jungle");
        ResourceLocation meadow = new ResourceLocation(modid, "meadow");
        ResourceLocation green_swamp = new ResourceLocation(modid, "green_swamp");
        ResourceLocation red_desert = new ResourceLocation(modid, "red_desert");
        ResourceLocation temperate_rainforest = new ResourceLocation(modid, "temperate_rainforest");
        ResourceLocation badlands = new ResourceLocation(modid, "badlands");
        ResourceLocation mountainous_desert = new ResourceLocation(modid, "mountainous_desert");
        ResourceLocation rocky_plateau = new ResourceLocation(modid, "rocky_plateau");
        ResourceLocation forested_hills = new ResourceLocation(modid, "forested_hills");
        ResourceLocation birch_forested_hills = new ResourceLocation(modid, "birch_forested_hills");
        ResourceLocation autumnal_wooded_hills = new ResourceLocation(modid, "autumnal_wooded_hills");
        ResourceLocation cliffs = new ResourceLocation(modid, "cliffs");
        ResourceLocation glacier = new ResourceLocation(modid, "glacier");
        ResourceLocation glacier_spikes = new ResourceLocation(modid, "glacier_spikes");
        ResourceLocation snowy_coniferous_forest = new ResourceLocation(modid, "snowy_coniferous_forest");
        ResourceLocation lush_hills = new ResourceLocation(modid, "lush_hills");
        ResourceLocation desert_shrubland = new ResourceLocation(modid, "desert_shrubland");
        ResourceLocation thicket = new ResourceLocation(modid, "thicket");
        ResourceLocation arid_highland = new ResourceLocation(modid, "arid_highland");
        ResourceLocation rocky_plains = new ResourceLocation(modid, "rocky_plains");

        if ((biome = Biome.REGISTRY.getObject(autumnal_woods)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVAutumnalWoods(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(woodlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVWoodlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mini_jungle)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVMiniJungle(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(meadow)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVMeadow(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(green_swamp)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVGreenSwamp(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(red_desert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVRedDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(temperate_rainforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVTemperateRainforest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(badlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVBadlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mountainous_desert)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVMountainousDesert(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rocky_plateau)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVRockyPlateau(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forested_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVForestedHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(birch_forested_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVBirchForestedHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(autumnal_wooded_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVAutumnalWoodedHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(cliffs)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVCliffs(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(glacier)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVGlacier(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(glacier_spikes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVGlacierSpikes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snowy_coniferous_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVSnowyConiferousForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(lush_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVLushHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(desert_shrubland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVDesertShrubland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(thicket)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVThicket(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(arid_highland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVAridHighland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(rocky_plains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTRAVRockyPlains(biome));
        }
    }

    private static void init_vampirism() {

        String modid = Mods.vampirism.name();
        Biome biome;
        ResourceLocation vampireforest = new ResourceLocation(modid, "vampireforest");

        if ((biome = Biome.REGISTRY.getObject(vampireforest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeVAMPVampireForest(biome));
        }
    }

    private static void init_zoesteria() {

        String modid = Mods.valoegheses_be.name();
        Biome biome;
        ResourceLocation archipelago = new ResourceLocation(modid, "archipelago");
        ResourceLocation australian_outback = new ResourceLocation(modid, "australian outback");
        ResourceLocation barelands = new ResourceLocation(modid, "barelands");
        ResourceLocation bluff = new ResourceLocation(modid, "bluff");
        ResourceLocation brushlands = new ResourceLocation(modid, "brushlands");
        ResourceLocation brushlands_hills = new ResourceLocation(modid, "brushlands hills");
        ResourceLocation bush = new ResourceLocation(modid, "bush");
        ResourceLocation bushland_hills = new ResourceLocation(modid, "bushland hills");
        ResourceLocation dense_brushlands = new ResourceLocation(modid, "dense brushlands");
        ResourceLocation extreme_southern_alps = new ResourceLocation(modid, "extreme southern alps");
        ResourceLocation forested_canyon_pillars = new ResourceLocation(modid, "forested canyon pillars");
        ResourceLocation forested_canyon_plateau = new ResourceLocation(modid, "forested canyon plateau");
        ResourceLocation forested_canyon_plateau_m = new ResourceLocation(modid, "forested canyon plateau m");
        ResourceLocation ghost_forest = new ResourceLocation(modid, "ghost forest");
        ResourceLocation grassy_fen = new ResourceLocation(modid, "grassy fen");
        ResourceLocation grassy_marshland = new ResourceLocation(modid, "grassy marshland");
        ResourceLocation high_inhabited_woodlands = new ResourceLocation(modid, "high inhabited woodlands");
        ResourceLocation high_woodlands = new ResourceLocation(modid, "high woodlands");
        ResourceLocation highlands = new ResourceLocation(modid, "highlands");
        ResourceLocation hot_brushlands = new ResourceLocation(modid, "hot brushlands");
        ResourceLocation low_spruce_woodlands = new ResourceLocation(modid, "low spruce woodlands");
        ResourceLocation low_tall_woodlands = new ResourceLocation(modid, "low tall woodlands");
        ResourceLocation low_woodlands = new ResourceLocation(modid, "low woodlands");
        ResourceLocation lowlands = new ResourceLocation(modid, "lowlands");
        ResourceLocation lowlands_chapparal = new ResourceLocation(modid, "lowlands chapparal");
        ResourceLocation mire = new ResourceLocation(modid, "mire");
        ResourceLocation moorlands = new ResourceLocation(modid, "moorlands");
        ResourceLocation northern_alps = new ResourceLocation(modid, "northern alps");
        ResourceLocation northern_snow_alps = new ResourceLocation(modid, "northern snow alps");
        ResourceLocation northern_snow_subalpine_alps = new ResourceLocation(modid, "northern snow subalpine alps");
        ResourceLocation northern_subalpine_alps = new ResourceLocation(modid, "northern subalpine alps");
        ResourceLocation orchid_fields = new ResourceLocation(modid, "orchid fields");
        ResourceLocation red_sand_dunes = new ResourceLocation(modid, "red sand dunes");
        ResourceLocation sand_dunes = new ResourceLocation(modid, "sand dunes");
        ResourceLocation sand_dunes_m = new ResourceLocation(modid, "sand dunes m");
        ResourceLocation sand_dunes_oasis = new ResourceLocation(modid, "sand dunes oasis");
        ResourceLocation snow_rocks = new ResourceLocation(modid, "snow rocks");
        ResourceLocation snow_rocks_mountains = new ResourceLocation(modid, "snow rocks mountains");
        ResourceLocation snow_rocks_plateau = new ResourceLocation(modid, "snow rocks plateau");
        ResourceLocation southern_alps = new ResourceLocation(modid, "southern alps");
        ResourceLocation southern_alps_subalpine = new ResourceLocation(modid, "southern alps subalpine");
        ResourceLocation stony_reef = new ResourceLocation(modid, "stony reef");
        ResourceLocation tropical_jungle = new ResourceLocation(modid, "tropical jungle");
        ResourceLocation wasteland_flats = new ResourceLocation(modid, "wasteland flats");
        ResourceLocation wasteland_flats_oasis = new ResourceLocation(modid, "wasteland flats oasis");
        ResourceLocation wasteland_flats_slopes = new ResourceLocation(modid, "wasteland flats slopes");
        ResourceLocation wasteland_flats_trees = new ResourceLocation(modid, "wasteland flats trees");
        ResourceLocation white_oaks = new ResourceLocation(modid, "white oaks");
        ResourceLocation white_oaks_hills = new ResourceLocation(modid, "white oaks hills");
        ResourceLocation white_woods = new ResourceLocation(modid, "white woods");
        ResourceLocation white_woods_hills = new ResourceLocation(modid, "white woods hills");
        ResourceLocation woodlands_hills = new ResourceLocation(modid, "woodlands hills");

        if ((biome = Biome.REGISTRY.getObject(archipelago)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEArchipelago(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(australian_outback)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEAustralianOutback(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(barelands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEBarelands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bluff)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEBluff(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(brushlands_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEBrushlandsHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(brushlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEBrushlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bush)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEBush(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(bushland_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEBushlandHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(dense_brushlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEDenseBrushlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(extreme_southern_alps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEExtremeSouthernAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forested_canyon_pillars)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEForestedCanyonPillars(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forested_canyon_plateau_m)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEForestedCanyonPlateauM(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(forested_canyon_plateau)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEForestedCanyonPlateau(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(ghost_forest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEGhostForest(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(grassy_fen)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEGrassyFen(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(grassy_marshland)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEGrassyMarshland(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(high_inhabited_woodlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEHighInhabitedWoodlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(high_woodlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEHighWoodlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(highlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEHighlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(hot_brushlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEHotBrushlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(low_spruce_woodlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOELowSpruceWoodlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(low_tall_woodlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOELowTallWoodlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(low_woodlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOELowWoodlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(lowlands_chapparal)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOELowlandsChapparal(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(lowlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOELowlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(mire)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEMire(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(moorlands)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEMoorlands(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(northern_alps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOENorthernAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(northern_snow_alps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOENorthernSnowAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(northern_snow_subalpine_alps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOENorthernSnowSubalpineAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(northern_subalpine_alps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOENorthernSubalpineAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(orchid_fields)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEOrchidFields(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(red_sand_dunes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOERedSandDunes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(sand_dunes_m)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESandDunesM(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(sand_dunes_oasis)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESandDunesOasis(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(sand_dunes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESandDunes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snow_rocks_mountains)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESnowRocksMountains(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snow_rocks_plateau)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESnowRocksPlateau(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(snow_rocks)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESnowRocks(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(southern_alps_subalpine)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESouthernAlpsSubalpine(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(southern_alps)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOESouthernAlps(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(stony_reef)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEStonyReef(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(tropical_jungle)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOETropicalJungle(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(wasteland_flats_oasis)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWastelandFlatsOasis(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(wasteland_flats_slopes)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWastelandFlatsSlopes(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(wasteland_flats_trees)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWastelandFlatsTrees(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(wasteland_flats)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWastelandFlats(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(white_oaks_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWhiteOaksHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(white_oaks)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWhiteOaks(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(white_woods_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWhiteWoodsHills(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(white_woods)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWhiteWoods(biome));
        }
        if ((biome = Biome.REGISTRY.getObject(woodlands_hills)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeZOEWoodlandsHills(biome));
        }
    }

    private static boolean isBiomePresent(ResourceLocation rl) {
        return Biome.REGISTRY.containsKey(rl);
    }

    private static boolean isBiomePresent(String modid, String biomeName) {
        ResourceLocation rl = new ResourceLocation(modid, biomeName);
        return Biome.REGISTRY.containsKey(rl);
    }
}
