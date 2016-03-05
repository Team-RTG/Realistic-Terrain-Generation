package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaStoneBeach;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase
{	
	public static  IBlockState topBlock = BiomeGenBase.stoneBeach.topBlock;
	public static IBlockState fillerBlock = BiomeGenBase.stoneBeach.fillerBlock;
	
	public RealisticBiomeVanillaStoneBeach(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.stoneBeach,
			BiomeGenBase.river,
			new TerrainVanillaStoneBeach(),
			new SurfaceVanillaStoneBeach(config, topBlock, fillerBlock, true, Blocks.gravel.getDefaultState(), 1f, 1.5f, 85f, 20f, 4f)
		);
	}	
}
