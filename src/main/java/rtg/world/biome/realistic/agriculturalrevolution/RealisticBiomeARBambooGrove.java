package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.agriculturalrevolution.SurfaceARBambooGrove;
import rtg.world.gen.terrain.*;

public class RealisticBiomeARBambooGrove extends RealisticBiomeARBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeARBambooGrove(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.agriculturalrevolution.TerrainARBambooGrove(),
            new SurfaceARBambooGrove(config, biome.topBlock, biome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.15f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainARBambooGrove();
    }

    public class TerrainARBambooGrove extends TerrainBase {

    /* Basic idea: High hilly terrain mixed with ground-noisy flats
     * using a transition that also generates the hills
     */

        private float hillockWavelength = 30;
        private float hillockBoost = 5;
        private float hillockVariance = 10;
        private float hillockSpikeWidth = 8;
        private float hillockSpikeHeight = 4;
        private float terrainBase = 68;

        private JitterEffect biomeHeight;// this includes the base


        public TerrainARBambooGrove() {
            // bumpy hills on top
            BumpyHillsEffect onTop = new BumpyHillsEffect();
            onTop.hillHeight = hillockVariance;
            onTop.hillWavelength = hillockWavelength;
            onTop.spikeHeight = hillockSpikeHeight;
            onTop.spikeWavelength = hillockSpikeWidth;
            onTop.hillOctave = 1;// same octave as variableRuggedness

            // plus raised a bit
            HeightEffect hillLevel = onTop.plus(new RaiseEffect(hillockBoost + terrainBase));

            // but only
            VariableRuggednessEffect hills = new VariableRuggednessEffect();
            hills.ruggedTerrain = hillLevel;
            hills.smoothTerrain = new RaiseEffect(terrainBase);
            hills.octave = 1;// just to make it clear;
            hills.startTransition = 0.1f;
            hills.transitionWidth = 0.35f;
            hills.wavelength = hillockWavelength;

            HeightEffect unJittered = hills.plus(new GroundEffect(6f));

            // and lets scramble it a bit

            biomeHeight = new JitterEffect();
            biomeHeight.amplitude = 2;
            biomeHeight.wavelength = 5;
            biomeHeight.jittered = unJittered;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            float result = biomeHeight.added(simplex, cell, x, y);
            if (result < 60) {
                throw new RuntimeException();
            }
            return result;
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
        }
    }
}
