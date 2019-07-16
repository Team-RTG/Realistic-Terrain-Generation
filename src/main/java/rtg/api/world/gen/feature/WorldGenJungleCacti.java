package rtg.api.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenJungleCacti extends WorldGenerator {

    private boolean sandOnly;
    private int extraHeight;

    public WorldGenJungleCacti(boolean sandOnly, int extraHeight) {

        this.sandOnly = sandOnly;
        this.extraHeight = extraHeight;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos blockPos) {

        for (int tries = 0; tries < 10; ++tries) {

            BlockPos pos = blockPos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (world.isAirBlock(pos)) {
                IBlockState ground = world.getBlockState(blockPos.down());
                if (ground == Blocks.SAND.getDefaultState() || (!sandOnly && (ground == Blocks.GRASS.getDefaultState() || ground == Blocks.DIRT.getDefaultState()))) {
                    if (ground == Blocks.GRASS.getDefaultState() || ground == Blocks.DIRT.getDefaultState()) {
                        world.setBlockState(pos.down(), Blocks.SAND.getDefaultState(), 2);
                    }

                    for (int i = 0; i < (1 + rand.nextInt(rand.nextInt(3) + 1)) + extraHeight; ++i) {
                        if (Blocks.CACTUS.canBlockStay(world, pos.up(i))) {
                            world.setBlockState(pos.up(i), Blocks.CACTUS.getDefaultState(), 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}
