package rtg.world.gen.surface.abyssalcraft;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.api.block.ACBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceACBase extends SurfaceBase {

    public SurfaceACBase(BiomeConfig config, IBlockState top, IBlockState fill) {

        super(config, top, fill);
    }

    @Override
    protected IBlockState hcStone(World world, int i, int j, int x, int y, int k) {

        return ACBlocks.darkstone.getDefaultState();
    }

    @Override
    protected IBlockState hcCobble(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {

        return ACBlocks.darkstone_cobblestone.getDefaultState();
    }
}
