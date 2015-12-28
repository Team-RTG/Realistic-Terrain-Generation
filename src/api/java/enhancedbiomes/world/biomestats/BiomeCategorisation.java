package enhancedbiomes.world.biomestats;

import static enhancedbiomes.world.biome.EnhancedBiomesWoodland.biomeBlossomHills;
import static enhancedbiomes.world.biome.EnhancedBiomesWoodland.biomeBlossomWoods;
import static net.minecraftforge.common.BiomeDictionary.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public enum BiomeCategorisation {
	GRASS,
	SAND,
	CANYONS,
	BOREAL,
	BROADLEAF,
	WETLAND,
	MOUNTAINS,
	VOLCANO,
	SEA,
	ICE,
	ROCK,
	SKY,
	SHROOM;
	
	public static BiomeCategorisation getCatForBiome(BiomeGenBase biome) {
		//Round 1
		if(isBiomeOfAnyType(biome, MESA)) return CANYONS;
		else if(isBiomeOfAnyType(biome, END)) return SKY;
		else if(isBiomeOfAnyType(biome, MUSHROOM)) return SHROOM;
		else if(isBiomeOfAllType(biome, CONIFEROUS, FOREST)) return BOREAL;
		else if(isBiomeOfAllType(biome, HOT, DRY)) return SAND;
		else if(isBiomeOfAnyType(biome, OCEAN)) return SEA;
		else if(isBiomeOfAnyType(biome, MOUNTAIN)) return MOUNTAINS;
		else if(isBiomeOfAnyType(biome, NETHER)) return VOLCANO;
		else if(isBiomeOfAnyType(biome, SWAMP, RIVER)) return WETLAND;
		else if(isBiomeOfAnyType(biome, SNOWY)) return ICE;
		
		//Round 2
		else if(isBiomeOfAnyType(biome, SANDY, SAVANNA, BEACH)) return SAND;
		else if(isBiomeOfAllType(biome, WASTELAND, COLD)) return ICE;
		else if(isBiomeOfAnyType(biome, FOREST, JUNGLE)) return BROADLEAF;
		else if(isBiomeOfAnyType(biome, PLAINS)) return GRASS;
		else if(isBiomeOfAnyType(biome, WASTELAND)) return ROCK;
		return GRASS;		
	}

	public static boolean isBiomeOfAnyType(BiomeGenBase biome, Type... types) {
		for(Type type : types) {
			if(isBiomeOfType(biome, type)) return true;
		}
		return false;
	}

	public static boolean isBiomeOfAllType(BiomeGenBase biome, Type... types) {
		for(Type type : types) {
			if(!isBiomeOfType(biome, type)) return false;
		}
		return true;
	}
	
	public static boolean isOriental(BiomeGenBase biome) {
		return biome == biomeBlossomWoods || biome == biomeBlossomHills;
	}
	
	public static BiomeGenBase[] getAllBiomesOfCat(BiomeCategorisation cat) {
		BiomeGenBase[] list = new BiomeGenBase[0];
		
		for(int id = 0; id < 256; id++) {
			if(BiomeGenBase.getBiome(id) != null && getCatForBiome(BiomeGenBase.getBiome(id)) == cat) {
				BiomeGenBase[] newList = new BiomeGenBase[list.length + 1];
				for(int l = 0; l < list.length; l++) {
					newList[l] = list[l];
				}
				newList[list.length] = BiomeGenBase.getBiome(id);
				list = newList;
			}
		}/*
		
		for(int a = 0; a < list.length; a++) {
			System.err.println(list[a].biomeName);
		}*/
		
		return list;	
	}
}