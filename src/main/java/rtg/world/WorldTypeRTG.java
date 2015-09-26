package rtg.world;

import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.ChunkProviderRTG;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeRTG extends WorldType
{
	public WorldTypeRTG(String name)
	{
		super(name);
	}
	
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerRTG(world);
    }

    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderRTG(world, world.getSeed());
    }
	
    public float getCloudHeight()
    {
        return 256F;
    }
}
