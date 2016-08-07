package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraftforge.fml.common.IWorldGenerator;

import com.sdj64.highlands.HighlandsSettings;
import com.sdj64.highlands.biome.HighlandsBiomes;

public class GenerateTrees implements IWorldGenerator
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
			
			
			if(biome.equals(HighlandsBiomes.meadow) && random.nextInt(32) == 1){
				HighlandsGenerators.poplarGen.generate(world, random, pos2);
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				HighlandsGenerators.poplarGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
			}
			
			if (HighlandsSettings.vanillaBiomeChanges){
				
				if(biome.biomeID == BiomeGenBase.desert.biomeID+128 && random.nextInt(4) == 1){
					HighlandsGenerators.palmGen.generate(world, random, pos2);
				}
				if(biome.equals(BiomeGenBase.savanna) && random.nextInt(45) == 1){
					HighlandsGenerators.aspenGen.generate(world, random, pos2);
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
					HighlandsGenerators.aspenGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				}
				if(biome.equals(BiomeGenBase.jungleEdge) || biome.equals(BiomeGenBase.jungle)){
					HighlandsGenerators.eucalyptusGen.generate(world, random, pos2);
					HighlandsGenerators.eucalyptusGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
					HighlandsGenerators.eucalyptusGen.generate(world, random, pos2.east(random.nextInt(16)-7).north(random.nextInt(16)-7));
				}
				
				if(biome.equals(BiomeGenBase.forest) && random.nextInt(12) == 1){
					HighlandsGenerators.greatOakGen.generate(world, random, pos2);
				}
				else if(biome.equals(BiomeGenBase.forest) && random.nextInt(4) == 1){
					new WorldGenBigTree(false).generate(world, random, pos2);
				}
				
				
				if((biome.equals(BiomeGenBase.taiga) || biome.equals(BiomeGenBase.coldTaiga)) && random.nextInt(25) == 1){
					new WorldGenMegaPineTree(false, true).generate(world, random, pos2);
				}
				if(biome.equals(BiomeGenBase.taiga) || biome.equals(BiomeGenBase.coldTaiga) ){//&& random.nextInt(2) == 1){
					HighlandsGenerators.firGen.generate(world, random, pos2);
				}
				
				if(biome.equals(BiomeGenBase.swampland) && random.nextInt(3) == 1){
					HighlandsGenerators.deadTreeGen.generate(world, random, pos2);
				}
				
				if(biome.equals(BiomeGenBase.icePlains) && random.nextInt(6) == 1){
					HighlandsGenerators.shrubGen.generate(world, random, pos2);
				}
			}
			
			//System.out.println("Generating Trees!");
			
			//MoreTreesMod.ashTreeGen.generate(world, random, pos2);
		}
	}

}
