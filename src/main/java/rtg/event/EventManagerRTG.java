package rtg.event;

import org.apache.logging.log4j.Level;

import rtg.RTG;
import rtg.config.rtg.ConfigRTG;
import rtg.world.WorldTypeRTG;
import rtg.world.gen.MapGenCavesRTG;
import rtg.world.gen.MapGenRavineRTG;
import rtg.world.gen.genlayer.RiverRemover;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.world.gen.structure.MapGenStructureIO;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EventManagerRTG
{

    public EventManagerRTG()
    {
        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
    }
    
	@SubscribeEvent(priority = EventPriority.LOW)
	public void eventListenerRTG(InitMapGenEvent event) {
	    
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
            
            if (ConfigRTG.enableCaveModifications) {
                
                event.newGen = new MapGenCavesRTG();
            }
        }
        else if (event.type == InitMapGenEvent.EventType.RAVINE) {
            
            if (ConfigRTG.enableRavineModifications) {
                
                event.newGen = new MapGenRavineRTG();
            }
        }
		
        if (ConfigRTG.enableDebugging) {
            FMLLog.log(Level.INFO, "event newGen = %s", event.newGen.toString());
        }
	}
	
    @SubscribeEvent
    public void eventListenerRTG(WorldEvent.Load event) {

        if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {
            
            MinecraftForge.TERRAIN_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.ORE_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.EVENT_BUS.unregister(RTG.eventMgr);
        }
    }

    @SubscribeEvent
    public void onGenerateMinable(OreGenEvent.GenerateMinable event) {

        switch (event.type) {
            
            case COAL:
                
                if (!ConfigRTG.generateOreCoal) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case IRON:
                
                if (!ConfigRTG.generateOreIron) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case GOLD:
                
                if (!ConfigRTG.generateOreGold) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case DIAMOND:
                
                if (!ConfigRTG.generateOreDiamond) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case REDSTONE:
                
                if (!ConfigRTG.generateOreRedstone) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case LAPIS:
                
                if (!ConfigRTG.generateOreLapis) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            default:
                break;
        }
    }

    @SubscribeEvent
    public void onBiomeGenInit(WorldTypeEvent.InitBiomeGens event) {
        
        // only handle RTG world type
        if (!event.worldType.getWorldTypeName().equalsIgnoreCase("RTG")) return;

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
    
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        
        if (!event.world.getWorldInfo().getTerrainType().getWorldTypeName().equalsIgnoreCase("RTG")) {
            return;
        }
        
        if (event.world.provider.dimensionId == 0) {
            
            FMLLog.log(Level.INFO, "World Seed: %d", event.world.getSeed());
        }
    }
}