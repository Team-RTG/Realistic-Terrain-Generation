package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPFungiForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPFungiForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPFungiForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.fungiForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPFungiForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainBOPFungiForest(135f, 300f),
			new SurfaceBOPFungiForest(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("BOP Fungi Forest");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigBOP.weightBOPfungiForest;
	}
}
