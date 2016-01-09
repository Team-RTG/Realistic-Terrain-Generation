package rtg.world.biome.realistic.extrabiomes;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLForestedHills;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLForestedHills;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLForestedHills extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.forestedhills.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLForestedHills()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLForestedHills(300f, 100f, 0f),
			new SurfaceEBXLForestedHills(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EBXL Forested Hills");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXLForestedHills;
		this.generateVillages = ConfigEBXL.villageEBXLForestedHills;
	}
}
