package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneRanges;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneRanges;

public class RealisticBiomeEBSandstoneRanges extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSandstoneRanges(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBSandstoneRanges(false, 35f, 80f, 30f, 20f, 10),
			new SurfaceEBSandstoneRanges(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Sandstone Ranges");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBSandstoneRanges;
	}
}
