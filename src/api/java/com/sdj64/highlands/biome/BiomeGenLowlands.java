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
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenLowlands extends BiomeGenBaseHighlands
{

	private int terrainInt1;
    private int terrainInt2;
    private int terrainInt3;
    private int terrainInt4;
	
	public BiomeGenLowlands(int par1)
    {
        super(par1);
        
        theBiomeDecorator.treesPerChunk = 2;
        theBiomeDecorator.grassPerChunk = 4;
        theBiomeDecorator.flowersPerChunk = 0;

        theBiomeDecorator.generateLakes = false;
        
        minHeight = -0.1F;
        maxHeight = 0.2F;
        temperature = 0.5F;
        rainfall = 1.2F;
        
        this.terrainInt1 = 0;
        this.terrainInt2 = 1;
        this.terrainInt3 = 2;
        this.terrainInt4 = this.terrainInt1;
        
        plants.add(HighlandsGenerators.blueSwampFlower);
        plants.add(HighlandsGenerators.mcDaisy);
        plants.add(HighlandsGenerators.cattail);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenAbstractTree genBigTreeChance(Random par1Random)
    {
        return (par1Random.nextInt(8) == 0 ?
        		HighlandsGenerators.shrub2Gen : par1Random.nextInt(4) != 0 ?
        		this.worldGeneratorTrees : HighlandsGenerators.firGen);
    }

    public void decorate(World world, Random random, BlockPos pos)
    {
        super.decorate(world, random, pos);
        
        genStandardOre(10, HighlandsGenerators.hlwater, 10, 64, world, random, pos);
        genStandardOre(theBiomeDecorator.chunkProviderSettings.diamondCount/2, theBiomeDecorator.diamondGen, theBiomeDecorator.chunkProviderSettings.diamondMinHeight, theBiomeDecorator.chunkProviderSettings.diamondMaxHeight, world, random, pos);
    }
    
    public void genTerrainBlocks(World worldIn, Random random, ChunkPrimer primer, int x, int z, double whatisthis)
    {
        this.topBlock = Blocks.grass.getDefaultState();
        this.fillerBlock = Blocks.dirt.getDefaultState();

        if (whatisthis > 1.0D && this.terrainInt4 != this.terrainInt2)
        {
            this.topBlock = Blocks.stone.getDefaultState();
            this.fillerBlock = Blocks.stone.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, random, primer, x, z, whatisthis);
    }
}
