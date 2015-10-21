package rtg.config.extrabiomes;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;

public class ConfigEBXL 
{
	public static Configuration config;
	
	public static final int biomeWeightMin = 0;
	public static final int biomeWeightMax = 100;
	public static final int biomeWeightDefault = 10;
	
	public static boolean generateEBXLBiomes = true;
			
	public static boolean generateEBXL_alpine = true;
	public static boolean generateEBXL_autumnwoods = true;
	public static boolean generateEBXL_birchforest = true;
	public static boolean generateEBXL_extremejungle = true;
	public static boolean generateEBXL_forestedisland = true;
	public static boolean generateEBXL_forestedhills = true;
	public static boolean generateEBXL_glacier = true;
	public static boolean generateEBXL_greenhills = true;
	public static boolean generateEBXL_icewasteland = true;
	public static boolean generateEBXL_greenswamp = true;
	public static boolean generateEBXL_marsh = true;
	public static boolean generateEBXL_meadow = true;
	public static boolean generateEBXL_minijungle = true;
	public static boolean generateEBXL_mountaindesert = true;
	public static boolean generateEBXL_mountainridge = true;
	public static boolean generateEBXL_mountaintaiga = true;
	public static boolean generateEBXL_pineforest = true;
	public static boolean generateEBXL_rainforest = true;
	public static boolean generateEBXL_redwoodforest = true;
	public static boolean generateEBXL_redwoodlush = true;
	public static boolean generateEBXL_savanna = true;
	public static boolean generateEBXL_shrubland = true;
	public static boolean generateEBXL_snowforest = true;
	public static boolean generateEBXL_snowyrainforest = true;
	public static boolean generateEBXL_temperaterainforest = true;
	public static boolean generateEBXL_tundra = true;
	public static boolean generateEBXL_wasteland = true;
	public static boolean generateEBXL_woodlands = true;

	public static int weightEBXL_alpine = biomeWeightDefault;
	public static int weightEBXL_autumnwoods = biomeWeightDefault;
	public static int weightEBXL_birchforest = biomeWeightDefault;
	public static int weightEBXL_extremejungle = biomeWeightDefault;
	public static int weightEBXL_forestedisland = biomeWeightDefault;
	public static int weightEBXL_forestedhills = biomeWeightDefault;
	public static int weightEBXL_glacier = biomeWeightDefault;
	public static int weightEBXL_greenhills = biomeWeightDefault;
	public static int weightEBXL_icewasteland = biomeWeightDefault;
	public static int weightEBXL_greenswamp = biomeWeightDefault;
	public static int weightEBXL_marsh = biomeWeightDefault;
	public static int weightEBXL_meadow = biomeWeightDefault;
	public static int weightEBXL_minijungle = biomeWeightDefault;
	public static int weightEBXL_mountaindesert = biomeWeightDefault;
	public static int weightEBXL_mountainridge = biomeWeightDefault;
	public static int weightEBXL_mountaintaiga = biomeWeightDefault;
	public static int weightEBXL_pineforest = biomeWeightDefault;
	public static int weightEBXL_rainforest = biomeWeightDefault;
	public static int weightEBXL_redwoodforest = biomeWeightDefault;
	public static int weightEBXL_redwoodlush = biomeWeightDefault;
	public static int weightEBXL_savanna = biomeWeightDefault;
	public static int weightEBXL_shrubland = biomeWeightDefault;
	public static int weightEBXL_snowforest = biomeWeightDefault;
	public static int weightEBXL_snowyrainforest = biomeWeightDefault;
	public static int weightEBXL_temperaterainforest = biomeWeightDefault;
	public static int weightEBXL_tundra = biomeWeightDefault;
	public static int weightEBXL_wasteland = biomeWeightDefault;
	public static int weightEBXL_woodlands = biomeWeightDefault;
		
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//EBXL
			generateEBXLBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			//Generations
			generateEBXL_alpine = config.getBoolean("generateEBXL_alpine", "Biomes", true, "");
			generateEBXL_autumnwoods = config.getBoolean("generateEBXL_autumnwoods", "Biomes", true, "");
			generateEBXL_birchforest = config.getBoolean("generateEBXL_birchforest", "Biomes", true, "");
			generateEBXL_extremejungle = config.getBoolean("generateEBXL_extremejungle", "Biomes", true, "");
			generateEBXL_forestedisland = config.getBoolean("generateEBXL_forestedisland", "Biomes", true, "");
			generateEBXL_forestedhills = config.getBoolean("generateEBXL_forestedhills", "Biomes", true, "");
			generateEBXL_glacier = config.getBoolean("generateEBXL_glacier", "Biomes", true, "");
			generateEBXL_greenhills = config.getBoolean("generateEBXL_greenhills", "Biomes", true, "");
			generateEBXL_icewasteland = config.getBoolean("generateEBXL_icewasteland", "Biomes", true, "");
			generateEBXL_greenswamp = config.getBoolean("generateEBXL_greenswamp", "Biomes", true, "");
			generateEBXL_marsh = config.getBoolean("generateEBXL_marsh", "Biomes", true, "");
			generateEBXL_meadow = config.getBoolean("generateEBXL_meadow", "Biomes", true, "");
			generateEBXL_minijungle = config.getBoolean("generateEBXL_minijungle", "Biomes", true, "");
			generateEBXL_mountaindesert = config.getBoolean("generateEBXL_mountaindesert", "Biomes", true, "");
			generateEBXL_mountainridge = config.getBoolean("generateEBXL_mountainridge", "Biomes", true, "");
			generateEBXL_mountaintaiga = config.getBoolean("generateEBXL_mountaintaiga", "Biomes", true, "");
			generateEBXL_pineforest = config.getBoolean("generateEBXL_pineforest", "Biomes", true, "");
			generateEBXL_rainforest = config.getBoolean("generateEBXL_rainforest", "Biomes", true, "");
			generateEBXL_redwoodforest = config.getBoolean("generateEBXL_redwoodforest", "Biomes", true, "");
			generateEBXL_redwoodlush = config.getBoolean("generateEBXL_redwoodlush", "Biomes", true, "");
			generateEBXL_savanna = config.getBoolean("generateEBXL_savanna", "Biomes", true, "");
			generateEBXL_shrubland = config.getBoolean("generateEBXL_shrubland", "Biomes", true, "");
			generateEBXL_snowforest = config.getBoolean("generateEBXL_snowforest", "Biomes", true, "");
			generateEBXL_snowyrainforest = config.getBoolean("generateEBXL_snowyrainforest", "Biomes", true, "");
			generateEBXL_temperaterainforest = config.getBoolean("generateEBXL_temperaterainforest", "Biomes", true, "");
			generateEBXL_tundra = config.getBoolean("generateEBXL_tundra", "Biomes", true, "");
			generateEBXL_wasteland = config.getBoolean("generateEBXL_wasteland", "Biomes", true, "");
			generateEBXL_woodlands = config.getBoolean("generateEBXL_woodlands", "Biomes", true, "");
			
