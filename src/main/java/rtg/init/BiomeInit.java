package rtg.init;

import rtg.api.RTGAPI;
import rtg.api.util.UtilityClass;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBeach;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForestHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForestHillsM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBirchForestM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdBeach;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaColdTaigaM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDeepOcean;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDesert;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDesertHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaDesertM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsEdge;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsPlus;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaExtremeHillsPlusM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaFlowerForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaForestHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaFrozenOcean;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaFrozenRiver;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaIceMountains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaIcePlains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaIcePlainsSpikes;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungle;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleEdge;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleEdgeM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaJungleM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMegaSpruceTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMegaTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMegaTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesa;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaBryce;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateau;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateauF;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateauFM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaPlateauM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMushroomIsland;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMushroomIslandShore;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaOcean;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaPlains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRedwoodTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRiver;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRoofedForest;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaRoofedForestM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavanna;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavannaM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavannaPlateau;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSavannaPlateauM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaStoneBeach;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSunflowerPlains;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSwampland;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaSwamplandM;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaTaiga;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaTaigaHills;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaTaigaM;

@UtilityClass
public final class BiomeInit
{
    private BiomeInit() {}

    public static void preInit() {
        RTGAPI.RTG_BIOMES.addBiomes(
            RealisticBiomeBase.RiverType.NORMAL.setRTGBiome(new RealisticBiomeVanillaRiver()),
            RealisticBiomeBase.RiverType.FROZEN.setRTGBiome(new RealisticBiomeVanillaFrozenRiver()),
            RealisticBiomeBase.BeachType.NORMAL.setRTGBiome(new RealisticBiomeVanillaBeach()),
            RealisticBiomeBase.BeachType.STONE .setRTGBiome(new RealisticBiomeVanillaStoneBeach()),
            RealisticBiomeBase.BeachType.COLD  .setRTGBiome(new RealisticBiomeVanillaColdBeach())
        );
    }

    public static void init() {

        init_minecraft();

        if (Mods.abyssalcraft.isLoaded())           { init_abyssalcraft(); }
        if (Mods.agriculturalrevolution.isLoaded()) { init_agriculturalrevolution(); }
        if (Mods.arsmagica2.isLoaded())             { init_arsmagica2(); }
        if (Mods.atg.isLoaded())                    { init_atg(); }
        if (Mods.betteragriculture.isLoaded())      { init_betteragriculture(); }
        if (Mods.biomesoplenty.isLoaded())          { init_biomesoplenty(); }
        if (Mods.biomesyougo.isLoaded())            { init_biomesyougo(); }
        if (Mods.buildcraft.isLoaded())             { init_buildcraft(); }
        if (Mods.floricraft.isLoaded())             { init_floricraft(); }
        if (Mods.flowercraftmod.isLoaded())         { init_flowercraftmod(); }
        if (Mods.iceandfire.isLoaded())             { init_iceandfire(); }
        if (Mods.jikou.isLoaded())                  { init_jikou(); }
        if (Mods.mithwoodforest.isLoaded())         { init_mithwoodforest(); }
        if (Mods.morechinesemc.isLoaded())          { init_morechinesemc(); }
        if (Mods.mw.isLoaded())                     { init_mw(); }
        if (Mods.rockhounding_surface.isLoaded())   { init_rockhounding_surface(); }
        if (Mods.sugiforest.isLoaded())             { init_sugiforest(); }
        if (Mods.thaumcraft.isLoaded())             { init_thaumcraft(); }
        if (Mods.vampirism.isLoaded())              { init_vampirism(); }

    }

