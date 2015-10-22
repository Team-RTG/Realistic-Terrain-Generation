package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLSnowyRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLSnowyRainforest;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLSnowyRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.snowyrainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLSnowyRainforest()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBXLSnowyRainforest(230f, 120f, 0f),
			new SurfaceEBXLSnowyRainforest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Snowy Rainforest");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigEBXL.weightEBXL_snowyrainforest;
	}
}
