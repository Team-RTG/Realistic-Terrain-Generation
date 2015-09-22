package rtg.world.biome.realistic.vanilla;

import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsPlus;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsPlus;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaExtremeHillsPlus extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.extremeHillsPlus.topBlock;
	public static Block fillerBlock = BiomeGenBase.extremeHillsPlus.fillerBlock;
	
	public RealisticBiomeVanillaExtremeHillsPlus()
	{
		super(
			BiomeGenBase.extremeHillsPlus,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaExtremeHillsPlus(),
			new SurfaceVanillaExtremeHillsPlus(Blocks.gravel, Blocks.stone, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
	}	
}
