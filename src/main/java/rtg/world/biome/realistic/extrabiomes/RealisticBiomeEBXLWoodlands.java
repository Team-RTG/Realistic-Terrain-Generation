package rtg.world.biome.realistic.extrabiomes;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLWoodlands;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLWoodlands;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLWoodlands extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.woodlands.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLWoodlands()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBXLWoodlands(0f, 80f, 68f, 200f),
			new SurfaceEBXLWoodlands(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("EBXL Woodlands");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXLWoodlands;
		this.generateVillages = ConfigEBXL.villageEBXLWoodlands;
	}
}
