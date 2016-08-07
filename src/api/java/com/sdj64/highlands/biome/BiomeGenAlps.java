package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTaiga1;

public class BiomeGenAlps extends BiomeGenBaseHighlands{

	public BiomeGenAlps(int par1)
	{
	    super(par1);
	    
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 0;
        theBiomeDecorator.flowersPerChunk = 0;
	    
	    this.spawnableCreatureList.clear();
	    this.topBlock = Blocks.snow.getDefaultState();
	    this.fillerBlock = Blocks.snow.getDefaultState();
	    
	    this.maxHeight = 1.0F;
	    this.minHeight = 1.5F;
	    this.temperature = 0.0F;
	    this.rainfall = 0.7F;
	        
	    this.setEnableSnow();
	}
	    
	public WorldGenAbstractTree genBigTreeChance(Random par1Random)
	{
	    return (par1Random.nextInt(5) == 0 ? HighlandsGenerators.firGen : HighlandsGenerators.shrubGen);
	}
	
	public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(12, HighlandsGenerators.hlice, 32, 100, world, random, pos);
        genStandardOre(theBiomeDecorator.chunkProviderSettings.ironCount/2, theBiomeDecorator.ironGen, theBiomeDecorator.chunkProviderSettings.ironMinHeight, theBiomeDecorator.chunkProviderSettings.ironMaxHeight, world, random, pos);
        
        for(int i = 0; i < 10; i++){
        	if(world.getTopSolidOrLiquidBlock(pos).getY() < 80){
	        	int x = random.nextInt(16) + 8;
	            int z = random.nextInt(16) + 8;
	            
	            BlockPos treepos = world.getHeight(pos.add(x, 0, z));
	            
	            if(random.nextInt(3) == 0){
	            	HighlandsGenerators.firGen.generate(world, random, treepos);
	            }
	            else{
	            	new WorldGenTaiga1().generate(world, random, treepos);
	            }
	            
	        }
        	else if(world.getTopSolidOrLiquidBlock(pos).getY() < 110){
	        	int x = random.nextInt(16) + 8;
	            int z = random.nextInt(16) + 8;
	            
	            BlockPos treepos = world.getHeight(pos.add(x, 0, z));
	            
	            if(random.nextInt(4) == 0){
		            if(random.nextInt(3) == 0){
		            	HighlandsGenerators.firGen.generate(world, random, treepos);
		            }
		            else{
		            	new WorldGenTaiga1().generate(world, random, treepos);
		            }
	            }
	            
	        }
        }
        
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
	
	
    /*
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)return 0xC6E3FF;
    	else return super.getSkyColorByTemp(par1);
    }
    */
    
    /*
    public void setSpawns(List hostile, List creature, List water){
    	this.spawnableMonsterList = hostile;
    	this.spawnableCreatureList = creature;
    	this.spawnableWaterCreatureList = water;
    }
    */
}
