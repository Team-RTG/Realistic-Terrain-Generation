package rtg.world.biome.realistic.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBase extends RealisticBiomeBase
{	
	public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	public static void init()
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
					new RealisticBiomeVanillaBeach(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_stoneBeach
			if (ConfigRTG.generateVanillaStoneBeach)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaStoneBeach(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_coldBeach
			if (ConfigRTG.generateVanillaColdBeach)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaColdBeach(),
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
					new RealisticBiomeVanillaDesert(),
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_desertHills
			if (ConfigRTG.generateVanillaDesertHills)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaDesertHills(),
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
					new RealisticBiomeVanillaExtremeHills(),
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_extremeHillsPlus
			if (ConfigRTG.generateVanillaExtremeHillsPlus)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaExtremeHillsPlus(),
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
					new RealisticBiomeVanillaForest(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_forestHills
			if (ConfigRTG.generateVanillaForestHills)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaForestHills(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_birchForest
			if (ConfigRTG.generateVanillaBirchForest)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaBirchForest(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_birchForestHills
			if (ConfigRTG.generateVanillaBirchForestHills)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaBirchForestHills(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_roofedForest
			if (ConfigRTG.generateVanillaRoofedForest)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaRoofedForest(),
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
					new RealisticBiomeVanillaIcePlains(),
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//vanilla_iceMountains
			if (ConfigRTG.generateVanillaIceMountains)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaIceMountains(),
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
					new RealisticBiomeVanillaJungle(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_jungleHills
			if (ConfigRTG.generateVanillaJungleHills)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaJungleHills(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_jungleEdge
			if (ConfigRTG.generateVanillaJungleEdge)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaJungleEdge(),
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
					new RealisticBiomeVanillaMesa(),
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_mesaPlateau
			if (ConfigRTG.generateVanillaMesaPlateau)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaMesaPlateau(),
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_mesaPlateau_F
			if (ConfigRTG.generateVanillaMesaPlateau_F)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaMesaPlateauF(),
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
					new RealisticBiomeVanillaMushroomIsland(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_mushroomIslandShore
			if (ConfigRTG.generateVanillaMushroomIslandShore)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaMushroomIslandShore(),
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
					new RealisticBiomeVanillaOcean(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_deepOcean
			if (ConfigRTG.generateVanillaDeepOcean)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaDeepOcean(),
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
					new RealisticBiomeVanillaPlains(),
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
			
			//vanilla_river
			if (ConfigRTG.generateVanillaRiver)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaRiver(),
					BiomeBase.BiomeCategory.WET
				);
			}
			
			//vanilla_frozenRiver
			if (ConfigRTG.generateVanillaFrozenRiver)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaFrozenRiver(),
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
					new RealisticBiomeVanillaSavanna(),
					BiomeBase.BiomeCategory.HOT
				);
			}
			
			//vanilla_savannaPlateau
			if (ConfigRTG.generateVanillaSavannaPlateau)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaSavannaPlateau(),
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
					new RealisticBiomeVanillaSwampland(),
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
					new RealisticBiomeVanillaTaiga(),
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_taigaHills
			if (ConfigRTG.generateVanillaTaigaHills)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaTaigaHills(),
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_coldTaiga
			if (ConfigRTG.generateVanillaColdTaiga)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaColdTaiga(),
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//vanilla_coldTaigaHills
			if (ConfigRTG.generateVanillaColdTaigaHills)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaColdTaigaHills(),
					BiomeBase.BiomeCategory.SNOW
				);
			}
			
			//vanilla_megaTaiga
			if (ConfigRTG.generateVanillaMegaTaiga)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaMegaTaiga(),
					BiomeBase.BiomeCategory.COLD
				);
			}
			
			//vanilla_megaTaigaHills
			if (ConfigRTG.generateVanillaMegaTaigaHills)
			{
				BiomeBase.addBiome(
					new RealisticBiomeVanillaMegaTaigaHills(),
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
}
