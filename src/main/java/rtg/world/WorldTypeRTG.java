package rtg.world;

import javax.annotation.Nonnull;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderOverworld;

import rtg.RTG;
import rtg.world.biome.BiomeProviderIRTG;
import rtg.world.gen.ChunkProviderRTG;

public class WorldTypeRTG extends WorldType
{

    private static BiomeProviderIRTG biomeProvider;
    public static ChunkProviderRTG chunkProvider;

    private static Runnable clearChunkManager() {
        return () -> biomeProvider = null;
    }

    private static Runnable clearChunkProvider() {
        return () -> chunkProvider = null;
    }

    public WorldTypeRTG(String name)
    {
        super(name);
    }

    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world)
    {
        if (world.provider.getDimension() == 0)
        {
            if (biomeProvider == null) {

                biomeProvider = new BiomeProviderIRTG(world, this);
                RTG.instance.runOnNextServerCloseOnly(clearChunkManager());
            }

            return biomeProvider;
        }
        else
        {
            return new BiomeProvider(world.getWorldInfo());
        }
    }

    @Override @Nonnull
    public IChunkGenerator getChunkGenerator(@Nonnull World world, String generatorOptions)
    {
        if (world.provider.getDimension() == 0) {

            if (chunkProvider == null) {

                chunkProvider = new ChunkProviderRTG(world, world.getSeed());
                RTG.instance.runOnNextServerCloseOnly(clearChunkProvider());

                // inform the event manager about the ChunkEvent.Load event
                RTG.eventMgr.setDimensionChunkLoadEvent(world.provider.getDimension(), chunkProvider.delayedDecorator);
                RTG.instance.runOnNextServerCloseOnly(chunkProvider.clearOnServerClose());

                return chunkProvider;
            }

            // return a "fake" provider that won't decorate for Streams
            ChunkProviderRTG result = new ChunkProviderRTG(world, world.getSeed());
            result.isFakeGenerator();

            return result;

            // no server close because it's not supposed to decorate
            //return chunkProvider;
        }
        else {

            return new ChunkProviderOverworld(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
        }
    }

    @Override
    public float getCloudHeight()
    {
        return 256F;
    }
}
