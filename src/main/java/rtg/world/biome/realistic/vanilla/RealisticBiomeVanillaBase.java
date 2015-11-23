package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaBase extends RealisticBiomeBase
{
    public static final int MUTATION_ADDEND = 128;
    
	public static RealisticBiomeBase vanillaBeach = new RealisticBiomeVanillaBeach();
	public static RealisticBiomeBase vanillaBirchForest = new RealisticBiomeVanillaBirchForest();
	public static RealisticBiomeBase vanillaBirchForestHills = new RealisticBiomeVanillaBirchForestHills();
	public static RealisticBiomeBase vanillaColdBeach = new RealisticBiomeVanillaColdBeach();
	public static RealisticBiomeBase vanillaColdTaiga = new RealisticBiomeVanillaColdTaiga();
	public static RealisticBiomeBase vanillaColdTaigaHills = new RealisticBiomeVanillaColdTaigaHills();
	public static RealisticBiomeBase vanillaDeepOcean = new RealisticBiomeVanillaDeepOcean();
	public static RealisticBiomeBase vanillaDesert = new RealisticBiomeVanillaDesert();
	public static RealisticBiomeBase vanillaDesertHills = new RealisticBiomeVanillaDesertHills();
	public static RealisticBiomeBase vanillaExtremeHills = new RealisticBiomeVanillaExtremeHills();
	public static RealisticBiomeBase vanillaExtremeHillsPlus = new RealisticBiomeVanillaExtremeHillsPlus();
	public static RealisticBiomeBase vanillaForest = new RealisticBiomeVanillaForest();
	public static RealisticBiomeBase vanillaForestHills = new RealisticBiomeVanillaForestHills();
	public static RealisticBiomeBase vanillaFrozenRiver = new RealisticBiomeVanillaFrozenRiver();
	public static RealisticBiomeBase vanillaIcePlains = new RealisticBiomeVanillaIcePlains();
	public static RealisticBiomeBase vanillaIceMountains = new RealisticBiomeVanillaIceMountains();
	public static RealisticBiomeBase vanillaJungle = new RealisticBiomeVanillaJungle();
	public static RealisticBiomeBase vanillaJungleEdge = new RealisticBiomeVanillaJungleEdge();
	public static RealisticBiomeBase vanillaJungleHills = new RealisticBiomeVanillaJungleHills();
	public static RealisticBiomeBase vanillaMegaTaiga = new RealisticBiomeVanillaMegaTaiga();
	public static RealisticBiomeBase vanillaMegaTaigaHills = new RealisticBiomeVanillaMegaTaigaHills();
	public static RealisticBiomeBase vanillaMesa = new RealisticBiomeVanillaMesa();
	public static RealisticBiomeBase vanillaMesaPlateau = new RealisticBiomeVanillaMesaPlateau();
	public static RealisticBiomeBase vanillaMesaPlateau_F = new RealisticBiomeVanillaMesaPlateauF();
	public static RealisticBiomeBase vanillaMushroomIsland = new RealisticBiomeVanillaMushroomIsland();
	public static RealisticBiomeBase vanillaMushroomIslandShore = new RealisticBiomeVanillaMushroomIslandShore();
	public static RealisticBiomeBase vanillaOcean = new RealisticBiomeVanillaOcean();
	public static RealisticBiomeBase vanillaPlains = new RealisticBiomeVanillaPlains();
	public static RealisticBiomeBase vanillaRiver = new RealisticBiomeVanillaRiver();
	public static RealisticBiomeBase vanillaRoofedForest = new RealisticBiomeVanillaRoofedForest();
	public static RealisticBiomeBase vanillaSavanna = new RealisticBiomeVanillaSavanna();
	public static RealisticBiomeBase vanillaSavannaPlateau = new RealisticBiomeVanillaSavannaPlateau();
	public static RealisticBiomeBase vanillaStoneBeach = new RealisticBiomeVanillaStoneBeach();
	public static RealisticBiomeBase vanillaSwampland = new RealisticBiomeVanillaSwampland();
	public static RealisticBiomeBase vanillaTaiga = new RealisticBiomeVanillaTaiga();
	public static RealisticBiomeBase vanillaTaigaHills = new RealisticBiomeVanillaTaigaHills();
	
    public static RealisticBiomeBase vanillaSunflowerPlains = new RealisticBiomeVanillaSunflowerPlains();
    public static RealisticBiomeBase vanillaDesertM = new RealisticBiomeVanillaDesertM();
    public static RealisticBiomeBase vanillaExtremeHillsM = new RealisticBiomeVanillaExtremeHillsM();
    public static RealisticBiomeBase vanillaFlowerForest = new RealisticBiomeVanillaFlowerForest();
    public static RealisticBiomeBase vanillaTaigaM = new RealisticBiomeVanillaTaigaM();
    public static RealisticBiomeBase vanillaSwamplandM = new RealisticBiomeVanillaSwamplandM();
    public static RealisticBiomeBase vanillaIcePlainsSpikes = new RealisticBiomeVanillaIcePlainsSpikes();
    public static RealisticBiomeBase vanillaJungleM = new RealisticBiomeVanillaJungleM();
    public static RealisticBiomeBase vanillaJungleEdgeM = new RealisticBiomeVanillaJungleEdgeM();
    public static RealisticBiomeBase vanillaBirchForestM = new RealisticBiomeVanillaBirchForestM();
    public static RealisticBiomeBase vanillaBirchForestHillsM = new RealisticBiomeVanillaBirchForestHillsM();
    public static RealisticBiomeBase vanillaRoofedForestM = new RealisticBiomeVanillaRoofedForestM();
    public static RealisticBiomeBase vanillaColdTaigaM = new RealisticBiomeVanillaColdTaigaM();
    public static RealisticBiomeBase vanillaMegaSpruceTaiga = new RealisticBiomeVanillaMegaSpruceTaiga();
    public static RealisticBiomeBase vanillaExtremeHillsPlusM = new RealisticBiomeVanillaExtremeHillsPlusM();
    public static RealisticBiomeBase vanillaSavannaM = new RealisticBiomeVanillaSavannaM();
    public static RealisticBiomeBase vanillaSavannaPlateauM = new RealisticBiomeVanillaSavannaPlateauM();
    public static RealisticBiomeBase vanillaMesaBryce = new RealisticBiomeVanillaMesaBryce();
    public static RealisticBiomeBase vanillaMesaPlateauFM = new RealisticBiomeVanillaMesaPlateauFM();
    public static RealisticBiomeBase vanillaMesaPlateauM = new RealisticBiomeVanillaMesaPlateauM();
	
	public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
		this.lavaLakeFrequency = 0;
	}
	
	public static void addBiomes()
	{
		if (ConfigVanilla.generateVanillaBiomes)
		{
			if (ConfigVanilla.generateVanillaBeach) { BiomeBase.addBiome(vanillaBeach); }
			if (ConfigVanilla.generateVanillaBirchForest) { BiomeBase.addBiome(vanillaBirchForest); }
			if (ConfigVanilla.generateVanillaBirchForestHills) { BiomeBase.addBiome(vanillaBirchForestHills); }
			if (ConfigVanilla.generateVanillaColdBeach) { BiomeBase.addBiome(vanillaColdBeach); }
			if (ConfigVanilla.generateVanillaColdTaiga) { BiomeBase.addBiome(vanillaColdTaiga); }
			if (ConfigVanilla.generateVanillaColdTaigaHills) { BiomeBase.addBiome(vanillaColdTaigaHills); }
			if (ConfigVanilla.generateVanillaDeepOcean) { BiomeBase.addBiome(vanillaDeepOcean); }
			if (ConfigVanilla.generateVanillaDesert) { BiomeBase.addBiome(vanillaDesert); }
			if (ConfigVanilla.generateVanillaDesertHills) { BiomeBase.addBiome(vanillaDesertHills); }
			if (ConfigVanilla.generateVanillaExtremeHills) { BiomeBase.addBiome(vanillaExtremeHills); }
			if (ConfigVanilla.generateVanillaExtremeHillsPlus) { BiomeBase.addBiome(vanillaExtremeHillsPlus); }
			if (ConfigVanilla.generateVanillaForest) { BiomeBase.addBiome(vanillaForest); }
			if (ConfigVanilla.generateVanillaForestHills) { BiomeBase.addBiome(vanillaForestHills); }
			if (ConfigVanilla.generateVanillaIcePlains) { BiomeBase.addBiome(vanillaIcePlains); }
			if (ConfigVanilla.generateVanillaIceMountains) { BiomeBase.addBiome(vanillaIceMountains); }
			if (ConfigVanilla.generateVanillaJungle) { BiomeBase.addBiome(vanillaJungle); }
			if (ConfigVanilla.generateVanillaJungleEdge) { BiomeBase.addBiome(vanillaJungleEdge); }
			if (ConfigVanilla.generateVanillaJungleHills) { BiomeBase.addBiome(vanillaJungleHills); }
			if (ConfigVanilla.generateVanillaMegaTaiga) { BiomeBase.addBiome(vanillaMegaTaiga); }
			if (ConfigVanilla.generateVanillaMegaTaigaHills) { BiomeBase.addBiome(vanillaMegaTaigaHills); }
			if (ConfigVanilla.generateVanillaMesa) { BiomeBase.addBiome(vanillaMesa); }
			if (ConfigVanilla.generateVanillaMesaPlateau) { BiomeBase.addBiome(vanillaMesaPlateau); }
			if (ConfigVanilla.generateVanillaMesaPlateau_F) { BiomeBase.addBiome(vanillaMesaPlateau_F); }
			if (ConfigVanilla.generateVanillaMushroomIsland) { BiomeBase.addBiome(vanillaMushroomIsland); }
			if (ConfigVanilla.generateVanillaMushroomIslandShore) { BiomeBase.addBiome(vanillaMushroomIslandShore); }
			if (ConfigVanilla.generateVanillaOcean) { BiomeBase.addBiome(vanillaOcean); }
			if (ConfigVanilla.generateVanillaPlains) { BiomeBase.addBiome(vanillaPlains); }
			if (ConfigVanilla.generateVanillaRoofedForest) { BiomeBase.addBiome(vanillaRoofedForest); }
			if (ConfigVanilla.generateVanillaSavanna) { BiomeBase.addBiome(vanillaSavanna); }
			if (ConfigVanilla.generateVanillaSavannaPlateau) { BiomeBase.addBiome(vanillaSavannaPlateau); }
			if (ConfigVanilla.generateVanillaStoneBeach) { BiomeBase.addBiome(vanillaStoneBeach); }
			if (ConfigVanilla.generateVanillaSwampland) { BiomeBase.addBiome(vanillaSwampland); }
			if (ConfigVanilla.generateVanillaTaiga) { BiomeBase.addBiome(vanillaTaiga); }
			if (ConfigVanilla.generateVanillaTaigaHills) { BiomeBase.addBiome(vanillaTaigaHills); }

            /**
             * Rivers will automatically get generated, so we don't need to add them here.
             */
            //if (ConfigVanilla.generateVanillaRiver) { BiomeBase.addBiome(vanillaRiver); }
            //if (ConfigVanilla.generateVanillaFrozenRiver) { BiomeBase.addBiome(vanillaFrozenRiver); }
			
		    if (ConfigVanilla.generateVanillaSunflowerPlains) { BiomeBase.addBiome(vanillaSunflowerPlains); }
		    if (ConfigVanilla.generateVanillaDesertM) { BiomeBase.addBiome(vanillaDesertM); }
		    if (ConfigVanilla.generateVanillaExtremeHillsM) { BiomeBase.addBiome(vanillaExtremeHillsM); }
		    if (ConfigVanilla.generateVanillaFlowerForest) { BiomeBase.addBiome(vanillaFlowerForest); }
		    if (ConfigVanilla.generateVanillaTaigaM) { BiomeBase.addBiome(vanillaTaigaM); }
		    if (ConfigVanilla.generateVanillaSwamplandM) { BiomeBase.addBiome(vanillaSwamplandM); }
		    if (ConfigVanilla.generateVanillaIcePlainsSpikes) { BiomeBase.addBiome(vanillaIcePlainsSpikes); }
		    if (ConfigVanilla.generateVanillaJungleM) { BiomeBase.addBiome(vanillaJungleM); }
		    if (ConfigVanilla.generateVanillaJungleEdgeM) { BiomeBase.addBiome(vanillaJungleEdgeM); }
		    if (ConfigVanilla.generateVanillaBirchForestM) { BiomeBase.addBiome(vanillaBirchForestM); }
		    if (ConfigVanilla.generateVanillaBirchForestHillsM) { BiomeBase.addBiome(vanillaBirchForestHillsM); }
		    if (ConfigVanilla.generateVanillaRoofedForestM) { BiomeBase.addBiome(vanillaRoofedForestM); }
		    if (ConfigVanilla.generateVanillaColdTaigaM) { BiomeBase.addBiome(vanillaColdTaigaM); }
		    if (ConfigVanilla.generateVanillaMegaSpruceTaiga) { BiomeBase.addBiome(vanillaMegaSpruceTaiga); }
		    if (ConfigVanilla.generateVanillaExtremeHillsPlusM) { BiomeBase.addBiome(vanillaExtremeHillsPlusM); }
		    if (ConfigVanilla.generateVanillaSavannaM) { BiomeBase.addBiome(vanillaSavannaM); }
		    if (ConfigVanilla.generateVanillaSavannaPlateauM) { BiomeBase.addBiome(vanillaSavannaPlateauM); }
		    if (ConfigVanilla.generateVanillaMesaBryce) { BiomeBase.addBiome(vanillaMesaBryce); }
		    if (ConfigVanilla.generateVanillaMesaPlateauFM) { BiomeBase.addBiome(vanillaMesaPlateauFM); }
		    if (ConfigVanilla.generateVanillaMesaPlateauM) { BiomeBase.addBiome(vanillaMesaPlateauM); }
		    
		    
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
            if (ConfigVanilla.villageVanillaExtremeHillsPlus) { BiomeBase.addVillageBiome(vanillaExtremeHillsPlus); }
            if (ConfigVanilla.villageVanillaForest) { BiomeBase.addVillageBiome(vanillaForest); }
            if (ConfigVanilla.villageVanillaForestHills) { BiomeBase.addVillageBiome(vanillaForestHills); }
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
            if (ConfigVanilla.villageVanillaExtremeHillsPlusM) { BiomeBase.addVillageBiome(vanillaExtremeHillsPlusM); }
            if (ConfigVanilla.villageVanillaSavannaM) { BiomeBase.addVillageBiome(vanillaSavannaM); }
            if (ConfigVanilla.villageVanillaSavannaPlateauM) { BiomeBase.addVillageBiome(vanillaSavannaPlateauM); }
            if (ConfigVanilla.villageVanillaMesaBryce) { BiomeBase.addVillageBiome(vanillaMesaBryce); }
            if (ConfigVanilla.villageVanillaMesaPlateauFM) { BiomeBase.addVillageBiome(vanillaMesaPlateauFM); }
            if (ConfigVanilla.villageVanillaMesaPlateauM) { BiomeBase.addVillageBiome(vanillaMesaPlateauM); }
		}
	}
}
