package rtg.config.arsmagica;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import rtg.api.biome.arsmagica.config.BiomeConfigAM;
import rtg.config.BiomeConfigManager;
import rtg.world.biome.BiomeBase;
import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.config.Configuration;

public class ConfigAM
{
    
    public static Configuration config;
    
    public static boolean generateAMBiomes = true;

    public static boolean villageAMWitchwoodForest = false;
    
    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            generateAMBiomes = config.getBoolean("Allow biomes from this mod to generate", "Allow mod biomes", generateAMBiomes, "If TRUE, uses the individual biome settings below. If FALSE, disables all biomes from this mod." + Configuration.NEW_LINE);

            villageAMWitchwoodForest = config.getBoolean(formatConfig("villageAMWitchwoodForest"), "Villages", villageAMWitchwoodForest, "");
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigAM.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "RTG has had a problem loading AM configuration.");
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
