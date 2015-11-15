package rtg.world.biome;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase
{
    public static final int DEFAULT_BIOME_SIZE = 1;
    public static final int MIN_BIOME_SIZE = 1;
    public static final int MAX_BIOME_SIZE = 5;
    
    public static final int DEFAULT_BIOME_WEIGHT = 10;
    public static final int MIN_BIOME_WEIGHT = 0;
    public static final int MAX_BIOME_WEIGHT = 20;
    
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

	public enum BiomeSize
	{
	    LARGE,
		NORMAL,
		SMALL
	}
	
	public static ArrayList<RealisticBiomeBase> biomes_snow;
	public static ArrayList<RealisticBiomeBase> biomes_cold;
	public static ArrayList<RealisticBiomeBase> biomes_hot;
	public static ArrayList<RealisticBiomeBase> biomes_wet;
	
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
	}
    
	public static void addBiome(RealisticBiomeBase b, BiomeSize size)
	{
		try
		{
			addWeightedBiome(b, size);
		}
		catch(Error e)
		{
			System.out.println("Failed to add biome.");
		}
	}
	
	public static void addBiome(RealisticBiomeBase b)
	{
		BiomeSize size = b.biomeSize;
		
		try
		{
			addWeightedBiome(b, size);
		}
		catch(Error e)
		{
			System.out.println("Failed to add biome.");
		}
	}

	public static void addWeightedBiome(RealisticBiomeBase b, BiomeSize size)
	{
		int weight = (int) b.biomeWeight;
		weight = (weight < MIN_BIOME_WEIGHT) ? MIN_BIOME_WEIGHT : ((weight > MAX_BIOME_WEIGHT) ? MAX_BIOME_WEIGHT : weight);
		
		/**
		 * Since biome-specific biome sizes aren't a thing yet,
		 * use the global biome size setting to increase the weights of the biomes.
		 */
		int biomeSize = ConfigRTG.biomeSize;
		biomeSize = (biomeSize < MIN_BIOME_SIZE) ? MIN_BIOME_SIZE : (biomeSize > MAX_BIOME_SIZE ? MAX_BIOME_SIZE : biomeSize);
		
		weight *= biomeSize;
		
		if (weight > 0) {			
			for (int i = 0; i < weight; i++) {
				
			    /**
			     * Sort by temperature.
			     */
			    if (b.baseBiome.temperature < 0.15f) {
			        biomes_snow.add(b);
			        
			        if (ConfigRTG.enableDebugging) {
			            FMLLog.log(Level.INFO, "Added %s to SNOW category (%d in total)", b.getRealisticBiomeName(), biomes_snow.size());
			        }
			    }
                else if (b.baseBiome.temperature <= 0.3f) {
                    biomes_cold.add(b);
                    
                    if (ConfigRTG.enableDebugging) {
                        FMLLog.log(Level.INFO, "Added %s to COLD category (%d in total)", b.getRealisticBiomeName(), biomes_cold.size());
                    }
                }
                else if (b.baseBiome.temperature <= 1f) {
                    biomes_wet.add(b);
                    
                    if (ConfigRTG.enableDebugging) {
                        FMLLog.log(Level.INFO, "Added %s to WET category (%d in total)", b.getRealisticBiomeName(), biomes_wet.size());
                    }
                }
                else {
                    biomes_hot.add(b);
                    
                    if (ConfigRTG.enableDebugging) {
                        FMLLog.log(Level.INFO, "Added %s to HOT category (%d in total)", b.getRealisticBiomeName(), biomes_hot.size());
                    }
                }

                /**
                 * Sort by size.
                 */
				switch (size)
				{
					case SMALL:
					    //TODO
						break;
                        
					case NORMAL:
					  //TODO
						break;
                        
					case LARGE:
					  //TODO
						break;
                        
					default:
						break;
				}
			}
		}
	}
}
