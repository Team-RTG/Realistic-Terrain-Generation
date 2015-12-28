package enhancedbiomes.world.biome;

import java.io.File;

public class EnhancedBiomesWasteland
{
	public static void config()
	{
		EnhancedBiomesRock.config();
		EnhancedBiomesSandstone.config();
	}
	
	public static void load()
	{
		EnhancedBiomesRock.load();
		EnhancedBiomesSandstone.load();
	}
}