package rtg.api.world.surface.templates;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;

public class SurfaceIslandMountainStone extends SurfaceBase {

    private float min;

    private float sCliff = 1.5f;
    private float sHeight = 60f;
    private float sStrength = 65f;
    private float cCliff = 1.5f;

    public SurfaceIslandMountainStone(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff) {

        super(config, top, fill);
        min = minCliff;
    }

    public SurfaceIslandMountainStone(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff, float stoneHeight, float stoneStrength, float clayCliff) {

        this(config, top, fill, minCliff);

        sCliff = stoneCliff;
        sHeight = stoneHeight;
        sStrength = stoneStrength;
        cCliff = clayCliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand;
        OpenSimplexNoise simplex = rtgWorld.simplex;
        float c = CliffCalculator.calc(x, z, noise);
        int cliff = 0;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, z).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth == 0) {

                    float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                    if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                        cliff = 1;
                    }
                    if (c > cCliff) {
                        cliff = 2;
                    }

                    if (cliff == 1) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                        }
                        else {

                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                    }
                    else if (cliff == 2) {
                        primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                    }
                    else {
                        primer.setBlockState(x, k, z, topBlock);
                    }
                }
                else if (depth < 6) {
                    if (cliff == 1) {
                        primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                    }
                    else if (cliff == 2) {
                        primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                    }
                    else {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }
                }
            }
        }
    }
}
