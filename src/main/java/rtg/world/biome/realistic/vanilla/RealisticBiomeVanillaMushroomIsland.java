package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {
    public static IBlockState topBlock = Biomes.mushroomIsland.topBlock;
    public static IBlockState fillerBlock = Biomes.mushroomIsland.fillerBlock;

    public RealisticBiomeVanillaMushroomIsland() {
        super(
                Biomes.mushroomIsland,
                Biomes.river
        );
        this.noLakes = true;
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaMushroomIsland(config, topBlock, fillerBlock, 67, topBlock, 0f);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainGrasslandFlats(x, y, simplex, river, 40f, 25f, 68f);
            }
        };
    }
}
