package rtg.config.vanilla;

import java.io.File;

import org.apache.logging.log4j.Level;

import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
	public static boolean generateVanillaColdBeach = true;
	public static boolean generateVanillaColdTaiga = true;
	public static boolean generateVanillaColdTaigaHills = true;
	public static boolean generateVanillaDeepOcean = true;
	public static boolean generateVanillaDesert = true;
	public static boolean generateVanillaDesertHills = true;
	public static boolean generateVanillaExtremeHills = true;
	public static boolean generateVanillaExtremeHillsPlus = true;
	public static boolean generateVanillaForest = true;
	public static boolean generateVanillaForestHills = true;
	public static boolean generateVanillaFrozenRiver = true;
	public static boolean generateVanillaIceMountains = true;
	public static boolean generateVanillaIcePlains = true;
	public static boolean generateVanillaJungle = true;
	public static boolean generateVanillaJungleEdge = true;
	public static boolean generateVanillaJungleHills = true;
	public static boolean generateVanillaMegaTaiga = true;
	public static boolean generateVanillaMegaTaigaHills = true;
	public static boolean generateVanillaMesa = true;
	public static boolean generateVanillaMesaPlateau = true;
	public static boolean generateVanillaMesaPlateau_F = true;
	public static boolean generateVanillaMushroomIsland = false;
	public static boolean generateVanillaMushroomIslandShore = false;
	public static boolean generateVanillaOcean = true;
	public static boolean generateVanillaPlains = true;
	public static boolean generateVanillaRiver = true;
	public static boolean generateVanillaRoofedForest = true;
	public static boolean generateVanillaSavanna = true;
	public static boolean generateVanillaSavannaPlateau = true;
	public static boolean generateVanillaStoneBeach = true;
	public static boolean generateVanillaSwampland = true;
	public static boolean generateVanillaTaiga = true;
	public static boolean generateVanillaTaigaHills = true;
	
    public static boolean generateVanillaSunflowerPlains = true;
    public static boolean generateVanillaDesertM = true;
    public static boolean generateVanillaExtremeHillsM = true;
    public static boolean generateVanillaFlowerForest = true;
    public static boolean generateVanillaTaigaM = true;
    public static boolean generateVanillaSwamplandM = true;
    public static boolean generateVanillaIcePlainsSpikes = true;
    public static boolean generateVanillaJungleM = true;
    public static boolean generateVanillaJungleEdgeM = true;
    public static boolean generateVanillaBirchForestM = true;
    public static boolean generateVanillaBirchForestHillsM = true;
    public static boolean generateVanillaRoofedForestM = true;
    public static boolean generateVanillaColdTaigaM = true;
    public static boolean generateVanillaMegaSpruceTaiga = true;
    public static boolean generateVanillaExtremeHillsPlusM = true;
    public static boolean generateVanillaSavannaM = true;
    public static boolean generateVanillaSavannaPlateauM = true;
    public static boolean generateVanillaMesaBryce = true;
    public static boolean generateVanillaMesaPlateauFM = true;
    public static boolean generateVanillaMesaPlateauM = true;
	
    
	public static int weightVanillaBeach = (int)Math.floor((double)(biomeWeightDefault * 0.4));
	public static int weightVanillaBirchForest = biomeWeightDefault;
	public static int weightVanillaBirchForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaColdBeach = (int)Math.floor((double)(biomeWeightDefault * 0.4));
	public static int weightVanillaColdTaiga = biomeWeightDefault;
	public static int weightVanillaColdTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaDeepOcean = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaDesert = biomeWeightDefault;
	public static int weightVanillaDesertHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaExtremeHills = biomeWeightDefault;
	public static int weightVanillaExtremeHillsPlus = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaForest = biomeWeightDefault;
	public static int weightVanillaForestHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaFrozenRiver = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaIceMountains = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaIcePlains = biomeWeightDefault;
	public static int weightVanillaJungle = biomeWeightDefault;
	public static int weightVanillaJungleEdge = (int)Math.floor((double)(biomeWeightDefault * 0.5));
	public static int weightVanillaJungleHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaMegaTaiga = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaMegaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.3));
	public static int weightVanillaMesa = (int)Math.floor((double)(biomeWeightDefault * 0.2));
	public static int weightVanillaMesaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaMesaPlateau_F = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaMushroomIsland = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaMushroomIslandShore = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaOcean = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaPlains = biomeWeightDefault;
	public static int weightVanillaRiver = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	public static int weightVanillaRoofedForest = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaSavanna = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	public static int weightVanillaSavannaPlateau = (int)Math.floor((double)(biomeWeightDefault * 0.7));
	public static int weightVanillaStoneBeach = (int)Math.floor((double)(biomeWeightDefault * 0.4));
	public static int weightVanillaSwampland = biomeWeightDefault;
	public static int weightVanillaTaiga = biomeWeightDefault;
	public static int weightVanillaTaigaHills = (int)Math.floor((double)(biomeWeightDefault * 0.8));
	
    public static int weightVanillaSunflowerPlains = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaDesertM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaExtremeHillsM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaFlowerForest = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaTaigaM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaSwamplandM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaIcePlainsSpikes = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaJungleM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaJungleEdgeM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaBirchForestM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaBirchForestHillsM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaRoofedForestM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaColdTaigaM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMegaSpruceTaiga = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaExtremeHillsPlusM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaSavannaM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaSavannaPlateauM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaBryce = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaPlateauFM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
    public static int weightVanillaMesaPlateauM = (int)Math.floor((double)(biomeWeightDefault * 0.1));
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();

			generateVanillaBiomes = config.getBoolean("Allow vanilla biomes to generate", "Allow vanilla biomes", generateVanillaBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod.");

			generateVanillaBeach = config.getBoolean("generateVanillaBeach", "Biomes", generateVanillaBeach, "");
			generateVanillaStoneBeach = config.getBoolean("generateVanillaStoneBeach", "Biomes", generateVanillaStoneBeach, "");
			generateVanillaColdBeach = config.getBoolean("generateVanillaColdBeach", "Biomes", generateVanillaColdBeach, "");
			generateVanillaDesert = config.getBoolean("generateVanillaDesert", "Biomes", generateVanillaDesert, "");
			generateVanillaDesertHills = config.getBoolean("generateVanillaDesertHills", "Biomes", generateVanillaDesertHills, "");
			generateVanillaExtremeHills = config.getBoolean("generateVanillaExtremeHills", "Biomes", generateVanillaExtremeHills, "");
			generateVanillaExtremeHillsPlus = config.getBoolean("generateVanillaExtremeHillsPlus", "Biomes", generateVanillaExtremeHillsPlus, "");
			generateVanillaForest = config.getBoolean("generateVanillaForest", "Biomes", generateVanillaForest, "");
			generateVanillaForestHills = config.getBoolean("generateVanillaForestHills", "Biomes", generateVanillaForestHills, "");
			generateVanillaBirchForest = config.getBoolean("generateVanillaBirchForest", "Biomes", generateVanillaBirchForest, "");
			generateVanillaBirchForestHills = config.getBoolean("generateVanillaBirchForestHills", "Biomes", generateVanillaBirchForestHills, "");
			generateVanillaRoofedForest = config.getBoolean("generateVanillaRoofedForest", "Biomes", generateVanillaRoofedForest, "");
			generateVanillaIcePlains = config.getBoolean("generateVanillaIcePlains", "Biomes", generateVanillaIcePlains, "");
			generateVanillaIceMountains = config.getBoolean("generateVanillaIceMountains", "Biomes", generateVanillaIceMountains, "");
			generateVanillaJungle = config.getBoolean("generateVanillaJungle", "Biomes", generateVanillaJungle, "");
			generateVanillaJungleHills = config.getBoolean("generateVanillaJungleHills", "Biomes", generateVanillaJungleHills, "");
			generateVanillaJungleEdge = config.getBoolean("generateVanillaJungleEdge", "Biomes", generateVanillaJungleEdge, "");
			generateVanillaMesa = config.getBoolean("generateVanillaMesa", "Biomes", generateVanillaMesa, "");
			generateVanillaMesaPlateau = config.getBoolean("generateVanillaMesaPlateau", "Biomes", generateVanillaMesaPlateau, "");
			generateVanillaMesaPlateau_F = config.getBoolean("generateVanillaMesaPlateau_F", "Biomes", generateVanillaMesaPlateau_F, "");
			generateVanillaMushroomIsland = config.getBoolean("generateVanillaMushroomIsland", "Biomes", generateVanillaMushroomIsland, "");
			generateVanillaMushroomIslandShore = config.getBoolean("generateVanillaMushroomIslandShore", "Biomes", generateVanillaMushroomIslandShore, "");
			generateVanillaOcean = config.getBoolean("generateVanillaOcean", "Biomes", generateVanillaOcean, "");
			generateVanillaDeepOcean = config.getBoolean("generateVanillaDeepOcean", "Biomes", generateVanillaDeepOcean, "");
			generateVanillaPlains = config.getBoolean("generateVanillaPlains", "Biomes", generateVanillaPlains, "");
			generateVanillaRiver = config.getBoolean("generateVanillaRiver", "Biomes", generateVanillaRiver, "This setting is ignored. Rivers will always generate, even if set to false.");
			generateVanillaFrozenRiver = config.getBoolean("generateVanillaFrozenRiver", "Biomes", generateVanillaFrozenRiver, "This setting is ignored. Frozen Rivers will always generate, even if set to false.");
			generateVanillaSavanna = config.getBoolean("generateVanillaSavanna", "Biomes", generateVanillaSavanna, "");
			generateVanillaSavannaPlateau = config.getBoolean("generateVanillaSavannaPlateau", "Biomes", generateVanillaSavannaPlateau, "");
			generateVanillaSwampland = config.getBoolean("generateVanillaSwampland", "Biomes", generateVanillaSwampland, "");
			generateVanillaTaiga = config.getBoolean("generateVanillaTaiga", "Biomes", generateVanillaTaiga, "");
			generateVanillaTaigaHills = config.getBoolean("generateVanillaTaigaHills", "Biomes", generateVanillaTaigaHills, "");
			generateVanillaColdTaiga = config.getBoolean("generateVanillaColdTaiga", "Biomes", generateVanillaColdTaiga, "");
			generateVanillaColdTaigaHills = config.getBoolean("generateVanillaColdTaigaHills", "Biomes", generateVanillaColdTaigaHills, "");
			generateVanillaMegaTaiga = config.getBoolean("generateVanillaMegaTaiga", "Biomes", generateVanillaMegaTaiga, "");
			generateVanillaMegaTaigaHills = config.getBoolean("generateVanillaMegaTaigaHills", "Biomes", generateVanillaMegaTaigaHills, "");
			
            generateVanillaSunflowerPlains = config.getBoolean("generateVanillaSunflowerPlains", "Biomes", generateVanillaSunflowerPlains, "");
            generateVanillaDesertM = config.getBoolean("generateVanillaDesertM", "Biomes", generateVanillaDesertM, "");
            generateVanillaExtremeHillsM = config.getBoolean("generateVanillaExtremeHillsM", "Biomes", generateVanillaExtremeHillsM, "");
            generateVanillaFlowerForest = config.getBoolean("generateVanillaFlowerForest", "Biomes", generateVanillaFlowerForest, "");
            generateVanillaTaigaM = config.getBoolean("generateVanillaTaigaM", "Biomes", generateVanillaTaigaM, "");
            generateVanillaSwamplandM = config.getBoolean("generateVanillaSwamplandM", "Biomes", generateVanillaSwamplandM, "");
            generateVanillaIcePlainsSpikes = config.getBoolean("generateVanillaIcePlainsSpikes", "Biomes", generateVanillaIcePlainsSpikes, "");
            generateVanillaJungleM = config.getBoolean("generateVanillaJungleM", "Biomes", generateVanillaJungleM, "");
            generateVanillaJungleEdgeM = config.getBoolean("generateVanillaJungleEdgeM", "Biomes", generateVanillaJungleEdgeM, "");
            generateVanillaBirchForestM = config.getBoolean("generateVanillaBirchForestM", "Biomes", generateVanillaBirchForestM, "");
            generateVanillaBirchForestHillsM = config.getBoolean("generateVanillaBirchForestHillsM", "Biomes", generateVanillaBirchForestHillsM, "");
            generateVanillaRoofedForestM = config.getBoolean("generateVanillaRoofedForestM", "Biomes", generateVanillaRoofedForestM, "");
            generateVanillaColdTaigaM = config.getBoolean("generateVanillaColdTaigaM", "Biomes", generateVanillaColdTaigaM, "");
            generateVanillaMegaSpruceTaiga = config.getBoolean("generateVanillaMegaSpruceTaiga", "Biomes", generateVanillaMegaSpruceTaiga, "");
            generateVanillaExtremeHillsPlusM = config.getBoolean("generateVanillaExtremeHillsPlusM", "Biomes", generateVanillaExtremeHillsPlusM, "");
            generateVanillaSavannaM = config.getBoolean("generateVanillaSavannaM", "Biomes", generateVanillaSavannaM, "");
            generateVanillaSavannaPlateauM = config.getBoolean("generateVanillaSavannaPlateauM", "Biomes", generateVanillaSavannaPlateauM, "");
            generateVanillaMesaBryce = config.getBoolean("generateVanillaMesaBryce", "Biomes", generateVanillaMesaBryce, "");
            generateVanillaMesaPlateauFM = config.getBoolean("generateVanillaMesaPlateauFM", "Biomes", generateVanillaMesaPlateauFM, "");
            generateVanillaMesaPlateauM = config.getBoolean("generateVanillaMesaPlateauM", "Biomes", generateVanillaMesaPlateauM, "");

            
			weightVanillaBeach = config.getInt("weightVanillaBeach", "Weights", weightVanillaBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaBirchForest = config.getInt("weightVanillaBirchForest", "Weights", weightVanillaBirchForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaBirchForestHills = config.getInt("weightVanillaBirchForestHills", "Weights", weightVanillaBirchForestHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdBeach = config.getInt("weightVanillaColdBeach", "Weights", weightVanillaColdBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdTaiga = config.getInt("weightVanillaColdTaiga", "Weights", weightVanillaColdTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaColdTaigaHills = config.getInt("weightVanillaColdTaigaHills", "Weights", weightVanillaColdTaigaHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDeepOcean = config.getInt("weightVanillaDeepOcean", "Weights", weightVanillaDeepOcean, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDesert = config.getInt("weightVanillaDesert", "Weights", weightVanillaDesert, biomeWeightMin, biomeWeightMax, "");
			weightVanillaDesertHills = config.getInt("weightVanillaDesertHills", "Weights", weightVanillaDesertHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaExtremeHills = config.getInt("weightVanillaExtremeHills", "Weights", weightVanillaExtremeHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaExtremeHillsPlus = config.getInt("weightVanillaExtremeHillsPlus", "Weights", weightVanillaExtremeHillsPlus, biomeWeightMin, biomeWeightMax, "");
			weightVanillaForest = config.getInt("weightVanillaForest", "Weights", weightVanillaForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaForestHills = config.getInt("weightVanillaForestHills", "Weights", weightVanillaForestHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaFrozenRiver = config.getInt("weightVanillaFrozenRiver", "Weights", weightVanillaFrozenRiver, biomeWeightMin, biomeWeightMax, "");
			weightVanillaIceMountains = config.getInt("weightVanillaIceMountains", "Weights", weightVanillaIceMountains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaIcePlains = config.getInt("weightVanillaIcePlains", "Weights", weightVanillaIcePlains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungle = config.getInt("weightVanillaJungle", "Weights", weightVanillaJungle, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungleEdge = config.getInt("weightVanillaJungleEdge", "Weights", weightVanillaJungleEdge, biomeWeightMin, biomeWeightMax, "");
			weightVanillaJungleHills = config.getInt("weightVanillaJungleHills", "Weights", weightVanillaJungleHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMegaTaiga = config.getInt("weightVanillaMegaTaiga", "Weights", weightVanillaMegaTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMegaTaigaHills = config.getInt("weightVanillaMegaTaigaHills", "Weights", weightVanillaMegaTaigaHills, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesa = config.getInt("weightVanillaMesa", "Weights", weightVanillaMesa, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesaPlateau = config.getInt("weightVanillaMesaPlateau", "Weights", weightVanillaMesaPlateau, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMesaPlateau_F = config.getInt("weightVanillaMesaPlateau_F", "Weights", weightVanillaMesaPlateau_F, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMushroomIsland = config.getInt("weightVanillaMushroomIsland", "Weights", weightVanillaMushroomIsland, biomeWeightMin, biomeWeightMax, "");
			weightVanillaMushroomIslandShore = config.getInt("weightVanillaMushroomIslandShore", "Weights", weightVanillaMushroomIslandShore, biomeWeightMin, biomeWeightMax, "");
			weightVanillaOcean = config.getInt("weightVanillaOcean", "Weights", weightVanillaOcean, biomeWeightMin, biomeWeightMax, "");
			weightVanillaPlains = config.getInt("weightVanillaPlains", "Weights", weightVanillaPlains, biomeWeightMin, biomeWeightMax, "");
			weightVanillaRiver = config.getInt("weightVanillaRiver", "Weights", weightVanillaRiver, biomeWeightMin, biomeWeightMax, "");
			weightVanillaRoofedForest = config.getInt("weightVanillaRoofedForest", "Weights", weightVanillaRoofedForest, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSavanna = config.getInt("weightVanillaSavanna", "Weights", weightVanillaSavanna, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSavannaPlateau = config.getInt("weightVanillaSavannaPlateau", "Weights", weightVanillaSavannaPlateau, biomeWeightMin, biomeWeightMax, "");
			weightVanillaStoneBeach = config.getInt("weightVanillaStoneBeach", "Weights", weightVanillaStoneBeach, biomeWeightMin, biomeWeightMax, "");
			weightVanillaSwampland = config.getInt("weightVanillaSwampland", "Weights", weightVanillaSwampland, biomeWeightMin, biomeWeightMax, "");
			weightVanillaTaiga = config.getInt("weightVanillaTaiga", "Weights", weightVanillaTaiga, biomeWeightMin, biomeWeightMax, "");
			weightVanillaTaigaHills = config.getInt("weightVanillaTaigaHills", "Weights", weightVanillaTaigaHills, biomeWeightMin, biomeWeightMax, "");
			
            weightVanillaSunflowerPlains = config.getInt("weightVanillaSunflowerPlains", "Weights", weightVanillaSunflowerPlains, biomeWeightMin, biomeWeightMax, "");
            weightVanillaDesertM = config.getInt("weightVanillaDesertM", "Weights", weightVanillaDesertM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaExtremeHillsM = config.getInt("weightVanillaExtremeHillsM", "Weights", weightVanillaExtremeHillsM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaFlowerForest = config.getInt("weightVanillaFlowerForest", "Weights", weightVanillaFlowerForest, biomeWeightMin, biomeWeightMax, "");
            weightVanillaTaigaM = config.getInt("weightVanillaTaigaM", "Weights", weightVanillaTaigaM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSwamplandM = config.getInt("weightVanillaSwamplandM", "Weights", weightVanillaSwamplandM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaIcePlainsSpikes = config.getInt("weightVanillaIcePlainsSpikes", "Weights", weightVanillaIcePlainsSpikes, biomeWeightMin, biomeWeightMax, "");
            weightVanillaJungleM = config.getInt("weightVanillaJungleM", "Weights", weightVanillaJungleM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaJungleEdgeM = config.getInt("weightVanillaJungleEdgeM", "Weights", weightVanillaJungleEdgeM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaBirchForestM = config.getInt("weightVanillaBirchForestM", "Weights", weightVanillaBirchForestM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaBirchForestHillsM = config.getInt("weightVanillaBirchForestHillsM", "Weights", weightVanillaBirchForestHillsM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaRoofedForestM = config.getInt("weightVanillaRoofedForestM", "Weights", weightVanillaRoofedForestM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaColdTaigaM = config.getInt("weightVanillaColdTaigaM", "Weights", weightVanillaColdTaigaM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMegaSpruceTaiga = config.getInt("weightVanillaMegaSpruceTaiga", "Weights", weightVanillaMegaSpruceTaiga, biomeWeightMin, biomeWeightMax, "");
            weightVanillaExtremeHillsPlusM = config.getInt("weightVanillaExtremeHillsPlusM", "Weights", weightVanillaExtremeHillsPlusM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSavannaM = config.getInt("weightVanillaSavannaM", "Weights", weightVanillaSavannaM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaSavannaPlateauM = config.getInt("weightVanillaSavannaPlateauM", "Weights", weightVanillaSavannaPlateauM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaBryce = config.getInt("weightVanillaMesaBryce", "Weights", weightVanillaMesaBryce, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaPlateauFM = config.getInt("weightVanillaMesaPlateauFM", "Weights", weightVanillaMesaPlateauFM, biomeWeightMin, biomeWeightMax, "");
            weightVanillaMesaPlateauM = config.getInt("weightVanillaMesaPlateauM", "Weights", weightVanillaMesaPlateauM, biomeWeightMin, biomeWeightMax, "");
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
}
