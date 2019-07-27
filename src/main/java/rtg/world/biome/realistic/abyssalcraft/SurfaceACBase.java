package rtg.world.biome.realistic.abyssalcraft;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import net.minecraft.block.state.IBlockState;
import rtg.api.config.BiomeConfig;
import rtg.api.world.surface.SurfaceBase;


public abstract class SurfaceACBase extends SurfaceBase {

    public SurfaceACBase(BiomeConfig config, IBlockState top, IBlockState fill) {

        super(config, top, fill);
    }

    @Override
    protected IBlockState hcStone() {

        return ACBlocks.stone.getDefaultState();
    }

    @Override
    protected IBlockState hcCobble() {

        return ACBlocks.cobblestone.getDefaultState();
    }
}
