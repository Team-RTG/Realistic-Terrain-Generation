package rtg.api.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import rtg.api.event.CustomizeBlockEvent;
import rtg.api.util.BlockUtil;
import rtg.api.util.BlockUtil.MatchType;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;


// TODO: [1.12] This class is required because net.minecraft.world.gen.feature.WorldGenBlockBlob has hardcoded checks for placement,
//              but this class should extend that class and override its #generate
public class WorldGenBlob extends WorldGenerator {

    private final IBlockState blobBlock;
    private final int blobSize;
    private boolean allowInWater;
    private Collection<Block> validGroundBlocks;
    private boolean enabled = true;

    public WorldGenBlob(IBlockState block, int size, Collection<Block> validGroundBlocks, boolean allowInWater) {

        super(false);
        this.blobBlock = block;
        this.blobSize = size;
        this.validGroundBlocks = Collections.unmodifiableCollection(validGroundBlocks);
        this.allowInWater = allowInWater;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (this.enabled) {

            CustomizeBlockEvent event = new CustomizeBlockEvent(world, pos, this.blobBlock);
            MinecraftForge.TERRAIN_GEN_BUS.post(event);
            IBlockState boulderBlock = event.getBlockState();

            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            MutableBlockPos mpos = new MutableBlockPos(pos);
            IBlockState blockstate;
            while (true) {

// TODO: [1.12] Rewrite this to be less convoluted
                mpos.move(EnumFacing.DOWN);
                if (mpos.getY() > 3) {
                    label63:
                    {
                        if (!world.isAirBlock(mpos)) {
                            blockstate = world.getBlockState(mpos);

                            // Water check.
                            if (!this.allowInWater) {
                                if (blockstate.getMaterial() == Material.WATER || BlockUtil.checkAreaMaterials(MatchType.ANY, world, mpos, 1, Material.WATER)) {
                                    return false;
                                }
                            }
                            if (validGroundBlocks.contains(blockstate.getBlock())) {
                                break label63;
                            }
                        }

                        --y;
                        continue;
                    }
                }

                if (mpos.getY() <= 3) {
                    return false;
                }

                int k2 = this.blobSize;

                for (int l = 0; this.blobSize >= 0 && l < 3; ++l) {
                    int sizeX = this.blobSize + rand.nextInt(2);
                    int sizeY = this.blobSize + rand.nextInt(2);
                    int sizeZ = this.blobSize + rand.nextInt(2);
                    float f = (sizeX + sizeY + sizeZ) * 0.333F + 0.5F;

                    for (int bx = x - sizeX; bx <= x + sizeX; ++bx) {
                        for (int bz = z - sizeZ; bz <= z + sizeZ; ++bz) {
                            for (int by = y - sizeY; by <= y + sizeY; ++by) {
                                float f1 = (bx - x);
                                float f2 = (bz - z);
                                float f3 = (by - y);

                                if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f) {
                                    this.placeBoulderBlock(world, new BlockPos(bx, by, bz), boulderBlock);
                                }
                            }
                        }
                    }

                    x += -(this.blobSize + 1) + rand.nextInt(2 + k2 * 2);
                    z += -(this.blobSize + 1) + rand.nextInt(2 + k2 * 2);
                    y += 0 - rand.nextInt(2);
                }

                return true;
            }
        }
        return false;
    }

    private void placeBoulderBlock(World world, BlockPos targetPos, IBlockState boulderBlock) {

        MutableBlockPos mpos = new MutableBlockPos(targetPos);
        Block targetBlock = world.getBlockState(targetPos).getBlock();

        if (targetBlock.isReplaceable(world, targetPos)) {

            // TODO: [1.12] This should probably match vanilla which uses flag = 4, not 2.
            world.setBlockState(targetPos, boulderBlock, 2);

            //Logger.info("Boulder block ({}) placed at {} {} {}", boulderBlock.getBlock().getLocalizedName(), targetPos.getX(), targetPos.getY(), targetPos.getZ());

            // Double-plant check.
            if (world.getBlockState(mpos.move(EnumFacing.UP)).getBlock() == Blocks.DOUBLE_PLANT) {
                world.setBlockState(mpos, Blocks.AIR.getDefaultState(), 2);
            }
        }
    }
}
