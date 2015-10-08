package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLForestedHills;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLForestedHills;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLForestedHills extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.forestedhills.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLForestedHills()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLForestedHills(230f, 120f, 0f),
			new SurfaceEBXLForestedHills(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EBXL Forested Hills");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEBXL.weightEBXL_forestedhills;
	}
}
