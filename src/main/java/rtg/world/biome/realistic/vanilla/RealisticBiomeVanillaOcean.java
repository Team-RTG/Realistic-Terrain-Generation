package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaOcean;
import rtg.terrain.vanilla.TerrainVanillaOcean;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.ocean.topBlock;
	public static Block fillerBlock = BiomeGenBase.ocean.fillerBlock;
	
	public RealisticBiomeVanillaOcean()
	{
		super(
			BiomeGenBase.ocean,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainVanillaOcean(),
			new SurfaceVanillaOcean(Blocks.sand, Blocks.sand, (byte)0, 0)
		);
	}	
}
