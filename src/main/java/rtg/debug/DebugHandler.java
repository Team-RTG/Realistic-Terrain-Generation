package rtg.debug;

import org.apache.logging.log4j.Level;

import rtg.config.ConfigRTG;
import rtg.reference.ModInfo;
import rtg.util.Logger;
import rtg.world.biome.ChunkManagerRealistic;
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

		if ( world.getWorldChunkManager() instanceof ChunkManagerRealistic ) {

			if (ConfigRTG.showDebugInfo && Minecraft.getMinecraft().gameSettings.showDebugInfo) {
				
				ChunkManagerRealistic chunkManager = (ChunkManagerRealistic)world.getWorldChunkManager();
				String details = "";
				
				event.left.add(null);
				int posX = (int)player.posX;
				int posZ = (int)player.posZ;
				BiomeGenBase biome = chunkManager.getBiomeGenAt(posX, posZ);
				RealisticBiomeBase realisticBiome = chunkManager.getBiomeDataAt(posX, posZ);
				
				details = PREFIX;
				details += "Realistic Base Biome (X/Z): " + realisticBiome.baseBiome.biomeName + " (ID=" + realisticBiome.baseBiome.biomeID + ")";
				event.left.add(details);
				
				details = PREFIX;
				details += "Realistic River Biome (X/Z): " + realisticBiome.riverBiome.biomeName + " (ID=" + realisticBiome.riverBiome.biomeID + ")";
				event.left.add(details);
				
				details = PREFIX;
				details += "Temperature/Rainfall (Static): " + biome.temperature + "/" + biome.rainfall;
				event.left.add(details);
				
				details = PREFIX;
				details += "Noise (X/Z): " + chunkManager.getNoiseAt(posX, posZ);
				event.left.add(details);

				details = PREFIX;
				details += "Ocean Value (X/Z): " + chunkManager.getOceanValue(posX, posZ);
				event.left.add(details);
				
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