package rtg.event;

import rtg.village.MapGenVillageRTG;
import rtg.world.gen.structure.MapGenScatteredFeatureModBiomes;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;

/**
 * Author: Choonster (https://github.com/Choonster)
 * Source: https://github.com/Choonster/TestMod2/blob/1575b85ad8949381215f3aeb6ca76ea2368074de/src/main/java/com/choonster/testmod2/event/TerrainGenHandler.java
 */
	
public class TerrainGenHandler {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void initMapGen(InitMapGenEvent event) {
		if (event.type == InitMapGenEvent.EventType.CAVE) {
			//event.newGen = new MapGenWrapper(event.newGen, new MapGenIronBlockCaves());
		} else if (event.type == InitMapGenEvent.EventType.SCATTERED_FEATURE) {
			event.newGen = new MapGenScatteredFeatureModBiomes();
		}
	}	
	
}