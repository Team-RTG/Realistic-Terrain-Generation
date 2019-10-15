package rtg.event;

import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import rtg.RTGConfig;
import rtg.api.RTGAPI;
import rtg.client.WorldTypeMessageGUI;


@Mod.EventBusSubscriber(modid = RTGAPI.RTG_MOD_ID, value = Side.CLIENT)
public final class EventHandlerClient
{
    private EventHandlerClient() {}

    @SubscribeEvent(priority = EventPriority.LOWEST) // We want to be last so that our handler avoids race conditions with other mods
    public static void onGuiCreateWorld(final GuiOpenEvent event) {

        GuiScreen gui = event.getGui();
        if (gui instanceof GuiCreateWorld) {

            // Access transformed (private -> public); See: src/main/resources/META-INF/rtg_at.cfg
            String seed = ((GuiCreateWorld)gui).worldSeed;

            // we only display the world type notification if creating a new world, not when recreating from an existing one
            if (seed.isEmpty() && RTGConfig.worldTypeNotification()) {
                RTGConfig.toggleWorldTypeNotification();
                event.setGui(new WorldTypeMessageGUI(gui));
            }
        }
    }
}
