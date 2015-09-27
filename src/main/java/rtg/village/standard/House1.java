package rtg.village.standard;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import rtg.village.StructureVillagePiecesRTG;

					/** library */

public class House1 extends StructureVillagePiecesRTG.Village 
{
	public House1() {
	}

	public House1(StructureVillagePiecesRTG.Start p_i2094_1_, int p_i2094_2_, Random p_i2094_3_, StructureBoundingBox p_i2094_4_, int p_i2094_5_) {
		super(p_i2094_1_, p_i2094_2_);
		this.coordBaseMode = p_i2094_5_;
		this.boundingBox = p_i2094_4_;
	}

	public static House1 getPiece(StructureVillagePiecesRTG.Start p_74898_0_, List p_74898_1_, Random p_74898_2_, int p_74898_3_, int p_74898_4_, int p_74898_5_, int p_74898_6_, int p_74898_7_) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74898_3_, p_74898_4_, p_74898_5_, 0, 0, 0, 9, 9, 6, p_74898_6_);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74898_1_, structureboundingbox) == null ? new House1(p_74898_0_, p_74898_7_, p_74898_2_, structureboundingbox, p_74898_6_) : null;
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

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 9 - 1, 0);
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 1, 7, 5, 4, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 8, 0, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 5, 0, 8, 5, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 6, 1, 8, 6, 4, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 7, 2, 8, 7, 3, Blocks.cobblestone, Blocks.cobblestone, false);
		int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
		int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
		int k;
		int l;

		for(k = -1; k <= 2; ++k) {
			for(l = 0; l <= 8; ++l) {
				this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, i, l, 6 + k, k, p_74875_3_);
				this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, j, l, 6 + k, 5 - k, p_74875_3_);
			}
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 1, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 5, 8, 1, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 8, 1, 0, 8, 1, 4, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 1, 0, 7, 1, 0, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 4, 0, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 2, 5, 0, 4, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 8, 2, 5, 8, 4, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 8, 2, 0, 8, 4, 0, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 2, 1, 0, 4, 4, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 2, 5, 7, 4, 5, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 8, 2, 1, 8, 4, 4, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 2, 0, 7, 4, 0, Blocks.planks, Blocks.planks, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 5, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 6, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 3, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 5, 3, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 6, 3, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 3, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 3, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 2, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 3, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 3, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 2, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 3, 2, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 5, 2, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 6, 2, 5, p_74875_3_);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 4, 1, 7, 4, 1, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 4, 4, 7, 4, 4, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 3, 4, 7, 3, 4, Blocks.bookshelf, Blocks.bookshelf, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 7, 1, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7, 1, 3, p_74875_3_);
		k = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, k, 6, 1, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, k, 5, 1, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, k, 4, 1, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, k, 3, 1, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 6, 1, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wooden_pressure_plate, 0, 6, 2, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 1, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wooden_pressure_plate, 0, 4, 2, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.crafting_table, 0, 7, 1, 1, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 1, 1, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 1, 2, 0, p_74875_3_);
		this.placeDoorAtCurrentPosition(p_74875_1_, p_74875_3_, p_74875_2_, 1, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));

		if(this.getBlockAtCurrentPosition(p_74875_1_, 1, 0, -1, p_74875_3_).getMaterial() == Material.air && this.getBlockAtCurrentPosition(p_74875_1_, 1, -1, -1, p_74875_3_).getMaterial() != Material.air) {
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 1, 0, -1, p_74875_3_);
		}

		for(l = 0; l < 6; ++l) {
			for(int i1 = 0; i1 < 9; ++i1) {
				this.clearCurrentPositionBlocksUpwards(p_74875_1_, i1, 9, l, p_74875_3_);
				this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, i1, -1, l, p_74875_3_);
			}
		}

		this.spawnVillagers(p_74875_1_, p_74875_3_, 2, 1, 2, 1);
		return true;
	}

	/**
	 * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
	 */
	protected int getVillagerType(int p_74888_1_) {
		return 1;
	}
}