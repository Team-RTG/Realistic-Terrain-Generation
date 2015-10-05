package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPAlpsForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPAlpsForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPAlpsForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.alpsForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPAlpsForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
			new TerrainBOPAlpsForest(),
			new SurfaceBOPAlpsForest(topBlock, fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("BOP Alps Forest");
		this.biomeWeight = ConfigBOP.weightBOPalpsForest;
	}
}
