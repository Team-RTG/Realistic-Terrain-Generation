package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneRangesM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneRangesM;

public class RealisticBiomeEBSandstoneRangesM extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSandstoneRangesM(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBSandstoneRangesM(false, 35f, 80f, 30f, 20f, 10),
			new SurfaceEBSandstoneRangesM(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Sandstone Ranges M");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBSandstoneRangesM;
	}
}
