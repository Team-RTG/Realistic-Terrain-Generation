package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;


public class RealisticBiomeNTStoneFields extends RealisticBiomeNTBasePlains {

    public RealisticBiomeNTStoneFields(Biome biome) {

        super(biome);
    }

    @Override
    protected IBlockState getMixBlock3() {

        return Blocks.STONE.getDefaultState();
    }
}
