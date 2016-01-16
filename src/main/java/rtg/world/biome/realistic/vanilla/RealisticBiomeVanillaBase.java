package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanilla;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaBase extends RealisticBiomeBase
{
    public static final int MUTATION_ADDEND = 128;
    
	public static RealisticBiomeBase vanillaBeach = new RealisticBiomeVanillaBeach(BiomeConfigVanilla.biomeConfigVanillaBeach);
	public static RealisticBiomeBase vanillaBirchForest = new RealisticBiomeVanillaBirchForest(BiomeConfigVanilla.biomeConfigVanillaBirchForest);
	public static RealisticBiomeBase vanillaBirchForestHills = new RealisticBiomeVanillaBirchForestHills(BiomeConfigVanilla.biomeConfigVanillaBirchForestHills);
	public static RealisticBiomeBase vanillaColdBeach = new RealisticBiomeVanillaColdBeach(BiomeConfigVanilla.biomeConfigVanillaColdBeach);
	public static RealisticBiomeBase vanillaColdTaiga = new RealisticBiomeVanillaColdTaiga(BiomeConfigVanilla.biomeConfigVanillaColdTaiga);
	public static RealisticBiomeBase vanillaColdTaigaHills = new RealisticBiomeVanillaColdTaigaHills(BiomeConfigVanilla.biomeConfigVanillaColdTaigaHills);
	public static RealisticBiomeBase vanillaDeepOcean = new RealisticBiomeVanillaDeepOcean(BiomeConfigVanilla.biomeConfigVanillaDeepOcean);
	public static RealisticBiomeBase vanillaDesert = new RealisticBiomeVanillaDesert(BiomeConfigVanilla.biomeConfigVanillaDesert);
	public static RealisticBiomeBase vanillaDesertHills = new RealisticBiomeVanillaDesertHills(BiomeConfigVanilla.biomeConfigVanillaDesertHills);
	public static RealisticBiomeBase vanillaExtremeHills = new RealisticBiomeVanillaExtremeHills(BiomeConfigVanilla.biomeConfigVanillaExtremeHills);
	public static RealisticBiomeBase vanillaExtremeHillsEdge = new RealisticBiomeVanillaExtremeHillsEdge(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsEdge);
	public static RealisticBiomeBase vanillaExtremeHillsPlus = new RealisticBiomeVanillaExtremeHillsPlus(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsPlus);
	public static RealisticBiomeBase vanillaForest = new RealisticBiomeVanillaForest(BiomeConfigVanilla.biomeConfigVanillaForest);
	public static RealisticBiomeBase vanillaForestHills = new RealisticBiomeVanillaForestHills(BiomeConfigVanilla.biomeConfigVanillaForestHills);
	public static RealisticBiomeBase vanillaFrozenOcean = new RealisticBiomeVanillaFrozenOcean(BiomeConfigVanilla.biomeConfigVanillaFrozenOcean);
	public static RealisticBiomeBase vanillaFrozenRiver = new RealisticBiomeVanillaFrozenRiver(BiomeConfigVanilla.biomeConfigVanillaFrozenRiver);
	public static RealisticBiomeBase vanillaIcePlains = new RealisticBiomeVanillaIcePlains(BiomeConfigVanilla.biomeConfigVanillaIcePlains);
	public static RealisticBiomeBase vanillaIceMountains = new RealisticBiomeVanillaIceMountains(BiomeConfigVanilla.biomeConfigVanillaIceMountains);
	public static RealisticBiomeBase vanillaJungle = new RealisticBiomeVanillaJungle(BiomeConfigVanilla.biomeConfigVanillaJungle);
	public static RealisticBiomeBase vanillaJungleEdge = new RealisticBiomeVanillaJungleEdge(BiomeConfigVanilla.biomeConfigVanillaJungleEdge);
	public static RealisticBiomeBase vanillaJungleHills = new RealisticBiomeVanillaJungleHills(BiomeConfigVanilla.biomeConfigVanillaJungleHills);
	public static RealisticBiomeBase vanillaMegaTaiga = new RealisticBiomeVanillaMegaTaiga(BiomeConfigVanilla.biomeConfigVanillaMegaTaiga);
	public static RealisticBiomeBase vanillaMegaTaigaHills = new RealisticBiomeVanillaMegaTaigaHills(BiomeConfigVanilla.biomeConfigVanillaMegaTaigaHills);
	public static RealisticBiomeBase vanillaMesa = new RealisticBiomeVanillaMesa(BiomeConfigVanilla.biomeConfigVanillaMesa);
	public static RealisticBiomeBase vanillaMesaPlateau = new RealisticBiomeVanillaMesaPlateau(BiomeConfigVanilla.biomeConfigVanillaMesaPlateau);
	public static RealisticBiomeBase vanillaMesaPlateau_F = new RealisticBiomeVanillaMesaPlateauF(BiomeConfigVanilla.biomeConfigVanillaMesaPlateauF);
	public static RealisticBiomeBase vanillaMushroomIsland = new RealisticBiomeVanillaMushroomIsland(BiomeConfigVanilla.biomeConfigVanillaMushroomIsland);
	public static RealisticBiomeBase vanillaMushroomIslandShore = new RealisticBiomeVanillaMushroomIslandShore(BiomeConfigVanilla.biomeConfigVanillaMushroomIslandShore);
	public static RealisticBiomeBase vanillaOcean = new RealisticBiomeVanillaOcean(BiomeConfigVanilla.biomeConfigVanillaOcean);
	public static RealisticBiomeBase vanillaPlains = new RealisticBiomeVanillaPlains(BiomeConfigVanilla.biomeConfigVanillaPlains);
	public static RealisticBiomeBase vanillaRiver = new RealisticBiomeVanillaRiver(BiomeConfigVanilla.biomeConfigVanillaRiver);
	public static RealisticBiomeBase vanillaRoofedForest = new RealisticBiomeVanillaRoofedForest(BiomeConfigVanilla.biomeConfigVanillaRoofedForest);
	public static RealisticBiomeBase vanillaSavanna = new RealisticBiomeVanillaSavanna(BiomeConfigVanilla.biomeConfigVanillaSavanna);
	public static RealisticBiomeBase vanillaSavannaPlateau = new RealisticBiomeVanillaSavannaPlateau(BiomeConfigVanilla.biomeConfigVanillaSavannaPlateau);
	public static RealisticBiomeBase vanillaStoneBeach = new RealisticBiomeVanillaStoneBeach(BiomeConfigVanilla.biomeConfigVanillaStoneBeach);
	public static RealisticBiomeBase vanillaSwampland = new RealisticBiomeVanillaSwampland(BiomeConfigVanilla.biomeConfigVanillaSwampland);
	public static RealisticBiomeBase vanillaTaiga = new RealisticBiomeVanillaTaiga(BiomeConfigVanilla.biomeConfigVanillaTaiga);
	public static RealisticBiomeBase vanillaTaigaHills = new RealisticBiomeVanillaTaigaHills(BiomeConfigVanilla.biomeConfigVanillaTaigaHills);
	
    public static RealisticBiomeBase vanillaSunflowerPlains = new RealisticBiomeVanillaSunflowerPlains(BiomeConfigVanilla.biomeConfigVanillaSunflowerPlains);
    public static RealisticBiomeBase vanillaDesertM = new RealisticBiomeVanillaDesertM(BiomeConfigVanilla.biomeConfigVanillaDesertM);
    public static RealisticBiomeBase vanillaExtremeHillsM = new RealisticBiomeVanillaExtremeHillsM(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsM);
    public static RealisticBiomeBase vanillaFlowerForest = new RealisticBiomeVanillaFlowerForest(BiomeConfigVanilla.biomeConfigVanillaFlowerForest);
    public static RealisticBiomeBase vanillaTaigaM = new RealisticBiomeVanillaTaigaM(BiomeConfigVanilla.biomeConfigVanillaTaigaM);
    public static RealisticBiomeBase vanillaSwamplandM = new RealisticBiomeVanillaSwamplandM(BiomeConfigVanilla.biomeConfigVanillaSwamplandM);
    public static RealisticBiomeBase vanillaIcePlainsSpikes = new RealisticBiomeVanillaIcePlainsSpikes(BiomeConfigVanilla.biomeConfigVanillaIcePlainsSpikes);
    public static RealisticBiomeBase vanillaJungleM = new RealisticBiomeVanillaJungleM(BiomeConfigVanilla.biomeConfigVanillaJungleM);
    public static RealisticBiomeBase vanillaJungleEdgeM = new RealisticBiomeVanillaJungleEdgeM(BiomeConfigVanilla.biomeConfigVanillaJungleEdgeM);
    public static RealisticBiomeBase vanillaBirchForestM = new RealisticBiomeVanillaBirchForestM(BiomeConfigVanilla.biomeConfigVanillaBirchForestM);
    public static RealisticBiomeBase vanillaBirchForestHillsM = new RealisticBiomeVanillaBirchForestHillsM(BiomeConfigVanilla.biomeConfigVanillaBirchForestHillsM);
    public static RealisticBiomeBase vanillaRoofedForestM = new RealisticBiomeVanillaRoofedForestM(BiomeConfigVanilla.biomeConfigVanillaRoofedForestM);
    public static RealisticBiomeBase vanillaColdTaigaM = new RealisticBiomeVanillaColdTaigaM(BiomeConfigVanilla.biomeConfigVanillaColdTaigaM);
    public static RealisticBiomeBase vanillaMegaSpruceTaiga = new RealisticBiomeVanillaMegaSpruceTaiga(BiomeConfigVanilla.biomeConfigVanillaMegaSpruceTaiga);
    public static RealisticBiomeBase vanillaRedwoodTaigaHills = new RealisticBiomeVanillaRedwoodTaigaHills(BiomeConfigVanilla.biomeConfigVanillaRedwoodTaigaHills);
    public static RealisticBiomeBase vanillaExtremeHillsPlusM = new RealisticBiomeVanillaExtremeHillsPlusM(BiomeConfigVanilla.biomeConfigVanillaExtremeHillsPlusM);
    public static RealisticBiomeBase vanillaSavannaM = new RealisticBiomeVanillaSavannaM(BiomeConfigVanilla.biomeConfigVanillaSavannaM);
    public static RealisticBiomeBase vanillaSavannaPlateauM = new RealisticBiomeVanillaSavannaPlateauM(BiomeConfigVanilla.biomeConfigVanillaSavannaPlateauM);
    public static RealisticBiomeBase vanillaMesaBryce = new RealisticBiomeVanillaMesaBryce(BiomeConfigVanilla.biomeConfigVanillaMesaBryce);
    public static RealisticBiomeBase vanillaMesaPlateauFM = new RealisticBiomeVanillaMesaPlateauFM(BiomeConfigVanilla.biomeConfigVanillaMesaPlateauFM);
    public static RealisticBiomeBase vanillaMesaPlateauM = new RealisticBiomeVanillaMesaPlateauM(BiomeConfigVanilla.biomeConfigVanillaMesaPlateauM);
	
	public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
		this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (ConfigVanilla.generateVanillaBiomes)
		{
			if (vanillaBeach.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaBeach); }
			if (vanillaBirchForest.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaBirchForest); }
			if (vanillaBirchForestHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaBirchForestHills); }
			if (vanillaColdBeach.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaColdBeach); }
			if (vanillaColdTaiga.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaColdTaiga); }
			if (vanillaColdTaigaHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaColdTaigaHills); }
			if (vanillaDeepOcean.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaDeepOcean); }
			if (vanillaDesert.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaDesert); }
			if (vanillaDesertHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaDesertHills); }
			if (vanillaExtremeHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaExtremeHills); }
			if (vanillaExtremeHillsEdge.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaExtremeHillsEdge); }
			if (vanillaExtremeHillsPlus.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaExtremeHillsPlus); }
			if (vanillaForest.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaForest); }
			if (vanillaForestHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaForestHills); }
			if (vanillaFrozenOcean.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaFrozenOcean); }
			if (vanillaIcePlains.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaIcePlains); }
			if (vanillaIceMountains.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaIceMountains); }
			if (vanillaJungle.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaJungle); }
			if (vanillaJungleEdge.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaJungleEdge); }
			if (vanillaJungleHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaJungleHills); }
			if (vanillaMegaTaiga.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMegaTaiga); }
			if (vanillaMegaTaigaHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMegaTaigaHills); }
			if (vanillaMesa.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMesa); }
			if (vanillaMesaPlateau.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMesaPlateau); }
			if (vanillaMesaPlateau_F.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMesaPlateau_F); }
			if (vanillaMushroomIsland.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMushroomIsland); }
			if (vanillaMushroomIslandShore.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMushroomIslandShore); }
			if (vanillaOcean.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaOcean); }
			if (vanillaPlains.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaPlains); }
			if (vanillaRoofedForest.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaRoofedForest); }
			if (vanillaSavanna.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaSavanna); }
			if (vanillaSavannaPlateau.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaSavannaPlateau); }
			if (vanillaStoneBeach.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaStoneBeach); }
			if (vanillaSwampland.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaSwampland); }
			if (vanillaTaiga.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaTaiga); }
			if (vanillaTaigaHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaTaigaHills); }

            /**
             * Rivers will automatically get generated, so we don't need to add them here.
             */
            //if (vanillaRiver.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaRiver); }
            //if (vanillaFrozenRiver.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaFrozenRiver); }
			
		    if (vanillaSunflowerPlains.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaSunflowerPlains); }
		    if (vanillaDesertM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaDesertM); }
		    if (vanillaExtremeHillsM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaExtremeHillsM); }
		    if (vanillaFlowerForest.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaFlowerForest); }
		    if (vanillaTaigaM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaTaigaM); }
		    if (vanillaSwamplandM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaSwamplandM); }
		    if (vanillaIcePlainsSpikes.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaIcePlainsSpikes); }
		    if (vanillaJungleM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaJungleM); }
		    if (vanillaJungleEdgeM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaJungleEdgeM); }
		    if (vanillaBirchForestM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaBirchForestM); }
		    if (vanillaBirchForestHillsM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaBirchForestHillsM); }
		    if (vanillaRoofedForestM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaRoofedForestM); }
		    if (vanillaColdTaigaM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaColdTaigaM); }
		    if (vanillaMegaSpruceTaiga.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMegaSpruceTaiga); }
		    if (vanillaRedwoodTaigaHills.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaRedwoodTaigaHills); }
		    if (vanillaExtremeHillsPlusM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaExtremeHillsPlusM); }
		    if (vanillaSavannaM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaSavannaM); }
		    if (vanillaSavannaPlateauM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaSavannaPlateauM); }
		    if (vanillaMesaBryce.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMesaBryce); }
		    if (vanillaMesaPlateauFM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMesaPlateauFM); }
		    if (vanillaMesaPlateauM.config._boolean(BiomeConfig.enableBiomeId)) { BiomeBase.addBiome(vanillaMesaPlateauM); }
		    
		    
            if (ConfigVanilla.villageVanillaBeach) { BiomeBase.addVillageBiome(vanillaBeach); }
            if (ConfigVanilla.villageVanillaBirchForest) { BiomeBase.addVillageBiome(vanillaBirchForest); }
            if (ConfigVanilla.villageVanillaBirchForestHills) { BiomeBase.addVillageBiome(vanillaBirchForestHills); }
            if (ConfigVanilla.villageVanillaColdBeach) { BiomeBase.addVillageBiome(vanillaColdBeach); }
            if (ConfigVanilla.villageVanillaColdTaiga) { BiomeBase.addVillageBiome(vanillaColdTaiga); }
            if (ConfigVanilla.villageVanillaColdTaigaHills) { BiomeBase.addVillageBiome(vanillaColdTaigaHills); }
            if (ConfigVanilla.villageVanillaDeepOcean) { BiomeBase.addVillageBiome(vanillaDeepOcean); }
            if (ConfigVanilla.villageVanillaDesert) { BiomeBase.addVillageBiome(vanillaDesert); }
            if (ConfigVanilla.villageVanillaDesertHills) { BiomeBase.addVillageBiome(vanillaDesertHills); }
            if (ConfigVanilla.villageVanillaExtremeHills) { BiomeBase.addVillageBiome(vanillaExtremeHills); }
            if (ConfigVanilla.villageVanillaExtremeHillsEdge) { BiomeBase.addVillageBiome(vanillaExtremeHillsEdge); }
            if (ConfigVanilla.villageVanillaExtremeHillsPlus) { BiomeBase.addVillageBiome(vanillaExtremeHillsPlus); }
            if (ConfigVanilla.villageVanillaForest) { BiomeBase.addVillageBiome(vanillaForest); }
            if (ConfigVanilla.villageVanillaForestHills) { BiomeBase.addVillageBiome(vanillaForestHills); }
            if (ConfigVanilla.villageVanillaFrozenOcean) { BiomeBase.addVillageBiome(vanillaFrozenOcean); }
            if (ConfigVanilla.villageVanillaIcePlains) { BiomeBase.addVillageBiome(vanillaIcePlains); }
            if (ConfigVanilla.villageVanillaIceMountains) { BiomeBase.addVillageBiome(vanillaIceMountains); }
            if (ConfigVanilla.villageVanillaJungle) { BiomeBase.addVillageBiome(vanillaJungle); }
            if (ConfigVanilla.villageVanillaJungleEdge) { BiomeBase.addVillageBiome(vanillaJungleEdge); }
            if (ConfigVanilla.villageVanillaJungleHills) { BiomeBase.addVillageBiome(vanillaJungleHills); }
            if (ConfigVanilla.villageVanillaMegaTaiga) { BiomeBase.addVillageBiome(vanillaMegaTaiga); }
            if (ConfigVanilla.villageVanillaMegaTaigaHills) { BiomeBase.addVillageBiome(vanillaMegaTaigaHills); }
            if (ConfigVanilla.villageVanillaMesa) { BiomeBase.addVillageBiome(vanillaMesa); }
            if (ConfigVanilla.villageVanillaMesaPlateau) { BiomeBase.addVillageBiome(vanillaMesaPlateau); }
            if (ConfigVanilla.villageVanillaMesaPlateau_F) { BiomeBase.addVillageBiome(vanillaMesaPlateau_F); }
            if (ConfigVanilla.villageVanillaMushroomIsland) { BiomeBase.addVillageBiome(vanillaMushroomIsland); }
            if (ConfigVanilla.villageVanillaMushroomIslandShore) { BiomeBase.addVillageBiome(vanillaMushroomIslandShore); }
            if (ConfigVanilla.villageVanillaOcean) { BiomeBase.addVillageBiome(vanillaOcean); }
            if (ConfigVanilla.villageVanillaPlains) { BiomeBase.addVillageBiome(vanillaPlains); }
            if (ConfigVanilla.villageVanillaRoofedForest) { BiomeBase.addVillageBiome(vanillaRoofedForest); }
            if (ConfigVanilla.villageVanillaSavanna) { BiomeBase.addVillageBiome(vanillaSavanna); }
            if (ConfigVanilla.villageVanillaSavannaPlateau) { BiomeBase.addVillageBiome(vanillaSavannaPlateau); }
            if (ConfigVanilla.villageVanillaStoneBeach) { BiomeBase.addVillageBiome(vanillaStoneBeach); }
            if (ConfigVanilla.villageVanillaSwampland) { BiomeBase.addVillageBiome(vanillaSwampland); }
            if (ConfigVanilla.villageVanillaTaiga) { BiomeBase.addVillageBiome(vanillaTaiga); }
            if (ConfigVanilla.villageVanillaTaigaHills) { BiomeBase.addVillageBiome(vanillaTaigaHills); }
            if (ConfigVanilla.villageVanillaSunflowerPlains) { BiomeBase.addVillageBiome(vanillaSunflowerPlains); }
            if (ConfigVanilla.villageVanillaDesertM) { BiomeBase.addVillageBiome(vanillaDesertM); }
            if (ConfigVanilla.villageVanillaExtremeHillsM) { BiomeBase.addVillageBiome(vanillaExtremeHillsM); }
            if (ConfigVanilla.villageVanillaFlowerForest) { BiomeBase.addVillageBiome(vanillaFlowerForest); }
            if (ConfigVanilla.villageVanillaTaigaM) { BiomeBase.addVillageBiome(vanillaTaigaM); }
            if (ConfigVanilla.villageVanillaSwamplandM) { BiomeBase.addVillageBiome(vanillaSwamplandM); }
            if (ConfigVanilla.villageVanillaIcePlainsSpikes) { BiomeBase.addVillageBiome(vanillaIcePlainsSpikes); }
            if (ConfigVanilla.villageVanillaJungleM) { BiomeBase.addVillageBiome(vanillaJungleM); }
            if (ConfigVanilla.villageVanillaJungleEdgeM) { BiomeBase.addVillageBiome(vanillaJungleEdgeM); }
            if (ConfigVanilla.villageVanillaBirchForestM) { BiomeBase.addVillageBiome(vanillaBirchForestM); }
            if (ConfigVanilla.villageVanillaBirchForestHillsM) { BiomeBase.addVillageBiome(vanillaBirchForestHillsM); }
            if (ConfigVanilla.villageVanillaRoofedForestM) { BiomeBase.addVillageBiome(vanillaRoofedForestM); }
            if (ConfigVanilla.villageVanillaColdTaigaM) { BiomeBase.addVillageBiome(vanillaColdTaigaM); }
            if (ConfigVanilla.villageVanillaMegaSpruceTaiga) { BiomeBase.addVillageBiome(vanillaMegaSpruceTaiga); }
            if (ConfigVanilla.villageVanillaRedwoodTaigaHills) { BiomeBase.addVillageBiome(vanillaRedwoodTaigaHills); }
            if (ConfigVanilla.villageVanillaExtremeHillsPlusM) { BiomeBase.addVillageBiome(vanillaExtremeHillsPlusM); }
            if (ConfigVanilla.villageVanillaSavannaM) { BiomeBase.addVillageBiome(vanillaSavannaM); }
            if (ConfigVanilla.villageVanillaSavannaPlateauM) { BiomeBase.addVillageBiome(vanillaSavannaPlateauM); }
            if (ConfigVanilla.villageVanillaMesaBryce) { BiomeBase.addVillageBiome(vanillaMesaBryce); }
            if (ConfigVanilla.villageVanillaMesaPlateauFM) { BiomeBase.addVillageBiome(vanillaMesaPlateauFM); }
            if (ConfigVanilla.villageVanillaMesaPlateauM) { BiomeBase.addVillageBiome(vanillaMesaPlateauM); }
		}
	}
}
