package rtg.config;

import java.io.File;

import rtg.config.arsmagica.ConfigAM;
import rtg.config.atg.ConfigATG;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.config.buildcraft.ConfigBC;
import rtg.config.chromaticraft.ConfigCC;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.config.highlands.ConfigHL;
import rtg.config.rtg.ConfigRTG;
import rtg.config.thaumcraft.ConfigTC;
import rtg.config.vanilla.ConfigVanilla;

public class ConfigManager
{
    
    public static File rtgConfigFile;
    public static File vanillaConfigFile;
    public static File bopConfigFile;
    public static File ebxlConfigFile;
    public static File ebConfigFile;
    public static File hlConfigFile;
    public static File tcConfigFile;
    public static File bcConfigFile;
    public static File amConfigFile;
    public static File atgConfigFile;
    public static File ccConfigFile;
    
    public static void init(String configpath)
    {
    
        rtgConfigFile = new File(configpath + "rtg.cfg");
        vanillaConfigFile = new File(configpath + "biomes/vanilla.cfg");
        bopConfigFile = new File(configpath + "biomes/biomesoplenty.cfg");
        ebxlConfigFile = new File(configpath + "biomes/extrabiomes.cfg");
        ebConfigFile = new File(configpath + "biomes/enhancedbiomes.cfg");
        hlConfigFile = new File(configpath + "biomes/highlands.cfg");
        tcConfigFile = new File(configpath + "biomes/thaumcraft.cfg");
        bcConfigFile = new File(configpath + "biomes/buildcraft.cfg");
        amConfigFile = new File(configpath + "biomes/arsmagica.cfg");
        atgConfigFile = new File(configpath + "biomes/atg.cfg");
        ccConfigFile = new File(configpath + "biomes/chromaticraft.cfg");
        
        ConfigRTG.init(rtgConfigFile);
        
        ConfigVanilla.init(vanillaConfigFile);
        
        ConfigBOP.init(bopConfigFile);
        ConfigEBXL.init(ebxlConfigFile);
        ConfigEB.init(ebConfigFile);
        ConfigHL.init(hlConfigFile);
        ConfigTC.init(tcConfigFile);
        ConfigBC.init(bcConfigFile);
        ConfigAM.init(amConfigFile);
        ConfigATG.init(atgConfigFile);
        ConfigCC.init(ccConfigFile);
    }
}
