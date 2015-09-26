package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.tree.WorldGenTreeSavanna;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesert;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesert;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.desert.topBlock;
	public static Block fillerBlock = BiomeGenBase.desert.fillerBlock;
	
	public RealisticBiomeVanillaDesert()
	{
		super(
			BiomeGenBase.desert,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainVanillaDesert(),
			new SurfaceVanillaDesert(topBlock, fillerBlock)
		);
	}
}
