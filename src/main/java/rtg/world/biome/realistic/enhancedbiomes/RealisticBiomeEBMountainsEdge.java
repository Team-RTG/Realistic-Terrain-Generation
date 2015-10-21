package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMountainsEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMountainsEdge;

public class RealisticBiomeEBMountainsEdge extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBMountainsEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBMountainsEdge(200f, 100f, 0f),
			new SurfaceEBMountainsEdge(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Mountains Edge");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEB.weightEBMountainsEdge;
	}
}
