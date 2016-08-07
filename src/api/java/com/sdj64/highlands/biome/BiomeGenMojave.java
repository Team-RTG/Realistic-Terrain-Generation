package com.sdj64.highlands.biome;

import java.util.Random;
import java.util.Random;

import com.sdj64.highlands.HighlandsMod;
import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenMojave extends BiomeGenBaseHighlands
{

    private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeGenMojave(int par1){
		super(par1);
		
		theBiomeDecorator.treesPerChunk = 1;
        theBiomeDecorator.grassPerChunk = 5;
        theBiomeDecorator.flowersPerChunk = 0;
        
        this.spawnableCreatureList.clear();
        
        this.minHeight = 0.2F;
        this.maxHeight = 0.4F;
        this.temperature = 1.6F;
        this.rainfall = 0.1F;
        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
        
        plants.add(HighlandsGenerators.thornbush);
        plants.add(HighlandsGenerators.mcBluet);
        plants.add(HighlandsGenerators.duneGrass);
    }

    
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
        return (par1Random.nextInt(3) == 0 ? new WorldGenSavannaTree(false) : HighlandsGenerators.shrub2Gen);
    }
    
    public void decorate(World world, Random random, BlockPos pos)
    {
        //genStandardOre(6, HighlandsGenerators.sandInDirt, 64, 120, world, random, pos);
    	
        super.decorate(world, random, pos);
        
        genStandardOre(5, HighlandsGenerators.hlsand, 0, 72, world, random, pos);
        genStandardOre(theBiomeDecorator.chunkProviderSettings.goldCount/2, theBiomeDecorator.goldGen, theBiomeDecorator.chunkProviderSettings.goldMinHeight, theBiomeDecorator.chunkProviderSettings.goldMaxHeight, world, random, pos);
    }
    
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.grass.getDefaultState();
        this.fillerBlock = Blocks.dirt.getDefaultState();

        if ((whatisthis < -1.0D || whatisthis > 2.0D) && this.terrainInt4 == this.terrainInt3)
        {
            this.topBlock = Blocks.sand.getDefaultState();
            this.fillerBlock = Blocks.sand.getDefaultState();
        }
        else if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.sand.getStateFromMeta(1);
            this.fillerBlock = Blocks.sand.getStateFromMeta(1);
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
	    
}
