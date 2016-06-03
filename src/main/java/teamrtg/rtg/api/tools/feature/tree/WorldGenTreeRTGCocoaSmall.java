package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.module.Mods;

import java.util.Random;

import static java.lang.Math.abs;
import static net.minecraft.block.material.Material.AIR;
import static net.minecraft.block.material.Material.VINE;
import static net.minecraft.init.Blocks.*;


class WorldGenTreeRTGCocoaSmall extends WorldGenerator {
    private static int[] cocoas = new int[] {
            2, 0, -2, 1,
            1, 1, -2, 0,
            0, 0, -2, -1,
            3, -1, -2, 0
    };


    public WorldGenTreeRTGCocoaSmall() {

    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block b = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

        if (b == SAND && !Mods.RTG.config.ALLOW_TREES_ON_SAND.get()) {
            return false;
        }

        if (b != GRASS && b != DIRT && b != SAND) {
            return false;
        }

        Material m = world.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial(world.getBlockState(new BlockPos(x, y, z)));
        if (m != AIR && m != VINE) {
            return false;
        }

        int h = y + 2 + rand.nextInt(3);
        for (; y < h; y++) {
            world.setBlockState(new BlockPos(x, y, z), LOG.getStateFromMeta(3), 0);
        }

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (abs(i) + abs(j) < 3) {
                    buildBlock(world, x + i, y - 1, z + j, LEAVES, 3, 0);
                }
            }
        }

        world.setBlockState(new BlockPos(x, y - 1, z), LOG.getStateFromMeta(3), 0);
        buildBlock(world, x + 1, y, z, LEAVES, 3, 0);
        buildBlock(world, x - 1, y, z, LEAVES, 3, 0);
        buildBlock(world, x, y, z, LEAVES, 3, 0);
        buildBlock(world, x, y, z + 1, LEAVES, 3, 0);
        buildBlock(world, x, y, z - 1, LEAVES, 3, 0);

        for (int k = 0; k < 16; k += 4) {
            if (rand.nextInt(20) == 0) {
                buildBlock(world, x + cocoas[k + 1], y + cocoas[k + 2], z + cocoas[k + 3], COCOA, cocoas[k + 0] + 8, 0);
            }
        }

        return true;
    }

    private void buildBlock(World w, int x, int y, int z, Block b, int m, int u) {
        Material ma = w.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial(w.getBlockState(new BlockPos(x, y, z)));

        if (ma == AIR || ma == VINE) {
            w.setBlockState(new BlockPos(x, y, z), b.getStateFromMeta(m), 0);
        }
    }
}
