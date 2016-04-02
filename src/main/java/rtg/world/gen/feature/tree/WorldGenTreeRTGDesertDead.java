package rtg.world.gen.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.config.ConfigRTG;

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

        if (g == sand && !ConfigRTG.ALLOW_TREES_ON_SAND.get()) {
            return false;
        }

        if (g != grass && g != dirt && g != sand) {
            return false;
        }

        if (type == 0) {
            int i, h = 8;

            for (i = 0; i < h; i++) {
                world.setBlockState(new BlockPos(x, y + i, z), log2.getDefaultState(), 0);
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
                    world.setBlockState(new BlockPos(x + (int) (xd * c), y + h + (int) c, z + (int) (zd * c)), log2.getStateFromMeta(12), 0);
                    c += 1f;
                }
            }
        } else {
            int h = rand.nextInt(3) + 2;
            for (int i = 0; i < h; i++) {
                world.setBlockState(new BlockPos(x, y + i, z), log2.getDefaultState(), 0);
            }

            h--;
            world.setBlockState(new BlockPos(x + 1, y + h, z), leaves2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x - 1, y + h, z), leaves2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x, y + h, z + 1), leaves2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x, y + h, z - 1), leaves2.getDefaultState(), 0);
            world.setBlockState(new BlockPos(x, y + h + 1, z), leaves2.getDefaultState(), 0);
        }

        return true;
    }
}
