package enhancedbiomes.world.gen;

import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

public class WorldGenEucalyptus extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	int height;
	int span;
	int branches;
	
	public WorldGenEucalyptus(Block woodId, int woodMeta, Block leavesId, int leavesMeta, int height, int span, int branches)
	{
		super(true);
		this.woodId = woodId;
		this.woodMeta = woodMeta;
		this.leavesId = leavesId;
		this.leavesMeta = leavesMeta;
		this.height = height;
		this.span = span;
		this.branches = branches;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		if(!var1.getBlock(var3, var4 - 1, var5).canSustainPlant(var1, var3, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
		{
			return false;
		}
		
		for(int i = 0; i < span; i++)
		{
			for(int k = -span; k < span + 1; k++)
			{
				for(int j = -span; j < span + 1; j++)
				{
					if(var1.getBlock(var3 + k, var4 + height + i, var5 + j) != Blocks.air)
					{
						return false;
					}
				}
			}
		}
		
		for(int p = -2; p < 3; p++)
		{
			for(int r = -1; r < 2; r++)
			{
				setBlockIfEmpty(var3 + p, var4 + height + span + 1, var5 + r, this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3 + r, var4 + height + span + 1, var5 + p, this.leavesId, this.leavesMeta, 3, var1);
			}
		}
		
		for(int p = -1; p < 2; p++)
		{
			for(int r = -1; r < 2; r++)
			{
				setBlockIfEmpty(var3 + r, var4 + height + span + 2, var5 + p, this.leavesId, this.leavesMeta, 3, var1);
			}
		}
		
		for(int a = 0; a < branches; a++)
		{
			int disX = (var2.nextInt((span * 2) + 1)) - span;
			int disY = var2.nextInt(span + 1);
			int disZ = (var2.nextInt((span * 2) + 1)) - span;
			
			int posX = var3 + disX;
			int posY = var4 + height - 1 + disY;
			int posZ = var5 + disZ;
			
			for(int p = -2; p < 3; p++)
			{
				for(int r = -1; r < 2; r++)
				{
					setBlockIfEmpty(posX + p, posY, posZ + r, this.leavesId, this.leavesMeta, 3, var1);
					setBlockIfEmpty(posX + r, posY, posZ + p, this.leavesId, this.leavesMeta, 3, var1);
				}
			}
			
			for(int p = -1; p < 2; p++)
			{
				for(int r = -1; r < 2; r++)
				{
					var1.setBlock(posX + r, posY + 1, posZ + p, this.leavesId, this.leavesMeta, 3);
				}
			}
			
			for(int b = 0; b < span; b++)
			{
				int x = disX * (b + 1) / span;
				int y = disY * (b + 1) / span;
				int z = disZ * (b + 1) / span;
				
				var1.setBlock(var3 + x, var4 + height - 1 + y, var5 + z, this.woodId, this.woodMeta, 3);
			}
			
			var1.setBlock(posX, posY, posZ, this.woodId, this.woodMeta, 3);
		}		
		
		for(int i = 0; i < height + span + 2; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		}
		
		return true;
	}
}
