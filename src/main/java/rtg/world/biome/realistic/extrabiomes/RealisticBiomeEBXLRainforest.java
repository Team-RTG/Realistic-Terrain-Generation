package rtg.world.biome.realistic.extrabiomes;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLRainforest;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.rainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLRainforest()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLRainforest(230f, 43f, 0f),
			new SurfaceEBXLRainforest(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("EBXL Rainforest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXLRainforest;
		this.generateVillages = ConfigEBXL.villageEBXLRainforest;
	}
}
