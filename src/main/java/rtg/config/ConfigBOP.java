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
			generateBOPbayou = config.getBoolean("generateBOPayou", "BOP Biomes", true, "");
			generateBOPbog = config.getBoolean("generateBOPbog", "BOP Biomes", true, "");
			generateBOPborealForest = config.getBoolean("generateBOPborealForest", "BOP Biomes", true, "");
			generateBOPbrushland = config.getBoolean("generateBOPbrushland", "BOP Biomes", true, "");
			generateBOPcanyon = config.getBoolean("generateBOPcanyon", "BOP Biomes", true, "");
			generateBOPchaparral = config.getBoolean("generateBOPchaparral", "BOP Biomes", true, "");
			generateBOPcherryBlossomGrove = config.getBoolean("generateBOPBlossomGrove", "BOP Biomes", true, "");
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
