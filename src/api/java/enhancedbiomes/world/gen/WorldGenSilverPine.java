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

public class WorldGenSilverPine extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	int height;
	
	public WorldGenSilverPine(Block woodId, int woodMeta, Block leavesId, int leavesMeta, int height)
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
			for(int y = height - 2; y < height + 13; y++)
			{
				for(int z = -1; z <= 1; z++)
				{
					if(var1.getBlock(var3 + x, var4 + y, var5 + z) != Blocks.air)
					{
						return false;
					}
				}
			}
		}
		
		//Layer 1
		for(int x = -2; x <= 2; x++)
		{
			for(int y = height + 1; y < height + 5; y++)
			{
				setBlockIfEmpty(var3 + x, var4 + y, var5, this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3, var4 + y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
			}
		}
		
		//Layer 1 & 2
		for(int x = -1; x <= 1; x++)
		{
			for(int y = height + 1; y < height + 9; y++)
			{
				for(int z = -1; z <= 1; z++)
				{
					setBlockIfEmpty(var3 + x, var4 + y, var5 + z, this.leavesId, this.leavesMeta, 3, var1);
				}
			}
		}
		
		//Layer 3
		for(int x = -1; x <= 1; x++)
		{
			for(int y = height + 9; y < height + 13; y++)
			{
				setBlockIfEmpty(var3 + x, var4 + y, var5, this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3, var4 + y, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
			}
		}
		
		//Layer 4
		for(int y = height + 13; y < height + 18; y++)
		{
			setBlockIfEmpty(var3, var4 + y, var5, this.leavesId, this.leavesMeta, 3, var1);
		}
		
		//Trunk
		for(int i = 0; i < height + 13; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		}	
		
		return true;
	}
}
