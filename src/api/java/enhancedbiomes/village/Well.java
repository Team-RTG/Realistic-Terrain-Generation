package enhancedbiomes.village;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
import enhancedbiomes.world.biomestats.BiomeCategorisation;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.*;

public class Well extends StructureVillagePieces.Start
{
	public Well() {}

	public Well(StructureVillagePiecesEB.Start p_i2109_1_, int p_i2109_2_, Random p_i2109_3_, int p_i2109_4_, int p_i2109_5_, BiomeGenBase biome)
	{
		super();
		this.coordBaseMode = p_i2109_3_.nextInt(4);
		this.biome = biome;

		switch (this.coordBaseMode)
		{
		case 0:
		case 2:
			this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
			break;
		default:
			this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
		}
	}

	/**
	 * Initiates construction of the Structure Component picked, at the current Location of StructGen
	 */
	public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
	{
		StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, this.getComponentType());
		StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, this.getComponentType());
		StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, this.getComponentType());
		StructureVillagePiecesEB.getNextComponentVillagePath((StructureVillagePiecesEB.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
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

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 3, 0);
		}

		for (int i = 0; i <= 5; ++i)
		{
			for (int j = 0; j <= 5; ++j)
			{
				if (j == 0 || j == 5 || i == 0 || i == 5)
				{
					int k = getTopSolidBlock(this.getXWithOffset(j, i), this.getZWithOffset(j, i), p_74875_1_) - this.boundingBox.minY;
					this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.gravel, 0, j, k, i, p_74875_3_);
					this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, k + 1, i, p_74875_3_);
				}
			}
		}

		if(BiomeCategorisation.isOriental(biome)) {
			this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 4, 11, 4, Blocks.cobblestone, Blocks.flowing_water, false);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.flowing_water, 0, 2, 11, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.flowing_water, 0, 3, 11, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.flowing_water, 0, 2, 11, 3, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.flowing_water, 0, 3, 11, 3, p_74875_3_);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 12, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 12, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 12, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 12, 4, p_74875_3_);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 13, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 13, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 13, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 13, 4, p_74875_3_);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 14, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 14, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 1, 14, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 4, 14, 4, p_74875_3_);

			this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 15, 0, 5, 15, 5, Blocks.wooden_slab, Blocks.wooden_slab, false);
			this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 15, 1, 4, 15, 4, Blocks.air, Blocks.air, false);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 0, 15, 0, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 5, 15, 0, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 0, 15, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.planks, 0, 5, 15, 5, p_74875_3_);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 2, 16, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 3, 16, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 2, 16, 3, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 3, 16, 3, p_74875_3_);

			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 1, 15, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 1, 15, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 1, 15, 3, p_74875_3_);

			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 2, 15, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 3, 15, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 4, 15, 1, p_74875_3_);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 4, 15, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 4, 15, 3, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 4, 15, 4, p_74875_3_);

			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 1, 15, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 2, 15, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 3, 15, 4, p_74875_3_);
			
		}
		else {
			this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 4, 12, 4, Blocks.cobblestone, Blocks.flowing_water, false);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 12, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 3, 12, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 12, 3, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 3, 12, 3, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 13, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 14, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 13, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 14, 1, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 13, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 1, 14, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 13, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 4, 14, 4, p_74875_3_);
			this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 15, 1, 4, 15, 4, Blocks.cobblestone, Blocks.cobblestone, false);
		}

		return true;
	}

	protected Block func_151558_b(Block p_151558_1_, int p_151558_2_)
	{
		BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(biome, p_151558_1_, p_151558_2_);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		if (event.getResult() == Result.DENY) return event.replacement;
		if (this.inDesert)
		{
			if (p_151558_1_ == Blocks.log || p_151558_1_ == Blocks.log2)
			{
				return Blocks.sandstone;
			}

			if (p_151558_1_ == Blocks.cobblestone)
			{
				return Blocks.sandstone;
			}

			if (p_151558_1_ == Blocks.planks)
			{
				return Blocks.sandstone;
			}

			if (p_151558_1_ == Blocks.oak_stairs)
			{
				return Blocks.sandstone_stairs;
			}

			if (p_151558_1_ == Blocks.stone_stairs)
			{
				return Blocks.sandstone_stairs;
			}

			if (p_151558_1_ == Blocks.gravel)
			{
				return Blocks.sandstone;
			}
		}

		return p_151558_1_;
	}

	protected int func_151557_c(Block p_151557_1_, int p_151557_2_)
	{
		BiomeEvent.GetVillageBlockMeta event = new BiomeEvent.GetVillageBlockMeta(biome, p_151557_1_, p_151557_2_);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		if (event.getResult() == Result.DENY) return event.replacement;
		if (this.inDesert)
		{
			if (p_151557_1_ == Blocks.log || p_151557_1_ == Blocks.log2)
			{
				return 0;
			}

			if (p_151557_1_ == Blocks.cobblestone)
			{
				return 0;
			}

			if (p_151557_1_ == Blocks.planks)
			{
				return 2;
			}
		}

		return p_151557_2_;
	}
}