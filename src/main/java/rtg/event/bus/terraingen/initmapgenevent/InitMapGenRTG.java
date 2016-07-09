package rtg.event.bus.terraingen.initmapgenevent;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import rtg.config.rtg.ConfigRTG;
import rtg.util.Logger;
import rtg.world.gen.MapGenCavesRTG;
import rtg.world.gen.MapGenRavineRTG;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class InitMapGenRTG
{

    public InitMapGenRTG()
    {

    }
    
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onInitMapGen(InitMapGenEvent event) {
	    
        Logger.debug("event type = %s", event.type.toString());
        Logger.debug("event originalGen = %s", event.originalGen.toString());
	    
		if (event.type == InitMapGenEvent.EventType.SCATTERED_FEATURE) {
			event.newGen = new MapGenScatteredFeatureRTG();
		}
        else if (event.type == InitMapGenEvent.EventType.VILLAGE) {
            
            if (ConfigRTG.enableVillageModifications) {
                event.newGen = new MapGenVillageRTG();
            }
        }
        else if (event.type == InitMapGenEvent.EventType.CAVE) {
            
            if (ConfigRTG.enableCaveModifications) {
                
                event.newGen = new MapGenCavesRTG();
            }
        }
        else if (event.type == InitMapGenEvent.EventType.RAVINE) {
            
            if (ConfigRTG.enableRavineModifications) {
                
                event.newGen = new MapGenRavineRTG();
            }
        }
		
        Logger.debug("event newGen = %s", event.newGen.toString());
	}
}