package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenWetlandBase;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import enhancedbiomes.world.biome.wetland.BiomeGenCarr;
import enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake;
import enhancedbiomes.world.biome.wetland.BiomeGenFen;
import enhancedbiomes.world.biome.wetland.BiomeGenLake;
import enhancedbiomes.world.biome.wetland.BiomeGenMangrove;
import enhancedbiomes.world.biome.wetland.BiomeGenMarsh;
import enhancedbiomes.world.biome.woodland.BiomeGenWoodlands;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EnhancedBiomesWetland
{	
	public static int mangroveId;	
	public static int mangroveGen;
	public static boolean villageMangrove;
	public static BiomeGenWetlandBase biomeMangrove; 	 	  
	
	public static int ephemeralLakeId;
	public static int ephemeralLakeGen;
	public static boolean villageEphemeralLake;
	public static BiomeGenWetlandBase biomeEphemeralLake;
	
	public static int ephemeralLakeEdgeId;
	public static boolean villageEphemeralLakeEdge;
	public static BiomeGenWetlandBase biomeEphemeralLakeEdge;
	
	public static int fenId;
	public static int fenGen;
	public static boolean villageFen;
	public static BiomeGenWetlandBase biomeFen;
	
	public static int carrId;
	public static int carrGen;
	public static boolean villageCarr;
	public static BiomeGenWetlandBase biomeCarr;
	
	public static int lakeId;
	public static boolean villageLake;
	public static BiomeGenWetlandBase biomeLake;
	
	public static int woodlandLakeId;
	public static boolean villageWoodlandLake;
	public static BiomeGenWoodlandBase biomeWoodlandLake;
	
	public static int woodlandLakeEdgeId;
	public static boolean villageWoodlandLakeEdge;
	public static BiomeGenWoodlandBase biomeWoodlandLakeEdge;

	public static int marshId;	
	public static int marshGen;	
	public static boolean villageMarsh;
	public static BiomeGenWetlandBase biomeMarsh; 	
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();
		
		mangroveId = config.get(config.CATEGORY_GENERAL, "Biome ID of Mangrove", BiomeIDs.mangroves).getInt();
		mangroveGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Mangrove biome", 10).getInt();
		villageMangrove = config.get(config.CATEGORY_GENERAL, "Generate villages in Mangrove biome", true).getBoolean(true);
		
		ephemeralLakeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Ephemeral Lake", BiomeIDs.ephemeralLake).getInt();
		ephemeralLakeGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Ephemeral Lake biome", 10).getInt();
		villageEphemeralLake = config.get(config.CATEGORY_GENERAL, "Generate villages in Ephemeral Lake biome", true).getBoolean(true);
		
		ephemeralLakeEdgeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Ephemeral Lake Edge", BiomeIDs.ephemeralLakeEdge).getInt();
		villageEphemeralLakeEdge = config.get(config.CATEGORY_GENERAL, "Generate villages in Ephemeral Lake Edge biome", true).getBoolean(true);
		
		fenId = config.get(config.CATEGORY_GENERAL, "Biome ID of Fens", BiomeIDs.fens).getInt();
		fenGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Fens biome", 10).getInt();
		villageFen = config.get(config.CATEGORY_GENERAL, "Generate villages in Fens biome", true).getBoolean(true);
		
		carrId = config.get(config.CATEGORY_GENERAL, "Biome ID of Carr", BiomeIDs.carr).getInt();
		carrGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Carr biome", 10).getInt();
		villageCarr = config.get(config.CATEGORY_GENERAL, "Generate villages in Carr biome", true).getBoolean(true);
		
		lakeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Lake", BiomeIDs.lake).getInt();
		villageLake = config.get(config.CATEGORY_GENERAL, "Generate villages in Lake biome", false).getBoolean(true);
		
		woodlandLakeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Woodland Lake", BiomeIDs.woodlandLake).getInt();
		villageWoodlandLake = config.get(config.CATEGORY_GENERAL, "Generate villages in Woodland Lake biome", true).getBoolean(true);
		
		woodlandLakeEdgeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Woodland Lake Edge", BiomeIDs.woodlandLakeEdge).getInt();
		villageWoodlandLakeEdge = config.get(config.CATEGORY_GENERAL, "Generate villages in Woodland Lake Edge biome", true).getBoolean(true);
		
		marshId = config.get(config.CATEGORY_GENERAL, "Biome ID of Marsh", BiomeIDs.marsh).getInt();
		marshGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Marsh biome", 10).getInt();
		villageMarsh = config.get(config.CATEGORY_GENERAL, "Generate villages in Marsh biome", true).getBoolean(true);
		
		config.save();
	}
	
	public static void load()
	{
		biomeMangrove = (BiomeGenWetlandBase) (new BiomeGenMangrove(mangroveId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.9F, 0.6F).setHeight(heightPartiallySubmerged).setBiomeName("Mangroves");
		BiomeGenManager.addWarmBiome(biomeMangrove, mangroveGen);	  
		if (villageMangrove == true) 			{  BiomeManager.addVillageBiome(biomeMangrove, true);  }
		BiomeManager.addStrongholdBiome(biomeMangrove);
		BiomeWoods.register(biomeMangrove, EnhancedBiomesBlocks.planksEB, 3);
		
		biomeEphemeralLake = (BiomeGenWetlandBase) (new BiomeGenEphemeralLake(ephemeralLakeId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.5F, 0.9F).setHeight(heightPartiallySubmerged).setBiomeName("Ephemeral Lake");
		BiomeGenManager.addWarmBiome(biomeEphemeralLake, ephemeralLakeGen);
		if (villageEphemeralLake == true) 			{  BiomeManager.addVillageBiome(biomeEphemeralLake, true);  }
		BiomeManager.addStrongholdBiome(biomeEphemeralLake);
		BiomeWoods.register(biomeEphemeralLake, EnhancedBiomesBlocks.planksEB, 13);
		
		biomeEphemeralLakeEdge = (BiomeGenWetlandBase) (new BiomeGenEphemeralLake(ephemeralLakeEdgeId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.5F, 0.9F).setHeight(heightShores).setBiomeName("Ephemeral Lake Edge");
		if (villageEphemeralLakeEdge == true) 			{  BiomeManager.addVillageBiome(biomeEphemeralLakeEdge, true);  }
		BiomeManager.addStrongholdBiome(biomeEphemeralLakeEdge);
		BiomeWoods.register(biomeEphemeralLakeEdge, EnhancedBiomesBlocks.planksEB, 13);
		
		biomeFen = (BiomeGenWetlandBase) (new BiomeGenFen(fenId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.2F, 0.8F).setHeight(heightPartiallySubmerged).setBiomeName("Fens");
		BiomeGenManager.addCoolBiome(biomeFen, fenGen);
		if (villageFen == true) 			{  BiomeManager.addVillageBiome(biomeFen, true);  }
		BiomeManager.addStrongholdBiome(biomeFen);
		BiomeWoods.register(biomeFen, EnhancedBiomesBlocks.planksEB, 3);
		
		biomeCarr = (BiomeGenWetlandBase) (new BiomeGenCarr(carrId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.25F, 0.8F).setHeight(heightPartiallySubmerged).setBiomeName("Carr");
		BiomeGenManager.addCoolBiome(biomeCarr, carrGen);
		if (villageCarr == true) 			{  BiomeManager.addVillageBiome(biomeCarr, true);  }
		BiomeManager.addStrongholdBiome(biomeCarr);	
		BiomeWoods.register(biomeCarr, EnhancedBiomesBlocks.planksEB, 8);
		
		biomeLake = (BiomeGenWetlandBase) (new BiomeGenLake(lakeId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.6F, 0.8F).setHeight(heightShallowWaters).setBiomeName("Lake");
		if (villageLake == true) 			{  BiomeManager.addVillageBiome(biomeLake, true);  }
		BiomeManager.addStrongholdBiome(biomeLake);
		BiomeWoods.register(biomeLake, EnhancedBiomesBlocks.planksEB, 8);
		
		biomeWoodlandLake = (BiomeGenWoodlandBase) (new BiomeGenWoodlands(woodlandLakeId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.5F, 0.9F).setHeight(heightPartiallySubmerged).setBiomeName("Woodland Lake");
		if (villageWoodlandLake == true) 			{  BiomeManager.addVillageBiome(biomeWoodlandLake, true);  }
		BiomeManager.addStrongholdBiome(biomeWoodlandLake);
		BiomeWoods.register(biomeWoodlandLake, Blocks.planks, 0);
		
		biomeWoodlandLakeEdge = (BiomeGenWoodlandBase) (new BiomeGenWoodlands(woodlandLakeEdgeId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.5F, 0.9F).setHeight(heightShores).setBiomeName("Woodland Lake Edge");
		if (villageWoodlandLakeEdge == true) 			{  BiomeManager.addVillageBiome(biomeWoodlandLakeEdge, true);  }
		BiomeManager.addStrongholdBiome(biomeWoodlandLakeEdge);
		BiomeWoods.register(biomeWoodlandLakeEdge, Blocks.planks, 0);

		biomeMarsh = (BiomeGenWetlandBase) (new BiomeGenMarsh(marshId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.9F, 0.6F).setHeight(heightLowPlains).setBiomeName("Marsh");
		BiomeGenManager.addWarmBiome(biomeMarsh, marshGen);
		if (villageMarsh == true) 			{  BiomeManager.addVillageBiome(biomeMarsh, true);  }
		BiomeManager.addStrongholdBiome(biomeMarsh);
		BiomeWoods.register(biomeMarsh, Blocks.planks, 0);
	}
}