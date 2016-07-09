package rtg.event.bus.event.worldevent;

import net.minecraftforge.event.world.WorldEvent;
import rtg.RTG;
import rtg.util.Logger;
import rtg.world.WorldTypeRTG;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldUnloadRTG
{

    public WorldUnloadRTG()
    {

    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {

        if (event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG) {
            
        	Logger.info("[onWorldUnload] Unregistering RTG's event handlers...");
        	RTG.eventMgr.unRegisterEventHandlers();
            Logger.info("[onWorldUnload] RTG's event handlers have been unregistered successfully.");
        }
        else {
        	
        	Logger.info("[onWorldUnload] Re-registering RTG's event handlers...");
        	RTG.eventMgr.registerEventHandlers();
        	Logger.info("[onWorldUnload] RTG's event handlers have been re-registered successfully.");
        }
    }
}