package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.world.biome.base.BiomeGenSandstoneBase;
import enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills;
import enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed;
import enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon;
import enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge;
import enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges;
import enhancedbiomes.world.biomestats.BiomeIDs;
import enhancedbiomes.world.biomestats.BiomeWoods;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EnhancedBiomesSandstone
{  	  	  	
	public static int sandStoneRangesId;
	public static int sandStoneRangesGen;
	public static boolean villageSandStoneRanges;
	public static BiomeGenSandstoneBase biomeSandStoneRanges;
	
	public static int screeId;
	public static boolean villageScree;
	public static BiomeGenSandstoneBase biomeScree;	 
	
	public static int sandStoneCanyonId;
	public static int sandStoneCanyonGen;
	public static boolean villageSandStoneCanyon; 
	public static BiomeGenSandstoneBase biomeSandStoneCanyon;
	
	public static int sandStoneGorgeId;
	public static boolean villageSandStoneGorge; 	  
	public static BiomeGenSandstoneBase biomeSandStoneGorge; 
	
	public static int clayHillsId;	 
	public static int clayHillsGen; 
	public static boolean villageClayHills;	 
	public static BiomeGenSandstoneBase biomeClayHills;

	public static int creekBedId;
	public static boolean villageCreekBed;
	public static BiomeGenSandstoneBase biomeCreekBed;  	
	
	public static int sandStoneRangesMId;
	public static boolean villageSandStoneRangesM;
	public static BiomeGenSandstoneBase biomeSandStoneRangesM;

	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		sandStoneRangesId = config.get(config.CATEGORY_GENERAL, "Biome ID of Sandstone Ranges", BiomeIDs.sandstoneRanges).getInt();
		sandStoneRangesGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Sandstone Ranges biome", 10).getInt();
		villageSandStoneRanges = config.get(config.CATEGORY_GENERAL, "Generate villages in Sandstone Ranges biome", true).getBoolean(true);
		
		screeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Scree", BiomeIDs.scree).getInt();
		villageScree = config.get(config.CATEGORY_GENERAL, "Generate villages in Scree biome", true).getBoolean(true);
		
		sandStoneCanyonId = config.get(config.CATEGORY_GENERAL, "Biome ID of Sandstone Canyon", BiomeIDs.sandstoneCanyons).getInt();
		sandStoneCanyonGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Sandstone Canyon biome", 10).getInt();
		villageSandStoneCanyon = config.get(config.CATEGORY_GENERAL, "Generate villages in Sandstone Canyon biome", true).getBoolean(true);
		
		sandStoneGorgeId = config.get(config.CATEGORY_GENERAL, "Biome ID of Sandstone Canyon 2", BiomeIDs.sandstoneCanyon).getInt();
		villageSandStoneGorge = config.get(config.CATEGORY_GENERAL, "Generate villages in Sandstone Canyon 2 biome", true).getBoolean(true);
		
		clayHillsId = config.get(config.CATEGORY_GENERAL, "Biome ID of Clay Hills", BiomeIDs.clayHills).getInt();
		clayHillsGen = config.get(config.CATEGORY_GENERAL, "Generation frequency of Clay Hills biome", 10).getInt();
		villageClayHills = config.get(config.CATEGORY_GENERAL, "Generate villages in Clay Hills biome", true).getBoolean(true);

		creekBedId = config.get(config.CATEGORY_GENERAL, "Biome ID of Creek Bed", BiomeIDs.creekBed).getInt();
		villageCreekBed = config.get(config.CATEGORY_GENERAL, "Generate villages in Creek Bed biome", true).getBoolean(true);

		sandStoneRangesMId = config.get(config.CATEGORY_GENERAL, "Biome ID of Sandstone Ranges M", BiomeIDs.sandstoneRangesM).getInt();
		villageSandStoneRangesM = config.get(config.CATEGORY_GENERAL, "Generate villages in Sandstone Ranges M biome", true).getBoolean(true);
		
		config.save();
	}
	
	public static void load()
	{  	
		biomeSandStoneRanges = (BiomeGenSandstoneBase) (new BiomeGenSandStoneRanges(sandStoneRangesId)).setDisableRain().setColor(9286496).setTemperatureRainfall(0.8F, 0.5F).setHeight(heightMidHills).setBiomeName("Sandstone Ranges");
		BiomeGenManager.addHotBiome(biomeSandStoneRanges, sandStoneRangesGen);
		if (villageSandStoneRanges == true) 	{  BiomeManager.addVillageBiome(biomeSandStoneRanges, true);  }
		BiomeManager.addStrongholdBiome(biomeSandStoneRanges);
		BiomeWoods.register(biomeSandStoneRanges, EnhancedBiomesBlocks.planksEB, 9);
		
		biomeScree = (BiomeGenSandstoneBase) (new BiomeGenSandStoneRanges(screeId)).setDisableRain().setColor(9286496).setTemperatureRainfall(0.8F, 0.5F).setHeight(heightLowHills).setBiomeName("Scree");
		if (villageScree == true) 				{  BiomeManager.addVillageBiome(biomeScree, true);  }
		BiomeManager.addStrongholdBiome(biomeScree);
		BiomeWoods.register(biomeScree, EnhancedBiomesBlocks.planksEB, 9);
		
		biomeSandStoneCanyon = (BiomeGenSandstoneBase) (new BiomeGenSandStoneCanyon(sandStoneCanyonId)).setDisableRain().setColor(9286496).setTemperatureRainfall(0.95F, 0.15F).setHeight(heightMidPlateaus).setBiomeName("Sandstone Canyons");
		BiomeGenManager.addHotBiome(biomeSandStoneCanyon, sandStoneCanyonGen);
		if (villageSandStoneCanyon == true) 	{  BiomeManager.addVillageBiome(biomeSandStoneCanyon, true);  }
		BiomeManager.addStrongholdBiome(biomeSandStoneCanyon);
		BiomeWoods.register(biomeSandStoneCanyon, Blocks.planks, 2);
		
		biomeSandStoneGorge = (BiomeGenSandstoneBase) (new BiomeGenSandStoneGorge(sandStoneGorgeId)).setDisableRain().setColor(9286496).setTemperatureRainfall(0.8F, 0.15F).setHeight(heightShallowWaters).setBiomeName("Sandstone Canyon");
		if (villageSandStoneGorge == true) 		{  BiomeManager.addVillageBiome(biomeSandStoneGorge, true);  }
		BiomeManager.addStrongholdBiome(biomeSandStoneGorge);	
		BiomeWoods.register(biomeSandStoneGorge, Blocks.planks, 2);	  
		
		biomeClayHills = (BiomeGenSandstoneBase) (new BiomeGenClayHills(clayHillsId)).setDisableRain().setColor(9286496).setTemperatureRainfall(0.8F, 0.4F).setHeight(heightMidHills).setBiomeName("Clay Hills");
		BiomeGenManager.addHotBiome(biomeClayHills, clayHillsGen);		  
		if (villageClayHills == true) 			{  BiomeManager.addVillageBiome(biomeClayHills, true);  }
		BiomeManager.addStrongholdBiome(biomeClayHills);
		BiomeWoods.register(biomeClayHills, EnhancedBiomesBlocks.planksEB, 13);

		biomeCreekBed = (BiomeGenSandstoneBase) (new BiomeGenCreekBed(creekBedId)).setDisableRain().setColor(9286496).setTemperatureRainfall(1.0F, 0.4F).setHeight(heightShallowWaters).setBiomeName("Creek Bed");
		if (villageCreekBed == true)			{  BiomeManager.addVillageBiome(biomeCreekBed, true);  }
		BiomeManager.addStrongholdBiome(biomeCreekBed);
		BiomeWoods.register(biomeCreekBed, EnhancedBiomesBlocks.planksEB, 9);

		biomeSandStoneRangesM = (BiomeGenSandstoneBase) (new BiomeGenSandStoneRanges(sandStoneRangesMId)).setDisableRain().setColor(9286496).setTemperatureRainfall(0.8F, 0.5F).setHeight(heightMidHills).setBiomeName("Sandstone Ranges M");
		if (villageSandStoneRangesM == true) 	{  BiomeManager.addVillageBiome(biomeSandStoneRangesM, true);  }
		BiomeManager.addStrongholdBiome(biomeSandStoneRangesM);
		BiomeWoods.register(biomeSandStoneRangesM, EnhancedBiomesBlocks.planksEB, 9);
	}
}