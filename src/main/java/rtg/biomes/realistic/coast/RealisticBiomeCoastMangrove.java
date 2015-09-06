package rtg.biomes.realistic.coast;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.deco.DecoBlob;
import rtg.deco.DecoGrass;
import rtg.deco.DecoWaterGrass;
import rtg.deco.trees.DecoMangrove;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;

public class RealisticBiomeCoastMangrove extends RealisticBiomeBase
{
	public RealisticBiomeCoastMangrove() 
	{
		super(0, RTGBiomes.baseOceanOasis);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		for (int l = 0; l < 2f * strength; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 68)
			{
		    	(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		for(int a = 0; a < 5f * strength; a++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
	
			WorldGenerator worldgenerator = new DecoMangrove(Blocks.log, 0, Blocks.leaves, 0, 1 + rand.nextInt(4), 4 + rand.nextInt(2), 5f, 2, 0.32f, 0.14f);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
		
		for(int l14 = 0; l14 < 9f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = 64 + rand.nextInt(64);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new DecoWaterGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
		}
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		river = river > 0.5f ? 1f : river * 2f;
		
		float h = ocean < 0.5f ? ocean * 14f : 7f;
		
		if(ocean < 1.9f)
		{
			float st = (1.9f - ocean) * 20f;
			st = st > 1f ? 1f : st;
			
			h += perlin.noise2(x / 12f, y / 12f) * 1.5f;
			h += perlin.noise2(x / 20f, y / 20f) * 3f;
		}
		
		return 55f + h;
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.3f ? true : false;
		boolean sand = false;
		
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
            		if(depth > -1 && depth < 2)
            		{
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else if (depth < 10)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
            		}
            	}
            	else
            	{
	        		if(depth == 0)
	        		{
	        			if(k > 67)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	        			}
		        		else
		        		{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
		        			sand = true;
		        		}
	        		}
	        		else if(depth < 5)
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
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        		}
            	}
            }
		}
    }
}
