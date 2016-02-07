package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.gen.surface.highlands.SurfaceHLSnowMountains;
import rtg.world.gen.terrain.highlands.TerrainHLSnowMountains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLSnowMountains extends RealisticBiomeHLBase
{	
	public static BiomeGenBase hlBiome = HighlandsBiomes.snowMountains;
	
	public static Block topBlock = hlBiome.topBlock;
	public static Block fillerBlock = hlBiome.fillerBlock;
	
	public RealisticBiomeHLSnowMountains(BiomeConfig config)
	{
		super(
			hlBiome, BiomeGenBase.frozenRiver,
			new TerrainHLSnowMountains(),
			new SurfaceHLSnowMountains(topBlock, fillerBlock, false, null, 1.2f)
		);
		
		this.config = config;
	}
}
