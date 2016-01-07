package rtg.config.biomesoplenty;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;

public class ConfigBOP 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateBOPBiomes = true;
			
	public static boolean generateBOPAlps = true;
	public static boolean generateBOPArctic = true;
	public static boolean generateBOPBambooForest = true;
	public static boolean generateBOPBayou = true;
	public static boolean generateBOPBog = true;
	public static boolean generateBOPBorealForest = true;
	public static boolean generateBOPBrushland = true;
	public static boolean generateBOPCanyon = true;
	public static boolean generateBOPChaparral = true;
	public static boolean generateBOPCherryBlossomGrove = true;
	public static boolean generateBOPConiferousForest = true;
	public static boolean generateBOPSnowyConiferousForest = true;
	public static boolean generateBOPCrag = true;
	public static boolean generateBOPDeadForest = true;
	public static boolean generateBOPDeadSwamp = true;
	public static boolean generateBOPDeciduousForest = true;
	public static boolean generateBOPDenseForest = true;
	public static boolean generateBOPDryRiver = true;
	public static boolean generateBOPEucalyptusForest = true;
	public static boolean generateBOPFen = true;
	public static boolean generateBOPFlowerField = true;
	public static boolean generateBOPFrostForest = true;
	public static boolean generateBOPFungiForest = true;
	public static boolean generateBOPGarden = true;
	public static boolean generateBOPGrassland = true;
	public static boolean generateBOPGrove = true;
	public static boolean generateBOPHeathland = true;
	public static boolean generateBOPHighland = true;
	public static boolean generateBOPJadeCliffs = true;
	public static boolean generateBOPLandOfLakes = true;
	public static boolean generateBOPLavenderFields = true;
	public static boolean generateBOPLushDesert = true;
	public static boolean generateBOPLushRiver = true;
	public static boolean generateBOPLushSwamp = true;
	public static boolean generateBOPMapleWoods = true;
	public static boolean generateBOPMarsh = true;
	public static boolean generateBOPMeadow = true;
	public static boolean generateBOPMoor = true;
	public static boolean generateBOPMountain = true;
	public static boolean generateBOPMysticGrove = true;
	public static boolean generateBOPOminousWoods = true;
	public static boolean generateBOPOriginValley = true;
	public static boolean generateBOPOutback = true;
	public static boolean generateBOPPrairie = true;
	public static boolean generateBOPRainforest = true;
	public static boolean generateBOPRedwoodForest = true;
	public static boolean generateBOPSacredSprings = true;
	public static boolean generateBOPSeasonalForest = true;
	public static boolean generateBOPShield = true;
	public static boolean generateBOPShrubland = true;
	public static boolean generateBOPSludgepit = true;
	public static boolean generateBOPSteppe = true;
	public static boolean generateBOPTemperateRainforest = true;
	public static boolean generateBOPThicket = true;
	public static boolean generateBOPTropicalRainforest = true;
	public static boolean generateBOPTundra = true;
	public static boolean generateBOPWasteland = true;
	public static boolean generateBOPWetland = true;
	public static boolean generateBOPWoodland = true;
	public static boolean generateBOPXericShrubland = true;
		
	//Sub biomes
	public static boolean generateBOPAlpsForest = true;
	public static boolean generateBOPCanyonRavine = true;
	public static boolean generateBOPGlacier = true;
	public static boolean generateBOPLandOfLakesMarsh = true;
	public static boolean generateBOPMangrove = true;
	public static boolean generateBOPMeadowForest = true;
	public static boolean generateBOPOasis = true;
	public static boolean generateBOPOrchard = true;
	public static boolean generateBOPQuagmire = true;
	public static boolean generateBOPScrubland = true;
	public static boolean generateBOPSeasonalForestClearing = true;
	public static boolean generateBOPSilkglades = true;
	public static boolean generateBOPSpruceWoods = true;
	public static boolean generateBOPTropics = true;
	public static boolean generateBOPVolcano = false;
	
	//Water biomes
	public static boolean generateBOPCoralReef = true;
	public static boolean generateBOPKelpForest = true;
	
	public static int weightBOPAlps = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPArctic = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPBambooForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPBayou = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPBog = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPBorealForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPBrushland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPCanyon = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPChaparral = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPCherryBlossomGrove = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPConiferousForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPSnowyConiferousForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPCrag = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPDeadForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPDeadSwamp = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPDeciduousForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPDenseForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPDryRiver = 0;
	public static int weightBOPEucalyptusForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPFen = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPFlowerField = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPFrostForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPFungiForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPGarden = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPGrassland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPGrove = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPHeathland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPHighland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPJadeCliffs = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPLandOfLakes = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPLavenderFields = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPLushDesert = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPLushRiver = 0;
	public static int weightBOPLushSwamp = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPMapleWoods = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPMarsh = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPMeadow = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPMoor = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPMountain = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPMysticGrove = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPOminousWoods = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPOriginValley = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPOutback = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPPrairie = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPRainforest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPRedwoodForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPSacredSprings = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPSeasonalForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPShield = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPShrubland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPSludgepit = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPSteppe = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPTemperateRainforest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPThicket = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPTropicalRainforest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPTundra = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPWasteland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPWetland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPWoodland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightBOPXericShrubland = (int)Math.floor((double)(biomeWeightDefault * 0.8));
		
	//Sub biomes
	public static int weightBOPAlpsForest = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPCanyonRavine = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPGlacier = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPLandOfLakesMarsh = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPMangrove = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPMeadowForest = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPOasis = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPOrchard = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPQuagmire = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPScrubland = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPSeasonalForestClearing = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPSilkglades = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPSpruceWoods = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPTropics = (int)Math.floor((double)(biomeWeightDefault * 0.6));
	public static int weightBOPVolcano = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	
	//Water biomes
	public static int weightBOPCoralReef = (int)Math.floor((double)(biomeWeightDefault * 1.2));
	public static int weightBOPKelpForest = (int)Math.floor((double)(biomeWeightDefault * 1.2));
	
    public static boolean villageBOPAlps = true;
    public static boolean villageBOPArctic = true;
    public static boolean villageBOPBambooForest = true;
    public static boolean villageBOPBayou = true;
    public static boolean villageBOPBog = true;
    public static boolean villageBOPBorealForest = true;
    public static boolean villageBOPBrushland = true;
    public static boolean villageBOPCanyon = true;
    public static boolean villageBOPChaparral = true;
    public static boolean villageBOPCherryBlossomGrove = true;
    public static boolean villageBOPConiferousForest = true;
    public static boolean villageBOPSnowyConiferousForest = true;
    public static boolean villageBOPCrag = true;
    public static boolean villageBOPDeadForest = true;
    public static boolean villageBOPDeadSwamp = true;
    public static boolean villageBOPDeciduousForest = true;
    public static boolean villageBOPDenseForest = true;
    public static boolean villageBOPDryRiver = false;
    public static boolean villageBOPEucalyptusForest = true;
    public static boolean villageBOPFen = true;
    public static boolean villageBOPFlowerField = true;
    public static boolean villageBOPFrostForest = true;
    public static boolean villageBOPFungiForest = true;
    public static boolean villageBOPGarden = true;
    public static boolean villageBOPGrassland = true;
    public static boolean villageBOPGrove = true;
    public static boolean villageBOPHeathland = true;
    public static boolean villageBOPHighland = true;
    public static boolean villageBOPJadeCliffs = true;
    public static boolean villageBOPLandOfLakes = true;
    public static boolean villageBOPLavenderFields = true;
    public static boolean villageBOPLushDesert = true;
    public static boolean villageBOPLushRiver = false;
    public static boolean villageBOPLushSwamp = true;
    public static boolean villageBOPMapleWoods = true;
    public static boolean villageBOPMarsh = true;
    public static boolean villageBOPMeadow = true;
    public static boolean villageBOPMoor = true;
    public static boolean villageBOPMountain = true;
    public static boolean villageBOPMysticGrove = true;
    public static boolean villageBOPOminousWoods = true;
    public static boolean villageBOPOriginValley = true;
    public static boolean villageBOPOutback = true;
    public static boolean villageBOPPrairie = true;
    public static boolean villageBOPRainforest = true;
    public static boolean villageBOPRedwoodForest = true;
    public static boolean villageBOPSacredSprings = true;
    public static boolean villageBOPSeasonalForest = true;
    public static boolean villageBOPShield = true;
    public static boolean villageBOPShrubland = true;
    public static boolean villageBOPSludgepit = true;
    public static boolean villageBOPSteppe = true;
    public static boolean villageBOPTemperateRainforest = true;
    public static boolean villageBOPThicket = true;
    public static boolean villageBOPTropicalRainforest = true;
    public static boolean villageBOPTundra = true;
    public static boolean villageBOPWasteland = true;
    public static boolean villageBOPWetland = true;
    public static boolean villageBOPWoodland = true;
    public static boolean villageBOPXericShrubland = true;
        
    //Sub biomes
    public static boolean villageBOPAlpsForest = true;
    public static boolean villageBOPCanyonRavine = true;
    public static boolean villageBOPGlacier = true;
    public static boolean villageBOPLandOfLakesMarsh = true;
    public static boolean villageBOPMangrove = true;
    public static boolean villageBOPMeadowForest = true;
    public static boolean villageBOPOasis = true;
    public static boolean villageBOPOrchard = true;
    public static boolean villageBOPQuagmire = true;
    public static boolean villageBOPScrubland = true;
    public static boolean villageBOPSeasonalForestClearing = true;
    public static boolean villageBOPSilkglades = true;
    public static boolean villageBOPSpruceWoods = true;
    public static boolean villageBOPTropics = true;
    public static boolean villageBOPVolcano = false;
    
    //Water biomes
    public static boolean villageBOPCoralReef = false;
    public static boolean villageBOPKelpForest = false;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//Bop
			generateBOPBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateBOPBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
			
			//Overworld biomes
			generateBOPAlps = config.getBoolean(formatConfig("generateBOPAlps"), "Biomes", generateBOPAlps, "");
			generateBOPArctic = config.getBoolean(formatConfig("generateBOPArctic"), "Biomes", generateBOPArctic, "");
			generateBOPBambooForest = config.getBoolean(formatConfig("generateBOPBambooForest"), "Biomes", generateBOPBambooForest, "");
			generateBOPBayou = config.getBoolean(formatConfig("generateBOPBayou"), "Biomes", generateBOPBayou, "");
			generateBOPBog = config.getBoolean(formatConfig("generateBOPBog"), "Biomes", generateBOPBog, "");
			generateBOPBorealForest = config.getBoolean(formatConfig("generateBOPBorealForest"), "Biomes", generateBOPBorealForest, "");
			generateBOPBrushland = config.getBoolean(formatConfig("generateBOPBrushland"), "Biomes", generateBOPBrushland, "");
			generateBOPCanyon = config.getBoolean(formatConfig("generateBOPCanyon"), "Biomes", generateBOPCanyon, "");
			generateBOPChaparral = config.getBoolean(formatConfig("generateBOPChaparral"), "Biomes", generateBOPChaparral, "");
			generateBOPCherryBlossomGrove = config.getBoolean(formatConfig("generateBOPCherryBlossomGrove"), "Biomes", generateBOPCherryBlossomGrove, "");
			generateBOPConiferousForest = config.getBoolean(formatConfig("generateBOPConiferousForest"), "Biomes", generateBOPConiferousForest, "");
			generateBOPSnowyConiferousForest = config.getBoolean(formatConfig("generateBOPSnowyConiferousForest"), "Biomes", generateBOPSnowyConiferousForest, "");
			generateBOPCrag = config.getBoolean(formatConfig("generateBOPCrag"), "Biomes", generateBOPCrag, "");
			generateBOPDeadForest = config.getBoolean(formatConfig("generateBOPDeadForest"), "Biomes", generateBOPDeadForest, "");
			generateBOPDeadSwamp = config.getBoolean(formatConfig("generateBOPDeadSwamp"), "Biomes", generateBOPDeadSwamp, "");
			generateBOPDeciduousForest = config.getBoolean(formatConfig("generateBOPDeciduousForest"), "Biomes", generateBOPDeciduousForest, "");
			generateBOPDenseForest = config.getBoolean(formatConfig("generateBOPDenseForest"), "Biomes", generateBOPDenseForest, "");
			generateBOPDryRiver = config.getBoolean(formatConfig("generateBOPDryRiver"), "Biomes", generateBOPDryRiver, "");
			generateBOPEucalyptusForest = config.getBoolean(formatConfig("generateBOPEucalyptusForest"), "Biomes", generateBOPEucalyptusForest, "");
			generateBOPFen = config.getBoolean(formatConfig("generateBOPFen"), "Biomes", generateBOPFen, "");
			generateBOPFlowerField = config.getBoolean(formatConfig("generateBOPFlowerField"), "Biomes", generateBOPFlowerField, "");
			generateBOPFrostForest = config.getBoolean(formatConfig("generateBOPFrostForest"), "Biomes", generateBOPFrostForest, "");
			generateBOPFungiForest = config.getBoolean(formatConfig("generateBOPFungiForest"), "Biomes", generateBOPFungiForest, "");
			generateBOPGarden = config.getBoolean(formatConfig("generateBOPGarden"), "Biomes", generateBOPGarden, "");
			generateBOPGrassland = config.getBoolean(formatConfig("generateBOPGrassland"), "Biomes", generateBOPGrassland, "");
			generateBOPGrove = config.getBoolean(formatConfig("generateBOPGrove"), "Biomes", generateBOPGrove, "");
			generateBOPHeathland = config.getBoolean(formatConfig("generateBOPHeathland"), "Biomes", generateBOPHeathland, "");
			generateBOPHighland = config.getBoolean(formatConfig("generateBOPHighland"), "Biomes", generateBOPHighland, "");
			generateBOPJadeCliffs = config.getBoolean(formatConfig("generateBOPJadeCliffs"), "Biomes", generateBOPJadeCliffs, "");
			generateBOPLandOfLakes = config.getBoolean(formatConfig("generateBOPLandOfLakes"), "Biomes", generateBOPLandOfLakes, "");
			generateBOPLavenderFields = config.getBoolean(formatConfig("generateBOPLavenderFields"), "Biomes", generateBOPLavenderFields, "");
			generateBOPLushDesert = config.getBoolean(formatConfig("generateBOPLushDesert"), "Biomes", generateBOPLushDesert, "");
			generateBOPLushRiver = config.getBoolean(formatConfig("generateBOPLushRiver"), "Biomes", generateBOPLushRiver, "");
			generateBOPLushSwamp = config.getBoolean(formatConfig("generateBOPLushSwamp"), "Biomes", generateBOPLushSwamp, "");
			generateBOPMapleWoods = config.getBoolean(formatConfig("generateBOPMapleWoods"), "Biomes", generateBOPMapleWoods, "");
			generateBOPMarsh = config.getBoolean(formatConfig("generateBOPMarsh"), "Biomes", generateBOPMarsh, "");
			generateBOPMeadow = config.getBoolean(formatConfig("generateBOPMeadow"), "Biomes", generateBOPMeadow, "");
			generateBOPMoor = config.getBoolean(formatConfig("generateBOPMoor"), "Biomes", generateBOPMoor, "");
			generateBOPMountain = config.getBoolean(formatConfig("generateBOPMountain"), "Biomes", generateBOPMountain, "");
			generateBOPMysticGrove = config.getBoolean(formatConfig("generateBOPMysticGrove"), "Biomes", generateBOPMysticGrove, "");
			generateBOPOminousWoods = config.getBoolean(formatConfig("generateBOPOminousWoods"), "Biomes", generateBOPOminousWoods, "");
			generateBOPOriginValley = config.getBoolean(formatConfig("generateBOPOriginValley"), "Biomes", generateBOPOriginValley, "");
			generateBOPOutback = config.getBoolean(formatConfig("generateBOPOutback"), "Biomes", generateBOPOutback, "");
			generateBOPPrairie = config.getBoolean(formatConfig("generateBOPPrairie"), "Biomes", generateBOPPrairie, "");
			generateBOPRainforest = config.getBoolean(formatConfig("generateBOPRainforest"), "Biomes", generateBOPRainforest, "");
			generateBOPRedwoodForest = config.getBoolean(formatConfig("generateBOPRedwoodForest"), "Biomes", generateBOPRedwoodForest, "");
			generateBOPSacredSprings = config.getBoolean(formatConfig("generateBOPSacredSprings"), "Biomes", generateBOPSacredSprings, "");
			generateBOPSeasonalForest = config.getBoolean(formatConfig("generateBOPSeasonalForest"), "Biomes", generateBOPSeasonalForest, "");
			generateBOPShield = config.getBoolean(formatConfig("generateBOPShield"), "Biomes", generateBOPShield, "");
			generateBOPShrubland = config.getBoolean(formatConfig("generateBOPShrubland"), "Biomes", generateBOPShrubland, "");
			generateBOPSludgepit = config.getBoolean(formatConfig("generateBOPSludgepit"), "Biomes", generateBOPSludgepit, "");
			generateBOPSteppe = config.getBoolean(formatConfig("generateBOPSteppe"), "Biomes", generateBOPSteppe, "");
			generateBOPTemperateRainforest = config.getBoolean(formatConfig("generateBOPTemperateRainforest"), "Biomes", generateBOPTemperateRainforest, "");
			generateBOPThicket = config.getBoolean(formatConfig("generateBOPThicket"), "Biomes", generateBOPThicket, "");
			generateBOPTropicalRainforest = config.getBoolean(formatConfig("generateBOPTropicalRainforest"), "Biomes", generateBOPTropicalRainforest, "");
			generateBOPTundra = config.getBoolean(formatConfig("generateBOPTundra"), "Biomes", generateBOPTundra, "");
			generateBOPWasteland = config.getBoolean(formatConfig("generateBOPWasteland"), "Biomes", generateBOPWasteland, "");
			generateBOPWetland = config.getBoolean(formatConfig("generateBOPWetland"), "Biomes", generateBOPWetland, "");
			generateBOPWoodland = config.getBoolean(formatConfig("generateBOPWoodland"), "Biomes", generateBOPWoodland, "");
			generateBOPXericShrubland = config.getBoolean(formatConfig("generateBOPXericShrubland"), "Biomes", generateBOPXericShrubland, "");
			
			//Sub biomes
			generateBOPAlpsForest = config.getBoolean(formatConfig("generateBOPAlpsForest"), "Biomes", generateBOPAlpsForest, "");
			generateBOPCanyonRavine = config.getBoolean(formatConfig("generateBOPCanyonRavine"), "Biomes", generateBOPCanyonRavine, "");
			generateBOPGlacier = config.getBoolean(formatConfig("generateBOPGlacier"), "Biomes", generateBOPGlacier, "");
			generateBOPLandOfLakesMarsh = config.getBoolean(formatConfig("generateBOPLandOfLakesMarsh"), "Biomes", generateBOPLandOfLakesMarsh, "");
			generateBOPMangrove = config.getBoolean(formatConfig("generateBOPMangrove"), "Biomes", generateBOPMangrove, "");
			generateBOPMeadowForest = config.getBoolean(formatConfig("generateBOPMeadowForest"), "Biomes", generateBOPMeadowForest, "");
			generateBOPOasis = config.getBoolean(formatConfig("generateBOPOasis"), "Biomes", generateBOPOasis, "");
			generateBOPOrchard = config.getBoolean(formatConfig("generateBOPOrchard"), "Biomes", generateBOPOrchard, "");
			generateBOPQuagmire = config.getBoolean(formatConfig("generateBOPQuagmire"), "Biomes", generateBOPQuagmire, "");
			generateBOPScrubland = config.getBoolean(formatConfig("generateBOPScrubland"), "Biomes", generateBOPScrubland, "");
			generateBOPSeasonalForestClearing = config.getBoolean(formatConfig("generateBOPSeasonalForestClearing"), "Biomes", generateBOPSeasonalForestClearing, "");
			generateBOPSilkglades = config.getBoolean(formatConfig("generateBOPSilkglades"), "Biomes", generateBOPSilkglades, "");
			generateBOPSpruceWoods = config.getBoolean(formatConfig("generateBOPSpruceWoods"), "Biomes", generateBOPSpruceWoods, "");
			generateBOPTropics = config.getBoolean(formatConfig("generateBOPTropics"), "Biomes", generateBOPTropics, "");
			generateBOPVolcano = config.getBoolean(formatConfig("generateBOPVolcano"), "Biomes", generateBOPVolcano, "");
			
			//Water biomes
			generateBOPCoralReef = config.getBoolean(formatConfig("generateBOPCoralReef"), "Biomes", generateBOPCoralReef, "");
			generateBOPKelpForest = config.getBoolean(formatConfig("generateBOPKelpForest"), "Biomes", generateBOPKelpForest, "");
			
			//Overworld biomes
			weightBOPAlps = config.getInt(formatConfig("weightBOPAlps"), "Weights", weightBOPAlps, biomeWeightMin, biomeWeightMax, "");
			weightBOPArctic = config.getInt(formatConfig("weightBOPArctic"), "Weights", weightBOPArctic, biomeWeightMin, biomeWeightMax, "");
			weightBOPBambooForest = config.getInt(formatConfig("weightBOPBambooForest"), "Weights", weightBOPBambooForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPBayou = config.getInt(formatConfig("weightBOPBayou"), "Weights", weightBOPBayou, biomeWeightMin, biomeWeightMax, "");
			weightBOPBog = config.getInt(formatConfig("weightBOPBog"), "Weights", weightBOPBog, biomeWeightMin, biomeWeightMax, "");
			weightBOPBorealForest = config.getInt(formatConfig("weightBOPBorealForest"), "Weights", weightBOPBorealForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPBrushland = config.getInt(formatConfig("weightBOPBrushland"), "Weights", weightBOPBrushland, biomeWeightMin, biomeWeightMax, "");
			weightBOPCanyon = config.getInt(formatConfig("weightBOPCanyon"), "Weights", weightBOPCanyon, biomeWeightMin, biomeWeightMax, "");
			weightBOPChaparral = config.getInt(formatConfig("weightBOPChaparral"), "Weights", weightBOPChaparral, biomeWeightMin, biomeWeightMax, "");
			weightBOPCherryBlossomGrove = config.getInt(formatConfig("weightBOPCherryBlossomGrove"), "Weights", weightBOPCherryBlossomGrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPConiferousForest = config.getInt(formatConfig("weightBOPConiferousForest"), "Weights", weightBOPConiferousForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPSnowyConiferousForest = config.getInt(formatConfig("weightBOPSnowyConiferousForest"), "Weights", weightBOPSnowyConiferousForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPCrag = config.getInt(formatConfig("weightBOPCrag"), "Weights", weightBOPCrag, biomeWeightMin, biomeWeightMax, "");
			weightBOPDeadForest = config.getInt(formatConfig("weightBOPDeadForest"), "Weights", weightBOPDeadForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPDeadSwamp = config.getInt(formatConfig("weightBOPDeadSwamp"), "Weights", weightBOPDeadSwamp, biomeWeightMin, biomeWeightMax, "");
			weightBOPDeciduousForest = config.getInt(formatConfig("weightBOPDeciduousForest"), "Weights", weightBOPDeciduousForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPDenseForest = config.getInt(formatConfig("weightBOPDenseForest"), "Weights", weightBOPDenseForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPDryRiver = config.getInt(formatConfig("weightBOPDryRiver"), "Weights", weightBOPDryRiver, biomeWeightMin, biomeWeightMax, "");
			weightBOPEucalyptusForest = config.getInt(formatConfig("weightBOPEucalyptusForest"), "Weights", weightBOPEucalyptusForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPFen = config.getInt(formatConfig("weightBOPFen"), "Weights", weightBOPFen, biomeWeightMin, biomeWeightMax, "");
			weightBOPFlowerField = config.getInt(formatConfig("weightBOPFlowerField"), "Weights", weightBOPFlowerField, biomeWeightMin, biomeWeightMax, "");
			weightBOPFrostForest = config.getInt(formatConfig("weightBOPFrostForest"), "Weights", weightBOPFrostForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPFungiForest = config.getInt(formatConfig("weightBOPFungiForest"), "Weights", weightBOPFungiForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPGarden = config.getInt(formatConfig("weightBOPGarden"), "Weights", weightBOPGarden, biomeWeightMin, biomeWeightMax, "");
			weightBOPGrassland = config.getInt(formatConfig("weightBOPGrassland"), "Weights", weightBOPGrassland, biomeWeightMin, biomeWeightMax, "");
			weightBOPGrove = config.getInt(formatConfig("weightBOPGrove"), "Weights", weightBOPGrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPHeathland = config.getInt(formatConfig("weightBOPHeathland"), "Weights", weightBOPHeathland, biomeWeightMin, biomeWeightMax, "");
			weightBOPHighland = config.getInt(formatConfig("weightBOPHighland"), "Weights", weightBOPHighland, biomeWeightMin, biomeWeightMax, "");
			weightBOPJadeCliffs = config.getInt(formatConfig("weightBOPJadeCliffs"), "Weights", weightBOPJadeCliffs, biomeWeightMin, biomeWeightMax, "");
			weightBOPLandOfLakes = config.getInt(formatConfig("weightBOPLandOfLakes"), "Weights", weightBOPLandOfLakes, biomeWeightMin, biomeWeightMax, "");
			weightBOPLavenderFields = config.getInt(formatConfig("weightBOPLavenderFields"), "Weights", weightBOPLavenderFields, biomeWeightMin, biomeWeightMax, "");
			weightBOPLushDesert = config.getInt(formatConfig("weightBOPLushDesert"), "Weights", weightBOPLushDesert, biomeWeightMin, biomeWeightMax, "");
			weightBOPLushRiver = config.getInt(formatConfig("weightBOPLushRiver"), "Weights", weightBOPLushRiver, biomeWeightMin, biomeWeightMax, "");
			weightBOPLushSwamp = config.getInt(formatConfig("weightBOPLushSwamp"), "Weights", weightBOPLushSwamp, biomeWeightMin, biomeWeightMax, "");
			weightBOPMapleWoods = config.getInt(formatConfig("weightBOPMapleWoods"), "Weights", weightBOPMapleWoods, biomeWeightMin, biomeWeightMax, "");
			weightBOPMarsh = config.getInt(formatConfig("weightBOPMarsh"), "Weights", weightBOPMarsh, biomeWeightMin, biomeWeightMax, "");
			weightBOPMeadow = config.getInt(formatConfig("weightBOPMeadow"), "Weights", weightBOPMeadow, biomeWeightMin, biomeWeightMax, "");
			weightBOPMoor = config.getInt(formatConfig("weightBOPMoor"), "Weights", weightBOPMoor, biomeWeightMin, biomeWeightMax, "");
			weightBOPMountain = config.getInt(formatConfig("weightBOPMountain"), "Weights", weightBOPMountain, biomeWeightMin, biomeWeightMax, "");
			weightBOPMysticGrove = config.getInt(formatConfig("weightBOPMysticGrove"), "Weights", weightBOPMysticGrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPOminousWoods = config.getInt(formatConfig("weightBOPOminousWoods"), "Weights", weightBOPOminousWoods, biomeWeightMin, biomeWeightMax, "");
			weightBOPOriginValley = config.getInt(formatConfig("weightBOPOriginValley"), "Weights", weightBOPOriginValley, biomeWeightMin, biomeWeightMax, "");
			weightBOPOutback = config.getInt(formatConfig("weightBOPOutback"), "Weights", weightBOPOutback, biomeWeightMin, biomeWeightMax, "");
			weightBOPPrairie = config.getInt(formatConfig("weightBOPPrairie"), "Weights", weightBOPPrairie, biomeWeightMin, biomeWeightMax, "");
			weightBOPRainforest = config.getInt(formatConfig("weightBOPRainforest"), "Weights", weightBOPRainforest, biomeWeightMin, biomeWeightMax, "");
			weightBOPRedwoodForest = config.getInt(formatConfig("weightBOPRedwoodForest"), "Weights", weightBOPRedwoodForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPSacredSprings = config.getInt(formatConfig("weightBOPSacredSprings"), "Weights", weightBOPSacredSprings, biomeWeightMin, biomeWeightMax, "");
			weightBOPSeasonalForest = config.getInt(formatConfig("weightBOPSeasonalForest"), "Weights", weightBOPSeasonalForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPShield = config.getInt(formatConfig("weightBOPShield"), "Weights", weightBOPShield, biomeWeightMin, biomeWeightMax, "");
			weightBOPShrubland = config.getInt(formatConfig("weightBOPShrubland"), "Weights", weightBOPShrubland, biomeWeightMin, biomeWeightMax, "");
			weightBOPSludgepit = config.getInt(formatConfig("weightBOPSludgepit"), "Weights", weightBOPSludgepit, biomeWeightMin, biomeWeightMax, "");
			weightBOPSteppe = config.getInt(formatConfig("weightBOPSteppe"), "Weights", weightBOPSteppe, biomeWeightMin, biomeWeightMax, "");
			weightBOPTemperateRainforest = config.getInt(formatConfig("weightBOPTemperateRainforest"), "Weights", weightBOPTemperateRainforest, biomeWeightMin, biomeWeightMax, "");
			weightBOPThicket = config.getInt(formatConfig("weightBOPThicket"), "Weights", weightBOPThicket, biomeWeightMin, biomeWeightMax, "");
			weightBOPTropicalRainforest = config.getInt(formatConfig("weightBOPTropicalRainforest"), "Weights", weightBOPTropicalRainforest, biomeWeightMin, biomeWeightMax, "");
			weightBOPTundra = config.getInt(formatConfig("weightBOPTundra"), "Weights", weightBOPTundra, biomeWeightMin, biomeWeightMax, "");
			weightBOPWasteland = config.getInt(formatConfig("weightBOPWasteland"), "Weights", weightBOPWasteland, biomeWeightMin, biomeWeightMax, "");
			weightBOPWetland = config.getInt(formatConfig("weightBOPWetland"), "Weights", weightBOPWetland, biomeWeightMin, biomeWeightMax, "");
			weightBOPWoodland = config.getInt(formatConfig("weightBOPWoodland"), "Weights", weightBOPWoodland, biomeWeightMin, biomeWeightMax, "");
			weightBOPXericShrubland = config.getInt(formatConfig("weightBOPXericShrubland"), "Weights", weightBOPXericShrubland, biomeWeightMin, biomeWeightMax, "");
			
			//Sub biomes
			weightBOPAlpsForest = config.getInt(formatConfig("weightBOPAlpsForest"), "Weights", weightBOPAlpsForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPCanyonRavine = config.getInt(formatConfig("weightBOPCanyonRavine"), "Weights", weightBOPCanyonRavine, biomeWeightMin, biomeWeightMax, "");
			weightBOPGlacier = config.getInt(formatConfig("weightBOPGlacier"), "Weights", weightBOPGlacier, biomeWeightMin, biomeWeightMax, "");
			weightBOPLandOfLakesMarsh = config.getInt(formatConfig("weightBOPLandOfLakesMarsh"), "Weights", weightBOPLandOfLakesMarsh, biomeWeightMin, biomeWeightMax, "");
			weightBOPMangrove = config.getInt(formatConfig("weightBOPMangrove"), "Weights", weightBOPMangrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPMeadowForest = config.getInt(formatConfig("weightBOPMeadowForest"), "Weights", weightBOPMeadowForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPOasis = config.getInt(formatConfig("weightBOPOasis"), "Weights", weightBOPOasis, biomeWeightMin, biomeWeightMax, "");
			weightBOPOrchard = config.getInt(formatConfig("weightBOPOrchard"), "Weights", weightBOPOrchard, biomeWeightMin, biomeWeightMax, "");
			weightBOPQuagmire = config.getInt(formatConfig("weightBOPQuagmire"), "Weights", weightBOPQuagmire, biomeWeightMin, biomeWeightMax, "");
			weightBOPScrubland = config.getInt(formatConfig("weightBOPScrubland"), "Weights", weightBOPScrubland, biomeWeightMin, biomeWeightMax, "");
			weightBOPSeasonalForestClearing = config.getInt(formatConfig("weightBOPSeasonalForestClearing"), "Weights", weightBOPSeasonalForestClearing, biomeWeightMin, biomeWeightMax, "");
			weightBOPSilkglades = config.getInt(formatConfig("weightBOPSilkglades"), "Weights", weightBOPSilkglades, biomeWeightMin, biomeWeightMax, "");
			weightBOPSpruceWoods = config.getInt(formatConfig("weightBOPSpruceWoods"), "Weights", weightBOPSpruceWoods, biomeWeightMin, biomeWeightMax, "");
			weightBOPTropics = config.getInt(formatConfig("weightBOPTropics"), "Weights", weightBOPTropics, biomeWeightMin, biomeWeightMax, "");
			weightBOPVolcano = config.getInt(formatConfig("weightBOPVolcano"), "Weights", weightBOPVolcano, biomeWeightMin, biomeWeightMax, "");
			
			//Water biomes
			weightBOPCoralReef = config.getInt(formatConfig("weightBOPCoralReef"), "Weights", weightBOPCoralReef, biomeWeightMin, biomeWeightMax, "");
			weightBOPKelpForest = config.getInt(formatConfig("weightBOPKelpForest"), "Weights", weightBOPKelpForest, biomeWeightMin, biomeWeightMax, "");
			
			
            //Overworld biomes
            villageBOPAlps = config.getBoolean(formatConfig("villageBOPAlps"), "Villages", villageBOPAlps, "");
            villageBOPArctic = config.getBoolean(formatConfig("villageBOPArctic"), "Villages", villageBOPArctic, "");
            villageBOPBambooForest = config.getBoolean(formatConfig("villageBOPBambooForest"), "Villages", villageBOPBambooForest, "");
            villageBOPBayou = config.getBoolean(formatConfig("villageBOPBayou"), "Villages", villageBOPBayou, "");
            villageBOPBog = config.getBoolean(formatConfig("villageBOPBog"), "Villages", villageBOPBog, "");
            villageBOPBorealForest = config.getBoolean(formatConfig("villageBOPBorealForest"), "Villages", villageBOPBorealForest, "");
            villageBOPBrushland = config.getBoolean(formatConfig("villageBOPBrushland"), "Villages", villageBOPBrushland, "");
            villageBOPCanyon = config.getBoolean(formatConfig("villageBOPCanyon"), "Villages", villageBOPCanyon, "");
            villageBOPChaparral = config.getBoolean(formatConfig("villageBOPChaparral"), "Villages", villageBOPChaparral, "");
            villageBOPCherryBlossomGrove = config.getBoolean(formatConfig("villageBOPCherryBlossomGrove"), "Villages", villageBOPCherryBlossomGrove, "");
            villageBOPConiferousForest = config.getBoolean(formatConfig("villageBOPConiferousForest"), "Villages", villageBOPConiferousForest, "");
            villageBOPSnowyConiferousForest = config.getBoolean(formatConfig("villageBOPSnowyConiferousForest"), "Villages", villageBOPSnowyConiferousForest, "");
            villageBOPCrag = config.getBoolean(formatConfig("villageBOPCrag"), "Villages", villageBOPCrag, "");
            villageBOPDeadForest = config.getBoolean(formatConfig("villageBOPDeadForest"), "Villages", villageBOPDeadForest, "");
            villageBOPDeadSwamp = config.getBoolean(formatConfig("villageBOPDeadSwamp"), "Villages", villageBOPDeadSwamp, "");
            villageBOPDeciduousForest = config.getBoolean(formatConfig("villageBOPDeciduousForest"), "Villages", villageBOPDeciduousForest, "");
            villageBOPDenseForest = config.getBoolean(formatConfig("villageBOPDenseForest"), "Villages", villageBOPDenseForest, "");
            villageBOPDryRiver = config.getBoolean(formatConfig("villageBOPDryRiver"), "Villages", villageBOPDryRiver, "");
            villageBOPEucalyptusForest = config.getBoolean(formatConfig("villageBOPEucalyptusForest"), "Villages", villageBOPEucalyptusForest, "");
            villageBOPFen = config.getBoolean(formatConfig("villageBOPFen"), "Villages", villageBOPFen, "");
            villageBOPFlowerField = config.getBoolean(formatConfig("villageBOPFlowerField"), "Villages", villageBOPFlowerField, "");
            villageBOPFrostForest = config.getBoolean(formatConfig("villageBOPFrostForest"), "Villages", villageBOPFrostForest, "");
            villageBOPFungiForest = config.getBoolean(formatConfig("villageBOPFungiForest"), "Villages", villageBOPFungiForest, "");
            villageBOPGarden = config.getBoolean(formatConfig("villageBOPGarden"), "Villages", villageBOPGarden, "");
            villageBOPGrassland = config.getBoolean(formatConfig("villageBOPGrassland"), "Villages", villageBOPGrassland, "");
            villageBOPGrove = config.getBoolean(formatConfig("villageBOPGrove"), "Villages", villageBOPGrove, "");
            villageBOPHeathland = config.getBoolean(formatConfig("villageBOPHeathland"), "Villages", villageBOPHeathland, "");
            villageBOPHighland = config.getBoolean(formatConfig("villageBOPHighland"), "Villages", villageBOPHighland, "");
            villageBOPJadeCliffs = config.getBoolean(formatConfig("villageBOPJadeCliffs"), "Villages", villageBOPJadeCliffs, "");
            villageBOPLandOfLakes = config.getBoolean(formatConfig("villageBOPLandOfLakes"), "Villages", villageBOPLandOfLakes, "");
            villageBOPLavenderFields = config.getBoolean(formatConfig("villageBOPLavenderFields"), "Villages", villageBOPLavenderFields, "");
            villageBOPLushDesert = config.getBoolean(formatConfig("villageBOPLushDesert"), "Villages", villageBOPLushDesert, "");
            villageBOPLushRiver = config.getBoolean(formatConfig("villageBOPLushRiver"), "Villages", villageBOPLushRiver, "");
            villageBOPLushSwamp = config.getBoolean(formatConfig("villageBOPLushSwamp"), "Villages", villageBOPLushSwamp, "");
            villageBOPMapleWoods = config.getBoolean(formatConfig("villageBOPMapleWoods"), "Villages", villageBOPMapleWoods, "");
            villageBOPMarsh = config.getBoolean(formatConfig("villageBOPMarsh"), "Villages", villageBOPMarsh, "");
            villageBOPMeadow = config.getBoolean(formatConfig("villageBOPMeadow"), "Villages", villageBOPMeadow, "");
            villageBOPMoor = config.getBoolean(formatConfig("villageBOPMoor"), "Villages", villageBOPMoor, "");
            villageBOPMountain = config.getBoolean(formatConfig("villageBOPMountain"), "Villages", villageBOPMountain, "");
            villageBOPMysticGrove = config.getBoolean(formatConfig("villageBOPMysticGrove"), "Villages", villageBOPMysticGrove, "");
            villageBOPOminousWoods = config.getBoolean(formatConfig("villageBOPOminousWoods"), "Villages", villageBOPOminousWoods, "");
            villageBOPOriginValley = config.getBoolean(formatConfig("villageBOPOriginValley"), "Villages", villageBOPOriginValley, "");
            villageBOPOutback = config.getBoolean(formatConfig("villageBOPOutback"), "Villages", villageBOPOutback, "");
            villageBOPPrairie = config.getBoolean(formatConfig("villageBOPPrairie"), "Villages", villageBOPPrairie, "");
            villageBOPRainforest = config.getBoolean(formatConfig("villageBOPRainforest"), "Villages", villageBOPRainforest, "");
            villageBOPRedwoodForest = config.getBoolean(formatConfig("villageBOPRedwoodForest"), "Villages", villageBOPRedwoodForest, "");
            villageBOPSacredSprings = config.getBoolean(formatConfig("villageBOPSacredSprings"), "Villages", villageBOPSacredSprings, "");
            villageBOPSeasonalForest = config.getBoolean(formatConfig("villageBOPSeasonalForest"), "Villages", villageBOPSeasonalForest, "");
            villageBOPShield = config.getBoolean(formatConfig("villageBOPShield"), "Villages", villageBOPShield, "");
            villageBOPShrubland = config.getBoolean(formatConfig("villageBOPShrubland"), "Villages", villageBOPShrubland, "");
            villageBOPSludgepit = config.getBoolean(formatConfig("villageBOPSludgepit"), "Villages", villageBOPSludgepit, "");
            villageBOPSteppe = config.getBoolean(formatConfig("villageBOPSteppe"), "Villages", villageBOPSteppe, "");
            villageBOPTemperateRainforest = config.getBoolean(formatConfig("villageBOPTemperateRainforest"), "Villages", villageBOPTemperateRainforest, "");
            villageBOPThicket = config.getBoolean(formatConfig("villageBOPThicket"), "Villages", villageBOPThicket, "");
            villageBOPTropicalRainforest = config.getBoolean(formatConfig("villageBOPTropicalRainforest"), "Villages", villageBOPTropicalRainforest, "");
            villageBOPTundra = config.getBoolean(formatConfig("villageBOPTundra"), "Villages", villageBOPTundra, "");
            villageBOPWasteland = config.getBoolean(formatConfig("villageBOPWasteland"), "Villages", villageBOPWasteland, "");
            villageBOPWetland = config.getBoolean(formatConfig("villageBOPWetland"), "Villages", villageBOPWetland, "");
            villageBOPWoodland = config.getBoolean(formatConfig("villageBOPWoodland"), "Villages", villageBOPWoodland, "");
            villageBOPXericShrubland = config.getBoolean(formatConfig("villageBOPXericShrubland"), "Villages", villageBOPXericShrubland, "");
            
            //Sub biomes
            villageBOPAlpsForest = config.getBoolean(formatConfig("villageBOPAlpsForest"), "Villages", villageBOPAlpsForest, "");
            villageBOPCanyonRavine = config.getBoolean(formatConfig("villageBOPCanyonRavine"), "Villages", villageBOPCanyonRavine, "");
            villageBOPGlacier = config.getBoolean(formatConfig("villageBOPGlacier"), "Villages", villageBOPGlacier, "");
            villageBOPLandOfLakesMarsh = config.getBoolean(formatConfig("villageBOPLandOfLakesMarsh"), "Villages", villageBOPLandOfLakesMarsh, "");
            villageBOPMangrove = config.getBoolean(formatConfig("villageBOPMangrove"), "Villages", villageBOPMangrove, "");
            villageBOPMeadowForest = config.getBoolean(formatConfig("villageBOPMeadowForest"), "Villages", villageBOPMeadowForest, "");
            villageBOPOasis = config.getBoolean(formatConfig("villageBOPOasis"), "Villages", villageBOPOasis, "");
            villageBOPOrchard = config.getBoolean(formatConfig("villageBOPOrchard"), "Villages", villageBOPOrchard, "");
            villageBOPQuagmire = config.getBoolean(formatConfig("villageBOPQuagmire"), "Villages", villageBOPQuagmire, "");
            villageBOPScrubland = config.getBoolean(formatConfig("villageBOPScrubland"), "Villages", villageBOPScrubland, "");
            villageBOPSeasonalForestClearing = config.getBoolean(formatConfig("villageBOPSeasonalForestClearing"), "Villages", villageBOPSeasonalForestClearing, "");
            villageBOPSilkglades = config.getBoolean(formatConfig("villageBOPSilkglades"), "Villages", villageBOPSilkglades, "");
            villageBOPSpruceWoods = config.getBoolean(formatConfig("villageBOPSpruceWoods"), "Villages", villageBOPSpruceWoods, "");
            villageBOPTropics = config.getBoolean(formatConfig("villageBOPTropics"), "Villages", villageBOPTropics, "");
            villageBOPVolcano = config.getBoolean(formatConfig("villageBOPVolcano"), "Villages", villageBOPVolcano, "");
            
            //Water biomes
            villageBOPCoralReef = config.getBoolean(formatConfig("villageBOPCoralReef"), "Villages", villageBOPCoralReef, "");
            villageBOPKelpForest = config.getBoolean(formatConfig("villageBOPKelpForest"), "Villages", villageBOPKelpForest, "");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading BOP configuration.");	
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
