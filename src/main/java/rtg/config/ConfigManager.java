package rtg.config;

import java.io.File;

import rtg.config.abyssalcraft.ConfigAC;
import rtg.config.arsmagica.ConfigAM;
import rtg.config.atg.ConfigATG;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.config.buildcraft.ConfigBC;
import rtg.config.chromaticraft.ConfigCC;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.config.forgottennature.ConfigFN;
import rtg.config.growthcraft.ConfigGC;
import rtg.config.highlands.ConfigHL;
import rtg.config.icmod.ConfigIC;
import rtg.config.idt.ConfigIDT;
import rtg.config.ridiculousworld.ConfigRW;
import rtg.config.rtg.ConfigRTG;
import rtg.config.thaumcraft.ConfigTC;
import rtg.config.tofucraft.ConfigTOFU;
import rtg.config.vampirism.ConfigVAMP;
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
    public static File gcConfigFile;
    public static File vampConfigFile;
    public static File acConfigFile;
    public static File rwConfigFile;
    public static File tofuConfigFile;
    public static File fnConfigFile;
    public static File icConfigFile;
    public static File idtConfigFile;

    private ConfigRTG configRTG = new ConfigRTG();
    public ConfigRTG rtg() {
        return configRTG;
    }
    
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
        gcConfigFile = new File(configpath + "biomes/growthcraft.cfg");
        vampConfigFile = new File(configpath + "biomes/vampirism.cfg");
        acConfigFile = new File(configpath + "biomes/abyssalcraft.cfg");
        rwConfigFile = new File(configpath + "biomes/ridiculousworld.cfg");
        tofuConfigFile = new File(configpath + "biomes/tofucraft.cfg");
        fnConfigFile = new File(configpath + "biomes/forgottennature.cfg");
        icConfigFile = new File(configpath + "biomes/icecreammod.cfg");
        idtConfigFile = new File(configpath + "biomes/industrialtechnologies.cfg");
        
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
        ConfigGC.init(gcConfigFile);
        ConfigVAMP.init(vampConfigFile);
        ConfigAC.init(acConfigFile);
        ConfigRW.init(rwConfigFile);
        ConfigTOFU.init(tofuConfigFile);
        ConfigFN.init(fnConfigFile);
        ConfigIC.init(icConfigFile);
        ConfigIDT.init(idtConfigFile);
    }
}
