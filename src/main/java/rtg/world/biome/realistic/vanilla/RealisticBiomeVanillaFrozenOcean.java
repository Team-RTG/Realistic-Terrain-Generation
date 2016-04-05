package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaFrozenOcean() {

        super(
                Biomes.frozenOcean,
                Biomes.river
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainOcean(x, y, simplex, river, 50f);
            }
        };
    }
    
    @Override
        protected SurfaceBase initSurface() {
        return new SurfaceVanillaFrozenOcean(this, 20f, 0.2f);
    }

    @Override
    protected void initDecos()
    {

    }
    
    @Override
    protected void initProperties()
    {
		config.addBlock(config.MIX_BLOCK).setDefault(Blocks.gravel.getDefaultState());
        this.config.SURFACE_WATER_LAKE_CHANCE.setDefault(0);
        this.config.SURFACE_LAVA_LAKE_CHANCE.setDefault(0);
    }
}
