package enhancedbiomes.gui;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.handlers.SeasonTickHandler;

public class EnhancedBiomesGUIDisplay extends Gui
{
	private Minecraft mc;

	public EnhancedBiomesGUIDisplay(Minecraft mc)
	{
		super();
		this.mc = mc;
	}

	@SubscribeEvent()
	public void onRenderOverlay(RenderGameOverlayEvent event)
	{
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{      
			return;
		}

		if(!this.mc.thePlayer.getEntityData().getBoolean("biomeInfoDisplay"))
		{
			return;
		}
		
		int pos = this.mc.thePlayer.getEntityData().getInteger("biomeInfoPos");
		
		BiomeGenBase currentBiome = this.mc.theWorld.getBiomeGenForCoords((int)this.mc.thePlayer.posX, (int)this.mc.thePlayer.posZ);
		
		ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        String nameid = "Biome: " + currentBiome.biomeName + ", ID: " + currentBiome.biomeID;
        String top = "Top Block: " + currentBiome.topBlock.getLocalizedName();
        String filler = "Filler Block: " + currentBiome.fillerBlock.getLocalizedName();
        String temp = "Temperature: " + currentBiome.temperature;
        String rootvar = "Terrain Type: " + getHeightDesc(currentBiome.rootHeight, currentBiome.heightVariation) + ", " + getHillDesc(currentBiome.rootHeight, currentBiome.heightVariation);
        EntityClientPlayerMP player = this.mc.thePlayer;
        String xyz = "X: " + (int)player.posX + "  Y: " + (int)player.posY + "  Z: " + (int)player.posZ;
        String season = SeasonTickHandler.season == 0 ? "Spring" : SeasonTickHandler.season == 1 ? "Summer" : 
        				SeasonTickHandler.season == 2 ? "Autumn" : SeasonTickHandler.season == 3 ? "Winter" : "Null";/*
        String tideHeight = "Tide Height: " + EnhancedBiomesMod.getTideHeight(this.mc.theWorld.getWorldTime());
        String stoneNoise = "" + EnhancedBiomesMod.getStoneNoiseForCoords((int) player.posX, (int) player.posZ);*/
        
        switch(pos)		
        {
			case 0:
				this.drawString(this.mc.fontRenderer, nameid, 2, 2, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, top, 2, 12, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, filler, 2, 22, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, temp, 2, 32, getColourForTemp(currentBiome.temperature));
				this.drawString(this.mc.fontRenderer, rootvar, 2, 42, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, xyz, 2, 52, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, season, 2, 62, 0xFFFFFF);/*
				this.drawString(this.mc.fontRenderer, tideHeight, 2, 72, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, stoneNoise, 2, 82, 0xFFFFFF);*/
				break;
			case 1:
				this.drawString(this.mc.fontRenderer, nameid, width - this.mc.fontRenderer.getStringWidth(nameid) - 2, 2, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, top, width - this.mc.fontRenderer.getStringWidth(top) - 2, 12, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, filler, width - this.mc.fontRenderer.getStringWidth(filler) - 2, 22, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, temp, width - this.mc.fontRenderer.getStringWidth(temp) - 2, 32, getColourForTemp(currentBiome.temperature));
				this.drawString(this.mc.fontRenderer, rootvar, width - this.mc.fontRenderer.getStringWidth(rootvar) - 2, 42, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, xyz, width - this.mc.fontRenderer.getStringWidth(xyz) - 2, 52, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, season, width - this.mc.fontRenderer.getStringWidth(season) - 2, 62, 0xFFFFFF);
				break;
			case 2:
				this.drawString(this.mc.fontRenderer, nameid, 2, height - 70, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, top, 2, height - 60, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, filler, 2, height - 50, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, temp, 2, height - 40, getColourForTemp(currentBiome.temperature));
				this.drawString(this.mc.fontRenderer, rootvar, 2, height - 30, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, xyz, 2, height - 20, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, season, 2, height - 10, 0xFFFFFF);
				break;
			case 3:
				this.drawString(this.mc.fontRenderer, nameid, width - this.mc.fontRenderer.getStringWidth(nameid) - 2, height - 70, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, top, width - this.mc.fontRenderer.getStringWidth(top) - 2, height - 60, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, filler, width - this.mc.fontRenderer.getStringWidth(filler) - 2, height - 50, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, temp, width - this.mc.fontRenderer.getStringWidth(temp) - 2, height - 40, getColourForTemp(currentBiome.temperature));
				this.drawString(this.mc.fontRenderer, rootvar, width - this.mc.fontRenderer.getStringWidth(rootvar) - 2, height - 30, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, xyz, width - this.mc.fontRenderer.getStringWidth(xyz) - 2, height - 20, 0xFFFFFF);
				this.drawString(this.mc.fontRenderer, season, width - this.mc.fontRenderer.getStringWidth(season) - 2, height - 10, 0xFFFFFF);
				break;
		}/**/
	}
	
	public static int getColourForTemp(float temp)
	{
		return temp < 0.2 ? 0x0543E3 : temp < 0.4 ? 0x058046 : temp < 1.0 ? 0xFAA00F : 0xFF0000;
	}
	
	public static String getHeightDesc(float root, float var)
	{
		if(root <= -0.8F) return "Oceanic";
		else if(root <= 0.0F) return "Wetland";
		else if(root <= 0.4F) return "Low";
		else if(root <= 1.0F) return "Medium";
		else if(root <= 1.5F) return "High";
		return "Alpine";
	}
	
	public static String getHillDesc(float root, float var)
	{
		if(var <= 0.2F) return "Flat";
		else if(var <= 0.5F) return "Hills";
		else if(var <= 0.8F) return "Mountainous";
		return "Sheer";
	}
}