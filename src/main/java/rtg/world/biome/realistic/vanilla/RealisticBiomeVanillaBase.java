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
		}
	}
}
