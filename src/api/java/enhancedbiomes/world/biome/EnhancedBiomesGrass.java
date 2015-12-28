package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenGrassBase;
import enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra;
import enhancedbiomes.world.biome.grass.BiomeGenBadlands;
import enhancedbiomes.world.biome.grass.BiomeGenMountains;
import enhancedbiomes.world.biome.grass.BiomeGenPlateau;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;

public class EnhancedBiomesGrass 
{
	public static int badlandsId;
	public static int badlandsGen;
	public static boolean villageBadlands;
	public static BiomeGenGrassBase biomeBadlands;
	
	public static int plateauId;
	public static int plateauGen;
	public static boolean villagePlateau;
	public static BiomeGenGrassBase biomePlateau;
	
	public static int mountainsId;
	public static int mountainsGen;
	public static boolean villageMountains;
	public static BiomeGenGrassBase biomeMountains; 
 	
	public static int mountainsEdgeId;
	public static boolean villageMountainsEdge;
	public static BiomeGenGrassBase biomeMountainsEdge;
 	
	public static int clearingId;
	public static boolean villageClearing;
	public static BiomeGenGrassBase biomeClearing;
	
	public static int mountainTundraId;
	public static int mountainTundraGen;
	public static boolean villageMountainTundra;
	public static BiomeGenGrassBase biomeMountainTundra; 
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		badlandsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Badlands", BiomeIDs.badlands).getInt();
		badlandsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Badlands biome", 10).getInt();
		villageBadlands = config.get(config.CATEGORY_GENERAL, "Generate villages in Badlands biome", true).getBoolean(true);
		
		plateauId = config.get(config.CATEGORY_GENERAL, "Biome ID of Plateau", BiomeIDs.plateau).getInt();
		plateauGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Plateau biome", 10).getInt();
		villagePlateau = config.get(config.CATEGORY_GENERAL, "Generate villages in Plateau biome", true).getBoolean(true);

		mountainsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Mountains", BiomeIDs.mountains).getInt();
		mountainsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Mountains biome", 10).getInt();
		villageMountains = config.get(config.CATEGORY_GENERAL, "Generate villages in Mountains biome", true).getBoolean(true);

		mountainsEdgeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Mountains Edge", BiomeIDs.mountainsEdge).getInt();
		villageMountainsEdge = config.get(config.CATEGORY_GENERAL, "Generate villages in Mountains Edge biome", true).getBoolean(true);

		clearingId = config.get(config.CATEGORY_GENERAL, "Biome ID of Clearing", BiomeIDs.clearing).getInt();
		villageClearing = config.get(config.CATEGORY_GENERAL, "Generate villages in Clearing biome", true).getBoolean(true);

		mountainTundraId = config.get(config.CATEGORY_GENERAL, "Biome ID of Alpine Tundra", BiomeIDs.alpineTundra).getInt();
		mountainTundraGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Alpine Tundra biome", 10).getInt();
		villageMountainTundra = config.get(config.CATEGORY_GENERAL, "Generate villages in Alpine Tundra biome", true).getBoolean(true);
		
		config.save();

		EnhancedBiomesPlains.config();
	}
	
	public static void load()
	{		
		biomeBadlands = (BiomeGenGrassBase) (new BiomeGenBadlands(badlandsId)).setColor(9286496).setTemperatureRainfall(2.0F, 0.1F).setHeight(heightDefault).setDisableRain().setBiomeName("Badlands");
		BiomeGenManager.addHotBiome(biomeBadlands, badlandsGen);
		if (villageBadlands)			{  BiomeManager.addVillageBiome(biomeBadlands, true);  }
		BiomeManager.addStrongholdBiome(biomeBadlands);
		BiomeWoods.register(biomeBadlands, EnhancedBiomesBlocks.planksEB, 13);
		
		biomePlateau = (BiomeGenGrassBase) (new BiomeGenPlateau(plateauId)).setColor(9286496).setTemperatureRainfall(0.3F, 0.6F).setHeight(heightMidPlateaus).setBiomeName("Plateau");
		BiomeGenManager.addCoolBiome(biomePlateau, plateauGen);
		if (villagePlateau) 			{  BiomeManager.addVillageBiome(biomePlateau, true);  }
		BiomeManager.addStrongholdBiome(biomePlateau);
		BiomeWoods.register(biomePlateau, Blocks.planks, 0);
		
		biomeMountains = (BiomeGenGrassBase) (new BiomeGenMountains(mountainsId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.6F).setHeight(heightHighHills).setBiomeName("Mountains");
		BiomeGenManager.addCoolBiome(biomeMountains, mountainsGen);
		if (villageMountains) 			{  BiomeManager.addVillageBiome(biomeMountains, true);  }
		BiomeManager.addStrongholdBiome(biomeMountains);
		BiomeWoods.register(biomeMountains, Blocks.planks, 1);
		
		biomeMountainsEdge = (BiomeGenGrassBase) (new BiomeGenMountains(mountainsEdgeId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.6F).setHeight(heightMidHills).setBiomeName("Mountains Edge");
		if (villageMountainsEdge) 		{  BiomeManager.addVillageBiome(biomeMountainsEdge, true);  }
		BiomeManager.addStrongholdBiome(biomeMountainsEdge);
		BiomeWoods.register(biomeMountainsEdge, Blocks.planks, 1);
		
		biomeClearing = (BiomeGenGrassBase) (new BiomeGenGrassBase(clearingId)).setColor(9286496).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightDefault).setBiomeName("Clearing");
		if (villageClearing) 		{  BiomeManager.addVillageBiome(biomeClearing, true);  }
		BiomeManager.addStrongholdBiome(biomeClearing);
		BiomeWoods.register(biomeClearing, Blocks.planks, 0);
		
		biomeMountainTundra = (BiomeGenGrassBase) (new BiomeGenAlpineTundra(mountainTundraId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.5F).setHeight(heightHighHills).setBiomeName("Alpine Tundra");
		BiomeGenManager.addCoolBiome(biomeMountainTundra, mountainTundraGen);
		if (villageMountainTundra) 			{  BiomeManager.addVillageBiome(biomeMountainTundra, true);  }
		BiomeManager.addStrongholdBiome(biomeMountainTundra);
		BiomeWoods.register(biomeMountainTundra, EnhancedBiomesBlocks.planksEB, 5);
		
		EnhancedBiomesPlains.load();
	}
}
