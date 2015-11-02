package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPScrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPScrubland;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPScrubland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.scrubland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPScrubland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPScrubland(),
			new SurfaceBOPScrubland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Scrubland");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPscrubland;
	}
}
