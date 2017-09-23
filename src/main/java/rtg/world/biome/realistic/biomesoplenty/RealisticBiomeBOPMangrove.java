package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.world.IRTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeBOPMangrove extends RealisticBiomeBOPBase {

	public static Biome biome = BOPBiomes.mangrove.get();
	public static Biome river = Biomes.RIVER;
	
	public RealisticBiomeBOPMangrove()
	{
		super(biome, river);
	}

	@Override
	public void initConfig() {}

	@Override
	public TerrainBase initTerrain() {

		return new TerrainBOPMangrove();
	}

	public class TerrainBOPMangrove extends TerrainBase
	{
		public TerrainBOPMangrove()
		{
		}

		@Override
		public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {
			return terrainBeach(x, y, rtgWorld.simplex(), river, 180f, 35f, 60f);
		}
	}

	@Override
	public SurfaceBase initSurface() {

		return new SurfaceBOPMangrove(config, biome.topBlock, biome.fillerBlock);
	}

	public class SurfaceBOPMangrove extends SurfaceBase {

		public SurfaceBOPMangrove(BiomeConfig config, IBlockState top, IBlockState filler) {

			super(config, top, filler);
		}

		@Override
		public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

			Random rand = rtgWorld.rand();
			float c = CliffCalculator.calc(x, z, noise);
			boolean cliff = c > 1.4f ? true : false;

			for (int k = 255; k > -1; k--) {
				Block b = primer.getBlockState(x, k, z).getBlock();
				if (b == Blocks.AIR) {
					depth = -1;
				}
				else if (b == Blocks.STONE) {
					depth++;

					if (cliff && k > 64) {
						if (depth > -1 && depth < 2) {
							if (rand.nextInt(3) == 0) {

								primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
							}
							else {

								primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
							}
						}
						else if (depth < 10) {
							primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
						}
					}
					else {
						if (depth == 0 && k > 61) {
							primer.setBlockState(x, k, z, topBlock);
						}
						else if (depth < 4) {
							primer.setBlockState(x, k, z, fillerBlock);
						}
					}
				}
			}
		}
	}

	@Override
	public void initDecos() {
		DecoBOPBaseBiomeDecorations decoBOPBaseBiomeDecorations = new DecoBOPBaseBiomeDecorations();
		this.addDeco(decoBOPBaseBiomeDecorations);
	}
}
