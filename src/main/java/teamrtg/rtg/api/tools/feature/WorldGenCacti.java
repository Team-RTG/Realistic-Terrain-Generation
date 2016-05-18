package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.init.Blocks.*;

public class WorldGenCacti extends WorldGenerator {
    private boolean SAND;
    private int eHeight;
    private IBlockState soil;

    public WorldGenCacti(boolean sandOnly) {
        this(sandOnly, 0);
    }

    public WorldGenCacti(boolean sandOnly, int extraHeight) {
        this(sandOnly, extraHeight, Blocks.SAND.getDefaultState());
    }

    public WorldGenCacti(boolean sandOnly, int extraHeight, IBlockState soil) {
        SAND = sandOnly;
        eHeight = 0;
        this.soil = soil;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block b;
        for (int l = 0; l < 10; ++l) {
            int i1 = x + rand.nextInt(8) - rand.nextInt(8);
            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            int k1 = z + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(new BlockPos(i1, j1, k1))) {
                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
                if (b == Blocks.SAND || (!SAND && (b == GRASS || b == DIRT))) {
                    int l1 = 1 + rand.nextInt(rand.nextInt(3) + 1);
                    if (b == GRASS || b == DIRT) {
                        world.setBlockState(new BlockPos(i1, j1 - 1, k1), Blocks.SAND.getDefaultState(), 2);
                    }

                    for (int i2 = 0; i2 < l1 + eHeight; ++i2) {
                        if (CACTUS.canBlockStay(world, new BlockPos(i1, j1 + i2, k1))) {
                            world.setBlockState(new BlockPos(i1, j1 + i2, k1), CACTUS.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}