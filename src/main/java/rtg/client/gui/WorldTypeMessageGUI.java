package rtg.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import rtg.RTG;
import rtg.api.RTGAPI;


@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = RTG.MOD_ID, value = Side.CLIENT)
public final class WorldTypeMessageGUI extends GuiScreen {

    private static final WorldTypeMessageGUI INSTANCE;
    private static final ResourceLocation LOGO_LOCATION = new ResourceLocation(RTG.MOD_ID, "textures/gui/rtg-logo-worldtype.png");
    private static final String LANG_KEY = "gui.createWorld.worldtype";

    static {
        INSTANCE = new WorldTypeMessageGUI();
    }

    private GuiScreen parentScreen;

    private WorldTypeMessageGUI() {
    }

    @SubscribeEvent
    public static void openCreateWorld(GuiOpenEvent event) {

        if (RTGAPI.config().RTG_WORLDTYPE_NOTIFICATION.getBoolean() && event.getGui() instanceof GuiCreateWorld) {

            RTGAPI.config().RTG_WORLDTYPE_NOTIFICATION.set(false);
            RTGAPI.config().getConfig().save();

            INSTANCE.setParentScreen(event.getGui());
            event.setGui(INSTANCE);
        }
    }

    private void setParentScreen(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {

        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 175, this.height - 24, 350, 20, I18n.format("gui.done")));
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
        this.drawCenteredString(this.fontRenderer, TextFormatting.WHITE + I18n.format(LANG_KEY + ".line1"), this.width / 2, 82, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, TextFormatting.WHITE + I18n.format(LANG_KEY + ".line2"), this.width / 2, 94, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, TextFormatting.WHITE + I18n.format(LANG_KEY + ".line3"), this.width / 2, 106, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, TextFormatting.WHITE + I18n.format(LANG_KEY + ".line4"), this.width / 2, 132, 0xFFFFFF);
        this.drawCenteredString(this.fontRenderer, TextFormatting.WHITE + I18n.format(LANG_KEY + ".line5"), this.width / 2, 144, 0xFFFFFF);

        GL11.glEnable(GL11.GL_BLEND);
        this.mc.getTextureManager().bindTexture(LOGO_LOCATION);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawTexturedModalRect(this.width / 2 - 168 / 2, 0, 0, 0, 168, 80);

        GL11.glDisable(GL11.GL_BLEND);

        super.drawScreen(x, y, renderPartialTicks);
    }
}
