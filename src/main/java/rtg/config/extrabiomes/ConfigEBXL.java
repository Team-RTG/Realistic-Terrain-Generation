package rtg.config.extrabiomes;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigEBXL 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
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
			generateEBXLBiomes = config.getBoolean("Generate Biomes", "Biomes", generateEBXLBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			//Generations
			generateEBXL_alpine = config.getBoolean("generateEBXL_alpine", "Biomes", generateEBXL_alpine, "");
			generateEBXL_autumnwoods = config.getBoolean("generateEBXL_autumnwoods", "Biomes", generateEBXL_autumnwoods, "");
			generateEBXL_birchforest = config.getBoolean("generateEBXL_birchforest", "Biomes", generateEBXL_birchforest, "");
			generateEBXL_extremejungle = config.getBoolean("generateEBXL_extremejungle", "Biomes", generateEBXL_extremejungle, "");
			generateEBXL_forestedisland = config.getBoolean("generateEBXL_forestedisland", "Biomes", generateEBXL_forestedisland, "");
			generateEBXL_forestedhills = config.getBoolean("generateEBXL_forestedhills", "Biomes", generateEBXL_forestedhills, "");
			generateEBXL_glacier = config.getBoolean("generateEBXL_glacier", "Biomes", generateEBXL_glacier, "");
			generateEBXL_greenhills = config.getBoolean("generateEBXL_greenhills", "Biomes", generateEBXL_greenhills, "");
			generateEBXL_icewasteland = config.getBoolean("generateEBXL_icewasteland", "Biomes", generateEBXL_icewasteland, "");
			generateEBXL_greenswamp = config.getBoolean("generateEBXL_greenswamp", "Biomes", generateEBXL_greenswamp, "");
			generateEBXL_marsh = config.getBoolean("generateEBXL_marsh", "Biomes", generateEBXL_marsh, "");
			generateEBXL_meadow = config.getBoolean("generateEBXL_meadow", "Biomes", generateEBXL_meadow, "");
			generateEBXL_minijungle = config.getBoolean("generateEBXL_minijungle", "Biomes", generateEBXL_minijungle, "");
			generateEBXL_mountaindesert = config.getBoolean("generateEBXL_mountaindesert", "Biomes", generateEBXL_mountaindesert, "");
			generateEBXL_mountainridge = config.getBoolean("generateEBXL_mountainridge", "Biomes", generateEBXL_mountainridge, "");
			generateEBXL_mountaintaiga = config.getBoolean("generateEBXL_mountaintaiga", "Biomes", generateEBXL_mountaintaiga, "");
			generateEBXL_pineforest = config.getBoolean("generateEBXL_pineforest", "Biomes", generateEBXL_pineforest, "");
			generateEBXL_rainforest = config.getBoolean("generateEBXL_rainforest", "Biomes", generateEBXL_rainforest, "");
			generateEBXL_redwoodforest = config.getBoolean("generateEBXL_redwoodforest", "Biomes", generateEBXL_redwoodforest, "");
			generateEBXL_redwoodlush = config.getBoolean("generateEBXL_redwoodlush", "Biomes", generateEBXL_redwoodlush, "");
			generateEBXL_savanna = config.getBoolean("generateEBXL_savanna", "Biomes", generateEBXL_savanna, "");
			generateEBXL_shrubland = config.getBoolean("generateEBXL_shrubland", "Biomes", generateEBXL_shrubland, "");
			generateEBXL_snowforest = config.getBoolean("generateEBXL_snowforest", "Biomes", generateEBXL_snowforest, "");
			generateEBXL_snowyrainforest = config.getBoolean("generateEBXL_snowyrainforest", "Biomes", generateEBXL_snowyrainforest, "");
			generateEBXL_temperaterainforest = config.getBoolean("generateEBXL_temperaterainforest", "Biomes", generateEBXL_temperaterainforest, "");
			generateEBXL_tundra = config.getBoolean("generateEBXL_tundra", "Biomes", generateEBXL_tundra, "");
			generateEBXL_wasteland = config.getBoolean("generateEBXL_wasteland", "Biomes", generateEBXL_wasteland, "");
			generateEBXL_woodlands = config.getBoolean("generateEBXL_woodlands", "Biomes", generateEBXL_woodlands, "");
			
			//Weights
			weightEBXL_alpine = config.getInt("weightEBXL_alpine", "Weights", weightEBXL_alpine, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_autumnwoods = config.getInt("weightEBXL_autumnwoods", "Weights", weightEBXL_autumnwoods, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_birchforest = config.getInt("weightEBXL_birchforest", "Weights", weightEBXL_birchforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_extremejungle = config.getInt("weightEBXL_extremejungle", "Weights", weightEBXL_extremejungle, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_forestedisland = config.getInt("weightEBXL_forestedisland", "Weights", weightEBXL_forestedisland, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_forestedhills = config.getInt("weightEBXL_forestedhills", "Weights", weightEBXL_forestedhills, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_glacier = config.getInt("weightEBXL_glacier", "Weights", weightEBXL_glacier, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_greenhills = config.getInt("weightEBXL_greenhills", "Weights", weightEBXL_greenhills, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_icewasteland = config.getInt("weightEBXL_icewasteland", "Weights", weightEBXL_icewasteland, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_greenswamp = config.getInt("weightEBXL_greenswamp", "Weights", weightEBXL_greenswamp, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_marsh = config.getInt("weightEBXL_marsh", "Weights", weightEBXL_marsh, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_meadow = config.getInt("weightEBXL_meadow", "Weights", weightEBXL_meadow, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_minijungle = config.getInt("weightEBXL_minijungle", "Weights", weightEBXL_minijungle, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountaindesert = config.getInt("weightEBXL_mountaindesert", "Weights", weightEBXL_mountaindesert, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountainridge = config.getInt("weightEBXL_mountainridge", "Weights", weightEBXL_mountainridge, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_mountaintaiga = config.getInt("weightEBXL_mountaintaiga", "Weights", weightEBXL_mountaintaiga, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_pineforest = config.getInt("weightEBXL_pineforest", "Weights", weightEBXL_pineforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_rainforest = config.getInt("weightEBXL_rainforest", "Weights", weightEBXL_rainforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_redwoodforest = config.getInt("weightEBXL_redwoodforest", "Weights", weightEBXL_redwoodforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_redwoodlush = config.getInt("weightEBXL_redwoodlush", "Weights", weightEBXL_redwoodlush, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_savanna = config.getInt("weightEBXL_savanna", "Weights", weightEBXL_savanna, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_shrubland = config.getInt("weightEBXL_shrubland", "Weights", weightEBXL_shrubland, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_snowforest = config.getInt("weightEBXL_snowforest", "Weights", weightEBXL_snowforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_snowyrainforest = config.getInt("weightEBXL_snowyrainforest", "Weights", weightEBXL_snowyrainforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_temperaterainforest = config.getInt("weightEBXL_temperaterainforest", "Weights", weightEBXL_temperaterainforest, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_tundra = config.getInt("weightEBXL_tundra", "Weights", weightEBXL_tundra, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_wasteland = config.getInt("weightEBXL_wasteland", "Weights", weightEBXL_wasteland, biomeWeightMin, biomeWeightMax, "");
			weightEBXL_woodlands = config.getInt("weightEBXL_woodlands", "Weights", weightEBXL_woodlands, biomeWeightMin, biomeWeightMax, "");
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
