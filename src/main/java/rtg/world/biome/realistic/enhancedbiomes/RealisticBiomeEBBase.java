package rtg.world.biome.realistic.enhancedbiomes;

import org.apache.logging.log4j.Level;

import rtg.api.biome.enhancedbiomes.config.BiomeConfigEB;
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
					    ebAlpineMountains = new RealisticBiomeEBAlpineMountains(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountains);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebAlpineMountains); }
					}
					else if (biomeName == "Alpine Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge")
					{
					    ebAlpineMountainsEdge = new RealisticBiomeEBAlpineMountainsEdge(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountainsEdge);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebAlpineMountainsEdge); }
					}
					else if (biomeName == "Alpine Mountains M" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpineM")
					{
					    ebAlpineMountainsM = new RealisticBiomeEBAlpineMountainsM(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountainsM);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebAlpineMountainsM); }
					}
					else if (biomeName == "Alpine Tundra" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra")
					{
					    ebAlpineTundra = new RealisticBiomeEBAlpineTundra(ebBiome, BiomeConfigEB.biomeConfigEBAlpineTundra);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebAlpineTundra); }
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
					    ebAspenForest = new RealisticBiomeEBAspenForest(ebBiome, BiomeConfigEB.biomeConfigEBAspenForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebAspenForest); }
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
					    ebAspenHills = new RealisticBiomeEBAspenHills(ebBiome, BiomeConfigEB.biomeConfigEBAspenHills);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebAspenHills); }
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
					    ebBadlands = new RealisticBiomeEBBadlands(ebBiome, BiomeConfigEB.biomeConfigEBBadlands);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBadlands); }
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
					    ebBasin = new RealisticBiomeEBBasin(ebBiome, BiomeConfigEB.biomeConfigEBBasin);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBasin); }
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
					    ebBlossomHills = new RealisticBiomeEBBlossomHills(ebBiome, BiomeConfigEB.biomeConfigEBBlossomHills);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBlossomHills); }
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
					    ebBlossomWoods = new RealisticBiomeEBBlossomWoods(ebBiome, BiomeConfigEB.biomeConfigEBBlossomWoods);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBlossomWoods); }
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
					    ebBorealArchipelago = new RealisticBiomeEBBorealArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBBorealArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBorealArchipelago); }
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebBorealForest = new RealisticBiomeEBBorealForest(ebBiome, BiomeConfigEB.biomeConfigEBBorealForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBorealForest); }
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebBorealPlateau = new RealisticBiomeEBBorealPlateau(ebBiome, BiomeConfigEB.biomeConfigEBBorealPlateau);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBorealPlateau); }
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebBorealPlateauM = new RealisticBiomeEBBorealPlateauM(ebBiome, BiomeConfigEB.biomeConfigEBBorealPlateauM);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebBorealPlateauM); }
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
					    ebCarr = new RealisticBiomeEBCarr(ebBiome, BiomeConfigEB.biomeConfigEBCarr);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebCarr); }
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
					    ebClayHills = new RealisticBiomeEBClayHills(ebBiome, BiomeConfigEB.biomeConfigEBClayHills);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebClayHills); }
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
					    ebClearing = new RealisticBiomeEBClearing(ebBiome, BiomeConfigEB.biomeConfigEBClearing);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebClearing); }
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebColdBorealForest = new RealisticBiomeEBColdBorealForest(ebBiome, BiomeConfigEB.biomeConfigEBColdBorealForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebColdBorealForest); }
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
					    ebColdCypressForest = new RealisticBiomeEBColdCypressForest(ebBiome, BiomeConfigEB.biomeConfigEBColdCypressForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebColdCypressForest); }
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
					    ebColdFirForest = new RealisticBiomeEBColdFirForest(ebBiome, BiomeConfigEB.biomeConfigEBColdFirForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebColdFirForest); }
					}
                    else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
                    {
                        ebColdPineForest = new RealisticBiomeEBColdPineForest(ebBiome, BiomeConfigEB.biomeConfigEBColdPineForest);
                        
                        if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebColdPineForest); }
                    }
                    else if (biomeName == "Creek Bed" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed")
                    {
                        ebCreekBed = new RealisticBiomeEBCreekBed(ebBiome, BiomeConfigEB.biomeConfigEBCreekBed);
                        
                        if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebCreekBed); }
                    }
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
					    ebCypressForest = new RealisticBiomeEBCypressForest(ebBiome, BiomeConfigEB.biomeConfigEBCypressForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebCypressForest); }
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
					    ebDesertArchipelago = new RealisticBiomeEBDesertArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBDesertArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebDesertArchipelago); }
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
					    ebEphemeralLake = new RealisticBiomeEBEphemeralLake(ebBiome, BiomeConfigEB.biomeConfigEBEphemeralLake);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebEphemeralLake); }
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
					    ebEphemeralLakeEdge = new RealisticBiomeEBEphemeralLakeEdge(ebBiome, BiomeConfigEB.biomeConfigEBEphemeralLakeEdge);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebEphemeralLakeEdge); }
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
					    ebFens = new RealisticBiomeEBFens(ebBiome, BiomeConfigEB.biomeConfigEBFens);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebFens); }
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
					    ebFirForest = new RealisticBiomeEBFirForest(ebBiome, BiomeConfigEB.biomeConfigEBFirForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebFirForest); }
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
					    ebFloweryArchipelago = new RealisticBiomeEBFloweryArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBFloweryArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebFloweryArchipelago); }
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
					    ebForestedArchipelago = new RealisticBiomeEBForestedArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBForestedArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebForestedArchipelago); }
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebForestedMountains = new RealisticBiomeEBForestedMountains(ebBiome, BiomeConfigEB.biomeConfigEBForestedMountains);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebForestedMountains); }
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebForestedValley = new RealisticBiomeEBForestedValley(ebBiome, BiomeConfigEB.biomeConfigEBForestedValley);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebForestedValley); }
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
					    ebFrozenArchipelago = new RealisticBiomeEBFrozenArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBFrozenArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebFrozenArchipelago); }
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
					    ebGlacier = new RealisticBiomeEBGlacier(ebBiome, BiomeConfigEB.biomeConfigEBGlacier);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebGlacier); }
					}
					else if (biomeName == "Grassy Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago")
					{
					    ebGrassyArchipelago = new RealisticBiomeEBGrassyArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBGrassyArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebGrassyArchipelago); }
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
					    ebIceSheet = new RealisticBiomeEBIceSheet(ebBiome, BiomeConfigEB.biomeConfigEBIceSheet);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebIceSheet); }
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
					    ebKakadu = new RealisticBiomeEBKakadu(ebBiome, BiomeConfigEB.biomeConfigEBKakadu);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebKakadu); }
					}
					else if (biomeName == "Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenLake")
					{
					    ebLake = new RealisticBiomeEBLake(ebBiome, BiomeConfigEB.biomeConfigEBLake);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebLake); }
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
					    ebLowHills = new RealisticBiomeEBLowHills(ebBiome, BiomeConfigEB.biomeConfigEBLowHills);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebLowHills); }
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
					    ebMangroves = new RealisticBiomeEBMangrove(ebBiome, BiomeConfigEB.biomeConfigEBMangroves);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebMangroves); }
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
					    ebMarsh = new RealisticBiomeEBMarsh(ebBiome, BiomeConfigEB.biomeConfigEBMarsh);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebMarsh); }
					}
					else if (biomeName == "Meadow" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow")
					{
					    ebMeadow = new RealisticBiomeEBMeadow(ebBiome, BiomeConfigEB.biomeConfigEBMeadow);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebMeadow); }
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
					    ebMeadowM = new RealisticBiomeEBMeadowM(ebBiome, BiomeConfigEB.biomeConfigEBMeadowM);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebMeadowM); }
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
					    ebMountainousArchipelago = new RealisticBiomeEBMountainousArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBMountainousArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebMountainousArchipelago); }
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
					    ebMountains = new RealisticBiomeEBMountains(ebBiome, BiomeConfigEB.biomeConfigEBMountains);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebMountains); }
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
					    ebMountainsEdge = new RealisticBiomeEBMountainsEdge(ebBiome, BiomeConfigEB.biomeConfigEBMountainsEdge);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebMountainsEdge); }
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
					    ebOakForest = new RealisticBiomeEBOakForest(ebBiome, BiomeConfigEB.biomeConfigEBOakForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebOakForest); }
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
					    ebOasis = new RealisticBiomeEBOasis(ebBiome, BiomeConfigEB.biomeConfigEBOasis);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebOasis); }
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
					    ebPineForest = new RealisticBiomeEBPineForest(ebBiome, BiomeConfigEB.biomeConfigEBPineForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebPineForest); }
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
					    ebPineForestArchipelago = new RealisticBiomeEBPineForestArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBPineForestArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebPineForestArchipelago); }
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
					    ebPlateau = new RealisticBiomeEBPlateau(ebBiome, BiomeConfigEB.biomeConfigEBPlateau);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebPlateau); }
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
					    ebPolarDesert = new RealisticBiomeEBPolarDesert(ebBiome, BiomeConfigEB.biomeConfigEBPolarDesert);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebPolarDesert); }
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
					    ebPrairie = new RealisticBiomeEBPrairie(ebBiome, BiomeConfigEB.biomeConfigEBPrairie);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebPrairie); }
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
					    ebRainforest = new RealisticBiomeEBRainforest(ebBiome, BiomeConfigEB.biomeConfigEBRainforest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebRainforest); }
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
					    ebRainforestValley = new RealisticBiomeEBRainforestValley(ebBiome, BiomeConfigEB.biomeConfigEBRainforestValley);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebRainforestValley); }
					}
                    else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
                    {
                        ebRedDesert = new RealisticBiomeEBRedDesert(ebBiome, BiomeConfigEB.biomeConfigEBRedDesert);
                        
                        if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebRedDesert); }
                    }
                    else if (biomeName == "Riparian Zone" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenRiparianZone")
                    {
                        ebRiparianZone = new RealisticBiomeEBRiparianZone(ebBiome, BiomeConfigEB.biomeConfigEBRiparianZone);
                        
                        if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebRiparianZone); }
                    }
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
					    ebRockyDesert = new RealisticBiomeEBRockyDesert(ebBiome, BiomeConfigEB.biomeConfigEBRockyDesert);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebRockyDesert); }
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
					    ebRockyHills = new RealisticBiomeEBRockyHills(ebBiome, BiomeConfigEB.biomeConfigEBRockyHills);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebRockyHills); }
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
					    ebRoofedShrublands = new RealisticBiomeEBRoofedShrublands(ebBiome, BiomeConfigEB.biomeConfigEBRoofedShrublands);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebRoofedShrublands); }
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
					    ebSahara = new RealisticBiomeEBSahara(ebBiome, BiomeConfigEB.biomeConfigEBSahara);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSahara); }
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
					    ebSandstoneCanyon = new RealisticBiomeEBSandstoneCanyon(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneCanyon);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSandstoneCanyon); }
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
					    ebSandstoneCanyons = new RealisticBiomeEBSandstoneCanyon2(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneCanyon2);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSandstoneCanyons); }
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
					    ebSandstoneRanges = new RealisticBiomeEBSandstoneRanges(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneRanges);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSandstoneRanges); }
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
					    ebSandstoneRangesM = new RealisticBiomeEBSandstoneRangesM(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneRangesM);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSandstoneRangesM); }
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
					    ebScree = new RealisticBiomeEBScree(ebBiome, BiomeConfigEB.biomeConfigEBScree);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebScree); }
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
					    ebScrub = new RealisticBiomeEBScrub(ebBiome, BiomeConfigEB.biomeConfigEBScrub);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebScrub); }
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
					    ebShield = new RealisticBiomeEBShield(ebBiome, BiomeConfigEB.biomeConfigEBShield);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebShield); }
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
					    ebShrublands = new RealisticBiomeEBShrublands(ebBiome, BiomeConfigEB.biomeConfigEBShrublands);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebShrublands); }
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
					    ebSilverPineForest = new RealisticBiomeEBSilverPineForest(ebBiome, BiomeConfigEB.biomeConfigEBSilverPineForest);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSilverPineForest); }
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
					    ebSilverPineHills = new RealisticBiomeEBSilverPineHills(ebBiome, BiomeConfigEB.biomeConfigEBSilverPineHills);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSilverPineHills); }
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
					    ebSnowyDesert = new RealisticBiomeEBSnowyDesert(ebBiome, BiomeConfigEB.biomeConfigEBSnowyDesert);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSnowyDesert); }
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
					    ebSnowyPlateau = new RealisticBiomeEBSnowyPlateau(ebBiome, BiomeConfigEB.biomeConfigEBSnowyPlateau);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSnowyPlateau); }
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
					    ebSnowyRanges = new RealisticBiomeEBSnowyRanges(ebBiome, BiomeConfigEB.biomeConfigEBSnowyRanges);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSnowyRanges); }
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
					    ebSnowyWastelands = new RealisticBiomeEBSnowyWastelands(ebBiome, BiomeConfigEB.biomeConfigEBSnowyWastelands);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSnowyWastelands); }
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
					    ebSteppe = new RealisticBiomeEBSteppe(ebBiome, BiomeConfigEB.biomeConfigEBSteppe);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebSteppe); }
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
					    ebStoneCanyon = new RealisticBiomeEBStoneCanyon(ebBiome, BiomeConfigEB.biomeConfigEBStoneCanyon);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebStoneCanyon); }
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
					    ebStoneCanyons = new RealisticBiomeEBStoneCanyon2(ebBiome, BiomeConfigEB.biomeConfigEBStoneCanyons);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebStoneCanyons); }
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
					    ebTropicalArchipelago = new RealisticBiomeEBTropicalArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBTropicalArchipelago);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebTropicalArchipelago); }
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
					    ebTundra = new RealisticBiomeEBTundra(ebBiome, BiomeConfigEB.biomeConfigEBTundra);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebTundra); }
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
					    ebVolcano = new RealisticBiomeEBVolcano(ebBiome, BiomeConfigEB.biomeConfigEBVolcano);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebVolcano); }
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
					    ebVolcanoM = new RealisticBiomeEBVolcanoM(ebBiome, BiomeConfigEB.biomeConfigEBVolcanoM);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebVolcanoM); }
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
					    ebWastelands = new RealisticBiomeEBWastelands(ebBiome, BiomeConfigEB.biomeConfigEBWastelands);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebWastelands); }
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandField = new RealisticBiomeEBWoodlandField(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandField);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebWoodlandField); }
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandHills = new RealisticBiomeEBWoodlandHills(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandHills);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebWoodlandHills); }
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandLake = new RealisticBiomeEBWoodlandLake(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandLake);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebWoodlandLake); }
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandLakeEdge = new RealisticBiomeEBWoodlandLakeEdge(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandLakeEdge);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebWoodlandLakeEdge); }
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlands = new RealisticBiomeEBWoodlands(ebBiome, BiomeConfigEB.biomeConfigEBWoodlands);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebWoodlands); }
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
					    ebXericSavannah = new RealisticBiomeEBXericSavanna(ebBiome, BiomeConfigEB.biomeConfigEBXericSavannah);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebXericSavannah); }
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
					    ebXericShrubland = new RealisticBiomeEBXericShrubland(ebBiome, BiomeConfigEB.biomeConfigEBXericShrubland);
					    
						if (ConfigEB.generateEBBiomes) { BiomeBase.addBiome(ebXericShrubland); }
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
