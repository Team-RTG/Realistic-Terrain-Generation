package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLSavanna;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLSavanna;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLSavanna extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.savanna.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLSavanna(BiomeConfig config)
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBXLSavanna(),
			new SurfaceEBXLSavanna(topBlock, fillerBlock, Blocks.sand, 13f, 0.27f)
		);
		
		this.config = config;
		this.biomeWeight = ConfigEBXL.weightEBXLSavanna;
		this.generateVillages = ConfigEBXL.villageEBXLSavanna;
	}
}
