package rtg.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import appalachia.api.block.IAppalachiaBlockLeavesFallen;

public class AppalachiaUtil {

    public static boolean canSnowAt(World world, BlockPos pos) {

        BlockPos groundPos = pos.down();
        if (world.getBlockState(groundPos).getBlock() instanceof IAppalachiaBlockLeavesFallen) {
            return false;
        }

        return true;
    }
}