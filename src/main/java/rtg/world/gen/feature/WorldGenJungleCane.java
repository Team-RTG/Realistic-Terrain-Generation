package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenJungleCane extends WorldGenerator {

    private int height;

    public WorldGenJungleCane(int h) {

        height = h;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        IBlockState b;
        while (y > 0) {
            b = world.getBlockState(new BlockPos(x, y, z));
            if (!world.isAirBlock(new BlockPos(x, y, z)) || b.getBlock().isLeaves(world, new BlockPos(x, y, z))) {
                break;
            }
            y--;
        }

        b = world.getBlockState(new BlockPos(x, y, z));
        if (b != Blocks.GRASS.getDefaultState() && b != Blocks.DIRT.getDefaultState()) {
            return false;
        }

        int j, sx, sz, ra;
        for (j = 0; j < 4; j++) {
            b = world.getBlockState(new BlockPos(j == 0 ? x - 1 : j == 1 ? x + 1 : x, y, j == 2 ? z - 1 : j == 3 ? z + 1 : z));
            if (b.getBlock().getMaterial() != Material.ground && b.getBlock().getMaterial() != Material.grass) {
                return false;
            }
        }

        for (j = 0; j < 4; j++) {
            sx = j == 0 ? x - 1 : j == 1 ? x + 1 : x;
            sz = j == 2 ? z - 1 : j == 3 ? z + 1 : z;
            ra = rand.nextInt(height * 2 + 1) + height;

            b = world.getBlockState(new BlockPos(sx, y + 1, sz));
            if (b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine) {
                for (int k = 0; k < ra; k++) {
                    b = world.getBlockState(new BlockPos(sx, y + 1 + k, sz));
                    if (b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine) {
                        world.setBlockState(new BlockPos(sx, y + 1 + k, sz), Blocks.REEDS.getDefaultState(), 2);
                    }
                    else {
                        break;
                    }
                }
            }
        }

        world.setBlockState(new BlockPos(x, y, z), Blocks.WATER.getDefaultState());

        return true;
    }
}
