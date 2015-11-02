package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBGrassyArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBGrassyArchipelago;

public class RealisticBiomeEBGrassyArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBGrassyArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBGrassyArchipelago(200f, 100f, 0f),
			new SurfaceEBGrassyArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Grassy Archipelago");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBGrassyArchipelago;
	}
}
