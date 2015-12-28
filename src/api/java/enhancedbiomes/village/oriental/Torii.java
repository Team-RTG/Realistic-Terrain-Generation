package enhancedbiomes.village.oriental;

import java.util.List;
import java.util.Random;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.village.StructureVillagePiecesEB;
import enhancedbiomes.village.StructureVillagePiecesEB.Start;
import enhancedbiomes.village.StructureVillagePiecesEB.Village;
import enhancedbiomes.world.gen.WorldGenShrineTree;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.*;

public class Torii extends StructureVillagePiecesEB.Village
{
	public Torii() {}

	public Torii(StructureVillagePiecesEB.Start p_i2101_1_, int p_i2101_2_, Random p_i2101_3_, StructureBoundingBox p_i2101_4_, int p_i2101_5_)
	{
		super(p_i2101_1_, p_i2101_2_);
		this.coordBaseMode = p_i2101_5_;
		this.boundingBox = p_i2101_4_;
	}

	public static Torii getPiece(StructureVillagePiecesEB.Start p_74908_0_, List p_74908_1_, Random p_74908_2_, int p_74908_3_, int p_74908_4_, int p_74908_5_, int p_74908_6_, int p_74908_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74908_3_, p_74908_4_, p_74908_5_, -1, 0, -1, 7, 6, 16, p_74908_6_);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74908_1_, structureboundingbox) == null ? new Torii(p_74908_0_, p_74908_7_, p_74908_2_, structureboundingbox, p_74908_6_) : null;
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
	 * Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox p_74875_3_)
	{
		if (this.field_143015_k < 0)
		{
			this.field_143015_k = this.getAverageGroundLevel(world, p_74875_3_);

			if (this.field_143015_k < 0)
			{
				return true;
			}

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
		}
		
		//Torii
		for(int x = 2; x < 5; x++) {
			for(int z = -1; z < 3; z++) {
				this.placeBlockAtCurrentPosition(world, Blocks.gravel, 0, x, getTopSolidBlock(this.getXWithOffset(x, z), this.getZWithOffset(x, z), world) - this.boundingBox.minY - 1, z, p_74875_3_);
			}
		}
		
		//this.fillWithBlocks(world, p_74875_3_, 0, 1, 0, 6, 6, 0, Blocks.air, Blocks.air, false);
		int m = getTopSolidBlock(this.getXWithOffset(1, 0), this.getZWithOffset(1, 0), world) - this.boundingBox.minY;
		int n = getTopSolidBlock(this.getXWithOffset(5, 0), this.getZWithOffset(5, 0), world) - this.boundingBox.minY;
		int h = Math.max(m, n);
		
		this.fillWithBlocks(world, p_74875_3_, 1, m, 0, 1, 3 + h, 0, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, p_74875_3_, 5, n, 0, 5, 3 + h, 0, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, p_74875_3_, 0, 4 + h, 0, 6, 4 + h, 0, Blocks.wooden_slab, Blocks.wooden_slab, false);
		this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, 3, 4 + h, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 1, 4 + h, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 5, 4 + h, 0, p_74875_3_);
		this.fillWithBlocks(world, p_74875_3_, 1, 5 + h, 0, 2, 5 + h, 0, Blocks.wooden_slab, Blocks.wooden_slab, false);
		this.fillWithBlocks(world, p_74875_3_, 4, 5 + h, 0, 5, 5 + h, 0, Blocks.wooden_slab, Blocks.wooden_slab, false);
		this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 8, -1, 5 + h, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 8, 7, 5 + h, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 0, 5 + h, 0, p_74875_3_);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 6, 5 + h, 0, p_74875_3_);
		
		//Shrine approach
		for(int x = 2; x < 5; x++) {
			for(int z = 3; z < 12; z++) {
				this.placeBlockAtCurrentPosition(world, Blocks.gravel, 0, x, getTopSolidBlock(this.getXWithOffset(x, z), this.getZWithOffset(x, z), world) - this.boundingBox.minY - 1, z, p_74875_3_);
			}
		}
		
		{
			int a = 1, b = 2, c = getTopSolidBlock(this.getXWithOffset(a, b), this.getZWithOffset(a, b), world) - this.boundingBox.minY;
			this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, a, c, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, EnhancedBiomesBlocks.shojiLamp, 0, a, c + 1, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, a, c + 2, b, p_74875_3_);
		}{
			int a = 1, b = 5, c = getTopSolidBlock(this.getXWithOffset(a, b), this.getZWithOffset(a, b), world) - this.boundingBox.minY;
			this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, a, c, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, EnhancedBiomesBlocks.shojiLamp, 0, a, c + 1, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, a, c + 2, b, p_74875_3_);
		}{
			int a = 1, b = 8, c = getTopSolidBlock(this.getXWithOffset(a, b), this.getZWithOffset(a, b), world) - this.boundingBox.minY;
			this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, a, c, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, EnhancedBiomesBlocks.shojiLamp, 0, a, c + 1, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, a, c + 2, b, p_74875_3_);
		}{
			int a = 5, b = 2, c = getTopSolidBlock(this.getXWithOffset(a, b), this.getZWithOffset(a, b), world) - this.boundingBox.minY;
			this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, a, c, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, EnhancedBiomesBlocks.shojiLamp, 0, a, c + 1, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, a, c + 2, b, p_74875_3_);
		}{
			int a = 5, b = 5, c = getTopSolidBlock(this.getXWithOffset(a, b), this.getZWithOffset(a, b), world) - this.boundingBox.minY;
			this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, a, c, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, EnhancedBiomesBlocks.shojiLamp, 0, a, c + 1, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, a, c + 2, b, p_74875_3_);
		}{
			int a = 5, b = 8, c = getTopSolidBlock(this.getXWithOffset(a, b), this.getZWithOffset(a, b), world) - this.boundingBox.minY;
			this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, a, c, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, EnhancedBiomesBlocks.shojiLamp, 0, a, c + 1, b, p_74875_3_);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, a, c + 2, b, p_74875_3_);
		}
		
		//Tree
		TreeGen.cherry_large().generate(world, rand, this.getXWithOffset(3, 13), getTopSolidBlock(this.getXWithOffset(3, 13), this.getZWithOffset(3, 13), world), this.getZWithOffset(3, 13));
		
		/*for(int x = 2; x < 5; x++) {
			for(int z = 12; z < 15; z++) {
				if(x != 3 || z != 13) this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 0, x, getTopSolidBlock(this.getXWithOffset(x, z), this.getZWithOffset(x, z), world) - this.boundingBox.minY, z, p_74875_3_);
			}
		}*/
		
		//TODO Villager spawning
		//this.spawnVillagers(world, p_74875_3_, 1, 1, 2, 1);
		return true;
	}
}