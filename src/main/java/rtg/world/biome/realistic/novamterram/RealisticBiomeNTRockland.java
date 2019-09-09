package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;


public class RealisticBiomeNTRockland extends RealisticBiomeNTBaseExtremeHills {

    public RealisticBiomeNTRockland(Biome biome) {

        super(biome);
    }

    @Override
    protected IBlockState getMixBlock3() {

        return Blocks.STONE.getDefaultState();
    }
}
