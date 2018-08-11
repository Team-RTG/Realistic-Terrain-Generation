package rtg.init;

import biomesoplenty.api.biome.BOPBiomes;
import rtg.api.RTGAPI;
import rtg.api.util.UtilityClass;
import rtg.util.ModCompat.Mods;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPAlps;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBambooForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBayou;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBog;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBorealForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBrushland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPChaparral;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPCherryBlossomGrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPColdDesert;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPConiferousForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPCoralReef;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPCrag;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPDeadForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPDeadSwamp;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPEucalyptusForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPFen;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPFlowerField;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPFlowerIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGlacier;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGrassland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGravelBeach;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPGrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPHighland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPKelpForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLandOfLakes;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLavenderFields;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLushDesert;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPLushSwamp;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMangrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMapleWoods;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMarsh;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMeadow;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMoor;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMountainFoothills;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMountainPeaks;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPMysticGrove;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOasis;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOminousWoods;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOrchard;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOriginIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOutback;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOvergrownCliffs;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPPrairie;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPQuagmire;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPRainforest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPRedwoodForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSacredSprings;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSeasonalForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPShield;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPShrubland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSnowyConiferousForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSnowyForest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPSteppe;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTemperateRainforest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTropicalIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTropicalRainforest;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPTundra;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPVolcanicIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWasteland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWetland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWoodland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPXericShrubland;
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
public final class BiomeInit {

    private BiomeInit() {

    }

    public static void preInit() {
        RTGAPI.RTG_BIOMES.addBiomes(
            RealisticBiomeBase.RiverType.NORMAL.setRTGBiome(new RealisticBiomeVanillaRiver()),
            RealisticBiomeBase.RiverType.FROZEN.setRTGBiome(new RealisticBiomeVanillaFrozenRiver()),
            RealisticBiomeBase.BeachType.NORMAL.setRTGBiome(new RealisticBiomeVanillaBeach()),
            RealisticBiomeBase.BeachType.STONE.setRTGBiome(new RealisticBiomeVanillaStoneBeach()),
            RealisticBiomeBase.BeachType.COLD.setRTGBiome(new RealisticBiomeVanillaColdBeach())
        );
    }

