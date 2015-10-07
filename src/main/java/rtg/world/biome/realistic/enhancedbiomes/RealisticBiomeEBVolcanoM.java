package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBVolcanoM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBVolcanoM;

public class RealisticBiomeEBVolcanoM extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBVolcanoM(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBVolcanoM(),
			new SurfaceEBVolcanoM(ebBiome.topBlock, ebBiome.fillerBlock, false, ebBiome.topBlock, 20f)
		);
		
		this.setRealisticBiomeName("EB Volcano M");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBVolcanoM;
	}
}
