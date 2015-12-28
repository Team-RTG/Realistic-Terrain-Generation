package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.helpers.EBHeights;
import enhancedbiomes.world.biome.base.BiomeGenRockBase;
import enhancedbiomes.world.biome.base.BiomeGenSandstoneBase;
import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import enhancedbiomes.world.biome.snow.BiomeGenAlpine;
import enhancedbiomes.world.biome.snow.BiomeGenAlpineM;
import enhancedbiomes.world.biome.snow.BiomeGenGlacier;
import enhancedbiomes.world.biome.snow.BiomeGenIceSheet;
import enhancedbiomes.world.biome.snow.BiomeGenPolarDesert;
import enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert;
import enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau;
import enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges;
import enhancedbiomes.world.biome.snow.BiomeGenTundra;
import enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands;
import enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;

public class EnhancedBiomesSnow
{
	public static int alpineId;
	public static int alpineGen;
	public static boolean villageAlpine;
	public static BiomeGenSnowBase biomeAlpine;	  
	
	public static int glacierId; 	  
	public static boolean villageGlacier;
	public static BiomeGenSnowBase biomeGlacier; 	  
	
	public static int iceSheetId;	  
	public static boolean villageIceSheet;
	public static BiomeGenSnowBase biomeIceSheet;	  
	
	public static int tundraId; 
	public static int tundraGen;  
	public static boolean villageTundra; 	
	public static BiomeGenSnowBase biomeTundra;  
	
	public static int plateauSnowId;
	public static int plateauSnowGen;
	public static boolean villagePlateauSnow;
	public static BiomeGenSnowBase biomePlateauSnow;
	
	public static int snowDesertId;   
	public static boolean villageSnowDesert; 	
	public static BiomeGenSnowBase biomeSnowDesert;   
	
	public static int polarDesertId;	 
	public static int polarDesertGen;
	public static boolean villagePolarDesert;
	public static BiomeGenSnowBase biomePolarDesert;	
	
	public static int wasteLandsSnowyId;
	public static boolean villageWasteLandsSnowy;  
	public static BiomeGenRockBase biomeWasteLandsSnowy; 

	public static int alpineMId;
	public static boolean villageAlpineM;
	public static BiomeGenSnowBase biomeAlpineM;	  
	
	/*public static int driftsId; 
	public static int driftsGen;  
	public static boolean villageDrifts; 	
	public static BiomeGenSnowBase biomeDrifts; */ 
	
	public static int snowyRangesId;
	public static int snowyRangesGen;
	public static boolean villageSnowyRanges;
	public static BiomeGenSnowBase biomeSnowyRanges;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();
		
		alpineId = config.get(config.CATEGORY_GENERAL, "Biome ID of Alpine Mountains", BiomeIDs.alpineMountains).getInt();
		alpineGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Alpine Mountains biome", 10).getInt();
		villageAlpine = config.get(config.CATEGORY_GENERAL, "Generate villages in Alpine Mountains biome", true).getBoolean(true);
		
		glacierId = config.get(config.CATEGORY_GENERAL, "Biome ID of Glacier", BiomeIDs.glacier).getInt();
		villageGlacier = config.get(config.CATEGORY_GENERAL, "Generate villages in Glacier biome", true).getBoolean(true);
		
		iceSheetId = config.get(config.CATEGORY_GENERAL, "Biome ID of Ice Sheet", BiomeIDs.iceSheet).getInt();
		villageIceSheet = config.get(config.CATEGORY_GENERAL, "Generate villages in Ice Sheet biome", true).getBoolean(true);
		
		tundraId = config.get(config.CATEGORY_GENERAL, "Biome ID of Tundra", BiomeIDs.tundra).getInt();
		tundraGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Tundra biome", 10).getInt();
		villageTundra = config.get(config.CATEGORY_GENERAL, "Generate villages in Tundra biome", true).getBoolean(true);
		
		plateauSnowId = config.get(config.CATEGORY_GENERAL, "Biome ID of Snowy Plateau", BiomeIDs.snowyPlateau).getInt();
		plateauSnowGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Snowy Plateau biome", 10).getInt();
		villagePlateauSnow = config.get(config.CATEGORY_GENERAL, "Generate villages in Snowy Plateau biome", true).getBoolean(true);
		
