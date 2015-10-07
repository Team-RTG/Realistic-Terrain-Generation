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
			if (ConfigRTG.generateVanillaBeach) { BiomeBase.addBiome(vanillaBeach); }
			if (ConfigRTG.generateVanillaBirchForest) { BiomeBase.addBiome(vanillaBirchForest); }
			if (ConfigRTG.generateVanillaBirchForestHills) { BiomeBase.addBiome(vanillaBirchForestHills); }
			if (ConfigRTG.generateVanillaColdBeach) { BiomeBase.addBiome(vanillaColdBeach); }
			if (ConfigRTG.generateVanillaColdTaiga) { BiomeBase.addBiome(vanillaColdTaiga); }
			if (ConfigRTG.generateVanillaColdTaigaHills) { BiomeBase.addBiome(vanillaColdTaigaHills); }
			if (ConfigRTG.generateVanillaDeepOcean) { BiomeBase.addBiome(vanillaDeepOcean); }
			if (ConfigRTG.generateVanillaDesert) { BiomeBase.addBiome(vanillaDesert); }
			if (ConfigRTG.generateVanillaDesertHills) { BiomeBase.addBiome(vanillaDesertHills); }
			if (ConfigRTG.generateVanillaExtremeHills) { BiomeBase.addBiome(vanillaExtremeHills); }
			if (ConfigRTG.generateVanillaExtremeHillsPlus) { BiomeBase.addBiome(vanillaExtremeHillsPlus); }
			if (ConfigRTG.generateVanillaForest) { BiomeBase.addBiome(vanillaForest); }
			if (ConfigRTG.generateVanillaForestHills) { BiomeBase.addBiome(vanillaForestHills); }
			if (ConfigRTG.generateVanillaFrozenRiver) { BiomeBase.addBiome(vanillaFrozenRiver); }
			if (ConfigRTG.generateVanillaIcePlains) { BiomeBase.addBiome(vanillaIcePlains); }
			if (ConfigRTG.generateVanillaIceMountains) { BiomeBase.addBiome(vanillaIceMountains); }
			if (ConfigRTG.generateVanillaJungle) { BiomeBase.addBiome(vanillaJungle); }
			if (ConfigRTG.generateVanillaJungleEdge) { BiomeBase.addBiome(vanillaJungleEdge); }
			if (ConfigRTG.generateVanillaJungleHills) { BiomeBase.addBiome(vanillaJungleHills); }
			if (ConfigRTG.generateVanillaMegaTaiga) { BiomeBase.addBiome(vanillaMegaTaiga); }
			if (ConfigRTG.generateVanillaMegaTaigaHills) { BiomeBase.addBiome(vanillaMegaTaigaHills); }
			if (ConfigRTG.generateVanillaMesa) { BiomeBase.addBiome(vanillaMesa); }
			if (ConfigRTG.generateVanillaMesaPlateau) { BiomeBase.addBiome(vanillaMesaPlateau); }
			if (ConfigRTG.generateVanillaMesaPlateau_F) { BiomeBase.addBiome(vanillaMesaPlateau_F); }
			if (ConfigRTG.generateVanillaMushroomIsland) { BiomeBase.addBiome(vanillaMushroomIsland); }
			if (ConfigRTG.generateVanillaMushroomIslandShore) { BiomeBase.addBiome(vanillaMushroomIslandShore); }
			if (ConfigRTG.generateVanillaOcean) { BiomeBase.addBiome(vanillaOcean); }
			if (ConfigRTG.generateVanillaPlains) { BiomeBase.addBiome(vanillaPlains); }
			
			/**
			 * Rivers will automatically get generated, so we don't need to add them here.
			 */
			//if (ConfigRTG.generateVanillaRiver) { BiomeBase.addBiome(vanillaRiver); }
			
			if (ConfigRTG.generateVanillaRoofedForest) { BiomeBase.addBiome(vanillaRoofedForest); }
			if (ConfigRTG.generateVanillaSavanna) { BiomeBase.addBiome(vanillaSavanna); }
			if (ConfigRTG.generateVanillaSavannaPlateau) { BiomeBase.addBiome(vanillaSavannaPlateau); }
			if (ConfigRTG.generateVanillaStoneBeach) { BiomeBase.addBiome(vanillaStoneBeach); }
			if (ConfigRTG.generateVanillaSwampland) { BiomeBase.addBiome(vanillaSwampland); }
			if (ConfigRTG.generateVanillaTaiga) { BiomeBase.addBiome(vanillaTaiga); }
			if (ConfigRTG.generateVanillaTaigaHills) { BiomeBase.addBiome(vanillaTaigaHills); }
		}
	}
	
	public static RealisticBiomeBase getRealisticVanillaBiomeFromVanillaVariableName(String name)
	{
		if (name.equals("beach")) { return vanillaBeach; }
		else if (name.equals("birchForest")) { return vanillaBirchForest; }
		else if (name.equals("birchForestHills")) { return vanillaBirchForestHills; }
		else if (name.equals("coldBeach")) { return vanillaColdBeach; }
		else if (name.equals("coldTaiga")) { return vanillaColdTaiga; }
		else if (name.equals("coldTaigaHills")) { return vanillaColdTaigaHills; }
		else if (name.equals("deepOcean")) { return vanillaDeepOcean; }
		else if (name.equals("desert")) { return vanillaDesert; }
		else if (name.equals("desertHills")) { return vanillaDesertHills; }
		else if (name.equals("extremeHills")) { return vanillaExtremeHills; }
		else if (name.equals("extremeHillsPlus")) { return vanillaExtremeHillsPlus; }
		else if (name.equals("forest")) { return vanillaForest; }
		else if (name.equals("forestHills")) { return vanillaForestHills; }
		else if (name.equals("frozenRiver")) { return vanillaFrozenRiver; }
		else if (name.equals("iceMountains")) { return vanillaIceMountains; }
		else if (name.equals("icePlains")) { return vanillaIcePlains; }
		else if (name.equals("jungle")) { return vanillaJungle; }
		else if (name.equals("jungleEdge")) { return vanillaJungleEdge; }
		else if (name.equals("jungleHills")) { return vanillaJungleHills; }
		else if (name.equals("megaTaiga")) { return vanillaMegaTaiga; }
		else if (name.equals("megaTaigaHills")) { return vanillaMegaTaigaHills; }
		else if (name.equals("mesa")) { return vanillaMesa; }
		else if (name.equals("mesaPlateau")) { return vanillaMesaPlateau; }
		else if (name.equals("mesaPlateau_F")) { return vanillaMesaPlateau_F; }
		else if (name.equals("mushroomIsland")) { return vanillaMushroomIsland; }
		else if (name.equals("mushroomIslandShore")) { return vanillaMushroomIslandShore; }
		else if (name.equals("ocean")) { return vanillaOcean; }
		else if (name.equals("plains")) { return vanillaPlains; }
		else if (name.equals("river")) { return vanillaRiver; }
		else if (name.equals("roofedForest")) { return vanillaRoofedForest; }
		else if (name.equals("savanna")) { return vanillaSavanna; }
		else if (name.equals("savannaPlateau")) { return vanillaSavannaPlateau; }
		else if (name.equals("stoneBeach")) { return vanillaStoneBeach; }
		else if (name.equals("swampland")) { return vanillaSwampland; }
		else if (name.equals("taiga")) { return vanillaTaiga; }
		else if (name.equals("taigaHills")) { return vanillaTaigaHills; }
		
		return null;
	}
}
