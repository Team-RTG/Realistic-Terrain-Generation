package rtg.world.biome.realistic.extrabiomes;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLSnowyRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLSnowyRainforest;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLSnowyRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.snowyrainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLSnowyRainforest()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBXLSnowyRainforest(230f, 60f, 0f),
			new SurfaceEBXLSnowyRainforest(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("EBXL Snowy Rainforest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXLSnowyRainforest;
		this.generateVillages = ConfigEBXL.villageEBXLSnowyRainforest;
	}
}
