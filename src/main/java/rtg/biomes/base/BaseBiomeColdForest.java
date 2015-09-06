package rtg.biomes.base;

import rtg.data.VillageMaterialData;
import rtg.data.VillageMaterials;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeColdForest extends BiomeGenBase
{
	public BaseBiomeColdForest(int id, String bn) 
	{
		super(id);
		setTemperatureRainfall(0.5f, 0.4f);
		setBiomeName(bn);
		spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 1, 2));
	}
}
