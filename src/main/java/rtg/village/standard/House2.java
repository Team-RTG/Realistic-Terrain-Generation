package rtg.village.standard;

import static net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import rtg.village.StructureVillagePiecesRTG;

public class House2 extends StructureVillagePiecesRTG.Village
{
	/** List of items that Village's Blacksmith chest can contain. */
	public static final WeightedRandomChestContent[] villageBlacksmithChestContents = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)};
	private boolean hasMadeChest;

	public House2() {
	}

	public House2(StructureVillagePiecesRTG.Start p_i2103_1_, int p_i2103_2_, Random p_i2103_3_, StructureBoundingBox p_i2103_4_, int p_i2103_5_) {
		super(p_i2103_1_, p_i2103_2_);
		this.coordBaseMode = p_i2103_5_;
		this.boundingBox = p_i2103_4_;
	}

	public static House2 getPiece(StructureVillagePiecesRTG.Start p_74915_0_, List p_74915_1_, Random p_74915_2_, int p_74915_3_, int p_74915_4_, int p_74915_5_, int p_74915_6_, int p_74915_7_) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74915_3_, p_74915_4_, p_74915_5_, 0, 0, 0, 10, 6, 7, p_74915_6_);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74915_1_, structureboundingbox) == null ? new House2(p_74915_0_, p_74915_7_, p_74915_2_, structureboundingbox, p_74915_6_) : null;
	}

	protected void func_143012_a(NBTTagCompound p_143012_1_) {
		super.func_143012_a(p_143012_1_);
		p_143012_1_.setBoolean("Chest", this.hasMadeChest);
	}

	protected void func_143011_b(NBTTagCompound p_143011_1_) {
		super.func_143011_b(p_143011_1_);
		this.hasMadeChest = p_143011_1_.getBoolean("Chest");
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
		if(this.field_143015_k < 0) {
			this.field_143015_k = this.getAverageGroundLevel(p_74875_1_, p_74875_3_);

			if(this.field_143015_k < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 9, 4, 6, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 9, 0, 6, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 0, 9, 4, 6, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 5, 0, 9, 5, 6, Blocks.stone_slab, Blocks.stone_slab, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 1, 8, 5, 5, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 4, 0, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 1, 0, 3, 4, 0, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 6, 0, 4, 6, Blocks.log, Blocks.log, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 3, 3, 1, p_74875_3_);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 1, 2, 3, 3, 2, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 4, 1, 3, 5, 3, 3, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 5, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 6, 5, 3, 6, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 5, 1, 0, 5, 3, 0, Blocks.fence, Blocks.fence, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 9, 1, 0, 9, 3, 0, Blocks.fence, Blocks.fence, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 1, 4, 9, 4, 6, Blocks.cobblestone, Blocks.cobblestone, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.flowing_lava, 0, 7, 1, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.flowing_lava, 0, 8, 1, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.iron_bars, 0, 9, 2, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.iron_bars, 0, 9, 2, 4, p_74875_3_);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 7, 2, 4, 8, 2, 5, Blocks.air, Blocks.air, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 6, 1, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.furnace, 0, 6, 2, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.furnace, 0, 6, 3, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 8, 1, 1, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 2, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 2, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 2, 1, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wooden_pressure_plate, 0, 2, 2, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 1, 1, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 2, 1, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 1, 1, 4, p_74875_3_);
		int i;
		int j;

		if(!this.hasMadeChest) {
			i = this.getYWithOffset(1);
			j = this.getXWithOffset(5, 5);
			int k = this.getZWithOffset(5, 5);

			if(p_74875_3_.isVecInside(j, i, k)) {
				this.hasMadeChest = true;
				this.generateStructureChestContents(p_74875_1_, p_74875_3_, p_74875_2_, 5, 1, 5, ChestGenHooks.getItems(VILLAGE_BLACKSMITH, p_74875_2_), ChestGenHooks.getCount(VILLAGE_BLACKSMITH, p_74875_2_));
			}
		}

		for(i = 6; i <= 8; ++i) {
			if(this.getBlockAtCurrentPosition(p_74875_1_, i, 0, -1, p_74875_3_).getMaterial() == Material.air && this.getBlockAtCurrentPosition(p_74875_1_, i, -1, -1, p_74875_3_).getMaterial() != Material.air) {
				this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), i, 0, -1, p_74875_3_);
			}
		}

		for(i = 0; i < 7; ++i) {
			for(j = 0; j < 10; ++j) {
				this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 6, i, p_74875_3_);
				this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, j, -1, i, p_74875_3_);
			}
		}

		this.spawnVillagers(p_74875_1_, p_74875_3_, 7, 1, 1, 1);
		return true;
	}

	/**
	 * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
	 */
	protected int getVillagerType(int p_74888_1_) {
		return 3;
	}
}