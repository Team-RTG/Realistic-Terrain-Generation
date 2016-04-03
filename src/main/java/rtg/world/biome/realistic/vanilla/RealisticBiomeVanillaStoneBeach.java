package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaStoneBeach() {
        super(
                Biomes.stoneBeach,
                Biomes.river
        );
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaStoneBeach(config, biome.config.TOP_BLOCK.get(), biome.config.FILL_BLOCK.get(), true, Blocks.gravel.getDefaultState(), 1f, 1.5f, 85f, 20f, 4f);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainBeach(x, y, simplex, river, 180f, 35f, 63f);
            }
        };
    }
}
