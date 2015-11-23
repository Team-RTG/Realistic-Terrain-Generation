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

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

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
            event.newGen = new MapGenVillageRTG();
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
	
    @SubscribeEvent
    public void onPopulateChunkEvent(PopulateChunkEvent.Post event)
    {
        if (!ConfigRTG.generateOreEmerald)
        {
            World world = event.world;
            int endX = (event.chunkX * 16) + 16;
            int endZ = (event.chunkZ * 16) + 16;
            Block checkBlock;
            Block needleBlock = Blocks.emerald_ore;
            Block replaceBlock = Blocks.stone;
            int replaceBlockMeta = 0;

            // Get the highest possible existing block location.
            int maxY = event.chunkProvider.provideChunk(event.chunkX, event.chunkZ).getTopFilledSegment() + 15;
            
            /*if (Loader.isModLoaded("enhancedbiomes") && EnhancedBiomesMod.useNewStone == 1) {
                
                needleBlock = (EnhancedBiomesMod.useNewOres == 1) ? EnhancedBiomesBlocks.oreEmeraldEB : Blocks.emerald_ore;
                replaceBlock = (EnhancedBiomesMod.useNewOres == 1) ? EnhancedBiomesBlocks.stoneEB : Blocks.stone;
                replaceBlockMeta = -1;
            }
            else {
                
                needleBlock = Blocks.emerald_ore;
                replaceBlock = Blocks.stone;
                replaceBlockMeta = 0;
            }*/
            
            for (int x = event.chunkX * 16; x < endX; ++x)
            {
                for (int z = event.chunkZ * 16; z < endZ; ++z)
                {
                    for (int y = 0; y < maxY; ++y)
                    {   
                        if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, needleBlock)) {
                            
                            /*if (replaceBlockMeta == -1) {
                                replaceBlockMeta = (EnhancedBiomesMod.useNewOres == 1) ? (byte) world.getBlockMetadata(x, y, z) : (byte) 0;
                            }*/
                            
                            world.setBlock(x, y, z, replaceBlock, replaceBlockMeta, 2);
                            
                            if (ConfigRTG.enableDebugging) {
                                FMLLog.log(Level.INFO, "Emerald replaced at %d, %d, %d", x, y, z);
                            }
                        }
                    }
                }
            }
        }
    }
}