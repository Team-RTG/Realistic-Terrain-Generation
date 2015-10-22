package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneCanyon;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneCanyon;

public class RealisticBiomeEBSandstoneCanyon extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSandstoneCanyon(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBSandstoneCanyon(false, 35f, 160f, 40f, 30f, 10),
			new SurfaceEBSandstoneCanyon(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Sandstone Canyon");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBSandstoneCanyon;
	}
}
