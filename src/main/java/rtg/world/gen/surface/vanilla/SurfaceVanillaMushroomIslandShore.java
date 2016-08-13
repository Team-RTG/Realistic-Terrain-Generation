package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceVanillaMushroomIslandShore extends SurfaceBase {

    private int beach;
    private IBlockState beachBlock;
    private float min;

    private float sCliff = 1.5f;
    private float sHeight = 60f;
    private float sStrength = 65f;
    private float cCliff = 1.5f;

    public SurfaceVanillaMushroomIslandShore(BiomeConfig config, IBlockState top, IBlockState fill, int beachHeight, IBlockState genBeachBlock, float minCliff) {

        super(config, top, fill);
        beach = beachHeight;
        beachBlock = genBeachBlock;
        min = minCliff;
    }

    public SurfaceVanillaMushroomIslandShore(BiomeConfig config, IBlockState top, IBlockState fill, int beachHeight, IBlockState genBeachBlock, float minCliff, float stoneCliff, float stoneHeight, float stoneStrength, float clayCliff) {

        this(config, top, fill, beachHeight, genBeachBlock, minCliff);

        sCliff = stoneCliff;
        sHeight = stoneHeight;
        sStrength = stoneStrength;
        cCliff = clayCliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        int cliff = 0;
        boolean gravel = false;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth == 0) {
                    if (k < beach) {
                        gravel = true;
                    }

                    float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                    if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                        cliff = 1;
                    }
                    if (c > cCliff) {
                        cliff = 2;
                    }

                    if (cliff == 1) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState((y * 16 + x) * 256 + k, hcCobble(world, i, j, x, y, k));
                        }
                        else {

                            primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else if (cliff == 2) {
                        primer.setBlockState((y * 16 + x) * 256 + k, getShadowStoneBlock(world, i, j, x, y, k));
                    }
                    else if (k < beach) {
                        primer.setBlockState((y * 16 + x) * 256 + k, beachBlock);
                        gravel = true;
                    }
                    else {
                        primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                    }
                }
                else if (depth < 6) {
                    if (cliff == 1) {
                        primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                    }
                    else if (cliff == 2) {
                        primer.setBlockState((y * 16 + x) * 256 + k, getShadowStoneBlock(world, i, j, x, y, k));
                    }
                    else if (gravel) {
                        primer.setBlockState((y * 16 + x) * 256 + k, beachBlock);
                    }
                    else {
                        primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                    }
                }
            }
        }
    }
}
