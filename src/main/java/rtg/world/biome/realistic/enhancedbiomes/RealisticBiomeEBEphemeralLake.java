package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBEphemeralLake;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBEphemeralLake;

public class RealisticBiomeEBEphemeralLake extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBEphemeralLake(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBEphemeralLake(),
			new SurfaceEBEphemeralLake(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Ephemeral Lake");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigEB.weightEBEphemeralLake;
	}
}
