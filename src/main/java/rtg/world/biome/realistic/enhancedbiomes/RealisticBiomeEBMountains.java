package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMountains;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMountains;

public class RealisticBiomeEBMountains extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBMountains(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBMountains(200f, 100f, 0f),
			new SurfaceEBMountains(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Mountains");
		this.biomeWeight = ConfigEB.weightEBMountains;
	}
}
