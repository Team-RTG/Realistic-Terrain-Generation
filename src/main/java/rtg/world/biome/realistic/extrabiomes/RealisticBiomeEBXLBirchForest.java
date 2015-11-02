package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLBirchForest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLBirchForest;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLBirchForest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.birchforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLBirchForest()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLBirchForest(230f, 120f, 0f),
			new SurfaceEBXLBirchForest(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EBXL Birch Forest");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXL_birchforest;
	}
}
