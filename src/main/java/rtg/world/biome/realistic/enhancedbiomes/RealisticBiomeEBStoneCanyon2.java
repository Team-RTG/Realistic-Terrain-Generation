package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBStoneCanyon2;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBStoneCanyon2;

public class RealisticBiomeEBStoneCanyon2 extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBStoneCanyon2(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBStoneCanyon2(false, 35f, 80f, 30f, 20f, 10),
			new SurfaceEBStoneCanyon2(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Stone Canyon 2");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBStoneCanyons;
	}
}
