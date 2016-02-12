package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import biomesoplenty.api.content.BOPCBiomes;
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
    public static RealisticBiomeBase bopSnowyConiferousForest;
    public static RealisticBiomeBase bopCrag;
    public static RealisticBiomeBase bopDeadForest;
    public static RealisticBiomeBase bopDeadSwamp;
    public static RealisticBiomeBase bopDeciduousForest;
    public static RealisticBiomeBase bopDenseForest;
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
    public static RealisticBiomeBase bopSteppe;
    public static RealisticBiomeBase bopTemperateRainforest;
    public static RealisticBiomeBase bopThicket;
    public static RealisticBiomeBase bopTropicalRainforest;
    public static RealisticBiomeBase bopTundra;
    public static RealisticBiomeBase bopWasteland;
    public static RealisticBiomeBase bopWetland;
    public static RealisticBiomeBase bopWoodland;
    public static RealisticBiomeBase bopXericShrubland;
    
    //Sub Biomes
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
    
    //River Biomes
    public static RealisticBiomeBase bopLushRiver;
    public static RealisticBiomeBase bopDryRiver;

    // true if it's the older version without Dense Forest, etc.
    public static boolean olderBoP = false;
    

	public RealisticBiomeBOPBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(config, b, riverbiome, t, s);
		
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("BiomesOPlenty"))
		{
		    //Overworld Biomes
		    if (BOPCBiomes.alps != null) bopAlps = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPAlps);
		    if (BOPCBiomes.arctic != null) bopArctic = new RealisticBiomeBOPArctic(BiomeConfigBOP.biomeConfigBOPArctic);
		    if (BOPCBiomes.bambooForest != null) bopBambooForest = new RealisticBiomeBOPBambooForest(BiomeConfigBOP.biomeConfigBOPBambooForest);
		    if (BOPCBiomes.bayou != null) bopBayou = new RealisticBiomeBOPBayou(BiomeConfigBOP.biomeConfigBOPBayou);
		    if (BOPCBiomes.bog != null) bopBog = new RealisticBiomeBOPBog(BiomeConfigBOP.biomeConfigBOPBog);
		    if (BOPCBiomes.borealForest != null) bopBorealForest = new RealisticBiomeBOPBorealForest(BiomeConfigBOP.biomeConfigBOPBorealForest);
		    if (BOPCBiomes.brushland != null) bopBrushland = new RealisticBiomeBOPBrushland(BiomeConfigBOP.biomeConfigBOPBrushland);
		    if (BOPCBiomes.canyon != null) bopCanyon = new RealisticBiomeBOPCanyon(BiomeConfigBOP.biomeConfigBOPCanyon);
		    if (BOPCBiomes.chaparral != null) bopChaparral = new RealisticBiomeBOPChaparral(BiomeConfigBOP.biomeConfigBOPChaparral);
		    if (BOPCBiomes.cherryBlossomGrove != null) bopCherryBlossomGrove = new RealisticBiomeBOPCherryBlossomGrove(BiomeConfigBOP.biomeConfigBOPCherryBlossomGrove);
		    if (BOPCBiomes.coniferousForest != null) bopConiferousForest = new RealisticBiomeBOPConiferousForest(BiomeConfigBOP.biomeConfigBOPConiferousForest);
		    if (BOPCBiomes.snowyConiferousForest != null) bopSnowyConiferousForest = new RealisticBiomeBOPSnowyConiferousForest(BiomeConfigBOP.biomeConfigBOPSnowyConiferousForest);
		    if (BOPCBiomes.crag != null) bopCrag = new RealisticBiomeBOPCrag(BiomeConfigBOP.biomeConfigBOPCrag);
		    if (BOPCBiomes.deadForest != null) bopDeadForest = new RealisticBiomeBOPDeadForest(BiomeConfigBOP.biomeConfigBOPDeadForest);
		    if (BOPCBiomes.deadSwamp != null) bopDeadSwamp = new RealisticBiomeBOPDeadSwamp(BiomeConfigBOP.biomeConfigBOPDeadSwamp);
		    if (BOPCBiomes.deciduousForest != null) bopDeciduousForest = new RealisticBiomeBOPDeciduousForest(BiomeConfigBOP.biomeConfigBOPDeciduousForest);
			
			try {
			    if (BOPCBiomes.denseForest != null) bopDenseForest = new RealisticBiomeBOPDenseForest(BiomeConfigBOP.biomeConfigBOPDenseForest);
			    if (BOPCBiomes.eucalyptusForest != null) bopEucalyptusForest = new RealisticBiomeBOPEucalyptusForest(BiomeConfigBOP.biomeConfigBOPEucalyptusForest);
            }
			catch (NoSuchFieldError e) {
                olderBoP = true;
            }

			if (BOPCBiomes.fen != null) bopFen = new RealisticBiomeBOPFen(BiomeConfigBOP.biomeConfigBOPFen);
			if (BOPCBiomes.flowerField != null) bopFlowerField = new RealisticBiomeBOPFlowerField(BiomeConfigBOP.biomeConfigBOPFlowerField);
			if (BOPCBiomes.frostForest != null) bopFrostForest = new RealisticBiomeBOPFrostForest(BiomeConfigBOP.biomeConfigBOPFrostForest);
			if (BOPCBiomes.fungiForest != null) bopFungiForest = new RealisticBiomeBOPFungiForest(BiomeConfigBOP.biomeConfigBOPFungiForest);
			if (BOPCBiomes.garden != null) bopGarden = new RealisticBiomeBOPGarden(BiomeConfigBOP.biomeConfigBOPGarden);
			if (BOPCBiomes.grassland != null) bopGrassland = new RealisticBiomeBOPGrassland(BiomeConfigBOP.biomeConfigBOPGrassland);
			if (BOPCBiomes.grove != null) bopGrove = new RealisticBiomeBOPGrove(BiomeConfigBOP.biomeConfigBOPGrove);
			if (BOPCBiomes.heathland != null) bopHeathland = new RealisticBiomeBOPHeathland(BiomeConfigBOP.biomeConfigBOPHeathland);
			if (BOPCBiomes.highland != null) bopHighland = new RealisticBiomeBOPHighland(BiomeConfigBOP.biomeConfigBOPHighland);
			if (BOPCBiomes.jadeCliffs != null) bopJadeCliffs = new RealisticBiomeBOPJadeCliffs(BiomeConfigBOP.biomeConfigBOPJadeCliffs);
			
            try {
                if (BOPCBiomes.landOfLakes != null) bopLandOfLakes = new RealisticBiomeBOPLandOfLakes(BiomeConfigBOP.biomeConfigBOPLandOfLakes);
            }
            catch (NoSuchFieldError e) {
                olderBoP = true;
            }
            
            if (BOPCBiomes.lavenderFields != null) bopLavenderFields = new RealisticBiomeBOPLavenderFields(BiomeConfigBOP.biomeConfigBOPLavenderFields);
            if (BOPCBiomes.lushDesert != null) bopLushDesert = new RealisticBiomeBOPLushDesert(BiomeConfigBOP.biomeConfigBOPLushDesert);
            if (BOPCBiomes.lushSwamp != null) bopLushSwamp = new RealisticBiomeBOPLushSwamp(BiomeConfigBOP.biomeConfigBOPLushSwamp);
            if (BOPCBiomes.mapleWoods != null) bopMapleWoods = new RealisticBiomeBOPMapleWoods(BiomeConfigBOP.biomeConfigBOPMapleWoods);
            if (BOPCBiomes.marsh != null) bopMarsh = new RealisticBiomeBOPMarsh(BiomeConfigBOP.biomeConfigBOPMarsh);
            if (BOPCBiomes.meadow != null) bopMeadow = new RealisticBiomeBOPMeadow(BiomeConfigBOP.biomeConfigBOPMeadow);
            if (BOPCBiomes.moor != null) bopMoor = new RealisticBiomeBOPMoor(BiomeConfigBOP.biomeConfigBOPMoor);
            if (BOPCBiomes.mountain != null) bopMountain = new RealisticBiomeBOPMountain(BiomeConfigBOP.biomeConfigBOPMountain);
            if (BOPCBiomes.mysticGrove != null) bopMysticGrove = new RealisticBiomeBOPMysticGrove(BiomeConfigBOP.biomeConfigBOPMysticGrove);
            if (BOPCBiomes.ominousWoods != null) bopOminousWoods = new RealisticBiomeBOPOminousWoods(BiomeConfigBOP.biomeConfigBOPOminousWoods);
            if (BOPCBiomes.originValley != null) bopOriginValley = new RealisticBiomeBOPOriginValley(BiomeConfigBOP.biomeConfigBOPOriginValley);
            if (BOPCBiomes.outback != null) bopOutback = new RealisticBiomeBOPOutback(BiomeConfigBOP.biomeConfigBOPOutback);
            if (BOPCBiomes.prairie != null) bopPrairie = new RealisticBiomeBOPPrairie(BiomeConfigBOP.biomeConfigBOPPrairie);
            if (BOPCBiomes.rainforest != null) bopRainforest = new RealisticBiomeBOPRainforest(BiomeConfigBOP.biomeConfigBOPRainforest);
            if (BOPCBiomes.redwoodForest != null) bopRedwoodForest = new RealisticBiomeBOPRedwoodForest(BiomeConfigBOP.biomeConfigBOPRedwoodForest);
            if (BOPCBiomes.sacredSprings != null) bopSacredSprings = new RealisticBiomeBOPSacredSprings(BiomeConfigBOP.biomeConfigBOPSacredSprings);
            if (BOPCBiomes.seasonalForest != null) bopSeasonalForest = new RealisticBiomeBOPSeasonalForest(BiomeConfigBOP.biomeConfigBOPSeasonalForest);
            if (BOPCBiomes.shield != null) bopShield = new RealisticBiomeBOPShield(BiomeConfigBOP.biomeConfigBOPShield);
            if (BOPCBiomes.shrubland != null) bopShrubland = new RealisticBiomeBOPShrubland(BiomeConfigBOP.biomeConfigBOPShrubland);
            if (BOPCBiomes.sludgepit != null) bopSludgepit = new RealisticBiomeBOPSludgepit(BiomeConfigBOP.biomeConfigBOPSludgepit);
            if (BOPCBiomes.steppe != null) bopSteppe = new RealisticBiomeBOPSteppe(BiomeConfigBOP.biomeConfigBOPSteppe);
            if (BOPCBiomes.temperateRainforest != null) bopTemperateRainforest = new RealisticBiomeBOPTemperateRainforest(BiomeConfigBOP.biomeConfigBOPTemperateRainforest);
            if (BOPCBiomes.thicket != null) bopThicket = new RealisticBiomeBOPThicket(BiomeConfigBOP.biomeConfigBOPThicket);
            if (BOPCBiomes.tropicalRainforest != null) bopTropicalRainforest = new RealisticBiomeBOPTropicalRainforest(BiomeConfigBOP.biomeConfigBOPTropicalRainforest);
            if (BOPCBiomes.tundra != null) bopTundra = new RealisticBiomeBOPTundra(BiomeConfigBOP.biomeConfigBOPTundra);
            if (BOPCBiomes.wasteland != null) bopWasteland = new RealisticBiomeBOPWasteland(BiomeConfigBOP.biomeConfigBOPWasteland);
            if (BOPCBiomes.wetland != null) bopWetland = new RealisticBiomeBOPWetland(BiomeConfigBOP.biomeConfigBOPWetland);
            if (BOPCBiomes.woodland != null) bopWoodland = new RealisticBiomeBOPWoodland(BiomeConfigBOP.biomeConfigBOPWoodland);
			
            try {
                if (BOPCBiomes.xericShrubland != null) bopXericShrubland = new RealisticBiomeBOPXericShrubland(BiomeConfigBOP.biomeConfigBOPXericShrubland);
            }
            catch (NoSuchFieldError e) {
                olderBoP = true;
            }
			
            //Sub Biomes
            if (BOPCBiomes.alpsForest != null) bopAlpsForest = new RealisticBiomeBOPAlpsForest(BiomeConfigBOP.biomeConfigBOPAlpsForest);
            if (BOPCBiomes.canyonRavine != null) bopCanyonRavine = new RealisticBiomeBOPCanyonRavine(BiomeConfigBOP.biomeConfigBOPCanyonRavine);
            if (BOPCBiomes.glacier != null) bopGlacier = new RealisticBiomeBOPGlacier(BiomeConfigBOP.biomeConfigBOPGlacier);
			
            try {
                if (BOPCBiomes.landOfLakesMarsh != null) bopLandOfLakesMarsh = new RealisticBiomeBOPLandOfLakesMarsh(BiomeConfigBOP.biomeConfigBOPLandOfLakesMarsh);
            }
            catch (NoSuchFieldError e) {
                olderBoP = true;
            }
            
            if (BOPCBiomes.mangrove != null) bopMangrove = new RealisticBiomeBOPMangrove(BiomeConfigBOP.biomeConfigBOPMangrove);
            if (BOPCBiomes.meadowForest != null) bopMeadowForest = new RealisticBiomeBOPMeadowForest(BiomeConfigBOP.biomeConfigBOPMeadowForest);
            if (BOPCBiomes.oasis != null) bopOasis = new RealisticBiomeBOPOasis(BiomeConfigBOP.biomeConfigBOPOasis);
            if (BOPCBiomes.orchard != null) bopOrchard = new RealisticBiomeBOPOrchard(BiomeConfigBOP.biomeConfigBOPOrchard);
            if (BOPCBiomes.quagmire != null) bopQuagmire = new RealisticBiomeBOPQuagmire(BiomeConfigBOP.biomeConfigBOPQuagmire);
            if (BOPCBiomes.scrubland != null) bopScrubland = new RealisticBiomeBOPScrubland(BiomeConfigBOP.biomeConfigBOPScrubland);
			
            try {
                if (BOPCBiomes.seasonalForestClearing != null) bopSeasonalForestClearing = new RealisticBiomeBOPSeasonalForestClearing(BiomeConfigBOP.biomeConfigBOPSeasonalForestClearing);
            }
            catch (NoSuchFieldError e) {
                olderBoP = true;
            }
			
            if (BOPCBiomes.silkglades != null) bopSilkglades = new RealisticBiomeBOPSilkglades(BiomeConfigBOP.biomeConfigBOPSilkglades);
            if (BOPCBiomes.spruceWoods != null) bopSpruceWoods = new RealisticBiomeBOPSpruceWoods(BiomeConfigBOP.biomeConfigBOPSpruceWoods);
            if (BOPCBiomes.tropics != null) bopTropics = new RealisticBiomeBOPTropics(BiomeConfigBOP.biomeConfigBOPTropics);
            if (BOPCBiomes.volcano != null) bopVolcano = new RealisticBiomeBOPVolcano(BiomeConfigBOP.biomeConfigBOPVolcano);
			
			//Ocean Biomes
            if (BOPCBiomes.coralReef != null) bopCoralReef = new RealisticBiomeBOPCoralReef(BiomeConfigBOP.biomeConfigBOPCoralReef);
            if (BOPCBiomes.kelpForest != null) bopKelpForest = new RealisticBiomeBOPKelpForest(BiomeConfigBOP.biomeConfigBOPKelpForest);
			
		    //River Biomes
            
            /**
             * The two river biomes don't seem to get initialised in BOP, so the null checks below always fail.
             * Therefore, no realistic versions of these biomes get created.
             */
            
            if (BOPCBiomes.lushRiver != null) bopLushRiver = new RealisticBiomeBOPLushRiver(BiomeConfigBOP.biomeConfigBOPLushRiver);
            if (BOPCBiomes.dryRiver != null) bopDryRiver = new RealisticBiomeBOPDryRiver(BiomeConfigBOP.biomeConfigBOPDryRiver);
		}
	}
}
