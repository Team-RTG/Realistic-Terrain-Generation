package enhancedbiomes.handlers;

import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.MapGenCavesEnhancedBiomes;
import enhancedbiomes.world.MapGenRavineEnhancedBiomes;

public class UseHoeEventHandler 
{
	@SubscribeEvent
	public void tillDirt(UseHoeEvent e) {
		e.setResult(Result.DEFAULT);
		if(e.world.getBlock(e.x, e.y + 1, e.z).isAir(e.world, e.x, e.y + 1, e.z) && (e.world.getBlock(e.x, e.y, e.z) == EnhancedBiomesBlocks.dirtEB || e.world.getBlock(e.x, e.y, e.z) == EnhancedBiomesBlocks.grassEB)) {
			e.world.setBlock(e.x, e.y, e.z, EnhancedBiomesBlocks.farmlandEB[e.world.getBlockMetadata(e.x, e.y, e.z)], 0, 2);
			e.setResult(Result.ALLOW);
		}
	}
}
