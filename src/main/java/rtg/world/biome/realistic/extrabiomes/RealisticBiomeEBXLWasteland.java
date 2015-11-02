package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLWasteland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLWasteland;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLWasteland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.wasteland.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLWasteland()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBXLWasteland(30f, 180f, 13f, 100f, 28f, 260f, 70f),
			new SurfaceEBXLWasteland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Wasteland");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXL_wasteland;
	}
}
