package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaForest;
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
			RTGBiomes.baseRiverTemperate,
			new CoastVanillaForest(),
			new TerrainVanillaForest(),
			new SurfaceVanillaForest(BiomeGenBase.forest.topBlock, BiomeGenBase.forest.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
