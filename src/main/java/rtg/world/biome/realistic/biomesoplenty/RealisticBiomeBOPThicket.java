package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPThicket;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPThicket;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPThicket extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.thicket;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPThicket()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainBOPThicket(70f, 180f, 13f, 100f, 38f, 260f, 71f),
			new SurfaceBOPThicket(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Thicket");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigBOP.weightBOPthicket;
	}
}
