package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLForestedIsland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLForestedIsland;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLForestedIsland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.forestedisland.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLForestedIsland()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLForestedIsland(90f, 180f, 13f, 100f, 1f, 260f, 59f),
			new SurfaceEBXLForestedIsland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Forested Island");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXL_forestedisland;
	}
}
