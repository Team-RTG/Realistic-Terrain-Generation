package enhancedbiomes.handlers;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.helpers.ColorizerFoliageBirch;

public class SeasonTickHandler 
{
	int su = -1;
	public static int season = 0;
	
	@SubscribeEvent
	public void onWorldTick(RenderTickEvent event) {
		int day = 0;
		if(MinecraftServer.getServer() == null) {
			if(event.side == Side.CLIENT && Minecraft.getMinecraft().theWorld != null) {
				day = (int) ((Minecraft.getMinecraft().theWorld.getWorldTime() + 6000) / 24000);
			}
			else return;		
		}
		else if(MinecraftServer.getServer().worldServers.length <= 0) {
			if(event.side == Side.CLIENT && Minecraft.getMinecraft().theWorld != null) {
				day = (int) ((Minecraft.getMinecraft().theWorld.getWorldTime() + 6000) / 24000);
			}
			else return;		
		}
		else day = (int) ((MinecraftServer.getServer().worldServers[0].getWorldTime() + 6000) / 24000);
		
		season = (day / EnhancedBiomesMod.seasonLength) % 4;		
		float[] temps = EnhancedBiomesMod.biomeTempsList;
		if(season == 0 && su != 0) //Spring
		{
			for(int a = 0; a < BiomeGenBase.getBiomeGenArray().length; a++)
			{
				if(BiomeGenBase.getBiomeGenArray()[a] != null) BiomeGenBase.getBiomeGenArray()[a].temperature = temps[a];
			}
			su = 0;
			if(event.side == Side.CLIENT)
			{
				setFoliageColourMap("enhancedbiomes:textures/colormap/foliageSpring.png");
				ColorizerFoliageBirch.birchColour = 0x80E055;
			}
			updateChunks(event);
		}
		else if(season == 1 && su != 1) //Summer
		{
			for(int a = 0; a < BiomeGenBase.getBiomeGenArray().length; a++)
			{
				if(BiomeGenBase.getBiomeGenArray()[a] != null) BiomeGenBase.getBiomeGenArray()[a].temperature = getTempChange(temps[a], 0.3F);
			}
			su = 1;
			if(event.side == Side.CLIENT)
			{
				setFoliageColourMap("enhancedbiomes:textures/colormap/foliageSummer.png");
				ColorizerFoliageBirch.birchColour = 0x80A755;
			}
			updateChunks(event);
		}
		else if(season == 2 && su != 2) //Autumn
		{
			for(int a = 0; a < BiomeGenBase.getBiomeGenArray().length; a++)
			{
				if(BiomeGenBase.getBiomeGenArray()[a] != null) BiomeGenBase.getBiomeGenArray()[a].temperature = temps[a];
			}
			su = 2;
			if(event.side == Side.CLIENT)
			{
				setFoliageColourMap("enhancedbiomes:textures/colormap/foliageAutumn.png");
				ColorizerFoliageBirch.birchColour = 0xFACF41;
			}
			updateChunks(event);
		}
		else if(season == 3 && su != 3) //Winter
		{
			for(int a = 0; a < BiomeGenBase.getBiomeGenArray().length; a++)
			{
				if(BiomeGenBase.getBiomeGenArray()[a] != null) BiomeGenBase.getBiomeGenArray()[a].temperature = getTempChange(temps[a], -0.3F);
			}
			su = 3;
			if(event.side == Side.CLIENT)
			{
				setFoliageColourMap("enhancedbiomes:textures/colormap/foliageWinter.png");
				ColorizerFoliageBirch.birchColour = 0x80A790;
			}
			updateChunks(event);
		}
	}

	private float getTempChange(float base, float change) 
	{
		float result = base + change;
		if((result >= 0.1F && result < 0.2F) || result < 0.0F) result = 0.0F;
		if(base >= 0.2F && result == 0.0F) result = 0.2F;
		return result;
	}
	
	private void updateChunks(TickEvent event)
	{
		if(event.side == Side.CLIENT)
		{
			Minecraft.getMinecraft().renderGlobal.loadRenderers();
			System.out.println("Re-rendering for updated season");
		}/*
		else if(event.side == Side.SERVER)
		{
			for(int a = 0; a < MinecraftServer.getServer().worldServers.length; a++)
			{
				WorldServer ws = MinecraftServer.getServer().worldServers[a];
				for(int b = 0; b < ws.playerEntities.size(); b++)
				{
					EntityPlayer player = (EntityPlayer) ws.playerEntities.get(b);
					player.
				}
			}
		}*/
	}
	
	private void setFoliageColourMap(String loc)
	{
		System.out.println("Trying to change foliage colouriser");
		try {
			ColorizerFoliage.setFoliageBiomeColorizer(TextureUtil.readImageData(Minecraft.getMinecraft().getResourceManager(), new ResourceLocation(loc)));
			System.out.println("Set foliage colouriser to " + loc);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to set foliage colouriser to " + loc);
		}
	}
}
