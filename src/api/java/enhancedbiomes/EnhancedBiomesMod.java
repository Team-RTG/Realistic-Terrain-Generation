package enhancedbiomes;

import static enhancedbiomes.blocks.BlockWithMeta.alfisol;
import static enhancedbiomes.blocks.BlockWithMeta.andisol;
import static enhancedbiomes.blocks.BlockWithMeta.gelisol;
import static enhancedbiomes.blocks.BlockWithMeta.histosol;
import static enhancedbiomes.blocks.BlockWithMeta.inceptisol;
import static enhancedbiomes.blocks.BlockWithMeta.mollisol;
import static enhancedbiomes.blocks.BlockWithMeta.oxisol;
import static enhancedbiomes.blocks.EnhancedBiomesBlocks.grassEB;
import static enhancedbiomes.blocks.LandTypes.typeGeoDefault;
import static enhancedbiomes.blocks.LandTypes.typeGeoGrass;
import static enhancedbiomes.blocks.LandTypes.typeGeoIce;
import static enhancedbiomes.blocks.LandTypes.typeGeoMountains;
import static enhancedbiomes.blocks.LandTypes.typeGeoSandCanyons;
import static enhancedbiomes.blocks.LandTypes.typeGeoVolcanoSea;
import static enhancedbiomes.blocks.LandTypes.typeGeoWetland;
import static enhancedbiomes.blocks.LandTypes.typeGeoWoodland;
import static enhancedbiomes.world.biome.EnhancedBiomesRock.biomeBasin;
import static enhancedbiomes.world.biomestats.BiomeCategorisation.getCatForBiome;
import static net.minecraft.init.Blocks.grass;
import static net.minecraftforge.common.BiomeDictionary.Type.SWAMP;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import enhancedbiomes.api.internal.OreGenEntry;
import enhancedbiomes.blocks.BlockWithMeta;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.proxy.CommonProxy;
import enhancedbiomes.world.biome.base.BiomeGenEBBase;

//@Mod(modid = "enhancedbiomes", name = "Enhanced Biomes", version = "2.5 for MC 1.7.10", useMetadata = true) //TODO Update version for release
public class EnhancedBiomesMod {		
	@SidedProxy(clientSide = "enhancedbiomes.proxy.ClientProxy", serverSide = "enhancedbiomes.proxy.CommonProxy")
	public static CommonProxy proxy; 
	@Instance("enhancedbiomes")
	public static EnhancedBiomesMod instance;

	public static WorldType enhancedBiomesWorldType;	
	public static WorldType singleBiomeWorldType;	

	private static Block[] rockList = new Block[BiomeGenBase.getBiomeGenArray().length];
	private static byte[] rockMetaList = new byte[BiomeGenBase.getBiomeGenArray().length];
	
	private static Block[] rockList2 = new Block[BiomeGenBase.getBiomeGenArray().length];
	private static byte[] rockMetaList2 = new byte[BiomeGenBase.getBiomeGenArray().length];
	
	public static Block[] soilList = new Block[BiomeGenBase.getBiomeGenArray().length];
	public static byte[] soilMetaList = new byte[BiomeGenBase.getBiomeGenArray().length];

	public static Block[] grassList = new Block[BiomeGenBase.getBiomeGenArray().length];
	public static byte[] grassMetaList = new byte[BiomeGenBase.getBiomeGenArray().length];
	
	public static Block[] woodList = new Block[BiomeGenBase.getBiomeGenArray().length];
	public static byte[] woodMetaList = new byte[BiomeGenBase.getBiomeGenArray().length];
	
	public static float[] biomeTempsList = new float[BiomeGenBase.getBiomeGenArray().length];
	
	public static boolean[] isStoneVillageList = new boolean[BiomeGenBase.getBiomeGenArray().length];

	public static ArrayList<OreGenEntry> modOreList = new ArrayList<OreGenEntry>();

	private static ArrayList<ArrayList<Double>> stoneNoisePP = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> stoneNoiseNP = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> stoneNoisePN = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> stoneNoiseNN = new ArrayList<ArrayList<Double>>();

	public static boolean runBiomeCheck = false;

