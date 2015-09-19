package rtg.biomes.vanilla;

import net.minecraft.world.biome.BiomeGenBase;

public class VanillaBiomes
{
	public static enum Climate {
		COLD,
		HOT,
		ICE,
		OASIS,
		TEMPERATE,
		WET
	}
	
	public static float tempCold = 0.5f; public static float rainCold = 0.4f;
	public static float tempHot = 0.8f; public static float rainHot = 0.2f;
	public static float tempIce = 0.0f; public static float rainIce = 0.1f;
	public static float tempOasis = 0.9f; public static float rainOasis = 0.9f;
	public static float tempTemperate = 0.8f; public static float rainTemperate = 0.6f;
	public static float tempWet = 0.9f; public static float rainWet = 0.9f;

	/**
	 * We need to set the temp/rain values 'on the fly' when we pass them as arguments to avoid
	 * the last temp/rain values being used throughout.
	 * So instead of using vanillaRiverCold, we now use VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD)
	 * Because Java.
	 */
	public static BiomeGenBase climatizedBiome(BiomeGenBase biome, Climate climate)
	{
		switch (climate)
		{
			case COLD:
				biome.setTemperatureRainfall(tempCold, rainCold);
				break;
			case HOT:
				biome.setTemperatureRainfall(tempHot, rainHot);
				break;
			case ICE:
				biome.setTemperatureRainfall(tempIce, rainIce);
				break;
			case OASIS:
				biome.setTemperatureRainfall(tempOasis, rainOasis);
				break;
			case TEMPERATE:
				biome.setTemperatureRainfall(tempTemperate, rainTemperate);
				break;
			case WET:
				biome.setTemperatureRainfall(tempWet, rainWet);
				break;
			default:
				break;
		}
		
		return biome;
	}
}
