package rtg.world.gen.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenLayers extends WorldGenerator {

    private IBlockState layerBlock;
    private PropertyInteger layerProperty;
    private int dropHeight; // The height (in blocks from y) from which we'll start trying to 'drop' layers on to the ground.
    private int layerRange;
    private int scatter;
    private ArrayList<IBlockState> validGroundBlocks;

    public WorldGenLayers(IBlockState block, PropertyInteger prop, int dropHeight, int range, int scatter) {

        super(false);

        this.layerBlock = block;
        this.layerProperty = prop;
        this.dropHeight = dropHeight;
        this.layerRange = range;
        this.scatter = scatter < 1 ? 1 : scatter;

        this.validGroundBlocks = new ArrayList<IBlockState>(Arrays.asList(
            Blocks.GRASS.getDefaultState(),
            Blocks.DIRT.getDefaultState(),
            Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL),
            Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT)
        ));
    }

    public WorldGenLayers(IBlockState block, PropertyInteger prop, int dropHeight, int range, int scatter, ArrayList<IBlockState> validGroundBlocks) {

        this(block, prop, dropHeight, range, scatter);

        this.validGroundBlocks = validGroundBlocks;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos();

        for (int x = pos.getX() - this.layerRange; x <= pos.getX() + this.layerRange; ++x) {
            for (int z = pos.getZ() - this.layerRange; z <= pos.getZ() + this.layerRange; ++z) {
                blockpos.setPos(x, pos.getY() + dropHeight, z);

                if (!world.isAirBlock(blockpos)) {
                    continue;
                }

                blockpos.move(EnumFacing.DOWN);

                while (blockpos.getY() > 0 && world.isAirBlock(blockpos)) {
                    blockpos.move(EnumFacing.DOWN);
                }

                blockpos.move(EnumFacing.UP);

                IBlockState state;
                if (this.layerBlock.getBlock().canPlaceBlockAt(world, blockpos) && rand.nextInt(this.scatter) == 0) {

                    state = world.getBlockState(new BlockPos(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ()));

                    if (this.validGroundBlocks.contains(state)) {
                        setBlockAndNotifyAdequately(world, blockpos, this.layerBlock);
                    }
                }
                else {

                    blockpos.move(EnumFacing.DOWN);
                    state = world.getBlockState(new BlockPos(blockpos));

                    if (state.getBlock() == this.layerBlock.getBlock()) {
                        int layers = state.getValue(this.layerProperty).intValue();

                        setBlockAndNotifyAdequately(world, blockpos, layerBlock.withProperty(this.layerProperty, (layers & 7) + 1));
                    }
                }
            }
        }

        return true;
    }
}