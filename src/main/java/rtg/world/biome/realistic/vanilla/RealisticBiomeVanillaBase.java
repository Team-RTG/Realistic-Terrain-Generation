package rtg.world.biome.realistic.vanilla;

import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.world.biome.realistic.RealisticBiomeBase;

public abstract class RealisticBiomeVanillaBase extends RealisticBiomeBase {

    public static RealisticBiomeBase vanillaBeach;
    public static RealisticBiomeBase vanillaBirchForest;
    public static RealisticBiomeBase vanillaBirchForestHills;
    public static RealisticBiomeBase vanillaBirchForestHillsM;
    public static RealisticBiomeBase vanillaBirchForestM;
    public static RealisticBiomeBase vanillaColdBeach;
    public static RealisticBiomeBase vanillaColdTaiga;
    public static RealisticBiomeBase vanillaColdTaigaHills;
    public static RealisticBiomeBase vanillaColdTaigaM;
    public static RealisticBiomeBase vanillaDeepOcean;
    public static RealisticBiomeBase vanillaDesert;
    public static RealisticBiomeBase vanillaDesertHills;
    public static RealisticBiomeBase vanillaDesertM;
    public static RealisticBiomeBase vanillaExtremeHills;
    public static RealisticBiomeBase vanillaExtremeHillsEdge;
    public static RealisticBiomeBase vanillaExtremeHillsM;
    public static RealisticBiomeBase vanillaExtremeHillsPlus;
    public static RealisticBiomeBase vanillaExtremeHillsPlusM;
    public static RealisticBiomeBase vanillaFlowerForest;
    public static RealisticBiomeBase vanillaForest;
    public static RealisticBiomeBase vanillaForestHills;
    public static RealisticBiomeBase vanillaFrozenOcean;
    public static RealisticBiomeBase vanillaFrozenRiver;
    public static RealisticBiomeBase vanillaIceMountains;
    public static RealisticBiomeBase vanillaIcePlains;
    public static RealisticBiomeBase vanillaIcePlainsSpikes;
    public static RealisticBiomeBase vanillaJungle;
    public static RealisticBiomeBase vanillaJungleEdge;
    public static RealisticBiomeBase vanillaJungleEdgeM;
    public static RealisticBiomeBase vanillaJungleHills;
    public static RealisticBiomeBase vanillaJungleM;
    public static RealisticBiomeBase vanillaMegaSpruceTaiga;
    public static RealisticBiomeBase vanillaMegaTaiga;
    public static RealisticBiomeBase vanillaMegaTaigaHills;
    public static RealisticBiomeBase vanillaMesa;
    public static RealisticBiomeBase vanillaMesaBryce;
    public static RealisticBiomeBase vanillaMesaPlateau;
    public static RealisticBiomeBase vanillaMesaPlateau_F;
    public static RealisticBiomeBase vanillaMesaPlateauFM;
    public static RealisticBiomeBase vanillaMesaPlateauM;
    public static RealisticBiomeBase vanillaMushroomIsland;
    public static RealisticBiomeBase vanillaMushroomIslandShore;
    public static RealisticBiomeBase vanillaOcean;
    public static RealisticBiomeBase vanillaPlains;
    public static RealisticBiomeBase vanillaRedwoodTaigaHills;
    public static RealisticBiomeBase vanillaRiver;
    public static RealisticBiomeBase vanillaRoofedForest;
    public static RealisticBiomeBase vanillaRoofedForestM;
    public static RealisticBiomeBase vanillaSavanna;
    public static RealisticBiomeBase vanillaSavannaM;
    public static RealisticBiomeBase vanillaSavannaPlateau;
    public static RealisticBiomeBase vanillaSavannaPlateauM;
    public static RealisticBiomeBase vanillaStoneBeach;
    public static RealisticBiomeBase vanillaSunflowerPlains;
    public static RealisticBiomeBase vanillaSwampland;
    public static RealisticBiomeBase vanillaSwamplandM;
    public static RealisticBiomeBase vanillaTaiga;
    public static RealisticBiomeBase vanillaTaigaHills;
    public static RealisticBiomeBase vanillaTaigaM;

