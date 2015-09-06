package rtg.biomes.realistic.coast;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.SurfaceBase;
import rtg.surface.SurfaceGrassland;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;
import rtg.util.SnowheightCalculator;

public class RealisticBiomeCoastIce extends RealisticBiomeBase
{
	private SurfaceBase surface;
	
	public RealisticBiomeCoastIce() 
	{
		super(0, RTGBiomes.baseSnowDesert);
		
		surface = new SurfaceGrassland(Blocks.packed_ice, Blocks.packed_ice, Blocks.packed_ice, Blocks.ice);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
    }
	
	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {		
		river = river > 0.5f ? 1f : river * 2f;

		float start = (perlin.noise2(x / 90f, y / 90f) * 1f) + (perlin.noise2(x / 40f, y / 40f) * 0.15f) + (perlin.noise2(x / 9f, y / 9f) * 0.07f);
		
		float h = 0f;
		float c = 0f;
		if(ocean + start > 1.4f)
		{
			c = ocean + start > 1.5f ? 0.1f : (ocean + start) - 1.4f;
			c *= 250 + perlin.noise2(x / 50f, y / 50f) * 25f;
		}
		
		if(ocean < 1.3f)
		{
			float st = (1.3f - ocean) * 20f;
			st = st > 1f ? 1f : st;
			
			h += perlin.noise2(x / 12f, y / 12f);
			h += perlin.noise2(x / 20f, y / 20f) * 2;
		}
		
		return 55f + h + (c * river);
    }
	
	@Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {		
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.4f ? true : false;
		int type = 0;
		
		for(int k = 255; k > -1; k--)
		{
			Block b = blocks[(y * 16 + x) * 256 + k];
	        if(b == Blocks.air)
	        {
	        	depth = -1;
	        }
	        else if(b == Blocks.stone)
	        {
	        	depth++;
	
	        	if(cliff)
	        	{
	        		if(depth == 0)
	        		{
	        			type = perlin.noise2(i / 5f, j / 5f) > 0f ? 1 : 0;
	        		}
	        		
	        		if (depth < 10)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = type == 0 ? Blocks.packed_ice : Blocks.ice;
	        		}
	        	}
	        	else
	        	{
	        		if(depth < 5 && k > 61)
	        		{        			
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
	            		if(depth == 0 && k > 61 && k < 254)
	            		{
	            			SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
	            		}
	        		}
	        		else if(depth < 3)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
	        		}
	        	}
	        }
		}
    }
}
