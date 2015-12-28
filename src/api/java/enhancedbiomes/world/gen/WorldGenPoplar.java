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

public class WorldGenPoplar extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	int height;
	
	public WorldGenPoplar(Block woodId, int woodMeta, Block leavesId, int leavesMeta, int height)
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
		
		for(int x = -1; x <= 1; x++)
		{
			for(int y = height + 2; y < height + 8; y++)
			{
				for(int z = -1; z <= 1; z++)
				{
					setBlockIfEmpty(var3 + x, var4 + y, var5 + z, this.leavesId, this.leavesMeta, 3, var1);
				}
			}
		}
		
		setBlockIfEmpty(var3 + 1, var4 + height, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 1, var4 + height, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height, var5 + 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height, var5 - 1, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(var3 + 1, var4 + height + 1, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 1, var4 + height + 1, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 1, var5 + 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 1, var5 - 1, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(var3 + 1, var4 + height + 8, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 1, var4 + height + 8, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 8, var5 + 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 8, var5 - 1, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(var3 + 1, var4 + height + 9, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 1, var4 + height + 9, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 9, var5 + 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 9, var5 - 1, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(var3 + 2, var4 + height + 4, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 2, var4 + height + 4, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 4, var5 + 2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 4, var5 - 2, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(var3 + 2, var4 + height + 5, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 2, var4 + height + 5, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 5, var5 + 2, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + height + 5, var5 - 2, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(var3, var4 + height + 10, var5, this.leavesId, this.leavesMeta, 3, var1);
		
		for(int i = 0; i < height + 10; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		}	
		
		return true;
	}
}
