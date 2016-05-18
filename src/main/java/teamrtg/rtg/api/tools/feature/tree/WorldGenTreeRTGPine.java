package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.abs;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.EnumFacing.UP;

public class WorldGenTreeRTGPine extends WorldGenerator {
    private int height;
    private int metadata;

    public WorldGenTreeRTGPine(int h, int m) {
        super(false);

        height = 4;
        metadata = m;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        int l = p_76484_2_.nextInt(height * 2) + height * 2;
        int i1 = height + p_76484_2_.nextInt(height);
        int j1 = l - i1;
        int k1 = 2 + p_76484_2_.nextInt(2);
        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256) {
            int i2;
            int l3;

            for (int l1 = p_76484_4_; l1 <= p_76484_4_ + 1 + l && flag; ++l1) {
                boolean flag1 = true;

                if (l1 - p_76484_4_ < i1) {
                    l3 = 0;
                } else {
                    l3 = k1;
                }

                for (i2 = p_76484_3_ - l3; i2 <= p_76484_3_ + l3 && flag; ++i2) {
                    for (int j2 = p_76484_5_ - l3; j2 <= p_76484_5_ + l3 && flag; ++j2) {
                        if (l1 >= 0 && l1 < 256) {
                            Block block = p_76484_1_.getBlockState(new BlockPos(i2, l1, j2)).getBlock();

                            if (!block.isAir(p_76484_1_.getBlockState(new BlockPos(i2, l1, j2)), p_76484_1_, new BlockPos(i2, l1, j2)) && !block.isLeaves(p_76484_1_.getBlockState(new BlockPos(i2, l1, j2)), p_76484_1_, new BlockPos(i2, l1, j2)) && block != SNOW_LAYER) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block block1 = p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_)).getBlock();

                boolean isSoil = block1.canSustainPlant(p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_)), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_), UP, (BlockSapling) SAPLING);
                if (isSoil && p_76484_4_ < 256 - l - 1) {
                    block1.onPlantGrow(p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_)), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ - 1, p_76484_5_), new BlockPos(p_76484_3_, p_76484_4_, p_76484_5_));
                    l3 = p_76484_2_.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4) {
                        k2 = p_76484_4_ + l - i4;

                        for (int l2 = p_76484_3_ - l3; l2 <= p_76484_3_ + l3; ++l2) {
                            int i3 = l2 - p_76484_3_;

                            for (int j3 = p_76484_5_ - l3; j3 <= p_76484_5_ + l3; ++j3) {
                                int k3 = j3 - p_76484_5_;

                                if ((abs(i3) != l3 || abs(k3) != l3 || l3 <= 0) && p_76484_1_.getBlockState(new BlockPos(l2, k2, j3)).getBlock().canBeReplacedByLeaves(p_76484_1_.getBlockState(new BlockPos(l2, k2, j3)), p_76484_1_, new BlockPos(l2, k2, j3))) {
                                    p_76484_1_.setBlockState(new BlockPos(l2, k2, j3), LEAVES.getStateFromMeta(metadata), 0);
                                }
                            }
                        }

                        if (l3 >= i2) {
                            l3 = b0;
                            b0 = 1;
                            ++i2;

                            if (i2 > k1) {
                                i2 = k1;
                            }
                        } else {
                            ++l3;
                        }
                    }

                    i4 = p_76484_2_.nextInt(3);

                    for (k2 = 0; k2 < l - i4; ++k2) {
                        Block block2 = p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)).getBlock();

                        if (block2.isAir(p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)) || block2.isLeaves(p_76484_1_.getBlockState(new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)), p_76484_1_, new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_)) || block2 == SNOW_LAYER) {
                            p_76484_1_.setBlockState(new BlockPos(p_76484_3_, p_76484_4_ + k2, p_76484_5_), LOG.getDefaultState(), 0);
                        }
                    }

                    if (height > 4) {
                        createTrunk(p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_);
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private void createTrunk(World world, Random rand, int x, int y, int z) {
        int[] pos = new int[] {0, 0, 1, 0, 0, 1, -1, 0, 0, -1};
        int sh;
        Block b;
        for (int t = 0; t < 5; t++) {
            sh = rand.nextInt(4) + y - 2;
            while (sh > y - 1) {
                if (world.getBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1])).getBlock() == GRASS) {
                    break;
                }
                world.setBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1]), LOG.getStateFromMeta(12), 0);
                sh--;
            }
        }
    }
}