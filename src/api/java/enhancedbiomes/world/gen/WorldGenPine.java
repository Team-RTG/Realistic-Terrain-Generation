package enhancedbiomes.world.gen;

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

public class WorldGenPine extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	int height;
	
	public WorldGenPine(Block woodId, int woodMeta, Block leavesId, int leavesMeta, int height)
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
		
		for(int i = 0; i < this.height * 3 + 3; i++)
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
		
		for(int y = 1; y <= height; y++)
		{
			new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 4, 1).generate(var1, var2, var3, var4 + (y * 3), var5);
			new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 3, 1).generate(var1, var2, var3, var4 + (y * 3) + 1, var5);
			new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 2, 1).generate(var1, var2, var3, var4 + (y * 3) + 2, var5);
		}		
		
		for(int i = 0; i <= height * 3 + 3; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		}
		
		new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 1, 1).generate(var1, var2, var3, var4 + this.height * 3 + 3, var5);
		setBlockIfEmpty(var3, var4 + this.height * 3 + 4, var5, this.leavesId, this.leavesMeta, 3, var1);
		
		return true;
	}
}
