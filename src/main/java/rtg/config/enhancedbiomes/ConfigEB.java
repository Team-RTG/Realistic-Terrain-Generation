package rtg.config.enhancedbiomes;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEB;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigEB 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateEBBiomes = true;
			
	public static boolean generateEBAlpineMountains = true;
	public static boolean generateEBAlpineMountainsEdge = true;
	public static boolean generateEBAlpineMountainsM = true;
	public static boolean generateEBAlpineTundra = true;
	public static boolean generateEBAspenForest = true;
	public static boolean generateEBAspenHills = true;
	public static boolean generateEBBadlands = true;
	public static boolean generateEBBasin =true;
	public static boolean generateEBBlossomHills = true;
	public static boolean generateEBBlossomWoods = true;
	public static boolean generateEBBorealArchipelago = true;
	public static boolean generateEBBorealForest = true;
	public static boolean generateEBBorealPlateau = true;
	public static boolean generateEBBorealPlateauM = true;
	public static boolean generateEBCarr = true;
	public static boolean generateEBClayHills =true;
	public static boolean generateEBClearing = true;
	public static boolean generateEBColdBorealForest = true;
	public static boolean generateEBColdCypressForest = true;
	public static boolean generateEBColdFirForest = true;
	public static boolean generateEBColdPineForest = true;
	public static boolean generateEBCreekBed = true;
	public static boolean generateEBCypressForest = true;
	public static boolean generateEBDesertArchipelago = true;
	public static boolean generateEBEphemeralLake =true;
	public static boolean generateEBEphemeralLakeEdge =true;
	public static boolean generateEBFens = true;
	public static boolean generateEBFirForest = true;
	public static boolean generateEBFloweryArchipelago = true;
	public static boolean generateEBForestedArchipelago = true;
	public static boolean generateEBForestedMountains = true;
	public static boolean generateEBForestedValley = true;
	public static boolean generateEBFrozenArchipelago = true;
	public static boolean generateEBGlacier = true;
	public static boolean generateEBGrassyArchipelago = true;
	public static boolean generateEBIceSheet =true;
	public static boolean generateEBKakadu = true;
	public static boolean generateEBLake = true;
	public static boolean generateEBLowHills = true;
	public static boolean generateEBMangroves = true;
	public static boolean generateEBMarsh = true;
	public static boolean generateEBMeadow = true;
	public static boolean generateEBMeadowM = true;
	public static boolean generateEBMountainousArchipelago = true;
	public static boolean generateEBMountains = true;
	public static boolean generateEBMountainsEdge = true;
	public static boolean generateEBOakForest = true;
	public static boolean generateEBOasis = true;
	public static boolean generateEBPineForest = true;
	public static boolean generateEBPineForestArchipelago = true;
	public static boolean generateEBPlateau = true;
	public static boolean generateEBPolarDesert =true;
	public static boolean generateEBPrairie = true;
	public static boolean generateEBRainforest = true;
	public static boolean generateEBRainforestValley = true;
	public static boolean generateEBRedDesert = true;
	public static boolean generateEBRiparianZone = true;
	public static boolean generateEBRockyDesert =true;
	public static boolean generateEBRockyHills =true;
	public static boolean generateEBRoofedShrublands = true;
	public static boolean generateEBSahara = true;
	public static boolean generateEBSandstoneCanyon =true;
	public static boolean generateEBSandstoneCanyons =true;
	public static boolean generateEBSandstoneRanges =true;
	public static boolean generateEBSandstoneRangesM =true;
	public static boolean generateEBScree =true;
	public static boolean generateEBScrub =true;
	public static boolean generateEBShield = true;
	public static boolean generateEBShrublands = true;
	public static boolean generateEBSilverPineForest = true;
	public static boolean generateEBSilverPineHills = true;
	public static boolean generateEBSnowyDesert = true;
	public static boolean generateEBSnowyPlateau = true;
	public static boolean generateEBSnowyRanges = true;
	public static boolean generateEBSnowyWastelands =true;
	public static boolean generateEBSteppe = true;
	public static boolean generateEBStoneCanyon =true;
	public static boolean generateEBStoneCanyons =true;
	public static boolean generateEBTropicalArchipelago = true;
	public static boolean generateEBTundra = true;
	public static boolean generateEBVolcano = true;
	public static boolean generateEBVolcanoM = true;
	public static boolean generateEBWastelands = true;
	public static boolean generateEBWoodlandField = true;
	public static boolean generateEBWoodlandHills = true;
	public static boolean generateEBWoodlandLake = true;
	public static boolean generateEBWoodlandLakeEdge = true;
	public static boolean generateEBWoodlands = true;
	public static boolean generateEBXericSavannah = true;
	public static boolean generateEBXericShrubland = true;
	
	public static int weightEBAlpineMountains = biomeWeightDefault;
	public static int weightEBAlpineMountainsEdge = biomeWeightDefault;
	public static int weightEBAlpineMountainsM = biomeWeightDefault;
	public static int weightEBAlpineTundra = biomeWeightDefault;
	public static int weightEBAspenForest = biomeWeightDefault;
	public static int weightEBAspenHills = biomeWeightDefault;
	public static int weightEBBadlands = biomeWeightDefault;
	public static int weightEBBasin = biomeWeightDefault;
	public static int weightEBBlossomHills = biomeWeightDefault;
	public static int weightEBBlossomWoods = biomeWeightDefault;
	public static int weightEBBorealArchipelago = biomeWeightDefault;
	public static int weightEBBorealForest = biomeWeightDefault;
	public static int weightEBBorealPlateau = biomeWeightDefault;
	public static int weightEBBorealPlateauM = biomeWeightDefault;
	public static int weightEBCarr = biomeWeightDefault;
	public static int weightEBClayHills = biomeWeightDefault;
	public static int weightEBClearing = biomeWeightDefault;
	public static int weightEBColdBorealForest = biomeWeightDefault;
	public static int weightEBColdCypressForest = biomeWeightDefault;
	public static int weightEBColdFirForest = biomeWeightDefault;
	public static int weightEBColdPineForest = biomeWeightDefault;
	public static int weightEBCreekBed = 0;
	public static int weightEBCypressForest = biomeWeightDefault;
	public static int weightEBDesertArchipelago = biomeWeightDefault;
	public static int weightEBEphemeralLake = biomeWeightDefault;
	public static int weightEBEphemeralLakeEdge = biomeWeightDefault;
	public static int weightEBFens = biomeWeightDefault;
	public static int weightEBFirForest = biomeWeightDefault;
	public static int weightEBFloweryArchipelago = biomeWeightDefault;
	public static int weightEBForestedArchipelago = biomeWeightDefault;
	public static int weightEBForestedMountains = biomeWeightDefault;
	public static int weightEBForestedValley = biomeWeightDefault;
	public static int weightEBFrozenArchipelago = biomeWeightDefault;
	public static int weightEBGlacier = biomeWeightDefault;
	public static int weightEBGrassyArchipelago = biomeWeightDefault;
	public static int weightEBIceSheet = biomeWeightDefault;
	public static int weightEBKakadu = biomeWeightDefault;
	public static int weightEBLake = biomeWeightDefault;
	public static int weightEBLowHills = biomeWeightDefault;
	public static int weightEBMangroves = biomeWeightDefault;
	public static int weightEBMarsh = biomeWeightDefault;
	public static int weightEBMeadow = biomeWeightDefault;
	public static int weightEBMeadowM = biomeWeightDefault;
	public static int weightEBMountainousArchipelago = biomeWeightDefault;
	public static int weightEBMountains = biomeWeightDefault;
	public static int weightEBMountainsEdge = biomeWeightDefault;
	public static int weightEBOakForest = biomeWeightDefault;
	public static int weightEBOasis = biomeWeightDefault;
	public static int weightEBPineForest = biomeWeightDefault;
	public static int weightEBPineForestArchipelago = biomeWeightDefault;
	public static int weightEBPlateau = biomeWeightDefault;
	public static int weightEBPolarDesert = biomeWeightDefault;
	public static int weightEBPrairie = biomeWeightDefault;
	public static int weightEBRainforest = biomeWeightDefault;
	public static int weightEBRainforestValley = biomeWeightDefault;
	public static int weightEBRedDesert = biomeWeightDefault;
	public static int weightEBRiparianZone = 0;
	public static int weightEBRockyDesert = biomeWeightDefault;
	public static int weightEBRockyHills = biomeWeightDefault;
	public static int weightEBRoofedShrublands = biomeWeightDefault;
	public static int weightEBSahara = biomeWeightDefault;
	public static int weightEBSandstoneCanyon = biomeWeightDefault;
	public static int weightEBSandstoneCanyons = biomeWeightDefault;
	public static int weightEBSandstoneRanges = biomeWeightDefault;
	public static int weightEBSandstoneRangesM = biomeWeightDefault;
	public static int weightEBScree = biomeWeightDefault;
	public static int weightEBScrub = biomeWeightDefault;
	public static int weightEBShield = biomeWeightDefault;
	public static int weightEBShrublands = biomeWeightDefault;
	public static int weightEBSilverPineForest = biomeWeightDefault;
	public static int weightEBSilverPineHills = biomeWeightDefault;
	public static int weightEBSnowyDesert = biomeWeightDefault;
	public static int weightEBSnowyPlateau = biomeWeightDefault;
	public static int weightEBSnowyRanges = biomeWeightDefault;
	public static int weightEBSnowyWastelands = biomeWeightDefault;
	public static int weightEBSteppe = biomeWeightDefault;
	public static int weightEBStoneCanyon = biomeWeightDefault;
	public static int weightEBStoneCanyons = biomeWeightDefault;
	public static int weightEBTropicalArchipelago = biomeWeightDefault;
	public static int weightEBTundra = biomeWeightDefault;
	public static int weightEBVolcano = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightEBVolcanoM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightEBWastelands = biomeWeightDefault;
	public static int weightEBWoodlandField = biomeWeightDefault;
	public static int weightEBWoodlandHills = biomeWeightDefault;
	public static int weightEBWoodlandLake = biomeWeightDefault;
	public static int weightEBWoodlandLakeEdge = biomeWeightDefault;
	public static int weightEBWoodlands = biomeWeightDefault;
	public static int weightEBXericSavannah = biomeWeightDefault;
	public static int weightEBXericShrubland = biomeWeightDefault;
	
    public static boolean villageEBAlpineMountains = true;
    public static boolean villageEBAlpineMountainsEdge = true;
    public static boolean villageEBAlpineMountainsM = true;
    public static boolean villageEBAlpineTundra = true;
    public static boolean villageEBAspenForest = true;
    public static boolean villageEBAspenHills = true;
    public static boolean villageEBBadlands = true;
    public static boolean villageEBBasin =true;
    public static boolean villageEBBlossomHills = true;
    public static boolean villageEBBlossomWoods = true;
    public static boolean villageEBBorealArchipelago = true;
    public static boolean villageEBBorealForest = true;
    public static boolean villageEBBorealPlateau = true;
    public static boolean villageEBBorealPlateauM = true;
    public static boolean villageEBCarr = true;
    public static boolean villageEBClayHills =true;
    public static boolean villageEBClearing = true;
    public static boolean villageEBColdBorealForest = true;
    public static boolean villageEBColdCypressForest = true;
    public static boolean villageEBColdFirForest = true;
    public static boolean villageEBColdPineForest = true;
    public static boolean villageEBCreekBed = true;
    public static boolean villageEBCypressForest = true;
    public static boolean villageEBDesertArchipelago = true;
    public static boolean villageEBEphemeralLake =true;
    public static boolean villageEBEphemeralLakeEdge =true;
    public static boolean villageEBFens = true;
    public static boolean villageEBFirForest = true;
    public static boolean villageEBFloweryArchipelago = true;
    public static boolean villageEBForestedArchipelago = true;
    public static boolean villageEBForestedMountains = true;
    public static boolean villageEBForestedValley = true;
    public static boolean villageEBFrozenArchipelago = true;
    public static boolean villageEBGlacier = true;
    public static boolean villageEBGrassyArchipelago = true;
    public static boolean villageEBIceSheet =true;
    public static boolean villageEBKakadu = true;
    public static boolean villageEBLake = true;
    public static boolean villageEBLowHills = true;
    public static boolean villageEBMangroves = true;
    public static boolean villageEBMarsh = true;
    public static boolean villageEBMeadow = true;
    public static boolean villageEBMeadowM = true;
    public static boolean villageEBMountainousArchipelago = true;
    public static boolean villageEBMountains = true;
    public static boolean villageEBMountainsEdge = true;
    public static boolean villageEBOakForest = true;
    public static boolean villageEBOasis = true;
    public static boolean villageEBPineForest = true;
    public static boolean villageEBPineForestArchipelago = true;
    public static boolean villageEBPlateau = true;
    public static boolean villageEBPolarDesert =true;
    public static boolean villageEBPrairie = true;
    public static boolean villageEBRainforest = true;
    public static boolean villageEBRainforestValley = true;
    public static boolean villageEBRedDesert = true;
    public static boolean villageEBRiparianZone = true;
    public static boolean villageEBRockyDesert =true;
    public static boolean villageEBRockyHills =true;
    public static boolean villageEBRoofedShrublands = true;
    public static boolean villageEBSahara = true;
    public static boolean villageEBSandstoneCanyon =true;
    public static boolean villageEBSandstoneCanyons =true;
    public static boolean villageEBSandstoneRanges =true;
    public static boolean villageEBSandstoneRangesM =true;
    public static boolean villageEBScree =true;
    public static boolean villageEBScrub =true;
    public static boolean villageEBShield = true;
    public static boolean villageEBShrublands = true;
    public static boolean villageEBSilverPineForest = true;
    public static boolean villageEBSilverPineHills = true;
    public static boolean villageEBSnowyDesert = true;
    public static boolean villageEBSnowyPlateau = true;
    public static boolean villageEBSnowyRanges = true;
    public static boolean villageEBSnowyWastelands =true;
    public static boolean villageEBSteppe = true;
    public static boolean villageEBStoneCanyon =true;
    public static boolean villageEBStoneCanyons =true;
    public static boolean villageEBTropicalArchipelago = true;
    public static boolean villageEBTundra = true;
    public static boolean villageEBVolcano =false;
    public static boolean villageEBVolcanoM =false;
    public static boolean villageEBWastelands =true;
    public static boolean villageEBWoodlandField = true;
    public static boolean villageEBWoodlandHills = true;
    public static boolean villageEBWoodlandLake = true;
    public static boolean villageEBWoodlandLakeEdge = true;
    public static boolean villageEBWoodlands = true;
    public static boolean villageEBXericSavannah = true;
    public static boolean villageEBXericShrubland = true;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateEBBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateEBBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
			
			generateEBAlpineMountains = config.getBoolean(formatConfig("generateEBAlpineMountains"), "Biomes", generateEBAlpineMountains, "");
			generateEBAlpineMountainsEdge = config.getBoolean(formatConfig("generateEBAlpineMountainsEdge"), "Biomes", generateEBAlpineMountainsEdge, "");
			generateEBAlpineMountainsM = config.getBoolean(formatConfig("generateEBAlpineMountainsM"), "Biomes", generateEBAlpineMountainsM, "");
			generateEBAlpineTundra = config.getBoolean(formatConfig("generateEBAlpineTundra"), "Biomes", generateEBAlpineTundra, "");
			generateEBAspenForest = config.getBoolean(formatConfig("generateEBAspenForest"), "Biomes", generateEBAspenForest, "");
			generateEBAspenHills = config.getBoolean(formatConfig("generateEBAspenHills"), "Biomes", generateEBAspenHills, "");
			generateEBBadlands = config.getBoolean(formatConfig("generateEBBadlands"), "Biomes", generateEBBadlands, "");
			generateEBBasin = config.getBoolean(formatConfig("generateEBBasin"), "Biomes", generateEBBasin, "");
			generateEBBlossomHills = config.getBoolean(formatConfig("generateEBBlossomHills"), "Biomes", generateEBBlossomHills, "");
			generateEBBlossomWoods = config.getBoolean(formatConfig("generateEBBlossomWoods"), "Biomes", generateEBBlossomWoods, "");
			generateEBBorealArchipelago = config.getBoolean(formatConfig("generateEBBorealArchipelago"), "Biomes", generateEBBorealArchipelago, "");
			generateEBBorealForest = config.getBoolean(formatConfig("generateEBBorealForest"), "Biomes", generateEBBorealForest, "");
			generateEBBorealPlateau = config.getBoolean(formatConfig("generateEBBorealPlateau"), "Biomes", generateEBBorealPlateau, "");
			generateEBBorealPlateauM = config.getBoolean(formatConfig("generateEBBorealPlateauM"), "Biomes", generateEBBorealPlateauM, "");
			generateEBCarr = config.getBoolean(formatConfig("generateEBCarr"), "Biomes", generateEBCarr, "");
			generateEBClayHills = config.getBoolean(formatConfig("generateEBClayHills"), "Biomes", generateEBClayHills, "");
			generateEBClearing = config.getBoolean(formatConfig("generateEBClearing"), "Biomes", generateEBClearing, "");
			generateEBColdBorealForest = config.getBoolean(formatConfig("generateEBColdBorealForest"), "Biomes", generateEBColdBorealForest, "");
			generateEBColdCypressForest = config.getBoolean(formatConfig("generateEBColdCypressForest"), "Biomes", generateEBColdCypressForest, "");
			generateEBColdFirForest = config.getBoolean(formatConfig("generateEBColdFirForest"), "Biomes", generateEBColdFirForest, "");
			generateEBColdPineForest = config.getBoolean(formatConfig("generateEBColdPineForest"), "Biomes", generateEBColdPineForest, "");
			generateEBCreekBed = config.getBoolean(formatConfig("generateEBCreekBed"), "Biomes", generateEBCreekBed, "");
			generateEBCypressForest = config.getBoolean(formatConfig("generateEBCypressForest"), "Biomes", generateEBCypressForest, "");
			generateEBDesertArchipelago = config.getBoolean(formatConfig("generateEBDesertArchipelago"), "Biomes", generateEBDesertArchipelago, "");
			generateEBEphemeralLake = config.getBoolean(formatConfig("generateEBEphemeralLake"), "Biomes", generateEBEphemeralLake, "");
			generateEBEphemeralLakeEdge = config.getBoolean(formatConfig("generateEBEphemeralLakeEdge"), "Biomes", generateEBEphemeralLakeEdge, "");
			generateEBFens = config.getBoolean(formatConfig("generateEBFens"), "Biomes", generateEBFens, "");
			generateEBFirForest = config.getBoolean(formatConfig("generateEBFirForest"), "Biomes", generateEBFirForest, "");
			generateEBFloweryArchipelago = config.getBoolean(formatConfig("generateEBFloweryArchipelago"), "Biomes", generateEBFloweryArchipelago, "");
			generateEBForestedArchipelago = config.getBoolean(formatConfig("generateEBForestedArchipelago"), "Biomes", generateEBForestedArchipelago, "");
			generateEBForestedMountains = config.getBoolean(formatConfig("generateEBForestedMountains"), "Biomes", generateEBForestedMountains, "");
			generateEBForestedValley = config.getBoolean(formatConfig("generateEBForestedValley"), "Biomes", generateEBForestedValley, "");
			generateEBFrozenArchipelago = config.getBoolean(formatConfig("generateEBFrozenArchipelago"), "Biomes", generateEBFrozenArchipelago, "");
			generateEBGlacier = config.getBoolean(formatConfig("generateEBGlacier"), "Biomes", generateEBGlacier, "");
			generateEBGrassyArchipelago = config.getBoolean(formatConfig("generateEBGrassyArchipelago"), "Biomes", generateEBGrassyArchipelago, "");
			generateEBIceSheet = config.getBoolean(formatConfig("generateEBIceSheet"), "Biomes", generateEBIceSheet, "");
			generateEBKakadu = config.getBoolean(formatConfig("generateEBKakadu"), "Biomes", generateEBKakadu, "");
			generateEBLake = config.getBoolean(formatConfig("generateEBLake"), "Biomes", generateEBLake, "");
			generateEBLowHills = config.getBoolean(formatConfig("generateEBLowHills"), "Biomes", generateEBLowHills, "");
			generateEBMangroves = config.getBoolean(formatConfig("generateEBMangroves"), "Biomes", generateEBMangroves, "");
			generateEBMarsh = config.getBoolean(formatConfig("generateEBMarsh"), "Biomes", generateEBMarsh, "");
			generateEBMeadow = config.getBoolean(formatConfig("generateEBMeadow"), "Biomes", generateEBMeadow, "");
			generateEBMeadowM = config.getBoolean(formatConfig("generateEBMeadowM"), "Biomes", generateEBMeadowM, "");
			generateEBMountainousArchipelago = config.getBoolean(formatConfig("generateEBMountainousArchipelago"), "Biomes", generateEBMountainousArchipelago, "");
			generateEBMountains = config.getBoolean(formatConfig("generateEBMountains"), "Biomes", generateEBMountains, "");
			generateEBMountainsEdge = config.getBoolean(formatConfig("generateEBMountainsEdge"), "Biomes", generateEBMountainsEdge, "");
			generateEBOakForest = config.getBoolean(formatConfig("generateEBOakForest"), "Biomes", generateEBOakForest, "");
			generateEBOasis = config.getBoolean(formatConfig("generateEBOasis"), "Biomes", generateEBOasis, "");
			generateEBPineForest = config.getBoolean(formatConfig("generateEBPineForest"), "Biomes", generateEBPineForest, "");
			generateEBPineForestArchipelago = config.getBoolean(formatConfig("generateEBPineForestArchipelago"), "Biomes", generateEBPineForestArchipelago, "");
			generateEBPlateau = config.getBoolean(formatConfig("generateEBPlateau"), "Biomes", generateEBPlateau, "");
			generateEBPolarDesert = config.getBoolean(formatConfig("generateEBPolarDesert"), "Biomes", generateEBPolarDesert, "");
			generateEBPrairie = config.getBoolean(formatConfig("generateEBPrairie"), "Biomes", generateEBPrairie, "");
			generateEBRainforest = config.getBoolean(formatConfig("generateEBRainforest"), "Biomes", generateEBRainforest, "");
			generateEBRainforestValley = config.getBoolean(formatConfig("generateEBRainforestValley"), "Biomes", generateEBRainforestValley, "");
			generateEBRedDesert = config.getBoolean(formatConfig("generateEBRedDesert"), "Biomes", generateEBRedDesert, "");
			generateEBRiparianZone = config.getBoolean(formatConfig("generateEBRiparianZone"), "Biomes", generateEBRiparianZone, "");
			generateEBRockyDesert = config.getBoolean(formatConfig("generateEBRockyDesert"), "Biomes", generateEBRockyDesert, "");
			generateEBRockyHills = config.getBoolean(formatConfig("generateEBRockyHills"), "Biomes", generateEBRockyHills, "");
			generateEBRoofedShrublands = config.getBoolean(formatConfig("generateEBRoofedShrublands"), "Biomes", generateEBRoofedShrublands, "");
			generateEBSahara = config.getBoolean(formatConfig("generateEBSahara"), "Biomes", generateEBSahara, "");
			generateEBSandstoneCanyon = config.getBoolean(formatConfig("generateEBSandstoneCanyon"), "Biomes", generateEBSandstoneCanyon, "");
			generateEBSandstoneCanyons = config.getBoolean(formatConfig("generateEBSandstoneCanyons"), "Biomes", generateEBSandstoneCanyons, "");
			generateEBSandstoneRanges = config.getBoolean(formatConfig("generateEBSandstoneRanges"), "Biomes", generateEBSandstoneRanges, "");
			generateEBSandstoneRangesM = config.getBoolean(formatConfig("generateEBSandstoneRangesM"), "Biomes", generateEBSandstoneRangesM, "");
			generateEBScree = config.getBoolean(formatConfig("generateEBScree"), "Biomes", generateEBScree, "");
			generateEBScrub = config.getBoolean(formatConfig("generateEBScrub"), "Biomes", generateEBScrub, "");
			generateEBShield = config.getBoolean(formatConfig("generateEBShield"), "Biomes", generateEBShield, "");
			generateEBShrublands = config.getBoolean(formatConfig("generateEBShrublands"), "Biomes", generateEBShrublands, "");
			generateEBSilverPineForest = config.getBoolean(formatConfig("generateEBSilverPineForest"), "Biomes", generateEBSilverPineForest, "");
			generateEBSilverPineHills = config.getBoolean(formatConfig("generateEBSilverPineHills"), "Biomes", generateEBSilverPineHills, "");
			generateEBSnowyDesert = config.getBoolean(formatConfig("generateEBSnowyDesert"), "Biomes", generateEBSnowyDesert, "");
			generateEBSnowyPlateau = config.getBoolean(formatConfig("generateEBSnowyPlateau"), "Biomes", generateEBSnowyPlateau, "");
			generateEBSnowyRanges = config.getBoolean(formatConfig("generateEBSnowyRanges"), "Biomes", generateEBSnowyRanges, "");
			generateEBSnowyWastelands = config.getBoolean(formatConfig("generateEBSnowyWastelands"), "Biomes", generateEBSnowyWastelands, "");
			generateEBSteppe = config.getBoolean(formatConfig("generateEBSteppe"), "Biomes", generateEBSteppe, "");
			generateEBStoneCanyon = config.getBoolean(formatConfig("generateEBStoneCanyon"), "Biomes", generateEBStoneCanyon, "");
			generateEBStoneCanyons = config.getBoolean(formatConfig("generateEBStoneCanyons"), "Biomes", generateEBStoneCanyons, "");
			generateEBTropicalArchipelago = config.getBoolean(formatConfig("generateEBTropicalArchipelago"), "Biomes", generateEBTropicalArchipelago, "");
			generateEBTundra = config.getBoolean(formatConfig("generateEBTundra"), "Biomes", generateEBTundra, "");
			generateEBVolcano = config.getBoolean(formatConfig("generateEBVolcano"), "Biomes", generateEBVolcano, "");
			generateEBVolcanoM = config.getBoolean(formatConfig("generateEBVolcanoM"), "Biomes", generateEBVolcanoM, "");
			generateEBWastelands = config.getBoolean(formatConfig("generateEBWastelands"), "Biomes", generateEBWastelands, "");
			generateEBWoodlandField = config.getBoolean(formatConfig("generateEBWoodlandField"), "Biomes", generateEBWoodlandField, "");
			generateEBWoodlandHills = config.getBoolean(formatConfig("generateEBWoodlandHills"), "Biomes", generateEBWoodlandHills, "");
			generateEBWoodlandLake = config.getBoolean(formatConfig("generateEBWoodlandLake"), "Biomes", generateEBWoodlandLake, "");
			generateEBWoodlandLakeEdge = config.getBoolean(formatConfig("generateEBWoodlandLakeEdge"), "Biomes", generateEBWoodlandLakeEdge, "");
			generateEBWoodlands = config.getBoolean(formatConfig("generateEBWoodlands"), "Biomes", generateEBWoodlands, "");
			generateEBXericSavannah = config.getBoolean(formatConfig("generateEBXericSavannah"), "Biomes", generateEBXericSavannah, "");
			generateEBXericShrubland = config.getBoolean(formatConfig("generateEBXericShrubland"), "Biomes", generateEBXericShrubland, "");
			
			weightEBAlpineMountains = config.getInt(formatConfig("weightEBAlpineMountains"), "Weights", weightEBAlpineMountains, biomeWeightMin, biomeWeightMax, "");
			weightEBAlpineMountainsEdge = config.getInt(formatConfig("weightEBAlpineMountainsEdge"), "Weights", weightEBAlpineMountainsEdge, biomeWeightMin, biomeWeightMax, "");
			weightEBAlpineMountainsM = config.getInt(formatConfig("weightEBAlpineMountainsM"), "Weights", weightEBAlpineMountainsM, biomeWeightMin, biomeWeightMax, "");
			weightEBAlpineTundra = config.getInt(formatConfig("weightEBAlpineTundra"), "Weights", weightEBAlpineTundra, biomeWeightMin, biomeWeightMax, "");
			weightEBAspenForest = config.getInt(formatConfig("weightEBAspenForest"), "Weights", weightEBAspenForest, biomeWeightMin, biomeWeightMax, "");
			weightEBAspenHills = config.getInt(formatConfig("weightEBAspenHills"), "Weights", weightEBAspenHills, biomeWeightMin, biomeWeightMax, "");
			weightEBBadlands = config.getInt(formatConfig("weightEBBadlands"), "Weights", weightEBBadlands, biomeWeightMin, biomeWeightMax, "");
			weightEBBasin = config.getInt(formatConfig("weightEBBasin"), "Weights", weightEBBasin, biomeWeightMin, biomeWeightMax, "");
			weightEBBlossomHills = config.getInt(formatConfig("weightEBBlossomHills"), "Weights", weightEBBlossomHills, biomeWeightMin, biomeWeightMax, "");
			weightEBBlossomWoods = config.getInt(formatConfig("weightEBBlossomWoods"), "Weights", weightEBBlossomWoods, biomeWeightMin, biomeWeightMax, "");
			weightEBBorealArchipelago = config.getInt(formatConfig("weightEBBorealArchipelago"), "Weights", weightEBBorealArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBBorealForest = config.getInt(formatConfig("weightEBBorealForest"), "Weights", weightEBBorealForest, biomeWeightMin, biomeWeightMax, "");
			weightEBBorealPlateau = config.getInt(formatConfig("weightEBBorealPlateau"), "Weights", weightEBBorealPlateau, biomeWeightMin, biomeWeightMax, "");
			weightEBBorealPlateauM = config.getInt(formatConfig("weightEBBorealPlateauM"), "Weights", weightEBBorealPlateauM, biomeWeightMin, biomeWeightMax, "");
			weightEBCarr = config.getInt(formatConfig("weightEBCarr"), "Weights", weightEBCarr, biomeWeightMin, biomeWeightMax, "");
			weightEBClayHills = config.getInt(formatConfig("weightEBClayHills"), "Weights", weightEBClayHills, biomeWeightMin, biomeWeightMax, "");
			weightEBClearing = config.getInt(formatConfig("weightEBClearing"), "Weights", weightEBClearing, biomeWeightMin, biomeWeightMax, "");
			weightEBColdBorealForest = config.getInt(formatConfig("weightEBColdBorealForest"), "Weights", weightEBColdBorealForest, biomeWeightMin, biomeWeightMax, "");
			weightEBColdCypressForest = config.getInt(formatConfig("weightEBColdCypressForest"), "Weights", weightEBColdCypressForest, biomeWeightMin, biomeWeightMax, "");
			weightEBColdFirForest = config.getInt(formatConfig("weightEBColdFirForest"), "Weights", weightEBColdFirForest, biomeWeightMin, biomeWeightMax, "");
			weightEBColdPineForest = config.getInt(formatConfig("weightEBColdPineForest"), "Weights", weightEBColdPineForest, biomeWeightMin, biomeWeightMax, "");
			weightEBCreekBed = config.getInt(formatConfig("weightEBCreekBed"), "Weights", weightEBCreekBed, biomeWeightMin, biomeWeightMax, "");
			weightEBCypressForest = config.getInt(formatConfig("weightEBCypressForest"), "Weights", weightEBCypressForest, biomeWeightMin, biomeWeightMax, "");
			weightEBDesertArchipelago = config.getInt(formatConfig("weightEBDesertArchipelago"), "Weights", weightEBDesertArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBEphemeralLake = config.getInt(formatConfig("weightEBEphemeralLake"), "Weights", weightEBEphemeralLake, biomeWeightMin, biomeWeightMax, "");
			weightEBEphemeralLakeEdge = config.getInt(formatConfig("weightEBEphemeralLakeEdge"), "Weights", weightEBEphemeralLakeEdge, biomeWeightMin, biomeWeightMax, "");
			weightEBFens = config.getInt(formatConfig("weightEBFens"), "Weights", weightEBFens, biomeWeightMin, biomeWeightMax, "");
			weightEBFirForest = config.getInt(formatConfig("weightEBFirForest"), "Weights", weightEBFirForest, biomeWeightMin, biomeWeightMax, "");
			weightEBFloweryArchipelago = config.getInt(formatConfig("weightEBFloweryArchipelago"), "Weights", weightEBFloweryArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBForestedArchipelago = config.getInt(formatConfig("weightEBForestedArchipelago"), "Weights", weightEBForestedArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBForestedMountains = config.getInt(formatConfig("weightEBForestedMountains"), "Weights", weightEBForestedMountains, biomeWeightMin, biomeWeightMax, "");
			weightEBForestedValley = config.getInt(formatConfig("weightEBForestedValley"), "Weights", weightEBForestedValley, biomeWeightMin, biomeWeightMax, "");
			weightEBFrozenArchipelago = config.getInt(formatConfig("weightEBFrozenArchipelago"), "Weights", weightEBFrozenArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBGlacier = config.getInt(formatConfig("weightEBGlacier"), "Weights", weightEBGlacier, biomeWeightMin, biomeWeightMax, "");
			weightEBGrassyArchipelago = config.getInt(formatConfig("weightEBGrassyArchipelago"), "Weights", weightEBGrassyArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBIceSheet = config.getInt(formatConfig("weightEBIceSheet"), "Weights", weightEBIceSheet, biomeWeightMin, biomeWeightMax, "");
			weightEBKakadu = config.getInt(formatConfig("weightEBKakadu"), "Weights", weightEBKakadu, biomeWeightMin, biomeWeightMax, "");
			weightEBLake = config.getInt(formatConfig("weightEBLake"), "Weights", weightEBLake, biomeWeightMin, biomeWeightMax, "");
			weightEBLowHills = config.getInt(formatConfig("weightEBLowHills"), "Weights", weightEBLowHills, biomeWeightMin, biomeWeightMax, "");
			weightEBMangroves = config.getInt(formatConfig("weightEBMangroves"), "Weights", weightEBMangroves, biomeWeightMin, biomeWeightMax, "");
			weightEBMarsh = config.getInt(formatConfig("weightEBMarsh"), "Weights", weightEBMarsh, biomeWeightMin, biomeWeightMax, "");
			weightEBMeadow = config.getInt(formatConfig("weightEBMeadow"), "Weights", weightEBMeadow, biomeWeightMin, biomeWeightMax, "");
			weightEBMeadowM = config.getInt(formatConfig("weightEBMeadowM"), "Weights", weightEBMeadowM, biomeWeightMin, biomeWeightMax, "");
			weightEBMountainousArchipelago = config.getInt(formatConfig("weightEBMountainousArchipelago"), "Weights", weightEBMountainousArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBMountains = config.getInt(formatConfig("weightEBMountains"), "Weights", weightEBMountains, biomeWeightMin, biomeWeightMax, "");
			weightEBMountainsEdge = config.getInt(formatConfig("weightEBMountainsEdge"), "Weights", weightEBMountainsEdge, biomeWeightMin, biomeWeightMax, "");
			weightEBOakForest = config.getInt(formatConfig("weightEBOakForest"), "Weights", weightEBOakForest, biomeWeightMin, biomeWeightMax, "");
			weightEBOasis = config.getInt(formatConfig("weightEBOasis"), "Weights", weightEBOasis, biomeWeightMin, biomeWeightMax, "");
			weightEBPineForest = config.getInt(formatConfig("weightEBPineForest"), "Weights", weightEBPineForest, biomeWeightMin, biomeWeightMax, "");
			weightEBPineForestArchipelago = config.getInt(formatConfig("weightEBPineForestArchipelago"), "Weights", weightEBPineForestArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBPlateau = config.getInt(formatConfig("weightEBPlateau"), "Weights", weightEBPlateau, biomeWeightMin, biomeWeightMax, "");
			weightEBPolarDesert = config.getInt(formatConfig("weightEBPolarDesert"), "Weights", weightEBPolarDesert, biomeWeightMin, biomeWeightMax, "");
			weightEBPrairie = config.getInt(formatConfig("weightEBPrairie"), "Weights", weightEBPrairie, biomeWeightMin, biomeWeightMax, "");
			weightEBRainforest = config.getInt(formatConfig("weightEBRainforest"), "Weights", weightEBRainforest, biomeWeightMin, biomeWeightMax, "");
			weightEBRainforestValley = config.getInt(formatConfig("weightEBRainforestValley"), "Weights", weightEBRainforestValley, biomeWeightMin, biomeWeightMax, "");
			weightEBRedDesert = config.getInt(formatConfig("weightEBRedDesert"), "Weights", weightEBRedDesert, biomeWeightMin, biomeWeightMax, "");
			weightEBRiparianZone = config.getInt(formatConfig("weightEBRiparianZone"), "Weights", weightEBRiparianZone, biomeWeightMin, biomeWeightMax, "");
			weightEBRockyDesert = config.getInt(formatConfig("weightEBRockyDesert"), "Weights", weightEBRockyDesert, biomeWeightMin, biomeWeightMax, "");
			weightEBRockyHills = config.getInt(formatConfig("weightEBRockyHills"), "Weights", weightEBRockyHills, biomeWeightMin, biomeWeightMax, "");
			weightEBRoofedShrublands = config.getInt(formatConfig("weightEBRoofedShrublands"), "Weights", weightEBRoofedShrublands, biomeWeightMin, biomeWeightMax, "");
			weightEBSahara = config.getInt(formatConfig("weightEBSahara"), "Weights", weightEBSahara, biomeWeightMin, biomeWeightMax, "");
			weightEBSandstoneCanyon = config.getInt(formatConfig("weightEBSandstoneCanyon"), "Weights", weightEBSandstoneCanyon, biomeWeightMin, biomeWeightMax, "");
			weightEBSandstoneCanyons = config.getInt(formatConfig("weightEBSandstoneCanyons"), "Weights", weightEBSandstoneCanyons, biomeWeightMin, biomeWeightMax, "");
			weightEBSandstoneRanges = config.getInt(formatConfig("weightEBSandstoneRanges"), "Weights", weightEBSandstoneRanges, biomeWeightMin, biomeWeightMax, "");
			weightEBSandstoneRangesM = config.getInt(formatConfig("weightEBSandstoneRangesM"), "Weights", weightEBSandstoneRangesM, biomeWeightMin, biomeWeightMax, "");
			weightEBScree = config.getInt(formatConfig("weightEBScree"), "Weights", weightEBScree, biomeWeightMin, biomeWeightMax, "");
			weightEBScrub = config.getInt(formatConfig("weightEBScrub"), "Weights", weightEBScrub, biomeWeightMin, biomeWeightMax, "");
			weightEBShield = config.getInt(formatConfig("weightEBShield"), "Weights", weightEBShield, biomeWeightMin, biomeWeightMax, "");
			weightEBShrublands = config.getInt(formatConfig("weightEBShrublands"), "Weights", weightEBShrublands, biomeWeightMin, biomeWeightMax, "");
			weightEBSilverPineForest = config.getInt(formatConfig("weightEBSilverPineForest"), "Weights", weightEBSilverPineForest, biomeWeightMin, biomeWeightMax, "");
			weightEBSilverPineHills = config.getInt(formatConfig("weightEBSilverPineHills"), "Weights", weightEBSilverPineHills, biomeWeightMin, biomeWeightMax, "");
			weightEBSnowyDesert = config.getInt(formatConfig("weightEBSnowyDesert"), "Weights", weightEBSnowyDesert, biomeWeightMin, biomeWeightMax, "");
			weightEBSnowyPlateau = config.getInt(formatConfig("weightEBSnowyPlateau"), "Weights", weightEBSnowyPlateau, biomeWeightMin, biomeWeightMax, "");
			weightEBSnowyRanges = config.getInt(formatConfig("weightEBSnowyRanges"), "Weights", weightEBSnowyRanges, biomeWeightMin, biomeWeightMax, "");
			weightEBSnowyWastelands = config.getInt(formatConfig("weightEBSnowyWastelands"), "Weights", weightEBSnowyWastelands, biomeWeightMin, biomeWeightMax, "");
			weightEBSteppe = config.getInt(formatConfig("weightEBSteppe"), "Weights", weightEBSteppe, biomeWeightMin, biomeWeightMax, "");
			weightEBStoneCanyon = config.getInt(formatConfig("weightEBStoneCanyon"), "Weights", weightEBStoneCanyon, biomeWeightMin, biomeWeightMax, "");
			weightEBStoneCanyons = config.getInt(formatConfig("weightEBStoneCanyons"), "Weights", weightEBStoneCanyons, biomeWeightMin, biomeWeightMax, "");
			weightEBTropicalArchipelago = config.getInt(formatConfig("weightEBTropicalArchipelago"), "Weights", weightEBTropicalArchipelago, biomeWeightMin, biomeWeightMax, "");
			weightEBTundra = config.getInt(formatConfig("weightEBTundra"), "Weights", weightEBTundra, biomeWeightMin, biomeWeightMax, "");
			weightEBVolcano = config.getInt(formatConfig("weightEBVolcano"), "Weights", weightEBVolcano, biomeWeightMin, biomeWeightMax, "");
			weightEBVolcanoM = config.getInt(formatConfig("weightEBVolcanoM"), "Weights", weightEBVolcanoM, biomeWeightMin, biomeWeightMax, "");
			weightEBWastelands = config.getInt(formatConfig("weightEBWastelands"), "Weights", weightEBWastelands, biomeWeightMin, biomeWeightMax, "");
			weightEBWoodlandField = config.getInt(formatConfig("weightEBWoodlandField"), "Weights", weightEBWoodlandField, biomeWeightMin, biomeWeightMax, "");
			weightEBWoodlandHills = config.getInt(formatConfig("weightEBWoodlandHills"), "Weights", weightEBWoodlandHills, biomeWeightMin, biomeWeightMax, "");
			weightEBWoodlandLake = config.getInt(formatConfig("weightEBWoodlandLake"), "Weights", weightEBWoodlandLake, biomeWeightMin, biomeWeightMax, "");
			weightEBWoodlandLakeEdge = config.getInt(formatConfig("weightEBWoodlandLakeEdge"), "Weights", weightEBWoodlandLakeEdge, biomeWeightMin, biomeWeightMax, "");
			weightEBWoodlands = config.getInt(formatConfig("weightEBWoodlands"), "Weights", weightEBWoodlands, biomeWeightMin, biomeWeightMax, "");
			weightEBXericSavannah = config.getInt(formatConfig("weightEBXericSavannah"), "Weights", weightEBXericSavannah, biomeWeightMin, biomeWeightMax, "");
			weightEBXericShrubland = config.getInt(formatConfig("weightEBXericShrubland"), "Weights", weightEBXericShrubland, biomeWeightMin, biomeWeightMax, "");
			
            villageEBAlpineMountains = config.getBoolean(formatConfig("villageEBAlpineMountains"), "Villages", villageEBAlpineMountains, "");
            villageEBAlpineMountainsEdge = config.getBoolean(formatConfig("villageEBAlpineMountainsEdge"), "Villages", villageEBAlpineMountainsEdge, "");
            villageEBAlpineMountainsM = config.getBoolean(formatConfig("villageEBAlpineMountainsM"), "Villages", villageEBAlpineMountainsM, "");
            villageEBAlpineTundra = config.getBoolean(formatConfig("villageEBAlpineTundra"), "Villages", villageEBAlpineTundra, "");
            villageEBAspenForest = config.getBoolean(formatConfig("villageEBAspenForest"), "Villages", villageEBAspenForest, "");
            villageEBAspenHills = config.getBoolean(formatConfig("villageEBAspenHills"), "Villages", villageEBAspenHills, "");
            villageEBBadlands = config.getBoolean(formatConfig("villageEBBadlands"), "Villages", villageEBBadlands, "");
            villageEBBasin = config.getBoolean(formatConfig("villageEBBasin"), "Villages", villageEBBasin, "");
            villageEBBlossomHills = config.getBoolean(formatConfig("villageEBBlossomHills"), "Villages", villageEBBlossomHills, "");
            villageEBBlossomWoods = config.getBoolean(formatConfig("villageEBBlossomWoods"), "Villages", villageEBBlossomWoods, "");
            villageEBBorealArchipelago = config.getBoolean(formatConfig("villageEBBorealArchipelago"), "Villages", villageEBBorealArchipelago, "");
            villageEBBorealForest = config.getBoolean(formatConfig("villageEBBorealForest"), "Villages", villageEBBorealForest, "");
            villageEBBorealPlateau = config.getBoolean(formatConfig("villageEBBorealPlateau"), "Villages", villageEBBorealPlateau, "");
            villageEBBorealPlateauM = config.getBoolean(formatConfig("villageEBBorealPlateauM"), "Villages", villageEBBorealPlateauM, "");
            villageEBCarr = config.getBoolean(formatConfig("villageEBCarr"), "Villages", villageEBCarr, "");
            villageEBClayHills = config.getBoolean(formatConfig("villageEBClayHills"), "Villages", villageEBClayHills, "");
            villageEBClearing = config.getBoolean(formatConfig("villageEBClearing"), "Villages", villageEBClearing, "");
            villageEBColdBorealForest = config.getBoolean(formatConfig("villageEBColdBorealForest"), "Villages", villageEBColdBorealForest, "");
            villageEBColdCypressForest = config.getBoolean(formatConfig("villageEBColdCypressForest"), "Villages", villageEBColdCypressForest, "");
            villageEBColdFirForest = config.getBoolean(formatConfig("villageEBColdFirForest"), "Villages", villageEBColdFirForest, "");
            villageEBColdPineForest = config.getBoolean(formatConfig("villageEBColdPineForest"), "Villages", villageEBColdPineForest, "");
            villageEBCreekBed = config.getBoolean(formatConfig("villageEBCreekBed"), "Villages", villageEBCreekBed, "");
            villageEBCypressForest = config.getBoolean(formatConfig("villageEBCypressForest"), "Villages", villageEBCypressForest, "");
            villageEBDesertArchipelago = config.getBoolean(formatConfig("villageEBDesertArchipelago"), "Villages", villageEBDesertArchipelago, "");
            villageEBEphemeralLake = config.getBoolean(formatConfig("villageEBEphemeralLake"), "Villages", villageEBEphemeralLake, "");
            villageEBEphemeralLakeEdge = config.getBoolean(formatConfig("villageEBEphemeralLakeEdge"), "Villages", villageEBEphemeralLakeEdge, "");
            villageEBFens = config.getBoolean(formatConfig("villageEBFens"), "Villages", villageEBFens, "");
            villageEBFirForest = config.getBoolean(formatConfig("villageEBFirForest"), "Villages", villageEBFirForest, "");
            villageEBFloweryArchipelago = config.getBoolean(formatConfig("villageEBFloweryArchipelago"), "Villages", villageEBFloweryArchipelago, "");
            villageEBForestedArchipelago = config.getBoolean(formatConfig("villageEBForestedArchipelago"), "Villages", villageEBForestedArchipelago, "");
            villageEBForestedMountains = config.getBoolean(formatConfig("villageEBForestedMountains"), "Villages", villageEBForestedMountains, "");
            villageEBForestedValley = config.getBoolean(formatConfig("villageEBForestedValley"), "Villages", villageEBForestedValley, "");
            villageEBFrozenArchipelago = config.getBoolean(formatConfig("villageEBFrozenArchipelago"), "Villages", villageEBFrozenArchipelago, "");
            villageEBGlacier = config.getBoolean(formatConfig("villageEBGlacier"), "Villages", villageEBGlacier, "");
            villageEBGrassyArchipelago = config.getBoolean(formatConfig("villageEBGrassyArchipelago"), "Villages", villageEBGrassyArchipelago, "");
            villageEBIceSheet = config.getBoolean(formatConfig("villageEBIceSheet"), "Villages", villageEBIceSheet, "");
            villageEBKakadu = config.getBoolean(formatConfig("villageEBKakadu"), "Villages", villageEBKakadu, "");
            villageEBLake = config.getBoolean(formatConfig("villageEBLake"), "Villages", villageEBLake, "");
            villageEBLowHills = config.getBoolean(formatConfig("villageEBLowHills"), "Villages", villageEBLowHills, "");
            villageEBMangroves = config.getBoolean(formatConfig("villageEBMangroves"), "Villages", villageEBMangroves, "");
            villageEBMarsh = config.getBoolean(formatConfig("villageEBMarsh"), "Villages", villageEBMarsh, "");
            villageEBMeadow = config.getBoolean(formatConfig("villageEBMeadow"), "Villages", villageEBMeadow, "");
            villageEBMeadowM = config.getBoolean(formatConfig("villageEBMeadowM"), "Villages", villageEBMeadowM, "");
            villageEBMountainousArchipelago = config.getBoolean(formatConfig("villageEBMountainousArchipelago"), "Villages", villageEBMountainousArchipelago, "");
            villageEBMountains = config.getBoolean(formatConfig("villageEBMountains"), "Villages", villageEBMountains, "");
            villageEBMountainsEdge = config.getBoolean(formatConfig("villageEBMountainsEdge"), "Villages", villageEBMountainsEdge, "");
            villageEBOakForest = config.getBoolean(formatConfig("villageEBOakForest"), "Villages", villageEBOakForest, "");
            villageEBOasis = config.getBoolean(formatConfig("villageEBOasis"), "Villages", villageEBOasis, "");
            villageEBPineForest = config.getBoolean(formatConfig("villageEBPineForest"), "Villages", villageEBPineForest, "");
            villageEBPineForestArchipelago = config.getBoolean(formatConfig("villageEBPineForestArchipelago"), "Villages", villageEBPineForestArchipelago, "");
            villageEBPlateau = config.getBoolean(formatConfig("villageEBPlateau"), "Villages", villageEBPlateau, "");
            villageEBPolarDesert = config.getBoolean(formatConfig("villageEBPolarDesert"), "Villages", villageEBPolarDesert, "");
            villageEBPrairie = config.getBoolean(formatConfig("villageEBPrairie"), "Villages", villageEBPrairie, "");
            villageEBRainforest = config.getBoolean(formatConfig("villageEBRainforest"), "Villages", villageEBRainforest, "");
            villageEBRainforestValley = config.getBoolean(formatConfig("villageEBRainforestValley"), "Villages", villageEBRainforestValley, "");
            villageEBRedDesert = config.getBoolean(formatConfig("villageEBRedDesert"), "Villages", villageEBRedDesert, "");
            villageEBRiparianZone = config.getBoolean(formatConfig("villageEBRiparianZone"), "Villages", villageEBRiparianZone, "");
            villageEBRockyDesert = config.getBoolean(formatConfig("villageEBRockyDesert"), "Villages", villageEBRockyDesert, "");
            villageEBRockyHills = config.getBoolean(formatConfig("villageEBRockyHills"), "Villages", villageEBRockyHills, "");
            villageEBRoofedShrublands = config.getBoolean(formatConfig("villageEBRoofedShrublands"), "Villages", villageEBRoofedShrublands, "");
            villageEBSahara = config.getBoolean(formatConfig("villageEBSahara"), "Villages", villageEBSahara, "");
            villageEBSandstoneCanyon = config.getBoolean(formatConfig("villageEBSandstoneCanyon"), "Villages", villageEBSandstoneCanyon, "");
            villageEBSandstoneCanyons = config.getBoolean(formatConfig("villageEBSandstoneCanyons"), "Villages", villageEBSandstoneCanyons, "");
            villageEBSandstoneRanges = config.getBoolean(formatConfig("villageEBSandstoneRanges"), "Villages", villageEBSandstoneRanges, "");
            villageEBSandstoneRangesM = config.getBoolean(formatConfig("villageEBSandstoneRangesM"), "Villages", villageEBSandstoneRangesM, "");
            villageEBScree = config.getBoolean(formatConfig("villageEBScree"), "Villages", villageEBScree, "");
            villageEBScrub = config.getBoolean(formatConfig("villageEBScrub"), "Villages", villageEBScrub, "");
            villageEBShield = config.getBoolean(formatConfig("villageEBShield"), "Villages", villageEBShield, "");
            villageEBShrublands = config.getBoolean(formatConfig("villageEBShrublands"), "Villages", villageEBShrublands, "");
            villageEBSilverPineForest = config.getBoolean(formatConfig("villageEBSilverPineForest"), "Villages", villageEBSilverPineForest, "");
            villageEBSilverPineHills = config.getBoolean(formatConfig("villageEBSilverPineHills"), "Villages", villageEBSilverPineHills, "");
            villageEBSnowyDesert = config.getBoolean(formatConfig("villageEBSnowyDesert"), "Villages", villageEBSnowyDesert, "");
            villageEBSnowyPlateau = config.getBoolean(formatConfig("villageEBSnowyPlateau"), "Villages", villageEBSnowyPlateau, "");
            villageEBSnowyRanges = config.getBoolean(formatConfig("villageEBSnowyRanges"), "Villages", villageEBSnowyRanges, "");
            villageEBSnowyWastelands = config.getBoolean(formatConfig("villageEBSnowyWastelands"), "Villages", villageEBSnowyWastelands, "");
            villageEBSteppe = config.getBoolean(formatConfig("villageEBSteppe"), "Villages", villageEBSteppe, "");
            villageEBStoneCanyon = config.getBoolean(formatConfig("villageEBStoneCanyon"), "Villages", villageEBStoneCanyon, "");
            villageEBStoneCanyons = config.getBoolean(formatConfig("villageEBStoneCanyons"), "Villages", villageEBStoneCanyons, "");
            villageEBTropicalArchipelago = config.getBoolean(formatConfig("villageEBTropicalArchipelago"), "Villages", villageEBTropicalArchipelago, "");
            villageEBTundra = config.getBoolean(formatConfig("villageEBTundra"), "Villages", villageEBTundra, "");
            villageEBVolcano = config.getBoolean(formatConfig("villageEBVolcano"), "Villages", villageEBVolcano, "");
            villageEBVolcanoM = config.getBoolean(formatConfig("villageEBVolcanoM"), "Villages", villageEBVolcanoM, "");
            villageEBWastelands = config.getBoolean(formatConfig("villageEBWastelands"), "Villages", villageEBWastelands, "");
            villageEBWoodlandField = config.getBoolean(formatConfig("villageEBWoodlandField"), "Villages", villageEBWoodlandField, "");
            villageEBWoodlandHills = config.getBoolean(formatConfig("villageEBWoodlandHills"), "Villages", villageEBWoodlandHills, "");
            villageEBWoodlandLake = config.getBoolean(formatConfig("villageEBWoodlandLake"), "Villages", villageEBWoodlandLake, "");
            villageEBWoodlandLakeEdge = config.getBoolean(formatConfig("villageEBWoodlandLakeEdge"), "Villages", villageEBWoodlandLakeEdge, "");
            villageEBWoodlands = config.getBoolean(formatConfig("villageEBWoodlands"), "Villages", villageEBWoodlands, "");
            villageEBXericSavannah = config.getBoolean(formatConfig("villageEBXericSavannah"), "Villages", villageEBXericSavannah, "");
            villageEBXericShrubland = config.getBoolean(formatConfig("villageEBXericShrubland"), "Villages", villageEBXericShrubland, "");
            
            BiomeConfig[] biomeConfigs = BiomeConfigEB.getBiomeConfigs();
            String categoryName;
            
            for (int i = 0; i < biomeConfigs.length; i++) {
                
                categoryName = "biome." + biomeConfigs[i].modSlug + "." + biomeConfigs[i].biomeSlug;
                
                biomeConfigs[i].enableBiome = config.getBoolean(
                    "enableBiome",
                    categoryName,
                    biomeConfigs[i].enableBiome,
                    ""
                );
                
                biomeConfigs[i].biomeWeight = config.getInt(
                    "biomeWeight",
                    categoryName,
                    biomeConfigs[i].biomeWeight,
                    biomeWeightMin,
                    biomeWeightMax,
                    ""
                );
                
                biomeConfigs[i].villageBiome = config.getBoolean(
                    "villageBiome",
                    categoryName,
                    biomeConfigs[i].villageBiome,
                    ""
                );
            }
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading EB configuration.");	
		}
		finally 
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}
	
    private static String formatConfig(String s)
    {
        String returnString = s;        
        
        returnString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(returnString), " ");

        if (s.startsWith("generate")) {
            returnString = StringUtils.replace(returnString, "generate", "Generate", 1);
        }
        else if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        else if (s.startsWith("weight")) {
            returnString = StringUtils.replace(returnString, "weight", "Weight of", 1);
        }
        
        return returnString;
    }
}
