package rtg.biomes.base;

import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeRiver extends BiomeGenBase
{
	public BaseBiomeRiver(int id, int subID, String bn) 
	{
		super(id);
		
		if(subID == 0) //ICE
		{
			setTemperatureRainfall(0.0f, 0.1f);
			spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
		}
		else if(subID == 1) //COLD
		{
			setTemperatureRainfall(0.5f, 0.4f);
			spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 1, 2));
		}
		else if(subID == 2) //TEMPERATE
		{
			setTemperatureRainfall(0.8f, 0.6f);
		}
		else if(subID == 3) //HOT
		{
			setTemperatureRainfall(0.8f, 0.2f);
			setDisableRain();
		}
		else if(subID == 4) //WET
		{
			setTemperatureRainfall(0.9f, 0.9f);
			spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		}
		else if(subID == 5) //OASIS
		{
			setTemperatureRainfall(0.9f, 0.9f);
		}
		
		setBiomeName(bn);
	}
}
