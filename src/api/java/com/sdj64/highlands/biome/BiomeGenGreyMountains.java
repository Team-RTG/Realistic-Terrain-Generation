package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.HighlandsMod;
import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenGreyMountains extends BiomeGenBaseHighlands
{

	private static final WorldGenBlockBlob blockBlob = new WorldGenBlockBlob(Blocks.cobblestone, 0);
	
	public BiomeGenGreyMountains(int par1)
    {
        super(par1);
        
        //this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 8;
        theBiomeDecorator.flowersPerChunk = 0;
        
        this.fillerBlock = Blocks.stone.getDefaultState();
        
        this.minHeight = 1.8F;
        this.maxHeight = 1.0F;
        this.temperature = 0.6F;
        this.rainfall = 0.1F;
        
        plants.add(HighlandsGenerators.thornbush);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
        return (par1Random.nextInt(3) != 0 ? HighlandsGenerators.shrub2Gen : this.worldGeneratorTrees);
    }
    
    
    public int getModdedBiomeGrassColor(int original)
    {
        return 0x909686;
    }
    
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        int i = random.nextInt(3)+1;
        int j;
        int k;
        int l;
        for (j = 0; j < i; ++j)
        {
            k = random.nextInt(16) + 8;
            l = random.nextInt(16) + 8;
            BlockPos blockpos1 = world.getHeight(pos.add(k, 0, l));
            blockBlob.generate(world, random, blockpos1);
        }
        
        for(int ind = 0; ind < 10; ind++){
        	if(world.getTopSolidOrLiquidBlock(pos).getY() < 80){
	        	int x = random.nextInt(16) + 8;
	            int z = random.nextInt(16) + 8;
	            
	            BlockPos treepos = world.getHeight(pos.add(x, 0, z));
	            
	            if(random.nextInt(3) == 0){
	            	HighlandsGenerators.deadTreeGen.generate(world, random, treepos);
	            }
	            else {
		            HighlandsGenerators.firGen.generate(world, random, treepos);
	            }
	            
	        }
        }
        
        genStandardOre(theBiomeDecorator.chunkProviderSettings.ironCount*3/4, theBiomeDecorator.ironGen, theBiomeDecorator.chunkProviderSettings.ironMinHeight, theBiomeDecorator.chunkProviderSettings.ironMaxHeight *3/2, world, random, pos);
    }
    
    
}




