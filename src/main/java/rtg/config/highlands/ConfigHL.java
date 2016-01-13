package rtg.config.highlands;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.highlands.config.BiomeConfigHL;
import rtg.config.BiomeConfigManager;
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
	public static boolean generateHLAlps = true;
	public static boolean generateHLAutumnForest = true;
	public static boolean generateHLBadlands = true;
	public static boolean generateHLBirchHills = true;
	public static boolean generateHLBog = true;
	public static boolean generateHLCliffs = true;
	public static boolean generateHLDesertMountains = true;
	public static boolean generateHLDunes = true;
	public static boolean generateHLEstuary = true;
	public static boolean generateHLFlyingMountains = true;
	public static boolean generateHLGlacier = true;
	public static boolean generateHLHighlandsB = true;
	public static boolean generateHLLowlands = true;
	public static boolean generateHLMeadow = true;
	public static boolean generateHLOutback = true;
	public static boolean generateHLPinelands = true;
	public static boolean generateHLRainforest = true;
	public static boolean generateHLRedwoodForest = true;
	public static boolean generateHLRockMountains = true;
	public static boolean generateHLSahel = true;
	public static boolean generateHLSavannah = true;
	public static boolean generateHLSnowMountains = true;
	public static boolean generateHLSteppe = true;
	public static boolean generateHLTallPineForest = true;
	public static boolean generateHLTropicalIslands = true;
	public static boolean generateHLTropics = true;
	public static boolean generateHLTundra = true;
	public static boolean generateHLWoodlands = true;
	public static boolean generateHLWoodsMountains = true;
    
    //Sub biomes
	public static boolean generateHLBaldHill = true;
	public static boolean generateHLCanyon = true;
	public static boolean generateHLDesertIsland = true;
	public static boolean generateHLForestIsland = true;
	public static boolean generateHLJungleIsland = true;
	public static boolean generateHLLake = true;
	public static boolean generateHLMesa = true;
	public static boolean generateHLOasis = true;
	public static boolean generateHLRockIsland = true;
	public static boolean generateHLSnowIsland = true;
	public static boolean generateHLValley = true;
	public static boolean generateHLVolcanoIsland = true;
	public static boolean generateHLWindyIsland = true;
    
    //Border biomes
	public static boolean generateHLShrubland = true;

	/*
	 * ######################################################################################
	 */
	
	//Main biomes
	public static int weightHLAlps = biomeWeightDefault;
	public static int weightHLAutumnForest = biomeWeightDefault;
	public static int weightHLBadlands = biomeWeightDefault;
	public static int weightHLBirchHills = biomeWeightDefault;
	public static int weightHLBog = biomeWeightDefault;
	public static int weightHLCliffs = biomeWeightDefault;
	public static int weightHLDesertMountains = biomeWeightDefault;
	public static int weightHLDunes = biomeWeightDefault;
	public static int weightHLEstuary = biomeWeightDefault;
	public static int weightHLFlyingMountains = biomeWeightDefault;
	public static int weightHLGlacier = biomeWeightDefault;
	public static int weightHLHighlandsB = biomeWeightDefault;
	public static int weightHLLowlands = biomeWeightDefault;
	public static int weightHLMeadow = biomeWeightDefault;
	public static int weightHLOutback = biomeWeightDefault;
	public static int weightHLPinelands = biomeWeightDefault;
	public static int weightHLRainforest = biomeWeightDefault;
	public static int weightHLRedwoodForest = biomeWeightDefault;
	public static int weightHLRockMountains = biomeWeightDefault;
	public static int weightHLSahel = biomeWeightDefault;
	public static int weightHLSavannah = biomeWeightDefault;
	public static int weightHLSnowMountains = biomeWeightDefault;
	public static int weightHLSteppe = biomeWeightDefault;
	public static int weightHLTallPineForest = biomeWeightDefault;
	public static int weightHLTropicalIslands = biomeWeightDefault;
	public static int weightHLTropics = biomeWeightDefault;
	public static int weightHLTundra = biomeWeightDefault;
	public static int weightHLWoodlands = biomeWeightDefault;
	public static int weightHLWoodsMountains = biomeWeightDefault;
    
    //Sub biomes
	public static int weightHLBaldHill = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLCanyon = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLDesertIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLForestIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLJungleIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLLake = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLMesa = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLOasis = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLRockIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLSnowIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLValley = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightHLVolcanoIsland = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightHLWindyIsland = (int)Math.floor((double)(biomeWeightDefault * 0.5));
    
    //Border biomes
	public static int weightHLShrubland = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	
    /*
     * ######################################################################################
     */
    
    //Main biomes
    public static boolean villageHLAlps = true;
    public static boolean villageHLAutumnForest = true;
    public static boolean villageHLBadlands = true;
    public static boolean villageHLBirchHills = true;
    public static boolean villageHLBog = true;
    public static boolean villageHLCliffs = true;
    public static boolean villageHLDesertMountains = true;
    public static boolean villageHLDunes = true;
    public static boolean villageHLEstuary = true;
    public static boolean villageHLFlyingMountains = true;
    public static boolean villageHLGlacier = true;
    public static boolean villageHLHighlandsB = true;
    public static boolean villageHLLowlands = true;
    public static boolean villageHLMeadow = true;
    public static boolean villageHLOutback = true;
    public static boolean villageHLPinelands = true;
    public static boolean villageHLRainforest = true;
    public static boolean villageHLRedwoodForest = true;
    public static boolean villageHLRockMountains = true;
    public static boolean villageHLSahel = true;
    public static boolean villageHLSavannah = true;
    public static boolean villageHLSnowMountains = true;
    public static boolean villageHLSteppe = true;
    public static boolean villageHLTallPineForest = true;
    public static boolean villageHLTropicalIslands = true;
    public static boolean villageHLTropics = true;
    public static boolean villageHLTundra = true;
    public static boolean villageHLWoodlands = true;
    public static boolean villageHLWoodsMountains = true;
    
    //Sub biomes
    public static boolean villageHLBaldHill = true;
    public static boolean villageHLCanyon = true;
    public static boolean villageHLDesertIsland = true;
    public static boolean villageHLForestIsland = true;
    public static boolean villageHLJungleIsland = true;
    public static boolean villageHLLake = true;
    public static boolean villageHLMesa = true;
    public static boolean villageHLOasis = true;
    public static boolean villageHLRockIsland = true;
    public static boolean villageHLSnowIsland = true;
    public static boolean villageHLValley = true;
    public static boolean villageHLVolcanoIsland = false;
    public static boolean villageHLWindyIsland = true;
    
    //Border biomes
    public static boolean villageHLShrubland = true;
    
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
			generateHLBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", true, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);
			
			//Generations
			
			/*
			 * ######################################################################################
			 */
			
			//Main biomes
			generateHLAlps = config.getBoolean(formatConfig("generateHLAlps"), "Biomes", generateHLAlps, "");
			generateHLAutumnForest = config.getBoolean(formatConfig("generateHLAutumnForest"), "Biomes", generateHLAutumnForest, "");
			generateHLBadlands = config.getBoolean(formatConfig("generateHLBadlands"), "Biomes", generateHLBadlands, "");
			generateHLBirchHills = config.getBoolean(formatConfig("generateHLBirchHills"), "Biomes", generateHLBirchHills, "");
			generateHLBog = config.getBoolean(formatConfig("generateHLBog"), "Biomes", generateHLBog, "");
			generateHLCliffs = config.getBoolean(formatConfig("generateHLCliffs"), "Biomes", generateHLCliffs, "");
			generateHLDesertMountains = config.getBoolean(formatConfig("generateHLDesertMountains"), "Biomes", generateHLDesertMountains, "");
			generateHLDunes = config.getBoolean(formatConfig("generateHLDunes"), "Biomes", generateHLDunes, "");
			generateHLEstuary = config.getBoolean(formatConfig("generateHLEstuary"), "Biomes", generateHLEstuary, "");
			generateHLFlyingMountains = config.getBoolean(formatConfig("generateHLFlyingMountains"), "Biomes", generateHLFlyingMountains, "");
			generateHLGlacier = config.getBoolean(formatConfig("generateHLGlacier"), "Biomes", generateHLGlacier, "");
			generateHLHighlandsB = config.getBoolean(formatConfig("generateHLHighlandsB"), "Biomes", generateHLHighlandsB, "");
			generateHLLowlands = config.getBoolean(formatConfig("generateHLLowlands"), "Biomes", generateHLLowlands, "");
			generateHLMeadow = config.getBoolean(formatConfig("generateHLMeadow"), "Biomes", generateHLMeadow, "");
			generateHLOutback = config.getBoolean(formatConfig("generateHLOutback"), "Biomes", generateHLOutback, "");
			generateHLPinelands = config.getBoolean(formatConfig("generateHLPinelands"), "Biomes", generateHLPinelands, "");
			generateHLRainforest = config.getBoolean(formatConfig("generateHLRainforest"), "Biomes", generateHLRainforest, "");
			generateHLRedwoodForest = config.getBoolean(formatConfig("generateHLRedwoodForest"), "Biomes", generateHLRedwoodForest, "");
			generateHLRockMountains = config.getBoolean(formatConfig("generateHLRockMountains"), "Biomes", generateHLRockMountains, "");
			generateHLSahel = config.getBoolean(formatConfig("generateHLSahel"), "Biomes", generateHLSahel, "");
			generateHLSavannah = config.getBoolean(formatConfig("generateHLSavannah"), "Biomes", generateHLSavannah, "");
			generateHLSnowMountains = config.getBoolean(formatConfig("generateHLSnowMountains"), "Biomes", generateHLSnowMountains, "");
			generateHLSteppe = config.getBoolean(formatConfig("generateHLSteppe"), "Biomes", generateHLSteppe, "");
			generateHLTallPineForest = config.getBoolean(formatConfig("generateHLTallPineForest"), "Biomes", generateHLTallPineForest, "");
			generateHLTropicalIslands = config.getBoolean(formatConfig("generateHLTropicalIslands"), "Biomes", generateHLTropicalIslands, "");
			generateHLTropics = config.getBoolean(formatConfig("generateHLTropics"), "Biomes", generateHLTropics, "");
			generateHLTundra = config.getBoolean(formatConfig("generateHLTundra"), "Biomes", generateHLTundra, "");
			generateHLWoodlands = config.getBoolean(formatConfig("generateHLWoodlands"), "Biomes", generateHLWoodlands, "");
			generateHLWoodsMountains = config.getBoolean(formatConfig("generateHLWoodsMountains"), "Biomes", generateHLWoodsMountains, "");

			//Sub biomes
			generateHLBaldHill = config.getBoolean(formatConfig("generateHLBaldHill"), "Biomes", generateHLBaldHill, "");
			generateHLCanyon = config.getBoolean(formatConfig("generateHLCanyon"), "Biomes", generateHLCanyon, "");
			generateHLDesertIsland = config.getBoolean(formatConfig("generateHLDesertIsland"), "Biomes", generateHLDesertIsland, "");
			generateHLForestIsland = config.getBoolean(formatConfig("generateHLForestIsland"), "Biomes", generateHLForestIsland, "");
			generateHLJungleIsland = config.getBoolean(formatConfig("generateHLJungleIsland"), "Biomes", generateHLJungleIsland, "");
			generateHLLake = config.getBoolean(formatConfig("generateHLLake"), "Biomes", generateHLLake, "");
			generateHLMesa = config.getBoolean(formatConfig("generateHLMesa"), "Biomes", generateHLMesa, "");
			generateHLOasis = config.getBoolean(formatConfig("generateHLOasis"), "Biomes", generateHLOasis, "");
			generateHLRockIsland = config.getBoolean(formatConfig("generateHLRockIsland"), "Biomes", generateHLRockIsland, "");
			generateHLSnowIsland = config.getBoolean(formatConfig("generateHLSnowIsland"), "Biomes", generateHLSnowIsland, "");
			generateHLValley = config.getBoolean(formatConfig("generateHLValley"), "Biomes", generateHLValley, "");
			generateHLVolcanoIsland = config.getBoolean(formatConfig("generateHLVolcanoIsland"), "Biomes", generateHLVolcanoIsland, "");
			generateHLWindyIsland = config.getBoolean(formatConfig("generateHLWindyIsland"), "Biomes", generateHLWindyIsland, "");
			    
			//Border Biomes
			generateHLShrubland = config.getBoolean(formatConfig("generateHLShrubland"), "Biomes", generateHLShrubland, "");
			
			/*
			 * ######################################################################################
			 */
			
			//Weights

			//Main biomes
			weightHLAlps = config.getInt(formatConfig("weightHLAlps"), "Weights", weightHLAlps, biomeWeightMin, biomeWeightMax, "");
			weightHLAutumnForest = config.getInt(formatConfig("weightHLAutumnForest"), "Weights", weightHLAutumnForest, biomeWeightMin, biomeWeightMax, "");
			weightHLBadlands = config.getInt(formatConfig("weightHLBadlands"), "Weights", weightHLBadlands, biomeWeightMin, biomeWeightMax, "");
			weightHLBirchHills = config.getInt(formatConfig("weightHLBirchHills"), "Weights", weightHLBirchHills, biomeWeightMin, biomeWeightMax, "");
			weightHLBog = config.getInt(formatConfig("weightHLBog"), "Weights", weightHLBog, biomeWeightMin, biomeWeightMax, "");
			weightHLCliffs = config.getInt(formatConfig("weightHLCliffs"), "Weights", weightHLCliffs, biomeWeightMin, biomeWeightMax, "");
			weightHLDesertMountains = config.getInt(formatConfig("weightHLDesertMountains"), "Weights", weightHLDesertMountains, biomeWeightMin, biomeWeightMax, "");
			weightHLDunes = config.getInt(formatConfig("weightHLDunes"), "Weights", weightHLDunes, biomeWeightMin, biomeWeightMax, "");
			weightHLEstuary = config.getInt(formatConfig("weightHLEstuary"), "Weights", weightHLEstuary, biomeWeightMin, biomeWeightMax, "");
			weightHLFlyingMountains = config.getInt(formatConfig("weightHLFlyingMountains"), "Weights", weightHLFlyingMountains, biomeWeightMin, biomeWeightMax, "");
			weightHLGlacier = config.getInt(formatConfig("weightHLGlacier"), "Weights", weightHLGlacier, biomeWeightMin, biomeWeightMax, "");
			weightHLHighlandsB = config.getInt(formatConfig("weightHLHighlandsB"), "Weights", weightHLHighlandsB, biomeWeightMin, biomeWeightMax, "");
			weightHLLowlands = config.getInt(formatConfig("weightHLLowlands"), "Weights", weightHLLowlands, biomeWeightMin, biomeWeightMax, "");
			weightHLMeadow = config.getInt(formatConfig("weightHLMeadow"), "Weights", weightHLMeadow, biomeWeightMin, biomeWeightMax, "");
			weightHLOutback = config.getInt(formatConfig("weightHLOutback"), "Weights", weightHLOutback, biomeWeightMin, biomeWeightMax, "");
			weightHLPinelands = config.getInt(formatConfig("weightHLPinelands"), "Weights", weightHLPinelands, biomeWeightMin, biomeWeightMax, "");
			weightHLRainforest = config.getInt(formatConfig("weightHLRainforest"), "Weights", weightHLRainforest, biomeWeightMin, biomeWeightMax, "");
			weightHLRedwoodForest = config.getInt(formatConfig("weightHLRedwoodForest"), "Weights", weightHLRedwoodForest, biomeWeightMin, biomeWeightMax, "");
			weightHLRockMountains = config.getInt(formatConfig("weightHLRockMountains"), "Weights", weightHLRockMountains, biomeWeightMin, biomeWeightMax, "");
			weightHLSahel = config.getInt(formatConfig("weightHLSahel"), "Weights", weightHLSahel, biomeWeightMin, biomeWeightMax, "");
			weightHLSavannah = config.getInt(formatConfig("weightHLSavannah"), "Weights", weightHLSavannah, biomeWeightMin, biomeWeightMax, "");
			weightHLSnowMountains = config.getInt(formatConfig("weightHLSnowMountains"), "Weights", weightHLSnowMountains, biomeWeightMin, biomeWeightMax, "");
			weightHLSteppe = config.getInt(formatConfig("weightHLSteppe"), "Weights", weightHLSteppe, biomeWeightMin, biomeWeightMax, "");
			weightHLTallPineForest = config.getInt(formatConfig("weightHLTallPineForest"), "Weights", weightHLTallPineForest, biomeWeightMin, biomeWeightMax, "");
			weightHLTropicalIslands = config.getInt(formatConfig("weightHLTropicalIslands"), "Weights", weightHLTropicalIslands, biomeWeightMin, biomeWeightMax, "");
			weightHLTropics = config.getInt(formatConfig("weightHLTropics"), "Weights", weightHLTropics, biomeWeightMin, biomeWeightMax, "");
			weightHLTundra = config.getInt(formatConfig("weightHLTundra"), "Weights", weightHLTundra, biomeWeightMin, biomeWeightMax, "");
			weightHLWoodlands = config.getInt(formatConfig("weightHLWoodlands"), "Weights", weightHLWoodlands, biomeWeightMin, biomeWeightMax, "");
			weightHLWoodsMountains = config.getInt(formatConfig("weightHLWoodsMountains"), "Weights", weightHLWoodsMountains, biomeWeightMin, biomeWeightMax, "");
			    
			//Sub biomes
			weightHLBaldHill = config.getInt(formatConfig("weightHLBaldHill"), "Weights", weightHLBaldHill, biomeWeightMin, biomeWeightMax, "");
			weightHLCanyon = config.getInt(formatConfig("weightHLCanyon"), "Weights", weightHLCanyon, biomeWeightMin, biomeWeightMax, "");
			weightHLDesertIsland = config.getInt(formatConfig("weightHLDesertIsland"), "Weights", weightHLDesertIsland, biomeWeightMin, biomeWeightMax, "");
			weightHLForestIsland = config.getInt(formatConfig("weightHLForestIsland"), "Weights", weightHLForestIsland, biomeWeightMin, biomeWeightMax, "");
			weightHLJungleIsland = config.getInt(formatConfig("weightHLJungleIsland"), "Weights", weightHLJungleIsland, biomeWeightMin, biomeWeightMax, "");
			weightHLLake = config.getInt(formatConfig("weightHLLake"), "Weights", weightHLLake, biomeWeightMin, biomeWeightMax, "");
			weightHLMesa = config.getInt(formatConfig("weightHLMesa"), "Weights", weightHLMesa, biomeWeightMin, biomeWeightMax, "");
			weightHLOasis = config.getInt(formatConfig("weightHLOasis"), "Weights", weightHLOasis, biomeWeightMin, biomeWeightMax, "");
			weightHLRockIsland = config.getInt(formatConfig("weightHLRockIsland"), "Weights", weightHLRockIsland, biomeWeightMin, biomeWeightMax, "");
			weightHLSnowIsland = config.getInt(formatConfig("weightHLSnowIsland"), "Weights", weightHLSnowIsland, biomeWeightMin, biomeWeightMax, "");
			weightHLValley = config.getInt(formatConfig("weightHLValley"), "Weights", weightHLValley, biomeWeightMin, biomeWeightMax, "");
			weightHLVolcanoIsland = config.getInt(formatConfig("weightHLVolcanoIsland"), "Weights", weightHLVolcanoIsland, biomeWeightMin, biomeWeightMax, "");
			weightHLWindyIsland = config.getInt(formatConfig("weightHLWindyIsland"), "Weights", weightHLWindyIsland, biomeWeightMin, biomeWeightMax, "");
			    
			//Border biomes
			weightHLShrubland = config.getInt(formatConfig("weightHLShrubland"), "Weights", weightHLShrubland, biomeWeightMin, biomeWeightMax, "");
			
            /*
             * ######################################################################################
             */
			
            //Villages

            //Main biomes
            villageHLAlps = config.getBoolean(formatConfig("villageHLAlps"), "Villages", villageHLAlps, "");
            villageHLAutumnForest = config.getBoolean(formatConfig("villageHLAutumnForest"), "Villages", villageHLAutumnForest, "");
            villageHLBadlands = config.getBoolean(formatConfig("villageHLBadlands"), "Villages", villageHLBadlands, "");
            villageHLBirchHills = config.getBoolean(formatConfig("villageHLBirchHills"), "Villages", villageHLBirchHills, "");
            villageHLBog = config.getBoolean(formatConfig("villageHLBog"), "Villages", villageHLBog, "");
            villageHLCliffs = config.getBoolean(formatConfig("villageHLCliffs"), "Villages", villageHLCliffs, "");
            villageHLDesertMountains = config.getBoolean(formatConfig("villageHLDesertMountains"), "Villages", villageHLDesertMountains, "");
            villageHLDunes = config.getBoolean(formatConfig("villageHLDunes"), "Villages", villageHLDunes, "");
            villageHLEstuary = config.getBoolean(formatConfig("villageHLEstuary"), "Villages", villageHLEstuary, "");
            villageHLFlyingMountains = config.getBoolean(formatConfig("villageHLFlyingMountains"), "Villages", villageHLFlyingMountains, "");
            villageHLGlacier = config.getBoolean(formatConfig("villageHLGlacier"), "Villages", villageHLGlacier, "");
            villageHLHighlandsB = config.getBoolean(formatConfig("villageHLHighlandsB"), "Villages", villageHLHighlandsB, "");
            villageHLLowlands = config.getBoolean(formatConfig("villageHLLowlands"), "Villages", villageHLLowlands, "");
            villageHLMeadow = config.getBoolean(formatConfig("villageHLMeadow"), "Villages", villageHLMeadow, "");
            villageHLOutback = config.getBoolean(formatConfig("villageHLOutback"), "Villages", villageHLOutback, "");
            villageHLPinelands = config.getBoolean(formatConfig("villageHLPinelands"), "Villages", villageHLPinelands, "");
            villageHLRainforest = config.getBoolean(formatConfig("villageHLRainforest"), "Villages", villageHLRainforest, "");
            villageHLRedwoodForest = config.getBoolean(formatConfig("villageHLRedwoodForest"), "Villages", villageHLRedwoodForest, "");
            villageHLRockMountains = config.getBoolean(formatConfig("villageHLRockMountains"), "Villages", villageHLRockMountains, "");
            villageHLSahel = config.getBoolean(formatConfig("villageHLSahel"), "Villages", villageHLSahel, "");
            villageHLSavannah = config.getBoolean(formatConfig("villageHLSavannah"), "Villages", villageHLSavannah, "");
            villageHLSnowMountains = config.getBoolean(formatConfig("villageHLSnowMountains"), "Villages", villageHLSnowMountains, "");
            villageHLSteppe = config.getBoolean(formatConfig("villageHLSteppe"), "Villages", villageHLSteppe, "");
            villageHLTallPineForest = config.getBoolean(formatConfig("villageHLTallPineForest"), "Villages", villageHLTallPineForest, "");
            villageHLTropicalIslands = config.getBoolean(formatConfig("villageHLTropicalIslands"), "Villages", villageHLTropicalIslands, "");
            villageHLTropics = config.getBoolean(formatConfig("villageHLTropics"), "Villages", villageHLTropics, "");
            villageHLTundra = config.getBoolean(formatConfig("villageHLTundra"), "Villages", villageHLTundra, "");
            villageHLWoodlands = config.getBoolean(formatConfig("villageHLWoodlands"), "Villages", villageHLWoodlands, "");
            villageHLWoodsMountains = config.getBoolean(formatConfig("villageHLWoodsMountains"), "Villages", villageHLWoodsMountains, "");

            //Sub biomes
            villageHLBaldHill = config.getBoolean(formatConfig("villageHLBaldHill"), "Villages", villageHLBaldHill, "");
            villageHLCanyon = config.getBoolean(formatConfig("villageHLCanyon"), "Villages", villageHLCanyon, "");
            villageHLDesertIsland = config.getBoolean(formatConfig("villageHLDesertIsland"), "Villages", villageHLDesertIsland, "");
            villageHLForestIsland = config.getBoolean(formatConfig("villageHLForestIsland"), "Villages", villageHLForestIsland, "");
            villageHLJungleIsland = config.getBoolean(formatConfig("villageHLJungleIsland"), "Villages", villageHLJungleIsland, "");
            villageHLLake = config.getBoolean(formatConfig("villageHLLake"), "Villages", villageHLLake, "");
            villageHLMesa = config.getBoolean(formatConfig("villageHLMesa"), "Villages", villageHLMesa, "");
            villageHLOasis = config.getBoolean(formatConfig("villageHLOasis"), "Villages", villageHLOasis, "");
            villageHLRockIsland = config.getBoolean(formatConfig("villageHLRockIsland"), "Villages", villageHLRockIsland, "");
            villageHLSnowIsland = config.getBoolean(formatConfig("villageHLSnowIsland"), "Villages", villageHLSnowIsland, "");
            villageHLValley = config.getBoolean(formatConfig("villageHLValley"), "Villages", villageHLValley, "");
            villageHLVolcanoIsland = config.getBoolean(formatConfig("villageHLVolcanoIsland"), "Villages", villageHLVolcanoIsland, "");
            villageHLWindyIsland = config.getBoolean(formatConfig("villageHLWindyIsland"), "Villages", villageHLWindyIsland, "");
                
            //Border Biomes
            villageHLShrubland = config.getBoolean(formatConfig("villageHLShrubland"), "Villages", villageHLShrubland, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigHL.getBiomeConfigs(), config);
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
	
    private static String formatConfig(String s)
    {
        String returnString = s;        
        
        returnString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(returnString), " ");

        if (s.startsWith("generate")) {
            returnString = StringUtils.replace(returnString, "generate", "Generate", 1);
        }
        else if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        else if (s.startsWith("weight")) {
            returnString = StringUtils.replace(returnString, "weight", "Weight of", 1);
        }
        
        return returnString;
    }
}
