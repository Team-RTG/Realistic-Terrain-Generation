package rtg.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import rtg.api.RTGAPI;
import rtg.event.WorldTypeMessageEventHandler;

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

        // This event handler unregisters itself, so it doesn't need to be a part of the event management system.
        if (RTGAPI.config().ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN.get()) {
            MinecraftForge.EVENT_BUS.register(new WorldTypeMessageEventHandler());
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        super.postInit(event);
    }
}
