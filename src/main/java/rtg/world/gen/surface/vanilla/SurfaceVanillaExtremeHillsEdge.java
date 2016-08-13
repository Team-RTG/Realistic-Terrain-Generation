package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHillsEdge;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceVanillaExtremeHillsEdge extends SurfaceBase {

    private IBlockState mixBlockTop;
    private IBlockState mixBlockFill;
    private float width;
    private float height;
    private float smallW;
    private float smallS;

    public SurfaceVanillaExtremeHillsEdge(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, float mixWidth,
                                          float mixHeight, float smallWidth, float smallStrength) {

        super(config, top, filler);

        mixBlockTop = this.getConfigBlock(config, BiomeConfigVanillaExtremeHillsEdge.surfaceMixBlockId,
            BiomeConfigVanillaExtremeHillsEdge.surfaceMixBlockMetaId,
            mixTop);

        mixBlockFill = this.getConfigBlock(config, BiomeConfigVanillaExtremeHillsEdge.surfaceMixFillerBlockId,
            BiomeConfigVanillaExtremeHillsEdge.surfaceMixFillerBlockMetaId, mixFill
        );

        width = mixWidth;
        height = mixHeight;
        smallW = smallWidth;
        smallS = smallStrength;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                             OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.4f ? true : false;
        boolean mix = false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            }
            else if (b == Blocks.stone) {
                depth++;

                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState((y * 16 + x) * 256 + k, hcCobble(world, i, j, x, y, k));
                        }
                        else {

                            primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else if (depth < 10) {
                        primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                    }
                }
                else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height) {
                            primer.setBlockState((y * 16 + x) * 256 + k, mixBlockTop);
                            mix = true;
                        }
                        else {
                            primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
                        }
                    }
                    else if (depth < 4) {
                        if (mix) {
                            primer.setBlockState((y * 16 + x) * 256 + k, mixBlockFill);
                        }
                        else {
                            primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
