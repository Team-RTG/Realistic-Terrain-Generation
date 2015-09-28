package rtg.config;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;

public class ConfigEB 
{
	public static Configuration config;
	
	public static boolean generateEBBiomes = true;
			
	public static boolean generateEBAlpineMountains = true;
	public static boolean generateEBAlpineMountainsEdge = true;
	public static boolean generateEBAlpineMountainsM = true;
	public static boolean generateEBAlpineTundra = true;
	public static boolean generateEBAspenForest = true;
	public static boolean generateEBAspenHills = true;
	public static boolean generateEBBadlands = true;
	public static boolean generateEBBasin = true;
	public static boolean generateEBBlossomHills = true;
	public static boolean generateEBBlossomWoods = true;
	public static boolean generateEBBorealArchipelago = true;
	public static boolean generateEBBorealForest = true;
	public static boolean generateEBBorealPlateau = true;
	public static boolean generateEBBorealPlateauM = true;
	public static boolean generateEBCarr = true;
	public static boolean generateEBClayHills = true;
	public static boolean generateEBClearing = true;
	public static boolean generateEBColdBorealForest = true;
	public static boolean generateEBColdCypressForest = true;
	public static boolean generateEBColdFirForest = true;
	public static boolean generateEBColdPineForest = true;
	public static boolean generateEBCreekBed = true;
	public static boolean generateEBCypressForest = true;
	public static boolean generateEBDesertArchipelago = true;
	public static boolean generateEBEphemeralLake = true;
	public static boolean generateEBEphemeralLakeEdge = true;
	public static boolean generateEBFens = true;
	public static boolean generateEBFirForest = true;
	public static boolean generateEBFloweryArchipelago = true;
	public static boolean generateEBForestedArchipelago = true;
	public static boolean generateEBForestedMountains = true;
	public static boolean generateEBForestedValley = true;
	public static boolean generateEBFrozenArchipelago = true;
	public static boolean generateEBGlacier = true;
	public static boolean generateEBGrassyArchipelago = true;
	public static boolean generateEBIceSheet = true;
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
	public static boolean generateEBPolarDesert = true;
	public static boolean generateEBPrairie = true;
	public static boolean generateEBRainforest = true;
	public static boolean generateEBRainforestValley = true;
	public static boolean generateEBRedDesert = true;
	public static boolean generateEBRiparianZone = true;
	public static boolean generateEBRockyDesert = true;
	public static boolean generateEBRockyHills = true;
	public static boolean generateEBRoofedShrublands = true;
	public static boolean generateEBSahara = true;
	public static boolean generateEBSandstoneCanyon = true;
	public static boolean generateEBSandstoneCanyons = true;
	public static boolean generateEBSandstoneRanges = true;
	public static boolean generateEBSandstoneRangesM = true;
	public static boolean generateEBScree = true;
	public static boolean generateEBScrub = true;
	public static boolean generateEBShield = true;
	public static boolean generateEBShrublands = true;
	public static boolean generateEBSilverPineForest = true;
	public static boolean generateEBSilverPineHills = true;
	public static boolean generateEBSnowyDesert = true;
	public static boolean generateEBSnowyPlateau = true;
	public static boolean generateEBSnowyRanges = true;
	public static boolean generateEBSnowyWastelands = true;
	public static boolean generateEBSteppe = true;
	public static boolean generateEBStoneCanyon = true;
	public static boolean generateEBStoneCanyons = true;
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
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateEBBiomes = config.getBoolean("Generate EB Biomes", "EB Biomes", true, "");
			
