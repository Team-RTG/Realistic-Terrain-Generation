package rtg.village.standard;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import rtg.village.StructureVillagePiecesRTG;

public class Torch extends StructureVillagePiecesRTG.Village
{
	public Torch() {
	}

	public Torch(StructureVillagePiecesRTG.Start p_i2097_1_, int p_i2097_2_, Random p_i2097_3_, StructureBoundingBox p_i2097_4_, int p_i2097_5_) {
		super(p_i2097_1_, p_i2097_2_);
		this.coordBaseMode = p_i2097_5_;
		this.boundingBox = p_i2097_4_;
	}

	public static StructureBoundingBox func_74904_a(StructureVillagePiecesRTG.Start p_74904_0_, List p_74904_1_, Random p_74904_2_, int p_74904_3_, int p_74904_4_, int p_74904_5_, int p_74904_6_) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74904_3_, p_74904_4_, p_74904_5_, 0, 0, 0, 3, 4, 2, p_74904_6_);
		return StructureComponent.findIntersecting(p_74904_1_, structureboundingbox) != null ? null : structureboundingbox;
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

		this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 2, 3, 1, Blocks.air, Blocks.air, false); 
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 0, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 1, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 2, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.redstone_lamp, 15, 1, 3, 0, p_74875_3_);
		//this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 0, 3, 0, p_74875_3_);
		//this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 1, 3, 1, p_74875_3_);
		//this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 2, 3, 0, p_74875_3_);
		//this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 1, 3, -1, p_74875_3_);
		/*
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 0, 0, 0, p_74875_3_); //Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 1, 0, 0, p_74875_3_);//Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 2, 0, 0, p_74875_3_);//Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 0, 0, 1, p_74875_3_);//Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.glowstone, 0, 1, 0, 1, p_74875_3_);//Blocks.glowstone, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 2, 0, 1, p_74875_3_);//Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 0, 0, 2, p_74875_3_);//Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 1, 0, 2, p_74875_3_);//Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.grass, 0, 2, 0, 2, p_74875_3_);//Blocks.grass, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 2), 1, 1, 0, p_74875_3_);//Blocks.stone_stairs, 2, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 0), 0, 1, 1, p_74875_3_);//Blocks.stone_stairs, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wool, 0, 1, 1, 1, p_74875_3_);//Blocks.wool, 15, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 1), 2, 1, 1, p_74875_3_);//Blocks.stone_stairs, 1, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 1, 1, 2, p_74875_3_);//Blocks.stone_stairs, 3, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.tallgrass, 0, 2, 1, 2, p_74875_3_);//Blocks.tallgrass, 3, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wool, 0, 1, 2, 1, p_74875_3_);//Blocks.wool, 15, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 3, 1, p_74875_3_);//Blocks.log, 3, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wool, 0, 1, 4, 1, p_74875_3_);//Blocks.wool, 15, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 5, 1, p_74875_3_);//Blocks.log, 3, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 0, 0, 6, 1, p_74875_3_);//Blocks.stone_slab, 3, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.furnace, 0, 1, 6, 1, p_74875_3_);// Blocks.furnace, 2, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 0, 2, 6, 1, p_74875_3_);//Blocks.stone_slab, 3, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 1, 7, 0, p_74875_3_);//Blocks.stone_stairs, 6, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 0, 7, 1, p_74875_3_);//Blocks.stone_stairs, 4, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.netherrack, 0, 1, 7, 1, p_74875_3_);//Blocks.netherrack, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 2, 7, 1, p_74875_3_);//Blocks.stone_stairs, 5, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 1, 7, 2, p_74875_3_);//Blocks.stone_stairs, 7, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_fence, 0, 1, 8, 0, p_74875_3_);//Blocks.nether_brick_fence, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_fence, 0, 0, 8, 1, p_74875_3_);//Blocks.nether_brick_fence, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fire, 0, 1, 8, 1, p_74875_3_);//Blocks.fire, 15, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_fence, 0, 2, 8, 1, p_74875_3_);//Blocks.nether_brick_fence, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_fence, 0, 1, 8, 2, p_74875_3_);//Blocks.nether_brick_fence, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 1, 9, 0, p_74875_3_);//Blocks.stone_stairs, 2, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 0, 9, 1, p_74875_3_);//Blocks.stone_stairs, 0, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 0, 1, 9, 1, p_74875_3_);//Blocks.stone_slab, 11, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 2, 9, 1, p_74875_3_);//Blocks.stone_stairs, 1, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 1, 9, 2, p_74875_3_);//Blocks.stone_stairs, 3, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 0, 1, 10, 1, p_74875_3_);//Blocks.stone_slab, 2, 3);
		
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_button, this.getMetadataWithOffset(Blocks.stone_button, 4), 1, 5, 0, p_74875_3_);//Blocks.stone_button, 4, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_button, this.getMetadataWithOffset(Blocks.stone_button, 2), 0, 5, 1, p_74875_3_);//Blocks.stone_button, 2, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_button, this.getMetadataWithOffset(Blocks.stone_button, 1), 2, 5, 1, p_74875_3_);//Blocks.stone_button, 1, 3);
		this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_button, this.getMetadataWithOffset(Blocks.stone_button, 3), 1, 5, 2, p_74875_3_);//Blocks.stone_button, 3, 3);
		*/
		return true;
	}
}