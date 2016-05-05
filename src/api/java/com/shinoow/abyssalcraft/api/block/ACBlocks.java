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
package com.shinoow.abyssalcraft.api.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Contains all blocks added in AbyssalCraft
 * 
 * @author shinoow
 *
 */
public class ACBlocks {

	public static Block darkstone = getBlock("darkstone");
	public static Block darkstone_cobblestone = getBlock("darkstone_cobble");
	public static Block darkstone_brick = getBlock("darkstone_brick");
	public static Block glowing_darkstone_bricks = getBlock("dsglow");
	public static Block darkstone_brick_slab = getBlock("darkbrickslab1");
	public static Block darkstone_cobblestone_slab = getBlock("darkcobbleslab1");
	public static Block darklands_grass = getBlock("darkgrass");
	public static Block darkstone_brick_stairs = getBlock("dbstairs");
	public static Block darkstone_cobblestone_stairs = getBlock("dcstairs");
	public static Block darklands_oak_leaves = getBlock("dltleaves");
	public static Block darklands_oak_wood = getBlock("dltlog");
	public static Block darklands_oak_sapling = getBlock("dltsapling");
	public static Block abyssal_stone = getBlock("abystone");
	public static Block abyssal_stone_brick = getBlock("abybrick");
	public static Block abyssal_stone_brick_slab = getBlock("abyslab1");
	public static Block abyssal_stone_btick_stairs = getBlock("abystairs");
	public static Block coralium_ore = getBlock("coraliumore");
	public static Block abyssalnite_ore= getBlock("abyore");
	public static Block abyssal_stone_brick_fence = getBlock("abyfence");
	public static Block darkstone_cobblestone_wall = getBlock("dscwall");
	public static Block oblivion_deathbomb = getBlock("odb");
	public static Block block_of_abyssalnite = getBlock("abyblock");
	public static Block coralium_infused_stone = getBlock("coraliumstone");
	public static Block odb_core = getBlock("odbcore");
	public static Block wooden_crate = getBlock("Crate");
	public static Block abyssal_gateway = getBlock("portal");
	public static Block darkstone_slab = getBlock("darkstoneslab1");
	public static Block darkstone_doubleslab = getBlock("darkstoneslab2");
	public static Block coralium_fire = getBlock("coraliumfire");
	public static Block darkstone_button = getBlock("dsbutton");
	public static Block darkstone_pressure_plate = getBlock("dspplate");
	public static Block darklands_oak_planks = getBlock("dltplank");
	public static Block darklands_oak_button = getBlock("dltbutton");
	public static Block darklands_oak_pressure_plate = getBlock("dltpplate");
	public static Block darklans_oak_stairs = getBlock("dltstairs");
	public static Block darklands_oak_slab = getBlock("dltslab1");
	public static Block liquid_coralium = getBlock("cwater");
	public static Block block_of_coralim = getBlock("corblock");
	public static Block dreadlands_infused_powerstone = getBlock("psdl");
	public static Block abyssal_coralium_ore = getBlock("abycorore");
	public static Block abyssal_stone_button = getBlock("abybutton");
	public static Block abyssal_stone_pressure_plate = getBlock("abypplate");
	public static Block darkstone_brick_fence = getBlock("dsbfence");
	public static Block darklands_oak_fence = getBlock("dltfence");
	public static Block dreadstone = getBlock("dreadstone");
	public static Block abyssalnite_stone = getBlock("abydreadstone");
	public static Block dreadland_abyssalnite_ore = getBlock("abydreadore");
	public static Block dreaded_abyssanite_ore = getBlock("dreadore");
	public static Block dreadstone_brick = getBlock("dreadbrick");
	public static Block abyssalnite_stone_brick = getBlock("abydreadbrick");
	public static Block dreadlands_grass = getBlock("dreadgrass");
	public static Block dreadlands_log = getBlock("dreadlog");
	public static Block dreadlands_leaves = getBlock("dreadleaves");
	public static Block dreadlands_sapling = getBlock("dreadsapling");
	public static Block dreadlands_planks = getBlock("dreadplanks");
	public static Block dreaded_gateway = getBlock("dreadportal");
	public static Block dreaded_fire = getBlock("dreadfire");
	public static Block depths_ghoul_head = getBlock("dghead");
	public static Block pete_head = getBlock("phead");
	public static Block mr_wilson_head = getBlock("whead");
	public static Block dr_orange_head = getBlock("ohead");
	public static Block dreadstone_brick_stairs = getBlock("dreadbrickstairs");
	public static Block dreadstone_brick_fence = getBlock("dreadbrickfence");
	public static Block dreadstone_brick_slab = getBlock("dreadbrickslab1");
	public static Block abyssalnite_stone_brick_stairs= getBlock("abydreadbrickstairs");
	public static Block abyssalnite_stone_brick_fence = getBlock("abydreadbrickfence");
	public static Block abyssalnite_stone_brick_slab = getBlock("abydreadbrick1");
	public static Block liquid_antimatter = getBlock("antiwater");
	public static Block coralium_stone = getBlock("cstone");
	public static Block coralium_stone_brick = getBlock("cstonebrick");
	public static Block coralium_stone_brick_fence = getBlock("cstonebrickfence");
	public static Block coralium_stone_brick_slab = getBlock("cstonebrickslab1");
	public static Block coralium_stone_brick_stairs = getBlock("cstonebrickstairs");
	public static Block coralium_stone_button = getBlock("cstonebutton");
	public static Block coralium_stone_pressure_plate = getBlock("cstonepplate");
	public static Block chagaroth_altar_top = getBlock("dreadaltartop");
	public static Block chagaroth_altar_bottom = getBlock("dreadaltarbottom");
	public static Block crystallizer_idle = getBlock("crystallizer");
	public static Block crystallizer_active = getBlock("crystallizer_on");
	public static Block block_of_dreadium = getBlock("dreadiumblock");
	public static Block transmutator_idle = getBlock("transmutator");
	public static Block transmutator_active = getBlock("transmutator_on");
	public static Block dreadguard_spawner = getBlock("dreadguardspawner");
	public static Block chagaroth_spawner = getBlock("chagarothspawner");
	public static Block dreadlands_wood_fence = getBlock("drtfence");
	public static Block nitre_ore = getBlock("nitreore");
	public static Block abyssal_iron_ore = getBlock("abyiroore");
	public static Block abyssal_gold_ore = getBlock("abygolore");
	public static Block abyssal_diamond_ore = getBlock("abydiaore");
	public static Block abyssal_nitre_ore = getBlock("abynitore");
	public static Block abyssal_tin_ore = getBlock("abycopore");
	public static Block abyssal_copper_ore = getBlock("abytinore");
	public static Block pearlescent_coralium_ore = getBlock("abypcorore");
	public static Block liquified_coralium_ore = getBlock("abylcorore");
	public static Block solid_lava = getBlock("solidlava");
	public static Block ethaxium = getBlock("ethaxium");
	/** Metadata block, 0 = Ethaxium Brick, 1 = Chiseled Ethaxium Brick */
	public static Block ethaxium_brick = getBlock("ethaxiumbrick");
	public static Block ethaxium_pillar = getBlock("ethaxiumpillar");
	public static Block ethaxium_brick_stairs = getBlock("ethaxiumbrickstairs");
	public static Block ethaxium_brick_slab = getBlock("ethaxiumbrickslab1");
	public static Block ethaxium_brick_fence = getBlock("ethaxiumbrickfence");
	public static Block omothol_stone = getBlock("omotholstone");
	public static Block block_of_ethaxium = getBlock("ethaxiumblock");
	public static Block omothol_gateway = getBlock("omotholportal");
	public static Block omothol_fire = getBlock("omotholfire");
	public static Block engraver = getBlock("engraver");
	public static Block materializer = getBlock("materializer");
	/** Metadata block, 0 = Dark Ethaxium Brick, 1 = Chiseled Dark Ethaxium Brick */
	public static Block dark_ethaxium_brick = getBlock("darkethaxiumbrick");
	public static Block dark_ethaxium_pillar = getBlock("darkethaxiumpillar");
	public static Block dark_ethaxium_brick_stairs = getBlock("darkethaxiumbrickstairs");
	public static Block dark_ethaxium_brick_slab = getBlock("darkethaxiumbrickslab1");
	public static Block dark_ethaxium_brick_fence = getBlock("darkethaxiumbrickfence");
	public static Block ritual_altar = getBlock("ritualaltar");
	public static Block ritual_pedestal = getBlock("ritualpedestal");
	public static Block shoggoth_ooze = getBlock("shoggothblock");
	public static Block cthulhu_statue = getBlock("cthulhustatue");
	public static Block hastur_statue = getBlock("hasturstatue");
	public static Block jzahar_statue = getBlock("jzaharstatue");
	public static Block azathoth_statue = getBlock("azathothstatue");
	public static Block nyarlathotep_statue = getBlock("nyarlathotepstatue");
	public static Block yog_sothoth_statue = getBlock("yogsothothstatue");
	public static Block shub_niggurath_statue = getBlock("shubniggurathstatue");
	public static Block monolith_stone = getBlock("monolithstone");
	public static Block shoggoth_biomass = getBlock("shoggothbiomass");
	public static Block energy_pedestal = getBlock("energypedestal");
	public static Block monolith_pillar = getBlock("monolithpillar");
	public static Block sacrificial_altar = getBlock("sacrificialaltar");
	/** Metadata block, 0 = Overworld, 1 = Abyssal Wasteland, 2 = Dreadlands, 3 = Omothol */
	public static Block tiered_energy_pedestal = getBlock("tieredenergypedestal");
	/** Metadata block, 0 = Overworld, 1 = Abyssal Wasteland, 2 = Dreadlands, 3 = Omothol */
	public static Block tiered_sacrificial_altar = getBlock("tieredsacrificialaltar");
	public static Block jzahar_spawner = getBlock("jzaharspawner");
	public static Block minion_of_the_gatekeeper_spawner = getBlock("gatekeeperminionspawner");
	public static Block mimic_fire = getBlock("fire");

	private static Block getBlock(String name){
		return GameRegistry.findBlock("abyssalcraft", name);
	}
}