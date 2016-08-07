package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTaiga1;

public class BiomeGenAdirondacks extends BiomeGenBaseHighlands
{

    private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeGenAdirondacks(int par1)
    {
        super(par1);
        
        theBiomeDecorator.treesPerChunk = 2;
        theBiomeDecorator.grassPerChunk = 6;
        theBiomeDecorator.flowersPerChunk = 0;
	    
        maxHeight = 0.8F;
        minHeight = 0.8F;
        temperature = 0.5F;
        rainfall = 0.6F;
        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
        
        plants.add(HighlandsGenerators.blueberryBush);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
        return HighlandsGenerators.shrubGen;
    }
    
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        for(int i = 0; i < 10; i++){
        	if(world.getTopSolidOrLiquidBlock(pos).getY() < 80){
	        	int x = random.nextInt(16) + 8;
	            int z = random.nextInt(16) + 8;
	            
	            BlockPos treepos = world.getHeight(pos.add(x, 0, z));
	            
	            //generate birch or fir tree
	            if(random.nextInt(3) == 0){
	            	new WorldGenBigTree(false).generate(world, random, treepos);
	            }
	            else if(random.nextInt(5) == 0){
	            	HighlandsGenerators.firGen.generate(world, random, treepos);
	            }
	            else {
	            	new WorldGenForest(false, false).generate(world, random, treepos);
	            }
	            
	        }
        	else if(world.getTopSolidOrLiquidBlock(pos).getY() < 110){
	        	int x = random.nextInt(16) + 8;
	            int z = random.nextInt(16) + 8;
	            
	            BlockPos treepos = world.getHeight(pos.add(x, 0, z));
	            
	            //generate birch or fir tree
	            if(random.nextInt(3) == 0){
	            	new WorldGenTaiga1().generate(world, random, treepos);
	            }
	            else {
	            	new WorldGenForest(false, false).generate(world, random, treepos);
	            }
	            
	        }
        }
        
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
    
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.grass.getDefaultState();
        this.fillerBlock = Blocks.dirt.getDefaultState();

        if (whatisthis < 0.0D)
        {
            this.topBlock = Blocks.dirt.getStateFromMeta(1);
            this.fillerBlock = Blocks.dirt.getStateFromMeta(1);
        }
        if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.stone.getDefaultState();
            this.fillerBlock = Blocks.stone.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
}
