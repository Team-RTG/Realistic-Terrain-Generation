package rtg.config;

import java.io.File;

import rtg.config.arsmagica.ConfigAM;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.config.buildcraft.ConfigBC;
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
    
    public static void init(String configpath)
    {
    
        rtgConfigFile = new File(configpath + "rtg.cfg");
        vanillaConfigFile = new File(configpath + "vanilla.cfg");
        bopConfigFile = new File(configpath + "mods/biomesoplenty.cfg");
        ebxlConfigFile = new File(configpath + "mods/extrabiomes.cfg");
        ebConfigFile = new File(configpath + "mods/enhancedbiomes.cfg");
        hlConfigFile = new File(configpath + "mods/highlands.cfg");
        tcConfigFile = new File(configpath + "mods/thaumcraft.cfg");
        bcConfigFile = new File(configpath + "mods/buildcraft.cfg");
        amConfigFile = new File(configpath + "mods/arsmagica.cfg");
        
        ConfigRTG.init(rtgConfigFile);
        ConfigVanilla.init(vanillaConfigFile);
        ConfigBOP.init(bopConfigFile);
        ConfigEBXL.init(ebxlConfigFile);
        ConfigEB.init(ebConfigFile);
        ConfigHL.init(hlConfigFile);
        ConfigTC.init(tcConfigFile);
        ConfigBC.init(bcConfigFile);
        ConfigAM.init(amConfigFile);
    }
}
