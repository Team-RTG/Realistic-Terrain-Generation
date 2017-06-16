package rtg.api.world.gen.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;


public class WorldGenSponge extends WorldGenerator {

    protected ArrayList<Block> validGroundBlocks;
    protected ArrayList<Block> validAdjacentBlocks;
    protected int minAdjacents;
    private IBlockState spongeBlock;
    private int spongeSize;
    private RTGConfig rtgConfig = RTGAPI.config();

    public WorldGenSponge(IBlockState b, int s, Random rand) {

        super(false);
        this.spongeBlock = b;
        this.spongeSize = s;

        this.validGroundBlocks = new ArrayList<Block>(Arrays.asList(
            Blocks.GRASS,
            Blocks.DIRT,
            Blocks.STONE,
            Blocks.GRAVEL,
            Blocks.CLAY,
            Blocks.SAND
        ));

        this.validAdjacentBlocks = new ArrayList<Block>(Arrays.asList(
            Blocks.PRISMARINE,
            Blocks.COBBLESTONE,
            Blocks.MOSSY_COBBLESTONE
        ));

        this.minAdjacents = 2;
    }

    public WorldGenSponge(IBlockState b, int s, Random rand, ArrayList<Block> validGroundBlocks) {

        this(b, s, rand);
        this.validGroundBlocks = validGroundBlocks;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        while (true) {
            if (y > 3) {
                label63:
                {
                    if (!world.isAirBlock(new BlockPos(x, y - 1, z))) {

                        IBlockState block = world.getBlockState(new BlockPos(x, y - 1, z));

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

            int k2 = this.spongeSize;

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
                                //if (isAdjacent(world, l1, j2, i2)){
                                    world.setBlockState(new BlockPos(l1, j2, i2), spongeBlock, 2);
                                    //Logger.debug("Sponge generated at %d %d %d", l1, j2, i2);
                                //}
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

    protected boolean isAdjacent(World world, int x, int y, int z) {

        int adjacentCount = 0;

        if (validAdjacentBlocks.contains(world.getBlockState(new BlockPos(x + 1, y, z)).getBlock())) {
            adjacentCount++;
        }

        if (validAdjacentBlocks.contains(world.getBlockState(new BlockPos(x - 1, y, z)).getBlock())) {
            adjacentCount++;
        }

        if (validAdjacentBlocks.contains(world.getBlockState(new BlockPos(x, y + 1, z)).getBlock())) {
            adjacentCount++;
        }

        if (validAdjacentBlocks.contains(world.getBlockState(new BlockPos(x, y - 1, z)).getBlock())) {
            adjacentCount++;
        }

        if (validAdjacentBlocks.contains(world.getBlockState(new BlockPos(x, y, z + 1)).getBlock())) {
            adjacentCount++;
        }

        if (validAdjacentBlocks.contains(world.getBlockState(new BlockPos(x, y, z - 1)).getBlock())) {
            adjacentCount++;
        }

        return (adjacentCount > 0 && adjacentCount >= this.minAdjacents);
    }
}