package rtg.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import rtg.api.RTGAPI;


public final class WorldTypeMessageGUI extends GuiScreen
{
    private static final ResourceLocation LOGO_LOCATION = new ResourceLocation(RTGAPI.RTG_MOD_ID, "textures/gui/rtg-logo-worldtype.png");
    private static final String LANG_KEY = "gui.createWorld.worldtype";

    private GuiScreen parentScreen;

    public WorldTypeMessageGUI(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {

        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 25, this.height - 24, 50, 20, I18n.format("gui.back")));
    }

    @Override
    protected void actionPerformed(GuiButton button) {

        if (button.enabled && button.id == 0) {
            this.mc.displayGuiScreen(this.parentScreen);
        }
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {

        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format(LANG_KEY + ".line1"), this.width / 2, 80, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, I18n.format(LANG_KEY + ".line2"), this.width / 2, 96, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, I18n.format(LANG_KEY + ".line3", I18n.format("gui.createWorld.worldtypename")), this.width / 2, 112, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, I18n.format(LANG_KEY + ".line4"), this.width / 2, 144, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, I18n.format(LANG_KEY + ".line5"), this.width / 2, 160, 0xFFFFFF);

        GL11.glEnable(GL11.GL_BLEND);
        this.mc.getTextureManager().bindTexture(LOGO_LOCATION);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawTexturedModalRect(this.width / 2 - 168 / 2, 0, 0, 0, 168, 80);

        GL11.glDisable(GL11.GL_BLEND);

        super.drawScreen(x, y, renderPartialTicks);
    }
}
