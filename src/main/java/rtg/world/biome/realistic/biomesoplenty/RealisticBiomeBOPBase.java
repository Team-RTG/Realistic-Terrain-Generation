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
		}
	}
}
