package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBlossomHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBlossomHills;

public class RealisticBiomeEBBlossomHills extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBBlossomHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBBlossomHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceEBBlossomHills(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Blossom Hills");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigEB.weightEBBlossomHills;
	}
}
