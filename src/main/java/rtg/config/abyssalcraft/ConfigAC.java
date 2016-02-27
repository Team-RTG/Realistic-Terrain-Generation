package rtg.config.abyssalcraft;

import net.minecraftforge.common.config.Configuration;
import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.BiomeConfigManager;
import rtg.util.Logger;

import java.io.File;

public class ConfigAC
{
    
    public static Configuration config;
    public static Configuration villageConfig;

    public static void init(File configFile)
    {
    
        config = new Configuration(configFile);
        
        try
        {
            config.load();
            
            BiomeConfigManager.setBiomeConfigsFromUserConfigs(BiomeConfigAC.getBiomeConfigs(), config);
            
        } catch (Exception e)
        {
            Logger.error("RTG has had a problem loading AC configuration.");
        } finally
        {
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }
    public static void initVillage(File configFile) {
        villageConfig = new Configuration(configFile);

        try
        {
            villageConfig.load();

            BiomeConfigManager.setVillageConfigsFromUserConfigs(BiomeConfigVanilla.getBiomeConfigs(), villageConfig);
        }
        catch (Exception e)
        {
            Logger.error("RTG has had a problem loading AC Village configuration. %s", e);
        }
        finally
        {
            if (villageConfig.hasChanged())
            {
                villageConfig.save();
            }
        }
    }
}
