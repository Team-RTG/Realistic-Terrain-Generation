package rtg.debug;

import org.apache.logging.log4j.Level;

import rtg.config.ConfigRTG;
import rtg.reference.ModInfo;
import rtg.util.Logger;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class DebugHandler {

	private static final String PREFIX = EnumChatFormatting.GOLD + ModInfo.MOD_ID + " " + EnumChatFormatting.RESET;

	@SubscribeEvent
	public void onDrawDebugText(RenderGameOverlayEvent.Text event) {
		
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		World world = Minecraft.getMinecraft().theWorld;

		if ( world.getWorldChunkManager() instanceof WorldChunkManagerRTG ) {

			if (ConfigRTG.showDebugInfo && Minecraft.getMinecraft().gameSettings.showDebugInfo) {
				
				WorldChunkManagerRTG chunkManager = (WorldChunkManagerRTG)world.getWorldChunkManager();
				String details = "";
				
				if (ConfigRTG.enableDebugging)
				{
					details = PREFIX;
					details += "WARNING!!! Debugging mode is ENABLED!";
					event.left.add(details);
				}
			}
		}
	}

	public static void log(String format, Object... data)
	{
		if (ConfigRTG.enableDebugging)
		{
			Logger.log(Level.INFO, format, data);
		}
	}
}