package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBScree;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBScree;

public class RealisticBiomeEBScree extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBScree(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
			new TerrainEBScree(false, 35f, 80f, 30f, 20f, 10),
			new SurfaceEBScree(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Scree");
		this.biomeWeight = ConfigEB.weightEBScree;
	}
}
