package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;


public class RealisticBiomeNTClayland extends RealisticBiomeNTBaseDesert {

    public RealisticBiomeNTClayland(Biome biome) {

        super(biome);
    }

    @Override
    protected IBlockState getMixBlock1() {

        return Blocks.HARDENED_CLAY.getDefaultState();
    }
}
