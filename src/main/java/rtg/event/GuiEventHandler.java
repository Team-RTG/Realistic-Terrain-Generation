package rtg.event;

import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rtg.api.util.Logger;
import rtg.world.WorldTypeRTG;

/**
 * Original class by Adubbz:
 *
 */


/**
 * Handles the automatic selection of the RTG world type when creating a new world.
 *
 * @author @Adubbz (https://github.com/Adubbz)
 *         <p>
 *         Source: https://github.com/Glitchfiend/BiomesOPlenty/blob/BOP-1.12.x-7.0.x/src/main/java/biomesoplenty/common/handler/GuiEventHandler.java
 *         Modified by: WhichOnesPink (https://github.com/whichonespink44)
 */
public class GuiEventHandler
{
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPreInitCreateWorld(GuiScreenEvent.InitGuiEvent.Pre event)
    {
        GuiScreen screenGui = event.getGui();

        if (screenGui instanceof GuiCreateWorld) {

            GuiCreateWorld createWorldGui = (GuiCreateWorld)screenGui;

            try {

                int selectedIndex = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, createWorldGui, "selectedIndex", "field_146331_K");

                // Do not change back when returning from the 'Customize' screen.
                if (selectedIndex == WorldType.DEFAULT.getId()) {
                    ReflectionHelper.setPrivateValue(GuiCreateWorld.class, createWorldGui, WorldTypeRTG.getInstance().getId(), "selectedIndex", "field_146331_K");
                }
            }
            catch (Exception e) {
                Logger.warn("Unable to access selectedIndex in GuiCreateWorld.", e.getMessage());
            }
        }
    }
}