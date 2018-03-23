package rtg.proxy;

import java.nio.file.Paths;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import rtg.RTG;
import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.util.PlateauUtil;
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
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenStrongholdRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import rtg.world.gen.structure.StructureOceanMonumentRTG;

public class CommonProxy
{
    public static final String LOCATION = "rtg.proxy.CommonProxy";

    public void preInit(FMLPreInitializationEvent event) {

        RTGAPI.configPath = Paths.get(event.getModConfigurationDirectory().getPath(), RTG.MOD_ID.toUpperCase());
        RTGAPI.rtgConfig  = new RTGConfig(RTGAPI.configPath.resolve(event.getSuggestedConfigurationFile().getName()).toFile());
        RTGAPI.rtgConfig.loadConfig();

        ModCompat.init();

        DimensionManagerRTG.addRTGDimension(DimensionType.OVERWORLD.getId());

// TODO: [Generator settings][Clean-up] These structure classes are to be removed
        if (RTGAPI.config().ENABLE_SCATTERED_FEATURE_MODIFICATIONS.get()) { MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG"); }
        if (RTGAPI.config().ENABLE_VILLAGE_MODIFICATIONS.get()) { MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG"); }
        if (RTGAPI.config().ENABLE_OCEAN_MONUMENT_MODIFICATIONS.get()) { MapGenStructureIO.registerStructure(StructureOceanMonumentRTG.StartMonument.class, "rtg_MapGenOceanMonumentRTG"); }
        if (RTGAPI.config().ENABLE_STRONGHOLD_MODIFICATIONS.get()) { MapGenStructureIO.registerStructure(MapGenStrongholdRTG.Start.class, "rtg_MapGenStrongholdRTG"); }
// ...
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

        // PlateauUtil must init after biomes are setup
        PlateauUtil.init();

        // Process unsupported biomes and initialise OrganicBiomes for them
        Biome.REGISTRY.forEach(biome -> {
            if (RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome)) == null) {
                OrganicBiome.newOrganicBiome(biome);
            }
        });

        ModCompat.doBiomeCheck();
    }
}