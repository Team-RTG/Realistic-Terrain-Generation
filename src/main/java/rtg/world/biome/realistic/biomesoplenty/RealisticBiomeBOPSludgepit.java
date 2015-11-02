package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSludgepit;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSludgepit;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSludgepit extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.sludgepit;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSludgepit()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPSludgepit(),
			new SurfaceBOPSludgepit(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Sludgepit");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPsludgepit;
	}
}
