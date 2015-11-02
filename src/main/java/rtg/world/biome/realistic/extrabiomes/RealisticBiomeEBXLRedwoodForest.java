package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLRedwoodForest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLRedwoodForest;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLRedwoodForest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.redwoodforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLRedwoodForest()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLRedwoodForest(230f, 120f, 0f),
			new SurfaceEBXLRedwoodForest(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
		
		this.setRealisticBiomeName("EBXL Redwood Forest");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXL_redwoodforest;
	}
}
