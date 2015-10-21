package rtg.config.biomesoplenty;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;

public class ConfigBOP 
{
	public static Configuration config;
	
	public static final int biomeWeightMin = 0;
	public static final int biomeWeightMax = 100;
	public static final int biomeWeightDefault = 10;
	
	public static boolean generateBOPBiomes = true;
			
	public static boolean generateBOPalps = true;
	public static boolean generateBOParctic = true;
	public static boolean generateBOPbambooForest = true;
	public static boolean generateBOPbayou = true;
	public static boolean generateBOPbog = true;
	public static boolean generateBOPborealForest = true;
	public static boolean generateBOPbrushland = true;
	public static boolean generateBOPcanyon = true;
	public static boolean generateBOPchaparral = true;
	public static boolean generateBOPcherryBlossomGrove = true;
	public static boolean generateBOPconiferousForest = true;
	public static boolean generateBOPsnowyConiferousForest = true;
	public static boolean generateBOPcrag = true;
	public static boolean generateBOPdeadForest = true;
	public static boolean generateBOPdeadSwamp = true;
	public static boolean generateBOPdeciduousForest = true;
	public static boolean generateBOPdenseForest = true;
	public static boolean generateBOPeucalyptusForest = true;
	public static boolean generateBOPfen = true;
	public static boolean generateBOPflowerField = true;
	public static boolean generateBOPfrostForest = true;
	public static boolean generateBOPfungiForest = true;
	public static boolean generateBOPgarden = true;
	public static boolean generateBOPgrassland = true;
	public static boolean generateBOPgrove = true;
	public static boolean generateBOPheathland = true;
	public static boolean generateBOPhighland = true;
	public static boolean generateBOPjadeCliffs = true;
	public static boolean generateBOPlandOfLakes = true;
	public static boolean generateBOPlavenderFields = true;
	public static boolean generateBOPlushDesert = true;
	public static boolean generateBOPlushSwamp = true;
	public static boolean generateBOPmapleWoods = true;
	public static boolean generateBOPmarsh = true;
	public static boolean generateBOPmeadow = true;
	public static boolean generateBOPmoor = true;
	public static boolean generateBOPmountain = true;
	public static boolean generateBOPmysticGrove = true;
	public static boolean generateBOPominousWoods = true;
	public static boolean generateBOPoriginValley = true;
	public static boolean generateBOPoutback = true;
	public static boolean generateBOPprairie = true;
	public static boolean generateBOPrainforest = true;
	public static boolean generateBOPredwoodForest = true;
	public static boolean generateBOPsacredSprings = true;
	public static boolean generateBOPseasonalForest = true;
	public static boolean generateBOPshield = true;
	public static boolean generateBOPshrubland = true;
	public static boolean generateBOPsludgepit = true;
	public static boolean generateBOPsteppe = true;
	public static boolean generateBOPtemperateRainforest = true;
	public static boolean generateBOPthicket = true;
	public static boolean generateBOPtropicalRainforest = true;
	public static boolean generateBOPtundra = true;
	public static boolean generateBOPwasteland = true;
	public static boolean generateBOPwetland = true;
	public static boolean generateBOPwoodland = true;
	public static boolean generateBOPxericShrubland = true;
		
	//Sub biomes
	public static boolean generateBOPalpsForest = true;
	public static boolean generateBOPcanyonRavine = true;
	public static boolean generateBOPglacier = true;
	public static boolean generateBOPlandOfLakesMarsh = true;
	public static boolean generateBOPmangrove = true;
	public static boolean generateBOPmeadowForest = true;
	public static boolean generateBOPoasis = true;
	public static boolean generateBOPorchard = true;
	public static boolean generateBOPquagmire = true;
	public static boolean generateBOPscrubland = true;
	public static boolean generateBOPseasonalForestClearing = true;
	public static boolean generateBOPsilkglades = true;
	public static boolean generateBOPspruceWoods = true;
	public static boolean generateBOPtropics = true;
	public static boolean generateBOPvolcano = true;
	
	//Water biomes
	public static boolean generateBOPcoralReef = true;
	public static boolean generateBOPkelpForest = true;
	
