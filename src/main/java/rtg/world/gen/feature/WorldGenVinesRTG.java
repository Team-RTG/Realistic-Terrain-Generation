package rtg.world.gen.feature;

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

public class WorldGenVinesRTG extends WorldGenerator {

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

    protected void addVine(World worldIn, Random rand, BlockPos pos, EnumFacing enumfacing)
    {
        IBlockState iblockstate = this.vineBlock.getDefaultState()
            .withProperty(this.propNorth, enumfacing == EnumFacing.SOUTH)
            .withProperty(this.propEast, enumfacing == EnumFacing.WEST)
            .withProperty(this.propSouth, enumfacing == EnumFacing.NORTH)
            .withProperty(this.propWest, enumfacing == EnumFacing.EAST);

        this.setBlockAndNotifyAdequately(worldIn, pos, iblockstate);

        int i = rand.nextInt(4) + 1;

        for (pos = pos.down(); worldIn.isAirBlock(pos) && i > 0; --i)
        {
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