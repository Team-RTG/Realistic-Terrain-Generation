package rtg.world.biome.realistic.defiledlands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.surface.SurfaceBase;


public abstract class SurfaceDLBase extends SurfaceBase {

    public SurfaceDLBase(BiomeConfig config, IBlockState top, IBlockState fill) {

        super(config, top, fill);
    }

    @Override
    protected IBlockState hcStone() {

        return BlockUtil.getBlockStateFromCfgString("defiledlands:stone_defiled", super.hcStone());
    }

    @Override
    protected IBlockState hcCobble() {

        return BlockUtil.getBlockStateFromCfgString("defiledlands:stone_defiled", super.hcCobble());
    }

    @Override
    protected IBlockState getShadowStoneBlock() {

        return BlockUtil.getBlockStateFromCfgString("defiledlands:stone_defiled", super.getShadowStoneBlock());
    }

    @Override
    protected IBlockState getShadowDesertBlock() {

        return BlockUtil.getBlockStateFromCfgString("defiledlands:sandstone_defiled", super.getShadowDesertBlock());
    }
}