	public static int weightBOPalps = biomeWeightDefault;
	public static int weightBOParctic = biomeWeightDefault;
	public static int weightBOPbambooForest = biomeWeightDefault;
	public static int weightBOPbayou = biomeWeightDefault;
	public static int weightBOPbog = biomeWeightDefault;
	public static int weightBOPborealForest = biomeWeightDefault;
	public static int weightBOPbrushland = biomeWeightDefault;
	public static int weightBOPcanyon = biomeWeightDefault;
	public static int weightBOPchaparral = biomeWeightDefault;
	public static int weightBOPcherryBlossomGrove = biomeWeightDefault;
	public static int weightBOPconiferousForest = biomeWeightDefault;
	public static int weightBOPsnowyConiferousForest = biomeWeightDefault;
	public static int weightBOPcrag = biomeWeightDefault;
	public static int weightBOPdeadForest = biomeWeightDefault;
	public static int weightBOPdeadSwamp = biomeWeightDefault;
	public static int weightBOPdeciduousForest = biomeWeightDefault;
	public static int weightBOPdenseForest = biomeWeightDefault;
	public static int weightBOPeucalyptusForest = biomeWeightDefault;
	public static int weightBOPfen = biomeWeightDefault;
	public static int weightBOPflowerField = biomeWeightDefault;
	public static int weightBOPfrostForest = biomeWeightDefault;
	public static int weightBOPfungiForest = biomeWeightDefault;
	public static int weightBOPgarden = biomeWeightDefault;
	public static int weightBOPgrassland = biomeWeightDefault;
	public static int weightBOPgrove = biomeWeightDefault;
	public static int weightBOPheathland = biomeWeightDefault;
	public static int weightBOPhighland = biomeWeightDefault;
	public static int weightBOPjadeCliffs = biomeWeightDefault;
	public static int weightBOPlandOfLakes = biomeWeightDefault;
	public static int weightBOPlavenderFields = biomeWeightDefault;
	public static int weightBOPlushDesert = biomeWeightDefault;
	public static int weightBOPlushSwamp = biomeWeightDefault;
	public static int weightBOPmapleWoods = biomeWeightDefault;
	public static int weightBOPmarsh = biomeWeightDefault;
	public static int weightBOPmeadow = biomeWeightDefault;
	public static int weightBOPmoor = biomeWeightDefault;
	public static int weightBOPmountain = biomeWeightDefault;
	public static int weightBOPmysticGrove = biomeWeightDefault;
	public static int weightBOPominousWoods = biomeWeightDefault;
	public static int weightBOPoriginValley = biomeWeightDefault;
	public static int weightBOPoutback = biomeWeightDefault;
	public static int weightBOPprairie = biomeWeightDefault;
	public static int weightBOPrainforest = biomeWeightDefault;
	public static int weightBOPredwoodForest = biomeWeightDefault;
	public static int weightBOPsacredSprings = biomeWeightDefault;
	public static int weightBOPseasonalForest = biomeWeightDefault;
	public static int weightBOPshield = biomeWeightDefault;
	public static int weightBOPshrubland = biomeWeightDefault;
	public static int weightBOPsludgepit = biomeWeightDefault;
	public static int weightBOPsteppe = biomeWeightDefault;
	public static int weightBOPtemperateRainforest = biomeWeightDefault;
	public static int weightBOPthicket = biomeWeightDefault;
	public static int weightBOPtropicalRainforest = biomeWeightDefault;
	public static int weightBOPtundra = biomeWeightDefault;
	public static int weightBOPwasteland = biomeWeightDefault;
	public static int weightBOPwetland = biomeWeightDefault;
	public static int weightBOPwoodland = biomeWeightDefault;
	public static int weightBOPxericShrubland = biomeWeightDefault;
		
	//Sub biomes
	public static int weightBOPalpsForest = biomeWeightDefault;
	public static int weightBOPcanyonRavine = biomeWeightDefault;
	public static int weightBOPglacier = biomeWeightDefault;
	public static int weightBOPlandOfLakesMarsh = biomeWeightDefault;
	public static int weightBOPmangrove = biomeWeightDefault;
	public static int weightBOPmeadowForest = biomeWeightDefault;
	public static int weightBOPoasis = biomeWeightDefault;
	public static int weightBOPorchard = biomeWeightDefault;
	public static int weightBOPquagmire = biomeWeightDefault;
	public static int weightBOPscrubland = biomeWeightDefault;
	public static int weightBOPseasonalForestClearing = biomeWeightDefault;
	public static int weightBOPsilkglades = biomeWeightDefault;
	public static int weightBOPspruceWoods = biomeWeightDefault;
	public static int weightBOPtropics = biomeWeightDefault;
	public static int weightBOPvolcano = biomeWeightDefault;
	