    public static void init() {

        init_minecraft();

        if (Mods.abyssalcraft.isLoaded()) {
            init_abyssalcraft();
        }
        if (Mods.agriculturalrevolution.isLoaded()) {
            init_agriculturalrevolution();
        }
        if (Mods.arsmagica2.isLoaded()) {
            init_arsmagica2();
        }
        if (Mods.atg.isLoaded()) {
            init_atg();
        }
        if (Mods.betteragriculture.isLoaded()) {
            init_betteragriculture();
        }
        if (Mods.biomesoplenty.isLoaded()) {
            init_biomesoplenty();
        }
        if (Mods.biomesyougo.isLoaded()) {
            init_biomesyougo();
        }
        if (Mods.buildcraft.isLoaded()) {
            init_buildcraft();
        }
        if (Mods.floricraft.isLoaded()) {
            init_floricraft();
        }
        if (Mods.flowercraftmod.isLoaded()) {
            init_flowercraftmod();
        }
        if (Mods.iceandfire.isLoaded()) {
            init_iceandfire();
        }
        if (Mods.jikou.isLoaded()) {
            init_jikou();
        }
        if (Mods.mithwoodforest.isLoaded()) {
            init_mithwoodforest();
        }
        if (Mods.morechinesemc.isLoaded()) {
            init_morechinesemc();
        }
        if (Mods.mw.isLoaded()) {
            init_mw();
        }
        if (Mods.rockhounding_surface.isLoaded()) {
            init_rockhounding_surface();
        }
        if (Mods.sugiforest.isLoaded()) {
            init_sugiforest();
        }
        if (Mods.thaumcraft.isLoaded()) {
            init_thaumcraft();
        }
        if (Mods.vampirism.isLoaded()) {
            init_vampirism();
        }

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

        if (BOPBiomes.alps.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPAlps());
        }
        if (BOPBiomes.bamboo_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBambooForest());
        }
        if (BOPBiomes.bayou.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBayou());
        }
        if (BOPBiomes.bog.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBog());
        }
        if (BOPBiomes.boreal_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBorealForest());
        }
        if (BOPBiomes.brushland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBrushland());
        }
        if (BOPBiomes.chaparral.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPChaparral());
        }
        if (BOPBiomes.cherry_blossom_grove.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCherryBlossomGrove());
        }
        if (BOPBiomes.cold_desert.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPColdDesert());
        }
        if (BOPBiomes.coniferous_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPConiferousForest());
        }
        if (BOPBiomes.coral_reef.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCoralReef());
        }
        if (BOPBiomes.crag.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCrag());
        }
        if (BOPBiomes.dead_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadForest());
        }
        if (BOPBiomes.dead_swamp.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadSwamp());
        }
        if (BOPBiomes.eucalyptus_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPEucalyptusForest());
        }
        if (BOPBiomes.fen.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFen());
        }
        if (BOPBiomes.flower_field.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerField());
        }
        if (BOPBiomes.flower_island.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerIsland());
        }
        if (BOPBiomes.glacier.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGlacier());
        }
        if (BOPBiomes.grassland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrassland());
        }
        if (BOPBiomes.gravel_beach.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGravelBeach());
        }
        if (BOPBiomes.grove.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrove());
        }
        if (BOPBiomes.highland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPHighland());
        }
        if (BOPBiomes.kelp_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPKelpForest());
        }
        if (BOPBiomes.land_of_lakes.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLandOfLakes());
        }
        if (BOPBiomes.lavender_fields.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLavenderFields());
        }
        if (BOPBiomes.lush_desert.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushDesert());
        }
        if (BOPBiomes.lush_swamp.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushSwamp());
        }
        if (BOPBiomes.mangrove.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMangrove());
        }
        if (BOPBiomes.maple_woods.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMapleWoods());
        }
        if (BOPBiomes.marsh.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMarsh());
        }
        if (BOPBiomes.meadow.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMeadow());
        }
        if (BOPBiomes.moor.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMoor());
        }
        if (BOPBiomes.mountain.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainPeaks());
        }
        if (BOPBiomes.mountain_foothills.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainFoothills());
        }
        if (BOPBiomes.mystic_grove.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMysticGrove());
        }
        if (BOPBiomes.oasis.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOasis());
        }
        if (BOPBiomes.ominous_woods.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOminousWoods());
        }
        if (BOPBiomes.orchard.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOrchard());
        }
        if (BOPBiomes.origin_island.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOriginIsland());
        }
        if (BOPBiomes.outback.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOutback());
        }
        if (BOPBiomes.overgrown_cliffs.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOvergrownCliffs());
        }
        if (BOPBiomes.prairie.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPPrairie());
        }
        if (BOPBiomes.quagmire.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPQuagmire());
        }
        if (BOPBiomes.rainforest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRainforest());
        }
        if (BOPBiomes.redwood_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRedwoodForest());
        }
        if (BOPBiomes.sacred_springs.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSacredSprings());
        }
        if (BOPBiomes.seasonal_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSeasonalForest());
        }
        if (BOPBiomes.shield.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShield());
        }
        if (BOPBiomes.shrubland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShrubland());
        }
        if (BOPBiomes.snowy_coniferous_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyConiferousForest());
        }
        if (BOPBiomes.snowy_forest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyForest());
        }
        if (BOPBiomes.steppe.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSteppe());
        }
        if (BOPBiomes.temperate_rainforest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTemperateRainforest());
        }
        if (BOPBiomes.tropical_island.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalIsland());
        }
        if (BOPBiomes.tropical_rainforest.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalRainforest());
        }
        if (BOPBiomes.tundra.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTundra());
        }
        if (BOPBiomes.volcanic_island.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPVolcanicIsland());
        }
        if (BOPBiomes.wasteland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWasteland());
        }
        if (BOPBiomes.wetland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWetland());
        }
        if (BOPBiomes.woodland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWoodland());
        }
        if (BOPBiomes.xeric_shrubland.isPresent()) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPXericShrubland());
        }
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
