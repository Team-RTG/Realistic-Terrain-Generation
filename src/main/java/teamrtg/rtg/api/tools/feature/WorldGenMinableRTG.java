package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.PI;
import static net.minecraft.init.Blocks.STONE;
import static net.minecraft.util.math.MathHelper.*;

public class WorldGenMinableRTG extends WorldGenerator {
    private Block field_150519_a;
    /**
     * The number of blocks to generate.
     */
    private int numberOfBlocks;
    private Block field_150518_c;
    private int mineableBlockMeta;

    public WorldGenMinableRTG(Block p_i45459_1_, int p_i45459_2_) {
        this(p_i45459_1_, p_i45459_2_, STONE);
    }

    public WorldGenMinableRTG(Block p_i45460_1_, int p_i45460_2_, Block p_i45460_3_) {
        this.field_150519_a = p_i45460_1_;
        this.numberOfBlocks = p_i45460_2_;
        this.field_150518_c = p_i45460_3_;
    }

    public WorldGenMinableRTG(Block block, int meta, int number, Block target) {
        this(block, number, target);
        this.mineableBlockMeta = meta;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        int intBlocksPlaced = 0;

        float f = p_76484_2_.nextFloat() * (float) PI;
        double d0 = (double) ((float) (p_76484_3_ + 8) + sin(f) * (float) this.numberOfBlocks / 8.0F);
        double d1 = (double) ((float) (p_76484_3_ + 8) - sin(f) * (float) this.numberOfBlocks / 8.0F);
        double d2 = (double) ((float) (p_76484_5_ + 8) + cos(f) * (float) this.numberOfBlocks / 8.0F);
        double d3 = (double) ((float) (p_76484_5_ + 8) - cos(f) * (float) this.numberOfBlocks / 8.0F);
        double d4 = (double) (p_76484_4_ + p_76484_2_.nextInt(3) - 2);
        double d5 = (double) (p_76484_4_ + p_76484_2_.nextInt(3) - 2);

        for (int l = 0; l <= this.numberOfBlocks; ++l) {
            double d6 = d0 + (d1 - d0) * (double) l / (double) this.numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double) l / (double) this.numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double) l / (double) this.numberOfBlocks;
            double d9 = p_76484_2_.nextDouble() * (double) this.numberOfBlocks / 16.0D;
            double d10 = (double) (sin((float) l * (float) PI / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
            double d11 = (double) (sin((float) l * (float) PI / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
            int i1 = floor_double(d6 - d10 / 2.0D);
            int j1 = floor_double(d7 - d11 / 2.0D);
            int k1 = floor_double(d8 - d10 / 2.0D);
            int l1 = floor_double(d6 + d10 / 2.0D);
            int i2 = floor_double(d7 + d11 / 2.0D);
            int j2 = floor_double(d8 + d10 / 2.0D);

            for (int k2 = i1; k2 <= l1; ++k2) {
                double d12 = ((double) k2 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D) {
                    for (int l2 = j1; l2 <= i2; ++l2) {
                        double d13 = ((double) l2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D) {
                            for (int i3 = k1; i3 <= j2; ++i3) {
                                double d14 = ((double) i3 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && p_76484_1_.getBlockState(new BlockPos(k2, l2, i3)).getBlock().isReplaceableOreGen(p_76484_1_.getBlockState(new BlockPos(k2, l2, i3)), p_76484_1_, new BlockPos(k2, l2, i3), BlockMatcher.forBlock(field_150518_c))) {
                                    p_76484_1_.setBlockState(new BlockPos(k2, l2, i3), this.field_150519_a.getStateFromMeta(mineableBlockMeta), 2);
                                    intBlocksPlaced++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}