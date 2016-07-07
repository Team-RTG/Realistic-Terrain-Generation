package rtg;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import rtg.api.event.BiomeConfigEvent;
import rtg.config.BiomeConfigManager;
import rtg.config.ConfigManagerRTG;
import rtg.event.EventManagerRTG;
import rtg.reference.ModInfo;
import rtg.util.Logger;
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
import rtg.world.biome.realistic.idt.RealisticBiomeIDTBase;
import rtg.world.biome.realistic.lotsomobs.RealisticBiomeLOMBase;
import rtg.world.biome.realistic.ridiculousworld.RealisticBiomeRWBase;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCBase;
import rtg.world.biome.realistic.tofucraft.RealisticBiomeTOFUBase;
import rtg.world.biome.realistic.vampirism.RealisticBiomeVAMPBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;

import java.util.ArrayList;

@SuppressWarnings({"unused", "WeakerAccess"})
//@Mod(modid = "RTG", name = "Realistic Terrain Generaton", version = "1.0.0-dev", dependencies = "required-after:Forge@[10.13.4.1448,)", acceptableRemoteVersions = "*")
@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = "required-after:Forge@[" + ModInfo.FORGE_DEP + ",)" + ModInfo.MOD_DEPS, acceptableRemoteVersions = "*")
public class RTG {

    @Instance(ModInfo.MOD_ID)
    public static RTG instance;
    public static WorldTypeRTG WORLD = new WorldTypeRTG(ModInfo.MOD_ID);
    public static EventManagerRTG EVENTMGR = new EventManagerRTG();
    private ArrayList<Runnable> serverCloseActions = new ArrayList<>();
    private ArrayList<Runnable> oneShotServerCloseActions = new ArrayList<>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        MinecraftForge.EVENT_BUS.post(new BiomeConfigEvent.Pre());
        // This MUST get called before the config is initialised.
        BiomeConfigManager.initBiomeConfigs();
        MinecraftForge.EVENT_BUS.post(new BiomeConfigEvent.Post());
        ConfigManagerRTG.init(event.getModConfigurationDirectory().getPath().concat("/" + ModInfo.MOD_ID + "/"));
    }

//  @EventHandler public void init(FMLInitializationEvent event) {}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
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
        RealisticBiomeIDTBase.addBiomes();
        RealisticBiomePresenceTester.doBiomeCheck();

        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
    }

//  @EventHandler public void serverAboutToStart(FMLServerAboutToStartEvent event) {}

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        if (RTG.WORLD.isWorldTypeRTG()) {
            Logger.info("FMLServerStartingEvent: WorldType is 'RTG' so registering RTG Event Manager");
            MinecraftForge.EVENT_BUS.register(EVENTMGR);
            MinecraftForge.ORE_GEN_BUS.register(EVENTMGR);
            MinecraftForge.TERRAIN_GEN_BUS.register(EVENTMGR);
        } else Logger.info("FMLServerStartingEvent: WorldType is not RTG, Skipping Event Manager Registration");
    }

//  @EventHandler public void serverStopping(FMLServerStoppingEvent event) {}

    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        Logger.info("FMLServerStoppedEvent: Unregistering RTG Event Manager");
        MinecraftForge.EVENT_BUS.unregister(EVENTMGR);
        MinecraftForge.ORE_GEN_BUS.unregister(EVENTMGR);
        MinecraftForge.TERRAIN_GEN_BUS.unregister(EVENTMGR);

        for (Runnable action : serverCloseActions) { action.run(); }
        for (Runnable action : oneShotServerCloseActions) { action.run(); }
        oneShotServerCloseActions.clear();
    }

    public void runOnServerClose(Runnable action) { serverCloseActions.add(action); }

    public void runOnNextServerCloseOnly(Runnable action) { serverCloseActions.add(action); }
}
