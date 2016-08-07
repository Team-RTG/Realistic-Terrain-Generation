package com.sdj64.highlands.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraftforge.fml.common.IWorldGenerator;

import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.biome.HighlandsBiomes;

public class GenerateRiverRapids implements IWorldGenerator
{
	
	public static final int SEA_LEVEL = 64;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		/*
		if(world.provider.getDimensionId() == 0){
			
			
			for(int i = 0; i < 16; i++){
				for(int k = 0; k < 16; k++){
					int locX = chunkX*16 + i;
					int locZ = chunkZ*16 + k;
					
					BlockPos pos = new BlockPos(locX, 1, locZ);
					BiomeGenBase biome = world.getBiomeGenForCoords(pos);
					
					if(biome.equals(BiomeGenBase.river)){
						BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos);
						
						BiomeGenBase biome2 = world.getBiomeGenForCoords(pos2.north(8));
						if(biome2.equals(BiomeGenBase.river))  biome2 = world.getBiomeGenForCoords(pos2.east(8));
						BiomeGenBase biome3 = world.getBiomeGenForCoords(pos2.south(8));
						if(biome3.equals(BiomeGenBase.river))  biome3 = world.getBiomeGenForCoords(pos2.west(8));
						
						int y = getNewSeaLevel(biome2, biome3);
						for(int j = 0; j < y-pos2.getY(); j++){
							world.setBlockState(pos2.up(j), Blocks.water.getDefaultState());
							Blocks.water.updateTick(world, pos2.up(j), Blocks.water.getDefaultState(), random);
						}
						
					}
				}
			}
		}
		*/
	}

	
	public int getNewSeaLevel(BiomeGenBase b1, BiomeGenBase b2){
		double b1average = b1.minHeight+ b1.maxHeight/2;
		double b2average = b2.minHeight+ b2.maxHeight/2;
		
		if(b2average + b1average > 2)	
			return (int)(SEA_LEVEL + b1average + b2average);
		else return SEA_LEVEL;
	}
}
