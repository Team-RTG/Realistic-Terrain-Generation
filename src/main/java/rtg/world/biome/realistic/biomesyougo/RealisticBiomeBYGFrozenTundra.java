package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGFrozenTundra;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGFrozenTundra extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBYGFrozenTundra(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBYGFrozenTundra(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGFrozenTundra();
    }

    public class TerrainBYGFrozenTundra extends TerrainBase {

        private float start = 0f;// this puts a minimum on "ruggedness" on the top. We want to allow flats
        private float height = 30f; // sets the variability range
        private float width = 90f; // width of irregularity noise on top. We want low, for a lot of features.

        public TerrainBYGFrozenTundra() {

            base = 110f;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, start, width, height, base - 62f);
            //return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
        }
    }
}