    public RealisticBiomeVanillaBase(BiomeConfig config, Biome b, Biome riverbiome) {

        super(config, b, riverbiome);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        vanillaBeach = new RealisticBiomeVanillaBeach(BiomeConfigVanilla.biomeConfigVanillaBeach);
        vanillaBirchForest = new RealisticBiomeVanillaBirchForest(BiomeConfigVanilla.biomeConfigVanillaBirchForest);
        vanillaBirchForestHills = new RealisticBiomeVanillaBirchForestHills(BiomeConfigVanilla.biomeConfigVanillaBirchForestHills);
        vanillaBirchForestHillsM = new RealisticBiomeVanillaBirchForestHillsM(BiomeConfigVanilla.biomeConfigVanillaBirchForestHillsM);
        vanillaBirchForestM = new RealisticBiomeVanillaBirchForestM(BiomeConfigVanilla.biomeConfigVanillaBirchForestM);
        vanillaColdBeach = new RealisticBiomeVanillaColdBeach(BiomeConfigVanilla.biomeConfigVanillaColdBeach);
        vanillaColdTaiga = new RealisticBiomeVanillaColdTaiga(BiomeConfigVanilla.biomeConfigVanillaColdTaiga);
        vanillaColdTaigaHills = new RealisticBiomeVanillaColdTaigaHills(BiomeConfigVanilla.biomeConfigVanillaColdTaigaHills);
        vanillaColdTaigaM = new RealisticBiomeVanillaColdTaigaM(BiomeConfigVanilla.biomeConfigVanillaColdTaigaM);
        vanillaDeepOcean = new RealisticBiomeVanillaDeepOcean(BiomeConfigVanilla.biomeConfigVanillaDeepOcean);
        vanillaDesert = new RealisticBiomeVanillaDesert(BiomeConfigVanilla.biomeConfigVanillaDesert);
        vanillaDesertHills = new RealisticBiomeVanillaDesertHills(BiomeConfigVanilla.biomeConfigVanillaDesertHills);
        vanillaDesertM = new RealisticBiomeVanillaDesertM(BiomeConfigVanilla.biomeConfigVanillaDesertM);
        vanillaExtremeHills = new RealisticBiomeVanillaExtremeHills(BiomeConfigVanilla.biomeConfigVanillaExtremeHills);
        vanillaExtremeHillsEdge = new RealisticBiomeVanillaExtremeHillsEdge(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsEdge);
        vanillaExtremeHillsM = new RealisticBiomeVanillaExtremeHillsM(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsM);
        vanillaExtremeHillsPlus = new RealisticBiomeVanillaExtremeHillsPlus(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsPlus);
        vanillaExtremeHillsPlusM = new RealisticBiomeVanillaExtremeHillsPlusM(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsPlusM);
        vanillaFlowerForest = new RealisticBiomeVanillaFlowerForest(BiomeConfigVanilla.biomeConfigVanillaFlowerForest);
        vanillaForest = new RealisticBiomeVanillaForest(BiomeConfigVanilla.biomeConfigVanillaForest);
        vanillaForestHills = new RealisticBiomeVanillaForestHills(BiomeConfigVanilla.biomeConfigVanillaForestHills);
        vanillaFrozenOcean = new RealisticBiomeVanillaFrozenOcean(BiomeConfigVanilla.biomeConfigVanillaFrozenOcean);
        vanillaFrozenRiver = new RealisticBiomeVanillaFrozenRiver(BiomeConfigVanilla.biomeConfigVanillaFrozenRiver);
        vanillaIceMountains = new RealisticBiomeVanillaIceMountains(BiomeConfigVanilla.biomeConfigVanillaIceMountains);
        vanillaIcePlains = new RealisticBiomeVanillaIcePlains(BiomeConfigVanilla.biomeConfigVanillaIcePlains);
        vanillaIcePlainsSpikes = new RealisticBiomeVanillaIcePlainsSpikes(BiomeConfigVanilla.biomeConfigVanillaIcePlainsSpikes);
        vanillaJungle = new RealisticBiomeVanillaJungle(BiomeConfigVanilla.biomeConfigVanillaJungle);
        vanillaJungleEdge = new RealisticBiomeVanillaJungleEdge(BiomeConfigVanilla.biomeConfigVanillaJungleEdge);
        vanillaJungleEdgeM = new RealisticBiomeVanillaJungleEdgeM(BiomeConfigVanilla.biomeConfigVanillaJungleEdgeM);
        vanillaJungleHills = new RealisticBiomeVanillaJungleHills(BiomeConfigVanilla.biomeConfigVanillaJungleHills);
        vanillaJungleM = new RealisticBiomeVanillaJungleM(BiomeConfigVanilla.biomeConfigVanillaJungleM);
        vanillaMegaSpruceTaiga = new RealisticBiomeVanillaMegaSpruceTaiga(BiomeConfigVanilla.biomeConfigVanillaMegaSpruceTaiga);
        vanillaMegaTaiga = new RealisticBiomeVanillaMegaTaiga(BiomeConfigVanilla.biomeConfigVanillaMegaTaiga);
        vanillaMegaTaigaHills = new RealisticBiomeVanillaMegaTaigaHills(BiomeConfigVanilla.biomeConfigVanillaMegaTaigaHills);
        vanillaMesa = new RealisticBiomeVanillaMesa(BiomeConfigVanilla.biomeConfigVanillaMesa);
        vanillaMesaBryce = new RealisticBiomeVanillaMesaBryce(BiomeConfigVanilla.biomeConfigVanillaMesaBryce);
        vanillaMesaPlateau = new RealisticBiomeVanillaMesaPlateau(BiomeConfigVanilla.biomeConfigVanillaMesaPlateau);
        vanillaMesaPlateau_F = new RealisticBiomeVanillaMesaPlateauF(BiomeConfigVanilla.biomeConfigVanillaMesaPlateauF);
        vanillaMesaPlateauFM = new RealisticBiomeVanillaMesaPlateauFM(BiomeConfigVanilla.biomeConfigVanillaMesaPlateauFM);
        vanillaMesaPlateauM = new RealisticBiomeVanillaMesaPlateauM(BiomeConfigVanilla.biomeConfigVanillaMesaPlateauM);
        vanillaMushroomIsland = new RealisticBiomeVanillaMushroomIsland(BiomeConfigVanilla.biomeConfigVanillaMushroomIsland);
        vanillaMushroomIslandShore = new RealisticBiomeVanillaMushroomIslandShore(BiomeConfigVanilla.biomeConfigVanillaMushroomIslandShore);
        vanillaOcean = new RealisticBiomeVanillaOcean(BiomeConfigVanilla.biomeConfigVanillaOcean);
        vanillaPlains = new RealisticBiomeVanillaPlains(BiomeConfigVanilla.biomeConfigVanillaPlains);
        vanillaRedwoodTaigaHills = new RealisticBiomeVanillaRedwoodTaigaHills(BiomeConfigVanilla.biomeConfigVanillaRedwoodTaigaHills);
        vanillaRiver = new RealisticBiomeVanillaRiver(BiomeConfigVanilla.biomeConfigVanillaRiver);
        vanillaRoofedForest = new RealisticBiomeVanillaRoofedForest(BiomeConfigVanilla.biomeConfigVanillaRoofedForest);
        vanillaRoofedForestM = new RealisticBiomeVanillaRoofedForestM(BiomeConfigVanilla.biomeConfigVanillaRoofedForestM);
        vanillaSavanna = new RealisticBiomeVanillaSavanna(BiomeConfigVanilla.biomeConfigVanillaSavanna);
        vanillaSavannaM = new RealisticBiomeVanillaSavannaM(BiomeConfigVanilla.biomeConfigVanillaSavannaM);
        vanillaSavannaPlateau = new RealisticBiomeVanillaSavannaPlateau(BiomeConfigVanilla.biomeConfigVanillaSavannaPlateau);
        vanillaSavannaPlateauM = new RealisticBiomeVanillaSavannaPlateauM(BiomeConfigVanilla.biomeConfigVanillaSavannaPlateauM);
        vanillaStoneBeach = new RealisticBiomeVanillaStoneBeach(BiomeConfigVanilla.biomeConfigVanillaStoneBeach);
        vanillaSunflowerPlains = new RealisticBiomeVanillaSunflowerPlains(BiomeConfigVanilla.biomeConfigVanillaSunflowerPlains);
        vanillaSwampland = new RealisticBiomeVanillaSwampland(BiomeConfigVanilla.biomeConfigVanillaSwampland);
        vanillaSwamplandM = new RealisticBiomeVanillaSwamplandM(BiomeConfigVanilla.biomeConfigVanillaSwamplandM);
        vanillaTaiga = new RealisticBiomeVanillaTaiga(BiomeConfigVanilla.biomeConfigVanillaTaiga);
        vanillaTaigaHills = new RealisticBiomeVanillaTaigaHills(BiomeConfigVanilla.biomeConfigVanillaTaigaHills);
        vanillaTaigaM = new RealisticBiomeVanillaTaigaM(BiomeConfigVanilla.biomeConfigVanillaTaigaM);
    }
}
