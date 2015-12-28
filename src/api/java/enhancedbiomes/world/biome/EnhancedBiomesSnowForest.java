package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenSnowForestBase;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest;
import enhancedbiomes.world.biome.woodland.BiomeGenBorealForest;
import enhancedbiomes.world.biome.woodland.BiomeGenCypressForest;
import enhancedbiomes.world.biome.woodland.BiomeGenPineForest;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EnhancedBiomesSnowForest
{		
	public static int coldPineForestId;
	public static boolean villageColdPineForest;
	public static BiomeGenWoodlandBase biomeColdPineForest;

	public static int coldFirForestId;
	public static boolean villageColdFirForest;
	public static BiomeGenSnowForestBase biomeColdFirForest;

	public static int coldCypressForestId;
	public static boolean villageColdCypressForest;
	public static BiomeGenWoodlandBase biomeColdCypressForest;

	public static int coldBorealForestId;
	public static boolean villageColdBorealForest;
	public static BiomeGenWoodlandBase biomeColdBorealForest;

	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();
		
		coldPineForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Cold Pine Forest", BiomeIDs.coldPineForest).getInt();
		villageColdPineForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Cold Pine Forest biome", true).getBoolean(true);
		
		coldFirForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Cold Fir Forest", BiomeIDs.coldFirForest).getInt();
		villageColdFirForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Cold Fir Forest biome", true).getBoolean(true);
		
		coldCypressForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Cold Cypress Forest", BiomeIDs.coldCypressForest).getInt();
		villageColdCypressForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Cold Cypress Forest biome", true).getBoolean(true);
		
		coldBorealForestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Cold Boreal Forest", BiomeIDs.coldBorealForest).getInt();
		villageColdBorealForest = config.get(config.CATEGORY_GENERAL, "Generate villages in Cold Boreal Forest biome", true).getBoolean(true);
		
		config.save();
	}
	
	public static void load()
	{		
		biomeColdPineForest = (BiomeGenWoodlandBase) (new BiomeGenPineForest(coldPineForestId)).setColor(9286496).setTemperatureRainfall(0.0F, 0.7F).setHeight(heightLowHills).setBiomeName("Cold Pine Forest");
		if (villageColdPineForest) 			{  BiomeManager.addVillageBiome(biomeColdPineForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeColdPineForest);
		BiomeWoods.register(biomeColdPineForest, EnhancedBiomesBlocks.planksEB, 6);

		biomeColdFirForest = (BiomeGenSnowForestBase) (new BiomeGenFirForest(coldFirForestId)).setColor(9286496).setTemperatureRainfall(0.0F, 0.3F).setHeight(heightHighPlains).setBiomeName("Cold Fir Forest");
		if (villageColdFirForest) 			{  BiomeManager.addVillageBiome(biomeColdFirForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeColdFirForest);	
		BiomeWoods.register(biomeColdFirForest, EnhancedBiomesBlocks.planksEB, 4);

		biomeColdCypressForest = (BiomeGenWoodlandBase) (new BiomeGenCypressForest(coldCypressForestId)).setColor(9286496).setTemperatureRainfall(0.0F, 0.7F).setHeight(heightLowHills).setBiomeName("Cold Cypress Forest");
		if (villageColdCypressForest) 			{  BiomeManager.addVillageBiome(biomeColdCypressForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeColdCypressForest);
		BiomeWoods.register(biomeColdCypressForest, EnhancedBiomesBlocks.planksEB, 5);	

		biomeColdBorealForest = (BiomeGenWoodlandBase) (new BiomeGenBorealForest(coldBorealForestId)).setColor(9286496).setTemperatureRainfall(0.0F, 0.6F).setHeight(heightHighPlains).setBiomeName("Cold Boreal Forest");
		if (villageColdBorealForest) 			{  BiomeManager.addVillageBiome(biomeColdBorealForest, true);  }	
		BiomeManager.addStrongholdBiome(biomeColdBorealForest);	
		BiomeWoods.register(biomeColdBorealForest, Blocks.planks, 1);
	}
}