package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBOasis;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBOasis;

public class RealisticBiomeEBOasis extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBOasis(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainEBOasis(),
			new SurfaceEBOasis(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Oasis");
		this.biomeWeight = ConfigEB.weightEBOasis;
	}
}
