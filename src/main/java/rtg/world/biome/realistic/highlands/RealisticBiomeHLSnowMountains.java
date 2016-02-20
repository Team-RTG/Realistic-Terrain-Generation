package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLSnowMountains;
import rtg.world.gen.terrain.highlands.TerrainHLSnowMountains;

public class RealisticBiomeHLSnowMountains extends RealisticBiomeHLBase
{	
	public static BiomeGenBase hlBiome = HighlandsBiomes.snowMountains;
	
	public static Block topBlock = hlBiome.topBlock.getBlock();
	public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeHLSnowMountains(BiomeConfig config)
	{
		super(config, 
			hlBiome, BiomeGenBase.frozenRiver,
			new TerrainHLSnowMountains(),
			new SurfaceHLSnowMountains(config, topBlock, fillerBlock, false, null, 1.2f)
		);
	}
}
