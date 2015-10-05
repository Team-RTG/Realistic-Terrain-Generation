package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneCanyon;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneCanyon;

public class RealisticBiomeEBSandstoneCanyon extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSandstoneCanyon(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
			new TerrainEBSandstoneCanyon(false, 35f, 160f, 40f, 30f, 10),
			new SurfaceEBSandstoneCanyon(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Sandstone Canyon");
		this.biomeWeight = ConfigEB.weightEBSandstoneCanyon;
	}
}
