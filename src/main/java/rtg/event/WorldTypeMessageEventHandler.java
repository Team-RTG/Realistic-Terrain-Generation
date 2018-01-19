package rtg.event;

import net.minecraft.client.gui.GuiCreateWorld;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import rtg.client.gui.WorldTypeMessageGUI;

/**
 * Handles the display of the notification screen when creating a new world.
 */
@SideOnly(Side.CLIENT)
public class WorldTypeMessageEventHandler {

    @SubscribeEvent
    public void openCreateWorld(GuiOpenEvent event) {

        if (event.getGui() instanceof GuiCreateWorld) {

            event.setGui(new WorldTypeMessageGUI(event.getGui()));
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
