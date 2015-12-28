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

public class WorldGenTallShrub extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	BlockLeavesBase leavesId;
	int leavesMeta;
	int height;
	
	public WorldGenTallShrub(Block woodId, int woodMeta, BlockLeavesBase leavesId, int leavesMeta, int height)
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
		
		for(int i = 1; i < (height * 2) + 1; i++)
		{
			for(int k = 0; k < 7; k++)
			{
				for(int j = 0; j < 7; j++)
				{
					if(var1.getBlock(var3 - 3 + k, var4 + i, var5 - 3 + j) != Blocks.air)
					{
						return false;
					}
				}
			}
		}
		
		int h1t = 1 + var2.nextInt(2);
		int h1b = 1 + var2.nextInt(2);
		int h1l = 1 + var2.nextInt(2);
		int h1r = 1 + var2.nextInt(2);
		
		int h2t = 1 + var2.nextInt(2);
		int h2b = 1 + var2.nextInt(2);
		int h2l = 1 + var2.nextInt(2);
		int h2r = 1 + var2.nextInt(2);
		
		var1.setBlock(var3, var4, var5, this.woodId, this.woodMeta, 3);
		
		for(int x = 0; x < h1t; x++)
		{
			var1.setBlock(var3, var4 + x, var5 + 1, this.woodId, this.woodMeta, 3);
		}
		
		for(int x = 0; x < h1b; x++)
		{
			var1.setBlock(var3, var4 + x, var5 - 1, this.woodId, this.woodMeta, 3);
		}
		
		for(int x = 0; x < h1l; x++)
		{
			var1.setBlock(var3 + 1, var4 + x, var5, this.woodId, this.woodMeta, 3);
		}
		
		for(int x = 0; x < h1r; x++)
		{
			var1.setBlock(var3 - 1, var4 + x, var5, this.woodId, this.woodMeta, 3);
		}
		
		
		for(int x = 0; x < h2t; x++)
		{
			int varX = var3;
			int varZ = var5 + 2;
			
			var1.setBlock(varX, var4 + x + h1t, varZ, this.woodId, this.woodMeta, 3);
			setBlockIfEmpty(varX, var4 + h2t + h1t, varZ, this.leavesId, this.leavesMeta, 3, var1);

			setBlockIfEmpty(varX + 1, var4 + x + h1t, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX - 1, var4 + x + h1t, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1t, varZ + 1, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1t, varZ - 1, this.leavesId, this.leavesMeta, 3, var1);
		}
		
		for(int x = 0; x < h2b; x++)
		{
			int varX = var3;
			int varZ = var5 - 2;
			
			var1.setBlock(varX, var4 + x + h1b, varZ, this.woodId, this.woodMeta, 3);
			setBlockIfEmpty(varX, var4 + h2b + h1b, varZ, this.leavesId, this.leavesMeta, 3, var1);

			setBlockIfEmpty(varX + 1, var4 + x + h1b, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX - 1, var4 + x + h1b, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1b, varZ + 1, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1b, varZ - 1, this.leavesId, this.leavesMeta, 3, var1);
		}
		
		for(int x = 0; x < h2l; x++)
		{
			int varX = var3 + 2;
			int varZ = var5;
			
			var1.setBlock(varX, var4 + x + h1l, varZ, this.woodId, this.woodMeta, 3);
			setBlockIfEmpty(varX, var4 + h2l + h1l, varZ, this.leavesId, this.leavesMeta, 3, var1);

			setBlockIfEmpty(varX + 1, var4 + x + h1l, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX - 1, var4 + x + h1l, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1l, varZ + 1, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1l, varZ - 1, this.leavesId, this.leavesMeta, 3, var1);
		}
		
		for(int x = 0; x < h2r; x++)
		{
			int varX = var3 - 2;
			int varZ = var5;
			
			var1.setBlock(varX, var4 + x + h1r, varZ, this.woodId, this.woodMeta, 3);
			setBlockIfEmpty(varX, var4 + h2r + h1r, varZ, this.leavesId, this.leavesMeta, 3, var1);

			setBlockIfEmpty(varX + 1, var4 + x + h1r, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX - 1, var4 + x + h1r, varZ, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1r, varZ + 1, this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(varX, var4 + x + h1r, varZ - 1, this.leavesId, this.leavesMeta, 3, var1);
		}
		
		return true;
	}
}
