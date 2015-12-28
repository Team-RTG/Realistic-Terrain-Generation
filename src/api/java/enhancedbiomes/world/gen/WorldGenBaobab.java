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

public class WorldGenBaobab extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	int height;
	
	public WorldGenBaobab(Block woodId, int woodMeta, Block leavesId, int leavesMeta, int height)
	{
		super(true);
		this.woodId = woodId;
		this.woodMeta = woodMeta;
		this.leavesId = leavesId;
		this.leavesMeta = leavesMeta;
		this.height = height;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		if(!var1.getBlock(var3, var4 - 1, var5).canSustainPlant(var1, var3, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
		{
			return false;
		}
		
		for(int i = 0; i < height; i++)
		{
			if(var1.getBlock(var3, var4 + i, var5) != Blocks.air || var1.getBlock(var3 + 1, var4 + i, var5) != Blocks.air || 
				var1.getBlock(var3, var4 + i, var5 + 1) != Blocks.air || var1.getBlock(var3 + 1, var4 + i, var5 + 1) != Blocks.air)
			{
				return false;
			}
		}
		
		//setBlockIfEmpty(var3, var4 + height + 10, var5, this.leavesId, this.leavesMeta, 3, var1);
		
		for(int i = 0; i < height; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 1, var4 + i, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + i, var5 + 1, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 1, var4 + i, var5 + 1, this.woodId, this.woodMeta, 3);
		}
		
		int b1 = var2.nextInt(2);
		var1.setBlock(var3 - 1, var4 + height - 1, var5 + b1, this.woodId, this.woodMeta + 4, 3);
		var1.setBlock(var3 - 2, var4 + height - 1, var5 + b1, this.woodId, this.woodMeta + 4, 3);
		var1.setBlock(var3 - 3, var4 + height, var5 + b1, this.woodId, this.woodMeta, 3);
		setBlockIfEmpty(var3 - 3, var4 + height + 1, var5 + b1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 2, var4 + height + 1, var5 + b1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 4, var4 + height + 1, var5 + b1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 3, var4 + height + 1, var5 + b1 - 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 3, var4 + height + 1, var5 + b1 + 1, this.leavesId, this.leavesMeta, 3, var1);
		
		int b2 = var2.nextInt(2);
		var1.setBlock(var3 + 2, var4 + height - 1, var5 + b2, this.woodId, this.woodMeta + 4, 3);
		var1.setBlock(var3 + 3, var4 + height - 1, var5 + b2, this.woodId, this.woodMeta + 4, 3);
		var1.setBlock(var3 + 4, var4 + height, var5 + b2, this.woodId, this.woodMeta, 3);
		setBlockIfEmpty(var3 + 4, var4 + height + 1, var5 + b2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + 3, var4 + height + 1, var5 + b2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + 5, var4 + height + 1, var5 + b2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + 4, var4 + height + 1, var5 + b2 - 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + 4, var4 + height + 1, var5 + b2 + 1, this.leavesId, this.leavesMeta, 3, var1);
		
		int b3 = var2.nextInt(2);
		var1.setBlock(var3 + b3, var4 + height - 1, var5 - 1, this.woodId, this.woodMeta + 8, 3);
		var1.setBlock(var3 + b3, var4 + height - 1, var5 - 2, this.woodId, this.woodMeta + 8, 3);
		var1.setBlock(var3 + b3, var4 + height, var5 - 3, this.woodId, this.woodMeta, 3);
		setBlockIfEmpty(var3 + b3, var4 + height + 1, var5 - 3, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b3, var4 + height + 1, var5 - 2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b3, var4 + height + 1, var5 - 4, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b3 + 1, var4 + height + 1, var5 - 3, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b3 - 1, var4 + height + 1, var5 - 3, this.leavesId, this.leavesMeta, 3, var1);
		
		int b4 = var2.nextInt(2);
		var1.setBlock(var3 + b4, var4 + height - 1, var5 + 2, this.woodId, this.woodMeta + 8, 3);
		var1.setBlock(var3 + b4, var4 + height - 1, var5 + 3, this.woodId, this.woodMeta + 8, 3);
		var1.setBlock(var3 + b4, var4 + height, var5 + 4, this.woodId, this.woodMeta, 3);
		setBlockIfEmpty(var3 + b4, var4 + height + 1, var5 + 4, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b4, var4 + height + 1, var5 + 3, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b4, var4 + height + 1, var5 + 5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b4 + 1, var4 + height + 1, var5 + 4, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + b4 - 1, var4 + height + 1, var5 + 4, this.leavesId, this.leavesMeta, 3, var1);
		
		int x1;
		int y1;
		int z1;
		
		int x2;
		int y2;
		int z2;
		
		if(var2.nextInt(2) == 0)
		{
			var1.setBlock(var3 + 1, var4 + height, var5, this.woodId, this.woodMeta, 3);
			if(var2.nextInt(2) == 0)
			{
				var1.setBlock(var3 + 2, var4 + height + 1, var5, this.woodId, this.woodMeta, 3);
				x1 = var3 + 2;
				y1 = var4 + height + 2;
				z1 = var5 + 0;
			}
			else
			{
				var1.setBlock(var3 + 1, var4 + height + 1, var5 - 1, this.woodId, this.woodMeta, 3);
				x1 = var3 + 1;
				y1 = var4 + height + 2;
				z1 = var5 - 1;
			}

			var1.setBlock(var3, var4 + height, var5 + 1, this.woodId, this.woodMeta, 3);
			if(var2.nextInt(2) == 0)
			{
				var1.setBlock(var3 - 1, var4 + height + 1, var5 + 1, this.woodId, this.woodMeta, 3);
				x2 = var3 - 1;
				y2 = var4 + height + 2;
				z2 = var5 + 1;
			}
			else
			{
				var1.setBlock(var3, var4 + height + 1, var5 + 2, this.woodId, this.woodMeta, 3);
				x2 = var3 + 0;
				y2 = var4 + height + 2;
				z2 = var5 + 2;
			}
		}
		else
		{
			var1.setBlock(var3, var4 + height, var5, this.woodId, this.woodMeta, 3);
			if(var2.nextInt(2) == 0)
			{
				var1.setBlock(var3 - 1, var4 + height + 1, var5, this.woodId, this.woodMeta, 3);
				x1 = var3 - 1;
				y1 = var4 + height + 2;
				z1 = var5 + 1;
			}
			else
			{
				var1.setBlock(var3, var4 + height + 1, var5 - 1, this.woodId, this.woodMeta, 3);
				x1 = var3 + 0;
				y1 = var4 + height + 2;
				z1 = var5  - 1;
			}

			var1.setBlock(var3 + 1, var4 + height, var5 + 1, this.woodId, this.woodMeta, 3);
			if(var2.nextInt(2) == 0)
			{
				var1.setBlock(var3 + 2, var4 + height + 1, var5 + 1, this.woodId, this.woodMeta, 3);
				x2 = var3 + 2;
				y2 = var4 + height + 2;
				z2 = var5 + 1;
			}
			else
			{
				var1.setBlock(var3 + 1, var4 + height + 1, var5 + 2, this.woodId, this.woodMeta, 3);
				x2 = var3 + 1;
				y2 = var4 + height + 2;
				z2 = var5 + 2;
			}
		}
		
		setBlockIfEmpty(x1, y1, z1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x1 + 1, y1, z1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x1, y1, z1 + 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x1 - 1, y1, z1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x1, y1, z1 - 1, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(x2, y2, z2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x2 + 1, y2, z2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x2, y2, z2 + 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x2 - 1, y2, z2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(x2, y2, z2 - 1, this.leavesId, this.leavesMeta, 3, var1);
		
		return true;
	}
}
