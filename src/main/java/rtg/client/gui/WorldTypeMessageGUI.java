package rtg.client.gui;

import java.io.File;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import static net.minecraft.util.EnumChatFormatting.DARK_RED;
import static net.minecraft.util.EnumChatFormatting.WHITE;

import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


/**
 * Displays a notification screen when creating a new world.
 *
 * @author @Adubbz (https://github.com/Adubbz)
 *         <p>
 *         Source: https://github.com/Glitchfiend/BiomesOPlenty/blob/BOP-1.7.10-2.1.x/src/main/java/biomesoplenty/client/gui/WorldTypeMessageGUI.java
 *         Modified by: WhichOnesPink (https://github.com/whichonespink44)
 */
public class WorldTypeMessageGUI extends GuiScreen {

    private static final ResourceLocation rtgLogoTexture = new ResourceLocation("rtg:textures/gui/rtg-logo-worldtype.png");
    private GuiScreen parentGuiScreen;
    private File nameHashFile;
    private String nameHash;

    public WorldTypeMessageGUI(GuiScreen parentGuiScreen, File nameHashFile, String nameHash) {

        this.parentGuiScreen = parentGuiScreen;

        this.nameHashFile = nameHashFile;
        this.nameHash = nameHash;
    }

    @Override
    public void initGui() {

        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 175, this.height - 24, 350, 20, I18n.format("OK")));
    }

    @Override
    public void onGuiClosed() {

        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(GuiButton button) {

        if (button.enabled) {
            if (button.id == 0) {
                try {
                    nameHashFile.createNewFile();
                    FileUtils.write(nameHashFile, nameHash + "StartupWarning".hashCode());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
        }
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {

        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRendererObj, "" + WHITE + StatCollector.translateToLocal("warning.rtgStartup1"), this.width / 2, 82, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "" + WHITE + StatCollector.translateToLocal("warning.rtgStartup2"), this.width / 2, 94, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "" + WHITE + StatCollector.translateToLocal("warning.rtgStartup3"), this.width / 2, 106, 0xFFFFFF);

        this.drawCenteredString(this.fontRendererObj, "" + WHITE + StatCollector.translateToLocal("warning.rtgStartup4"), this.width / 2, 132, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "" + WHITE + StatCollector.translateToLocal("warning.rtgStartup5"), this.width / 2, 144, 0xFFFFFF);

        this.drawCenteredString(this.fontRendererObj, "" + DARK_RED + StatCollector.translateToLocal("warning.rtgStartup6"), this.width / 2, 168, 0xFFFFFF);

        GL11.glEnable(GL11.GL_BLEND);
        this.mc.getTextureManager().bindTexture(rtgLogoTexture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawTexturedModalRect(this.width / 2 - 168 / 2, 0, 0, 0, 168, 80);

        GL11.glDisable(GL11.GL_BLEND);

        super.drawScreen(x, y, renderPartialTicks);
    }
}
