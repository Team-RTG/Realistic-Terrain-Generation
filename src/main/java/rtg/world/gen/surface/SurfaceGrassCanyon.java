package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;

import java.util.Random;

public class SurfaceGrassCanyon extends SurfaceBase {
    private byte claycolor;

    public SurfaceGrassCanyon(BiomeConfig config, IBlockState top, IBlockState fill, byte clayByte) {
        super(config, top, fill);
        claycolor = clayByte;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        float c = CliffCalculator.calc(x, y, noise);
        boolean cliff = c > 1.3f ? true : false;

        for (int k = 255; k > -1; k--) {
            Block b = primer.getBlockState(x, 256 + k, y).getBlock();
            if (b == Blocks.air) {
                depth = -1;
            } else if (b == Blocks.stone) {
                depth++;

                if (depth > -1 && depth < 12) {
                    if (cliff) {
                        primer.setBlockState(x, 256 + k, y, Blocks.stained_hardened_clay.getStateFromMeta(claycolor));
                    } else {
                        if (depth > 4) {
                            primer.setBlockState(x, 256 + k, y, Blocks.stained_hardened_clay.getStateFromMeta(claycolor));
                        } else {
                            if (depth == 0) {
                                primer.setBlockState(x, 256 + k, y, topBlock);
                            } else {
                                primer.setBlockState(x, 256 + k, y, fillerBlock);
                            }
                        }
                    }
                } else if (k > 63) {
                    primer.setBlockState(x, 256 + k, y, Blocks.stained_hardened_clay.getStateFromMeta(claycolor));
                }
            }
        }
    }
}
