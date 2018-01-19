package rtg.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import rtg.api.RTGAPI;

/**
 * Displays a notification screen when creating a new world.
 */
@SideOnly(Side.CLIENT)
public class WorldTypeMessageGUI extends GuiScreen {

    private final GuiScreen parentGuiScreen;

    public WorldTypeMessageGUI(GuiScreen parentGuiScreen) {

        this.parentGuiScreen = parentGuiScreen;
    }

    @Override
    public void initGui() {

        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 175, this.height - 24, 350, 20, I18n.format("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton button) {

        if (button.enabled && button.id == 0) {
            RTGAPI.config().ENABLE_WORLD_TYPE_NOTIFICATION_SCREEN.set(false);
            RTGAPI.config().getConfig().save();
            this.mc.displayGuiScreen(this.parentGuiScreen);
        }
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {

        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, TextFormatting.WHITE    + I18n.format("warning.rtgStartup1"), this.width / 2, 82, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, TextFormatting.WHITE    + I18n.format("warning.rtgStartup2"), this.width / 2, 94, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, TextFormatting.WHITE    + I18n.format("warning.rtgStartup3"), this.width / 2, 106, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, TextFormatting.WHITE    + I18n.format("warning.rtgStartup4"), this.width / 2, 132, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, TextFormatting.WHITE    + I18n.format("warning.rtgStartup5"), this.width / 2, 144, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, TextFormatting.DARK_RED + I18n.format("warning.rtgStartup6"), this.width / 2, 168, 0xFFFFFF);

        GL11.glEnable(GL11.GL_BLEND);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("rtg:textures/gui/rtg-logo-worldtype.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawTexturedModalRect(this.width / 2 - 168 / 2, 0, 0, 0, 168, 80);

        GL11.glDisable(GL11.GL_BLEND);

        super.drawScreen(x, y, renderPartialTicks);
    }
}
