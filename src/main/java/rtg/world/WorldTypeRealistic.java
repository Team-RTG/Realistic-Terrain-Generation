package rtg.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeRealistic extends WorldType
{
	public WorldTypeRealistic(String name)
	{
		super(name);
	}
	
    public WorldChunkManager getChunkManager(World world)
    {
        return new ChunkManagerRealistic(world);
    }

    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkGeneratorRealistic(world, world.getSeed());
    }
	
    public float getCloudHeight()
    {
        return 256F;
    }
}
