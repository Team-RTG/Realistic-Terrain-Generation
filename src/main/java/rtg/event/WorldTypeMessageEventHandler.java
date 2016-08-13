package rtg.event;

import java.io.File;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.io.FileUtils;

import rtg.client.gui.WorldTypeMessageGUI;

/**
 * Handles the display of the notification screen when creating a new world.
 *
 * @author @Adubbz (https://github.com/Adubbz)
 *         <p>
 *         Source: https://github.com/Glitchfiend/BiomesOPlenty/blob/BOP-1.7.10-2.1.x/src/main/java/biomesoplenty/common/eventhandler/client/gui/WorldTypeMessageEventHandler.java
 *         Modified by: WhichOnesPink (https://github.com/whichonespink44)
 */
public class WorldTypeMessageEventHandler {

    public static WorldTypeMessageEventHandler instance = new WorldTypeMessageEventHandler();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void openCreateWorld(GuiOpenEvent event) {

        if (event.getGui() instanceof GuiCreateWorld) {
            File nameHashFile = new File(Minecraft.getMinecraft().mcDataDir.getPath() + File.separator + "settings.rtg");
            String nameHash = "" + Minecraft.getMinecraft().getSession().getUsername().hashCode();

            try {
                if (!nameHashFile.exists() || !FileUtils.readFileToString(nameHashFile).contains(nameHash + "StartupWarning".hashCode())) {
                    event.setGui(new WorldTypeMessageGUI(event.getGui(), nameHashFile, nameHash));
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            MinecraftForge.EVENT_BUS.unregister(instance);
        }
    }
}