	//Water biomes
	public static int weightBOPcoralReef = biomeWeightDefault;
	public static int weightBOPkelpForest = biomeWeightDefault;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//Bop
			generateBOPBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			//Overworld biomes
			generateBOPalps = config.getBoolean("generateBOPalps", "Biomes", true, "");
			generateBOParctic = config.getBoolean("generateBOParctic", "Biomes", true, "");
			generateBOPbambooForest = config.getBoolean("generateBOPbambooForest", "Biomes", true, "");
			generateBOPbayou = config.getBoolean("generateBOPbayou", "Biomes", true, "");
			generateBOPbog = config.getBoolean("generateBOPbog", "Biomes", true, "");
			generateBOPborealForest = config.getBoolean("generateBOPborealForest", "Biomes", true, "");
			generateBOPbrushland = config.getBoolean("generateBOPbrushland", "Biomes", true, "");
			generateBOPcanyon = config.getBoolean("generateBOPcanyon", "Biomes", true, "");
			generateBOPchaparral = config.getBoolean("generateBOPchaparral", "Biomes", true, "");
			generateBOPcherryBlossomGrove = config.getBoolean("generateBOPcherryBlossomGrove", "Biomes", true, "");
			generateBOPconiferousForest = config.getBoolean("generateBOPconiferousForest", "Biomes", true, "");
			generateBOPsnowyConiferousForest = config.getBoolean("generateBOPsnowyConiferousForest", "Biomes", true, "");
			generateBOPcrag = config.getBoolean("generateBOPcrag", "Biomes", true, "");
			generateBOPdeadForest = config.getBoolean("generateBOPdeadForest", "Biomes", true, "");
			generateBOPdeadSwamp = config.getBoolean("generateBOPdeadSwamp", "Biomes", true, "");
			generateBOPdeciduousForest = config.getBoolean("generateBOPdeciduousForest", "Biomes", true, "");
			generateBOPdenseForest = config.getBoolean("generateBOPdenseForest", "Biomes", true, "");
			generateBOPeucalyptusForest = config.getBoolean("generateBOPeucalyptusForest", "Biomes", true, "");
			generateBOPfen = config.getBoolean("generateBOPfen", "Biomes", true, "");
			generateBOPflowerField = config.getBoolean("generateBOPflowerField", "Biomes", true, "");
			generateBOPfrostForest = config.getBoolean("generateBOPfrostForest", "Biomes", true, "");
			generateBOPfungiForest = config.getBoolean("generateBOPfungiForest", "Biomes", true, "");
			generateBOPgarden = config.getBoolean("generateBOPgarden", "Biomes", true, "");
			generateBOPgrassland = config.getBoolean("generateBOPgrassland", "Biomes", true, "");
			generateBOPgrove = config.getBoolean("generateBOPgrove", "Biomes", true, "");
			generateBOPheathland = config.getBoolean("generateBOPheathland", "Biomes", true, "");
			generateBOPhighland = config.getBoolean("generateBOPhighland", "Biomes", true, "");
			generateBOPjadeCliffs = config.getBoolean("generateBOPjadeCliffs", "Biomes", true, "");
			generateBOPlandOfLakes = config.getBoolean("generateBOPlandOfLakes", "Biomes", true, "");
			generateBOPlavenderFields = config.getBoolean("generateBOPlavenderFields", "Biomes", true, "");
			generateBOPlushDesert = config.getBoolean("generateBOPlushDesert", "Biomes", true, "");
			generateBOPlushSwamp = config.getBoolean("generateBOPlushSwamp", "Biomes", true, "");
			generateBOPmapleWoods = config.getBoolean("generateBOPmapleWoods", "Biomes", true, "");
			generateBOPmarsh = config.getBoolean("generateBOPmarsh", "Biomes", true, "");
			generateBOPmeadow = config.getBoolean("generateBOPmeadow", "Biomes", true, "");
			generateBOPmoor = config.getBoolean("generateBOPmoor", "Biomes", true, "");
			generateBOPmountain = config.getBoolean("generateBOPmountain", "Biomes", true, "");
			generateBOPmysticGrove = config.getBoolean("generateBOPmysticGrove", "Biomes", true, "");
			generateBOPominousWoods = config.getBoolean("generateBOPominousWoods", "Biomes", true, "");
			generateBOPoriginValley = config.getBoolean("generateBOPoriginValley", "Biomes", true, "");
			generateBOPoutback = config.getBoolean("generateBOPoutback", "Biomes", true, "");
			generateBOPprairie = config.getBoolean("generateBOPprairie", "Biomes", true, "");
			generateBOPrainforest = config.getBoolean("generateBOPrainforest", "Biomes", true, "");
			generateBOPredwoodForest = config.getBoolean("generateBOPredwoodForest", "Biomes", true, "");
			generateBOPsacredSprings = config.getBoolean("generateBOPsacredSprings", "Biomes", true, "");
			generateBOPseasonalForest = config.getBoolean("generateBOPseasonalForest", "Biomes", true, "");
			generateBOPshield = config.getBoolean("generateBOPshield", "Biomes", true, "");
			generateBOPshrubland = config.getBoolean("generateBOPshrubland", "Biomes", true, "");
			generateBOPsludgepit = config.getBoolean("generateBOPsludgepit", "Biomes", true, "");
			generateBOPsteppe = config.getBoolean("generateBOPsteppe", "Biomes", true, "");
			generateBOPtemperateRainforest = config.getBoolean("generateBOPtemperateRainforest", "Biomes", true, "");
			generateBOPthicket = config.getBoolean("generateBOPthicket", "Biomes", true, "");
			generateBOPtropicalRainforest = config.getBoolean("generateBOPtropicalRainforest", "Biomes", true, "");
			generateBOPtundra = config.getBoolean("generateBOPtundra", "Biomes", true, "");
			generateBOPwasteland = config.getBoolean("generateBOPwasteland", "Biomes", true, "");
			generateBOPwetland = config.getBoolean("generateBOPwetland", "Biomes", true, "");
			generateBOPwoodland = config.getBoolean("generateBOPwoodland", "Biomes", true, "");
			generateBOPxericShrubland = config.getBoolean("generateBOPxericShrubland", "Biomes", true, "");
			
