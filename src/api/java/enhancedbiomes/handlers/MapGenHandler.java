package enhancedbiomes.handlers;

import enhancedbiomes.village.MapGenVillageEB;
import enhancedbiomes.world.MapGenScatteredFeatureEnhancedBiomes;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MapGenHandler 
{
	@SubscribeEvent
	public void replaceVillageGen(InitMapGenEvent event)
	{
		if(event.type == EventType.VILLAGE) {
			event.newGen = new MapGenVillageEB();
		}/*
		else if(event.type == EventType.SCATTERED_FEATURE) {
			event.newGen = new MapGenScatteredFeatureEnhancedBiomes();
		}*/
	}
}
