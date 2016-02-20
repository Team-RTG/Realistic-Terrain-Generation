package rtg.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rtg.config.rtg.ConfigRTG;
import rtg.reference.ModInfo;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;

public final class DebugHandler {

	private static final String PREFIX = EnumChatFormatting.GOLD + ModInfo.MOD_ID + " " + EnumChatFormatting.RESET;

	@SubscribeEvent
	public void onDrawDebugText(RenderGameOverlayEvent.Text event) {
		
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		World world = Minecraft.getMinecraft().theWorld;

		if ( world.getWorldChunkManager() instanceof WorldChunkManagerRTG ) {

			if (ConfigRTG.showDebugInfo && Minecraft.getMinecraft().gameSettings.showDebugInfo) {
				
				WorldChunkManagerRTG chunkManager = (WorldChunkManagerRTG)world.getWorldChunkManager();
				String details = "";
				
				event.left.add(null);
				int posX = (int)player.posX;
				int posZ = (int)player.posZ;
				
//                RealisticBiomeBase realisticBiome = chunkManager.getBiomeDataAt(
//                    (int)Math.floor(posX / 16), 
//                    (int)Math.floor(posZ / 16)
//                );
                
                BiomeGenBase biome = world.getBiomeGenForCoords(new BlockPos(posX,0 ,posZ));
                RealisticBiomeBase realisticBiome = RealisticBiomeBase.getBiome(biome.biomeID);
				
                details = PREFIX;
                details += "River Strength: " + chunkManager.getRiverStrength(posX, posZ);
                event.left.add(details);
                
                details = PREFIX;
                details += "Temperature/Rainfall: " + biome.temperature + "/" + biome.rainfall;
                event.left.add(details);
				
				//details = PREFIX;
				//details += "Noise (X/Z): " + chunkManager.getNoiseAt(posX, posZ);
				//event.left.add(details);
				
				if (ConfigRTG.enableDebugging)
				{
					details = PREFIX;
					details += "WARNING!!! Debugging mode is ENABLED!";
					event.left.add(details);
				}
			}
		}
	}
}