package enhancedbiomes.world.biome.wasteland.rock;

import java.util.List;
import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenRockBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.*;

public class BiomeGenWasteLands extends BiomeGenRockBase
{
    public BiomeGenWasteLands(int par1)
    {
        super(par1);
        this.topBlock = Blocks.gravel;
        this.fillerBlock = Blocks.cobblestone;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.generateLakes = false;
    }

    public void decorate(World worldObj, Random rand, int x, int z)
    {
        super.decorate(worldObj, rand, x, z);
		
        for(int c = 20; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	(new WorldGenMinableEnhancedBiomes(EnhancedBiomesMod.soilList[biomeID], EnhancedBiomesMod.soilMetaList[biomeID], 50, Blocks.gravel)).generate(worldObj, rand, j2, l3, j5);
    	}
        
        for(int c = 200; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlock(j2, l3, j5) == Blocks.air && worldObj.getBlock(j2, l3 - 1, j5) == EnhancedBiomesMod.soilList[biomeID])
        	{
        		TreeGen.dead(rand);
        	}
    	}      
    }
}