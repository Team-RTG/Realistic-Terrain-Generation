package rtg.world.biome.realistic.enhancedbiomes;

import org.apache.logging.log4j.Level;

import rtg.api.biomes.enhancedbiomes.config.BiomeConfigEB;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

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
    public static RealisticBiomeBase ebCreekBed;
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
    public static RealisticBiomeBase ebRiparianZone;
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
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
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
						    ebAlpineMountains = new RealisticBiomeEBAlpineMountains(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountains);
						    BiomeBase.addBiome(ebAlpineMountains);
						    BiomeBase.addVillageBiome(ebAlpineMountains);
						}
					}
					else if (biomeName == "Alpine Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge")
					{
						if (ConfigEB.generateEBAlpineMountainsEdge) {
						    ebAlpineMountainsEdge = new RealisticBiomeEBAlpineMountainsEdge(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountainsEdge);
							BiomeBase.addBiome(ebAlpineMountainsEdge);
							BiomeBase.addVillageBiome(ebAlpineMountainsEdge);
						}
					}
					else if (biomeName == "Alpine Mountains M" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpineM")
					{
						if (ConfigEB.generateEBAlpineMountainsM) {
						    ebAlpineMountainsM = new RealisticBiomeEBAlpineMountainsM(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountainsM);
							BiomeBase.addBiome(ebAlpineMountainsM);
							BiomeBase.addVillageBiome(ebAlpineMountainsM);
						}
					}
					else if (biomeName == "Alpine Tundra" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra")
					{
						if (ConfigEB.generateEBAlpineTundra) {
						    ebAlpineTundra = new RealisticBiomeEBAlpineTundra(ebBiome, BiomeConfigEB.biomeConfigEBAlpineTundra);
							BiomeBase.addBiome(ebAlpineTundra);
							BiomeBase.addVillageBiome(ebAlpineTundra);
						}
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenForest) {
						    ebAspenForest = new RealisticBiomeEBAspenForest(ebBiome, BiomeConfigEB.biomeConfigEBAspenForest);
							BiomeBase.addBiome(ebAspenForest);
							BiomeBase.addVillageBiome(ebAspenForest);
						}
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
						if (ConfigEB.generateEBAspenHills) {
						    ebAspenHills = new RealisticBiomeEBAspenHills(ebBiome, BiomeConfigEB.biomeConfigEBAspenHills);
							BiomeBase.addBiome(ebAspenHills);
							BiomeBase.addVillageBiome(ebAspenHills);
						}
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
						if (ConfigEB.generateEBBadlands) {
						    ebBadlands = new RealisticBiomeEBBadlands(ebBiome, BiomeConfigEB.biomeConfigEBBadlands);
							BiomeBase.addBiome(ebBadlands);
							BiomeBase.addVillageBiome(ebBadlands);
						}
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
						if (ConfigEB.generateEBBasin) {
						    ebBasin = new RealisticBiomeEBBasin(ebBiome, BiomeConfigEB.biomeConfigEBBasin);
							BiomeBase.addBiome(ebBasin);
							BiomeBase.addVillageBiome(ebBasin);
						}
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomHills) {
						    ebBlossomHills = new RealisticBiomeEBBlossomHills(ebBiome, BiomeConfigEB.biomeConfigEBBlossomHills);
							BiomeBase.addBiome(ebBlossomHills);
							BiomeBase.addVillageBiome(ebBlossomHills);
						}
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
						if (ConfigEB.generateEBBlossomWoods) {
						    ebBlossomWoods = new RealisticBiomeEBBlossomWoods(ebBiome, BiomeConfigEB.biomeConfigEBBlossomWoods);
							BiomeBase.addBiome(ebBlossomWoods);
							BiomeBase.addVillageBiome(ebBlossomWoods);
						}
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
						if (ConfigEB.generateEBBorealArchipelago) {
						    ebBorealArchipelago = new RealisticBiomeEBBorealArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBBorealArchipelago);
							BiomeBase.addBiome(ebBorealArchipelago);
							BiomeBase.addVillageBiome(ebBorealArchipelago);
						}
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealForest) {
						    ebBorealForest = new RealisticBiomeEBBorealForest(ebBiome, BiomeConfigEB.biomeConfigEBBorealForest);
							BiomeBase.addBiome(ebBorealForest);
							BiomeBase.addVillageBiome(ebBorealForest);
						}
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateau) {
						    ebBorealPlateau = new RealisticBiomeEBBorealPlateau(ebBiome, BiomeConfigEB.biomeConfigEBBorealPlateau);
							BiomeBase.addBiome(ebBorealPlateau);
							BiomeBase.addVillageBiome(ebBorealPlateau);
						}
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBBorealPlateauM) {
						    ebBorealPlateauM = new RealisticBiomeEBBorealPlateauM(ebBiome, BiomeConfigEB.biomeConfigEBBorealPlateauM);
							BiomeBase.addBiome(ebBorealPlateauM);
							BiomeBase.addVillageBiome(ebBorealPlateauM);
						}
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
						if (ConfigEB.generateEBCarr) {
						    ebCarr = new RealisticBiomeEBCarr(ebBiome, BiomeConfigEB.biomeConfigEBCarr);
							BiomeBase.addBiome(ebCarr);
							BiomeBase.addVillageBiome(ebCarr);
						}
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
						if (ConfigEB.generateEBClayHills) {
						    ebClayHills = new RealisticBiomeEBClayHills(ebBiome, BiomeConfigEB.biomeConfigEBClayHills);
							BiomeBase.addBiome(ebClayHills);
							BiomeBase.addVillageBiome(ebClayHills);
						}
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
						if (ConfigEB.generateEBClearing) {
						    ebClearing = new RealisticBiomeEBClearing(ebBiome, BiomeConfigEB.biomeConfigEBClearing);
							BiomeBase.addBiome(ebClearing);
							BiomeBase.addVillageBiome(ebClearing);
						}
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
						if (ConfigEB.generateEBColdBorealForest) {
						    ebColdBorealForest = new RealisticBiomeEBColdBorealForest(ebBiome, BiomeConfigEB.biomeConfigEBColdBorealForest);
							BiomeBase.addBiome(ebColdBorealForest);
							BiomeBase.addVillageBiome(ebColdBorealForest);
						}
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBColdCypressForest) {
						    ebColdCypressForest = new RealisticBiomeEBColdCypressForest(ebBiome, BiomeConfigEB.biomeConfigEBColdCypressForest);
							BiomeBase.addBiome(ebColdCypressForest);
							BiomeBase.addVillageBiome(ebColdCypressForest);
						}
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBColdFirForest) {
						    ebColdFirForest = new RealisticBiomeEBColdFirForest(ebBiome, BiomeConfigEB.biomeConfigEBColdFirForest);
							BiomeBase.addBiome(ebColdFirForest);
							BiomeBase.addVillageBiome(ebColdFirForest);
						}
					}
                    else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
                    {
                        if (ConfigEB.generateEBColdPineForest) {
                            ebColdPineForest = new RealisticBiomeEBColdPineForest(ebBiome, BiomeConfigEB.biomeConfigEBColdPineForest);
                            BiomeBase.addBiome(ebColdPineForest);
                            BiomeBase.addVillageBiome(ebColdPineForest);
                        }
                    }
                    else if (biomeName == "Creek Bed" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed")
                    {
                        if (ConfigEB.generateEBCreekBed) {
                            ebCreekBed = new RealisticBiomeEBCreekBed(ebBiome, BiomeConfigEB.biomeConfigEBCreekBed);
                            BiomeBase.addBiome(ebCreekBed);
                            BiomeBase.addVillageBiome(ebCreekBed);
                        }
                    }
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
						if (ConfigEB.generateEBCypressForest) {
						    ebCypressForest = new RealisticBiomeEBCypressForest(ebBiome, BiomeConfigEB.biomeConfigEBCypressForest);
							BiomeBase.addBiome(ebCypressForest);
							BiomeBase.addVillageBiome(ebCypressForest);
						}
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
						if (ConfigEB.generateEBDesertArchipelago) {
						    ebDesertArchipelago = new RealisticBiomeEBDesertArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBDesertArchipelago);
							BiomeBase.addBiome(ebDesertArchipelago);
							BiomeBase.addVillageBiome(ebDesertArchipelago);
						}
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLake) {
						    ebEphemeralLake = new RealisticBiomeEBEphemeralLake(ebBiome, BiomeConfigEB.biomeConfigEBEphemeralLake);
							BiomeBase.addBiome(ebEphemeralLake);
							BiomeBase.addVillageBiome(ebEphemeralLake);
						}
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
						if (ConfigEB.generateEBEphemeralLakeEdge) {
						    ebEphemeralLakeEdge = new RealisticBiomeEBEphemeralLakeEdge(ebBiome, BiomeConfigEB.biomeConfigEBEphemeralLakeEdge);
							BiomeBase.addBiome(ebEphemeralLakeEdge);
							BiomeBase.addVillageBiome(ebEphemeralLakeEdge);
						}
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
						if (ConfigEB.generateEBFens) {
						    ebFens = new RealisticBiomeEBFens(ebBiome, BiomeConfigEB.biomeConfigEBFens);
							BiomeBase.addBiome(ebFens);
							BiomeBase.addVillageBiome(ebFens);
						}
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
						if (ConfigEB.generateEBFirForest) {
						    ebFirForest = new RealisticBiomeEBFirForest(ebBiome, BiomeConfigEB.biomeConfigEBFirForest);
							BiomeBase.addBiome(ebFirForest);
							BiomeBase.addVillageBiome(ebFirForest);
						}
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
						if (ConfigEB.generateEBFloweryArchipelago) {
						    ebFloweryArchipelago = new RealisticBiomeEBFloweryArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBFloweryArchipelago);
							BiomeBase.addBiome(ebFloweryArchipelago);
							BiomeBase.addVillageBiome(ebFloweryArchipelago);
						}
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
						if (ConfigEB.generateEBForestedArchipelago) {
						    ebForestedArchipelago = new RealisticBiomeEBForestedArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBForestedArchipelago);
							BiomeBase.addBiome(ebForestedArchipelago);
							BiomeBase.addVillageBiome(ebForestedArchipelago);
						}
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedMountains) {
						    ebForestedMountains = new RealisticBiomeEBForestedMountains(ebBiome, BiomeConfigEB.biomeConfigEBForestedMountains);
							BiomeBase.addBiome(ebForestedMountains);
							BiomeBase.addVillageBiome(ebForestedMountains);
						}
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBForestedValley) {
						    ebForestedValley = new RealisticBiomeEBForestedValley(ebBiome, BiomeConfigEB.biomeConfigEBForestedValley);
							BiomeBase.addBiome(ebForestedValley);
							BiomeBase.addVillageBiome(ebForestedValley);
						}
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
						if (ConfigEB.generateEBFrozenArchipelago) {
						    ebFrozenArchipelago = new RealisticBiomeEBFrozenArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBFrozenArchipelago);
							BiomeBase.addBiome(ebFrozenArchipelago);
							BiomeBase.addVillageBiome(ebFrozenArchipelago);
						}
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
						if (ConfigEB.generateEBGlacier) {
						    ebGlacier = new RealisticBiomeEBGlacier(ebBiome, BiomeConfigEB.biomeConfigEBGlacier);
							BiomeBase.addBiome(ebGlacier);
							BiomeBase.addVillageBiome(ebGlacier);
						}
					}
					else if (biomeName == "Grassy Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago")
					{
						if (ConfigEB.generateEBGrassyArchipelago) {
						    ebGrassyArchipelago = new RealisticBiomeEBGrassyArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBGrassyArchipelago);
							BiomeBase.addBiome(ebGrassyArchipelago);
							BiomeBase.addVillageBiome(ebGrassyArchipelago);
						}
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
						if (ConfigEB.generateEBIceSheet) {
						    ebIceSheet = new RealisticBiomeEBIceSheet(ebBiome, BiomeConfigEB.biomeConfigEBIceSheet);
							BiomeBase.addBiome(ebIceSheet);
							BiomeBase.addVillageBiome(ebIceSheet);
						}
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
						if (ConfigEB.generateEBKakadu) {
						    ebKakadu = new RealisticBiomeEBKakadu(ebBiome, BiomeConfigEB.biomeConfigEBKakadu);
							BiomeBase.addBiome(ebKakadu);
							BiomeBase.addVillageBiome(ebKakadu);
						}
					}
					else if (biomeName == "Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenLake")
					{
						if (ConfigEB.generateEBLake) {
						    ebLake = new RealisticBiomeEBLake(ebBiome, BiomeConfigEB.biomeConfigEBLake);
							BiomeBase.addBiome(ebLake);
							BiomeBase.addVillageBiome(ebLake);
						}
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
						if (ConfigEB.generateEBLowHills) {
						    ebLowHills = new RealisticBiomeEBLowHills(ebBiome, BiomeConfigEB.biomeConfigEBLowHills);
							BiomeBase.addBiome(ebLowHills);
							BiomeBase.addVillageBiome(ebLowHills);
						}
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
						if (ConfigEB.generateEBMangroves) {
						    ebMangroves = new RealisticBiomeEBMangrove(ebBiome, BiomeConfigEB.biomeConfigEBMangroves);
							BiomeBase.addBiome(ebMangroves);
							BiomeBase.addVillageBiome(ebMangroves);
						}
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
						if (ConfigEB.generateEBMarsh) {
						    ebMarsh = new RealisticBiomeEBMarsh(ebBiome, BiomeConfigEB.biomeConfigEBMarsh);
							BiomeBase.addBiome(ebMarsh);
							BiomeBase.addVillageBiome(ebMarsh);
						}
					}
					else if (biomeName == "Meadow" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow")
					{
						if (ConfigEB.generateEBMeadow) {
						    ebMeadow = new RealisticBiomeEBMeadow(ebBiome, BiomeConfigEB.biomeConfigEBMeadow);
							BiomeBase.addBiome(ebMeadow);
							BiomeBase.addVillageBiome(ebMeadow);
						}
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
						if (ConfigEB.generateEBMeadowM) {
						    ebMeadowM = new RealisticBiomeEBMeadowM(ebBiome, BiomeConfigEB.biomeConfigEBMeadowM);
							BiomeBase.addBiome(ebMeadowM);
							BiomeBase.addVillageBiome(ebMeadowM);
						}
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
						if (ConfigEB.generateEBMountainousArchipelago) {
						    ebMountainousArchipelago = new RealisticBiomeEBMountainousArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBMountainousArchipelago);
							BiomeBase.addBiome(ebMountainousArchipelago);
							BiomeBase.addVillageBiome(ebMountainousArchipelago);
						}
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountains) {
						    ebMountains = new RealisticBiomeEBMountains(ebBiome, BiomeConfigEB.biomeConfigEBMountains);
							BiomeBase.addBiome(ebMountains);
							BiomeBase.addVillageBiome(ebMountains);
						}
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
						if (ConfigEB.generateEBMountainsEdge) {
						    ebMountainsEdge = new RealisticBiomeEBMountainsEdge(ebBiome, BiomeConfigEB.biomeConfigEBMountainsEdge);
							BiomeBase.addBiome(ebMountainsEdge);
							BiomeBase.addVillageBiome(ebMountainsEdge);
						}
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
						if (ConfigEB.generateEBOakForest) {
						    ebOakForest = new RealisticBiomeEBOakForest(ebBiome, BiomeConfigEB.biomeConfigEBOakForest);
							BiomeBase.addBiome(ebOakForest);
							BiomeBase.addVillageBiome(ebOakForest);
						}
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
						if (ConfigEB.generateEBOasis) {
						    ebOasis = new RealisticBiomeEBOasis(ebBiome, BiomeConfigEB.biomeConfigEBOasis);
							BiomeBase.addBiome(ebOasis);
							BiomeBase.addVillageBiome(ebOasis);
						}
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
						if (ConfigEB.generateEBPineForest) {
						    ebPineForest = new RealisticBiomeEBPineForest(ebBiome, BiomeConfigEB.biomeConfigEBPineForest);
							BiomeBase.addBiome(ebPineForest);
							BiomeBase.addVillageBiome(ebPineForest);
						}
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
						if (ConfigEB.generateEBPineForestArchipelago) {
						    ebPineForestArchipelago = new RealisticBiomeEBPineForestArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBPineForestArchipelago);
							BiomeBase.addBiome(ebPineForestArchipelago);
							BiomeBase.addVillageBiome(ebPineForestArchipelago);
						}
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
						if (ConfigEB.generateEBPlateau) {
						    ebPlateau = new RealisticBiomeEBPlateau(ebBiome, BiomeConfigEB.biomeConfigEBPlateau);
							BiomeBase.addBiome(ebPlateau);
							BiomeBase.addVillageBiome(ebPlateau);
						}
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
						if (ConfigEB.generateEBPolarDesert) {
						    ebBiome.setEnableSnow();
						    ebPolarDesert = new RealisticBiomeEBPolarDesert(ebBiome, BiomeConfigEB.biomeConfigEBPolarDesert);
							BiomeBase.addBiome(ebPolarDesert);
							BiomeBase.addVillageBiome(ebPolarDesert);
						}
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
						if (ConfigEB.generateEBPrairie) {
						    ebPrairie = new RealisticBiomeEBPrairie(ebBiome, BiomeConfigEB.biomeConfigEBPrairie);
							BiomeBase.addBiome(ebPrairie);
							BiomeBase.addVillageBiome(ebPrairie);
						}
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforest) {
						    ebRainforest = new RealisticBiomeEBRainforest(ebBiome, BiomeConfigEB.biomeConfigEBRainforest);
							BiomeBase.addBiome(ebRainforest);
							BiomeBase.addVillageBiome(ebRainforest);
						}
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
						if (ConfigEB.generateEBRainforestValley) {
						    ebRainforestValley = new RealisticBiomeEBRainforestValley(ebBiome, BiomeConfigEB.biomeConfigEBRainforestValley);
							BiomeBase.addBiome(ebRainforestValley);
							BiomeBase.addVillageBiome(ebRainforestValley);
						}
					}
                    else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
                    {
                        if (ConfigEB.generateEBRedDesert) {
                            ebRedDesert = new RealisticBiomeEBRedDesert(ebBiome, BiomeConfigEB.biomeConfigEBRedDesert);
                            BiomeBase.addBiome(ebRedDesert);
                            BiomeBase.addVillageBiome(ebRedDesert);
                        }
                    }
                    else if (biomeName == "Riparian Zone" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenRiparianZone")
                    {
                        if (ConfigEB.generateEBRiparianZone) {
                            ebRiparianZone = new RealisticBiomeEBRiparianZone(ebBiome, BiomeConfigEB.biomeConfigEBRiparianZone);
                            BiomeBase.addBiome(ebRiparianZone);
                            BiomeBase.addVillageBiome(ebRiparianZone);
                        }
                    }
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
						if (ConfigEB.generateEBRockyDesert) {
						    ebRockyDesert = new RealisticBiomeEBRockyDesert(ebBiome, BiomeConfigEB.biomeConfigEBRockyDesert);
							BiomeBase.addBiome(ebRockyDesert);
							BiomeBase.addVillageBiome(ebRockyDesert);
						}
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
						if (ConfigEB.generateEBRockyHills) {
						    ebRockyHills = new RealisticBiomeEBRockyHills(ebBiome, BiomeConfigEB.biomeConfigEBRockyHills);
							BiomeBase.addBiome(ebRockyHills);
							BiomeBase.addVillageBiome(ebRockyHills);
						}
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
						if (ConfigEB.generateEBRoofedShrublands) {
						    ebRoofedShrublands = new RealisticBiomeEBRoofedShrublands(ebBiome, BiomeConfigEB.biomeConfigEBRoofedShrublands);
							BiomeBase.addBiome(ebRoofedShrublands);
							BiomeBase.addVillageBiome(ebRoofedShrublands);
						}
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
						if (ConfigEB.generateEBSahara) {
						    ebSahara = new RealisticBiomeEBSahara(ebBiome, BiomeConfigEB.biomeConfigEBSahara);
							BiomeBase.addBiome(ebSahara);
							BiomeBase.addVillageBiome(ebSahara);
						}
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
						if (ConfigEB.generateEBSandstoneCanyon) {
						    ebSandstoneCanyon = new RealisticBiomeEBSandstoneCanyon(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneCanyon);
							BiomeBase.addBiome(ebSandstoneCanyon);
							BiomeBase.addVillageBiome(ebSandstoneCanyon);
						}
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
						if (ConfigEB.generateEBSandstoneCanyons) {
						    ebSandstoneCanyons = new RealisticBiomeEBSandstoneCanyon2(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneCanyon2);
							BiomeBase.addBiome(ebSandstoneCanyons);
							BiomeBase.addVillageBiome(ebSandstoneCanyons);
						}
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRanges) {
						    ebSandstoneRanges = new RealisticBiomeEBSandstoneRanges(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneRanges);
							BiomeBase.addBiome(ebSandstoneRanges);
							BiomeBase.addVillageBiome(ebSandstoneRanges);
						}
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBSandstoneRangesM) {
						    ebSandstoneRangesM = new RealisticBiomeEBSandstoneRangesM(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneRangesM);
							BiomeBase.addBiome(ebSandstoneRangesM);
							BiomeBase.addVillageBiome(ebSandstoneRangesM);
						}
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
						if (ConfigEB.generateEBScree) {
						    ebScree = new RealisticBiomeEBScree(ebBiome, BiomeConfigEB.biomeConfigEBScree);
							BiomeBase.addBiome(ebScree);
							BiomeBase.addVillageBiome(ebScree);
						}
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
						if (ConfigEB.generateEBScrub) {
						    ebScrub = new RealisticBiomeEBScrub(ebBiome, BiomeConfigEB.biomeConfigEBScrub);
							BiomeBase.addBiome(ebScrub);
							BiomeBase.addVillageBiome(ebScrub);
						}
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
						if (ConfigEB.generateEBShield) {
						    ebShield = new RealisticBiomeEBShield(ebBiome, BiomeConfigEB.biomeConfigEBShield);
							BiomeBase.addBiome(ebShield);
							BiomeBase.addVillageBiome(ebShield);
						}
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
						if (ConfigEB.generateEBShrublands) {
						    ebShrublands = new RealisticBiomeEBShrublands(ebBiome, BiomeConfigEB.biomeConfigEBShrublands);
							BiomeBase.addBiome(ebShrublands);
							BiomeBase.addVillageBiome(ebShrublands);
						}
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineForest) {
						    ebSilverPineForest = new RealisticBiomeEBSilverPineForest(ebBiome, BiomeConfigEB.biomeConfigEBSilverPineForest);
							BiomeBase.addBiome(ebSilverPineForest);
							BiomeBase.addVillageBiome(ebSilverPineForest);
						}
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
						if (ConfigEB.generateEBSilverPineHills) {
						    ebSilverPineHills = new RealisticBiomeEBSilverPineHills(ebBiome, BiomeConfigEB.biomeConfigEBSilverPineHills);
							BiomeBase.addBiome(ebSilverPineHills);
							BiomeBase.addVillageBiome(ebSilverPineHills);
						}
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
						if (ConfigEB.generateEBSnowyDesert) {
						    ebSnowyDesert = new RealisticBiomeEBSnowyDesert(ebBiome, BiomeConfigEB.biomeConfigEBSnowyDesert);
							BiomeBase.addBiome(ebSnowyDesert);
							BiomeBase.addVillageBiome(ebSnowyDesert);
						}
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
						if (ConfigEB.generateEBSnowyPlateau) {
						    ebSnowyPlateau = new RealisticBiomeEBSnowyPlateau(ebBiome, BiomeConfigEB.biomeConfigEBSnowyPlateau);
							BiomeBase.addBiome(ebSnowyPlateau);
							BiomeBase.addVillageBiome(ebSnowyPlateau);
						}
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
						if (ConfigEB.generateEBSnowyRanges) {
						    ebSnowyRanges = new RealisticBiomeEBSnowyRanges(ebBiome, BiomeConfigEB.biomeConfigEBSnowyRanges);
							BiomeBase.addBiome(ebSnowyRanges);
							BiomeBase.addVillageBiome(ebSnowyRanges);
						}
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBSnowyWastelands) {
						    ebSnowyWastelands = new RealisticBiomeEBSnowyWastelands(ebBiome, BiomeConfigEB.biomeConfigEBSnowyWastelands);
							BiomeBase.addBiome(ebSnowyWastelands);
							BiomeBase.addVillageBiome(ebSnowyWastelands);
						}
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
						if (ConfigEB.generateEBSteppe) {
						    ebSteppe = new RealisticBiomeEBSteppe(ebBiome, BiomeConfigEB.biomeConfigEBSteppe);
							BiomeBase.addBiome(ebSteppe);
							BiomeBase.addVillageBiome(ebSteppe);
						}
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyon) {
						    ebStoneCanyon = new RealisticBiomeEBStoneCanyon(ebBiome, BiomeConfigEB.biomeConfigEBStoneCanyon);
							BiomeBase.addBiome(ebStoneCanyon);
							BiomeBase.addVillageBiome(ebStoneCanyon);
						}
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
						if (ConfigEB.generateEBStoneCanyons) {
						    ebStoneCanyons = new RealisticBiomeEBStoneCanyon2(ebBiome, BiomeConfigEB.biomeConfigEBStoneCanyons);
							BiomeBase.addBiome(ebStoneCanyons);
							BiomeBase.addVillageBiome(ebStoneCanyons);
						}
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
						if (ConfigEB.generateEBTropicalArchipelago) {
						    ebTropicalArchipelago = new RealisticBiomeEBTropicalArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBTropicalArchipelago);
							BiomeBase.addBiome(ebTropicalArchipelago);
							BiomeBase.addVillageBiome(ebTropicalArchipelago);
						}
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
						if (ConfigEB.generateEBTundra) {
						    ebTundra = new RealisticBiomeEBTundra(ebBiome, BiomeConfigEB.biomeConfigEBTundra);
							BiomeBase.addBiome(ebTundra);
							BiomeBase.addVillageBiome(ebTundra);
						}
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcano) {
						    ebVolcano = new RealisticBiomeEBVolcano(ebBiome, BiomeConfigEB.biomeConfigEBVolcano);
							BiomeBase.addBiome(ebVolcano);
							BiomeBase.addVillageBiome(ebVolcano);
						}
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
						if (ConfigEB.generateEBVolcanoM) {
						    ebVolcanoM = new RealisticBiomeEBVolcanoM(ebBiome, BiomeConfigEB.biomeConfigEBVolcanoM);
							BiomeBase.addBiome(ebVolcanoM);
							BiomeBase.addVillageBiome(ebVolcanoM);
						}
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
						if (ConfigEB.generateEBWastelands) {
						    ebWastelands = new RealisticBiomeEBWastelands(ebBiome, BiomeConfigEB.biomeConfigEBWastelands);
							BiomeBase.addBiome(ebWastelands);
							BiomeBase.addVillageBiome(ebWastelands);
						}
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandField) {
						    ebWoodlandField = new RealisticBiomeEBWoodlandField(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandField);
							BiomeBase.addBiome(ebWoodlandField);
							BiomeBase.addVillageBiome(ebWoodlandField);
						}
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandHills) {
						    ebWoodlandHills = new RealisticBiomeEBWoodlandHills(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandHills);
							BiomeBase.addBiome(ebWoodlandHills);
							BiomeBase.addVillageBiome(ebWoodlandHills);
						}
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLake) {
						    ebWoodlandLake = new RealisticBiomeEBWoodlandLake(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandLake);
							BiomeBase.addBiome(ebWoodlandLake);
							BiomeBase.addVillageBiome(ebWoodlandLake);
						}
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlandLakeEdge) {
						    ebWoodlandLakeEdge = new RealisticBiomeEBWoodlandLakeEdge(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandLakeEdge);
							BiomeBase.addBiome(ebWoodlandLakeEdge);
							BiomeBase.addVillageBiome(ebWoodlandLakeEdge);
						}
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
						if (ConfigEB.generateEBWoodlands) {
						    ebWoodlands = new RealisticBiomeEBWoodlands(ebBiome, BiomeConfigEB.biomeConfigEBWoodlands);
							BiomeBase.addBiome(ebWoodlands);
							BiomeBase.addVillageBiome(ebWoodlands);
						}
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
						if (ConfigEB.generateEBXericSavannah) {
						    ebXericSavannah = new RealisticBiomeEBXericSavanna(ebBiome, BiomeConfigEB.biomeConfigEBXericSavannah);
							BiomeBase.addBiome(ebXericSavannah);
							BiomeBase.addVillageBiome(ebXericSavannah);
						}
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
						if (ConfigEB.generateEBXericShrubland) {
						    ebXericShrubland = new RealisticBiomeEBXericShrubland(ebBiome, BiomeConfigEB.biomeConfigEBXericShrubland);
							BiomeBase.addBiome(ebXericShrubland);
							BiomeBase.addVillageBiome(ebXericShrubland);
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
