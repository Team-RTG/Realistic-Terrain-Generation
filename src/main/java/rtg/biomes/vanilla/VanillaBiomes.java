package rtg.biomes.vanilla;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;

public class VanillaBiomes
{
	//RIVER
	public static BiomeGenBase vanillaRiverCold;
	public static BiomeGenBase vanillaRiverHot;
	public static BiomeGenBase vanillaRiverIce;
	public static BiomeGenBase vanillaRiverOasis;
	public static BiomeGenBase vanillaRiverTemperate;
	public static BiomeGenBase vanillaRiverWet;
	
	//OCEAN
	public static BiomeGenBase vanillaOceanCold;
	public static BiomeGenBase vanillaOceanHot;
	public static BiomeGenBase vanillaOceanIce;
	public static BiomeGenBase vanillaOceanOasis;
	public static BiomeGenBase vanillaOceanTemperate;
	public static BiomeGenBase vanillaOceanWet;

	public static void load()
	{
		//RIVER
		vanillaRiverCold = BiomeGenBase.river;
		vanillaRiverCold.setTemperatureRainfall(0.5f, 0.4f);
		
		vanillaRiverHot = BiomeGenBase.river;
		vanillaRiverHot.setTemperatureRainfall(0.8f, 0.2f);
		
		vanillaRiverIce = BiomeGenBase.river;
		vanillaRiverIce.setTemperatureRainfall(0.0f, 0.1f);
		
		vanillaRiverOasis = BiomeGenBase.river;
		vanillaRiverOasis.setTemperatureRainfall(0.9f, 0.9f);
		
		vanillaRiverTemperate = BiomeGenBase.river;
		vanillaRiverTemperate.setTemperatureRainfall(0.8f, 0.6f);
		
		vanillaRiverWet = BiomeGenBase.river;
		vanillaRiverWet.setTemperatureRainfall(0.9f, 0.9f);
		
		//OCEAN		
		vanillaOceanCold = BiomeGenBase.ocean;
		vanillaOceanCold.setTemperatureRainfall(0.5f, 0.4f);
		
		vanillaOceanHot = BiomeGenBase.ocean;
		vanillaOceanHot.setTemperatureRainfall(0.8f, 0.2f);
		
		vanillaOceanIce = BiomeGenBase.ocean;
		vanillaOceanIce.setTemperatureRainfall(0.0f, 0.1f);
		
		vanillaOceanOasis = BiomeGenBase.ocean;
		vanillaOceanOasis.setTemperatureRainfall(0.9f, 0.9f);
		
		vanillaOceanTemperate = BiomeGenBase.ocean;
		vanillaOceanTemperate.setTemperatureRainfall(0.8f, 0.6f);
		
		vanillaOceanWet = BiomeGenBase.ocean;
		vanillaOceanWet.setTemperatureRainfall(0.9f, 0.9f);
	}
}
