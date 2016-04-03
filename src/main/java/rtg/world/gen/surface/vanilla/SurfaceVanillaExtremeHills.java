package rtg.world.gen.surface.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.math.CliffCalculator;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceVanillaExtremeHills extends SurfaceBase {

    private float width = 60f;
    private float height = -0.14f;
    private float smallW = 14f;
    private float smallS = 0.25f;

    public SurfaceVanillaExtremeHills(RealisticBiomeBase biome) {

        super(biome);
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f;
        boolean mix = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState(x, k, y, hcCobble());
                        } else {

                            primer.setBlockState(x, k, y, hcStone());
                        }
                    } else if (depth < 10) {
                        primer.setBlockState(x, k, y, hcStone());
                    }
                } else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height) {
                            primer.setBlockState(x, k, y, biome.config.MIX_BLOCK_TOP.get());
                            mix = true;
                        } else {
                            primer.setBlockState(x, k, y, biome.config.TOP_BLOCK.get());
                        }
                    } else if (depth < 4) {
                        if (mix) {
                            primer.setBlockState(x, k, y, biome.config.MIX_BLOCK_FILL.get());
                        } else {
                            primer.setBlockState(x, k, y, biome.config.FILL_BLOCK.get());
                        }
                    }
                }
            }
        }
    }
}
