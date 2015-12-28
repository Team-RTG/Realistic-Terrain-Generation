package enhancedbiomes.blocks;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EnhancedBiomesApi;
import enhancedbiomes.api.internal.OreGenEntry;
import enhancedbiomes.blocks.renderer.BlockGrassRenderer;
import enhancedbiomes.blocks.renderer.BlockOreRenderer;
import enhancedbiomes.blocks.renderer.BlockRedstoneOreRenderer;
import enhancedbiomes.blocks.tileentity.TileEntitySaguaro;
import enhancedbiomes.blocks.tileentity.TileEntityTide;
import enhancedbiomes.creativetab.CreativeTabEnhancedBiomes;
import enhancedbiomes.handlers.FuelHandler;
import enhancedbiomes.helpers.EnhancedBiomesJava;
import enhancedbiomes.items.ItemBlockEnhancedBiomes;
import enhancedbiomes.items.ItemBlockLogBirch;
import enhancedbiomes.items.ItemBlockLogJungle;
import enhancedbiomes.items.ItemBlockLogOak;
import enhancedbiomes.items.ItemBlockLogSpruce;
import enhancedbiomes.items.ItemSlab1;
import enhancedbiomes.items.ItemSlab2;
import enhancedbiomes.items.ItemSlabS;
import enhancedbiomes.items.ItemSlabS2;
import enhancedbiomes.items.ItemSlabSB;
import enhancedbiomes.items.ItemSlabSB2;
import enhancedbiomes.items.ItemSlabSC;
import enhancedbiomes.items.ItemSlabSC2;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.LoaderState;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import static net.minecraft.block.Block.*;
import static net.minecraft.init.Items.*;
import static net.minecraft.init.Blocks.*;

public class EnhancedBiomesBlocks {	
	public static Block logOak;
	public static Block logSpruce;
	public static Block logBirch;
	public static Block logJungle;
	
	public static Block planksEB;
	
	public static BlockSlab slab1;
	public static BlockSlab slab2;	
	public static BlockSlab doubleSlab1;
	public static BlockSlab doubleSlab2;
	public static Block[] stairsEB = new Block[16];
	
	public static BlockLeavesOak leavesOak;
	public static BlockLeavesSpruce leavesSpruce;
	public static BlockLeavesBirch leavesBirch;
	public static BlockLeavesJungle leavesJungle;

	public static BlockSaplingEnhancedBiomes saplingEB;
		
	public static Block stoneEB;
	public static Block stoneCobbleEB;
	public static Block stoneBrickEB;
	
	public static Block oreGoldEB;
	public static Block oreIronEB;
	public static Block oreCoalEB;
	public static Block oreLapisEB;
	public static Block oreRedstoneEB;
	public static Block oreRedstoneLitEB;
	public static Block oreDiamondEB;
	public static Block oreEmeraldEB;
	
	public static BlockSlab slabS;
	public static BlockSlab slabSB;	
	public static BlockSlab slabSC;	
	public static BlockSlab doubleSlabS;
	public static BlockSlab doubleSlabSB;
	public static BlockSlab doubleSlabSC;
	public static Block[] stairsSEB = new Block[EnhancedBiomesJava.createList(BlockStoneEB.stones, BlockStoneEB.stones2).length * 2];
	
	public static BlockSlab slabS2;
	public static BlockSlab slabSB2;	
	public static BlockSlab slabSC2;	
	public static BlockSlab doubleSlabS2;
	public static BlockSlab doubleSlabSB2;
	public static BlockSlab doubleSlabSC2;

	public static BlockSoilEB dirtEB;
	public static BlockGrassEB grassEB;
	public static BlockFarmlandEB[] farmlandEB = new BlockFarmlandEB[BlockSoilEB.soils.length];

	public static BlockSaguaro saguaro;
	public static BlockSaguaroSapling saguaroSapling;

	public static Block shoji;
	public static BlockShojiLamp shojiLamp;
	
	/**
	 * Testing purposes only, should be removed for release
	 */
	@Deprecated
	public static Block util;
	
	/**
	 * Try not to use unless necessary due to lack of mod support
	 */
	public static boolean isGrass(Block block)
	{
		return block == grass || block == grassEB;
	}

