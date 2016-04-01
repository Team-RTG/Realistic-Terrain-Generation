package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenRiver;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaFrozenRiver extends RealisticBiomeVanillaBase {
    public static IBlockState topBlock = Biomes.frozenRiver.topBlock;
    public static IBlockState fillerBlock = Biomes.frozenRiver.fillerBlock;

    public RealisticBiomeVanillaFrozenRiver() {
        super(
                Biomes.frozenRiver,
                Biomes.frozenRiver
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaFrozenRiver(config);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainFlatLakes(x, y, simplex, river, 3f, 60f);
            }
        };
    }
}
