package rtg.world.biome.realistic.atg;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGWoodland;
import rtg.world.gen.terrain.atg.TerrainATGWoodland;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGWoodland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGWoodland(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainATGWoodland(),
			new SurfaceATGWoodland(atgBiome.topBlock, atgBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("ATG Woodland");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigATG.weightATGWoodland;
	}
}
