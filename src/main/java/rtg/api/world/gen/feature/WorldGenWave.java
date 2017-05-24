package rtg.api.world.gen.feature;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.WorldUtil;

public class WorldGenWave extends WorldGenerator {

    private IBlockState waveBlock;
    private int waveLength;
    private int direction;

    public WorldGenWave(IBlockState waveBlock, int waveLength) {

        this.waveBlock = waveBlock;
        this.waveLength = waveLength;
        this.direction = 0; // The direction of the wave (0 = X; 1 = Z)
    }

    public WorldGenWave(int waveLength) {

        this(Blocks.WATER.getDefaultState().withProperty(BlockLiquid.LEVEL, 6), waveLength);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        return this.generate(world, rand, pos.getX(), pos.getY(), pos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {

        IBlockState g = world.getBlockState(new BlockPos(x, y - 1, z));
        if (g.getMaterial() != Material.WATER || g == this.waveBlock) {
            return false;
        }

        WorldUtil worldUtil = new WorldUtil(world);
        int i;
        IBlockState b;

        ArrayList<Integer> aX = new ArrayList<Integer>();
        ArrayList<Integer> aY = new ArrayList<Integer>();
        ArrayList<Integer> aZ = new ArrayList<Integer>();
        ArrayList<IBlockState> aBlock = new ArrayList<IBlockState>();

        for (i = 0; i < waveLength; i++) {
            x -= direction == 0 ? 1 : 0;
            z -= direction == 1 ? 1 : 0;
        }

        for (i = 0; i < waveLength * 2; i++) {

            // Store the wave information instead of placing it straight away.
            aX.add(x);
            aY.add(y);
            aZ.add(z);
            aBlock.add(waveBlock);

            x += direction == 0 ? 1 : 0;
            z += direction == 1 ? 1 : 0;
        }

        for (int i1 = 0; i1 < aBlock.size(); i1++) {
            world.setBlockState(new BlockPos(aX.get(i1).intValue(), aY.get(i1).intValue(), aZ.get(i1).intValue()), aBlock.get(i1), 0);
        }

        return true;
    }

    public IBlockState getWaveBlock() {

        return waveBlock;
    }

    public WorldGenWave setWaveBlock(IBlockState waveBlock) {

        this.waveBlock = waveBlock;
        return this;
    }
}
