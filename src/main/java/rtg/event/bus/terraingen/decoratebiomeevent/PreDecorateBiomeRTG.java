package rtg.event.bus.terraingen.decoratebiomeevent;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import rtg.RTG;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.WorldChunkManagerRTG;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PreDecorateBiomeRTG
{

    public PreDecorateBiomeRTG()
    {

    }
    
    @SubscribeEvent
    public void preBiomeDecorate(DecorateBiomeEvent.Pre event)
    {

        //Are we in an RTG world? Do we have RTG's chunk manager?
        if (event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG && event.world.getWorldChunkManager() instanceof WorldChunkManagerRTG) {
            
            WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) event.world.getWorldChunkManager();
            RTG.eventMgr.biome = cmr.getBiomeDataAt(event.chunkX, event.chunkZ);
        }
    }
}