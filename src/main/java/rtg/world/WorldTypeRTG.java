package rtg.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;

import rtg.RTG;
import rtg.api.RTGAPI;
import rtg.api.util.Logger;
import rtg.api.world.RTGWorld;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.gen.ChunkGeneratorRTG;


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
    public BiomeProvider getBiomeProvider(World world)
    {
        if (!world.isRemote) {
            final DimensionType type = world.provider.getDimensionType();
            if (RTGAPI.isAllowedDimensionType(type)) {
                Logger.debug("Allowed DimensionType detected (ID:{}, Type:{}, Suffix:{}).. returning BiomeProviderRTG", type.getId(), type, type.getSuffix());
                return new BiomeProviderRTG(RTGWorld.getInstance(world));
            } else {
                Logger.debug("DimensionType not in whitelist (ID:{}, Type:{}, Suffix:{}).. returning BiomeProvider", type.getId(), type, type.getSuffix());
            }
        }
        return new BiomeProvider(world.getWorldInfo());
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions)
    {
        if (!world.isRemote) {
            final DimensionType type = world.provider.getDimensionType();
            if (RTGAPI.isAllowedDimensionType(type)) {
                Logger.debug("Allowed DimensionType detected (ID:{}, Type:{}, Suffix:{}).. returning ChunkGeneratorRTG", type.getId(), type, type.getSuffix());
                return new ChunkGeneratorRTG(RTGWorld.getInstance(world));
            } else {
                Logger.debug("DimensionType not in whitelist (ID:{}, Type:{}, Suffix:{}).. returning ChunkGeneratorOverworld", type.getId(), type, type.getSuffix());
            }
        }
        final WorldInfo wi = world.getWorldInfo();
        return new ChunkGeneratorOverworld(world, wi.getSeed(), wi.isMapFeaturesEnabled(), wi.getGeneratorOptions());
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

    @Override // Client-only; we make a proxied call here (no going back to SideOnly) so the dedicated server doesn't flip out with ClassNotFoundException
    public void onCustomizeButton(net.minecraft.client.Minecraft mc, net.minecraft.client.gui.GuiCreateWorld guiCreateWorld) {
        RTG.getProxy().displayCustomizeWorldScreen(guiCreateWorld);
    }
}
