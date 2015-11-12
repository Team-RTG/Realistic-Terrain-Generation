package rtg.world.biome.realistic.atg;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGTropicalShrubland;
import rtg.world.gen.terrain.atg.TerrainATGTropicalShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGTropicalShrubland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGTropicalShrubland(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainATGTropicalShrubland(230f, 120f, 0f),
			new SurfaceATGTropicalShrubland(atgBiome.topBlock, atgBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("ATG Tropical Shrubland");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigATG.weightATGTropicalShrubland;
	}
}
