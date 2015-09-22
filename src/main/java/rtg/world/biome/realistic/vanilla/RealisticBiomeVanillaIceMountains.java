package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.terrain.vanilla.TerrainVanillaIceMountains;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.iceMountains.topBlock;
	public static Block fillerBlock = BiomeGenBase.iceMountains.fillerBlock;
	
	public RealisticBiomeVanillaIceMountains()
	{
		super(
			BiomeGenBase.iceMountains,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
			new TerrainVanillaIceMountains(),
			new SurfaceVanillaIceMountains(topBlock, fillerBlock, Blocks.packed_ice, Blocks.ice)
		);
	}	
}
