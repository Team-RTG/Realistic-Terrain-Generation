package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.HighlandsMod;
import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenPinelands extends BiomeGenBaseHighlands
{

	public BiomeGenPinelands(int par1)
    {
        super(par1);
        
        theBiomeDecorator.treesPerChunk = 3;
        theBiomeDecorator.grassPerChunk = 6;
        theBiomeDecorator.flowersPerChunk = 0;
	    
        maxHeight = 0.6F;
        minHeight = 0.4F;
        temperature = 0.5F;
        rainfall = 0.6F;
        
        plants.add(HighlandsGenerators.thornbush);
        plants.add(HighlandsGenerators.blueberryBush);
        plants.add(HighlandsGenerators.raspberryBush);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
        return (par1Random.nextInt(2) == 0 ? HighlandsGenerators.shrubGen : new WorldGenTaiga2(false));
    }
    
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(theBiomeDecorator.chunkProviderSettings.ironCount/2, theBiomeDecorator.ironGen, theBiomeDecorator.chunkProviderSettings.ironMinHeight, theBiomeDecorator.chunkProviderSettings.ironMaxHeight, world, random, pos);
        
        int i = 3 + random.nextInt(6);
        int j;
        int k;
        int l;

        for (j = 0; j < i; ++j)
        {
            k = random.nextInt(16);
            l = random.nextInt(28) + 4;
            int i1 = random.nextInt(16);
            BlockPos blockpos1 = pos.add(k, l, i1);

            if (world.getBlockState(blockpos1).getBlock().isReplaceableOreGen(world, blockpos1, net.minecraft.block.state.pattern.BlockHelper.forBlock(Blocks.stone)))
            {
                world.setBlockState(blockpos1, Blocks.emerald_ore.getDefaultState(), 2);
            }
        }
    }
}
