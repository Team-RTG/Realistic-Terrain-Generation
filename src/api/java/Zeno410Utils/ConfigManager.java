
package Zeno410Utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Configuration;

public class ConfigManager<Type extends Settings> {
    static Logger logger = new Zeno410Logger("ConfigManager").logger();
    public final static String CONFIG_DIRECTORY = "worldSpecificConfig";
    private Configuration general;
    private File generalConfigFile;
    private Type settings;
    private File worldConfigFile;
    private Configuration worldSpecific;

    public ConfigManager(Configuration general, Type settings, File generalFile) {
        this.general = general;
        this.settings = settings;
        this.generalConfigFile  = generalFile;
    }

    private boolean usable(File tested) {
        return tested != null;
    }

    public void saveWorldSpecific() {
        worldSpecific.save();
    }

    private void setWorldConfigFile(File newFile) {
        if ((worldConfigFile== null)||(!newFile.getAbsolutePath().equals(worldConfigFile.getAbsolutePath()))) {
            worldConfigFile = newFile;
            if (usable(worldConfigFile)) {
                // usable world
                    logger.info(worldConfigFile.getPath());
                if (newFile.exists()) {
                    worldSpecific = new Configuration(worldConfigFile);
                    logger.info("exists ");
                    worldSpecific.load();
                    settings.readFrom(worldSpecific);
                } else {
                    logger.info("doesn't exist");
                    worldSpecific = new Configuration(worldConfigFile);
                    // else we use the default;
                    settings.readFrom(general);
                    settings.copyTo(worldSpecific);
                }
                worldSpecific.save();
            } else {
                logger.info("null file");
                worldSpecific = null;
                settings.readFrom(general);
            }
        }
    }

    public void setWorldFile(File newFile) {
        // this is the world save directory
        File configDirectory = new File(newFile,CONFIG_DIRECTORY);
        configDirectory.mkdir();

        String configName = generalConfigFile.getPath();
        String generalConfigDirectoryName = generalConfigFile.getParentFile().getPath();
        String detailName = configName.substring(generalConfigDirectoryName.length()+1);
        logger.info("Filename "+detailName);
        File localConfigFile = new File(configDirectory,detailName);
        setWorldConfigFile(localConfigFile);
    }

    public void clearWorldFile() {
        worldConfigFile = null;
        worldSpecific = null;
        logger.info("clearing ");
    }

    public void setWorldFile(WorldServer server) {
        setWorldFile(server.getChunkSaveLocation());
    }

/**
 * This class manages overall and world-specific configs
 * @author Zeno410
 */
/*public class ConfigManager<Type extends Settings> {
    static Logger logger = new Zeno410Logger("ConfigManager").logger();
    public final static String CONFIG_DIRECTORY = "worldSpecificConfig";
    private Configuration general;
    private File generalConfigFile;
    private File worldConfigFile;
    private Configuration worldSpecific;
    private Type settings;

    public ConfigManager(Configuration general, Type settings, File generalFile) {
        this.general = general;
        this.settings = settings;
        this.generalConfigFile  = generalFile;
    }

    private boolean usable(File tested) {
        return tested != null;
    }

    
    private void setWorldConfigFile(File newFile) {
        if ((worldConfigFile== null)||(!newFile.getAbsolutePath().equals(worldConfigFile.getAbsolutePath()))) {
            worldConfigFile = newFile;
            if (usable(worldConfigFile)) {
                // usable world
                    logger.info(worldConfigFile.getPath());
                if (newFile.exists()) {
                    worldSpecific = new Configuration(worldConfigFile);
                    logger.info("exists ");
                    worldSpecific.load();
                    settings.readFrom(worldSpecific);
                } else {
                    logger.info("doesn't exist");
                    worldSpecific = new Configuration(worldConfigFile);
                    // else we use the default;
                    settings.readFrom(general);
                    settings.copyTo(worldSpecific);
                }
                worldSpecific.save();
            } else {
                logger.info("null file");
                worldSpecific = null;
                settings.readFrom(general);
            }
        }
    }

    public void setWorldFile(File newFile) {
        // this is the world save directory
        String configDirectoryName = newFile.getAbsoluteFile()+File.separator+CONFIG_DIRECTORY;
        logger.info("Directory name "+configDirectoryName);
        File configDirectory = fileNamed(configDirectoryName);
        configDirectory.mkdir();

        String configName = generalConfigFile.getPath();
        String generalConfigDirectoryName = generalConfigFile.getParentFile().getPath();
        String detailName = configName.substring(generalConfigDirectoryName.length()+1);
        logger.info("Filename "+detailName);
        String localConfigFileName = configDirectoryName + File.separator+ detailName;
        File localConfigFile = fileNamed(localConfigFileName);
        setWorldConfigFile(localConfigFile);
    }

    public String lastFile(String multipath) {
        String [] parts = multipath.split(File.separator);
        return parts[parts.length -1];
    }

    public static File fileNamed(String output) {
        try {
                URI outputURI = new URI(("file:///"+ output.replaceAll(" ", "%20")));
                File outputFile = new File(outputURI);

                if (!outputFile.exists()) {
                    createParents(outputFile);
                    outputFile.createNewFile();

                }
                return outputFile;
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException(e);
            }
    }

    private static void createParents(File outputFile) {
        File parent = outputFile.getParentFile();
        if(!parent.exists()){
            parent.mkdirs();
        }

    }
}*/
}
