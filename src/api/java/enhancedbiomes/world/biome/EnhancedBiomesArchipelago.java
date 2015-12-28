package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago;
import enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago;
import enhancedbiomes.world.biome.base.BiomeGenArchipelagoBase;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EnhancedBiomesArchipelago 
{	
	public static int mountainArchipelagoId;
	public static int mountainArchipelagoGen;	
	public static boolean villageMountainArchipelago;
	public static BiomeGenArchipelagoBase biomeMountainArchipelago;
	
	public static int desertArchipelagoId;
	public static int desertArchipelagoGen;	
	public static boolean villageDesertArchipelago;	
	public static BiomeGenArchipelagoBase biomeDesertArchipelago;
	
	public static int forestArchipelagoId;
	public static int forestArchipelagoGen;	
	public static boolean villageForestArchipelago;
	public static BiomeGenArchipelagoBase biomeForestArchipelago;
	
	public static int jungleArchipelagoId;
	public static int jungleArchipelagoGen;	
	public static boolean villageJungleArchipelago;
	public static BiomeGenArchipelagoBase biomeJungleArchipelago;
	
	public static int pineArchipelagoId;
	public static int pineArchipelagoGen;	
	public static boolean villagePineArchipelago;
	public static BiomeGenArchipelagoBase biomePineArchipelago;
		
	public static int snowArchipelagoId;
	public static int snowArchipelagoGen;	
	public static boolean villageSnowArchipelago;
	public static BiomeGenArchipelagoBase biomeSnowArchipelago;
		
	public static int plainsArchipelagoId;
	public static int plainsArchipelagoGen;		
	public static boolean villagePlainsArchipelago;
	public static BiomeGenArchipelagoBase biomePlainsArchipelago;
	
	public static int flowerArchipelagoId;
	public static boolean villageFlowerArchipelago;
	public static BiomeGenArchipelagoBase biomeFlowerArchipelago;
	
	public static int borealArchipelagoId;
	public static boolean villageBorealArchipelago;
	public static BiomeGenArchipelagoBase biomeBorealArchipelago;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		mountainArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Mountainous Archipelago", BiomeIDs.mountainousArchipelago).getInt();
		mountainArchipelagoGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Mountainous Archipelago biome", 10).getInt();
		villageMountainArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Mountainous Archipelago biome", true).getBoolean(true);	
		
		desertArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Desert Archipelago", BiomeIDs.desertArchipelago).getInt();
		desertArchipelagoGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Desert Archipelago biome", 10).getInt();
		villageDesertArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Desert Archipelago biome", true).getBoolean(true);
		
		forestArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Forested Archipelago", BiomeIDs.forestedArchipelago).getInt();
		forestArchipelagoGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Forested Archipelago biome", 10).getInt();
		villageForestArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Forested Archipelago biome", true).getBoolean(true);
		
		jungleArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Tropical Archipelago", BiomeIDs.tropicalArchipelago).getInt();
		jungleArchipelagoGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Tropical Archipelago biome", 10).getInt();
		villageJungleArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Tropical Archipelago biome", true).getBoolean(true);
		
		pineArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Pine Forest Archipelago", BiomeIDs.pineForestArchipelago).getInt();
		pineArchipelagoGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Pine Forest Archipelago biome", 10).getInt();
		villagePineArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Pine Forest Archipelago biome", true).getBoolean(true);	
		
		snowArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Frozen Archipelago", BiomeIDs.frozenArchipelago).getInt();
		snowArchipelagoGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Frozen Archipelago biome", 10).getInt();
		villageSnowArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Frozen Archipelago biome", true).getBoolean(true);	
		
		plainsArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Grassy Archipelago", BiomeIDs.grassyArchipelago).getInt();
		plainsArchipelagoGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Grassy Archipelago biome", 10).getInt();
		villagePlainsArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Grassy Archipelago biome", true).getBoolean(true);	
		
		flowerArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Flowery Archipelago", BiomeIDs.floweryArchipelago).getInt();
		villageFlowerArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Flowery Archipelago biome", true).getBoolean(true);
		
		borealArchipelagoId = config.get(config.CATEGORY_GENERAL, "Biome ID of Boreal Archipelago", BiomeIDs.borealArchipelago).getInt();
		villageBorealArchipelago = config.get(config.CATEGORY_GENERAL, "Generate villages in Boreal Archipelago biome", true).getBoolean(true);	
		
		config.save();
	}
	
	public static void load()
	{
		
		biomeMountainArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenMountainsArchipelago(mountainArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.25F, 0.6F)).setHeight(heightHighArchipelago).setBiomeName("Mountainous Archipelago");
		BiomeGenManager.addCoolBiome(biomeMountainArchipelago, mountainArchipelagoGen);
		if (villageMountainArchipelago)	{  BiomeManager.addVillageBiome(biomeMountainArchipelago, true);  }
		BiomeManager.addStrongholdBiome(biomeMountainArchipelago);
		BiomeWoods.register(biomeMountainArchipelago, Blocks.planks, 1);
		
		biomeDesertArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenDesertArchipelago(desertArchipelagoId).setDisableRain().setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(2.0F, 0.0F)).setHeight(heightLowArchipelago).setBiomeName("Desert Archipelago");
		BiomeGenManager.addHotBiome(biomeDesertArchipelago, desertArchipelagoGen);
		if (villageDesertArchipelago)	{  BiomeManager.addVillageBiome(biomeDesertArchipelago, true);  }
		BiomeManager.addStrongholdBiome(biomeDesertArchipelago);
		BiomeWoods.register(biomeDesertArchipelago, EnhancedBiomesBlocks.planksEB, 13, false);
		
		biomeForestArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenForestArchipelago(forestArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.7F, 0.8F)).setHeight(heightLowArchipelago).setBiomeName("Forested Archipelago");
		BiomeGenManager.addWarmBiome(biomeForestArchipelago, forestArchipelagoGen);
		if (villageForestArchipelago)	{  BiomeManager.addVillageBiome(biomeForestArchipelago, true);  }
		BiomeManager.addStrongholdBiome(biomeForestArchipelago);
		BiomeWoods.register(biomeForestArchipelago, Blocks.planks, 0);
		
		biomeJungleArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenJungleArchipelago(jungleArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F)).setHeight(heightMidArchipelago).setBiomeName("Tropical Archipelago");
		BiomeGenManager.addWarmBiome(biomeJungleArchipelago, jungleArchipelagoGen);
		if (villageJungleArchipelago)	{  BiomeManager.addVillageBiome(biomeJungleArchipelago, true);  }
		BiomeManager.addStrongholdBiome(biomeJungleArchipelago);
		BiomeWoods.register(biomeJungleArchipelago, Blocks.planks, 3);
		
		biomePineArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenPineForestArchipelago(pineArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.2F, 0.7F)).setHeight(heightLowArchipelago).setBiomeName("Pine Forest Archipelago");
		BiomeGenManager.addCoolBiome(biomePineArchipelago, pineArchipelagoGen);
		if (villagePineArchipelago)	{  BiomeManager.addVillageBiome(biomePineArchipelago, true);  }		
		BiomeManager.addStrongholdBiome(biomePineArchipelago);	
		BiomeWoods.register(biomePineArchipelago, EnhancedBiomesBlocks.planksEB, 6);
		
		biomeSnowArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenSnowArchipelago(snowArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.0F, 0.5F)).setHeight(heightLowArchipelago).setBiomeName("Frozen Archipelago");
		BiomeGenManager.addFrozenBiome(biomeSnowArchipelago, snowArchipelagoGen);
		if (villageSnowArchipelago)	{  BiomeManager.addVillageBiome(biomeSnowArchipelago, true);  }		
		BiomeManager.addStrongholdBiome(biomeSnowArchipelago);	
		BiomeWoods.register(biomeSnowArchipelago, Blocks.planks, 1);
		
		biomePlainsArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenPlainsArchipelago(plainsArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.8F, 0.4F)).setHeight(heightLowArchipelago).setBiomeName("Grassy Archipelago");
		BiomeGenManager.addWarmBiome(biomePlainsArchipelago, plainsArchipelagoGen);
		if (villagePlainsArchipelago)	{  BiomeManager.addVillageBiome(biomePlainsArchipelago, true);  }		
		BiomeManager.addStrongholdBiome(biomePlainsArchipelago);
		BiomeWoods.register(biomePlainsArchipelago, Blocks.planks, 0);
		
		biomeFlowerArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenFlowerArchipelago(flowerArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.8F, 0.4F)).setHeight(heightLowArchipelago).setBiomeName("Flowery Archipelago");
		if (villageFlowerArchipelago)	{  BiomeManager.addVillageBiome(biomeFlowerArchipelago, true);  }
		BiomeManager.addStrongholdBiome(biomeFlowerArchipelago);
		BiomeWoods.register(biomeFlowerArchipelago, Blocks.planks, 0);
		
		biomeBorealArchipelago = (BiomeGenArchipelagoBase) (new BiomeGenBorealArchipelago(borealArchipelagoId).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(0.2F, 0.7F)).setHeight(heightLowArchipelago).setBiomeName("Boreal Archipelago");
		if (villageBorealArchipelago)	{  BiomeManager.addVillageBiome(biomeBorealArchipelago, true);  }		
		BiomeManager.addStrongholdBiome(biomeBorealArchipelago);
		BiomeWoods.register(biomeBorealArchipelago, Blocks.planks, 1);	
	}
}
