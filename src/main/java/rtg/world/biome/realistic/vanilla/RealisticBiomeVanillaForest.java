package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaForest;
import rtg.terrain.vanilla.TerrainVanillaForest;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.forest.topBlock;
	public static Block fillerBlock = BiomeGenBase.forest.fillerBlock;
	
	public RealisticBiomeVanillaForest()
	{
		super(
			BiomeGenBase.forest,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaForest(),
			new SurfaceVanillaForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
