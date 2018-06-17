package rtg.proxy;

import java.nio.file.Paths;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import rtg.RTG;
import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.util.ModCompat;
import rtg.world.biome.organic.OrganicBiome;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACBase;
import rtg.world.biome.realistic.agriculturalrevolution.RealisticBiomeARBase;
import rtg.world.biome.realistic.arsmagica.RealisticBiomeAMBase;
import rtg.world.biome.realistic.atg.RealisticBiomeATGBase;
import rtg.world.biome.realistic.betteragriculture.RealisticBiomeBABase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGBase;
import rtg.world.biome.realistic.floricraft.RealisticBiomeFLORIBase;
import rtg.world.biome.realistic.flowercraft.RealisticBiomeFCBase;
import rtg.world.biome.realistic.iceandfire.RealisticBiomeIAFBase;
import rtg.world.biome.realistic.jikou.RealisticBiomeJIKBase;
import rtg.world.biome.realistic.mineworld.RealisticBiomeMWBase;
import rtg.world.biome.realistic.mithwoodforest.RealisticBiomeMFBase;
import rtg.world.biome.realistic.morechinesemc.RealisticBiomeMCMBase;
import rtg.world.biome.realistic.rockhoundingsurface.RealisticBiomeRHSBase;
import rtg.world.biome.realistic.sugiforest.RealisticBiomeSFBase;
import rtg.world.biome.realistic.vampirism.RealisticBiomeVAMPBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;

public class CommonProxy
{
    public static final String LOCATION = "rtg.proxy.CommonProxy";

    public void preInit(FMLPreInitializationEvent event) {

        RTGAPI.setConfigPath(Paths.get(event.getModConfigurationDirectory().getPath(), RTG.MOD_ID.toUpperCase()));
        RTGAPI.setConfig(new RTGConfig(RTGAPI.getConfigPath().resolve(event.getSuggestedConfigurationFile().getName()).toFile()));
        RTGAPI.config().loadConfig();

        ModCompat.init();

        DimensionManagerRTG.addRTGDimension(DimensionType.OVERWORLD.getId());
    }

    public void init(FMLInitializationEvent event) {

        RTG.getEventMgr().init();
    }

    public void postInit(FMLPostInitializationEvent event) {

        RealisticBiomeVanillaBase.addBiomes();

        RealisticBiomeACBase.addBiomes();
        RealisticBiomeAMBase.addBiomes();
        RealisticBiomeARBase.addBiomes();
        RealisticBiomeATGBase.addBiomes();
        RealisticBiomeBABase.addBiomes();
        RealisticBiomeBOPBase.addBiomes();
        RealisticBiomeBYGBase.addBiomes();
        RealisticBiomeFCBase.addBiomes();
        RealisticBiomeFLORIBase.addBiomes();
        RealisticBiomeIAFBase.addBiomes();
        RealisticBiomeJIKBase.addBiomes();
        RealisticBiomeMCMBase.addBiomes();
        RealisticBiomeMFBase.addBiomes();
        RealisticBiomeMWBase.addBiomes();
        RealisticBiomeRHSBase.addBiomes();
        RealisticBiomeSFBase.addBiomes();
        RealisticBiomeVAMPBase.addBiomes();

        RealisticBiomeBase.addModBiomes();

        // Process unsupported biomes and initialise OrganicBiomes for them
        Biome.REGISTRY.forEach(biome -> {
            if (RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome)) == null) {
                OrganicBiome.newOrganicBiome(biome);
            }
        });

        ModCompat.doBiomeCheck();
    }

    public void loadComplete() {
        RTGAPI.RTG_BIOMES.setLocked();// We don't want the biome map to change after this point, so we lock it.
    }
}