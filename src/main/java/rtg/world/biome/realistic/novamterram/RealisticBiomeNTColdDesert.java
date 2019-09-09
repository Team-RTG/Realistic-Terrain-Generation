package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.util.BlockUtil;
import rtg.api.world.surface.SurfaceBase;


public class RealisticBiomeNTColdDesert extends RealisticBiomeNTBaseDesert {

    public RealisticBiomeNTColdDesert(Biome biome) {

        super(biome, RiverType.FROZEN, BeachType.COLD);
    }

    @Override
    protected IBlockState getMixBlock1() {

        return BlockUtil.getBlockStateFromCfgString("nt:cold_sand", Blocks.SAND.getDefaultState());
    }

    @Override
    protected IBlockState getMixBlock2() {

        return Blocks.SAND.getDefaultState();
    }

    @Override
    protected IBlockState getMixBlock3() {

        return BlockUtil.getBlockStateFromCfgString("nt:cold_sand", Blocks.SAND.getDefaultState());
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceNTGeneric(getConfig(), Blocks.SAND.getDefaultState(), baseBiome().fillerBlock,
            0f, 1.5f, 60f, 65f, 1.5f,
            getMixBlock1(), 0.6f, getMixBlock2(), -0.2f, getMixBlock3(), -0.4f);
    }
}
