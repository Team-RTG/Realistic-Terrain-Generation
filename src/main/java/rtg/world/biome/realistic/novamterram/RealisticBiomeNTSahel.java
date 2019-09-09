package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;


public class RealisticBiomeNTSahel extends RealisticBiomeNTBaseDesert {

    public RealisticBiomeNTSahel(Biome biome) {

        super(biome);
    }

    @Override
    protected IBlockState getMixBlock2() {

        return Blocks.GRASS.getDefaultState();
    }

    @Override
    protected IBlockState getMixBlock3() {

        return Blocks.GRASS.getDefaultState();
    }
}
