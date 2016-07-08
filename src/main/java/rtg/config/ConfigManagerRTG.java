package rtg.config;

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

import java.io.File;

public class ConfigManagerRTG
{
    public static void init(String confpath)
    {
        final String biomedir = confpath + "biomes/";

        ConfigRTG.init(     new File(confpath + "rtg.cfg"));
        ConfigVanilla.init( new File(biomedir + "vanilla.cfg"));
        ConfigBOP.init(     new File(biomedir + "biomesoplenty.cfg"));
        ConfigEBXL.init(    new File(biomedir + "extrabiomes.cfg"));
        ConfigEB.init(      new File(biomedir + "enhancedbiomes.cfg"));
        ConfigHL.init(      new File(biomedir + "highlands.cfg"));
        ConfigTC.init(      new File(biomedir + "thaumcraft.cfg"));
        ConfigBC.init(      new File(biomedir + "buildcraft.cfg"));
        ConfigAM.init(      new File(biomedir + "arsmagica.cfg"));
        ConfigATG.init(     new File(biomedir + "atg.cfg"));
        ConfigCC.init(      new File(biomedir + "chromaticraft.cfg"));
        ConfigGC.init(      new File(biomedir + "growthcraft.cfg"));
        ConfigVAMP.init(    new File(biomedir + "vampirism.cfg"));
        ConfigAC.init(      new File(biomedir + "abyssalcraft.cfg"));
        ConfigRW.init(      new File(biomedir + "ridiculousworld.cfg"));
        ConfigTOFU.init(    new File(biomedir + "tofucraft.cfg"));
        ConfigFN.init(      new File(biomedir + "forgottennature.cfg"));
        ConfigIC.init(      new File(biomedir + "icecreammod.cfg"));
        ConfigIDT.init(     new File(biomedir + "industrialtechnologies.cfg"));
    }
}
