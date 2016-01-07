package rtg.config.vanilla;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigVanilla
{
	public static Configuration config;
	
	public static final int biomeWeightMin = BiomeBase.MIN_BIOME_WEIGHT;
	public static final int biomeWeightMax = BiomeBase.MAX_BIOME_WEIGHT;
	public static final int biomeWeightDefault = BiomeBase.DEFAULT_BIOME_WEIGHT;
	
	public static boolean generateVanillaBiomes = true;
	
    public static boolean generateVanillaBeach = true;
    public static boolean generateVanillaBirchForest = true;
    public static boolean generateVanillaBirchForestHills = true;
    public static boolean generateVanillaBirchForestHillsM = true;
    public static boolean generateVanillaBirchForestM = true;
    public static boolean generateVanillaColdBeach = true;
    public static boolean generateVanillaColdTaiga = true;
    public static boolean generateVanillaColdTaigaHills = true;
    public static boolean generateVanillaColdTaigaM = true;
    public static boolean generateVanillaDeepOcean = true;
    public static boolean generateVanillaDesert = true;
    public static boolean generateVanillaDesertHills = true;
    public static boolean generateVanillaDesertM = true;
    public static boolean generateVanillaExtremeHills = true;
    public static boolean generateVanillaExtremeHillsM = true;
    public static boolean generateVanillaExtremeHillsPlus = true;
    public static boolean generateVanillaExtremeHillsPlusM = true;
    public static boolean generateVanillaFlowerForest = true;
    public static boolean generateVanillaForest = true;
    public static boolean generateVanillaForestHills = true;
    public static boolean generateVanillaFrozenRiver = true;
    public static boolean generateVanillaIceMountains = true;
    public static boolean generateVanillaIcePlains = true;
    public static boolean generateVanillaIcePlainsSpikes = true;
    public static boolean generateVanillaJungle = true;
    public static boolean generateVanillaJungleEdge = true;
    public static boolean generateVanillaJungleEdgeM = true;
    public static boolean generateVanillaJungleHills = true;
    public static boolean generateVanillaJungleM = true;
    public static boolean generateVanillaMegaSpruceTaiga = true;
    public static boolean generateVanillaMegaTaiga = true;
    public static boolean generateVanillaMegaTaigaHills = true;
    public static boolean generateVanillaMesa = true;
    public static boolean generateVanillaMesaBryce = true;
    public static boolean generateVanillaMesaPlateau = true;
    public static boolean generateVanillaMesaPlateau_F = true;
    public static boolean generateVanillaMesaPlateauFM = true;
    public static boolean generateVanillaMesaPlateauM = true;
    public static boolean generateVanillaMushroomIsland = false;
    public static boolean generateVanillaMushroomIslandShore = false;
    public static boolean generateVanillaOcean = true;
    public static boolean generateVanillaPlains = true;
    public static boolean generateVanillaRedwoodTaigaHills = true;
    public static boolean generateVanillaRiver = true;
    public static boolean generateVanillaRoofedForest = true;
    public static boolean generateVanillaRoofedForestM = true;
    public static boolean generateVanillaSavanna = true;
    public static boolean generateVanillaSavannaM = true;
    public static boolean generateVanillaSavannaPlateau = true;
    public static boolean generateVanillaSavannaPlateauM = true;
    public static boolean generateVanillaStoneBeach = true;
    public static boolean generateVanillaSunflowerPlains = true;
    public static boolean generateVanillaSwampland = true;
    public static boolean generateVanillaSwamplandM = true;
    public static boolean generateVanillaTaiga = true;
    public static boolean generateVanillaTaigaHills = true;
    public static boolean generateVanillaTaigaM = true;
	
    public static int weightVanillaBeach = (int)Math.floor((double)(biomeWeightDefault * 0.2));
    public static int weightVanillaBirchForest = biomeWeightDefault;
    public static int weightVanillaBirchForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaBirchForestHillsM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaBirchForestM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaColdBeach = (int)Math.floor((double)(biomeWeightDefault * 0.2));
    public static int weightVanillaColdTaiga = biomeWeightDefault;
    public static int weightVanillaColdTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaColdTaigaM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaDeepOcean = (int)Math.floor((double)(biomeWeightDefault * 2));
    public static int weightVanillaDesert = biomeWeightDefault;
    public static int weightVanillaDesertHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaDesertM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaExtremeHills = biomeWeightDefault;
    public static int weightVanillaExtremeHillsM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaExtremeHillsPlus = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaExtremeHillsPlusM = (int)Math.floor((double)(biomeWeightDefault * 0.2));
    public static int weightVanillaFlowerForest = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaForest = biomeWeightDefault;
    public static int weightVanillaForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaFrozenRiver = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaIceMountains = (int)Math.floor((double)(biomeWeightDefault * 0.2));
    public static int weightVanillaIcePlains = biomeWeightDefault;
    public static int weightVanillaIcePlainsSpikes = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaJungle = biomeWeightDefault;
    public static int weightVanillaJungleEdge = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaJungleEdgeM = (int)Math.floor((double)(biomeWeightDefault * 0.2));
    public static int weightVanillaJungleHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaJungleM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaMegaSpruceTaiga = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaMegaTaiga = biomeWeightDefault;
    public static int weightVanillaMegaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaMesa = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaBryce = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaPlateau_F = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaPlateauFM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaPlateauM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMushroomIsland = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMushroomIslandShore = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaOcean = (int)Math.floor((double)(biomeWeightDefault * 2));
    public static int weightVanillaPlains = biomeWeightDefault;
    public static int weightVanillaRedwoodTaigaHills = 0;
    public static int weightVanillaRiver = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaRoofedForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaRoofedForestM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaSavanna = biomeWeightDefault;
    public static int weightVanillaSavannaM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaSavannaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.6));
    public static int weightVanillaSavannaPlateauM = (int)Math.floor((double)(biomeWeightDefault * 0.2));
    public static int weightVanillaStoneBeach = (int)Math.floor((double)(biomeWeightDefault * 0.2));
    public static int weightVanillaSunflowerPlains = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaSwampland = biomeWeightDefault;
    public static int weightVanillaSwamplandM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    public static int weightVanillaTaiga = biomeWeightDefault;
    public static int weightVanillaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
    public static int weightVanillaTaigaM = (int)Math.floor((double)(biomeWeightDefault * 0.4));
    
    public static boolean villageVanillaBeach = true;
    public static boolean villageVanillaBirchForest = true;
    public static boolean villageVanillaBirchForestHills = true;
    public static boolean villageVanillaBirchForestHillsM = true;
    public static boolean villageVanillaBirchForestM = true;
    public static boolean villageVanillaColdBeach = true;
    public static boolean villageVanillaColdTaiga = true;
    public static boolean villageVanillaColdTaigaHills = true;
    public static boolean villageVanillaColdTaigaM = true;
    public static boolean villageVanillaDeepOcean = false;
    public static boolean villageVanillaDesert = true;
    public static boolean villageVanillaDesertHills = true;
    public static boolean villageVanillaDesertM = true;
    public static boolean villageVanillaExtremeHills = true;
    public static boolean villageVanillaExtremeHillsM = true;
    public static boolean villageVanillaExtremeHillsPlus = true;
    public static boolean villageVanillaExtremeHillsPlusM = true;
    public static boolean villageVanillaFlowerForest = true;
    public static boolean villageVanillaForest = true;
    public static boolean villageVanillaForestHills = true;
    //public static boolean villageVanillaFrozenRiver = true;
    public static boolean villageVanillaIceMountains = true;
    public static boolean villageVanillaIcePlains = true;
    public static boolean villageVanillaIcePlainsSpikes = true;
    public static boolean villageVanillaJungle = true;
    public static boolean villageVanillaJungleEdge = true;
    public static boolean villageVanillaJungleEdgeM = true;
    public static boolean villageVanillaJungleHills = true;
    public static boolean villageVanillaJungleM = true;
    public static boolean villageVanillaMegaSpruceTaiga = true;
    public static boolean villageVanillaMegaTaiga = true;
    public static boolean villageVanillaMegaTaigaHills = true;
    public static boolean villageVanillaMesa = true;
    public static boolean villageVanillaMesaBryce = true;
    public static boolean villageVanillaMesaPlateau = true;
    public static boolean villageVanillaMesaPlateau_F = true;
    public static boolean villageVanillaMesaPlateauFM = true;
    public static boolean villageVanillaMesaPlateauM = true;
    public static boolean villageVanillaMushroomIsland = true;
    public static boolean villageVanillaMushroomIslandShore = true;
    public static boolean villageVanillaOcean = false;
    public static boolean villageVanillaPlains = true;
    public static boolean villageVanillaRedwoodTaigaHills = true;
    //public static boolean villageVanillaRiver = true;
    public static boolean villageVanillaRoofedForest = true;
    public static boolean villageVanillaRoofedForestM = true;
    public static boolean villageVanillaSavanna = true;
    public static boolean villageVanillaSavannaM = true;
    public static boolean villageVanillaSavannaPlateau = true;
    public static boolean villageVanillaSavannaPlateauM = true;
    public static boolean villageVanillaStoneBeach = true;
    public static boolean villageVanillaSunflowerPlains = true;
    public static boolean villageVanillaSwampland = true;
    public static boolean villageVanillaSwamplandM = true;
    public static boolean villageVanillaTaiga = true;
    public static boolean villageVanillaTaigaHills = true;
    public static boolean villageVanillaTaigaM = true;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();

			generateVanillaBiomes = config.getBoolean("Allow vanilla biomes to generate", "Allow vanilla biomes", generateVanillaBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all vanilla biomes." + Configuration.NEW_LINE);

            generateVanillaBeach = config.getBoolean(formatConfig("generateVanillaBeach"), "Biomes", generateVanillaBeach, "");
            generateVanillaBirchForest = config.getBoolean(formatConfig("generateVanillaBirchForest"), "Biomes", generateVanillaBirchForest, "");
            generateVanillaBirchForestHills = config.getBoolean(formatConfig("generateVanillaBirchForestHills"), "Biomes", generateVanillaBirchForestHills, "");
            generateVanillaBirchForestHillsM = config.getBoolean(formatConfig("generateVanillaBirchForestHillsM"), "Biomes", generateVanillaBirchForestHillsM, "");
            generateVanillaBirchForestM = config.getBoolean(formatConfig("generateVanillaBirchForestM"), "Biomes", generateVanillaBirchForestM, "");
            generateVanillaColdBeach = config.getBoolean(formatConfig("generateVanillaColdBeach"), "Biomes", generateVanillaColdBeach, "");
            generateVanillaColdTaiga = config.getBoolean(formatConfig("generateVanillaColdTaiga"), "Biomes", generateVanillaColdTaiga, "");
            generateVanillaColdTaigaHills = config.getBoolean(formatConfig("generateVanillaColdTaigaHills"), "Biomes", generateVanillaColdTaigaHills, "");
            generateVanillaColdTaigaM = config.getBoolean(formatConfig("generateVanillaColdTaigaM"), "Biomes", generateVanillaColdTaigaM, "");
            generateVanillaDeepOcean = config.getBoolean(formatConfig("generateVanillaDeepOcean"), "Biomes", generateVanillaDeepOcean, "");
            generateVanillaDesert = config.getBoolean(formatConfig("generateVanillaDesert"), "Biomes", generateVanillaDesert, "");
            generateVanillaDesertHills = config.getBoolean(formatConfig("generateVanillaDesertHills"), "Biomes", generateVanillaDesertHills, "");
            generateVanillaDesertM = config.getBoolean(formatConfig("generateVanillaDesertM"), "Biomes", generateVanillaDesertM, "");
            generateVanillaExtremeHills = config.getBoolean(formatConfig("generateVanillaExtremeHills"), "Biomes", generateVanillaExtremeHills, "");
            generateVanillaExtremeHillsM = config.getBoolean(formatConfig("generateVanillaExtremeHillsM"), "Biomes", generateVanillaExtremeHillsM, "");
            generateVanillaExtremeHillsPlus = config.getBoolean(formatConfig("generateVanillaExtremeHillsPlus"), "Biomes", generateVanillaExtremeHillsPlus, "");
            generateVanillaExtremeHillsPlusM = config.getBoolean(formatConfig("generateVanillaExtremeHillsPlusM"), "Biomes", generateVanillaExtremeHillsPlusM, "");
            generateVanillaFlowerForest = config.getBoolean(formatConfig("generateVanillaFlowerForest"), "Biomes", generateVanillaFlowerForest, "");
            generateVanillaForest = config.getBoolean(formatConfig("generateVanillaForest"), "Biomes", generateVanillaForest, "");
            generateVanillaForestHills = config.getBoolean(formatConfig("generateVanillaForestHills"), "Biomes", generateVanillaForestHills, "");
            generateVanillaFrozenRiver = config.getBoolean(formatConfig("generateVanillaFrozenRiver"), "Biomes", generateVanillaFrozenRiver, "This setting is ignored. Frozen Rivers will always generate, even if set to false." + Configuration.NEW_LINE);
            generateVanillaIceMountains = config.getBoolean(formatConfig("generateVanillaIceMountains"), "Biomes", generateVanillaIceMountains, "");
            generateVanillaIcePlains = config.getBoolean(formatConfig("generateVanillaIcePlains"), "Biomes", generateVanillaIcePlains, "");
            generateVanillaIcePlainsSpikes = config.getBoolean(formatConfig("generateVanillaIcePlainsSpikes"), "Biomes", generateVanillaIcePlainsSpikes, "");
            generateVanillaJungle = config.getBoolean(formatConfig("generateVanillaJungle"), "Biomes", generateVanillaJungle, "");
            generateVanillaJungleEdge = config.getBoolean(formatConfig("generateVanillaJungleEdge"), "Biomes", generateVanillaJungleEdge, "");
            generateVanillaJungleEdgeM = config.getBoolean(formatConfig("generateVanillaJungleEdgeM"), "Biomes", generateVanillaJungleEdgeM, "");
            generateVanillaJungleHills = config.getBoolean(formatConfig("generateVanillaJungleHills"), "Biomes", generateVanillaJungleHills, "");
            generateVanillaJungleM = config.getBoolean(formatConfig("generateVanillaJungleM"), "Biomes", generateVanillaJungleM, "");
            generateVanillaMegaSpruceTaiga = config.getBoolean(formatConfig("generateVanillaMegaSpruceTaiga"), "Biomes", generateVanillaMegaSpruceTaiga, "");
            generateVanillaMegaTaiga = config.getBoolean(formatConfig("generateVanillaMegaTaiga"), "Biomes", generateVanillaMegaTaiga, "");
            generateVanillaMegaTaigaHills = config.getBoolean(formatConfig("generateVanillaMegaTaigaHills"), "Biomes", generateVanillaMegaTaigaHills, "");
            generateVanillaMesa = config.getBoolean(formatConfig("generateVanillaMesa"), "Biomes", generateVanillaMesa, "");
            generateVanillaMesaBryce = config.getBoolean(formatConfig("generateVanillaMesaBryce"), "Biomes", generateVanillaMesaBryce, "");
            generateVanillaMesaPlateau = config.getBoolean(formatConfig("generateVanillaMesaPlateau"), "Biomes", generateVanillaMesaPlateau, "");
            generateVanillaMesaPlateau_F = config.getBoolean(formatConfig("generateVanillaMesaPlateau_F"), "Biomes", generateVanillaMesaPlateau_F, "");
            generateVanillaMesaPlateauFM = config.getBoolean(formatConfig("generateVanillaMesaPlateauFM"), "Biomes", generateVanillaMesaPlateauFM, "");
            generateVanillaMesaPlateauM = config.getBoolean(formatConfig("generateVanillaMesaPlateauM"), "Biomes", generateVanillaMesaPlateauM, "");
            generateVanillaMushroomIsland = config.getBoolean(formatConfig("generateVanillaMushroomIsland"), "Biomes", generateVanillaMushroomIsland, "");
            generateVanillaMushroomIslandShore = config.getBoolean(formatConfig("generateVanillaMushroomIslandShore"), "Biomes", generateVanillaMushroomIslandShore, "");
            generateVanillaOcean = config.getBoolean(formatConfig("generateVanillaOcean"), "Biomes", generateVanillaOcean, "");
            generateVanillaPlains = config.getBoolean(formatConfig("generateVanillaPlains"), "Biomes", generateVanillaPlains, "");
            generateVanillaRedwoodTaigaHills = config.getBoolean(formatConfig("generateVanillaRedwoodTaigaHills"), "Biomes", generateVanillaRedwoodTaigaHills, "");
            generateVanillaRiver = config.getBoolean(formatConfig("generateVanillaRiver"), "Biomes", generateVanillaRiver, "This setting is ignored. Rivers will always generate, even if set to false." + Configuration.NEW_LINE);
            generateVanillaRoofedForest = config.getBoolean(formatConfig("generateVanillaRoofedForest"), "Biomes", generateVanillaRoofedForest, "");
            generateVanillaRoofedForestM = config.getBoolean(formatConfig("generateVanillaRoofedForestM"), "Biomes", generateVanillaRoofedForestM, "");
            generateVanillaSavanna = config.getBoolean(formatConfig("generateVanillaSavanna"), "Biomes", generateVanillaSavanna, "");
            generateVanillaSavannaM = config.getBoolean(formatConfig("generateVanillaSavannaM"), "Biomes", generateVanillaSavannaM, "");
            generateVanillaSavannaPlateau = config.getBoolean(formatConfig("generateVanillaSavannaPlateau"), "Biomes", generateVanillaSavannaPlateau, "");
            generateVanillaSavannaPlateauM = config.getBoolean(formatConfig("generateVanillaSavannaPlateauM"), "Biomes", generateVanillaSavannaPlateauM, "");
            generateVanillaStoneBeach = config.getBoolean(formatConfig("generateVanillaStoneBeach"), "Biomes", generateVanillaStoneBeach, "");
            generateVanillaSunflowerPlains = config.getBoolean(formatConfig("generateVanillaSunflowerPlains"), "Biomes", generateVanillaSunflowerPlains, "");
            generateVanillaSwampland = config.getBoolean(formatConfig("generateVanillaSwampland"), "Biomes", generateVanillaSwampland, "");
            generateVanillaSwamplandM = config.getBoolean(formatConfig("generateVanillaSwamplandM"), "Biomes", generateVanillaSwamplandM, "");
            generateVanillaTaiga = config.getBoolean(formatConfig("generateVanillaTaiga"), "Biomes", generateVanillaTaiga, "");
            generateVanillaTaigaHills = config.getBoolean(formatConfig("generateVanillaTaigaHills"), "Biomes", generateVanillaTaigaHills, "");
            generateVanillaTaigaM = config.getBoolean(formatConfig("generateVanillaTaigaM"), "Biomes", generateVanillaTaigaM, "");
            
            weightVanillaBeach = config.getInt(formatConfig("weightVanillaBeach"), "Weights", weightVanillaBeach, biomeWeightMin, biomeWeightMax, "");
            weightVanillaBirchForest = config.getInt(formatConfig("weightVanillaBirchForest"), "Weights", weightVanillaBirchForest, biomeWeightMin, biomeWeightMax, "");
            weightVanillaBirchForestHills = config.getInt(formatConfig("weightVanillaBirchForestHills"), "Weights", weightVanillaBirchForestHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaBirchForestHillsM = config.getInt(formatConfig("weightVanillaBirchForestHillsM"), "Weights", weightVanillaBirchForestHillsM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaBirchForestM = config.getInt(formatConfig("weightVanillaBirchForestM"), "Weights", weightVanillaBirchForestM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaColdBeach = config.getInt(formatConfig("weightVanillaColdBeach"), "Weights", weightVanillaColdBeach, biomeWeightMin, biomeWeightMax, "");
            weightVanillaColdTaiga = config.getInt(formatConfig("weightVanillaColdTaiga"), "Weights", weightVanillaColdTaiga, biomeWeightMin, biomeWeightMax, "");
            weightVanillaColdTaigaHills = config.getInt(formatConfig("weightVanillaColdTaigaHills"), "Weights", weightVanillaColdTaigaHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaColdTaigaM = config.getInt(formatConfig("weightVanillaColdTaigaM"), "Weights", weightVanillaColdTaigaM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaDeepOcean = config.getInt(formatConfig("weightVanillaDeepOcean"), "Weights", weightVanillaDeepOcean, biomeWeightMin, biomeWeightMax, "");
            weightVanillaDesert = config.getInt(formatConfig("weightVanillaDesert"), "Weights", weightVanillaDesert, biomeWeightMin, biomeWeightMax, "");
            weightVanillaDesertHills = config.getInt(formatConfig("weightVanillaDesertHills"), "Weights", weightVanillaDesertHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaDesertM = config.getInt(formatConfig("weightVanillaDesertM"), "Weights", weightVanillaDesertM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaExtremeHills = config.getInt(formatConfig("weightVanillaExtremeHills"), "Weights", weightVanillaExtremeHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaExtremeHillsM = config.getInt(formatConfig("weightVanillaExtremeHillsM"), "Weights", weightVanillaExtremeHillsM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaExtremeHillsPlus = config.getInt(formatConfig("weightVanillaExtremeHillsPlus"), "Weights", weightVanillaExtremeHillsPlus, biomeWeightMin, biomeWeightMax, "");
            weightVanillaExtremeHillsPlusM = config.getInt(formatConfig("weightVanillaExtremeHillsPlusM"), "Weights", weightVanillaExtremeHillsPlusM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaFlowerForest = config.getInt(formatConfig("weightVanillaFlowerForest"), "Weights", weightVanillaFlowerForest, biomeWeightMin, biomeWeightMax, "");
            weightVanillaForest = config.getInt(formatConfig("weightVanillaForest"), "Weights", weightVanillaForest, biomeWeightMin, biomeWeightMax, "");
            weightVanillaForestHills = config.getInt(formatConfig("weightVanillaForestHills"), "Weights", weightVanillaForestHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaFrozenRiver = config.getInt(formatConfig("weightVanillaFrozenRiver"), "Weights", weightVanillaFrozenRiver, biomeWeightMin, biomeWeightMax, "");
            weightVanillaIceMountains = config.getInt(formatConfig("weightVanillaIceMountains"), "Weights", weightVanillaIceMountains, biomeWeightMin, biomeWeightMax, "");
            weightVanillaIcePlains = config.getInt(formatConfig("weightVanillaIcePlains"), "Weights", weightVanillaIcePlains, biomeWeightMin, biomeWeightMax, "");
            weightVanillaIcePlainsSpikes = config.getInt(formatConfig("weightVanillaIcePlainsSpikes"), "Weights", weightVanillaIcePlainsSpikes, biomeWeightMin, biomeWeightMax, "");
            weightVanillaJungle = config.getInt(formatConfig("weightVanillaJungle"), "Weights", weightVanillaJungle, biomeWeightMin, biomeWeightMax, "");
            weightVanillaJungleEdge = config.getInt(formatConfig("weightVanillaJungleEdge"), "Weights", weightVanillaJungleEdge, biomeWeightMin, biomeWeightMax, "");
            weightVanillaJungleEdgeM = config.getInt(formatConfig("weightVanillaJungleEdgeM"), "Weights", weightVanillaJungleEdgeM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaJungleHills = config.getInt(formatConfig("weightVanillaJungleHills"), "Weights", weightVanillaJungleHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaJungleM = config.getInt(formatConfig("weightVanillaJungleM"), "Weights", weightVanillaJungleM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMegaSpruceTaiga = config.getInt(formatConfig("weightVanillaMegaSpruceTaiga"), "Weights", weightVanillaMegaSpruceTaiga, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMegaTaiga = config.getInt(formatConfig("weightVanillaMegaTaiga"), "Weights", weightVanillaMegaTaiga, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMegaTaigaHills = config.getInt(formatConfig("weightVanillaMegaTaigaHills"), "Weights", weightVanillaMegaTaigaHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesa = config.getInt(formatConfig("weightVanillaMesa"), "Weights", weightVanillaMesa, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaBryce = config.getInt(formatConfig("weightVanillaMesaBryce"), "Weights", weightVanillaMesaBryce, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaPlateau = config.getInt(formatConfig("weightVanillaMesaPlateau"), "Weights", weightVanillaMesaPlateau, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaPlateau_F = config.getInt(formatConfig("weightVanillaMesaPlateau_F"), "Weights", weightVanillaMesaPlateau_F, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaPlateauFM = config.getInt(formatConfig("weightVanillaMesaPlateauFM"), "Weights", weightVanillaMesaPlateauFM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaPlateauM = config.getInt(formatConfig("weightVanillaMesaPlateauM"), "Weights", weightVanillaMesaPlateauM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMushroomIsland = config.getInt(formatConfig("weightVanillaMushroomIsland"), "Weights", weightVanillaMushroomIsland, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMushroomIslandShore = config.getInt(formatConfig("weightVanillaMushroomIslandShore"), "Weights", weightVanillaMushroomIslandShore, biomeWeightMin, biomeWeightMax, "");
            weightVanillaOcean = config.getInt(formatConfig("weightVanillaOcean"), "Weights", weightVanillaOcean, biomeWeightMin, biomeWeightMax, "");
            weightVanillaPlains = config.getInt(formatConfig("weightVanillaPlains"), "Weights", weightVanillaPlains, biomeWeightMin, biomeWeightMax, "");
            weightVanillaRedwoodTaigaHills = config.getInt(formatConfig("weightVanillaRedwoodTaigaHills"), "Weights", weightVanillaRedwoodTaigaHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaRiver = config.getInt(formatConfig("weightVanillaRiver"), "Weights", weightVanillaRiver, biomeWeightMin, biomeWeightMax, "");
            weightVanillaRoofedForest = config.getInt(formatConfig("weightVanillaRoofedForest"), "Weights", weightVanillaRoofedForest, biomeWeightMin, biomeWeightMax, "");
            weightVanillaRoofedForestM = config.getInt(formatConfig("weightVanillaRoofedForestM"), "Weights", weightVanillaRoofedForestM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSavanna = config.getInt(formatConfig("weightVanillaSavanna"), "Weights", weightVanillaSavanna, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSavannaM = config.getInt(formatConfig("weightVanillaSavannaM"), "Weights", weightVanillaSavannaM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSavannaPlateau = config.getInt(formatConfig("weightVanillaSavannaPlateau"), "Weights", weightVanillaSavannaPlateau, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSavannaPlateauM = config.getInt(formatConfig("weightVanillaSavannaPlateauM"), "Weights", weightVanillaSavannaPlateauM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaStoneBeach = config.getInt(formatConfig("weightVanillaStoneBeach"), "Weights", weightVanillaStoneBeach, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSunflowerPlains = config.getInt(formatConfig("weightVanillaSunflowerPlains"), "Weights", weightVanillaSunflowerPlains, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSwampland = config.getInt(formatConfig("weightVanillaSwampland"), "Weights", weightVanillaSwampland, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSwamplandM = config.getInt(formatConfig("weightVanillaSwamplandM"), "Weights", weightVanillaSwamplandM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaTaiga = config.getInt(formatConfig("weightVanillaTaiga"), "Weights", weightVanillaTaiga, biomeWeightMin, biomeWeightMax, "");
            weightVanillaTaigaHills = config.getInt(formatConfig("weightVanillaTaigaHills"), "Weights", weightVanillaTaigaHills, biomeWeightMin, biomeWeightMax, "");
            weightVanillaTaigaM = config.getInt(formatConfig("weightVanillaTaigaM"), "Weights", weightVanillaTaigaM, biomeWeightMin, biomeWeightMax, "");
            
            villageVanillaBeach = config.getBoolean(formatConfig("villageVanillaBeach"), "Villages", villageVanillaBeach, "");
            villageVanillaBirchForest = config.getBoolean(formatConfig("villageVanillaBirchForest"), "Villages", villageVanillaBirchForest, "");
            villageVanillaBirchForestHills = config.getBoolean(formatConfig("villageVanillaBirchForestHills"), "Villages", villageVanillaBirchForestHills, "");
            villageVanillaBirchForestHillsM = config.getBoolean(formatConfig("villageVanillaBirchForestHillsM"), "Villages", villageVanillaBirchForestHillsM, "");
            villageVanillaBirchForestM = config.getBoolean(formatConfig("villageVanillaBirchForestM"), "Villages", villageVanillaBirchForestM, "");
            villageVanillaColdBeach = config.getBoolean(formatConfig("villageVanillaColdBeach"), "Villages", villageVanillaColdBeach, "");
            villageVanillaColdTaiga = config.getBoolean(formatConfig("villageVanillaColdTaiga"), "Villages", villageVanillaColdTaiga, "");
            villageVanillaColdTaigaHills = config.getBoolean(formatConfig("villageVanillaColdTaigaHills"), "Villages", villageVanillaColdTaigaHills, "");
            villageVanillaColdTaigaM = config.getBoolean(formatConfig("villageVanillaColdTaigaM"), "Villages", villageVanillaColdTaigaM, "");
            villageVanillaDeepOcean = config.getBoolean(formatConfig("villageVanillaDeepOcean"), "Villages", villageVanillaDeepOcean, "");
            villageVanillaDesert = config.getBoolean(formatConfig("villageVanillaDesert"), "Villages", villageVanillaDesert, "");
            villageVanillaDesertHills = config.getBoolean(formatConfig("villageVanillaDesertHills"), "Villages", villageVanillaDesertHills, "");
            villageVanillaDesertM = config.getBoolean(formatConfig("villageVanillaDesertM"), "Villages", villageVanillaDesertM, "");
            villageVanillaExtremeHills = config.getBoolean(formatConfig("villageVanillaExtremeHills"), "Villages", villageVanillaExtremeHills, "");
            villageVanillaExtremeHillsM = config.getBoolean(formatConfig("villageVanillaExtremeHillsM"), "Villages", villageVanillaExtremeHillsM, "");
            villageVanillaExtremeHillsPlus = config.getBoolean(formatConfig("villageVanillaExtremeHillsPlus"), "Villages", villageVanillaExtremeHillsPlus, "");
            villageVanillaExtremeHillsPlusM = config.getBoolean(formatConfig("villageVanillaExtremeHillsPlusM"), "Villages", villageVanillaExtremeHillsPlusM, "");
            villageVanillaFlowerForest = config.getBoolean(formatConfig("villageVanillaFlowerForest"), "Villages", villageVanillaFlowerForest, "");
            villageVanillaForest = config.getBoolean(formatConfig("villageVanillaForest"), "Villages", villageVanillaForest, "");
            villageVanillaForestHills = config.getBoolean(formatConfig("villageVanillaForestHills"), "Villages", villageVanillaForestHills, "");
            //villageVanillaFrozenRiver = config.getBoolean(formatConfig("villageVanillaFrozenRiver"), "Villages", villageVanillaFrozenRiver, "This setting is ignored. Frozen Rivers will always generate, even if set to false.");
            villageVanillaIceMountains = config.getBoolean(formatConfig("villageVanillaIceMountains"), "Villages", villageVanillaIceMountains, "");
            villageVanillaIcePlains = config.getBoolean(formatConfig("villageVanillaIcePlains"), "Villages", villageVanillaIcePlains, "");
            villageVanillaIcePlainsSpikes = config.getBoolean(formatConfig("villageVanillaIcePlainsSpikes"), "Villages", villageVanillaIcePlainsSpikes, "");
            villageVanillaJungle = config.getBoolean(formatConfig("villageVanillaJungle"), "Villages", villageVanillaJungle, "");
            villageVanillaJungleEdge = config.getBoolean(formatConfig("villageVanillaJungleEdge"), "Villages", villageVanillaJungleEdge, "");
            villageVanillaJungleEdgeM = config.getBoolean(formatConfig("villageVanillaJungleEdgeM"), "Villages", villageVanillaJungleEdgeM, "");
            villageVanillaJungleHills = config.getBoolean(formatConfig("villageVanillaJungleHills"), "Villages", villageVanillaJungleHills, "");
            villageVanillaJungleM = config.getBoolean(formatConfig("villageVanillaJungleM"), "Villages", villageVanillaJungleM, "");
            villageVanillaMegaSpruceTaiga = config.getBoolean(formatConfig("villageVanillaMegaSpruceTaiga"), "Villages", villageVanillaMegaSpruceTaiga, "");
            villageVanillaMegaTaiga = config.getBoolean(formatConfig("villageVanillaMegaTaiga"), "Villages", villageVanillaMegaTaiga, "");
            villageVanillaMegaTaigaHills = config.getBoolean(formatConfig("villageVanillaMegaTaigaHills"), "Villages", villageVanillaMegaTaigaHills, "");
            villageVanillaMesa = config.getBoolean(formatConfig("villageVanillaMesa"), "Villages", villageVanillaMesa, "");
            villageVanillaMesaBryce = config.getBoolean(formatConfig("villageVanillaMesaBryce"), "Villages", villageVanillaMesaBryce, "");
            villageVanillaMesaPlateau = config.getBoolean(formatConfig("villageVanillaMesaPlateau"), "Villages", villageVanillaMesaPlateau, "");
            villageVanillaMesaPlateau_F = config.getBoolean(formatConfig("villageVanillaMesaPlateau_F"), "Villages", villageVanillaMesaPlateau_F, "");
            villageVanillaMesaPlateauFM = config.getBoolean(formatConfig("villageVanillaMesaPlateauFM"), "Villages", villageVanillaMesaPlateauFM, "");
            villageVanillaMesaPlateauM = config.getBoolean(formatConfig("villageVanillaMesaPlateauM"), "Villages", villageVanillaMesaPlateauM, "");
            villageVanillaMushroomIsland = config.getBoolean(formatConfig("villageVanillaMushroomIsland"), "Villages", villageVanillaMushroomIsland, "");
            villageVanillaMushroomIslandShore = config.getBoolean(formatConfig("villageVanillaMushroomIslandShore"), "Villages", villageVanillaMushroomIslandShore, "");
            villageVanillaOcean = config.getBoolean(formatConfig("villageVanillaOcean"), "Villages", villageVanillaOcean, "");
            villageVanillaPlains = config.getBoolean(formatConfig("villageVanillaPlains"), "Villages", villageVanillaPlains, "");
            //villageVanillaRiver = config.getBoolean(formatConfig("villageVanillaRiver"), "Villages", villageVanillaRiver, "This setting is ignored. Rivers will always generate, even if set to false.");
            villageVanillaRedwoodTaigaHills = config.getBoolean(formatConfig("villageVanillaRedwoodTaigaHills"), "Villages", villageVanillaRedwoodTaigaHills, "");
            villageVanillaRoofedForest = config.getBoolean(formatConfig("villageVanillaRoofedForest"), "Villages", villageVanillaRoofedForest, "");
            villageVanillaRoofedForestM = config.getBoolean(formatConfig("villageVanillaRoofedForestM"), "Villages", villageVanillaRoofedForestM, "");
            villageVanillaSavanna = config.getBoolean(formatConfig("villageVanillaSavanna"), "Villages", villageVanillaSavanna, "");
            villageVanillaSavannaM = config.getBoolean(formatConfig("villageVanillaSavannaM"), "Villages", villageVanillaSavannaM, "");
            villageVanillaSavannaPlateau = config.getBoolean(formatConfig("villageVanillaSavannaPlateau"), "Villages", villageVanillaSavannaPlateau, "");
            villageVanillaSavannaPlateauM = config.getBoolean(formatConfig("villageVanillaSavannaPlateauM"), "Villages", villageVanillaSavannaPlateauM, "");
            villageVanillaStoneBeach = config.getBoolean(formatConfig("villageVanillaStoneBeach"), "Villages", villageVanillaStoneBeach, "");
            villageVanillaSunflowerPlains = config.getBoolean(formatConfig("villageVanillaSunflowerPlains"), "Villages", villageVanillaSunflowerPlains, "");
            villageVanillaSwampland = config.getBoolean(formatConfig("villageVanillaSwampland"), "Villages", villageVanillaSwampland, "");
            villageVanillaSwamplandM = config.getBoolean(formatConfig("villageVanillaSwamplandM"), "Villages", villageVanillaSwamplandM, "");
            villageVanillaTaiga = config.getBoolean(formatConfig("villageVanillaTaiga"), "Villages", villageVanillaTaiga, "");
            villageVanillaTaigaHills = config.getBoolean(formatConfig("villageVanillaTaigaHills"), "Villages", villageVanillaTaigaHills, "");
            villageVanillaTaigaM = config.getBoolean(formatConfig("villageVanillaTaigaM"), "Villages", villageVanillaTaigaM, "");
		}
		catch (Exception e) 
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading Vanilla configuration.");	
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
        
        returnString = StringUtils.replace(returnString, "VanillaMesaPlateau_F", "VanillaMesaPlateauF");
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
