package enhancedbiomes.village.standard;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import enhancedbiomes.village.StructureVillagePiecesEB;

public class Torch extends StructureVillagePiecesEB.Village
{
	public Torch() {}

	public Torch(StructureVillagePiecesEB.Start p_i2097_1_, int p_i2097_2_, Random p_i2097_3_, StructureBoundingBox p_i2097_4_, int p_i2097_5_)
	{
		super(p_i2097_1_, p_i2097_2_);
		this.coordBaseMode = p_i2097_5_;
		this.boundingBox = p_i2097_4_;
	}

	public static StructureBoundingBox func_74904_a(StructureVillagePiecesEB.Start p_74904_0_, List p_74904_1_, Random p_74904_2_, int p_74904_3_, int p_74904_4_, int p_74904_5_, int p_74904_6_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74904_3_, p_74904_4_, p_74904_5_, 0, 0, 0, 3, 4, 2, p_74904_6_);
		return StructureComponent.findIntersecting(p_74904_1_, structureboundingbox) != null ? null : structureboundingbox;
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

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
		}

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 2, 3, 1, Blocks.air, Blocks.air, false);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 0, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 1, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wool, 15, 1, 3, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 0, 3, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 1, 3, 1, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 2, 3, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 1, 3, -1, p_74875_3_);
		return true;
	}
}