package rtg.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

import rtg.RTG;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.ChunkProviderRTG;

public class WorldTypeRTG extends WorldType
{

    private static WorldChunkManagerRTG chunkManager;
    public static ChunkProviderRTG chunkProvider;

    private static Runnable clearChunkManager() {
        return new Runnable() {

            public void run() {
                chunkManager = null;
            }

        };
    }

    private static Runnable clearChunkProvider() {
        return new Runnable() {

            public void run() {
                chunkProvider = null;
            }

        };
    }

    public WorldTypeRTG(String name)
    {
        super(name);
    }

    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        if (world.provider.getDimensionId() == 0) {
            if (chunkManager == null) {
                chunkManager = new WorldChunkManagerRTG(world,this);
                RTG.instance.runOnNextServerCloseOnly(clearChunkManager());
            }
            return chunkManager;
        } else {
            return new WorldChunkManager(world);
        }
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        if (world.provider.getDimensionId() == 0) {
            if (chunkProvider == null) {
                chunkProvider = new ChunkProviderRTG(world, world.getSeed());
                RTG.instance.runOnNextServerCloseOnly(clearChunkProvider());
                // inform the event manager about the ChunkEvent.Load event
                RTG.eventMgr.setDimensionChunkLoadEvent(world.provider.getDimensionId(), chunkProvider.delayedDecorator);
                RTG.instance.runOnNextServerCloseOnly(chunkProvider.clearOnServerClose());
                return chunkProvider;
            }
            // return a "fake" provider that won't decorate for Streams
            ChunkProviderRTG result = new ChunkProviderRTG(world, world.getSeed());
            result.isFakeGenerator();
            return result;
            // no server close because it's not supposed to decorate
            //return chunkProvider;
        } else {
            return new ChunkProviderGenerate(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
        }
    }

    @Override
    public float getCloudHeight()
    {
        return 256F;
    }
}
