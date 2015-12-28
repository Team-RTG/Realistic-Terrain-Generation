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

public class WorldGenMangrove extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	int height;
	
	public WorldGenMangrove(Block woodId, int woodMeta, Block leavesId, int leavesMeta, int height)
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
		if(var1.getBlock(var3, var4 - 1, var5) != Blocks.sand && !var1.getBlock(var3, var4 - 1, var5).canSustainPlant(var1, var3, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes)EnhancedBiomesBlocks.saplingEB))
		{
			return false;
		}
		
		for(int i = 0; i < this.height; i++)
		{
			for(int k = 0; k < 2; k++)
			{
				for(int j = 0; j < 2; j++)
				{
					if(var1.getBlock(var3 + k, var4 + i, var5 + j) != Blocks.air && var1.getBlock(var3 + k, var4 + i, var5 + j) != Blocks.water && var1.getBlock(var3 + k, var4 + i, var5 + j) != Blocks.flowing_water)
					{
						return false;
					}
				}
			}
		}
		
		for(int i = 0; i < 2; i++)
		{
			for(int x = -1; x < 3; x++)
			{
				for(int z = 0; z < 2; z++)
				{
					setBlockIfEmpty(var3 + x, var4 + 2 + i, var5 + z, this.leavesId, this.leavesMeta, 3, var1);
					setBlockIfEmpty(var3 + z, var4 + 2 + i, var5 + x, this.leavesId, this.leavesMeta, 3, var1);
				}
			}
		}		
		
		for(int i = 0; i < height; i++)
		{
			for(int x = 0; x < 2; x++)
			{
				for(int z = 0; z < 2; z++)
				{
					var1.setBlock(var3 + x, var4 + i, var5 + z, this.woodId, this.woodMeta, 3);
				}
			}
		}
		
		int topX = var2.nextInt(2);
		int topZ = var2.nextInt(2);
		
		var1.setBlock(var3 + topX, var4 + height, var5 + topZ, this.woodId, this.woodMeta, 3);
		setBlockIfEmpty(var3 + topX, var4 + height + 1, var5 + topZ, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + 1 - topX, var4 + height + 1, var5 + topZ, this.leavesId, this.leavesMeta, 3, var1);
		setBlockIfEmpty(var3 + topX, var4 + height + 1, var5 + 1 - topZ, this.leavesId, this.leavesMeta, 3, var1);

		createRoot(var3 - 1, var4, var5, var2, var1, woodId, woodMeta);
		createRoot(var3 - 1, var4, var5 + 1, var2, var1, woodId, woodMeta);
		
		createRoot(var3, var4, var5 - 1, var2, var1, woodId, woodMeta);
		createRoot(var3 + 1, var4, var5 - 1, var2, var1, woodId, woodMeta);
		
		createRoot(var3 + 2, var4, var5, var2, var1, woodId, woodMeta);
		createRoot(var3 + 2, var4, var5 + 1, var2, var1, woodId, woodMeta);
		
		createRoot(var3, var4, var5 + 2, var2, var1, woodId, woodMeta);
		createRoot(var3 + 1, var4, var5 + 2, var2, var1, woodId, woodMeta);
		
		return true;
	}
	
	public static void createRoot(int x, int y, int z, Random rand, World world, Block woodId, int woodMeta)
	{
		int type = rand.nextInt(4);
		
		if(type < 2)
		{
			world.setBlock(x, y - type, z, woodId, woodMeta, 3);
		}
	}
}
