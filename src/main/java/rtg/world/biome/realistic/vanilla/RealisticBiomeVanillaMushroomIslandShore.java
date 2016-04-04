package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIslandShore;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaMushroomIslandShore extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaMushroomIslandShore() {
        super(
                Biomes.mushroomIslandShore,
                Biomes.river
        );
        this.noLakes = true;
    }

    @Override
        protected SurfaceBase initSurface() {
        return new SurfaceVanillaMushroomIslandShore(this, 67, 0f);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainMarsh(x, y, simplex, 62f);
            }
        };
    }
}
