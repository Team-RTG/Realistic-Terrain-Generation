package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBLake;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBLake;

public class RealisticBiomeEBLake extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBLake(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainEBLake(),
			new SurfaceEBLake(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Lake");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBLake);
	}
}
