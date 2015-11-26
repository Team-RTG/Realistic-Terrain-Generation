package rtg.event;

import org.apache.logging.log4j.Level;

import rtg.config.rtg.ConfigRTG;
import rtg.world.gen.MapGenCavesRTG;
import rtg.world.gen.MapGenRavineRTG;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.world.gen.structure.MapGenStructureIO;

import net.minecraftforge.event.terraingen.InitMapGenEvent;

public class EventManagerRTG
{

    public EventManagerRTG()
    {
        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
    }
    
	@SubscribeEvent(priority = EventPriority.LOW)
	public void initMapGen(InitMapGenEvent event) {
	    
	    if (ConfigRTG.enableDebugging) {
	        FMLLog.log(Level.INFO, "event type = %s", event.type.toString());
	        FMLLog.log(Level.INFO, "event originalGen = %s", event.originalGen.toString());
	    }
	    
		if (event.type == InitMapGenEvent.EventType.SCATTERED_FEATURE) {
			event.newGen = new MapGenScatteredFeatureRTG();
		}
        else if (event.type == InitMapGenEvent.EventType.VILLAGE) {
            
            if (ConfigRTG.enableVillageModifications) {
                event.newGen = new MapGenVillageRTG();
            }
        }
        else if (event.type == InitMapGenEvent.EventType.CAVE) {
            event.newGen = new MapGenCavesRTG();
        }
        else if (event.type == InitMapGenEvent.EventType.RAVINE) {
            event.newGen = new MapGenRavineRTG();
        }
		
        if (ConfigRTG.enableDebugging) {
            FMLLog.log(Level.INFO, "event newGen = %s", event.newGen.toString());
        }
	}
}