		snowDesertId = config.get(config.CATEGORY_GENERAL, "Biome ID of Snowy Desert", BiomeIDs.snowyDesert).getInt();
		villageSnowDesert = config.get(config.CATEGORY_GENERAL, "Generate villages in Snowy Desert biome", true).getBoolean(true);

		polarDesertId = config.get(config.CATEGORY_GENERAL, "Biome ID of Polar Desert", BiomeIDs.polarDesert).getInt();
		polarDesertGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Polar Desert biome", 10).getInt();
		villagePolarDesert = config.get(config.CATEGORY_GENERAL, "Generate villages in Polar Desert biome", true).getBoolean(true);
		
		wasteLandsSnowyId = config.get(config.CATEGORY_GENERAL, "Biome ID of Snowy Wastelands", BiomeIDs.snowyWastelands).getInt();
		villageWasteLandsSnowy = config.get(config.CATEGORY_GENERAL, "Generate villages in Snowy Wastelands biome", true).getBoolean(true);
		
		alpineMId = config.get(config.CATEGORY_GENERAL, "Biome ID of Alpine Mountains M", BiomeIDs.alpineMountainsM).getInt();
		villageAlpineM = config.get(config.CATEGORY_GENERAL, "Generate villages in Alpine Mountains M biome", true).getBoolean(true);
		
		/*driftsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Drifts", BiomeIDs.drifts).getInt();
		driftsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Drifts biome", 10).getInt();
		villageDrifts = config.get(config.CATEGORY_GENERAL, "Generate villages in Drifts biome", true).getBoolean(true);*/

		snowyRangesId = config.get(config.CATEGORY_GENERAL, "Biome ID of Snowy Ranges", BiomeIDs.snowyRanges).getInt();
		snowyRangesGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Snowy Ranges biome", 10).getInt();
		villageSnowyRanges = config.get(config.CATEGORY_GENERAL, "Generate villages in Snowy Ranges biome", true).getBoolean(true);
		
		config.save();

