package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.HighlandsMod;
import com.sdj64.highlands.generator.HighlandsGenerators;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDunes extends BiomeGenBaseHighlands
{

	public BiomeGenDunes(int par1)
    {
		super(par1);
		
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 0;
        theBiomeDecorator.flowersPerChunk = 0;
    	
	    this.minHeight = -0.15F;
	    this.maxHeight = 0.5F;
        this.temperature = 0.95F;
        this.rainfall = 0.4F;
        
        this.topBlock = Blocks.sand.getDefaultState();
        this.fillerBlock = Blocks.sand.getDefaultState();
        
        this.plants.add(HighlandsGenerators.duneGrass);
        this.plants.add(HighlandsGenerators.empty);
    }
	
	public WorldGenAbstractTree genBigTreeChance(Random random)
    {
		return HighlandsGenerators.palmGen;
    }
}
