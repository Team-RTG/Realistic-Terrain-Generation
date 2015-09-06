package rtg.data;

import java.util.Random;

import rtg.RTG;
import rtg.deco.trees.DecoSavannah;
import rtg.deco.trees.DecoSmallPine;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TreeReplacement 
{
	/*
	 * 
	 * 
	 * 
	 * Missing a check for air and its extremely buggy :(
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	@SubscribeEvent
	public boolean saplingGrowTree(SaplingGrowTreeEvent e) 
	{
		System.out.println("Zwieber nie gaan stele nu he");
		if(e.world.getWorldInfo().getTerrainType() == RTG.worldtype)
		{
			int type = e.world.getBlockMetadata(e.x, e.y, e.z);
			System.out.println("tiepe: " + type);
			if(type == 9)
			{
				e.world.setBlock(e.x, e.y, e.z, Blocks.air);
				(new DecoSmallPine(3 + e.rand.nextInt(3), 5 + e.rand.nextInt(5))).generate(e.world, e.rand, e.x, e.y, e.z);
				return true;
			}			
			if(type == 12)
			{
				e.world.setBlock(e.x, e.y, e.z, Blocks.air);
				(new DecoSavannah(1)).generate(e.world, e.rand, e.x, e.y, e.z);
				return true;
			}
		}
		return false;
	}
}
