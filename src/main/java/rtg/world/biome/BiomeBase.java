package rtg.world.biome;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase
{
    public static final int DEFAULT_BIOME_SIZE = 3;
    public static final int MIN_BIOME_SIZE = 1;
    public static final int MAX_BIOME_SIZE = 20;
    
    public static final int DEFAULT_BIOME_WEIGHT = 10;
    public static final int MIN_BIOME_WEIGHT = 0;
    public static final int MAX_BIOME_WEIGHT = 100;
    
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

	public static ArrayList<Integer> biomes_snow;
	public static ArrayList<Integer> biomes_cold;
	public static ArrayList<Integer> biomes_hot;
	public static ArrayList<Integer> biomes_wet;
	
	public static float tempCold = 0.2f, rainCold = 0.3f;
	public static float tempHot = 2f, rainHot = 0f;
	public static float tempIce = -0.5f, rainIce = 0.4f;
	public static float tempOasis = 0.9f, rainOasis = 1f;
	public static float tempTemperate = 0.8f, rainTemperate = 0.4f;
	public static float tempWet = 0.95f, rainWet = 0.9f;	
	
	public static ArrayList<BiomeGenBase> arrVillageBiomes;

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
		biomes_snow = new ArrayList<Integer>();
		biomes_cold = new ArrayList<Integer>();
		biomes_hot = new ArrayList<Integer>();
		biomes_wet = new ArrayList<Integer>();
		
		arrVillageBiomes = new ArrayList<BiomeGenBase>();
	}
    
    public static void addVillageBiome(RealisticBiomeBase b)
    {
        if (b.generateVillages) {
            arrVillageBiomes.add(b.baseBiome);
        }
    }
	
	public static void addBiome(RealisticBiomeBase b)
	{
		try
		{
			addWeightedBiome(b);
		}
		catch(Error e)
		{
		    FMLLog.log(Level.ERROR, "Failed to add biome.");
		}
	}

	public static void addWeightedBiome(RealisticBiomeBase b)
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
			        biomes_snow.add(b.biomeID);
			    }
                else if (b.baseBiome.temperature <= 0.3f) {
                    biomes_cold.add(b.biomeID);
                }
                else if (b.baseBiome.temperature <= 1f) {
                    biomes_wet.add(b.biomeID);
                }
                else {
                    biomes_hot.add(b.biomeID);
                }
			}
		}
	}
}
