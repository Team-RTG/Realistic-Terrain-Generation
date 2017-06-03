package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.util.SnowHeightCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase {

	public static Biome biome = Biomes.ICE_PLAINS;
	public static Biome river = Biomes.FROZEN_RIVER;
	
	public RealisticBiomeVanillaIcePlains() {

		super(biome, river);
	}

	@Override
	public void initConfig() {

		this.getConfig().addProperty(this.getConfig().USE_ARCTIC_SURFACE).set(true);
		this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
	}

	@Override
	public TerrainBase initTerrain() {

		return new TerrainVanillaIcePlains();
	}

    public class TerrainVanillaIcePlains extends TerrainBase {

        public TerrainVanillaIcePlains() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

			return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 200f, 65f);
        }
    }

	@Override
	public SurfaceBase initSurface() {

	    if (this.getConfig().USE_ARCTIC_SURFACE.get()) {

            return new SurfacePolar(config,
                Blocks.SNOW.getDefaultState(), //Block top
                biome.fillerBlock, //Block filler,
                Blocks.SNOW.getDefaultState(), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            );
        }
        else {

            return new SurfaceVanillaIcePlains(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.topBlock);
        }

	}

	public class SurfaceVanillaIcePlains extends SurfaceBase
	{
		private IBlockState cliffBlock1;
		private IBlockState cliffBlock2;

		public SurfaceVanillaIcePlains(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2)
		{
			super(config, top, filler);

			cliffBlock1 = cliff1;
			cliffBlock2 = cliff2;
		}

		@Override
		public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

			Random rand = rtgWorld.rand;
			float c = CliffCalculator.calc(x, z, noise);
			boolean cliff = c > 1.4f ? true : false;

			for (int k = 255; k > -1; k--) {
				Block b = primer.getBlockState(x, k, z).getBlock();
				if (b == Blocks.AIR) {
					depth = -1;
				}
				else if (b == Blocks.STONE) {
					depth++;

					if(cliff)
					{
						if(depth > -1 && depth < 2)
						{
							primer.setBlockState(x, k, z, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
						}
						else if (depth < 10)
						{
							primer.setBlockState(x, k, z, cliffBlock1);
						}
					}
					else
					{
						if(depth == 0 && k > 61)
						{
							primer.setBlockState(x, k, z, topBlock);
						}
						else if(depth < 4)
						{
							primer.setBlockState(x, k, z, fillerBlock);
						}
					}
				}
			}
		}
	}

	public class SurfacePolar extends SurfaceBase {

		private IBlockState blockMixTop;
		private IBlockState blockMixFiller;
		private float floMixWidth;
		private float floMixHeight;
		private float floSmallWidth;
		private float floSmallStrength;

		public SurfacePolar(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
									float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

			super(config, top, filler);

			blockMixTop = mixTop;
			blockMixFiller = mixFiller;

			floMixWidth = mixWidth;
			floMixHeight = mixHeight;
			floSmallWidth = smallWidth;
			floSmallStrength = smallStrength;
		}

		@Override
		public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

			Random rand = rtgWorld.rand;
			OpenSimplexNoise simplex = rtgWorld.simplex;
			boolean water = false;
			boolean riverPaint = false;
			boolean grass = false;

			if (river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.1f) > 0.86f) {
				riverPaint = true;

				if (simplex.noise2(i / 12f, j / 12f) > 0.25f) {
					grass = true;
				}
			}

			Block b;
			for (int k = 255; k > -1; k--) {
				b = primer.getBlockState(x, k, z).getBlock();
				if (b == Blocks.AIR) {
					depth = -1;
				}
				else if (b == Blocks.STONE) {
					depth++;

					if (riverPaint) {
						if (grass && depth < 4) {
							primer.setBlockState(x, k, z, fillerBlock);
						}
						else if (depth == 0) {
							if (rand.nextInt(2) == 0) {

								primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
							}
							else {

								primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
							}
						}
					}
					else if (depth > -1 && depth < 9) {
						primer.setBlockState(x, k, z, topBlock);

						if (depth == 0 && k > 61 && k < 254) {
							SnowHeightCalculator.calc(x, k, z, primer, noise);
						}
					}
				}
				else if (!water && b == Blocks.WATER) {
					primer.setBlockState(x, k, z, Blocks.ICE.getDefaultState());
					water = true;
				}
			}
		}
	}

	@Override
	public void initDecos() {

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
