package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOasis;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOasis;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPOasis extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.oasis;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPOasis()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainBOPOasis(),
			new SurfaceBOPOasis(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Oasis");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigBOP.weightBOPoasis;
	}
}
