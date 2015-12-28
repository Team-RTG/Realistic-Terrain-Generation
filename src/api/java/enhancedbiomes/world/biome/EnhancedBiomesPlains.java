package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands;
import enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed;
import enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills;
import enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow;
import enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM;
import enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie;
import enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah;
import enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EnhancedBiomesPlains 
{	  
	public static int grasslandsId;
	public static int grasslandsGen;   
	public static boolean villageGrasslands;    
	public static BiomeGenPlainsBase biomeGrasslands;
	 
	public static int savannahId;	
	public static int savannahGen;  	  	  
	public static boolean villageSavannah;	  	 
	public static BiomeGenPlainsBase biomeSavannah;	  	 	  
	
	public static int steppeId;	 
	public static int steppeGen; 
	public static boolean villageSteppe;	   
	public static BiomeGenPlainsBase biomeSteppe;	 	  		    
	
	public static int prairieId;
	public static int prairieGrass;
	public static int prairieGen;			  
	public static boolean villagePrairie;	  	  
	public static BiomeGenPlainsBase biomePrairie;	  		

	public static int lowHillsId;
	public static int lowHillsGen;
	public static boolean villageLowHills;
	public static BiomeGenPlainsBase biomeLowHills;
	
	public static int meadowId;
	public static int meadowGen;
	public static boolean villageMeadow;
	public static BiomeGenPlainsBase biomeMeadow;
	
	public static int grasslandsRoofedId;   
	public static boolean villageGrasslandsRoofed;    
	public static BiomeGenPlainsBase biomeGrasslandsRoofed;
	 
	public static int meadowMId;
	public static boolean villageMeadowM;
	public static BiomeGenPlainsBase biomeMeadowM;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		grasslandsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Shrublands", BiomeIDs.shrublands).getInt();
		grasslandsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Shrublands biome", 10).getInt();
		villageGrasslands = config.get(config.CATEGORY_GENERAL, "Generate villages in Shrublands biome", true).getBoolean(true);
		
		savannahId = config.get(config.CATEGORY_GENERAL, "Biome ID of Xeric Savanna", BiomeIDs.xericSavannah).getInt();
		savannahGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Xeric Savanna biome", 10).getInt();
		villageSavannah = config.get(config.CATEGORY_GENERAL, "Generate villages in Xeric Savanna biome", true).getBoolean(true);
		
		steppeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Steppe", BiomeIDs.steppe).getInt();
		steppeGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Steppe biome", 10).getInt();
		villageSteppe = config.get(config.CATEGORY_GENERAL, "Generate villages in Steppe biome", true).getBoolean(true);
		
		prairieId = config.get(config.CATEGORY_GENERAL, "Biome ID of Prairie", BiomeIDs.prairie).getInt();
		prairieGrass = config.get(config.CATEGORY_GENERAL, "Grass colour of Prairie biome", 0xFBC520).getInt();
		prairieGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Prairie biome", 10).getInt();
		villagePrairie = config.get(config.CATEGORY_GENERAL, "Generate villages in Prairie biome", true).getBoolean(true);
		
		lowHillsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Low Hills", BiomeIDs.lowHills).getInt();
		lowHillsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Low Hills biome", 10).getInt();
		villageLowHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Low Hills biome", true).getBoolean(true);
		
		meadowId = config.get(config.CATEGORY_GENERAL, "Biome ID of Meadow", BiomeIDs.meadow).getInt();
		meadowGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Meadow biome", 10).getInt();
		villageMeadow = config.get(config.CATEGORY_GENERAL, "Generate villages in Meadow biome", true).getBoolean(true);
		
		grasslandsRoofedId = config.get(config.CATEGORY_GENERAL, "Biome ID of Roofed Shrublands", BiomeIDs.roofedShrublands).getInt();
		villageGrasslandsRoofed = config.get(config.CATEGORY_GENERAL, "Generate villages in Roofed Shrublands biome", true).getBoolean(true);
		
		meadowMId = config.get(config.CATEGORY_GENERAL, "Biome ID of Meadow M", BiomeIDs.meadowM).getInt();
		villageMeadowM = config.get(config.CATEGORY_GENERAL, "Generate villages in Meadow M biome", true).getBoolean(true);
		
		config.save();
	}
	
	public static void load()
	{
		biomeGrasslands = (BiomeGenPlainsBase) (new BiomeGenGrasslands(grasslandsId)).setColor(9286496).setTemperatureRainfall(0.8F, 0.4F).setHeight(heightDefault).setBiomeName("Shrublands");
		BiomeGenManager.addWarmBiome(biomeGrasslands, grasslandsGen);
		if (villageGrasslands) 			{  BiomeManager.addVillageBiome(biomeGrasslands, true);  }
		BiomeManager.addStrongholdBiome(biomeGrasslands);
		BiomeWoods.register(biomeGrasslands, Blocks.planks, 0);
		
		biomeSavannah = (BiomeGenPlainsBase) (new BiomeGenSavannah(savannahId)).setDisableRain().setColor(9286496).setTemperatureRainfall(1.5F, 0.05F).setHeight(heightLowPlains).setBiomeName("Xeric Savannah");
		BiomeGenManager.addHotBiome(biomeSavannah, savannahGen);	  
		if (villageSavannah) 			{  BiomeManager.addVillageBiome(biomeSavannah, true);  }	  
		BiomeManager.addStrongholdBiome(biomeSavannah);
		BiomeWoods.register(biomeSavannah, EnhancedBiomesBlocks.planksEB, 1);
		
		biomeSteppe = (BiomeGenPlainsBase) (new BiomeGenSteppe(steppeId)).setColor(9286496).setTemperatureRainfall(0.3F, 1.0F).setHeight(heightLowPlateaus).setBiomeName("Steppe");
		BiomeGenManager.addCoolBiome(biomeSteppe, steppeGen); 	  
		if (villageSteppe) 				{  BiomeManager.addVillageBiome(biomeSteppe, true);  }		  
		BiomeManager.addStrongholdBiome(biomeSteppe);
		BiomeWoods.register(biomeSteppe, EnhancedBiomesBlocks.planksEB, 2);
		
		biomePrairie = (BiomeGenPlainsBase) (new BiomeGenPrairie(prairieId)).setColor(9286496).setTemperatureRainfall(0.9F, 0.0F).setHeight(heightLowPlains).setBiomeName("Prairie");
		BiomeGenManager.addWarmBiome(biomePrairie, prairieGen);	  
		if (villagePrairie) 			{  BiomeManager.addVillageBiome(biomePrairie, true);  }		  
		BiomeManager.addStrongholdBiome(biomePrairie);
		BiomeWoods.register(biomePrairie, EnhancedBiomesBlocks.planksEB, 12);
		
		biomeLowHills = (BiomeGenPlainsBase) (new BiomeGenLowHills(lowHillsId)).setColor(9286496).setTemperatureRainfall(0.8F, 0.4F).setHeight(heightMidPlains).setBiomeName("Low Hills");
		BiomeGenManager.addWarmBiome(biomeLowHills, lowHillsGen);
		if (villageLowHills) 			{  BiomeManager.addVillageBiome(biomeLowHills, true);  }
		BiomeManager.addStrongholdBiome(biomeLowHills);
		BiomeWoods.register(biomeLowHills, Blocks.planks, 0);
		
		biomeMeadow = (BiomeGenPlainsBase) (new BiomeGenMeadow(meadowId)).setColor(9286496).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightDefault).setBiomeName("Meadow");
		BiomeGenManager.addWarmBiome(biomeMeadow, meadowGen);
		if (villageMeadow) 				{  BiomeManager.addVillageBiome(biomeMeadow, true);  }
		BiomeManager.addStrongholdBiome(biomeMeadow);
		BiomeWoods.register(biomeMeadow, EnhancedBiomesBlocks.planksEB, 2);
		
		biomeGrasslandsRoofed = (BiomeGenPlainsBase) (new BiomeGenGrasslandsRoofed(grasslandsRoofedId)).setColor(9286496).setTemperatureRainfall(0.8F, 0.4F).setHeight(heightDefault).setBiomeName("Roofed Shrublands");
		if (villageGrasslandsRoofed) 			{  BiomeManager.addVillageBiome(biomeGrasslandsRoofed, true);  }
		BiomeManager.addStrongholdBiome(biomeGrasslandsRoofed);	
		BiomeWoods.register(biomeGrasslandsRoofed, Blocks.planks, 5);
		
		biomeMeadowM = (BiomeGenPlainsBase) (new BiomeGenMeadowM(meadowMId)).setColor(9286496).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightDefault).setBiomeName("Meadow M");
		if (villageMeadowM) 				{  BiomeManager.addVillageBiome(biomeMeadowM, true);  }
		BiomeManager.addStrongholdBiome(biomeMeadowM);	
		BiomeWoods.register(biomeMeadowM, EnhancedBiomesBlocks.planksEB, 2);
	}
}
