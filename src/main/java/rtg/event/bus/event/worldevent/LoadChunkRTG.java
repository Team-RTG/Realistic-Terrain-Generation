package rtg.event.bus.event.worldevent;

import java.util.WeakHashMap;

import net.minecraftforge.event.world.ChunkEvent;
import rtg.util.Acceptor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LoadChunkRTG
{

    private WeakHashMap<Integer,Acceptor<ChunkEvent.Load>> chunkLoadEvents =
            new WeakHashMap<Integer,Acceptor<ChunkEvent.Load>> ();
    
    public LoadChunkRTG()
    {

    }

    @SubscribeEvent
    public void onChunkLoadEvent(ChunkEvent.Load loadEvent)  {
        Integer dimension = loadEvent.world.provider.dimensionId;
        Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(dimension);
        if (acceptor != null) {
            acceptor.accept(loadEvent);
        }
    }
}