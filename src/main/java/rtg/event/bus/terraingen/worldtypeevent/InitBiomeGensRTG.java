package rtg.event.bus.terraingen.worldtypeevent;

import net.minecraftforge.event.terraingen.WorldTypeEvent;
import rtg.world.gen.genlayer.RiverRemover;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class InitBiomeGensRTG
{

    public InitBiomeGensRTG()
    {

    }

    @SubscribeEvent
    public void onBiomeGenInit(WorldTypeEvent.InitBiomeGens event) {
        
        // only handle RTG world type
        if (!event.worldType.getWorldTypeName().equalsIgnoreCase("RTG")) return;

        if (event.newBiomeGens[0].getClass().getName().contains("GenLayerEB")) return;
        boolean stripRivers = true; // This used to be a config option. Hardcoding until we have a need for the option.
        
        if (stripRivers) {
            try {
                event.newBiomeGens = new RiverRemover().riverLess(event.originalBiomeGens);
            } catch (ClassCastException ex) {
                //throw ex;
                // failed attempt because the GenLayers don't end with GenLayerRiverMix
            }
        }
    }
}