package enhancedbiomes.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.EnhancedBiomesMath;
import enhancedbiomes.world.gen.geometry.WorldGenCylinderIfEmpty;
import enhancedbiomes.world.gen.geometry.WorldGenSphereIfEmpty;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenKapok extends WorldGenAbstractTree
{
	int height;
	Block woodId;
	int woodMeta;
	BlockLeavesBase leavesId;
	int leavesMeta;
	
	public WorldGenKapok(Block woodId, int woodMeta, BlockLeavesBase leavesId, int leavesMeta, int height)
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
			
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < 2; x++)
			{
				for(int z = 0; z < 2; z++)
				{
					if(var1.getBlock(var3 + z, var4 + y, var5 + x) != Blocks.air)
					{
						return false;
					}
				}
			}
		}
		
		//Trunk
		for(int i = 0; i < this.height; i++)
		{
			var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 1, var4 + i, var5, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3, var4 + i, var5 + 1, this.woodId, this.woodMeta, 3);
			var1.setBlock(var3 + 1, var4 + i, var5 + 1, this.woodId, this.woodMeta, 3);
		}
		
		//Buttresses
		ArrayList<Integer> buttressList = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
		for(int a = 0; a < 4; a++)
		{
			int currentButress = buttressList.get(var2.nextInt(buttressList.size()));
			int x = var3;
			int z = var5;
			int endX = var3;
			int endZ = var5;
			switch(currentButress)
			{
			//1
			case 0:
				x -= 1; z -= 0;
				break;
			case 1:
				x -= 1; z -= 1;
				break;
			case 2:
				x -= 0; z -= 1;
				break;
			//2
			case 3:
				x += 1; z -= 1;
				break;
			case 4:
				x += 2; z -= 1;
				break;
			case 5:
				x += 2; z += 0;
				break;
			//3
			case 6:
				x += 2; z += 1;
				break;
			case 7:
				x += 2; z += 2;
				break;
			case 8:
				x += 1; z += 2;
				break;
			//3
			case 9:
				x += 0; z += 2;
				break;
			case 10:
				x -= 1; z += 2;
				break;
			case 11:
				x -= 1; z += 1;
				break;
			//0
			default:
				x -= 1; z -= 1;
				break;
			}

			endX = (x - var3 == 1 || x - var3 == 2) ? x + 2 : x - 2;
			endZ = (z - var5 == 1 || z - var5 == 2) ? z + 2 : z - 2;
			
			for(int b = 0; b < 4; b++) 
			{
				setBlockIfEmpty(x, var4 + b, z, woodId, woodMeta, 3, var1);
				setBlockIfEmpty((int) EnhancedBiomesMath.average(x, endX), var4 + b - 2, (int) EnhancedBiomesMath.average(z, endZ), woodId, woodMeta, 3, var1);
				setBlockIfEmpty(endX, var4 + b - 3, endZ, woodId, woodMeta, 3, var1);
			}
		}
		
		//Branches
		for(int a = 0; a < 10; a++)
		{
			int heightOnTrunk = height - var2.nextInt(10) - 3;
			
			int disX = var2.nextInt(22) - 10;
			int disY = var2.nextInt(7) - 3;
			int disZ = var2.nextInt(22) - 10;
			
			int posX = var3 + disX;
			int posY = var4 + heightOnTrunk + disY;
			int posZ = var5 + disZ;

			for(int b = 0; b < 10; b++)
			{
				int x = disX * (b + 1) / 10;
				int y = disY * (b + 1) / 10;
				int z = disZ * (b + 1) / 10;
				
				var1.setBlock(var3 + x, var4 + heightOnTrunk - 1 + y, var5 + z, this.woodId, this.woodMeta, 3);
				
				if(b == 9)
				{
					posX = var3 + x;
					posY = var4 + heightOnTrunk - 1 + y;
					posZ = var5 + z;
				}
			}
			
			var1.setBlock(posX, posY, posZ, this.woodId, this.woodMeta, 3);
			
			//Leaves
			new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 5, 1).generate(var1, var2, posX, posY, posZ);
			new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 3, 1).generate(var1, var2, posX, posY + 1, posZ);
		}
		
		//Canopy
		int chX = var2.nextInt(2);
		int chZ = var2.nextInt(2);
		
		var1.setBlock(var3, var4 + height, var5, woodId, woodMeta, 3);
		new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 7, 1).generate(var1, var2, var3 + chX, var4 + height, var5 + chZ);
		new WorldGenCylinderIfEmpty(leavesId, leavesMeta, 5, 1).generate(var1, var2, var3 + chX, var4 + height + 1, var5 + chZ);
		
		return true;
	}
}
