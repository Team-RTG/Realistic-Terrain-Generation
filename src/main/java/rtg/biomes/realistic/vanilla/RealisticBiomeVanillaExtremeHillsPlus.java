package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaExtremeHillsPlus;
import rtg.surface.vanilla.SurfaceVanillaExtremeHillsPlus;
import rtg.terrain.vanilla.TerrainVanillaExtremeHillsPlus;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaExtremeHillsPlus extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.extremeHillsPlus.topBlock;
	public static Block fillerBlock = BiomeGenBase.extremeHillsPlus.fillerBlock;
	
	public RealisticBiomeVanillaExtremeHillsPlus()
	{
		super(
			BiomeGenBase.extremeHillsPlus,
			VanillaBiomes.vanillaRiverCold,
			new CoastVanillaExtremeHillsPlus(),
			new TerrainVanillaExtremeHillsPlus(),
			new SurfaceVanillaExtremeHillsPlus(Blocks.gravel, Blocks.gravel, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
	}	
}
