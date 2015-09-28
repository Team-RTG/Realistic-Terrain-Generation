package rtg.world.layer;

import java.util.ArrayList;

//import enhancedbiomes.EnhancedBiomesMod;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;

public class BiomeGenManager
{
	static ArrayList<BiomeGenBase> hotBiomes = new ArrayList<BiomeGenBase>();
	static ArrayList<BiomeGenBase> warmBiomes = new ArrayList<BiomeGenBase>();
	static ArrayList<BiomeGenBase> coolBiomes = new ArrayList<BiomeGenBase>();
	static ArrayList<BiomeGenBase> frozenBiomes = new ArrayList<BiomeGenBase>();

	static int[] rareBiomes = new int[BiomeGenBase.getBiomeGenArray().length];

	public static void addFrozenBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			frozenBiomes.add(biome);
		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(biome, weight));
	}

	public static void addCoolBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			coolBiomes.add(biome);
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(biome, weight));
	}

	public static void addWarmBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			warmBiomes.add(biome);
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(biome, weight));
	}

	public static void addHotBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			hotBiomes.add(biome);
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(biome, weight));
	}

	public static void addEBFrozenBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			frozenBiomes.add(biome);
	}

	public static void addEBCoolBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			coolBiomes.add(biome);
	}

	public static void addEBWarmBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			warmBiomes.add(biome);
	}

	public static void addEBHotBiome(BiomeGenBase biome, int weight) {
		if(weight < 1) return;
		for(int a = 0; a < weight; a++)
			hotBiomes.add(biome);
	}

	public static BiomeGenBase[] getHotBiomes(BiomeGenBase[] input) {
		BiomeGenBase[] output = new BiomeGenBase[input.length + hotBiomes.size()];
		for(int a = 0; a < input.length; a++)
			output[a] = input[a];
		for(int b = 0; b < hotBiomes.size(); b++)
			output[b + input.length] = hotBiomes.get(b);
		return output;
	}

	public static BiomeGenBase[] getWarmBiomes(BiomeGenBase[] input) {
		BiomeGenBase[] output = new BiomeGenBase[input.length + warmBiomes.size()];
		for(int a = 0; a < input.length; a++)
			output[a] = input[a];
		for(int b = 0; b < warmBiomes.size(); b++)
			output[b + input.length] = warmBiomes.get(b);
		return output;
	}

	public static BiomeGenBase[] getCoolBiomes(BiomeGenBase[] input) {
		BiomeGenBase[] output = new BiomeGenBase[input.length + coolBiomes.size()];
		for(int a = 0; a < input.length; a++)
			output[a] = input[a];
		for(int b = 0; b < coolBiomes.size(); b++)
			output[b + input.length] = coolBiomes.get(b);
		return output;
	}

	public static BiomeGenBase[] getFrozenBiomes(BiomeGenBase[] input) {
		BiomeGenBase[] output = new BiomeGenBase[input.length + frozenBiomes.size()];
		for(int a = 0; a < input.length; a++)
			output[a] = input[a];
		for(int b = 0; b < frozenBiomes.size(); b++)
			output[b + input.length] = frozenBiomes.get(b);
		return output;
	}

	public static void setRareBiome(int defaultBiome, int rareBiome) {
		rareBiomes[defaultBiome] = rareBiome;
	}
	// need fix
	//public static int getRareBiome(int defaultBiome) {
	//	return EnhancedBiomesMod.useListedRareBiomes ? rareBiomes[defaultBiome] : defaultBiome < 128 && BiomeGenBase.getBiome(defaultBiome + 128) != null ? defaultBiome + 128 : 0;
	//}
}
