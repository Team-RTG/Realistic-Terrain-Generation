package rtg.event;

import java.util.Random;

//import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import com.google.common.primitives.Ints;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldHelper
{
	public static void setBlockIfEmpty(int par1, int par2, int par3, Block par4, int par5, int par6, World world) {
		if(world.isAirBlock(par1, par2, par3)) {
			world.setBlock(par1, par2, par3, par4, par5, par6);
		}
	}

	public static void setBlockIfNotStone(int par1, int par2, int par3, Block par4, int par5, int par6, World world) {
		if(!world.getBlock(par1, par2, par3).isReplaceableOreGen(world, par1, par2, par3, Blocks.stone)) {
			world.setBlock(par1, par2, par3, par4, par5, par6);
		}
	}

	public static void setBlockUnderSky(int par1, int par2, int par3, Block par4, int par5, int par6, World world) {
		if(world.canBlockSeeTheSky(par1, par2, par3)) {
			world.setBlock(par1, par2, par3, par4, par5, par6);
		}
	}

	/**
	 * Finds the highest block on the x, z coordinate that is stone and returns its y coord. Args x, z
	 */
	public static int getTopStoneBlock(int par1, int par2, World world) {
		Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
		int x = par1;
		int z = par2;
		int k = chunk.getTopFilledSegment() + 15;
		par1 &= 15;

		for(par2 &= 15; k > 0; --k) {
			Block l = chunk.getBlock(par1, k, par2);

			if(l.isReplaceableOreGen(world, par1, k, par2, Blocks.stone)) {
				return k + 1;
			}
		}

		return -1;
	}

	/**
	 * Finds the highest block on the x, z coordinate that is stone and returns its y coord. Args x, z
	 */
	public static int getTopSolidBlock(int par1, int par2, World world) {
		Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
		int posY = world.getTopSolidOrLiquidBlock(par1, par2);

		while(world.getBlock(par1, posY, par2).getMaterial() instanceof MaterialLiquid || world.getBlock(par1, posY, par2).getMaterial() == Material.air) {
			posY--;
		}

		return posY + 1;

		/*Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
		int x = par1;
		int z = par2;
		int k = chunk.getTopFilledSegment() + 15;
		par1 &= 15;

		for (par2 &= 15; k > 0; --k)
		{
		    int l = chunk.getBlockID(par1, k, par2);

		    if (l == Block.stone.blockID)
		    {
		        return k + 1;
		    }
		}*/
	}
}