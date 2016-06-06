package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import teamrtg.rtg.api.module.Mods;

import java.util.Random;

import static net.minecraft.init.Blocks.SAPLING;
import static net.minecraft.util.EnumFacing.UP;

public class WorldGenTreeRTGTrees extends WorldGenTrees {
    private static final String __OBFID = "CL_00000438";
    /**
     * The minimum height of a generated tree.
     */
    private final int minTreeHeight;
    /**
     * True if this tree should grow Vines.
     */
    private final boolean vinesGrow;
    /**
     * The metadata value of the wood to use in tree generation.
     */
    private final int metaWood;
    /**
     * The metadata value of the LEAVES to use in tree generation.
     */
    private final int metaLeaves;

    public WorldGenTreeRTGTrees(boolean doBlockNotify) {
        this(false, 4, 0, 0, false);
    }

    public WorldGenTreeRTGTrees(boolean doBlockNotify, int minTreeHeight, int metaWood, int metaLeaves, boolean vinesGrow) {
        super(doBlockNotify);
        this.minTreeHeight = 4;
        this.metaWood = 0;
        this.metaLeaves = 0;
        this.vinesGrow = false;
    }

    public boolean generate(World world, Random rand, int worldX, int worldY, int worldZ) {
        int l = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (worldY >= 1 && worldY + l + 1 <= 256) {
            byte b0;
            int k1;
            Block block;

            for (int i1 = worldY; i1 <= worldY + 1 + l; ++i1) {
                b0 = 1;

                if (i1 == worldY) {
                    b0 = 0;
                }

                if (i1 >= worldY + 1 + l - 2) {
                    b0 = 2;
                }

                for (int j1 = worldX - b0; j1 <= worldX + b0 && flag; ++j1) {
                    for (k1 = worldZ - b0; k1 <= worldZ + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            block = world.getBlockState(new BlockPos(j1, i1, k1)).getBlock();

                            if (!this.isReplaceable(world, new BlockPos(j1, i1, k1))) {
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
                Block block2 = world.getBlockState(new BlockPos(worldX, worldY - 1, worldZ)).getBlock();

                if (block2 == Blocks.SAND && !Mods.RTG.config.ALLOW_TREES_ON_SAND.get()) {
                    return false;
                }

                boolean isSoil = block2.canSustainPlant(world.getBlockState(new BlockPos(worldX, worldY - 1, worldZ)), world, new BlockPos(worldX, worldY - 1, worldZ), UP, (BlockSapling) SAPLING);
                if (isSoil && worldY < 256 - l - 1) {
                    block2.onPlantGrow(world.getBlockState(new BlockPos(worldX, worldY - 1, worldZ)), world, new BlockPos(worldX, worldY - 1, worldZ), new BlockPos(worldX, worldY, worldZ));
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = worldY - b0 + l; k1 <= worldY + l; ++k1) {
                        i3 = k1 - (worldY + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = worldX - l1; i2 <= worldX + l1; ++i2) {
                            j2 = i2 - worldX;

                            for (int k2 = worldZ - l1; k2 <= worldZ + l1; ++k2) {
                                int l2 = k2 - worldZ;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0) {
                                    Block block1 = world.getBlockState(new BlockPos(i2, k1, k2)).getBlock();

                                    if (block1.isAir(world.getBlockState(new BlockPos(i2, k1, k2)), world, new BlockPos(i2, k1, k2)) || block1.isLeaves(world.getBlockState(new BlockPos(i2, k1, k2)), world, new BlockPos(i2, k1, k2))) {
                                        this.setBlockAndNotifyAdequately(world, new BlockPos(i2, k1, k2), Blocks.LEAVES.getStateFromMeta(this.metaLeaves));
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1) {
                        block = world.getBlockState(new BlockPos(worldX, worldY + k1, worldZ)).getBlock();

                        if (block.isAir(world.getBlockState(new BlockPos(worldX, worldY + k1, worldZ)), world, new BlockPos(worldX, worldY + k1, worldZ)) || block.isLeaves(world.getBlockState(new BlockPos(worldX, worldY + k1, worldZ)), world, new BlockPos(worldX, worldY + k1, worldZ))) {
                            this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY + k1, worldZ), Blocks.LOG.getStateFromMeta(this.metaWood));

                            if (this.vinesGrow && k1 > 0) {
                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX - 1, worldY + k1, worldZ))) {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX - 1, worldY + k1, worldZ), Blocks.VINE.getStateFromMeta(8));
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX + 1, worldY + k1, worldZ))) {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX + 1, worldY + k1, worldZ), Blocks.VINE.getStateFromMeta(2));
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX, worldY + k1, worldZ - 1))) {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY + k1, worldZ - 1), Blocks.VINE.getStateFromMeta(1));
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX, worldY + k1, worldZ + 1))) {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY + k1, worldZ + 1), Blocks.VINE.getStateFromMeta(4));
                                }
                            }
                        }
                    }

                    if (this.vinesGrow) {
                        for (k1 = worldY - 3 + l; k1 <= worldY + l; ++k1) {
                            i3 = k1 - (worldY + l);
                            l1 = 2 - i3 / 2;

                            for (i2 = worldX - l1; i2 <= worldX + l1; ++i2) {
                                for (j2 = worldZ - l1; j2 <= worldZ + l1; ++j2) {
                                    if (world.getBlockState(new BlockPos(i2, k1, j2)).getBlock().isLeaves(world.getBlockState(new BlockPos(i2, k1, j2)), world, new BlockPos(i2, k1, j2))) {
                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2 - 1, k1, j2)).getBlock().isAir(world.getBlockState(new BlockPos(i2 - 1, k1, j2)), world, new BlockPos(i2 - 1, k1, j2))) {
                                            this.growVines(world, i2 - 1, k1, j2, 8);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2 + 1, k1, j2)).getBlock().isAir(world.getBlockState(new BlockPos(i2 + 1, k1, j2)), world, new BlockPos(i2 + 1, k1, j2))) {
                                            this.growVines(world, i2 + 1, k1, j2, 2);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2, k1, j2 - 1)).getBlock().isAir(world.getBlockState(new BlockPos(i2, k1, j2 - 1)), world, new BlockPos(i2, k1, j2 - 1))) {
                                            this.growVines(world, i2, k1, j2 - 1, 1);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2, k1, j2 + 1)).getBlock().isAir(world.getBlockState(new BlockPos(i2, k1, j2 + 1)), world, new BlockPos(i2, k1, j2 + 1))) {
                                            this.growVines(world, i2, k1, j2 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

                        if (rand.nextInt(5) == 0 && l > 5) {
                            for (k1 = 0; k1 < 2; ++k1) {
                                for (i3 = 0; i3 < 4; ++i3) {
                                    if (rand.nextInt(4 - k1) == 0) {
                                        l1 = rand.nextInt(3);
                                        //TODO: find what replaces Direction
                                        //this.setBlockAndNotifyAdequately(world, new BlockPos(worldX + Direction.offsetX[Direction.rotateOpposite[i3]], worldY + l - 5 + k1, worldZ + Direction.offsetZ[Direction.rotateOpposite[i3]]), Blocks.cocoa.getStateFromMeta( l1 << 2 | i3));
                                    }
                                }
                            }
                        }
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

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, VINE-length
     */
    private void growVines(World world, int worldX, int worldY, int worldZ, int vineLength) {
        this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY, worldZ), Blocks.VINE.getStateFromMeta(vineLength));
        int i1 = 4;

        while (true) {
            --worldY;

            if (!world.getBlockState(new BlockPos(worldX, worldY, worldZ)).getBlock().isAir(world.getBlockState(new BlockPos(worldX, worldY, worldZ)), world, new BlockPos(worldX, worldY, worldZ)) || i1 <= 0) {
                return;
            }

            this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY, worldZ), Blocks.VINE.getStateFromMeta(vineLength));
            --i1;
        }
    }
}