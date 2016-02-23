package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.Loader;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

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
    //public static RealisticBiomeBase bopCanyon;
    public static RealisticBiomeBase bopChaparral;
    public static RealisticBiomeBase bopCherryBlossomGrove;
    public static RealisticBiomeBase bopConiferousForest;
    public static RealisticBiomeBase bopSnowyConiferousForest;
    public static RealisticBiomeBase bopCrag;
    public static RealisticBiomeBase bopDeadForest;
    public static RealisticBiomeBase bopDeadSwamp;
    //public static RealisticBiomeBase bopDeciduousForest;
    //public static RealisticBiomeBase bopDenseForest;
    public static RealisticBiomeBase bopEucalyptusForest;
    public static RealisticBiomeBase bopFen;
    public static RealisticBiomeBase bopFlowerField;
    public static RealisticBiomeBase bopFrostForest;
    //public static RealisticBiomeBase bopFungiForest;
    //public static RealisticBiomeBase bopGarden;
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
    //public static RealisticBiomeBase bopSludgepit;
    public static RealisticBiomeBase bopSteppe;
    public static RealisticBiomeBase bopTemperateRainforest;
    //public static RealisticBiomeBase bopThicket;
    public static RealisticBiomeBase bopTropicalRainforest;
    public static RealisticBiomeBase bopTundra;
    public static RealisticBiomeBase bopWasteland;
    public static RealisticBiomeBase bopWetland;
    public static RealisticBiomeBase bopWoodland;
    public static RealisticBiomeBase bopXericShrubland;
    
    //Sub Biomes
    //public static RealisticBiomeBase bopAlpsForest;
    //public static RealisticBiomeBase bopCanyonRavine;
    public static RealisticBiomeBase bopGlacier;
    //public static RealisticBiomeBase bopLandOfLakesMarsh;
    //public static RealisticBiomeBase bopMangrove;
    //public static RealisticBiomeBase bopMeadowForest;
    public static RealisticBiomeBase bopOasis;
    public static RealisticBiomeBase bopOrchard;
    public static RealisticBiomeBase bopQuagmire;
   // public static RealisticBiomeBase bopScrubland;
    //public static RealisticBiomeBase bopSeasonalForestClearing;
    //public static RealisticBiomeBase bopSilkglades;
    //public static RealisticBiomeBase bopSpruceWoods;
    public static RealisticBiomeBase bopTropics;
    public static RealisticBiomeBase bopVolcano;
    
    //Ocean Biomes
    public static RealisticBiomeBase bopCoralReef;
    public static RealisticBiomeBase bopKelpForest;
    
    //River Biomes
    //public static RealisticBiomeBase bopLushRiver;
    //public static RealisticBiomeBase bopDryRiver;

    // true if it's the older version without Dense Forest, etc.
    //public static boolean olderBoP = false;
    

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
		    if (BOPBiomes.alps.isPresent()) bopAlps = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPAlps);
		    if (BOPBiomes.cold_desert.isPresent()) bopArctic = new RealisticBiomeBOPArctic(BiomeConfigBOP.biomeConfigBOPArctic);
		    if (BOPBiomes.bamboo_forest.isPresent()) bopBambooForest = new RealisticBiomeBOPBambooForest(BiomeConfigBOP.biomeConfigBOPBambooForest);
		    if (BOPBiomes.bayou.isPresent()) bopBayou = new RealisticBiomeBOPBayou(BiomeConfigBOP.biomeConfigBOPBayou);
		    if (BOPBiomes.bog.isPresent()) bopBog = new RealisticBiomeBOPBog(BiomeConfigBOP.biomeConfigBOPBog);
		    if (BOPBiomes.boreal_forest.isPresent()) bopBorealForest = new RealisticBiomeBOPBorealForest(BiomeConfigBOP.biomeConfigBOPBorealForest);
		    if (BOPBiomes.brushland.isPresent()) bopBrushland = new RealisticBiomeBOPBrushland(BiomeConfigBOP.biomeConfigBOPBrushland);
		    //if (BOPBiomes.canyon.isPresent()) bopCanyon = new RealisticBiomeBOPCanyon(BiomeConfigBOP.biomeConfigBOPCanyon);
		    if (BOPBiomes.chaparral.isPresent()) bopChaparral = new RealisticBiomeBOPChaparral(BiomeConfigBOP.biomeConfigBOPChaparral);
		    if (BOPBiomes.cherry_blossom_grove.isPresent()) bopCherryBlossomGrove = new RealisticBiomeBOPCherryBlossomGrove(BiomeConfigBOP.biomeConfigBOPCherryBlossomGrove);
		    if (BOPBiomes.coniferous_forest.isPresent()) bopConiferousForest = new RealisticBiomeBOPConiferousForest(BiomeConfigBOP.biomeConfigBOPConiferousForest);
		    if (BOPBiomes.snowy_coniferous_forest.isPresent()) bopSnowyConiferousForest = new RealisticBiomeBOPSnowyConiferousForest(BiomeConfigBOP.biomeConfigBOPSnowyConiferousForest);
		    if (BOPBiomes.crag.isPresent()) bopCrag = new RealisticBiomeBOPCrag(BiomeConfigBOP.biomeConfigBOPCrag);
		    if (BOPBiomes.dead_forest.isPresent()) bopDeadForest = new RealisticBiomeBOPDeadForest(BiomeConfigBOP.biomeConfigBOPDeadForest);
		    if (BOPBiomes.dead_swamp.isPresent()) bopDeadSwamp = new RealisticBiomeBOPDeadSwamp(BiomeConfigBOP.biomeConfigBOPDeadSwamp);
		    //if (BOPBiomes.deciduous_forest.isPresent()) bopDeciduousForest = new RealisticBiomeBOPDeciduousForest(BiomeConfigBOP.biomeConfigBOPDeciduousForest);

            //if (BOPBiomes.denseForest.isPresent()) bopDenseForest = new RealisticBiomeBOPDenseForest(BiomeConfigBOP.biomeConfigBOPDenseForest);
            if (BOPBiomes.eucalyptus_forest.isPresent()) bopEucalyptusForest = new RealisticBiomeBOPEucalyptusForest(BiomeConfigBOP.biomeConfigBOPEucalyptusForest);

			if (BOPBiomes.fen.isPresent()) bopFen = new RealisticBiomeBOPFen(BiomeConfigBOP.biomeConfigBOPFen);
			if (BOPBiomes.flower_field.isPresent()) bopFlowerField = new RealisticBiomeBOPFlowerField(BiomeConfigBOP.biomeConfigBOPFlowerField);
			if (BOPBiomes.snowy_forest.isPresent()) bopFrostForest = new RealisticBiomeBOPFrostForest(BiomeConfigBOP.biomeConfigBOPFrostForest);
			//if (BOPBiomes.fungi_forest.isPresent()) bopFungiForest = new RealisticBiomeBOPFungiForest(BiomeConfigBOP.biomeConfigBOPFungiForest);
			//if (BOPBiomes.garden.isPresent()) bopGarden = new RealisticBiomeBOPGarden(BiomeConfigBOP.biomeConfigBOPGarden);
			if (BOPBiomes.grassland.isPresent()) bopGrassland = new RealisticBiomeBOPGrassland(BiomeConfigBOP.biomeConfigBOPGrassland);
			if (BOPBiomes.grove.isPresent()) bopGrove = new RealisticBiomeBOPGrove(BiomeConfigBOP.biomeConfigBOPGrove);
			if (BOPBiomes.heathland.isPresent()) bopHeathland = new RealisticBiomeBOPHeathland(BiomeConfigBOP.biomeConfigBOPHeathland);
			if (BOPBiomes.highland.isPresent()) bopHighland = new RealisticBiomeBOPHighland(BiomeConfigBOP.biomeConfigBOPHighland);
			if (BOPBiomes.overgrown_cliffs.isPresent()) bopJadeCliffs = new RealisticBiomeBOPJadeCliffs(BiomeConfigBOP.biomeConfigBOPJadeCliffs);

            if (BOPBiomes.land_of_lakes.isPresent()) bopLandOfLakes = new RealisticBiomeBOPLandOfLakes(BiomeConfigBOP.biomeConfigBOPLandOfLakes);
            
            if (BOPBiomes.lavender_fields.isPresent()) bopLavenderFields = new RealisticBiomeBOPLavenderFields(BiomeConfigBOP.biomeConfigBOPLavenderFields);
            if (BOPBiomes.lush_desert.isPresent()) bopLushDesert = new RealisticBiomeBOPLushDesert(BiomeConfigBOP.biomeConfigBOPLushDesert);
            if (BOPBiomes.lush_swamp.isPresent()) bopLushSwamp = new RealisticBiomeBOPLushSwamp(BiomeConfigBOP.biomeConfigBOPLushSwamp);
            if (BOPBiomes.maple_woods.isPresent()) bopMapleWoods = new RealisticBiomeBOPMapleWoods(BiomeConfigBOP.biomeConfigBOPMapleWoods);
            if (BOPBiomes.marsh.isPresent()) bopMarsh = new RealisticBiomeBOPMarsh(BiomeConfigBOP.biomeConfigBOPMarsh);
            if (BOPBiomes.meadow.isPresent()) bopMeadow = new RealisticBiomeBOPMeadow(BiomeConfigBOP.biomeConfigBOPMeadow);
            if (BOPBiomes.moor.isPresent()) bopMoor = new RealisticBiomeBOPMoor(BiomeConfigBOP.biomeConfigBOPMoor);
            if (BOPBiomes.mountain.isPresent()) bopMountain = new RealisticBiomeBOPMountain(BiomeConfigBOP.biomeConfigBOPMountain);
            if (BOPBiomes.mystic_grove.isPresent()) bopMysticGrove = new RealisticBiomeBOPMysticGrove(BiomeConfigBOP.biomeConfigBOPMysticGrove);
            if (BOPBiomes.ominous_woods.isPresent()) bopOminousWoods = new RealisticBiomeBOPOminousWoods(BiomeConfigBOP.biomeConfigBOPOminousWoods);
            if (BOPBiomes.origin_island.isPresent()) bopOriginValley = new RealisticBiomeBOPOriginValley(BiomeConfigBOP.biomeConfigBOPOriginValley);
            if (BOPBiomes.outback.isPresent()) bopOutback = new RealisticBiomeBOPOutback(BiomeConfigBOP.biomeConfigBOPOutback);
            if (BOPBiomes.prairie.isPresent()) bopPrairie = new RealisticBiomeBOPPrairie(BiomeConfigBOP.biomeConfigBOPPrairie);
            if (BOPBiomes.rainforest.isPresent()) bopRainforest = new RealisticBiomeBOPRainforest(BiomeConfigBOP.biomeConfigBOPRainforest);
            if (BOPBiomes.redwood_forest.isPresent()) bopRedwoodForest = new RealisticBiomeBOPRedwoodForest(BiomeConfigBOP.biomeConfigBOPRedwoodForest);
            if (BOPBiomes.sacred_springs.isPresent()) bopSacredSprings = new RealisticBiomeBOPSacredSprings(BiomeConfigBOP.biomeConfigBOPSacredSprings);
            if (BOPBiomes.seasonal_forest.isPresent()) bopSeasonalForest = new RealisticBiomeBOPSeasonalForest(BiomeConfigBOP.biomeConfigBOPSeasonalForest);
            if (BOPBiomes.shield.isPresent()) bopShield = new RealisticBiomeBOPShield(BiomeConfigBOP.biomeConfigBOPShield);
            if (BOPBiomes.shrubland.isPresent()) bopShrubland = new RealisticBiomeBOPShrubland(BiomeConfigBOP.biomeConfigBOPShrubland);
            //if (BOPBiomes.sludgepit.isPresent()) bopSludgepit = new RealisticBiomeBOPSludgepit(BiomeConfigBOP.biomeConfigBOPSludgepit);
            if (BOPBiomes.steppe.isPresent()) bopSteppe = new RealisticBiomeBOPSteppe(BiomeConfigBOP.biomeConfigBOPSteppe);
            if (BOPBiomes.temperate_rainforest.isPresent()) bopTemperateRainforest = new RealisticBiomeBOPTemperateRainforest(BiomeConfigBOP.biomeConfigBOPTemperateRainforest);
            //if (BOPBiomes.thicket.isPresent()) bopThicket = new RealisticBiomeBOPThicket(BiomeConfigBOP.biomeConfigBOPThicket);
            if (BOPBiomes.tropical_rainforest.isPresent()) bopTropicalRainforest = new RealisticBiomeBOPTropicalRainforest(BiomeConfigBOP.biomeConfigBOPTropicalRainforest);
            if (BOPBiomes.tundra.isPresent()) bopTundra = new RealisticBiomeBOPTundra(BiomeConfigBOP.biomeConfigBOPTundra);
            if (BOPBiomes.wasteland.isPresent()) bopWasteland = new RealisticBiomeBOPWasteland(BiomeConfigBOP.biomeConfigBOPWasteland);
            if (BOPBiomes.wetland.isPresent()) bopWetland = new RealisticBiomeBOPWetland(BiomeConfigBOP.biomeConfigBOPWetland);
            if (BOPBiomes.woodland.isPresent()) bopWoodland = new RealisticBiomeBOPWoodland(BiomeConfigBOP.biomeConfigBOPWoodland);

            if (BOPBiomes.xeric_shrubland.isPresent()) bopXericShrubland = new RealisticBiomeBOPXericShrubland(BiomeConfigBOP.biomeConfigBOPXericShrubland);
			
            //Sub Biomes
            //if (BOPBiomes.alpsForest.isPresent()) bopAlpsForest = new RealisticBiomeBOPAlpsForest(BiomeConfigBOP.biomeConfigBOPAlpsForest);
            //if (BOPBiomes.canyonRavine.isPresent()) bopCanyonRavine = new RealisticBiomeBOPCanyonRavine(BiomeConfigBOP.biomeConfigBOPCanyonRavine);
            if (BOPBiomes.glacier.isPresent()) bopGlacier = new RealisticBiomeBOPGlacier(BiomeConfigBOP.biomeConfigBOPGlacier);
			
            //try {
            //if (BOPBiomes.land_OfLakesMarsh.isPresent()) bopLandOfLakesMarsh = new RealisticBiomeBOPLandOfLakesMarsh(BiomeConfigBOP.biomeConfigBOPLandOfLakesMarsh);
            //}
            //catch (NoSuchFieldError e) {
            //    olderBoP = true;
            //}

            //if (BOPBiomes.mangrove.isPresent()) bopMangrove = new RealisticBiomeBOPMangrove(BiomeConfigBOP.biomeConfigBOPMangrove);
            //if (BOPBiomes.meadowForest.isPresent()) bopMeadowForest = new RealisticBiomeBOPMeadowForest(BiomeConfigBOP.biomeConfigBOPMeadowForest);
            if (BOPBiomes.oasis.isPresent()) bopOasis = new RealisticBiomeBOPOasis(BiomeConfigBOP.biomeConfigBOPOasis);
            if (BOPBiomes.orchard.isPresent()) bopOrchard = new RealisticBiomeBOPOrchard(BiomeConfigBOP.biomeConfigBOPOrchard);
            if (BOPBiomes.quagmire.isPresent()) bopQuagmire = new RealisticBiomeBOPQuagmire(BiomeConfigBOP.biomeConfigBOPQuagmire);
            //if (BOPBiomes.scrubland.isPresent()) bopScrubland = new RealisticBiomeBOPScrubland(BiomeConfigBOP.biomeConfigBOPScrubland);

            //try {
            //     if (BOPBiomes.seasonalForestClearing.isPresent()) bopSeasonalForestClearing = new RealisticBiomeBOPSeasonalForestClearing(BiomeConfigBOP.biomeConfigBOPSeasonalForestClearing);
            //}
            // catch (NoSuchFieldError e) {
            //     olderBoP = true;
            //}

            //if (BOPBiomes.silkglades.isPresent()) bopSilkglades = new RealisticBiomeBOPSilkglades(BiomeConfigBOP.biomeConfigBOPSilkglades);
            //if (BOPBiomes.spruceWoods.isPresent()) bopSpruceWoods = new RealisticBiomeBOPSpruceWoods(BiomeConfigBOP.biomeConfigBOPSpruceWoods);
            if (BOPBiomes.tropical_island.isPresent()) bopTropics = new RealisticBiomeBOPTropics(BiomeConfigBOP.biomeConfigBOPTropics);
            
            if (BiomeConfigBOP.biomeConfigBOPVolcano == null) throw new RuntimeException("Biome config cannot be NULL when adding BOP biomes.");
            if (BOPBiomes.volcanic_island.isPresent()) bopVolcano = new RealisticBiomeBOPVolcano(BiomeConfigBOP.biomeConfigBOPVolcano);
			
			//Ocean Biomes
            if (BOPBiomes.coral_reef.isPresent()) bopCoralReef = new RealisticBiomeBOPCoralReef(BiomeConfigBOP.biomeConfigBOPCoralReef);
            if (BOPBiomes.kelp_forest.isPresent()) bopKelpForest = new RealisticBiomeBOPKelpForest(BiomeConfigBOP.biomeConfigBOPKelpForest);
			
		    //River Biomes
            
            /**
             * The two river biomes don't seem to get initialised in BOP, so the null checks below always fail.
             * Therefore, no realistic versions of these biomes get created.
             */

            //if (BOPBiomes.lushRiver.isPresent()) bopLushRiver = new RealisticBiomeBOPLushRiver(BiomeConfigBOP.biomeConfigBOPLushRiver);
            //if (BOPBiomes.dryRiver.isPresent()) bopDryRiver = new RealisticBiomeBOPDryRiver(BiomeConfigBOP.biomeConfigBOPDryRiver);
        }
	}
}
