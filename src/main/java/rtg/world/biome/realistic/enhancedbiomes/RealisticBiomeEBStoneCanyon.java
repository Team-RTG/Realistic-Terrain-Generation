package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBStoneCanyon;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBStoneCanyon;

public class RealisticBiomeEBStoneCanyon extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBStoneCanyon(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBStoneCanyon(false, 35f, 160f, 40f, 30f, 10),
			new SurfaceEBStoneCanyon(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Stone Canyon");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBStoneCanyon;
		this.generateVillages = ConfigEB.villageEBStoneCanyon;
	}
}
