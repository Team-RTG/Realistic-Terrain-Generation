package teamrtg.rtg.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamrtg.rtg.api.world.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.core.ModInfo;
import teamrtg.rtg.world.biome.BiomeProviderRTG;

public final class DebugHandler {

    private static final String PREFIX = ChatFormatting.GOLD + ModInfo.MOD_ID + " " + ChatFormatting.RESET;

    @SubscribeEvent
    public void onDrawDebugText(RenderGameOverlayEvent.Text event) {

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        World world = Minecraft.getMinecraft().theWorld;

        if (world.getBiomeProvider() instanceof BiomeProviderRTG) {

            if (Mods.RTG.config.SHOW_DEBUG_INFO.get() && Minecraft.getMinecraft().gameSettings.showDebugInfo) {

                BiomeProviderRTG chunkManager = (BiomeProviderRTG) world.getBiomeProvider();
                String details = "";

                event.getLeft().add(null);
                int posX = (int) player.posX;
                int posZ = (int) player.posZ;

//                RealisticBiomeBase realisticBiome = chunkManager.getRealisticAt(
//                    (int)Math.floor(posX / 16), 
//                    (int)Math.floor(posZ / 16)
//                );

                BiomeGenBase biome = world.getBiomeGenForCoords(new BlockPos(posX, 0, posZ));
                RealisticBiomeBase realisticBiome = RealisticBiomeBase.forBiome(BiomeUtils.getId(biome));

                details = PREFIX;
                details += "River Strength: " + chunkManager.getRiverStrength(posX, posZ);
                event.getLeft().add(details);

                details = PREFIX;
                details += "Temperature/Rainfall: " + biome.getTemperature() + "/" + biome.getRainfall();
                event.getLeft().add(details);

                //details = PREFIX;
                //details += "Noise (X/Z): " + chunkManager.getNoiseAt(posX, posZ);
                //event.left.add(details);

                if (Mods.RTG.config.DEBUG_LOGGING.get()) {
                    details = PREFIX;
                    details += "WARNING!!! Debugging mode is ENABLED!";
                    event.getLeft().add(details);
                }
            }
        }
    }
}