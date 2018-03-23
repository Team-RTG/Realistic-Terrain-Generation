package rtg;

import java.util.ArrayList;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

import rtg.client.gui.RTGConfigGUIFactory;
import rtg.event.EventManagerRTG;
import rtg.proxy.ClientProxy;
import rtg.proxy.CommonProxy;


@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(
    modid                    = RTG.MOD_ID,
    name                     = RTG.MOD_NAME,
    version                  = RTG.MOD_VERSION,
    dependencies             = "required-after:forge@[" + RTG.MCF_MINVER + "," + RTG.MCF_MAXVER + ")" + RTG.MOD_DEPS,
    guiFactory               = RTGConfigGUIFactory.LOCATION,
    acceptableRemoteVersions = "*"
)
public final class RTG {

    public static final String MOD_ID = "rtg";
    public static final String MOD_NAME = "Realistic Terrain Generation";
    public static final String MOD_VERSION = "@MOD_VERSION@";
    public static final String MCF_MINVER = "0.0-MCF+MINVER";
    public static final String MCF_MAXVER = "9001.0-MCF+MAXVER";
    public static final String MOD_DEPS = ";after:MODDEPS";

    private RTG() {}
    private static final RTG instance = new RTG();
    @Mod.InstanceFactory
    public static RTG getInstance() { return instance; }

    private static final EventManagerRTG eventMgr = new EventManagerRTG();

//  public        final ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<>();
    public        final ArrayList<Runnable> serverCloseActions = new ArrayList<>();

    @SidedProxy(serverSide = CommonProxy.LOCATION, clientSide = ClientProxy.LOCATION)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void initPre(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void initPost(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onServerStopped(FMLServerStoppedEvent event) {

        serverCloseActions.forEach(Runnable::run);
//      oneShotServerCloseActions.forEach(Runnable::run);
//      oneShotServerCloseActions.clear();
    }

    // TImeTracker, UBColumnCache
    public void runOnServerClose(Runnable action) {
        serverCloseActions.add(action);
    }

    public void runOnNextServerCloseOnly(Runnable action) {
        serverCloseActions.add(action);
    }

    public static EventManagerRTG getEventMgr() {
        return eventMgr;
    }
}
