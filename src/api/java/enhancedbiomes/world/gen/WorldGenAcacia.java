package enhancedbiomes.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.gen.geometry.WorldGenCylinderIfEmpty;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenAcacia extends WorldGenAbstractTree
{
	int height;
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	
	public WorldGenAcacia(int height, Block woodId, int woodMeta, Block leavesId, int leavesMeta)
	{
		super(true);
		this.height = height;
		this.woodId = woodId;
		this.woodMeta = woodMeta;
		this.leavesId = leavesId;
		this.leavesMeta = leavesMeta;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		if(!var1.getBlock(var3, var4 - 1, var5).canSustainPlant(var1, var3, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
		{
			return false;
		}
		
		for(int i = 0; i < this.height + 3; i++)
		{
			if(var1.getBlock(var3, var4 + i, var5) != Blocks.air)
			{
				return false;
			}
		}
		
		for(int i = 0; i < this.height + 3; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		}
		
		ArrayList<Integer> sides = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
		
		int ran1 = var2.nextInt(4);
		int dir1 = sides.get(ran1);
		sides.remove(ran1);
		
		int ran2 = var2.nextInt(3);
		int dir2 = sides.get(ran2);
					
		//Low
		if(dir1 == 1)
		{
			var1.setBlock(var3 + 1, var4 + this.height + 2, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 2, var4 + this.height + 3, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 2, var4 + this.height + 4, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 3, var4 + this.height + 5, var5, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3 + 3, var4 + this.height + 6, var5);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3 + 3, var4 + this.height + 7, var5);
		}
		
		if(dir1 == 2)
		{
			var1.setBlock(var3 - 1, var4 + this.height + 2, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 - 2, var4 + this.height + 3, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 - 2, var4 + this.height + 4, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 - 3, var4 + this.height + 5, var5, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3 - 3, var4 + this.height + 6, var5);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3 - 3, var4 + this.height + 7, var5);
		}
		
		if(dir1 == 3)
		{
			var1.setBlock(var3, var4 + this.height + 2, var5 + 1, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 3, var5 + 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 4, var5 + 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 5, var5 + 3, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3, var4 + this.height + 6, var5 + 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3, var4 + this.height + 7, var5 + 3);
		}
		
		if(dir1 == 4)
		{
			var1.setBlock(var3, var4 + this.height + 2, var5 - 1, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 3, var5 - 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 4, var5 - 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 5, var5 - 3, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3, var4 + this.height + 6, var5 - 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3, var4 + this.height + 7, var5 - 3);
		}		
		
		//High
		if(dir2 == 1)
		{
			var1.setBlock(var3 + 1, var4 + this.height + 3, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 2, var4 + this.height + 4, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 2, var4 + this.height + 5, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 3, var4 + this.height + 6, var5, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3 + 3, var4 + this.height + 7, var5);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3 + 3, var4 + this.height + 8, var5);
		}
		
		if(dir2 == 2)
		{
			var1.setBlock(var3 - 1, var4 + this.height + 3, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 - 2, var4 + this.height + 4, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 - 2, var4 + this.height + 5, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 - 3, var4 + this.height + 6, var5, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3 - 3, var4 + this.height + 7, var5);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3 - 3, var4 + this.height + 8, var5);
		}
		
		if(dir2 == 3)
		{
			var1.setBlock(var3, var4 + this.height + 3, var5 + 1, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 4, var5 + 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 5, var5 + 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 6, var5 + 3, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3, var4 + this.height + 7, var5 + 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3, var4 + this.height + 8, var5 + 3);
			
		}
		
		if(dir2 == 4)
		{
			var1.setBlock(var3, var4 + this.height + 3, var5 - 1, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 4, var5 - 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 5, var5 - 2, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + this.height + 6, var5 - 3, this.woodId, this.woodMeta, 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 5, 1)).generate(var1, var2, var3, var4 + this.height + 7, var5 - 3);
			(new WorldGenCylinderIfEmpty(this.leavesId, this.leavesMeta, 3, 1)).generate(var1, var2, var3, var4 + this.height + 8, var5 - 3);
		}
		
		return true;
	}
}
