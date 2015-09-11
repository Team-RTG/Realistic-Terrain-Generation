package rtg.event;

import rtg.world.gen.structure.MapGenScatteredFeatureModBiomes;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;

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