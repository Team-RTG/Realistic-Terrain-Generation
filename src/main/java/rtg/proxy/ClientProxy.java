package rtg.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import rtg.event.GuiEventHandler;
import rtg.event.WorldTypeMessageEventHandler;
import static rtg.api.RTGAPI.config;

public class ClientProxy extends CommonProxy
{
    public static final String LOCATION = "rtg.proxy.ClientProxy";

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {

        super.init(event);

        if (config().ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN.get()) {
            MinecraftForge.EVENT_BUS.register(WorldTypeMessageEventHandler.instance);
        }

        if (config().USE_RTG_WORLD_TYPE_DEFAULT.get()) {
            MinecraftForge.EVENT_BUS.register(GuiEventHandler.instance);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
