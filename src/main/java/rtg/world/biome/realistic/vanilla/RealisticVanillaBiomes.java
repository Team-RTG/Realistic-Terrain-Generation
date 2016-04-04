package rtg.world.biome.realistic.vanilla;

import rtg.api.mods.ModBiomes;

/**
 * @author topisani
 */
public class RealisticVanillaBiomes extends ModBiomes {

    public RealisticBiomeVanillaBeach beach;
    public RealisticBiomeVanillaBirchForest birchForest;
    public RealisticBiomeVanillaBirchForestHills birchForestHills;
    public RealisticBiomeVanillaBirchForestHillsM birchForestHillsM;
    public RealisticBiomeVanillaBirchForestM birchForestM;
    public RealisticBiomeVanillaColdBeach coldBeach;
    public RealisticBiomeVanillaColdTaiga coldTaiga;
    public RealisticBiomeVanillaColdTaigaHills coldTaigaHills;
    public RealisticBiomeVanillaColdTaigaM coldTaigaM;
    public RealisticBiomeVanillaDeepOcean deepOcean;
    public RealisticBiomeVanillaDesert desert;
    public RealisticBiomeVanillaDesertHills desertHills;
    public RealisticBiomeVanillaDesertM desertM;
    public RealisticBiomeVanillaExtremeHills extremeHills;
    public RealisticBiomeVanillaExtremeHillsEdge extremeHillsEdge;
    public RealisticBiomeVanillaExtremeHillsM extremeHillsM;
    public RealisticBiomeVanillaExtremeHillsPlus extremeHillsPlus;
    public RealisticBiomeVanillaExtremeHillsPlusM extremeHillsPlusM;
    public RealisticBiomeVanillaFlowerForest flowerForest;
    public RealisticBiomeVanillaForest forest;
    public RealisticBiomeVanillaForestHills forestHills;
    public RealisticBiomeVanillaFrozenOcean frozenOcean;
    public RealisticBiomeVanillaFrozenRiver frozenRiver;
    public RealisticBiomeVanillaIceMountains iceMountains;
    public RealisticBiomeVanillaIcePlains icePlains;
    public RealisticBiomeVanillaIcePlainsSpikes icePlainsSpikes;
    public RealisticBiomeVanillaJungle jungle;
    public RealisticBiomeVanillaJungleEdge jungleEdge;
    public RealisticBiomeVanillaJungleEdgeM jungleEdgeM;
    public RealisticBiomeVanillaJungleHills jungleHills;
    public RealisticBiomeVanillaJungleM jungleM;
    public RealisticBiomeVanillaMegaSpruceTaiga megaSpruceTaiga;
    public RealisticBiomeVanillaMegaTaiga megaTaiga;
    public RealisticBiomeVanillaMegaTaigaHills megaTaigaHills;
    public RealisticBiomeVanillaMesa mesa;
    public RealisticBiomeVanillaMesaBryce mesaBryce;
    public RealisticBiomeVanillaMesaPlateau mesaPlateau;
    public RealisticBiomeVanillaMesaPlateauF mesaPlateau_F;
    public RealisticBiomeVanillaMesaPlateauFM mesaPlateauFM;
    public RealisticBiomeVanillaMesaPlateauM mesaPlateauM;
    public RealisticBiomeVanillaMushroomIsland mushroomIsland;
    public RealisticBiomeVanillaMushroomIslandShore mushroomIslandShore;
    public RealisticBiomeVanillaOcean ocean;
    public RealisticBiomeVanillaPlains plains;
    public RealisticBiomeVanillaRedwoodTaigaHills redwoodTaigaHills;
    public RealisticBiomeVanillaRiver river;
    public RealisticBiomeVanillaRoofedForest roofedForest;
    public RealisticBiomeVanillaRoofedForestM roofedForestM;
    public RealisticBiomeVanillaSavanna savanna;
    public RealisticBiomeVanillaSavannaM savannaM;
    public RealisticBiomeVanillaSavannaPlateau savannaPlateau;
    public RealisticBiomeVanillaSavannaPlateauM savannaPlateauM;
    public RealisticBiomeVanillaStoneBeach stoneBeach;
    public RealisticBiomeVanillaSunflowerPlains sunflowerPlains;
    public RealisticBiomeVanillaSwampland swampland;
    public RealisticBiomeVanillaSwamplandM swamplandM;
    public RealisticBiomeVanillaTaiga taiga;
    public RealisticBiomeVanillaTaigaHills taigaHills;
    public RealisticBiomeVanillaTaigaM taigaM;

