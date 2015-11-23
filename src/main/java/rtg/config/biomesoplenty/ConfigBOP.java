package rtg.config.biomesoplenty;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;

public class ConfigBOP 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
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
	public static boolean generateBOPvolcano = false;
	
	//Water biomes
	public static boolean generateBOPcoralReef = false;
	public static boolean generateBOPkelpForest = false;
	
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
	public static int weightBOPvolcano = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	
	//Water biomes
	public static int weightBOPcoralReef = biomeWeightDefault;
	public static int weightBOPkelpForest = biomeWeightDefault;
	
    public static boolean villageBOPalps = true;
    public static boolean villageBOParctic = true;
    public static boolean villageBOPbambooForest = true;
    public static boolean villageBOPbayou = true;
    public static boolean villageBOPbog = true;
    public static boolean villageBOPborealForest = true;
    public static boolean villageBOPbrushland = true;
    public static boolean villageBOPcanyon = true;
    public static boolean villageBOPchaparral = true;
    public static boolean villageBOPcherryBlossomGrove = true;
    public static boolean villageBOPconiferousForest = true;
    public static boolean villageBOPsnowyConiferousForest = true;
    public static boolean villageBOPcrag = true;
    public static boolean villageBOPdeadForest = true;
    public static boolean villageBOPdeadSwamp = true;
    public static boolean villageBOPdeciduousForest = true;
    public static boolean villageBOPdenseForest = true;
    public static boolean villageBOPeucalyptusForest = true;
    public static boolean villageBOPfen = true;
    public static boolean villageBOPflowerField = true;
    public static boolean villageBOPfrostForest = true;
    public static boolean villageBOPfungiForest = true;
    public static boolean villageBOPgarden = true;
    public static boolean villageBOPgrassland = true;
    public static boolean villageBOPgrove = true;
    public static boolean villageBOPheathland = true;
    public static boolean villageBOPhighland = true;
    public static boolean villageBOPjadeCliffs = true;
    public static boolean villageBOPlandOfLakes = true;
    public static boolean villageBOPlavenderFields = true;
    public static boolean villageBOPlushDesert = true;
    public static boolean villageBOPlushSwamp = true;
    public static boolean villageBOPmapleWoods = true;
    public static boolean villageBOPmarsh = true;
    public static boolean villageBOPmeadow = true;
    public static boolean villageBOPmoor = true;
    public static boolean villageBOPmountain = true;
    public static boolean villageBOPmysticGrove = true;
    public static boolean villageBOPominousWoods = true;
    public static boolean villageBOPoriginValley = true;
    public static boolean villageBOPoutback = true;
    public static boolean villageBOPprairie = true;
    public static boolean villageBOPrainforest = true;
    public static boolean villageBOPredwoodForest = true;
    public static boolean villageBOPsacredSprings = true;
    public static boolean villageBOPseasonalForest = true;
    public static boolean villageBOPshield = true;
    public static boolean villageBOPshrubland = true;
    public static boolean villageBOPsludgepit = true;
    public static boolean villageBOPsteppe = true;
    public static boolean villageBOPtemperateRainforest = true;
    public static boolean villageBOPthicket = true;
    public static boolean villageBOPtropicalRainforest = true;
    public static boolean villageBOPtundra = true;
    public static boolean villageBOPwasteland = true;
    public static boolean villageBOPwetland = true;
    public static boolean villageBOPwoodland = true;
    public static boolean villageBOPxericShrubland = true;
        
    //Sub biomes
    public static boolean villageBOPalpsForest = true;
    public static boolean villageBOPcanyonRavine = true;
    public static boolean villageBOPglacier = true;
    public static boolean villageBOPlandOfLakesMarsh = true;
    public static boolean villageBOPmangrove = true;
    public static boolean villageBOPmeadowForest = true;
    public static boolean villageBOPoasis = true;
    public static boolean villageBOPorchard = true;
    public static boolean villageBOPquagmire = true;
    public static boolean villageBOPscrubland = true;
    public static boolean villageBOPseasonalForestClearing = true;
    public static boolean villageBOPsilkglades = true;
    public static boolean villageBOPspruceWoods = true;
    public static boolean villageBOPtropics = true;
    public static boolean villageBOPvolcano = false;
    
    //Water biomes
    public static boolean villageBOPcoralReef = false;
    public static boolean villageBOPkelpForest = false;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//Bop
			generateBOPBiomes = config.getBoolean("Generate Biomes", "Biomes", generateBOPBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			//Overworld biomes
			generateBOPalps = config.getBoolean("generateBOPalps", "Biomes", generateBOPalps, "");
			generateBOParctic = config.getBoolean("generateBOParctic", "Biomes", generateBOParctic, "");
			generateBOPbambooForest = config.getBoolean("generateBOPbambooForest", "Biomes", generateBOPbambooForest, "");
			generateBOPbayou = config.getBoolean("generateBOPbayou", "Biomes", generateBOPbayou, "");
			generateBOPbog = config.getBoolean("generateBOPbog", "Biomes", generateBOPbog, "");
			generateBOPborealForest = config.getBoolean("generateBOPborealForest", "Biomes", generateBOPborealForest, "");
			generateBOPbrushland = config.getBoolean("generateBOPbrushland", "Biomes", generateBOPbrushland, "");
			generateBOPcanyon = config.getBoolean("generateBOPcanyon", "Biomes", generateBOPcanyon, "");
			generateBOPchaparral = config.getBoolean("generateBOPchaparral", "Biomes", generateBOPchaparral, "");
			generateBOPcherryBlossomGrove = config.getBoolean("generateBOPcherryBlossomGrove", "Biomes", generateBOPcherryBlossomGrove, "");
			generateBOPconiferousForest = config.getBoolean("generateBOPconiferousForest", "Biomes", generateBOPconiferousForest, "");
			generateBOPsnowyConiferousForest = config.getBoolean("generateBOPsnowyConiferousForest", "Biomes", generateBOPsnowyConiferousForest, "");
			generateBOPcrag = config.getBoolean("generateBOPcrag", "Biomes", generateBOPcrag, "");
			generateBOPdeadForest = config.getBoolean("generateBOPdeadForest", "Biomes", generateBOPdeadForest, "");
			generateBOPdeadSwamp = config.getBoolean("generateBOPdeadSwamp", "Biomes", generateBOPdeadSwamp, "");
			generateBOPdeciduousForest = config.getBoolean("generateBOPdeciduousForest", "Biomes", generateBOPdeciduousForest, "");
			generateBOPdenseForest = config.getBoolean("generateBOPdenseForest", "Biomes", generateBOPdenseForest, "");
			generateBOPeucalyptusForest = config.getBoolean("generateBOPeucalyptusForest", "Biomes", generateBOPeucalyptusForest, "");
			generateBOPfen = config.getBoolean("generateBOPfen", "Biomes", generateBOPfen, "");
			generateBOPflowerField = config.getBoolean("generateBOPflowerField", "Biomes", generateBOPflowerField, "");
			generateBOPfrostForest = config.getBoolean("generateBOPfrostForest", "Biomes", generateBOPfrostForest, "");
			generateBOPfungiForest = config.getBoolean("generateBOPfungiForest", "Biomes", generateBOPfungiForest, "");
			generateBOPgarden = config.getBoolean("generateBOPgarden", "Biomes", generateBOPgarden, "");
			generateBOPgrassland = config.getBoolean("generateBOPgrassland", "Biomes", generateBOPgrassland, "");
			generateBOPgrove = config.getBoolean("generateBOPgrove", "Biomes", generateBOPgrove, "");
			generateBOPheathland = config.getBoolean("generateBOPheathland", "Biomes", generateBOPheathland, "");
			generateBOPhighland = config.getBoolean("generateBOPhighland", "Biomes", generateBOPhighland, "");
			generateBOPjadeCliffs = config.getBoolean("generateBOPjadeCliffs", "Biomes", generateBOPjadeCliffs, "");
			generateBOPlandOfLakes = config.getBoolean("generateBOPlandOfLakes", "Biomes", generateBOPlandOfLakes, "");
			generateBOPlavenderFields = config.getBoolean("generateBOPlavenderFields", "Biomes", generateBOPlavenderFields, "");
			generateBOPlushDesert = config.getBoolean("generateBOPlushDesert", "Biomes", generateBOPlushDesert, "");
			generateBOPlushSwamp = config.getBoolean("generateBOPlushSwamp", "Biomes", generateBOPlushSwamp, "");
			generateBOPmapleWoods = config.getBoolean("generateBOPmapleWoods", "Biomes", generateBOPmapleWoods, "");
			generateBOPmarsh = config.getBoolean("generateBOPmarsh", "Biomes", generateBOPmarsh, "");
			generateBOPmeadow = config.getBoolean("generateBOPmeadow", "Biomes", generateBOPmeadow, "");
			generateBOPmoor = config.getBoolean("generateBOPmoor", "Biomes", generateBOPmoor, "");
			generateBOPmountain = config.getBoolean("generateBOPmountain", "Biomes", generateBOPmountain, "");
			generateBOPmysticGrove = config.getBoolean("generateBOPmysticGrove", "Biomes", generateBOPmysticGrove, "");
			generateBOPominousWoods = config.getBoolean("generateBOPominousWoods", "Biomes", generateBOPominousWoods, "");
			generateBOPoriginValley = config.getBoolean("generateBOPoriginValley", "Biomes", generateBOPoriginValley, "");
			generateBOPoutback = config.getBoolean("generateBOPoutback", "Biomes", generateBOPoutback, "");
			generateBOPprairie = config.getBoolean("generateBOPprairie", "Biomes", generateBOPprairie, "");
			generateBOPrainforest = config.getBoolean("generateBOPrainforest", "Biomes", generateBOPrainforest, "");
			generateBOPredwoodForest = config.getBoolean("generateBOPredwoodForest", "Biomes", generateBOPredwoodForest, "");
			generateBOPsacredSprings = config.getBoolean("generateBOPsacredSprings", "Biomes", generateBOPsacredSprings, "");
			generateBOPseasonalForest = config.getBoolean("generateBOPseasonalForest", "Biomes", generateBOPseasonalForest, "");
			generateBOPshield = config.getBoolean("generateBOPshield", "Biomes", generateBOPshield, "");
			generateBOPshrubland = config.getBoolean("generateBOPshrubland", "Biomes", generateBOPshrubland, "");
			generateBOPsludgepit = config.getBoolean("generateBOPsludgepit", "Biomes", generateBOPsludgepit, "");
			generateBOPsteppe = config.getBoolean("generateBOPsteppe", "Biomes", generateBOPsteppe, "");
			generateBOPtemperateRainforest = config.getBoolean("generateBOPtemperateRainforest", "Biomes", generateBOPtemperateRainforest, "");
			generateBOPthicket = config.getBoolean("generateBOPthicket", "Biomes", generateBOPthicket, "");
			generateBOPtropicalRainforest = config.getBoolean("generateBOPtropicalRainforest", "Biomes", generateBOPtropicalRainforest, "");
			generateBOPtundra = config.getBoolean("generateBOPtundra", "Biomes", generateBOPtundra, "");
			generateBOPwasteland = config.getBoolean("generateBOPwasteland", "Biomes", generateBOPwasteland, "");
			generateBOPwetland = config.getBoolean("generateBOPwetland", "Biomes", generateBOPwetland, "");
			generateBOPwoodland = config.getBoolean("generateBOPwoodland", "Biomes", generateBOPwoodland, "");
			generateBOPxericShrubland = config.getBoolean("generateBOPxericShrubland", "Biomes", generateBOPxericShrubland, "");
			
			//Sub biomes
			generateBOPalpsForest = config.getBoolean("generateBOPalpsForest", "Biomes", generateBOPalpsForest, "");
			generateBOPcanyonRavine = config.getBoolean("generateBOPcanyonRavine", "Biomes", generateBOPcanyonRavine, "");
			generateBOPglacier = config.getBoolean("generateBOPglacier", "Biomes", generateBOPglacier, "");
			generateBOPlandOfLakesMarsh = config.getBoolean("generateBOPlandOfLakesMarsh", "Biomes", generateBOPlandOfLakesMarsh, "");
			generateBOPmangrove = config.getBoolean("generateBOPmangrove", "Biomes", generateBOPmangrove, "");
			generateBOPmeadowForest = config.getBoolean("generateBOPmeadowForest", "Biomes", generateBOPmeadowForest, "");
			generateBOPoasis = config.getBoolean("generateBOPoasis", "Biomes", generateBOPoasis, "");
			generateBOPorchard = config.getBoolean("generateBOPorchard", "Biomes", generateBOPorchard, "");
			generateBOPquagmire = config.getBoolean("generateBOPquagmire", "Biomes", generateBOPquagmire, "");
			generateBOPscrubland = config.getBoolean("generateBOPscrubland", "Biomes", generateBOPscrubland, "");
			generateBOPseasonalForestClearing = config.getBoolean("generateBOPseasonalForestClearing", "Biomes", generateBOPseasonalForestClearing, "");
			generateBOPsilkglades = config.getBoolean("generateBOPsilkglades", "Biomes", generateBOPsilkglades, "");
			generateBOPspruceWoods = config.getBoolean("generateBOPspruceWoods", "Biomes", generateBOPspruceWoods, "");
			generateBOPtropics = config.getBoolean("generateBOPtropics", "Biomes", generateBOPtropics, "");
			generateBOPvolcano = config.getBoolean("generateBOPvolcano", "Biomes", generateBOPvolcano, "");
			
			//Water biomes
			generateBOPcoralReef = config.getBoolean("generateBOPcoralReef", "Biomes", generateBOPcoralReef, "");
			generateBOPkelpForest = config.getBoolean("generateBOPkelpForest", "Biomes", generateBOPkelpForest, "");
			
			//Overworld biomes
			weightBOPalps = config.getInt("weightBOPalps", "Weights", weightBOPalps, biomeWeightMin, biomeWeightMax, "");
			weightBOParctic = config.getInt("weightBOParctic", "Weights", weightBOParctic, biomeWeightMin, biomeWeightMax, "");
			weightBOPbambooForest = config.getInt("weightBOPbambooForest", "Weights", weightBOPbambooForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPbayou = config.getInt("weightBOPbayou", "Weights", weightBOPbayou, biomeWeightMin, biomeWeightMax, "");
			weightBOPbog = config.getInt("weightBOPbog", "Weights", weightBOPbog, biomeWeightMin, biomeWeightMax, "");
			weightBOPborealForest = config.getInt("weightBOPborealForest", "Weights", weightBOPborealForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPbrushland = config.getInt("weightBOPbrushland", "Weights", weightBOPbrushland, biomeWeightMin, biomeWeightMax, "");
			weightBOPcanyon = config.getInt("weightBOPcanyon", "Weights", weightBOPcanyon, biomeWeightMin, biomeWeightMax, "");
			weightBOPchaparral = config.getInt("weightBOPchaparral", "Weights", weightBOPchaparral, biomeWeightMin, biomeWeightMax, "");
			weightBOPcherryBlossomGrove = config.getInt("weightBOPcherryBlossomGrove", "Weights", weightBOPcherryBlossomGrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPconiferousForest = config.getInt("weightBOPconiferousForest", "Weights", weightBOPconiferousForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPsnowyConiferousForest = config.getInt("weightBOPsnowyConiferousForest", "Weights", weightBOPsnowyConiferousForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPcrag = config.getInt("weightBOPcrag", "Weights", weightBOPcrag, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeadForest = config.getInt("weightBOPdeadForest", "Weights", weightBOPdeadForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeadSwamp = config.getInt("weightBOPdeadSwamp", "Weights", weightBOPdeadSwamp, biomeWeightMin, biomeWeightMax, "");
			weightBOPdeciduousForest = config.getInt("weightBOPdeciduousForest", "Weights", weightBOPdeciduousForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPdenseForest = config.getInt("weightBOPdenseForest", "Weights", weightBOPdenseForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPeucalyptusForest = config.getInt("weightBOPeucalyptusForest", "Weights", weightBOPeucalyptusForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPfen = config.getInt("weightBOPfen", "Weights", weightBOPfen, biomeWeightMin, biomeWeightMax, "");
			weightBOPflowerField = config.getInt("weightBOPflowerField", "Weights", weightBOPflowerField, biomeWeightMin, biomeWeightMax, "");
			weightBOPfrostForest = config.getInt("weightBOPfrostForest", "Weights", weightBOPfrostForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPfungiForest = config.getInt("weightBOPfungiForest", "Weights", weightBOPfungiForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPgarden = config.getInt("weightBOPgarden", "Weights", weightBOPgarden, biomeWeightMin, biomeWeightMax, "");
			weightBOPgrassland = config.getInt("weightBOPgrassland", "Weights", weightBOPgrassland, biomeWeightMin, biomeWeightMax, "");
			weightBOPgrove = config.getInt("weightBOPgrove", "Weights", weightBOPgrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPheathland = config.getInt("weightBOPheathland", "Weights", weightBOPheathland, biomeWeightMin, biomeWeightMax, "");
			weightBOPhighland = config.getInt("weightBOPhighland", "Weights", weightBOPhighland, biomeWeightMin, biomeWeightMax, "");
			weightBOPjadeCliffs = config.getInt("weightBOPjadeCliffs", "Weights", weightBOPjadeCliffs, biomeWeightMin, biomeWeightMax, "");
			weightBOPlandOfLakes = config.getInt("weightBOPlandOfLakes", "Weights", weightBOPlandOfLakes, biomeWeightMin, biomeWeightMax, "");
			weightBOPlavenderFields = config.getInt("weightBOPlavenderFields", "Weights", weightBOPlavenderFields, biomeWeightMin, biomeWeightMax, "");
			weightBOPlushDesert = config.getInt("weightBOPlushDesert", "Weights", weightBOPlushDesert, biomeWeightMin, biomeWeightMax, "");
			weightBOPlushSwamp = config.getInt("weightBOPlushSwamp", "Weights", weightBOPlushSwamp, biomeWeightMin, biomeWeightMax, "");
			weightBOPmapleWoods = config.getInt("weightBOPmapleWoods", "Weights", weightBOPmapleWoods, biomeWeightMin, biomeWeightMax, "");
			weightBOPmarsh = config.getInt("weightBOPmarsh", "Weights", weightBOPmarsh, biomeWeightMin, biomeWeightMax, "");
			weightBOPmeadow = config.getInt("weightBOPmeadow", "Weights", weightBOPmeadow, biomeWeightMin, biomeWeightMax, "");
			weightBOPmoor = config.getInt("weightBOPmoor", "Weights", weightBOPmoor, biomeWeightMin, biomeWeightMax, "");
			weightBOPmountain = config.getInt("weightBOPmountain", "Weights", weightBOPmountain, biomeWeightMin, biomeWeightMax, "");
			weightBOPmysticGrove = config.getInt("weightBOPmysticGrove", "Weights", weightBOPmysticGrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPominousWoods = config.getInt("weightBOPominousWoods", "Weights", weightBOPominousWoods, biomeWeightMin, biomeWeightMax, "");
			weightBOPoriginValley = config.getInt("weightBOPoriginValley", "Weights", weightBOPoriginValley, biomeWeightMin, biomeWeightMax, "");
			weightBOPoutback = config.getInt("weightBOPoutback", "Weights", weightBOPoutback, biomeWeightMin, biomeWeightMax, "");
			weightBOPprairie = config.getInt("weightBOPprairie", "Weights", weightBOPprairie, biomeWeightMin, biomeWeightMax, "");
			weightBOPrainforest = config.getInt("weightBOPrainforest", "Weights", weightBOPrainforest, biomeWeightMin, biomeWeightMax, "");
			weightBOPredwoodForest = config.getInt("weightBOPredwoodForest", "Weights", weightBOPredwoodForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPsacredSprings = config.getInt("weightBOPsacredSprings", "Weights", weightBOPsacredSprings, biomeWeightMin, biomeWeightMax, "");
			weightBOPseasonalForest = config.getInt("weightBOPseasonalForest", "Weights", weightBOPseasonalForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPshield = config.getInt("weightBOPshield", "Weights", weightBOPshield, biomeWeightMin, biomeWeightMax, "");
			weightBOPshrubland = config.getInt("weightBOPshrubland", "Weights", weightBOPshrubland, biomeWeightMin, biomeWeightMax, "");
			weightBOPsludgepit = config.getInt("weightBOPsludgepit", "Weights", weightBOPsludgepit, biomeWeightMin, biomeWeightMax, "");
			weightBOPsteppe = config.getInt("weightBOPsteppe", "Weights", weightBOPsteppe, biomeWeightMin, biomeWeightMax, "");
			weightBOPtemperateRainforest = config.getInt("weightBOPtemperateRainforest", "Weights", weightBOPtemperateRainforest, biomeWeightMin, biomeWeightMax, "");
			weightBOPthicket = config.getInt("weightBOPthicket", "Weights", weightBOPthicket, biomeWeightMin, biomeWeightMax, "");
			weightBOPtropicalRainforest = config.getInt("weightBOPtropicalRainforest", "Weights", weightBOPtropicalRainforest, biomeWeightMin, biomeWeightMax, "");
			weightBOPtundra = config.getInt("weightBOPtundra", "Weights", weightBOPtundra, biomeWeightMin, biomeWeightMax, "");
			weightBOPwasteland = config.getInt("weightBOPwasteland", "Weights", weightBOPwasteland, biomeWeightMin, biomeWeightMax, "");
			weightBOPwetland = config.getInt("weightBOPwetland", "Weights", weightBOPwetland, biomeWeightMin, biomeWeightMax, "");
			weightBOPwoodland = config.getInt("weightBOPwoodland", "Weights", weightBOPwoodland, biomeWeightMin, biomeWeightMax, "");
			weightBOPxericShrubland = config.getInt("weightBOPxericShrubland", "Weights", weightBOPxericShrubland, biomeWeightMin, biomeWeightMax, "");
			
			//Sub biomes
			weightBOPalpsForest = config.getInt("weightBOPalpsForest", "Weights", weightBOPalpsForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPcanyonRavine = config.getInt("weightBOPcanyonRavine", "Weights", weightBOPcanyonRavine, biomeWeightMin, biomeWeightMax, "");
			weightBOPglacier = config.getInt("weightBOPglacier", "Weights", weightBOPglacier, biomeWeightMin, biomeWeightMax, "");
			weightBOPlandOfLakesMarsh = config.getInt("weightBOPlandOfLakesMarsh", "Weights", weightBOPlandOfLakesMarsh, biomeWeightMin, biomeWeightMax, "");
			weightBOPmangrove = config.getInt("weightBOPmangrove", "Weights", weightBOPmangrove, biomeWeightMin, biomeWeightMax, "");
			weightBOPmeadowForest = config.getInt("weightBOPmeadowForest", "Weights", weightBOPmeadowForest, biomeWeightMin, biomeWeightMax, "");
			weightBOPoasis = config.getInt("weightBOPoasis", "Weights", weightBOPoasis, biomeWeightMin, biomeWeightMax, "");
			weightBOPorchard = config.getInt("weightBOPorchard", "Weights", weightBOPorchard, biomeWeightMin, biomeWeightMax, "");
			weightBOPquagmire = config.getInt("weightBOPquagmire", "Weights", weightBOPquagmire, biomeWeightMin, biomeWeightMax, "");
			weightBOPscrubland = config.getInt("weightBOPscrubland", "Weights", weightBOPscrubland, biomeWeightMin, biomeWeightMax, "");
			weightBOPseasonalForestClearing = config.getInt("weightBOPseasonalForestClearing", "Weights", weightBOPseasonalForestClearing, biomeWeightMin, biomeWeightMax, "");
			weightBOPsilkglades = config.getInt("weightBOPsilkglades", "Weights", weightBOPsilkglades, biomeWeightMin, biomeWeightMax, "");
			weightBOPspruceWoods = config.getInt("weightBOPspruceWoods", "Weights", weightBOPspruceWoods, biomeWeightMin, biomeWeightMax, "");
			weightBOPtropics = config.getInt("weightBOPtropics", "Weights", weightBOPtropics, biomeWeightMin, biomeWeightMax, "");
			weightBOPvolcano = config.getInt("weightBOPvolcano", "Weights", weightBOPvolcano, biomeWeightMin, biomeWeightMax, "");
			
			//Water biomes
			weightBOPcoralReef = config.getInt("weightBOPcoralReef", "Weights", weightBOPcoralReef, biomeWeightMin, biomeWeightMax, "");
			weightBOPkelpForest = config.getInt("weightBOPkelpForest", "Weights", weightBOPkelpForest, biomeWeightMin, biomeWeightMax, "");
			
			
            //Overworld biomes
            villageBOPalps = config.getBoolean("villageBOPalps", "Villages", villageBOPalps, "");
            villageBOParctic = config.getBoolean("villageBOParctic", "Villages", villageBOParctic, "");
            villageBOPbambooForest = config.getBoolean("villageBOPbambooForest", "Villages", villageBOPbambooForest, "");
            villageBOPbayou = config.getBoolean("villageBOPbayou", "Villages", villageBOPbayou, "");
            villageBOPbog = config.getBoolean("villageBOPbog", "Villages", villageBOPbog, "");
            villageBOPborealForest = config.getBoolean("villageBOPborealForest", "Villages", villageBOPborealForest, "");
            villageBOPbrushland = config.getBoolean("villageBOPbrushland", "Villages", villageBOPbrushland, "");
            villageBOPcanyon = config.getBoolean("villageBOPcanyon", "Villages", villageBOPcanyon, "");
            villageBOPchaparral = config.getBoolean("villageBOPchaparral", "Villages", villageBOPchaparral, "");
            villageBOPcherryBlossomGrove = config.getBoolean("villageBOPcherryBlossomGrove", "Villages", villageBOPcherryBlossomGrove, "");
            villageBOPconiferousForest = config.getBoolean("villageBOPconiferousForest", "Villages", villageBOPconiferousForest, "");
            villageBOPsnowyConiferousForest = config.getBoolean("villageBOPsnowyConiferousForest", "Villages", villageBOPsnowyConiferousForest, "");
            villageBOPcrag = config.getBoolean("villageBOPcrag", "Villages", villageBOPcrag, "");
            villageBOPdeadForest = config.getBoolean("villageBOPdeadForest", "Villages", villageBOPdeadForest, "");
            villageBOPdeadSwamp = config.getBoolean("villageBOPdeadSwamp", "Villages", villageBOPdeadSwamp, "");
            villageBOPdeciduousForest = config.getBoolean("villageBOPdeciduousForest", "Villages", villageBOPdeciduousForest, "");
            villageBOPdenseForest = config.getBoolean("villageBOPdenseForest", "Villages", villageBOPdenseForest, "");
            villageBOPeucalyptusForest = config.getBoolean("villageBOPeucalyptusForest", "Villages", villageBOPeucalyptusForest, "");
            villageBOPfen = config.getBoolean("villageBOPfen", "Villages", villageBOPfen, "");
            villageBOPflowerField = config.getBoolean("villageBOPflowerField", "Villages", villageBOPflowerField, "");
            villageBOPfrostForest = config.getBoolean("villageBOPfrostForest", "Villages", villageBOPfrostForest, "");
            villageBOPfungiForest = config.getBoolean("villageBOPfungiForest", "Villages", villageBOPfungiForest, "");
            villageBOPgarden = config.getBoolean("villageBOPgarden", "Villages", villageBOPgarden, "");
            villageBOPgrassland = config.getBoolean("villageBOPgrassland", "Villages", villageBOPgrassland, "");
            villageBOPgrove = config.getBoolean("villageBOPgrove", "Villages", villageBOPgrove, "");
            villageBOPheathland = config.getBoolean("villageBOPheathland", "Villages", villageBOPheathland, "");
            villageBOPhighland = config.getBoolean("villageBOPhighland", "Villages", villageBOPhighland, "");
            villageBOPjadeCliffs = config.getBoolean("villageBOPjadeCliffs", "Villages", villageBOPjadeCliffs, "");
            villageBOPlandOfLakes = config.getBoolean("villageBOPlandOfLakes", "Villages", villageBOPlandOfLakes, "");
            villageBOPlavenderFields = config.getBoolean("villageBOPlavenderFields", "Villages", villageBOPlavenderFields, "");
            villageBOPlushDesert = config.getBoolean("villageBOPlushDesert", "Villages", villageBOPlushDesert, "");
            villageBOPlushSwamp = config.getBoolean("villageBOPlushSwamp", "Villages", villageBOPlushSwamp, "");
            villageBOPmapleWoods = config.getBoolean("villageBOPmapleWoods", "Villages", villageBOPmapleWoods, "");
            villageBOPmarsh = config.getBoolean("villageBOPmarsh", "Villages", villageBOPmarsh, "");
            villageBOPmeadow = config.getBoolean("villageBOPmeadow", "Villages", villageBOPmeadow, "");
            villageBOPmoor = config.getBoolean("villageBOPmoor", "Villages", villageBOPmoor, "");
            villageBOPmountain = config.getBoolean("villageBOPmountain", "Villages", villageBOPmountain, "");
            villageBOPmysticGrove = config.getBoolean("villageBOPmysticGrove", "Villages", villageBOPmysticGrove, "");
            villageBOPominousWoods = config.getBoolean("villageBOPominousWoods", "Villages", villageBOPominousWoods, "");
            villageBOPoriginValley = config.getBoolean("villageBOPoriginValley", "Villages", villageBOPoriginValley, "");
            villageBOPoutback = config.getBoolean("villageBOPoutback", "Villages", villageBOPoutback, "");
            villageBOPprairie = config.getBoolean("villageBOPprairie", "Villages", villageBOPprairie, "");
            villageBOPrainforest = config.getBoolean("villageBOPrainforest", "Villages", villageBOPrainforest, "");
            villageBOPredwoodForest = config.getBoolean("villageBOPredwoodForest", "Villages", villageBOPredwoodForest, "");
            villageBOPsacredSprings = config.getBoolean("villageBOPsacredSprings", "Villages", villageBOPsacredSprings, "");
            villageBOPseasonalForest = config.getBoolean("villageBOPseasonalForest", "Villages", villageBOPseasonalForest, "");
            villageBOPshield = config.getBoolean("villageBOPshield", "Villages", villageBOPshield, "");
            villageBOPshrubland = config.getBoolean("villageBOPshrubland", "Villages", villageBOPshrubland, "");
            villageBOPsludgepit = config.getBoolean("villageBOPsludgepit", "Villages", villageBOPsludgepit, "");
            villageBOPsteppe = config.getBoolean("villageBOPsteppe", "Villages", villageBOPsteppe, "");
            villageBOPtemperateRainforest = config.getBoolean("villageBOPtemperateRainforest", "Villages", villageBOPtemperateRainforest, "");
            villageBOPthicket = config.getBoolean("villageBOPthicket", "Villages", villageBOPthicket, "");
            villageBOPtropicalRainforest = config.getBoolean("villageBOPtropicalRainforest", "Villages", villageBOPtropicalRainforest, "");
            villageBOPtundra = config.getBoolean("villageBOPtundra", "Villages", villageBOPtundra, "");
            villageBOPwasteland = config.getBoolean("villageBOPwasteland", "Villages", villageBOPwasteland, "");
            villageBOPwetland = config.getBoolean("villageBOPwetland", "Villages", villageBOPwetland, "");
            villageBOPwoodland = config.getBoolean("villageBOPwoodland", "Villages", villageBOPwoodland, "");
            villageBOPxericShrubland = config.getBoolean("villageBOPxericShrubland", "Villages", villageBOPxericShrubland, "");
            
            //Sub biomes
            villageBOPalpsForest = config.getBoolean("villageBOPalpsForest", "Villages", villageBOPalpsForest, "");
            villageBOPcanyonRavine = config.getBoolean("villageBOPcanyonRavine", "Villages", villageBOPcanyonRavine, "");
            villageBOPglacier = config.getBoolean("villageBOPglacier", "Villages", villageBOPglacier, "");
            villageBOPlandOfLakesMarsh = config.getBoolean("villageBOPlandOfLakesMarsh", "Villages", villageBOPlandOfLakesMarsh, "");
            villageBOPmangrove = config.getBoolean("villageBOPmangrove", "Villages", villageBOPmangrove, "");
            villageBOPmeadowForest = config.getBoolean("villageBOPmeadowForest", "Villages", villageBOPmeadowForest, "");
            villageBOPoasis = config.getBoolean("villageBOPoasis", "Villages", villageBOPoasis, "");
            villageBOPorchard = config.getBoolean("villageBOPorchard", "Villages", villageBOPorchard, "");
            villageBOPquagmire = config.getBoolean("villageBOPquagmire", "Villages", villageBOPquagmire, "");
            villageBOPscrubland = config.getBoolean("villageBOPscrubland", "Villages", villageBOPscrubland, "");
            villageBOPseasonalForestClearing = config.getBoolean("villageBOPseasonalForestClearing", "Villages", villageBOPseasonalForestClearing, "");
            villageBOPsilkglades = config.getBoolean("villageBOPsilkglades", "Villages", villageBOPsilkglades, "");
            villageBOPspruceWoods = config.getBoolean("villageBOPspruceWoods", "Villages", villageBOPspruceWoods, "");
            villageBOPtropics = config.getBoolean("villageBOPtropics", "Villages", villageBOPtropics, "");
            villageBOPvolcano = config.getBoolean("villageBOPvolcano", "Villages", villageBOPvolcano, "");
            
            //Water biomes
            villageBOPcoralReef = config.getBoolean("villageBOPcoralReef", "Villages", villageBOPcoralReef, "");
            villageBOPkelpForest = config.getBoolean("villageBOPkelpForest", "Villages", villageBOPkelpForest, "");
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
