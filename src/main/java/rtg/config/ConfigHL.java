package rtg.config;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;

public class ConfigHL 
{
	public static Configuration config;
	
	public static final int biomeWeightMin = 0;
	public static final int biomeWeightMax = 100;
	public static final int biomeWeightDefault = 10;
	
	public static boolean generateHLBiomes = true;
		
	/*
	 * ######################################################################################
	 */
	
	//Main biomes
	public static boolean generateHL_alps = true;
	public static boolean generateHL_autumnForest = true;
	public static boolean generateHL_badlands = true;
	public static boolean generateHL_birchHills = true;
	public static boolean generateHL_bog = true;
	public static boolean generateHL_cliffs = true;
	public static boolean generateHL_desertMountains = true;
	public static boolean generateHL_dunes = true;
	public static boolean generateHL_estuary = true;
	public static boolean generateHL_flyingMountains = true;
	public static boolean generateHL_glacier = true;
	public static boolean generateHL_highlandsb = true;
	public static boolean generateHL_lowlands = true;
	public static boolean generateHL_meadow = true;
	public static boolean generateHL_outback = true;
	public static boolean generateHL_pinelands = true;
	public static boolean generateHL_rainforest = true;
	public static boolean generateHL_redwoodForest = true;
	public static boolean generateHL_rockMountains = true;
	public static boolean generateHL_sahel = true;
	public static boolean generateHL_savannah = true;
	public static boolean generateHL_snowMountains = true;
	public static boolean generateHL_steppe = true;
	public static boolean generateHL_tallPineForest = true;
	public static boolean generateHL_tropicalIslands = true;
	public static boolean generateHL_tropics = true;
	public static boolean generateHL_tundra = true;
	public static boolean generateHL_woodlands = true;
	public static boolean generateHL_woodsMountains = true;
    
    //Sub biomes
	public static boolean generateHL_baldHill = true;
	public static boolean generateHL_canyon = true;
	public static boolean generateHL_desertIsland = true;
	public static boolean generateHL_forestIsland = true;
	public static boolean generateHL_jungleIsland = true;
	public static boolean generateHL_lake = true;
	public static boolean generateHL_mesa = true;
	public static boolean generateHL_oasis = true;
	public static boolean generateHL_rockIsland = true;
	public static boolean generateHL_snowIsland = true;
	public static boolean generateHL_valley = true;
	public static boolean generateHL_volcanoIsland = true;
	public static boolean generateHL_windyIsland = true;
    
    //Border biomes
	public static boolean generateHL_shrubland = true;

	/*
	 * ######################################################################################
	 */
	
	//Main biomes
	public static int weightHL_alps = biomeWeightDefault;
	public static int weightHL_autumnForest = biomeWeightDefault;
	public static int weightHL_badlands = biomeWeightDefault;
	public static int weightHL_birchHills = biomeWeightDefault;
	public static int weightHL_bog = biomeWeightDefault;
	public static int weightHL_cliffs = biomeWeightDefault;
	public static int weightHL_desertMountains = biomeWeightDefault;
	public static int weightHL_dunes = biomeWeightDefault;
	public static int weightHL_estuary = biomeWeightDefault;
	public static int weightHL_flyingMountains = biomeWeightDefault;
	public static int weightHL_glacier = biomeWeightDefault;
	public static int weightHL_highlandsb = biomeWeightDefault;
	public static int weightHL_lowlands = biomeWeightDefault;
	public static int weightHL_meadow = biomeWeightDefault;
	public static int weightHL_outback = biomeWeightDefault;
	public static int weightHL_pinelands = biomeWeightDefault;
	public static int weightHL_rainforest = biomeWeightDefault;
	public static int weightHL_redwoodForest = biomeWeightDefault;
	public static int weightHL_rockMountains = biomeWeightDefault;
	public static int weightHL_sahel = biomeWeightDefault;
	public static int weightHL_savannah = biomeWeightDefault;
	public static int weightHL_snowMountains = biomeWeightDefault;
	public static int weightHL_steppe = biomeWeightDefault;
	public static int weightHL_tallPineForest = biomeWeightDefault;
	public static int weightHL_tropicalIslands = biomeWeightDefault;
	public static int weightHL_tropics = biomeWeightDefault;
	public static int weightHL_tundra = biomeWeightDefault;
	public static int weightHL_woodlands = biomeWeightDefault;
	public static int weightHL_woodsMountains = biomeWeightDefault;
    
