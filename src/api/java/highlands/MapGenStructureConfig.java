package highlands;

import highlands.api.HighlandsBiomes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;

public class MapGenStructureConfig {
	 //Village Biomes lists
	public static List hlvillagebiomes;
	public static List defaultvillagebiomes;
	
	private static final String CATEGORY_BIOME_VILLAGE = "Biomes.Villages true/false?";
	private static final String CATEGORY_BIOME_VILLAGE_VANILLA = "BiomesVanilla.Villages true/false?";
	
	public static void init () {
 		//add biomes to spawn strongholds in
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			if(i != HighlandsBiomes.woodsMountains && i != HighlandsBiomes.flyingMountains && i != HighlandsBiomes.ocean2)
				BiomeManager.strongHoldBiomes.add(i);
		}
	}
	
	public static void postInit () {
		Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + File.separator + "Highlands" + File.separator + "StructureConfig.cfg"));
		config.load();
		
		defaultvillagebiomes = MapGenVillage.villageSpawnBiomes;
		hlvillagebiomes = new ArrayList();
		
		// config settings to allow/disallow village generation in certain biomes
		// Highland Biomes
		if (config.get(CATEGORY_BIOME_VILLAGE, "Autumn Forest Village", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.autumnForest);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Highlands Village", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.highlandsb);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Pinelands Village", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.pinelands);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Tall Pine Forest", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.tallPineForest);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Meadow", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.meadow);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Savannah", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.savannah);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Sahel", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.sahel);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Steppe", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.steppe);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Outback", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.outback);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Lowlands", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.lowlands);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Birch Hills", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.birchHills);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Tropical Islands", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.tropicalIslands);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Bog", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.bog);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Redwood Forest", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.redwoodForest);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Rainforest", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.rainforest);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Forest Island", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.forestIsland);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE, "Windy Island", true).getBoolean(true)) {
			hlvillagebiomes.add(HighlandsBiomes.windyIsland);
		}
		
		//Vanilla Biomes
		if (config.get(CATEGORY_BIOME_VILLAGE_VANILLA, "jungle", true).getBoolean(true)) {
			hlvillagebiomes.add(BiomeGenBase.jungle);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE_VANILLA, "forest", true).getBoolean(true)) {
			hlvillagebiomes.add(BiomeGenBase.forest);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE_VANILLA, "taiga", true).getBoolean(true)) {
			hlvillagebiomes.add(BiomeGenBase.taiga);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE_VANILLA, "swampland", true).getBoolean(true)) {
			hlvillagebiomes.add(BiomeGenBase.swampland);
		}
		if (config.get(CATEGORY_BIOME_VILLAGE_VANILLA, "icePlains", true).getBoolean(true)) {
			hlvillagebiomes.add(BiomeGenBase.icePlains);
		}
		
		config.save();
	}
}
