package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenWildWheat extends WorldGenerator {

    private Block farmtype;


    /**
     * 0 = potatoes, 1 = carrots, 2 = wheat
     */
    public WorldGenWildWheat(int type) {

        farmtype = type == 0 ? Blocks.POTATOES : type == 1 ? Blocks.CARROTS : Blocks.WHEAT;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {

        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {

        Block b;
        while (y > 0) {
            b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            if (!world.isAirBlock(new BlockPos(x, y, z)) || b.isLeaves(world, new BlockPos(x, y, z))) {
                break;
            }
            y--;
        }

        b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (b != Blocks.GRASS && b != Blocks.DIRT) {
            return false;
        }

        for (int j = 0; j < 4; j++) {
            b = world.getBlockState(new BlockPos(j == 0 ? x - 1 : j == 1 ? x + 1 : x, y, j == 2 ? z - 1 : j == 3 ? z + 1 : z)).getBlock();
            if (b.getMaterial() != Material.ground && b.getMaterial() != Material.grass) {
                return false;
            }
        }

        int rx, ry, rz;
        for (int i = 0; i < 30; i++) {
            rx = rand.nextInt(5) - 2;
            ry = rand.nextInt(2) - 1;
            rz = rand.nextInt(5) - 2;
            b = world.getBlockState(new BlockPos(x + rx, y + ry, z + rz)).getBlock();

            if ((b == Blocks.GRASS || b == Blocks.DIRT) && world.isAirBlock(new BlockPos(x + rx, y + ry + 1, z + rz))) {
                world.setBlockState(new BlockPos(x + rx, y + ry, z + rz), Blocks.FARMLAND.getStateFromMeta(rand.nextInt(4) + 4), 0);
                world.setBlockState(new BlockPos(x + rx, y + ry + 1, z + rz), farmtype.getStateFromMeta(rand.nextInt(4) + 4), 0);
            }
        }

        world.setBlockState(new BlockPos(x, y, z), Blocks.WATER.getDefaultState());
        return true;
    }
}
