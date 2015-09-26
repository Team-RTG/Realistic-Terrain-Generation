package rtg.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

public class RTGBiomeManager 
{
	/**
	 * We need to populate these lists & arrays with biomes that extend BiomeGenBase...
	 * but RTG biomes don't extend BiomeGenBase  :(
	 */
	public static List<BiomeEntry>[] overworldBiomes = new ArrayList[4];
	public static List<BiomeEntry>[] overworldSubBiomes = new ArrayList[BiomeGenBase.getBiomeGenArray().length];
	public static List<Integer> overworldOceanBiomes = new ArrayList();
	public static BiomeGenBase[] overworldRiverBiomes = new BiomeGenBase[BiomeGenBase.getBiomeGenArray().length];
	public static List<BiomeEntry> netherBiomes = new ArrayList();
	public static List<BiomeEntry> endBiomes = new ArrayList();
	
	public static boolean isBiomeOceanic(int biomeId)
	{
		BiomeGenBase biome = BiomeGenBase.getBiome(biomeId);
		boolean isBiomeOceanic = false;
		
		//This logic is just a quick fix. Needs to use a more comprehensive way of determining if a biome is oceanic.
		//Just did it this way to get it working quickly.
		if (biome.getTempCategory() == TempCategory.OCEAN)
		{
			isBiomeOceanic = true;
		}
		
		return isBiomeOceanic;
	}
	
	private static int getConfiguredWeight(BiomeGenBase biome, String biomeType, int weight)
	{
		//Hardcoded as a quick fix.
		return 10;
	}
	
	public class TemperatureType
	{
		public static final int HOT = 0;
		public static final int WARM = 1;
		public static final int COOL = 2;
		public static final int ICY = 3;
	}
}
