package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaForest;
import rtg.terrain.vanilla.TerrainVanillaForest;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.forest.topBlock;
	public static Block fillerBlock = BiomeGenBase.forest.fillerBlock;
	
	public RealisticBiomeVanillaForest()
	{
		super(
			BiomeGenBase.forest,
			VanillaBiomes.vanillaRiverTemperate,
			new TerrainVanillaForest(),
			new SurfaceVanillaForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
