package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountainsEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountainsEdge;

public class RealisticBiomeEBAlpineMountainsEdge extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAlpineMountainsEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAlpineMountainsEdge(230f, 120f, 50f),
			new SurfaceEBAlpineMountainsEdge(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Mountains Edge");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBAlpineMountainsEdge;
		this.generateVillages = ConfigEB.villageEBAlpineMountainsEdge;
	}
}
