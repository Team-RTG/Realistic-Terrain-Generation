package rtg.biomes.base;

import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeSnowDesert extends BiomeGenBase
{
	public BaseBiomeSnowDesert(int id, String bn) 
	{
		super(id);
		setTemperatureRainfall(0.0f, 0.1f);
		setBiomeName(bn);
		spawnableCreatureList.clear();
	}
}