			//Weights
			weightEBXL_alpine = config.getInt("weightEBXL_alpine", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_autumnwoods = config.getInt("weightEBXL_autumnwoods", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_birchforest = config.getInt("weightEBXL_birchforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_extremejungle = config.getInt("weightEBXL_extremejungle", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_forestedisland = config.getInt("weightEBXL_forestedisland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_forestedhills = config.getInt("weightEBXL_forestedhills", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_glacier = config.getInt("weightEBXL_glacier", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_greenhills = config.getInt("weightEBXL_greenhills", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_icewasteland = config.getInt("weightEBXL_icewasteland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_greenswamp = config.getInt("weightEBXL_greenswamp", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_marsh = config.getInt("weightEBXL_marsh", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_meadow = config.getInt("weightEBXL_meadow", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_minijungle = config.getInt("weightEBXL_minijungle", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountaindesert = config.getInt("weightEBXL_mountaindesert", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountainridge = config.getInt("weightEBXL_mountainridge", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountaintaiga = config.getInt("weightEBXL_mountaintaiga", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_pineforest = config.getInt("weightEBXL_pineforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_rainforest = config.getInt("weightEBXL_rainforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_redwoodforest = config.getInt("weightEBXL_redwoodforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_redwoodlush = config.getInt("weightEBXL_redwoodlush", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_savanna = config.getInt("weightEBXL_savanna", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_shrubland = config.getInt("weightEBXL_shrubland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_snowforest = config.getInt("weightEBXL_snowforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_snowyrainforest = config.getInt("weightEBXL_snowyrainforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_temperaterainforest = config.getInt("weightEBXL_temperaterainforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_tundra = config.getInt("weightEBXL_tundra", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_wasteland = config.getInt("weightEBXL_wasteland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_woodlands = config.getInt("weightEBXL_woodlands", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading EBXL configuration.");	
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
