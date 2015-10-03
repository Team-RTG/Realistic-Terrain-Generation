package rtg.config;

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
	public static boolean generateBOPdryRiver = true;
	public static boolean generateBOPlushRiver = true;
	
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
	public static int weightBOPdryRiver = biomeWeightDefault;
	public static int weightBOPlushRiver = biomeWeightDefault;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//Bop
			generateBOPBiomes = config.getBoolean("Generate BOP Biomes", "BOP Biomes", true, "");
			
			//Overworld biomes
			generateBOPalps = config.getBoolean("generateBOPalps", "BOP Biomes", true, "");
			generateBOParctic = config.getBoolean("generateBOParctic", "BOP Biomes", true, "");
			generateBOPbambooForest = config.getBoolean("generateBOPbambooForest", "BOP Biomes", true, "");
			generateBOPbayou = config.getBoolean("generateBOPbayou", "BOP Biomes", true, "");
			generateBOPbog = config.getBoolean("generateBOPbog", "BOP Biomes", true, "");
			generateBOPborealForest = config.getBoolean("generateBOPborealForest", "BOP Biomes", true, "");
			generateBOPbrushland = config.getBoolean("generateBOPbrushland", "BOP Biomes", true, "");
			generateBOPcanyon = config.getBoolean("generateBOPcanyon", "BOP Biomes", true, "");
			generateBOPchaparral = config.getBoolean("generateBOPchaparral", "BOP Biomes", true, "");
			generateBOPcherryBlossomGrove = config.getBoolean("generateBOPcherryBlossomGrove", "BOP Biomes", true, "");
			generateBOPconiferousForest = config.getBoolean("generateBOPconiferousForest", "BOP Biomes", true, "");
			generateBOPsnowyConiferousForest = config.getBoolean("generateBOPsnowyConiferousForest", "BOP Biomes", true, "");
			generateBOPcrag = config.getBoolean("generateBOPcrag", "BOP Biomes", true, "");
			generateBOPdeadForest = config.getBoolean("generateBOPdeadForest", "BOP Biomes", true, "");
			generateBOPdeadSwamp = config.getBoolean("generateBOPdeadSwamp", "BOP Biomes", true, "");
			generateBOPdeciduousForest = config.getBoolean("generateBOPdeciduousForest", "BOP Biomes", true, "");
			generateBOPdenseForest = config.getBoolean("generateBOPdenseForest", "BOP Biomes", true, "");
			generateBOPeucalyptusForest = config.getBoolean("generateBOPeucalyptusForest", "BOP Biomes", true, "");
			generateBOPfen = config.getBoolean("generateBOPfen", "BOP Biomes", true, "");
			generateBOPflowerField = config.getBoolean("generateBOPflowerField", "BOP Biomes", true, "");
			generateBOPfrostForest = config.getBoolean("generateBOPfrostForest", "BOP Biomes", true, "");
			generateBOPfungiForest = config.getBoolean("generateBOPfungiForest", "BOP Biomes", true, "");
			generateBOPgarden = config.getBoolean("generateBOPgarden", "BOP Biomes", true, "");
			generateBOPgrassland = config.getBoolean("generateBOPgrassland", "BOP Biomes", true, "");
			generateBOPgrove = config.getBoolean("generateBOPgrove", "BOP Biomes", true, "");
			generateBOPheathland = config.getBoolean("generateBOPheathland", "BOP Biomes", true, "");
			generateBOPhighland = config.getBoolean("generateBOPhighland", "BOP Biomes", true, "");
			generateBOPjadeCliffs = config.getBoolean("generateBOPjadeCliffs", "BOP Biomes", true, "");
			generateBOPlandOfLakes = config.getBoolean("generateBOPlandOfLakes", "BOP Biomes", true, "");
			generateBOPlavenderFields = config.getBoolean("generateBOPlavenderFields", "BOP Biomes", true, "");
			generateBOPlushDesert = config.getBoolean("generateBOPlushDesert", "BOP Biomes", true, "");
			generateBOPlushSwamp = config.getBoolean("generateBOPlushSwamp", "BOP Biomes", true, "");
			generateBOPmapleWoods = config.getBoolean("generateBOPmapleWoods", "BOP Biomes", true, "");
			generateBOPmarsh = config.getBoolean("generateBOPmarsh", "BOP Biomes", true, "");
			generateBOPmeadow = config.getBoolean("generateBOPmeadow", "BOP Biomes", true, "");
			generateBOPmoor = config.getBoolean("generateBOPmoor", "BOP Biomes", true, "");
			generateBOPmountain = config.getBoolean("generateBOPmountain", "BOP Biomes", true, "");
			generateBOPmysticGrove = config.getBoolean("generateBOPmysticGrove", "BOP Biomes", true, "");
			generateBOPominousWoods = config.getBoolean("generateBOPominousWoods", "BOP Biomes", true, "");
			generateBOPoriginValley = config.getBoolean("generateBOPoriginValley", "BOP Biomes", true, "");
			generateBOPoutback = config.getBoolean("generateBOPoutback", "BOP Biomes", true, "");
			generateBOPprairie = config.getBoolean("generateBOPprairie", "BOP Biomes", true, "");
			generateBOPrainforest = config.getBoolean("generateBOPrainforest", "BOP Biomes", true, "");
			generateBOPredwoodForest = config.getBoolean("generateBOPredwoodForest", "BOP Biomes", true, "");
			generateBOPsacredSprings = config.getBoolean("generateBOPsacredSprings", "BOP Biomes", true, "");
			generateBOPseasonalForest = config.getBoolean("generateBOPseasonalForest", "BOP Biomes", true, "");
			generateBOPshield = config.getBoolean("generateBOPshield", "BOP Biomes", true, "");
			generateBOPshrubland = config.getBoolean("generateBOPshrubland", "BOP Biomes", true, "");
			generateBOPsludgepit = config.getBoolean("generateBOPsludgepit", "BOP Biomes", true, "");
			generateBOPsteppe = config.getBoolean("generateBOPsteppe", "BOP Biomes", true, "");
			generateBOPtemperateRainforest = config.getBoolean("generateBOPtemperateRainforest", "BOP Biomes", true, "");
			generateBOPthicket = config.getBoolean("generateBOPthicket", "BOP Biomes", true, "");
			generateBOPtropicalRainforest = config.getBoolean("generateBOPtropicalRainforest", "BOP Biomes", true, "");
			generateBOPtundra = config.getBoolean("generateBOPtundra", "BOP Biomes", true, "");
			generateBOPwasteland = config.getBoolean("generateBOPwasteland", "BOP Biomes", true, "");
			generateBOPwetland = config.getBoolean("generateBOPwetland", "BOP Biomes", true, "");
			generateBOPwoodland = config.getBoolean("generateBOPwoodland", "BOP Biomes", true, "");
			generateBOPxericShrubland = config.getBoolean("generateBOPxericShrubland", "BOP Biomes", true, "");
			
			//Sub biomes
			generateBOPalpsForest = config.getBoolean("generateBOPalpsForest", "BOP Biomes", true, "");
			generateBOPcanyonRavine = config.getBoolean("generateBOPcanyonRavine", "BOP Biomes", true, "");
			generateBOPglacier = config.getBoolean("generateBOPglacier", "BOP Biomes", true, "");
			generateBOPlandOfLakesMarsh = config.getBoolean("generateBOPlandOfLakesMarsh", "BOP Biomes", true, "");
			generateBOPmangrove = config.getBoolean("generateBOPmangrove", "BOP Biomes", true, "");
			generateBOPmeadowForest = config.getBoolean("generateBOPmeadowForest", "BOP Biomes", true, "");
			generateBOPoasis = config.getBoolean("generateBOPoasis", "BOP Biomes", true, "");
			generateBOPorchard = config.getBoolean("generateBOPorchard", "BOP Biomes", true, "");
			generateBOPquagmire = config.getBoolean("generateBOPquagmire", "BOP Biomes", true, "");
			generateBOPscrubland = config.getBoolean("generateBOPscrubland", "BOP Biomes", true, "");
			generateBOPseasonalForestClearing = config.getBoolean("generateBOPseasonalForestClearing", "BOP Biomes", true, "");
			generateBOPsilkglades = config.getBoolean("generateBOPsilkglades", "BOP Biomes", true, "");
			generateBOPspruceWoods = config.getBoolean("generateBOPspruceWoods", "BOP Biomes", true, "");
			generateBOPtropics = config.getBoolean("generateBOPtropics", "BOP Biomes", true, "");
			generateBOPvolcano = config.getBoolean("generateBOPvolcano", "BOP Biomes", true, "");
			
			//Water biomes
			generateBOPcoralReef = config.getBoolean("generateBOPcoralReef", "BOP Biomes", true, "");
			generateBOPkelpForest = config.getBoolean("generateBOPkelpForest", "BOP Biomes", true, "");
			generateBOPdryRiver = config.getBoolean("generateBOPdryRiver", "BOP Biomes", true, "");
			generateBOPlushRiver = config.getBoolean("generateBOPlushRiver", "BOP Biomes", true, "");
			
			//Overworld biomes
			weightBOPalps = config.getInt("weightBOPalps", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOParctic = config.getInt("weightBOParctic", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbambooForest = config.getInt("weightBOPbambooForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbayou = config.getInt("weightBOPbayou", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbog = config.getInt("weightBOPbog", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPborealForest = config.getInt("weightBOPborealForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPbrushland = config.getInt("weightBOPbrushland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcanyon = config.getInt("weightBOPcanyon", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPchaparral = config.getInt("weightBOPchaparral", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcherryBlossomGrove = config.getInt("weightBOPcherryBlossomGrove", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPconiferousForest = config.getInt("weightBOPconiferousForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsnowyConiferousForest = config.getInt("weightBOPsnowyConiferousForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcrag = config.getInt("weightBOPcrag", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeadForest = config.getInt("weightBOPdeadForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeadSwamp = config.getInt("weightBOPdeadSwamp", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeciduousForest = config.getInt("weightBOPdeciduousForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdenseForest = config.getInt("weightBOPdenseForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPeucalyptusForest = config.getInt("weightBOPeucalyptusForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPfen = config.getInt("weightBOPfen", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPflowerField = config.getInt("weightBOPflowerField", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPfrostForest = config.getInt("weightBOPfrostForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPfungiForest = config.getInt("weightBOPfungiForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPgarden = config.getInt("weightBOPgarden", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPgrassland = config.getInt("weightBOPgrassland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPgrove = config.getInt("weightBOPgrove", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPheathland = config.getInt("weightBOPheathland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPhighland = config.getInt("weightBOPhighland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPjadeCliffs = config.getInt("weightBOPjadeCliffs", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlandOfLakes = config.getInt("weightBOPlandOfLakes", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlavenderFields = config.getInt("weightBOPlavenderFields", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlushDesert = config.getInt("weightBOPlushDesert", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlushSwamp = config.getInt("weightBOPlushSwamp", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmapleWoods = config.getInt("weightBOPmapleWoods", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmarsh = config.getInt("weightBOPmarsh", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmeadow = config.getInt("weightBOPmeadow", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmoor = config.getInt("weightBOPmoor", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmountain = config.getInt("weightBOPmountain", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmysticGrove = config.getInt("weightBOPmysticGrove", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPominousWoods = config.getInt("weightBOPominousWoods", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPoriginValley = config.getInt("weightBOPoriginValley", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPoutback = config.getInt("weightBOPoutback", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPprairie = config.getInt("weightBOPprairie", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPrainforest = config.getInt("weightBOPrainforest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPredwoodForest = config.getInt("weightBOPredwoodForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsacredSprings = config.getInt("weightBOPsacredSprings", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPseasonalForest = config.getInt("weightBOPseasonalForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPshield = config.getInt("weightBOPshield", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPshrubland = config.getInt("weightBOPshrubland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsludgepit = config.getInt("weightBOPsludgepit", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsteppe = config.getInt("weightBOPsteppe", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtemperateRainforest = config.getInt("weightBOPtemperateRainforest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPthicket = config.getInt("weightBOPthicket", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtropicalRainforest = config.getInt("weightBOPtropicalRainforest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtundra = config.getInt("weightBOPtundra", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPwasteland = config.getInt("weightBOPwasteland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPwetland = config.getInt("weightBOPwetland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPwoodland = config.getInt("weightBOPwoodland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPxericShrubland = config.getInt("weightBOPxericShrubland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			
			//Sub biomes
			weightBOPalpsForest = config.getInt("weightBOPalpsForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPcanyonRavine = config.getInt("weightBOPcanyonRavine", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPglacier = config.getInt("weightBOPglacier", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlandOfLakesMarsh = config.getInt("weightBOPlandOfLakesMarsh", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmangrove = config.getInt("weightBOPmangrove", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPmeadowForest = config.getInt("weightBOPmeadowForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPoasis = config.getInt("weightBOPoasis", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPorchard = config.getInt("weightBOPorchard", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPquagmire = config.getInt("weightBOPquagmire", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPscrubland = config.getInt("weightBOPscrubland", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPseasonalForestClearing = config.getInt("weightBOPseasonalForestClearing", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPsilkglades = config.getInt("weightBOPsilkglades", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPspruceWoods = config.getInt("weightBOPspruceWoods", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPtropics = config.getInt("weightBOPtropics", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPvolcano = config.getInt("weightBOPvolcano", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			
			//Water biomes
			weightBOPcoralReef = config.getInt("weightBOPcoralReef", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPkelpForest = config.getInt("weightBOPkelpForest", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPdryRiver = config.getInt("weightBOPdryRiver", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightBOPlushRiver = config.getInt("weightBOPlushRiver", "BOP Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
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
