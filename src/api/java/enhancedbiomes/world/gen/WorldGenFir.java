package enhancedbiomes.world.gen;

import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenFir extends WorldGenAbstractTree
{
	int height;
	Block woodId;
	int woodMeta;
	BlockLeavesBase leavesId;
	int leavesMeta;
	boolean largeTree;
	
	public WorldGenFir(int height, Block woodId, int woodMeta, BlockLeavesBase leavesId, int leavesMeta, boolean largeTree)
	{
		super(true);
		this.height = height;
		this.woodId = woodId;
		this.woodMeta = woodMeta;
		this.leavesId = leavesId;
		this.leavesMeta = leavesMeta;
		this.largeTree = largeTree;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		if (this.largeTree == true)
		{
			for(int y = 0; y < this.height + 18; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						if(var1.getBlock(var3 + z, var4 + y, var5 + x) != Blocks.air)
						{
							return false;
						}
					}
				}
			}
			
			for(int y = 0; y < 18; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 12; z++)
					{
						if(var1.getBlock(var3 + z - 5, var4 + this.height + y, var5 + x) != Blocks.air)
						{
							return false;
						}
					}
				}
				
				for(int x = 0; x < 12; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						if(var1.getBlock(var3 + z, var4 + this.height + y, var5 + x - 5) != Blocks.air)
						{
							return false;
						}
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 10; z++)
					{
						if(var1.getBlock(var3 + z - 4, var4 + this.height + y, var5 + x - 1) != Blocks.air)
						{
							return false;
						}
					}
				}
				
				for(int x = 0; x < 10; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						if(var1.getBlock(var3 + z - 1, var4 + this.height + y, var5 + x - 4) != Blocks.air)
						{
							return false;
						}
					}
				}
				
				for(int x = 0; x < 8; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						if(var1.getBlock(var3 + z - 2, var4 + this.height + y, var5 + x - 3) != Blocks.air)
						{
							return false;
						}
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 8; z++)
					{
						if(var1.getBlock(var3 + z - 3, var4 + this.height + y, var5 + x - 2) != Blocks.air)
						{
							return false;
						}
					}
				}
			}
			
			if(!var1.getBlock(var3, var4 - 1, var5).canSustainPlant(var1, var3, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
			{
				return false;
			}
			
			if(!var1.getBlock(var3, var4 - 1, var5 + 1).canSustainPlant(var1, var3, var4 - 1, var5 + 1, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
			{
				return false;
			}
			
			if(!var1.getBlock(var3 + 1, var4 - 1, var5).canSustainPlant(var1, var3 + 1, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
			{
				return false;
			}
			
			if(!var1.getBlock(var3 + 1, var4 - 1, var5 + 1).canSustainPlant(var1, var3 + 1, var4 - 1, var5 + 1, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
			{
				return false;
			}
			
			//Layer 1
			for(int z = 0; z < 2; z++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int y = 0; y < 3; y++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 18 + y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Layer 2
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					setBlockIfEmpty(var3 + 2, var4 + this.height + 17 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					setBlockIfEmpty(var3 - 1, var4 + this.height + 17 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
				}
				
				for(int z = 0; z < 2; z++)
				{
					setBlockIfEmpty(var3 + z, var4 + this.height + 17 - y, var5 + 2, this.leavesId, this.leavesMeta, 3, var1);
					setBlockIfEmpty(var3 + z, var4 + this.height + 17 - y, var5 - 1, this.leavesId, this.leavesMeta, 3, var1);
				}
			}
			
			//Layer 3
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height + 14 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 14 - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 14 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 1:1
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height + 9 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 9 - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 9 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 1:2
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 8; z++)
					{
						setBlockIfEmpty(var3 + z - 3, var4 + this.height + 8 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 8; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 8 - y, var5 + x - 3, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height + 8 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 8 - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 2:1
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 8; z++)
					{
						setBlockIfEmpty(var3 + z - 3, var4 + this.height + 5 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 8; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 5 - y, var5 + x - 3, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height + 5 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 5 - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 2:2
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 10; z++)
					{
						setBlockIfEmpty(var3 + z - 4, var4 + this.height + 4 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 10; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 4 - y, var5 + x - 4, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 8; z++)
					{
						setBlockIfEmpty(var3 + z - 3, var4 + this.height + 4 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 8; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 4 - y, var5 + x - 3, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height + 4 - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 3:1
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 10; z++)
					{
						setBlockIfEmpty(var3 + z - 4, var4 + this.height + 1 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 10; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 1 - y, var5 + x - 4, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 8; z++)
					{
						setBlockIfEmpty(var3 + z - 3, var4 + this.height + 1 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 8; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 1 - y, var5 + x - 3, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height + 1 - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 3:2
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 12; z++)
					{
						setBlockIfEmpty(var3 + z - 5, var4 + this.height - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 12; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height - y, var5 + x - 5, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 4; x++)
				{
					for(int z = 0; z < 10; z++)
					{
						setBlockIfEmpty(var3 + z - 4, var4 + this.height - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 10; x++)
				{
					for(int z = 0; z < 4; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height - y, var5 + x - 4, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 8; x++)
				{
					for(int z = 0; z < 6; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height - y, var5 + x - 3, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 6; x++)
				{
					for(int z = 0; z < 8; z++)
					{
						setBlockIfEmpty(var3 + z - 3, var4 + this.height - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Trunk
			for(int i = 0; i < this.height + 18; i++)
			{
				var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
				var1.setBlock(var3 + 1, var4 + i, var5, this.woodId, this.woodMeta, 3);
				var1.setBlock(var3, var4 + i, var5 + 1, this.woodId, this.woodMeta, 3);
				var1.setBlock(var3 + 1, var4 + i, var5 + 1, this.woodId, this.woodMeta, 3);
			}		
			
			return true;
		}
		else
		{
			//Small Tree
			for(int i = 0; i < this.height + 5; i++)
			{
				if(var1.getBlock(var3, var4 + i, var5) != Blocks.air)
				{
					return false;
				}
			}
			
			if(!var1.getBlock(var3, var4 - 1, var5).canSustainPlant(var1, var3, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
			{
				return false;
			}
			
			for(int y = 0; y < 5; y++)
			{
				for(int x = 0; x < 1; x++)
				{
					for(int z = 0; z < 5; z++)
					{
						if(var1.getBlock(var3 + z - 2, var4 + this.height + y, var5 + x) != Blocks.air)
						{
							return false;
						}
					}
				}
				
				for(int x = 0; x < 5; x++)
				{
					for(int z = 0; z < 1; z++)
					{
						if(var1.getBlock(var3 + z, var4 + this.height + y, var5 + x - 2) != Blocks.air)
						{
							return false;
						}
					}
				}
				
				for(int x = 0; x < 3; x++)
				{
					for(int z = 0; z < 3; z++)
					{
						if(var1.getBlock(var3 + z - 1, var4 + this.height + y, var5 + x - 1) != Blocks.air)
						{
							return false;
						}
					}
				}
			}
			
			//Layer 1
			for(int z = 0; z < 1; z++)
			{
				for(int x = 0; x < 1; x++)
				{
					for(int y = 0; y < 2; y++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 5 + y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Layer 2
			for(int y = 0; y < 2; y++)
			{
				for(int x = 0; x < 1; x++)
				{
					for(int z = 0; z < 3; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 4 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 3; x++)
				{
					for(int z = 0; z < 1; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 4 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 1:1
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 1; x++)
				{
					for(int z = 0; z < 3; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height + 1 - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 3; x++)
				{
					for(int z = 0; z < 1; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height + 1 - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Platform 1:2
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 1; x++)
				{
					for(int z = 0; z < 5; z++)
					{
						setBlockIfEmpty(var3 + z - 2, var4 + this.height - y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 5; x++)
				{
					for(int z = 0; z < 1; z++)
					{
						setBlockIfEmpty(var3 + z, var4 + this.height - y, var5 + x - 2, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
				
				for(int x = 0; x < 3; x++)
				{
					for(int z = 0; z < 3; z++)
					{
						setBlockIfEmpty(var3 + z - 1, var4 + this.height - y, var5 + x - 1, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Trunk
			for(int i = 0; i < this.height + 5; i++)
			{
				var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
			}
			
			return true;
		}
	}
}
