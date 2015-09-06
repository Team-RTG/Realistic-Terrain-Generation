package rtg.biomes.base;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeColdPlains extends BiomeGenBase
{
	public BaseBiomeColdPlains(int id, String bn) 
	{
		super(id);
		setTemperatureRainfall(0.2f, 0.2f);
		setBiomeName(bn);
		spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 2, 3));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 3));
	}
}
