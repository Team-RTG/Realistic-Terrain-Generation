package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

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
	
	public RealisticBiomeBOPBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
	}
	
	public RealisticBiomeBOPBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase[] s)
	{
		super(b, riverbiome, t, s);
		
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("BiomesOPlenty") && ConfigBOP.generateBOPBiomes)
		{
			//Overworld Biomes
			bopAlps = new RealisticBiomeBOPAlps();
			bopArctic = new RealisticBiomeBOPArctic();
			bopBambooForest = new RealisticBiomeBOPBambooForest();
			bopBayou = new RealisticBiomeBOPBayou();
			bopBog = new RealisticBiomeBOPBog();
			bopBorealForest = new RealisticBiomeBOPBorealForest();
			bopBrushland = new RealisticBiomeBOPBrushland();
			bopCanyon = new RealisticBiomeBOPCanyon();
			bopChaparral = new RealisticBiomeBOPChaparral();
			bopCherryBlossomGrove = new RealisticBiomeBOPCherryBlossomGrove();
			bopConiferousForest = new RealisticBiomeBOPConiferousForest();
			bopCrag = new RealisticBiomeBOPCrag();
			bopDeadForest = new RealisticBiomeBOPDeadForest();
			bopDeadSwamp = new RealisticBiomeBOPDeadSwamp();
			bopDeciduousForest = new RealisticBiomeBOPDeciduousForest();
			bopDenseForest = new RealisticBiomeBOPDenseForest();
			bopEucalyptusForest = new RealisticBiomeBOPEucalyptusForest();
			bopFen = new RealisticBiomeBOPFen();
			bopFlowerField = new RealisticBiomeBOPFlowerField();
			bopFrostForest = new RealisticBiomeBOPFrostForest();
			bopFungiForest = new RealisticBiomeBOPFungiForest();
			bopGarden = new RealisticBiomeBOPGarden();
			bopGrassland = new RealisticBiomeBOPGrassland();
			bopGrove = new RealisticBiomeBOPGrove();
			bopHeathland = new RealisticBiomeBOPHeathland();
			bopHighland = new RealisticBiomeBOPHighland();
			bopJadeCliffs = new RealisticBiomeBOPJadeCliffs();
			bopLandOfLakes = new RealisticBiomeBOPLandOfLakes();
			bopLavenderFields = new RealisticBiomeBOPLavenderFields();
			bopLushDesert = new RealisticBiomeBOPLushDesert();
			bopLushSwamp = new RealisticBiomeBOPLushSwamp();
			bopMapleWoods = new RealisticBiomeBOPMapleWoods();
			bopMarsh = new RealisticBiomeBOPMarsh();
			bopMeadow = new RealisticBiomeBOPMeadow();
			bopMoor = new RealisticBiomeBOPMoor();
			bopMountain = new RealisticBiomeBOPMountain();
			bopMysticGrove = new RealisticBiomeBOPMysticGrove();
			bopOminousWoods = new RealisticBiomeBOPOminousWoods();
			bopOriginValley = new RealisticBiomeBOPOriginValley();
			bopOutback = new RealisticBiomeBOPOutback();
			bopPrairie = new RealisticBiomeBOPPrairie();
			bopRainforest = new RealisticBiomeBOPRainforest();
			bopRedwoodForest = new RealisticBiomeBOPRedwoodForest();
			bopSacredSprings = new RealisticBiomeBOPSacredSprings();
			bopSeasonalForest = new RealisticBiomeBOPSeasonalForest();
			bopShield = new RealisticBiomeBOPShield();
			bopShrubland = new RealisticBiomeBOPShrubland();
			bopSludgepit = new RealisticBiomeBOPSludgepit();
			bopSnowyConiferousForest = new RealisticBiomeBOPSnowyConiferousForest();
			bopSteppe = new RealisticBiomeBOPSteppe();
			bopTemperateRainforest = new RealisticBiomeBOPTemperateRainforest();
			bopThicket = new RealisticBiomeBOPThicket();
			bopTropicalRainforest = new RealisticBiomeBOPTropicalRainforest();
			bopTundra = new RealisticBiomeBOPTundra();
			bopWasteland = new RealisticBiomeBOPWasteland();
			bopWetland = new RealisticBiomeBOPWetland();
			bopWoodland = new RealisticBiomeBOPWoodland();
			bopXericShrubland = new RealisticBiomeBOPXericShrubland();
			
			//Sub Biomes (treated as normal biomes in RTG)
			bopAlpsForest = new RealisticBiomeBOPAlpsForest();
			bopCanyonRavine = new RealisticBiomeBOPCanyonRavine();
			bopGlacier = new RealisticBiomeBOPGlacier();
			bopLandOfLakesMarsh = new RealisticBiomeBOPLandOfLakesMarsh();
			bopMangrove = new RealisticBiomeBOPMangrove();
			bopMeadowForest = new RealisticBiomeBOPMeadowForest();
			bopOasis = new RealisticBiomeBOPOasis();
			bopOrchard = new RealisticBiomeBOPOrchard();
			bopQuagmire = new RealisticBiomeBOPQuagmire();
			bopScrubland = new RealisticBiomeBOPScrubland();
			bopSeasonalForestClearing = new RealisticBiomeBOPSeasonalForestClearing();
			bopSilkglades = new RealisticBiomeBOPSilkglades();
			bopSpruceWoods = new RealisticBiomeBOPSpruceWoods();
			bopTropics = new RealisticBiomeBOPTropics();
			bopVolcano = new RealisticBiomeBOPVolcano();
			
			//Ocean Biomes
			bopCoralReef = new RealisticBiomeBOPCoralReef();
			bopKelpForest = new RealisticBiomeBOPKelpForest();
			
			//Overworld Biomes
			if (ConfigBOP.generateBOPalps) { BiomeBase.addBiome(bopAlps); }
			if (ConfigBOP.generateBOParctic) { BiomeBase.addBiome(bopArctic); }
			if (ConfigBOP.generateBOPbambooForest) { BiomeBase.addBiome(bopBambooForest); }
			if (ConfigBOP.generateBOPbayou) { BiomeBase.addBiome(bopBayou); }
			if (ConfigBOP.generateBOPbog) { BiomeBase.addBiome(bopBog); }
			if (ConfigBOP.generateBOPborealForest) { BiomeBase.addBiome(bopBorealForest); }
			if (ConfigBOP.generateBOPbrushland) { BiomeBase.addBiome(bopBrushland); }
			if (ConfigBOP.generateBOPcanyon) { BiomeBase.addBiome(bopCanyon); }
			if (ConfigBOP.generateBOPchaparral) { BiomeBase.addBiome(bopChaparral); }
			if (ConfigBOP.generateBOPcherryBlossomGrove) { BiomeBase.addBiome(bopCherryBlossomGrove); }
			if (ConfigBOP.generateBOPconiferousForest) { BiomeBase.addBiome(bopConiferousForest); }
			if (ConfigBOP.generateBOPcrag) { BiomeBase.addBiome(bopCrag); }
			if (ConfigBOP.generateBOPdeadForest) { BiomeBase.addBiome(bopDeadForest); }
			if (ConfigBOP.generateBOPdeadSwamp) { BiomeBase.addBiome(bopDeadSwamp); }
			if (ConfigBOP.generateBOPdeciduousForest) { BiomeBase.addBiome(bopDeciduousForest); }
			if (ConfigBOP.generateBOPdenseForest) { BiomeBase.addBiome(bopDenseForest); }
			if (ConfigBOP.generateBOPeucalyptusForest) { BiomeBase.addBiome(bopEucalyptusForest); }
			if (ConfigBOP.generateBOPfen) { BiomeBase.addBiome(bopFen); }
			if (ConfigBOP.generateBOPflowerField) { BiomeBase.addBiome(bopFlowerField); }
			if (ConfigBOP.generateBOPfrostForest) { BiomeBase.addBiome(bopFrostForest); }
			if (ConfigBOP.generateBOPfungiForest) { BiomeBase.addBiome(bopFungiForest); }
			if (ConfigBOP.generateBOPgarden) { BiomeBase.addBiome(bopGarden); }
			if (ConfigBOP.generateBOPgrassland) { BiomeBase.addBiome(bopGrassland); }
			if (ConfigBOP.generateBOPgrove) { BiomeBase.addBiome(bopGrove); }
			if (ConfigBOP.generateBOPheathland) { BiomeBase.addBiome(bopHeathland); }
			if (ConfigBOP.generateBOPhighland) { BiomeBase.addBiome(bopHighland); }
			if (ConfigBOP.generateBOPjadeCliffs) { BiomeBase.addBiome(bopJadeCliffs); }
			if (ConfigBOP.generateBOPlandOfLakes) { BiomeBase.addBiome(bopLandOfLakes); }
			if (ConfigBOP.generateBOPlandOfLakesMarsh) { BiomeBase.addBiome(bopLavenderFields); }
			if (ConfigBOP.generateBOPlushDesert) { BiomeBase.addBiome(bopLushDesert); }
			if (ConfigBOP.generateBOPlushSwamp) { BiomeBase.addBiome(bopLushSwamp); }
			if (ConfigBOP.generateBOPmapleWoods) { BiomeBase.addBiome(bopMapleWoods); }
			if (ConfigBOP.generateBOPmarsh) { BiomeBase.addBiome(bopMarsh); }
			if (ConfigBOP.generateBOPmeadow) { BiomeBase.addBiome(bopMeadow); }
			if (ConfigBOP.generateBOPmoor) { BiomeBase.addBiome(bopMoor); }
			if (ConfigBOP.generateBOPmountain) { BiomeBase.addBiome(bopMountain); }
			if (ConfigBOP.generateBOPmysticGrove) { BiomeBase.addBiome(bopMysticGrove); }
			if (ConfigBOP.generateBOPominousWoods) { BiomeBase.addBiome(bopOminousWoods); }
			if (ConfigBOP.generateBOPoriginValley) { BiomeBase.addBiome(bopOriginValley); }
			if (ConfigBOP.generateBOPoutback) { BiomeBase.addBiome(bopOutback); }
			if (ConfigBOP.generateBOPprairie) { BiomeBase.addBiome(bopPrairie); }
			if (ConfigBOP.generateBOPrainforest) { BiomeBase.addBiome(bopRainforest); }
			if (ConfigBOP.generateBOPredwoodForest) { BiomeBase.addBiome(bopRedwoodForest); }
			if (ConfigBOP.generateBOPsacredSprings) { BiomeBase.addBiome(bopSacredSprings); }
			if (ConfigBOP.generateBOPseasonalForest) { BiomeBase.addBiome(bopSeasonalForest); }
			if (ConfigBOP.generateBOPshield) { BiomeBase.addBiome(bopShield); }
			if (ConfigBOP.generateBOPshrubland) { BiomeBase.addBiome(bopShrubland); }
			if (ConfigBOP.generateBOPsludgepit) { BiomeBase.addBiome(bopSludgepit); }
			if (ConfigBOP.generateBOPsnowyConiferousForest) { BiomeBase.addBiome(bopSnowyConiferousForest); }
			if (ConfigBOP.generateBOPsteppe) { BiomeBase.addBiome(bopSteppe); }
			if (ConfigBOP.generateBOPtemperateRainforest) { BiomeBase.addBiome(bopTemperateRainforest); }
			if (ConfigBOP.generateBOPthicket) { BiomeBase.addBiome(bopThicket); }
			if (ConfigBOP.generateBOPtropicalRainforest) { BiomeBase.addBiome(bopTropicalRainforest); }
			if (ConfigBOP.generateBOPtundra) { BiomeBase.addBiome(bopTundra); }
			if (ConfigBOP.generateBOPwasteland) { BiomeBase.addBiome(bopWasteland); }
			if (ConfigBOP.generateBOPwetland) { BiomeBase.addBiome(bopWetland); }
			if (ConfigBOP.generateBOPwoodland) { BiomeBase.addBiome(bopWoodland); }
			if (ConfigBOP.generateBOPxericShrubland) { BiomeBase.addBiome(bopXericShrubland); }
			
			//Sub Biomes
			if (ConfigBOP.generateBOPalpsForest) { BiomeBase.addBiome(bopAlpsForest); }
			if (ConfigBOP.generateBOPcanyonRavine) { BiomeBase.addBiome(bopCanyonRavine); }
			if (ConfigBOP.generateBOPglacier) { BiomeBase.addBiome(bopGlacier); }
			if (ConfigBOP.generateBOPlandOfLakesMarsh) { BiomeBase.addBiome(bopLandOfLakesMarsh); }
			if (ConfigBOP.generateBOPmangrove) { BiomeBase.addBiome(bopMangrove); }
			if (ConfigBOP.generateBOPmeadowForest) { BiomeBase.addBiome(bopMeadowForest); }
			if (ConfigBOP.generateBOPoasis) { BiomeBase.addBiome(bopOasis); }
			if (ConfigBOP.generateBOPorchard) { BiomeBase.addBiome(bopOrchard); }
			if (ConfigBOP.generateBOPquagmire) { BiomeBase.addBiome(bopQuagmire); }
			if (ConfigBOP.generateBOPscrubland) { BiomeBase.addBiome(bopScrubland); }
			if (ConfigBOP.generateBOPseasonalForestClearing) { BiomeBase.addBiome(bopSeasonalForestClearing); }
			if (ConfigBOP.generateBOPsilkglades) { BiomeBase.addBiome(bopSilkglades); }
			if (ConfigBOP.generateBOPspruceWoods) { BiomeBase.addBiome(bopSpruceWoods); }
			if (ConfigBOP.generateBOPtropics) { BiomeBase.addBiome(bopTropics); }
			if (ConfigBOP.generateBOPvolcano) { BiomeBase.addBiome(bopVolcano); }
			
			//Ocean Biomes
			if (ConfigBOP.generateBOPcoralReef) { BiomeBase.addBiome(bopCoralReef); }
			if (ConfigBOP.generateBOPkelpForest) { BiomeBase.addBiome(bopKelpForest); }
			
			
            //Overworld Biomes
            if (ConfigBOP.villageBOPalps) { BiomeBase.addVillageBiome(bopAlps); }
            if (ConfigBOP.villageBOParctic) { BiomeBase.addVillageBiome(bopArctic); }
            if (ConfigBOP.villageBOPbambooForest) { BiomeBase.addVillageBiome(bopBambooForest); }
            if (ConfigBOP.villageBOPbayou) { BiomeBase.addVillageBiome(bopBayou); }
            if (ConfigBOP.villageBOPbog) { BiomeBase.addVillageBiome(bopBog); }
            if (ConfigBOP.villageBOPborealForest) { BiomeBase.addVillageBiome(bopBorealForest); }
            if (ConfigBOP.villageBOPbrushland) { BiomeBase.addVillageBiome(bopBrushland); }
            if (ConfigBOP.villageBOPcanyon) { BiomeBase.addVillageBiome(bopCanyon); }
            if (ConfigBOP.villageBOPchaparral) { BiomeBase.addVillageBiome(bopChaparral); }
            if (ConfigBOP.villageBOPcherryBlossomGrove) { BiomeBase.addVillageBiome(bopCherryBlossomGrove); }
            if (ConfigBOP.villageBOPconiferousForest) { BiomeBase.addVillageBiome(bopConiferousForest); }
            if (ConfigBOP.villageBOPcrag) { BiomeBase.addVillageBiome(bopCrag); }
            if (ConfigBOP.villageBOPdeadForest) { BiomeBase.addVillageBiome(bopDeadForest); }
            if (ConfigBOP.villageBOPdeadSwamp) { BiomeBase.addVillageBiome(bopDeadSwamp); }
            if (ConfigBOP.villageBOPdeciduousForest) { BiomeBase.addVillageBiome(bopDeciduousForest); }
            if (ConfigBOP.villageBOPdenseForest) { BiomeBase.addVillageBiome(bopDenseForest); }
            if (ConfigBOP.villageBOPeucalyptusForest) { BiomeBase.addVillageBiome(bopEucalyptusForest); }
            if (ConfigBOP.villageBOPfen) { BiomeBase.addVillageBiome(bopFen); }
            if (ConfigBOP.villageBOPflowerField) { BiomeBase.addVillageBiome(bopFlowerField); }
            if (ConfigBOP.villageBOPfrostForest) { BiomeBase.addVillageBiome(bopFrostForest); }
            if (ConfigBOP.villageBOPfungiForest) { BiomeBase.addVillageBiome(bopFungiForest); }
            if (ConfigBOP.villageBOPgarden) { BiomeBase.addVillageBiome(bopGarden); }
            if (ConfigBOP.villageBOPgrassland) { BiomeBase.addVillageBiome(bopGrassland); }
            if (ConfigBOP.villageBOPgrove) { BiomeBase.addVillageBiome(bopGrove); }
            if (ConfigBOP.villageBOPheathland) { BiomeBase.addVillageBiome(bopHeathland); }
            if (ConfigBOP.villageBOPhighland) { BiomeBase.addVillageBiome(bopHighland); }
            if (ConfigBOP.villageBOPjadeCliffs) { BiomeBase.addVillageBiome(bopJadeCliffs); }
            if (ConfigBOP.villageBOPlandOfLakes) { BiomeBase.addVillageBiome(bopLandOfLakes); }
            if (ConfigBOP.villageBOPlandOfLakesMarsh) { BiomeBase.addVillageBiome(bopLavenderFields); }
            if (ConfigBOP.villageBOPlushDesert) { BiomeBase.addVillageBiome(bopLushDesert); }
            if (ConfigBOP.villageBOPlushSwamp) { BiomeBase.addVillageBiome(bopLushSwamp); }
            if (ConfigBOP.villageBOPmapleWoods) { BiomeBase.addVillageBiome(bopMapleWoods); }
            if (ConfigBOP.villageBOPmarsh) { BiomeBase.addVillageBiome(bopMarsh); }
            if (ConfigBOP.villageBOPmeadow) { BiomeBase.addVillageBiome(bopMeadow); }
            if (ConfigBOP.villageBOPmoor) { BiomeBase.addVillageBiome(bopMoor); }
            if (ConfigBOP.villageBOPmountain) { BiomeBase.addVillageBiome(bopMountain); }
            if (ConfigBOP.villageBOPmysticGrove) { BiomeBase.addVillageBiome(bopMysticGrove); }
            if (ConfigBOP.villageBOPominousWoods) { BiomeBase.addVillageBiome(bopOminousWoods); }
            if (ConfigBOP.villageBOPoriginValley) { BiomeBase.addVillageBiome(bopOriginValley); }
            if (ConfigBOP.villageBOPoutback) { BiomeBase.addVillageBiome(bopOutback); }
            if (ConfigBOP.villageBOPprairie) { BiomeBase.addVillageBiome(bopPrairie); }
            if (ConfigBOP.villageBOPrainforest) { BiomeBase.addVillageBiome(bopRainforest); }
            if (ConfigBOP.villageBOPredwoodForest) { BiomeBase.addVillageBiome(bopRedwoodForest); }
            if (ConfigBOP.villageBOPsacredSprings) { BiomeBase.addVillageBiome(bopSacredSprings); }
            if (ConfigBOP.villageBOPseasonalForest) { BiomeBase.addVillageBiome(bopSeasonalForest); }
            if (ConfigBOP.villageBOPshield) { BiomeBase.addVillageBiome(bopShield); }
            if (ConfigBOP.villageBOPshrubland) { BiomeBase.addVillageBiome(bopShrubland); }
            if (ConfigBOP.villageBOPsludgepit) { BiomeBase.addVillageBiome(bopSludgepit); }
            if (ConfigBOP.villageBOPsnowyConiferousForest) { BiomeBase.addVillageBiome(bopSnowyConiferousForest); }
            if (ConfigBOP.villageBOPsteppe) { BiomeBase.addVillageBiome(bopSteppe); }
            if (ConfigBOP.villageBOPtemperateRainforest) { BiomeBase.addVillageBiome(bopTemperateRainforest); }
            if (ConfigBOP.villageBOPthicket) { BiomeBase.addVillageBiome(bopThicket); }
            if (ConfigBOP.villageBOPtropicalRainforest) { BiomeBase.addVillageBiome(bopTropicalRainforest); }
            if (ConfigBOP.villageBOPtundra) { BiomeBase.addVillageBiome(bopTundra); }
            if (ConfigBOP.villageBOPwasteland) { BiomeBase.addVillageBiome(bopWasteland); }
            if (ConfigBOP.villageBOPwetland) { BiomeBase.addVillageBiome(bopWetland); }
            if (ConfigBOP.villageBOPwoodland) { BiomeBase.addVillageBiome(bopWoodland); }
            if (ConfigBOP.villageBOPxericShrubland) { BiomeBase.addVillageBiome(bopXericShrubland); }
            
            //Sub Biomes
            if (ConfigBOP.villageBOPalpsForest) { BiomeBase.addVillageBiome(bopAlpsForest); }
            if (ConfigBOP.villageBOPcanyonRavine) { BiomeBase.addVillageBiome(bopCanyonRavine); }
            if (ConfigBOP.villageBOPglacier) { BiomeBase.addVillageBiome(bopGlacier); }
            if (ConfigBOP.villageBOPlandOfLakesMarsh) { BiomeBase.addVillageBiome(bopLandOfLakesMarsh); }
            if (ConfigBOP.villageBOPmangrove) { BiomeBase.addVillageBiome(bopMangrove); }
            if (ConfigBOP.villageBOPmeadowForest) { BiomeBase.addVillageBiome(bopMeadowForest); }
            if (ConfigBOP.villageBOPoasis) { BiomeBase.addVillageBiome(bopOasis); }
            if (ConfigBOP.villageBOPorchard) { BiomeBase.addVillageBiome(bopOrchard); }
            if (ConfigBOP.villageBOPquagmire) { BiomeBase.addVillageBiome(bopQuagmire); }
            if (ConfigBOP.villageBOPscrubland) { BiomeBase.addVillageBiome(bopScrubland); }
            if (ConfigBOP.villageBOPseasonalForestClearing) { BiomeBase.addVillageBiome(bopSeasonalForestClearing); }
            if (ConfigBOP.villageBOPsilkglades) { BiomeBase.addVillageBiome(bopSilkglades); }
            if (ConfigBOP.villageBOPspruceWoods) { BiomeBase.addVillageBiome(bopSpruceWoods); }
            if (ConfigBOP.villageBOPtropics) { BiomeBase.addVillageBiome(bopTropics); }
            if (ConfigBOP.villageBOPvolcano) { BiomeBase.addVillageBiome(bopVolcano); }
            
            //Ocean Biomes
            if (ConfigBOP.villageBOPcoralReef) { BiomeBase.addVillageBiome(bopCoralReef); }
            if (ConfigBOP.villageBOPkelpForest) { BiomeBase.addVillageBiome(bopKelpForest); }
		}
	}
}
