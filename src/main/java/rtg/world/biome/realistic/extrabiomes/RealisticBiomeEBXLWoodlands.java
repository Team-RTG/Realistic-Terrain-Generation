package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLWoodlands;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLWoodlands;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLWoodlands extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.woodlands.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLWoodlands()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBXLWoodlands(0f, 140f, 68f, 200f),
			new SurfaceEBXLWoodlands(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Woodlands");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXL_woodlands;
	}
}
