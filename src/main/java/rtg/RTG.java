package rtg;

import java.util.ArrayList;

import net.minecraft.world.gen.structure.MapGenStructureIO;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;

import rtg.api.event.BiomeConfigEvent;
import rtg.config.BiomeConfigManager;
import rtg.config.ConfigManager;
import rtg.config.rtg.ConfigRTG;
import rtg.event.EventManagerRTG;
import rtg.event.WorldTypeMessageEventHandler;
import rtg.reference.ModInfo;
import rtg.util.RealisticBiomePresenceTester;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACBase;
import rtg.world.biome.realistic.arsmagica.RealisticBiomeAMBase;
import rtg.world.biome.realistic.atg.RealisticBiomeATGBase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.buildcraft.RealisticBiomeBCBase;
import rtg.world.biome.realistic.chromaticraft.RealisticBiomeCCBase;
import rtg.world.biome.realistic.eccentricbiomes.RealisticBiomeECCBase;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBBase;
import rtg.world.biome.realistic.extrabiomes.RealisticBiomeEBXLBase;
import rtg.world.biome.realistic.flowercraft.RealisticBiomeFCBase;
import rtg.world.biome.realistic.forgottennature.RealisticBiomeFNBase;
import rtg.world.biome.realistic.growthcraft.RealisticBiomeGCBase;
import rtg.world.biome.realistic.highlands.RealisticBiomeHLBase;
import rtg.world.biome.realistic.hotwatermod.RealisticBiomeHWMBase;
import rtg.world.biome.realistic.icmod.RealisticBiomeICBase;
import rtg.world.biome.realistic.idt.RealisticBiomeIDTBase;
import rtg.world.biome.realistic.itd.RealisticBiomeITDBase;
import rtg.world.biome.realistic.lotsomobs.RealisticBiomeLOMBase;
import rtg.world.biome.realistic.ridiculousworld.RealisticBiomeRWBase;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCBase;
import rtg.world.biome.realistic.tofucraft.RealisticBiomeTOFUBase;
import rtg.world.biome.realistic.vampirism.RealisticBiomeVAMPBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;


//@Mod(modid = "RTG", name = "Realistic Terrain Generaton", version = "0.8.0d", dependencies = "required-after:Forge@[10.13.4.1448,)", acceptableRemoteVersions = "*")
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = "required-after:Forge@[" + ModInfo.FORGE_DEP + ",)" + ModInfo.MOD_DEPS, acceptableRemoteVersions = "*")
public class RTG {

    @Instance(ModInfo.MOD_ID)
    public static RTG instance;
    public static String configPath;
    public static WorldTypeRTG worldtype;
    public static EventManagerRTG eventMgr;

    private ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<>();
    private ArrayList<Runnable> serverCloseActions = new ArrayList<>();

    private ConfigManager configManager = new ConfigManager();

    public ConfigManager configManager(int dimension) {
        return configManager;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;

        worldtype = new WorldTypeRTG("RTG");

        // Biome configs MUST get initialised before the main config.
        MinecraftForge.EVENT_BUS.post(new BiomeConfigEvent.Pre());
        BiomeConfigManager.initBiomeConfigs();
        MinecraftForge.EVENT_BUS.post(new BiomeConfigEvent.Post());

        configPath = event.getModConfigurationDirectory() + "/RTG/";
        ConfigManager.init(configPath);

        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");

        eventMgr = new EventManagerRTG();
        eventMgr.registerEventHandlers();

        // This event handler unregisters itself, so it doesn't need to be a part of the event management system.
        if (ConfigRTG.enableWorldTypeNotificationScreen) {
            MinecraftForge.EVENT_BUS.register(WorldTypeMessageEventHandler.instance);
        }
    }

    /*
    @EventHandler
    public void init(FMLInitializationEvent event) {}
    */

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        RealisticBiomeVanillaBase.addBiomes();
        
        RealisticBiomeBOPBase.addBiomes();
        RealisticBiomeEBBase.addBiomes();
        RealisticBiomeEBXLBase.addBiomes();
        RealisticBiomeECCBase.addBiomes();
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
        RealisticBiomeIDTBase.addBiomes();
        RealisticBiomeITDBase.addBiomes();
        RealisticBiomeFCBase.addBiomes();
        RealisticBiomeHWMBase.addBiomes();

        RealisticBiomePresenceTester.doBiomeCheck();
    }

    /*
    @EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event) {}
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {}
    
    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {}

    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {}
    */

    public void runOnServerClose(Runnable action) {
        serverCloseActions.add(action);
    }

    public void runOnNextServerCloseOnly(Runnable action) {
        serverCloseActions.add(action);
    }

    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event)
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