    private static void init_minecraft() {
        // vanilla rivers and beaches are initialised to enum fields during #preInit
        RTGAPI.RTG_BIOMES.addBiomes(
            new RealisticBiomeVanillaBirchForest(),
            new RealisticBiomeVanillaBirchForestHills(),
            new RealisticBiomeVanillaBirchForestHillsM(),
            new RealisticBiomeVanillaBirchForestM(),
            new RealisticBiomeVanillaColdTaiga(),
            new RealisticBiomeVanillaColdTaigaHills(),
            new RealisticBiomeVanillaColdTaigaM(),
            new RealisticBiomeVanillaDeepOcean(),
            new RealisticBiomeVanillaDesert(),
            new RealisticBiomeVanillaDesertHills(),
            new RealisticBiomeVanillaDesertM(),
            new RealisticBiomeVanillaExtremeHills(),
            new RealisticBiomeVanillaExtremeHillsEdge(),
            new RealisticBiomeVanillaExtremeHillsM(),
            new RealisticBiomeVanillaExtremeHillsPlus(),
            new RealisticBiomeVanillaExtremeHillsPlusM(),
            new RealisticBiomeVanillaFlowerForest(),
            new RealisticBiomeVanillaForest(),
            new RealisticBiomeVanillaForestHills(),
            new RealisticBiomeVanillaFrozenOcean(),
            new RealisticBiomeVanillaIceMountains(),
            new RealisticBiomeVanillaIcePlains(),
            new RealisticBiomeVanillaIcePlainsSpikes(),
            new RealisticBiomeVanillaJungle(),
            new RealisticBiomeVanillaJungleEdge(),
            new RealisticBiomeVanillaJungleEdgeM(),
            new RealisticBiomeVanillaJungleHills(),
            new RealisticBiomeVanillaJungleM(),
            new RealisticBiomeVanillaMegaSpruceTaiga(),
            new RealisticBiomeVanillaMegaTaiga(),
            new RealisticBiomeVanillaMegaTaigaHills(),
            new RealisticBiomeVanillaMesa(),
            new RealisticBiomeVanillaMesaBryce(),
            new RealisticBiomeVanillaMesaPlateau(),
            new RealisticBiomeVanillaMesaPlateauF(),
            new RealisticBiomeVanillaMesaPlateauFM(),
            new RealisticBiomeVanillaMesaPlateauM(),
            new RealisticBiomeVanillaMushroomIsland(),
            new RealisticBiomeVanillaMushroomIslandShore(),
            new RealisticBiomeVanillaOcean(),
            new RealisticBiomeVanillaPlains(),
            new RealisticBiomeVanillaRedwoodTaigaHills(),
            new RealisticBiomeVanillaRoofedForest(),
            new RealisticBiomeVanillaRoofedForestM(),
            new RealisticBiomeVanillaSavanna(),
            new RealisticBiomeVanillaSavannaM(),
            new RealisticBiomeVanillaSavannaPlateau(),
            new RealisticBiomeVanillaSavannaPlateauM(),
            new RealisticBiomeVanillaSunflowerPlains(),
            new RealisticBiomeVanillaSwampland(),
            new RealisticBiomeVanillaSwamplandM(),
            new RealisticBiomeVanillaTaiga(),
            new RealisticBiomeVanillaTaigaHills(),
            new RealisticBiomeVanillaTaigaM()
        );
    }

    private static void init_abyssalcraft() {

    }

    private static void init_agriculturalrevolution() {

    }

    private static void init_arsmagica2() {

    }

    private static void init_atg() {

    }

    private static void init_betteragriculture() {

    }

    private static void init_biomesoplenty() {

    }

    private static void init_biomesyougo() {

    }

    private static void init_buildcraft() {

    }

    private static void init_floricraft() {

    }

    private static void init_flowercraftmod() {

    }

    private static void init_iceandfire() {

    }

    private static void init_jikou() {

    }

    private static void init_mithwoodforest() {

    }

    private static void init_morechinesemc() {

    }

    private static void init_mw() {

    }

    private static void init_rockhounding_surface() {

    }

    private static void init_sugiforest() {

    }

    private static void init_thaumcraft() {

    }

    private static void init_vampirism() {

    }
}
