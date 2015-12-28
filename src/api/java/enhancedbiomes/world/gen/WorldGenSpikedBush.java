package enhancedbiomes.world.gen;

import java.util.Random;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenSpikedBush extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	Block growOn;
	
	public WorldGenSpikedBush(Block woodId, int woodMeta, Block leavesId, int leavesMeta, Block growOnBlockID)
	{
		super(true);
		this.woodId = woodId;
		this.woodMeta = woodMeta;
		this.leavesId = leavesId;
		this.leavesMeta = leavesMeta;
		this.growOn = growOnBlockID;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		if(var1.getBlock(var3, var4 - 1, var5) != growOn)
		{
			return false;
		}
		
		for(int i = 0; i < 4; i++)
		{
			if(var1.getBlock(var3, var4 + i, var5) != Blocks.air)
			{
				return false;
			}
		}
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(var1.getBlock(var3 - 1 + i, var4 + 2, var5 - 1 + j) != Blocks.air)
				{
					return false;
				}
			}
		}
		
		for(int i = 0; i < 3; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		}
		
		setBlockIfEmpty(var3, var4 + 3, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + 1, var4 + 2, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 - 1, var4 + 2, var5, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + 2, var5 + 1, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3, var4 + 2, var5 - 1, this.leavesId, this.leavesMeta, 3, var1);
		
		return true;
	}
}
