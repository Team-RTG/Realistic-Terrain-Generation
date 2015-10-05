package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMountainsEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMountainsEdge;

public class RealisticBiomeEBMountainsEdge extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBMountainsEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBMountainsEdge(200f, 100f, 0f),
			new SurfaceEBMountainsEdge(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Mountains Edge");
		this.biomeWeight = ConfigEB.weightEBMountainsEdge;
	}
}
