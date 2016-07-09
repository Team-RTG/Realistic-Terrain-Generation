package rtg.event.bus.event.worldevent;

import net.minecraftforge.event.world.WorldEvent;
import rtg.RTG;
import rtg.util.Logger;
import rtg.world.WorldTypeRTG;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldLoadRTG
{

    public WorldLoadRTG()
    {

    }
	
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {

        if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {
            
        	Logger.info("[onWorldLoad] Unregistering RTG's event handlers...");
        	RTG.eventMgr.unRegisterEventHandlers();
            Logger.info("[onWorldLoad] RTG's event handlers have been unregistered successfully.");
            
            return;
        }
        
    	Logger.info("[onWorldLoad] Re-registering RTG's event handlers...");
    	RTG.eventMgr.registerEventHandlers();
    	Logger.info("[onWorldLoad] RTG's event handlers have been re-registered successfully.");
        
        if (event.world.provider.dimensionId == 0) {
        	
            Logger.info("World Seed: %d", event.world.getSeed());
        }
    }
}