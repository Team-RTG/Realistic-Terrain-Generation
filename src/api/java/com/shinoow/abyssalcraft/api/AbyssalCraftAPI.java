/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2016 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.api;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.internal.DummyNecroDataHandler;
import com.shinoow.abyssalcraft.api.internal.IInternalNecroDataHandler;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.item.ItemEngraving;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData;
import com.shinoow.abyssalcraft.api.recipe.*;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Main API class for AbyssalCraft, has child classes for most features.<br>
 * Check {@link IMCHelper} for InterModComms registration
 * 
 * @author shinoow
 *
 */
public class AbyssalCraftAPI {

	/**
	 * String used to specify the API version in the "package-info.java" classes
	 */
	public static final String API_VERSION = "1.7";

	/**
	 * Enchantment IDs, first one is the Coralium enchantment, second Dread enchantment,
	 * third the Light Pierce enchantment, and the fourth is the Iron Wall enchantment.
	 */
	public static int enchId1, enchId2, enchId3, enchId4;

	/**
	 * Potion IDs, first one is the Coralium Plague, second Dread Plague, and third Antimatter
	 */
	public static int potionId1, potionId2, potionId3;

	private static List<IFuelHandler> crystallizerFuelHandlers = Lists.newArrayList();
	private static List<IFuelHandler> transmutatorFuelHandlers = Lists.newArrayList();

	private static HashMap<Integer, String> potionRequirements = null;
	private static HashMap<Integer, String> potionAmplifiers = null;

	public static DamageSource coralium = new DamageSource("coralium").setDamageBypassesArmor().setMagicDamage();
	public static DamageSource dread = new DamageSource("dread").setDamageBypassesArmor().setMagicDamage();
	public static DamageSource antimatter = new DamageSource("antimatter").setDamageBypassesArmor().setMagicDamage();

	private static List<Class<? extends EntityLivingBase>> shoggothFood = Lists.newArrayList();

	private static List<Block> shoggothBlockBlacklist = Lists.newArrayList();

	private static List<ItemStack> crystals = Lists.newArrayList();

	private static HashMap<NecroData, Integer> necroData = Maps.newHashMap();

	/**
	 *  {@link EnumCreatureAttribute} used for the Shadow mobs
	 */
	public static EnumCreatureAttribute SHADOW = EnumHelper.addCreatureAttribute("SHADOW");

	public static ArmorMaterial abyssalniteArmor = EnumHelper.addArmorMaterial("Abyssalnite", 35, new int[]{3, 6, 8, 3}, 13);
	public static ArmorMaterial dreadedAbyssalniteArmor = EnumHelper.addArmorMaterial("Dread", 36, new int[]{3, 6, 8, 3}, 15);
	public static ArmorMaterial refinedCoraliumArmor = EnumHelper.addArmorMaterial("Coralium", 37, new int[]{3, 6, 8, 3}, 14);
	public static ArmorMaterial platedCoraliumArmor = EnumHelper.addArmorMaterial("CoraliumP", 55, new int[]{4, 7, 9, 4}, 14);
	public static ArmorMaterial depthsArmor = EnumHelper.addArmorMaterial("Depths", 33, new int[]{3, 6, 8, 3}, 25);
	public static ArmorMaterial dreadiumArmor = EnumHelper.addArmorMaterial("Dreadium", 40, new int[]{3, 6, 8, 3}, 15);
	public static ArmorMaterial dreadiumSamuraiArmor = EnumHelper.addArmorMaterial("DreadiumS", 45, new int[]{3, 6, 8, 3}, 20);
	public static ArmorMaterial ethaxiumArmor = EnumHelper.addArmorMaterial("Ethaxium", 50, new int[]{3, 6, 8, 3}, 25);

	public static ToolMaterial darkstoneTool = EnumHelper.addToolMaterial("DARKSTONE", 1, 180, 5.0F, 1, 5);
	public static ToolMaterial abyssalniteTool = EnumHelper.addToolMaterial("ABYSSALNITE", 4, 1261, 10.0F, 4, 13);
	public static ToolMaterial refinedCoraliumTool = EnumHelper.addToolMaterial("CORALIUM", 5, 1800, 12.0F, 5, 14);
	public static ToolMaterial dreadiumTool = EnumHelper.addToolMaterial("DREADIUM", 6, 2300, 14.0F, 6, 15);
	public static ToolMaterial ethaxiumTool = EnumHelper.addToolMaterial("ETHAXIUM", 8, 2800, 16.0F, 8, 20);

