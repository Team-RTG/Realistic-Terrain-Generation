package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.tree.WorldGenTreeBig;
import rtg.world.gen.feature.tree.WorldGenTreeBirch;
import rtg.world.gen.feature.tree.WorldGenTreePineBig;
import rtg.world.gen.feature.tree.WorldGenTreePineEuro;
import rtg.world.gen.feature.tree.WorldGenTreePineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeSavanna;
import rtg.world.gen.feature.tree.WorldGenTreeWillow;
import rtg.world.gen.surface.vanilla.SurfaceVanillaPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.plains.topBlock;
	public static Block fillerBlock = BiomeGenBase.plains.fillerBlock;
	
	public RealisticBiomeVanillaPlains()
	{
		super(
			BiomeGenBase.plains,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaPlains(),
			new SurfaceVanillaPlains(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}
}
