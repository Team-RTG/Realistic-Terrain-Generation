package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWoodland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPWoodland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.woodland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPWoodland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPWoodland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPWoodland(10f, 25f, 72f, 120f);
    }

    public class TerrainBOPWoodland extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainBOPWoodland(float hillStart, float landHeight, float baseHeight, float hillWidth) {

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
