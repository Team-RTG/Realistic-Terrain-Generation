package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

import java.util.Random;

public class SurfaceMountainPolar extends SurfaceBase
{
	private boolean beach;
	private Block beachBlock;
	private float min;
	
	public SurfaceMountainPolar(BiomeConfig config, Block top, Block fill, boolean genBeach, Block genBeachBlock, float minCliff) 
	{
	    super(config, top, (byte)0, fill, (byte)0);
		beach = genBeach;
		beachBlock = genBeachBlock;
		min = minCliff;
	}

	@Override
	public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		
	}
}