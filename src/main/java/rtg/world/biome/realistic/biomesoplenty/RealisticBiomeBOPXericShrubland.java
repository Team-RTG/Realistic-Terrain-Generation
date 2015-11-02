package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPXericShrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPXericShrubland;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPXericShrubland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.xericShrubland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPXericShrubland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPXericShrubland(0f, 140f, 68f, 200f),
			new SurfaceBOPXericShrubland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Xeric Shrubland");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPxericShrubland;
	}
}
