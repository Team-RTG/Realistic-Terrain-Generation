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
package com.shinoow.abyssalcraft.api.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Contains all items added in AbyssalCraft
 * 
 * @author shinoow
 *
 */
public class ACItems {

	public static Item oblivion_catalyst = getItem("OC");
	public static Item gateway_key = getItem("portalplacer");
	public static Item staff_of_the_gatekeeper = getItem("staff");
	public static Item liquid_coralium_bucket = getItem("cbucket");
	public static Item powerstone_tracker = getItem("psdlfinder");
	public static Item eye_of_the_abyss = getItem("eoa");
	public static Item dreaded_gateway_key = getItem("portalplacerdl");
	public static Item dreaded_shard_of_abyssalnite = getItem("dreadshard");
	public static Item dreaded_chunk_of_abyssalnite = getItem("dreadchunk");
	public static Item chunk_of_abyssalnite = getItem("abychunk");
	public static Item abyssalnite_ingot = getItem("abyingot");
	public static Item coralium_gem = getItem("coralium");
	public static Item coralium_gem_cluster_2 = getItem("ccluster2");
	public static Item coralium_gem_cluster_3 = getItem("ccluster3");
	public static Item coralium_gem_cluster_4 = getItem("ccluster4");
	public static Item coralium_gem_cluster_5 = getItem("ccluster5");
	public static Item coralium_gem_cluster_6 = getItem("ccluster6");
	public static Item coralium_gem_cluster_7 = getItem("ccluster7");
	public static Item coralium_gem_cluster_8 = getItem("ccluster8");
	public static Item coralium_gem_cluster_9 = getItem("ccluster9");
	public static Item coralium_pearl = getItem("cpearl");
	public static Item chunk_of_coralium = getItem("cchunk");
	public static Item refined_coralium_ingot = getItem("cingot");
	public static Item coralium_plate = getItem("platec");
	public static Item transmutation_gem = getItem("corb");
	public static Item coralium_plagued_flesh = getItem("corflesh");
	public static Item coralium_plagued_flesh_on_a_bone = getItem("corbone");
	public static Item darkstone_pickaxe = getItem("dpick");
	public static Item darkstone_axe = getItem("daxe");
	public static Item darkstone_shovel = getItem("dshovel");
	public static Item darkstone_sword = getItem("dsword");
	public static Item darkstone_hoe = getItem("dhoe");
	public static Item abyssalnite_pickaxe = getItem("apick");
	public static Item abyssalnite_axe = getItem("aaxe");
	public static Item abyssalnite_shovel = getItem("ashovel");
	public static Item abyssalnite_sword = getItem("asword");
	public static Item abyssalnite_hoe = getItem("ahoe");
	public static Item refined_coralium_pickaxe = getItem("corpick");
	public static Item refined_coralium_axe = getItem("coraxe");
	public static Item refined_coralium_shovel = getItem("corshovel");
	public static Item refined_coralium_sword = getItem("corsword");
	public static Item refined_coralium_hoe = getItem("corhoe");
	public static Item abyssalnite_boots = getItem("aboots");
	public static Item abyssalnite_helmet = getItem("ahelmet");
	public static Item abyssalnite_chestplate = getItem("aplate");
	public static Item abyssalnite_leggings = getItem("alegs");
	public static Item dreaded_abyssalnite_boots = getItem("dboots");
	public static Item dreaded_abyssalnite_helmet = getItem("dhelmet");
	public static Item dreaded_abyssalnite_chestplate = getItem("dplate");
	public static Item dreaded_abyssalnite_leggings = getItem("dlegs");
	public static Item refined_coralium_boots = getItem("corboots");
	public static Item refined_coralium_helmet = getItem("corhelmet");
	public static Item refined_coralium_chestplate = getItem("corplate");
	public static Item refined_coralium_leggings = getItem("corlegs");
	public static Item plated_coralium_boots = getItem("corbootsp");
	public static Item plated_coralium_helmet = getItem("corhelmetp");
	public static Item plated_coralium_chestplate = getItem("corplatep");
	public static Item plated_coralium_leggings = getItem("corlegsp");
	public static Item depths_boots = getItem("depthsboots");
	public static Item depths_helmet = getItem("depthshelmet");
	public static Item depths_chestplate = getItem("depthsplate");
	public static Item depths_leggings = getItem("depthslegs");
	public static Item cobblestone_upgrade_kit = getItem("cobbleu");
	public static Item iron_upgrade_kit = getItem("ironu");
	public static Item gold_upgrade_kit = getItem("goldu");
	public static Item diamond_upgrade_kit = getItem("diamondu");
	public static Item abyssalnite_upgrade_kit = getItem("abyssalniteu");
	public static Item coralium_upgrade_kit = getItem("coraliumu");
	public static Item mre = getItem("mre");
	public static Item iron_plate = getItem("ironp");
	public static Item chicken_on_a_plate = getItem("chickenp");
	public static Item pork_on_a_plate = getItem("porkp");
	public static Item beef_on_a_plate = getItem("beefp");
	public static Item fish_on_a_plate = getItem("fishp");
	public static Item dirty_plate = getItem("dirtyplate");
	public static Item fried_egg = getItem("friedegg");
	public static Item fried_egg_on_a_plate = getItem("eggp");
	public static Item washcloth = getItem("cloth");
	public static Item shadow_fragment = getItem("shadowfragment");
	public static Item shadow_shard = getItem("shadowshard");
	public static Item shadow_gem = getItem("shadowgem");
	public static Item shard_of_oblivion = getItem("oblivionshard");
	public static Item coralium_longbow = getItem("corbow");
	public static Item liquid_antimatter_bucket = getItem("antibucket");
	public static Item coralium_brick = getItem("cbrick");
	public static Item cudgel = getItem("cudgel");
	public static Item dreadium_ingot = getItem("dreadiumingot");
	public static Item dread_fragment = getItem("dreadfragment");
	public static Item dreadium_boots = getItem("dreadiumboots");
	public static Item dreadium_helmet = getItem("dreadiumhelmet");
	public static Item dreadium_chestplate = getItem("dreadiumplate");
	public static Item dreadium_leggings = getItem("dreadiumlegs");
	public static Item dreadium_pickaxe = getItem("dreadiumpickaxe");
	public static Item dreadium_axe = getItem("dreadiumaxe");
	public static Item dreadium_shovel = getItem("dreadiumshovel");
	public static Item dreadium_sword = getItem("dreadiumsword");
	public static Item dreadium_hoe = getItem("dreadiumhoe");
	public static Item dreadium_upgrade_kit = getItem("dreadiumu");
	public static Item carbon_cluster = getItem("carboncluster");
	public static Item dense_carbon_cluster = getItem("densecarboncluster");
	public static Item methane = getItem("methane");
	public static Item nitre = getItem("nitre");
	public static Item sulfur = getItem("sulfur");
	/** Metadata Item, 0 = Iron, 1 = Gold, 2 = Sulfur, 3 = Carbon, 4 = Oxygen, 5 = Hydrogen, 6 = Nitrogen, 7 = Phosphorus,
	 * 8 = Potassium, 9 = Nitrate, 10 = Methane, 11 = Redstone, 12 = Abyssalnite, 13 = Coralium, 14 = Dreadium, 15 = Blaze,
	 * 16 = Tin, 17 = Copper, 18 = Silicon, 19 = Magnesium, 20 = Aluminium, 21 = Silica, 22 = Alumina, 23 = Magnesia, 24 = Zinc */
	public static Item crystal = getItem("crystal");
	/** Metadata Item, 0 = Iron, 1 = Gold, 2 = Sulfur, 3 = Carbon, 4 = Oxygen, 5 = Hydrogen, 6 = Nitrogen, 7 = Phosphorus,
	 * 8 = Potassium, 9 = Nitrate, 10 = Methane, 11 = Redstone, 12 = Abyssalnite, 13 = Coralium, 14 = Dreadium, 15 = Blaze,
	 * 16 = Tin, 17 = Copper, 18 = Silicon, 19 = Magnesium, 20 = Aluminium, 21 = Silica, 22 = Alumina, 23 = Magnesia, 24 = Zinc */
	public static Item crystal_shard = getItem("crystalshard");
	public static Item dread_cloth = getItem("dreadcloth");
	public static Item dreadium_plate = getItem("dreadiumplate");
	public static Item dreadium_katana_blade = getItem("dreadblade");
	public static Item dreadium_katana_hilt = getItem("dreadhilt");
	public static Item dreadium_katana = getItem("dreadkatana");
	public static Item dread_plagued_gateway_key = getItem("dreadkey");
	public static Item rlyehian_gateway_key = getItem("portalplacerjzh");
	public static Item dreadium_samurai_boots = getItem("dreadiumsamuraiboots");
	public static Item dreadium_samurai_helmet = getItem("dreadiumsamuraihelmet");
	public static Item dreadium_samurai_chestplate = getItem("dreadiumsamuraiplate");
	public static Item dreadium_samurai_leggings = getItem("dreadiumsamurailegs");
	public static Item tin_ingot = getItem("tiningot");
	public static Item copper_ingot = getItem("copperingot");
	public static Item anti_beef = getItem("antibeef");
	public static Item anti_chicken = getItem("antichicken");
	public static Item anti_pork = getItem("antipork");
	public static Item rotten_anti_flesh = getItem("antiflesh");
	public static Item anti_bone = getItem("antibone");
	public static Item anti_spider_eye = getItem("antispidereye");
	public static Item sacthoths_soul_harvesting_blade = getItem("soulreaper");
	public static Item ethaxium_brick = getItem("ethbrick");
	public static Item ethaxium_ingot = getItem("ethaxiumingot");
	public static Item life_crystal = getItem("lifecrystal");
	public static Item ethaxium_boots = getItem("ethaxiumboots");
	public static Item ethaxium_helmet = getItem("ethaxiumhelmet");
	public static Item ethaxium_chestplate = getItem("ethaxiumplate");
	public static Item ethaxium_leggings = getItem("ethaxiumlegs");
	public static Item ethaxium_pickaxe = getItem("ethaxiumpickaxe");
	public static Item ethaxium_axe = getItem("ethaxiumaxe");
	public static Item ethaxium_shovel = getItem("ethaxiumshovel");
	public static Item ethaxium_sword = getItem("ethaxiumsword");
	public static Item ethaxium_hoe = getItem("ethaxiumhoe");
	public static Item ethaxium_upgrade_kit = getItem("ethaxiumu");
	public static Item coin = getItem("coin");
	public static Item cthulhu_engraved_coin = getItem("cthulhucoin");
	public static Item elder_engraved_coin = getItem("eldercoin");
	public static Item jzahar_engraved_coin = getItem("jzaharcoin");
	public static Item blank_engraving = getItem("engraving_blank");
	public static Item cthulhu_engraving = getItem("engraving_cthulhu");
	public static Item elder_engraving = getItem("engraving_elder");
	public static Item jzahar_engraving	= getItem("engraving_jzahar");
	public static Item eldritch_scale = getItem("eldritchscale");
	public static Item omothol_flesh = getItem("omotholflesh");
	public static Item anti_plagued_flesh = getItem("anticorflesh");
	public static Item anti_plagued_flesh_on_a_bone = getItem("anticorbone");
	public static Item necronomicon = getItem("necronomicon");
	public static Item abyssal_wasteland_necronomicon = getItem("necronomicon_cor");
	public static Item dreadlands_necronomicon = getItem("necronomicon_dre");
	public static Item omothol_necronomicon = getItem("necronomicon_omt");
	public static Item abyssalnomicon = getItem("abyssalnomicon");
	public static Item small_crystal_bag = getItem("crystalbag_small");
	public static Item medium_crystal_bag = getItem("crystalbag_medium");
	public static Item large_crystal_bag = getItem("crystalbag_large");
	public static Item huge_crystal_bag = getItem("crystalbag_huge");
	/** Metadata Item, 0 = Overworld, 1 = Abyssal Wasteland, 2 = Dreadlands, 3 = Omothol, 4 = Dark Realm */
	public static Item shoggoth_flesh = getItem("shoggothflesh");
	/** Metadata Item, 0 = Abyssalnite, 1 = Refined Coralium, 2 = Dreadium, 3 = Ethaxium */
	public static Item ingot_nugget = getItem("ingotnugget");
	public static Item staff_of_rending = getItem("drainstaff");
	/** Metadata Item, 0 = Abyssal Wasteland, 1 = Dreadlands, 2 = Omothol */
	public static Item essence = getItem("essence");
	/** Metadata Item, 0 = Abyssal Wasteland, 1 = Dreadlands, 2 = Omothol */
	public static Item skin = getItem("skin");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item ritual_charm = getItem("charm");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item cthulhu_charm = getItem("cthulhucharm");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item hastur_charm = getItem("hasturcharm");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item jzahar_charm = getItem("jzaharcharm");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item azathoth_charm = getItem("azathothcharm");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item nyarlathotep_charm = getItem("nyarlathotepcharm");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item yog_sothoth_charm = getItem("yogsothothcharm");
	/** Metadata Item, 0 = empty, 1 = range, 2 = duration, 3 = power */
	public static Item shub_niggurath_charm = getItem("shubniggurathcharm");
	public static Item hastur_engraved_coin = getItem("hasturcoin");
	public static Item azathoth_engraved_coin = getItem("azathothcoin");
	public static Item nyarlathotep_engraved_coin = getItem("nyarlathotepcoin");
	public static Item yog_sothoth_engraved_coin = getItem("yogsothothcoin");
	public static Item shub_niggurath_engraved_coin = getItem("shubniggurathcoin");
	public static Item hastur_engraving = getItem("engraving_hastur");
	public static Item azathoth_engraving = getItem("engraving_azathoth");
	public static Item nyarlathotep_engraving = getItem("engraving_nyarlathotep");
	public static Item yog_sothoth_engraving = getItem("engraving_yogsothoth");
	public static Item shub_niggurath_engraving = getItem("engraving_shubniggurath");
	public static Item essence_of_the_gatekeeper = getItem("gatekeeperessence");
	//	public static Item shadow_titan_armor_plate = getItem("shadowplate");

	private static Item getItem(String name){
		return GameRegistry.findItem("abyssalcraft", name);
	}
}
