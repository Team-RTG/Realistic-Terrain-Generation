package teamrtg.rtg.core;

import java.util.ArrayList;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.relauncher.Side;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.RealisticBiomePresenceTester;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.world.RealisticBiomeFaker;
import teamrtg.rtg.client.DebugHandler;
import teamrtg.rtg.core.event.EventManagerRTG;
import teamrtg.rtg.core.world.WorldTypeRTG;
import teamrtg.rtg.core.world.gen.structure.MapGenScatteredFeatureRTG;
import teamrtg.rtg.core.world.gen.structure.MapGenVillageRTG;
import teamrtg.rtg.core.world.gen.structure.StructureOceanMonumentRTG;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = "required-after:Forge@[" + ModInfo.FORGE_DEP + ",)" + ModInfo.MOD_DEPS, acceptableRemoteVersions = "*")
public class RTG {

    @Instance(ModInfo.MOD_ID)
    public static RTG instance;
    public static String configPath;
    public static WorldTypeRTG worldtype;
    public static EventManagerRTG eventMgr;

    @SidedProxy(serverSide = ModInfo.PROXY_COMMON, clientSide = ModInfo.PROXY_CLIENT)
    public static CommonProxy proxy;

    private ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<>();
    private ArrayList<Runnable> serverCloseActions = new ArrayList<>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;

        configPath = event.getModConfigurationDirectory() + "/RTG/";
        Mods.syncAllConfigs();
        
        // This must come before the event manager is registered.
        this.registerStructures();

        Logger.info("[FMLPreInitializationEvent] Creating RTG's EventManager");
        eventMgr = new EventManagerRTG();

        worldtype = new WorldTypeRTG(ModInfo.MOD_ID);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new DebugHandler());
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Mods.initAllBiomes();
        RealisticBiomeFaker.initFakeBiomes();
        RealisticBiomePresenceTester.doBiomeCheck();
    }
    
    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event)
    {

        if (eventMgr.isRegistered()) {
            Logger.info("Unregistering RTG's Terrain Event Handlers...");
            RTG.eventMgr.unRegisterEventHandlers();
            if (!eventMgr.isRegistered()) Logger.info("RTG's Terrain Event Handlers have been unregistered successfully.");
        }

    }
    
    private void registerStructures()
    {
    	// Scattered features
        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        
        // Villages
        if (Mods.RTG.config.ENABLE_VILLAGE_MODIFICATIONS.get())
            MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
        
        // Ocean monuments
        MapGenStructureIO.registerStructure(StructureOceanMonumentRTG.StartMonument.class, "rtg_MapGenOceanMonumentRTG");
    }


    public void runOnServerClose(Runnable action) {
        serverCloseActions.add(action);
    }

    public void runOnNextServerCloseOnly(Runnable action) {
        serverCloseActions.add(action);
    }

    @EventHandler
    public void fmlLifeCycle(FMLServerStoppedEvent event) {
        for (Runnable action : serverCloseActions) {
            action.run();
        }
        for (Runnable action : oneShotServerCloseActions) {
            action.run();
        }
        oneShotServerCloseActions.clear();

    }
}