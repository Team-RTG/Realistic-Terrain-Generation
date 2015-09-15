package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaRoofedForest;
import rtg.surface.vanilla.SurfaceVanillaRoofedForest;
import rtg.terrain.vanilla.TerrainVanillaRoofedForest;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.roofedForest.topBlock;
	public static Block fillerBlock = BiomeGenBase.roofedForest.fillerBlock;
	
	public RealisticBiomeVanillaRoofedForest()
	{
		super(
			BiomeGenBase.roofedForest,
			VanillaBiomes.vanillaRiverTemperate,
			new CoastVanillaRoofedForest(),
			new TerrainVanillaRoofedForest(),
			new SurfaceVanillaRoofedForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
