package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineMountainsM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineMountainsM;

public class RealisticBiomeEBAlpineMountainsM extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAlpineMountainsM(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBAlpineMountainsM(),
			new SurfaceEBAlpineMountainsM(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Mountains M");
		this.biomeWeight = ConfigEB.weightEBAlpineMountainsM;
	}
}
