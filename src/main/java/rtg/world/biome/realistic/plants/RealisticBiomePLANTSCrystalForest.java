package rtg.world.biome.realistic.plants;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.surface.SurfaceGeneric;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomePLANTSCrystalForest extends RealisticBiomePLANTSBase {

    public RealisticBiomePLANTSCrystalForest(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainPLANTSCrystalForest(); //(58f, 80f, 36f)
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceGeneric(getConfig(), this.baseBiome().topBlock, this.baseBiome().fillerBlock);
    }

    public class TerrainPLANTSCrystalForest extends TerrainBase {

        private float baseHeight = 72f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 20f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 10f;

        public TerrainPLANTSCrystalForest() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld);

            float h = terrainGrasslandHills(x, y, rtgWorld, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

            return riverized(groundNoise + h, river);
        }
    }
}
