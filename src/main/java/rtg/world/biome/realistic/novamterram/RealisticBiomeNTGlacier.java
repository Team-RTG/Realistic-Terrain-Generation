package rtg.world.biome.realistic.novamterram;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.world.surface.SurfaceBase;


public class RealisticBiomeNTGlacier extends RealisticBiomeNTBaseGlacier {

    public RealisticBiomeNTGlacier(Biome biome) {

        super(biome, RiverType.FROZEN, BeachType.COLD);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPGlacier(getConfig(), Blocks.SNOW.getDefaultState(), Blocks.ICE.getDefaultState(),
            Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(),
            Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(),
            60f, -0.14f, 14f, 0.25f
        );
    }
}
