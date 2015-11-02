package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMiniJungle;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMiniJungle;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLMiniJungle extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.minijungle.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMiniJungle()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLMiniJungle(0f, 140f, 68f, 200f),
			new SurfaceEBXLMiniJungle(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Mini Jungle");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXL_minijungle;
	}
}
