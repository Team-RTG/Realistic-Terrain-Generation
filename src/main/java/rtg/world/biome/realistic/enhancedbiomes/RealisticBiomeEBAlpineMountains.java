package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountains;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountains;

public class RealisticBiomeEBAlpineMountains extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAlpineMountains(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBAlpineMountains(),
			new SurfaceEBAlpineMountains(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Mountains");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBAlpineMountains);
	}
}
