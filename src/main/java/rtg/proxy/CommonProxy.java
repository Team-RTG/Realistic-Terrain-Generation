package rtg.proxy;

import java.nio.file.Paths;

import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import rtg.RTG;
import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.init.BiomeInit;
import rtg.util.ModCompat;
import rtg.world.WorldTypeRTG;
import rtg.world.gen.structure.WoodlandMansionRTG;


public class CommonProxy {

    public static final String LOCATION = "rtg.proxy.CommonProxy";

    public void preInit(FMLPreInitializationEvent event) {

        RTGAPI.setConfigPath(Paths.get(event.getModConfigurationDirectory().getPath(), RTG.MOD_ID.toUpperCase()));
        RTGAPI.setConfig(new RTGConfig(RTGAPI.getConfigPath().resolve(event.getSuggestedConfigurationFile().getName()).toFile()));
        RTGAPI.config().loadConfig();

        RTGAPI.addAllowedDimensionType(DimensionType.OVERWORLD);

        WorldTypeRTG.init();
        ModCompat.init();
        BiomeInit.preInit();// initialise river and beach biomes

        this.registerStructures();
    }

    public void init(FMLInitializationEvent event) {

        RTG.getEventMgr().init();
    }

    public void postInit(FMLPostInitializationEvent event) {

        BiomeInit.init();// initialise all biomes supported internally

        ModCompat.doBiomeCheck();
    }

    public void loadComplete() {
        RTGAPI.RTG_BIOMES.setLocked();// We don't want the biome map to change after this point, so we lock it.
    }

    private void registerStructures() {
        MapGenStructureIO.registerStructure(WoodlandMansionRTG.Start.class, "WoodlandMansionRTG");
    }
}