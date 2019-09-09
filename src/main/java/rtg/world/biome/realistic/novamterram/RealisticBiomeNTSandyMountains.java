package rtg.world.biome.realistic.novamterram;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.world.surface.SurfaceBase;


public class RealisticBiomeNTSandyMountains extends RealisticBiomeNTBaseMountain {

    public RealisticBiomeNTSandyMountains(Biome biome) {

        super(biome);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPMountainPeaks(getConfig(), Blocks.SAND.getDefaultState(), //Block top
            Blocks.SANDSTONE.getDefaultState(), //Block filler,
            Blocks.SAND.getDefaultState(), //IBlockState mixTop,
            Blocks.SANDSTONE.getDefaultState(), //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }
}
