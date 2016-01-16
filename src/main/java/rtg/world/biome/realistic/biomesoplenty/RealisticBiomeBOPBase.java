package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
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
			if (bopAlps.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopAlps); }
			if (bopArctic.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopArctic); }
			if (bopBambooForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopBambooForest); }
			if (bopBayou.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopBayou); }
			if (bopBog.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopBog); }
			if (bopBorealForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopBorealForest); }
			if (bopBrushland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopBrushland); }
			if (bopCanyon.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopCanyon); }
			if (bopChaparral.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopChaparral); }
			if (bopCherryBlossomGrove.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopCherryBlossomGrove); }
			if (bopConiferousForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopConiferousForest); }
			if (bopCrag.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopCrag); }
			if (bopDeadForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopDeadForest); }
			if (bopDeadSwamp.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopDeadSwamp); }
			if (bopDeciduousForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopDeciduousForest); }
			
            /**
             * Enabling either of these river biomes causes a crash on startup for some reason... disabling for now. - Pink
             */
			//if (bopDryRiver.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopDryRiver); }
			//if (bopLushRiver.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopLushRiver); }
			
			if (bopFen.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopFen); }
			if (bopFlowerField.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopFlowerField); }
			if (bopFrostForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopFrostForest); }
			if (bopFungiForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopFungiForest); }
			if (bopGarden.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopGarden); }
			if (bopGrassland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopGrassland); }
			if (bopGrove.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopGrove); }
			if (bopHeathland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopHeathland); }
			if (bopHighland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopHighland); }
			if (bopJadeCliffs.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopJadeCliffs); }
			if (bopLavenderFields.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopLavenderFields); }
			if (bopLushDesert.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopLushDesert); }
			if (bopLushSwamp.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopLushSwamp); }
			if (bopMapleWoods.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMapleWoods); }
			if (bopMarsh.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMarsh); }
			if (bopMeadow.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMeadow); }
			if (bopMoor.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMoor); }
			if (bopMountain.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMountain); }
			if (bopMysticGrove.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMysticGrove); }
			if (bopOminousWoods.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopOminousWoods); }
			if (bopOriginValley.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopOriginValley); }
			if (bopOutback.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopOutback); }
			if (bopPrairie.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopPrairie); }
			if (bopRainforest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopRainforest); }
			if (bopRedwoodForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopRedwoodForest); }
			if (bopSacredSprings.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSacredSprings); }
			if (bopSeasonalForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSeasonalForest); }
			if (bopShield.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopShield); }
			if (bopShrubland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopShrubland); }
			if (bopSludgepit.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSludgepit); }
			if (bopSnowyConiferousForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSnowyConiferousForest); }
			if (bopSteppe.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSteppe); }
			if (bopTemperateRainforest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopTemperateRainforest); }
			if (bopThicket.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopThicket); }
			if (bopTropicalRainforest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopTropicalRainforest); }
			if (bopTundra.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopTundra); }
			if (bopWasteland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopWasteland); }
			if (bopWetland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopWetland); }
			if (bopWoodland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopWoodland); }

            if (!olderBoP) {
                if (bopDenseForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopDenseForest); }
                if (bopEucalyptusForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopEucalyptusForest); }
                if (bopLandOfLakesMarsh.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopLandOfLakesMarsh); }
                if (bopLandOfLakes.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopLandOfLakes); }
			    if (bopSeasonalForestClearing.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSeasonalForestClearing); }
                if (bopXericShrubland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopXericShrubland); }
            }

			
			//Sub Biomes
			if (bopAlpsForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopAlpsForest); }
			if (bopCanyonRavine.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopCanyonRavine); }
			if (bopGlacier.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopGlacier); }
			if (bopMangrove.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMangrove); }
			if (bopMeadowForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopMeadowForest); }
			if (bopOasis.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopOasis); }
			if (bopOrchard.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopOrchard); }
			if (bopQuagmire.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopQuagmire); }
			if (bopScrubland.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopScrubland); }
			if (bopSilkglades.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSilkglades); }
			if (bopSpruceWoods.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopSpruceWoods); }
			if (bopTropics.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopTropics); }
			if (bopVolcano.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopVolcano); }
			
			//Ocean Biomes
			if (bopCoralReef.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopCoralReef); }
			if (bopKelpForest.config._boolean("biomeEnabled")) { BiomeBase.addBiome(bopKelpForest); }
			
			
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
