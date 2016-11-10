package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPAlps;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPAlps extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.alps.get();
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBOPAlps(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPAlps(config, biome.topBlock, biome.fillerBlock, 0.45f)
        );

        this.generatesEmeralds = true;
        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPAlps();
    }

    public class TerrainBOPAlps extends TerrainBase {

        // the BoP version has steep slopes and a flat area on top. The RTG version will
        private float start = 0f;// this puts a minimum on "ruggedness" on the top. We want to allow flats
        private float height = 40f; // sets the variability range
        private float width = 80f; // width of irregularity noise on top. We want low, for a lot of features.

        public TerrainBOPAlps() {

            base = 120f;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, start, width, height, base - 62f);
            //return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
        }
    }
}
