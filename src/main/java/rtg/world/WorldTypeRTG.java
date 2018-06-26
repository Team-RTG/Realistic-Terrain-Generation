package rtg.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import rtg.RTG;
import rtg.api.RTGAPI;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.util.Logger;
import rtg.api.world.RTGWorld;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.gen.ChunkGeneratorRTG;

public final class WorldTypeRTG extends WorldType
{
    private static WorldTypeRTG INSTANCE;
    public static WorldTypeRTG getInstance() {
        if (INSTANCE == null) { init(); }
        return INSTANCE;
    }

// TODO: [Generator settings][Dimensions] Remove all use of the static fields: biomeProvider, chunkProvider
    private static BiomeProviderRTG biomeProvider;
    public static ChunkGeneratorRTG chunkProvider;

    private WorldTypeRTG() {
        super(RTGAPI.RTG_WORLDTYPE_ID);
    }

    public static void init() {
        INSTANCE = new WorldTypeRTG();
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {

        if (DimensionManagerRTG.isValidDimension(world.provider.getDimension())) {

            if (biomeProvider == null) {

                biomeProvider = new BiomeProviderRTG(RTGWorld.getInstance(world));
                RTG.getInstance().runOnNextServerCloseOnly(clearProvider(biomeProvider));
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

                chunkProvider = new ChunkGeneratorRTG(RTGWorld.getInstance(world));
                RTG.getInstance().runOnNextServerCloseOnly(clearProvider(chunkProvider));

                // inform the event manager about the ChunkEvent.Load event
                RTG.getEventMgr().setDimensionChunkLoadEvent(world.provider.getDimension(), chunkProvider.delayedDecorator);
                RTG.getInstance().runOnNextServerCloseOnly(chunkProvider.clearOnServerClose());

                Logger.debug("WorldTypeRTG#getChunkGenerator() returning ChunkGeneratorRTG for Dim {}", world.provider.getDimension());

                return chunkProvider;
            }
            else return new ChunkGeneratorOverworld(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
        }
        Logger.error("Invalid dimension. Serving up ChunkGeneratorOverworld instead of ChunkGeneratorRTG.");
        return new ChunkGeneratorOverworld(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }

    @Override
    public float getCloudHeight()
    {
        return 256F;
    }

    private static Runnable clearProvider(Object provider) {
        if (provider instanceof BiomeProviderRTG) {
            Logger.debug("WorldTypeRTG#clearProvider() provider instanceof BiomeProviderRTG (setting to NULL)");
            return () -> biomeProvider = null;
        }
        else if (provider instanceof ChunkGeneratorRTG) {
            Logger.debug("WorldTypeRTG#clearProvider() provider instanceof ChunkGeneratorRTG (setting to NULL)");
            return () -> chunkProvider = null;
        }
        return () -> {};
    }

    @Override
    public boolean isCustomizable() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslationKey() {
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

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof WorldTypeRTG;
    }
}
