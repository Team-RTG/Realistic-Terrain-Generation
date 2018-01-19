package rtg.world;

import javax.annotation.Nullable;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderOverworld;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import rtg.RTG;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.util.Logger;
import rtg.api.world.gen.GenSettingsRepo;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.gen.ChunkProviderRTG;

public class WorldTypeRTG extends WorldType
{

// TODO: [Generator settings][Dimensions] Remove all use of the static fields: biomeProvider, chunkProvider
    private static BiomeProviderRTG biomeProvider;
    public static ChunkProviderRTG chunkProvider;

    public WorldTypeRTG(String name)
    {
        super(name);
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {

        // Populate the GenSettingsRepo here as it is the earliest possible
        if (!world.isRemote) {
            Logger.info("WorldTypeRTG#getBiomeProvider: Adding entry to GenSettingsRepo for Dim {}", world.provider.getDimension());
            GenSettingsRepo.addSettingsForWorld(world);
        }

        if (DimensionManagerRTG.isValidDimension(world.provider.getDimension())) {

            if (biomeProvider == null) {

                biomeProvider = new BiomeProviderRTG(world, this);
                RTG.instance.runOnNextServerCloseOnly(clearProvider(biomeProvider));
            }

            Logger.debug("WorldTypeRTG#getBiomeProvider() returning BiomeProviderRTG");

            return biomeProvider;
        }
        else {

            Logger.debug("WorldTypeRTG#getBiomeProvider() returning vanilla BiomeProvider");

            return new BiomeProvider(world.getWorldInfo());
        }
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {

        if (DimensionManagerRTG.isValidDimension(world.provider.getDimension())) {

            if (chunkProvider == null) {

                chunkProvider = new ChunkProviderRTG(world, world.getSeed(), generatorOptions);
                RTG.instance.runOnNextServerCloseOnly(clearProvider(chunkProvider));

                // inform the event manager about the ChunkEvent.Load event
                RTG.eventMgr.setDimensionChunkLoadEvent(world.provider.getDimension(), chunkProvider.delayedDecorator);
                RTG.instance.runOnNextServerCloseOnly(chunkProvider.clearOnServerClose());

                Logger.debug("WorldTypeRTG#getChunkGenerator() returning ChunkProviderRTG for Dim {}", world.provider.getDimension());

                return chunkProvider;
            }

            // return a "fake" provider that won't decorate for Streams
            ChunkProviderRTG result = new ChunkProviderRTG(world, world.getSeed(), generatorOptions);
            result.isFakeGenerator();

            return result;

            // no server close because it's not supposed to decorate
            //return chunkProvider;
        }
        else {

            Logger.error("Invalid dimension. Serving up ChunkProviderOverworld instead of ChunkProviderRTG.");
            return new ChunkProviderOverworld(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
        }
    }

    @Override
    public float getCloudHeight()
    {
        return 256F;
    }

    @Nullable
    private static Runnable clearProvider(Object provider) {
        if (provider instanceof BiomeProviderRTG) {
            Logger.debug("WorldTypeRTG#clearProvider() provider instanceof BiomeProviderRTG (setting to NULL)");
            return () -> biomeProvider = null;
        }
        else if (provider instanceof ChunkProviderRTG) {
            Logger.debug("WorldTypeRTG#clearProvider() provider instanceof ChunkProviderRTG (setting to NULL)");
            return () -> chunkProvider = null;
        }
        else {
            Logger.debug("WorldTypeRTG#clearProvider() returning NULL");
            return null;
        }
    }

    @Override
    public boolean isCustomizable() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslateName() {
        return "gui.createWorld.worldtypename";
    }

    // Keep fully qualified names to avoid client class imports
    @Override
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(net.minecraft.client.Minecraft mc, net.minecraft.client.gui.GuiCreateWorld guiCreateWorld) {
        mc.displayGuiScreen(new rtg.client.gui.GuiCustomizeWorldScreenRTG(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
    }
// TODO: [Generator settings] Add an override for WorldType#getBiomeLayer to allow use of fixedBiome.
//                            This will likely require a custom GenLayerBiome class to be written.
}
