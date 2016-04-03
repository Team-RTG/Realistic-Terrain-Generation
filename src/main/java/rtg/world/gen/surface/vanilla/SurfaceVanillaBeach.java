package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBeach;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaBeach extends SurfaceBase {

    public SurfaceVanillaBeach(RealisticBiomeVanillaBeach biome) {
        super(biome);
    }
    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f;
        boolean dirt = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (cliff) {
                    if (depth < 6) {
                        primer.setBlockState(x, k, y, biome.config.CLIFF_BLOCK_1.get().getBlock().getStateFromMeta(14));
                    }
                } else if (depth < 6) {
                    if (depth == 0 && k > 61 && k < 64) {
                        //if(simplex.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f))
                        primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                    } else if (depth < 4) {
                        if (k > 61 && k < 69) {
                            primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                        }
                    } else {
                        if (k > 56 && k < 68) { // one lower for under sand and 4 deeper
                            primer.setBlockState(x, k, y, Blocks.sandstone.getDefaultState());
                        } else {
                            primer.setBlockState(x, k, y, Blocks.stone.getDefaultState());
                        }
                    }
                }
            }
        }
    }
}
