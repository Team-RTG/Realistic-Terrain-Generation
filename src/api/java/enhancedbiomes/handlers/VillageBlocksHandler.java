package enhancedbiomes.handlers;

import java.util.Random;

import com.google.common.eventbus.Subscribe;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.village.VillagePieceSelection;
import enhancedbiomes.world.biome.EnhancedBiomesRock;
import enhancedbiomes.world.biomestats.BiomeCategorisation;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.BiomeEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import static net.minecraft.init.Blocks.*;
import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;

public class VillageBlocksHandler 
{
	private Random rand = new Random();
	
	@SubscribeEvent
	public void setVillageBlock(BiomeEvent.GetVillageBlockID event)
	{
		BiomeGenBase b = event.biome;
		if(b == null) return;
		int bi = b.biomeID;
		Block rock = EnhancedBiomesMod.getDominantStone(bi);
		int rmeta = EnhancedBiomesMod.getDominantStoneMeta(bi);
		Block wood = EnhancedBiomesMod.woodList[bi];
		if(wood == null) wood = Blocks.planks;
		int wmeta = EnhancedBiomesMod.woodMetaList[bi];
		Block a = event.original;
		int ameta = event.type;
		if(EnhancedBiomesMod.isStoneVillageList[bi])
		{
			if(b == EnhancedBiomesRock.biomeBasin || b == EnhancedBiomesRock.biomeRockHills)
			{
				if(a == log || a == log2) event.replacement = stonebrick;
				else if(a == planks) event.replacement = cobblestone;
				else if(a == wooden_slab) event.replacement = stone_slab;
				else if(a == acacia_stairs	|| a == birch_stairs	|| a == dark_oak_stairs || 
						a == jungle_stairs	|| a == oak_stairs 		|| a == spruce_stairs) 
					event.replacement = stone_stairs;
			}
			else
			{
				if(a == stone) event.replacement = stoneEB;
				else if(a == cobblestone) event.replacement = stoneCobbleEB;
				else if(a == stonebrick) event.replacement = stoneBrickEB;
				else if(a == stone_slab) 
				{
					if(ameta % 8 == 0) event.replacement = slabS;
					else if(ameta % 8 == 3) event.replacement = slabSC;
					else if(ameta % 8 == 5) event.replacement = slabSB;
				}
				else if(a == double_stone_slab)
				{
					if(ameta == 0) event.replacement = doubleSlabS;
					else if(ameta == 3) event.replacement = doubleSlabSC;
					else if(ameta == 5) event.replacement = doubleSlabSB;
				}
				else if(a == stone_stairs) event.replacement = stairsSEB[rmeta];
				else if(a == stone_brick_stairs) event.replacement = stairsSEB[rmeta + 6];

				else if(a == log || a == log2) event.replacement = stoneBrickEB;
				else if(a == planks) event.replacement = stoneBrickEB;
				else if(a == wooden_slab) event.replacement = slabSC;
				else if(a == acacia_stairs	|| a == birch_stairs	|| a == dark_oak_stairs || 
						a == jungle_stairs	|| a == oak_stairs 		|| a == spruce_stairs) 
					event.replacement = stairsSEB[rmeta + 6];
			}
		}
		else
		{
			if(rock == stoneEB && rmeta == 2)
			{
				if(a == stone) event.replacement = stoneEB;
				else if(a == cobblestone) event.replacement = stoneBrickEB;
				else if(a == stonebrick) event.replacement = stoneBrickEB;
				else if(a == stone_slab) 
				{
					if(ameta % 8 == 0) event.replacement = slabS;
					else if(ameta % 8 == 3) event.replacement = slabSB;
					else if(ameta % 8 == 5) event.replacement = slabSB;
				}
				else if(a == double_stone_slab)
				{
					if(ameta == 0) event.replacement = doubleSlabS;
					else if(ameta == 3) event.replacement = doubleSlabSB;
					else if(ameta == 5) event.replacement = doubleSlabSB;
				}
				else if(a == stone_stairs) event.replacement = stairsSEB[rmeta + 6];
				else if(a == stone_brick_stairs) event.replacement = stairsSEB[rmeta + 6];
			}
			else if(rock == stoneEB)
			{
				if(a == stone) event.replacement = stoneEB;
				else if(a == cobblestone) event.replacement = stoneCobbleEB;
				else if(a == stonebrick) event.replacement = stoneBrickEB;
				else if(a == stone_slab) 
				{
					if(ameta % 8 == 0) event.replacement = slabS;
					else if(ameta % 8 == 3) event.replacement = slabSC;
					else if(ameta % 8 == 5) event.replacement = slabSB;
				}
				else if(a == double_stone_slab)
				{
					if(ameta == 0) event.replacement = doubleSlabS;
					else if(ameta == 3) event.replacement = doubleSlabSC;
					else if(ameta == 5) event.replacement = doubleSlabSB;
				}
				else if(a == stone_stairs) event.replacement = stairsSEB[rmeta];
				else if(a == stone_brick_stairs) event.replacement = stairsSEB[rmeta + 6];
			}

			if(wood == planksEB)
			{
				if(a == log || a == log2)
				{
					if(wmeta < 4) event.replacement = logOak;
					else if(wmeta < 8) event.replacement = logSpruce;
					else if(wmeta < 12) event.replacement = logBirch;
					else event.replacement = logJungle;				
				}
				else if(a == planks) event.replacement = planksEB;
				else if(a == wooden_slab)
				{
					if(wmeta < 8) event.replacement = slab1;
					else event.replacement = slab2;
				}
				else if(a == acacia_stairs	|| a == birch_stairs	|| a == dark_oak_stairs || 
						a == jungle_stairs	|| a == oak_stairs 		|| a == spruce_stairs) 
					event.replacement = stairsEB[wmeta];
			}
			else if(wood == planks && wmeta <= 3)
			{
				if(a == log2) event.replacement = log;
				else if(a == acacia_stairs	|| a == birch_stairs	|| a == dark_oak_stairs || 
						a == jungle_stairs	|| a == oak_stairs 		|| a == spruce_stairs)
				{
					if(wmeta == 0) event.replacement = oak_stairs;
					else if(wmeta == 1) event.replacement = spruce_stairs;
					else if(wmeta == 2) event.replacement = birch_stairs;
					else event.replacement = jungle_stairs;
				}
			}
			else if(wood == planks && wmeta > 3)
			{
				if(a == log) event.replacement = log2;
				else if(a == acacia_stairs	|| a == birch_stairs	|| a == dark_oak_stairs || 
						a == jungle_stairs	|| a == oak_stairs 		|| a == spruce_stairs)
				{
					if(wmeta == 4) event.replacement = acacia_stairs;
					else event.replacement = dark_oak_stairs;
				}
			}
		}
		
		if(a == gravel) {
			if(BiomeCategorisation.isOriental(b)) {
				int pathType = rand.nextInt(5);
				if(pathType <= 1) {
					event.replacement = cobblestone;
				}
			}
		}

		if(event.replacement != null) 
		{
			event.setResult(Result.DENY);
		}
	}

