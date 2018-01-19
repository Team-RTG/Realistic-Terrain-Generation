package rtg.api.world.gen.feature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.BoulderUtil;
import rtg.api.util.RandomUtil;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WorldGenBlob extends WorldGenerator {

    protected boolean water = true;
// TODO: [Clean-up] Change to List
    private ArrayList<Block> validGroundBlocks;
    private IBlockState blobBlock;
    private int blobSize;
    private boolean booShouldGenerate = true;

    private WorldGenBlob(IBlockState block, int size, Random rand) {

        super(false);
        this.blobBlock = block;
        this.blobSize = size;

        this.validGroundBlocks = new ArrayList<>(Arrays.asList(
            Blocks.GRASS,
            Blocks.DIRT,
            Blocks.STONE,
            Blocks.GRAVEL,
            Blocks.CLAY,
            Blocks.SAND
        ));

// TODO: [Generator settings] The boulder config check should be removed and all functionality changed to use the generator settings in DecoBoulder#generate
        if (blobBlock == Blocks.MOSSY_COBBLESTONE.getDefaultState() || blobBlock == Blocks.COBBLESTONE.getDefaultState()) {

            RTGConfig rtgConfig = RTGAPI.config();
            int chance = rtgConfig.COBBLESTONE_BOULDER_CHANCE.get();
            chance = (chance < 1) ? 1 : ((chance > 100) ? 100 : chance);

            int random = RandomUtil.getRandomInt(rand, 1, chance);

            if (random == 1) {
                booShouldGenerate = false;
            }

//            }
        }
    }

    private WorldGenBlob(IBlockState block, int size, Random rand, boolean water) {

        this(block, size, rand);
        this.water = water;
    }

    public WorldGenBlob(IBlockState block, int size, Random rand, boolean water, ArrayList<Block> validGroundBlocks) {

        this(block, size, rand, water);
        this.validGroundBlocks = validGroundBlocks;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (!booShouldGenerate) {
            return false;
        }

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        IBlockState boulderBlock = BoulderUtil.getBoulderBlock(this.blobBlock, x, y, z);

        while (true) {
            if (y > 3) {
                label63:
                {
                    if (!world.isAirBlock(new BlockPos(x, y - 1, z))) {
                        IBlockState block = world.getBlockState(new BlockPos(x, y - 1, z));

                        // Water check.
                        if (!this.water) {

                            if (block.getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x, y - 1, z - 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x, y - 1, z + 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x - 1, y - 1, z)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x - 1, y - 1, z - 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x - 1, y - 1, z + 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x + 1, y - 1, z)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x + 1, y - 1, z - 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x + 1, y - 1, z + 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                        }

                        if (validGroundBlocks.contains(block.getBlock())) {
                            break label63;
                        }
                    }

                    --y;
                    continue;
                }
            }

            if (y <= 3) {
                return false;
            }

            int k2 = this.blobSize;

            for (int l = 0; k2 >= 0 && l < 3; ++l) {
                int i1 = k2 + rand.nextInt(2);
                int j1 = k2 + rand.nextInt(2);
                int k1 = k2 + rand.nextInt(2);
                float f = (float) (i1 + j1 + k1) * 0.333F + 0.5F;

                for (int l1 = x - i1; l1 <= x + i1; ++l1) {
                    for (int i2 = z - k1; i2 <= z + k1; ++i2) {
                        for (int j2 = y - j1; j2 <= y + j1; ++j2) {
                            float f1 = (float) (l1 - x);
                            float f2 = (float) (i2 - z);
                            float f3 = (float) (j2 - y);

                            if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f) {
                                this.placeBoulderBlock(world, new BlockPos(l1, j2, i2), boulderBlock);
                            }
                        }
                    }
                }

                x += -(k2 + 1) + rand.nextInt(2 + k2 * 2);
                z += -(k2 + 1) + rand.nextInt(2 + k2 * 2);
                y += 0 - rand.nextInt(2);
            }

            return true;
        }
    }

    private void placeBoulderBlock(World world, BlockPos targetPos, IBlockState boulderBlock) {


        Block targetblock = world.getBlockState(targetPos).getBlock();


        if (targetblock.isReplaceable(world, targetPos)) {

            world.setBlockState(targetPos, boulderBlock, 2);

            // Double-plant check.
            BlockPos aboveTargetPos = targetPos.up();
            Block abovetargetblock = world.getBlockState(aboveTargetPos).getBlock();

            if (abovetargetblock == Blocks.DOUBLE_PLANT) {
                world.setBlockState(aboveTargetPos, Blocks.AIR.getDefaultState(), 2);
                //Logger.debug("Replaced double plant at %d %d %d.", aboveTargetPos.getX(), aboveTargetPos.getY(), aboveTargetPos.getZ());
            }
        }
    }
}