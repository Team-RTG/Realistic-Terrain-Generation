package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaExtremeHills;
import rtg.terrain.vanilla.TerrainVanillaExtremeHills;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.extremeHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.extremeHills.fillerBlock;
	
	public RealisticBiomeVanillaExtremeHills()
	{
		super(
			BiomeGenBase.extremeHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaExtremeHills(0f, 140f, 68f, 150f),
			new SurfaceVanillaExtremeHills(topBlock, fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
	}	
}
