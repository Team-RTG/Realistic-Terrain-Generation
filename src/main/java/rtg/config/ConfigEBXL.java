package rtg.config;

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
			generateEBXLBiomes = config.getBoolean("Generate EBXL Biomes", "EBXL Biomes", true, "");
			
			//Generations
			generateEBXL_alpine = config.getBoolean("generateEBXL_alpine", "EBXL Biomes", true, "");
			generateEBXL_autumnwoods = config.getBoolean("generateEBXL_autumnwoods", "EBXL Biomes", true, "");
			generateEBXL_birchforest = config.getBoolean("generateEBXL_birchforest", "EBXL Biomes", true, "");
			generateEBXL_extremejungle = config.getBoolean("generateEBXL_extremejungle", "EBXL Biomes", true, "");
			generateEBXL_forestedisland = config.getBoolean("generateEBXL_forestedisland", "EBXL Biomes", true, "");
			generateEBXL_forestedhills = config.getBoolean("generateEBXL_forestedhills", "EBXL Biomes", true, "");
			generateEBXL_glacier = config.getBoolean("generateEBXL_glacier", "EBXL Biomes", true, "");
			generateEBXL_greenhills = config.getBoolean("generateEBXL_greenhills", "EBXL Biomes", true, "");
			generateEBXL_icewasteland = config.getBoolean("generateEBXL_icewasteland", "EBXL Biomes", true, "");
			generateEBXL_greenswamp = config.getBoolean("generateEBXL_greenswamp", "EBXL Biomes", true, "");
			generateEBXL_marsh = config.getBoolean("generateEBXL_marsh", "EBXL Biomes", true, "");
			generateEBXL_meadow = config.getBoolean("generateEBXL_meadow", "EBXL Biomes", true, "");
			generateEBXL_minijungle = config.getBoolean("generateEBXL_minijungle", "EBXL Biomes", true, "");
			generateEBXL_mountaindesert = config.getBoolean("generateEBXL_mountaindesert", "EBXL Biomes", true, "");
			generateEBXL_mountainridge = config.getBoolean("generateEBXL_mountainridge", "EBXL Biomes", true, "");
			generateEBXL_mountaintaiga = config.getBoolean("generateEBXL_mountaintaiga", "EBXL Biomes", true, "");
			generateEBXL_pineforest = config.getBoolean("generateEBXL_pineforest", "EBXL Biomes", true, "");
			generateEBXL_rainforest = config.getBoolean("generateEBXL_rainforest", "EBXL Biomes", true, "");
			generateEBXL_redwoodforest = config.getBoolean("generateEBXL_redwoodforest", "EBXL Biomes", true, "");
			generateEBXL_redwoodlush = config.getBoolean("generateEBXL_redwoodlush", "EBXL Biomes", true, "");
			generateEBXL_savanna = config.getBoolean("generateEBXL_savanna", "EBXL Biomes", true, "");
			generateEBXL_shrubland = config.getBoolean("generateEBXL_shrubland", "EBXL Biomes", true, "");
			generateEBXL_snowforest = config.getBoolean("generateEBXL_snowforest", "EBXL Biomes", true, "");
			generateEBXL_snowyrainforest = config.getBoolean("generateEBXL_snowyrainforest", "EBXL Biomes", true, "");
			generateEBXL_temperaterainforest = config.getBoolean("generateEBXL_temperaterainforest", "EBXL Biomes", true, "");
			generateEBXL_tundra = config.getBoolean("generateEBXL_tundra", "EBXL Biomes", true, "");
			generateEBXL_wasteland = config.getBoolean("generateEBXL_wasteland", "EBXL Biomes", true, "");
			generateEBXL_woodlands = config.getBoolean("generateEBXL_woodlands", "EBXL Biomes", true, "");
			
			//Weights
			weightEBXL_alpine = config.getInt("weightEBXL_alpine", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_autumnwoods = config.getInt("weightEBXL_autumnwoods", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_birchforest = config.getInt("weightEBXL_birchforest", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_extremejungle = config.getInt("weightEBXL_extremejungle", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_forestedisland = config.getInt("weightEBXL_forestedisland", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_forestedhills = config.getInt("weightEBXL_forestedhills", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_glacier = config.getInt("weightEBXL_glacier", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_greenhills = config.getInt("weightEBXL_greenhills", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_icewasteland = config.getInt("weightEBXL_icewasteland", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_greenswamp = config.getInt("weightEBXL_greenswamp", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_marsh = config.getInt("weightEBXL_marsh", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_meadow = config.getInt("weightEBXL_meadow", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_minijungle = config.getInt("weightEBXL_minijungle", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountaindesert = config.getInt("weightEBXL_mountaindesert", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountainridge = config.getInt("weightEBXL_mountainridge", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountaintaiga = config.getInt("weightEBXL_mountaintaiga", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_pineforest = config.getInt("weightEBXL_pineforest", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_rainforest = config.getInt("weightEBXL_rainforest", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_redwoodforest = config.getInt("weightEBXL_redwoodforest", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_redwoodlush = config.getInt("weightEBXL_redwoodlush", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_savanna = config.getInt("weightEBXL_savanna", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_shrubland = config.getInt("weightEBXL_shrubland", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_snowforest = config.getInt("weightEBXL_snowforest", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_snowyrainforest = config.getInt("weightEBXL_snowyrainforest", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_temperaterainforest = config.getInt("weightEBXL_temperaterainforest", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_tundra = config.getInt("weightEBXL_tundra", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_wasteland = config.getInt("weightEBXL_wasteland", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_woodlands = config.getInt("weightEBXL_woodlands", "EBXL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
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
