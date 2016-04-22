package rtg.world.gen.surface.highlands;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.CliffCalculator;

public class SurfaceHLDunes extends SurfaceBase
{
	public SurfaceHLDunes(BiomeConfig config, Block top, Block fill) 
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
                    if (depth > 4)  {
                        blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
                    } else {
                        float c = CliffCalculator.calc(x, y, noise);
                        if (c < 0.8) {
                           blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
                        } else {

                           if (c>4.4+simplex.octave(4).noise2((float)x/20f, (float)y/20f)*2f){
                               blocks[(y * 16 + x) * 256 + k] = getShadowDesertBlock(world, i, j, x, y, k);
                               metadata[(y * 16 + x) * 256 + k] = getShadowDesertMeta(world, i, j, x, y, k);
                           } else if (c > 1.4) {
                               blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
                               metadata[(y * 16 + x) * 256 + k] = (byte)2;
                           } else {
                                blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
                           }
                        }
                    }
        		}
            }

            
		}
	}
}
