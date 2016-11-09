package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPChaparral;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPChaparral extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.chaparral.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPChaparral(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPChaparral(),
            new SurfaceBOPChaparral(config, biome.topBlock, biome.fillerBlock, Blocks.SAND.getDefaultState(), 26f, 0.35f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPChaparral();
    }

    public class TerrainBOPChaparral extends TerrainBase {

        private float baseHeight = 76f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 40f;
        private float smoothHillWavelength = 60f;
        private float smoothHillStrength = 30f;


        private SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
        private float wavelength = 10f;// of jitter
        private float amplitude = 2f;// of jitter

        public TerrainBOPChaparral() {

        }

        public TerrainBOPChaparral(float bh, float hs) {

            baseHeight = bh;
            peakyHillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

            //float m = hills(x, y, peakyHillStrength, simplex, river);

            simplex.riverJitter().evaluateNoise((float) x / wavelength, (float) y / wavelength, jitter);
            int pX = (int) Math.round(x + jitter.deltax() * amplitude);
            int pY = (int) Math.round(y + jitter.deltay() * amplitude);
            float h = this.terrainGrasslandHills(pX, pY, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

            return groundNoise + h;
        }
    }
}
