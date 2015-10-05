package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBForestedArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBForestedArchipelago;

public class RealisticBiomeEBForestedArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBForestedArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBForestedArchipelago(),
			new SurfaceEBForestedArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Forested Archipelago");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigEB.weightEBForestedArchipelago;
	}
}
