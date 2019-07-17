package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightVariation;


public class RealisticBiomeTRAVGreenSwamp extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVGreenSwamp(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPMarsh();
    }

    public static class TerrainBOPMarsh extends TerrainBase {

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
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return baseHeight + variation.added(rtgWorld, x, y) + smallVariation.added(rtgWorld, x, y);
        }
    }
}
