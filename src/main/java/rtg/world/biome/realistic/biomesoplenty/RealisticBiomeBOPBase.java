package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.biomesoplenty.config.BiomeConfigBOP;
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
		if (Loader.isModLoaded("BiomesOPlenty"))
		{
			//Overworld Biomes
			bopAlps = new RealisticBiomeBOPAlps(BiomeConfigBOP.biomeConfigBOPAlps);
			bopArctic = new RealisticBiomeBOPArctic(BiomeConfigBOP.biomeConfigBOPArctic);
			bopBambooForest = new RealisticBiomeBOPBambooForest(BiomeConfigBOP.biomeConfigBOPBambooForest);
			bopBayou = new RealisticBiomeBOPBayou(BiomeConfigBOP.biomeConfigBOPBayou);
			bopBog = new RealisticBiomeBOPBog(BiomeConfigBOP.biomeConfigBOPBog);
			bopBorealForest = new RealisticBiomeBOPBorealForest(BiomeConfigBOP.biomeConfigBOPBorealForest);
			bopBrushland = new RealisticBiomeBOPBrushland(BiomeConfigBOP.biomeConfigBOPBrushland);
			bopCanyon = new RealisticBiomeBOPCanyon(BiomeConfigBOP.biomeConfigBOPCanyon);
			bopChaparral = new RealisticBiomeBOPChaparral(BiomeConfigBOP.biomeConfigBOPChaparral);
			bopCherryBlossomGrove = new RealisticBiomeBOPCherryBlossomGrove(BiomeConfigBOP.biomeConfigBOPCherryBlossomGrove);
			bopConiferousForest = new RealisticBiomeBOPConiferousForest(BiomeConfigBOP.biomeConfigBOPConiferousForest);
			bopCrag = new RealisticBiomeBOPCrag(BiomeConfigBOP.biomeConfigBOPCrag);
			bopDeadForest = new RealisticBiomeBOPDeadForest(BiomeConfigBOP.biomeConfigBOPDeadForest);
			bopDeadSwamp = new RealisticBiomeBOPDeadSwamp(BiomeConfigBOP.biomeConfigBOPDeadSwamp);
			bopDeciduousForest = new RealisticBiomeBOPDeciduousForest(BiomeConfigBOP.biomeConfigBOPDeciduousForest);
			
			try {
                bopDenseForest = new RealisticBiomeBOPDenseForest(BiomeConfigBOP.biomeConfigBOPDenseForest);
                bopEucalyptusForest = new RealisticBiomeBOPEucalyptusForest(BiomeConfigBOP.biomeConfigBOPEucalyptusForest);
            } catch (NoSuchFieldError e) {
                /// old BoP
                olderBoP = true;
            }
			
			/**
			 * Enabling either of these river biomes causes a crash on startup for some reason... disabling for now. - Pink
			 */
			//bopDryRiver = new RealisticBiomeBOPDryRiver(BiomeConfigBOP.biomeConfigBOPDryRiver);
			//bopLushRiver = new RealisticBiomeBOPLushRiver(BiomeConfigBOP.biomeConfigBOPLushRiver);
			
			bopFen = new RealisticBiomeBOPFen(BiomeConfigBOP.biomeConfigBOPFen);
			bopFlowerField = new RealisticBiomeBOPFlowerField(BiomeConfigBOP.biomeConfigBOPFlowerField);
			bopFrostForest = new RealisticBiomeBOPFrostForest(BiomeConfigBOP.biomeConfigBOPFrostForest);
			bopFungiForest = new RealisticBiomeBOPFungiForest(BiomeConfigBOP.biomeConfigBOPFungiForest);
			bopGarden = new RealisticBiomeBOPGarden(BiomeConfigBOP.biomeConfigBOPGarden);
			bopGrassland = new RealisticBiomeBOPGrassland(BiomeConfigBOP.biomeConfigBOPGrassland);
			bopGrove = new RealisticBiomeBOPGrove(BiomeConfigBOP.biomeConfigBOPGrove);
			bopHeathland = new RealisticBiomeBOPHeathland(BiomeConfigBOP.biomeConfigBOPHeathland);
			bopHighland = new RealisticBiomeBOPHighland(BiomeConfigBOP.biomeConfigBOPHighland);
			bopJadeCliffs = new RealisticBiomeBOPJadeCliffs(BiomeConfigBOP.biomeConfigBOPJadeCliffs);
			
            try {
                bopLandOfLakes = new RealisticBiomeBOPLandOfLakes(BiomeConfigBOP.biomeConfigBOPLandOfLakes);
            }
            catch (NoSuchFieldError e) {
                
            }
            
			bopLavenderFields = new RealisticBiomeBOPLavenderFields(BiomeConfigBOP.biomeConfigBOPLavenderFields);
			bopLushDesert = new RealisticBiomeBOPLushDesert(BiomeConfigBOP.biomeConfigBOPLushDesert);
			bopLushSwamp = new RealisticBiomeBOPLushSwamp(BiomeConfigBOP.biomeConfigBOPLushSwamp);
			bopMapleWoods = new RealisticBiomeBOPMapleWoods(BiomeConfigBOP.biomeConfigBOPMapleWoods);
			bopMarsh = new RealisticBiomeBOPMarsh(BiomeConfigBOP.biomeConfigBOPMarsh);
			bopMeadow = new RealisticBiomeBOPMeadow(BiomeConfigBOP.biomeConfigBOPMeadow);
			bopMoor = new RealisticBiomeBOPMoor(BiomeConfigBOP.biomeConfigBOPMoor);
			bopMountain = new RealisticBiomeBOPMountain(BiomeConfigBOP.biomeConfigBOPMountain);
			bopMysticGrove = new RealisticBiomeBOPMysticGrove(BiomeConfigBOP.biomeConfigBOPMysticGrove);
			bopOminousWoods = new RealisticBiomeBOPOminousWoods(BiomeConfigBOP.biomeConfigBOPOminousWoods);
			bopOriginValley = new RealisticBiomeBOPOriginValley(BiomeConfigBOP.biomeConfigBOPOriginValley);
			bopOutback = new RealisticBiomeBOPOutback(BiomeConfigBOP.biomeConfigBOPOutback);
			bopPrairie = new RealisticBiomeBOPPrairie(BiomeConfigBOP.biomeConfigBOPPrairie);
			bopRainforest = new RealisticBiomeBOPRainforest(BiomeConfigBOP.biomeConfigBOPRainforest);
			bopRedwoodForest = new RealisticBiomeBOPRedwoodForest(BiomeConfigBOP.biomeConfigBOPRedwoodForest);
			bopSacredSprings = new RealisticBiomeBOPSacredSprings(BiomeConfigBOP.biomeConfigBOPSacredSprings);
			bopSeasonalForest = new RealisticBiomeBOPSeasonalForest(BiomeConfigBOP.biomeConfigBOPSeasonalForest);
			bopShield = new RealisticBiomeBOPShield(BiomeConfigBOP.biomeConfigBOPShield);
			bopShrubland = new RealisticBiomeBOPShrubland(BiomeConfigBOP.biomeConfigBOPShrubland);
			bopSludgepit = new RealisticBiomeBOPSludgepit(BiomeConfigBOP.biomeConfigBOPSludgepit);
			bopSnowyConiferousForest = new RealisticBiomeBOPSnowyConiferousForest(BiomeConfigBOP.biomeConfigBOPSnowyConiferousForest);
			bopSteppe = new RealisticBiomeBOPSteppe(BiomeConfigBOP.biomeConfigBOPSteppe);
			bopTemperateRainforest = new RealisticBiomeBOPTemperateRainforest(BiomeConfigBOP.biomeConfigBOPTemperateRainforest);
			bopThicket = new RealisticBiomeBOPThicket(BiomeConfigBOP.biomeConfigBOPThicket);
			bopTropicalRainforest = new RealisticBiomeBOPTropicalRainforest(BiomeConfigBOP.biomeConfigBOPTropicalRainforest);
			bopTundra = new RealisticBiomeBOPTundra(BiomeConfigBOP.biomeConfigBOPTundra);
			bopWasteland = new RealisticBiomeBOPWasteland(BiomeConfigBOP.biomeConfigBOPWasteland);
			bopWetland = new RealisticBiomeBOPWetland(BiomeConfigBOP.biomeConfigBOPWetland);
			bopWoodland = new RealisticBiomeBOPWoodland(BiomeConfigBOP.biomeConfigBOPWoodland);
			
            try {
                bopXericShrubland = new RealisticBiomeBOPXericShrubland(BiomeConfigBOP.biomeConfigBOPXericShrubland);
            }
            catch (NoSuchFieldError e) {
                // oldBoP
            }
			
			//Sub Biomes (treated as normal biomes in RTG)
			bopAlpsForest = new RealisticBiomeBOPAlpsForest(BiomeConfigBOP.biomeConfigBOPAlpsForest);
			bopCanyonRavine = new RealisticBiomeBOPCanyonRavine(BiomeConfigBOP.biomeConfigBOPCanyonRavine);
			bopGlacier = new RealisticBiomeBOPGlacier(BiomeConfigBOP.biomeConfigBOPGlacier);
			
            try {
                bopLandOfLakesMarsh = new RealisticBiomeBOPLandOfLakesMarsh(BiomeConfigBOP.biomeConfigBOPLandOfLakesMarsh);
			    bopSeasonalForestClearing = new RealisticBiomeBOPSeasonalForestClearing(BiomeConfigBOP.biomeConfigBOPSeasonalForestClearing);
            }
            catch (NoSuchFieldError e) {
                //old BoP
            }
            
			bopMangrove = new RealisticBiomeBOPMangrove(BiomeConfigBOP.biomeConfigBOPMangrove);
			bopMeadowForest = new RealisticBiomeBOPMeadowForest(BiomeConfigBOP.biomeConfigBOPMeadowForest);
			bopOasis = new RealisticBiomeBOPOasis(BiomeConfigBOP.biomeConfigBOPOasis);
			bopOrchard = new RealisticBiomeBOPOrchard(BiomeConfigBOP.biomeConfigBOPOrchard);
			bopQuagmire = new RealisticBiomeBOPQuagmire(BiomeConfigBOP.biomeConfigBOPQuagmire);
			bopScrubland = new RealisticBiomeBOPScrubland(BiomeConfigBOP.biomeConfigBOPScrubland);
			bopSilkglades = new RealisticBiomeBOPSilkglades(BiomeConfigBOP.biomeConfigBOPSilkglades);
			bopSpruceWoods = new RealisticBiomeBOPSpruceWoods(BiomeConfigBOP.biomeConfigBOPSpruceWoods);
			bopTropics = new RealisticBiomeBOPTropics(BiomeConfigBOP.biomeConfigBOPTropics);
			bopVolcano = new RealisticBiomeBOPVolcano(BiomeConfigBOP.biomeConfigBOPVolcano);
			
			//Ocean Biomes
			bopCoralReef = new RealisticBiomeBOPCoralReef(BiomeConfigBOP.biomeConfigBOPCoralReef);
			bopKelpForest = new RealisticBiomeBOPKelpForest(BiomeConfigBOP.biomeConfigBOPKelpForest);
		}
	}
}
