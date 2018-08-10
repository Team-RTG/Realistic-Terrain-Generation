package rtg.api.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.RTGAPI;


public class WorldGenVinesRTG extends WorldGenerator {

    private static final Block volcanoBlock = Block.getBlockFromName(RTGAPI.config().VOLCANO_MAIN_BLOCK.get());
    private static final Block volcanoMix1Block = Block.getBlockFromName(RTGAPI.config().VOLCANO_MIX1_BLOCK.get());
    private static final Block volcanoMix2Block = Block.getBlockFromName(RTGAPI.config().VOLCANO_MIX2_BLOCK.get());
    private static final Block volcanoMix3Block = Block.getBlockFromName(RTGAPI.config().VOLCANO_MIX3_BLOCK.get());
    protected Block vineBlock;
    protected int maxY;
    protected PropertyBool propNorth;
    protected PropertyBool propEast;
    protected PropertyBool propSouth;
    protected PropertyBool propWest;

    public WorldGenVinesRTG() {

        this.vineBlock = Blocks.VINE;
        this.setMaxY(254);
        this.propNorth = BlockVine.NORTH;
        this.propEast = BlockVine.EAST;
        this.propSouth = BlockVine.SOUTH;
        this.propWest = BlockVine.WEST;
    }

    public WorldGenVinesRTG(Block vineBlock, int maxY, PropertyBool propNorth, PropertyBool propEast, PropertyBool propSouth, PropertyBool propWest) {

        this();

        this.vineBlock = vineBlock;
        this.maxY = maxY;
        this.propNorth = propNorth;
        this.propEast = propEast;
        this.propSouth = propSouth;
        this.propWest = propWest;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        for (; position.getY() < this.maxY; position = position.up()) {

            if (worldIn.isAirBlock(position)) {

                Block north = worldIn.getBlockState(position.north()).getBlock();
                Block south = worldIn.getBlockState(position.south()).getBlock();
                Block east = worldIn.getBlockState(position.east()).getBlock();
                Block west = worldIn.getBlockState(position.west()).getBlock();

                // No vines on volcanoes.
                if (north == volcanoBlock || north == volcanoMix1Block || north == volcanoMix2Block || north == volcanoMix3Block
                    || south == volcanoBlock || south == volcanoMix1Block || south == volcanoMix2Block || south == volcanoMix3Block
                    || east == volcanoBlock || east == volcanoMix1Block || east == volcanoMix2Block || east == volcanoMix3Block
                    || west == volcanoBlock || west == volcanoMix1Block || west == volcanoMix2Block || west == volcanoMix3Block) {
                    return false;
                }

                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL.facings()) {

                    if (this.vineBlock.canPlaceBlockOnSide(worldIn, position, enumfacing)) {

                        this.addVine(worldIn, rand, position, enumfacing);
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

    protected void addVine(World worldIn, Random rand, BlockPos pos, EnumFacing enumfacing) {
        IBlockState iblockstate = this.vineBlock.getDefaultState()
            .withProperty(this.propNorth, enumfacing == EnumFacing.SOUTH)
            .withProperty(this.propEast, enumfacing == EnumFacing.WEST)
            .withProperty(this.propSouth, enumfacing == EnumFacing.NORTH)
            .withProperty(this.propWest, enumfacing == EnumFacing.EAST);

        this.setBlockAndNotifyAdequately(worldIn, pos, iblockstate);

        int i = rand.nextInt(4) + 1;

        for (pos = pos.down(); worldIn.isAirBlock(pos) && i > 0; --i) {
            this.setBlockAndNotifyAdequately(worldIn, pos, iblockstate);
            pos = pos.down();
        }
    }

    public Block getVineBlock() {

        return vineBlock;
    }

    public WorldGenVinesRTG setVineBlock(Block vineBlock) {

        this.vineBlock = vineBlock;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public WorldGenVinesRTG setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public PropertyBool getPropNorth() {

        return propNorth;
    }

    public WorldGenVinesRTG setPropNorth(PropertyBool propNorth) {

        this.propNorth = propNorth;
        return this;
    }

    public PropertyBool getPropEast() {

        return propEast;
    }

    public WorldGenVinesRTG setPropEast(PropertyBool propEast) {

        this.propEast = propEast;
        return this;
    }

    public PropertyBool getPropSouth() {

        return propSouth;
    }

    public WorldGenVinesRTG setPropSouth(PropertyBool propSouth) {

        this.propSouth = propSouth;
        return this;
    }

    public PropertyBool getPropWest() {

        return propWest;
    }

    public WorldGenVinesRTG setPropWest(PropertyBool propWest) {

        this.propWest = propWest;
        return this;
    }
}