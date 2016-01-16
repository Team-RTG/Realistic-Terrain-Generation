package rtg.world.biome.realistic.enhancedbiomes;

import org.apache.logging.log4j.Level;

import rtg.api.biome.BiomeConfig;
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
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
						    BiomeBase.addBiome(ebAlpineMountains);
						    BiomeBase.addVillageBiome(ebAlpineMountains);
						}
					}
					else if (biomeName == "Alpine Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenAlpineEdge")
					{
					    ebAlpineMountainsEdge = new RealisticBiomeEBAlpineMountainsEdge(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountainsEdge);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebAlpineMountainsEdge);
							BiomeBase.addVillageBiome(ebAlpineMountainsEdge);
						}
					}
					else if (biomeName == "Alpine Mountains M" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenAlpineM")
					{
					    ebAlpineMountainsM = new RealisticBiomeEBAlpineMountainsM(ebBiome, BiomeConfigEB.biomeConfigEBAlpineMountainsM);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebAlpineMountainsM);
							BiomeBase.addVillageBiome(ebAlpineMountainsM);
						}
					}
					else if (biomeName == "Alpine Tundra" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenAlpineTundra")
					{
					    ebAlpineTundra = new RealisticBiomeEBAlpineTundra(ebBiome, BiomeConfigEB.biomeConfigEBAlpineTundra);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebAlpineTundra);
							BiomeBase.addVillageBiome(ebAlpineTundra);
						}
					}
					else if (biomeName == "Aspen Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
					    ebAspenForest = new RealisticBiomeEBAspenForest(ebBiome, BiomeConfigEB.biomeConfigEBAspenForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebAspenForest);
							BiomeBase.addVillageBiome(ebAspenForest);
						}
					}
					else if (biomeName == "Aspen Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenAspenForest")
					{
					    ebAspenHills = new RealisticBiomeEBAspenHills(ebBiome, BiomeConfigEB.biomeConfigEBAspenHills);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebAspenHills);
							BiomeBase.addVillageBiome(ebAspenHills);
						}
					}
					else if (biomeName == "Badlands" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenBadlands")
					{
					    ebBadlands = new RealisticBiomeEBBadlands(ebBiome, BiomeConfigEB.biomeConfigEBBadlands);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBadlands);
							BiomeBase.addVillageBiome(ebBadlands);
						}
					}
					else if (biomeName == "Basin" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenBasin")
					{
					    ebBasin = new RealisticBiomeEBBasin(ebBiome, BiomeConfigEB.biomeConfigEBBasin);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBasin);
							BiomeBase.addVillageBiome(ebBasin);
						}
					}
					else if (biomeName == "Blossom Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
					    ebBlossomHills = new RealisticBiomeEBBlossomHills(ebBiome, BiomeConfigEB.biomeConfigEBBlossomHills);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBlossomHills);
							BiomeBase.addVillageBiome(ebBlossomHills);
						}
					}
					else if (biomeName == "Blossom Woods" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCherryBlossom")
					{
					    ebBlossomWoods = new RealisticBiomeEBBlossomWoods(ebBiome, BiomeConfigEB.biomeConfigEBBlossomWoods);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBlossomWoods);
							BiomeBase.addVillageBiome(ebBlossomWoods);
						}
					}
					else if (biomeName == "Boreal Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenBorealArchipelago")
					{
					    ebBorealArchipelago = new RealisticBiomeEBBorealArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBBorealArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBorealArchipelago);
							BiomeBase.addVillageBiome(ebBorealArchipelago);
						}
					}
					else if (biomeName == "Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebBorealForest = new RealisticBiomeEBBorealForest(ebBiome, BiomeConfigEB.biomeConfigEBBorealForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBorealForest);
							BiomeBase.addVillageBiome(ebBorealForest);
						}
					}
					else if (biomeName == "Boreal Plateau" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebBorealPlateau = new RealisticBiomeEBBorealPlateau(ebBiome, BiomeConfigEB.biomeConfigEBBorealPlateau);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBorealPlateau);
							BiomeBase.addVillageBiome(ebBorealPlateau);
						}
					}
					else if (biomeName == "Boreal Plateau M" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebBorealPlateauM = new RealisticBiomeEBBorealPlateauM(ebBiome, BiomeConfigEB.biomeConfigEBBorealPlateauM);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebBorealPlateauM);
							BiomeBase.addVillageBiome(ebBorealPlateauM);
						}
					}
					else if (biomeName == "Carr" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenCarr")
					{
					    ebCarr = new RealisticBiomeEBCarr(ebBiome, BiomeConfigEB.biomeConfigEBCarr);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebCarr);
							BiomeBase.addVillageBiome(ebCarr);
						}
					}
					else if (biomeName == "Clay Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenClayHills")
					{
					    ebClayHills = new RealisticBiomeEBClayHills(ebBiome, BiomeConfigEB.biomeConfigEBClayHills);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebClayHills);
							BiomeBase.addVillageBiome(ebClayHills);
						}
					}
					else if (biomeName == "Clearing" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenGrassBase")
					{
					    ebClearing = new RealisticBiomeEBClearing(ebBiome, BiomeConfigEB.biomeConfigEBClearing);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebClearing);
							BiomeBase.addVillageBiome(ebClearing);
						}
					}
					else if (biomeName == "Cold Boreal Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenBorealForest")
					{
					    ebColdBorealForest = new RealisticBiomeEBColdBorealForest(ebBiome, BiomeConfigEB.biomeConfigEBColdBorealForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebColdBorealForest);
							BiomeBase.addVillageBiome(ebColdBorealForest);
						}
					}
					else if (biomeName == "Cold Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
					    ebColdCypressForest = new RealisticBiomeEBColdCypressForest(ebBiome, BiomeConfigEB.biomeConfigEBColdCypressForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebColdCypressForest);
							BiomeBase.addVillageBiome(ebColdCypressForest);
						}
					}
					else if (biomeName == "Cold Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
					    ebColdFirForest = new RealisticBiomeEBColdFirForest(ebBiome, BiomeConfigEB.biomeConfigEBColdFirForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebColdFirForest);
							BiomeBase.addVillageBiome(ebColdFirForest);
						}
					}
                    else if (biomeName == "Cold Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
                    {
                        if (ConfigEB.generateEBBiomes && ebColdPineForest.config._boolean(BiomeConfig.enableBiomeId)) {
                            ebColdPineForest = new RealisticBiomeEBColdPineForest(ebBiome, BiomeConfigEB.biomeConfigEBColdPineForest);
                            BiomeBase.addBiome(ebColdPineForest);
                            BiomeBase.addVillageBiome(ebColdPineForest);
                        }
                    }
                    else if (biomeName == "Creek Bed" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenCreekBed")
                    {
                        if (ConfigEB.generateEBBiomes && ebCreekBed.config._boolean(BiomeConfig.enableBiomeId)) {
                            ebCreekBed = new RealisticBiomeEBCreekBed(ebBiome, BiomeConfigEB.biomeConfigEBCreekBed);
                            BiomeBase.addBiome(ebCreekBed);
                            BiomeBase.addVillageBiome(ebCreekBed);
                        }
                    }
					else if (biomeName == "Cypress Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenCypressForest")
					{
					    ebCypressForest = new RealisticBiomeEBCypressForest(ebBiome, BiomeConfigEB.biomeConfigEBCypressForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebCypressForest);
							BiomeBase.addVillageBiome(ebCypressForest);
						}
					}
					else if (biomeName == "Desert Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenDesertArchipelago")
					{
					    ebDesertArchipelago = new RealisticBiomeEBDesertArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBDesertArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebDesertArchipelago);
							BiomeBase.addVillageBiome(ebDesertArchipelago);
						}
					}
					else if (biomeName == "Ephemeral Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
					    ebEphemeralLake = new RealisticBiomeEBEphemeralLake(ebBiome, BiomeConfigEB.biomeConfigEBEphemeralLake);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebEphemeralLake);
							BiomeBase.addVillageBiome(ebEphemeralLake);
						}
					}
					else if (biomeName == "Ephemeral Lake Edge" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenEphemeralLake")
					{
					    ebEphemeralLakeEdge = new RealisticBiomeEBEphemeralLakeEdge(ebBiome, BiomeConfigEB.biomeConfigEBEphemeralLakeEdge);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebEphemeralLakeEdge);
							BiomeBase.addVillageBiome(ebEphemeralLakeEdge);
						}
					}
					else if (biomeName == "Fens" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenFen")
					{
					    ebFens = new RealisticBiomeEBFens(ebBiome, BiomeConfigEB.biomeConfigEBFens);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebFens);
							BiomeBase.addVillageBiome(ebFens);
						}
					}
					else if (biomeName == "Fir Forest" && biomeClass == "enhancedbiomes.world.biome.snow.snowforest.BiomeGenFirForest")
					{
					    ebFirForest = new RealisticBiomeEBFirForest(ebBiome, BiomeConfigEB.biomeConfigEBFirForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebFirForest);
							BiomeBase.addVillageBiome(ebFirForest);
						}
					}
					else if (biomeName == "Flowery Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenFlowerArchipelago")
					{
					    ebFloweryArchipelago = new RealisticBiomeEBFloweryArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBFloweryArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebFloweryArchipelago);
							BiomeBase.addVillageBiome(ebFloweryArchipelago);
						}
					}
					else if (biomeName == "Forested Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenForestArchipelago")
					{
					    ebForestedArchipelago = new RealisticBiomeEBForestedArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBForestedArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebForestedArchipelago);
							BiomeBase.addVillageBiome(ebForestedArchipelago);
						}
					}
					else if (biomeName == "Forested Mountains" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebForestedMountains = new RealisticBiomeEBForestedMountains(ebBiome, BiomeConfigEB.biomeConfigEBForestedMountains);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebForestedMountains);
							BiomeBase.addVillageBiome(ebForestedMountains);
						}
					}
					else if (biomeName == "Forested Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebForestedValley = new RealisticBiomeEBForestedValley(ebBiome, BiomeConfigEB.biomeConfigEBForestedValley);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebForestedValley);
							BiomeBase.addVillageBiome(ebForestedValley);
						}
					}
					else if (biomeName == "Frozen Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenSnowArchipelago")
					{
					    ebFrozenArchipelago = new RealisticBiomeEBFrozenArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBFrozenArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebFrozenArchipelago);
							BiomeBase.addVillageBiome(ebFrozenArchipelago);
						}
					}
					else if (biomeName == "Glacier" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenGlacier")
					{
					    ebGlacier = new RealisticBiomeEBGlacier(ebBiome, BiomeConfigEB.biomeConfigEBGlacier);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebGlacier);
							BiomeBase.addVillageBiome(ebGlacier);
						}
					}
					else if (biomeName == "Grassy Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPlainsArchipelago")
					{
					    ebGrassyArchipelago = new RealisticBiomeEBGrassyArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBGrassyArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebGrassyArchipelago);
							BiomeBase.addVillageBiome(ebGrassyArchipelago);
						}
					}
					else if (biomeName == "Ice Sheet" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenIceSheet")
					{
					    ebIceSheet = new RealisticBiomeEBIceSheet(ebBiome, BiomeConfigEB.biomeConfigEBIceSheet);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebIceSheet);
							BiomeBase.addVillageBiome(ebIceSheet);
						}
					}
					else if (biomeName == "Kakadu" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenKakadu")
					{
					    ebKakadu = new RealisticBiomeEBKakadu(ebBiome, BiomeConfigEB.biomeConfigEBKakadu);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebKakadu);
							BiomeBase.addVillageBiome(ebKakadu);
						}
					}
					else if (biomeName == "Lake" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenLake")
					{
					    ebLake = new RealisticBiomeEBLake(ebBiome, BiomeConfigEB.biomeConfigEBLake);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebLake);
							BiomeBase.addVillageBiome(ebLake);
						}
					}
					else if (biomeName == "Low Hills" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenLowHills")
					{
					    ebLowHills = new RealisticBiomeEBLowHills(ebBiome, BiomeConfigEB.biomeConfigEBLowHills);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebLowHills);
							BiomeBase.addVillageBiome(ebLowHills);
						}
					}
					else if (biomeName == "Mangroves" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMangrove")
					{
					    ebMangroves = new RealisticBiomeEBMangrove(ebBiome, BiomeConfigEB.biomeConfigEBMangroves);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebMangroves);
							BiomeBase.addVillageBiome(ebMangroves);
						}
					}
					else if (biomeName == "Marsh" && biomeClass == "enhancedbiomes.world.biome.wetland.BiomeGenMarsh")
					{
					    ebMarsh = new RealisticBiomeEBMarsh(ebBiome, BiomeConfigEB.biomeConfigEBMarsh);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebMarsh);
							BiomeBase.addVillageBiome(ebMarsh);
						}
					}
					else if (biomeName == "Meadow" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadow")
					{
					    ebMeadow = new RealisticBiomeEBMeadow(ebBiome, BiomeConfigEB.biomeConfigEBMeadow);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebMeadow);
							BiomeBase.addVillageBiome(ebMeadow);
						}
					}
					else if (biomeName == "Meadow M" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenMeadowM")
					{
					    ebMeadowM = new RealisticBiomeEBMeadowM(ebBiome, BiomeConfigEB.biomeConfigEBMeadowM);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebMeadowM);
							BiomeBase.addVillageBiome(ebMeadowM);
						}
					}
					else if (biomeName == "Mountainous Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenMountainsArchipelago")
					{
					    ebMountainousArchipelago = new RealisticBiomeEBMountainousArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBMountainousArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebMountainousArchipelago);
							BiomeBase.addVillageBiome(ebMountainousArchipelago);
						}
					}
					else if (biomeName == "Mountains" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
					    ebMountains = new RealisticBiomeEBMountains(ebBiome, BiomeConfigEB.biomeConfigEBMountains);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebMountains);
							BiomeBase.addVillageBiome(ebMountains);
						}
					}
					else if (biomeName == "Mountains Edge" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenMountains")
					{
					    ebMountainsEdge = new RealisticBiomeEBMountainsEdge(ebBiome, BiomeConfigEB.biomeConfigEBMountainsEdge);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebMountainsEdge);
							BiomeBase.addVillageBiome(ebMountainsEdge);
						}
					}
					else if (biomeName == "Oak Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenOakForest")
					{
					    ebOakForest = new RealisticBiomeEBOakForest(ebBiome, BiomeConfigEB.biomeConfigEBOakForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebOakForest);
							BiomeBase.addVillageBiome(ebOakForest);
						}
					}
					else if (biomeName == "Oasis" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenOasis")
					{
					    ebOasis = new RealisticBiomeEBOasis(ebBiome, BiomeConfigEB.biomeConfigEBOasis);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebOasis);
							BiomeBase.addVillageBiome(ebOasis);
						}
					}
					else if (biomeName == "Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenPineForest")
					{
					    ebPineForest = new RealisticBiomeEBPineForest(ebBiome, BiomeConfigEB.biomeConfigEBPineForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebPineForest);
							BiomeBase.addVillageBiome(ebPineForest);
						}
					}
					else if (biomeName == "Pine Forest Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenPineForestArchipelago")
					{
					    ebPineForestArchipelago = new RealisticBiomeEBPineForestArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBPineForestArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebPineForestArchipelago);
							BiomeBase.addVillageBiome(ebPineForestArchipelago);
						}
					}
					else if (biomeName == "Plateau" && biomeClass == "enhancedbiomes.world.biome.grass.BiomeGenPlateau")
					{
					    ebPlateau = new RealisticBiomeEBPlateau(ebBiome, BiomeConfigEB.biomeConfigEBPlateau);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebPlateau);
							BiomeBase.addVillageBiome(ebPlateau);
						}
					}
					else if (biomeName == "Polar Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenPolarDesert")
					{
						if (ConfigEB.generateEBBiomes && ebPolarDesert.config._boolean(BiomeConfig.enableBiomeId)) {
						    ebBiome.setEnableSnow();
						    ebPolarDesert = new RealisticBiomeEBPolarDesert(ebBiome, BiomeConfigEB.biomeConfigEBPolarDesert);
							BiomeBase.addBiome(ebPolarDesert);
							BiomeBase.addVillageBiome(ebPolarDesert);
						}
					}
					else if (biomeName == "Prairie" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenPrairie")
					{
					    ebPrairie = new RealisticBiomeEBPrairie(ebBiome, BiomeConfigEB.biomeConfigEBPrairie);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebPrairie);
							BiomeBase.addVillageBiome(ebPrairie);
						}
					}
					else if (biomeName == "Rainforest" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
					    ebRainforest = new RealisticBiomeEBRainforest(ebBiome, BiomeConfigEB.biomeConfigEBRainforest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebRainforest);
							BiomeBase.addVillageBiome(ebRainforest);
						}
					}
					else if (biomeName == "Rainforest Valley" && biomeClass == "enhancedbiomes.world.biome.woodland.tropical.BiomeGenRainforest")
					{
					    ebRainforestValley = new RealisticBiomeEBRainforestValley(ebBiome, BiomeConfigEB.biomeConfigEBRainforestValley);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebRainforestValley);
							BiomeBase.addVillageBiome(ebRainforestValley);
						}
					}
                    else if (biomeName == "Red Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRedDesert")
                    {
                        if (ConfigEB.generateEBBiomes && ebRedDesert.config._boolean(BiomeConfig.enableBiomeId)) {
                            ebRedDesert = new RealisticBiomeEBRedDesert(ebBiome, BiomeConfigEB.biomeConfigEBRedDesert);
                            BiomeBase.addBiome(ebRedDesert);
                            BiomeBase.addVillageBiome(ebRedDesert);
                        }
                    }
                    else if (biomeName == "Riparian Zone" && biomeClass == "enhancedbiomes.world.biome.base.BiomeGenRiparianZone")
                    {
                        if (ConfigEB.generateEBBiomes && ebRiparianZone.config._boolean(BiomeConfig.enableBiomeId)) {
                            ebRiparianZone = new RealisticBiomeEBRiparianZone(ebBiome, BiomeConfigEB.biomeConfigEBRiparianZone);
                            BiomeBase.addBiome(ebRiparianZone);
                            BiomeBase.addVillageBiome(ebRiparianZone);
                        }
                    }
					else if (biomeName == "Rocky Desert" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenRockyDesert")
					{
					    ebRockyDesert = new RealisticBiomeEBRockyDesert(ebBiome, BiomeConfigEB.biomeConfigEBRockyDesert);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebRockyDesert);
							BiomeBase.addVillageBiome(ebRockyDesert);
						}
					}
					else if (biomeName == "Rocky Hills" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenRockHills")
					{
					    ebRockyHills = new RealisticBiomeEBRockyHills(ebBiome, BiomeConfigEB.biomeConfigEBRockyHills);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebRockyHills);
							BiomeBase.addVillageBiome(ebRockyHills);
						}
					}
					else if (biomeName == "Roofed Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslandsRoofed")
					{
					    ebRoofedShrublands = new RealisticBiomeEBRoofedShrublands(ebBiome, BiomeConfigEB.biomeConfigEBRoofedShrublands);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebRoofedShrublands);
							BiomeBase.addVillageBiome(ebRoofedShrublands);
						}
					}
					else if (biomeName == "Sahara" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenSahara")
					{
					    ebSahara = new RealisticBiomeEBSahara(ebBiome, BiomeConfigEB.biomeConfigEBSahara);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSahara);
							BiomeBase.addVillageBiome(ebSahara);
						}
					}
					else if (biomeName == "Sandstone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneGorge")
					{
					    ebSandstoneCanyon = new RealisticBiomeEBSandstoneCanyon(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneCanyon);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSandstoneCanyon);
							BiomeBase.addVillageBiome(ebSandstoneCanyon);
						}
					}
					else if (biomeName == "Sandstone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneCanyon")
					{
					    ebSandstoneCanyons = new RealisticBiomeEBSandstoneCanyon2(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneCanyon2);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSandstoneCanyons);
							BiomeBase.addVillageBiome(ebSandstoneCanyons);
						}
					}
					else if (biomeName == "Sandstone Ranges" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
					    ebSandstoneRanges = new RealisticBiomeEBSandstoneRanges(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneRanges);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSandstoneRanges);
							BiomeBase.addVillageBiome(ebSandstoneRanges);
						}
					}
					else if (biomeName == "Sandstone Ranges M" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
					    ebSandstoneRangesM = new RealisticBiomeEBSandstoneRangesM(ebBiome, BiomeConfigEB.biomeConfigEBSandstoneRangesM);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSandstoneRangesM);
							BiomeBase.addVillageBiome(ebSandstoneRangesM);
						}
					}
					else if (biomeName == "Scree" && biomeClass == "enhancedbiomes.world.biome.wasteland.sandstone.BiomeGenSandStoneRanges")
					{
					    ebScree = new RealisticBiomeEBScree(ebBiome, BiomeConfigEB.biomeConfigEBScree);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebScree);
							BiomeBase.addVillageBiome(ebScree);
						}
					}
					else if (biomeName == "Scrub" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenScrub")
					{
					    ebScrub = new RealisticBiomeEBScrub(ebBiome, BiomeConfigEB.biomeConfigEBScrub);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebScrub);
							BiomeBase.addVillageBiome(ebScrub);
						}
					}
					else if (biomeName == "Shield" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenShield")
					{
					    ebShield = new RealisticBiomeEBShield(ebBiome, BiomeConfigEB.biomeConfigEBShield);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebShield);
							BiomeBase.addVillageBiome(ebShield);
						}
					}
					else if (biomeName == "Shrublands" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenGrasslands")
					{
					    ebShrublands = new RealisticBiomeEBShrublands(ebBiome, BiomeConfigEB.biomeConfigEBShrublands);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebShrublands);
							BiomeBase.addVillageBiome(ebShrublands);
						}
					}
					else if (biomeName == "Silver Pine Forest" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
					    ebSilverPineForest = new RealisticBiomeEBSilverPineForest(ebBiome, BiomeConfigEB.biomeConfigEBSilverPineForest);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSilverPineForest);
							BiomeBase.addVillageBiome(ebSilverPineForest);
						}
					}
					else if (biomeName == "Silver Pine Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenSilverPineForest")
					{
					    ebSilverPineHills = new RealisticBiomeEBSilverPineHills(ebBiome, BiomeConfigEB.biomeConfigEBSilverPineHills);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSilverPineHills);
							BiomeBase.addVillageBiome(ebSilverPineHills);
						}
					}
					else if (biomeName == "Snowy Desert" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyDesert")
					{
					    ebSnowyDesert = new RealisticBiomeEBSnowyDesert(ebBiome, BiomeConfigEB.biomeConfigEBSnowyDesert);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSnowyDesert);
							BiomeBase.addVillageBiome(ebSnowyDesert);
						}
					}
					else if (biomeName == "Snowy Plateau" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyPlateau")
					{
					    ebSnowyPlateau = new RealisticBiomeEBSnowyPlateau(ebBiome, BiomeConfigEB.biomeConfigEBSnowyPlateau);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSnowyPlateau);
							BiomeBase.addVillageBiome(ebSnowyPlateau);
						}
					}
					else if (biomeName == "Snowy Ranges" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenSnowyRanges")
					{
					    ebSnowyRanges = new RealisticBiomeEBSnowyRanges(ebBiome, BiomeConfigEB.biomeConfigEBSnowyRanges);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSnowyRanges);
							BiomeBase.addVillageBiome(ebSnowyRanges);
						}
					}
					else if (biomeName == "Snowy Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
					    ebSnowyWastelands = new RealisticBiomeEBSnowyWastelands(ebBiome, BiomeConfigEB.biomeConfigEBSnowyWastelands);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSnowyWastelands);
							BiomeBase.addVillageBiome(ebSnowyWastelands);
						}
					}
					else if (biomeName == "Steppe" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSteppe")
					{
					    ebSteppe = new RealisticBiomeEBSteppe(ebBiome, BiomeConfigEB.biomeConfigEBSteppe);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebSteppe);
							BiomeBase.addVillageBiome(ebSteppe);
						}
					}
					else if (biomeName == "Stone Canyon" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
					    ebStoneCanyon = new RealisticBiomeEBStoneCanyon(ebBiome, BiomeConfigEB.biomeConfigEBStoneCanyon);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebStoneCanyon);
							BiomeBase.addVillageBiome(ebStoneCanyon);
						}
					}
					else if (biomeName == "Stone Canyons" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenStoneCanyon")
					{
					    ebStoneCanyons = new RealisticBiomeEBStoneCanyon2(ebBiome, BiomeConfigEB.biomeConfigEBStoneCanyons);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebStoneCanyons);
							BiomeBase.addVillageBiome(ebStoneCanyons);
						}
					}
					else if (biomeName == "Tropical Archipelago" && biomeClass == "enhancedbiomes.world.biome.archipelago.BiomeGenJungleArchipelago")
					{
					    ebTropicalArchipelago = new RealisticBiomeEBTropicalArchipelago(ebBiome, BiomeConfigEB.biomeConfigEBTropicalArchipelago);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebTropicalArchipelago);
							BiomeBase.addVillageBiome(ebTropicalArchipelago);
						}
					}
					else if (biomeName == "Tundra" && biomeClass == "enhancedbiomes.world.biome.snow.BiomeGenTundra")
					{
					    ebTundra = new RealisticBiomeEBTundra(ebBiome, BiomeConfigEB.biomeConfigEBTundra);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebTundra);
							BiomeBase.addVillageBiome(ebTundra);
						}
					}
					else if (biomeName == "Volcano" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
					    ebVolcano = new RealisticBiomeEBVolcano(ebBiome, BiomeConfigEB.biomeConfigEBVolcano);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebVolcano);
							BiomeBase.addVillageBiome(ebVolcano);
						}
					}
					else if (biomeName == "Volcano M" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenVolcano")
					{
					    ebVolcanoM = new RealisticBiomeEBVolcanoM(ebBiome, BiomeConfigEB.biomeConfigEBVolcanoM);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebVolcanoM);
							BiomeBase.addVillageBiome(ebVolcanoM);
						}
					}
					else if (biomeName == "Wastelands" && biomeClass == "enhancedbiomes.world.biome.wasteland.rock.BiomeGenWasteLands")
					{
					    ebWastelands = new RealisticBiomeEBWastelands(ebBiome, BiomeConfigEB.biomeConfigEBWastelands);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebWastelands);
							BiomeBase.addVillageBiome(ebWastelands);
						}
					}
					else if (biomeName == "Woodland Field" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandField = new RealisticBiomeEBWoodlandField(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandField);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebWoodlandField);
							BiomeBase.addVillageBiome(ebWoodlandField);
						}
					}
					else if (biomeName == "Woodland Hills" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandHills = new RealisticBiomeEBWoodlandHills(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandHills);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebWoodlandHills);
							BiomeBase.addVillageBiome(ebWoodlandHills);
						}
					}
					else if (biomeName == "Woodland Lake" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandLake = new RealisticBiomeEBWoodlandLake(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandLake);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebWoodlandLake);
							BiomeBase.addVillageBiome(ebWoodlandLake);
						}
					}
					else if (biomeName == "Woodland Lake Edge" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlandLakeEdge = new RealisticBiomeEBWoodlandLakeEdge(ebBiome, BiomeConfigEB.biomeConfigEBWoodlandLakeEdge);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebWoodlandLakeEdge);
							BiomeBase.addVillageBiome(ebWoodlandLakeEdge);
						}
					}
					else if (biomeName == "Woodlands" && biomeClass == "enhancedbiomes.world.biome.woodland.BiomeGenWoodlands")
					{
					    ebWoodlands = new RealisticBiomeEBWoodlands(ebBiome, BiomeConfigEB.biomeConfigEBWoodlands);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebWoodlands);
							BiomeBase.addVillageBiome(ebWoodlands);
						}
					}
					else if (biomeName == "Xeric Savannah" && biomeClass == "enhancedbiomes.world.biome.grass.plains.BiomeGenSavannah")
					{
					    ebXericSavannah = new RealisticBiomeEBXericSavanna(ebBiome, BiomeConfigEB.biomeConfigEBXericSavannah);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
							BiomeBase.addBiome(ebXericSavannah);
							BiomeBase.addVillageBiome(ebXericSavannah);
						}
					}
					else if (biomeName == "Xeric Shrubland" && biomeClass == "enhancedbiomes.world.biome.sand.BiomeGenXericShrubland")
					{
					    ebXericShrubland = new RealisticBiomeEBXericShrubland(ebBiome, BiomeConfigEB.biomeConfigEBXericShrubland);
					    
						if (ConfigEB.generateEBBiomes && ebAlpineMountains.config._boolean(BiomeConfig.enableBiomeId)) {
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
