package rtg.world.gen.surface.vanilla;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceVanillaDesert extends SurfaceBase
{
	public SurfaceVanillaDesert(BiomeConfig config, Block top, Block fill) 
	{
	    super(config, top, (byte)0, fill, (byte)0);
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		boolean water = false;
		boolean riverPaint = false;
		boolean grass = false;
		
		if(river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.1f) > 0.86f)
		{
			riverPaint = true;
			
			if(simplex.noise2(i / 12f, j / 12f) > 0.25f)
			{
				grass = true;
			}
		}
		
		Block b;
		for(int k = 255; k > -1; k--)
		{
			b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

            	if(riverPaint)
            	{
            		if(grass && depth < 4)
            		{
    	        		blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
            		}
            		else if(depth == 0)
            		{
    	        		blocks[(y * 16 + x) * 256 + k] = rand.nextInt(2) == 0 ? Blocks.sand : Blocks.sandstone;
            		}
            	}
        		else if(depth > -1 && depth < 9)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
            		if(depth == 0 && k > 61 && k < 254);
        		}
            }

            
		}
	}
}
