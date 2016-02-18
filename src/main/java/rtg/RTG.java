package rtg;

import java.util.ArrayList;

import rtg.api.event.BiomeConfigEvent;
import rtg.config.BiomeConfigManager;
import rtg.config.ConfigManager;
import rtg.debug.DebugHandler;
import rtg.event.EventManagerRTG;
import rtg.proxy.CommonProxy;
import rtg.reference.ModInfo;
import rtg.util.RealisticBiomePresenceTester;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.buildcraft.RealisticBiomeBCBase;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, acceptableRemoteVersions = "*")
public class RTG {
    
    @Instance("RTG")
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
        
        //RealisticBiomeBOPBase.addBiomes();
        //RealisticBiomeTCBase.addBiomes();
        //RealisticBiomeBCBase.addBiomes();
        
        //RealisticBiomePresenceTester.doBiomeCheck();
    }
    
    @EventHandler
    public void fmlLifeCycle(FMLServerAboutToStartEvent event)
    {

    }
    
    @EventHandler
    public void fmlLifeCycle(FMLServerStartingEvent event)
    {

    }
    
    @EventHandler
    public void fmlLifeCycle(FMLServerStartedEvent event)
    {

    }

    @EventHandler
    public void fmlLifeCycle(FMLServerStoppingEvent event)
    {

    }

    public void runOnServerClose(Runnable action) {
        serverCloseActions.add(action);
    }
    
    private ArrayList<Runnable> serverCloseActions = new ArrayList<Runnable>();
    @EventHandler
    public void fmlLifeCycle(FMLServerStoppedEvent event)
    {
        for (Runnable action: serverCloseActions) {
            action.run();
        }

    }
}
