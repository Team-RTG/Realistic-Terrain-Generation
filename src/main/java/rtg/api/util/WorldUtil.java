package rtg.api.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class WorldUtil {

    private World world;
    private boolean appalachia;

    public WorldUtil(World world) {

        this.world = world;
        ModPresenceTester apl = new ModPresenceTester("appalachia");
        this.appalachia = apl.present();
    }

    /**
     * Checks a given coordinate to see if it is surrounded by a given block, usually air.
     */
    public boolean isSurroundedByBlock(IBlockState checkBlock, int checkDistance, SurroundCheckType checkType, Random rand, int x, int y, int z) {

        switch (checkType) {
            case FULL: // Checks the entire radius around the coord.

                for (int ix = -checkDistance; ix <= checkDistance; ix++) {
                    for (int iz = -checkDistance; iz <= checkDistance; iz++) {

                        if (x == ix && z == iz) {
                            continue;
                        }

                        if (this.world.getBlockState(new BlockPos(x + ix, y, z + iz)) != checkBlock) {
                            return false;
                        }
                    }
                }

                break;

            case CARDINAL: // Checks the N/E/S/W directions around the coord.

                for (int i = checkDistance; i > 0; i--) {

                    if (this.world.getBlockState(new BlockPos(x, y, z + i)) != checkBlock) {
                        return false;
                    }
                    if (this.world.getBlockState(new BlockPos(x, y, z - i)) != checkBlock) {
                        return false;
                    }
                    if (this.world.getBlockState(new BlockPos(x + i, y, z)) != checkBlock) {
                        return false;
                    }
                    if (this.world.getBlockState(new BlockPos(x - i, y, z)) != checkBlock) {
                        return false;
                    }
                }

                break;

            case ORDINAL: // Checks the NE/SE/SW/NW directions around the coord.

                for (int i = checkDistance; i > 0; i--) {

                    if (this.world.getBlockState(new BlockPos(x + i, y, z + i)) != checkBlock) {
                        return false;
                    }
                    if (this.world.getBlockState(new BlockPos(x + i, y, z - i)) != checkBlock) {
                        return false;
                    }
                    if (this.world.getBlockState(new BlockPos(x - i, y, z + i)) != checkBlock) {
                        return false;
                    }
                    if (this.world.getBlockState(new BlockPos(x - i, y, z - i)) != checkBlock) {
                        return false;
                    }
                }

                break;

            case UP: // Checks above coord.

                IBlockState b;
                for (int i = checkDistance; i > 0; i--) {

                    b = this.world.getBlockState(new BlockPos(x, y + i, z));

                    if (b != checkBlock) {
                        return false;
                    }
                }

                break;

            default:
                break;
        }

        return true;
    }

    /**
     * Checks to see if a given block is above a given coordinate.
     * Use isSurroundedByBlock() with SurroundCheckType.UP if you want to check all blocks above.
     */
    public boolean isBlockAbove(IBlockState checkBlock, int checkDistance, World world, int x, int y, int z, boolean materialCheck) {

        Material checkBlockMaterial = checkBlock.getMaterial();
        IBlockState blockAbove;
        Material m;

        for (int i = 1; i <= checkDistance; i++) {

            blockAbove = world.getBlockState(new BlockPos(x, y + checkDistance, z));

            if (materialCheck) {
                m = blockAbove.getMaterial();
                if (m != checkBlockMaterial) {
                    return false;
                }
            }
            else if (blockAbove != checkBlock) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks to see if a given block is below a given coordinate.
     * Use isSurroundedByBlock() with SurroundCheckType.DOWN if you want to check all blocks below.
     */
    public boolean isBlockBelow(IBlockState checkBlock, int checkDistance, World world, int x, int y, int z, boolean materialCheck) {

        Material checkBlockMaterial = checkBlock.getMaterial();
        IBlockState blockBelow;
        Material m;

        for (int i = 1; i <= checkDistance; i++) {

            blockBelow = world.getBlockState(new BlockPos(x, y + checkDistance, z));

            if (materialCheck) {
                m = blockBelow.getMaterial();
                if (m != checkBlockMaterial) {
                    return false;
                }
            }
            else if (blockBelow != checkBlock) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the Block has a bounding box.
     * @param world The world to check in
     * @param pos The position to check
     * @return True if the block has a bounding box, false otherwise.
     */
    public static boolean isNotSolid(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        AxisAlignedBB boundingBox = state.getCollisionBoundingBox(world, pos);

        return boundingBox == null || boundingBox.equals(Block.NULL_AABB);
    }

    /**
     * Gets the next Block which has a bounding box.
     * @param world The world to check in
     * @param start The Block to start looking from
     * @param direction The direction to go to
     * @return The position of the next solid block
     */
    public static BlockPos nextSolidBlock(World world, BlockPos start, EnumFacing direction) {
        while (isNotSolid(world, start)) {
            start = start.offset(direction);
        }

        return start;
    }

    public void setDoublePlant(BlockPos lowerPos, IBlockState doublePlant, int flag) {
        this.world.setBlockState(lowerPos, doublePlant.withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER), flag);
        this.world.setBlockState(lowerPos.up(), doublePlant.withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER), flag);
    }

    public void setDoublePlant(BlockPos lowerPos, IBlockState doublePlant) {
        this.setDoublePlant(lowerPos, doublePlant, 2);
    }

    public boolean canSnowAt(BlockPos pos, boolean checkLight) {

        if (!this.world.canSnowAt(pos, true)) {
            return false;
        }

        if (this.appalachia) {
            BlockPos groundPos = pos.down();
            String groundBlockName = this.world.getBlockState(groundPos).getBlock().getUnlocalizedName().toLowerCase();
            if (groundBlockName.contains("fallen") && groundBlockName.contains("leaves")) {
                return false;
            }
        }

        return true;
    }

    public enum SurroundCheckType {
        FULL,
        CARDINAL,
        ORDINAL,
        UP
    }
}