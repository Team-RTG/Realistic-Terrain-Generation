package highlands;

import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.biome.BiomeGenAlps;
import highlands.biome.BiomeGenAutumnForest;
import highlands.biome.BiomeGenBadlands;
import highlands.biome.BiomeGenBaldHill;
import highlands.biome.BiomeGenBaseHighlands;
import highlands.biome.BiomeGenBirchHills;
import highlands.biome.BiomeGenBog;
import highlands.biome.BiomeGenCliffs;
import highlands.biome.BiomeGenDesertIsland;
import highlands.biome.BiomeGenDesertMountains;
import highlands.biome.BiomeGenDunes;
import highlands.biome.BiomeGenEstuary;
import highlands.biome.BiomeGenFlyingMountains;
import highlands.biome.BiomeGenGlacier;
import highlands.biome.BiomeGenHighlands;
import highlands.biome.BiomeGenLake;
import highlands.biome.BiomeGenLowlands;
import highlands.biome.BiomeGenMeadow;
import highlands.biome.BiomeGenMesa;
import highlands.biome.BiomeGenOasis;
import highlands.biome.BiomeGenOcean2;
import highlands.biome.BiomeGenOutback;
import highlands.biome.BiomeGenPinelands;
import highlands.biome.BiomeGenRainforest;
import highlands.biome.BiomeGenRedwoodForest;
import highlands.biome.BiomeGenRockIsland;
import highlands.biome.BiomeGenRockMountains;
import highlands.biome.BiomeGenSahel;
import highlands.biome.BiomeGenSavannah;
import highlands.biome.BiomeGenShrubland;
import highlands.biome.BiomeGenSnowMountains;
import highlands.biome.BiomeGenSteppe;
import highlands.biome.BiomeGenTallPineForest;
import highlands.biome.BiomeGenTropicalIslands;
import highlands.biome.BiomeGenTropics;
import highlands.biome.BiomeGenTundra;
import highlands.biome.BiomeGenVolcanoIsland;
import highlands.biome.BiomeGenWoodlands;
import highlands.biome.BiomeGenWoodsMountains;
import highlands.block.BlockCocoaPlant2;
import highlands.block.BlockHLPlankSlab;
import highlands.block.BlockHighlandsLeaves;
import highlands.block.BlockHighlandsLeaves2;
import highlands.block.BlockHighlandsLog;
import highlands.block.BlockHighlandsLog2;
import highlands.block.BlockHighlandsPlanks;
import highlands.block.BlockHighlandsSapling;
import highlands.block.BlockHighlandsSapling2;
import highlands.block.BlockHighlandsSmallPlants;
import highlands.block.BlockHighlandsStairs;
import highlands.block.ItemBlockMetadata;
import highlands.block.ItemBlockPlanks;
import highlands.block.ItemHighlandsBerries;
import highlands.block.ItemSlabPlanks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;


public class Initializer
{
	private static String biomePrefix = "";
	public static boolean tooManyBiomesInstalled;
	private static boolean railcraftInstalled;
	
