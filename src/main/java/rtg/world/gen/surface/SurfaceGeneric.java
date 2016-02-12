package rtg.world.gen.surface;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceGeneric extends SurfaceBase
{

	public SurfaceGeneric(BiomeConfig config, Block top, Block filler)
	{
		super(config, top, filler);
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
	    
		for(int k = 255; k > -1; k--)
		{
			Block b = blocks[(y * 16 + x) * 256 + k];
			
            if (b == Blocks.air)
            {
            	depth = -1;
            }
            else if (b == Blocks.stone)
            {
            	depth++;

        		if (depth == 0 && k > 61)
        		{
        			blocks[(y * 16 + x) * 256 + k] = topBlock;
        		    metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
        		}
        		else if (depth < 4)
        		{
        			blocks[(y * 16 + x) * 256 + k] = fillerBlock;
        		    metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
        		}
            }
		}
	}
}
