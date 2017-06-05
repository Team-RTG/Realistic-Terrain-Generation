package rtg.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import rtg.api.RTGAPI;
import rtg.reference.ModInfo;

public class RTGConfigGUI extends GuiConfig {

    private static final Configuration CONFIG = RTGAPI.config().getConfig();

    public RTGConfigGUI(GuiScreen parentScreen) {
        super(parentScreen, getElements(), ModInfo.MOD_ID, false, false, I18n.format("gui.config.title"));
    }

    //TODO: Localise config element tooltips
    private static List<IConfigElement> getElements() {
        List<IConfigElement> elements = Lists.newArrayList();
        CONFIG.getCategoryNames().forEach(cat -> {
            if (!CONFIG.getCategory(cat).isChild()) {
                elements.add(new ConfigElement(CONFIG.getCategory(cat)));
            }
        });
        return elements;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        if (CONFIG.hasChanged()) {CONFIG.save();}
    }
}