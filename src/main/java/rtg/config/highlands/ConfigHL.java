package rtg.config.highlands;

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
			generateHLBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "");
			
			//Generations
			
			/*
			 * ######################################################################################
			 */
			
			//Main biomes
			generateHL_alps = config.getBoolean("generateHL_alps", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			generateHL_autumnForest = config.getBoolean("generateHL_autumnForest", "Biomes", true, "");
			generateHL_badlands = config.getBoolean("generateHL_badlands", "Biomes", true, "");
			generateHL_birchHills = config.getBoolean("generateHL_birchHills", "Biomes", true, "");
			generateHL_bog = config.getBoolean("generateHL_bog", "Biomes", true, "");
			generateHL_cliffs = config.getBoolean("generateHL_cliffs", "Biomes", true, "");
			generateHL_desertMountains = config.getBoolean("generateHL_desertMountains", "Biomes", true, "");
			generateHL_dunes = config.getBoolean("generateHL_dunes", "Biomes", true, "");
			generateHL_estuary = config.getBoolean("generateHL_estuary", "Biomes", true, "");
			generateHL_flyingMountains = config.getBoolean("generateHL_flyingMountains", "Biomes", true, "");
			generateHL_glacier = config.getBoolean("generateHL_glacier", "Biomes", true, "");
			generateHL_highlandsb = config.getBoolean("generateHL_highlandsb", "Biomes", true, "");
			generateHL_lowlands = config.getBoolean("generateHL_lowlands", "Biomes", true, "");
			generateHL_meadow = config.getBoolean("generateHL_meadow", "Biomes", true, "");
			generateHL_outback = config.getBoolean("generateHL_outback", "Biomes", true, "");
			generateHL_pinelands = config.getBoolean("generateHL_pinelands", "Biomes", true, "");
			generateHL_rainforest = config.getBoolean("generateHL_rainforest", "Biomes", true, "");
			generateHL_redwoodForest = config.getBoolean("generateHL_redwoodForest", "Biomes", true, "");
			generateHL_rockMountains = config.getBoolean("generateHL_rockMountains", "Biomes", true, "");
			generateHL_sahel = config.getBoolean("generateHL_sahel", "Biomes", true, "");
			generateHL_savannah = config.getBoolean("generateHL_savannah", "Biomes", true, "");
			generateHL_snowMountains = config.getBoolean("generateHL_snowMountains", "Biomes", true, "");
			generateHL_steppe = config.getBoolean("generateHL_steppe", "Biomes", true, "");
			generateHL_tallPineForest = config.getBoolean("generateHL_tallPineForest", "Biomes", true, "");
			generateHL_tropicalIslands = config.getBoolean("generateHL_tropicalIslands", "Biomes", true, "");
			generateHL_tropics = config.getBoolean("generateHL_tropics", "Biomes", true, "");
			generateHL_tundra = config.getBoolean("generateHL_tundra", "Biomes", true, "");
			generateHL_woodlands = config.getBoolean("generateHL_woodlands", "Biomes", true, "");
			generateHL_woodsMountains = config.getBoolean("generateHL_woodsMountains", "Biomes", true, "");

			//Sub biomes
			generateHL_baldHill = config.getBoolean("generateHL_baldHill", "Biomes", true, "");
			generateHL_canyon = config.getBoolean("generateHL_canyon", "Biomes", true, "");
			generateHL_desertIsland = config.getBoolean("generateHL_desertIsland", "Biomes", true, "");
			generateHL_forestIsland = config.getBoolean("generateHL_forestIsland", "Biomes", true, "");
			generateHL_jungleIsland = config.getBoolean("generateHL_jungleIsland", "Biomes", true, "");
			generateHL_lake = config.getBoolean("generateHL_lake", "Biomes", true, "");
			generateHL_mesa = config.getBoolean("generateHL_mesa", "Biomes", true, "");
			generateHL_oasis = config.getBoolean("generateHL_oasis", "Biomes", true, "");
			generateHL_rockIsland = config.getBoolean("generateHL_rockIsland", "Biomes", true, "");
			generateHL_snowIsland = config.getBoolean("generateHL_snowIsland", "Biomes", true, "");
			generateHL_valley = config.getBoolean("generateHL_valley", "Biomes", true, "");
			generateHL_volcanoIsland = config.getBoolean("generateHL_volcanoIsland", "Biomes", true, "");
			generateHL_windyIsland = config.getBoolean("generateHL_windyIsland", "Biomes", true, "");
			    
			//Border Biomes
			generateHL_shrubland = config.getBoolean("generateHL_shrubland", "Biomes", true, "");
			
			/*
			 * ######################################################################################
			 */
			
			//Weights

			//Main biomes
			weightHL_alps = config.getInt("weightHL_alps", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_autumnForest = config.getInt("weightHL_autumnForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_badlands = config.getInt("weightHL_badlands", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_birchHills = config.getInt("weightHL_birchHills", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_bog = config.getInt("weightHL_bog", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_cliffs = config.getInt("weightHL_cliffs", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_desertMountains = config.getInt("weightHL_desertMountains", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_dunes = config.getInt("weightHL_dunes", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_estuary = config.getInt("weightHL_estuary", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_flyingMountains = config.getInt("weightHL_flyingMountains", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_glacier = config.getInt("weightHL_glacier", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_highlandsb = config.getInt("weightHL_highlandsb", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_lowlands = config.getInt("weightHL_lowlands", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_meadow = config.getInt("weightHL_meadow", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_outback = config.getInt("weightHL_outback", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_pinelands = config.getInt("weightHL_pinelands", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_rainforest = config.getInt("weightHL_rainforest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_redwoodForest = config.getInt("weightHL_redwoodForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_rockMountains = config.getInt("weightHL_rockMountains", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_sahel = config.getInt("weightHL_sahel", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_savannah = config.getInt("weightHL_savannah", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_snowMountains = config.getInt("weightHL_snowMountains", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_steppe = config.getInt("weightHL_steppe", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tallPineForest = config.getInt("weightHL_tallPineForest", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tropicalIslands = config.getInt("weightHL_tropicalIslands", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tropics = config.getInt("weightHL_tropics", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_tundra = config.getInt("weightHL_tundra", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_woodlands = config.getInt("weightHL_woodlands", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_woodsMountains = config.getInt("weightHL_woodsMountains", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			    
			//Sub biomes
			weightHL_baldHill = config.getInt("weightHL_baldHill", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_canyon = config.getInt("weightHL_canyon", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_desertIsland = config.getInt("weightHL_desertIsland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_forestIsland = config.getInt("weightHL_forestIsland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_jungleIsland = config.getInt("weightHL_jungleIsland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_lake = config.getInt("weightHL_lake", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_mesa = config.getInt("weightHL_mesa", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_oasis = config.getInt("weightHL_oasis", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_rockIsland = config.getInt("weightHL_rockIsland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_snowIsland = config.getInt("weightHL_snowIsland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_valley = config.getInt("weightHL_valley", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_volcanoIsland = config.getInt("weightHL_volcanoIsland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			weightHL_windyIsland = config.getInt("weightHL_windyIsland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			    
			//Border biomes
			weightHL_shrubland = config.getInt("weightHL_shrubland", "Weights", biomeWeightDefault, biomeWeightMin, biomeWeightMax, "");
			
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
