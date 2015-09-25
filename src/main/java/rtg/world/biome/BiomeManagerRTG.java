package rtg.world.biome;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
//import rtg.world.BiomeManager.BiomeEntry; 

import rtg.config.*;
import rtg.temp.*;
import rtg.world.biome.realistic.vanilla.*;

//import biomesoplenty.api.biome.BOPInheritedBiome;
//import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
//import biomesoplenty.common.configuration.BOPConfigurationBiomeWeights;
//import biomesoplenty.common.configuration.BOPConfigurationIDs;

public class BiomeManagerRTG
{
	private static int nextBiomeId = 40;
	
	public static List<rtg.world.biome.BiomeManager.BiomeEntry>[] overworldBiomes = new ArrayList[4];
	public static List<rtg.world.biome.BiomeManager.BiomeEntry>[] overworldSubBiomes = new ArrayList[BiomeGenBase.getBiomeGenArray().length];
	public static List<Integer> overworldOceanBiomes = new ArrayList();
	public static BiomeGenBase[] overworldRiverBiomes = new BiomeGenBase[BiomeGenBase.getBiomeGenArray().length];
	public static List<rtg.world.biome.BiomeManager.BiomeEntry> netherBiomes = new ArrayList();
	public static List<rtg.world.biome.BiomeManager.BiomeEntry> endBiomes = new ArrayList();
	
	public static BiomeGenBase createAndRegisterBiome(Class<? extends BiomeGenBase> biomeClass, String biomeType, String biomeName, List<rtg.world.biome.BiomeManager.BiomeEntry> biomeList, int weight)
	{
		BiomeGenBase biome = createBiome(biomeClass, biomeName);
		
		if (biome != null)
		{
			rtg.world.biome.BiomeManager.BiomeEntry entry = new rtg.world.biome.BiomeManager.BiomeEntry(biome, getConfiguredWeight(biome, biomeType, weight));

			if (ConfigRTG.config.get(biomeType + " Biomes To Generate", biome.biomeName, true).getBoolean(false))
			{
				if (biomeList != null) biomeList.add(entry);
			}

			return biome;
		}
		
		return null;
	}
	
	public static BiomeGenBase createBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, int biomeId)
	{
		if (biomeId == -1) biomeId = ConfigRTG.config.get("Biome IDs", biomeName + " ID", getNextFreeBiomeId()).getInt();

		if (biomeId != -1)
		{ 
			try
			{
				BiomeGenBase biome;
				
				if (BOPInheritedBiome.class.isAssignableFrom(biomeClass))
				{
					biome = biomeClass.getConstructor(int.class, BiomeGenBase.class).newInstance(biomeId, BiomeGenBase.getBiome(biomeId)).setBiomeName(biomeName);
				}
				else
				{
					 biome = biomeClass.getConstructor(int.class).newInstance(biomeId).setBiomeName(biomeName);
				}
				
				return biome;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public static BiomeGenBase createBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName)
	{
		return createBiome(biomeClass, biomeName, -1);
	}
	
	public static int getNextFreeBiomeId()
	{
		for (int i = nextBiomeId; i < 256; i++)
		{
			if (BiomeGenBase.getBiomeGenArray()[i] != null) 
			{
				if (i == 255) throw new IllegalArgumentException("There are no more biome ids avaliable!");
				continue;
			}
			else
			{
				nextBiomeId = i + 1;
				return i;
			}
		}
		
		return -1;
	}
	
	public static boolean isBiomeOceanic(int biomeId)
	{
		return overworldOceanBiomes.contains(biomeId);
	}
	
	private static int getConfiguredWeight(BiomeGenBase biome, String biomeType, int weight)
	{
		return BiomeWeightsRTG.config.get(biomeType + " Biome Weights", biome.biomeName, weight).getInt(weight);
	}
	
	public class TemperatureType
	{
		public static final int HOT = 0;
		public static final int WARM = 1;
		public static final int COOL = 2;
		public static final int ICY = 3;
	}
}