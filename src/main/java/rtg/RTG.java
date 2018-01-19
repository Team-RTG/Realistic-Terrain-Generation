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
import rtg.reference.ModInfo;


@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(
    modid                    = ModInfo.MOD_ID,
    name                     = ModInfo.MOD_NAME,
    version                  = ModInfo.MOD_VERSION,
    dependencies             = "required-after:Forge@[" + ModInfo.MCF_MINVER + "," + ModInfo.MCF_MAXVER + ")" + ModInfo.MOD_DEPS,
    guiFactory               = RTGConfigGUIFactory.LOCATION,
    acceptableRemoteVersions = "*"
)
public class RTG {

    public static final EventManagerRTG eventMgr = new EventManagerRTG();
//  public        final ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<>();
    public        final ArrayList<Runnable> serverCloseActions = new ArrayList<>();

    @Mod.Instance(ModInfo.MOD_ID)
    public static RTG instance;

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
}
