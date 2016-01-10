package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biomes.extrabiomes.config.BiomeConfigEBXLTemperateRainforest;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLTemperateRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLTemperateRainforest;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLTemperateRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.temperaterainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLTemperateRainforest()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBXLTemperateRainforest(300f, 70f, 0f),
			new SurfaceEBXLTemperateRainforest(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
		
		this.biomeConfig = new BiomeConfigEBXLTemperateRainforest();
		this.biomeWeight = ConfigEBXL.weightEBXLTemperateRainforest;
		this.generateVillages = ConfigEBXL.villageEBXLTemperateRainforest;
	}
}
