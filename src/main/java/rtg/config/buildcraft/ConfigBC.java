package rtg.config.buildcraft;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.buildcraft.config.BiomeConfigBC;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigBC
{
    
    public static Configuration config;
    
    public static boolean generateBCBiomes = true;

    public static boolean villageBCDesertOilField = false;
    public static boolean villageBCOceanOilField = false;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            generateBCBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateBCBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);

            villageBCDesertOilField = config.getBoolean(formatConfig("villageBCDesertOilField"), "Villages", villageBCDesertOilField, "");
            villageBCOceanOilField = config.getBoolean(formatConfig("villageBCOceanOilField"), "Villages", villageBCOceanOilField, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigBC.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "RTG has had a problem loading BuildCraft configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
    
    private static String formatConfig(String s)
    {
        String returnString = s;        
        
        returnString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(returnString), " ");

        if (s.startsWith("village")) {
            returnString = StringUtils.replace(returnString, "village", "Allow villages to generate in", 1);
        }
        
        return returnString;
    }
}
