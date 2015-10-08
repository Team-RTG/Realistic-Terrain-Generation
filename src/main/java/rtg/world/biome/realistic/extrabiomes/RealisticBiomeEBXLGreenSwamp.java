package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLGreenSwamp;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLGreenSwamp;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLGreenSwamp extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.greenswamp.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLGreenSwamp()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLGreenSwamp(),
			new SurfaceEBXLGreenSwamp(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EBXL Green Swamp");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigEBXL.weightEBXL_greenswamp;
	}
}
