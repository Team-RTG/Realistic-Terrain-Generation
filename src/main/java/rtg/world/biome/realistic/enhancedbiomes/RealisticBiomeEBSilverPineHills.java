package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSilverPineHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSilverPineHills;

public class RealisticBiomeEBSilverPineHills extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSilverPineHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBSilverPineHills(200f, 100f, 0f),
			new SurfaceEBSilverPineHills(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Silver Pine Hills");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSilverPineHills;
		this.generateVillages = ConfigEB.villageEBSilverPineHills;
	}
}
