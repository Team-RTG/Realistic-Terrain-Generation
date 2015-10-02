package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import org.apache.logging.log4j.Level;

import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;

public class RealisticBiomeEBBase extends RealisticBiomeBase
{	
	public RealisticBiomeEBBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("enhancedbiomes"))
		{
			BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
			
			for (int i = 0; i < 256; i++)
			{
				if (b[i] != null)
				{
					BiomeGenBase ebBiome = b[i];
					String biomeName = b[i].biomeName;
					String biomeClass = b[i].getBiomeClass().getName();
					
					/**
					 * Enhanced Biomes sometimes uses the same biome class to generate different biomes.
					 * As such, we have to check both the biome name and the biome class to make sure we've got the right biome.
					 */
					
					if (biomeName == "Alpine Mountains" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpine")
					{
						if (ConfigEB.generateEBAlpineMountains) {
							BiomeBase.addBiome(
								new RealisticBiomeEBAlpineMountains(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Alpine Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge")
					{
						if (ConfigEB.generateEBAlpineMountainsEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeEBAlpineMountainsEdge(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Alpine Mountains M" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpineM")
					{
						if (ConfigEB.generateEBAlpineMountainsM) {
							BiomeBase.addBiome(
								new RealisticBiomeEBAlpineMountainsM(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Alpine Tundra" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra")
					{
						if (ConfigEB.generateEBAlpineTundra) {
							BiomeBase.addBiome(
								new RealisticBiomeEBAlpineTundra(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBAspenForest(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenHills) {
							BiomeBase.addBiome(
								new RealisticBiomeEBAspenHills(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
						if (ConfigEB.generateEBBadlands) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBadlands(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
						if (ConfigEB.generateEBBasin) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBasin(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomHills) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBlossomHills(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomWoods) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBlossomWoods(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
						if (ConfigEB.generateEBBorealArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBorealArchipelago(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBorealForest(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateau) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBorealPlateau(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateauM) {
							BiomeBase.addBiome(
								new RealisticBiomeEBBorealPlateauM(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
						if (ConfigEB.generateEBCarr) {
							BiomeBase.addBiome(
								new RealisticBiomeEBCarr(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
						if (ConfigEB.generateEBClayHills) {
							BiomeBase.addBiome(
								new RealisticBiomeEBClayHills(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
						if (ConfigEB.generateEBClearing) {
							BiomeBase.addBiome(
								new RealisticBiomeEBClearing(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBColdBorealForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBColdBorealForest(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBColdCypressForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBColdCypressForest(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBColdFirForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBColdFirForest(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						if (ConfigEB.generateEBColdPineForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBColdPineForest(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Creek Bed" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed")
					{
						if (ConfigEB.generateEBCreekBed) {
							BiomeBase.addBiome(
								new RealisticBiomeEBCreekBed(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBCypressForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBCypressForest(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
						if (ConfigEB.generateEBDesertArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBDesertArchipelago(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLake) {
							BiomeBase.addBiome(
								new RealisticBiomeEBEphemeralLake(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLakeEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeEBEphemeralLakeEdge(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
						if (ConfigEB.generateEBFens) {
							BiomeBase.addBiome(
								new RealisticBiomeEBFens(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBFirForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBFirForest(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
						if (ConfigEB.generateEBFloweryArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBFloweryArchipelago(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
						if (ConfigEB.generateEBForestedArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBForestedArchipelago(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedMountains) {
							BiomeBase.addBiome(
								new RealisticBiomeEBForestedMountains(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedValley) {
							BiomeBase.addBiome(
								new RealisticBiomeEBForestedValley(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
						if (ConfigEB.generateEBFrozenArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBFrozenArchipelago(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
						if (ConfigEB.generateEBGlacier) {
							BiomeBase.addBiome(
								new RealisticBiomeEBGlacier(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Grassy Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago")
					{
						if (ConfigEB.generateEBGrassyArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBGrassyArchipelago(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
						if (ConfigEB.generateEBIceSheet) {
							BiomeBase.addBiome(
								new RealisticBiomeEBIceSheet(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
						if (ConfigEB.generateEBKakadu) {
							BiomeBase.addBiome(
								new RealisticBiomeEBKakadu(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenLake")
					{
						if (ConfigEB.generateEBLake) {
							BiomeBase.addBiome(
								new RealisticBiomeEBLake(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
						if (ConfigEB.generateEBLowHills) {
							BiomeBase.addBiome(
								new RealisticBiomeEBLowHills(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
						if (ConfigEB.generateEBMangroves) {
							BiomeBase.addBiome(
								new RealisticBiomeEBMangrove(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
						if (ConfigEB.generateEBMarsh) {
							BiomeBase.addBiome(
								new RealisticBiomeEBMarsh(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Meadow" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow")
					{
						if (ConfigEB.generateEBMeadow) {
							BiomeBase.addBiome(
								new RealisticBiomeEBMeadow(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
						if (ConfigEB.generateEBMeadowM) {
							BiomeBase.addBiome(
								new RealisticBiomeEBMeadowM(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
						if (ConfigEB.generateEBMountainousArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBMountainousArchipelago(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountains) {
							BiomeBase.addBiome(
								new RealisticBiomeEBMountains(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountainsEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeEBMountainsEdge(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
						if (ConfigEB.generateEBOakForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBOakForest(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
						if (ConfigEB.generateEBOasis) {
							BiomeBase.addBiome(
								new RealisticBiomeEBOasis(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						if (ConfigEB.generateEBPineForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBPineForest(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
						if (ConfigEB.generateEBPineForestArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBPineForestArchipelago(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
						if (ConfigEB.generateEBPlateau) {
							BiomeBase.addBiome(
								new RealisticBiomeEBPlateau(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
						if (ConfigEB.generateEBPolarDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeEBPolarDesert(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
						if (ConfigEB.generateEBPrairie) {
							BiomeBase.addBiome(
								new RealisticBiomeEBPrairie(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBRainforest(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforestValley) {
							BiomeBase.addBiome(
								new RealisticBiomeEBRainforestValley(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
					{
						if (ConfigEB.generateEBRedDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeEBRedDesert(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Riparian Zone" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenRiparianZone")
					{
						if (ConfigEB.generateEBRiparianZone) {
							BiomeBase.addBiome(
								new RealisticBiomeEBRiparianZone(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
						if (ConfigEB.generateEBRockyDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeEBRockyDesert(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
						if (ConfigEB.generateEBRockyHills) {
							BiomeBase.addBiome(
								new RealisticBiomeEBRockyHills(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
						if (ConfigEB.generateEBRoofedShrublands) {
							BiomeBase.addBiome(
								new RealisticBiomeEBRoofedShrublands(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
						if (ConfigEB.generateEBSahara) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSahara(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
						if (ConfigEB.generateEBSandstoneCanyon) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSandstoneCanyon(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
						if (ConfigEB.generateEBSandstoneCanyons) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSandstoneCanyon2(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRanges) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSandstoneRanges(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRangesM) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSandstoneRangesM(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBScree) {
							BiomeBase.addBiome(
								new RealisticBiomeEBScree(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
						if (ConfigEB.generateEBScrub) {
							BiomeBase.addBiome(
								new RealisticBiomeEBScrub(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
						if (ConfigEB.generateEBShield) {
							BiomeBase.addBiome(
								new RealisticBiomeEBShield(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
						if (ConfigEB.generateEBShrublands) {
							BiomeBase.addBiome(
								new RealisticBiomeEBShrublands(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineForest) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSilverPineForest(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineHills) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSilverPineHills(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
						if (ConfigEB.generateEBSnowyDesert) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSnowyDesert(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
						if (ConfigEB.generateEBSnowyPlateau) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSnowyPlateau(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
						if (ConfigEB.generateEBSnowyRanges) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSnowyRanges(ebBiome),
								BiomeBase.BiomeCategory.SNOW
							);
						}
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBSnowyWastelands) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSnowyWastelands(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
						if (ConfigEB.generateEBSteppe) {
							BiomeBase.addBiome(
								new RealisticBiomeEBSteppe(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyon) {
							BiomeBase.addBiome(
								new RealisticBiomeEBStoneCanyon(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyons) {
							BiomeBase.addBiome(
								new RealisticBiomeEBStoneCanyon2(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
						if (ConfigEB.generateEBTropicalArchipelago) {
							BiomeBase.addBiome(
								new RealisticBiomeEBTropicalArchipelago(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
						if (ConfigEB.generateEBTundra) {
							BiomeBase.addBiome(
								new RealisticBiomeEBTundra(ebBiome),
								BiomeBase.BiomeCategory.COLD
							);
						}
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcano) {
							BiomeBase.addBiome(
								new RealisticBiomeEBVolcano(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcanoM) {
							BiomeBase.addBiome(
								new RealisticBiomeEBVolcanoM(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBWastelands) {
							BiomeBase.addBiome(
								new RealisticBiomeEBWastelands(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandField) {
							BiomeBase.addBiome(
								new RealisticBiomeEBWoodlandField(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandHills) {
							BiomeBase.addBiome(
								new RealisticBiomeEBWoodlandHills(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLake) {
							BiomeBase.addBiome(
								new RealisticBiomeEBWoodlandLake(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLakeEdge) {
							BiomeBase.addBiome(
								new RealisticBiomeEBWoodlandLakeEdge(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlands) {
							BiomeBase.addBiome(
								new RealisticBiomeEBWoodlands(ebBiome),
								BiomeBase.BiomeCategory.WET
							);
						}
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
						if (ConfigEB.generateEBXericSavannah) {
							BiomeBase.addBiome(
								new RealisticBiomeEBXericSavanna(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
						if (ConfigEB.generateEBXericShrubland) {
							BiomeBase.addBiome(
								new RealisticBiomeEBXericShrubland(ebBiome),
								BiomeBase.BiomeCategory.HOT
							);
						}
					}
					else if (biomeClass.contains("enhancedbiomes.world.biome"))
					{
						FMLLog.log(Level.INFO, "EB biome (%s) could not be generated realistically.", biomeName);
					}
				}
			}
		}
	}
}
