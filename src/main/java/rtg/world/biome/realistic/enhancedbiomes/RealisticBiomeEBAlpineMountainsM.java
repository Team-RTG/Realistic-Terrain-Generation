package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountainsM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountainsM;

public class RealisticBiomeEBAlpineMountainsM extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAlpineMountainsM(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAlpineMountainsM(),
			new SurfaceEBAlpineMountainsM(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Mountains M");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigEB.weightEBAlpineMountainsM;
	}
}
