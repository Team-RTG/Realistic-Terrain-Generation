package rtg.world.biome.realistic.mistbiomes;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeMBColdMistTaiga extends RealisticBiomeMBBase {

    public RealisticBiomeMBColdMistTaiga(Biome biome) {

        super(biome, RiverType.FROZEN, BeachType.COLD);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaColdTaiga();
    }

    public class TerrainVanillaColdTaiga extends TerrainBase {

        public TerrainVanillaColdTaiga() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, rtgWorld, river, 66f);
        }
    }
}
