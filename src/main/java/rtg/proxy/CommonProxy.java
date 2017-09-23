package rtg.proxy;

import java.io.File;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.util.BiomeUtils;
import rtg.api.world.biome.IRealisticBiome;
import rtg.event.EventManagerRTG;
import rtg.reference.ModInfo;
import rtg.util.RealisticBiomePresenceTester;
import rtg.world.WorldTypeRTG;
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
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenStrongholdRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import rtg.world.gen.structure.StructureOceanMonumentRTG;
import static rtg.RTG.eventMgr;
import static rtg.RTG.worldtype;
import static rtg.api.RTGAPI.configPath;
import static rtg.api.world.biome.IRealisticBiome.arrRealisticBiomes;
import static rtg.api.world.biome.OrganicBiomeGenerator.organicBiomes;

public class CommonProxy
{
    public static final String LOCATION = "rtg.proxy.CommonProxy";

    public void preInit(FMLPreInitializationEvent event) {

        worldtype = new WorldTypeRTG(ModInfo.WORLD_TYPE);

        DimensionManagerRTG.addRTGDimension(DimensionManagerRTG.OVERWORLD);

        configPath = event.getModConfigurationDirectory() + File.separator + ModInfo.CONFIG_DIRECTORY + File.separator;
        RTGAPI.rtgConfig = new RTGConfig();
        RTGAPI.rtgConfig.load(configPath + "rtg.cfg");

        this.registerStructures();
    }

    public void init(FMLInitializationEvent event) {

        eventMgr = new EventManagerRTG();
        eventMgr.registerEventHandlers();
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

        initOrganicBiomes();

        RealisticBiomePresenceTester.doBiomeCheck();
    }

    private void registerStructures() {

        if (RTGAPI.config().ENABLE_SCATTERED_FEATURE_MODIFICATIONS.get()) {
            MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        }

        if (RTGAPI.config().ENABLE_VILLAGE_MODIFICATIONS.get()) {
            MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
        }

        if (RTGAPI.config().ENABLE_OCEAN_MONUMENT_MODIFICATIONS.get()) {
            MapGenStructureIO.registerStructure(StructureOceanMonumentRTG.StartMonument.class, "rtg_MapGenOceanMonumentRTG");
        }

        if (RTGAPI.config().ENABLE_STRONGHOLD_MODIFICATIONS.get()) {
            MapGenStructureIO.registerStructure(MapGenStrongholdRTG.Start.class, "rtg_MapGenStrongholdRTG");
        }
    }

    private static void initOrganicBiomes() {
        Biome[] b = BiomeUtils.getRegisteredBiomes();
        for (Biome biome : b) {
            if (biome != null) {
                try {
                    arrRealisticBiomes[Biome.getIdForBiome(biome)].baseBiome().getBiomeName();
                } catch (Exception e) {
                    IRealisticBiome organicBiome = new OrganicBiome(biome);
                    organicBiomes[Biome.getIdForBiome(biome)] = true;
                }
            }
        }
    }
}