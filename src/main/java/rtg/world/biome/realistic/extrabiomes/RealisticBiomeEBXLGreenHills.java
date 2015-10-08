package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLGreenHills;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLGreenHills;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLGreenHills extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.greenhills.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLGreenHills()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLGreenHills(230f, 120f, 0f),
			new SurfaceEBXLGreenHills(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EBXL Green Hills");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEBXL.weightEBXL_greenhills;
	}
}
