package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushSwamp;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushSwamp;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPLushSwamp extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.lushSwamp;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLushSwamp()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainBOPLushSwamp(),
			new SurfaceBOPLushSwamp(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Lush Swamp");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigBOP.weightBOPlushSwamp;
	}
}
