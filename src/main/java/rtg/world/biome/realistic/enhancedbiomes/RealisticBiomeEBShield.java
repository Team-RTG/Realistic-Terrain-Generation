package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBShield;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBShield;

public class RealisticBiomeEBShield extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBShield(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBShield(),
			new SurfaceEBShield(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Shield");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBShield;
	}
}
