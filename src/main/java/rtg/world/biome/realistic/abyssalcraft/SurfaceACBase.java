package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.block.state.IBlockState;

import com.shinoow.abyssalcraft.api.block.ACBlocks;

import rtg.api.config.BiomeConfig;
import rtg.api.world.IRTGWorld;
import rtg.api.world.surface.SurfaceBase;

public abstract class SurfaceACBase extends SurfaceBase {

    public SurfaceACBase(BiomeConfig config, IBlockState top, IBlockState fill) {

        super(config, top, fill);
    }

    @Override
    protected IBlockState hcStone(IRTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        return ACBlocks.darkstone.getDefaultState();
    }

    @Override
    protected IBlockState hcCobble(IRTGWorld rtgWorld, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {

        return ACBlocks.darkstone_cobblestone.getDefaultState();
    }
}
