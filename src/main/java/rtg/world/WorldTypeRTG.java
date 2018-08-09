package rtg.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import rtg.api.RTGAPI;
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

    private WorldTypeRTG() {
        super(RTGAPI.RTG_WORLDTYPE_ID);
    }

    public static void init() {
        INSTANCE = new WorldTypeRTG();
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {

        if (RTGAPI.isAllowedDimensionType(world.provider.getDimension())) {
            Logger.debug("WorldTypeRTG#getBiomeProvider() returning BiomeProviderRTG");
            return new BiomeProviderRTG(RTGWorld.getInstance(world));
        }
        else throw new RuntimeException(String.format("Illegal DimensionType (%s) for RTG WorldType", world.provider.getDimensionType().getName()));
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {

        if (RTGAPI.isAllowedDimensionType(world.provider.getDimension())) {
            Logger.debug("WorldTypeRTG#getChunkGenerator() returning ChunkGeneratorRTG for Dim {}", world.provider.getDimension());
            return new ChunkGeneratorRTG(RTGWorld.getInstance(world));
        }
        else throw new RuntimeException(String.format("Illegal DimensionType (%s) for RTG WorldType", world.provider.getDimensionType().getName()));
    }

    @Override
    public float getCloudHeight()
    {
        return 256F;
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
