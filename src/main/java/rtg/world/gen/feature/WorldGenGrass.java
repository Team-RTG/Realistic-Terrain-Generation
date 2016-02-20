package rtg.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.init.Blocks.*;

public class WorldGenGrass extends WorldGenerator {
    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
    private Block block;
    private int metadata;

    public WorldGenGrass(Block b, int m) {
        block = b;
        metadata = m;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        while (y > 0) {
            if (!world.isAirBlock(new BlockPos(x, y, z)) || world.getBlockState(new BlockPos(x, y, z)).getBlock().isLeaves(world, new BlockPos(x, y, z))) {
                break;
            }
            y--;
        }

        if (block == double_plant) {
            for (int l = 0; l < 64; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && j1 < 254 && double_plant.canBlockStay(world, new BlockPos(i1, j1, k1), double_plant.getDefaultState())) {
                    world.setBlockState(new BlockPos(i1, j1, k1), double_plant.getStateFromMeta(metadata));
                }
            }
        } else if (block == leaves) {
            for (int l = 0; l < 64; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock() == grass) {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(metadata), 0);
                }
            }
        } else {
            for (int l = 0; l < 128; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && block.canPlaceBlockAt(world, new BlockPos(i1, j1, k1))) {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(metadata), 0);
                }
            }
        }
        return true;
    }
}
