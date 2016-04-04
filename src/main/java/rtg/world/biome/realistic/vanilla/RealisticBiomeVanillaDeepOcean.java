package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaDeepOcean() {

        super(
                Biomes.deepOcean,
                Biomes.river
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainOcean(x, y, simplex, river, 40f);
            }
        };
    }
    
    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaDeepOcean(this);
    }

    @Override
    protected void initDecos()
    {

    }
    
    @Override
    protected void initProperties()
    {
		config.addBlock(config.MIX_BLOCK).setDefault(Blocks.clay.getDefaultState());
    }
}
