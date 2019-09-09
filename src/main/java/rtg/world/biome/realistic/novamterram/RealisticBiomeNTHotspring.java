package rtg.world.biome.realistic.novamterram;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.world.surface.SurfaceBase;


public class RealisticBiomeNTHotspring extends RealisticBiomeNTBaseSwamp {

    public RealisticBiomeNTHotspring(Biome biome) {

        super(biome);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaSwampland(getConfig(), Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState());
    }
}
