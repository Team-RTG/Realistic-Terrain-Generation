package rtg.world.gen.feature;

/**
 * @author Zeno410
 */

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPlantBlock extends WorldGenerator {

    private IBlockState soilBlock;
    private IBlockState plantBlock;

    public WorldGenPlantBlock(IBlockState plantBlock) {

        this.plantBlock = plantBlock;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        IBlockState b;
        //for (int l = 0; l < 10; ++l)
        {
            int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(new BlockPos(i1, j1, k1)) || world.getBlockState(new BlockPos(x, y, z)).getBlock().isLeaves(world, new BlockPos(x, y, z))) {
                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1));
                if (b == Blocks.grass.getDefaultState() || b == Blocks.dirt.getDefaultState()) {
                    if (plantBlock.getBlock().canPlaceBlockAt(world, new BlockPos(i1, j1, k1))) {
                        world.setBlockState(new BlockPos(i1, j1, k1), plantBlock, 2);
                    }
                }
            }
        }

        return true;
    }
}