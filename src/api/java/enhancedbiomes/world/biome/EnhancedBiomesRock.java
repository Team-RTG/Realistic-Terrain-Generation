package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenRockBase;
import enhancedbiomes.world.biome.base.BiomeGenSandstoneBase;
import enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin;
import enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills;
import enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon;
import enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano;
import enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands;
import enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

public class EnhancedBiomesRock
{	 
	public static int volcanoId;
	public static boolean villageVolcano; 
	public static BiomeGenRockBase biomeVolcano;
	
	public static int wasteLandsId;
	public static int wasteLandsGen;
	public static boolean villageWasteLands;  
	public static BiomeGenRockBase biomeWasteLands;
	
	public static int rockHillsId;	 
	public static int rockHillsGen; 
	public static boolean villageRockHills;	 
	public static BiomeGenRockBase biomeRockHills;	
	
	public static int basinId;
	public static boolean villageBasin; 
	public static BiomeGenRockBase biomeBasin; 
	
	public static int stoneCanyonId;
	public static boolean villageStoneCanyon; 
	public static BiomeGenRockBase biomeStoneCanyon;
	
	public static int stoneGorgeId;
	public static boolean villageStoneGorge; 	  
	public static BiomeGenRockBase biomeStoneGorge; 
	
	public static int volcanoMId;
	public static boolean villageVolcanoM; 
	public static BiomeGenRockBase biomeVolcanoM;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		volcanoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Volcano", BiomeIDs.volcano).getInt();
		villageVolcano = config.get(config.CATEGORY_GENERAL, "Generate villages in Volcano biome", false).getBoolean(false);
		
		wasteLandsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Wastelands", BiomeIDs.wastelands).getInt();
		wasteLandsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Wastelands biome", 10).getInt();
		villageWasteLands = config.get(config.CATEGORY_GENERAL, "Generate villages in Wastelands biome", true).getBoolean(true);
		
		rockHillsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Rocky Hills", BiomeIDs.rockyHills).getInt();
		rockHillsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Rocky Hills biome", 10).getInt();
		villageRockHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Rocky Hills biome", true).getBoolean(true);
		
		basinId = config.get(config.CATEGORY_GENERAL, "Biome ID of Basin", BiomeIDs.basin).getInt();		
		villageBasin = config.get(config.CATEGORY_GENERAL, "Generate villages in Basin biome", true).getBoolean(true);
		
		stoneCanyonId = config.get(config.CATEGORY_GENERAL, "Biome ID of Stone Canyon", BiomeIDs.stoneCanyons).getInt();
		villageStoneCanyon = config.get(config.CATEGORY_GENERAL, "Generate villages in Stone Canyon biome", true).getBoolean(true);
		
		stoneGorgeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Stone Canyon 2", BiomeIDs.stoneCanyon).getInt();
		villageStoneGorge = config.get(config.CATEGORY_GENERAL, "Generate villages in Stone Canyon 2 biome", true).getBoolean(true);
		
		volcanoMId = config.get(config.CATEGORY_GENERAL, "Biome ID of Volcano M", BiomeIDs.volcanoM).getInt();
		villageVolcanoM = config.get(config.CATEGORY_GENERAL, "Generate villages in Volcano M biome", false).getBoolean(false);
		
		config.save();
	}
	
	public static void load()
	{			
		biomeVolcano = (BiomeGenRockBase) (new BiomeGenVolcano(volcanoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.0F)).setHeight(heightMidHills).setDisableRain().setBiomeName("Volcano");
		if (villageVolcano) 			{  BiomeManager.addVillageBiome(biomeVolcano, true);  }
		BiomeManager.addStrongholdBiome(biomeVolcano);	
		BiomeWoods.register(biomeVolcano, EnhancedBiomesBlocks.planksEB, 13, false);
		
		biomeWasteLands = (BiomeGenRockBase) (new BiomeGenWasteLands(wasteLandsId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.0F)).setHeight(heightDefault).setDisableRain().setBiomeName("Wastelands");
		BiomeGenManager.addHotBiome(biomeWasteLands, wasteLandsGen);  		  
		if (villageWasteLands) 			{  BiomeManager.addVillageBiome(biomeWasteLands, true);  }
		BiomeManager.addStrongholdBiome(biomeWasteLands);
		BiomeWoods.register(biomeWasteLands, EnhancedBiomesBlocks.planksEB, 13);		  
		
		biomeRockHills = (BiomeGenRockBase) (new BiomeGenRockHills(rockHillsId)).setColor(9286496).setTemperatureRainfall(0.25F, 0.4F).setHeight(heightMidHills).setBiomeName("Rocky Hills");
		BiomeGenManager.addCoolBiome(biomeRockHills, rockHillsGen);		  
		if (villageRockHills) 			{  BiomeManager.addVillageBiome(biomeRockHills, true);  }
		BiomeManager.addStrongholdBiome(biomeRockHills);	
		BiomeWoods.register(biomeRockHills, EnhancedBiomesBlocks.planksEB, 13, false);

		biomeBasin = (BiomeGenRockBase) (new BiomeGenBasin(basinId)).setColor(9286496).setTemperatureRainfall(0.7F, 0.7F).setHeight(heightMidPlains).setBiomeName("Basin");
		if (villageBasin) 				{  BiomeManager.addVillageBiome(biomeBasin, true);  }
		BiomeManager.addStrongholdBiome(biomeBasin);
		BiomeWoods.register(biomeBasin, EnhancedBiomesBlocks.planksEB, 13, false);
		
		biomeStoneCanyon = (BiomeGenRockBase) (new BiomeGenStoneCanyon(stoneCanyonId)).setColor(9286496).setDisableRain().setTemperatureRainfall(1.5F, 0.0F).setHeight(heightMidPlateaus).setBiomeName("Stone Canyons");
		if (villageStoneCanyon) 	{  BiomeManager.addVillageBiome(biomeStoneCanyon, true);  }
		BiomeManager.addStrongholdBiome(biomeStoneCanyon);
		BiomeWoods.register(biomeStoneCanyon, Blocks.planks, 2);
		
		biomeStoneGorge = (BiomeGenRockBase) (new BiomeGenStoneCanyon(stoneGorgeId)).setColor(9286496).setDisableRain().setTemperatureRainfall(1.0F, 0.05F).setHeight(heightShallowWaters).setBiomeName("Stone Canyon");
		if (villageStoneGorge) 		{  BiomeManager.addVillageBiome(biomeStoneGorge, true);  }
		BiomeManager.addStrongholdBiome(biomeStoneGorge);	
		BiomeWoods.register(biomeStoneGorge, Blocks.planks, 2);

		biomeVolcanoM = (BiomeGenRockBase) (new BiomeGenVolcano(volcanoMId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.0F)).setHeight(heightMidPlains).setDisableRain().setBiomeName("Volcano M");
		if (villageVolcanoM) 			{  BiomeManager.addVillageBiome(biomeVolcanoM, true);  }
		BiomeManager.addStrongholdBiome(biomeVolcanoM);	
		BiomeWoods.register(biomeVolcanoM, EnhancedBiomesBlocks.planksEB, 13, false);
	}
}