package enhancedbiomes.achievements;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;

import java.io.File;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.biome.EnhancedBiomesSnow;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityEvent;

public class EnhancedBiomesAchievements
{
	public static AchievementPage enhancedBiomesPage;
	
	public static Achievement ach;
	public static Achievement achSnow;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Achievements.cfg");
		Configuration config = new Configuration(configFile);
		config.load();
		
		config.save();
	}
	
	public static void load()
	{
		ach = new Achievement("bbach", "EnhancedBiomes", 0, 0, EnhancedBiomesBlocks.saplingEB, AchievementList.openInventory);
		achSnow = new Achievement("bbachSnow", "EnhancedBiomesSnow", 1, -1, Blocks.snow, ach);
		
		achDesc();
				
		enhancedBiomesPage = new AchievementPage("Enhanced Biomes", ach, achSnow);
		
		AchievementPage.registerAchievementPage(enhancedBiomesPage);
	}
	
	public static void achDesc()
	{
		LanguageRegistry.instance().addStringLocalization("achievement.EnhancedBiomes", "en_US", "A Enhanced Biome");
		LanguageRegistry.instance().addStringLocalization("achievement.EnhancedBiomes.desc", "en_US", "Locate a Enhanced Biome");
		
		LanguageRegistry.instance().addStringLocalization("achievement.EnhancedBiomesSnow", "en_US", "Enhanced Snow");
		LanguageRegistry.instance().addStringLocalization("achievement.EnhancedBiomesSnow.desc", "en_US", "Find a new snow biome");
	}
	
	/*@ForgeSubscribe
	public void chunkEntered(EntityEvent.EnteringChunk event)
	{
		if (event.entity != null)
		{
			if (event.entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)event.entity;
				World world = player.worldObj;

				int x = MathHelper.floor_double(player.posX);
				int y = MathHelper.floor_double(player.boundingBox.minY);
				int z = MathHelper.floor_double(player.posZ);

				int biomeID = world.getBiomeGenForCoords(x, z).biomeID;
				
				if (biomeID == EnhancedBiomesSnow.biomeAlpine.biomeID)
				{
					player.addStat(ach, 1);
					player.addStat(achSnow, 1);
				}
			}
		}
	}*/
}