    //Sub biomes
	public static int weightHL_baldHill = biomeWeightDefault;
	public static int weightHL_canyon = biomeWeightDefault;
	public static int weightHL_desertIsland = biomeWeightDefault;
	public static int weightHL_forestIsland = biomeWeightDefault;
	public static int weightHL_jungleIsland = biomeWeightDefault;
	public static int weightHL_lake = biomeWeightDefault;
	public static int weightHL_mesa = biomeWeightDefault;
	public static int weightHL_oasis = biomeWeightDefault;
	public static int weightHL_rockIsland = biomeWeightDefault;
	public static int weightHL_snowIsland = biomeWeightDefault;
	public static int weightHL_valley = biomeWeightDefault;
	public static int weightHL_volcanoIsland = biomeWeightDefault;
	public static int weightHL_windyIsland = biomeWeightDefault;
    
    //Border biomes
	public static int weightHL_shrubland = biomeWeightDefault;
	
	/*
	 * ######################################################################################
	 */
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			//HL
			generateHLBiomes = config.getBoolean("Generate HL Biomes", "HL Biomes", true, "");
			
			//Generations
			
			/*
			 * ######################################################################################
			 */
			
			//Main biomes
			generateHL_alps = config.getBoolean("generateHL_alps", "HL Biomes", true, "");
			generateHL_autumnForest = config.getBoolean("generateHL_autumnForest", "HL Biomes", true, "");
			generateHL_badlands = config.getBoolean("generateHL_badlands", "HL Biomes", true, "");
			generateHL_birchHills = config.getBoolean("generateHL_birchHills", "HL Biomes", true, "");
			generateHL_bog = config.getBoolean("generateHL_bog", "HL Biomes", true, "");
			generateHL_cliffs = config.getBoolean("generateHL_cliffs", "HL Biomes", true, "");
			generateHL_desertMountains = config.getBoolean("generateHL_desertMountains", "HL Biomes", true, "");
			generateHL_dunes = config.getBoolean("generateHL_dunes", "HL Biomes", true, "");
			generateHL_estuary = config.getBoolean("generateHL_estuary", "HL Biomes", true, "");
			generateHL_flyingMountains = config.getBoolean("generateHL_flyingMountains", "HL Biomes", true, "");
			generateHL_glacier = config.getBoolean("generateHL_glacier", "HL Biomes", true, "");
			generateHL_highlandsb = config.getBoolean("generateHL_highlandsb", "HL Biomes", true, "");
			generateHL_lowlands = config.getBoolean("generateHL_lowlands", "HL Biomes", true, "");
			generateHL_meadow = config.getBoolean("generateHL_meadow", "HL Biomes", true, "");
			generateHL_outback = config.getBoolean("generateHL_outback", "HL Biomes", true, "");
			generateHL_pinelands = config.getBoolean("generateHL_pinelands", "HL Biomes", true, "");
			generateHL_rainforest = config.getBoolean("generateHL_rainforest", "HL Biomes", true, "");
			generateHL_redwoodForest = config.getBoolean("generateHL_redwoodForest", "HL Biomes", true, "");
			generateHL_rockMountains = config.getBoolean("generateHL_rockMountains", "HL Biomes", true, "");
			generateHL_sahel = config.getBoolean("generateHL_sahel", "HL Biomes", true, "");
			generateHL_savannah = config.getBoolean("generateHL_savannah", "HL Biomes", true, "");
			generateHL_snowMountains = config.getBoolean("generateHL_snowMountains", "HL Biomes", true, "");
			generateHL_steppe = config.getBoolean("generateHL_steppe", "HL Biomes", true, "");
			generateHL_tallPineForest = config.getBoolean("generateHL_tallPineForest", "HL Biomes", true, "");
			generateHL_tropicalIslands = config.getBoolean("generateHL_tropicalIslands", "HL Biomes", true, "");
			generateHL_tropics = config.getBoolean("generateHL_tropics", "HL Biomes", true, "");
			generateHL_tundra = config.getBoolean("generateHL_tundra", "HL Biomes", true, "");
			generateHL_woodlands = config.getBoolean("generateHL_woodlands", "HL Biomes", true, "");
			generateHL_woodsMountains = config.getBoolean("generateHL_woodsMountains", "HL Biomes", true, "");

