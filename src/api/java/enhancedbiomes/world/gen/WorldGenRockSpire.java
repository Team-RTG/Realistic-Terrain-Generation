package enhancedbiomes.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.*;

public class WorldGenRockSpire extends WorldGenerator
{
	Block[] blocks;
	int height;
	byte[] metas;
	
	public WorldGenRockSpire(Block[] materials, byte[] meta, int height)
	{
		this.blocks = materials;
		this.height = height;
		this.metas = meta;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		int yC = height / 2 + var2.nextInt(height / 2) + 1;
		int yC2 = getTopStoneBlock(var3, var5, var1) - var4;
		
		for(int addY = yC; addY >= yC2; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3, var4 + addY, var5, blocks[i], metas[i], 3);
		}
		
		int[][] hList = new int[4][3];
		
		for(int a = 0; a < 4; a++)
		{
			int i1 = yC / 2 + var2.nextInt(yC / 2) + 1;
			int i2 = i1 / 2 + var2.nextInt(i1 / 2) + 1;
			int i3 = i1 / 2 + var2.nextInt(i1 / 2) + 1;
			
			hList[a] = new int[]{i1, i2, i3};
		}
		
		//1
		int i112 = getTopStoneBlock(var3 + 1, var5, var1) - var4;
		int i122 = getTopStoneBlock(var3 + 2, var5, var1) - var4;
		int i132 = getTopStoneBlock(var3 + 1, var5 + 1, var1) - var4;
		
		for(int addY = hList[0][0]; addY >= i112; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 + 1, var4 + addY, var5, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[0][1]; addY >= i122; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 + 2, var4 + addY, var5, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[0][2]; addY >= i132; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 + 1, var4 + addY, var5 + 1, blocks[i], metas[i], 3);
		}
		
		//2
		int i212 = getTopStoneBlock(var3, var5 + 1, var1) - var4;
		int i222 = getTopStoneBlock(var3, var5 + 2, var1) - var4;
		int i232 = getTopStoneBlock(var3 - 1, var5 + 1, var1) - var4;
		
		for(int addY = hList[1][0]; addY >= i212; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3, var4 + addY, var5 + 1, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[1][1]; addY >= i222; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3, var4 + addY, var5 + 2, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[1][2]; addY >= i232; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 - 1, var4 + addY, var5 + 1, blocks[i], metas[i], 3);
		}
		
		//3
		int i312 = getTopStoneBlock(var3 - 1, var5, var1) - var4;
		int i322 = getTopStoneBlock(var3 - 2, var5, var1) - var4;
		int i332 = getTopStoneBlock(var3 - 1, var5 - 1, var1) - var4;
		
		for(int addY = hList[2][0]; addY >= i312; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 - 1, var4 + addY, var5, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[2][1]; addY >= i322; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 - 2, var4 + addY, var5, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[2][2]; addY >= i332; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 - 1, var4 + addY, var5 - 1, blocks[i], metas[i], 3);
		}
		
		//4
		int i412 = getTopStoneBlock(var3, var5 - 1, var1) - var4;
		int i422 = getTopStoneBlock(var3, var5 - 2, var1) - var4;
		int i432 = getTopStoneBlock(var3 + 1, var5 - 1, var1) - var4;
		
		for(int addY = hList[3][0]; addY >= i412; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3, var4 + addY, var5 - 1, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[3][1]; addY >= i422; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3, var4 + addY, var5 - 2, blocks[i], metas[i], 3);
		}
		
		for(int addY = hList[3][2]; addY >= i432; addY--)
		{
			int i = var2.nextInt(blocks.length);
			var1.setBlock(var3 + 1, var4 + addY, var5 - 1, blocks[i], metas[i], 3);
		}
		
		return true;
	}
}
