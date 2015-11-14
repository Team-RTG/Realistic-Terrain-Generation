package rtg;

import rtg.config.ConfigManager;
import rtg.debug.DebugHandler;
import rtg.event.EventManagerRTG;
import rtg.proxy.CommonProxy;
import rtg.reference.ModInfo;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.arsmagica.RealisticBiomeAMBase;
import rtg.world.biome.realistic.atg.RealisticBiomeATGBase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.buildcraft.RealisticBiomeBCBase;
import rtg.world.biome.realistic.chromaticraft.RealisticBiomeCCBase;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBBase;
import rtg.world.biome.realistic.extrabiomes.RealisticBiomeEBXLBase;
import rtg.world.biome.realistic.highlands.RealisticBiomeHLBase;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, acceptableRemoteVersions = "*")
public class RTG {
    
    @Instance("RTG")
    public static RTG instance;
    public static String configPath;
    public static final WorldTypeRTG worldtype = (new WorldTypeRTG("RTG"));
    public static final EventManagerRTG eventMgr = new EventManagerRTG();
    
    @SidedProxy(serverSide = ModInfo.PROXY_COMMON, clientSide = ModInfo.PROXY_CLIENT)
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    
        instance = this;
        
        configPath = event.getModConfigurationDirectory() + "/RTG/";
        ConfigManager.init(configPath);
                
        MinecraftForge.TERRAIN_GEN_BUS.register(eventMgr);
        MinecraftForge.EVENT_BUS.register(eventMgr);
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event) {
    
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new DebugHandler());
        }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    
        BiomeBase.init();
        
        RealisticBiomeVanillaBase.addBiomes();
        
        RealisticBiomeBOPBase.addBiomes();
        RealisticBiomeEBBase.addBiomes();
        RealisticBiomeEBXLBase.addBiomes();
        RealisticBiomeHLBase.addBiomes();
        RealisticBiomeTCBase.addBiomes();
        RealisticBiomeBCBase.addBiomes();
        RealisticBiomeAMBase.addBiomes();
        RealisticBiomeATGBase.addBiomes();
        RealisticBiomeCCBase.addBiomes();
    }
}
