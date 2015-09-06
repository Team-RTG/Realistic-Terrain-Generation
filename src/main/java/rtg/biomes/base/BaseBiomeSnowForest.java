package rtg.biomes.base;

import rtg.data.VillageMaterialData;
import rtg.data.VillageMaterials;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeSnowForest extends BiomeGenBase
{
	public BaseBiomeSnowForest(int id, String bn) 
	{
		super(id);
		setTemperatureRainfall(0.0f, 0.1f);
		setBiomeName(bn);
		spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
	}
}
