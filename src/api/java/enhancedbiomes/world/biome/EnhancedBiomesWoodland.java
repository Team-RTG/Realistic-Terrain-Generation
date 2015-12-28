package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenSnowForestBase;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge;
import enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest;
import enhancedbiomes.world.biome.woodland.BiomeGenAspenForest;
import enhancedbiomes.world.biome.woodland.BiomeGenBorealForest;
import enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom;
import enhancedbiomes.world.biome.woodland.BiomeGenCypressForest;
import enhancedbiomes.world.biome.woodland.BiomeGenKakadu;
import enhancedbiomes.world.biome.woodland.BiomeGenOakForest;
import enhancedbiomes.world.biome.woodland.BiomeGenPineForest;
import enhancedbiomes.world.biome.woodland.BiomeGenShield;
import enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest;
import enhancedbiomes.world.biome.woodland.BiomeGenWoodlands;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;

public class EnhancedBiomesWoodland
{	  
	public static int blossomWoodsId;
	public static int blossomWoodsGen;
	public static boolean villageBlossomWoods;
	public static BiomeGenWoodlandBase biomeBlossomWoods;
	 
	public static int blossomHillsId;
	public static boolean villageBlossomHills; 	 
	public static BiomeGenWoodlandBase biomeBlossomHills;
	  
	public static int woodLandsId;
	public static int woodLandsGen; 	  
	public static boolean villageWoodLands; 	  
	public static BiomeGenWoodlandBase biomeWoodLands;
	
	public static int woodLandHillsId;	  
	public static boolean villageWoodLandHills;
	public static BiomeGenWoodlandBase biomeWoodLandHills;
	
	public static int pineForestId;
	public static int pineForestGen;
	public static boolean villagePineForest;
	public static BiomeGenWoodlandBase biomePineForest;
	
	public static int oakForestId;
	public static int oakForestGen;
	public static boolean villageOakForest;
	public static BiomeGenWoodlandBase biomeOakForest;
	
	public static int borealForestId;
	public static int borealForestGen;
	public static boolean villageBorealForest;
	public static BiomeGenWoodlandBase biomeBorealForest;
	
	public static int kakaduId;	  
	public static int kakaduGen;
	public static boolean villageKakadu; 
	public static BiomeGenWoodlandBase biomeKakadu; 
	
	public static int silverPineForestId;
	public static int silverPineForestGen;
	public static boolean villageSilverPineForest;
	public static BiomeGenWoodlandBase biomeSilverPineForest;
	
	public static int silverPineHillsId;
	public static boolean villageSilverPineHills;
	public static BiomeGenWoodlandBase biomeSilverPineHills;
	
	public static int aspenForestId;
	public static int aspenForestGen;
	public static boolean villageAspenForest;
	public static BiomeGenWoodlandBase biomeAspenForest;
	
	public static int aspenHillsId;
	public static boolean villageAspenHills;
	public static BiomeGenWoodlandBase biomeAspenHills;
	
	public static int cypressForestId;
	public static int cypressForestGen;
	public static boolean villageCypressForest;
	public static BiomeGenWoodlandBase biomeCypressForest;
	
	public static int shieldId;
	public static int shieldGen;
	public static boolean villageShield;
	public static BiomeGenWoodlandBase biomeShield;

	public static int forestMountainsId;
	public static boolean villageForestMountains;
	public static BiomeGenWoodlandBase biomeForestMountains;
	
	public static int woodlandFieldId;
	public static int woodlandFieldGen;   
	public static boolean villageWoodlandField;    
	public static BiomeGenWoodlandBase biomeWoodlandField;
	
	public static int borealPlateauId;
	public static int borealPlateauGen;
	public static boolean villageBorealPlateau;
	public static BiomeGenWoodlandBase biomeBorealPlateau;
	
	public static int alpineEdgeId;	  	  
	public static boolean villageAlpineEdge;
	public static BiomeGenSnowForestBase biomeAlpineEdge;  	 
	
	public static int firForestId;
	public static int firForestGen;
	public static boolean villageFirForest;	
	public static BiomeGenSnowForestBase biomeFirForest;	
	
	public static int borealPlateauMId;
	public static boolean villageBorealPlateauM;
	public static BiomeGenWoodlandBase biomeBorealPlateauM;

