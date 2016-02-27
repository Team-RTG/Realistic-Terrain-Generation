package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMushroomIslandShore;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase
{	
	public static IBlockState topBlock = BiomeGenBase.mushroomIslandShore.topBlock;
	public static  IBlockState fillerBlock = BiomeGenBase.mushroomIslandShore.fillerBlock;
	
	public RealisticBiomeVanillaMushroomIslandShore(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.mushroomIslandShore,
			BiomeGenBase.river,
			new TerrainVanillaMushroomIslandShore(),
			new SurfaceVanillaMushroomIslandShore(config, topBlock, fillerBlock, 67, topBlock, 0f)
		);
	}	
}
