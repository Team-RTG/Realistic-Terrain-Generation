package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBCreekBed;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBCreekBed;

public class RealisticBiomeEBCreekBed extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBCreekBed(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
			new TerrainEBCreekBed(),
			new SurfaceEBCreekBed(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Creek Bed");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBCreekBed;
	}
}
