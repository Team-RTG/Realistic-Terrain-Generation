package rtg.api;

import net.minecraft.world.biome.BiomeGenBase;

public class RTGBiomes 
{
	/*
	 * These biomes are used for grass/foliage color and entity spawning
	 */
	public static BiomeGenBase baseRiverIce;
	public static BiomeGenBase baseRiverCold;
	public static BiomeGenBase baseRiverTemperate;
	public static BiomeGenBase baseRiverHot;
	public static BiomeGenBase baseRiverWet;
	public static BiomeGenBase baseRiverOasis;
	
	public static BiomeGenBase baseOceanIce;
	public static BiomeGenBase baseOceanCold;
	public static BiomeGenBase baseOceanTemperate;
	public static BiomeGenBase baseOceanHot;
	public static BiomeGenBase baseOceanWet;
	public static BiomeGenBase baseOceanOasis;
	
	public static BiomeGenBase baseSnowDesert;
	public static BiomeGenBase baseSnowForest;
	public static BiomeGenBase baseColdPlains;
	public static BiomeGenBase baseColdForest;
	public static BiomeGenBase baseHotPlains;
	public static BiomeGenBase baseHotForest;
	public static BiomeGenBase baseHotDesert;
	public static BiomeGenBase basePlains;
	public static BiomeGenBase baseTropicalIsland;
	public static BiomeGenBase baseRedwood;
	public static BiomeGenBase baseJungle;
	public static BiomeGenBase baseOasis;
	public static BiomeGenBase baseTemperateForest;
	
	/*
	 * This function will never break. Use this instead of the above.
	 */
	public static BiomeGenBase[] getAllBaseBiomes()
	{
		return new BiomeGenBase[]{
			baseRiverIce,
			baseRiverCold,
			baseRiverTemperate,
			baseRiverHot,
			baseRiverWet,
			baseRiverOasis,
			
			baseOceanIce,
			baseOceanCold,
			baseOceanTemperate,
			baseOceanHot,
			baseOceanWet,
			baseOceanOasis,
				
			baseSnowDesert,
			baseSnowForest,
			baseColdPlains,
			baseColdForest,
			baseHotPlains,
			baseHotForest,
			baseHotDesert,
			basePlains,
			baseTropicalIsland,
			baseRedwood,
			baseJungle,
			baseOasis,
			baseTemperateForest
		};
	}
}
