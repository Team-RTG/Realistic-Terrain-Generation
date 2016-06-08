package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.abs;
import static net.minecraft.block.material.Material.AIR;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.math.MathHelper.abs_int;
import static net.minecraft.util.math.MathHelper.ceiling_float_int;

public class WorldGenIceSpike extends WorldGenerator {
    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }


    public boolean generate(World world, Random rand, int x, int y, int z) {
        while (world.isAirBlock(new BlockPos(x, y, z)) && y > 2) {
            --y;
        }

        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (block != SNOW && block != SNOW_LAYER) {
            return false;
        } else {
            y += rand.nextInt(4);
            int l = rand.nextInt(4) + 7;
            int i1 = l / 4 + rand.nextInt(2);

            if (i1 > 1 && rand.nextInt(60) == 0) {
                y += 10 + rand.nextInt(30);
            }

            int j1;
            int k1;
            int l1;

            for (j1 = 0; j1 < l; ++j1) {
                float f = (1.0F - (float) j1 / (float) l) * (float) i1;
                k1 = ceiling_float_int(f);

                for (l1 = -k1; l1 <= k1; ++l1) {
                    float f1 = (float) abs_int(l1) - 0.25F;

                    for (int i2 = -k1; i2 <= k1; ++i2) {
                        float f2 = (float) abs_int(i2) - 0.25F;

                        if ((l1 == 0 && i2 == 0 || f1 * f1 + f2 * f2 <= f * f) && (l1 != -k1 && l1 != k1 && i2 != -k1 && i2 != k1 || rand.nextFloat() <= 0.75F)) {
                            block = world.getBlockState(new BlockPos(x + l1, y + j1, z + i2)).getBlock();

                            if (block.getMaterial(block.getDefaultState()) == AIR || block == DIRT || block == SNOW || block == ICE) {
                                this.setBlockAndNotifyAdequately(world, new BlockPos(x + l1, y + j1, z + i2), PACKED_ICE.getDefaultState());
                            }

                            if (j1 != 0 && k1 > 1) {
                                block = world.getBlockState(new BlockPos(x + l1, y - j1, z + i2)).getBlock();

                                if (block.getMaterial(block.getDefaultState()) == AIR || block == DIRT || block == SNOW || block == ICE) {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(x + l1, y - j1, z + i2), PACKED_ICE.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }

            j1 = i1 - 1;

            if (j1 < 0) {
                j1 = 0;
            } else if (j1 > 1) {
                j1 = 1;
            }

            for (int j2 = -j1; j2 <= j1; ++j2) {
                k1 = -j1;

                while (k1 <= j1) {
                    l1 = y - 1;
                    int k2 = 50;

                    if (abs(j2) == 1 && abs(k1) == 1) {
                        k2 = rand.nextInt(5);
                    }

                    while (true) {
                        if (l1 > 50) {
                            block = world.getBlockState(new BlockPos(x + j2, l1, z + k1)).getBlock();

                            if (block.getMaterial(block.getDefaultState()) == AIR || block == DIRT || block == SNOW || block == ICE || block == PACKED_ICE) {
                                this.setBlockAndNotifyAdequately(world, new BlockPos(x + j2, l1, z + k1), PACKED_ICE.getDefaultState());
                                --l1;
                                --k2;

                                if (k2 <= 0) {
                                    l1 -= rand.nextInt(5) + 1;
                                    k2 = rand.nextInt(5);
                                }

                                continue;
                            }
                        }

                        ++k1;
                        break;
                    }
                }
            }

            return true;
        }
    }
}