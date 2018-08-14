package rtg.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import rtg.api.RTGAPI;
import rtg.api.util.UtilityClass;
import rtg.util.ModCompat.Mods;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACCoraliumInfestedSwamp;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklands;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsForest;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsHighland;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsMountains;
import rtg.world.biome.realistic.abyssalcraft.RealisticBiomeACDarklandsPlains;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPAlps;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPAlpsFoothills;
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
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOriginBeach;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOriginIsland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOutback;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPOvergrownCliffs;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPPasture;
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
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWhiteBeach;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPWoodland;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPXericShrubland;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCEerie;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCMagicalForest;
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

    public static void init() {

        init_minecraft();

        if (Mods.abyssalcraft.isLoaded()) {
            init_abyssalcraft();
        }

        if (Mods.biomesoplenty.isLoaded()) {
            init_biomesoplenty();
        }

        if (Mods.buildcraft.isLoaded()) {
            init_buildcraft();
        }

        if (Mods.thaumcraft.isLoaded()) {
            init_thaumcraft();
        }

        if (Mods.traverse.isLoaded()) {
            init_traverse();
        }

        // This must be done after all biomes have been initialised so that they are all available.
        RTGAPI.initPatchBiome();
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

        String modid = Mods.abyssalcraft.name();

        if (isBiomePresent(modid, "coralium_infested_swamp")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACCoraliumInfestedSwamp());
        }
        if (isBiomePresent(modid, "darklands")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklands());
        }
        if (isBiomePresent(modid, "darklands_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsForest());
        }
        if (isBiomePresent(modid, "darklands_hills")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsHighland());
        }
        if (isBiomePresent(modid, "darklands_mountains")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsMountains());
        }
        if (isBiomePresent(modid, "darklands_plains")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeACDarklandsPlains());
        }
    }

    private static void init_biomesoplenty() {

        String modid = Mods.biomesoplenty.name();

        if (isBiomePresent(modid, "alps")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPAlps());
        }
        if (isBiomePresent(modid, "alps_foothills")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPAlpsFoothills());
        }
        if (isBiomePresent(modid, "bamboo_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBambooForest());
        }
        if (isBiomePresent(modid, "bayou")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBayou());
        }
        if (isBiomePresent(modid, "bog")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBog());
        }
        if (isBiomePresent(modid, "boreal_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBorealForest());
        }
        if (isBiomePresent(modid, "brushland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPBrushland());
        }
        if (isBiomePresent(modid, "chaparral")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPChaparral());
        }
        if (isBiomePresent(modid, "cherry_blossom_grove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCherryBlossomGrove());
        }
        if (isBiomePresent(modid, "cold_desert")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPColdDesert());
        }
        if (isBiomePresent(modid, "coniferous_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPConiferousForest());
        }
        if (isBiomePresent(modid, "coral_reef")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCoralReef());
        }
        if (isBiomePresent(modid, "crag")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPCrag());
        }
        if (isBiomePresent(modid, "dead_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadForest());
        }
        if (isBiomePresent(modid, "dead_swamp")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPDeadSwamp());
        }
        if (isBiomePresent(modid, "eucalyptus_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPEucalyptusForest());
        }
        if (isBiomePresent(modid, "fen")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFen());
        }
        if (isBiomePresent(modid, "flower_field")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerField());
        }
        if (isBiomePresent(modid, "flower_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPFlowerIsland());
        }
        if (isBiomePresent(modid, "glacier")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGlacier());
        }
        if (isBiomePresent(modid, "grassland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrassland());
        }
        if (isBiomePresent(modid, "gravel_beach")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGravelBeach());
        }
        if (isBiomePresent(modid, "grove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPGrove());
        }
        if (isBiomePresent(modid, "highland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPHighland());
        }
        if (isBiomePresent(modid, "kelp_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPKelpForest());
        }
        if (isBiomePresent(modid, "land_of_lakes")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLandOfLakes());
        }
        if (isBiomePresent(modid, "lavender_fields")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLavenderFields());
        }
        if (isBiomePresent(modid, "lush_desert")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushDesert());
        }
        if (isBiomePresent(modid, "lush_swamp")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPLushSwamp());
        }
        if (isBiomePresent(modid, "mangrove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMangrove());
        }
        if (isBiomePresent(modid, "maple_woods")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMapleWoods());
        }
        if (isBiomePresent(modid, "marsh")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMarsh());
        }
        if (isBiomePresent(modid, "meadow")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMeadow());
        }
        if (isBiomePresent(modid, "moor")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMoor());
        }
        if (isBiomePresent(modid, "mountain")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainPeaks());
        }
        if (isBiomePresent(modid, "mountain_foothills")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMountainFoothills());
        }
        if (isBiomePresent(modid, "mystic_grove")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPMysticGrove());
        }
        if (isBiomePresent(modid, "oasis")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOasis());
        }
        if (isBiomePresent(modid, "ominous_woods")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOminousWoods());
        }
        if (isBiomePresent(modid, "orchard")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOrchard());
        }
        if (isBiomePresent(modid, "origin_beach")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOriginBeach());
        }
        if (isBiomePresent(modid, "origin_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOriginIsland());
        }
        if (isBiomePresent(modid, "outback")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOutback());
        }
        if (isBiomePresent(modid, "overgrown_cliffs")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPOvergrownCliffs());
        }
        if (isBiomePresent(modid, "pasture")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPPasture());
        }
        if (isBiomePresent(modid, "prairie")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPPrairie());
        }
        if (isBiomePresent(modid, "quagmire")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPQuagmire());
        }
        if (isBiomePresent(modid, "rainforest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRainforest());
        }
        if (isBiomePresent(modid, "redwood_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPRedwoodForest());
        }
        if (isBiomePresent(modid, "sacred_springs")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSacredSprings());
        }
        if (isBiomePresent(modid, "seasonal_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSeasonalForest());
        }
        if (isBiomePresent(modid, "shield")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShield());
        }
        if (isBiomePresent(modid, "shrubland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPShrubland());
        }
        if (isBiomePresent(modid, "snowy_coniferous_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyConiferousForest());
        }
        if (isBiomePresent(modid, "snowy_forest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSnowyForest());
        }
        if (isBiomePresent(modid, "steppe")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPSteppe());
        }
        if (isBiomePresent(modid, "temperate_rainforest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTemperateRainforest());
        }
        if (isBiomePresent(modid, "tropical_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalIsland());
        }
        if (isBiomePresent(modid, "tropical_rainforest")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTropicalRainforest());
        }
        if (isBiomePresent(modid, "tundra")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPTundra());
        }
        if (isBiomePresent(modid, "volcanic_island")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPVolcanicIsland());
        }
        if (isBiomePresent(modid, "wasteland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWasteland());
        }
        if (isBiomePresent(modid, "wetland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWetland());
        }
        if (isBiomePresent(modid, "white_beach")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWhiteBeach());
        }
        if (isBiomePresent(modid, "woodland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPWoodland());
        }
        if (isBiomePresent(modid, "xeric_shrubland")) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeBOPXericShrubland());
        }
    }

    private static void init_buildcraft() {

    }

    private static void init_thaumcraft() {

        final ResourceLocation magicalForest = new ResourceLocation(Mods.thaumcraft.name(), "magical_forest");
        final ResourceLocation eerie         = new ResourceLocation(Mods.thaumcraft.name(), "eerie");
        Biome biome;

        if ((biome = Biome.REGISTRY.getObject(magicalForest)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTCMagicalForest(biome));
        }

        if ((biome = Biome.REGISTRY.getObject(eerie)) != null) {
            RTGAPI.RTG_BIOMES.addBiomes(new RealisticBiomeTCEerie(biome));
        }
    }

    private static void init_traverse() {

    }

    private static boolean isBiomePresent(String modid, String biomeName) {
        ResourceLocation rl = new ResourceLocation(modid, biomeName);
        return Biome.REGISTRY.containsKey(rl);
    }
}
