package enhancedbiomes.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class Path extends Road
{
	private int averageGroundLevel;
	public Path() {}

	public Path(StructureVillagePiecesEB.Start p_i2105_1_, int p_i2105_2_, Random p_i2105_3_, StructureBoundingBox p_i2105_4_, int p_i2105_5_)
	{
		super(p_i2105_1_, p_i2105_2_);
		this.coordBaseMode = p_i2105_5_;
		this.boundingBox = p_i2105_4_;
		this.averageGroundLevel = Math.max(p_i2105_4_.getXSize(), p_i2105_4_.getZSize());
	}

	protected void func_143012_a(NBTTagCompound p_143012_1_)
	{
		super.func_143012_a(p_143012_1_);
		p_143012_1_.setInteger("Length", this.averageGroundLevel);
	}

	protected void func_143011_b(NBTTagCompound p_143011_1_)
	{
		super.func_143011_b(p_143011_1_);
		this.averageGroundLevel = p_143011_1_.getInteger("Length");
	}

	/**
	 * Initiates construction of the Structure Component picked, at the current Location of StructGen
	 */
	public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
	{
		boolean flag = false;
		int i;
		StructureComponent structurecomponent1;

		for (i = p_74861_3_.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + p_74861_3_.nextInt(5))
		{
			structurecomponent1 = this.getNextComponentNN((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

			if (structurecomponent1 != null)
			{
				i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
				flag = true;
			}
		}

		for (i = p_74861_3_.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + p_74861_3_.nextInt(5))
		{
			structurecomponent1 = this.getNextComponentPP((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

			if (structurecomponent1 != null)
			{
				i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
				flag = true;
			}
		}

		if (flag && p_74861_3_.nextInt(3) > 0)
		{
			switch (this.coordBaseMode)
			{
			case 0:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 1, this.getComponentType());
				break;
			case 1:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
				break;
			case 2:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, 1, this.getComponentType());
				break;
			case 3:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
			}
		}

		if (flag && p_74861_3_.nextInt(3) > 0)
		{
			switch (this.coordBaseMode)
			{
			case 0:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 3, this.getComponentType());
				break;
			case 1:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
				break;
			case 2:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, 3, this.getComponentType());
				break;
			case 3:
				StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
			}
		}
	}

	public static StructureBoundingBox func_74933_a(StructureVillagePiecesEB.Start p_74933_0_, List p_74933_1_, Random p_74933_2_, int p_74933_3_, int p_74933_4_, int p_74933_5_, int p_74933_6_)
	{
		for (int i1 = 7 * MathHelper.getRandomIntegerInRange(p_74933_2_, 3, 5); i1 >= 7; i1 -= 7)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74933_3_, p_74933_4_, p_74933_5_, 0, 0, 0, 3, 3, i1, p_74933_6_);

			if (StructureComponent.findIntersecting(p_74933_1_, structureboundingbox) == null)
			{
				return structureboundingbox;
			}
		}

		return null;
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
	 * Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
	{
		for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
		{
			for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
			{
				if (p_74875_3_.isVecInside(i, 64, j))
				{
					int k = p_74875_1_.getTopSolidOrLiquidBlock(i, j) - 1;
					p_74875_1_.setBlock(i, k, j, this.func_151558_b(Blocks.gravel, 0), this.func_151557_c(Blocks.gravel, 0), 2);
				}
			}
		}

		return true;
	}
}