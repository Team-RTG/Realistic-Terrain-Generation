package rtg.event;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;
import rtg.world.gen.structure.MapGenMineshaftRTG;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.event.terraingen.InitMapGenEvent;

/**
 * Author: Choonster (https://github.com/Choonster)
 * Source: https://github.com/Choonster/TestMod2/blob/1575b85ad8949381215f3aeb6ca76ea2368074de/src/main/java/com/choonster/testmod2/event/TerrainGenHandler.java
 */
	
public class TerrainGenHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void initMapGen(InitMapGenEvent event) {
	    
	    if (ConfigRTG.enableDebugging) {
	        FMLLog.log(Level.INFO, "event type = %s", event.type.toString());
	        FMLLog.log(Level.INFO, "event originalGen = %s", event.originalGen.toString());
	    }
	    
		if (event.type == InitMapGenEvent.EventType.SCATTERED_FEATURE) {
			event.newGen = new MapGenScatteredFeatureRTG();
		}
		else if (event.type == InitMapGenEvent.EventType.MINESHAFT) {
		    //event.newGen = new MapGenMineshaftRTG();
		}
		
        if (ConfigRTG.enableDebugging) {
            FMLLog.log(Level.INFO, "event newGen = %s", event.newGen.toString());
        }
	}
}