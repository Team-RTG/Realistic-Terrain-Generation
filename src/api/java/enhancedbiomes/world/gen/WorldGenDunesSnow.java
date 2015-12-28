package enhancedbiomes.world.gen;

import java.util.Random;

import enhancedbiomes.world.biome.EnhancedBiomesSand;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDunesSnow extends WorldGenerator
{
	int height = 4;
	int steep = 3;
	
	BiomeGenBase biome;
	Block block;
	
	public WorldGenDunesSnow(BiomeGenBase bi, Block bl)
	{
		biome = bi;
		block = bl;
	}
	
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) 
	{
		int variation = (var3 / 5) % 16;
		int finalZ = var5;
		
		for(int z = finalZ; z < finalZ + (height * 2); z++)
		{
			if((var1.getBlock(var3, var1.getTopSolidOrLiquidBlock(var3, z) - 1, z) == block || var1.getBlock(var3, var1.getTopSolidOrLiquidBlock(var3, z) - 1, z) == Blocks.snow_layer) && 
				var1.getBiomeGenForCoords(var3, z) == biome)
			{
				for(int y = 0; y < height - 1 - ((Math.abs(Math.max(finalZ, z) - Math.min(finalZ, z))) / steep); y++)
				{
					setBlockIfEmpty(var3, var1.getTopSolidOrLiquidBlock(var3, z), z, block, 0, 3, var1);
				}
			}
		}
		
		for(int z = finalZ - 1; z > finalZ - height; z--)
		{
			if((var1.getBlock(var3, var1.getTopSolidOrLiquidBlock(var3, z) - 1, z) == block || var1.getBlock(var3, var1.getTopSolidOrLiquidBlock(var3, z) - 1, z) == Blocks.snow_layer) && 
				var1.getBiomeGenForCoords(var3, z) == biome)
			{
				for(int y = 0; y < height - 1 - Math.abs(Math.max(finalZ, z) - Math.min(finalZ, z)); y++)
				{
					setBlockIfEmpty(var3, var1.getTopSolidOrLiquidBlock(var3, z), z, block, 0, 3, var1);
				}
			}
		}
		
		return true;
	}
}