			generateEBAlpineMountains = config.getBoolean("generateEBAlpineMountains", "EB Biomes", true, "");
			generateEBAlpineMountainsEdge = config.getBoolean("generateEBAlpineMountainsEdge", "EB Biomes", true, "");
			generateEBAlpineMountainsM = config.getBoolean("generateEBAlpineMountainsM", "EB Biomes", true, "");
			generateEBAlpineTundra = config.getBoolean("generateEBAlpineTundra", "EB Biomes", true, "");
			generateEBAspenForest = config.getBoolean("generateEBAspenForest", "EB Biomes", true, "");
			generateEBAspenHills = config.getBoolean("generateEBAspenHills", "EB Biomes", true, "");
			generateEBBadlands = config.getBoolean("generateEBBadlands", "EB Biomes", true, "");
			generateEBBasin = config.getBoolean("generateEBBasin", "EB Biomes", true, "");
			generateEBBlossomHills = config.getBoolean("generateEBBlossomHills", "EB Biomes", true, "");
			generateEBBlossomWoods = config.getBoolean("generateEBBlossomWoods", "EB Biomes", true, "");
			generateEBBorealArchipelago = config.getBoolean("generateEBBorealArchipelago", "EB Biomes", true, "");
			generateEBBorealForest = config.getBoolean("generateEBBorealForest", "EB Biomes", true, "");
			generateEBBorealPlateau = config.getBoolean("generateEBBorealPlateau", "EB Biomes", true, "");
			generateEBBorealPlateauM = config.getBoolean("generateEBBorealPlateauM", "EB Biomes", true, "");
			generateEBCarr = config.getBoolean("generateEBCarr", "EB Biomes", true, "");
			generateEBClayHills = config.getBoolean("generateEBClayHills", "EB Biomes", true, "");
			generateEBClearing = config.getBoolean("generateEBClearing", "EB Biomes", true, "");
			generateEBColdBorealForest = config.getBoolean("generateEBColdBorealForest", "EB Biomes", true, "");
			generateEBColdCypressForest = config.getBoolean("generateEBColdCypressForest", "EB Biomes", true, "");
			generateEBColdFirForest = config.getBoolean("generateEBColdFirForest", "EB Biomes", true, "");
			generateEBColdPineForest = config.getBoolean("generateEBColdPineForest", "EB Biomes", true, "");
			generateEBCreekBed = config.getBoolean("generateEBCreekBed", "EB Biomes", true, "");
			generateEBCypressForest = config.getBoolean("generateEBCypressForest", "EB Biomes", true, "");
			generateEBDesertArchipelago = config.getBoolean("generateEBDesertArchipelago", "EB Biomes", true, "");
			generateEBEphemeralLake = config.getBoolean("generateEBEphemeralLake", "EB Biomes", true, "");
			generateEBEphemeralLakeEdge = config.getBoolean("generateEBEphemeralLakeEdge", "EB Biomes", true, "");
			generateEBFens = config.getBoolean("generateEBFens", "EB Biomes", true, "");
			generateEBFirForest = config.getBoolean("generateEBFirForest", "EB Biomes", true, "");
			generateEBFloweryArchipelago = config.getBoolean("generateEBFloweryArchipelago", "EB Biomes", true, "");
			generateEBForestedArchipelago = config.getBoolean("generateEBForestedArchipelago", "EB Biomes", true, "");
			generateEBForestedMountains = config.getBoolean("generateEBForestedMountains", "EB Biomes", true, "");
			generateEBForestedValley = config.getBoolean("generateEBForestedValley", "EB Biomes", true, "");
			generateEBFrozenArchipelago = config.getBoolean("generateEBFrozenArchipelago", "EB Biomes", true, "");
			generateEBGlacier = config.getBoolean("generateEBGlacier", "EB Biomes", true, "");
			generateEBGrassyArchipelago = config.getBoolean("generateEBGrassyArchipelago", "EB Biomes", true, "");
			generateEBIceSheet = config.getBoolean("generateEBIceSheet", "EB Biomes", true, "");
			generateEBKakadu = config.getBoolean("generateEBKakadu", "EB Biomes", true, "");
			generateEBLake = config.getBoolean("generateEBLake", "EB Biomes", true, "");
			generateEBLowHills = config.getBoolean("generateEBLowHills", "EB Biomes", true, "");
			generateEBMangroves = config.getBoolean("generateEBMangroves", "EB Biomes", true, "");
			generateEBMarsh = config.getBoolean("generateEBMarsh", "EB Biomes", true, "");
			generateEBMeadow = config.getBoolean("generateEBMeadow", "EB Biomes", true, "");
			generateEBMeadowM = config.getBoolean("generateEBMeadowM", "EB Biomes", true, "");
			generateEBMountainousArchipelago = config.getBoolean("generateEBMountainousArchipelago", "EB Biomes", true, "");
			generateEBMountains = config.getBoolean("generateEBMountains", "EB Biomes", true, "");
			generateEBMountainsEdge = config.getBoolean("generateEBMountainsEdge", "EB Biomes", true, "");
			generateEBOakForest = config.getBoolean("generateEBOakForest", "EB Biomes", true, "");
			generateEBOasis = config.getBoolean("generateEBOasis", "EB Biomes", true, "");
			generateEBPineForest = config.getBoolean("generateEBPineForest", "EB Biomes", true, "");
			generateEBPineForestArchipelago = config.getBoolean("generateEBPineForestArchipelago", "EB Biomes", true, "");
			generateEBPlateau = config.getBoolean("generateEBPlateau", "EB Biomes", true, "");
			generateEBPolarDesert = config.getBoolean("generateEBPolarDesert", "EB Biomes", true, "");
			generateEBPrairie = config.getBoolean("generateEBPrairie", "EB Biomes", true, "");
			generateEBRainforest = config.getBoolean("generateEBRainforest", "EB Biomes", true, "");
			generateEBRainforestValley = config.getBoolean("generateEBRainforestValley", "EB Biomes", true, "");
			generateEBRedDesert = config.getBoolean("generateEBRedDesert", "EB Biomes", true, "");
			generateEBRiparianZone = config.getBoolean("generateEBRiparianZone", "EB Biomes", true, "");
			generateEBRockyDesert = config.getBoolean("generateEBRockyDesert", "EB Biomes", true, "");
			generateEBRockyHills = config.getBoolean("generateEBRockyHills", "EB Biomes", true, "");
			generateEBRoofedShrublands = config.getBoolean("generateEBRoofedShrublands", "EB Biomes", true, "");
			generateEBSahara = config.getBoolean("generateEBSahara", "EB Biomes", true, "");
			generateEBSandstoneCanyon = config.getBoolean("generateEBSandstoneCanyon", "EB Biomes", true, "");
			generateEBSandstoneCanyons = config.getBoolean("generateEBSandstoneCanyons", "EB Biomes", true, "");
			generateEBSandstoneRanges = config.getBoolean("generateEBSandstoneRanges", "EB Biomes", true, "");
			generateEBSandstoneRangesM = config.getBoolean("generateEBSandstoneRangesM", "EB Biomes", true, "");
			generateEBScree = config.getBoolean("generateEBScree", "EB Biomes", true, "");
			generateEBScrub = config.getBoolean("generateEBScrub", "EB Biomes", true, "");
			generateEBShield = config.getBoolean("generateEBShield", "EB Biomes", true, "");
			generateEBShrublands = config.getBoolean("generateEBShrublands", "EB Biomes", true, "");
			generateEBSilverPineForest = config.getBoolean("generateEBSilverPineForest", "EB Biomes", true, "");
			generateEBSilverPineHills = config.getBoolean("generateEBSilverPineHills", "EB Biomes", true, "");
			generateEBSnowyDesert = config.getBoolean("generateEBSnowyDesert", "EB Biomes", true, "");
			generateEBSnowyPlateau = config.getBoolean("generateEBSnowyPlateau", "EB Biomes", true, "");
			generateEBSnowyRanges = config.getBoolean("generateEBSnowyRanges", "EB Biomes", true, "");
			generateEBSnowyWastelands = config.getBoolean("generateEBSnowyWastelands", "EB Biomes", true, "");
			generateEBSteppe = config.getBoolean("generateEBSteppe", "EB Biomes", true, "");
			generateEBStoneCanyon = config.getBoolean("generateEBStoneCanyon", "EB Biomes", true, "");
			generateEBStoneCanyons = config.getBoolean("generateEBStoneCanyons", "EB Biomes", true, "");
			generateEBTropicalArchipelago = config.getBoolean("generateEBTropicalArchipelago", "EB Biomes", true, "");
			generateEBTundra = config.getBoolean("generateEBTundra", "EB Biomes", true, "");
			generateEBVolcano = config.getBoolean("generateEBVolcano", "EB Biomes", true, "");
			generateEBVolcanoM = config.getBoolean("generateEBVolcanoM", "EB Biomes", true, "");
			generateEBWastelands = config.getBoolean("generateEBWastelands", "EB Biomes", true, "");
			generateEBWoodlandField = config.getBoolean("generateEBWoodlandField", "EB Biomes", true, "");
			generateEBWoodlandHills = config.getBoolean("generateEBWoodlandHills", "EB Biomes", true, "");
			generateEBWoodlandLake = config.getBoolean("generateEBWoodlandLake", "EB Biomes", true, "");
			generateEBWoodlandLakeEdge = config.getBoolean("generateEBWoodlandLakeEdge", "EB Biomes", true, "");
			generateEBWoodlands = config.getBoolean("generateEBWoodlands", "EB Biomes", true, "");
			generateEBXericSavannah = config.getBoolean("generateEBXericSavannah", "EB Biomes", true, "");
			generateEBXericShrubland = config.getBoolean("generateEBXericShrubland", "EB Biomes", true, "");
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
}
