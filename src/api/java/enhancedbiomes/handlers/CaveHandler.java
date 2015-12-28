package enhancedbiomes.handlers;

import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.world.MapGenCavesEnhancedBiomes;
import enhancedbiomes.world.MapGenRavineEnhancedBiomes;

public class CaveHandler 
{
	@SubscribeEvent
	public void replaceCaveGens(InitMapGenEvent e) {
		if(e.type == EventType.CAVE) e.newGen = new MapGenBase();//CavesEnhancedBiomes();
	}
	
	@SubscribeEvent
	public void replaceRavineGens(InitMapGenEvent e) {
		if(e.type == EventType.RAVINE) e.newGen = new MapGenBase();//RavineEnhancedBiomes();
	}
}
