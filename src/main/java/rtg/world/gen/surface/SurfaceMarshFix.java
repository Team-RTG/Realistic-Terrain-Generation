package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

import java.util.Random;

public class SurfaceMarshFix extends SurfaceBase {



    public SurfaceMarshFix(RealisticBiomeBase biome) {
        super(biome);



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

                if (cliff && k > 64) {
                    if (depth > -1 && depth < 2) {
                        primer.setBlockState(x, k, y, rand.nextInt(3) == 0 ? biome.config.CLIFF_BLOCK_2.get() : biome.config.CLIFF_BLOCK_1.get());
                    } else if (depth < 10) {
                        primer.setBlockState(x, k, y, biome.config.CLIFF_BLOCK_1.get());
                    }
                } else {
                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                    } else if (depth < 4) {
                        primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                    }
                }
            }
        }
    }
}
