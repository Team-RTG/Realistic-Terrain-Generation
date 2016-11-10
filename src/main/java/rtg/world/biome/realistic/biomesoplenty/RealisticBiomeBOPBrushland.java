package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBrushland;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPBrushland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.brushland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBrushland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPBrushland(config, biome.topBlock, biome.fillerBlock, Blocks.SAND.getDefaultState(), 13f, 0.27f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBrushland();
    }

    public class TerrainBOPBrushland extends TerrainBase {

        private float baseHeight = 76f;
        private float hillStrength = 20f;

        public TerrainBOPBrushland() {

        }

        public TerrainBOPBrushland(float bh, float hs) {

            baseHeight = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

            float m = hills(x, y, hillStrength, simplex, river);

            return baseHeight + groundNoise + m;
        }
    }
}
