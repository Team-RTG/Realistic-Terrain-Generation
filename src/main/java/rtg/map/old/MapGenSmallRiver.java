package rtg.map.old;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class MapGenSmallRiver 
{
    private Random rand;
    
    public MapGenSmallRiver()
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
        if(rand.nextInt(2) == 0)
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
    	
    }
    
    public int coordstoArray(int x, int y, int z)
    {
    	return (z * 16 + x) * 256 + y;
    }
}
