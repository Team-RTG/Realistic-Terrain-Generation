package rtg.api.world.gen.feature;

/**
 * @author WhichOnesPink
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenSeaweed extends WorldGenerator {

    protected IBlockState seaweedBlock;
    protected int height;
    protected ArrayList<Block> validGroundBlocks;

    public WorldGenSeaweed(IBlockState seaweedBlock, int height) {

        this.setSeaweedBlock(seaweedBlock);
        this.setHeight(height);

        this.setValidGroundBlocks(new ArrayList<Block>(Arrays.asList(
            Blocks.DIRT,
            Blocks.GRAVEL,
            Blocks.CLAY,
            Blocks.SAND
        )));
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        IBlockState b;

        if (canGenerateAt(world, pos)) {

            BlockPos p;
            int direction;

            if (seaweedBlock.getBlock().canPlaceBlockAt(world, pos)) {
                world.setBlockState(pos, seaweedBlock, 2);
            }

            for (int i = 0; i < height; i++) {

                direction = rand.nextInt(4);

                switch (direction) {
                    case 0:
                        x -= 1;
                        break;
                    case 1:
                        x += 1;
                        break;
                    case 2:
                        z -= 1;
                        break;
                    case 3:
                        z += 1;
                        break;
                    default:
                        ; // Straight up.
                        break;
                }

                p = new BlockPos(x, y + i, z);
                b = world.getBlockState(p);

                if (b == Blocks.WATER.getDefaultState()) {
                    if (seaweedBlock.getBlock().canPlaceBlockAt(world, p)) {
                        world.setBlockState(p, seaweedBlock, 2);
                    }
                }
            }
        }

        return true;
    }

    protected boolean canGenerateAt(World world, BlockPos pos) {

        // Check for valid ground block.
        Block groundBlock = world.getBlockState(pos.down()).getBlock();

        // Check the block below the surface to help reduce floaters caused by falling blocks.
        Block belowBlock = world.getBlockState(pos.down(2)).getBlock();

        return getValidGroundBlocks().contains(groundBlock) && getValidGroundBlocks().contains(belowBlock);
    }

    public IBlockState getSeaweedBlock() {

        return seaweedBlock;
    }

    public WorldGenSeaweed setSeaweedBlock(IBlockState seaweedBlock) {

        this.seaweedBlock = seaweedBlock;
        return this;
    }

    public int getHeight() {

        return height;
    }

    public WorldGenSeaweed setHeight(int height) {

        this.height = height;
        return this;
    }

    public ArrayList<Block> getValidGroundBlocks() {

        return validGroundBlocks;
    }

    public WorldGenSeaweed setValidGroundBlocks(ArrayList<Block> validGroundBlocks) {

        this.validGroundBlocks = validGroundBlocks;
        return this;
    }
}