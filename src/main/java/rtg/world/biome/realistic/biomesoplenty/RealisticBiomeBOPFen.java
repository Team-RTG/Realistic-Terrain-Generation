package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPFen;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPFen;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPFen extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.fen;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPFen()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPFen(0f, 140f, 68f, 200f),
			new SurfaceBOPFen(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Fen");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPfen;
	}
}
