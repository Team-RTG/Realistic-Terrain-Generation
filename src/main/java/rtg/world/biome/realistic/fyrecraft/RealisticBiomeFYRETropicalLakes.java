package rtg.world.biome.realistic.fyrecraft;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightVariation;
import rtg.api.world.terrain.heighteffect.JitterEffect;


public class RealisticBiomeFYRETropicalLakes extends RealisticBiomeFYREBase {

    public RealisticBiomeFYRETropicalLakes(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPLandOfLakes();
    }

    public class TerrainBOPLandOfLakes extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;
        private HeightVariation small;
        private HeightVariation large;
        private JitterEffect largeJitter;
        private JitterEffect smallJitter;


        public TerrainBOPLandOfLakes() {
            super(63f);
            small = new HeightVariation();
            small.height = 2.5f;
            small.octave = 1;
            small.wavelength = 10;

            large = new HeightVariation();
            large.height = 5;
            large.octave = 2;
            large.wavelength = 20;

            smallJitter = new JitterEffect();
            smallJitter.amplitude = 2;
            smallJitter.wavelength = 9;
            smallJitter.jittered = large.plus(small);

            largeJitter = new JitterEffect();
            largeJitter.amplitude = 4;
            largeJitter.wavelength = 18;
            largeJitter.jittered = smallJitter;

        }

        public TerrainBOPLandOfLakes(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            return riverized(largeJitter.added(rtgWorld, x, y) + this.base, river);
            //return terrainRollingHills(x, y, rtgWorld.simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 0f);
        }
    }
}