	public static CreativeTabs tabEnhancedBiomesOrganic;
	public static CreativeTabs tabEnhancedBiomesMineral;
	
	public static String[] woodNames = new String[]{"Great Oak", "Thorntree", "Poplar", "Mangrove", 
													"Fir", "Cypress", "Pine", "Silver Pine", 
													"Alder", "Eucalyptus", "Aspen", "", 
													"Baobab", "Dead", "Cherry", ""};
	
	public static void load() {
		RenderingRegistry.registerBlockHandler(new BlockOreRenderer());
		RenderingRegistry.registerBlockHandler(new BlockRedstoneOreRenderer());
		
		tabEnhancedBiomesOrganic = new CreativeTabEnhancedBiomes(CreativeTabs.getNextID(), "enhancedBiomesOrganic", 0);
		tabEnhancedBiomesMineral = new CreativeTabEnhancedBiomes(CreativeTabs.getNextID(), "enhancedBiomesMineral", 1);
		
		logOak = new BlockLogOak().setHardness(2.0F).setStepSound(soundTypeWood).setBlockName("logOakEB").setCreativeTab(tabEnhancedBiomesOrganic);
		logSpruce = new BlockLogSpruce().setHardness(2.0F).setStepSound(soundTypeWood).setBlockName("logSpruceEB").setCreativeTab(tabEnhancedBiomesOrganic);	 
		logBirch = new BlockLogBirch().setHardness(2.0F).setStepSound(soundTypeWood).setBlockName("logBirchEB").setCreativeTab(tabEnhancedBiomesOrganic);	 
		logJungle = new BlockLogJungle().setHardness(2.0F).setStepSound(soundTypeWood).setBlockName("logJungleEB").setCreativeTab(tabEnhancedBiomesOrganic);	 
		
		planksEB = new BlockPlanksEB().setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("planksEB").setCreativeTab(tabEnhancedBiomesOrganic);	 
		
		slab1 = (BlockSlab) new BlockHalfSlabEB(false).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("slab1EB").setCreativeTab(tabEnhancedBiomesOrganic);
		slab2 = (BlockSlab) new BlockHalfSlabEB(false).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("slab2EB").setCreativeTab(tabEnhancedBiomesOrganic);
		
		doubleSlab1 = (BlockSlab) new BlockHalfSlabEB(true).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("doubleSlab1EB").setCreativeTab(tabEnhancedBiomesOrganic);
		doubleSlab2 = (BlockSlab) new BlockHalfSlabEB(true).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setBlockName("doubleSlab2EB").setCreativeTab(tabEnhancedBiomesOrganic);

        for(int x = 0; x < stairsEB.length; x++)
		{
			if(woodNames[x] != "")
			{
				stairsEB[x] = new BlockStairsEB(planksEB, x, true).setBlockName("stairsWoodEB" + x).setCreativeTab(tabEnhancedBiomesOrganic);
			}
		}
		
		leavesOak = (BlockLeavesOak)(new BlockLeavesOak()).setHardness(0.2F).setLightOpacity(1).setStepSound(soundTypeGrass).setBlockName("leavesOakEB").setCreativeTab(tabEnhancedBiomesOrganic);
		leavesSpruce = (BlockLeavesSpruce)(new BlockLeavesSpruce()).setHardness(0.2F).setLightOpacity(1).setStepSound(soundTypeGrass).setBlockName("leavesSpruceEB").setCreativeTab(tabEnhancedBiomesOrganic);
		leavesBirch = (BlockLeavesBirch)(new BlockLeavesBirch()).setHardness(0.2F).setLightOpacity(1).setStepSound(soundTypeGrass).setBlockName("leavesBirchEB").setCreativeTab(tabEnhancedBiomesOrganic);
		leavesJungle = (BlockLeavesJungle)(new BlockLeavesJungle()).setHardness(0.2F).setLightOpacity(1).setStepSound(soundTypeGrass).setBlockName("leavesJungleEB").setCreativeTab(tabEnhancedBiomesOrganic);
		
		saplingEB = (BlockSaplingEnhancedBiomes) new BlockSaplingEnhancedBiomes().setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("saplingEB").setBlockTextureName("saplingEB").setCreativeTab(tabEnhancedBiomesOrganic);
		
		stoneEB = (new BlockStoneEB()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypeStone).setBlockName("stoneEB").setBlockTextureName("stone").setCreativeTab(tabEnhancedBiomesMineral);
		stoneCobbleEB = (new BlockStoneBrickEB()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypeStone).setBlockName("stoneCobbleEB").setBlockTextureName("stoneCobble").setCreativeTab(tabEnhancedBiomesMineral);
		stoneBrickEB = (new BlockStoneBrickEB()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypeStone).setBlockName("stoneBrickEB").setBlockTextureName("stoneBrick").setCreativeTab(tabEnhancedBiomesMineral);

	    oreGoldEB = (new BlockOreEB()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreGoldEB").setBlockTextureName("gold_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    oreIronEB = (new BlockOreEB()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreIronEB").setBlockTextureName("iron_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    oreCoalEB = (new BlockOreEB()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreCoalEB").setBlockTextureName("coal_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    oreLapisEB = (new BlockOreEB()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreLapisEB").setBlockTextureName("lapis_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    oreRedstoneEB = (new BlockRedstoneOreEB(false)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreRedstoneEB").setBlockTextureName("redstone_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    oreRedstoneLitEB = (new BlockRedstoneOreEB(true)).setLightLevel(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreRedstoneLitEB").setBlockTextureName("redstone_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    oreDiamondEB = (new BlockOreEB()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreDiamondEB").setBlockTextureName("diamond_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    oreEmeraldEB = (new BlockOreEB()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setBlockName("oreEmeraldEB").setBlockTextureName("emerald_ore").setCreativeTab(tabEnhancedBiomesMineral);
	    //TODO Remove for release
	    /*EnhancedBiomesApi.createOre("enhancedbiomes", "oreTest", "test_ore", Blocks.redstone_block, 1, false, Items.redstone, 0, 2, 5, 1, 3, 0, 128, 30, 16);
	    EnhancedBiomesApi.createOre("enhancedbiomes", "oreTest2", "test2_ore", Blocks.glowstone, 1, false, Items.glowstone_dust, 0, 2, 5, 1, 3, 0, 128, 30, 16);*/
		
	    slabS = (BlockSlab) new BlockHalfSlabSEB(false).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("slabSEB").setCreativeTab(tabEnhancedBiomesMineral);
	    slabSB = (BlockSlab) new BlockHalfSlabSEB(false).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("slabSEBB").setCreativeTab(tabEnhancedBiomesMineral);
	    slabSC = (BlockSlab) new BlockHalfSlabSEB(false).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("slabSCEB").setCreativeTab(tabEnhancedBiomesMineral);
		
	    doubleSlabS = (BlockSlab) new BlockHalfSlabSEB(true).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("doubleSlabSEB").setCreativeTab(tabEnhancedBiomesMineral);
	    doubleSlabSB = (BlockSlab) new BlockHalfSlabSEB(true).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("doubleSlabSEBB").setCreativeTab(tabEnhancedBiomesMineral);
	    doubleSlabSC = (BlockSlab) new BlockHalfSlabSEB(true).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("doubleSlabSCEB").setCreativeTab(tabEnhancedBiomesMineral);
		
	    slabS2 = (BlockSlab) new BlockHalfSlabSEB2(false).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("slabSEB2").setCreativeTab(tabEnhancedBiomesMineral);
	    slabSB2 = (BlockSlab) new BlockHalfSlabSEB2(false).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("slabSEBB2").setCreativeTab(tabEnhancedBiomesMineral);
	    slabSC2 = (BlockSlab) new BlockHalfSlabSEB2(false).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("slabSCEB2").setCreativeTab(tabEnhancedBiomesMineral);
		
	    doubleSlabS2 = (BlockSlab) new BlockHalfSlabSEB2(true).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("doubleSlabSEB2").setCreativeTab(tabEnhancedBiomesMineral);
	    doubleSlabSB2 = (BlockSlab) new BlockHalfSlabSEB2(true).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("doubleSlabSEBB2").setCreativeTab(tabEnhancedBiomesMineral);
	    doubleSlabSC2 = (BlockSlab) new BlockHalfSlabSEB2(true).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setBlockName("doubleSlabSCEB2").setCreativeTab(tabEnhancedBiomesMineral);
		
	    for(int x = 0; x < stairsSEB.length / 2; x++) stairsSEB[x] = new BlockStairsEB(stoneCobbleEB, x, false).setBlockName("stairsSCEB" + x).setCreativeTab(tabEnhancedBiomesMineral);
		for(int x = 0; x < stairsSEB.length / 2; x++) stairsSEB[x + (stairsSEB.length / 2)] = new BlockStairsEB(stoneBrickEB, x, false).setBlockName("stairsSEBB" + x).setCreativeTab(tabEnhancedBiomesMineral);
		
		dirtEB = (BlockSoilEB) new BlockSoilEB().setHardness(0.5F).setStepSound(soundTypeGravel).setBlockName("dirtEB").setCreativeTab(tabEnhancedBiomesOrganic);
	    grassEB = (BlockGrassEB) new BlockGrassEB().setHardness(0.6F).setStepSound(soundTypeGrass).setBlockName("grassEB").setCreativeTab(tabEnhancedBiomesOrganic);
	    for(int x = 0; x < BlockSoilEB.soils.length; x++) farmlandEB[x] = (BlockFarmlandEB) new BlockFarmlandEB(x).setHardness(0.6F).setStepSound(soundTypeGravel).setBlockName("farmlandEB" + x).setCreativeTab(null);
	    
	    saguaro = (BlockSaguaro) new BlockSaguaro().setHardness(0.4F).setStepSound(soundTypeCloth).setBlockName("saguaro").setCreativeTab(null);
	    saguaroSapling = (BlockSaguaroSapling) new BlockSaguaroSapling().setHardness(0.0F).setStepSound(soundTypeGrass).setBlockName("saguaroSapling").setCreativeTab(tabEnhancedBiomesOrganic);
		GameRegistry.registerTileEntity(TileEntitySaguaro.class, "saguaroEB");
	    
	    shoji = (new BlockShoji("enhancedbiomes:shoji", "enhancedbiomes:shoji", Material.cloth, false)).setHardness(0.2F).setStepSound(soundTypeCloth).setBlockName("shoji").setCreativeTab(tabEnhancedBiomesOrganic);
	    shojiLamp = (BlockShojiLamp) new BlockShojiLamp().setHardness(0.5F).setLightLevel(0.9375F).setStepSound(soundTypeCloth).setBlockName("shojiLamp").setCreativeTab(tabEnhancedBiomesOrganic);
	    
	    //TODO Remove for release
		//util = new BlockUtil().setBlockName("util"); inputBlock(util);
		
		inputWoodBlock(logOak, 		ItemBlockLogOak.class);			Blocks.fire.setFireInfo(logOak, 5, 5);
		inputWoodBlock(logSpruce, 	ItemBlockLogSpruce.class);		Blocks.fire.setFireInfo(logSpruce, 5, 5);
		inputWoodBlock(logBirch, 	ItemBlockLogBirch.class);		Blocks.fire.setFireInfo(logBirch, 5, 5);
		inputWoodBlock(logJungle, 	ItemBlockLogJungle.class);		Blocks.fire.setFireInfo(logJungle, 5, 5);
		
		inputMetaBlock(planksEB);									Blocks.fire.setFireInfo(planksEB, 5, 20);
		OreDictionary.registerOre("plankWood", new ItemStack(planksEB, 1, OreDictionary.WILDCARD_VALUE));

		inputMetaBlock(leavesOak);									Blocks.fire.setFireInfo(leavesOak, 30, 60);
		OreDictionary.registerOre("treeLeaves", new ItemStack(leavesOak, 1, OreDictionary.WILDCARD_VALUE));

		inputMetaBlock(leavesSpruce);								Blocks.fire.setFireInfo(leavesSpruce, 30, 60);
		OreDictionary.registerOre("treeLeaves", new ItemStack(leavesSpruce, 1, OreDictionary.WILDCARD_VALUE));

		inputMetaBlock(leavesBirch);								Blocks.fire.setFireInfo(leavesBirch, 30, 60);
		OreDictionary.registerOre("treeLeaves", new ItemStack(leavesBirch, 1, OreDictionary.WILDCARD_VALUE));

		inputMetaBlock(leavesJungle);								Blocks.fire.setFireInfo(leavesJungle, 30, 60);
		OreDictionary.registerOre("treeLeaves", new ItemStack(leavesJungle, 1, OreDictionary.WILDCARD_VALUE));

		inputSlabBlocks(slab1, doubleSlab1, ItemSlab1.class);		Blocks.fire.setFireInfo(slab1, 5, 20);		Blocks.fire.setFireInfo(doubleSlab1, 5, 20);
		OreDictionary.registerOre("slabWood", new ItemStack(slab1, 1, OreDictionary.WILDCARD_VALUE));
		
		inputSlabBlocks(slab2, doubleSlab2, ItemSlab2.class);		Blocks.fire.setFireInfo(slab2, 5, 20);		Blocks.fire.setFireInfo(doubleSlab2, 5, 20);
		OreDictionary.registerOre("slabWood", new ItemStack(slab2, 1, OreDictionary.WILDCARD_VALUE));
		
		inputBlockList(stairsEB);
		for(int a = 0; a < stairsEB.length; a++) {
			if(stairsEB[a] != null) {
				Blocks.fire.setFireInfo(stairsEB[a], 5, 20);
				OreDictionary.registerOre("stairWood", new ItemStack(stairsEB[a], 1, OreDictionary.WILDCARD_VALUE));
			}
		}
		
		inputMetaBlock(saplingEB);
		OreDictionary.registerOre("treeSapling", new ItemStack(saplingEB, 1, OreDictionary.WILDCARD_VALUE));

		inputMetaBlock(stoneEB);
		OreDictionary.registerOre("stone", new ItemStack(stoneEB, 1, OreDictionary.WILDCARD_VALUE));
		stoneEB.setHarvestLevel("pickaxe", 0);
		
		inputMetaBlock(stoneBrickEB);
		stoneBrickEB.setHarvestLevel("pickaxe", 0);
		
		inputMetaBlock(stoneCobbleEB);
		OreDictionary.registerOre("cobblestone", new ItemStack(stoneCobbleEB, 1, OreDictionary.WILDCARD_VALUE));
		stoneCobbleEB.setHarvestLevel("pickaxe", 0);
		
		inputMetaBlock(oreGoldEB);
		OreDictionary.registerOre("oreGold", new ItemStack(oreGoldEB, 1, OreDictionary.WILDCARD_VALUE));
		oreGoldEB.setHarvestLevel("pickaxe", 2);
		
		inputMetaBlock(oreIronEB);
		OreDictionary.registerOre("oreIron", new ItemStack(oreIronEB, 1, OreDictionary.WILDCARD_VALUE));
		oreIronEB.setHarvestLevel("pickaxe", 1);
		
		inputMetaBlock(oreCoalEB);
		OreDictionary.registerOre("oreCoal", new ItemStack(oreCoalEB, 1, OreDictionary.WILDCARD_VALUE));
		oreCoalEB.setHarvestLevel("pickaxe", 0);
		
		inputMetaBlock(oreLapisEB);
		OreDictionary.registerOre("oreLapis", new ItemStack(oreLapisEB, 1, OreDictionary.WILDCARD_VALUE));
		oreLapisEB.setHarvestLevel("pickaxe", 1);
		
		inputMetaBlock(oreRedstoneEB);
		OreDictionary.registerOre("oreRedstone", new ItemStack(oreRedstoneEB, 1, OreDictionary.WILDCARD_VALUE));
		oreRedstoneEB.setHarvestLevel("pickaxe", 2);
		
		inputMetaBlock(oreRedstoneLitEB);
		oreRedstoneLitEB.setHarvestLevel("pickaxe", 2);
		
		inputMetaBlock(oreDiamondEB);
		OreDictionary.registerOre("oreDiamond", new ItemStack(oreDiamondEB, 1, OreDictionary.WILDCARD_VALUE));
		oreDiamondEB.setHarvestLevel("pickaxe", 2);
		
		inputMetaBlock(oreEmeraldEB);
		OreDictionary.registerOre("oreEmerald", new ItemStack(oreEmeraldEB, 1, OreDictionary.WILDCARD_VALUE));
		oreEmeraldEB.setHarvestLevel("pickaxe", 2);

		inputSlabBlocks(slabS, doubleSlabS, ItemSlabS.class);
		inputSlabBlocks(slabSB, doubleSlabSB, ItemSlabSB.class);
		inputSlabBlocks(slabSC, doubleSlabSC, ItemSlabSC.class);
		
		inputSlabBlocks(slabS2, doubleSlabS2, ItemSlabS2.class);
		inputSlabBlocks(slabSB2, doubleSlabSB2, ItemSlabSB2.class);
		inputSlabBlocks(slabSC2, doubleSlabSC2, ItemSlabSC2.class);
		
		inputBlockList(stairsSEB);

		inputMetaBlock(dirtEB);
		dirtEB.setHarvestLevel("shovel", 0);
		
		inputMetaBlock(grassEB);
		grassEB.setHarvestLevel("shovel", 0);
		
		inputBlockList(farmlandEB);
		for(int a = 0; a < farmlandEB.length; a++) {
			farmlandEB[a].setHarvestLevel("shovel", 0);
		}
		
		inputMetaBlock(saguaro);
		inputBlock(saguaroSapling);

		inputBlock(shoji);
		inputBlock(shojiLamp);

		RenderingRegistry.registerBlockHandler(new BlockGrassRenderer());
		
		for(int x = 0; x < 4; x++) {
			GameRegistry.addShapelessRecipe(new ItemStack(planksEB, 4, x), new Object[] {new ItemStack(logOak, 1, x)});
			GameRegistry.addShapelessRecipe(new ItemStack(planksEB, 4, 4 + x), new Object[] {new ItemStack(logSpruce, 1, x)});
			GameRegistry.addShapelessRecipe(new ItemStack(planksEB, 4, 8 + x), new Object[] {new ItemStack(logBirch, 1, x)});
			GameRegistry.addShapelessRecipe(new ItemStack(planksEB, 4, 12 + x), new Object[] {new ItemStack(logJungle, 1, x)});
		}		  
		 
		for(int x = 0; x < 8; x++) {
			GameRegistry.addRecipe(new ItemStack(slab1, 6, x), new Object[] {"###", '#', new ItemStack(planksEB, 1, x)});
			GameRegistry.addRecipe(new ItemStack(slab2, 6, x), new Object[] {"###", '#', new ItemStack(planksEB, 1, 8 + x)});
		}
		
		for(int x = 0; x < 16; x++) {
			if(stairsEB[x] != null)
				GameRegistry.addRecipe(new ItemStack(stairsEB[x], 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(planksEB, 1, x)});
		}
		
		for(int x = 0; x < BlockStoneBrickEB.stones.length; x++) {
        	GameRegistry.addSmelting(new ItemStack(stoneCobbleEB, 1, x), new ItemStack(stoneEB, 1, x), 0.1F);
        }
		
		for(int x = 0; x < BlockStoneBrickEB.stones.length; x++) {
			GameRegistry.addRecipe(new ItemStack(stoneBrickEB, 4, x), new Object[] {"##", "##", '#', new ItemStack(stoneEB, 1, x)});
        }
		
		for(int x = 0; x < BlockStoneEB.stones.length; x++) {
			GameRegistry.addRecipe(new ItemStack(slabS, 6, x), new Object[] {"###", '#', new ItemStack(stoneEB, 1, x)});
			GameRegistry.addRecipe(new ItemStack(slabSB, 6, x), new Object[] {"###", '#', new ItemStack(stoneBrickEB, 1, x)});
			GameRegistry.addRecipe(new ItemStack(slabSC, 6, x), new Object[] {"###", '#', new ItemStack(stoneCobbleEB, 1, x)});
		}
		
		for(int x = 0; x < BlockStoneEB.stones2.length; x++) {
			GameRegistry.addRecipe(new ItemStack(slabS2, 6, x), new Object[] {"###", '#', new ItemStack(stoneEB, 1, x + BlockStoneEB.stones.length)});
			GameRegistry.addRecipe(new ItemStack(slabSB2, 6, x), new Object[] {"###", '#', new ItemStack(stoneBrickEB, 1, x + BlockStoneEB.stones.length)});
			GameRegistry.addRecipe(new ItemStack(slabSC2, 6, x), new Object[] {"###", '#', new ItemStack(stoneCobbleEB, 1, x + BlockStoneEB.stones.length)});
		}
		
		for(int x = 0; x < stairsSEB.length / 2; x++) {
			if(stairsSEB[x] != null)
				GameRegistry.addRecipe(new ItemStack(stairsSEB[x], 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(stoneCobbleEB, 1, x)});

			if(stairsSEB[x + stairsSEB.length / 2] != null)
				GameRegistry.addRecipe(new ItemStack(stairsSEB[x + 6], 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(stoneBrickEB, 1, x)});

    		GameRegistry.addSmelting(new ItemStack(oreGoldEB, 1, x), new ItemStack(Items.gold_ingot, 1, 0), 1.0F);
    		GameRegistry.addSmelting(new ItemStack(oreDiamondEB, 1, x), new ItemStack(Items.diamond, 1, 0), 1.0F);
    		GameRegistry.addSmelting(new ItemStack(oreIronEB, 1, x), new ItemStack(Items.iron_ingot, 1, 0), 0.7F);
    		GameRegistry.addSmelting(new ItemStack(oreLapisEB, 1, x), new ItemStack(Items.dye, 1, 4), 0.2F);
    		GameRegistry.addSmelting(new ItemStack(oreRedstoneEB, 1, x), new ItemStack(Items.redstone, 1, 0), 0.7F);
    		GameRegistry.addSmelting(new ItemStack(oreEmeraldEB, 1, x), new ItemStack(Items.emerald, 1, 0), 1.0F);
    		GameRegistry.addSmelting(new ItemStack(oreCoalEB, 1, x), new ItemStack(Items.coal, 1, 0), 0.1F);
		}
		
		GameRegistry.addRecipe(new ItemStack(shoji, 4, 0), new Object[] {"#X#", "XXX", "#X#", '#', paper, 'X', stick});
		GameRegistry.addShapelessRecipe(new ItemStack(shojiLamp, 1, 0), new Object[] {new ItemStack(paper, 1, 0), new ItemStack(torch, 1, 0)});
		
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	public static void inputBlock(Block block) {
		GameRegistry.registerBlock(block, "enhancedbiomes." + block.getUnlocalizedName());
	}
	
	public static void inputBlockList(Block[] block) {
		for(int x = 0; x < block.length; x++) {
			if(block[x] != null) {
				inputBlock(block[x]);
			}
		}		
	}
	
	public static void inputMetaBlock(Block block) {
		GameRegistry.registerBlock(block, ItemBlockEnhancedBiomes.class, "enhancedbiomes." + block.getUnlocalizedName());
	}
	
	public static void inputMetaBlockWithItemBlock(Block block, Class<? extends ItemBlock> blockClass) {
		GameRegistry.registerBlock(block, blockClass, "enhancedbiomes." + block.getUnlocalizedName());
	}
	
	public static void inputSlabBlocks(BlockSlab blockHalfSlab, BlockSlab blockDoubleSlab, Class<? extends ItemSlab> blockClass) {
		GameRegistry.registerBlock(blockHalfSlab, blockClass, "enhancedbiomes." + blockHalfSlab.getUnlocalizedName());
		GameRegistry.registerBlock(blockDoubleSlab, blockClass, "enhancedbiomes." + blockDoubleSlab.getUnlocalizedName());
	}
	
	public static void inputWoodBlock(Block block, Class<? extends ItemBlockWithMetadata> logClass) {
		GameRegistry.registerBlock(block, logClass, "enhancedbiomes." + block.getUnlocalizedName());
		OreDictionary.registerOre("logWood", new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE));
	    GameRegistry.addSmelting(block, new ItemStack(coal, 1, 1), 0.15F);
	}

	public static void setStoneBlock(int x, int y, int z, Block blockDef, Block blockEB, int meta, int flag, World world) {
		if(world.getBlock(x, y, z) == EnhancedBiomesBlocks.stoneEB) world.setBlock(x, y, z, blockEB, world.getBlockMetadata(x, y, z), 2);
        else world.setBlock(x, y, z, blockDef, 0, 2);
	}
}
