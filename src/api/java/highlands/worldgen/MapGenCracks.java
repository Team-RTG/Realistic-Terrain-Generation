package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;

public class MapGenCracks extends MapGenBase
{
    private float[] field_75046_d = new float[1024];

    protected void func_151540_a(long seed, int chunkX, int chunkZ, Block[] blockArray, double x, double y, double z, float p_151540_12_, float p_151540_13_, float p_151540_14_, int p_151540_15_, int p_151540_16_, double height)
    {
        Random random = new Random(seed);
        double d4 = (double)(chunkX * 16 + 8);
        double d5 = (double)(chunkZ * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;

        if (p_151540_16_ <= 0)
        {
            int j1 = this.range * 16 - 16;
            p_151540_16_ = j1 - random.nextInt(j1 / 4);
        }

        boolean flag1 = false;

        if (p_151540_15_ == -1)
        {
            p_151540_15_ = p_151540_16_ / 2;
            flag1 = true;
        }

        float f5 = 1.0F;

        for (int k1 = 0; k1 < 256; ++k1)
        {
            if (k1 == 0 || random.nextInt(3) == 0)
            {
                f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
            }

            this.field_75046_d[k1] = f5 * f5;
        }

        for (; p_151540_15_ < p_151540_16_; ++p_151540_15_)
        {
            double d12 = 1.5D + (double)(MathHelper.sin((float)p_151540_15_ * (float)Math.PI / (float)p_151540_16_) * p_151540_12_ * 1.0F);
            double d6 = d12 * height;
            d12 *= (double)random.nextFloat() * 0.25D + 0.75D;
            d6 *= (double)random.nextFloat() * 0.25D + 0.75D;
            float f6 = MathHelper.cos(p_151540_14_);
            float f7 = MathHelper.sin(p_151540_14_);
            x += (double)(MathHelper.cos(p_151540_13_) * f6);
            y += (double)f7;
            z += (double)(MathHelper.sin(p_151540_13_) * f6);
            p_151540_14_ *= 0.7F;
            p_151540_14_ += f4 * 0.05F;
            p_151540_13_ += f3 * 0.05F;
            f4 *= 0.8F;
            f3 *= 0.5F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (flag1 || random.nextInt(4) != 0)
            {
                double d7 = x - d4;
                double d8 = z - d5;
                double d9 = (double)(p_151540_16_ - p_151540_15_);
                double d10 = (double)(p_151540_12_ + 2.0F + 16.0F);

                if (d7 * d7 + d8 * d8 - d9 * d9 > d10 * d10)
                {
                    return;
                }

                if (x >= d4 - 16.0D - d12 * 2.0D && z >= d5 - 16.0D - d12 * 2.0D && x <= d4 + 16.0D + d12 * 2.0D && z <= d5 + 16.0D + d12 * 2.0D)
                {
                    int xMin = MathHelper.floor_double(x - d12) - chunkX * 16 - 1;
                    int xMax = MathHelper.floor_double(x + d12) - chunkX * 16 + 1;
                    int yMin = MathHelper.floor_double(y - d6) - 1;
                    int yMax = MathHelper.floor_double(y + d6) + 1;
                    int zMin = MathHelper.floor_double(z - d12) - chunkZ * 16 - 1;
                    int zMax = MathHelper.floor_double(z + d12) - chunkZ * 16 + 1;

                    if (xMin < 0)
                    {
                        xMin = 0;
                    }

                    if (xMax > 16)
                    {
                        xMax = 16;
                    }

                    if (yMin < 1)
                    {
                        yMin = 1;
                    }

                    if (yMax > 248)
                    {
                        yMax = 248;
                    }

                    if (zMin < 0)
                    {
                        zMin = 0;
                    }

                    if (zMax > 16)
                    {
                        zMax = 16;
                    }

                    boolean flag2 = false; // ignore coords if water found
                    int k2;
                    int j3;

                    // scan from top to bottom for water
                    for (k2 = xMin; !flag2 && k2 < xMax; ++k2) // x
                    {
                        for (int l2 = zMin; !flag2 && l2 < zMax; ++l2) // z
                        {
                            for (int i3 = yMax + 1; !flag2 && i3 >= yMin - 1; --i3) // y
                            {
                                j3 = (k2 * 16 + l2) * 256 + i3; // index

                                if (i3 >= 0 && i3 < 256)
                                {
                                    Block block = blockArray[j3];

                                    if (isOceanBlock(blockArray, j3, k2, i3, l2, chunkX, chunkZ))
                                    {
                                        flag2 = true;
                                    }

                                    if (i3 != yMin - 1 && k2 != xMin && k2 != xMax - 1 && l2 != zMin && l2 != zMax - 1)
                                    {
                                        i3 = yMin;
                                    }
                                }
                            }
                        }
                    }

                    // cut hole
                    if (!flag2)
                    {
                        for (k2 = xMin; k2 < xMax; ++k2) // x
                        {
                            double d13 = ((double)(k2 + chunkX * 16) + 0.5D - x) / d12;

                            for (j3 = zMin; j3 < zMax; ++j3) // z
                            {
                                double d14 = ((double)(j3 + chunkZ * 16) + 0.5D - z) / d12;
                                int k3 = (k2 * 16 + j3) * 256 + yMax;
                                boolean flag = false;

                                if (d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    for (int l3 = yMax - 1; l3 >= yMin; --l3) // y
                                    {
                                        double d11 = ((double)l3 + 0.5D - y) / d6;

                                        if ((d13 * d13 + d14 * d14) * (double)this.field_75046_d[l3] + d11 * d11 / 6.0D < 1.0D)
                                        {
                                            Block block1 = blockArray[k3];

                                            if (isTopBlock(blockArray, k3, k2, l3, j3, chunkX, chunkZ))
                                            {
                                                flag = true;
                                            }

                                            digBlock(blockArray, k3, k2, l3, j3, chunkX, chunkZ, flag);
                                        }

                                        --k3;
                                    }
                                }
                            }
                        }

                        if (flag1)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    // primary call to generate
    @Override
    protected void func_151538_a(World world, int x, int z, int chunkX, int chunkZ, Block[] blockArray)
    {
        if (this.rand.nextInt(50) == 0)
        {
            double d0 = (double)(x * 16 + this.rand.nextInt(16)); // random x position
            double d1 = (double)(this.rand.nextInt(this.rand.nextInt(40) + 8) + 20); // random top of ravine
            double d2 = (double)(z * 16 + this.rand.nextInt(16)); // random y position
            byte b0 = 1;

            for (int i1 = 0; i1 < b0; ++i1)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F; // rotation
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F; // -1 to +1 / 8
                float f2 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F; // 0 to 6 - gap size ?
                this.func_151540_a(this.rand.nextLong(), chunkX, chunkZ, blockArray, d0, d1, d2, f2, f, f1, 0, 0, 3.0D);
            }
        }
    }

    protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ)
    {
        return data[index] == Blocks.water || data[index] == Blocks.flowing_water;
    }

    //Exception biomes to make sure we generate like vanilla
    private boolean isExceptionBiome(BiomeGenBase biome)
    {
        if (biome == BiomeGenBase.mushroomIsland) return true;
        if (biome == BiomeGenBase.beach) return true;
        if (biome == BiomeGenBase.desert) return true;
        return false;
    }

    //Determine if the block at the specified location is the top block for the biome, we take into account
    //Vanilla bugs to make sure that we generate the map the same way vanilla does.
    private boolean isTopBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ)
    {
        BiomeGenBase biome = worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        return (isExceptionBiome(biome) ? data[index] == Blocks.grass : data[index] == biome.topBlock);
    }

    /**
     * Digs out the current block, default implementation removes stone, filler, and top block
     * Sets the block to lava if y is less then 10, and air other wise.
     * If setting to air, it also checks to see if we've broken the surface and if so 
     * tries to make the floor the biome's top block
     * 
     * @param data Block data array
     * @param index Pre-calculated index into block data
     * @param x local X position
     * @param y local Y position
     * @param z local Z position
     * @param chunkX Chunk X position
     * @param chunkZ Chunk Y position
     * @param foundTop True if we've encountered the biome's top block. Ideally if we've broken the surface.
     */
    protected void digBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop)
    {
        BiomeGenBase biome = worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        Block top    = (isExceptionBiome(biome) ? Blocks.grass : biome.topBlock);
        Block filler = (isExceptionBiome(biome) ? Blocks.dirt  : biome.fillerBlock);
        Block block  = data[index];

        if (block == Blocks.stone || block == filler || block == top)
        {
            if (y < 10)
            {
                data[index] = Blocks.flowing_lava;
            }
            else
            {
                data[index] = null;

                if (foundTop && data[index - 1] == filler)
                {
                    data[index - 1] = top;
                }
            }
        }
    }
}
