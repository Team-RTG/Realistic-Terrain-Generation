package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsHighland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeACDarklandsHighland extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands_hills;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklandsHighland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceACDarklandsHighland(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACDarklandsHighland(10f, 120f, 10f, 200f);
    }

    public class TerrainACDarklandsHighland extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainACDarklandsHighland(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, start, width, height, base);
        }
    }
}
