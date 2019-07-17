package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.api.world.terrain.heighteffect.MountainsWithPassesEffect;


public class RealisticBiomeBYGSkyrisHighlands extends RealisticBiomeBYGBase {

    public RealisticBiomeBYGSkyrisHighlands(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPMountainPeaks(120f, 130f);
    }

    public static class TerrainBOPMountainPeaks extends TerrainBase {

        private float width;
        private float strength;
        private float terrainHeight;
        private float spikeWidth = 30;
        private float spikeHeight = 50;
        private HeightEffect heightEffect;

        public TerrainBOPMountainPeaks(float mountainWidth, float mountainStrength) {

            this(mountainWidth, mountainStrength, 90f);
        }

        public TerrainBOPMountainPeaks(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
            MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
            mountainEffect.mountainHeight = strength;
            mountainEffect.mountainWavelength = width;
            mountainEffect.spikeHeight = this.spikeHeight;
            mountainEffect.spikeWavelength = this.spikeWidth;


            heightEffect = new JitterEffect(7f, 10f, mountainEffect);
            heightEffect = new JitterEffect(3f, 6f, heightEffect);

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return riverized(heightEffect.added(rtgWorld, x, y) + terrainHeight, river);
        }
    }
}
