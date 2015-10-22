package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

import net.minecraft.world.biome.BiomeGenBase;

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
			if (ConfigVanilla.generateVanillaFrozenRiver) { BiomeBase.addBiome(vanillaFrozenRiver); }
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
			
			/**
			 * Rivers will automatically get generated, so we don't need to add them here.
			 */
			//if (ConfigVanilla.generateVanillaRiver) { BiomeBase.addBiome(vanillaRiver); }
			
			if (ConfigVanilla.generateVanillaRoofedForest) { BiomeBase.addBiome(vanillaRoofedForest); }
			if (ConfigVanilla.generateVanillaSavanna) { BiomeBase.addBiome(vanillaSavanna); }
			if (ConfigVanilla.generateVanillaSavannaPlateau) { BiomeBase.addBiome(vanillaSavannaPlateau); }
			if (ConfigVanilla.generateVanillaStoneBeach) { BiomeBase.addBiome(vanillaStoneBeach); }
			if (ConfigVanilla.generateVanillaSwampland) { BiomeBase.addBiome(vanillaSwampland); }
			if (ConfigVanilla.generateVanillaTaiga) { BiomeBase.addBiome(vanillaTaiga); }
			if (ConfigVanilla.generateVanillaTaigaHills) { BiomeBase.addBiome(vanillaTaigaHills); }
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
