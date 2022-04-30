package rtg.world.biome.realistic.sakura;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.spookybiomes.RealisticBiomeSBWitchwoodForest;

public class RealisticBiomeSAKURAMapleForest extends RealisticBiomeSAKURABase {

    public RealisticBiomeSAKURAMapleForest(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPOminousWoods(65f, 80f, 48f);
    }

    public static class TerrainBOPOminousWoods extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;

        public TerrainBOPOminousWoods(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainRollingHills(x, y, rtgWorld, river, hillStrength, maxHeight, groundNoiseAmplitudeHills, 0f);
        }
    }
}
