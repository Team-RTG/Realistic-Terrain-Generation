package rtg.event;

import org.apache.logging.log4j.Level;

import rtg.RTG;
import rtg.config.rtg.ConfigRTG;
import rtg.data.VillageMaterialData;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.MapGenStructureIO;

import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;

public class EventManagerRTG {

    private static VillageMaterialData[] dataList = new VillageMaterialData[256];
    
    public EventManagerRTG()
    {
        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
    }
    
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
	
    /*@SubscribeEvent
    public void onBiomeSize(BiomeSize event)
    {
        if (ConfigRTG.enableDebugging) {
            FMLLog.log(Level.INFO, "Original biome size = %d", event.originalSize);
        }
        
        if (event.worldType.equals(RTG.worldtype)) {
            
            event.newSize = (byte)6;
            
            if (ConfigRTG.enableDebugging) {
                FMLLog.log(Level.INFO, "New biome size = %d", event.newSize);
            }
        }
    }*/
	
    @SubscribeEvent
    public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
    {
        if (event.biome != null)
        {
            VillageMaterialData vmd = this.dataList[event.biome.biomeID];
            
            if (vmd != null)
            {
                if (event.original == Blocks.log) { event.replacement = vmd.logBlock; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.cobblestone) { event.replacement = vmd.cobbleBlock; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.planks) { event.replacement = vmd.plankBlock; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.gravel) { event.replacement = vmd.pathBlock; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.oak_stairs) { event.replacement = vmd.stairsWoodBlock; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.stone_stairs) { event.replacement = vmd.stairsStoneBlock; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.stone_slab) { event.replacement = vmd.slabsBlock; event.setResult(Result.DENY); return; }
            }
        }
    }
    
    @SubscribeEvent
    public void getVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
    {   
        if (event.biome != null)
        {
            VillageMaterialData vmd = this.dataList[event.biome.biomeID];
            
            if (vmd != null)
            {
                if (event.original == Blocks.log) { event.replacement = vmd.logBlockMeta; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.cobblestone) { event.replacement = vmd.cobbleBlockMeta; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.planks) { event.replacement = vmd.plankBlockMeta; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.gravel) { event.replacement = vmd.pathBlockMeta; event.setResult(Result.DENY); return; }
                if (event.original == Blocks.stone_slab) { event.replacement = vmd.slabsBlockMeta; event.setResult(Result.DENY); return; }
            }
        }
    }
	
    public static void registerVillageMaterial(VillageMaterialData data)
    {
        dataList[data.biomeID] = data;
    }
}