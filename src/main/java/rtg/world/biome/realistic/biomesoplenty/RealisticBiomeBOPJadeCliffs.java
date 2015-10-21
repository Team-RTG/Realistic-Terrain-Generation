package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPJadeCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPJadeCliffs;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPJadeCliffs extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.jadeCliffs;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPJadeCliffs()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPJadeCliffs(230f, 120f, 0f),
			new SurfaceBOPJadeCliffs(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("BOP Jade Cliffs");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigBOP.weightBOPjadeCliffs;
	}
}
