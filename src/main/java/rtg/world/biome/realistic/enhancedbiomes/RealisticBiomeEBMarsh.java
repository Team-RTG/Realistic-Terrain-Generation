package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMarsh;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMarsh;

public class RealisticBiomeEBMarsh extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBMarsh(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainEBMarsh(),
			new SurfaceEBMarsh(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Marsh");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBMarsh);
	}
}
