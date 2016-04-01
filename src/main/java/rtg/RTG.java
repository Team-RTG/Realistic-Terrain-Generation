package rtg;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import rtg.api.event.RTGEvent;
import rtg.api.util.ISupportedMod;
import rtg.debug.DebugHandler;
import rtg.event.EventManagerRTG;
import rtg.proxy.CommonProxy;
import rtg.api.util.RealisticBiomePresenceTester;
import rtg.util.SupportedMod;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;

import java.util.ArrayList;

import static rtg.reference.ModInfo.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION, dependencies = "required-after:Forge@[" + FORGE_DEP + ",)" + MOD_DEPS, acceptableRemoteVersions = "*")
public class RTG {

    @Instance(MOD_ID)
    public static RTG instance;
    public static String configPath;
    public static WorldTypeRTG worldtype;
    public static EventManagerRTG eventMgr;
    public static ISupportedMod[] supportedMods;

    @SidedProxy(serverSide = PROXY_COMMON, clientSide = PROXY_CLIENT)
    public static CommonProxy proxy;

    private ArrayList<Runnable> serverCloseActions = new ArrayList<>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;

        eventMgr = new EventManagerRTG();
        MinecraftForge.EVENT_BUS.register(eventMgr);
        MinecraftForge.ORE_GEN_BUS.register(eventMgr);
        MinecraftForge.TERRAIN_GEN_BUS.register(eventMgr);

        MinecraftForge.EVENT_BUS.post(new RTGEvent.BiomeConfigEvent.Pre());

        // This MUST get called before the config is initialised.

        MinecraftForge.EVENT_BUS.post(new RTGEvent.BiomeConfigEvent.Post());

        configPath = event.getModConfigurationDirectory() + "/RTG/";

        SupportedMod.initAll(supportedMods);

        worldtype = new WorldTypeRTG("RTG");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new DebugHandler());
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        RealisticBiomeVanillaBase.addBiomes();

        RealisticBiomePresenceTester.doBiomeCheck();
    }
}