	private static IInternalNecroDataHandler internalNDHandler = new DummyNecroDataHandler();

	/**
	 * Used by AbyssalCraft to set the Internal NecroData Handler.<br>
	 * If any other mod tries to use this method, nothing will happen.
	 * @param handler Handler instance
	 */
	public static void setInternalNDHandler(IInternalNecroDataHandler handler){
		if(internalNDHandler.getClass().getName().equals(DummyNecroDataHandler.class.getName())
				&& Loader.instance().getLoaderState() == LoaderState.PREINITIALIZATION
				&& Loader.instance().activeModContainer().getModId().equals("abyssalcraft"))
			internalNDHandler = handler;
	}

	/**
	 * Internal NecroData handler.<br>
	 * Use this to alter the internal NecroData instances.
	 */
	public static IInternalNecroDataHandler getInternalNDHandler(){
		return internalNDHandler;
	}

	/**
	 * Initializes the reflection required for the Potion code, ignore it
	 */
	@SuppressWarnings("unchecked")
	public static void initPotionReflection(){
		for(Field f : PotionHelper.class.getDeclaredFields())
			try {
				if(f.getName().equals("potionRequirements") || f.getName().equals("field_77927_l")){
					f.setAccessible(true);
					try {
						potionRequirements = (HashMap<Integer, String>)f.get(null);
					} catch (IllegalArgumentException
							| IllegalAccessException e) {
						System.err.println("Whoops, something screwed up here, please report this to shinoow:");
						e.printStackTrace();
					}
				}
				if(f.getName().equals("potionAmplifiers") || f.getName().equals("field_77928_m")){
					f.setAccessible(true);
					try {
						potionAmplifiers = (HashMap<Integer, String>)f.get(null);
					} catch (IllegalArgumentException
							| IllegalAccessException e) {
						System.err.println("Whoops, something screwed up here, please report this to shinoow:");
						e.printStackTrace();
					}
				}
			} catch (SecurityException e) {
				System.err.println("Whoops, something screwed up here, please report this to shinoow:");
				e.printStackTrace();
			}
	}

	/**
	 * Sets the repair items for each armor/tool material
	 */
	public static void setRepairItems(){

		abyssalniteArmor.customCraftingMaterial = ACItems.abyssalnite_ingot;
		dreadedAbyssalniteArmor.customCraftingMaterial = ACItems.dreaded_shard_of_abyssalnite;
		refinedCoraliumArmor.customCraftingMaterial = ACItems.refined_coralium_ingot;
		platedCoraliumArmor.customCraftingMaterial = ACItems.coralium_plate;
		depthsArmor.customCraftingMaterial = ACItems.coralium_gem_cluster_9;
		dreadiumArmor.customCraftingMaterial = ACItems.dreadium_ingot;
		dreadiumSamuraiArmor.customCraftingMaterial = ACItems.dreadium_plate;
		ethaxiumArmor.customCraftingMaterial = ACItems.ethaxium_ingot;

		darkstoneTool.setRepairItem(new ItemStack(ACBlocks.darkstone_cobblestone));
		abyssalniteTool.setRepairItem(new ItemStack(ACItems.abyssalnite_ingot));
		refinedCoraliumTool.setRepairItem(new ItemStack(ACItems.refined_coralium_ingot));
		dreadiumTool.setRepairItem(new ItemStack(ACItems.dreadium_ingot));
		ethaxiumTool.setRepairItem(new ItemStack(ACItems.ethaxium_ingot));
	}

	/**
	 * Adds a bit sequence used to calculate the status on a potion.
	 * This description probably hardly makes any sense, deal with it.
	 * @param id The potion id
	 * @param requirements A bit sequence
	 * 
	 * @since 1.1
	 */
	public static void addPotionRequirements(int id, String requirements){
		potionRequirements.put(Integer.valueOf(id), requirements);
	}

	/**
	 * Adds an amplifier to a potion.
	 * This description probably hardly makes any sense, deal with it.
	 * @param id The potion id
	 * @param amplifier The potion amplifier value (usually 5)
	 * 
	 * @since 1.1
	 */
	public static void addPotionAmplifiers(int id, String amplifier){
		potionAmplifiers.put(Integer.valueOf(id), amplifier);
	}

