package rtg.village;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
//import enhancedbiomes.world.biomestats.BiomeCategorisation;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import static rtg.event.WorldHelper.*;

public class Well extends StructureVillagePieces.Start
{
	public Well() {
	}

	public Well(StructureVillagePiecesRTG.Start p_i2109_1_, int p_i2109_2_, Random p_i2109_3_, int p_i2109_4_, int p_i2109_5_, BiomeGenBase biome) {
		super();
		this.coordBaseMode = p_i2109_3_.nextInt(4);
		this.biome = biome;

		switch(this.coordBaseMode) {
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
	public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
		StructureVillagePiecesRTG.getNextComponentVillagePath((StructureVillagePiecesRTG.Start) p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, this.getComponentType());
		StructureVillagePiecesRTG.getNextComponentVillagePath((StructureVillagePiecesRTG.Start) p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, this.getComponentType());
		StructureVillagePiecesRTG.getNextComponentVillagePath((StructureVillagePiecesRTG.Start) p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, this.getComponentType());
		StructureVillagePiecesRTG.getNextComponentVillagePath((StructureVillagePiecesRTG.Start) p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
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

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 3, 0);
		}

		for(int i = 0; i <= 5; ++i) {
			for(int j = 0; j <= 5; ++j) {
				if(j == 0 || j == 5 || i == 0 || i == 5) {
					int k = getTopSolidBlock(this.getXWithOffset(j, i), this.getZWithOffset(j, i), p_74875_1_) - this.boundingBox.minY;
					this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.gravel, 0, j, k, i, p_74875_3_);
					this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 1, i, p_74875_3_);
				}
			}
		}

		{
	/* original well orso		
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
*/
			
			//dunno 
	/*		this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 4, 12, 4, Blocks.cobblestone, Blocks.flowing_water, false);
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
			this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 15, 1, 4, 15, 4, Blocks.cobblestone, Blocks.cobblestone, false); */
			

			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.gold_block, 0, 0, 11, 13, p_74875_3_);


			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 3, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 3, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 3, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 3, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 3, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 3, 1, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 4, 0, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 4, 0, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 4, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 4, 0, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 4, 0, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 4, 1, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 4, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 4, 1, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 4, 1, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 4, 2, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 2, 4, 2, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 0, 4, 2, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_stairs, 6, 4, 3, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_stairs, 2, 4, 4, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.iron_block, 0, 4, 4, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_stairs, 7, 4, 4, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick_stairs, 3, 4, 5, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 4, 6, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 4, 6, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 4, 6, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 0, 4, 7, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 5, 0, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 5, 0, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.soul_sand, 0, 5, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 5, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 5, 0, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 5, 0, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 5, 1, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 5, 1, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 5, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 5, 1, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 5, 1, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 5, 2, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 5, 2, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 5, 2, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 5, 3, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 5, 3, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.sponge, 0, 5, 4, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 5, 5, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 5, 6, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 5, 6, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 5, 6, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 5, 6, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 5, 6, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 5, 7, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 5, 7, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, 1, 5, 7, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 5, 7, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 5, 7, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 0, 5, 8, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 5, 8, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 0, 5, 8, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 6, 0, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 6, 0, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.soul_sand, 0, 6, 0, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 6, 0, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 6, 0, 12, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 6, 1, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 6, 1, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 3, 6, 1, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 6, 1, 12, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 6, 2, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 3, 6, 2, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 2, 6, 4, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 6, 6, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 6, 6, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 6, 6, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 6, 6, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 5, 6, 7, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 6, 7, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 2, 6, 8, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 6, 8, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 6, 8, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 3, 6, 8, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 6, 9, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 0, 6, 9, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 6, 9, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 7, 0, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 7, 0, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 0, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 0, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 7, 0, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 7, 0, 12, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 7, 1, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 2, 7, 1, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 1, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 1, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 7, 1, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 7, 1, 12, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 7, 2, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 2, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 7, 2, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 2, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 3, 7, 2, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 3, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.fence, 0, 7, 3, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 3, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 7, 3, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 4, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 2, 7, 4, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 4, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 5, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 5, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 7, 6, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 6, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 7, 6, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 7, 6, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 2, 7, 7, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, 3, 7, 7, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, 2, 7, 7, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 3, 7, 7, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 7, 8, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 7, 8, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 2, 7, 9, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 7, 9, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 3, 7, 9, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.nether_brick, 0, 7, 10, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 8, 0, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 8, 0, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 8, 0, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 8, 0, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 8, 0, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 8, 0, 12, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 8, 1, 4, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 8, 1, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 8, 1, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 8, 1, 12, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 3, 8, 2, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 7, 8, 2, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 8, 3, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 2, 8, 4, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 8, 6, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 8, 6, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 8, 6, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 8, 6, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 8, 7, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 8, 7, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 2, 8, 8, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 8, 8, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 8, 8, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 3, 8, 8, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 8, 9, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 1, 8, 9, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 8, 9, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 9, 0, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 9, 0, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.soul_sand, 0, 9, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 9, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 9, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 9, 0, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 9, 0, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 9, 1, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 9, 1, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 9, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 9, 1, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 9, 1, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 9, 2, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 9, 2, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 9, 2, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 9, 3, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 9, 3, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.sponge, 0, 9, 4, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 9, 5, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 9, 6, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 9, 6, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.log, 0, 9, 6, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 10, 9, 6, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 9, 6, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 9, 7, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 9, 7, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.oak_stairs, 0, 9, 7, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 9, 7, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 5, 9, 7, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 1, 9, 8, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 9, 8, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 1, 9, 8, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 10, 0, 5, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 10, 0, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 10, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 10, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 10, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 10, 0, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 10, 0, 11, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 10, 1, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 10, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, 1, 10, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 2, 10, 1, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 10, 1, 10, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 3, 10, 2, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 3, 10, 2, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 10, 2, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 10, 6, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 10, 6, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 13, 10, 6, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_brick_stairs, 1, 10, 7, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 11, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 11, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.double_stone_slab, 0, 11, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 11, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 11, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_slab, 6, 11, 1, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.gold_block, 0, 12, 0, 0, p_74875_3_);
