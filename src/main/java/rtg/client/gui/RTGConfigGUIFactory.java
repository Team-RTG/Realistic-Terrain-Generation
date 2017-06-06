package rtg.client.gui;


import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class RTGConfigGUIFactory implements IModGuiFactory
{
    public static final String LOCATION = "rtg.client.gui.RTGConfigGUIFactory";
    @Override public void initialize(Minecraft minecraftInstance) {}
    @Override public Class<? extends GuiScreen> mainConfigGuiClass() {return RTGConfigGUI.class;}
    @Override public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {return null;}
    @SuppressWarnings("deprecation")
    @Override public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {return null;}
}
