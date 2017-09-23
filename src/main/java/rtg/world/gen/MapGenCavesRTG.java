package rtg.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

import com.google.common.base.Objects;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;

// TODO: [Clean-up] Clean up this class to fully match the vanilla super class.
// TODO: [Clean-up] Remove unneccessary overrides, or delegate to the super methods instead of overiding them with cloned functionality that may change in the future
// TODO: [Clean-up] Remove all biome-specific code
public class MapGenCavesRTG extends MapGenCaves
{
    protected static final IBlockState BLK_LAVA = Blocks.LAVA.getDefaultState();
    protected static final IBlockState BLK_AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState BLK_SANDSTONE = Blocks.SANDSTONE.getDefaultState();
    protected static final IBlockState BLK_RED_SANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();
// TODO: [Generator settings] remove these fields and their checks (Make in CPRTG#provideChunk)
    private RTGConfig rtgConfig = RTGAPI.config();
    private boolean enableCaves;

// TODO: [Generator settings] update to use the generator settings
//  private final int caveDensity;
//  private final int caveChance;

//  MapGenCavesRTG(int caveChance, int caveDensity) {
//      this.caveChance  = caveChance;
//      this.caveDensity = caveDensity;
//  }

    protected void addRoom(long p_180703_1_, int p_180703_3_, int p_180703_4_, ChunkPrimer p_180703_5_,
                           double p_180703_6_, double p_180703_8_, double p_180703_10_) {

        this.addTunnel(
            p_180703_1_, p_180703_3_, p_180703_4_, p_180703_5_, p_180703_6_, p_180703_8_, p_180703_10_,
            1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void addTunnel(long p_180702_1_, int p_180702_3_, int p_180702_4_, ChunkPrimer p_180702_5_,
                             double p_180702_6_, double p_180702_8_, double p_180702_10_,
                             float p_180702_12_, float p_180702_13_, float p_180702_14_,
                             int p_180702_15_, int p_180702_16_, double p_180702_17_) {

        double d0 = (double) (p_180702_3_ * 16 + 8);
        double d1 = (double) (p_180702_4_ * 16 + 8);
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(p_180702_1_);

        if (p_180702_16_ <= 0) {
            int i = this.range * 16 - 16;
            p_180702_16_ = i - random.nextInt(i / 4);
        }

        boolean flag2 = false;

        if (p_180702_15_ == -1) {
            p_180702_15_ = p_180702_16_ / 2;
            flag2 = true;
        }

        int j = random.nextInt(p_180702_16_ / 2) + p_180702_16_ / 4;

        for (boolean flag = random.nextInt(6) == 0; p_180702_15_ < p_180702_16_; ++p_180702_15_) {
            double d2 = 1.5D + (double) (MathHelper.sin((float) p_180702_15_ * (float) Math.PI / (float) p_180702_16_) * p_180702_12_);
            double d3 = d2 * p_180702_17_;
            float f2 = MathHelper.cos(p_180702_14_);
            float f3 = MathHelper.sin(p_180702_14_);
            p_180702_6_ += (double) (MathHelper.cos(p_180702_13_) * f2);
            p_180702_8_ += (double) f3;
            p_180702_10_ += (double) (MathHelper.sin(p_180702_13_) * f2);

            if (flag) p_180702_14_ = p_180702_14_ * 0.92F;
            else      p_180702_14_ = p_180702_14_ * 0.7F;

            p_180702_14_ = p_180702_14_ + f1 * 0.1F;
            p_180702_13_ += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag2 && p_180702_15_ == j && p_180702_12_ > 1.0F && p_180702_16_ > 0) {
                this.addTunnel(
                    random.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_,
                    random.nextFloat() * 0.5F + 0.5F, p_180702_13_ - ((float) Math.PI / 2F), p_180702_14_ / 3.0F,
                    p_180702_15_, p_180702_16_, 1.0D);
                this.addTunnel(
                    random.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_,
                    random.nextFloat() * 0.5F + 0.5F, p_180702_13_ + ((float) Math.PI / 2F), p_180702_14_ / 3.0F,
                    p_180702_15_, p_180702_16_, 1.0D);
                return;
            }

            if (flag2 || random.nextInt(4) != 0) {
                double d4 = p_180702_6_ - d0;
                double d5 = p_180702_10_ - d1;
                double d6 = (double) (p_180702_16_ - p_180702_15_);
                double d7 = (double) (p_180702_12_ + 2.0F + 16.0F);

                if (d4 * d4 + d5 * d5 - d6 * d6 > d7 * d7) return;

                if (p_180702_6_ >= d0 - 16.0D - d2 * 2.0D && p_180702_10_ >= d1 - 16.0D - d2 * 2.0D && p_180702_6_ <= d0 + 16.0D + d2 * 2.0D && p_180702_10_ <= d1 + 16.0D + d2 * 2.0D) {
                    int k2 = MathHelper.floor(p_180702_6_ - d2) - p_180702_3_ * 16 - 1;
                    int k = MathHelper.floor(p_180702_6_ + d2) - p_180702_3_ * 16 + 1;
                    int l2 = MathHelper.floor(p_180702_8_ - d3) - 1;
                    int l = MathHelper.floor(p_180702_8_ + d3) + 1;
                    int i3 = MathHelper.floor(p_180702_10_ - d2) - p_180702_4_ * 16 - 1;
                    int i1 = MathHelper.floor(p_180702_10_ + d2) - p_180702_4_ * 16 + 1;

                    if (k2 < 0)  k2 = 0;
                    if (k > 16)  k  = 16;
                    if (l2 < 1)  l2 = 1;
                    if (l > 248) l  = 248;
                    if (i3 < 0)  i3 = 0;
                    if (i1 > 16) i1 = 16;

                    boolean flag3 = false;

                    for (int j1 = k2; !flag3 && j1 < k; ++j1) {
                        for (int k1 = i3; !flag3 && k1 < i1; ++k1) {
                            for (int l1 = l + 1; !flag3 && l1 >= l2 - 1; --l1) {
                                if (l1 >= 0 && l1 < 256) {
                                    if (isOceanBlock(p_180702_5_, j1, l1, k1, p_180702_3_, p_180702_4_)) flag3 = true;
                                    if (l1 != l2 - 1 && j1 != k2 && j1 != k - 1 && k1 != i3 && k1 != i1 - 1) l1 = l2;
                                }
                            }
                        }
                    }

                    if (!flag3) {
                        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                        for (int j3 = k2; j3 < k; ++j3) {
                            double d10 = ((double) (j3 + p_180702_3_ * 16) + 0.5D - p_180702_6_) / d2;

                            for (int i2 = i3; i2 < i1; ++i2) {
                                double d8 = ((double) (i2 + p_180702_4_ * 16) + 0.5D - p_180702_10_) / d2;
                                boolean flag1 = false;

                                if (d10 * d10 + d8 * d8 < 1.0D) {
                                    for (int j2 = l; j2 > l2; --j2) {
                                        double d9 = ((double) (j2 - 1) + 0.5D - p_180702_8_) / d3;

                                        if (d9 > -0.7D && d10 * d10 + d9 * d9 + d8 * d8 < 1.0D) {
                                            IBlockState iblockstate1 = p_180702_5_.getBlockState(j3, j2, i2);
                                            IBlockState iblockstate2 = Objects.firstNonNull(p_180702_5_.getBlockState(j3, j2 + 1, i2), BLK_AIR);

                                            if (isTopBlock(p_180702_5_, j3, j2, i2, p_180702_3_, p_180702_4_)) flag1 = true;

                                            digBlock(p_180702_5_, j3, j2, i2, p_180702_3_, p_180702_4_, flag1, iblockstate1, iblockstate2);
                                        }
                                    }
                                }
                            }
                        }
                        if (flag2) break;
                    }
                }
            }
        }
    }

    protected boolean canReplaceBlock(IBlockState p_175793_1_, IBlockState p_175793_2_) {
        return (
            p_175793_1_.getBlock() == Blocks.STONE ||
                p_175793_1_.getBlock() == Blocks.DIRT ||
                p_175793_1_.getBlock() == Blocks.GRASS ||
                p_175793_1_.getBlock() == Blocks.HARDENED_CLAY ||
                p_175793_1_.getBlock() == Blocks.STAINED_HARDENED_CLAY ||
                p_175793_1_.getBlock() == Blocks.SANDSTONE ||
                p_175793_1_.getBlock() == Blocks.RED_SANDSTONE ||
                p_175793_1_.getBlock() == Blocks.MYCELIUM ||
                p_175793_1_.getBlock() == Blocks.SNOW_LAYER ||
                p_175793_1_.getBlock() == Blocks.SAND ||
                p_175793_1_.getBlock() == Blocks.GRAVEL
        ) && p_175793_2_.getMaterial() != Material.WATER;
    }

    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int p_180701_4_, int p_180701_5_, ChunkPrimer chunkPrimerIn) {
        // Return early if caves are disabled.
// TODO: [Generator settings] Remove. Check is made in CPRTG#provideChunk
        if (!rtgConfig.ENABLE_CAVES.get()) return;

        // Use the global settings by default.
// TODO: [Generator settings] update to use the generator settings, and the new fields caveDensity, caveChance
        int caveDensity = rtgConfig.CAVE_DENSITY.get();
        int caveFrequency = rtgConfig.CAVE_FREQUENCY.get();

// TODO: [Clean-up] remove all references to biome specific cave generation
        //biome-specific cave features disabled for performance;

//        // If the user has set biome-specific settings, let's use those instead.
//        Biome biome = worldIn.getBiome(new BlockPos(this.rand.nextInt(16) + chunkX * 16, 0, this.rand.nextInt(16) + chunkZ * 16));
//
//        RealisticBiomeBase realisticBiome = RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome));
//
//        if (realisticBiome != null) {
//            caveDensity = (realisticBiome.getConfig().CAVE_DENSITY.get() > -1) ? realisticBiome.getConfig().CAVE_DENSITY.get() : caveDensity;
//            caveFrequency = (realisticBiome.getConfig().CAVE_FREQUENCY.get() > -1) ? realisticBiome.getConfig().CAVE_FREQUENCY.get() : caveFrequency;
//        }

        // Return early if caves are disabled.
        if (caveDensity < 1 || caveFrequency < 1) return;

        caveDensity = (caveDensity < 1) ? 1 : ((caveDensity > 39) ? 39 : caveDensity);
        caveDensity += 1;

        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(caveDensity) + 1) + 1);

        caveFrequency = (caveFrequency < 1) ? 1 : ((caveFrequency > 39) ? 39 : caveFrequency);
        caveFrequency += 1;

        if (this.rand.nextInt(caveFrequency) != 0) i = 0;

        for (int j = 0; j < i; ++j) {
            double d0 = (double) (chunkX * 16 + this.rand.nextInt(16));
            double d1 = (double) this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = (double) (chunkZ * 16 + this.rand.nextInt(16));
            int k = 1;

            if (this.rand.nextInt(4) == 0) {
                this.addRoom(this.rand.nextLong(), p_180701_4_, p_180701_5_, chunkPrimerIn, d0, d1, d2);
                k += this.rand.nextInt(4);
            }

            for (int l = 0; l < k; ++l) {
                float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0) {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }
                this.addTunnel(this.rand.nextLong(), p_180701_4_, p_180701_5_, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }

    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
        net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
        return block == Blocks.FLOWING_WATER || block == Blocks.WATER;
    }

    /**
     * Digs out the current block, default implementation removes stone, filler, and top block
     * Sets the block to lava if y is less then 10, and air other wise.
     * If setting to air, it also checks to see if we've broken the surface and if so
     * tries to make the floor the biome's top block
     *
     * @param data
     *     Block data array
     * @param x
     *     local X position
     * @param y
     *     local Y position
     * @param z
     *     local Z position
     * @param chunkX
     *     Chunk X position
     * @param chunkZ
     *     Chunk Y position
     * @param foundTop
     *     True if we've encountered the biome's top block. Ideally if we've broken the surface.
     */
    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
        net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;

        if (this.canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()) {
            if (y - 1 < 10) {
                data.setBlockState(x, y, z, BLK_LAVA);
            } else {
                data.setBlockState(x, y, z, BLK_AIR);

                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock()) {
                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }

    //Exception biomes to make sure we generate like vanilla
    private boolean isExceptionBiome(net.minecraft.world.biome.Biome biome) {
        return biome == net.minecraft.init.Biomes.BEACH || biome == net.minecraft.init.Biomes.DESERT;
    }

    //Determine if the block at the specified location is the top block for the biome, we take into account
    //Vanilla bugs to make sure that we generate the map the same way vanilla does.
    private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
        net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState state = data.getBlockState(x, y, z);
        return (isExceptionBiome(biome) ? state.getBlock() == Blocks.GRASS : state.getBlock() == biome.topBlock);
    }
}