package rtg.config.highlands;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigHL 
{
	public static Configuration config;
	
    public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
    public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
    public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
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
	public static int weightHL_baldHill = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_canyon = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_desertIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_forestIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_jungleIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_lake = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_mesa = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_oasis = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_rockIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_snowIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_valley = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_volcanoIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHL_windyIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
    
    //Border biomes
	public static int weightHL_shrubland = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	
    /*
     * ######################################################################################
     */
    
    //Main biomes
    public static boolean villageHL_alps = true;
    public static boolean villageHL_autumnForest = true;
    public static boolean villageHL_badlands = true;
    public static boolean villageHL_birchHills = true;
    public static boolean villageHL_bog = true;
    public static boolean villageHL_cliffs = true;
    public static boolean villageHL_desertMountains = true;
    public static boolean villageHL_dunes = true;
    public static boolean villageHL_estuary = true;
    public static boolean villageHL_flyingMountains = true;
    public static boolean villageHL_glacier = true;
    public static boolean villageHL_highlandsb = true;
    public static boolean villageHL_lowlands = true;
    public static boolean villageHL_meadow = true;
    public static boolean villageHL_outback = true;
    public static boolean villageHL_pinelands = true;
    public static boolean villageHL_rainforest = true;
    public static boolean villageHL_redwoodForest = true;
    public static boolean villageHL_rockMountains = true;
    public static boolean villageHL_sahel = true;
    public static boolean villageHL_savannah = true;
    public static boolean villageHL_snowMountains = true;
    public static boolean villageHL_steppe = true;
    public static boolean villageHL_tallPineForest = true;
    public static boolean villageHL_tropicalIslands = true;
    public static boolean villageHL_tropics = true;
    public static boolean villageHL_tundra = true;
    public static boolean villageHL_woodlands = true;
    public static boolean villageHL_woodsMountains = true;
    
    //Sub biomes
    public static boolean villageHL_baldHill = true;
    public static boolean villageHL_canyon = true;
    public static boolean villageHL_desertIsland = true;
    public static boolean villageHL_forestIsland = true;
    public static boolean villageHL_jungleIsland = true;
    public static boolean villageHL_lake = true;
    public static boolean villageHL_mesa = true;
    public static boolean villageHL_oasis = true;
    public static boolean villageHL_rockIsland = true;
    public static boolean villageHL_snowIsland = true;
    public static boolean villageHL_valley = true;
    public static boolean villageHL_volcanoIsland = true;
    public static boolean villageHL_windyIsland = true;
    
    //Border biomes
    public static boolean villageHL_shrubland = true;
    
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
			generateHLBiomes = config.getBoolean("Generate Biomes", "Biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");
			
			//Generations
			
			/*
			 * ######################################################################################
			 */
			
			//Main biomes
			generateHL_alps = config.getBoolean("generateHL_alps", "Biomes", generateHL_alps, "");
			generateHL_autumnForest = config.getBoolean("generateHL_autumnForest", "Biomes", generateHL_autumnForest, "");
			generateHL_badlands = config.getBoolean("generateHL_badlands", "Biomes", generateHL_badlands, "");
			generateHL_birchHills = config.getBoolean("generateHL_birchHills", "Biomes", generateHL_birchHills, "");
			generateHL_bog = config.getBoolean("generateHL_bog", "Biomes", generateHL_bog, "");
			generateHL_cliffs = config.getBoolean("generateHL_cliffs", "Biomes", generateHL_cliffs, "");
			generateHL_desertMountains = config.getBoolean("generateHL_desertMountains", "Biomes", generateHL_desertMountains, "");
			generateHL_dunes = config.getBoolean("generateHL_dunes", "Biomes", generateHL_dunes, "");
			generateHL_estuary = config.getBoolean("generateHL_estuary", "Biomes", generateHL_estuary, "");
			generateHL_flyingMountains = config.getBoolean("generateHL_flyingMountains", "Biomes", generateHL_flyingMountains, "");
			generateHL_glacier = config.getBoolean("generateHL_glacier", "Biomes", generateHL_glacier, "");
			generateHL_highlandsb = config.getBoolean("generateHL_highlandsb", "Biomes", generateHL_highlandsb, "");
			generateHL_lowlands = config.getBoolean("generateHL_lowlands", "Biomes", generateHL_lowlands, "");
			generateHL_meadow = config.getBoolean("generateHL_meadow", "Biomes", generateHL_meadow, "");
			generateHL_outback = config.getBoolean("generateHL_outback", "Biomes", generateHL_outback, "");
			generateHL_pinelands = config.getBoolean("generateHL_pinelands", "Biomes", generateHL_pinelands, "");
			generateHL_rainforest = config.getBoolean("generateHL_rainforest", "Biomes", generateHL_rainforest, "");
			generateHL_redwoodForest = config.getBoolean("generateHL_redwoodForest", "Biomes", generateHL_redwoodForest, "");
			generateHL_rockMountains = config.getBoolean("generateHL_rockMountains", "Biomes", generateHL_rockMountains, "");
			generateHL_sahel = config.getBoolean("generateHL_sahel", "Biomes", generateHL_sahel, "");
			generateHL_savannah = config.getBoolean("generateHL_savannah", "Biomes", generateHL_savannah, "");
			generateHL_snowMountains = config.getBoolean("generateHL_snowMountains", "Biomes", generateHL_snowMountains, "");
			generateHL_steppe = config.getBoolean("generateHL_steppe", "Biomes", generateHL_steppe, "");
			generateHL_tallPineForest = config.getBoolean("generateHL_tallPineForest", "Biomes", generateHL_tallPineForest, "");
			generateHL_tropicalIslands = config.getBoolean("generateHL_tropicalIslands", "Biomes", generateHL_tropicalIslands, "");
			generateHL_tropics = config.getBoolean("generateHL_tropics", "Biomes", generateHL_tropics, "");
			generateHL_tundra = config.getBoolean("generateHL_tundra", "Biomes", generateHL_tundra, "");
			generateHL_woodlands = config.getBoolean("generateHL_woodlands", "Biomes", generateHL_woodlands, "");
			generateHL_woodsMountains = config.getBoolean("generateHL_woodsMountains", "Biomes", generateHL_woodsMountains, "");

			//Sub biomes
			generateHL_baldHill = config.getBoolean("generateHL_baldHill", "Biomes", generateHL_baldHill, "");
			generateHL_canyon = config.getBoolean("generateHL_canyon", "Biomes", generateHL_canyon, "");
			generateHL_desertIsland = config.getBoolean("generateHL_desertIsland", "Biomes", generateHL_desertIsland, "");
			generateHL_forestIsland = config.getBoolean("generateHL_forestIsland", "Biomes", generateHL_forestIsland, "");
			generateHL_jungleIsland = config.getBoolean("generateHL_jungleIsland", "Biomes", generateHL_jungleIsland, "");
			generateHL_lake = config.getBoolean("generateHL_lake", "Biomes", generateHL_lake, "");
			generateHL_mesa = config.getBoolean("generateHL_mesa", "Biomes", generateHL_mesa, "");
			generateHL_oasis = config.getBoolean("generateHL_oasis", "Biomes", generateHL_oasis, "");
			generateHL_rockIsland = config.getBoolean("generateHL_rockIsland", "Biomes", generateHL_rockIsland, "");
			generateHL_snowIsland = config.getBoolean("generateHL_snowIsland", "Biomes", generateHL_snowIsland, "");
			generateHL_valley = config.getBoolean("generateHL_valley", "Biomes", generateHL_valley, "");
			generateHL_volcanoIsland = config.getBoolean("generateHL_volcanoIsland", "Biomes", generateHL_volcanoIsland, "");
			generateHL_windyIsland = config.getBoolean("generateHL_windyIsland", "Biomes", generateHL_windyIsland, "");
			    
			//Border Biomes
			generateHL_shrubland = config.getBoolean("generateHL_shrubland", "Biomes", generateHL_shrubland, "");
			
			/*
			 * ######################################################################################
			 */
			
			//Weights

			//Main biomes
			weightHL_alps = config.getInt("weightHL_alps", "Weights", weightHL_alps, biomeWeightMin, biomeWeightMax, "");
			weightHL_autumnForest = config.getInt("weightHL_autumnForest", "Weights", weightHL_autumnForest, biomeWeightMin, biomeWeightMax, "");
			weightHL_badlands = config.getInt("weightHL_badlands", "Weights", weightHL_badlands, biomeWeightMin, biomeWeightMax, "");
			weightHL_birchHills = config.getInt("weightHL_birchHills", "Weights", weightHL_birchHills, biomeWeightMin, biomeWeightMax, "");
			weightHL_bog = config.getInt("weightHL_bog", "Weights", weightHL_bog, biomeWeightMin, biomeWeightMax, "");
			weightHL_cliffs = config.getInt("weightHL_cliffs", "Weights", weightHL_cliffs, biomeWeightMin, biomeWeightMax, "");
			weightHL_desertMountains = config.getInt("weightHL_desertMountains", "Weights", weightHL_desertMountains, biomeWeightMin, biomeWeightMax, "");
			weightHL_dunes = config.getInt("weightHL_dunes", "Weights", weightHL_dunes, biomeWeightMin, biomeWeightMax, "");
			weightHL_estuary = config.getInt("weightHL_estuary", "Weights", weightHL_estuary, biomeWeightMin, biomeWeightMax, "");
			weightHL_flyingMountains = config.getInt("weightHL_flyingMountains", "Weights", weightHL_flyingMountains, biomeWeightMin, biomeWeightMax, "");
			weightHL_glacier = config.getInt("weightHL_glacier", "Weights", weightHL_glacier, biomeWeightMin, biomeWeightMax, "");
			weightHL_highlandsb = config.getInt("weightHL_highlandsb", "Weights", weightHL_highlandsb, biomeWeightMin, biomeWeightMax, "");
			weightHL_lowlands = config.getInt("weightHL_lowlands", "Weights", weightHL_lowlands, biomeWeightMin, biomeWeightMax, "");
			weightHL_meadow = config.getInt("weightHL_meadow", "Weights", weightHL_meadow, biomeWeightMin, biomeWeightMax, "");
			weightHL_outback = config.getInt("weightHL_outback", "Weights", weightHL_outback, biomeWeightMin, biomeWeightMax, "");
			weightHL_pinelands = config.getInt("weightHL_pinelands", "Weights", weightHL_pinelands, biomeWeightMin, biomeWeightMax, "");
			weightHL_rainforest = config.getInt("weightHL_rainforest", "Weights", weightHL_rainforest, biomeWeightMin, biomeWeightMax, "");
			weightHL_redwoodForest = config.getInt("weightHL_redwoodForest", "Weights", weightHL_redwoodForest, biomeWeightMin, biomeWeightMax, "");
			weightHL_rockMountains = config.getInt("weightHL_rockMountains", "Weights", weightHL_rockMountains, biomeWeightMin, biomeWeightMax, "");
			weightHL_sahel = config.getInt("weightHL_sahel", "Weights", weightHL_sahel, biomeWeightMin, biomeWeightMax, "");
			weightHL_savannah = config.getInt("weightHL_savannah", "Weights", weightHL_savannah, biomeWeightMin, biomeWeightMax, "");
			weightHL_snowMountains = config.getInt("weightHL_snowMountains", "Weights", weightHL_snowMountains, biomeWeightMin, biomeWeightMax, "");
			weightHL_steppe = config.getInt("weightHL_steppe", "Weights", weightHL_steppe, biomeWeightMin, biomeWeightMax, "");
			weightHL_tallPineForest = config.getInt("weightHL_tallPineForest", "Weights", weightHL_tallPineForest, biomeWeightMin, biomeWeightMax, "");
			weightHL_tropicalIslands = config.getInt("weightHL_tropicalIslands", "Weights", weightHL_tropicalIslands, biomeWeightMin, biomeWeightMax, "");
			weightHL_tropics = config.getInt("weightHL_tropics", "Weights", weightHL_tropics, biomeWeightMin, biomeWeightMax, "");
			weightHL_tundra = config.getInt("weightHL_tundra", "Weights", weightHL_tundra, biomeWeightMin, biomeWeightMax, "");
			weightHL_woodlands = config.getInt("weightHL_woodlands", "Weights", weightHL_woodlands, biomeWeightMin, biomeWeightMax, "");
			weightHL_woodsMountains = config.getInt("weightHL_woodsMountains", "Weights", weightHL_woodsMountains, biomeWeightMin, biomeWeightMax, "");
			    
			//Sub biomes
			weightHL_baldHill = config.getInt("weightHL_baldHill", "Weights", weightHL_baldHill, biomeWeightMin, biomeWeightMax, "");
			weightHL_canyon = config.getInt("weightHL_canyon", "Weights", weightHL_canyon, biomeWeightMin, biomeWeightMax, "");
			weightHL_desertIsland = config.getInt("weightHL_desertIsland", "Weights", weightHL_desertIsland, biomeWeightMin, biomeWeightMax, "");
			weightHL_forestIsland = config.getInt("weightHL_forestIsland", "Weights", weightHL_forestIsland, biomeWeightMin, biomeWeightMax, "");
			weightHL_jungleIsland = config.getInt("weightHL_jungleIsland", "Weights", weightHL_jungleIsland, biomeWeightMin, biomeWeightMax, "");
			weightHL_lake = config.getInt("weightHL_lake", "Weights", weightHL_lake, biomeWeightMin, biomeWeightMax, "");
			weightHL_mesa = config.getInt("weightHL_mesa", "Weights", weightHL_mesa, biomeWeightMin, biomeWeightMax, "");
			weightHL_oasis = config.getInt("weightHL_oasis", "Weights", weightHL_oasis, biomeWeightMin, biomeWeightMax, "");
			weightHL_rockIsland = config.getInt("weightHL_rockIsland", "Weights", weightHL_rockIsland, biomeWeightMin, biomeWeightMax, "");
			weightHL_snowIsland = config.getInt("weightHL_snowIsland", "Weights", weightHL_snowIsland, biomeWeightMin, biomeWeightMax, "");
			weightHL_valley = config.getInt("weightHL_valley", "Weights", weightHL_valley, biomeWeightMin, biomeWeightMax, "");
			weightHL_volcanoIsland = config.getInt("weightHL_volcanoIsland", "Weights", weightHL_volcanoIsland, biomeWeightMin, biomeWeightMax, "");
			weightHL_windyIsland = config.getInt("weightHL_windyIsland", "Weights", weightHL_windyIsland, biomeWeightMin, biomeWeightMax, "");
			    
			//Border biomes
			weightHL_shrubland = config.getInt("weightHL_shrubland", "Weights", weightHL_shrubland, biomeWeightMin, biomeWeightMax, "");
			
            /*
             * ######################################################################################
             */
			
            //Villages

            //Main biomes
            villageHL_alps = config.getBoolean("villageHL_alps", "Villages", villageHL_alps, "");
            villageHL_autumnForest = config.getBoolean("villageHL_autumnForest", "Villages", villageHL_autumnForest, "");
            villageHL_badlands = config.getBoolean("villageHL_badlands", "Villages", villageHL_badlands, "");
            villageHL_birchHills = config.getBoolean("villageHL_birchHills", "Villages", villageHL_birchHills, "");
            villageHL_bog = config.getBoolean("villageHL_bog", "Villages", villageHL_bog, "");
            villageHL_cliffs = config.getBoolean("villageHL_cliffs", "Villages", villageHL_cliffs, "");
            villageHL_desertMountains = config.getBoolean("villageHL_desertMountains", "Villages", villageHL_desertMountains, "");
            villageHL_dunes = config.getBoolean("villageHL_dunes", "Villages", villageHL_dunes, "");
            villageHL_estuary = config.getBoolean("villageHL_estuary", "Villages", villageHL_estuary, "");
            villageHL_flyingMountains = config.getBoolean("villageHL_flyingMountains", "Villages", villageHL_flyingMountains, "");
            villageHL_glacier = config.getBoolean("villageHL_glacier", "Villages", villageHL_glacier, "");
            villageHL_highlandsb = config.getBoolean("villageHL_highlandsb", "Villages", villageHL_highlandsb, "");
            villageHL_lowlands = config.getBoolean("villageHL_lowlands", "Villages", villageHL_lowlands, "");
            villageHL_meadow = config.getBoolean("villageHL_meadow", "Villages", villageHL_meadow, "");
            villageHL_outback = config.getBoolean("villageHL_outback", "Villages", villageHL_outback, "");
            villageHL_pinelands = config.getBoolean("villageHL_pinelands", "Villages", villageHL_pinelands, "");
            villageHL_rainforest = config.getBoolean("villageHL_rainforest", "Villages", villageHL_rainforest, "");
            villageHL_redwoodForest = config.getBoolean("villageHL_redwoodForest", "Villages", villageHL_redwoodForest, "");
            villageHL_rockMountains = config.getBoolean("villageHL_rockMountains", "Villages", villageHL_rockMountains, "");
            villageHL_sahel = config.getBoolean("villageHL_sahel", "Villages", villageHL_sahel, "");
            villageHL_savannah = config.getBoolean("villageHL_savannah", "Villages", villageHL_savannah, "");
            villageHL_snowMountains = config.getBoolean("villageHL_snowMountains", "Villages", villageHL_snowMountains, "");
            villageHL_steppe = config.getBoolean("villageHL_steppe", "Villages", villageHL_steppe, "");
            villageHL_tallPineForest = config.getBoolean("villageHL_tallPineForest", "Villages", villageHL_tallPineForest, "");
            villageHL_tropicalIslands = config.getBoolean("villageHL_tropicalIslands", "Villages", villageHL_tropicalIslands, "");
            villageHL_tropics = config.getBoolean("villageHL_tropics", "Villages", villageHL_tropics, "");
            villageHL_tundra = config.getBoolean("villageHL_tundra", "Villages", villageHL_tundra, "");
            villageHL_woodlands = config.getBoolean("villageHL_woodlands", "Villages", villageHL_woodlands, "");
            villageHL_woodsMountains = config.getBoolean("villageHL_woodsMountains", "Villages", villageHL_woodsMountains, "");

            //Sub biomes
            villageHL_baldHill = config.getBoolean("villageHL_baldHill", "Villages", villageHL_baldHill, "");
            villageHL_canyon = config.getBoolean("villageHL_canyon", "Villages", villageHL_canyon, "");
            villageHL_desertIsland = config.getBoolean("villageHL_desertIsland", "Villages", villageHL_desertIsland, "");
            villageHL_forestIsland = config.getBoolean("villageHL_forestIsland", "Villages", villageHL_forestIsland, "");
            villageHL_jungleIsland = config.getBoolean("villageHL_jungleIsland", "Villages", villageHL_jungleIsland, "");
            villageHL_lake = config.getBoolean("villageHL_lake", "Villages", villageHL_lake, "");
            villageHL_mesa = config.getBoolean("villageHL_mesa", "Villages", villageHL_mesa, "");
            villageHL_oasis = config.getBoolean("villageHL_oasis", "Villages", villageHL_oasis, "");
            villageHL_rockIsland = config.getBoolean("villageHL_rockIsland", "Villages", villageHL_rockIsland, "");
            villageHL_snowIsland = config.getBoolean("villageHL_snowIsland", "Villages", villageHL_snowIsland, "");
            villageHL_valley = config.getBoolean("villageHL_valley", "Villages", villageHL_valley, "");
            villageHL_volcanoIsland = config.getBoolean("villageHL_volcanoIsland", "Villages", villageHL_volcanoIsland, "");
            villageHL_windyIsland = config.getBoolean("villageHL_windyIsland", "Villages", villageHL_windyIsland, "");
                
            //Border Biomes
            villageHL_shrubland = config.getBoolean("villageHL_shrubland", "Villages", villageHL_shrubland, "");
			
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
