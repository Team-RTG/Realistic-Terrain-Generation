package enhancedbiomes.world;

import enhancedbiomes.world.gen.layer.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;

public class WorldTypeSingleBiome extends WorldType
{   		
	public WorldTypeSingleBiome(String str)
    {
        super(str);        
    }

	@Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerEB(world);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderEnhancedBiomes(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }

    /**
     * Creates the GenLayerBiome used for generating the world
     * 
     * @param worldSeed The world seed
     * @param parentLayer The parent layer to feed into any layer you return
     * @return A GenLayer that will return ints representing the Biomes to be generated, see GenLayerBiome
     */
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerSingleBiome(200L, parentLayer, this);
        ret = GenLayerEBZoom.magnify(1000L, ret, 2);
        ret = new GenLayerEBBiomeEdge(1000L, ret);
        return ret;
    }
}