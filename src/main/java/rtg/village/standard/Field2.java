package rtg.village.standard;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import rtg.village.StructureVillagePiecesRTG;

public class Field2 extends StructureVillagePiecesRTG.Village
{
	/** First crop type for this field. */
	private Block cropTypeA;
	/** Second crop type for this field. */
	private Block cropTypeB;

	public Field2() {
	}

	public Field2(StructureVillagePiecesRTG.Start p_i2096_1_, int p_i2096_2_, Random p_i2096_3_, StructureBoundingBox p_i2096_4_, int p_i2096_5_) {
		super(p_i2096_1_, p_i2096_2_);
		this.coordBaseMode = p_i2096_5_;
		this.boundingBox = p_i2096_4_;
		this.cropTypeA = this.func_151560_a(p_i2096_3_);
		this.cropTypeB = this.func_151560_a(p_i2096_3_);
	}

	protected void func_143012_a(NBTTagCompound p_143012_1_) {
		super.func_143012_a(p_143012_1_);
		p_143012_1_.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
		p_143012_1_.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
	}

	protected void func_143011_b(NBTTagCompound p_143011_1_) {
		super.func_143011_b(p_143011_1_);
		this.cropTypeA = Block.getBlockById(p_143011_1_.getInteger("CA"));
		this.cropTypeB = Block.getBlockById(p_143011_1_.getInteger("CB"));
	}

	private Block func_151560_a(Random p_151560_1_) {
		switch(p_151560_1_.nextInt(5)) {
			case 0:
				return Blocks.carrots;
			case 1:
				return Blocks.potatoes;
			default:
				return Blocks.wheat;
		}
	}

	public static Field2 getPiece(StructureVillagePiecesRTG.Start p_74902_0_, List p_74902_1_, Random p_74902_2_, int p_74902_3_, int p_74902_4_, int p_74902_5_, int p_74902_6_, int p_74902_7_) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74902_3_, p_74902_4_, p_74902_5_, 0, 0, 0, 7, 4, 9, p_74902_6_);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74902_1_, structureboundingbox) == null ? new Field2(p_74902_0_, p_74902_7_, p_74902_2_, structureboundingbox, p_74902_6_) : null;
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

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 6, 4, 8, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.farmland, Blocks.farmland, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.farmland, Blocks.farmland, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 0, 0, 8, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 0, 0, 6, 0, 8, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 0, 5, 0, 0, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 8, 5, 0, 8, Blocks.log, Blocks.log, false);
		this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water, Blocks.water, false);
		int i;

		for(i = 1; i <= 7; ++i) {
			this.placeBlockAtCurrentPosition(p_74875_1_, this.cropTypeA, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 1, 1, i, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, this.cropTypeA, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 2, 1, i, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, this.cropTypeB, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 4, 1, i, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, this.cropTypeB, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 5, 1, i, p_74875_3_);
		}

		for(i = 0; i < 9; ++i) {
			for(int j = 0; j < 7; ++j) {
				this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 4, i, p_74875_3_);
				this.func_151554_b(p_74875_1_, Blocks.dirt, 0, j, -1, i, p_74875_3_);
			}
		}

		return true;
	}
}