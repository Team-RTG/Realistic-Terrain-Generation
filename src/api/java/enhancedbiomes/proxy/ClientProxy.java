package enhancedbiomes.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import enhancedbiomes.gui.EnhancedBiomesGUIDisplay;
import enhancedbiomes.handlers.KeyBindingHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init() 
	{  
		FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
		MinecraftForge.EVENT_BUS.register(new EnhancedBiomesGUIDisplay(Minecraft.getMinecraft()));
	}
}