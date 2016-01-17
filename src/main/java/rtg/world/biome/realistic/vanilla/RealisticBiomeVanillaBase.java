package rtg.world.biome.realistic.vanilla;

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
			BiomeBase.addBiome(vanillaBeach);
			BiomeBase.addBiome(vanillaBirchForest);
			BiomeBase.addBiome(vanillaBirchForestHills);
			BiomeBase.addBiome(vanillaColdBeach);
			BiomeBase.addBiome(vanillaColdTaiga);
			BiomeBase.addBiome(vanillaColdTaigaHills);
			BiomeBase.addBiome(vanillaDeepOcean);
			BiomeBase.addBiome(vanillaDesert);
			BiomeBase.addBiome(vanillaDesertHills);
			BiomeBase.addBiome(vanillaExtremeHills);
			BiomeBase.addBiome(vanillaExtremeHillsEdge);
			BiomeBase.addBiome(vanillaExtremeHillsPlus);
			BiomeBase.addBiome(vanillaForest);
			BiomeBase.addBiome(vanillaForestHills);
			BiomeBase.addBiome(vanillaFrozenOcean);
			BiomeBase.addBiome(vanillaIcePlains);
			BiomeBase.addBiome(vanillaIceMountains);
			BiomeBase.addBiome(vanillaJungle);
			BiomeBase.addBiome(vanillaJungleEdge);
			BiomeBase.addBiome(vanillaJungleHills);
			BiomeBase.addBiome(vanillaMegaTaiga);
			BiomeBase.addBiome(vanillaMegaTaigaHills);
			BiomeBase.addBiome(vanillaMesa);
			BiomeBase.addBiome(vanillaMesaPlateau);
			BiomeBase.addBiome(vanillaMesaPlateau_F);
			BiomeBase.addBiome(vanillaMushroomIsland);
			BiomeBase.addBiome(vanillaMushroomIslandShore);
			BiomeBase.addBiome(vanillaOcean);
			BiomeBase.addBiome(vanillaPlains);
			BiomeBase.addBiome(vanillaRoofedForest);
			BiomeBase.addBiome(vanillaSavanna);
			BiomeBase.addBiome(vanillaSavannaPlateau);
			BiomeBase.addBiome(vanillaStoneBeach);
			BiomeBase.addBiome(vanillaSwampland);
			BiomeBase.addBiome(vanillaTaiga);
			BiomeBase.addBiome(vanillaTaigaHills);

            /**
             * Rivers will automatically get generated, so we don't need to add them here.
             */
            //BiomeBase.addBiome(vanillaRiver);
            //BiomeBase.addBiome(vanillaFrozenRiver);
			
		    BiomeBase.addBiome(vanillaSunflowerPlains);
		    BiomeBase.addBiome(vanillaDesertM);
		    BiomeBase.addBiome(vanillaExtremeHillsM);
		    BiomeBase.addBiome(vanillaFlowerForest);
		    BiomeBase.addBiome(vanillaTaigaM);
		    BiomeBase.addBiome(vanillaSwamplandM);
		    BiomeBase.addBiome(vanillaIcePlainsSpikes);
		    BiomeBase.addBiome(vanillaJungleM);
		    BiomeBase.addBiome(vanillaJungleEdgeM);
		    BiomeBase.addBiome(vanillaBirchForestM);
		    BiomeBase.addBiome(vanillaBirchForestHillsM);
		    BiomeBase.addBiome(vanillaRoofedForestM);
		    BiomeBase.addBiome(vanillaColdTaigaM);
		    BiomeBase.addBiome(vanillaMegaSpruceTaiga);
		    BiomeBase.addBiome(vanillaRedwoodTaigaHills);
		    BiomeBase.addBiome(vanillaExtremeHillsPlusM);
		    BiomeBase.addBiome(vanillaSavannaM);
		    BiomeBase.addBiome(vanillaSavannaPlateauM);
		    BiomeBase.addBiome(vanillaMesaBryce);
		    BiomeBase.addBiome(vanillaMesaPlateauFM);
		    BiomeBase.addBiome(vanillaMesaPlateauM);
		}
	}
}
