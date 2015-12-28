package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenTropicalBase;
import enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis;
import enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EnhancedBiomesTropical
{
	public static int oasisId;
	public static boolean villageOasis;	  
	public static BiomeGenTropicalBase biomeOasis;
	
	public static int rainforestId;	  
	public static int rainforestGen;
	public static boolean villageRainforest; 
	public static BiomeGenTropicalBase biomeRainforest; 	
	
	public static int rainforestValleyId; 
	public static boolean villageRainforestValley;
	public static BiomeGenTropicalBase biomeRainforestValley; 	
	 
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		oasisId = config.get(config.CATEGORY_GENERAL, "Biome ID of Oasis", BiomeIDs.oasis).getInt();
		villageOasis = config.get(config.CATEGORY_GENERAL, "Generate villages in Oasis biome", true).getBoolean(true);
		
		rainforestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Rainforest", BiomeIDs.rainforest).getInt();
		rainforestGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Rainforest biome", 10).getInt();
		villageRainforest = config.get(config.CATEGORY_GENERAL, "Generate villages in Rainforest biome", true).getBoolean(true);
		
		rainforestValleyId = config.get(config.CATEGORY_GENERAL, "Biome ID of Rainforest Valley", BiomeIDs.rainforestValley).getInt();
		villageRainforestValley = config.get(config.CATEGORY_GENERAL, "Generate villages in Rainforest Valley biome", true).getBoolean(true);
		
		config.save();
	}
	
	public static void load()
	{
		biomeOasis = (BiomeGenTropicalBase) (new BiomeGenOasis(oasisId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.6F)).setHeight(heightDefault).setBiomeName("Oasis");													
		if (villageOasis == true) 				{  BiomeManager.addVillageBiome(biomeOasis, true);  }
		BiomeManager.addStrongholdBiome(biomeOasis);
		BiomeWoods.register(biomeOasis, Blocks.planks, 3);
		
		biomeRainforest = (BiomeGenTropicalBase) (new BiomeGenRainforest(rainforestId)).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F).setHeight(heightLowPlateaus).setBiomeName("Rainforest");
		BiomeGenManager.addWarmBiome(biomeRainforest, rainforestGen);	
		if (villageRainforest == true) 			{  BiomeManager.addVillageBiome(biomeRainforest, true);  }	
		BiomeManager.addStrongholdBiome(biomeRainforest);
		BiomeWoods.register(biomeRainforest, Blocks.planks, 3);
		
		biomeRainforestValley = (BiomeGenTropicalBase) (new BiomeGenRainforest(rainforestValleyId)).setColor(2900485).func_76733_a(5470985).setTemperatureRainfall(0.95F, 1.2F).setHeight(heightPartiallySubmerged).setBiomeName("Rainforest Valley");
		if (villageRainforestValley == true) 	{  BiomeManager.addVillageBiome(biomeRainforestValley, true);  }	
		BiomeManager.addStrongholdBiome(biomeRainforestValley);
		BiomeWoods.register(biomeRainforestValley, Blocks.planks, 3);
	}
}