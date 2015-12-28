package enhancedbiomes.world.gen;

import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenCypress extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	int height;
	
	public WorldGenCypress(Block woodId, int woodMeta, Block leavesId, int leavesMeta, int height)
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
		
		for(int i = 0; i < this.height * 2; i++)
		{
			for(int k = 0; k < 5; k++)
			{
				for(int j = 0; j < 5; j++)
				{
					if(var1.getBlock(var3 - 2 + k, var4 + 3 + i, var5 - 2 + j) != Blocks.air)
					{
						return false;
					}
				}
			}
		}
		
		for(int x = 0; x < height; x++)
		{
			int largeLayer = x * 2 + 3;
			int smallLayer = x * 2 + 4;
			
			//Large Layer
			for(int y = -2; y < 3; y++)
			{
				for(int z = -2; z < 3; z++)
				{
					if((y == -2 || y == 2) && (z == -2 || z == 2))
					{
						
					}
					else
					{
						setBlockIfEmpty(var3 + y, var4 + largeLayer, var5 + z, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
			
			//Small Layer
			for(int y = -1; y < 2; y++)
			{
				for(int z = -1; z < 2; z++)
				{
					if((y == -1 || y == 1) && (z == -1 || z == 1))
					{
						
					}
					else
					{
						setBlockIfEmpty(var3 + y, var4 + smallLayer, var5 + z, this.leavesId, this.leavesMeta, 3, var1);
					}
				}
			}
		}		
		
		for(int i = 0; i < (height * 2) + 3; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		}
		
		setBlockIfEmpty(var3, var4 + (this.height * 2) + 3, var5, this.leavesId, this.leavesMeta, 3, var1);
		
		return true;
	}
}
