package rtg.world.biome;

import java.util.ArrayList;

import cpw.mods.fml.common.Loader;
import rtg.config.ConfigRTG;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBBase;
import rtg.world.biome.realistic.extrabiomes.RealisticBiomeEBXLBase;
import rtg.world.biome.realistic.highlands.RealisticBiomeHighlandsBase;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase
{
	public BiomeBase(int intBiomeId) {
		super(intBiomeId, false);
	}

	public static enum Climate {
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
    
	public static void addBiome(RealisticBiomeBase b, BiomeBase.BiomeCategory cat)
	{
		try
		{
			switch(cat)
			{
				case SNOW:
					addWeightedBiome(b, biomes_snow);
					break;
				case COLD:
					addWeightedBiome(b, biomes_cold);
					break;
				case HOT: 
					addWeightedBiome(b, biomes_hot);
					break;
				case WET: 
					addWeightedBiome(b, biomes_wet);
					break;
				case SMALL: 
					addWeightedBiome(b, biomes_small);
					break;
			}
		}
		catch(Error e)
		{
			System.out.println("RTG Support: failed to add biome");
		}
	}
	
	public static void addBiome(RealisticBiomeBase b)
	{
		BiomeCategory cat = b.biomeCategory;
		cat = (cat != null) ? cat : BiomeCategory.WET;
		
		try
		{
			switch(cat)
			{
				case SNOW:
					addWeightedBiome(b, biomes_snow);
					break;
				case COLD:
					addWeightedBiome(b, biomes_cold);
					break;
				case HOT: 
					addWeightedBiome(b, biomes_hot);
					break;
				case WET: 
					addWeightedBiome(b, biomes_wet);
					break;
				case SMALL: 
					addWeightedBiome(b, biomes_small);
					break;
			}
		}
		catch(Error e)
		{
			System.out.println("RTG Support: failed to add biome");
		}
	}

	public static void addWeightedBiome(RealisticBiomeBase b, ArrayList<RealisticBiomeBase> ba)
	{
		int bw = (int) b.biomeWeight;
		bw = (bw < 0) ? 0 : ((bw > 100) ? 100 : bw);
		
		if (bw > 0) {			
			for (int i = 0; i < bw; i++) {
				ba.add(b);
			}
		}
	}
}
