package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.block.material.Material.GRASS;
import static net.minecraft.block.material.Material.GROUND;
import static net.minecraft.init.Blocks.REEDS;
import static net.minecraft.init.Blocks.WATER;

class WorldGenJungleCane extends WorldGenerator {
    private int height;


    public WorldGenJungleCane(int h) {
        height = h;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block b;
        while (y > 0) {
            b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            if (!world.isAirBlock(new BlockPos(x, y, z)) || b.isLeaves(world.getBlockState(new BlockPos(x, y, z)), world, new BlockPos(x, y, z))) {
                break;
            }
            y--;
        }

        b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (b != Blocks.GRASS && b != Blocks.DIRT) {
            return false;
        }

        int j, sx, sz, ra;
        for (j = 0; j < 4; j++) {
            b = world.getBlockState(new BlockPos(j == 0 ? x - 1 : j == 1 ? x + 1 : x, y, j == 2 ? z - 1 : j == 3 ? z + 1 : z)).getBlock();
            if (b.getMaterial(b.getDefaultState()) != GROUND && b.getMaterial(b.getDefaultState()) != GRASS) {
                return false;
            }
        }

        for (j = 0; j < 4; j++) {
            sx = j == 0 ? x - 1 : j == 1 ? x + 1 : x;
            sz = j == 2 ? z - 1 : j == 3 ? z + 1 : z;
            ra = rand.nextInt(height * 2 + 1) + height;

            b = world.getBlockState(new BlockPos(sx, y + 1, sz)).getBlock();
            if (b.getMaterial(b.getDefaultState()) == Material.AIR || b.getMaterial(b.getDefaultState()) == Material.VINE) {
                for (int k = 0; k < ra; k++) {
                    b = world.getBlockState(new BlockPos(sx, y + 1 + k, sz)).getBlock();
                    if (b.getMaterial(b.getDefaultState()) == Material.AIR || b.getMaterial(b.getDefaultState()) == Material.VINE) {
                        world.setBlockState(new BlockPos(sx, y + 1 + k, sz), REEDS.getDefaultState(), 2);
                    } else {
                        break;
                    }
                }
            }
        }

        world.setBlockState(new BlockPos(x, y, z), WATER.getDefaultState());

        return true;
    }
}
