package rtg.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
//import enhancedbiomes.handlers.VillagerRegistryEB;
import rtg.village.standard.*;
import rtg.village.oriental.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import static net.minecraftforge.common.ChestGenHooks.*;
import static rtg.village.VillagePieceSelection.*;

public class StructureVillagePiecesRTG
{
	private static int func_75079_a(List p_75079_0_) {
		boolean flag = false;
		int i = 0;
		StructureVillagePieces.PieceWeight pieceweight;

		for(Iterator iterator = p_75079_0_.iterator(); iterator.hasNext(); i += pieceweight.villagePieceWeight) {
			pieceweight = (StructureVillagePieces.PieceWeight) iterator.next();

			if(pieceweight.villagePiecesLimit > 0 && pieceweight.villagePiecesSpawned < pieceweight.villagePiecesLimit) {
				flag = true;
			}
		}

		return flag ? i : -1;
	}

	/**
	 * attempts to find a next Village Component to be spawned
	 */
	private static StructureVillagePieces.Village getNextVillageComponent(StructureVillagePiecesRTG.Start p_75081_0_, List p_75081_1_, Random p_75081_2_, int p_75081_3_, int p_75081_4_, int p_75081_5_, int p_75081_6_, int p_75081_7_) {
		int j1 = func_75079_a(p_75081_0_.structureVillageWeightedPieceList);

		if(j1 <= 0) {
			return null;
		}
		else {
			int k1 = 0;

			while(k1 < 5) {
				++k1;
				int l1 = p_75081_2_.nextInt(j1);
				Iterator iterator = p_75081_0_.structureVillageWeightedPieceList.iterator();

				while(iterator.hasNext()) {
					StructureVillagePieces.PieceWeight pieceweight = (StructureVillagePieces.PieceWeight) iterator.next();
					l1 -= pieceweight.villagePieceWeight;

					if(l1 < 0) {
						if(!pieceweight.canSpawnMoreVillagePiecesOfType(p_75081_7_) || pieceweight == p_75081_0_.structVillagePieceWeight && p_75081_0_.structureVillageWeightedPieceList.size() > 1) {
							break;
						}

						StructureVillagePieces.Village village = getPiece(p_75081_0_, pieceweight, p_75081_1_, p_75081_2_, p_75081_3_, p_75081_4_, p_75081_5_, p_75081_6_, p_75081_7_);

						if(village != null) {
							++pieceweight.villagePiecesSpawned;
							p_75081_0_.structVillagePieceWeight = pieceweight;

							if(!pieceweight.canSpawnMoreVillagePieces()) {
								p_75081_0_.structureVillageWeightedPieceList.remove(pieceweight);
							}

							return village;
						}
					}
				}
			}

			StructureBoundingBox structureboundingbox = getTorchBoundingBox(p_75081_0_ == null ? BiomeGenBase.plains : p_75081_0_.biome, p_75081_0_, p_75081_1_, p_75081_2_, p_75081_3_, p_75081_4_, p_75081_5_, p_75081_6_);

			if(structureboundingbox != null) {
				return getTorch(p_75081_0_ == null ? BiomeGenBase.plains : p_75081_0_.biome, p_75081_0_, p_75081_7_, p_75081_2_, structureboundingbox, p_75081_6_);
			}
			else {
				return null;
			}
		}
	}

	/**
	 * attempts to find a next Structure Component to be spawned, private Village function
	 */
	private static StructureComponent getNextVillageStructureComponent(StructureVillagePiecesRTG.Start p_75077_0_, List p_75077_1_, Random p_75077_2_, int p_75077_3_, int p_75077_4_, int p_75077_5_, int p_75077_6_, int p_75077_7_) {
		if(p_75077_7_ > 50) {
			return null;
		}
		else if(Math.abs(p_75077_3_ - p_75077_0_.getBoundingBox().minX) <= 112 && Math.abs(p_75077_5_ - p_75077_0_.getBoundingBox().minZ) <= 112) {
			StructureVillagePieces.Village village = getNextVillageComponent(p_75077_0_, p_75077_1_, p_75077_2_, p_75077_3_, p_75077_4_, p_75077_5_, p_75077_6_, p_75077_7_ + 1);

			if(village != null) {
				int j1 = (village.getBoundingBox().minX + village.getBoundingBox().maxX) / 2;
				int k1 = (village.getBoundingBox().minZ + village.getBoundingBox().maxZ) / 2;
				int l1 = village.getBoundingBox().maxX - village.getBoundingBox().minX;
				int i2 = village.getBoundingBox().maxZ - village.getBoundingBox().minZ;
				int j2 = l1 > i2 ? l1 : i2;

				if(p_75077_0_.getWorldChunkManager().areBiomesViable(j1, k1, j2 / 2 + 4, MapGenVillageRTG.villageSpawnBiomes)) {
					p_75077_1_.add(village);
					p_75077_0_.field_74932_i.add(village);
					return village;
				}
			}

			return null;
		}
		else {
			return null;
		}
	}

