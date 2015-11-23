package rtg.config.addon;

import java.io.File;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import rtg.RTG;
import rtg.event.MapGenHandler;
import rtg.event.VillageBlocksHandler;
import rtg.world.gen.structure.MapGenVillageRTG;
import rtg.village.VillagePieceSelection;

public class ConfigAddon 
{
	public static Configuration config;
	
	public static final int biomeWeightMin = 0;
	public static final int biomeWeightMax = 100;
	public static final int biomeWeightDefault = 10;
	
	public static boolean generateAddonBiomes = true;
	
	public static boolean generateCanyonForest = true;
	public static boolean generateDarkRedwoodPlains = true;
	public static boolean generateDuneValleyForest = true;
	public static boolean generateHotForest = true;
	public static boolean generateJungleCanyon = true;
	public static boolean generateMesaPlains = true;
	public static boolean generatePolar = true;
	public static boolean generateRedDesertMountains = true;
	public static boolean generateRedOasis = true;
	public static boolean generateSavannaForest = true;
	
	public static boolean villageDarkRedwoodPlains= true;
	public static boolean villageDuneValleyForest = true;
	public static boolean villageHotForest = true;
	public static boolean villageMesaPlains = true;
	public static boolean villageRedOasis = true;
	public static boolean villageSavannaForest = true;
	
	public static int weightCanyonForest = biomeWeightDefault;
	public static int weightDarkRedwoodPlains = biomeWeightDefault;
	public static int weightDuneValleyForest = biomeWeightDefault;
	public static int weightHotForest = biomeWeightDefault;
	public static int weightJungleCanyon = biomeWeightDefault;
	public static int weightMesaPlains = biomeWeightDefault;
	public static int weightPolar = biomeWeightDefault;
	public static int weightRedDesertMountains = biomeWeightDefault;
	public static int weightRedOasis = biomeWeightDefault;
	public static int weightSavannaForest = biomeWeightDefault;
	
	public static int villageDistance;
	public static int villageSize;
	
	public static boolean useVillageMods;

	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);
	
		try 
		{
			config.load();
			
			generateAddonBiomes = config.getBoolean("Generate Addon Biomes", "Addon Biomes", true, "");
			
			//gen biomes
			generateCanyonForest = config.getBoolean("generateCanyonForest", "Addon Biomes", true, "");
			generateDarkRedwoodPlains = config.getBoolean("generateDarkRedwoodPlains", "Addon Biomes", true, "");
			generateDuneValleyForest = config.getBoolean("generateDuneValleyForest", "Addon Biomes", true, "");
			generateHotForest = config.getBoolean("generateHotForest", "Addon Biomes", true, "");
			generateJungleCanyon = config.getBoolean("generateJungleCanyon", "Addon Biomes", true, "");
			generateMesaPlains = config.getBoolean("generateMesaPlains", "Addon Biomes", true, "");
			generatePolar = config.getBoolean("generatePolar", "Addon Biomes", true, "");
			generateRedDesertMountains = config.getBoolean("generateRedDesertMountains", "Addon Biomes", true, "");
			generateRedOasis = config.getBoolean("generateRedOasis", "Addon Biomes", true, "");
			generateSavannaForest = config.getBoolean("generateSavannaForest", "Addon Biomes", true, "");
			
			//village biomes
			villageDarkRedwoodPlains = config.getBoolean("villageDarkRedwoodPlains", "Villages", villageDarkRedwoodPlains, "");
			villageDuneValleyForest = config.getBoolean("villageDuneValleyForest", "Villages", villageDuneValleyForest, "");
			villageHotForest = config.getBoolean("villageHotForest", "Villages", villageHotForest, "");
			villageMesaPlains = config.getBoolean("villageMesaPlains", "Villages", villageMesaPlains, "");
			villageRedOasis = config.getBoolean("villageRedOasis", "Villages", villageRedOasis, "");
			villageSavannaForest = config.getBoolean("villageSavannaForest", "Villages", villageSavannaForest, "");
			
			//biome weights
			weightCanyonForest = config.getInt("weightCanyonForest", "Addon Biome Weights", weightCanyonForest, biomeWeightMin, biomeWeightMax, "");
			weightDarkRedwoodPlains = config.getInt("weightDarkRedwoodPlains", "Addon Biome Weights", weightDarkRedwoodPlains, biomeWeightMin, biomeWeightMax, "");
			weightDuneValleyForest = config.getInt("weightDuneValleyForest", "Addon Biome Weights", weightDuneValleyForest, biomeWeightMin, biomeWeightMax, "");
			weightHotForest = config.getInt("weightHotForest", "Addon Biome Weights", weightHotForest, biomeWeightMin, biomeWeightMax, "");
			weightJungleCanyon = config.getInt("weightJungleCanyon", "Addon Biome Weights", weightJungleCanyon, biomeWeightMin, biomeWeightMax, "");
			weightMesaPlains = config.getInt("weightMesaPlains", "Addon Biome Weights", weightMesaPlains, biomeWeightMin, biomeWeightMax, "");
			weightPolar = config.getInt("weightPolar", "Addon Biome Weights", weightPolar, biomeWeightMin, biomeWeightMax, "");
			weightRedDesertMountains = config.getInt("weightRedDesertMountains", "Addon Biome Weights", weightRedDesertMountains, biomeWeightMin, biomeWeightMax, "");
			weightRedOasis = config.getInt("weightRedOasis", "Addon Biome Weights", weightRedOasis, biomeWeightMin, biomeWeightMax, "");
			weightSavannaForest = config.getInt("weightSavannaForest", "Addon Biome Weights", weightSavannaForest, biomeWeightMin, biomeWeightMax, "");
		
		//	villageDistance = config.get(config.CATEGORY_GENERAL, "Distance between villages", 32, "Normal is 32").getInt();
		//	villageSize = config.get(config.CATEGORY_GENERAL, "Size of villages", 0, "Normal is 0").getInt();
		
			useVillageMods = config.get(config.CATEGORY_GENERAL, "Enable the modifications to villages", true, "Disabling this will solve issues with other mods which edit the way villages generate").getBoolean(true);
		}
		
		
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "RTG has had a problem loading BOP configuration.");	
		}
		
		finally 
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}

		if(useVillageMods) {
			MinecraftForge.TERRAIN_GEN_BUS.register(new MapGenHandler());
			MapGenStructureIO.registerStructure(rtg.world.gen.structure.MapGenVillageRTG.Start.class, "VillageEB");
			VillagePieceSelection.registerVillagePieces();
		//	MinecraftForge.TERRAIN_GEN_BUS.register(new VillageBlocksHandler());
			//TODO New scattered features
			//MapGenStructureIO.registerStructure(StructureScatteredFeatureEnhancedBiomesStart.class, "ScatteredFeatureEB");
		}
	 }	
}