package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLRainforest;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.rainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLRainforest()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLRainforest(230f, 100f, 0f),
			new SurfaceEBXLRainforest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Rainforest");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXL_rainforest;
	}
}
