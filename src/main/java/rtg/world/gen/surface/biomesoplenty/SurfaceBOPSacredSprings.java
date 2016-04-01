package rtg.world.gen.surface.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceBOPSacredSprings extends SurfaceBase {

    public SurfaceBOPSacredSprings(BiomeConfig config, IBlockState top, IBlockState filler) {
        super(config, top, filler);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                        } else {

                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                    } else if (depth < 10) {
                        primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                    }
                } else {
                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, k, y, topBlock);
                    } else if (depth < 4) {
                        primer.setBlockState(x, k, y, fillerBlock);
                    }
                }
            }
        }
    }
}
