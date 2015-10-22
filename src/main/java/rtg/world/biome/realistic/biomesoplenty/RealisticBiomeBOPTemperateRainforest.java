package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTemperateRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTemperateRainforest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPTemperateRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.temperateRainforest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPTemperateRainforest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPTemperateRainforest(),
			new SurfaceBOPTemperateRainforest(topBlock, fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("BOP Temperate Rainforest");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigBOP.weightBOPtemperateRainforest;
	}
}
