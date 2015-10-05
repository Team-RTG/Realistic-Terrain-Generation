package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineTundra;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineTundra;

public class RealisticBiomeEBAlpineTundra extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAlpineTundra(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBAlpineTundra(),
			new SurfaceEBAlpineTundra(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Tundra");
		this.biomeWeight = ConfigEB.weightEBAlpineTundra;
	}
}