	public static boolean worldType;
	public static boolean vanilla;
	//public static boolean achievement;
	public static boolean seasons;
	//public static boolean tides;

	public static boolean worldTypeSB;
	public static int biomeIDSB;
	
	public static int biomeSize;
	public static int villageDistance;
	public static int villageSize;

	/*public static int ratioIsland;
	public static int ratioOcean;*/
	
	public static int seasonLength;

	public static int useNewStone;
	public static int useNewOres;
	public static boolean useNewGrass;
	public static boolean useVillageMods;	
	public static boolean useListedRareBiomes;
	
	public static boolean obsidianVolcano;	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
//		EnhancedBiomesApi.orh = new OreRegisteringHandler();
//		
//		File configFile = new File("config/Enhanced Biomes/General.cfg");
//		Configuration config = new Configuration(configFile);
//		config.load();
//		
//		worldType = config.get(config.CATEGORY_GENERAL, "Allow world type module", true).getBoolean(true);
//		vanilla = config.get(config.CATEGORY_GENERAL, "Allow vanilla module", true).getBoolean(true);
//		//achievement = config.get(config.CATEGORY_GENERAL, "Allow achievement module", true).getBoolean(true);
//		seasons = config.get(config.CATEGORY_GENERAL, "Allow seasons module", true).getBoolean(true);
//		//tides = config.get(config.CATEGORY_GENERAL, "Allow tides module", true).getBoolean(true);
//		
//		worldTypeSB = config.get(config.CATEGORY_GENERAL, "Allow single biome world type", false, "Only a single biome (plus sub-biomes) will generate in this world type").getBoolean(false);
//		biomeIDSB = config.get(config.CATEGORY_GENERAL, "Biome ID to generate in single biome world type", 1, "Check the Biomes config for biome IDs").getInt();
//		
//		biomeSize = config.get(config.CATEGORY_GENERAL, "Size of biomes", 4, "Normal is 4, large biomes is 6, but other sizes can be chosen").getInt();
//		//TODO Revert for release
//		villageDistance = config.get(config.CATEGORY_GENERAL, "Distance between villages", 32, "Normal is 32").getInt();
//		villageSize = config.get(config.CATEGORY_GENERAL, "Size of villages", 0, "Normal is 0").getInt();
//		
//		useNewStone = config.get(config.CATEGORY_GENERAL, "Use new stone", 1, "If 0, the new stones will not generate, if 2 they will generate as pockets").getInt();
//		useNewOres = config.get(config.CATEGORY_GENERAL, "Use new ores - only relevant if the new stones are generating", 1, "If 0, the new ores will not generate, if 2, the new ores that drop themselves will instead drop standard ores").getInt();
//		useNewGrass = config.get(config.CATEGORY_GENERAL, "Use new grass", true, "If false, the new grass will not generate").getBoolean(true);
//		useVillageMods = config.get(config.CATEGORY_GENERAL, "Enable the modifications to villages", true, "Disabling this will solve issues with other mods which edit the way villages generate").getBoolean(true);
//		useListedRareBiomes = config.get(config.CATEGORY_GENERAL, "Use EB's rare biome code (Rare biomes have to be registered with EB to work)", true, "If true, rare biomes can have any ID, if false, their ID must be 128 more than their standard biome's ID").getBoolean(true);
//		
//		seasonLength = config.get(config.CATEGORY_GENERAL, "Days in each season", 4, "Default is 4").getInt();
//		
//		obsidianVolcano = config.get(config.CATEGORY_GENERAL, "Create volcanoes out of obsidian", true, "If false, volcanoes will instead be made from basalt").getBoolean(true);
//		config.save();
//
//		/*configFile = new File("config/Enhanced Biomes/Oceans.cfg");
//		config = new Configuration(configFile);
//		config.load();
//
//		ratioIsland = config.get(config.CATEGORY_GENERAL, "Ratio of Islands", 3, "Default is 3. This determines the ratio of islands to ocean in the 1/3 of the ocean that they generate in").getInt();
//		ratioOcean = config.get(config.CATEGORY_GENERAL, "Ratio of Ocean", 5, "Default is 5").getInt();
//
//		config.save();*/
//		
//		//Configs
//		if(vanilla) VanillaHandler.config();
//		EnhancedBiomesBiome.config();
//		
//		//Content
//		if(worldType) enhancedBiomesWorldType = new WorldTypeEnhancedBiomes("typeEB");	
//		if(worldTypeSB) singleBiomeWorldType = new WorldTypeSingleBiome("typeOneBiome");	
//		if(vanilla) VanillaHandler.load();
//		EnhancedBiomesBlocks.load();
//		EnhancedBiomesItems.load();
//
//		BlockWithMeta.createList();
//		LandTypes.createLandTypes();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {  		
//		EnhancedBiomesBiome.load();	    
//		BiomeTypes.registerAllBiomeTypes();
//		RareBiomeHandler.registerRareBiomes();
//		for(int a = 0; a < BiomeGenBase.getBiomeGenArray().length; a++) {
//			if(rockList[a] == null) rockList[a] = getRocksForBiome(a)[0].block;			
//			if(rockMetaList[a] == 0) rockMetaList[a] = getRocksForBiome(a)[0].meta;
//
//			if(rockList2[a] == null) rockList2[a] = getRocksForBiome(a)[1].block;			
//			if(rockMetaList2[a] == 0) rockMetaList2[a] = getRocksForBiome(a)[1].meta;
//
//			if(soilList[a] == null) soilList[a] = getSoilForBiome(a).block;
//			if(soilMetaList[a] == 0) soilMetaList[a] = getSoilForBiome(a).meta;
//
//			if(grassList[a] == null) grassList[a] = getGrassForBiome(a);			
//			if(grassMetaList[a] == 0) grassMetaList[a] = soilMetaList[a];
//
//			if(woodList[a] == null) woodList[a] = planks;
//			if(BiomeGenBase.getBiomeGenArray()[a] != null) biomeTempsList[a] = BiomeGenBase.getBiomeGenArray()[a].temperature;
//			else biomeTempsList[a] = 0.5F;
//		}
//        //TODO Achievements
//		//if(achievement) EnhancedBiomesAchievements.load();
//		
//		//Events
//		if(useVillageMods) {
//			MinecraftForge.TERRAIN_GEN_BUS.register(new MapGenHandler());
//			MapGenStructureIO.registerStructure(MapGenVillageEB.Start.class, "VillageEB");
//			VillagePieceSelection.registerVillagePieces();
//			MinecraftForge.TERRAIN_GEN_BUS.register(new VillageBlocksHandler());
//			//TODO New scattered features
//			//MapGenStructureIO.registerStructure(StructureScatteredFeatureEnhancedBiomesStart.class, "ScatteredFeatureEB");
//		}
//		
//		if(useNewStone == 2) {
//			MinecraftForge.EVENT_BUS.register(new StoneHandler());
//		}
//		
//		MinecraftForge.TERRAIN_GEN_BUS.register(new SubBiomeEventHandler());			
//		MinecraftForge.EVENT_BUS.register(new ReplaceBiomeBlocksHandler());				
//		MinecraftForge.TERRAIN_GEN_BUS.register(new ReplaceBiomeBlocksHandler());			
//		MinecraftForge.TERRAIN_GEN_BUS.register(new CaveHandler());	
//		MinecraftForge.EVENT_BUS.register(new PreDecorationHandler());
//		
//		MinecraftForge.ORE_GEN_BUS.register(new OreHandler());	
//		MinecraftForge.EVENT_BUS.register(new ModOreHandler());
//		
//		MinecraftForge.EVENT_BUS.register(new BonemealHandler());
//		MinecraftForge.EVENT_BUS.register(new UseHoeEventHandler());
//				
//		if(seasons) FMLCommonHandler.instance().bus().register(new SeasonTickHandler());
//        
//        if(runBiomeCheck) { 
//            try {
//				createBiomeImage();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//        	for(int x = 0; x < BiomeGenBase.getBiomeGenArray().length; x++) {
//            	if(BiomeGenBase.getBiomeGenArray()[x] != null) {
//            		BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[x];
//            		//System.out.println("public static final int " + biome.biomeName.substring(0, 1).toLowerCase() + biome.biomeName.substring(1).replaceAll(" ", "") + " = " + biome.biomeID + ";");
//            		Block top = null;
//            		if(biome.topBlock != null)top = biome.topBlock;
//            		
//            		Block filler = null;
//            		if(biome.fillerBlock != null)filler = biome.fillerBlock;
//            		
//            		System.out.println(x + ": " + biome.biomeName);
//            		System.out.println("Height: " + biome.rootHeight + ", " + biome.heightVariation);
//            		System.out.println("Temperature: " + biome.temperature);
//            		System.out.println("Rainfall: " + biome.rainfall);
//            		if(top != null)System.out.println("Top block: " + top.getLocalizedName());
//            		if(filler != null)System.out.println("Filler block: " + filler.getLocalizedName());
//            		System.out.print("Biome Types: ");
//            		for(int a = 0; a < BiomeDictionary.getTypesForBiome(biome).length; a++) {
//            			System.out.print(BiomeDictionary.getTypesForBiome(biome)[a].toString() + ", ");
//            		}
//            		System.out.println();
//            	}
//    		}
//            FMLCommonHandler.instance().handleExit(0);
//        }
//		
//		proxy.init();		   
	}
	  
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}	 
	
	private static BlockWithMeta[] getRocksForBiome(int id) {
		if(useNewStone != 1) return typeGeoDefault;
		else {
			BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[id];
			if(biome == null) 	return typeGeoGrass;
			else if(biome.biomeName.contains("Shield")) return typeGeoVolcanoSea;
			else if(biome == biomeBasin) return typeGeoMountains;
			switch(getCatForBiome(biome)) {
			case GRASS: 		return typeGeoGrass;
			case CANYONS: 		return typeGeoSandCanyons;
			case SAND:			return typeGeoSandCanyons;
			case BROADLEAF: 	return typeGeoWoodland;
			case BOREAL: 		return typeGeoWoodland;
			case WETLAND: 		return typeGeoWetland;
			case MOUNTAINS: 	return typeGeoMountains;
			case VOLCANO: 		return typeGeoVolcanoSea;
			case SEA: 			return typeGeoVolcanoSea;
			case ICE: 			return typeGeoIce;
			case ROCK: 			return typeGeoVolcanoSea;
			default:			return typeGeoGrass;
			}		
		}
	}

	private static BlockWithMeta getSoilForBiome(int id) {
		if(!useNewGrass) return BlockWithMeta.dirt;
		else
		{
			BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[id];
			if(biome == null) 	return BlockWithMeta.dirt;
			switch(getCatForBiome(biome)) {
			case GRASS: 		return mollisol;
			case CANYONS: 		return oxisol;
			case SAND:			return oxisol;
			case BROADLEAF: 	return alfisol;
			case BOREAL: 		return BlockWithMeta.dirt;
			case WETLAND: 		return histosol;
			case MOUNTAINS: 	return inceptisol;
			case VOLCANO: 		return andisol;
			case SEA: 			return andisol;
			case ICE: 			return gelisol;
			default:			return BlockWithMeta.dirt;	
			}		
		}
	}

	private static Block getGrassForBiome(int id) {
		if(!useNewGrass) return grass;
		else {
			if(getSoilForBiome(id).block == Blocks.dirt) return grass;
			return grassEB;
		}
	}

	public static boolean isValidIslandBiome(BiomeGenBase biome) {
		if(biome.rootHeight >= 0.2F) return true;
		else if(BiomeDictionary.isBiomeOfType(biome, SWAMP)) return true;
		return false;
	}
	
	public static float getTideHeight(long time) {
		time %= 24000;
		float r = 63.5F;
		if(time <= 6000) r = (float) (63.5 + (((int) (time / 500)) * 0.125D));
		else if(time <= 18000) r = (float) (65 - (((int) ((time - 6000) / 500)) * 0.125D));
		else r = (float) (62 + (((int) ((time - 18000) / 500)) * 0.125D));
		return r;
	}

	private static Block getRockForPos(int biomeID, double d) {
		return d < -0.5 ? rockList2[biomeID] : rockList[biomeID];
	}

	private static byte getRockMetaForPos(int biomeID, double d) {
		return d < -0.5 ? rockMetaList2[biomeID] : rockMetaList[biomeID];
	}
	
	public static void setStoneNoiseForCoords(int oriX, int oriZ, double noise) {
		int x = Math.abs(oriX), z = Math.abs(oriZ);				
		if((oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).size() <= x) {
			ArrayList<Double> blankArray = new ArrayList<Double>();
			for(int a = (oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).size(); a < x + 1; a++) {
				(oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).add(a, blankArray);
			}
		}
		if((oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).get(x).size() <= z) {
			for(int a = (oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).get(x).size(); a < z + 1; a++) {
				(oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).get(x).add(a, 0D);
			}
		}
		(oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).get(x).set(z, noise);
	}
	
	public static double getStoneNoiseForCoords(int oriX, int oriZ) {
		int x = Math.abs(oriX), z = Math.abs(oriZ);				
		return (oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).size() < x || 
				(oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).get(x).size() < z ? 0 :
				(oriX >= 0 ? oriZ >= 0 ? stoneNoisePP : stoneNoisePN : oriZ >= 0 ? stoneNoiseNP : stoneNoiseNN).get(x).get(z);
	}
	
	private static Block getRockForCoords(int x, int z, World world) {
		return getRockForPos(world.getBiomeGenForCoords(x, z).biomeID, getStoneNoiseForCoords(x, z));
	}
	
	private static byte getRockMetaForCoords(int x, int z, World world) {
		return getRockMetaForPos(world.getBiomeGenForCoords(x, z).biomeID, getStoneNoiseForCoords(x, z));
	}
	
	public static Block getRockForCoordsAndBiome(int x, int z, int biomeID) {
		return getRockForPos(biomeID, getStoneNoiseForCoords(x, z));
	}
	
	public static byte getRockMetaForCoordsAndBiome(int x, int z, int biomeID) {
		return getRockMetaForPos(biomeID, getStoneNoiseForCoords(x, z));
	}
	
	public static Block getDominantStone(int biomeID) {
		return rockList[biomeID];
	}
	
	public static byte getDominantStoneMeta(int biomeID) {
		return rockMetaList[biomeID];
	}
	
	public static Block getDominantStone(int x, int z, World world) {
		return rockList[world.getBiomeGenForCoords(x, z).biomeID];
	}
	
	public static byte getDominantStoneMeta(int x, int z, World world) {
		return rockMetaList[world.getBiomeGenForCoords(x, z).biomeID];
	}
	
	public static Block getCobbleFromStone(Block stone) {
		return stone == EnhancedBiomesBlocks.stoneEB ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
	}
	
	public static Block getOreDrop(Block ore) {
		if(useNewOres == 2) return ore == EnhancedBiomesBlocks.oreIronEB ? Blocks.iron_ore : ore == EnhancedBiomesBlocks.oreGoldEB ? Blocks.gold_ore : ore;
		return ore;
	}
	
	public static void createBiomeImage() throws Exception {
		try {
			BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			for(int a = 0; a < 256; a++) { 
				if(BiomeGenBase.getBiomeGenArray()[a] != null) {
					BiomeGenBase b = BiomeGenBase.getBiomeGenArray()[a];
					if(a < 128) {
						if(b instanceof BiomeGenEBBase) {
							if(a < 128 && BiomeGenBase.getBiomeGenArray()[a + 128] != null) ig2.setPaint(Color.red.darker());
							else ig2.setPaint(Color.red);
						}
						else {
							if(a < 128 && BiomeGenBase.getBiomeGenArray()[a + 128] != null) ig2.setPaint(Color.blue.darker());
							else ig2.setPaint(Color.blue);
						}
					}
					else {
						if(b instanceof BiomeGenEBBase) ig2.setPaint(Color.red.darker().darker());
						else ig2.setPaint(Color.blue.darker().darker());
					}
					ig2.fillRect(a % 16, a / 16, 1, 1);
				}
			}

			ImageIO.write(bi, "PNG", new File("config/Enhanced Biomes/biomeidimage.png"));

		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}