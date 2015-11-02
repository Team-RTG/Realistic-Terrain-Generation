package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBXericSavanna;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBXericSavanna;

public class RealisticBiomeEBXericSavanna extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBXericSavanna(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBXericSavanna(),
			new SurfaceEBXericSavanna(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Xeric Savanna");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBXericSavannah;
	}
}
