package rtg.event;

import java.util.WeakHashMap;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkEvent;
import rtg.event.bus.event.worldevent.LoadChunkRTG;
import rtg.event.bus.event.worldevent.WorldLoadRTG;
import rtg.event.bus.event.worldevent.WorldUnloadRTG;
import rtg.event.bus.oregen.oregenevent.GenerateMinableRTG;
import rtg.event.bus.terraingen.biomeevent.GetVillageBlockRTG;
import rtg.event.bus.terraingen.decoratebiomeevent.PreDecorateBiomeRTG;
import rtg.event.bus.terraingen.initmapgenevent.InitMapGenRTG;
import rtg.event.bus.terraingen.saplinggrowtreeevent.SaplingGrowTreeRTG;
import rtg.event.bus.terraingen.worldtypeevent.InitBiomeGensRTG;
import rtg.util.Acceptor;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class EventManagerRTG
{

	public static LoadChunkRTG loadChunkRTG = new LoadChunkRTG();
	public static WorldLoadRTG worldLoadRTG = new WorldLoadRTG();
	public static WorldUnloadRTG worldUnloadRTG = new WorldUnloadRTG();
	public static GenerateMinableRTG generateMinableRTG = new GenerateMinableRTG();
	public static GetVillageBlockRTG getVillageBlockRTG = new GetVillageBlockRTG();
	public static PreDecorateBiomeRTG preDecorateBiomeRTG = new PreDecorateBiomeRTG();
	public static InitMapGenRTG initMapGenRTG = new InitMapGenRTG();
	public static SaplingGrowTreeRTG saplingGrowTreeRTG = new SaplingGrowTreeRTG();
	public static InitBiomeGensRTG initBiomeGensRTG = new InitBiomeGensRTG();
	
	public RealisticBiomeBase biome = null;
	
    private WeakHashMap<Integer,Acceptor<ChunkEvent.Load>> chunkLoadEvents =
            new WeakHashMap<Integer,Acceptor<ChunkEvent.Load>> ();
    
    public EventManagerRTG()
    {

    }
    
    public void registerEventHandlers()
    {
    	MinecraftForge.EVENT_BUS.register(loadChunkRTG);
    	MinecraftForge.EVENT_BUS.register(worldLoadRTG);
    	MinecraftForge.EVENT_BUS.register(worldUnloadRTG);
    	MinecraftForge.ORE_GEN_BUS.register(generateMinableRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.register(getVillageBlockRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.register(preDecorateBiomeRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.register(initMapGenRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.register(saplingGrowTreeRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.register(initBiomeGensRTG);
    }
    
    public void unRegisterEventHandlers()
    {

    	/**
    	 * The onWorldLoad and onWorldUnload handlers must always be registered.
    	 * 
    	 * MinecraftForge.EVENT_BUS.unregister(worldLoadRTG);
    	 * MinecraftForge.EVENT_BUS.unregister(worldUnloadRTG);
    	 */

    	MinecraftForge.EVENT_BUS.unregister(loadChunkRTG);
    	MinecraftForge.ORE_GEN_BUS.unregister(generateMinableRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.unregister(getVillageBlockRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.unregister(preDecorateBiomeRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.unregister(initMapGenRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.unregister(saplingGrowTreeRTG);
    	MinecraftForge.TERRAIN_GEN_BUS.unregister(initBiomeGensRTG);
    }

    public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {
        chunkLoadEvents.put(dimension, action);
    }
}