	/**
	 * Basic Crystallization
	 * @param input The block to crystallize
	 * @param output1 The first crystal output
	 * @param output2 The second crystal output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addCrystallization(Block input, ItemStack output1, ItemStack output2, float xp){
		CrystallizerRecipes.instance().crystallize(input, output1, output2, xp);
	}

	/**
	 * Basic Crystallization
	 * @param input The item to crystallize
	 * @param output1 The first crystal output
	 * @param output2 The second crystal output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addCrystallization(Item input, ItemStack output1, ItemStack output2, float xp){
		CrystallizerRecipes.instance().crystallize(input, output1, output2, xp);
	}

	/**
	 * Basic Crystallization
	 * @param input The itemstack to crystallize
	 * @param output1 The first crystal output
	 * @param output2 The second crystal output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addCrystallization(ItemStack input, ItemStack output1, ItemStack output2, float xp){
		CrystallizerRecipes.instance().crystallize(input, output1, output2, xp);
	}

	/**
	 * Single-output Crystallization
	 * @param input The block to crystallize
	 * @param output The crystal output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addSingleCrystallization(Block input, ItemStack output, float xp){
		addCrystallization(input, output, null, xp);
	}

	/**
	 * Single-output Crystallization
	 * @param input The item to crystallize
	 * @param output The crystal output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addSingleCrystallization(Item input, ItemStack output, float xp){
		addCrystallization(input, output, null, xp);
	}

	/**
	 * Single-output Crystallization
	 * @param input The itemstack to crystallize
	 * @param output The crystal output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addSingleCrystallization(ItemStack input, ItemStack output, float xp){
		addCrystallization(input, output, null, xp);
	}

	/**
	 * Basic Transmutation
	 * @param input The block to transmutate
	 * @param output The transmutated output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addTransmutation(Block input, ItemStack output, float xp){
		TransmutatorRecipes.instance().transmute(input, output, xp);
	}

	/**
	 * Basic Transmutation
	 * @param input The item to transmutate
	 * @param output The transmutated output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addTransmutation(Item input, ItemStack output, float xp){
		TransmutatorRecipes.instance().transmute(input, output, xp);
	}

	/**
	 * Basic Transmutation
	 * @param input The itemstack to transmutate
	 * @param output The transmutated output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addTransmutation(ItemStack input, ItemStack output, float xp){
		TransmutatorRecipes.instance().transmute(input, output, xp);
	}

	/**
	 * Smelting through the OreDictionary
	 * @param input The ore input
	 * @param output The ore output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addOreSmelting(String input, String output, float xp){
		if(!OreDictionary.getOres(output).isEmpty())
			for(ItemStack stack : OreDictionary.getOres(input))
				FurnaceRecipes.smelting().func_151394_a(stack, OreDictionary.getOres(output).get(0), xp);
	}

	/**
	 * OreDictionary specific Crystallization
	 * @param input The ore input
	 * @param output1 The first ore output
	 * @param output2 The second ore output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addCrystallization(String input, String output1, String output2, float xp){
		if(!OreDictionary.getOres(output1).isEmpty() && !OreDictionary.getOres(output2).isEmpty())
			for(ItemStack stack : OreDictionary.getOres(input))
				addCrystallization(stack, OreDictionary.getOres(output1).get(0), OreDictionary.getOres(output2).get(0), xp);
	}

	/**
	 * OreDictionary specific Crystallization
	 * @param input The ore input
	 * @param output1 The first ore output
	 * @param out1 Quantity of the first output
	 * @param output2 The second ore output
	 * @param out2 Quantity of the second output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addCrystallization(String input, String output1, int out1, String output2, int out2, float xp){
		if(!OreDictionary.getOres(output1).isEmpty() && !OreDictionary.getOres(output2).isEmpty()){
			ItemStack o1 = OreDictionary.getOres(output1).get(0).copy();
			o1.stackSize = out1;
			ItemStack o2 = OreDictionary.getOres(output2).get(0).copy();
			o2.stackSize = out2;
			for(ItemStack stack : OreDictionary.getOres(input))
				addCrystallization(stack, o1, o2, xp);
		}
	}

	/**
	 * OreDictionary specific single-output Crystallization
	 * @param input The ore input
	 * @param output The ore output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addSingleCrystallization(String input, String output, float xp){
		if(!OreDictionary.getOres(output).isEmpty())
			for(ItemStack stack : OreDictionary.getOres(input))
				addSingleCrystallization(stack, OreDictionary.getOres(output).get(0), xp);
	}

	/**
	 * OreDictionary specific single-output Crystallization
	 * @param input The ore input
	 * @param output The ore output
	 * @param out The output quantity
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addSingleCrystallization(String input, String output, int out, float xp){
		if(!OreDictionary.getOres(output).isEmpty()){
			ItemStack o = OreDictionary.getOres(output).get(0).copy();
			o.stackSize = out;
			for(ItemStack stack : OreDictionary.getOres(input))
				addSingleCrystallization(stack, o, xp);
		}
	}

	/**
	 * OreDictionary specific Transmutation
	 * @param input The ore input
	 * @param output The ore output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addTransmutation(String input, String output, float xp){
		if(!OreDictionary.getOres(output).isEmpty())
			for(ItemStack stack : OreDictionary.getOres(input))
				addTransmutation(stack, OreDictionary.getOres(output).get(0), xp);
	}

	/**
	 * OreDictionary specific Transmutation
	 * @param input The ore input
	 * @param output The ore output
	 * @param out The output quantity
	 * @param xp Amount of exp given
	 * 
	 * @since 1.0
	 */
	public static void addTransmutation(String input, String output, int out, float xp){
		if(!OreDictionary.getOres(output).isEmpty()){
			ItemStack o = OreDictionary.getOres(output).get(0).copy();
			o.stackSize = out;
			for(ItemStack stack : OreDictionary.getOres(input))
				addTransmutation(stack, o, xp);
		}
	}

