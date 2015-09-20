package rtg.biomes.vanilla;

import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.util.Logger;
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
	
	public static RealisticBiomeBase getRealisticVanillaBiomeFromVanillaVariableName(String name)
	{
		if (name.equals("beach")) { return RealisticBiomeBase.vanillaBeach; }
		else if (name.equals("birchForest")) { return RealisticBiomeBase.vanillaBirchForest; }
		else if (name.equals("birchForestHills")) { return RealisticBiomeBase.vanillaBirchForestHills; }
		else if (name.equals("coldBeach")) { return RealisticBiomeBase.vanillaColdBeach; }
		else if (name.equals("coldTaiga")) { return RealisticBiomeBase.vanillaColdTaiga; }
		else if (name.equals("coldTaigaHills")) { return RealisticBiomeBase.vanillaColdTaigaHills; }
		else if (name.equals("deepOcean")) { return RealisticBiomeBase.vanillaDeepOcean; }
		else if (name.equals("desert")) { return RealisticBiomeBase.vanillaDesert; }
		else if (name.equals("desertHills")) { return RealisticBiomeBase.vanillaDesertHills; }
		else if (name.equals("extremeHills")) { return RealisticBiomeBase.vanillaExtremeHills; }
		else if (name.equals("extremeHillsPlus")) { return RealisticBiomeBase.vanillaExtremeHillsPlus; }
		else if (name.equals("forest")) { return RealisticBiomeBase.vanillaForest; }
		else if (name.equals("forestHills")) { return RealisticBiomeBase.vanillaForestHills; }
		else if (name.equals("frozenRiver")) { return RealisticBiomeBase.vanillaFrozenRiver; }
		else if (name.equals("iceMountains")) { return RealisticBiomeBase.vanillaIceMountains; }
		else if (name.equals("icePlains")) { return RealisticBiomeBase.vanillaIcePlains; }
		else if (name.equals("jungle")) { return RealisticBiomeBase.vanillaJungle; }
		else if (name.equals("jungleEdge")) { return RealisticBiomeBase.vanillaJungleEdge; }
		else if (name.equals("jungleHills")) { return RealisticBiomeBase.vanillaJungleHills; }
		else if (name.equals("megaTaiga")) { return RealisticBiomeBase.vanillaMegaTaiga; }
		else if (name.equals("megaTaigaHills")) { return RealisticBiomeBase.vanillaMegaTaigaHills; }
		else if (name.equals("mesa")) { return RealisticBiomeBase.vanillaMesa; }
		else if (name.equals("mesaPlateau")) { return RealisticBiomeBase.vanillaMesaPlateau; }
		else if (name.equals("mesaPlateau_F")) { return RealisticBiomeBase.vanillaMesaPlateau_F; }
		else if (name.equals("mushroomIsland")) { return RealisticBiomeBase.vanillaMushroomIsland; }
		else if (name.equals("mushroomIslandShore")) { return RealisticBiomeBase.vanillaMushroomIslandShore; }
		else if (name.equals("ocean")) { return RealisticBiomeBase.vanillaOcean; }
		else if (name.equals("plains")) { return RealisticBiomeBase.vanillaPlains; }
		else if (name.equals("river")) { return RealisticBiomeBase.vanillaRiver; }
		else if (name.equals("roofedForest")) { return RealisticBiomeBase.vanillaRoofedForest; }
		else if (name.equals("savanna")) { return RealisticBiomeBase.vanillaSavanna; }
		else if (name.equals("savannaPlateau")) { return RealisticBiomeBase.vanillaSavannaPlateau; }
		else if (name.equals("stoneBeach")) { return RealisticBiomeBase.vanillaStoneBeach; }
		else if (name.equals("swampland")) { return RealisticBiomeBase.vanillaSwampland; }
		else if (name.equals("taiga")) { return RealisticBiomeBase.vanillaTaiga; }
		else if (name.equals("taigaHills")) { return RealisticBiomeBase.vanillaTaigaHills; }
		
		return null;
	}
}