	public static StructureComponent getNextComponentVillagePath(StructureVillagePiecesRTG.Start p_75080_0_, List p_75080_1_, Random p_75080_2_, int p_75080_3_, int p_75080_4_, int p_75080_5_, int p_75080_6_, int p_75080_7_) {
		if(p_75080_7_ > 3 + p_75080_0_.terrainType) {
			return null;
		}
		else if(Math.abs(p_75080_3_ - p_75080_0_.getBoundingBox().minX) <= 112 && Math.abs(p_75080_5_ - p_75080_0_.getBoundingBox().minZ) <= 112) {
			StructureBoundingBox structureboundingbox = getPathBoundingBox(p_75080_0_ == null ? BiomeGenBase.plains : p_75080_0_.biome, p_75080_0_, p_75080_1_, p_75080_2_, p_75080_3_, p_75080_4_, p_75080_5_, p_75080_6_);

			if(structureboundingbox != null && structureboundingbox.minY > 10) {
				rtg.village.Path path = getPath(p_75080_0_, p_75080_7_, p_75080_2_, structureboundingbox, p_75080_6_);
				int j1 = (path.getBoundingBox().minX + path.getBoundingBox().maxX) / 2;
				int k1 = (path.getBoundingBox().minZ + path.getBoundingBox().maxZ) / 2;
				int l1 = path.getBoundingBox().maxX - path.getBoundingBox().minX;
				int i2 = path.getBoundingBox().maxZ - path.getBoundingBox().minZ;
				int j2 = l1 > i2 ? l1 : i2;

				if(p_75080_0_.getWorldChunkManager().areBiomesViable(j1, k1, j2 / 2 + 4, MapGenVillageRTG.villageSpawnBiomes)) {
					p_75080_1_.add(path);
					p_75080_0_.field_74930_j.add(path);
					return path;
				}
			}

			return null;
		}
		else {
			return null;
		}
	}

	public static class Start extends Well
	{
		public WorldChunkManager worldChunkMngr;
		/** Boolean that determines if the village is in a desert or not. */
		public boolean inDesert;
		/** World terrain type, 0 for normal, 1 for flap map */
		public int terrainType;
		public StructureVillagePieces.PieceWeight structVillagePieceWeight;
		/**
		 * Contains List of all spawnable Structure Piece Weights. If no more Pieces of a type can be spawned, they are removed from this list
		 */
		public List structureVillageWeightedPieceList;
		public List field_74932_i = new ArrayList();
		public List field_74930_j = new ArrayList();
		public BiomeGenBase biome;

		public Start() {
		}

		public Start(WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_) {
			super((StructureVillagePiecesRTG.Start) null, 0, p_i2104_3_, p_i2104_4_, p_i2104_5_, p_i2104_1_.getBiomeGenAt(p_i2104_4_, p_i2104_5_));
			this.worldChunkMngr = p_i2104_1_;
			this.structureVillageWeightedPieceList = p_i2104_6_;
			this.terrainType = p_i2104_7_;
			BiomeGenBase biomegenbase = p_i2104_1_.getBiomeGenAt(p_i2104_4_, p_i2104_5_);
			this.inDesert = biomegenbase == BiomeGenBase.desert || biomegenbase == BiomeGenBase.desertHills;
			this.biome = biomegenbase;
		}

		public WorldChunkManager getWorldChunkManager() {
			return this.worldChunkMngr;
		}
	}

	public abstract static class Village extends StructureVillagePieces.Village//StructureComponent
	{
		protected int field_143015_k = -1;
		/** The number of villagers that have been spawned in this component. */
		private int villagersSpawned;
		private boolean field_143014_b;
		protected StructureVillagePiecesRTG.Start startPiece;

		public Village() {
		}

		protected Village(StructureVillagePiecesRTG.Start p_i2107_1_, int p_i2107_2_) {
			super(p_i2107_1_, p_i2107_2_);

			if(p_i2107_1_ != null) {
				this.field_143014_b = p_i2107_1_.inDesert;
				startPiece = p_i2107_1_;
			}
		}