	/**
	 * Registers a coin to the coin list (use {@link #addEngraving(ItemStack, ItemEngraving, float)}.
	 * Both regular coins and engraved coins should be registered here
	 * to register the engraving template and engraved coin)
	 * @param coin The ItemStack containing a coin
	 * 
	 * @since 1.3.5
	 */
	public static void addCoin(ItemStack coin){
		if(!EngraverRecipes.instance().getCoinList().contains(coin))
			EngraverRecipes.instance().addCoin(coin);
		else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "This Coin is already registered!");
	}

	/**
	 * Registers a coin to the coin list (use {@link #addEngraving(Item, ItemEngraving, float)}.
	 * Both regular coins and engraved coins should be registered here
	 * to register the engraving template and engraved coin)
	 * @param coin The Item representing a coin
	 * 
	 * @since 1.3.5
	 */
	public static void addCoin(Item coin){
		if(!EngraverRecipes.instance().getCoinList().contains(new ItemStack(coin)))
			EngraverRecipes.instance().addCoin(coin);
		else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "This Coin is already registered!");
	}

	/**
	 * Registers a coin engraving (use {@link #addCoin(ItemStack)} to register the coin)
	 * @param coin The Engraved Coin
	 * @param engraving The Engraving Template
	 * @param xp Amount of exp given
	 * 
	 * @since 1.3.5
	 */
	public static void addEngraving(ItemStack coin, ItemEngraving engraving, float xp){
		if(!EngraverRecipes.instance().getEngravingList().containsKey(engraving) &&
				!EngraverRecipes.instance().getEngravingList().containsValue(coin))
			EngraverRecipes.instance().addEngraving(coin, engraving, xp);
		else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "This Engraving Template and/or Engraved Coin is already registered!");
	}

	/**
	 * Registers a coin engraving (use {@link #addCoin(Item)} to register the coin)
	 * @param coin The Engraved Coin
	 * @param engraving The Engraving Template
	 * @param xp Amount of exp given
	 * 
	 * @since 1.3.5
	 */
	public static void addEngraving(Item coin, ItemEngraving engraving, float xp){
		if(!EngraverRecipes.instance().getEngravingList().containsKey(engraving) &&
				!EngraverRecipes.instance().getEngravingList().containsValue(new ItemStack(coin)))
			EngraverRecipes.instance().addEngraving(coin, engraving, xp);
		else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "This Engraving Template and/or Engraved Coin is already registered!");
	}

	/**
	 * Basic Materialization.<br>
	 * Note: all inputs has to be either {@link ICrystal}s or be registered in the Crystal List {@link AbyssalCraftAPI#addCrystal(ItemStack)}
	 * @param input An array of ItemStacks (maximum is 5)
	 * @param output The output
	 * @param xp Amount of exp given
	 * 
	 * @since 1.4.5
	 */
	public static void addMaterialization(ItemStack[] input, ItemStack output){
		for(ItemStack item : input)
			if(!APIUtils.isCrystal(item)) throw new ClassCastException("All of the input items has to be Crystals!");
		if(input.length > 0 || input == null)
			if(input.length <= 5)
				MaterializerRecipes.instance().materialize(input, output);
			else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "This Materializer recipe has more than 5 inputs! (%d)", input.length);
		else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "This Materializer recipe has no inputs!");
	}

	/**
	 * Basic Materialization.
	 * @param materialization A Materializer Recipe
	 * 
	 * @since 1.5
	 */
	public static void addMaterialization(Materialization materialization){
		MaterializerRecipes.instance().materialize(materialization);
	}

	/**
	 * Fuel types, also has support for the vanilla furnace.
	 * @author shinoow
	 *
	 */
	public enum FuelType{
		CRYSTALLIZER, TRANSMUTATOR, FURNACE
	}

	/**
	 * Registers a fuel handler for an AbyssalCraft fuel type
	 * @param handler The file that implements {@link IFuelHandler}
	 * @param type The fuel type
	 * 
	 * @since 1.0
	 */
	public static void registerFuelHandler(IFuelHandler handler, FuelType type){
		switch(type){
		case CRYSTALLIZER:
			crystallizerFuelHandlers.add(handler);
			break;
		case TRANSMUTATOR:
			transmutatorFuelHandlers.add(handler);
			break;
		case FURNACE:
			GameRegistry.registerFuelHandler(handler);
		}
	}

	/**
	 * Gets the fuel value from an ItemStack, depending on the fuel type
	 * @param itemStack The ItemStack getting checked
	 * @param type The fuel type
	 * @return The fuel value for the specified machine
	 * 
	 * @since 1.0
	 */
	public static int getFuelValue(ItemStack itemStack, FuelType type){
		int fuelValue = 0;
		switch(type){
		case CRYSTALLIZER:
			for (IFuelHandler handler : crystallizerFuelHandlers)
				fuelValue = Math.max(fuelValue, handler.getBurnTime(itemStack));
			break;
		case TRANSMUTATOR:
			for (IFuelHandler handler : transmutatorFuelHandlers)
				fuelValue = Math.max(fuelValue, handler.getBurnTime(itemStack));
			break;
		case FURNACE:
			GameRegistry.getFuelValue(itemStack);
		}
		return fuelValue;
	}

	/**
	 * Adds the entity to a list of entities that the Lesser Shoggoth eats
	 * (Note: It's useless to add your entity here if it extends {@link EntityAnimal}, {@link EntityAmbientCreature}, {@link EntityWaterMob} or {@link EntityTameable}).
	 * If your Entity's superclass is a subclass of EntityTameable, you will need to add the superclass.
	 * @param clazz The potential "food" for the Lesser Shoggoth
	 * 
	 * @since 1.2
	 */
	public static void addShoggothFood(Class<? extends EntityLivingBase> clazz){
		shoggothFood.add(clazz);
	}

	/**
	 * Used by the Lesser Shoggoth to fetch a list of things to eat
	 * @return An ArrayList containing Entity classes
	 * 
	 * @since 1.2
	 */
	public static List<Class<? extends EntityLivingBase>> getShoggothFood(){
		return shoggothFood;
	}

	/**
	 * Adds the block to a list of blocks that won't turn into
	 * a fleshy soil when a Lesser Shoggoth walks over it
	 * (Note: Any liquid block and tile entity block will automatically be blacklisted)
	 * @param block The block to blacklist
	 * 
	 * @since 1.4
	 */
	public static void addShoggothBlacklist(Block block){
		shoggothBlockBlacklist.add(block);
	}

	/**
	 * Used by the Lesser Shoggoth to fetch a list of blocks that won't be converted when
	 * it walks over them
	 * @return An ArrayList containing Blocks
	 * 
	 * @since 1.4
	 */
	public static List<Block> getShoggothBlockBlacklist(){
		return shoggothBlockBlacklist;
	}

	/**
	 * Adds the ItemStack to the crystal list. Anything added to this list will function like a {@link ICrystal}
	 * @param crystal The ItemStack to be added
	 * 
	 * @since 1.3
	 */
	public static void addCrystal(ItemStack crystal){
		crystals.add(crystal);
	}

	/**
	 * Used by various things to fetch a list of ItemStacks that should function like {@link ICrystal}s
	 * @return An ArrayList of ItemStacks
	 * 
	 * @since 1.3
	 */
	public static List<ItemStack> getCrystals(){
		return crystals;
	}

	/**
	 * Method used to register pages to the Necronomicon
	 * @param data The NecroData instance, with page data
	 * @param bookType The Necronomicon "level" required in order to read this.
	 * <ul>
	 * <li>0 = Necronomicon</li>
	 * <li>1 = Abyssal Wasteland Necronomicon</li>
	 * <li>2 = Dreadlands Necronomicon</li>
	 * <li>3 = Omothol Necronomicon</li>
	 * <li>4 = Abyssalnomicon</li>
	 * </ul>
	 * 
	 * @since 1.3.5
	 */
	public static void registerNecronomiconData(NecroData data, int bookType){
		if(bookType <= 4 && bookType >= 0)
			necroData.put(data, bookType);
		else FMLLog.log("AbyssalCraftAPI", Level.ERROR, "Necronomicon book type does not exist: %d", bookType);
	}

	/**
	 * Used by the Necronomicon for fetching pages made outside of AbyssalCraft
	 * @return A HashMap of NecroDatas and Integers
	 * 
	 * @since 1.3.5
	 */
	public static HashMap<NecroData,Integer> getNecronomiconData(){
		return necroData;
	}

	/**
	 * Contains the names of all mobs added in AbyssalCraft.
	 * 
	 * @author shinoow
	 *
	 */
	public static class ACEntities {

		private static String[] mobNames = {"depthsghoul", "evilpig", "abyssalzombie", "Jzahar", "abygolem", "dreadgolem",
			"dreadguard", "dragonminion", "dragonboss", "shadowcreature", "shadowmonster", "dreadling", "dreadspawn",
			"demonpig", "gskeleton", "chagarothspawn", "chagarothfist", "chagaroth", "shadowbeast", "shadowboss",
			"antiabyssalzombie", "antibat", "antichicken", "anticow", "anticreeper", "antighoul", "antipig", "antiplayer",
			"antiskeleton", "antispider", "antizombie", "lessershoggoth", "shadowtitan", "omotholwarden", "jzaharminion",
			"omotholghoul", "remnant", "greaterdreadspawn", "lesserdreadbeast", "evilcow", "evilchicken", "demoncow",
		"demonchicken"};

		public static String depths_ghoul = mobNames[0];
		public static String evil_pig = mobNames[1];
		public static String abyssal_zombie = mobNames[2];
		public static String jzahar = mobNames[3];
		public static String abyssalnite_golem = mobNames[4];
		public static String dreaded_abyssalnite_golem = mobNames[5];
		public static String dreadguard = mobNames[6];
		public static String spectral_dragon = mobNames[7];
		public static String asorah = mobNames[8];
		public static String shadow_creature = mobNames[9];
		public static String shadow_monster = mobNames[10];
		public static String dreadling = mobNames[11];
		public static String dread_spawn = mobNames[12];
		public static String demon_pig = mobNames[13];
		public static String skeleton_goliath = mobNames[14];
		public static String spawn_of_chagaroth = mobNames[15];
		public static String fist_of_chagaroth = mobNames[16];
		public static String chagaroth = mobNames[17];
		public static String shadow_beast = mobNames[18];
		public static String sacthoth = mobNames[19];
		public static String abyssal_anti_zombie = mobNames[20];
		public static String anti_bat = mobNames[21];
		public static String anti_chicken = mobNames[22];
		public static String anti_cow = mobNames[23];
		public static String anti_creeper = mobNames[24];
		public static String anti_ghoul = mobNames[25];
		public static String anti_pig = mobNames[26];
		public static String anti_player = mobNames[27];
		public static String anti_skeleton = mobNames[28];
		public static String anti_spider = mobNames[29];
		public static String anti_zombie = mobNames[30];
		public static String lesser_shoggoth = mobNames[31];
		public static String shadow_titan = mobNames[32];
		public static String omothol_warden = mobNames[33];
		public static String minion_of_the_gatekeeper = mobNames[34];
		public static String omothol_ghoul = mobNames[35];
		public static String remnant = mobNames[36];
		public static String greater_dread_spawn = mobNames[37];
		public static String lesser_dreadbeast = mobNames[38];
		public static String evil_cow = mobNames[39];
		public static String evil_chicken = mobNames[40];
		public static String demon_cow = mobNames[41];
		public static String demon_chicken = mobNames[42];
	}

	/**
	 * Contains all potion effects added in AbyssalCraft.
	 * You can reference them from here, or use the ID directly
	 * 
	 * @author shinoow
	 *
	 */
	public static class ACPotions {

		public static Potion Coralium_plague = Potion.potionTypes[potionId1];
		public static Potion Dread_plague = Potion.potionTypes[potionId2];
		public static Potion Antimatter = Potion.potionTypes[potionId3];
	}
}
