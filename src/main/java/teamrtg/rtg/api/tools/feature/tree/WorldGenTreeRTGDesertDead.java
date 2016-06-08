package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.module.Mods;

import java.util.Random;

import static java.lang.Math.*;
import static net.minecraft.init.Blocks.*;

class WorldGenTreeRTGDesertDead extends WorldGenerator {
    private int type;


    public WorldGenTreeRTGDesertDead(int t) {
        type = t;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block g = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

        if (g == SAND && !Mods.RTG.config.ALLOW_TREES_ON_SAND.get()) {
            return false;
        }

        if (g != GRASS && g != DIRT && g != SAND) {
            return false;
        }

        if (type == 0) {
            int i, h = 8;

            for (i = 0; i < h; i++) {
                world.setBlockState(new BlockPos(x, y + i, z), LOG2.getDefaultState(), 0);
            }

            int branches = 2 + rand.nextInt(3);
            float dir, xd, yd, zd, l, c, sk = (360f / branches);

            for (i = 0; i < branches; i++) {
                dir = i * sk + rand.nextFloat() * sk;
                xd = (float) cos(dir * PI / 180f);
                zd = (float) sin(dir * PI / 180f);
                l = 1f + rand.nextFloat() * 3f;
                c = 1f;

                while (c < l) {
                    world.setBlockState(new BlockPos(x + (int) (xd * c), y + h + (int) c, z + (int) (zd * c)), LOG2.getStateFromMeta(12), 0);
                    c += 1f;
                }
            }
        } else {
            int h = rand.nextInt(3) + 2;
            for (int i = 0; i < h; i++) {
                world.setBlockState(new BlockPos(x, y + i, z), LOG2.getDefaultState(), 0);
            }

            h--;
            world.setBlockState(new BlockPos(x + 1, y + h, z), LEAVES2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x - 1, y + h, z), LEAVES2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x, y + h, z + 1), LEAVES2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x, y + h, z - 1), LEAVES2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x, y + h + 1, z), LEAVES2.getDefaultState(), 0);
        }

        return true;
    }
}
