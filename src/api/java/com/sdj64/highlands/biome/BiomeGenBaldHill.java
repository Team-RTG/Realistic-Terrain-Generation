package com.sdj64.highlands.biome;

import java.util.Random;

import com.sdj64.highlands.generator.HighlandsGenerators;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenBaldHill extends BiomeGenBaseHighlands
{

	public BiomeGenBaldHill(int par1){
		super(par1);
		
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 4;
        theBiomeDecorator.flowersPerChunk = 3;
        
        this.minHeight = 1.5F;
        this.maxHeight = 0.4F;
        this.temperature = 0.5F;
        this.rainfall = 0.7F;
        
        plants.add(HighlandsGenerators.mcBluet);
        plants.add(HighlandsGenerators.mcAllium);
        
    }

	public WorldGenAbstractTree genBigTreeChance(Random random)
    {
		return (random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
    }
	    
}

