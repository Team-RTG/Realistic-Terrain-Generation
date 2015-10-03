package rtg.world.biome.realistic.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBase extends RealisticBiomeBase
{
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
	
	public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	public static void addBiomes()
	{
		if (ConfigRTG.generateVanillaBiomes)
		{
			/*
			###################################################################################################
			# START BEACH BIOMES
			###################################################################################################
			*/
			
			//vanilla_beach
			if (ConfigRTG.generateVanillaBeach)
			{
				BiomeBase.addBiome(
					vanillaBeach,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_stoneBeach
			if (ConfigRTG.generateVanillaStoneBeach)
			{
				BiomeBase.addBiome(
					vanillaStoneBeach,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_coldBeach
			if (ConfigRTG.generateVanillaColdBeach)
			{
				BiomeBase.addBiome(
					vanillaColdBeach,
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			/*
			###################################################################################################
			# END BEACH BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START DESERT BIOMES
			###################################################################################################
			*/
			
			//vanilla_desert
			if (ConfigRTG.generateVanillaDesert)
			{
				BiomeBase.addBiome(
					vanillaDesert,
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_desertHills
			if (ConfigRTG.generateVanillaDesertHills)
			{
				BiomeBase.addBiome(
					vanillaDesertHills,
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			/*
			###################################################################################################
			# END DESERT BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START EXTREME HILLS BIOMES
			###################################################################################################
			*/
			
			//vanilla_extremeHills
			if (ConfigRTG.generateVanillaExtremeHills)
			{
				BiomeBase.addBiome(
					vanillaExtremeHills,
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_extremeHillsPlus
			if (ConfigRTG.generateVanillaExtremeHillsPlus)
			{
				BiomeBase.addBiome(
					vanillaExtremeHillsPlus,
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_extremeHillsEdge - NOT IMPLEMENTED IN VANILLA
			
			/*
			###################################################################################################
			# END EXTREME HILLS BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START FOREST BIOMES
			###################################################################################################
			*/
			
			//vanilla_forest
			if (ConfigRTG.generateVanillaForest)
			{
				BiomeBase.addBiome(
					vanillaForest,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_forestHills
			if (ConfigRTG.generateVanillaForestHills)
			{
				BiomeBase.addBiome(
					vanillaForestHills,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_birchForest
			if (ConfigRTG.generateVanillaBirchForest)
			{
				BiomeBase.addBiome(
					vanillaBirchForest,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_birchForestHills
			if (ConfigRTG.generateVanillaBirchForestHills)
			{
				BiomeBase.addBiome(
					vanillaBirchForestHills,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_roofedForest
			if (ConfigRTG.generateVanillaRoofedForest)
			{
				BiomeBase.addBiome(
					vanillaRoofedForest,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			/*
			###################################################################################################
			# END FOREST BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START ICE BIOMES
			###################################################################################################
			*/
			
			//vanilla_icePlains
			if (ConfigRTG.generateVanillaIcePlains)
			{
				BiomeBase.addBiome(
					vanillaIcePlains,
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//vanilla_iceMountains
			if (ConfigRTG.generateVanillaIceMountains)
			{
				BiomeBase.addBiome(
					vanillaIceMountains,
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			/*
			###################################################################################################
			# END ICE BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START JUNGLE BIOMES
			###################################################################################################
			*/
			
			//vanilla_jungle
			if (ConfigRTG.generateVanillaJungle)
			{
				BiomeBase.addBiome(
					vanillaJungle,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_jungleHills
			if (ConfigRTG.generateVanillaJungleHills)
			{
				BiomeBase.addBiome(
					vanillaJungleHills,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_jungleEdge
			if (ConfigRTG.generateVanillaJungleEdge)
			{
				BiomeBase.addBiome(
					vanillaJungleEdge,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			/*
			###################################################################################################
			# END JUNGLE BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START MESA BIOMES
			###################################################################################################
			*/
			
			//vanilla_mesa
			if (ConfigRTG.generateVanillaMesa)
			{
				BiomeBase.addBiome(
					vanillaMesa,
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_mesaPlateau
			if (ConfigRTG.generateVanillaMesaPlateau)
			{
				BiomeBase.addBiome(
					vanillaMesaPlateau,
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_mesaPlateau_F
			if (ConfigRTG.generateVanillaMesaPlateau_F)
			{
				BiomeBase.addBiome(
					vanillaMesaPlateau_F,
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			/*
			###################################################################################################
			# END MESA BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START MUSHROOM ISLAND BIOMES
			###################################################################################################
			*/
			
			//vanilla_mushroomIsland
			if (ConfigRTG.generateVanillaMushroomIsland)
			{
				BiomeBase.addBiome(
					vanillaMushroomIsland,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_mushroomIslandShore
			if (ConfigRTG.generateVanillaMushroomIslandShore)
			{
				BiomeBase.addBiome(
					vanillaMushroomIslandShore,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			/*
			###################################################################################################
			# END MUSHROOM ISLAND BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START OCEAN BIOMES
			###################################################################################################
			*/
			
			//vanilla_ocean
			if (ConfigRTG.generateVanillaOcean)
			{
				BiomeBase.addBiome(
					vanillaOcean,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_deepOcean
			if (ConfigRTG.generateVanillaDeepOcean)
			{
				BiomeBase.addBiome(
					vanillaDeepOcean,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_frozenOcean - NOT IMPLEMENTED IN VANILLA
			
			/*
			###################################################################################################
			# END OCEAN BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START PLAINS BIOMES
			###################################################################################################
			*/
			
			//vanilla_plains
			if (ConfigRTG.generateVanillaPlains)
			{
				BiomeBase.addBiome(
					vanillaPlains,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			/*
			###################################################################################################
			# END PLAINS BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START RIVER BIOMES
			###################################################################################################
			*/
			
			//vanilla_river - THIS BIOME WILL ALWAYS GENERATE SO WE DON'T NEED TO ADD IT TO THE LIST OF REALISTIC BIOMES
			//if (ConfigRTG.generateVanillaRiver)
			//{
			//	BiomeBase.addBiome(
			//		vanillaRiver,
			//		BiomeBase.BiomeCategory.WET
			//	);
			//}
			
			//vanilla_frozenRiver
			if (ConfigRTG.generateVanillaFrozenRiver)
			{
				BiomeBase.addBiome(
					vanillaFrozenRiver,
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			/*
			###################################################################################################
			# END RIVER BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START SAVANNA BIOMES
			###################################################################################################
			*/
			
			//vanilla_savanna
			if (ConfigRTG.generateVanillaSavanna)
			{
				BiomeBase.addBiome(
					vanillaSavanna,
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_savannaPlateau
			if (ConfigRTG.generateVanillaSavannaPlateau)
			{
				BiomeBase.addBiome(
					vanillaSavannaPlateau,
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			/*
			###################################################################################################
			# END SAVANNA BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START SWAMP BIOMES
			###################################################################################################
			*/
			
			//vanilla_swampland
			if (ConfigRTG.generateVanillaSwampland)
			{
				BiomeBase.addBiome(
					vanillaSwampland,
					BiomeBase.BiomeCategory.WET
				);
			}
			
			/*
			###################################################################################################
			# END SWAMP BIOMES
			###################################################################################################
			*/
			
			/*
			###################################################################################################
			# START TAIGA BIOMES
			###################################################################################################
			*/
			
			//vanilla_taiga
			if (ConfigRTG.generateVanillaTaiga)
			{
				BiomeBase.addBiome(
					vanillaTaiga,
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_taigaHills
			if (ConfigRTG.generateVanillaTaigaHills)
			{
				BiomeBase.addBiome(
					vanillaTaigaHills,
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_coldTaiga
			if (ConfigRTG.generateVanillaColdTaiga)
			{
				BiomeBase.addBiome(
					vanillaColdTaiga,
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//vanilla_coldTaigaHills
			if (ConfigRTG.generateVanillaColdTaigaHills)
			{
				BiomeBase.addBiome(
					vanillaColdTaigaHills,
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//vanilla_megaTaiga
			if (ConfigRTG.generateVanillaMegaTaiga)
			{
				BiomeBase.addBiome(
					vanillaMegaTaiga,
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_megaTaigaHills
			if (ConfigRTG.generateVanillaMegaTaigaHills)
			{
				BiomeBase.addBiome(
					vanillaMegaTaigaHills,
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			/*
			###################################################################################################
			# END TAIGA BIOMES
			###################################################################################################
			*/
		}
	}
	
	public static RealisticBiomeBase getRealisticVanillaBiomeFromVanillaVariableName(String name)
	{
		if (name.equals("beach")) { return RealisticBiomeVanillaBase.vanillaBeach; }
		else if (name.equals("birchForest")) { return RealisticBiomeVanillaBase.vanillaBirchForest; }
		else if (name.equals("birchForestHills")) { return RealisticBiomeVanillaBase.vanillaBirchForestHills; }
		else if (name.equals("coldBeach")) { return RealisticBiomeVanillaBase.vanillaColdBeach; }
		else if (name.equals("coldTaiga")) { return RealisticBiomeVanillaBase.vanillaColdTaiga; }
		else if (name.equals("coldTaigaHills")) { return RealisticBiomeVanillaBase.vanillaColdTaigaHills; }
		else if (name.equals("deepOcean")) { return RealisticBiomeVanillaBase.vanillaDeepOcean; }
		else if (name.equals("desert")) { return RealisticBiomeVanillaBase.vanillaDesert; }
		else if (name.equals("desertHills")) { return RealisticBiomeVanillaBase.vanillaDesertHills; }
		else if (name.equals("extremeHills")) { return RealisticBiomeVanillaBase.vanillaExtremeHills; }
		else if (name.equals("extremeHillsPlus")) { return RealisticBiomeVanillaBase.vanillaExtremeHillsPlus; }
		else if (name.equals("forest")) { return RealisticBiomeVanillaBase.vanillaForest; }
		else if (name.equals("forestHills")) { return RealisticBiomeVanillaBase.vanillaForestHills; }
		else if (name.equals("frozenRiver")) { return RealisticBiomeVanillaBase.vanillaFrozenRiver; }
		else if (name.equals("iceMountains")) { return RealisticBiomeVanillaBase.vanillaIceMountains; }
		else if (name.equals("icePlains")) { return RealisticBiomeVanillaBase.vanillaIcePlains; }
		else if (name.equals("jungle")) { return RealisticBiomeVanillaBase.vanillaJungle; }
		else if (name.equals("jungleEdge")) { return RealisticBiomeVanillaBase.vanillaJungleEdge; }
		else if (name.equals("jungleHills")) { return RealisticBiomeVanillaBase.vanillaJungleHills; }
		else if (name.equals("megaTaiga")) { return RealisticBiomeVanillaBase.vanillaMegaTaiga; }
		else if (name.equals("megaTaigaHills")) { return RealisticBiomeVanillaBase.vanillaMegaTaigaHills; }
		else if (name.equals("mesa")) { return RealisticBiomeVanillaBase.vanillaMesa; }
		else if (name.equals("mesaPlateau")) { return RealisticBiomeVanillaBase.vanillaMesaPlateau; }
		else if (name.equals("mesaPlateau_F")) { return RealisticBiomeVanillaBase.vanillaMesaPlateau_F; }
		else if (name.equals("mushroomIsland")) { return RealisticBiomeVanillaBase.vanillaMushroomIsland; }
		else if (name.equals("mushroomIslandShore")) { return RealisticBiomeVanillaBase.vanillaMushroomIslandShore; }
		else if (name.equals("ocean")) { return RealisticBiomeVanillaBase.vanillaOcean; }
		else if (name.equals("plains")) { return RealisticBiomeVanillaBase.vanillaPlains; }
		else if (name.equals("river")) { return RealisticBiomeVanillaBase.vanillaRiver; }
		else if (name.equals("roofedForest")) { return RealisticBiomeVanillaBase.vanillaRoofedForest; }
		else if (name.equals("savanna")) { return RealisticBiomeVanillaBase.vanillaSavanna; }
		else if (name.equals("savannaPlateau")) { return RealisticBiomeVanillaBase.vanillaSavannaPlateau; }
		else if (name.equals("stoneBeach")) { return RealisticBiomeVanillaBase.vanillaStoneBeach; }
		else if (name.equals("swampland")) { return RealisticBiomeVanillaBase.vanillaSwampland; }
		else if (name.equals("taiga")) { return RealisticBiomeVanillaBase.vanillaTaiga; }
		else if (name.equals("taigaHills")) { return RealisticBiomeVanillaBase.vanillaTaigaHills; }
		
		return null;
	}
}
