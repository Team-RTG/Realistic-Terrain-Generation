package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenSandBase;
import enhancedbiomes.world.biome.sand.BiomeGenRedDesert;
import enhancedbiomes.world.biome.sand.BiomeGenRockyDesert;
import enhancedbiomes.world.biome.sand.BiomeGenSahara;
import enhancedbiomes.world.biome.sand.BiomeGenScrub;
import enhancedbiomes.world.biome.sand.BiomeGenXericShrubland;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EnhancedBiomesSand 
{	
	public static int rockyDesertId;	 
	public static int rockyDesertGen;
	public static boolean villageRockyDesert;
	public static BiomeGenSandBase biomeRockyDesert;	

	public static int scrubId;
	public static int scrubGen;
	public static boolean villageScrub;
	public static BiomeGenSandBase biomeScrub;

	public static int saharaId;
	public static int saharaGen;
	public static boolean villageSahara;
	public static BiomeGenSandBase biomeSahara;
	
	public static int redDesertId;	 
	public static int redDesertGen;
	public static boolean villageRedDesert;
	public static BiomeGenSandBase biomeRedDesert;

	public static int xericShrublandId;
	public static int xericShrublandGen;
	public static boolean villageXericShrubland;
	public static BiomeGenSandBase biomeXericShrubland;	

	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		rockyDesertId = config.get(config.CATEGORY_GENERAL, "Biome ID of Rocky Desert", BiomeIDs.rockyDesert).getInt();
		rockyDesertGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Rocky Desert biome", 10).getInt();
		villageRockyDesert = config.get(config.CATEGORY_GENERAL, "Generate villages in Rocky Desert biome", true).getBoolean(true);

		scrubId = config.get(config.CATEGORY_GENERAL, "Biome ID of Scrub", BiomeIDs.scrub).getInt();
		scrubGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Scrub biome", 10).getInt();
		villageScrub = config.get(config.CATEGORY_GENERAL, "Generate villages in Scrub biome", true).getBoolean(true);
		
		saharaId = config.get(config.CATEGORY_GENERAL, "Biome ID of Sahara", BiomeIDs.sahara).getInt();
		saharaGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Sahara biome", 10).getInt();
		villageSahara = config.get(config.CATEGORY_GENERAL, "Generate villages in Sahara biome", true).getBoolean(true);
		
		redDesertId = config.get(config.CATEGORY_GENERAL, "Biome ID of Red Desert", BiomeIDs.redDesert).getInt();
		redDesertGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Red Desert biome", 10).getInt();
		villageRedDesert = config.get(config.CATEGORY_GENERAL, "Generate villages in Red Desert biome", true).getBoolean(true);

		xericShrublandId = config.get(config.CATEGORY_GENERAL, "Biome ID of Xeric Shrubland", BiomeIDs.xericShrubland).getInt();
		xericShrublandGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Xeric Shrubland biome", 10).getInt();
		villageXericShrubland = config.get(config.CATEGORY_GENERAL, "Generate villages in Xeric Shrubland biome", true).getBoolean(true);

		config.save();
	}
	
	public static void load()
	{		
		biomeRockyDesert = (BiomeGenSandBase) (new BiomeGenRockyDesert(rockyDesertId).setDisableRain().setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.8F, 0.0F)).setHeight(heightDefault).setBiomeName("Rocky Desert");
		BiomeGenManager.addHotBiome(biomeRockyDesert, rockyDesertGen);	
		if (villageRockyDesert)			{  BiomeManager.addVillageBiome(biomeRockyDesert, true);  }
		BiomeManager.addStrongholdBiome(biomeRockyDesert);		
		//BiomeGenManager.addCaveExceptionBiome(biomeRockyDesert);
		BiomeWoods.register(biomeRockyDesert, EnhancedBiomesBlocks.planksEB, 13, false);
		
		biomeScrub = (BiomeGenSandBase) (new BiomeGenScrub(scrubId)).setDisableRain().setColor(9286496).setTemperatureRainfall(1.0F, 0.25F).setHeight(heightDefault).setBiomeName("Scrub");
		BiomeGenManager.addHotBiome(biomeScrub, scrubGen);
		if (villageScrub)				{  BiomeManager.addVillageBiome(biomeScrub, true);  }
		BiomeManager.addStrongholdBiome(biomeScrub);
		BiomeWoods.register(biomeScrub, EnhancedBiomesBlocks.planksEB, 13, false);
		
		biomeSahara = (BiomeGenSandBase) (new BiomeGenSahara(saharaId)).setDisableRain().setColor(9286496).setTemperatureRainfall(2.0F, 0.0F).setHeight(heightDefault).setBiomeName("Sahara");
		BiomeGenManager.addHotBiome(biomeSahara, saharaGen);
		if (villageSahara)				{  BiomeManager.addVillageBiome(biomeSahara, true);  }
		BiomeManager.addStrongholdBiome(biomeSahara);
		//BiomeGenManager.addCaveExceptionBiome(biomeSahara);
		BiomeWoods.register(biomeSahara, EnhancedBiomesBlocks.planksEB, 13, false);
		
		biomeRedDesert = (BiomeGenSandBase) (new BiomeGenRedDesert(redDesertId).setDisableRain().setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.8F, 0.0F)).setHeight(heightDefault).setBiomeName("Red Desert");
		BiomeGenManager.addHotBiome(biomeRedDesert, redDesertGen);	
		if (villageRedDesert)			{  BiomeManager.addVillageBiome(biomeRedDesert, true);  }
		BiomeManager.addStrongholdBiome(biomeRedDesert);
		BiomeWoods.register(biomeRedDesert, EnhancedBiomesBlocks.planksEB, 13, false);
		
		biomeXericShrubland = (BiomeGenSandBase) (new BiomeGenXericShrubland(xericShrublandId)).setDisableRain().setColor(9286496).setTemperatureRainfall(1.0F, 0.25F).setHeight(heightDefault).setBiomeName("Xeric Shrubland");
		BiomeGenManager.addHotBiome(biomeXericShrubland, xericShrublandGen);
		if (villageXericShrubland)				{  BiomeManager.addVillageBiome(biomeXericShrubland, true);  }
		BiomeManager.addStrongholdBiome(biomeXericShrubland);
		BiomeWoods.register(biomeXericShrubland, Blocks.planks, 0, false);
	}
}
