package rtg;

import java.io.File;
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

import rtg.config.ConfigManager;
import rtg.config.rtg.ConfigRTG;
import rtg.event.EventManagerRTG;
import rtg.event.WorldTypeMessageEventHandler;
import rtg.proxy.ClientProxy;
import rtg.proxy.CommonProxy;
import rtg.reference.ModInfo;
import rtg.util.RealisticBiomePresenceTester;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACBase;
import rtg.world.biome.realistic.agriculturalrevolution.RealisticBiomeARBase;
import rtg.world.biome.realistic.betteragriculture.RealisticBiomeBABase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBase;
import rtg.world.biome.realistic.flowercraft.RealisticBiomeFCBase;
import rtg.world.biome.realistic.mineworld.RealisticBiomeMWBase;
import rtg.world.biome.realistic.mithwoodforest.RealisticBiomeMFBase;
import rtg.world.biome.realistic.morechinesemc.RealisticBiomeMCMBase;
import rtg.world.biome.realistic.sugiforest.RealisticBiomeSFBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenStrongholdRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import rtg.world.gen.structure.StructureOceanMonumentRTG;


@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(
    modid                    = ModInfo.MOD_ID,
    name                     = ModInfo.MOD_NAME,
    version                  = ModInfo.MOD_VERSION,
    dependencies             = "required-after:Forge@[" + ModInfo.MCF_MINVER + "," + ModInfo.MCF_MAXVER + ")" + ModInfo.MOD_DEPS,
    acceptableRemoteVersions = "*"
)
public class RTG {

    public static String configPath;
    public static WorldTypeRTG worldtype;
    public static EventManagerRTG eventMgr;
    private ConfigManager configManager = new ConfigManager();
    private ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<>();
    private ArrayList<Runnable> serverCloseActions = new ArrayList<>();

    @Instance(ModInfo.MOD_ID)
    public static RTG instance;

    @SidedProxy(serverSide = CommonProxy.LOCATION, clientSide = ClientProxy.LOCATION)
    public static CommonProxy proxy;

    @EventHandler
    public void initPre(FMLPreInitializationEvent event) {

        instance = this;

        worldtype = new WorldTypeRTG(ModInfo.WORLD_TYPE);

        configPath = event.getModConfigurationDirectory() + File.separator + ModInfo.CONFIG_DIRECTORY + File.separator;
        ConfigManager.init(configPath);

        this.registerStructures();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        eventMgr = new EventManagerRTG();
        eventMgr.registerEventHandlers();

        // This event handler unregisters itself, so it doesn't need to be a part of the event management system.
        if (ConfigRTG.enableWorldTypeNotificationScreen) {
            MinecraftForge.EVENT_BUS.register(WorldTypeMessageEventHandler.instance);
        }
    }

    @EventHandler
    public void initPost(FMLPostInitializationEvent event) {

        RealisticBiomeVanillaBase.addBiomes();

        RealisticBiomeACBase.addBiomes();
        RealisticBiomeARBase.addBiomes();
        RealisticBiomeBABase.addBiomes();
        RealisticBiomeBOPBase.addBiomes();
        RealisticBiomeBYGBase.addBiomes();
        RealisticBiomeFCBase.addBiomes();
        RealisticBiomeMCMBase.addBiomes();
        RealisticBiomeMFBase.addBiomes();
        RealisticBiomeMWBase.addBiomes();
        RealisticBiomeSFBase.addBiomes();
        
        RealisticBiomePresenceTester.doBiomeCheck();
    }

    @EventHandler
    public void onServerStopped(FMLServerStoppedEvent event)
    {
        serverCloseActions.forEach(Runnable::run);
        oneShotServerCloseActions.forEach(Runnable::run);
        oneShotServerCloseActions.clear();
    }

    private void registerStructures() {

        if (ConfigRTG.enableScatteredFeatureModifications) {
            MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        }

        if (ConfigRTG.enableVillageModifications) {
            MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
        }

        if (ConfigRTG.enableOceanMonumentModifications) {
            MapGenStructureIO.registerStructure(StructureOceanMonumentRTG.StartMonument.class, "rtg_MapGenOceanMonumentRTG");
        }

        if (ConfigRTG.enableStrongholdModifications) {
            MapGenStructureIO.registerStructure(MapGenStrongholdRTG.Start.class, "rtg_MapGenStrongholdRTG");
        }
    }

    public void runOnServerClose(Runnable action) {
        serverCloseActions.add(action);
    }

    public void runOnNextServerCloseOnly(Runnable action) {
        serverCloseActions.add(action);
    }

    /*
     * This method is currently unused, but we're leaving it here for when we start
     * supporting multiple dimensions.
     */
    public ConfigManager configManager(int dimension) {
        return configManager;
    }
}
