package rtg.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;
import rtg.api.RTGAPI;
import rtg.api.util.Logger;
import rtg.api.world.RTGWorld;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.gen.ChunkGeneratorRTG;


@SuppressWarnings("EqualsAndHashcode")
public final class WorldTypeRTG extends WorldType {

    private static WorldTypeRTG INSTANCE;

    private WorldTypeRTG() {
        super(RTGAPI.RTG_WORLDTYPE_ID);
    }

    public static WorldTypeRTG getInstance() {
        if (INSTANCE == null) {
            init();
        }
        return INSTANCE;
    }

    public static void init() {
        INSTANCE = new WorldTypeRTG();
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {

        // TODO: It may be appropriate to add a config setting for a DimensionType whitelist in the future.
        if (RTGAPI.isAllowedDimensionType(world.provider.getDimension()) ||
            DimensionManager.getProviderType(world.provider.getDimension()).getSuffix().equals("_rtg") ||
            DimensionManager.getProviderType(world.provider.getDimension()).name().startsWith("jed_surface")) {

            Logger.debug("WorldType#getBiomeProvider: Allowed DimensionType detected (ID:{}, Type:{}, Suffix:{}).. returning BiomeProviderRTG",
                world.provider.getDimension(),
                world.provider.getDimensionType(),
                DimensionManager.getProviderType(world.provider.getDimension()).getSuffix()
            );
            return new BiomeProviderRTG(RTGWorld.getInstance(world));
        }
        else {
            Logger.debug("WorldType#getBiomeProvider: DimensionType not in whitelist (ID:{}, Type:{}, Suffix:{}).. returning BiomeProvider",
                world.provider.getDimension(),
                world.provider.getDimensionType(),
                DimensionManager.getProviderType(world.provider.getDimension()).getSuffix()
            );
            return new BiomeProvider(world.getWorldInfo());
        }
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {

        if (RTGAPI.isAllowedDimensionType(world.provider.getDimension()) ||
            DimensionManager.getProviderType(world.provider.getDimension()).getSuffix().equals("_rtg") ||
            DimensionManager.getProviderType(world.provider.getDimension()).name().startsWith("jed_surface")) {

            Logger.debug("WorldType#getChunkGenerator: Allowed DimensionType detected (ID:{}, Type:{}, Suffix:{}).. returning ChunkGeneratorRTG",
                world.provider.getDimension(),
                world.provider.getDimensionType(),
                DimensionManager.getProviderType(world.provider.getDimension()).getSuffix()
            );
            return new ChunkGeneratorRTG(RTGWorld.getInstance(world));
        }
        else {
            Logger.debug("WorldType#getChunkGenerator: DimensionType not in whitelist (ID:{}, Type:{}, Suffix:{}).. returning ChunkGeneratorOverworld",
                world.provider.getDimension(),
                world.provider.getDimensionType(),
                DimensionManager.getProviderType(world.provider.getDimension()).getSuffix()
            );
            return new ChunkGeneratorOverworld(world, world.getWorldInfo().getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions());
        }
    }

    @Override
    public float getCloudHeight() {
        return 256F;
    }

    @Override
    public boolean isCustomizable() {
        return true;
    }

    @Override // Client-only
    public String getTranslationKey() {
        return "gui.createWorld.worldtypename";
    }

    // Keep fully qualified names to avoid client class imports
    @Override // Client-only
    public void onCustomizeButton(net.minecraft.client.Minecraft mc, net.minecraft.client.gui.GuiCreateWorld guiCreateWorld) {
        mc.displayGuiScreen(new rtg.client.GuiCustomizeWorldScreenRTG(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
    }
// TODO: [Generator settings] Add an override for WorldType#getBiomeLayer to allow use of fixedBiome.
//                            This will likely require a custom GenLayerBiome class to be written.

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof WorldTypeRTG;
    }
}
