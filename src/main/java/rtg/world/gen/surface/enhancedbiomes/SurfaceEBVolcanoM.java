package rtg.world.gen.surface.enhancedbiomes;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBVolcanoM extends SurfaceEBBase
{
	private boolean beach;
	private Block beachBlock;
	private float min;
	
	public SurfaceEBVolcanoM(Block top, Block fill, boolean genBeach, Block genBeachBlock, float minCliff) 
	{
		super(top, fill);
		beach = genBeach;
		beachBlock = genBeachBlock;
		min = minCliff;
	}

	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		
	}
}
