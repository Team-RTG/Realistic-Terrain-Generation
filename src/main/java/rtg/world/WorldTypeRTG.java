package rtg.world;

import rtg.reference.ModInfo;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.ChunkProviderRealistic;
import rtg.world.gen.layer.GenLayerBiomeRTG;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldTypeRTG extends WorldType
{
	public WorldTypeRTG()
	{
        super(ModInfo.WORLD_NAME);
	}	

	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerBiomeRTG(200L, parentLayer, this);

        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        
        return ret;
    }
    
    @Override
	public WorldChunkManager getChunkManager(World world)
    {
    	return new WorldChunkManagerRTG(world);
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
