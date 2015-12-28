package enhancedbiomes.village.standard;

import java.util.List;
import java.util.Random;

import enhancedbiomes.village.StructureVillagePiecesEB;
import enhancedbiomes.village.StructureVillagePiecesEB.Start;
import enhancedbiomes.village.StructureVillagePiecesEB.Village;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class WoodHut extends StructureVillagePiecesEB.Village
{
	private boolean isTallHouse;
	private int tablePosition;
	public WoodHut() {}

	public WoodHut(StructureVillagePiecesEB.Start p_i2101_1_, int p_i2101_2_, Random p_i2101_3_, StructureBoundingBox p_i2101_4_, int p_i2101_5_)
	{
		super(p_i2101_1_, p_i2101_2_);
		this.coordBaseMode = p_i2101_5_;
		this.boundingBox = p_i2101_4_;
		this.isTallHouse = p_i2101_3_.nextBoolean();
		this.tablePosition = p_i2101_3_.nextInt(3);
	}

	protected void func_143012_a(NBTTagCompound p_143012_1_)
	{
		super.func_143012_a(p_143012_1_);
		p_143012_1_.setInteger("T", this.tablePosition);
		p_143012_1_.setBoolean("C", this.isTallHouse);
	}

	protected void func_143011_b(NBTTagCompound p_143011_1_)
	{
		super.func_143011_b(p_143011_1_);
		this.tablePosition = p_143011_1_.getInteger("T");
		this.isTallHouse = p_143011_1_.getBoolean("C");
	}

	public static WoodHut getPiece(StructureVillagePiecesEB.Start p_74908_0_, List p_74908_1_, Random p_74908_2_, int p_74908_3_, int p_74908_4_, int p_74908_5_, int p_74908_6_, int p_74908_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74908_3_, p_74908_4_, p_74908_5_, 0, 0, 0, 4, 6, 5, p_74908_6_);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74908_1_, structureboundingbox) == null ? new WoodHut(p_74908_0_, p_74908_7_, p_74908_2_, structureboundingbox, p_74908_6_) : null;
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

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 5, 4, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 3, 0, 4, Blocks.cobblestone, Blocks.cobblestone, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 2, 0, 3, Blocks.dirt, Blocks.dirt, false);

		if (this.isTallHouse)
		{
			this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 4, 1, 2, 4, 3, Blocks.log, Blocks.log, false);
		}
		else
		{
			this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 5, 1, 2, 5, 3, Blocks.log, Blocks.log, false);
		}

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 4, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 2, 4, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 4, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 2, 4, 4, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 0, 4, 1, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 0, 4, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 0, 4, 3, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 3, 4, 1, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 3, 4, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 3, 4, 3, p_74875_3_);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 3, 0, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 1, 0, 3, 3, 0, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 4, 0, 3, 4, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 1, 4, 3, 3, 4, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 1, 1, 3, 3, 3, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 4, 2, 3, 4, Blocks.planks, Blocks.planks, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 0, 2, 2, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glass_pane, 0, 3, 2, 2, p_74875_3_);

		if (this.tablePosition > 0)
		{
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, this.tablePosition, 1, 3, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wooden_pressure_plate, 0, this.tablePosition, 2, 3, p_74875_3_);
		}

		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 1, 1, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 1, 2, 0, p_74875_3_);
		this.placeDoorAtCurrentPosition(p_74875_1_, p_74875_3_, p_74875_2_, 1, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));

		if (this.getBlockAtCurrentPosition(p_74875_1_, 1, 0, -1, p_74875_3_).getMaterial() == Material.air && this.getBlockAtCurrentPosition(p_74875_1_, 1, -1, -1, p_74875_3_).getMaterial() != Material.air)
		{
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 1, 0, -1, p_74875_3_);
		}

		for (int i = 0; i < 5; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 6, i, p_74875_3_);
				this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, j, -1, i, p_74875_3_);
			}
		}

		this.spawnVillagers(p_74875_1_, p_74875_3_, 1, 1, 2, 1);
		return true;
	}
}