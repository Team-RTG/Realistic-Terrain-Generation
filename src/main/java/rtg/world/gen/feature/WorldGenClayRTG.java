package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenClayRTG extends WorldGenerator {

    private Block field_150546_a = Blocks.CLAY;
    /**
     * The number of blocks to generate.
     */
    private int numberOfBlocks;

    public WorldGenClayRTG(int p_i2011_1_) {

        this.numberOfBlocks = p_i2011_1_;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (world.getBlockState(pos).getBlock().getMaterial() != Material.water) {
            return false;
        }
        else {
            int i = rand.nextInt(this.numberOfBlocks - 2) + 2;
            int j = 1;

            for (int k = pos.getX() - i; k <= pos.getX() + i; ++k) {
                for (int l = pos.getZ() - i; l <= pos.getZ() + i; ++l) {
                    int i1 = k - pos.getX();
                    int j1 = l - pos.getZ();

                    if (i1 * i1 + j1 * j1 <= i * i) {
                        for (int k1 = pos.getY() - j; k1 <= pos.getY() + j; ++k1) {
                            BlockPos blockpos = new BlockPos(k, k1, l);
                            Block block = world.getBlockState(blockpos).getBlock();

                            if (block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.SAND || block == Blocks.GRAVEL || block == Blocks.CLAY) {
                                world.setBlockState(blockpos, this.field_150546_a.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}