			//Sub biomes
			generateHL_baldHill = config.getBoolean("generateHL_baldHill", "HL Biomes", true, "");
			generateHL_canyon = config.getBoolean("generateHL_canyon", "HL Biomes", true, "");
			generateHL_desertIsland = config.getBoolean("generateHL_desertIsland", "HL Biomes", true, "");
			generateHL_forestIsland = config.getBoolean("generateHL_forestIsland", "HL Biomes", true, "");
			generateHL_jungleIsland = config.getBoolean("generateHL_jungleIsland", "HL Biomes", true, "");
			generateHL_lake = config.getBoolean("generateHL_lake", "HL Biomes", true, "");
			generateHL_mesa = config.getBoolean("generateHL_mesa", "HL Biomes", true, "");
			generateHL_oasis = config.getBoolean("generateHL_oasis", "HL Biomes", true, "");
			generateHL_rockIsland = config.getBoolean("generateHL_rockIsland", "HL Biomes", true, "");
			generateHL_snowIsland = config.getBoolean("generateHL_snowIsland", "HL Biomes", true, "");
			generateHL_valley = config.getBoolean("generateHL_valley", "HL Biomes", true, "");
			generateHL_volcanoIsland = config.getBoolean("generateHL_volcanoIsland", "HL Biomes", true, "");
			generateHL_windyIsland = config.getBoolean("generateHL_windyIsland", "HL Biomes", true, "");
			    
			//Border Biomes
			generateHL_shrubland = config.getBoolean("generateHL_shrubland", "HL Biomes", true, "");
			
			/*
			 * ######################################################################################
			 */
			
			//Weights

			//Main biomes
			weightHL_alps = config.getInt("weightHL_alps", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_autumnForest = config.getInt("weightHL_autumnForest", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_badlands = config.getInt("weightHL_badlands", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_birchHills = config.getInt("weightHL_birchHills", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_bog = config.getInt("weightHL_bog", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_cliffs = config.getInt("weightHL_cliffs", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_desertMountains = config.getInt("weightHL_desertMountains", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_dunes = config.getInt("weightHL_dunes", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_estuary = config.getInt("weightHL_estuary", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_flyingMountains = config.getInt("weightHL_flyingMountains", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_glacier = config.getInt("weightHL_glacier", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_highlandsb = config.getInt("weightHL_highlandsb", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_lowlands = config.getInt("weightHL_lowlands", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_meadow = config.getInt("weightHL_meadow", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_outback = config.getInt("weightHL_outback", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_pinelands = config.getInt("weightHL_pinelands", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_rainforest = config.getInt("weightHL_rainforest", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_redwoodForest = config.getInt("weightHL_redwoodForest", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_rockMountains = config.getInt("weightHL_rockMountains", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_sahel = config.getInt("weightHL_sahel", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_savannah = config.getInt("weightHL_savannah", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_snowMountains = config.getInt("weightHL_snowMountains", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_steppe = config.getInt("weightHL_steppe", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tallPineForest = config.getInt("weightHL_tallPineForest", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tropicalIslands = config.getInt("weightHL_tropicalIslands", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tropics = config.getInt("weightHL_tropics", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tundra = config.getInt("weightHL_tundra", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_woodlands = config.getInt("weightHL_woodlands", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_woodsMountains = config.getInt("weightHL_woodsMountains", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			    
			//Sub biomes
			weightHL_baldHill = config.getInt("weightHL_baldHill", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_canyon = config.getInt("weightHL_canyon", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_desertIsland = config.getInt("weightHL_desertIsland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_forestIsland = config.getInt("weightHL_forestIsland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_jungleIsland = config.getInt("weightHL_jungleIsland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_lake = config.getInt("weightHL_lake", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_mesa = config.getInt("weightHL_mesa", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_oasis = config.getInt("weightHL_oasis", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_rockIsland = config.getInt("weightHL_rockIsland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_snowIsland = config.getInt("weightHL_snowIsland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_valley = config.getInt("weightHL_valley", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_volcanoIsland = config.getInt("weightHL_volcanoIsland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_windyIsland = config.getInt("weightHL_windyIsland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			    
			//Border biomes
			weightHL_shrubland = config.getInt("weightHL_shrubland", "HL Biome Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading HL configuration.");	
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
