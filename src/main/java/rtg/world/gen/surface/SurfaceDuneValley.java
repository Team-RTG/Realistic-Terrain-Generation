package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

import java.util.Random;

public class SurfaceDuneValley extends SurfaceBase
{
	private float valley;
	private boolean dirt;
	private boolean mix;
	
	public SurfaceDuneValley(BiomeConfig config, IBlockState top, IBlockState fill, float valleySize, boolean d, boolean m)
	{
	    super(config, top, fill);
		
		valley = valleySize;
		dirt = d;
		mix = m;
	}
	
	@Override
	public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
    	float h = (simplex.noise2(i / valley, j / valley) + 0.25f) * 65f;
    	h = h < 1f ? 1f : h;
		float m = simplex.noise2(i / 12f, j / 12f);
		boolean sand = false;
		
    	Block b;
		for(int k = 255; k > -1; k--)
		{
			b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;
            	
            	if(depth == 0)
        		{
                	if(k > 90f + simplex.noise2(i / 24f, j / 24f) * 10f - h || (m < -0.28f && mix))
        			{
    					primer.setBlockState((y * 16 + x) * 256 + k, Blocks.sand.getDefaultState());
    					//base[x * 16 + y] = RealisticBiomeVanillaBase.vanillaDesert;
    					sand = true;
        			}
        			else if(dirt && m < 0.22f || k < 62)
        			{
    					primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getStateFromMeta(1));
        			}
        			else
        			{
    					primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
        			}
        		}
        		else if(depth < 6)
        		{
        			if(sand)
        			{
        				if(depth < 4)
        				{
            				primer.setBlockState((y * 16 + x) * 256 + k, Blocks.sand.getDefaultState());
        				}
        				else
        				{
            				primer.setBlockState((y * 16 + x) * 256 + k, Blocks.sandstone.getDefaultState());
        				}
        			}
        			else
        			{
        				primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
        			}
        		}
            }
		}
	}
}
