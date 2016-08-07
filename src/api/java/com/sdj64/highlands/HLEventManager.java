package com.sdj64.highlands;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.sdj64.highlands.biome.ChunkProviderHighlands;
import com.sdj64.highlands.generator.HighlandsGenerators;
import com.sdj64.highlands.generator.layer.GenLayerHighlands;

public class HLEventManager {
	
	@SubscribeEvent
	public void onDecorateTree(Decorate e)
	{
		if(e.type == Decorate.EventType.TREE){
			
			BiomeGenBase biome = e.world.getBiomeGenForCoords(e.pos);
			
			/*
			if(biome.equals(BiomeGenBase.forest) && e.rand.nextInt(18) == 1){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.ashGen.generate(e.world, e.rand, e.pos);
			}
			*/
			
			/*
			if(biome.equals(BiomeGenBase.forest) && e.rand.nextInt(20) == 1){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.greatOakGen.generate(e.world, e.rand, e.pos);
			}
			if(biome.equals(BiomeGenBase.forest) && e.rand.nextInt(7) == 1){
				e.setResult(Event.Result.DENY);
				new WorldGenBigTree(false).generate(e.world, e.rand, e.pos);
			}
			
			if(biome.equals(BiomeGenBase.birchForest) && e.rand.nextInt(12) == 1){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.poplarGen.generate(e.world, e.rand, e.pos);
			}
			*/
			if(biome.equals(BiomeGenBase.savanna) && e.rand.nextInt(3) != 1){
				e.setResult(Event.Result.DENY);
			}
		}
	}
	
	@SubscribeEvent
	public void onGenLayerInitiate(InitBiomeGens e)
	{
		e.newBiomeGens = GenLayerHighlands.initializeAllBiomeGenerators(e.seed, e.worldType, "");
	}
	
	@SubscribeEvent
	public void onBiomeSize(BiomeSize e)
	{
		if(e.worldType.equals(HighlandsMod.worldTypeHighlands)){
			e.newSize = HighlandsSettings.HighlandsBiomeSizeDefault;
		}
		if(e.worldType.equals(HighlandsMod.worldTypeHighlandsLB)){
			e.newSize = HighlandsSettings.HighlandsBiomeSizeLB;
		}
	}
	
	//used to grow Great Oak from a 2x2 oak sapling square
	@SubscribeEvent
	public void onSapling(SaplingGrowTreeEvent e)
	{
		boolean flagSquare = false;
		BlockPos treeGrowPos = e.pos;
		
		//great oak grows on the northwest corner
		if(((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos, BlockPlanks.EnumType.OAK)){
			//Sapling is in southwest corner
			if(((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.north(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.east(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.north().east(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				treeGrowPos = treeGrowPos.north();
			}
			//Sapling is in southeast corner
			if(((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.north(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.west(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.north().west(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				treeGrowPos = treeGrowPos.west().north();
			}
			//Sapling is in northwest corner
			if(((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.south(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.east(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.south().east(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				//treeGrowPos is good
			}
			//Sapling is in northeast corner
			if(((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.south(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.west(), BlockPlanks.EnumType.OAK) &&
					((BlockSapling)Blocks.sapling).isTypeAt(e.world, e.pos.south().west(), BlockPlanks.EnumType.OAK)){
				flagSquare = true;
				treeGrowPos = treeGrowPos.west();
			}
			
			if(flagSquare){
				e.setResult(Event.Result.DENY);
				HighlandsGenerators.greatOakSapling.generate(e.world, e.rand, treeGrowPos);
			}
			
		}
	}
	
	
}




