package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSteppe;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSteppe;

public class RealisticBiomeEBSteppe extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSteppe(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBSteppe(),
			new SurfaceEBSteppe(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Steppe");
		this.biomeWeight = ConfigEB.weightEBSteppe;
	}
}
