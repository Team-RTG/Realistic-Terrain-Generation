package rtg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase
{

	public BiomeBase(int intBiomeId) {
		super(intBiomeId, false);
		
		// And I won't tell no one your name... and I won't tell 'em your name.
		this.biomeName = getBiome(intBiomeId).biomeName;
	}

	public enum Climate {
		COLD,
		HOT,
		ICE,
		OASIS,
		TEMPERATE,
		WET
	}
	
	public static float tempCold = 0.2f, rainCold = 0.3f;
	public static float tempHot = 2f, rainHot = 0f;
	public static float tempIce = -0.5f, rainIce = 0.4f;
	public static float tempOasis = 0.9f, rainOasis = 1f;
	public static float tempTemperate = 0.8f, rainTemperate = 0.4f;
	public static float tempWet = 0.95f, rainWet = 0.9f;	
	
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
				biome.setTemperatureRainfall(BiomeBase.tempCold, BiomeBase.rainCold);
				break;
			case HOT:
				biome.setTemperatureRainfall(BiomeBase.tempHot, BiomeBase.rainHot);
				break;
			case ICE:
				biome.setTemperatureRainfall(BiomeBase.tempIce, BiomeBase.rainIce);
				break;
			case OASIS:
				biome.setTemperatureRainfall(BiomeBase.tempOasis, BiomeBase.rainOasis);
				break;
			case TEMPERATE:
				biome.setTemperatureRainfall(BiomeBase.tempTemperate, BiomeBase.rainTemperate);
				break;
			case WET:
				biome.setTemperatureRainfall(BiomeBase.tempWet, BiomeBase.rainWet);
				break;
			default:
				break;
		}
		
		return biome;
	}
	
    public static void init()
	{

	}
}