//			world.setBlockAndMetadataWithNotify(2, 1, 8, 1, 2);
//			world.setBlockAndMetadataWithNotify(3, 1, 6, 1, 2);
//			world.setBlockAndMetadataWithNotify(3, 1, 10, 1, 2);
//			world.setBlockBlocks.watery(3, 1, 11, 1, 2);
//			world.setBlockAndMetadataWithNotify(3, 4, 8, 2, 2);
//			world.setBlockAndMetadataWithNotify(5, 0, 9, 8, 2);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 5, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 5, 1, 9, p_74875_3_);
//			world.setBlockAndMetadataWithNotify(5, 1, 12, 1, 2);
//			world.setBlockAndMetadataWithNotify(6, 0, 6, 8, 2);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 6, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 6, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 6, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 6, 1, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 6, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 6, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 6, 1, 9, p_74875_3_);
//			world.setBlockAndMetadataWithNotify(6, 1, 10, 1, 2);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 7, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 7, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 7, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 7, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 7, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 7, 1, 9, p_74875_3_);
//			world.setBlockAndMetadataWithNotify(7, 11, 8, 5, 2);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 0, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 0, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 0, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 1, 6, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 1, 8, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 1, 9, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 8, 1, 10, p_74875_3_);
//			world.setBlockAndMetadataWithNotify(9, 1, 4, 1, 2);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 9, 1, 7, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.water, 0, 9, 1, 9, p_74875_3_);
//			world.setBlockAndMetadataWithNotify(9, 1, 13, 4, 2);
//			world.setBlockAndMetadataWithNotify(9, 2, 11, 4, 2);
//			world.setBlockAndMetadataWithNotify(10, 1, 12, 2, 2);
//			world.setBlockAndMetadataWithNotify(10, 2, 10, 6, 2);
//			world.setBlockAndMetadataWithNotify(11, 1, 5, 1, 2);
//			world.setBlockAndMetadataWithNotify(11, 1, 10, 1, 2);
//			world.setBlockAndMetadataWithNotify(11, 2, 9, 2, 2);

		}

		return true;
	}


	protected Block func_151558_b(Block p_151558_1_, int p_151558_2_) {
		BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(biome, p_151558_1_, p_151558_2_);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		if(event.getResult() == Result.DENY) return event.replacement;
		if(this.inDesert) {
			if(p_151558_1_ == Blocks.log || p_151558_1_ == Blocks.log2) {
				return Blocks.sandstone;
			}

			if(p_151558_1_ == Blocks.cobblestone) {
				return Blocks.sandstone;
			}

			if(p_151558_1_ == Blocks.planks) {
				return Blocks.sandstone;
			}

			if(p_151558_1_ == Blocks.oak_stairs) {
				return Blocks.sandstone_stairs;
			}

			if(p_151558_1_ == Blocks.stone_stairs) {
				return Blocks.sandstone_stairs;
			}

			if(p_151558_1_ == Blocks.gravel) {
				return Blocks.sandstone;
			}
		}

		return p_151558_1_;
	}

	protected int func_151557_c(Block p_151557_1_, int p_151557_2_) {
		BiomeEvent.GetVillageBlockMeta event = new BiomeEvent.GetVillageBlockMeta(biome, p_151557_1_, p_151557_2_);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		if(event.getResult() == Result.DENY) return event.replacement;
		if(this.inDesert) {
			if(p_151557_1_ == Blocks.log || p_151557_1_ == Blocks.log2) {
				return 0;
			}

			if(p_151557_1_ == Blocks.cobblestone) {
				return 0;
			}

			if(p_151557_1_ == Blocks.planks) {
				return 2;
			}
		}

		return p_151557_2_;
	}
}