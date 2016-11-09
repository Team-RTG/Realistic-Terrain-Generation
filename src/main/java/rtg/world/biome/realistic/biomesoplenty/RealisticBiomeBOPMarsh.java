package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMarsh;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPMarsh extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.marsh.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMarsh(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPMarsh(),
            new SurfaceBOPMarsh(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPMarsh();
    }

    public class TerrainBOPMarsh extends TerrainBase {

        private float baseHeight = 62f;
        private HeightVariation variation;
        private HeightVariation smallVariation;

        public TerrainBOPMarsh() {

            variation = new HeightVariation();
            variation.height = 1.5f;
            variation.wavelength = 20;
            variation.octave = 0;

            smallVariation = new HeightVariation();
            smallVariation.height = 1.5f;
            smallVariation.wavelength = 10;
            smallVariation.octave = 0;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return baseHeight + variation.added(simplex, cell, x, y) + smallVariation.added(simplex, cell, x, y);
        }
    }
}
