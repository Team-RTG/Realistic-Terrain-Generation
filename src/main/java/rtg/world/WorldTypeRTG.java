package rtg.world;

import javax.annotation.Nonnull;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderOverworld;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rtg.RTG;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.gen.ChunkProviderRTG;

public class WorldTypeRTG extends WorldType
{

    private static BiomeProviderRTG biomeProvider;
    public static ChunkProviderRTG chunkProvider;
    private final boolean hasNotificationData;

    public WorldTypeRTG(String name)
    {
        super(name);
        this.hasNotificationData = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean showWorldInfoNotice() {
        return this.hasNotificationData;
    }

    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world)
    {
        if (world.provider.getDimension() == 0)
        {
            if (biomeProvider == null) {

                biomeProvider = new BiomeProviderRTG(world, this);
                RTG.instance.runOnNextServerCloseOnly(clearProvider(biomeProvider));
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
                RTG.instance.runOnNextServerCloseOnly(clearProvider(chunkProvider));

                // inform the event manager about the ChunkEvent.Load event
                RTG.eventMgr.setDimensionChunkLoadEvent(world.provider.getDimension(), chunkProvider.delayedDecorator);
                RTG.instance.runOnNextServerCloseOnly(chunkProvider.clearOnServerClose());

                return chunkProvider;
            }

            // Return a "pseudo" provider that won't decorate for Streams.
            ChunkProviderRTG result = new ChunkProviderRTG(world, world.getSeed());
            result.isPseudoGenerator();

            return result;

            // no server close because it's not supposed to decorate
            //return chunkProvider;
        }
        else {
            return new ChunkProviderOverworld(
                world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions
            );
        }
    }

    @Override
    public float getCloudHeight()
    {
        return 256F;
    }

    private static Runnable clearProvider(Object provider) {
        if (provider instanceof BiomeProviderRTG)
            return () -> biomeProvider = null;
        else if (provider instanceof ChunkProviderRTG)
            return () -> chunkProvider = null;
        else return null;
    }
}
