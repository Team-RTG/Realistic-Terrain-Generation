package rtg.map.old;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;

public class MapGenAncientVillage
{
    private Random rand;
    
    public MapGenAncientVillage()
    {
    	rand = new Random();
    }

    public void generate(IChunkProvider provider, World world, int chunkX, int chunkY, Block[] blocks)
    {
    	int range = 3;
        rand.setSeed(world.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;

        for (int i = chunkX - range; i <= chunkX + range; i++)
        {
            for (int j = chunkY - range; j <= chunkY + range; j++)
            {
            	rand.setSeed((long)i * i1 + (long)j * j1 ^ world.getSeed());
                if(recursiveGenerate(world, i, j, chunkX, chunkY, blocks))
                {
                	return;
                }
            }
        }
    }
	
    public boolean recursiveGenerate(World world, int baseX, int baseY, int chunkX, int chunkY, Block[] block)
    {
        if(rand.nextInt(80) == 0)
        {
            long i1 = this.rand.nextLong() / 2L * 2L + 1L;
            long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        	rand.setSeed((long)chunkX * i1 + (long)chunkY * j1 ^ world.getSeed());
            
        	build(world, block, Math.max(Math.abs(baseX - chunkX),Math.abs(baseY - chunkY)));
        	return true;
        }
        return false;
    }  
    
    public void build(World world, Block[] block, int dis)
    {
    	int y = 120;
    	for(; y > 49; y--)
    	{
    		if(block[coordstoArray(8, y - 1, 8)] != Blocks.air)
    		{
    			if(y > 118)
    			{
    	    		return;
    			}
    			break;
    		}
    		
    		if(y < 51)
    		{
    			return;
    		}
    	}
    	
    	int sizeX = rand.nextInt(5) + 10 - (dis * 2);
    	int sizeZ = rand.nextInt(5) + 10 - (dis * 2);
    	int startX = rand.nextInt(2) + dis;
    	int startZ = rand.nextInt(2) + dis;
    	int i, j, height;
    	
    	for(i = startX; i < startX + sizeX; i++)
    	{
    		if(block[coordstoArray(startX + i, y - 2, startZ)] != Blocks.air)
    		{
        		height = rand.nextInt(4);
        		for(j = 0; j < height; j++)
        		{
        			block[coordstoArray(startX + i, y + j - 1, startZ)] = Blocks.mossy_cobblestone;
        		}
    		}

    		if(block[coordstoArray(startX + i, y - 2, startZ + sizeZ)] != Blocks.air)
    		{
	    		height = rand.nextInt(4);
	    		for(j = 0; j < height; j++)
	    		{
	    			block[coordstoArray(startX + i, y + j - 1, startZ + sizeZ)] = Blocks.mossy_cobblestone;
	    		}
    		}
    	}
    	
    	for(i = startZ + 1; i < startZ + sizeZ - 1; i++)
    	{
    		if(block[coordstoArray(startX, y - 2, startZ + i)] != Blocks.air)
    		{
        		height = rand.nextInt(4);
        		for(j = 0; j < height; j++)
        		{
        			block[coordstoArray(startX, y + j - 1, startZ + i)] = Blocks.mossy_cobblestone;
        		}
    		}

    		if(block[coordstoArray(startX + sizeX, y - 2, startZ + i)] != Blocks.air)
    		{
	    		height = rand.nextInt(4);
	    		for(j = 0; j < height; j++)
	    		{
	    			block[coordstoArray(startX + sizeX, y + j - 1, startZ + i)] = Blocks.mossy_cobblestone;
	    		}
    		}
    	}
    }
    
    public int coordstoArray(int x, int y, int z)
    {
    	return (z * 16 + x) * 256 + y;
    }
}
