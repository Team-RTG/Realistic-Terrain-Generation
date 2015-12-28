package enhancedbiomes.world.biome.grass.plains;

import java.util.Random;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import enhancedbiomes.world.gen.WorldGenSandAndCactus;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenSavannah extends BiomeGenPlainsBase
{
    public BiomeGenSavannah(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = 25;
    }
    
    public void decorate(World worldObj, Random rand, int x, int z)
    {
    	for(int c = 45; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlock(j2, l3, j5) == Blocks.air && EnhancedBiomesBlocks.isGrass(worldObj.getBlock(j2, l3 - 1, j5)))
        	{
        		(TreeGen.thorntree()).generate(worldObj, rand, j2, l3, j5);
        	}
    	} 
        
    	for(int c = 35; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlock(j2, l3, j5) == Blocks.air && EnhancedBiomesBlocks.isGrass(worldObj.getBlock(j2, l3 - 1, j5)))
        	{
        		(new WorldGenSandAndCactus()).generate(worldObj, rand, j2, l3, j5);
        	}
    	} 
        
        for(int c = 25; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlock(j2, l3, j5) == Blocks.air && EnhancedBiomesBlocks.isGrass(worldObj.getBlock(j2, l3 - 1, j5)))
        	{
        		(TreeGen.baobab(rand)).generate(worldObj, rand, j2, l3, j5);
        	}
    	}
        
        super.decorate(worldObj, rand, x, z);		
    }
}
