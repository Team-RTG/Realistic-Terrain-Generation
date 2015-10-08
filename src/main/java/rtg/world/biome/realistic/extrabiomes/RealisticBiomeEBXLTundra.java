package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLTundra;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLTundra;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLTundra extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.tundra.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLTundra()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLTundra(90f, 180f, 13f, 100f, 38f, 260f, 71f),
			new SurfaceEBXLTundra(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Tundra");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEBXL.weightEBXL_tundra;
	}
}