		protected void func_143012_a(NBTTagCompound p_143012_1_) {
			p_143012_1_.setInteger("HPos", this.field_143015_k);
			p_143012_1_.setInteger("VCount", this.villagersSpawned);
			p_143012_1_.setBoolean("Desert", this.field_143014_b);
		}

		protected void func_143011_b(NBTTagCompound p_143011_1_) {
			this.field_143015_k = p_143011_1_.getInteger("HPos");
			this.villagersSpawned = p_143011_1_.getInteger("VCount");
			this.field_143014_b = p_143011_1_.getBoolean("Desert");
		}

		/**
		 * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
		 */
		protected StructureComponent getNextComponentNN(StructureVillagePiecesRTG.Start p_74891_1_, List p_74891_2_, Random p_74891_3_, int p_74891_4_, int p_74891_5_) {
			switch(this.coordBaseMode) {
				case 0:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
				case 1:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
				case 2:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, 1, this.getComponentType());
				case 3:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, 2, this.getComponentType());
				default:
					return null;
			}
		}

		/**
		 * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
		 */
		protected StructureComponent getNextComponentPP(StructureVillagePiecesRTG.Start p_74894_1_, List p_74894_2_, Random p_74894_3_, int p_74894_4_, int p_74894_5_) {
			switch(this.coordBaseMode) {
				case 0:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
				case 1:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
				case 2:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, 3, this.getComponentType());
				case 3:
					return StructureVillagePiecesRTG.getNextVillageStructureComponent(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, 0, this.getComponentType());
				default:
					return null;
			}
		}

		/**
		 * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of all the levels in the BB's horizontal rectangle).
		 */
		protected int getAverageGroundLevel(World p_74889_1_, StructureBoundingBox p_74889_2_) {
			int i = 0;
			int j = 0;

			for(int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k) {
				for(int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l) {
					if(p_74889_2_.isVecInside(l, 64, k)) {
						i += Math.max(p_74889_1_.getTopSolidOrLiquidBlock(l, k), p_74889_1_.provider.getAverageGroundLevel() - 2);
						++j;
					}
				}
			}

			if(j == 0) {
				return -1;
			}
			else {
				return i / j;
			}
		}

		protected static boolean canVillageGoDeeper(StructureBoundingBox p_74895_0_) {
			return p_74895_0_ != null && p_74895_0_.minY > 10;
		}

		/**
		 * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y offset, z offset, number of villagers
		 */
		protected void spawnVillagers(World p_74893_1_, StructureBoundingBox p_74893_2_, int p_74893_3_, int p_74893_4_, int p_74893_5_, int p_74893_6_) {
			if(this.villagersSpawned < p_74893_6_) {
				for(int i1 = this.villagersSpawned; i1 < p_74893_6_; ++i1) {
					int j1 = this.getXWithOffset(p_74893_3_ + i1, p_74893_5_);
					int k1 = this.getYWithOffset(p_74893_4_);
					int l1 = this.getZWithOffset(p_74893_3_ + i1, p_74893_5_);

					if(!p_74893_2_.isVecInside(j1, k1, l1)) {
						break;
					}

					++this.villagersSpawned;
					EntityVillager entityvillager = new EntityVillager(p_74893_1_, this.getVillagerType(i1));
					entityvillager.setLocationAndAngles((double) j1 + 0.5D, (double) k1, (double) l1 + 0.5D, 0.0F, 0.0F);
					p_74893_1_.spawnEntityInWorld(entityvillager);
				}
			}
		}

		/**
		 * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
		 */
		protected int getVillagerType(int p_74888_1_) {
			return 0;
		}

		protected Block func_151558_b(Block p_151558_1_, int p_151558_2_) {
			BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(startPiece == null ? BiomeGenBase.plains : startPiece.biome, p_151558_1_, p_151558_2_);
			MinecraftForge.TERRAIN_GEN_BUS.post(event);
			if(event.getResult() == Result.DENY) return event.replacement;
			if(this.field_143014_b) {
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
			BiomeEvent.GetVillageBlockMeta event = new BiomeEvent.GetVillageBlockMeta(startPiece == null ? BiomeGenBase.plains : startPiece.biome, p_151557_1_, p_151557_2_);
			MinecraftForge.TERRAIN_GEN_BUS.post(event);
			if(event.getResult() == Result.DENY) return event.replacement;
			if(this.field_143014_b) {
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
}