package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraftforge.fml.common.IWorldGenerator;

import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.biome.BiomeGenBaseHighlands;
import com.sdj64.highlands.biome.HighlandsBiomes;

public class GeneratePlants implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimensionId() == 0){
			int locX = chunkX*16 + random.nextInt(16);
			int locZ = chunkZ*16 + random.nextInt(16);
			BlockPos pos = new BlockPos(locX, 1, locZ);
			BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos);
			
			BiomeGenBase biome = world.getBiomeGenForCoords(pos);
			
			if(biome instanceof BiomeGenBaseHighlands){
				
				
				int l = ((BiomeGenBaseHighlands) biome).plants.size();
				if(l > 0){
					((BiomeGenBaseHighlands) biome).plants.get(random.nextInt(l)).generate(world, random, pos2);
				}
			}
			
			if (HighlandsSettings.vanillaBiomeChanges){
				if(biome.equals(BiomeGenBase.swampland) && random.nextInt(32) == 1){
					HighlandsGenerators.cattail.generate(world, random, pos2);
				}
				if(biome.equals(BiomeGenBase.mesaPlateau_F) && random.nextInt(45) == 1){
					HighlandsGenerators.mcOTulip.generate(world, random, pos2);
				}
			
			}
		}
	}

}
