package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropicalRainforest;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPTropicalRainforest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.tropical_rainforest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPTropicalRainforest(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPTropicalRainforest(0f, 60f, 68f, 200f),
            new SurfaceBOPTropicalRainforest(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPTropicalRainforest(0f, 60f, 68f, 200f);
    }

    public class TerrainBOPTropicalRainforest extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainBOPTropicalRainforest(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, start, width, height, base - 62f);
        }
    }
}
