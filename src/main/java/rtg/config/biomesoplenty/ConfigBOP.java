package rtg.config.biomesoplenty;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigBOP 
{
	public static Configuration config;
	
	public static boolean generateBOPBiomes = true;
	
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
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigBOP.getBiomeConfigs(), config);
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

        if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        
        return returnString;
    }
}
