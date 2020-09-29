package rtg;

import java.nio.file.Paths;

import net.minecraft.client.Minecraft;
import net.minecraft.world.DimensionType;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import rtg.RTGConfig.RTGGuiConfigFactory;
import rtg.api.RTGAPI;
import rtg.api.util.PlateauUtil;
import rtg.event.EventHandlerCommon;
import rtg.init.BiomeInit;
import rtg.server.RTGCommandTree;
import rtg.util.ModCompat;
import rtg.world.WorldTypeRTG;


@SuppressWarnings({"unused", "WeakerAccess"})
@Mod(
    modid        = RTG.MOD_ID,
    name         = "Realistic Terrain Generation",
    version      = "@MOD_VERSION@",
    dependencies = "required-after:forge@[" + "@FORGE_MINVER@" + ",);required:" + RTGAPI.RTG_API_ID + "@[" + RTGAPI.VERSION + ",);" + "@MOD_DEPS@",
    guiFactory   = RTGGuiConfigFactory.LOCATION,
    acceptableRemoteVersions = "*"
)
public final class RTG {

    public  static final String MOD_ID      = RTGAPI.RTG_MOD_ID;
    private static final RTG    instance    = new RTG();

    private static boolean DISABLE_DECORATIONS;
    private static boolean DISABLE_SURFACES;

    private RTG() {}

    @Mod.InstanceFactory
    public static RTG getInstance() {
        return instance;
    }

    @SidedProxy
    private static RTGProxy proxy;
    public  static RTGProxy getProxy() { return proxy; }

    @Mod.EventHandler
    public void initPre(FMLPreInitializationEvent event) {

        DISABLE_DECORATIONS = System.getProperties().containsKey("rtg.disableDecorations");
        DISABLE_SURFACES    = System.getProperties().containsKey("rtg.disableSurfaces");

        RTGAPI.setConfigPath(Paths.get(event.getModConfigurationDirectory().getPath(), RTG.MOD_ID.toUpperCase()));
        RTGConfig.init(event);

        RTGAPI.addAllowedDimensionType(DimensionType.OVERWORLD);

        WorldTypeRTG.init();
        ModCompat.init();
        BiomeInit.preInit();// initialise river and beach biomes
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        EventHandlerCommon.init();// TERRAIN_GEN_BUS, ORE_GEN_BUS
    }

    @Mod.EventHandler
    public void initPost(FMLPostInitializationEvent event) {
        BiomeInit.init();// initialise all biomes supported internally
        ModCompat.doBiomeCheck();
        PlateauUtil.init();
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        RTGAPI.RTG_BIOMES.setLocked();// We don't want the biome map to change after this point, so we lock it.
    }

    @Mod.EventHandler
    public void serverStarting(final FMLServerStartingEvent event) {
        event.registerServerCommand(new RTGCommandTree());
    }

    public static boolean decorationsDisable() {
        return DISABLE_DECORATIONS;
    }

    public static boolean surfacesDisabled() {
        return DISABLE_SURFACES;
    }

    public static final class ClientProxy implements RTGProxy {
        @Override public void displayCustomizeWorldScreen(net.minecraft.client.gui.GuiCreateWorld guiCreateWorld) {
            Minecraft.getMinecraft().displayGuiScreen(new rtg.client.GuiCustomizeWorldScreenRTG(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
        }
    }

    public static class ServerProxy implements RTGProxy { @Override public void displayCustomizeWorldScreen(net.minecraft.client.gui.GuiCreateWorld guiCreateWorld) {} }

    public interface RTGProxy { void displayCustomizeWorldScreen(net.minecraft.client.gui.GuiCreateWorld guiCreateWorld); }
}
