package rtg.support;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.support.edit.*;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.biome.realistic.vanilla.*;

public class SupportVanilla
{
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
		
		
		/*
		------------------------------------------------------------------------------
		The following code has been copied from net.minecraft.world.biome.BiomeGenBase and is intended for reference only.
		Please don't uncomment this section. - WhichOnesPink
		------------------------------------------------------------------------------
	    public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setHeight(height_Oceans);
	    public static final BiomeGenBase plains = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains");
	    public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowPlains);
	    public static final BiomeGenBase extremeHills = (new BiomeGenHills(3, false)).setColor(6316128).setBiomeName("Extreme Hills").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
	    public static final BiomeGenBase forest = (new BiomeGenForest(4, 0)).setColor(353825).setBiomeName("Forest");
	    public static final BiomeGenBase taiga = (new BiomeGenTaiga(5, 0)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_MidPlains);
	    public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setHeight(height_PartiallySubmerged).setTemperatureRainfall(0.8F, 0.9F);
	    public static final BiomeGenBase river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setHeight(height_ShallowWaters);
	    public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
	    public static final BiomeGenBase sky = (new BiomeGenEnd(9)).setColor(8421631).setBiomeName("Sky").setDisableRain();
	    public static final BiomeGenBase frozenOcean = (new BiomeGenOcean(10)).setColor(9474208).setBiomeName("FrozenOcean").setEnableSnow().setHeight(height_Oceans).setTemperatureRainfall(0.0F, 0.5F);
	    public static final BiomeGenBase frozenRiver = (new BiomeGenRiver(11)).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow().setHeight(height_ShallowWaters).setTemperatureRainfall(0.0F, 0.5F);
	    public static final BiomeGenBase icePlains = (new BiomeGenSnow(12, false)).setColor(16777215).setBiomeName("Ice Plains").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F).setHeight(height_LowPlains);
	    public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13, false)).setColor(10526880).setBiomeName("Ice Mountains").setEnableSnow().setHeight(height_LowHills).setTemperatureRainfall(0.0F, 0.5F);
	    public static final BiomeGenBase mushroomIsland = (new BiomeGenMushroomIsland(14)).setColor(16711935).setBiomeName("MushroomIsland").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_LowIslands);
	    public static final BiomeGenBase mushroomIslandShore = (new BiomeGenMushroomIsland(15)).setColor(10486015).setBiomeName("MushroomIslandShore").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_Shores);
	    public static final BiomeGenBase beach = (new BiomeGenBeach(16)).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F).setHeight(height_Shores);
	    public static final BiomeGenBase desertHills = (new BiomeGenDesert(17)).setColor(13786898).setBiomeName("DesertHills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowHills);
	    public static final BiomeGenBase forestHills = (new BiomeGenForest(18, 0)).setColor(2250012).setBiomeName("ForestHills").setHeight(height_LowHills);
	    public static final BiomeGenBase taigaHills = (new BiomeGenTaiga(19, 0)).setColor(1456435).setBiomeName("TaigaHills").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_LowHills);
	    public static final BiomeGenBase extremeHillsEdge = (new BiomeGenHills(20, true)).setColor(7501978).setBiomeName("Extreme Hills Edge").setHeight(height_MidHills.attenuate()).setTemperatureRainfall(0.2F, 0.3F);
	    public static final BiomeGenBase jungle = (new BiomeGenJungle(21, false)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F);
	    public static final BiomeGenBase jungleHills = (new BiomeGenJungle(22, false)).setColor(2900485).setBiomeName("JungleHills").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F).setHeight(height_LowHills);
	    public static final BiomeGenBase jungleEdge = (new BiomeGenJungle(23, true)).setColor(6458135).setBiomeName("JungleEdge").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.8F);
	    public static final BiomeGenBase deepOcean = (new BiomeGenOcean(24)).setColor(48).setBiomeName("Deep Ocean").setHeight(height_DeepOceans);
	    public static final BiomeGenBase stoneBeach = (new BiomeGenStoneBeach(25)).setColor(10658436).setBiomeName("Stone Beach").setTemperatureRainfall(0.2F, 0.3F).setHeight(height_RockyWaters);
	    public static final BiomeGenBase coldBeach = (new BiomeGenBeach(26)).setColor(16445632).setBiomeName("Cold Beach").setTemperatureRainfall(0.05F, 0.3F).setHeight(height_Shores).setEnableSnow();
	    public static final BiomeGenBase birchForest = (new BiomeGenForest(27, 2)).setBiomeName("Birch Forest").setColor(3175492);
	    public static final BiomeGenBase birchForestHills = (new BiomeGenForest(28, 2)).setBiomeName("Birch Forest Hills").setColor(2055986).setHeight(height_LowHills);
	    public static final BiomeGenBase roofedForest = (new BiomeGenForest(29, 3)).setColor(4215066).setBiomeName("Roofed Forest");
	    public static final BiomeGenBase coldTaiga = (new BiomeGenTaiga(30, 0)).setColor(3233098).setBiomeName("Cold Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_MidPlains).func_150563_c(16777215);
	    public static final BiomeGenBase coldTaigaHills = (new BiomeGenTaiga(31, 0)).setColor(2375478).setBiomeName("Cold Taiga Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_LowHills).func_150563_c(16777215);
	    public static final BiomeGenBase megaTaiga = (new BiomeGenTaiga(32, 1)).setColor(5858897).setBiomeName("Mega Taiga").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_MidPlains);
	    public static final BiomeGenBase megaTaigaHills = (new BiomeGenTaiga(33, 1)).setColor(4542270).setBiomeName("Mega Taiga Hills").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_LowHills);
	    public static final BiomeGenBase extremeHillsPlus = (new BiomeGenHills(34, true)).setColor(5271632).setBiomeName("Extreme Hills+").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
	    public static final BiomeGenBase savanna = (new BiomeGenSavanna(35)).setColor(12431967).setBiomeName("Savanna").setTemperatureRainfall(1.2F, 0.0F).setDisableRain().setHeight(height_LowPlains);
	    public static final BiomeGenBase savannaPlateau = (new BiomeGenSavanna(36)).setColor(10984804).setBiomeName("Savanna Plateau").setTemperatureRainfall(1.0F, 0.0F).setDisableRain().setHeight(height_HighPlateaus);
	    public static final BiomeGenBase mesa = (new BiomeGenMesa(37, false, false)).setColor(14238997).setBiomeName("Mesa");
	    public static final BiomeGenBase mesaPlateau_F = (new BiomeGenMesa(38, false, true)).setColor(11573093).setBiomeName("Mesa Plateau F").setHeight(height_HighPlateaus);
	    public static final BiomeGenBase mesaPlateau = (new BiomeGenMesa(39, false, false)).setColor(13274213).setBiomeName("Mesa Plateau").setHeight(height_HighPlateaus);
		------------------------------------------------------------------------------
		*/
	}
}
