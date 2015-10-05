package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMeadowM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMeadowM;

public class RealisticBiomeEBMeadowM extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBMeadowM(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBMeadowM(),
			new SurfaceEBMeadowM(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Meadow M");
		this.biomeWeight = ConfigEB.weightEBMeadowM;
	}
}
