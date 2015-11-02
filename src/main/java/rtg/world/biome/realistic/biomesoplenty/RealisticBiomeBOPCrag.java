package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCrag;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCrag;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPCrag extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.crag;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCrag()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPCrag(),
			new SurfaceBOPCrag(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Crag");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPcrag;
	}
}
