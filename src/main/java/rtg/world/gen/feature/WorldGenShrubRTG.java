package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.config.rtg.ConfigRTG;

public class WorldGenShrubRTG extends WorldGenerator {

    private int varSize;
    private IBlockState logBlock;
    private IBlockState leaveBlock;
    private boolean varSand;

    public WorldGenShrubRTG(int size, IBlockState log, IBlockState leav, boolean sand) {

        varSize = size;
        varSand = sand;

        logBlock = log;
        leaveBlock = leav;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int width = varSize > 6 ? 6 : varSize;
        int height = varSize > 3 ? 2 : 1;

        for (int i = 0; i < varSize; i++) {
            int rX = rand.nextInt(width * 2) - width;
            int rY = rand.nextInt(height);
            int rZ = rand.nextInt(width * 2) - width;

            if (i == 0 && varSize > 4) {
                buildLeaves(world, x + rX, y, z + rZ, 3);
            }
            else if (i == 1 && varSize > 2) {
                buildLeaves(world, x + rX, y, z + rZ, 2);
            }
            else {
                buildLeaves(world, x + rX, y + rY, z + rZ, 1);
            }
        }
        return true;
    }

    public void buildLeaves(World world, int x, int y, int z, int size) {

        IBlockState b = world.getBlockState(new BlockPos(x, y - 2, z));
        IBlockState b1 = world.getBlockState(new BlockPos(x, y - 1, z));

        if ((b == Blocks.SAND.getDefaultState() || b1 == Blocks.SAND.getDefaultState()) && !ConfigRTG.allowTreesToGenerateOnSand) {
            return;
        }

        if (b.getMaterial() == Material.GRASS || b.getMaterial() == Material.GROUND || (varSand && b.getMaterial() == Material.SAND)) {
            if (b1 != Blocks.WATER.getDefaultState()) {
                if (!ConfigRTG.allowShrubsToGenerateBelowSurface) {

                    if (b1.getMaterial() != Material.AIR &&
                        b1.getMaterial() != Material.VINE &&
                        b1.getMaterial() != Material.PLANTS &&
                        b1 != Blocks.SNOW_LAYER.getDefaultState()) {
                        return;
                    }
                }

                for (int i = -size; i <= size; i++) {
                    for (int j = -1; j <= 1; j++) {
                        for (int k = -size; k <= size; k++) {
                            if (Math.abs(i) + Math.abs(j) + Math.abs(k) <= size) {
                                buildBlock(world, x + i, y + j, z + k, leaveBlock);
                            }
                        }
                    }
                }
                world.setBlockState(new BlockPos(x, y - 1, z), logBlock, 0);
            }
        }
    }

    public void buildBlock(World world, int x, int y, int z, IBlockState block) {

        IBlockState b = world.getBlockState(new BlockPos(x, y, z));
        if (b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.PLANTS || b == Blocks.SNOW_LAYER) {
            world.setBlockState(new BlockPos(x, y, z), block, 0);
        }
    }
}
