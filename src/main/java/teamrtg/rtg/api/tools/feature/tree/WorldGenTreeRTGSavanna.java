package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.module.Mods;

import java.util.Random;

import static java.lang.Math.*;
import static net.minecraft.init.Blocks.*;


public class WorldGenTreeRTGSavanna extends WorldGenerator {
    private int type;
    private boolean SAND;

    public WorldGenTreeRTGSavanna(int t) {
        this(t, true);
    }

    public WorldGenTreeRTGSavanna(int t, boolean s) {
        type = t;
        SAND = true;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block b = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

        if (b == Blocks.SAND && !Mods.RTG.config.ALLOW_TREES_ON_SAND.get()) {
            return false;
        }

        if (b != GRASS && b != DIRT && ((SAND && b != Blocks.SAND) || !SAND)) {
            return false;
        }

        if (type == 0) {
            int h = 10 + rand.nextInt(5);
            int bh = h - 6;

            for (int i = 0; i < h; i++) {
                world.setBlockState(new BlockPos(x, y + i, z), LOG2.getDefaultState(), 0);
            }
            genLeaves(world, rand, x, y + h, z);

            int sh, eh, dir;
            float xd, yd, c;

            for (int a = 7 - 1 + rand.nextInt(3); a > -1; a--) {
                sh = bh + rand.nextInt(6 - 2);
                eh = h - (int) ((h - sh) * 1f);
                dir = rand.nextInt(360);
                xd = (float) cos(dir * PI / 180f) * 2f;
                yd = (float) sin(dir * PI / 180f) * 2f;
                c = 1;

                while (sh < h) {
                    world.setBlockState(new BlockPos(x + (int) (xd * c), y + sh, z + (int) (yd * c)), LOG2.getDefaultState(), 0);
                    sh++;
                    c += 0.5f;
                }
                genLeaves(world, rand, x + (int) (xd * c), y + sh, z + (int) (yd * c));
            }
        } else if (type == 1) {
            int h = 6 + rand.nextInt(3);
            int bh = h - 3;

            for (int i = 0; i < h; i++) {
                world.setBlockState(new BlockPos(x, y + i, z), LOG2.getDefaultState(), 0);
            }
            genLeaves(world, rand, x, y + h, z);

            int sh, eh, dir;
            float xd, yd, c;

            for (int a = 1 + rand.nextInt(2); a > -1; a--) {
                sh = bh + rand.nextInt(3 - 1);
                eh = h - (int) ((h - sh) * 0.25f);
                dir = rand.nextInt(360);
                xd = (float) cos(dir * PI / 180f) * 2f;
                yd = (float) sin(dir * PI / 180f) * 2f;
                c = 1;

                while (sh < h) {
                    world.setBlockState(new BlockPos(x + (int) (xd * c), y + sh, z + (int) (yd * c)), LOG2.getDefaultState(), 0);
                    sh++;
                    c += 0.5f;
                }
                genLeaves(world, rand, x + (int) (xd * c), y + sh, z + (int) (yd * c));
            }
        } else if (type == 2) {
            int h = 12 + rand.nextInt(5);
            int bh = h - 3;

            for (int i = 0; i < h; i++) {
                world.setBlockState(new BlockPos(x, y + i, z), LOG2.getDefaultState());
            }
            genLeaves(world, rand, x, y + h, z);

            int sh, eh, dir;
            float xd, yd, c;

            for (int a = 1 + rand.nextInt(2); a > -1; a--) {
                sh = bh + rand.nextInt(3 - 1);
                eh = h - (int) ((h - sh) * 0.25f);
                dir = rand.nextInt(360);
                xd = (float) cos(dir * PI / 180f) * 2f;
                yd = (float) sin(dir * PI / 180f) * 2f;
                c = 1;

                while (sh < h) {
                    world.setBlockState(new BlockPos(x + (int) (xd * c), y + sh, z + (int) (yd * c)), LOG2.getDefaultState());
                    sh++;
                    c += 0.5f;
                }
                genLeaves(world, rand, x + (int) (xd * c), y + sh, z + (int) (yd * c));
            }
        }

        return true;
    }

    public void genLeaves(World world, Random rand, int x, int y, int z) {
        if (type == 0) {
            int i;
            int j;
            for (i = -2; i <= 2; i++) {
                for (j = -2; j <= 2; j++) {
                    if (world.isAirBlock(new BlockPos(x + i, y + 1, z + j)) && abs(i) + abs(j) < 4) {
                        world.setBlockState(new BlockPos(x + i, y + 1, z + j), LEAVES2.getDefaultState(), 0);
                    }
                }
            }

            for (i = -3; i <= 3; i++) {
                for (j = -3; j <= 3; j++) {
                    if (world.isAirBlock(new BlockPos(x + i, y, z + j)) && abs(i) + abs(j) < 5) {
                        world.setBlockState(new BlockPos(x + i, y, z + j), LEAVES2.getDefaultState(), 0);
                    }
                }
            }

            world.setBlockState(new BlockPos(x, y, z), LOG2.getDefaultState());
        } else {
            int i;
            int j;
            for (i = -1; i <= 1; i++) {
                for (j = -1; j <= 1; j++) {
                    if (world.isAirBlock(new BlockPos(x + i, y + 1, z + j))) {
                        world.setBlockState(new BlockPos(x + i, y + 1, z + j), LEAVES2.getDefaultState(), 0);
                    }
                }
            }

            for (i = -2; i <= 2; i++) {
                for (j = -2; j <= 2; j++) {
                    if (world.isAirBlock(new BlockPos(x + i, y, z + j)) && abs(i) + abs(j) < 4) {
                        world.setBlockState(new BlockPos(x + i, y, z + j), LEAVES2.getDefaultState(), 0);
                    }
                }
            }

            world.setBlockState(new BlockPos(x, y, z), LOG2.getDefaultState());
        }
    }
}