    @Override
    public void initBiomes() {
    	
	    addBiome(beach = new RealisticBiomeVanillaBeach());
	    addBiome(birchForest = new RealisticBiomeVanillaBirchForest());
	    addBiome(birchForestHills = new RealisticBiomeVanillaBirchForestHills());
	    addBiome(birchForestHillsM = new RealisticBiomeVanillaBirchForestHillsM());
	    addBiome(birchForestM = new RealisticBiomeVanillaBirchForestM());
	    addBiome(coldBeach = new RealisticBiomeVanillaColdBeach());
	    addBiome(coldTaiga = new RealisticBiomeVanillaColdTaiga());
	    addBiome(coldTaigaHills = new RealisticBiomeVanillaColdTaigaHills());
	    addBiome(coldTaigaM = new RealisticBiomeVanillaColdTaigaM());
	    addBiome(deepOcean = new RealisticBiomeVanillaDeepOcean());
	    addBiome(desert = new RealisticBiomeVanillaDesert());
	    addBiome(desertHills = new RealisticBiomeVanillaDesertHills());
	    addBiome(desertM = new RealisticBiomeVanillaDesertM());
	    addBiome(extremeHills = new RealisticBiomeVanillaExtremeHills());
	    addBiome(extremeHillsEdge = new RealisticBiomeVanillaExtremeHillsEdge());
	    addBiome(extremeHillsM = new RealisticBiomeVanillaExtremeHillsM());
	    addBiome(extremeHillsPlus = new RealisticBiomeVanillaExtremeHillsPlus());
	    addBiome(extremeHillsPlusM = new RealisticBiomeVanillaExtremeHillsPlusM());
	    addBiome(flowerForest = new RealisticBiomeVanillaFlowerForest());
	    addBiome(forest = new RealisticBiomeVanillaForest());
	    addBiome(forestHills = new RealisticBiomeVanillaForestHills());
	    addBiome(frozenOcean = new RealisticBiomeVanillaFrozenOcean());
	    addBiome(frozenRiver = new RealisticBiomeVanillaFrozenRiver());
	    addBiome(iceMountains = new RealisticBiomeVanillaIceMountains());
	    addBiome(icePlains = new RealisticBiomeVanillaIcePlains());
	    addBiome(icePlainsSpikes = new RealisticBiomeVanillaIcePlainsSpikes());
	    addBiome(jungle = new RealisticBiomeVanillaJungle());
	    addBiome(jungleEdge = new RealisticBiomeVanillaJungleEdge());
	    addBiome(jungleEdgeM = new RealisticBiomeVanillaJungleEdgeM());
	    addBiome(jungleHills = new RealisticBiomeVanillaJungleHills());
	    addBiome(jungleM = new RealisticBiomeVanillaJungleM());
	    addBiome(megaSpruceTaiga = new RealisticBiomeVanillaMegaSpruceTaiga());
	    addBiome(megaTaiga = new RealisticBiomeVanillaMegaTaiga());
	    addBiome(megaTaigaHills = new RealisticBiomeVanillaMegaTaigaHills());
	    addBiome(mesa = new RealisticBiomeVanillaMesa());
	    addBiome(mesaBryce = new RealisticBiomeVanillaMesaBryce());
	    addBiome(mesaPlateau = new RealisticBiomeVanillaMesaPlateau());
	    addBiome(mesaPlateau_F = new RealisticBiomeVanillaMesaPlateauF());
	    addBiome(mesaPlateauFM = new RealisticBiomeVanillaMesaPlateauFM());
	    addBiome(mesaPlateauM = new RealisticBiomeVanillaMesaPlateauM());
	    addBiome(mushroomIsland = new RealisticBiomeVanillaMushroomIsland());
	    addBiome(mushroomIslandShore = new RealisticBiomeVanillaMushroomIslandShore());
	    addBiome(ocean = new RealisticBiomeVanillaOcean());
	    addBiome(plains = new RealisticBiomeVanillaPlains());
	    addBiome(redwoodTaigaHills = new RealisticBiomeVanillaRedwoodTaigaHills());
	    addBiome(river = new RealisticBiomeVanillaRiver());
	    addBiome(roofedForest = new RealisticBiomeVanillaRoofedForest());
	    addBiome(roofedForestM = new RealisticBiomeVanillaRoofedForestM());
	    addBiome(savanna = new RealisticBiomeVanillaSavanna());
	    addBiome(savannaM = new RealisticBiomeVanillaSavannaM());
	    addBiome(savannaPlateau = new RealisticBiomeVanillaSavannaPlateau());
	    addBiome(savannaPlateauM = new RealisticBiomeVanillaSavannaPlateauM());
	    addBiome(stoneBeach = new RealisticBiomeVanillaStoneBeach());
	    addBiome(sunflowerPlains = new RealisticBiomeVanillaSunflowerPlains());
	    addBiome(swampland = new RealisticBiomeVanillaSwampland());
	    addBiome(swamplandM = new RealisticBiomeVanillaSwamplandM());
	    addBiome(taiga = new RealisticBiomeVanillaTaiga());
	    addBiome(taigaHills = new RealisticBiomeVanillaTaigaHills());
	    addBiome(taigaM = new RealisticBiomeVanillaTaigaM());
    }
}
