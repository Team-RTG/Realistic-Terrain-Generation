package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOutback;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOutback;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPOutback extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.outback;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPOutback()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainBOPOutback(300f),
			new SurfaceBOPOutback(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone, (byte)0, 1)
		);
		
		this.setRealisticBiomeName("BOP Outback");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPoutback;
	}
}
