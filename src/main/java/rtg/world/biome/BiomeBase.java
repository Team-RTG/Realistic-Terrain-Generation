package rtg.world.biome;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;

import org.apache.logging.log4j.Level;

import rtg.world.biome.realistic.RealisticBiomeBase;
import cpw.mods.fml.common.FMLLog;

public class BiomeBase extends BiomeGenBase
{
	public BiomeBase(int intBiomeId) {
		super(intBiomeId, false);
	}

	public enum Climate {
		COLD,
		HOT,
		ICE,
		OASIS,
		TEMPERATE,
		WET
	}

	public enum BiomeCategory
	{
		SNOW,
		COLD,
		HOT,
		WET,
		SMALL
	}
	
	public static ArrayList<RealisticBiomeBase> biomes_snow;
	public static ArrayList<RealisticBiomeBase> biomes_cold;
	public static ArrayList<RealisticBiomeBase> biomes_hot;
	public static ArrayList<RealisticBiomeBase> biomes_wet;
	public static ArrayList<RealisticBiomeBase> biomes_small;
	
	public static float tempCold = 0.5f, rainCold = 0.4f;
	public static float tempHot = 0.8f, rainHot = 0.2f;
	public static float tempIce = 0.0f, rainIce = 0.1f;
	public static float tempOasis = 0.9f, rainOasis = 0.9f;
	public static float tempTemperate = 0.8f, rainTemperate = 0.6f;
	public static float tempWet = 0.9f, rainWet = 0.9f;	

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
		biomes_snow = new ArrayList<RealisticBiomeBase>();
		biomes_cold = new ArrayList<RealisticBiomeBase>();
		biomes_hot = new ArrayList<RealisticBiomeBase>();
		biomes_wet = new ArrayList<RealisticBiomeBase>();
		biomes_small = new ArrayList<RealisticBiomeBase>();
	}
    
	public static void addBiome(RealisticBiomeBase b, BiomeCategory cat)
	{
		try
		{
			addWeightedBiome(b, cat);
		}
		catch(Error e)
		{
			System.out.println("Failed to add biome.");
		}
	}
	
	public static void addBiome(RealisticBiomeBase b)
	{
		BiomeCategory cat = b.biomeCategory;
		
		try
		{
			addWeightedBiome(b, cat);
		}
		catch(Error e)
		{
			System.out.println("Failed to add biome.");
		}
	}

	public static void addWeightedBiome(RealisticBiomeBase b, BiomeCategory bc)
	{
		int bw = (int) b.biomeWeight;
		bw = (bw < 0) ? 0 : ((bw > 100) ? 100 : bw);
		
		if (bw > 0) {			
			for (int i = 0; i < bw; i++) {
				
				switch (bc)
				{
					case SNOW:
						biomes_snow.add(b);
						//FMLLog.log(Level.INFO, "Added biome (%s) to category (SNOW). %d biomes in this category so far.", b.getRealisticBiomeName(), biomes_snow.size());
						break;
					case COLD:
						biomes_cold.add(b);
						//FMLLog.log(Level.INFO, "Added biome (%s) to category (COLD). %d biomes in this category so far.", b.getRealisticBiomeName(), biomes_cold.size());
						break;
					case HOT:
						biomes_hot.add(b);
						//FMLLog.log(Level.INFO, "Added biome (%s) to category (HOT). %d biomes in this category so far.", b.getRealisticBiomeName(), biomes_hot.size());
						break;
					case WET:
						biomes_wet.add(b);
						//FMLLog.log(Level.INFO, "Added biome (%s) to category (WET). %d biomes in this category so far.", b.getRealisticBiomeName(), biomes_wet.size());
						break;
					case SMALL:
						biomes_small.add(b);
						//FMLLog.log(Level.INFO, "Added biome (%s) to category (SMALL). %d biomes in this category so far.", b.getRealisticBiomeName(), biomes_small.size());
						break;
					default:
						//FMLLog.log(Level.INFO, "Failed to add biome (%s) to category.", b.getRealisticBiomeName());
						break;
				}
			}
		}
	}
}
