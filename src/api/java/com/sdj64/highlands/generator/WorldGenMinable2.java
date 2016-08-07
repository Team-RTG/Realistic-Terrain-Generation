package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.base.Predicate;


/**
 * WorldGenMinable2 is a copy of WorldGenMinable that allows, if necessary, generation of
 * blocks in other places besides stone.  It can also still generate in stone (and stone variants,
 * just like WorldGenMinable) too.
 * @author sdj64
 *
 */
public class WorldGenMinable2 extends WorldGenerator
{
    private final IBlockState oreBlock;
    /** The number of blocks to generate. */
    private final int numberOfBlocks;
    private final Predicate field_175919_c;
    
    private boolean genInStone;
    private IBlockState BSGin;

    public WorldGenMinable2(IBlockState blockState, int numBlocks, boolean generateInStone)
    {
        this(blockState, numBlocks, BlockHelper.forBlock(Blocks.stone));
        genInStone = generateInStone;
        BSGin = null;
    }
    
    public WorldGenMinable2(IBlockState blockState, int numBlocks, IBlockState blockStateGenerateIn)
    {
        this(blockState, numBlocks, BlockHelper.forBlock(Blocks.stone));
        genInStone = false;
        BSGin = blockStateGenerateIn;
    }

    public WorldGenMinable2(IBlockState blockState, int numBlocks, Predicate p_i45631_3_)
    {
        this.oreBlock = blockState;
        this.numberOfBlocks = numBlocks;
        this.field_175919_c = p_i45631_3_;
    }

    public boolean generate(World world, Random random, BlockPos pos)
    {
        float f = random.nextFloat() * (float)Math.PI;
        double d0 = (double)((float)(pos.getX() + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d1 = (double)((float)(pos.getX() + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d2 = (double)((float)(pos.getZ() + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d3 = (double)((float)(pos.getZ() + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d4 = (double)(pos.getY() + random.nextInt(3) - 2);
        double d5 = (double)(pos.getY() + random.nextInt(3) - 2);

        for (int i = 0; i < this.numberOfBlocks; ++i)
        {
            float f1 = (float)i / (float)this.numberOfBlocks;
            double d6 = d0 + (d1 - d0) * (double)f1;
            double d7 = d4 + (d5 - d4) * (double)f1;
            double d8 = d2 + (d3 - d2) * (double)f1;
            double d9 = random.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double d10 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor_double(d6 - d10 / 2.0D);
            int k = MathHelper.floor_double(d7 - d11 / 2.0D);
            int l = MathHelper.floor_double(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor_double(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor_double(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor_double(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1)
            {
                double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int i2 = k; i2 <= j1; ++i2)
                    {
                        double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int j2 = l; j2 <= k1; ++j2)
                            {
                                double d14 = ((double)j2 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    BlockPos blockpos1 = new BlockPos(l1, i2, j2);

                                    if(genInStone){
	                                    if (world.getBlockState(blockpos1).getBlock().isReplaceableOreGen(world, blockpos1, this.field_175919_c))
	                                    {
	                                        world.setBlockState(blockpos1, this.oreBlock, 2);
	                                    }
                                    }
                                    else if(!(BSGin == null))
                                    {
                                    	if (world.getBlockState(blockpos1).equals(BSGin) || 
                                    			(BSGin.equals(Blocks.dirt.getDefaultState()) &&
                                    					world.getBlockState(blockpos1).equals(Blocks.grass.getDefaultState())))
	                                    {
	                                        world.setBlockState(blockpos1, this.oreBlock, 2);
	                                    }
                                    }
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