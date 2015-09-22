package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaBirchForest;
import rtg.terrain.vanilla.TerrainVanillaBirchForest;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaBirchForest extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.birchForest.topBlock;
	public static Block fillerBlock = BiomeGenBase.birchForest.fillerBlock;
	
	public RealisticBiomeVanillaBirchForest()
	{
		super(
			BiomeGenBase.birchForest,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaBirchForest(),
			new SurfaceVanillaBirchForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
