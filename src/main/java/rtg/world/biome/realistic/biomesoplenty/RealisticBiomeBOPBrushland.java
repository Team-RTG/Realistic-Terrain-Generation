package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBrushland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBrushland;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPBrushland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.brushland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBrushland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPBrushland(90f, 180f, 13f, 100f, 38f, 260f, 71f),
			new SurfaceBOPBrushland(topBlock, fillerBlock, Blocks.sand, Blocks.stone, Blocks.cobblestone, 13f, 0.27f)
		);
		
		this.setRealisticBiomeName("BOP Brushland");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigBOP.weightBOPbrushland;
	}
}
