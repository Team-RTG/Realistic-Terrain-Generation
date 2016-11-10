package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.agriculturalrevolution.SurfaceARTropicalHills;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeARTropicalHills extends RealisticBiomeARBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeARTropicalHills(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new SurfaceARTropicalHills(config, Blocks.SAND.getDefaultState(), Blocks.SANDSTONE.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainARTropicalHills(10f, 80f, 68f, 200f);
    }

    public class TerrainARTropicalHills extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainARTropicalHills(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
        {
            return terrainHighland(x, y, simplex, cell, river, start, width, height, base - 62f);
        }
    }
}
