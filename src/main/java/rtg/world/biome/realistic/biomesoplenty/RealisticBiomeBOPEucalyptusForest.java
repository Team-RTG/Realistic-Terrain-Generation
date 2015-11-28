package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPEucalyptusForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPEucalyptusForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPEucalyptusForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.eucalyptusForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPEucalyptusForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPEucalyptusForest(0f, 180f, 68f, 120f),
			new SurfaceBOPEucalyptusForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Eucalyptus Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPEucalyptusForest;
		this.generateVillages = ConfigBOP.villageBOPEucalyptusForest;
	}
}
