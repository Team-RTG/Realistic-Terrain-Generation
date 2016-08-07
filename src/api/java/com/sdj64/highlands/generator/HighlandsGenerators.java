package com.sdj64.highlands.generator;

import com.sdj64.highlands.block.BlockHighlandsLeaves;
import com.sdj64.highlands.block.HighlandsBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class HighlandsGenerators {

	/*
	 * List of trees:
	 * Aspen: medium tree with straight branches.
	 * Great Oak: oak big tree but even bigger, with 2x2 trunk.
	 * Poplar: tall thin tree.
	 * Eucalyptus: tall tree with a unique crown shape.
	 * Highlands Shrub: like jungle shrubs but with oak or spruce.
	 * Palm: palm leaves come out from the top in 8 directions.
	 * Fir: medium pine tree that can occasionally grow with a larger 2x2 trunk.
	 * Redwood: giant pine tree with a circular trunk.
	 * Bamboo: thin stem that gets more leafy at the top.
	 */
	public static final WorldGenAbstractTree aspenGen = new WorldGenTreeAspen(HighlandsBlocks.leaves[0], HighlandsBlocks.woods[0], flmeta(0), 0,  10, 15, false);
	public static final WorldGenAbstractTree aspenSapling = new WorldGenTreeAspen(HighlandsBlocks.leaves[0], HighlandsBlocks.woods[0], flmeta(0), 0, 10, 15, true);
	public static final WorldGenAbstractTree greatOakGen = new WorldGenGreatOak(Blocks.leaves, Blocks.log, 0, 0, 2, 20, false);
	public static final WorldGenAbstractTree greatOakSapling = new WorldGenGreatOak(Blocks.leaves, Blocks.log, 0, 0, 2, 20, true);
	public static final WorldGenAbstractTree poplarGen = new WorldGenTreePoplar(HighlandsBlocks.leaves[1], HighlandsBlocks.woods[1], flmeta(1), 0, 7, 11, false);
	public static final WorldGenAbstractTree poplarSapling = new WorldGenTreePoplar(HighlandsBlocks.leaves[1], HighlandsBlocks.woods[1], flmeta(1), 0, 7, 11, true);
	public static final WorldGenAbstractTree eucalyptusGen = new WorldGenTreeEuca(HighlandsBlocks.leaves[2], HighlandsBlocks.woods[2], flmeta(2), 0, 14, 22, false);
	public static final WorldGenAbstractTree eucalyptusSapling = new WorldGenTreeEuca(HighlandsBlocks.leaves[2], HighlandsBlocks.woods[2], flmeta(2), 0, 14, 22, true);
	public static final WorldGenAbstractTree palmGen = new WorldGenTreePalm(HighlandsBlocks.leaves[3], HighlandsBlocks.woods[3], flmeta(3), 0, 6, 12, false);
	public static final WorldGenAbstractTree palmSapling = new WorldGenTreePalm(HighlandsBlocks.leaves[3], HighlandsBlocks.woods[3], flmeta(3), 0, 6, 12, true);
	public static final WorldGenAbstractTree firGen = new WorldGenTreeFir(HighlandsBlocks.leaves[4], HighlandsBlocks.woods[4], flmeta(4), 0, 9, 20, false);
	public static final WorldGenAbstractTree firSapling = new WorldGenTreeFir(HighlandsBlocks.leaves[4], HighlandsBlocks.woods[4], flmeta(4), 0, 9, 20, true);
	public static final WorldGenAbstractTree redwoodGen = new WorldGenTreeRedwood(HighlandsBlocks.leaves[5], HighlandsBlocks.woods[5], flmeta(5), 0, 27, 38, false);
	public static final WorldGenAbstractTree redwoodSapling = new WorldGenTreeRedwood(HighlandsBlocks.leaves[5], HighlandsBlocks.woods[5], flmeta(5), 0, 27, 38, true);
	public static final WorldGenAbstractTree shrubGen = new WorldGenHighlandsShrub(Blocks.leaves, Blocks.log, 1, 1, false);
	public static final WorldGenAbstractTree shrubSapling = new WorldGenHighlandsShrub(Blocks.leaves, Blocks.log, 1, 1, true);
	public static final WorldGenAbstractTree shrub2Gen = new WorldGenHighlandsShrub(Blocks.leaves, Blocks.log, 0, 0, false);
	public static final WorldGenAbstractTree shrub2Sapling = new WorldGenHighlandsShrub(Blocks.leaves, Blocks.log, 0, 0, true);
	public static final WorldGenAbstractTree bambooGen = new WorldGenTreeBamboo(HighlandsBlocks.leaves[6], HighlandsBlocks.woods[6], flmeta(6), 0, 6, 12, false);
	public static final WorldGenAbstractTree bambooSapling = new WorldGenTreeBamboo(HighlandsBlocks.leaves[6], HighlandsBlocks.woods[6], flmeta(6), 0, 6, 12, true);
	public static final WorldGenAbstractTree deadTreeGen = new WorldGenDeadTree(Blocks.air, Blocks.log, 0, 0, 1, 12, false);
	
	/*
	 * List of plant generators:
	 * blueSwampFlower: small blue flowers on vertical stalks
	 * cattail: brown straight stalks with tufts on top
	 * greenLeaf: green leafy tropical ferns
	 * blueberryBush: bushes with blue berries
	 * raspberryBush: bushes with pinkish red berries
	 * lavender: purple bush
	 * cotton: bushes with white tufts
	 * thornbush: bushes with brown thorns that hurt when you walk through
	 * duneGrass: yellow and green grass
	 * empty: for when you want nothing to generate...
	 * mcOrchid: Minecraft's blue orchid
	 * mcAllium: Minecraft's purple allium
	 * mcBluet: Minecraft's Azure Bluet (white colored, not blue)
	 * mcRTulip: Minecraft's red tulip
	 * mcOTulip: Minecraft's orange tulip
	 * mcWTulip: Minecraft's white tulip
	 * mcDaisy: Minecraft's white daisy
	 */
	public static final WorldGenPlants blueSwampFlower = new WorldGenPlants(HighlandsBlocks.plants[0].getDefaultState(), 10);
	public static final WorldGenPlants cattail = new WorldGenPlants(HighlandsBlocks.plants[1].getDefaultState(), 24);
	public static final WorldGenPlants cotton = new WorldGenPlants(HighlandsBlocks.plants[2].getDefaultState(), 12);
	public static final WorldGenPlants blueberryBush = new WorldGenPlants(HighlandsBlocks.plants[3].getDefaultState(), 12);
	public static final WorldGenPlants raspberryBush = new WorldGenPlants(HighlandsBlocks.plants[4].getDefaultState(), 12);
	public static final WorldGenPlants thornbush = new WorldGenPlants(HighlandsBlocks.plants[5].getDefaultState(), 14);
	public static final WorldGenPlants lavender = new WorldGenPlants(HighlandsBlocks.plants[6].getDefaultState(), 8);
	public static final WorldGenPlants greenLeaf = new WorldGenPlants(HighlandsBlocks.plants[7].getDefaultState(), 18);
	public static final WorldGenPlants duneGrass = new WorldGenPlants(HighlandsBlocks.plants[8].getDefaultState(), 32);
	public static final WorldGenPlants empty = new WorldGenPlants(Blocks.air.getDefaultState(), 0);
	
	
	public static final WorldGenPlants mcOrchid = new WorldGenPlants(Blocks.red_flower.getStateFromMeta(1), 8);
	public static final WorldGenPlants mcAllium = new WorldGenPlants(Blocks.red_flower.getStateFromMeta(2), 8);
	public static final WorldGenPlants mcBluet = new WorldGenPlants(Blocks.red_flower.getStateFromMeta(3), 8);
	public static final WorldGenPlants mcRTulip = new WorldGenPlants(Blocks.red_flower.getStateFromMeta(4), 8);
	public static final WorldGenPlants mcOTulip = new WorldGenPlants(Blocks.red_flower.getStateFromMeta(5), 8);
	public static final WorldGenPlants mcWTulip = new WorldGenPlants(Blocks.red_flower.getStateFromMeta(6), 8);
	public static final WorldGenPlants mcDaisy = new WorldGenPlants(Blocks.red_flower.getStateFromMeta(8), 8);
	
	
	
	/*
	 * List of ore generators:
	 * stoneInDirt: generates stone in large patches in dirt/grass
	 * sandInDirt: generates orange sand in large patches in dirt/grass
	 * hlwater: generates ore-like pockets of water underground
	 * hlice: generates ore-like pockets of Packed Ice underground
	 * hlsand: generates sand underground in large patches
	 */
	public static final WorldGenMinable2 stoneInDirt = new WorldGenMinable2(Blocks.stone.getDefaultState(), 64, Blocks.dirt.getDefaultState());
	public static final WorldGenMinable2 sandInDirt = new WorldGenMinable2(Blocks.sand.getStateFromMeta(1), 64, Blocks.dirt.getDefaultState());
	public static final WorldGenMinable2 hlwater = new WorldGenMinable2(Blocks.water.getDefaultState(), 10, true);
	public static final WorldGenMinable2 hlice = new WorldGenMinable2(Blocks.packed_ice.getDefaultState(), 14, true);
	public static final WorldGenMinable2 hlsand = new WorldGenMinable2(Blocks.sand.getDefaultState(), 30, true);
	
	
	
	//find leaf meta for tree leaves (check-decay and decayable both true)
	//not really working right now.  0 works so...
	private static int flmeta(int i) {
		return 0;//HighlandsBlocks.leaves[i].getMetaFromState(
				//HighlandsBlocks.leaves[i].getDefaultState().withProperty(BlockHighlandsLeaves.DECAYABLE, true).withProperty(BlockHighlandsLeaves.CHECK_DECAY, true));
	}
	
	
}
