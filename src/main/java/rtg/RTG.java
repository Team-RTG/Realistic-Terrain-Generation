package rtg;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;
import rtg.api.event.BiomeConfigEvent;
import rtg.config.BiomeConfigManager;
import rtg.config.ConfigManager;
import rtg.debug.DebugHandler;
import rtg.event.EventManagerRTG;
import rtg.proxy.CommonProxy;
import rtg.reference.ModInfo;
import rtg.util.RealisticBiomePresenceTester;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACBase;
import rtg.world.biome.realistic.arsmagica.RealisticBiomeAMBase;
import rtg.world.biome.realistic.atg.RealisticBiomeATGBase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.buildcraft.RealisticBiomeBCBase;
import rtg.world.biome.realistic.chromaticraft.RealisticBiomeCCBase;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBBase;
import rtg.world.biome.realistic.extrabiomes.RealisticBiomeEBXLBase;
import rtg.world.biome.realistic.forgottennature.RealisticBiomeFNBase;
import rtg.world.biome.realistic.growthcraft.RealisticBiomeGCBase;
import rtg.world.biome.realistic.highlands.RealisticBiomeHLBase;
import rtg.world.biome.realistic.icmod.RealisticBiomeICBase;
import rtg.world.biome.realistic.lotsomobs.RealisticBiomeLOMBase;
import rtg.world.biome.realistic.ridiculousworld.RealisticBiomeRWBase;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCBase;
import rtg.world.biome.realistic.tofucraft.RealisticBiomeTOFUBase;
import rtg.world.biome.realistic.vampirism.RealisticBiomeVAMPBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.relauncher.Side;

//@Mod(modid = "RTG", name = "Realistic Terrain Generaton", version = "0.8.0d", dependencies = "required-after:Forge@[10.13.4.1448,)", acceptableRemoteVersions = "*")
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = "required-after:Forge@[" + ModInfo.FORGE_DEP + ",)" + ModInfo.MOD_DEPS, acceptableRemoteVersions = "*")
public class RTG {

    @Instance(ModInfo.MOD_ID)
    public static RTG instance;
    public static String configPath;
    public static WorldTypeRTG worldtype;
    public static EventManagerRTG eventMgr;
    
    @SidedProxy(serverSide = ModInfo.PROXY_COMMON, clientSide = ModInfo.PROXY_CLIENT)
    public static CommonProxy proxy;

    private ConfigManager configManager = new ConfigManager();

    public ConfigManager configManager(int dimension) {
        return configManager;
    }

    @EventHandler
    public void fmlLifeCycleEvent(FMLPreInitializationEvent event) 
    {    
        instance = this;

        eventMgr = new EventManagerRTG();
        MinecraftForge.EVENT_BUS.register(eventMgr);
        MinecraftForge.ORE_GEN_BUS.register(eventMgr);
        MinecraftForge.TERRAIN_GEN_BUS.register(eventMgr);
        
        MinecraftForge.EVENT_BUS.post(new BiomeConfigEvent.Pre());
        
        // This MUST get called before the config is initialised.
        BiomeConfigManager.initBiomeConfigs();
        
        MinecraftForge.EVENT_BUS.post(new BiomeConfigEvent.Post());
        
        configPath = event.getModConfigurationDirectory() + "/RTG/";
        ConfigManager.init(configPath);
        
        worldtype = new WorldTypeRTG("RTG");
    }
    
    @EventHandler
    public void fmlLifeCycleEvent(FMLInitializationEvent event) 
    {
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new DebugHandler());
        }
    }
    
    @EventHandler
    public void fmlLifeCycle(FMLPostInitializationEvent event)
    {

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
        RealisticBiomeGCBase.addBiomes();
        RealisticBiomeVAMPBase.addBiomes();
        RealisticBiomeACBase.addBiomes();
        RealisticBiomeRWBase.addBiomes();
        RealisticBiomeLOMBase.addBiomes();
        RealisticBiomeTOFUBase.addBiomes();
        RealisticBiomeFNBase.addBiomes();
        RealisticBiomeICBase.addBiomes();

        RealisticBiomePresenceTester.doBiomeCheck();
    }
    
/* FIXME: Why are we subscribing to events we don't do anything with? -srs_bsns
    @EventHandler
    public void fmlLifeCycle(FMLServerAboutToStartEvent event) {}
    
    @EventHandler
    public void fmlLifeCycle(FMLServerStartingEvent event) {}
    
    @EventHandler
    public void fmlLifeCycle(FMLServerStartedEvent event) {}

    @EventHandler
    public void fmlLifeCycle(FMLServerStoppingEvent event) {}
*/


    public void runOnServerClose(Runnable action) {
        serverCloseActions.add(action);
    }

    public void runOnNextServerCloseOnly(Runnable action) {
        serverCloseActions.add(action);
    }

    private ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<Runnable>();
    private ArrayList<Runnable> serverCloseActions = new ArrayList<Runnable>();
    @EventHandler
    public void fmlLifeCycle(FMLServerStoppedEvent event)
    {
        for (Runnable action: serverCloseActions) {
            action.run();
        }
        for (Runnable action: oneShotServerCloseActions) {
            action.run();
        }
        oneShotServerCloseActions.clear();

    }
}