	public static void constructSettings()
	{
		
		Highlands.HighlandsBiomeSizeDefault = Config.biomeSize.getInt();
		Highlands.HighlandsBiomeSizeLB = Config.LBbiomeSize.getInt();
		
		Highlands.islandRarity = Config.islandRarity.getInt();
		
		int a = Config.moreOceans.getInt();
		for(int i = 0; i < a; i++){
			if(Highlands.improvedOceans && HighlandsBiomes.ocean2!= null) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.ocean2);
			else {
				Highlands.improvedOceans = false;
				HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.ocean);
			}
		}
		
		Highlands.mocreaturescomp = Config.mobModCompatibility.getBoolean(false);
		
		Highlands.skyColorFlag = Config.skyColors.getBoolean(false);
		
		Highlands.vanillaBlocksFlag = !Config.modWoodAndLeaves.getBoolean(true);
		Highlands.plantsFlag = Config.smallPlants.getBoolean(true);
		
		Highlands.addBoPbiomes = Config.addBoPbiomes.getBoolean(false);
		
		Highlands.useOreGens = Config.genOre.getBoolean(true);
		Highlands.useGenLayers = !Config.safeMode.getBoolean(false);
		
		tooManyBiomesInstalled = Loader.isModLoaded("TooManyBiomes");
		railcraftInstalled = Loader.isModLoaded("Railcraft");
	}	
	
	
	public static void constructBlocks() {
		//Saplings
		HighlandsBlocks.acaciaSapling = new BlockHighlandsSapling(1).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_acaciaSapling");
		HighlandsBlocks.beechSapling = new BlockHighlandsSapling(6).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_beechSapling");
		HighlandsBlocks.canopySapling = new BlockHighlandsSapling(4).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_canopySapling");
		HighlandsBlocks.deadSapling = new BlockHighlandsSapling(7).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_deadSapling");
		HighlandsBlocks.greatOakSapling = new BlockHighlandsSapling(5).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_greatOakSapling");
		HighlandsBlocks.firSapling = new BlockHighlandsSapling(0).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_firSapling");
		HighlandsBlocks.poplarSapling = new BlockHighlandsSapling(2).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_poplarSapling");
		HighlandsBlocks.redwoodSapling = new BlockHighlandsSapling(3).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_redwoodSapling");
		HighlandsBlocks.evergreenBushSapling = new BlockHighlandsSapling(8).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_evgBushSapling");
		HighlandsBlocks.deciduousBushSapling = new BlockHighlandsSapling(9).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_decBushSapling");
		HighlandsBlocks.palmSapling = new BlockHighlandsSapling(10).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_palmSapling");
		HighlandsBlocks.ironwoodSapling = new BlockHighlandsSapling(11).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_ironwoodSapling");
		HighlandsBlocks.mangroveSapling = new BlockHighlandsSapling(12).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_mangroveSapling");
		HighlandsBlocks.ashSapling = new BlockHighlandsSapling(13).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_ashSapling");
		HighlandsBlocks.autumnOrangeSapling = new BlockHighlandsSapling(14).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_autumnOrangeSapling");
		HighlandsBlocks.autumnYellowSapling = new BlockHighlandsSapling(15).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_autumnYellowSapling");
		
		HighlandsBlocks.japaneseMapleSapling = new BlockHighlandsSapling2(1).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_japaneseMapleSapling");
		
		//Wood
		HighlandsBlocks.acaciaWood = new BlockHighlandsLog(1).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_acaciaWood");
		HighlandsBlocks.canopyWood = new BlockHighlandsLog(4).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_canopyWood");
		HighlandsBlocks.firWood = new BlockHighlandsLog(0).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_firWood");
		HighlandsBlocks.poplarWood = new BlockHighlandsLog(2).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_poplarWood");
		HighlandsBlocks.redwoodWood = new BlockHighlandsLog(3).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_redwoodWood");
		HighlandsBlocks.palmWood = new BlockHighlandsLog(10).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_palmWood");
		HighlandsBlocks.ironWood = new BlockHighlandsLog(11).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_ironwoodWood");
		HighlandsBlocks.mangroveWood = new BlockHighlandsLog(12).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_mangroveWood");
		HighlandsBlocks.ashWood = new BlockHighlandsLog(13).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_ashWood");
		
		HighlandsBlocks.japaneseMapleWood = new BlockHighlandsLog2(1).setHardness(2.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_japaneseMapleWood");
		
		//Leaves
		HighlandsBlocks.acaciaLeaves = new BlockHighlandsLeaves(1).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_acaciaLeaves");
		HighlandsBlocks.canopyLeaves = new BlockHighlandsLeaves(4).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_canopyLeaves");
		HighlandsBlocks.firLeaves = new BlockHighlandsLeaves(0).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_firLeaves");
		HighlandsBlocks.poplarLeaves = new BlockHighlandsLeaves(2).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_poplarLeaves");
		HighlandsBlocks.redwoodLeaves = new BlockHighlandsLeaves(3).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_redwoodLeaves");
		HighlandsBlocks.palmLeaves = new BlockHighlandsLeaves(10).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_palmLeaves");
		HighlandsBlocks.ironwoodLeaves = new BlockHighlandsLeaves(11).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_ironwoodLeaves");
		HighlandsBlocks.mangroveLeaves = new BlockHighlandsLeaves(12).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_mangroveLeaves");
		HighlandsBlocks.ashLeaves = new BlockHighlandsLeaves(13).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_ashLeaves");
		HighlandsBlocks.autumnYellowLeaves = new BlockHighlandsLeaves(14).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_autumnYellowLeaves");
		HighlandsBlocks.autumnOrangeLeaves = new BlockHighlandsLeaves(15).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_autumnOrangeLeaves");
		
		HighlandsBlocks.japaneseMapleLeaves = new BlockHighlandsLeaves2(1).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("hl_japaneseMapleLeaves");
		
		//Plants
		HighlandsBlocks.blueFlower = new BlockHighlandsSmallPlants(0).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_blueFlower");
		HighlandsBlocks.leafyFern = new BlockHighlandsSmallPlants(1).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_leafyFern");
		HighlandsBlocks.whiteFlower = new BlockHighlandsSmallPlants(2).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_whiteFlower");
		HighlandsBlocks.cattail = new BlockHighlandsSmallPlants(3).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_cattail");
		HighlandsBlocks.lavender = new BlockHighlandsSmallPlants(4).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_lavender");
		HighlandsBlocks.raspberryBush = new BlockHighlandsSmallPlants(5).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_raspberryBush");
		HighlandsBlocks.blueberryBush = new BlockHighlandsSmallPlants(6).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_blueberryBush");
		HighlandsBlocks.thornbush = new BlockHighlandsSmallPlants(7).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_thornbush");
		HighlandsBlocks.cotton = new BlockHighlandsSmallPlants(8).setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass).setBlockName("hl_cotton");
		
		HighlandsBlocks.berries = new ItemHighlandsBerries().setUnlocalizedName("hl_berries");
		GameRegistry.registerItem(HighlandsBlocks.berries, "hl_berries");
		
		HighlandsBlocks.cocoa2 = new BlockCocoaPlant2().setBlockName("hl_cocoa");
		GameRegistry.registerBlock(HighlandsBlocks.cocoa2, "hl_cocoa");
		
		//Planks and stairs
		HighlandsBlocks.hlplanks = new BlockHighlandsPlanks().setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_woodPlanks");
		GameRegistry.registerBlock(HighlandsBlocks.hlplanks, ItemBlockPlanks.class, "hl_woodPlanks");
		
		HighlandsBlocks.hlplankstairs0 = new BlockHighlandsStairs(HighlandsBlocks.hlplanks, 0).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_woodStairs0");
		registerBlock(HighlandsBlocks.hlplankstairs0, "Yellow Wood Stairs");
		
		HighlandsBlocks.hlplankstairs1 = new BlockHighlandsStairs(HighlandsBlocks.hlplanks, 1).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_woodStairs1");
		registerBlock(HighlandsBlocks.hlplankstairs1, "White Wood Stairs");
		
		HighlandsBlocks.hlplankstairs2 = new BlockHighlandsStairs(HighlandsBlocks.hlplanks, 2).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_woodStairs2");
		registerBlock(HighlandsBlocks.hlplankstairs2, "Red Wood Stairs");
		
		HighlandsBlocks.hlplankstairs3 = new BlockHighlandsStairs(HighlandsBlocks.hlplanks, 3).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_woodStairs3");
		registerBlock(HighlandsBlocks.hlplankstairs3, "Grey Wood Stairs");
		
		HighlandsBlocks.hlplankhalfdouble = new BlockHLPlankSlab(true, HighlandsBlocks.hlplanks).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_woodSlabDouble");
		GameRegistry.registerBlock(HighlandsBlocks.hlplankhalfdouble, ItemSlabPlanks.class, "hl_woodDoubleSlab");
		HighlandsBlocks.hlplankhalf = new BlockHLPlankSlab(false, HighlandsBlocks.hlplanks).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundTypeWood).setBlockName("hl_woodSlab");
		GameRegistry.registerBlock(HighlandsBlocks.hlplankhalf, ItemSlabPlanks.class, "hl_woodSlab");
		
		


		HighlandsBlocks.saplings = new Block[]{
				HighlandsBlocks.firSapling,
				HighlandsBlocks.acaciaSapling,
				HighlandsBlocks.redwoodSapling,
				HighlandsBlocks.poplarSapling,
				HighlandsBlocks.canopySapling,
				HighlandsBlocks.greatOakSapling,
				HighlandsBlocks.beechSapling,
				HighlandsBlocks.deadSapling,
				HighlandsBlocks.evergreenBushSapling,
				HighlandsBlocks.deciduousBushSapling,
				HighlandsBlocks.palmSapling,
				HighlandsBlocks.ironwoodSapling,
				HighlandsBlocks.mangroveSapling,
				HighlandsBlocks.ashSapling,
				HighlandsBlocks.autumnYellowSapling,
				HighlandsBlocks.autumnOrangeSapling,
				
				HighlandsBlocks.japaneseMapleSapling,
		};

		HighlandsBlocks.leaves = new Block[]{
				HighlandsBlocks.firLeaves,
				HighlandsBlocks.acaciaLeaves,
				HighlandsBlocks.redwoodLeaves,
				HighlandsBlocks.poplarLeaves,
				HighlandsBlocks.canopyLeaves,
				HighlandsBlocks.palmLeaves,
				HighlandsBlocks.ironwoodLeaves,
				HighlandsBlocks.mangroveLeaves,
				HighlandsBlocks.ashLeaves,
				HighlandsBlocks.autumnYellowLeaves,
				HighlandsBlocks.autumnOrangeLeaves,
				
				HighlandsBlocks.japaneseMapleLeaves,
		};

		HighlandsBlocks.logs = new Block[]{
				HighlandsBlocks.firWood,
				HighlandsBlocks.acaciaWood,
				HighlandsBlocks.redwoodWood,
				HighlandsBlocks.poplarWood,
				HighlandsBlocks.canopyWood,
				HighlandsBlocks.palmWood,
				HighlandsBlocks.ironWood,
				HighlandsBlocks.mangroveWood,
				HighlandsBlocks.ashWood,
				
				HighlandsBlocks.japaneseMapleWood,
		};
		
		HighlandsBlocks.plants = new Block[]{
			    HighlandsBlocks.blueFlower,
			    HighlandsBlocks.leafyFern,
			    HighlandsBlocks.lavender,
			    HighlandsBlocks.cattail,
			    HighlandsBlocks.whiteFlower,
			    HighlandsBlocks.raspberryBush,
			    HighlandsBlocks.blueberryBush,
			    HighlandsBlocks.cotton,
			    HighlandsBlocks.thornbush,
		};
		
		HighlandsBlocks.planks = new Block[]{
				HighlandsBlocks.hlplanks,
				HighlandsBlocks.hlplankstairs0,
				HighlandsBlocks.hlplankstairs1,
				HighlandsBlocks.hlplankstairs2,
				HighlandsBlocks.hlplankstairs3,
				HighlandsBlocks.hlplankhalf,
				HighlandsBlocks.hlplankhalfdouble,
		};

	}

	
	/*Height values for vanilla overrides*/
	private static final Height desertIsle = new Height(0.1F, 0.1F);
	private static final Height woodIsle = new Height(0.2F, 0.4F);
	private static final Height windIsle = new Height(0.1F, 0.2F);
	
	public static void constructBiomes() {
		
		biomePrefix = Config.biomePrefix.getBoolean(false) ? "Highlands_" : "";
		
		//main biomes
		if(Config.alpsID > -1) {
			HighlandsBiomes.alps = new BiomeGenAlps(Config.alpsID).setBiomeName(biomePrefix+"Alps");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.alps);
		}
		if(Config.autumnForestID > -1) {
			HighlandsBiomes.autumnForest = new BiomeGenAutumnForest(Config.autumnForestID).setBiomeName(biomePrefix+"Autumn Forest");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.autumnForest);
		}
		if(Config.badlandsID > -1) {
			HighlandsBiomes.badlands = new BiomeGenBadlands(Config.badlandsID).setBiomeName(biomePrefix+"Badlands");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.badlands);
		}
		if(Config.birchHillsID > -1) {
			HighlandsBiomes.birchHills = new BiomeGenBirchHills(Config.birchHillsID).setBiomeName(biomePrefix+"Birch Hills");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.birchHills);
		}
		if(Config.bogID > -1) {
			HighlandsBiomes.bog = new BiomeGenBog(Config.bogID).setBiomeName(biomePrefix+"Bog");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.bog);
		}
		if(Config.cliffsID > -1) {
			HighlandsBiomes.cliffs = new BiomeGenCliffs(Config.cliffsID).setBiomeName(biomePrefix+"Cliffs");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.cliffs);
		}
		if(Config.desertMountainsID > -1) {
			HighlandsBiomes.desertMountains = new BiomeGenDesertMountains(Config.desertMountainsID).setBiomeName(biomePrefix+"Desert Mountains");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.desertMountains);
			BiomeManager.removeSpawnBiome(HighlandsBiomes.desertMountains);
		}
		if(Config.dunesID > -1) {
			HighlandsBiomes.dunes = new BiomeGenDunes(Config.dunesID).setBiomeName(biomePrefix+"Dunes");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.dunes);
		}
		if(Config.estuaryID > -1) {
			HighlandsBiomes.estuary = new BiomeGenEstuary(Config.estuaryID).setBiomeName(biomePrefix+"Estuary");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.estuary);
		}
		//if(Config.evergladesID > -1) {
		//	HighlandsBiomes.everglades = new BiomeGenEverglades(Config.evergladesID).setBiomeName(biomePrefix+"Everglades");
		//	HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.everglades);
		//}
		if(Config.flyingMountainsID > -1) {
			HighlandsBiomes.flyingMountains = new BiomeGenFlyingMountains(Config.flyingMountainsID).setBiomeName(biomePrefix+"Flying Mountains");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.flyingMountains);
			BiomeManager.removeSpawnBiome(HighlandsBiomes.flyingMountains);
		}
		if(Config.glacierID > -1) {
			HighlandsBiomes.glacier = new BiomeGenGlacier(Config.glacierID).setBiomeName(biomePrefix+"Glacier");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.glacier);
		}
		if(Config.highlandsbID > -1) {
			HighlandsBiomes.highlandsb = new BiomeGenHighlands(Config.highlandsbID).setBiomeName(biomePrefix+"Highlands");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.highlandsb);
		}
		if(Config.lowlandsID > -1) {
			HighlandsBiomes.lowlands = new BiomeGenLowlands(Config.lowlandsID).setBiomeName(biomePrefix+"Lowlands");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.lowlands);
		}
		if(Config.meadowID > -1) {
			HighlandsBiomes.meadow = new BiomeGenMeadow(Config.meadowID).setBiomeName(biomePrefix+"Meadow");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.meadow);
		}
		if(Config.outbackID > -1) {
			HighlandsBiomes.outback = new BiomeGenOutback(Config.outbackID).setBiomeName(biomePrefix+"Outback");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.outback);
		}
		if(Config.pinelandsID > -1) {
			HighlandsBiomes.pinelands = new BiomeGenPinelands(Config.pinelandsID).setBiomeName(biomePrefix+"Pinelands");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.pinelands);
		}
		if(Config.rainforestID > -1) {
			HighlandsBiomes.rainforest = new BiomeGenRainforest(Config.rainforestID).setBiomeName(biomePrefix+"Rainforest");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.rainforest);
		}
		if(Config.redwoodForestID > -1) {
			HighlandsBiomes.redwoodForest = new BiomeGenRedwoodForest(Config.redwoodForestID).setBiomeName(biomePrefix+"Redwood Forest");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.redwoodForest);
		}
		if(Config.rockMountainsID > -1) {
			HighlandsBiomes.rockMountains = new BiomeGenRockMountains(Config.rockMountainsID).setBiomeName(biomePrefix+"Rock Mountains");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.rockMountains);
			BiomeManager.removeSpawnBiome(HighlandsBiomes.rockMountains);
		}
		if(Config.sahelID > -1) {
			HighlandsBiomes.sahel = new BiomeGenSahel(Config.sahelID).setBiomeName(biomePrefix+"Sahel");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.sahel);
		}
		//TODO: replace savannah with new biome, cracked ground of some sort, dead trees
		if(Config.savannahID > -1) {
			HighlandsBiomes.savannah = new BiomeGenSavannah(Config.savannahID).setBiomeName(biomePrefix+"Savannah");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.savannah);
		}
		if(Config.steppeID > -1) {
			HighlandsBiomes.steppe = new BiomeGenSteppe(Config.steppeID).setBiomeName(biomePrefix+"Steppe");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.steppe);
		}
		if(Config.snowMountainsID > -1) {
			HighlandsBiomes.snowMountains = new BiomeGenSnowMountains(Config.snowMountainsID).setBiomeName(biomePrefix+"Snow Mountains");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.snowMountains);
			BiomeManager.removeSpawnBiome(HighlandsBiomes.snowMountains);
		}
		if(Config.tallPineForestID > -1) {
			HighlandsBiomes.tallPineForest = new BiomeGenTallPineForest(Config.tallPineForestID).setBiomeName(biomePrefix+"Tall Pine Forest");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tallPineForest);
		}
		if(Config.tropicsID > -1) {
			HighlandsBiomes.tropics = new BiomeGenTropics(Config.tropicsID).setBiomeName(biomePrefix+"Tropics");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tropics);
		}
		if(Config.tropicalIslandsID > -1) {
			HighlandsBiomes.tropicalIslands = new BiomeGenTropicalIslands(Config.tropicalIslandsID).setBiomeName(biomePrefix+"Tropical Islands");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tropicalIslands);
		}
		if(Config.tundraID > -1) {
			HighlandsBiomes.tundra = new BiomeGenTundra(Config.tundraID).setBiomeName(biomePrefix+"Tundra");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tundra);
		} 
		if(Config.woodlandsID > -1) {
			HighlandsBiomes.woodlands = new BiomeGenWoodlands(Config.woodlandsID).setBiomeName(biomePrefix+"Woodlands");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.woodlands);
			
		}
		if(Config.woodlandMountainsID > -1) {
			HighlandsBiomes.woodsMountains = new BiomeGenWoodsMountains(Config.woodlandMountainsID).setBiomeName(biomePrefix+"Woodland Mountains");
			HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.woodsMountains);
			BiomeManager.removeSpawnBiome(HighlandsBiomes.woodsMountains);
		}
		//TODO: Oriental Forest Biome
		/**
		 * Add a Japanese Biome and Cherry Blosom Trees.
		 * orientally themed biome
		 * Perhaps combine that with lots of water pools and source blocks and high proportion of reeds/bamboo
		 *  (could be used to produce sticks/planks/medicine).
		 *  Maybe Fir trees and shrubs + white flowers would give it a humid feel.
		 *  Grass colour should be a deep green, similar to tropics/flying mountains.
		 *  For custom ore generation, Coal and iron are common to China/Japan, so these would be best.
		*/
		//TODO: Inland Volcano Biome
		//TODO: replacement for 
		
		//improved ocean biome
		if(Config.ocean2ID > -1) {
			HighlandsBiomes.ocean2 = new BiomeGenOcean2(Config.ocean2ID).setBiomeName(biomePrefix+"Ocean");
			Highlands.improvedOceans = true;
			BiomeDictionary.registerBiomeType(HighlandsBiomes.ocean2, BiomeDictionary.Type.WATER);
			BiomeManager.removeSpawnBiome(HighlandsBiomes.ocean2);
			if (railcraftInstalled) {
				FMLInterModComms.sendMessage("Railcraft", "geode-biome", ""+Config.ocean2ID);
			}
		}
		
		//sub-biomes
		if(Config.desertIslandID > -1) {
			HighlandsBiomes.desertIsland = new BiomeGenDesertIsland(Config.desertIslandID).setBiomeName(biomePrefix+"Desert Island").setHeight(desertIsle);
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.desertIsland);
		}
		if(Config.forestIslandID > -1) {
			HighlandsBiomes.forestIsland = new BiomeGenWoodlands(Config.forestIslandID).setBiomeName(biomePrefix+"Forest Island").setHeight(woodIsle);
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.forestIsland);
		}
		if(Config.jungleIslandID > -1) {
			HighlandsBiomes.jungleIsland = new BiomeGenJungle(Config.jungleIslandID, false).setBiomeName(biomePrefix+"Jungle Island").setHeight(woodIsle).setTemperatureRainfall(1.0F, 1.2F);
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.jungleIsland);
		}
		if(Config.volcanoIslandID > -1) {
			HighlandsBiomes.volcanoIsland = new BiomeGenVolcanoIsland(Config.volcanoIslandID).setBiomeName(biomePrefix+"Volcano Island");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.volcanoIsland);
		}
		if(Config.snowIslandID > -1) {
			HighlandsBiomes.snowIsland = new BiomeGenTaiga(Config.snowIslandID, 3).setBiomeName(biomePrefix+"Snow Island").setHeight(woodIsle).setTemperatureRainfall(0.0F, 0.6F);
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.snowIsland);
		}
		if(Config.rockIslandID > -1) {
			HighlandsBiomes.rockIsland = new BiomeGenRockIsland(Config.rockIslandID).setBiomeName(biomePrefix+"Rock Island");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.rockIsland);
		}
		if(Config.windyIslandID > -1) {
			HighlandsBiomes.windyIsland = new BiomeGenOcean(Config.windyIslandID).setBiomeName(biomePrefix+"Windy Island").setHeight(windIsle);
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.windyIsland);
		}
		if(Config.lakeID > -1) {
			HighlandsBiomes.lake = new BiomeGenLake(Config.lakeID).setBiomeName(biomePrefix+"Lake");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.lake);
		}
		if(Config.baldHillID > -1) {
			HighlandsBiomes.baldHill = new BiomeGenBaldHill(Config.baldHillID).setBiomeName(biomePrefix+"Bald Hill");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.baldHill);
		}
		if(Config.mesaID > -1) {
			HighlandsBiomes.mesa = new BiomeGenMesa(Config.mesaID).setBiomeName(biomePrefix+"Mesa");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.mesa);
		}
		if(Config.valleyID > -1) {
			HighlandsBiomes.valley = new BiomeGenForest(Config.valleyID, 3).setBiomeName(biomePrefix+"Valley");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.valley);
		}
		if(Config.oasisID > -1) {
			HighlandsBiomes.oasis = new BiomeGenOasis(Config.oasisID).setBiomeName(biomePrefix+"Oasis");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.oasis);
		}
		if(Config.canyonID > -1) {
			HighlandsBiomes.canyon = new BiomeGenOutback(Config.canyonID).setBiomeName(biomePrefix+"Canyon");
			HighlandsBiomes.subBiomes.add(HighlandsBiomes.canyon);
		}
		
		//border biomes
		if(Config.shrublandID > -1) {
			HighlandsBiomes.shrubland = new BiomeGenShrubland(Config.shrublandID).setBiomeName(biomePrefix+"Shrubland");
			Highlands.borderBiomeFlag = true;
		}
		
		HighlandsBiomes.biomesForDefault = (ArrayList<BiomeGenBase>)HighlandsBiomes.biomesForHighlands.clone();
		
		//vanilla biomes
		if(Config.plainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.plains);
		if(Config.desertGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.desert);
		if(Config.extremeHillsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.extremeHills);
		if(Config.forestGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.forest);
		if(Config.swamplandGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.swampland);
		if(Config.jungleGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.jungle);
		if(Config.icePlainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.icePlains);
		if(Config.taigaGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.taiga);
	}
	
	
	
	public static void initRecipes(){
		
		//SAPLINGS
		
		//Fir Sapling
		if(HighlandsBlocks.firSapling != null){
			registerBlock(HighlandsBlocks.firSapling, ItemBlockMetadata.class, "Fir Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.firSapling),
					new ItemStack(Blocks.sapling, 1, 1), new ItemStack(Blocks.log, 1, 1));
		}
		//Acacia Sapling
		if(HighlandsBlocks.acaciaSapling != null){
			registerBlock(HighlandsBlocks.acaciaSapling, "Acacia Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.acaciaSapling),
					new ItemStack(Blocks.sapling, 1, 3), new ItemStack(Blocks.log, 1, 0));
		}
		//Poplar Sapling
		if(HighlandsBlocks.poplarSapling != null){
			registerBlock(HighlandsBlocks.poplarSapling, "Poplar Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.poplarSapling),
					new ItemStack(Blocks.sapling, 1, 2), new ItemStack(Blocks.log, 1, 0));
		}
		//Sequoia Sapling
		if(HighlandsBlocks.redwoodSapling != null && HighlandsBlocks.firSapling != null){
			registerBlock(HighlandsBlocks.redwoodSapling, "Redwood Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.redwoodSapling),
					new ItemStack(Items.dye, 1, 15), new ItemStack(HighlandsBlocks.firSapling, 1, 0), new ItemStack(Blocks.log, 1, 0));
		}
		//Canopy Tree Sapling
		if(HighlandsBlocks.canopySapling != null){
			registerBlock(HighlandsBlocks.canopySapling, "Canopy Tree Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.canopySapling),
					new ItemStack(Blocks.sapling, 1, 3), new ItemStack(Blocks.log, 1, 3));
		}
		//Great Oak Sapling
		if(HighlandsBlocks.greatOakSapling != null){
			registerBlock(HighlandsBlocks.greatOakSapling, "Great Oak Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.greatOakSapling),
					new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Blocks.log, 1, 0));
		}
		//Beech Sapling
		if(HighlandsBlocks.beechSapling != null){
			registerBlock(HighlandsBlocks.beechSapling, "Beech Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.beechSapling),
					new ItemStack(Blocks.sapling, 1, 2), new ItemStack(Blocks.log, 1, 2));
		}
		//Evergreen Bush Sapling
		if(HighlandsBlocks.evergreenBushSapling != null){
			registerBlock(HighlandsBlocks.evergreenBushSapling, "Evergreen Bushling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.evergreenBushSapling, 2, 0),
					new ItemStack(Blocks.sapling, 1, 1), new ItemStack(Items.stick));
		}
		//Deciduous Bush Sapling
		if(HighlandsBlocks.deciduousBushSapling != null){
			registerBlock(HighlandsBlocks.deciduousBushSapling, "Deciduous Bushling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.deciduousBushSapling, 2, 0),
					new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Items.stick));
		}
		//Palm Sapling
		if(HighlandsBlocks.palmSapling != null){
			registerBlock(HighlandsBlocks.palmSapling, "Palm Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.palmSapling),
					new ItemStack(Blocks.sapling, 1, 3), new ItemStack(Blocks.log, 1, 2));
		}
		//Dead Tree Sapling
		if(HighlandsBlocks.deadSapling != null){
			registerBlock(HighlandsBlocks.deadSapling, "Dead Tree Sapling");
		}
		//Ironwood Sapling
		if(HighlandsBlocks.ironwoodSapling != null && HighlandsBlocks.beechSapling != null && HighlandsBlocks.greatOakSapling != null
				&& HighlandsBlocks.redwoodSapling != null && HighlandsBlocks.canopySapling != null){
			registerBlock(HighlandsBlocks.ironwoodSapling, "Ironwood Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.ironwoodSapling),
					new ItemStack(HighlandsBlocks.redwoodSapling, 1, 0), new ItemStack(HighlandsBlocks.greatOakSapling, 1, 0),
					new ItemStack(HighlandsBlocks.canopySapling, 1, 0), new ItemStack(HighlandsBlocks.beechSapling, 1, 0), new ItemStack(Blocks.iron_block));
		}
		//Mangrove Sapling
		if(HighlandsBlocks.mangroveSapling != null){
			registerBlock(HighlandsBlocks.mangroveSapling, "Mangrove Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.mangroveSapling),
					new ItemStack(Blocks.sapling, 1, 2), new ItemStack(Blocks.log, 1, 3));
		}
		//Ash Sapling
		if(HighlandsBlocks.ashSapling != null){
			registerBlock(HighlandsBlocks.ashSapling, "Ash Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.ashSapling),
					new ItemStack(HighlandsBlocks.greatOakSapling, 1, 0), new ItemStack(Blocks.log, 1, 3));
		}
		//Orange Sapling
		if(HighlandsBlocks.autumnOrangeSapling != null){
			registerBlock(HighlandsBlocks.autumnOrangeSapling, "Orange Autumn Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.autumnOrangeSapling),
					new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Items.dye, 1, 14));
		}
		//Yellow Sapling
		if(HighlandsBlocks.autumnYellowSapling != null){
			registerBlock(HighlandsBlocks.autumnYellowSapling, "Yellow Autumn Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.autumnYellowSapling), new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Items.dye, 1, 11));
		}
		//Japanese Maple Sapling
		if (HighlandsBlocks.japaneseMapleSapling != null) {
			registerBlock(HighlandsBlocks.japaneseMapleSapling, "Japanese Maple Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.japaneseMapleSapling), new ItemStack(Blocks.sapling, 1, 0), new ItemStack(Items.dye, 1, 15));
		}
		
		
		//WOODS
		
		//Fir Log
		if(HighlandsBlocks.firWood != null){
			registerBlock(HighlandsBlocks.firWood, "Fir Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.firWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Blocks.planks, 4, 1), new ItemStack(HighlandsBlocks.firWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.firWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Acacia Log
		if(HighlandsBlocks.acaciaWood != null){
			registerBlock(HighlandsBlocks.acaciaWood, "Acacia Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.acaciaWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 0), new ItemStack(HighlandsBlocks.acaciaWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.acaciaWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Poplar Log
		if(HighlandsBlocks.poplarWood != null){
			registerBlock(HighlandsBlocks.poplarWood, "Poplar Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.poplarWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 1), new ItemStack(HighlandsBlocks.poplarWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.poplarWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Sequoia Log
		if(HighlandsBlocks.redwoodWood != null){
			registerBlock(HighlandsBlocks.redwoodWood, "Redwood Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.redwoodWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 2), new ItemStack(HighlandsBlocks.redwoodWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.redwoodWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Canopy Tree Log
		if(HighlandsBlocks.canopyWood != null){
			registerBlock(HighlandsBlocks.canopyWood, "Canopy Tree Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.canopyWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Blocks.planks, 4, 2), new ItemStack(HighlandsBlocks.canopyWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.canopyWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Mangrove Log
		if(HighlandsBlocks.mangroveWood != null){
			registerBlock(HighlandsBlocks.mangroveWood, "Mangrove Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.mangroveWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 3), new ItemStack(HighlandsBlocks.mangroveWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.mangroveWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Ash Log
		if(HighlandsBlocks.ashWood != null){
			registerBlock(HighlandsBlocks.ashWood, "Ash Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.ashWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Blocks.planks, 4, 0), new ItemStack(HighlandsBlocks.ashWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.ashWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Palm Log
		if(HighlandsBlocks.palmWood != null){
			registerBlock(HighlandsBlocks.palmWood, "Palm Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.palmWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Blocks.planks, 4, 0), new ItemStack(HighlandsBlocks.palmWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.palmWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		//Ironwood Log
		if(HighlandsBlocks.ironWood != null){
			registerBlock(HighlandsBlocks.ironWood, "Ironwood Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.ironWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 3), new ItemStack(HighlandsBlocks.ironWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.ironWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		
		//Japanese Maple Log
		if(HighlandsBlocks.japaneseMapleWood != null){
			registerBlock(HighlandsBlocks.japaneseMapleWood, "Japanese Maple Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.japaneseMapleWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 3), new ItemStack(HighlandsBlocks.japaneseMapleWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.japaneseMapleWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		}
		
		//Highlands Planks
		if(HighlandsBlocks.hlplanks != null){
			OreDictionary.registerOre("plankWood", new ItemStack(HighlandsBlocks.hlplanks, 1, OreDictionary.WILDCARD_VALUE));
		}
		
		//Planks - stairs
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs0 != null){
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs0, 4), new Object[] { "B  ", "BB ", "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 0)});
		}
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs1 != null){
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs1, 4), new Object[] { "B  ", "BB ", "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 1)});
		}
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs2 != null){
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs2, 4), new Object[] { "B  ", "BB ", "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 2)});
		}
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs3 != null){
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs3, 4), new Object[] { "B  ", "BB ", "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 3)});
		}

		
		//Planks - half blocks
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankhalf != null){
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 0), new Object[] { "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 0)});
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 1), new Object[] { "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 1)});
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 2), new Object[] { "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 2)});
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 3), new Object[] { "BBB", Character.valueOf('B'), new ItemStack(HighlandsBlocks.hlplanks,1, 3)});
			
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 0), new Object[] { "P", "P", Character.valueOf('P'), new ItemStack(HighlandsBlocks.hlplankhalf,1, 0)});
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 1), new Object[] { "P", "P", Character.valueOf('P'), new ItemStack(HighlandsBlocks.hlplankhalf,1, 1)});
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 2), new Object[] { "P", "P", Character.valueOf('P'), new ItemStack(HighlandsBlocks.hlplankhalf,1, 2)});
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 3), new Object[] { "P", "P", Character.valueOf('P'), new ItemStack(HighlandsBlocks.hlplankhalf,1, 3)});
		}
		//Planks - double half blocks
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankhalfdouble != null){
			//GameRegistry.registerBlock(HighlandsBlocks.hlplankhalfdouble, ItemBlockMetadata.class, "hl_woodDoubleSlab");
		}
		
		
		
		//LEAVES
		
		//Fir Leaves
		if(HighlandsBlocks.firLeaves != null){
			registerBlock(HighlandsBlocks.firLeaves, "Fir Leaves");
		}
		//Acacia Leaves
		if(HighlandsBlocks.acaciaLeaves != null){
			registerBlock(HighlandsBlocks.acaciaLeaves, "Acacia Leaves");
		}
		//Poplar Leaves
		if(HighlandsBlocks.poplarLeaves != null){
			registerBlock(HighlandsBlocks.poplarLeaves, "Poplar Leaves");
		}
		//Sequoia Leaves
		if(HighlandsBlocks.redwoodLeaves != null && HighlandsBlocks.firLeaves != null){
			registerBlock(HighlandsBlocks.redwoodLeaves, "Redwood Leaves");
		}
		//Canopy Tree Leaves
		if(HighlandsBlocks.canopyLeaves != null){
			registerBlock(HighlandsBlocks.canopyLeaves, "Canopy Tree Leaves");
		}
		//Ironwood Leaves
		if(HighlandsBlocks.ironwoodLeaves != null){
			registerBlock(HighlandsBlocks.ironwoodLeaves, "Ironwood Leaves");
		}
		//Mangrove Leaves
		if(HighlandsBlocks.mangroveLeaves != null){
			registerBlock(HighlandsBlocks.mangroveLeaves, "Mangrove Leaves");
		}
		//Ash Leaves
		if(HighlandsBlocks.ashLeaves != null){
			registerBlock(HighlandsBlocks.ashLeaves, "Ash Leaves");
		}
		//Palm Leaves
		if(HighlandsBlocks.palmLeaves != null){
			registerBlock(HighlandsBlocks.palmLeaves, "Palm Leaves");
		}
		//Yellow Leaves
		if(HighlandsBlocks.autumnOrangeLeaves != null){
			registerBlock(HighlandsBlocks.autumnOrangeLeaves, "Yellow Autumn Leaves");
		}
		//Orange Leaves
		if(HighlandsBlocks.autumnYellowLeaves != null){
			registerBlock(HighlandsBlocks.autumnYellowLeaves, "Orange Autumn Leaves");
		}
		//Japanese Maple Leaves
		if (HighlandsBlocks.japaneseMapleLeaves != null){
			registerBlock(HighlandsBlocks.japaneseMapleLeaves, "Japanese Maple Leaves");
		}
		
		//PLANTS
		
		//Blue Flower
		if(HighlandsBlocks.blueFlower != null){
			registerBlock(HighlandsBlocks.blueFlower, "Blue Flower");
			GameRegistry.addShapelessRecipe(new ItemStack (Items.dye, 1, 6), new ItemStack(HighlandsBlocks.blueFlower, 1, 0));
		}
		if(HighlandsBlocks.leafyFern != null){
			registerBlock(HighlandsBlocks.leafyFern, "Leafy Fern");
			GameRegistry.addShapelessRecipe(new ItemStack (Items.dye, 1, 10), new ItemStack(HighlandsBlocks.leafyFern, 1, 0));
		}
		if(HighlandsBlocks.whiteFlower != null){
			registerBlock(HighlandsBlocks.whiteFlower, "White Flower");
			GameRegistry.addShapelessRecipe(new ItemStack (Items.dye, 1, 7), new ItemStack(HighlandsBlocks.whiteFlower, 1, 0));
		}
		if(HighlandsBlocks.cattail != null){
			registerBlock(HighlandsBlocks.cattail, "Cattail");
			GameRegistry.addShapelessRecipe(new ItemStack (Items.paper, 1, 0), new ItemStack(HighlandsBlocks.cattail, 1, 0));
		}
		if(HighlandsBlocks.lavender != null){
			registerBlock(HighlandsBlocks.lavender, "Lavender");
			GameRegistry.addShapelessRecipe(new ItemStack (Items.dye, 1, 5), new ItemStack(HighlandsBlocks.lavender, 1, 0));
		}
		if(HighlandsBlocks.raspberryBush != null){
			registerBlock(HighlandsBlocks.raspberryBush, "Raspberry Bush");
			if(HighlandsBlocks.berries != null)
				GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.berries, 1, 0), new ItemStack(HighlandsBlocks.raspberryBush, 1, 0));
		}
		if(HighlandsBlocks.blueberryBush != null){
			registerBlock(HighlandsBlocks.blueberryBush, "Blueberry Bush");
			if(HighlandsBlocks.berries != null)
				GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.berries, 1, 0), new ItemStack(HighlandsBlocks.blueberryBush, 1, 0));
		}
		if(HighlandsBlocks.thornbush != null){
			registerBlock(HighlandsBlocks.thornbush, "Thornbush");
			GameRegistry.addShapelessRecipe(new ItemStack (Items.stick, 2, 0), new ItemStack(HighlandsBlocks.thornbush, 1, 0));
		}
		if(HighlandsBlocks.cotton != null){
			registerBlock(HighlandsBlocks.cotton, "Cotton Plant");
			GameRegistry.addShapelessRecipe(new ItemStack (Items.string, 1, 0), new ItemStack(HighlandsBlocks.cotton, 1, 0));
		}
		
	}
	
	public static void registerBlock(Block b, String name){
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}
    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, String name)
    {
        GameRegistry.registerBlock(block, itemBlockClass, name);
    }
	
	//sets up sub-biome lists after all biomes are initialized.
	public static void setUpAllSubBiomes(){
		ArrayList<BiomeGenBase> enabledBiomes = new ArrayList<BiomeGenBase>();
		for(BiomeGenBase b : HighlandsBiomes.biomesForHighlands)enabledBiomes.add(b);
		for(BiomeGenBase b : HighlandsBiomes.subBiomes)enabledBiomes.add(b);
		
		
		addSubBiome(HighlandsBiomes.alps, HighlandsBiomes.tallPineForest, enabledBiomes);
		addSubBiome(HighlandsBiomes.alps, HighlandsBiomes.glacier, enabledBiomes);
		addSubBiome(HighlandsBiomes.alps, BiomeGenBase.frozenRiver, enabledBiomes);
		addSubBiome(HighlandsBiomes.tropicalIslands, HighlandsBiomes.volcanoIsland, enabledBiomes);
		for(int i = 0; i < 3; i++){
			addSubBiome(HighlandsBiomes.tropicalIslands, HighlandsBiomes.tropicalIslands, enabledBiomes);
		}
		addSubBiome(HighlandsBiomes.autumnForest, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.autumnForest, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.birchHills, HighlandsBiomes.meadow, enabledBiomes);
		addSubBiome(HighlandsBiomes.cliffs, HighlandsBiomes.valley, enabledBiomes);
		addSubBiome(HighlandsBiomes.dunes, HighlandsBiomes.oasis, enabledBiomes);
		addSubBiome(HighlandsBiomes.estuary, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.meadow, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.meadow, HighlandsBiomes.birchHills, enabledBiomes);
		addSubBiome(HighlandsBiomes.woodlands, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.woodlands, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.woodlands, BiomeGenBase.plains, enabledBiomes);
		addSubBiome(HighlandsBiomes.highlandsb, HighlandsBiomes.woodlands, enabledBiomes);
		addSubBiome(HighlandsBiomes.lowlands, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.lowlands, HighlandsBiomes.lake, enabledBiomes);
		for(int i = 0; i < 3; i++){
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.forestIsland, enabledBiomes);
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.jungleIsland, enabledBiomes);
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.desertIsland, enabledBiomes);
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.windyIsland, enabledBiomes);
		}
		for(int i = 0; i < Highlands.islandRarity; i++){
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.ocean2, enabledBiomes);
		}
		addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.snowIsland, enabledBiomes);
		addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.volcanoIsland, enabledBiomes);
		addSubBiome(HighlandsBiomes.outback, HighlandsBiomes.mesa, enabledBiomes);
		addSubBiome(HighlandsBiomes.outback, BiomeGenBase.desert, enabledBiomes);
		addSubBiome(HighlandsBiomes.pinelands, HighlandsBiomes.autumnForest, enabledBiomes);
		addSubBiome(HighlandsBiomes.redwoodForest, HighlandsBiomes.highlandsb, enabledBiomes);
		addSubBiome(HighlandsBiomes.redwoodForest, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.sahel, HighlandsBiomes.mesa, enabledBiomes);
		addSubBiome(HighlandsBiomes.sahel, BiomeGenBase.desert, enabledBiomes);
		addSubBiome(HighlandsBiomes.sahel, HighlandsBiomes.savannah, enabledBiomes);
		addSubBiome(HighlandsBiomes.savannah, HighlandsBiomes.mesa, enabledBiomes);
		addSubBiome(HighlandsBiomes.steppe, HighlandsBiomes.canyon, enabledBiomes);
		addSubBiome(HighlandsBiomes.tallPineForest, HighlandsBiomes.alps, enabledBiomes);
		addSubBiome(HighlandsBiomes.tallPineForest, BiomeGenBase.frozenRiver, enabledBiomes);
		addSubBiome(HighlandsBiomes.rainforest, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.rainforest, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.tropics, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.tundra, HighlandsBiomes.alps, enabledBiomes);
		addSubBiome(HighlandsBiomes.tundra, HighlandsBiomes.tallPineForest, enabledBiomes);
	}
	
	public static void addSubBiome(BiomeGenBase parent, BiomeGenBase sub, ArrayList<BiomeGenBase> list){
		if(parent != null && sub != null && list.contains(parent) && list.contains(sub)){
			((BiomeGenBaseHighlands)parent).subBiomes.add(sub);
		}
	}
	
	public static void setFireProperties(Block block, int encouragement, int flammability) {
		if (block != null)
		Blocks.fire.setFireInfo(block, encouragement, flammability);
	}

}