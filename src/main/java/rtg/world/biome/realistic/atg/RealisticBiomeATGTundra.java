package rtg.world.biome.realistic.atg;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGTundra;
import rtg.world.gen.terrain.atg.TerrainATGTundra;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGTundra extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGTundra(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainATGTundra(),
			new SurfaceATGTundra(atgBiome.topBlock, atgBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("ATG Tundra");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigATG.weightATGTundra;
		this.generateVillages = ConfigATG.villageATGTundra;
	}
}
