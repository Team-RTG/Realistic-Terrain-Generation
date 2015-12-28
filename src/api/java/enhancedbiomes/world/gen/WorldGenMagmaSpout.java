package enhancedbiomes.world.gen;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

import java.util.Random;

import enhancedbiomes.world.gen.geometry.WorldGenSphere;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMagmaSpout extends WorldGenerator
{
	Block liquidId = Blocks.flowing_lava;
	int height = 3;
	Block lakeSurround = Blocks.obsidian;
	
	public WorldGenMagmaSpout(Block liquid, int height, Block lakeSurround)
	{
		this.liquidId = liquid;
		this.height = height;
		this.lakeSurround = lakeSurround;
	}
	
	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z) 
	{
		int ventHeight = par1World.getTopSolidOrLiquidBlock(x, z);
		
		if (ventHeight < 16 || ventHeight > 128) 
		{
			return false;
		}
		
		int maxY = ventHeight + this.height;
		int baseY = 17 + par2Random.nextInt(8);
		
		//Generate Conduit
		for (int posY = baseY + 1; posY <= ventHeight - 4; posY++) 
		{
			par1World.setBlock(x + 1, posY, z, this.lakeSurround);
			par1World.setBlock(x - 1, posY, z, this.lakeSurround);
			par1World.setBlock(x, posY, z + 1, this.lakeSurround);
			par1World.setBlock(x, posY, z - 1, this.lakeSurround);
		}
		
		//Generate Magma Chamber
		int radius = (maxY - baseY) / 10;
		(new WorldGenSphere(this.lakeSurround, radius + 1, true, this.liquidId)).generate(par1World, par2Random, x, baseY, z);
		(new WorldGenSphere(this.liquidId, radius)).generate(par1World, par2Random, x, baseY, z);
		
		//Generate Throat
		if(par2Random.nextInt(4) != 0)
		{
			(new WorldGenMagmaPools(this.liquidId, this.lakeSurround)).generate(par1World, par2Random, x, y, z);
		}		
		
		//Fill Conduit
		for (int posY = baseY + 1; posY <= maxY; posY++) 
		{
			par1World.setBlock(x, posY, z, this.liquidId);
		}
		
		return true;
	}
}