	@SubscribeEvent
	public void setVillageMeta(BiomeEvent.GetVillageBlockMeta event)
	{
		BiomeGenBase b = event.biome;
		if(b == null) return;
		int bi = b.biomeID;
		Block rock = EnhancedBiomesMod.getDominantStone(bi);
		int rmeta = EnhancedBiomesMod.getDominantStoneMeta(bi);
		Block wood = EnhancedBiomesMod.woodList[bi];
		if(wood == null) wood = Blocks.planks;
		int wmeta = EnhancedBiomesMod.woodMetaList[bi];
		Block a = event.original;
		int ameta = event.type;
		event.replacement = event.type;
		if(EnhancedBiomesMod.isStoneVillageList[bi])
		{
			if(b == EnhancedBiomesRock.biomeBasin || b == EnhancedBiomesRock.biomeRockHills)
			{
				if(a == log || a == log2) event.replacement = 0;
				else if(a == planks) event.replacement = 0;
				else if(a == wooden_slab) event.replacement = 3 + (ameta >= 8 ? 8 : 0);
			}
			else
			{
				if(a == stone) event.replacement = rmeta;
				else if(a == cobblestone) event.replacement = rmeta;
				else if(a == stonebrick) event.replacement = rmeta;
				else if(a == stone_slab) 
				{
					if(ameta % 8 == 0) event.replacement = rmeta + (ameta >= 8 ? 8 : 0);
					else if(ameta % 8 == 3) event.replacement = rmeta + (ameta >= 8 ? 8 : 0);
					else if(ameta % 8 == 5) event.replacement = rmeta + (ameta >= 8 ? 8 : 0);
				}
				else if(a == double_stone_slab)
				{
					if(ameta == 0) event.replacement = rmeta;
					else if(ameta == 3) event.replacement = rmeta;
					else if(ameta == 5) event.replacement = rmeta;
				}

				else if(a == log || a == log2) event.replacement = rmeta;
				else if(a == planks) event.replacement = rmeta;
				else if(a == wooden_slab) event.replacement = rmeta + (ameta >= 8 ? 8 : 0);
			}
		}
		else
		{
			if(rock == stoneEB)
			{
				if(a == stone) event.replacement = rmeta;
				else if(a == cobblestone) event.replacement = rmeta;
				else if(a == stonebrick) event.replacement = rmeta;
				else if(a == stone_slab) 
				{
					if(ameta % 8 == 0) event.replacement = rmeta + (ameta >= 8 ? 8 : 0);
					else if(ameta % 8 == 3) event.replacement = rmeta + (ameta >= 8 ? 8 : 0);
					else if(ameta % 8 == 5) event.replacement = rmeta + (ameta >= 8 ? 8 : 0);
				}
				else if(a == double_stone_slab)
				{
					if(ameta == 0) event.replacement = rmeta;
					else if(ameta == 3) event.replacement = rmeta;
					else if(ameta == 5) event.replacement = rmeta;
				}
			}

			if(wood == planksEB)
			{
				if(a == log || a == log2) event.replacement = wmeta % 4 + (ameta / 4 * 4);
				else if(a == planks) event.replacement = wmeta;
				else if(a == wooden_slab) event.replacement = wmeta % 8 + (ameta >= 8 ? 8 : 0);
			}
			else if(wood == planks)
			{
				if(a == log || a == log2) event.replacement = wmeta % 4 + (ameta / 4 * 4);
				else if(a == planks) event.replacement = wmeta;
				else if(a == wooden_slab) event.replacement = wmeta % 8 + (ameta >= 8 ? 8 : 0);
			}
		}		

		if(event.replacement != event.type) 
		{
			event.setResult(Result.DENY);
		}
	}
}
