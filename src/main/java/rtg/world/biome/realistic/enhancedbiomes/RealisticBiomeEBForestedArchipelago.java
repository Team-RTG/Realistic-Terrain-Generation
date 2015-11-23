package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBForestedArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBForestedArchipelago;

public class RealisticBiomeEBForestedArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBForestedArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBForestedArchipelago(),
			new SurfaceEBForestedArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Forested Archipelago");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBForestedArchipelago;
		this.generateVillages = ConfigEB.villageEBForestedArchipelago;
	}
}
