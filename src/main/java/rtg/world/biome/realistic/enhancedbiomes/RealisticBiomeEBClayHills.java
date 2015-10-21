package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBClayHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBClayHills;

public class RealisticBiomeEBClayHills extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBClayHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBClayHills(),
			new SurfaceEBClayHills(ebBiome.topBlock, ebBiome.fillerBlock, (byte)1)
		);
		
		this.setRealisticBiomeName("EB Clay Hills");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBClayHills;
	}
}
