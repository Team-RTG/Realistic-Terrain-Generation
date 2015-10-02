package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBCarr;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBCarr;

public class RealisticBiomeEBCarr extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBCarr(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainEBCarr(),
			new SurfaceEBCarr(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Carr");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBCarr);
	}
}
