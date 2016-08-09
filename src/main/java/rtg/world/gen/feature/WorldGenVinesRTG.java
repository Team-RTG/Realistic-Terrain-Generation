package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVinesRTG extends WorldGenerator {

    public boolean generate(World worldIn, Random rand, BlockPos position) {

        for (; position.getY() < 128; position = position.up()) {
            if (worldIn.isAirBlock(position)) {
                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL.facings()) {
                    if (Blocks.vine.canPlaceBlockOnSide(worldIn, position, enumfacing)) {
                        IBlockState iblockstate = Blocks.vine.getDefaultState()
                            .withProperty(BlockVine.NORTH, enumfacing == EnumFacing.SOUTH)
                            .withProperty(BlockVine.EAST, enumfacing == EnumFacing.WEST)
                            .withProperty(BlockVine.SOUTH, enumfacing == EnumFacing.NORTH)
                            .withProperty(BlockVine.WEST, enumfacing == EnumFacing.EAST);
                        worldIn.setBlockState(position, iblockstate, 2);
                        break;
                    }
                }
            }
            else {
                position = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
            }
        }

        return true;
    }
}