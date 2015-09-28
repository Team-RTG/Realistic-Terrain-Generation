package rtg.world.gen.surface.vanilla;

import java.util.Random;





import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDesert;
import rtg.world.gen.surface.SurfaceBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceVanillaSavannaPlateau extends SurfaceBase
{
	private float valley;
	private boolean dirt;
	private boolean mix;
	
	public SurfaceVanillaSavannaPlateau(Block top, Block fill, float valleySize, boolean d, boolean m) 
	{
		super(top, fill);
		
		valley = valleySize;
		dirt = d;
		mix = m;
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
    	float h = (perlin.noise2(i / valley, j / valley) + 0.25f) * 65f;
    	h = h < 1f ? 1f : h;
		float m = perlin.noise2(i / 12f, j / 12f);
		boolean sand = false;
		
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
            	
            	if(depth == 0)
        		{
                	if(k > 90f + perlin.noise2(i / 24f, j / 24f) * 10f - h || (m < -0.28f && mix))
        			{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
    					base[x * 16 + y] = BiomeGenBase.savannaPlateau;
    					sand = true;
        			}
        			else if(dirt && m < 0.22f || k < 62)
        			{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
    					metadata[(y * 16 + x) * 256 + k] = 1;
        			}
        			else
        			{
    					blocks[(y * 16 + x) * 256 + k] = topBlock;
        			}
        		}
        		else if(depth < 6)
        		{
        			if(sand)
        			{
        				if(depth < 4)
        				{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
        				}
        				else
        				{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
        				}
        			}
        			else
        			{
        				blocks[(y * 16 + x) * 256 + k] = fillerBlock;
        			}
        		}
            }
		}
	}
}
