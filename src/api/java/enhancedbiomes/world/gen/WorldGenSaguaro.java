package enhancedbiomes.world.gen;

import java.util.Random;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import static enhancedbiomes.blocks.EnhancedBiomesBlocks.saguaro;

public class WorldGenSaguaro extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if(world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, saguaro)) {
			for(int a = -2; a <= 2; a++) {
				for(int c = -2; c <= 2; c++) {
					for(int b = 2; b <= 5; b++) {
						if(world.getBlock(x + a, y + b, z + c) != Blocks.air) return false;
					}
				}
			}
			if(world.getBlock(x, y, z) != Blocks.air) return false;
			if(world.getBlock(x, y + 1, z) != Blocks.air) return false;
			
			int height = 1;
			
			world.setBlock(x, y, z, saguaro, 0, 3);
			world.setBlock(x, y + 1, z, saguaro, 0, 3);
			world.setBlock(x, y + 2, z, saguaro, 0, 3);
			world.setBlock(x, y + 3, z, saguaro, 0, 3);
			world.setBlock(x, y + 4, z, saguaro, 0, 3);
			world.setBlock(x, y + 5, z, saguaro, 11, 3);

			world.setBlock(x, y + height, z, saguaro, 1, 3);

			world.setBlock(x + 1, y + height, z, saguaro, 3, 3);
			world.setBlock(x - 1, y + height, z, saguaro, 4, 3);
			world.setBlock(x, y + height, z + 1, saguaro, 5, 3);
			world.setBlock(x, y + height, z - 1, saguaro, 6, 3);

			world.setBlock(x + 2, y + height, z, saguaro, 7, 3);
			world.setBlock(x - 2, y + height, z, saguaro, 8, 3);
			world.setBlock(x, y + height, z + 2, saguaro, 9, 3);
			world.setBlock(x, y + height, z - 2, saguaro, 10, 3);

			if(rand.nextInt(2) == 0) {
				world.setBlock(x + 2, y + 1 + height, z, saguaro, 0, 3);
				world.setBlock(x + 2, y + 2 + height, z, saguaro, 2, 3);
			}
			else world.setBlock(x + 2, y + 1 + height, z, saguaro, 2, 3);
			
			if(rand.nextInt(2) == 0) {
				world.setBlock(x - 2, y + 1 + height, z, saguaro, 0, 3);
				world.setBlock(x - 2, y + 2 + height, z, saguaro, 2, 3);
			}
			else world.setBlock(x - 2, y + 1 + height, z, saguaro, 2, 3);
			
			if(rand.nextInt(2) == 0) {
				world.setBlock(x, y + 1 + height, z + 2, saguaro, 0, 3);
				world.setBlock(x, y + 2 + height, z + 2, saguaro, 2, 3);
			}
			else world.setBlock(x, y + 1 + height, z + 2, saguaro, 2, 3);
			
			if(rand.nextInt(2) == 0) {
				world.setBlock(x, y + 1 + height, z - 2, saguaro, 0, 3);
				world.setBlock(x, y + 2 + height, z - 2, saguaro, 2, 3);
			}
			else world.setBlock(x, y + 1 + height, z - 2, saguaro, 2, 3);

			return true;
		}
		return false;
	}
}
