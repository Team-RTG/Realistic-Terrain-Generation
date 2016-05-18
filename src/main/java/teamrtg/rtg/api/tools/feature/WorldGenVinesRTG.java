package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenVinesRTG extends WorldGenerator {
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (; position.getY() < 128; position = position.up()) {
            if (worldIn.isAirBlock(position)) {
                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL.facings()) {
                    if (Blocks.VINE.canPlaceBlockOnSide(worldIn, position, enumfacing)) {
                        IBlockState iblockstate = Blocks.VINE.getDefaultState()
                                .withProperty(BlockVine.NORTH, enumfacing == EnumFacing.SOUTH)
                                .withProperty(BlockVine.EAST, enumfacing == EnumFacing.WEST)
                                .withProperty(BlockVine.SOUTH, enumfacing == EnumFacing.NORTH)
                                .withProperty(BlockVine.WEST, enumfacing == EnumFacing.EAST);
                        worldIn.setBlockState(position, iblockstate, 2);
                        break;
                    }
                }
            } else {
                position = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
            }
        }

        return true;
    }
}