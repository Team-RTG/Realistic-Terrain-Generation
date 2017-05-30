package rtg.world.biome.realistic.vanilla;

import net.minecraft.world.biome.Biome;

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

    public RealisticBiomeVanillaBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);
    }

    @Override
    public Biome baseBiome() {
        return this.baseBiome;
    }

    @Override
    public Biome riverBiome() {
        return this.riverBiome;
    }

    @Override
    public String modSlug() {
        return "vanilla";
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    public static void addBiomes() {

        vanillaBeach = new RealisticBiomeVanillaBeach();
        vanillaBirchForest = new RealisticBiomeVanillaBirchForest();
        vanillaBirchForestHills = new RealisticBiomeVanillaBirchForestHills();
        vanillaBirchForestHillsM = new RealisticBiomeVanillaBirchForestHillsM();
        vanillaBirchForestM = new RealisticBiomeVanillaBirchForestM();
        vanillaColdBeach = new RealisticBiomeVanillaColdBeach();
        vanillaColdTaiga = new RealisticBiomeVanillaColdTaiga();
        vanillaColdTaigaHills = new RealisticBiomeVanillaColdTaigaHills();
        vanillaColdTaigaM = new RealisticBiomeVanillaColdTaigaM();
        vanillaDeepOcean = new RealisticBiomeVanillaDeepOcean();
        vanillaDesert = new RealisticBiomeVanillaDesert();
        vanillaDesertHills = new RealisticBiomeVanillaDesertHills();
        vanillaDesertM = new RealisticBiomeVanillaDesertM();
        vanillaExtremeHills = new RealisticBiomeVanillaExtremeHills();
        vanillaExtremeHillsEdge = new RealisticBiomeVanillaExtremeHillsEdge();
        vanillaExtremeHillsM = new RealisticBiomeVanillaExtremeHillsM();
        vanillaExtremeHillsPlus = new RealisticBiomeVanillaExtremeHillsPlus();
        vanillaExtremeHillsPlusM = new RealisticBiomeVanillaExtremeHillsPlusM();
        vanillaFlowerForest = new RealisticBiomeVanillaFlowerForest();
        vanillaForest = new RealisticBiomeVanillaForest();
        vanillaForestHills = new RealisticBiomeVanillaForestHills();
        vanillaFrozenOcean = new RealisticBiomeVanillaFrozenOcean();
        vanillaFrozenRiver = new RealisticBiomeVanillaFrozenRiver();
        vanillaIceMountains = new RealisticBiomeVanillaIceMountains();
        vanillaIcePlains = new RealisticBiomeVanillaIcePlains();
        vanillaIcePlainsSpikes = new RealisticBiomeVanillaIcePlainsSpikes();
        vanillaJungle = new RealisticBiomeVanillaJungle();
        vanillaJungleEdge = new RealisticBiomeVanillaJungleEdge();
        vanillaJungleEdgeM = new RealisticBiomeVanillaJungleEdgeM();
        vanillaJungleHills = new RealisticBiomeVanillaJungleHills();
        vanillaJungleM = new RealisticBiomeVanillaJungleM();
        vanillaMegaSpruceTaiga = new RealisticBiomeVanillaMegaSpruceTaiga();
        vanillaMegaTaiga = new RealisticBiomeVanillaMegaTaiga();
        vanillaMegaTaigaHills = new RealisticBiomeVanillaMegaTaigaHills();
        vanillaMesa = new RealisticBiomeVanillaMesa();
        vanillaMesaBryce = new RealisticBiomeVanillaMesaBryce();
        vanillaMesaPlateau = new RealisticBiomeVanillaMesaPlateau();
        vanillaMesaPlateau_F = new RealisticBiomeVanillaMesaPlateauF();
        vanillaMesaPlateauFM = new RealisticBiomeVanillaMesaPlateauFM();
        vanillaMesaPlateauM = new RealisticBiomeVanillaMesaPlateauM();
        vanillaMushroomIsland = new RealisticBiomeVanillaMushroomIsland();
        vanillaMushroomIslandShore = new RealisticBiomeVanillaMushroomIslandShore();
        vanillaOcean = new RealisticBiomeVanillaOcean();
        vanillaPlains = new RealisticBiomeVanillaPlains();
        vanillaRedwoodTaigaHills = new RealisticBiomeVanillaRedwoodTaigaHills();
        vanillaRiver = new RealisticBiomeVanillaRiver();
        vanillaRoofedForest = new RealisticBiomeVanillaRoofedForest();
        vanillaRoofedForestM = new RealisticBiomeVanillaRoofedForestM();
        vanillaSavanna = new RealisticBiomeVanillaSavanna();
        vanillaSavannaM = new RealisticBiomeVanillaSavannaM();
        vanillaSavannaPlateau = new RealisticBiomeVanillaSavannaPlateau();
        vanillaSavannaPlateauM = new RealisticBiomeVanillaSavannaPlateauM();
        vanillaStoneBeach = new RealisticBiomeVanillaStoneBeach();
        vanillaSunflowerPlains = new RealisticBiomeVanillaSunflowerPlains();
        vanillaSwampland = new RealisticBiomeVanillaSwampland();
        vanillaSwamplandM = new RealisticBiomeVanillaSwamplandM();
        vanillaTaiga = new RealisticBiomeVanillaTaiga();
        vanillaTaigaHills = new RealisticBiomeVanillaTaigaHills();
        vanillaTaigaM = new RealisticBiomeVanillaTaigaM();
    }
}
