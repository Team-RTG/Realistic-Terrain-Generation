package rtg.config.enhancedbiomes;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.enhancedbiomes.config.BiomeConfigEB;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigEB 
{
	public static Configuration config;
	
	public static boolean generateEBBiomes = true;
	
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
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigEB.getBiomeConfigs(), config);
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

        if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        
        return returnString;
    }
}
