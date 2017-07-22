package rtg.event;

import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.WorldType;

import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import rtg.RTG;
import rtg.api.util.Accessor;
import rtg.api.util.Logger;

/**
 * Original class by Adubbz:
 *
 */


/**
 * Handles the automatic selection of the RTG world type when creating a new world.
 *
 * @author @Adubbz (https://github.com/Adubbz)
 *         <p>
 *         Source: https://github.com/Glitchfiend/BiomesOPlenty/blob/962f1d0db5a8f04d601215ea79944254bcbd48f0/src/main/java/biomesoplenty/common/handler/GuiEventHandler.java
 *         Modified by: WhichOnesPink (https://github.com/whichonespink44)
 */
@SideOnly(Side.CLIENT)
public class GuiEventHandler {

    public static GuiEventHandler instance = new GuiEventHandler();

    @SubscribeEvent
    public void onPreInitCreateWorld(GuiScreenEvent.InitGuiEvent.Pre event) {

        GuiScreen screenGui = event.getGui();

        if (screenGui instanceof GuiCreateWorld) {

            GuiCreateWorld createWorldGui = (GuiCreateWorld)screenGui;

            try {

                Accessor<GuiCreateWorld, Integer> selectedIndexField = new Accessor<>("selectedIndex", "field_146331_K");

                int selectedIndex = selectedIndexField.get(createWorldGui);

                // Do not change back when returning from the 'Customize' screen.
                if (selectedIndex == WorldType.DEFAULT.getWorldTypeID()) {
                    selectedIndexField.setField(createWorldGui, RTG.worldtype.getWorldTypeID());
                }
            }
            catch (Exception e) {
                Logger.warn("Unable to access selectedIndex in GuiCreateWorld.", e.getMessage());
            }
        }
    }
}
