package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneCanyon2;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneCanyon2;

public class RealisticBiomeEBSandstoneCanyon2 extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSandstoneCanyon2(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBSandstoneCanyon2(false, 35f, 80f, 30f, 20f, 10),
			new SurfaceEBSandstoneCanyon2(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Sandstone Canyon 2");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBSandstoneCanyons;
	}
}
