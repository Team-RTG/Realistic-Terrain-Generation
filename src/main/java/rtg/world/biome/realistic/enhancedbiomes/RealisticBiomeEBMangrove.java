package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMangrove;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMangrove;

public class RealisticBiomeEBMangrove extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBMangrove(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBMangrove(),
			new SurfaceEBMangrove(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Mangrove");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBMangroves;
		this.generateVillages = ConfigEB.villageEBMangroves;
	}
}