	public static int forestValleyId;
	public static boolean villageForestValley;
	public static BiomeGenWoodlandBase biomeForestValley;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		blossomWoodsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Blossom Woods", BiomeIDs.blossomWoods).getInt();
		blossomWoodsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Blossom Woods biome", 10).getInt();
		villageBlossomWoods = config.get(config.CATEGORY_GENERAL, "Generate villages in Blossom Woods biome", true).getBoolean(true);
		
		blossomHillsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Blossom Hills", BiomeIDs.blossomHills).getInt();
		villageBlossomHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Blossom Hills biome", true).getBoolean(true);
		
		woodLandsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Woodlands", BiomeIDs.woodlands).getInt();
		woodLandsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Woodlands biome", 10).getInt();
		villageWoodLands = config.get(config.CATEGORY_GENERAL, "Generate villages in Woodlands biome", true).getBoolean(true);
		
		woodLandHillsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Woodland Hills", BiomeIDs.woodlandHills).getInt();
		villageWoodLandHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Woodland Hills biome", true).getBoolean(true);
		
		pineForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Pine Forest", BiomeIDs.pineForest).getInt();
		pineForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Pine Forest biome", 10).getInt();
		villagePineForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Pine Forest biome", true).getBoolean(true);
		
		oakForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Oak Forest", BiomeIDs.oakForest).getInt();
		oakForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Oak Forest biome", 10).getInt();
		villageOakForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Oak Forest biome", true).getBoolean(true);
		
		borealForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Boreal Forest", BiomeIDs.borealForest).getInt();
		borealForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Boreal Forest biome", 10).getInt();
		villageBorealForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Boreal Forest biome", true).getBoolean(true);
		
		kakaduId = config.get(config.CATEGORY_GENERAL, "Biome ID of Kakadu", BiomeIDs.kakadu).getInt();
		kakaduGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Kakadu biome", 10).getInt();
		villageKakadu = config.get(config.CATEGORY_GENERAL, "Generate villages in Kakadu biome", true).getBoolean(true);
		
		silverPineForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Silver Pine Forest", BiomeIDs.silverPineForest).getInt();
		silverPineForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Silver Pine Forest biome", 10).getInt();
		villageSilverPineForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Silver Pine Forest biome", true).getBoolean(true);
		
		silverPineHillsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Silver Pine Hills", BiomeIDs.silverPineHills).getInt();
		villageSilverPineHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Silver Pine Hills biome", true).getBoolean(true);
		
		aspenForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Aspen Forest", BiomeIDs.aspenForest).getInt();
		aspenForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Aspen Forest biome", 10).getInt();
		villageAspenForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Aspen Forest biome", true).getBoolean(true);
		
		aspenHillsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Aspen Hills", BiomeIDs.aspenHills).getInt();
		villageAspenHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Aspen Hills biome", true).getBoolean(true);
		
		cypressForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Cypress Forest", BiomeIDs.cypressForest).getInt();
		cypressForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Cypress Forest biome", 10).getInt();
		villageCypressForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Cypress Forest biome", true).getBoolean(true);
		
		shieldId = config.get(config.CATEGORY_GENERAL, "Biome ID of Shield", BiomeIDs.shield).getInt();
		shieldGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Shield biome", 10).getInt();
		villageShield = config.get(config.CATEGORY_GENERAL, "Generate villages in Shield biome", true).getBoolean(true);

		forestMountainsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Forested Mountains", BiomeIDs.forestedMountains).getInt();
		villageForestMountains = config.get(config.CATEGORY_GENERAL, "Generate villages in Forested Mountains biome", true).getBoolean(true);
		
		woodlandFieldId = config.get(config.CATEGORY_GENERAL, "Biome ID of Woodland Field", BiomeIDs.woodlandField).getInt();
		woodlandFieldGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Woodland Field biome", 10).getInt();
		villageWoodlandField = config.get(config.CATEGORY_GENERAL, "Generate villages in Woodland Field biome", true).getBoolean(true);
		
		borealPlateauId = config.get(config.CATEGORY_GENERAL, "Biome ID of Boreal Plateau", BiomeIDs.borealPlateau).getInt();
		borealPlateauGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Boreal Plateau biome", 10).getInt();
		villageBorealPlateau = config.get(config.CATEGORY_GENERAL, "Generate villages in Boreal Plateau biome", true).getBoolean(true);
		
		alpineEdgeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Alpine Mountains Edge", BiomeIDs.alpineMountainsEdge).getInt();
		villageAlpineEdge = config.get(config.CATEGORY_GENERAL, "Generate villages in Alpine Mountains Edge biome", true).getBoolean(true);
		
		firForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Fir Forest", BiomeIDs.firForest).getInt();
		firForestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Fir Forest biome", 10).getInt();
		villageFirForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Fir Forest biome", true).getBoolean(true);
		
		borealPlateauMId = config.get(config.CATEGORY_GENERAL, "Biome ID of Boreal Plateau M", BiomeIDs.borealPlateauM).getInt();
		villageBorealPlateauM = config.get(config.CATEGORY_GENERAL, "Generate villages in Boreal Plateau M biome", true).getBoolean(true);

		forestValleyId = config.get(config.CATEGORY_GENERAL, "Biome ID of Forested Valley", BiomeIDs.forestedValley).getInt();
		villageForestValley = config.get(config.CATEGORY_GENERAL, "Generate villages in Forested Valley biome", true).getBoolean(true);
		
		config.save();

		EnhancedBiomesTropical.config();
	}
	
	public static void load()
	{
		biomeBlossomWoods = (BiomeGenWoodlandBase) (new BiomeGenCherryBlossom(blossomWoodsId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightLowPlains).setBiomeName("Blossom Woods");
		BiomeGenManager.addWarmBiome(biomeBlossomWoods, blossomWoodsGen);
		if (villageBlossomWoods == true) 		{  BiomeManager.addVillageBiome(biomeBlossomWoods, true);  }	
		BiomeManager.addStrongholdBiome(biomeBlossomWoods);
		BiomeWoods.register(biomeBlossomWoods, EnhancedBiomesBlocks.planksEB, 14);

		biomeBlossomHills = (BiomeGenWoodlandBase) (new BiomeGenCherryBlossom(blossomHillsId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightLowHills).setBiomeName("Blossom Hills");
		if (villageBlossomHills == true) 		{  BiomeManager.addVillageBiome(biomeBlossomHills, true);  }	
		BiomeManager.addStrongholdBiome(biomeBlossomHills);
		BiomeWoods.register(biomeBlossomHills, EnhancedBiomesBlocks.planksEB, 14);
		
		biomeWoodLands = (BiomeGenWoodlandBase) (new BiomeGenWoodlands(woodLandsId)).setColor(9286496).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightDefault).setBiomeName("Woodlands");
		BiomeGenManager.addWarmBiome(biomeWoodLands, woodLandsGen);
		if (villageWoodLands == true) 			{  BiomeManager.addVillageBiome(biomeWoodLands, true);  }	
		BiomeManager.addStrongholdBiome(biomeWoodLands);
		BiomeWoods.register(biomeWoodLands, Blocks.planks, 0);
		
		biomeWoodLandHills = (BiomeGenWoodlandBase) (new BiomeGenWoodlands(woodLandHillsId)).setColor(9286496).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightLowHills).setBiomeName("Woodland Hills");
		if (villageWoodLandHills == true) 		{  BiomeManager.addVillageBiome(biomeWoodLandHills, true);  }	
		BiomeManager.addStrongholdBiome(biomeWoodLandHills);
		BiomeWoods.register(biomeWoodLandHills, Blocks.planks, 0);

		biomePineForest = (BiomeGenWoodlandBase) (new BiomeGenPineForest(pineForestId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.7F).setHeight(heightLowHills).setBiomeName("Pine Forest");
		BiomeGenManager.addCoolBiome(biomePineForest, pineForestGen);
		if (villagePineForest == true) 			{  BiomeManager.addVillageBiome(biomePineForest, true);  }	
		BiomeManager.addStrongholdBiome(biomePineForest);
		BiomeWoods.register(biomePineForest, EnhancedBiomesBlocks.planksEB, 6);
		
		biomeOakForest = (BiomeGenWoodlandBase) (new BiomeGenOakForest(oakForestId)).setColor(9286496).setTemperatureRainfall(0.6F, 0.7F).setHeight(heightMidPlains).setBiomeName("Oak Forest");
		BiomeGenManager.addWarmBiome(biomeOakForest, oakForestGen);
		if (villageOakForest == true) 			{  BiomeManager.addVillageBiome(biomeOakForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeOakForest);
		BiomeWoods.register(biomeOakForest, EnhancedBiomesBlocks.planksEB, 0);	
		
		biomeBorealForest = (BiomeGenWoodlandBase) (new BiomeGenBorealForest(borealForestId)).setColor(9286496).setTemperatureRainfall(0.5F, 0.6F).setHeight(heightHighPlains).setBiomeName("Boreal Forest");
		BiomeGenManager.addWarmBiome(biomeBorealForest, borealForestGen);
		if (villageBorealForest == true) 			{  BiomeManager.addVillageBiome(biomeBorealForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeBorealForest);
		BiomeWoods.register(biomeBorealForest, Blocks.planks, 1);	
		
		biomeKakadu = (BiomeGenWoodlandBase) (new BiomeGenKakadu(kakaduId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.8F, 0.8F).setHeight(heightMidPlains).setBiomeName("Kakadu");
		BiomeGenManager.addWarmBiome(biomeKakadu, kakaduGen);
		if (villageKakadu == true) 			{  BiomeManager.addVillageBiome(biomeKakadu, true);  }	
		BiomeManager.addStrongholdBiome(biomeKakadu);
		BiomeWoods.register(biomeKakadu, Blocks.planks, 0);

		biomeSilverPineForest = (BiomeGenWoodlandBase) (new BiomeGenSilverPineForest(silverPineForestId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.6F).setHeight(heightMidPlains).setBiomeName("Silver Pine Forest");
		BiomeGenManager.addWarmBiome(biomeSilverPineForest, silverPineForestGen);
		if (villageSilverPineForest == true) 			{  BiomeManager.addVillageBiome(biomeSilverPineForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeSilverPineForest);
		BiomeWoods.register(biomeSilverPineForest, EnhancedBiomesBlocks.planksEB, 7);

		biomeSilverPineHills = (BiomeGenWoodlandBase) (new BiomeGenSilverPineForest(silverPineHillsId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.6F).setHeight(heightLowHills).setBiomeName("Silver Pine Hills");
		if (villageSilverPineHills == true) 			{  BiomeManager.addVillageBiome(biomeSilverPineHills, true);  }	
		BiomeManager.addStrongholdBiome(biomeSilverPineHills);
		BiomeWoods.register(biomeSilverPineHills, EnhancedBiomesBlocks.planksEB, 7);

		biomeAspenForest = (BiomeGenWoodlandBase) (new BiomeGenAspenForest(aspenForestId)).setColor(9286496).setTemperatureRainfall(0.2F, 0.6F).setHeight(heightLowPlains).setBiomeName("Aspen Forest");
		BiomeGenManager.addCoolBiome(biomeAspenForest, aspenForestGen);
		if (villageAspenForest == true) 			{  BiomeManager.addVillageBiome(biomeAspenForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeAspenForest);
		BiomeWoods.register(biomeAspenForest, EnhancedBiomesBlocks.planksEB, 10);

		biomeAspenHills = (BiomeGenWoodlandBase) (new BiomeGenAspenForest(aspenHillsId)).setColor(9286496).setTemperatureRainfall(0.2F, 0.6F).setHeight(heightLowHills).setBiomeName("Aspen Hills");
		if (villageAspenHills == true) 			{  BiomeManager.addVillageBiome(biomeAspenHills, true);  }	
		BiomeManager.addStrongholdBiome(biomeAspenHills);
		BiomeWoods.register(biomeAspenHills, EnhancedBiomesBlocks.planksEB, 10);

		biomeCypressForest = (BiomeGenWoodlandBase) (new BiomeGenCypressForest(cypressForestId)).setColor(9286496).setTemperatureRainfall(0.6F, 0.7F).setHeight(heightLowHills).setBiomeName("Cypress Forest");
		BiomeGenManager.addWarmBiome(biomeCypressForest, cypressForestGen);
		if (villageCypressForest == true) 			{  BiomeManager.addVillageBiome(biomeCypressForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeCypressForest);
		BiomeWoods.register(biomeCypressForest, EnhancedBiomesBlocks.planksEB, 5);

		biomeShield = (BiomeGenWoodlandBase) (new BiomeGenShield(shieldId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.6F).setHeight(heightMidPlains).setBiomeName("Shield");
		BiomeGenManager.addCoolBiome(biomeShield, shieldGen);
		if (villageShield == true) 			{  BiomeManager.addVillageBiome(biomeShield, true);  }	
		BiomeManager.addStrongholdBiome(biomeShield);
		BiomeWoods.register(biomeShield, EnhancedBiomesBlocks.planksEB, 4);
		
		biomeForestMountains = (BiomeGenWoodlandBase) (new BiomeGenWoodlands(forestMountainsId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightHighPlateaus).setBiomeName("Forested Mountains");    		
		if (villageForestMountains == true) 	{  BiomeManager.addVillageBiome(biomeForestMountains, true);  }
		BiomeManager.addStrongholdBiome(biomeForestMountains);	
		BiomeWoods.register(biomeForestMountains, Blocks.planks, 0);
		
		biomeWoodlandField = (BiomeGenWoodlandBase) (new BiomeGenWoodlands(woodlandFieldId)).setColor(9286496).setTemperatureRainfall(0.8F, 0.4F).setHeight(heightMidPlains).setBiomeName("Woodland Field");
		biomeWoodlandField.theBiomeDecorator.treesPerChunk = 1;
		BiomeGenManager.addWarmBiome(biomeWoodlandField, woodlandFieldGen);
		if (villageWoodlandField == true) 			{  BiomeManager.addVillageBiome(biomeWoodlandField, true);  }
		BiomeWoods.register(biomeWoodlandField, Blocks.planks, 0);	
		
		biomeBorealPlateau = (BiomeGenWoodlandBase) (new BiomeGenBorealForest(borealPlateauId)).setColor(9286496).setTemperatureRainfall(0.5F, 0.6F).setHeight(heightHighPlateaus).setBiomeName("Boreal Plateau");
		BiomeGenManager.addWarmBiome(biomeBorealPlateau, borealPlateauGen);
		if (villageBorealPlateau == true) 			{  BiomeManager.addVillageBiome(biomeBorealPlateau, true);  }	
		BiomeManager.addStrongholdBiome(biomeBorealPlateau);	
		BiomeWoods.register(biomeBorealPlateau, Blocks.planks, 1);
		
		biomeAlpineEdge = (BiomeGenSnowForestBase) (new BiomeGenAlpineEdge(alpineEdgeId)).setColor(7501978).func_76733_a(5470985).setTemperatureRainfall(0.2F, 0.3F).setHeight(heightMidHills).setBiomeName("Alpine Mountains Edge");
		if (villageAlpineEdge == true)			{  BiomeManager.addVillageBiome(biomeAlpineEdge, true);  }
		BiomeManager.addStrongholdBiome(biomeAlpineEdge);
		BiomeWoods.register(biomeAlpineEdge, EnhancedBiomesBlocks.planksEB, 6);
		
		biomeFirForest = (BiomeGenSnowForestBase) (new BiomeGenFirForest(firForestId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.3F).setHeight(heightHighPlains).setBiomeName("Fir Forest");
		BiomeGenManager.addCoolBiome(biomeFirForest, firForestGen);
		if (villageFirForest == true)			{  BiomeManager.addVillageBiome(biomeFirForest, true);  }
		BiomeManager.addStrongholdBiome(biomeFirForest);	
		BiomeWoods.register(biomeFirForest, EnhancedBiomesBlocks.planksEB, 4);	
		
		biomeBorealPlateauM = (BiomeGenWoodlandBase) (new BiomeGenBorealForest(borealPlateauMId)).setColor(9286496).setTemperatureRainfall(0.2F, 0.6F).setHeight(heightHighPlateaus).setBiomeName("Boreal Plateau M");
		if (villageBorealPlateau == true) 			{  BiomeManager.addVillageBiome(biomeBorealPlateauM, true);  }	
		BiomeManager.addStrongholdBiome(biomeBorealPlateauM);	
		BiomeWoods.register(biomeBorealPlateauM, Blocks.planks, 1);
		
		biomeForestValley = (BiomeGenWoodlandBase) (new BiomeGenWoodlands(forestValleyId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightMidPlains).setBiomeName("Forested Valley");    		
		if (villageForestValley == true) 	{  BiomeManager.addVillageBiome(biomeForestValley, true);  }
		BiomeManager.addStrongholdBiome(biomeForestValley);	
		BiomeWoods.register(biomeForestValley, Blocks.planks, 0);
		
		EnhancedBiomesTropical.load();
	}
}