package rtg.village.standard;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import rtg.village.StructureVillagePiecesRTG;

public class Church extends StructureVillagePiecesRTG.Village
{
	public Church() {
	}

	public Church(StructureVillagePiecesRTG.Start p_i2102_1_, int p_i2102_2_, Random p_i2102_3_, StructureBoundingBox p_i2102_4_, int p_i2102_5_) {
		super(p_i2102_1_, p_i2102_2_);
		this.coordBaseMode = p_i2102_5_;
		this.boundingBox = p_i2102_4_;
	}

	public static Church getPiece(StructureVillagePiecesRTG.Start p_74919_0_, List p_74919_1_, Random p_74919_2_, int p_74919_3_, int p_74919_4_, int p_74919_5_, int p_74919_6_, int p_74919_7_) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74919_3_, p_74919_4_, p_74919_5_, 0, 0, 0, 5, 12, 9, p_74919_6_);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74919_1_, structureboundingbox) == null ? new Church(p_74919_0_, p_74919_7_, p_74919_2_, structureboundingbox, p_74919_6_) : null;
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

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 12 - 1, 0);
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 3, 7, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 1, 3, 9, 3, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 0, 3, 0, 8, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 0, 3, 10, 0, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 10, 3, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 4, 1, 1, 4, 10, 3, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 4, 0, 4, 7, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 4, 0, 4, 4, 4, 7, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 8, 3, 4, 8, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 4, 3, 10, 4, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 5, 3, 5, 7, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 9, 0, 4, 9, 4, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 0, 4, 4, 4, Blocks.cobblestone, Blocks.cobblestone, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 0, 11, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 11, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 2, 11, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 2, 11, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 1, 1, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 1, 1, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 2, 1, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 3, 1, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 3, 1, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 1, 1, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 1, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 3, 1, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 1), 1, 2, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 0), 3, 2, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 3, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 3, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 6, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 7, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 6, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 7, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 6, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 7, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 6, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 7, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 3, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 4, 3, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 3, 8, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 2, 4, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 1, 4, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 3, 4, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 2, 4, 5, p_74875_3_);
		int i = this.getMetadataWithOffset(Blocks.ladder, 4);
		int j;

		for(j = 1; j <= 9; ++j) {
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.ladder, i, 3, j, 3, p_74875_3_);
		}

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 1, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 2, 0, p_74875_3_);
		this.placeDoorAtCurrentPosition(p_74875_1_, p_74875_3_, p_74875_2_, 2, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));

		if(this.getBlockAtCurrentPosition(p_74875_1_, 2, 0, -1, p_74875_3_).getMaterial() == Material.air && this.getBlockAtCurrentPosition(p_74875_1_, 2, -1, -1, p_74875_3_).getMaterial() != Material.air) {
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 0, -1, p_74875_3_);
		}

		for(j = 0; j < 9; ++j) {
			for(int k = 0; k < 5; ++k) {
				this.clearCurrentPositionBlocksUpwards(p_74875_1_, k, 12, j, p_74875_3_);
				this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, k, -1, j, p_74875_3_);
			}
		}

		this.spawnVillagers(p_74875_1_, p_74875_3_, 2, 1, 2, 1);
		return true;
	}

	/**
	 * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
	 */
	protected int getVillagerType(int p_74888_1_) {
		return 2;
	}
}