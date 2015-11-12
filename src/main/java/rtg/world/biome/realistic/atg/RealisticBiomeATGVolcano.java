package rtg.world.biome.realistic.atg;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGVolcano;
import rtg.world.gen.terrain.atg.TerrainATGVolcano;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGVolcano extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGVolcano(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainATGVolcano(230f, 120f, 0f),
			new SurfaceATGVolcano(atgBiome.topBlock, atgBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("ATG Volcano");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigATG.weightATGVolcano;
	}
}
