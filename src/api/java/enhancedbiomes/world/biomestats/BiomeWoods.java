package enhancedbiomes.world.biomestats;

import enhancedbiomes.EnhancedBiomesMod;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeWoods 
{
	public static void register(BiomeGenBase biome, Block planksID, int planksMeta)
	{
		EnhancedBiomesMod.woodList[biome.biomeID] = planksID;
		EnhancedBiomesMod.woodMetaList[biome.biomeID] = (byte) planksMeta;
	}
	
	public static void register(BiomeGenBase biome, Block planksID, int planksMeta, boolean isWoodenVillage)
	{
		EnhancedBiomesMod.woodList[biome.biomeID] = planksID;
		EnhancedBiomesMod.woodMetaList[biome.biomeID] = (byte) planksMeta;
		EnhancedBiomesMod.isStoneVillageList[biome.biomeID] = !isWoodenVillage;
	}
}
