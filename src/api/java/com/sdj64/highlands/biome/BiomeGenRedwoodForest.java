package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.HighlandsMod;
import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenRedwoodForest extends BiomeGenBaseHighlands
{

	private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeGenRedwoodForest(int par1)
    {
        super(par1);
        
        theBiomeDecorator.treesPerChunk = 14;
        theBiomeDecorator.grassPerChunk = 5;
        theBiomeDecorator.flowersPerChunk = 0;
	    
        minHeight = 0.5F;
        maxHeight = 0.2F;
        temperature = 0.6F;
        rainfall = 0.2F;
        
        this.topBlock = Blocks.dirt.getStateFromMeta(2);
        
        plants.add(HighlandsGenerators.lavender);
        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
    	if(par1Random.nextInt(3) == 0)
    		return HighlandsGenerators.redwoodGen;
    	else if(par1Random.nextInt(2) == 0)
    		return HighlandsGenerators.firGen;
    	else return HighlandsGenerators.shrub2Gen;
    }
    
    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(theBiomeDecorator.chunkProviderSettings.redstoneCount/2, theBiomeDecorator.redstoneGen, theBiomeDecorator.chunkProviderSettings.redstoneMinHeight, theBiomeDecorator.chunkProviderSettings.redstoneMaxHeight, world, random, pos);
    }

    
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.dirt.getStateFromMeta(2);
        this.fillerBlock = Blocks.dirt.getDefaultState();

        if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.grass.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
}