			//Sub biomes
			generateBOPalpsForest = config.getBoolean("generateBOPalpsForest", "Biomes", true, "");
			generateBOPcanyonRavine = config.getBoolean("generateBOPcanyonRavine", "Biomes", true, "");
			generateBOPglacier = config.getBoolean("generateBOPglacier", "Biomes", true, "");
			generateBOPlandOfLakesMarsh = config.getBoolean("generateBOPlandOfLakesMarsh", "Biomes", true, "");
			generateBOPmangrove = config.getBoolean("generateBOPmangrove", "Biomes", true, "");
			generateBOPmeadowForest = config.getBoolean("generateBOPmeadowForest", "Biomes", true, "");
			generateBOPoasis = config.getBoolean("generateBOPoasis", "Biomes", true, "");
			generateBOPorchard = config.getBoolean("generateBOPorchard", "Biomes", true, "");
			generateBOPquagmire = config.getBoolean("generateBOPquagmire", "Biomes", true, "");
			generateBOPscrubland = config.getBoolean("generateBOPscrubland", "Biomes", true, "");
			generateBOPseasonalForestClearing = config.getBoolean("generateBOPseasonalForestClearing", "Biomes", true, "");
			generateBOPsilkglades = config.getBoolean("generateBOPsilkglades", "Biomes", true, "");
			generateBOPspruceWoods = config.getBoolean("generateBOPspruceWoods", "Biomes", true, "");
			generateBOPtropics = config.getBoolean("generateBOPtropics", "Biomes", true, "");
			generateBOPvolcano = config.getBoolean("generateBOPvolcano", "Biomes", true, "");
			
			//Water biomes
			generateBOPcoralReef = config.getBoolean("generateBOPcoralReef", "Biomes", true, "");
			generateBOPkelpForest = config.getBoolean("generateBOPkelpForest", "Biomes", true, "");
			
