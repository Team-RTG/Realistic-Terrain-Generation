package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import org.apache.logging.log4j.Level;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;

public class RealisticBiomeEBBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase ebAlpineMountains;
    public static RealisticBiomeBase ebAlpineMountainsEdge;
    public static RealisticBiomeBase ebAlpineMountainsM;
    public static RealisticBiomeBase ebAlpineTundra;
    public static RealisticBiomeBase ebAspenForest;
    public static RealisticBiomeBase ebAspenHills;
    public static RealisticBiomeBase ebBadlands;
    public static RealisticBiomeBase ebBasin;
    public static RealisticBiomeBase ebBlossomHills;
    public static RealisticBiomeBase ebBlossomWoods;
    public static RealisticBiomeBase ebBorealArchipelago;
    public static RealisticBiomeBase ebBorealForest;
    public static RealisticBiomeBase ebBorealPlateau;
    public static RealisticBiomeBase ebBorealPlateauM;
    public static RealisticBiomeBase ebCarr;
    public static RealisticBiomeBase ebClayHills;
    public static RealisticBiomeBase ebClearing;
    public static RealisticBiomeBase ebColdBorealForest;
    public static RealisticBiomeBase ebColdCypressForest;
    public static RealisticBiomeBase ebColdFirForest;
    public static RealisticBiomeBase ebColdPineForest;
    //public static RealisticBiomeBase ebCreekBed;
    public static RealisticBiomeBase ebCypressForest;
    public static RealisticBiomeBase ebDesertArchipelago;
    public static RealisticBiomeBase ebEphemeralLake;
    public static RealisticBiomeBase ebEphemeralLakeEdge;
    public static RealisticBiomeBase ebFens;
    public static RealisticBiomeBase ebFirForest;
    public static RealisticBiomeBase ebFloweryArchipelago;
    public static RealisticBiomeBase ebForestedArchipelago;
    public static RealisticBiomeBase ebForestedMountains;
    public static RealisticBiomeBase ebForestedValley;
    public static RealisticBiomeBase ebFrozenArchipelago;
    public static RealisticBiomeBase ebGlacier;
    public static RealisticBiomeBase ebGrassyArchipelago;
    public static RealisticBiomeBase ebIceSheet;
    public static RealisticBiomeBase ebKakadu;
    public static RealisticBiomeBase ebLake;
    public static RealisticBiomeBase ebLowHills;
    public static RealisticBiomeBase ebMangroves;
    public static RealisticBiomeBase ebMarsh;
    public static RealisticBiomeBase ebMeadow;
    public static RealisticBiomeBase ebMeadowM;
    public static RealisticBiomeBase ebMountainousArchipelago;
    public static RealisticBiomeBase ebMountains;
    public static RealisticBiomeBase ebMountainsEdge;
    public static RealisticBiomeBase ebOakForest;
    public static RealisticBiomeBase ebOasis;
    public static RealisticBiomeBase ebPineForest;
    public static RealisticBiomeBase ebPineForestArchipelago;
    public static RealisticBiomeBase ebPlateau;
    public static RealisticBiomeBase ebPolarDesert;
    public static RealisticBiomeBase ebPrairie;
    public static RealisticBiomeBase ebRainforest;
    public static RealisticBiomeBase ebRainforestValley;
    public static RealisticBiomeBase ebRedDesert;
    //public static RealisticBiomeBase ebRiparianZone;
    public static RealisticBiomeBase ebRockyDesert;
    public static RealisticBiomeBase ebRockyHills;
    public static RealisticBiomeBase ebRoofedShrublands;
    public static RealisticBiomeBase ebSahara;
    public static RealisticBiomeBase ebSandstoneCanyon;
    public static RealisticBiomeBase ebSandstoneCanyons;
    public static RealisticBiomeBase ebSandstoneRanges;
    public static RealisticBiomeBase ebSandstoneRangesM;
    public static RealisticBiomeBase ebScree;
    public static RealisticBiomeBase ebScrub;
    public static RealisticBiomeBase ebShield;
    public static RealisticBiomeBase ebShrublands;
    public static RealisticBiomeBase ebSilverPineForest;
    public static RealisticBiomeBase ebSilverPineHills;
    public static RealisticBiomeBase ebSnowyDesert;
    public static RealisticBiomeBase ebSnowyPlateau;
    public static RealisticBiomeBase ebSnowyRanges;
    public static RealisticBiomeBase ebSnowyWastelands;
    public static RealisticBiomeBase ebSteppe;
    public static RealisticBiomeBase ebStoneCanyon;
    public static RealisticBiomeBase ebStoneCanyons;
    public static RealisticBiomeBase ebTropicalArchipelago;
    public static RealisticBiomeBase ebTundra;
    public static RealisticBiomeBase ebVolcano;
    public static RealisticBiomeBase ebVolcanoM;
    public static RealisticBiomeBase ebWastelands;
    public static RealisticBiomeBase ebWoodlandField;
    public static RealisticBiomeBase ebWoodlandHills;
    public static RealisticBiomeBase ebWoodlandLake;
    public static RealisticBiomeBase ebWoodlandLakeEdge;
    public static RealisticBiomeBase ebWoodlands;
    public static RealisticBiomeBase ebXericSavannah;
    public static RealisticBiomeBase ebXericShrubland;
    
	public RealisticBiomeEBBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("enhancedbiomes") && ConfigEB.generateEBBiomes)
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
						    ebAlpineMountains = new RealisticBiomeEBAlpineMountains(ebBiome);
							BiomeBase.addBiome(ebAlpineMountains);
						}
					}
					else if (biomeName == "Alpine Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge")
					{
						if (ConfigEB.generateEBAlpineMountainsEdge) {
						    ebAlpineMountainsEdge = new RealisticBiomeEBAlpineMountainsEdge(ebBiome);
							BiomeBase.addBiome(ebAlpineMountainsEdge);
						}
					}
					else if (biomeName == "Alpine Mountains M" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpineM")
					{
						if (ConfigEB.generateEBAlpineMountainsM) {
						    ebAlpineMountainsM = new RealisticBiomeEBAlpineMountainsM(ebBiome);
							BiomeBase.addBiome(ebAlpineMountainsM);
						}
					}
					else if (biomeName == "Alpine Tundra" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra")
					{
						if (ConfigEB.generateEBAlpineTundra) {
						    ebAlpineTundra = new RealisticBiomeEBAlpineTundra(ebBiome);
							BiomeBase.addBiome(ebAlpineTundra);
						}
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenForest) {
						    ebAspenForest = new RealisticBiomeEBAspenForest(ebBiome);
							BiomeBase.addBiome(ebAspenForest);
						}
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenHills) {
						    ebAspenHills = new RealisticBiomeEBAspenHills(ebBiome);
							BiomeBase.addBiome(ebAspenHills);
						}
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
						if (ConfigEB.generateEBBadlands) {
						    ebBadlands = new RealisticBiomeEBBadlands(ebBiome);
							BiomeBase.addBiome(ebBadlands);
						}
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
						if (ConfigEB.generateEBBasin) {
						    ebBasin = new RealisticBiomeEBBasin(ebBiome);
							BiomeBase.addBiome(ebBasin);
						}
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomHills) {
						    ebBlossomHills = new RealisticBiomeEBBlossomHills(ebBiome);
							BiomeBase.addBiome(ebBlossomHills);
						}
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomWoods) {
						    ebBlossomWoods = new RealisticBiomeEBBlossomWoods(ebBiome);
							BiomeBase.addBiome(ebBlossomWoods);
						}
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
						if (ConfigEB.generateEBBorealArchipelago) {
						    ebBorealArchipelago = new RealisticBiomeEBBorealArchipelago(ebBiome);
							BiomeBase.addBiome(ebBorealArchipelago);
						}
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealForest) {
						    ebBorealForest = new RealisticBiomeEBBorealForest(ebBiome);
							BiomeBase.addBiome(ebBorealForest);
						}
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateau) {
						    ebBorealPlateau = new RealisticBiomeEBBorealPlateau(ebBiome);
							BiomeBase.addBiome(ebBorealPlateau);
						}
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateauM) {
						    ebBorealPlateauM = new RealisticBiomeEBBorealPlateauM(ebBiome);
							BiomeBase.addBiome(ebBorealPlateauM);
						}
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
						if (ConfigEB.generateEBCarr) {
						    ebCarr = new RealisticBiomeEBCarr(ebBiome);
							BiomeBase.addBiome(ebCarr);
						}
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
						if (ConfigEB.generateEBClayHills) {
						    ebClayHills = new RealisticBiomeEBClayHills(ebBiome);
							BiomeBase.addBiome(ebClayHills);
						}
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
						if (ConfigEB.generateEBClearing) {
						    ebClearing = new RealisticBiomeEBClearing(ebBiome);
							BiomeBase.addBiome(ebClearing);
						}
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBColdBorealForest) {
						    ebColdBorealForest = new RealisticBiomeEBColdBorealForest(ebBiome);
							BiomeBase.addBiome(ebColdBorealForest);
						}
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBColdCypressForest) {
						    ebColdCypressForest = new RealisticBiomeEBColdCypressForest(ebBiome);
							BiomeBase.addBiome(ebColdCypressForest);
						}
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBColdFirForest) {
						    ebColdFirForest = new RealisticBiomeEBColdFirForest(ebBiome);
							BiomeBase.addBiome(ebColdFirForest);
						}
					}
					else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						if (ConfigEB.generateEBColdPineForest) {
						    ebColdPineForest = new RealisticBiomeEBColdPineForest(ebBiome);
							BiomeBase.addBiome(ebColdPineForest);
						}
					}
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBCypressForest) {
						    ebCypressForest = new RealisticBiomeEBCypressForest(ebBiome);
							BiomeBase.addBiome(ebCypressForest);
						}
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
						if (ConfigEB.generateEBDesertArchipelago) {
						    ebDesertArchipelago = new RealisticBiomeEBDesertArchipelago(ebBiome);
							BiomeBase.addBiome(ebDesertArchipelago);
						}
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLake) {
						    ebEphemeralLake = new RealisticBiomeEBEphemeralLake(ebBiome);
							BiomeBase.addBiome(ebEphemeralLake);
						}
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLakeEdge) {
						    ebEphemeralLakeEdge = new RealisticBiomeEBEphemeralLakeEdge(ebBiome);
							BiomeBase.addBiome(ebEphemeralLakeEdge);
						}
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
						if (ConfigEB.generateEBFens) {
						    ebFens = new RealisticBiomeEBFens(ebBiome);
							BiomeBase.addBiome(ebFens);
						}
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBFirForest) {
						    ebFirForest = new RealisticBiomeEBFirForest(ebBiome);
							BiomeBase.addBiome(ebFirForest);
						}
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
						if (ConfigEB.generateEBFloweryArchipelago) {
						    ebFloweryArchipelago = new RealisticBiomeEBFloweryArchipelago(ebBiome);
							BiomeBase.addBiome(ebFloweryArchipelago);
						}
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
						if (ConfigEB.generateEBForestedArchipelago) {
						    ebForestedArchipelago = new RealisticBiomeEBForestedArchipelago(ebBiome);
							BiomeBase.addBiome(ebForestedArchipelago);
						}
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedMountains) {
						    ebForestedMountains = new RealisticBiomeEBForestedMountains(ebBiome);
							BiomeBase.addBiome(ebForestedMountains);
						}
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedValley) {
						    ebForestedValley = new RealisticBiomeEBForestedValley(ebBiome);
							BiomeBase.addBiome(ebForestedValley);
						}
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
						if (ConfigEB.generateEBFrozenArchipelago) {
						    ebFrozenArchipelago = new RealisticBiomeEBFrozenArchipelago(ebBiome);
							BiomeBase.addBiome(ebFrozenArchipelago);
						}
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
						if (ConfigEB.generateEBGlacier) {
						    ebGlacier = new RealisticBiomeEBGlacier(ebBiome);
							BiomeBase.addBiome(ebGlacier);
						}
					}
					else if (biomeName == "Grassy Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago")
					{
						if (ConfigEB.generateEBGrassyArchipelago) {
						    ebGrassyArchipelago = new RealisticBiomeEBGrassyArchipelago(ebBiome);
							BiomeBase.addBiome(ebGrassyArchipelago);
						}
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
						if (ConfigEB.generateEBIceSheet) {
						    ebIceSheet = new RealisticBiomeEBIceSheet(ebBiome);
							BiomeBase.addBiome(ebIceSheet);
						}
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
						if (ConfigEB.generateEBKakadu) {
						    ebKakadu = new RealisticBiomeEBKakadu(ebBiome);
							BiomeBase.addBiome(ebKakadu);
						}
					}
					else if (biomeName == "Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenLake")
					{
						if (ConfigEB.generateEBLake) {
						    ebLake = new RealisticBiomeEBLake(ebBiome);
							BiomeBase.addBiome(ebLake);
						}
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
						if (ConfigEB.generateEBLowHills) {
						    ebLowHills = new RealisticBiomeEBLowHills(ebBiome);
							BiomeBase.addBiome(ebLowHills);
						}
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
						if (ConfigEB.generateEBMangroves) {
						    ebMangroves = new RealisticBiomeEBMangrove(ebBiome);
							BiomeBase.addBiome(ebMangroves);
						}
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
						if (ConfigEB.generateEBMarsh) {
						    ebMarsh = new RealisticBiomeEBMarsh(ebBiome);
							BiomeBase.addBiome(ebMarsh);
						}
					}
					else if (biomeName == "Meadow" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow")
					{
						if (ConfigEB.generateEBMeadow) {
						    ebMeadow = new RealisticBiomeEBMeadow(ebBiome);
							BiomeBase.addBiome(ebMeadow);
						}
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
						if (ConfigEB.generateEBMeadowM) {
						    ebMeadowM = new RealisticBiomeEBMeadowM(ebBiome);
							BiomeBase.addBiome(ebMeadowM);
						}
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
						if (ConfigEB.generateEBMountainousArchipelago) {
						    ebMountainousArchipelago = new RealisticBiomeEBMountainousArchipelago(ebBiome);
							BiomeBase.addBiome(ebMountainousArchipelago);
						}
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountains) {
						    ebMountains = new RealisticBiomeEBMountains(ebBiome);
							BiomeBase.addBiome(ebMountains);
						}
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountainsEdge) {
						    ebMountainsEdge = new RealisticBiomeEBMountainsEdge(ebBiome);
							BiomeBase.addBiome(ebMountainsEdge);
						}
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
						if (ConfigEB.generateEBOakForest) {
						    ebOakForest = new RealisticBiomeEBOakForest(ebBiome);
							BiomeBase.addBiome(ebOakForest);
						}
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
						if (ConfigEB.generateEBOasis) {
						    ebOasis = new RealisticBiomeEBOasis(ebBiome);
							BiomeBase.addBiome(ebOasis);
						}
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						if (ConfigEB.generateEBPineForest) {
						    ebPineForest = new RealisticBiomeEBPineForest(ebBiome);
							BiomeBase.addBiome(ebPineForest);
						}
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
						if (ConfigEB.generateEBPineForestArchipelago) {
						    ebPineForestArchipelago = new RealisticBiomeEBPineForestArchipelago(ebBiome);
							BiomeBase.addBiome(ebPineForestArchipelago);
						}
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
						if (ConfigEB.generateEBPlateau) {
						    ebPlateau = new RealisticBiomeEBPlateau(ebBiome);
							BiomeBase.addBiome(ebPlateau);
						}
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
						if (ConfigEB.generateEBPolarDesert) {
						    ebPolarDesert = new RealisticBiomeEBPolarDesert(ebBiome);
							BiomeBase.addBiome(ebPolarDesert);
						}
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
						if (ConfigEB.generateEBPrairie) {
						    ebPrairie = new RealisticBiomeEBPrairie(ebBiome);
							BiomeBase.addBiome(ebPrairie);
						}
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforest) {
						    ebRainforest = new RealisticBiomeEBRainforest(ebBiome);
							BiomeBase.addBiome(ebRainforest);
						}
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforestValley) {
						    ebRainforestValley = new RealisticBiomeEBRainforestValley(ebBiome);
							BiomeBase.addBiome(ebRainforestValley);
						}
					}
					else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
					{
						if (ConfigEB.generateEBRedDesert) {
						    ebRedDesert = new RealisticBiomeEBRedDesert(ebBiome);
							BiomeBase.addBiome(ebRedDesert);
						}
					}
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
						if (ConfigEB.generateEBRockyDesert) {
						    ebRockyDesert = new RealisticBiomeEBRockyDesert(ebBiome);
							BiomeBase.addBiome(ebRockyDesert);
						}
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
						if (ConfigEB.generateEBRockyHills) {
						    ebRockyHills = new RealisticBiomeEBRockyHills(ebBiome);
							BiomeBase.addBiome(ebRockyHills);
						}
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
						if (ConfigEB.generateEBRoofedShrublands) {
						    ebRoofedShrublands = new RealisticBiomeEBRoofedShrublands(ebBiome);
							BiomeBase.addBiome(ebRoofedShrublands);
						}
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
						if (ConfigEB.generateEBSahara) {
						    ebSahara = new RealisticBiomeEBSahara(ebBiome);
							BiomeBase.addBiome(ebSahara);
						}
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
						if (ConfigEB.generateEBSandstoneCanyon) {
						    ebSandstoneCanyon = new RealisticBiomeEBSandstoneCanyon(ebBiome);
							BiomeBase.addBiome(ebSandstoneCanyon);
						}
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
						if (ConfigEB.generateEBSandstoneCanyons) {
						    ebSandstoneCanyons = new RealisticBiomeEBSandstoneCanyon2(ebBiome);
							BiomeBase.addBiome(ebSandstoneCanyons);
						}
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRanges) {
						    ebSandstoneRanges = new RealisticBiomeEBSandstoneRanges(ebBiome);
							BiomeBase.addBiome(ebSandstoneRanges);
						}
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRangesM) {
						    ebSandstoneRangesM = new RealisticBiomeEBSandstoneRangesM(ebBiome);
							BiomeBase.addBiome(ebSandstoneRangesM);
						}
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBScree) {
						    ebScree = new RealisticBiomeEBScree(ebBiome);
							BiomeBase.addBiome(ebScree);
						}
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
						if (ConfigEB.generateEBScrub) {
						    ebScrub = new RealisticBiomeEBScrub(ebBiome);
							BiomeBase.addBiome(ebScrub);
						}
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
						if (ConfigEB.generateEBShield) {
						    ebShield = new RealisticBiomeEBShield(ebBiome);
							BiomeBase.addBiome(ebShield);
						}
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
						if (ConfigEB.generateEBShrublands) {
						    ebShrublands = new RealisticBiomeEBShrublands(ebBiome);
							BiomeBase.addBiome(ebShrublands);
						}
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineForest) {
						    ebSilverPineForest = new RealisticBiomeEBSilverPineForest(ebBiome);
							BiomeBase.addBiome(ebSilverPineForest);
						}
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineHills) {
						    ebSilverPineHills = new RealisticBiomeEBSilverPineHills(ebBiome);
							BiomeBase.addBiome(ebSilverPineHills);
						}
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
						if (ConfigEB.generateEBSnowyDesert) {
						    ebSnowyDesert = new RealisticBiomeEBSnowyDesert(ebBiome);
							BiomeBase.addBiome(ebSnowyDesert);
						}
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
						if (ConfigEB.generateEBSnowyPlateau) {
						    ebSnowyPlateau = new RealisticBiomeEBSnowyPlateau(ebBiome);
							BiomeBase.addBiome(ebSnowyPlateau);
						}
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
						if (ConfigEB.generateEBSnowyRanges) {
						    ebSnowyRanges = new RealisticBiomeEBSnowyRanges(ebBiome);
							BiomeBase.addBiome(ebSnowyRanges);
						}
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBSnowyWastelands) {
						    ebSnowyWastelands = new RealisticBiomeEBSnowyWastelands(ebBiome);
							BiomeBase.addBiome(ebSnowyWastelands);
						}
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
						if (ConfigEB.generateEBSteppe) {
						    ebSteppe = new RealisticBiomeEBSteppe(ebBiome);
							BiomeBase.addBiome(ebSteppe);
						}
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyon) {
						    ebStoneCanyon = new RealisticBiomeEBStoneCanyon(ebBiome);
							BiomeBase.addBiome(ebStoneCanyon);
						}
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyons) {
						    ebStoneCanyons = new RealisticBiomeEBStoneCanyon2(ebBiome);
							BiomeBase.addBiome(ebStoneCanyons);
						}
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
						if (ConfigEB.generateEBTropicalArchipelago) {
						    ebTropicalArchipelago = new RealisticBiomeEBTropicalArchipelago(ebBiome);
							BiomeBase.addBiome(ebTropicalArchipelago);
						}
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
						if (ConfigEB.generateEBTundra) {
						    ebTundra = new RealisticBiomeEBTundra(ebBiome);
							BiomeBase.addBiome(ebTundra);
						}
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcano) {
						    ebVolcano = new RealisticBiomeEBVolcano(ebBiome);
							BiomeBase.addBiome(ebVolcano);
						}
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcanoM) {
						    ebVolcanoM = new RealisticBiomeEBVolcanoM(ebBiome);
							BiomeBase.addBiome(ebVolcanoM);
						}
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBWastelands) {
						    ebWastelands = new RealisticBiomeEBWastelands(ebBiome);
							BiomeBase.addBiome(ebWastelands);
						}
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandField) {
						    ebWoodlandField = new RealisticBiomeEBWoodlandField(ebBiome);
							BiomeBase.addBiome(ebWoodlandField);
						}
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandHills) {
						    ebWoodlandHills = new RealisticBiomeEBWoodlandHills(ebBiome);
							BiomeBase.addBiome(ebWoodlandHills);
						}
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLake) {
						    ebWoodlandLake = new RealisticBiomeEBWoodlandLake(ebBiome);
							BiomeBase.addBiome(ebWoodlandLake);
						}
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLakeEdge) {
						    ebWoodlandLakeEdge = new RealisticBiomeEBWoodlandLakeEdge(ebBiome);
							BiomeBase.addBiome(ebWoodlandLakeEdge);
						}
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlands) {
						    ebWoodlands = new RealisticBiomeEBWoodlands(ebBiome);
							BiomeBase.addBiome(ebWoodlands);
						}
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
						if (ConfigEB.generateEBXericSavannah) {
						    ebXericSavannah = new RealisticBiomeEBXericSavanna(ebBiome);
							BiomeBase.addBiome(ebXericSavannah);
						}
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
						if (ConfigEB.generateEBXericShrubland) {
						    ebXericShrubland = new RealisticBiomeEBXericShrubland(ebBiome);
							BiomeBase.addBiome(ebXericShrubland);
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
