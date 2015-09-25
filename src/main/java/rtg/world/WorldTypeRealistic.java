package rtg.world;

import rtg.world.biome.*;
import rtg.world.gen.layers.*;
//import rtg.world.gen.ChunkProviderRealistic;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.*;

public class WorldTypeRealistic extends WorldType
{
	public WorldTypeRealistic(String name)
	{
		super(name);
	}
	
	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerBiomeRTG(200L, parentLayer, this);

        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
	
    public WorldChunkManager getChunkManager(World world)
    {
        return new ChunkManagerRealistic(world);
    }

 //   public IChunkProvider getChunkGenerator(World world, String generatorOptions)
   // {
     //   return new ChunkProviderRealistic(world, world.getSeed());
   // }
	
    public float getCloudHeight()
    {
        return 256F;
    }
}
