package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biomes.biomesoplenty.config.BiomeConfigBOP;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPBase extends RealisticBiomeBase
{
	//Overworld Biomes
	public static RealisticBiomeBase bopAlps;
	public static RealisticBiomeBase bopArctic;
	public static RealisticBiomeBase bopBambooForest;
	public static RealisticBiomeBase bopBayou;
	public static RealisticBiomeBase bopBog;
	public static RealisticBiomeBase bopBorealForest;
	public static RealisticBiomeBase bopBrushland;
	public static RealisticBiomeBase bopCanyon;
	public static RealisticBiomeBase bopChaparral;
	public static RealisticBiomeBase bopCherryBlossomGrove;
	public static RealisticBiomeBase bopConiferousForest;
	public static RealisticBiomeBase bopCrag;
	public static RealisticBiomeBase bopDeadForest;
	public static RealisticBiomeBase bopDeadSwamp;
	public static RealisticBiomeBase bopDeciduousForest;
	public static RealisticBiomeBase bopDenseForest;
	public static RealisticBiomeBase bopDryRiver;
	public static RealisticBiomeBase bopEucalyptusForest;
	public static RealisticBiomeBase bopFen;
	public static RealisticBiomeBase bopFlowerField;
	public static RealisticBiomeBase bopFrostForest;
	public static RealisticBiomeBase bopFungiForest;
	public static RealisticBiomeBase bopGarden;
	public static RealisticBiomeBase bopGrassland;
	public static RealisticBiomeBase bopGrove;
	public static RealisticBiomeBase bopHeathland;
	public static RealisticBiomeBase bopHighland;
	public static RealisticBiomeBase bopJadeCliffs;
	public static RealisticBiomeBase bopLandOfLakes;
	public static RealisticBiomeBase bopLavenderFields;
	public static RealisticBiomeBase bopLushDesert;
	public static RealisticBiomeBase bopLushRiver;
	public static RealisticBiomeBase bopLushSwamp;
	public static RealisticBiomeBase bopMapleWoods;
	public static RealisticBiomeBase bopMarsh;
	public static RealisticBiomeBase bopMeadow;
	public static RealisticBiomeBase bopMoor;
	public static RealisticBiomeBase bopMountain;
	public static RealisticBiomeBase bopMysticGrove;
	public static RealisticBiomeBase bopOminousWoods;
	public static RealisticBiomeBase bopOriginValley;
	public static RealisticBiomeBase bopOutback;
	public static RealisticBiomeBase bopPrairie;
	public static RealisticBiomeBase bopRainforest;
	public static RealisticBiomeBase bopRedwoodForest;
	public static RealisticBiomeBase bopSacredSprings;
	public static RealisticBiomeBase bopSeasonalForest;
	public static RealisticBiomeBase bopShield;
	public static RealisticBiomeBase bopShrubland;
	public static RealisticBiomeBase bopSludgepit;
	public static RealisticBiomeBase bopSnowyConiferousForest;
	public static RealisticBiomeBase bopSteppe;
	public static RealisticBiomeBase bopTemperateRainforest;
	public static RealisticBiomeBase bopThicket;
	public static RealisticBiomeBase bopTropicalRainforest;
	public static RealisticBiomeBase bopTundra;
	public static RealisticBiomeBase bopWasteland;
	public static RealisticBiomeBase bopWetland;
	public static RealisticBiomeBase bopWoodland;
	public static RealisticBiomeBase bopXericShrubland;
	
	//Sub Biomes (treated as normal biomes in RTG)
	public static RealisticBiomeBase bopAlpsForest;
	public static RealisticBiomeBase bopCanyonRavine;
	public static RealisticBiomeBase bopGlacier;
	public static RealisticBiomeBase bopLandOfLakesMarsh;
	public static RealisticBiomeBase bopMangrove;
	public static RealisticBiomeBase bopMeadowForest;
	public static RealisticBiomeBase bopOasis;
	public static RealisticBiomeBase bopOrchard;
	public static RealisticBiomeBase bopQuagmire;
	public static RealisticBiomeBase bopScrubland;
	public static RealisticBiomeBase bopSeasonalForestClearing;
	public static RealisticBiomeBase bopSilkglades;
	public static RealisticBiomeBase bopSpruceWoods;
	public static RealisticBiomeBase bopTropics;
	public static RealisticBiomeBase bopVolcano;
	
	//Ocean Biomes
	public static RealisticBiomeBase bopCoralReef;
	public static RealisticBiomeBase bopKelpForest;

    public static boolean            olderBoP = false;
    // true if it's the older version without Dense Forest, etc.

	public RealisticBiomeBOPBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public RealisticBiomeBOPBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase[] s)
	{
		super(b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("BiomesOPlenty") && ConfigBOP.generateBOPBiomes)
		{
			//Overworld Biomes
			bopAlps = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPAlps);
			bopArctic = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPArctic);
			bopBambooForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPBambooForest);
			bopBayou = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPBayou);
			bopBog = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPBog);
			bopBorealForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPBorealForest);
			bopBrushland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPBrushland);
			bopCanyon = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPCanyon);
			bopChaparral = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPChaparral);
			bopCherryBlossomGrove = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPCherryBlossomGrove);
			bopConiferousForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPConiferousForest);
			bopCrag = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPCrag);
			bopDeadForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPDeadForest);
			bopDeadSwamp = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPDeadSwamp);
			bopDeciduousForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPDeciduousForest);
			
			try {
                bopDenseForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPDenseForest);
                bopEucalyptusForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPEucalyptusForest);
            } catch (NoSuchFieldError e) {
                /// old BoP
                olderBoP = true;
            }
			
			/**
			 * Enabling either of these river biomes causes a crash on startup for some reason... disabling for now. - Pink
			 */
			//bopDryRiver = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPDryRiver);
			//bopLushRiver = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPLushRiver);
			
			bopFen = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPFen);
			bopFlowerField = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPFlowerField);
			bopFrostForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPFrostForest);
			bopFungiForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPFungiForest);
			bopGarden = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPGarden);
			bopGrassland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPGrassland);
			bopGrove = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPGrove);
			bopHeathland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPHeathland);
			bopHighland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPHighland);
			bopJadeCliffs = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPJadeCliffs);
			
            try {
                bopLandOfLakes = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPLandOfLakes);
            }
            catch (NoSuchFieldError e) {
                
            }
            
			bopLavenderFields = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPLavenderFields);
			bopLushDesert = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPLushDesert);
			bopLushSwamp = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPLushSwamp);
			bopMapleWoods = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMapleWoods);
			bopMarsh = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMarsh);
			bopMeadow = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMeadow);
			bopMoor = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMoor);
			bopMountain = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMountain);
			bopMysticGrove = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMysticGrove);
			bopOminousWoods = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPOminousWoods);
			bopOriginValley = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPOriginValley);
			bopOutback = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPOutback);
			bopPrairie = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPPrairie);
			bopRainforest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPRainforest);
			bopRedwoodForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPRedwoodForest);
			bopSacredSprings = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSacredSprings);
			bopSeasonalForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSeasonalForest);
			bopShield = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPShield);
			bopShrubland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPShrubland);
			bopSludgepit = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSludgepit);
			bopSnowyConiferousForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSnowyConiferousForest);
			bopSteppe = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSteppe);
			bopTemperateRainforest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPTemperateRainforest);
			bopThicket = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPThicket);
			bopTropicalRainforest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPTropicalRainforest);
			bopTundra = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPTundra);
			bopWasteland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPWasteland);
			bopWetland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPWetland);
			bopWoodland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPWoodland);
			
            try {
                bopXericShrubland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPXericShrubland);
            }
            catch (NoSuchFieldError e) {
                // oldBoP
            }
			
			//Sub Biomes (treated as normal biomes in RTG)
			bopAlpsForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPAlpsForest);
			bopCanyonRavine = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPCanyonRavine);
			bopGlacier = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPGlacier);
			
            try {
                bopLandOfLakesMarsh = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPLandOfLakesMarsh);
			    bopSeasonalForestClearing = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSeasonalForestClearing);
            }
            catch (NoSuchFieldError e) {
                //old BoP
            }
            
			bopMangrove = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMangrove);
			bopMeadowForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPMeadowForest);
			bopOasis = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPOasis);
			bopOrchard = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPOrchard);
			bopQuagmire = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPQuagmire);
			bopScrubland = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPScrubland);
			bopSilkglades = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSilkglades);
			bopSpruceWoods = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPSpruceWoods);
			bopTropics = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPTropics);
			bopVolcano = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPVolcano);
			
			//Ocean Biomes
			bopCoralReef = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPCoralReef);
			bopKelpForest = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPKelpForest);
			
			//Overworld Biomes
			if (ConfigBOP.generateBOPAlps) { BiomeBase.addBiome(bopAlps); }
			if (ConfigBOP.generateBOPArctic) { BiomeBase.addBiome(bopArctic); }
			if (ConfigBOP.generateBOPBambooForest) { BiomeBase.addBiome(bopBambooForest); }
			if (ConfigBOP.generateBOPBayou) { BiomeBase.addBiome(bopBayou); }
			if (ConfigBOP.generateBOPBog) { BiomeBase.addBiome(bopBog); }
			if (ConfigBOP.generateBOPBorealForest) { BiomeBase.addBiome(bopBorealForest); }
			if (ConfigBOP.generateBOPBrushland) { BiomeBase.addBiome(bopBrushland); }
			if (ConfigBOP.generateBOPCanyon) { BiomeBase.addBiome(bopCanyon); }
			if (ConfigBOP.generateBOPChaparral) { BiomeBase.addBiome(bopChaparral); }
			if (ConfigBOP.generateBOPCherryBlossomGrove) { BiomeBase.addBiome(bopCherryBlossomGrove); }
			if (ConfigBOP.generateBOPConiferousForest) { BiomeBase.addBiome(bopConiferousForest); }
			if (ConfigBOP.generateBOPCrag) { BiomeBase.addBiome(bopCrag); }
			if (ConfigBOP.generateBOPDeadForest) { BiomeBase.addBiome(bopDeadForest); }
			if (ConfigBOP.generateBOPDeadSwamp) { BiomeBase.addBiome(bopDeadSwamp); }
			if (ConfigBOP.generateBOPDeciduousForest) { BiomeBase.addBiome(bopDeciduousForest); }
			
            /**
             * Enabling either of these river biomes causes a crash on startup for some reason... disabling for now. - Pink
             */
			//if (ConfigBOP.generateBOPDryRiver) { BiomeBase.addBiome(bopDryRiver); }
			//if (ConfigBOP.generateBOPLushRiver) { BiomeBase.addBiome(bopLushRiver); }
			
			if (ConfigBOP.generateBOPFen) { BiomeBase.addBiome(bopFen); }
			if (ConfigBOP.generateBOPFlowerField) { BiomeBase.addBiome(bopFlowerField); }
			if (ConfigBOP.generateBOPFrostForest) { BiomeBase.addBiome(bopFrostForest); }
			if (ConfigBOP.generateBOPFungiForest) { BiomeBase.addBiome(bopFungiForest); }
			if (ConfigBOP.generateBOPGarden) { BiomeBase.addBiome(bopGarden); }
			if (ConfigBOP.generateBOPGrassland) { BiomeBase.addBiome(bopGrassland); }
			if (ConfigBOP.generateBOPGrove) { BiomeBase.addBiome(bopGrove); }
			if (ConfigBOP.generateBOPHeathland) { BiomeBase.addBiome(bopHeathland); }
			if (ConfigBOP.generateBOPHighland) { BiomeBase.addBiome(bopHighland); }
			if (ConfigBOP.generateBOPJadeCliffs) { BiomeBase.addBiome(bopJadeCliffs); }
			if (ConfigBOP.generateBOPLavenderFields) { BiomeBase.addBiome(bopLavenderFields); }
			if (ConfigBOP.generateBOPLushDesert) { BiomeBase.addBiome(bopLushDesert); }
			if (ConfigBOP.generateBOPLushSwamp) { BiomeBase.addBiome(bopLushSwamp); }
			if (ConfigBOP.generateBOPMapleWoods) { BiomeBase.addBiome(bopMapleWoods); }
			if (ConfigBOP.generateBOPMarsh) { BiomeBase.addBiome(bopMarsh); }
			if (ConfigBOP.generateBOPMeadow) { BiomeBase.addBiome(bopMeadow); }
			if (ConfigBOP.generateBOPMoor) { BiomeBase.addBiome(bopMoor); }
			if (ConfigBOP.generateBOPMountain) { BiomeBase.addBiome(bopMountain); }
			if (ConfigBOP.generateBOPMysticGrove) { BiomeBase.addBiome(bopMysticGrove); }
			if (ConfigBOP.generateBOPOminousWoods) { BiomeBase.addBiome(bopOminousWoods); }
			if (ConfigBOP.generateBOPOriginValley) { BiomeBase.addBiome(bopOriginValley); }
			if (ConfigBOP.generateBOPOutback) { BiomeBase.addBiome(bopOutback); }
			if (ConfigBOP.generateBOPPrairie) { BiomeBase.addBiome(bopPrairie); }
			if (ConfigBOP.generateBOPRainforest) { BiomeBase.addBiome(bopRainforest); }
			if (ConfigBOP.generateBOPRedwoodForest) { BiomeBase.addBiome(bopRedwoodForest); }
			if (ConfigBOP.generateBOPSacredSprings) { BiomeBase.addBiome(bopSacredSprings); }
			if (ConfigBOP.generateBOPSeasonalForest) { BiomeBase.addBiome(bopSeasonalForest); }
			if (ConfigBOP.generateBOPShield) { BiomeBase.addBiome(bopShield); }
			if (ConfigBOP.generateBOPShrubland) { BiomeBase.addBiome(bopShrubland); }
			if (ConfigBOP.generateBOPSludgepit) { BiomeBase.addBiome(bopSludgepit); }
			if (ConfigBOP.generateBOPSnowyConiferousForest) { BiomeBase.addBiome(bopSnowyConiferousForest); }
			if (ConfigBOP.generateBOPSteppe) { BiomeBase.addBiome(bopSteppe); }
			if (ConfigBOP.generateBOPTemperateRainforest) { BiomeBase.addBiome(bopTemperateRainforest); }
			if (ConfigBOP.generateBOPThicket) { BiomeBase.addBiome(bopThicket); }
			if (ConfigBOP.generateBOPTropicalRainforest) { BiomeBase.addBiome(bopTropicalRainforest); }
			if (ConfigBOP.generateBOPTundra) { BiomeBase.addBiome(bopTundra); }
			if (ConfigBOP.generateBOPWasteland) { BiomeBase.addBiome(bopWasteland); }
			if (ConfigBOP.generateBOPWetland) { BiomeBase.addBiome(bopWetland); }
			if (ConfigBOP.generateBOPWoodland) { BiomeBase.addBiome(bopWoodland); }

            if (!olderBoP) {
                if (ConfigBOP.generateBOPDenseForest) { BiomeBase.addBiome(bopDenseForest); }
                if (ConfigBOP.generateBOPEucalyptusForest) { BiomeBase.addBiome(bopEucalyptusForest); }
                if (ConfigBOP.generateBOPLandOfLakesMarsh) { BiomeBase.addBiome(bopLandOfLakesMarsh); }
                if (ConfigBOP.generateBOPLandOfLakes) { BiomeBase.addBiome(bopLandOfLakes); }
			    if (ConfigBOP.generateBOPSeasonalForestClearing) { BiomeBase.addBiome(bopSeasonalForestClearing); }
                if (ConfigBOP.generateBOPXericShrubland) { BiomeBase.addBiome(bopXericShrubland); }
            }

			
			//Sub Biomes
			if (ConfigBOP.generateBOPAlpsForest) { BiomeBase.addBiome(bopAlpsForest); }
			if (ConfigBOP.generateBOPCanyonRavine) { BiomeBase.addBiome(bopCanyonRavine); }
			if (ConfigBOP.generateBOPGlacier) { BiomeBase.addBiome(bopGlacier); }
			if (ConfigBOP.generateBOPMangrove) { BiomeBase.addBiome(bopMangrove); }
			if (ConfigBOP.generateBOPMeadowForest) { BiomeBase.addBiome(bopMeadowForest); }
			if (ConfigBOP.generateBOPOasis) { BiomeBase.addBiome(bopOasis); }
			if (ConfigBOP.generateBOPOrchard) { BiomeBase.addBiome(bopOrchard); }
			if (ConfigBOP.generateBOPQuagmire) { BiomeBase.addBiome(bopQuagmire); }
			if (ConfigBOP.generateBOPScrubland) { BiomeBase.addBiome(bopScrubland); }
			if (ConfigBOP.generateBOPSilkglades) { BiomeBase.addBiome(bopSilkglades); }
			if (ConfigBOP.generateBOPSpruceWoods) { BiomeBase.addBiome(bopSpruceWoods); }
			if (ConfigBOP.generateBOPTropics) { BiomeBase.addBiome(bopTropics); }
			if (ConfigBOP.generateBOPVolcano) { BiomeBase.addBiome(bopVolcano); }
			
			//Ocean Biomes
			if (ConfigBOP.generateBOPCoralReef) { BiomeBase.addBiome(bopCoralReef); }
			if (ConfigBOP.generateBOPKelpForest) { BiomeBase.addBiome(bopKelpForest); }
			
			
            //Overworld Biomes
            if (ConfigBOP.villageBOPAlps) { BiomeBase.addVillageBiome(bopAlps); }
            if (ConfigBOP.villageBOPArctic) { BiomeBase.addVillageBiome(bopArctic); }
            if (ConfigBOP.villageBOPBambooForest) { BiomeBase.addVillageBiome(bopBambooForest); }
            if (ConfigBOP.villageBOPBayou) { BiomeBase.addVillageBiome(bopBayou); }
            if (ConfigBOP.villageBOPBog) { BiomeBase.addVillageBiome(bopBog); }
            if (ConfigBOP.villageBOPBorealForest) { BiomeBase.addVillageBiome(bopBorealForest); }
            if (ConfigBOP.villageBOPBrushland) { BiomeBase.addVillageBiome(bopBrushland); }
            if (ConfigBOP.villageBOPCanyon) { BiomeBase.addVillageBiome(bopCanyon); }
            if (ConfigBOP.villageBOPChaparral) { BiomeBase.addVillageBiome(bopChaparral); }
            if (ConfigBOP.villageBOPCherryBlossomGrove) { BiomeBase.addVillageBiome(bopCherryBlossomGrove); }
            if (ConfigBOP.villageBOPConiferousForest) { BiomeBase.addVillageBiome(bopConiferousForest); }
            if (ConfigBOP.villageBOPCrag) { BiomeBase.addVillageBiome(bopCrag); }
            if (ConfigBOP.villageBOPDeadForest) { BiomeBase.addVillageBiome(bopDeadForest); }
            if (ConfigBOP.villageBOPDeadSwamp) { BiomeBase.addVillageBiome(bopDeadSwamp); }
            if (ConfigBOP.villageBOPDeciduousForest) { BiomeBase.addVillageBiome(bopDeciduousForest); }
            if (ConfigBOP.villageBOPDryRiver) { BiomeBase.addVillageBiome(bopDryRiver); }
            if (ConfigBOP.villageBOPFen) { BiomeBase.addVillageBiome(bopFen); }
            if (ConfigBOP.villageBOPFlowerField) { BiomeBase.addVillageBiome(bopFlowerField); }
            if (ConfigBOP.villageBOPFrostForest) { BiomeBase.addVillageBiome(bopFrostForest); }
            if (ConfigBOP.villageBOPFungiForest) { BiomeBase.addVillageBiome(bopFungiForest); }
            if (ConfigBOP.villageBOPGarden) { BiomeBase.addVillageBiome(bopGarden); }
            if (ConfigBOP.villageBOPGrassland) { BiomeBase.addVillageBiome(bopGrassland); }
            if (ConfigBOP.villageBOPGrove) { BiomeBase.addVillageBiome(bopGrove); }
            if (ConfigBOP.villageBOPHeathland) { BiomeBase.addVillageBiome(bopHeathland); }
            if (ConfigBOP.villageBOPHighland) { BiomeBase.addVillageBiome(bopHighland); }
            if (ConfigBOP.villageBOPJadeCliffs) { BiomeBase.addVillageBiome(bopJadeCliffs); }
            if (ConfigBOP.villageBOPLavenderFields) { BiomeBase.addVillageBiome(bopLavenderFields); }
            if (ConfigBOP.villageBOPLushDesert) { BiomeBase.addVillageBiome(bopLushDesert); }
            if (ConfigBOP.villageBOPLushRiver) { BiomeBase.addVillageBiome(bopLushRiver); }
            if (ConfigBOP.villageBOPLushSwamp) { BiomeBase.addVillageBiome(bopLushSwamp); }
            if (ConfigBOP.villageBOPMapleWoods) { BiomeBase.addVillageBiome(bopMapleWoods); }
            if (ConfigBOP.villageBOPMarsh) { BiomeBase.addVillageBiome(bopMarsh); }
            if (ConfigBOP.villageBOPMeadow) { BiomeBase.addVillageBiome(bopMeadow); }
            if (ConfigBOP.villageBOPMoor) { BiomeBase.addVillageBiome(bopMoor); }
            if (ConfigBOP.villageBOPMountain) { BiomeBase.addVillageBiome(bopMountain); }
            if (ConfigBOP.villageBOPMysticGrove) { BiomeBase.addVillageBiome(bopMysticGrove); }
            if (ConfigBOP.villageBOPOminousWoods) { BiomeBase.addVillageBiome(bopOminousWoods); }
            if (ConfigBOP.villageBOPOriginValley) { BiomeBase.addVillageBiome(bopOriginValley); }
            if (ConfigBOP.villageBOPOutback) { BiomeBase.addVillageBiome(bopOutback); }
            if (ConfigBOP.villageBOPPrairie) { BiomeBase.addVillageBiome(bopPrairie); }
            if (ConfigBOP.villageBOPRainforest) { BiomeBase.addVillageBiome(bopRainforest); }
            if (ConfigBOP.villageBOPRedwoodForest) { BiomeBase.addVillageBiome(bopRedwoodForest); }
            if (ConfigBOP.villageBOPSacredSprings) { BiomeBase.addVillageBiome(bopSacredSprings); }
            if (ConfigBOP.villageBOPSeasonalForest) { BiomeBase.addVillageBiome(bopSeasonalForest); }
            if (ConfigBOP.villageBOPShield) { BiomeBase.addVillageBiome(bopShield); }
            if (ConfigBOP.villageBOPShrubland) { BiomeBase.addVillageBiome(bopShrubland); }
            if (ConfigBOP.villageBOPSludgepit) { BiomeBase.addVillageBiome(bopSludgepit); }
            if (ConfigBOP.villageBOPSnowyConiferousForest) { BiomeBase.addVillageBiome(bopSnowyConiferousForest); }
            if (ConfigBOP.villageBOPSteppe) { BiomeBase.addVillageBiome(bopSteppe); }
            if (ConfigBOP.villageBOPTemperateRainforest) { BiomeBase.addVillageBiome(bopTemperateRainforest); }
            if (ConfigBOP.villageBOPThicket) { BiomeBase.addVillageBiome(bopThicket); }
            if (ConfigBOP.villageBOPTropicalRainforest) { BiomeBase.addVillageBiome(bopTropicalRainforest); }
            if (ConfigBOP.villageBOPTundra) { BiomeBase.addVillageBiome(bopTundra); }
            if (ConfigBOP.villageBOPWasteland) { BiomeBase.addVillageBiome(bopWasteland); }
            if (ConfigBOP.villageBOPWetland) { BiomeBase.addVillageBiome(bopWetland); }
            if (ConfigBOP.villageBOPWoodland) { BiomeBase.addVillageBiome(bopWoodland); }
            
            if (!olderBoP){
                if (ConfigBOP.villageBOPDenseForest) { BiomeBase.addVillageBiome(bopDenseForest); }
                if (ConfigBOP.villageBOPEucalyptusForest) { BiomeBase.addVillageBiome(bopEucalyptusForest); }
                if (ConfigBOP.villageBOPLandOfLakes) { BiomeBase.addVillageBiome(bopLandOfLakes); }
                if (ConfigBOP.villageBOPXericShrubland) { BiomeBase.addVillageBiome(bopXericShrubland); }
                if (ConfigBOP.villageBOPLandOfLakesMarsh) { BiomeBase.addVillageBiome(bopLandOfLakesMarsh); }
                if (ConfigBOP.villageBOPSeasonalForestClearing) { BiomeBase.addVillageBiome(bopSeasonalForestClearing); }
            }
            
            //Sub Biomes
            if (ConfigBOP.villageBOPAlpsForest) { BiomeBase.addVillageBiome(bopAlpsForest); }
            if (ConfigBOP.villageBOPCanyonRavine) { BiomeBase.addVillageBiome(bopCanyonRavine); }
            if (ConfigBOP.villageBOPGlacier) { BiomeBase.addVillageBiome(bopGlacier); }
            if (ConfigBOP.villageBOPMangrove) { BiomeBase.addVillageBiome(bopMangrove); }
            if (ConfigBOP.villageBOPMeadowForest) { BiomeBase.addVillageBiome(bopMeadowForest); }
            if (ConfigBOP.villageBOPOasis) { BiomeBase.addVillageBiome(bopOasis); }
            if (ConfigBOP.villageBOPOrchard) { BiomeBase.addVillageBiome(bopOrchard); }
            if (ConfigBOP.villageBOPQuagmire) { BiomeBase.addVillageBiome(bopQuagmire); }
            if (ConfigBOP.villageBOPScrubland) { BiomeBase.addVillageBiome(bopScrubland); }
            if (ConfigBOP.villageBOPSilkglades) { BiomeBase.addVillageBiome(bopSilkglades); }
            if (ConfigBOP.villageBOPSpruceWoods) { BiomeBase.addVillageBiome(bopSpruceWoods); }
            if (ConfigBOP.villageBOPTropics) { BiomeBase.addVillageBiome(bopTropics); }
            if (ConfigBOP.villageBOPVolcano) { BiomeBase.addVillageBiome(bopVolcano); }
            
            //Ocean Biomes
            if (ConfigBOP.villageBOPCoralReef) { BiomeBase.addVillageBiome(bopCoralReef); }
            if (ConfigBOP.villageBOPKelpForest) { BiomeBase.addVillageBiome(bopKelpForest); }
		}
	}
}
