package enhancedbiomes.world.biome.archipelago;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenArchipelagoBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeGenFlowerArchipelago extends BiomeGenArchipelagoBase
{
    public BiomeGenFlowerArchipelago(int p_i45377_1_)
    {
        super(p_i45377_1_);
        this.theBiomeDecorator.treesPerChunk = 6;
        this.theBiomeDecorator.flowersPerChunk = 100;
        this.theBiomeDecorator.grassPerChunk = 1;

        this.func_76733_a(5159473);
       
        this.flowers.clear();
        for (int x = 0; x < BlockFlower.field_149859_a.length; x++)
        {
            this.addFlower(Blocks.red_flower, x, 10);
        }
    }
    
    @Override
    public BiomeGenBase func_150557_a(int p_150557_1_, boolean p_150557_2_)
    {
        return super.func_150557_a(p_150557_1_, p_150557_2_);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(5) == 0 ? TreeGen.birch() : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_)
    {
    	double d0 = MathHelper.clamp_double((1.0D + plantNoise.func_151601_a((double)p_150572_2_ / 48.0D, (double)p_150572_4_ / 48.0D)) / 2.0D, 0.0D, 0.9999D);
        int l = (int)(d0 * (double)BlockFlower.field_149859_a.length);

        if (l == 1)
        {
            l = 0;
        }

        return BlockFlower.field_149859_a[l];
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;

        k = par2Random.nextInt(5) - 1;
        l = 0;

        while (l < k)
        {
            i1 = par2Random.nextInt(3);

            if (i1 == 0)
            {
                genTallFlowers.func_150548_a(1);
            }
            else if (i1 == 1)
            {
                genTallFlowers.func_150548_a(4);
            }
            else if (i1 == 2)
            {
                genTallFlowers.func_150548_a(5);
            }

            j1 = 0;

            while (true)
            {
                if (j1 < 5)
                {
                    k1 = par3 + par2Random.nextInt(16) + 8;
                    int i2 = par4 + par2Random.nextInt(16) + 8;
                    int l1 = par2Random.nextInt(par1World.getHeightValue(k1, i2) + 32);

                    if (!genTallFlowers.generate(par1World, par2Random, k1, l1, i2))
                    {
                        ++j1;
                        continue;
                    }
                }

                ++l;
                break;
            }
        }

        super.decorate(par1World, par2Random, par3, par4);
    }
}