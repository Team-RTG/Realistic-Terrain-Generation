package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBTundra;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBTundra;

public class RealisticBiomeEBTundra extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBTundra(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBTundra(),
			new SurfaceEBTundra(ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Tundra");
		this.biomeWeight = ConfigEB.weightEBTundra;
	}
}
