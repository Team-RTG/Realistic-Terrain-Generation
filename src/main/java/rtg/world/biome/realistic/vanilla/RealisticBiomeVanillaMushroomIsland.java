package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaMushroomIsland() {
        super(
                Biomes.mushroomIsland,
                Biomes.river
        );
        this.noLakes = true;
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

    @Override
        protected SurfaceBase initSurface() {
        return new SurfaceVanillaMushroomIsland(this, 67, 0f);
    }

    @Override
    protected void initDecos()
    {

    }
    
    @Override
    protected void initProperties()
    {
    	config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.mycelium.getDefaultState());
    }
}
