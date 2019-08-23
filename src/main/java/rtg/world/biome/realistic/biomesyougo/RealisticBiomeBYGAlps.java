package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeBYGAlps extends RealisticBiomeBYGBase {

    public RealisticBiomeBYGAlps(Biome biome) {

        super(biome, RiverType.FROZEN, BeachType.COLD);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().SURFACE_WATER_LAKE_MULT.set(0.0f);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGBiome();
    }

    public static class TerrainBYGBiome extends TerrainBase {

        // the BoP version has steep slopes and a flat area on top. The RTG version will mimic that.
        private float start = 0f;// this puts a minimum on "ruggedness" on the top. We want to allow flats
        private float height = 40f; // sets the variability range
        private float width = 80f; // width of irregularity noise on top. We want low, for a lot of features.

        public TerrainBYGBiome() {

            base = 120f;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainHighland(x, y, rtgWorld, river, start, width, height, base - 62f);
            //return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
        }
    }
}