			//Overworld biomes
			weightBOPalps = config.getInt("weightBOPalps", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOParctic = config.getInt("weightBOParctic", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbambooForest = config.getInt("weightBOPbambooForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbayou = config.getInt("weightBOPbayou", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbog = config.getInt("weightBOPbog", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPborealForest = config.getInt("weightBOPborealForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbrushland = config.getInt("weightBOPbrushland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcanyon = config.getInt("weightBOPcanyon", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPchaparral = config.getInt("weightBOPchaparral", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcherryBlossomGrove = config.getInt("weightBOPcherryBlossomGrove", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPconiferousForest = config.getInt("weightBOPconiferousForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsnowyConiferousForest = config.getInt("weightBOPsnowyConiferousForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcrag = config.getInt("weightBOPcrag", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeadForest = config.getInt("weightBOPdeadForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeadSwamp = config.getInt("weightBOPdeadSwamp", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeciduousForest = config.getInt("weightBOPdeciduousForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdenseForest = config.getInt("weightBOPdenseForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPeucalyptusForest = config.getInt("weightBOPeucalyptusForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPfen = config.getInt("weightBOPfen", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPflowerField = config.getInt("weightBOPflowerField", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPfrostForest = config.getInt("weightBOPfrostForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPfungiForest = config.getInt("weightBOPfungiForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPgarden = config.getInt("weightBOPgarden", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPgrassland = config.getInt("weightBOPgrassland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPgrove = config.getInt("weightBOPgrove", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPheathland = config.getInt("weightBOPheathland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPhighland = config.getInt("weightBOPhighland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPjadeCliffs = config.getInt("weightBOPjadeCliffs", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlandOfLakes = config.getInt("weightBOPlandOfLakes", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlavenderFields = config.getInt("weightBOPlavenderFields", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlushDesert = config.getInt("weightBOPlushDesert", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlushSwamp = config.getInt("weightBOPlushSwamp", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmapleWoods = config.getInt("weightBOPmapleWoods", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmarsh = config.getInt("weightBOPmarsh", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmeadow = config.getInt("weightBOPmeadow", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmoor = config.getInt("weightBOPmoor", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmountain = config.getInt("weightBOPmountain", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmysticGrove = config.getInt("weightBOPmysticGrove", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPominousWoods = config.getInt("weightBOPominousWoods", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPoriginValley = config.getInt("weightBOPoriginValley", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPoutback = config.getInt("weightBOPoutback", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPprairie = config.getInt("weightBOPprairie", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPrainforest = config.getInt("weightBOPrainforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPredwoodForest = config.getInt("weightBOPredwoodForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsacredSprings = config.getInt("weightBOPsacredSprings", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPseasonalForest = config.getInt("weightBOPseasonalForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPshield = config.getInt("weightBOPshield", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPshrubland = config.getInt("weightBOPshrubland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsludgepit = config.getInt("weightBOPsludgepit", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsteppe = config.getInt("weightBOPsteppe", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtemperateRainforest = config.getInt("weightBOPtemperateRainforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPthicket = config.getInt("weightBOPthicket", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtropicalRainforest = config.getInt("weightBOPtropicalRainforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtundra = config.getInt("weightBOPtundra", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPwasteland = config.getInt("weightBOPwasteland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPwetland = config.getInt("weightBOPwetland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPwoodland = config.getInt("weightBOPwoodland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPxericShrubland = config.getInt("weightBOPxericShrubland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			
			//Sub biomes
			weightBOPalpsForest = config.getInt("weightBOPalpsForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcanyonRavine = config.getInt("weightBOPcanyonRavine", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPglacier = config.getInt("weightBOPglacier", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlandOfLakesMarsh = config.getInt("weightBOPlandOfLakesMarsh", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmangrove = config.getInt("weightBOPmangrove", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmeadowForest = config.getInt("weightBOPmeadowForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPoasis = config.getInt("weightBOPoasis", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPorchard = config.getInt("weightBOPorchard", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPquagmire = config.getInt("weightBOPquagmire", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPscrubland = config.getInt("weightBOPscrubland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPseasonalForestClearing = config.getInt("weightBOPseasonalForestClearing", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsilkglades = config.getInt("weightBOPsilkglades", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPspruceWoods = config.getInt("weightBOPspruceWoods", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtropics = config.getInt("weightBOPtropics", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPvolcano = config.getInt("weightBOPvolcano", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			
			//Water biomes
			weightBOPcoralReef = config.getInt("weightBOPcoralReef", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPkelpForest = config.getInt("weightBOPkelpForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
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
}
