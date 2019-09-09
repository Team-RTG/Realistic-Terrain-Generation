package rtg.world.biome.realistic.novamterram;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.world.surface.SurfaceBase;


public class RealisticBiomeNTStoneMountains extends RealisticBiomeNTBaseMountain {

    public RealisticBiomeNTStoneMountains(Biome biome) {

        super(biome);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPMountainPeaks(getConfig(), Blocks.STONE.getDefaultState(), //Block top
            Blocks.STONE.getDefaultState(), //Block filler,
            Blocks.COBBLESTONE.getDefaultState(), //IBlockState mixTop,
            Blocks.STONE.getDefaultState(), //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }
}
