package rtg.world;

import rtg.world.biome.ChunkManagerRealistic;
import rtg.world.gen.ChunkProviderRealistic;
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
        return new ChunkProviderRealistic(world, world.getSeed());
    }
	
    public float getCloudHeight()
    {
        return 256F;
    }
}
