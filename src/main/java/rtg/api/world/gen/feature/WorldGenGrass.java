package rtg.api.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenGrass extends WorldGenerator {

    private IBlockState block;
    private int metadata;

    public WorldGenGrass(IBlockState b, int m) {

        block = b.getBlock().getStateFromMeta(m);
        metadata = m;
    }

    protected IBlockState block() {

        return block;
    }

    protected int metadata() {

        return metadata;
    }

    protected void setBlock(Random random) {

    }// nothing needed

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        while (y > 0) {
            if (!world.isAirBlock(new BlockPos(x, y, z)) || world.getBlockState(new BlockPos(x, y, z)).getBlock().isLeaves(world.getBlockState(new BlockPos(x, y, z)), world, new BlockPos(x, y, z))) {
                break;
            }
            y--;
        }

        setBlock(rand);
        if (block() == Blocks.DOUBLE_PLANT.getStateFromMeta(metadata)) {
            //for (int l = 0; l < 64; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && j1 < 254 && Blocks.DOUBLE_PLANT.canBlockStay(world, new BlockPos(i1, j1, k1), Blocks.DOUBLE_PLANT.getDefaultState())) {
                    world.setBlockState(new BlockPos(i1, j1, k1), Blocks.DOUBLE_PLANT.getStateFromMeta(metadata));
                }
            }
        }
        else if (block() == Blocks.LEAVES) {
            //for (int l = 0; l < 64; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock() == Blocks.GRASS) {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getBlock().getStateFromMeta(metadata), 0);
                }
            }
        }
        else {
            //for (int l = 0; l < 128; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && block.getBlock().canPlaceBlockAt(world, new BlockPos(i1, j1, k1))) {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getBlock().getStateFromMeta(metadata), 0);
                }
            }
        }
        return true;
    }

    public static class SingleType extends WorldGenGrass {

        public SingleType(IBlockState b, int m) {

            super(b, m);
        }
    }
}