		EnhancedBiomesSnowForest.config();
	}
	
	public static void load()
	{
		biomeAlpine = (BiomeGenSnowBase) (new BiomeGenAlpine(alpineId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.3F).setHeight(heightHighHills).setEnableSnow().setBiomeName("Alpine Mountains");
		BiomeGenManager.addFrozenBiome(biomeAlpine, alpineGen);		  
		if (villageAlpine)				{  BiomeManager.addVillageBiome(biomeAlpine, true);  }
		BiomeManager.addStrongholdBiome(biomeAlpine);
		BiomeWoods.register(biomeAlpine, EnhancedBiomesBlocks.planksEB, 6);
		
		biomeGlacier = (BiomeGenSnowBase) (new BiomeGenGlacier(glacierId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.0F)).setHeight(heightMidPlateaus).setEnableSnow().setBiomeName("Glacier");
		if (villageGlacier)				{  BiomeManager.addVillageBiome(biomeGlacier, true);  }	
		BiomeManager.addStrongholdBiome(biomeGlacier);
		BiomeWoods.register(biomeGlacier, EnhancedBiomesBlocks.planksEB, 6);
		
		biomeIceSheet = (BiomeGenSnowBase) (new BiomeGenIceSheet(iceSheetId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.0F)).setHeight(heightSeaPlateaus).setEnableSnow().setBiomeName("Ice Sheet");
		if (villageIceSheet)			{  BiomeManager.addVillageBiome(biomeIceSheet, true);  }
		BiomeManager.addStrongholdBiome(biomeIceSheet);
		BiomeWoods.register(biomeIceSheet, Blocks.planks, 1);
		
		biomeTundra = (BiomeGenSnowBase) (new BiomeGenTundra(tundraId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.5F).setHeight(heightSeaPlateaus).setEnableSnow().setBiomeName("Tundra");
		BiomeGenManager.addFrozenBiome(biomeTundra, tundraGen);
		if (villageTundra)				{  BiomeManager.addVillageBiome(biomeTundra, true);  }
		BiomeManager.addStrongholdBiome(biomeTundra);
		BiomeWoods.register(biomeTundra, Blocks.planks, 1);
		
		biomePlateauSnow = (BiomeGenSnowBase) (new BiomeGenSnowyPlateau(plateauSnowId)).setColor(9286496).setTemperatureRainfall(0.0F, 0.8F).setHeight(heightMidPlateaus).setBiomeName("Snowy Plateau");
		BiomeGenManager.addFrozenBiome(biomePlateauSnow, plateauSnowGen);
		if (villagePlateauSnow)			{  BiomeManager.addVillageBiome(biomePlateauSnow, true);  }
		BiomeManager.addStrongholdBiome(biomePlateauSnow);
		BiomeWoods.register(biomePlateauSnow, EnhancedBiomesBlocks.planksEB, 4);	
		
		biomeSnowDesert = (BiomeGenSnowBase) (new BiomeGenSnowyDesert(snowDesertId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.0F).setHeight(heightDefault).setEnableSnow().setBiomeName("Snowy Desert");
		if (villageSnowDesert)				{  BiomeManager.addVillageBiome(biomeSnowDesert, true);  }
		BiomeManager.addStrongholdBiome(biomeSnowDesert);	
		BiomeWoods.register(biomeSnowDesert, EnhancedBiomesBlocks.planksEB, 13);

		biomePolarDesert = (BiomeGenSnowBase) (new BiomeGenPolarDesert(polarDesertId).setDisableRain().setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.0F)).setHeight(heightDefault).setBiomeName("Polar Desert");
		BiomeGenManager.addFrozenBiome(biomePolarDesert, polarDesertGen);	
		if (villagePolarDesert)			{  BiomeManager.addVillageBiome(biomePolarDesert, true);  }
		BiomeManager.addStrongholdBiome(biomePolarDesert);		
		BiomeWoods.register(biomePolarDesert, EnhancedBiomesBlocks.planksEB, 13);
		//BiomeGenManager.addCaveExceptionBiome(biomePolarDesert);	
		
		biomeWasteLandsSnowy = (BiomeGenRockBase) (new BiomeGenWasteLands(wasteLandsSnowyId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.2F)).setHeight(heightDefault).setBiomeName("Snowy Wastelands");
		if (villageWasteLandsSnowy) 			{  BiomeManager.addVillageBiome(biomeWasteLandsSnowy, true);  }
		BiomeManager.addStrongholdBiome(biomeWasteLandsSnowy);	
		BiomeWoods.register(biomeWasteLandsSnowy, EnhancedBiomesBlocks.planksEB, 13);
		
		biomeAlpineM = (BiomeGenSnowBase) (new BiomeGenAlpineM(alpineMId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.3F).setHeight(heightHighHills).setEnableSnow().setBiomeName("Alpine Mountains M");
		if (villageAlpineM)				{  BiomeManager.addVillageBiome(biomeAlpineM, true);  }
		BiomeManager.addStrongholdBiome(biomeAlpineM);
		BiomeWoods.register(biomeAlpineM, EnhancedBiomesBlocks.planksEB, 6);
		
		/*biomeDrifts = (BiomeGenSnowBase) (new BiomeGenDrifts(driftsId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.7F).setHeight(heightDefault).setEnableSnow().setBiomeName("Drifts");
		registerBiomeType(biomeDrifts, Type.FROZEN);							
		BiomeGenManager.addFrozenBiome(biomeDrifts, driftsGen);
		if (villageDrifts)				{  BiomeManager.addVillageBiome(biomeDrifts, true);  }
		BiomeManager.addStrongholdBiome(biomeDrifts);
		BiomeWoods.register(biomeDrifts, EnhancedBiomesBlocks.planksEB, 4);*/
		
		biomeSnowyRanges = (BiomeGenSnowBase) (new BiomeGenSnowyRanges(snowyRangesId)).setColor(6316128).func_76733_a(5470985).setTemperatureRainfall(0.25F, 0.3F).setHeight(heightHighSlopes).setBiomeName("Snowy Ranges");
		BiomeGenManager.addFrozenBiome(biomeSnowyRanges, snowyRangesGen);
		if (villageSnowyRanges == true) 	{  BiomeManager.addVillageBiome(biomeSnowyRanges, true);  }
		BiomeManager.addStrongholdBiome(biomeSnowyRanges);
		BiomeWoods.register(biomeSnowyRanges, EnhancedBiomesBlocks.planksEB, 6);
		
		EnhancedBiomesSnowForest.load();
	}
}