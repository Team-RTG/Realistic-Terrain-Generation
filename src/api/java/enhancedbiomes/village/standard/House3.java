package enhancedbiomes.village.standard;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import enhancedbiomes.village.StructureVillagePiecesEB;

public class House3 extends StructureVillagePiecesEB.Village
{
	public House3() {}

	public House3(StructureVillagePiecesEB.Start p_i2106_1_, int p_i2106_2_, Random p_i2106_3_, StructureBoundingBox p_i2106_4_, int p_i2106_5_)
	{
		super(p_i2106_1_, p_i2106_2_);
		this.coordBaseMode = p_i2106_5_;
		this.boundingBox = p_i2106_4_;
	}

	public static House3 getPiece(StructureVillagePiecesEB.Start p_74921_0_, List p_74921_1_, Random p_74921_2_, int p_74921_3_, int p_74921_4_, int p_74921_5_, int p_74921_6_, int p_74921_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74921_3_, p_74921_4_, p_74921_5_, 0, 0, 0, 9, 7, 12, p_74921_6_);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74921_1_, structureboundingbox) == null ? new House3(p_74921_0_, p_74921_7_, p_74921_2_, structureboundingbox, p_74921_6_) : null;
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
	 * Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
	{
		if (this.field_143015_k < 0)
		{
			this.field_143015_k = this.getAverageGroundLevel(p_74875_1_, p_74875_3_);

			if (this.field_143015_k < 0)
			{
				return true;
			}

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 1, 7, 4, 4, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 1, 6, 8, 4, 10, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 0, 5, 8, 0, 10, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 7, 0, 4, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 0, 3, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 8, 0, 0, 8, 3, 10, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 0, 7, 2, 0, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 5, 2, 1, 5, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 2, 0, 6, 2, 3, 10, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 0, 10, 7, 3, 10, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 2, 0, 7, 3, 0, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 2, 5, 2, 3, 5, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 1, 8, 4, 1, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 4, 3, 4, 4, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 5, 2, 8, 5, 3, Blocks.planks, Blocks.planks, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 0, 4, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 0, 4, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 8, 4, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 8, 4, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 8, 4, 4, p_74875_3_);
		int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
		int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
		int k;
		int l;

		for (k = -1; k <= 2; ++k)
		{
			for (l = 0; l <= 8; ++l)
			{
				this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, i, l, 4 + k, k, p_74875_3_);

				if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6))
				{
					this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, j, l, 4 + k, 5 - k, p_74875_3_);
				}
			}
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 4, 5, 3, 4, 10, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 7, 4, 2, 7, 4, 10, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 4, 5, 4, 4, 5, 10, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 5, 4, 6, 5, 10, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 5, 6, 3, 5, 6, 10, Blocks.planks, Blocks.planks, false);
		k = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
		int i1;

		for (l = 4; l >= 1; --l)
		{
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, l, 2 + l, 7 - l, p_74875_3_);

			for (i1 = 8 - l; i1 <= 10; ++i1)
			{
				this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, k, l, 2 + l, i1, p_74875_3_);
			}
		}

		l = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 6, 6, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 7, 5, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, l, 6, 6, 4, p_74875_3_);
		int j1;

		for (i1 = 6; i1 <= 8; ++i1)
		{
			for (j1 = 5; j1 <= 10; ++j1)
			{
				this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, l, i1, 12 - i1, j1, p_74875_3_);
			}
		}

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 0, 2, 1, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 0, 2, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 5, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 6, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 8, 2, 1, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 2, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 8, 2, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 8, 2, 5, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 8, 2, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 2, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 8, 2, 8, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 8, 2, 9, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 2, 2, 6, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 2, 7, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 2, 2, 8, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 2, 2, 9, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 4, 10, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 5, 4, 10, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 6, 4, 10, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 5, 5, 10, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 1, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 2, 3, 1, p_74875_3_);
		this.placeDoorAtCurrentPosition(p_74875_1_, p_74875_3_, p_74875_2_, 2, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, -1, 3, 2, -1, Blocks.air, Blocks.air, false);

		if (this.getBlockAtCurrentPosition(p_74875_1_, 2, 0, -1, p_74875_3_).getMaterial() == Material.air && this.getBlockAtCurrentPosition(p_74875_1_, 2, -1, -1, p_74875_3_).getMaterial() != Material.air)
		{
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 0, -1, p_74875_3_);
		}

		for (i1 = 0; i1 < 5; ++i1)
		{
			for (j1 = 0; j1 < 9; ++j1)
			{
				this.clearCurrentPositionBlocksUpwards(p_74875_1_, j1, 7, i1, p_74875_3_);
				this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, j1, -1, i1, p_74875_3_);
			}
		}

		for (i1 = 5; i1 < 11; ++i1)
		{
			for (j1 = 2; j1 < 9; ++j1)
			{
				this.clearCurrentPositionBlocksUpwards(p_74875_1_, j1, 7, i1, p_74875_3_);
				this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, j1, -1, i1, p_74875_3_);
			}
		}

		this.spawnVillagers(p_74875_1_, p_74875_3_, 4, 1, 2, 2);
		return true;
	}
}